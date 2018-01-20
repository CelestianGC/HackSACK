package hacksack;

import java.beans.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import org.jdom.*;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class CreatureCombat
    extends JPanel {
  public CreatureCore oCreature = null;
  public HackSackFrame oParent = null;
  public JLabel lACHit = new JLabel("10");
  public JLabel lDamage = new JLabel("0");
  public JLabel lCombat = new JLabel("<none>");

  public CreatureCombatButton bAttackButton = null;
  public JComboBox jWeaponType = null;
  public JComboBox jWeaponUsed = null;

  public JSpinner jToHitMod = null;
  public JSpinner jTotalMod = null;
  public JTextField jDamageDice = null;

//  public JSpinner jWeaponSpeed = null;
//  public JSpinner jInitMod = null;
//  public JLabel lInit = new JLabel("INIT");
  public JSpinner jModPenetration = null;
  public JSpinner jModFumble = null;
  public JSpinner jModCrit = null;
  public int nInit = 0;
  public JSpinner jSpecRoll = null;

  public int nWeaponTypeIndex = 0; // temp variable, not saved for now

  public JLabel jLabelToHitModDetails = null;
  public JLabel jLabelToDamModDetails = null;
  public JLabel jLabelDamageDiceDetails = null;
  public int nWeaponSelectedID = -1;

//  public JTabbedPane jThisAttackTab = null;

// ------------------
  public CreatureCombat() {
    Font fFont = new Font("Dialog", Font.PLAIN, 9);

//    jThisAttackTab = new JTabbedPane();

    lDamage.setForeground(Color.RED);
    lDamage.setFont(new Font("Dialog", Font.BOLD, 12));

    bAttackButton = new CreatureCombatButton(this);
    bAttackButton.setFont(fFont);

    jWeaponType = new JComboBox();
    jWeaponUsed = new JComboBox();
    jWeaponUsed.setRenderer(new CellRendererGearBox());
    jWeaponUsed.addActionListener(new CreatureCombat_jWeaponUsed_actionAdapter(this));

    SpinnerNumberModel mTotalMod = new SpinnerNumberModel(0, -100, 100, 1);
    jTotalMod = new JSpinner(mTotalMod);
    jTotalMod.setFont(fFont);
    jTotalMod.addChangeListener(new jAttackModsListener_changeAdapter(this,
        "jTotalMod"));

    SpinnerNumberModel mToHitMod = new SpinnerNumberModel(0, -20, 20, 1);
    jToHitMod = new JSpinner(mToHitMod);
    jToHitMod.setFont(fFont);
    jToHitMod.addChangeListener(new jAttackModsListener_changeAdapter(this,
        "jToHitMod"));

    jDamageDice = new JTextField();
    jDamageDice.setText("1d4"); // default value, 1d4
    jDamageDice.addFocusListener(new CreatureCombat_jDamageDice_focusAdapter(this));
    jDamageDice.addActionListener(new CreatureCombat_jDamageDice_actionAdapter(this));

    SpinnerNumberModel mModPenetration = new SpinnerNumberModel(0, 0, 5, 1);
    jModPenetration = new JSpinner(mModPenetration);
    jModPenetration.setFont(fFont);
    jModPenetration.addChangeListener(new jAttackModsListener_changeAdapter(this,
        "jModPenetration"));

    SpinnerNumberModel mModFumble = new SpinnerNumberModel(0, 0, 10, 1);
    jModFumble = new JSpinner(mModFumble);
    jModFumble.setFont(fFont);
    jModFumble.addChangeListener(new jAttackModsListener_changeAdapter(this,
        "jModFumble"));

    SpinnerNumberModel mModCrit = new SpinnerNumberModel(0, 0, 5, 1);
    jModCrit = new JSpinner(mModCrit);
    jModCrit.setFont(fFont);
    jModCrit.addChangeListener(new jAttackModsListener_changeAdapter(this,
        "jModCrit"));

    jSpecRoll = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
    jSpecRoll.setFont(fFont);

    jLabelToHitModDetails = new JLabel("");
    jLabelDamageDiceDetails = new JLabel("");
    jLabelToDamModDetails = new JLabel("");

    lACHit.setToolTipText("Hit,Miss (AC HIT)");

//    jAttackModsListener_stateChanged(null, null); // force it to set values
  }

  public CreatureCombat(HackSackFrame oParent, CreatureCore oOriginalCreature) {
    this();
    this.oCreature = oOriginalCreature;
    this.oParent = oParent;
    // build up weapon combo box
    jWeaponType.removeAllItems();
    jWeaponType.addItem("Monster");
    for (int i = 0; i < oParent.gmWeaponTable.length; i++) {
      jWeaponType.addItem(oParent.gmWeaponTable[i]);
    }
    jWeaponType.setSelectedIndex(0);
    //-- end build weapontype
  }

  public void updateAttack(CreatureCombat thisAttack) {
//    String sCombatText = thisAttack.bAttackButton.getText();
    int nTargetIndex = thisAttack.oCreature.jAttackingThisPerson.
        getSelectedIndex();

    int nSpecificRoll = Integer.parseInt(jSpecRoll.getValue().toString());
    jSpecRoll.setValue(new Integer(0));

    // set damage to either weapon or creature claw/claw type
    String sDamageDice = thisAttack.getSelectedWeaponDamage();

    TablePlayer oTarget = null;
    if (nTargetIndex > 0) {
      oTarget = (TablePlayer) oCreature.jAttackingThisPerson.getSelectedItem();
    }

    thisAttack.initSetAttackDeactive();

    int nDiceTotalModifier = Integer.parseInt(thisAttack.jTotalMod.getModel().
                                              getValue().toString());
    int nToHitAdj = Integer.parseInt(thisAttack.jToHitMod.getModel().getValue().
                                     toString());
    int nCritHit = Integer.parseInt(thisAttack.jModCrit.getModel().getValue().
                                    toString());
    int nFumble = Integer.parseInt(thisAttack.jModFumble.getModel().getValue().
                                   toString());
    int nPenetration = Integer.parseInt(thisAttack.jModPenetration.getModel().
                                        getValue().toString());
    int nRollTotal = 0;

    int nClass = thisAttack.oCreature.jClassSelect.getSelectedIndex();
    int nToHitRank = AttackRank.GetToHitRating(nClass,
                                               thisAttack.oCreature.nLevel, 0);

    String sLocationHitFlavorText = null;
    Dicer dDicer = new Dicer();

    // setup attacking a specific player
    int nTargetAC = 10; // default set if using 10, this is AC of the person creature is attacking
    if (oTarget != null) { // if index != 0 (just default attack) then we find player and get aC
      int nTargetACIndex = thisAttack.oCreature.jTargetAC.getSelectedIndex();
      nTargetAC = Integer.parseInt(oTarget.nAC[nTargetACIndex].getValue().
                                   toString());
      sDamageDice = getSelectedWeaponDamage();
    }
    // end specific target setup

    if (nCritHit == 0) {
      nCritHit = 20;
    }
    else {
      nCritHit = (20 - nCritHit);
    }
    nFumble += 1;

    // if nSpecificRoll > 0 use it.
    int nToHitRoll = ( (nSpecificRoll > 0) ? (nSpecificRoll) : (dDicer.d20()));
    dDicer.setLastDiceDescription("Attack Roll = ");

    AttackRank oA = new AttackRank();
    oA.getACHit(thisAttack.oCreature.oParent, nClass, nToHitRank,
                ( (nToHitRoll + nToHitAdj) < 1 ? 1 : nToHitRoll + nToHitAdj));
    String sAcHit = null;
    if (oA.nEndAC == oA.nStartAC) {
      sAcHit = "" + oA.nEndAC;
    }
    else {
      sAcHit = oA.nStartAC + ".." + oA.nEndAC;

      // clear last lCombat message
    }
    thisAttack.lCombat.setFont(new Font("Dialog", Font.PLAIN, 9));
    thisAttack.lCombat.setForeground(Color.BLACK);
    if (oTarget != null) {
      // set the flavor text for hit location

      CritLocation oCritLocation =  TableCritBody.critGetLocation(thisAttack.oCreature.oParent,
                                      oTarget.jSizeComboBox.getSelectedIndex(),
                                      thisAttack.oCreature.nBASESizeIndex,
                                      0);
      sLocationHitFlavorText = oCritLocation.sLocation;
      thisAttack.lCombat.setText(sLocationHitFlavorText);
    }
    else {
      thisAttack.lCombat.setText("<none>");

    }
    log(thisAttack, false, true,
        "-- Begin Attack for " + thisAttack.oCreature.sCreatureName + " --\n");
    String sMod = nToHitAdj > -1 ? "+" : "";
    log(thisAttack, false, false,
        "Attack roll " + nToHitRoll + sMod + nToHitAdj + " hit AC " + sAcHit);
    if (nToHitRoll >= nCritHit && !oParent.jADDCritCheckBox.isSelected()) { // roll 20, display crit
      log(thisAttack, false, false, " <CRITICAL HIT>");
    }
    else
    if (nToHitRoll <= nFumble && !oParent.jADDFumbleCheckBox.isSelected()) { // roll 1 display fumble
      log(thisAttack, false, false, " <FUMBLE>");
    }
    log(thisAttack, false, false, "\n");

//    sMod = nDiceModifier > -1 ? "+" : "";
    log(thisAttack, true, false,
        "Damage roll [" + sDamageDice + "] " + nDiceTotalModifier +
        " to total.\n");

    int nDiceNum = dDicer.getNumberDice(sDamageDice);
    int nDiceSize = dDicer.getNumDiceSides(sDamageDice);
    int nDamageDiceMod = dDicer.getPerDiceMod(sDamageDice);

    nRollTotal = dDicer.rollDamage(nDiceNum,nDiceSize,nDamageDiceMod,
                                   nDiceTotalModifier, nPenetration,
                                   oParent.jADDPenetrationCheckBox.isSelected());

    if (thisAttack.oCreature.oParent.jGMDetailsCheckBox.isSelected()) {
      for (int i = 0; i < dDicer.lDice.size(); i++) {
        Dice dDice = (Dice) dDicer.lDice.get(i);
        log(thisAttack, true, false, dDice.sDesc + dDice.nRolled + "\n");
      }
    }

    thisAttack.lDamage.setText("" + nRollTotal);

    if (nDiceTotalModifier != 0) {
      log(thisAttack, true, false,
          "\nMod to Total :" + Integer.toString(nDiceTotalModifier) + "\n");
    }
    log(thisAttack, false, false,
        "Total Damage :" + Integer.toString(nRollTotal) + "\n");

    // update AC hit label
    if (oTarget != null) {
      // display HIT or MISSED for this player
      // and AC that was hit in parenthesis
      if (oA.nEndAC <= nTargetAC) {
        // PLAYER hit
        thisAttack.lACHit.setFont(new Font("Dialog", Font.BOLD, 14));
        thisAttack.lACHit.setForeground(Color.RED);
        thisAttack.lACHit.setText("HIT"+
                                  " (" + sAcHit + ")");
//        thisAttack.lACHit.setText("HIT");
        thisAttack.lDamage.setForeground(Color.RED);
        thisAttack.lDamage.setFont(new Font("Dialog", Font.BOLD, 14));
        log(thisAttack, false, false,
            thisAttack.oCreature.sCreatureName + " hit " + oTarget.sCharacter +
            " for " + nRollTotal + " damage.\n");
        oTarget.playerLog(thisAttack.oCreature.sCreatureName + " hit " +
                          oTarget.sCharacter + " for " + nRollTotal +
                          " damage in the " + sLocationHitFlavorText + ".\n");

        oCreature.oParent.gplGroupLog.groupLog(thisAttack.oCreature.
                                               sCreatureName + " hit " +
                                               oTarget.sCharacter + " for " +
                                               nRollTotal +
                                               " damage in the " +
                                               sLocationHitFlavorText + ".\n");
      }
      else {
        int nShieldlessAC = Integer.parseInt(oTarget.nAC[oTarget.AC_SHIELDLESS].getValue().toString());
        int nNormalAC = Integer.parseInt(oTarget.nAC[oTarget.AC_NORMAL].getValue().toString());
        boolean bShieldHit = false;
        int nShieldAC = (nShieldlessAC - nNormalAC);
        if (oA.nEndAC <= (nTargetAC + nShieldAC))
          bShieldHit = true;
//oParent.DiceOutTextArea.append("Shieldless:"+nShieldlessAC+" nNormalAC:"+
//                                       nNormalAC+" nShieldADJ:"+nShieldAC+" nTargetAC:"+nTargetAC+
//                                       " nEndAC:"+oA.nEndAC+"\n");

        thisAttack.lACHit.setFont(new Font("Dialog", Font.PLAIN, 12));
        thisAttack.lACHit.setForeground(Color.BLUE);
        thisAttack.lACHit.setText("MISS"+
                                  (bShieldHit?"/HIT-SHIELD":"")+
                                  " (" + sAcHit + ")");

        thisAttack.lDamage.setFont(new Font("Dialog", Font.PLAIN, 14));
        thisAttack.lDamage.setForeground(Color.BLUE);
//        thisAttack.lDamage.setText(""); // commented cause they need to know shield hit damage

        log(thisAttack, false, false,
            thisAttack.oCreature.sCreatureName + " missed " +
            oTarget.sCharacter + ".\n");
        oTarget.playerLog(thisAttack.oCreature.sCreatureName + " missed " +
                          oTarget.sCharacter + ".\n");
      }

    }
    else {
      thisAttack.lACHit.setFont(new Font("Dialog", Font.BOLD, 14));
      thisAttack.lACHit.setForeground(Color.RED);
      thisAttack.lACHit.setText(sAcHit);
      thisAttack.lDamage.setFont(new Font("Dialog", Font.BOLD, 14));
      thisAttack.lDamage.setForeground(Color.RED);
    }

    if (nToHitRoll >= nCritHit && !oParent.jADDCritCheckBox.isSelected()) { // roll 20, display crit
      thisAttack.lCombat.setFont(new Font("Dialog", Font.BOLD, 14));
      thisAttack.lCombat.setForeground(Color.RED);
      thisAttack.lCombat.setText("CRITICAL");

      if (oTarget != null) {
        int nWeaponTypeIndex = 1;

        TableGear oWeapon = getSelectedWeapon(); // we have weapon in hand
        if (oWeapon != null)
          nWeaponTypeIndex = getSelectedWeaponDamageType(oWeapon);
          else
            nWeaponTypeIndex = thisAttack.jWeaponType.getSelectedIndex();
        if (nWeaponTypeIndex == 0) { // if its default type "monster" we default to slash
          nWeaponTypeIndex = 1;
        }

        CritResults oCrit = CritResults.GetCritResults(
            thisAttack.oCreature.oParent,
            nClass,
            thisAttack.oCreature.nAC,
            nToHitRank,
            Integer.parseInt(thisAttack.jToHitMod.getValue().toString()),
            oTarget.jSizeComboBox.getSelectedIndex(),
            thisAttack.oCreature.nBASESizeIndex,
            nWeaponTypeIndex, 0, 0, 0,
            false, sDamageDice,nRollTotal,thisAttack);

        // log this to crit display pane also
        thisAttack.oCreature.oParent.DisplayCriticalHitResults(oCrit,
            thisAttack, oTarget);

        DialogFancyCrit dlg = new DialogFancyCrit(thisAttack, oTarget, oCrit);
        Dimension dlgSize = dlg.panel1.getPreferredSize();
        Dimension frmSize = thisAttack.oCreature.oParent.getSize();
        Point loc = thisAttack.oCreature.oParent.getLocation();
        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.pack();
        dlg.setVisible(true);
//        dlg.show();

        thisAttack.bAttackButton.setFont(new Font("Dialog", Font.PLAIN, 9));
        thisAttack.bAttackButton.setForeground(Color.BLACK);
        thisAttack.bAttackButton.setText("Attack");
      }
    }
    else if (nToHitRoll <= nFumble && !oParent.jADDFumbleCheckBox.isSelected()) { // roll 1 display fumble
      thisAttack.lCombat.setFont(new Font("Dialog", Font.BOLD, 12));
      thisAttack.lCombat.setForeground(Color.BLUE);
      thisAttack.lCombat.setText("FUMBLE");
      String sText =
          thisAttack.oCreature.oParent.Fumble(thisAttack.oCreature.oParent.
                                              MyRandom(1000));
      DialogDetails dlg = new DialogDetails(sText,
                                            thisAttack.oCreature.sCreatureName +
                                            " fumbled!");
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = thisAttack.oCreature.oParent.getSize();
      Point loc = thisAttack.oCreature.oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.show();
    }

    // show dialog and warn about fatigue check.
    if (oCreature.oParent.jGMConfigFatigueCheckBox2.isSelected() &&
//        oCreature.initReadyForNextRound() &&
        oCreature.nFatigueFactor <= (oCreature.nCombatRounds) && // if the fatigue factor is less than current number of combat rounds
        !oCreature.bFatigued) {
      oCreature.oParent.ShowDone(this,
                                 oCreature.sCreatureName //+"("+oCreature.nFatigueFactor+") "+
                                 +" has been fighting for " +
                                 oCreature.nCombatRounds + " round" +
                                 (oCreature.nCombatRounds > 1 ? "s" : "") + " and will need to check fatigue (WIS+CON/2) or suffer an additional -1 STR/DEX each round now.");
      oCreature.bFatigued = true; // so we dont display this every round, not saved to BS
    }

    log(thisAttack, false, false, "-- End Attack --\n");

    /*
       // disabled this since we have a list of creatures now...

          // move viewport to next READY attacker
          if (isAnotherAttacker()) {
            if (oCreature.oParent.AskYN(oCreature.oParent.fBattleSheetFrame,
     "Move to next creature ready to attack?"))
              viewNextAttacker();
          }
     */

  }

  // log to the current display we use...
  public void log(CreatureCombat thisAttack, boolean bDetailed,
                  boolean bNewBlock, String sStr) {
    if (!thisAttack.oCreature.oParent.jGMAppendCheckBox.isSelected() &&
        bNewBlock) {
      thisAttack.oCreature.oParent.jGMTextArea.setText("");
    }
    if (bDetailed && thisAttack.oCreature.oParent.jGMDetailsCheckBox.isSelected()) {
      thisAttack.oCreature.oParent.jGMTextArea.append(sStr);
    }
    if (!bDetailed) {
      thisAttack.oCreature.oParent.jGMTextArea.append(sStr);

    }
  }

  // weaponused selection changed
  void jWeaponUsed_actionPerformed(ActionEvent e) {
    setDamageDiceDetails(getSelectedWeaponDamage());
  }

  // GetWeapon damage string, sets basedon target size or default medium
  String getSelectedWeaponDamage() {
    String sDamageDice = jDamageDice.getText(); ;

    Object oObj = jWeaponUsed.getSelectedItem();
    if (oObj instanceof TableGear) {
      TableGear oWeapon = (TableGear) oObj;

      // default to medium size damage if we're using a weapon
      // and dont have a target selected
      if (oCreature.jAttackingThisPerson.getSelectedIndex() == 0) {
        sDamageDice =
            oWeapon.jDamage[oWeapon.DAMAGE_SIZE_MEDIUM].getText();
      }
      else {
        // damage is weapon damage versus the size of the target
        TablePlayer oPlayer =
            (TablePlayer) oCreature.jAttackingThisPerson.getSelectedItem();
        if (oPlayer != null)
        sDamageDice =
            oWeapon.jDamage[oPlayer.jSizeComboBox.getSelectedIndex()].getText();
      }
    }

    return sDamageDice;
  }

  // return structure of selected weapon
  TableGear getSelectedWeapon() {
    TableGear oGear = null;

    Object oObj = jWeaponUsed.getSelectedItem();
    if (oObj instanceof TableGear) {
      TableGear oWeapon = (TableGear) oObj;
      oGear = oWeapon;
    }

    return oGear;
  }

  // getSelected weapon type hack,piece,crush..
  int getSelectedWeaponDamageType(TableGear oGear) {
    int nType = 1;

    if (oGear.sWeaponType.equalsIgnoreCase("H")) {
      nType = 1;
    }
    else
    if (oGear.sWeaponType.equalsIgnoreCase("C")) {
      nType = 2;
    }
    else
    if (oGear.sWeaponType.equalsIgnoreCase("P")) {
      nType = 3;
    }

    return nType;
  }

  public void setupWeaponUsedBox(ArrayList lGearList) {

    Object oSelectedObject = jWeaponUsed.getSelectedItem();
    jWeaponUsed.removeAllItems();
    jWeaponUsed.addItem("MONSTER-NATURAL");

    for (int i = 0; i < lGearList.size(); i++) {
      TableGear oGear = (TableGear) lGearList.get(i);
      if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_WEAPON)) {
        jWeaponUsed.addItem(oGear);
      }
    }

    if (nWeaponSelectedID != -1) {
      TableGear oGear = TableGear.GetGearFromID(oParent,nWeaponSelectedID);
      if (oGear != null)
        oSelectedObject = oGear;
      // we set it, now toss it.
      nWeaponSelectedID = -1;
    }

    if (oSelectedObject != null) {
      jWeaponUsed.setSelectedItem(oSelectedObject);
    }
    else {
      jWeaponUsed.setSelectedIndex(0);
    }
  }

  // find the next creature that has an ATTACK ready and move view port to it
/*  public void viewNextAttacker() {
    boolean bDone = false;
    for (int i = 0; i < oCreature.oParent.lCreatures.size() && !bDone; i++) {
      CreatureCore oC = (CreatureCore) oCreature.oParent.lCreatures.get(i);
      for (int j = 0; j < oC.lAttacks.size() && !bDone; j++) {
        CreatureCombat oAtk = (CreatureCombat) oC.lAttacks.get(j);
        if (oAtk.bAttackButton.getText().equals("ATTACK")) {
          oC.jNameLabel.scrollRectToVisible(
              oC.oParent.fBattleSheetFrame.jBattleSheetScrollPane1.
              getVisibleRect());
          bDone = true;
        }
      }

    }
  }

  // see if there is a creature ready to attack
  public boolean isAnotherAttacker() {
    boolean bDone = false;
    for (int i = 0; i < oCreature.oParent.lCreatures.size() && !bDone; i++) {
      CreatureCore oC = (CreatureCore) oCreature.oParent.lCreatures.get(i);
      for (int j = 0; j < oC.lAttacks.size() && !bDone; j++) {
        CreatureCombat oAtk = (CreatureCombat) oC.lAttacks.get(j);
        if (oAtk.bAttackButton.getText().equals("ATTACK")) {
          bDone = true;
        }
      }

    }
    return bDone;
  }
*/
  // is this attack ready to go (because init)
  public boolean initThisAttackReady() {
    return (bAttackButton.getText().equals("ATTACK"));
  }
  // set attack button red hot active!
  public void initSetAttackActive() {
    /* // disabled since we only list one creature at a time now
         JTabbedPane jTab = (JTabbedPane)bAttackButton.getParent().getParent();
         jTab.setSelectedIndex(0); // Attack box
     */
    bAttackButton.setFont(new Font("Dialog", Font.BOLD, 11));
    bAttackButton.setBackground(Color.BLACK);
    bAttackButton.setForeground(Color.RED);
    bAttackButton.setText("ATTACK");
    this.oCreature.bAggro = true;

    int nThisAttack = this.oCreature.lAttacks.indexOf(this);
    if (this.oCreature.jMyAttackTabPane.isVisible() &&
        this.oCreature.jMyAttackTabPane.getTabCount() > nThisAttack) {
      this.oCreature.jMyAttackTabPane.setForegroundAt(nThisAttack, Color.RED);
      this.oCreature.jMyAttackTabPane.setSelectedIndex(nThisAttack);
    }


    // jump scroll panel to the currently ready to attack NPC!
//    bAttackButton.scrollRectToVisible(
//        oCreature.oParent.fBattleSheetFrame.jBattleSheetScrollPane1.getVisibleRect());

    // lets try the hp section so you can see name also
//    oCreature.jNameLabel.scrollRectToVisible(
//        oCreature.oParent.fBattleSheetFrame.jBattleSheetScrollPane1.getVisibleRect());

    nInit = 0; // we ran this init so reset to 0
  }

  // set attack button red hot active!
  public void initSetAttackDeactive() {
    bAttackButton.setFont(new Font("Dialog", Font.PLAIN, 9));
    bAttackButton.setBackground(new Color(236, 233, 216));
    bAttackButton.setForeground(Color.BLACK);
    bAttackButton.setText("Attack");
    this.oCreature.bAggro = false;
    int nThisAttack = this.oCreature.lAttacks.indexOf(this);
    if (this.oCreature.jMyAttackTabPane.isVisible() &&
        this.oCreature.jMyAttackTabPane.getTabCount() > nThisAttack) {
//      this.oCreature.jMyAttackTabPane.setFont(new Font("Dialog",Font.PLAIN,9));
      this.oCreature.jMyAttackTabPane.setForegroundAt(nThisAttack, Color.BLACK);
    }
    // reset label to display only initiatives left.
    oCreature.jInitiative.setText(oCreature.initGetInitString());
  }

  void setDamageDiceDetails(String sDamage) {
    jLabelDamageDiceDetails.setText("Damage " + sDamage);
  }

  void jAttackModsListener_stateChanged(ChangeEvent e, String sName) {

//    if (sName.equalsIgnoreCase("jPerDiceMod")){
//    }

    // for now anytime ANY of the spinners are adjusted I just reset the text
    // on the label
    if (this.oCreature == null)       // if this is just a blank list of attacks
      oParent.bCreatureChanged = true;// we're in the creature editor

    int nToHitMod = Integer.parseInt(jToHitMod.getValue().toString());
    int nToDamMod = Integer.parseInt(jTotalMod.getValue().toString());

    if (nToHitMod < 0) {
      jLabelToHitModDetails.setText(nToHitMod + " ToHit, ");
    }
    else if (nToHitMod > 0) {
      jLabelToHitModDetails.setText("+" + nToHitMod + " ToHit, ");
    }
    else {
      jLabelToHitModDetails.setText("");
    }

    if (nToDamMod < 0) {
      jLabelToDamModDetails.setText("[" + nToDamMod + "]");
    }
    else if (nToDamMod > 0) {
      jLabelToDamModDetails.setText("[" + nToDamMod + "]");
    }
    else {
      jLabelToDamModDetails.setText("");
    }

    // we dont set this unless they are using monster type claw/claw/bite
    if (jWeaponUsed.getSelectedIndex() == 0)
    setDamageDiceDetails(jDamageDice.getText());

  }

  // run this when value is changed (focuslost or press enter)
  void jDamageDice_focusLost() {
    if (!Dicer.isValidDamageDiceString(jDamageDice.getText())) {
      jDamageDice.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamageDice.getParent(), null);
    }
    setDamageDiceDetails(jDamageDice.getText());

    if (this.oCreature == null)  // if no creature above, we're in edit mode
      oParent.bCreatureChanged = true;
  }

  // run this when value is set, like at start.
  void jDamageDice_propertyChange(PropertyChangeEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamageDice.getText())) {
      jDamageDice.setText("1d6");
    }
    setDamageDiceDetails(jDamageDice.getText());
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
	Element eItem = new Element("Attack");

    eItem.addContent(new Element("jWeaponType").setText(""+jWeaponType.getSelectedIndex()));
    eItem.addContent(new Element("jDamageDice").setText(jDamageDice.getText()));
    eItem.addContent(new Element("jToHitMod").setText(jToHitMod.getValue().toString()));
    eItem.addContent(new Element("jTotalMod").setText(jTotalMod.getValue().toString()));
    eItem.addContent(new Element("jModCrit").setText(jModCrit.getValue().toString()));
    eItem.addContent(new Element("jModFumble").setText(jModFumble.getValue().toString()));
    eItem.addContent(new Element("jModPenetration").setText(jModPenetration.getValue().toString()));
    eItem.addContent(new Element("nWeaponSelectedID").setText(""+nWeaponSelectedID));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @param oCreature CreatureCore
   * @return TableClass
   */
  static CreatureCombat xmlGetFromElements(Element eItem,
                                           HackSackFrame oParent,
                                           CreatureCore oCreature) {
    CreatureCombat oO = new CreatureCombat(oParent,oCreature);
//eItem.
    oO.jWeaponType.setSelectedIndex(Integer.parseInt(eItem.getChild("jWeaponType").getText()));
    oO.jDamageDice.setText(eItem.getChild("jDamageDice").getText());
    oO.jToHitMod.setValue(new Integer(Integer.parseInt(eItem.getChild("jToHitMod").getText())));
    oO.jTotalMod.setValue(new Integer(Integer.parseInt(eItem.getChild("jTotalMod").getText())));
    oO.jModCrit.setValue(new Integer(Integer.parseInt(eItem.getChild("jModCrit").getText())));
    oO.jModFumble.setValue(new Integer(Integer.parseInt(eItem.getChild("jModFumble").getText())));
    oO.jModPenetration.setValue(new Integer(Integer.parseInt(eItem.getChild("jModPenetration").getText())));
    oO.nWeaponSelectedID = Integer.parseInt(eItem.getChild("nWeaponSelectedID").getText());

    return oO;
  }


} // end CreatureCombat class

class CreatureCombatButton
    extends JButton {
  private CreatureCombat attack = null;

  private CreatureCombatButton() {
  }

  public CreatureCombatButton(CreatureCombat attack) {
    this.attack = attack;
    setText("Attack");

    addActionListener(new CreatureAttackListener(attack));
  }
}

class CreatureAttackListener
    implements ActionListener {
  private CreatureCombat attack = null;

  public CreatureAttackListener(CreatureCombat attack) {
    this.attack = attack;
  }

  public void actionPerformed(ActionEvent e) {
    attack.updateAttack(attack);
  }
}

// -- attack mods spinner listener
class jAttackModsListener_changeAdapter
    implements javax.swing.event.ChangeListener {
  CreatureCombat adaptee;
  String sName;

  jAttackModsListener_changeAdapter(CreatureCombat adaptee,
                                    String sIncomingName) {
    this.adaptee = adaptee;
    this.sName = sIncomingName;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jAttackModsListener_stateChanged(e, sName);
  }
}

// damage dice listeners
class CreatureCombat_jDamageDice_focusAdapter
    extends java.awt.event.FocusAdapter {
  CreatureCombat adaptee;

  CreatureCombat_jDamageDice_focusAdapter(CreatureCombat adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamageDice_focusLost();
  }
}

class CreatureCombat_jDamageDice_propertyChangeAdapter
    implements java.beans.PropertyChangeListener {
  CreatureCombat adaptee;

  CreatureCombat_jDamageDice_propertyChangeAdapter(CreatureCombat adaptee) {
    this.adaptee = adaptee;
  }

  public void propertyChange(PropertyChangeEvent e) {
    adaptee.jDamageDice_propertyChange(e);
  }
}

class CreatureCombat_jDamageDice_actionAdapter
    implements java.awt.event.ActionListener {
  CreatureCombat adaptee;

  CreatureCombat_jDamageDice_actionAdapter(CreatureCombat adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jDamageDice_focusLost();
  }
}

// combo box for selecting weapon we're using in the battle sheet
class CreatureCombat_jWeaponUsed_actionAdapter
    implements java.awt.event.ActionListener {
  CreatureCombat adaptee;

  CreatureCombat_jWeaponUsed_actionAdapter(CreatureCombat adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jWeaponUsed_actionPerformed(e);
  }
}
