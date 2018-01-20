package hacksack;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import org.jdom.*;
//import com.borland.jbcl.layout.*;

/**
 * <p>Title: Structure of creatures </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

//public class CreatureCore extends JPanel {
public class CreatureCore
    implements Comparable {
  protected HackSackFrame oParent = null;
  public CreatureTextField tHPAdj = null;
  public CreatureCoreButton bDelete = null;
  public CreatureCoreButton bKill = null;
  public CreatureCoreButton bCrit = null;
  public CreatureCoreButton bFumble = null;
  public CreatureCoreButton bDesc = null;
  public CreatureCoreButton bArmorShieldSelect = null;
  public JComboBox jClassSelect = null;
  public JLabel jHitPointLabel = null; // current hit points

  public JComboBox jAttackingThisPerson = null; // this is the select box, tels ya who creature is fighting
  public JComboBox jTargetAC = null;
  public JPanel jCritFumblePanel = null;

  public String sCreatureName = null;
  public JLabel jNameLabel = null;
  public JTextArea jNotePad = null;
  public JLabel jInitiative = null;
  public int nHitPoints = 0; // max hitpoints
  public int nAC = 0;
  public int nLevel = 0;

//  public int nTHACO = 20;
  public int nToHitRank = 0;
  public int nFatigueFactor = 10;
  public int nCombatRounds = 0; // how long ive been fighting
  public int nDamageThisRound = 0;
  public boolean bFatigued = false;
  public JLabel jFatiguedLabel = null;
  public int nEXP = -1;
  public int nHonor = -1;
  public int nHonorIndex = 1;
  public int nCreatureID = -1;

  public int nACBase = -100;
  public JLabel lAC = null;
  public JComboBox jArmorType = null;
  public CreatureTextField lArmorHPAdj = null;
  public JLabel jACWornLabel = null;
  public JLabel jACMagicLabel = null;

  public JLabel lShieldAC = null;
  public JComboBox jShieldType = null;
  public CreatureTextField tShieldHPAdj = null;
  public JLabel jShieldWornLabel = null;
  public JLabel jShieldMagicLabel = null;

//  public JSpinner jArmorMod = null;
  public JSpinner jACMod = null;
//  public int nArmorDamage = 0; // damage this creatures armor has taken

  public Gear oArmorWorn = null;
  public Gear oShieldWorn = null;

  public ArrayList lAttacks;

//  public TableArmor oA = null;

  // new data in creature file
  public String sDescription = null;
  public String sSpecialAttack = "N";
  public String sSpecialDefense = "N";
  public String sMagicDefense = "Normal";
  public String sPSIAttack = "N";
  public String sPSIDefense = "N";
  public int nBASENumAtks = 1;
  public int nBASEMorale = 10;
  public int nBASEMove = 12;
  public int nBASESizeIndex = 2; // medium
  public int nBASEAlignmentIndex = 4;
  public int nBASEHackFactor = 1;
  public String sHOBLocation = null;

  //

  public boolean bAggro = false;
  public JCheckBox jIgnored = null;
  public ArrayList aAttackerList;
  public JLabel jAttackedBy = null;
  public JTabbedPane jMyAttackTabPane = null;
  public JLabel jArmorWornName = null;
  public JLabel jShieldWornName = null;

  public CreatureCore() {

    jMyAttackTabPane = new JTabbedPane();
    jArmorWornName = new JLabel("none");
    jShieldWornName = new JLabel("none");

    sHOBLocation = "Unknown.";
    aAttackerList = new ArrayList();
    jAttackedBy = new JLabel("None.");

    tHPAdj = new CreatureTextField(this, "tHPAdj");

    bDelete = new CreatureCoreButton(this, "Remove");
    bDelete.setText("Delete");
    bKill = new CreatureCoreButton(this, "bKill");
    bKill.setText("Kill");
    bCrit = new CreatureCoreButton(this, "Critical");
    bCrit.setText("Critical");
    bFumble = new CreatureCoreButton(this, "Fumble");
    bFumble.setText("Fumble");
    bDesc = new CreatureCoreButton(this, "Description");
    bDesc.setText("Details");
    bDesc.setToolTipText("Description info on this creature.");

    bArmorShieldSelect = new CreatureCoreButton(this, "bArmorShieldSelect");
    bArmorShieldSelect.setText("Select Armor");
    bArmorShieldSelect.setToolTipText("Select new armor and shield worn.");


    jHitPointLabel = new JLabel("NA");
    jNotePad = new JTextArea("");
    // class index fighter=0,cleric=1,thief=2,mage=3,monster=4
    String[] gmClassTable = {
        "Fighter", "Cleric", "Thief", "Magic-User", "Monster"};
    jClassSelect = new JComboBox(gmClassTable);
    jClassSelect.setSelectedIndex(4); // set pull down to default to monster
    lAttacks = new ArrayList();

    jAttackingThisPerson = new JComboBox();
    jAttackingThisPerson.setRenderer(new CellRendererPlayerBox());
    jTargetAC = new JComboBox();

    jCritFumblePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    jArmorType = new JComboBox();
    jArmorType.setRenderer(new CellRendererGearBox());
    jArmorType.setFont(new Font("Dialog",0,9));

    jShieldType = new JComboBox();
    jShieldType.setRenderer(new CellRendererGearBox());
    jShieldType.setFont(new Font("Dialog",0,9));

    lShieldAC = new JLabel("");
    jShieldMagicLabel = new JLabel("");
    jShieldWornLabel = new JLabel("");
    tShieldHPAdj = new CreatureTextField(this, "tShieldHPAdj");


    jACWornLabel = new JLabel("");

    jACMagicLabel = new JLabel("NA");
    jACMagicLabel.setToolTipText("AC and current hit points of that AC rank.");
    lAC = new JLabel("AC");

    jInitiative = new JLabel();

    lArmorHPAdj = new CreatureTextField(this, "lArmorHPAdj");
//    jArmorMod = new JSpinner(new SpinnerNumberModel(0, -20, 20, 1));
    jACMod = new JSpinner(new SpinnerNumberModel(0, -20, 20, 1));
//    jArmorMod.addChangeListener(new jCreatureSpinnerListener(this, "jArmorMod"));
    jACMod.addChangeListener(new jCreatureSpinnerListener(this, "jACMod"));

    jFatiguedLabel = new JLabel("Fatigued");
    jNameLabel = new JLabel("-Name=");

    jIgnored = new JCheckBox("Ignored", false);
  }

// just sort it like so
//lCreatures.sort();
  public int compareTo(Object o) {
    return sCreatureName.compareTo( ( (CreatureCore) o).sCreatureName);
  }

  public CreatureCore(HackSackFrame oThisFrame, String name) {
    this(); //call the above contructor
    this.sCreatureName = name;
    this.oParent = oThisFrame;

    jAttackingThisPerson.addActionListener(new
                                           jAttackingThisPersonPullDownListener(
        oParent, "pAttacking", this));

  }

  public void updateButtonPressed(CreatureCore thisCreature,
                                  String sButtonPressed) {

     if (sButtonPressed.equalsIgnoreCase("bArmorShieldSelect")) {
       DialogNPCArmorSelect.DoDialogNPCArmorSelect(oParent,oParent.fBattleSheetFrame,this);
     } else
    if (sButtonPressed.equalsIgnoreCase("Description")) {
      TableSavedCreatures oOrig = null;
      if (thisCreature.nCreatureID > 0) {
        oOrig = thisCreature.GetCreatureFromID(thisCreature.oParent,
                                               thisCreature.nCreatureID);
        oOrig.loadButtonPressed(oOrig);
        oParent.jGMDiceBagTabbedPane.setSelectedIndex(2);
        oParent.jGMDiceBagTabbedPane.requestFocus();
//        oParent.ShowDone(oParent,thisCreature.sCreatureName+" loaded.\n");
      }
      if (oOrig == null && !thisCreature.sDescription.equals("")) {
        oParent.ShowDoneFrame(oParent.fBattleSheetFrame,
                              "Details :" + thisCreature.sCreatureName,
                              thisCreature.sDescription);
      }
    }
    else
    if (sButtonPressed.equalsIgnoreCase("critical")) {

    }
    else
    if (sButtonPressed.equalsIgnoreCase("fumble")) {

    }
    else
    if (sButtonPressed.equalsIgnoreCase("remove")) {
      oParent.gplGroupLog.groupLog("Deleted " + thisCreature.sCreatureName +
                                   " from battle sheet" +
                                   (thisCreature.jNotePad.getText().equals("") ?
                                    "" :
                                    ", DETAILS: " +
                                    thisCreature.jNotePad.getText()) +
                                   ".\n");
      thisCreature.oParent.lCreatures.remove(thisCreature);
      thisCreature.oParent.nLastCreature = 0;

//      thisCreature.oParent.fBattleSheetFrame.jBSCreaturePanel.removeAll();
//      thisCreature.oParent.fBattleSheetFrame.mCreatureList.removeAllElements();
//      thisCreature.oParent.fBattleSheetFrame.jBSCreaturePanel.repaint();

      thisCreature.oParent.jSheetTotalLabel.setText("Total Creatures:" +
          thisCreature.oParent.nLastCreature);
      thisCreature.oParent.fBattleSheetFrame.AppendToBattleSheet(oParent);
      thisCreature.oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
      if (thisCreature.oParent.nLastCreature > 0) {
        thisCreature.oParent.jSheetTotalLabel.setText("Total Creatures:" +
            thisCreature.oParent.nLastCreature);
      }
      else {
        thisCreature.oParent.jSheetTotalLabel.setText("");
      }
//      thisCreature.oParent.fBattleSheetFrame.jBattleSheetTotalLabel.setText(":" +
//          thisCreature.oParent.nLastCreature + ":" + System.currentTimeMillis());
    }
    else
    if (sButtonPressed.equalsIgnoreCase("bKill")) {
      oParent.gplGroupLog.groupLog("Defeated " + thisCreature.sCreatureName +
                                   " and awarded EXP to group" +
                                   (thisCreature.jAttackedBy.getText().
                                    equalsIgnoreCase("None") ? "" :
                                    ", Killed by " +
                                    thisCreature.jAttackedBy.getText()) +
                                   (thisCreature.jNotePad.getText().equals("") ?
                                    "" :
                                    ", DETAILS: " +
                                    thisCreature.jNotePad.getText()) +
                                   ".\n");

      if (oParent.jGMConfigDefeatAwardCheckBox1.isSelected()) {
        // setup monster EXP awards
        Monster oE = new Monster(oParent);
        oE.sName = thisCreature.sCreatureName;
        oE.sDesc = thisCreature.jNotePad.getText();
        oE.nEXPValue = thisCreature.nEXP;
        oE.nHonorValue = thisCreature.nHonor; // not used yet?
        oParent.gplGroupLog.lMonsters.add(oE);

        // setup monster honor awards if honor is average or greater
        if (thisCreature.nHonorIndex <= 1) {
          HonorAward oHonorAward = new HonorAward(oParent);
          oHonorAward.nID = -10;
          oHonorAward.sDetails = thisCreature.sCreatureName + " defeated by " +
              thisCreature.jNotePad.getText();
          oParent.gplGroupLog.lHonor.add(oHonorAward);
        }
      }

      // refresh the log screen
//      if (oParent.fPlayerGroupFrame.isVisible()) {
//        oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
//      }
      thisCreature.oParent.lCreatures.remove(thisCreature);
      thisCreature.oParent.nLastCreature = 0;

//      thisCreature.oParent.fBattleSheetFrame.jBSCreaturePanel.removeAll();
//      thisCreature.oParent.fBattleSheetFrame.mCreatureList.removeAllElements();
//      thisCreature.oParent.fBattleSheetFrame.jBSCreaturePanel.repaint();

      thisCreature.oParent.jSheetTotalLabel.setText("Total Creatures:" +
          thisCreature.oParent.nLastCreature);
      thisCreature.oParent.fBattleSheetFrame.AppendToBattleSheet(oParent);
      thisCreature.oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
      if (thisCreature.oParent.nLastCreature > 0) {
        thisCreature.oParent.jSheetTotalLabel.setText("Total Creatures:" +
            thisCreature.oParent.nLastCreature);
      }
      else {
        thisCreature.oParent.jSheetTotalLabel.setText("");
      }
//      thisCreature.oParent.fBattleSheetFrame.jBattleSheetTotalLabel.setText(":" +
//          thisCreature.oParent.nLastCreature + ":" + System.currentTimeMillis());
    } // was remove button

  }

  // when someone alters the hp adjustment field we run this...
  public void updateTextField(CreatureCore thisCreature, String sName) {

    String sWhatMagicItemsTakeDamageFrom =
                                 "Is this damage from one of the following:\n" +
                                 "A critical hit?\n" +
                                 "A penetration hit?\n" +
                                 "A spell?\n" +
                                 "A magical attack?\n";


    // creature hp adjust field on battle sheet
    if (sName.equalsIgnoreCase("tHPAdj")) {
      int nMaxHP = thisCreature.nHitPoints;
      int nCurrentHP = Integer.parseInt(thisCreature.jHitPointLabel.getText());
      String sAdjustment = thisCreature.tHPAdj.getText();

      // remove + signs
      if (sAdjustment.matches("(?i).*?(\\+).*?")) {
        sAdjustment = sAdjustment.replaceAll("(?i).*?(\\+).*?", "");
      }

      int nHPAdjustment = Integer.parseInt(sAdjustment);
      int nHPResult = (nCurrentHP + nHPAdjustment);

      // track damage creature has taken this round
      if (nHPAdjustment < 0) {
        nDamageThisRound += nHPAdjustment;

      }
      if (nHPResult < -100) { // some sanity
        nHPResult = -100;
      }
      else
      if (nHPResult > nMaxHP) {
        nHPResult = nMaxHP;

      }
      String sMod = nHPAdjustment < 0 ? "" : "+";

      log(thisCreature, false, true,
          thisCreature.sCreatureName + " adjusted health " + nCurrentHP + sMod +
          nHPAdjustment + "=" + nHPResult + "(" + nDamageThisRound + ")\n");
      oParent.gplGroupLog.groupLog(thisCreature.sCreatureName +
                                   " adjusted health " + nCurrentHP + sMod +
                                   nHPAdjustment + "=" + nHPResult + "(" +
                                   nDamageThisRound + ")\n");
      thisCreature.jHitPointLabel.setText(Integer.toString(nHPResult));
      thisCreature.tHPAdj.setText("");
      // MAKE TOP Check
      if ( (nHPResult > 0) && // still alive?
          ( ( -1 * (nDamageThisRound)) > (nMaxHP / 2)) &&
          oParent.jGMConfigTOPCheckBox1.isSelected()) {
        DialogTOP.LoadDialog(jAttackingThisPerson, oParent, this);
      }

    }
    // ----------- shield health adjustment
    else if (sName.equalsIgnoreCase("tShieldHPAdj")) {
      String sAdjustment = thisCreature.tShieldHPAdj.getText();
      thisCreature.tShieldHPAdj.setText("");
      if (oShieldWorn != null) {
        if (sAdjustment.matches("(?i).*?(\\+).*?")) {
          sAdjustment = sAdjustment.replaceAll("(?i).*?(\\+).*?", "");
        }
        int nTotalToRemove = Integer.parseInt(sAdjustment);
        if (getShieldMagicBonus() > 0) {
          if (!oParent.AskYN(oParent.fBattleSheetFrame,
                                          "This shield has magical properties.\n"+
                                          sWhatMagicItemsTakeDamageFrom))
          // if it's not one of the above we just toss the damage
          // because magical armor doesn't take damage it its not one of the listed
          // effects.
          nTotalToRemove = 0;
        }

        if (nTotalToRemove < 0) { // negative value
          // deal with magical bonuses on armor first
          while (oShieldWorn.nShieldACMagicBonusCurrent > 0 && nTotalToRemove < 0) {
            oShieldWorn.nShieldACMagicHealthCurrent += nTotalToRemove;
            if (oShieldWorn.nShieldACMagicHealthCurrent <= 0) {
              nTotalToRemove = oShieldWorn.nShieldACMagicHealthCurrent;
              // remove a plus magic bonus
              --oShieldWorn.nShieldACMagicBonusCurrent;
              // set bonus health value to max for next plus if any exist
              oShieldWorn.nShieldACMagicHealthCurrent = oShieldWorn.nShieldACMagicBonus;
            } else {
              nTotalToRemove = 0;
            }
          } // end with magical bonuses
          if (nTotalToRemove < 0) { // see if we're still got stuff to remove
            for (int i = oShieldWorn.nShieldACBaseCurrent;
                 i >= oShieldWorn.SHIELD_1 && nTotalToRemove != 0; i--) {

              if (oShieldWorn.nShieldHPCurrent[i] > 0) {
                oShieldWorn.nShieldHPCurrent[i] += nTotalToRemove;
                // if we went below 0, new totaltoremove
                if (oShieldWorn.nShieldHPCurrent[i] <= 0) {
                  nTotalToRemove = oShieldWorn.nShieldHPCurrent[i];
                  oShieldWorn.nShieldHPCurrent[i] = 0;
                  oShieldWorn.nShieldACBaseCurrent = (i - 1); // ac got worse
                }
                else { // set ntotaltoremove to 0 cause we're finished
                  nTotalToRemove = 0;
                }
              }

            }

            // if last armor slot is empty then ....
            if (oShieldWorn.nShieldHPCurrent[ (oShieldWorn.SHIELD_1)] <= 0) {
              oShieldWorn.nShieldHPCurrent[ (oShieldWorn.SHIELD_1)] = 0;
              oShieldWorn.nShieldACBaseCurrent = 0;
              if (nTotalToRemove < 0)
              oParent.ShowDoneFrame(oParent.fBattleSheetFrame,
                                    "Shield Destroyed!",(-1*nTotalToRemove)+" damage hits the target.");
            }

          }
        }
        else if (nTotalToRemove > 0) { // positive value
          for (int i = oShieldWorn.nShieldACBaseCurrent;
               i <= oShieldWorn.nShieldACBase && nTotalToRemove != 0; i++) {

            if (oShieldWorn.nShieldHPCurrent[i] < oShieldWorn.nShieldHP[i]) {
              oShieldWorn.nShieldHPCurrent[i] += nTotalToRemove;
              // if we go above max or even for this AC
              if (oShieldWorn.nShieldHPCurrent[i] >= oShieldWorn.nShieldHP[i]) {
                nTotalToRemove = (oShieldWorn.nShieldHPCurrent[i] -
                                  oShieldWorn.nShieldHP[i]);
                oShieldWorn.nShieldHPCurrent[i] = oShieldWorn.nShieldHP[i];
                oShieldWorn.nShieldACBaseCurrent = i;
              }
              else {
                // we added health to this ac rank, set it as a valid AC
                nTotalToRemove = 0;
                if (oShieldWorn.nShieldACBaseCurrent < i) {
                  oShieldWorn.nShieldACBaseCurrent = i;
                }
              }
            }
          }

        }

      setArmorClassLabel();
      }

    }
    // ----------------- armor health adjustment
    else if (sName.equalsIgnoreCase("lArmorHPAdj")) {
      String sAdjustment = thisCreature.lArmorHPAdj.getText();
      thisCreature.lArmorHPAdj.setText("");
      if (oArmorWorn != null) {
        // remove + signs
        if (sAdjustment.matches("(?i).*?(\\+).*?")) {
          sAdjustment = sAdjustment.replaceAll("(?i).*?(\\+).*?", "");
        }
        int nTotalToRemove = Integer.parseInt(sAdjustment);
        if (getArmorClassMagicBonus() > 0) {
          if (!oParent.AskYN(oParent.fBattleSheetFrame,
                                          "This armor has magical properties.\n"+
                                          sWhatMagicItemsTakeDamageFrom))
          // if it's not one of the above we just toss the damage
          // because magical armor doesn't take damage it its not one of the listed
          // effects.
          nTotalToRemove = 0;
        }


        if (nTotalToRemove < 0) { // negative value
          // deal with magical bonuses on armor first
          while (oArmorWorn.nACMagicBonusCurrent > 0 &&
                 nTotalToRemove < 0) {
            oArmorWorn.nACMagicBonusHealthCurrent += nTotalToRemove;
            if (oArmorWorn.nACMagicBonusHealthCurrent <= 0) {
              nTotalToRemove = oArmorWorn.nACMagicBonusHealthCurrent;
              // remove a plus magic bonus
              --oArmorWorn.nACMagicBonusCurrent;
              // set bonus health value to max for next plus if any exist
              oArmorWorn.nACMagicBonusHealthCurrent = oArmorWorn.nACMagicBonusHealth;
            } else {
              nTotalToRemove = 0;
            }
          } // end with magical bonuses

          if (nTotalToRemove < 0) { // still negative value
            for (int i = oArmorWorn.nACBase;
                 i <= oArmorWorn.AC_9 && nTotalToRemove != 0; i++) {
              if (oArmorWorn.nACHPCurrent[i] > 0) {
                oArmorWorn.nACHPCurrent[i] += nTotalToRemove;
                // if we went below 0, new totaltoremove
                if (oArmorWorn.nACHPCurrent[i] <= 0) {
                  nTotalToRemove = oArmorWorn.nACHPCurrent[i];
                  oArmorWorn.nACHPCurrent[i] = 0;
                  oArmorWorn.nACBaseCurrent = (i + 1); // ac got worse
                }
                else { // set ntotaltoremove to 0 cause we're finished
                  nTotalToRemove = 0;
                }
              }
            }

            // if last armor slot is empty then ....
            if (oArmorWorn.nACHPCurrent[ (oArmorWorn.AC_9)] <= 0) {
              oArmorWorn.nACBaseCurrent = 10;
              if (nTotalToRemove < 0)
              oParent.ShowDoneFrame(oParent.fBattleSheetFrame,
                                    "Armor Destroyed!",(-1*nTotalToRemove)+" damage hits the target.");
            }
          }
        }
        // positive value
        else if (nTotalToRemove > 0) {
          for (int i = oArmorWorn.AC_9;
               i >= oArmorWorn.nACBase && nTotalToRemove != 0; i--) {
            if (oArmorWorn.nACHPCurrent[i] < oArmorWorn.nACHP[i]) {
              oArmorWorn.nACHPCurrent[i] += nTotalToRemove;
              // if we go above max or even for this AC
              if (oArmorWorn.nACHPCurrent[i] >= oArmorWorn.nACHP[i]) {
                nTotalToRemove = (oArmorWorn.nACHPCurrent[i] -
                                  oArmorWorn.nACHP[i]);
                oArmorWorn.nACHPCurrent[i] = oArmorWorn.nACHP[i];
                oArmorWorn.nACBaseCurrent = i;
              }
              else {
                // we added health to this ac rank, set it as a valid AC
                nTotalToRemove = 0;
                if (oArmorWorn.nACBaseCurrent < i) {
                  oArmorWorn.nACBaseCurrent = i;
                }
              }
            }
          }
        }

        setArmorClassLabel();
      }
    }    // ---------------- end armor health adjustment

  }

  // figure out who this creature is attacking if any
  TablePlayer getWhoImAttacking() {
    TablePlayer oTarget = null;
    if (jAttackingThisPerson.getSelectedIndex() > 0) { // index 0 is DEFAULT
      oTarget = (TablePlayer) jAttackingThisPerson.getSelectedItem();
    }

    return oTarget;
  }

// attacking this player pull down stuff
  void updateAttackingPulldown(HackSackFrame oParent, String sThisButtonName,
                               CreatureCore oMe) {
    // who I am attacking...
    if (sThisButtonName.equalsIgnoreCase("pAttacking")) {
      TablePlayer oPlayer = getWhoImAttacking(); //(TablePlayer) jAttackingThisPerson.getSelectedItem();
//      if (jAttackingThisPerson.getSelectedIndex() > 0) { // index 0 is DEFAULT
      if (oPlayer != null) {
        oMe.jTargetAC.removeAllItems();
        oMe.jTargetAC.addItem("AC NORMAL " +
                              oPlayer.nAC[oPlayer.AC_NORMAL].getValue().
                              toString()); // 0
        oMe.jTargetAC.addItem("AC SUPRISED " +
                              oPlayer.nAC[oPlayer.AC_SUPRISED].getValue().
                              toString()); // 1
        oMe.jTargetAC.addItem("AC SHIELDLESS " +
                              oPlayer.nAC[oPlayer.AC_SHIELDLESS].getValue().
                              toString()); // 2
        oMe.jTargetAC.addItem("AC REAR " +
                              oPlayer.nAC[oPlayer.AC_REAR].getValue().toString()); // 3
      }
      else { // we just attack and display AC/damage
        oMe.jTargetAC.removeAllItems();
        oMe.jTargetAC.addItem("DEFAULT");
      }
      // reset damagedice details incase we target size changed
      for (int i = 0; i < lAttacks.size(); i++) {
        CreatureCombat oAtk = (CreatureCombat) lAttacks.get(i);
        oAtk.setDamageDiceDetails(oAtk.getSelectedWeaponDamage());
      }

    } // armor wearing, not used.
  }

  // creature spinners
  void updatedSpinner(CreatureCore oC, String sName) {
    if (sName.equalsIgnoreCase("jACMod")) {
      setArmorClassLabel();
    }

  }

  // get the creature object using the ID of the creature
  TableSavedCreatures GetCreatureFromID(HackSackFrame oParent, int nID) {
    TableSavedCreatures oCreature = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lSavedCreatures.size() && !bFound; i++) {
      TableSavedCreatures oFind = (TableSavedCreatures) oParent.lSavedCreatures.
          get(i);
      if (oFind.nCreatureID == nID) {
        oCreature = oFind;
        bFound = true;
      }
    }
    return oCreature;
  }

  // log to display panel
  public void log(CreatureCore thisCreature, boolean bDetailed,
                  boolean bNewBlock, String sStr) {
    if (!thisCreature.oParent.jGMAppendCheckBox.isSelected() && bNewBlock) {
      thisCreature.oParent.jGMTextArea.setText("");
    }
    if (bDetailed && thisCreature.oParent.jGMDetailsCheckBox.isSelected()) {
      thisCreature.oParent.jGMTextArea.append(sStr);
    }
    if (!bDetailed) {
      thisCreature.oParent.jGMTextArea.append(sStr);

    }
  }

  // if it's my attack round return
  // my attack structure
  public CreatureCombat initMyAttackRound(int nCurrentSegment) {
    CreatureCombat oA = null;

    for (int i = 0; i < lAttacks.size() && (oA == null); i++) {
      CreatureCombat oRec = (CreatureCombat) lAttacks.get(i);
      if (nCurrentSegment == oRec.nInit) {
        oA = oRec;
      }
    }

    return oA;
  }

  public String initGetInitString() {
    String sInitText = null;

    for (int j = 0; j < lAttacks.size(); j++) {
      CreatureCombat oA = (CreatureCombat) lAttacks.get(j);
      if (oA.nInit > 0) {
        if (sInitText == null) {
          sInitText = "Init :" + oA.nInit;
        }
        else {
          sInitText += "," + oA.nInit;
        }
      }
    }

    if (sInitText == null) {
      sInitText = ""; // initiative complete
    }
    return sInitText;
  }

  // clear init values if they are < 10, if not use 10-current for new setting
  // and dont roll new init
  public boolean initReadyForNextRound() {
    boolean bReady = true;

    String sInitText = null;

    for (int j = 0; j < lAttacks.size(); j++) {
      CreatureCombat oA = (CreatureCombat) lAttacks.get(j);
      if (oA.nInit > 10) {
        oA.nInit = (oA.nInit - 10);
        if (sInitText == null) {
          sInitText = "Init : " + oA.nInit;
        }
        else {
          sInitText += "," + oA.nInit;

        }
        bReady = false; // no ready, we still trying to finish last round, keep my init the same
      }
      else {
        oA.nInit = 0;
      }
    }

    jInitiative.setText(sInitText);
    return bReady;
  }

  // RollCreatures initiative
  public void initRollCreatureInitiative() {
    int nFirstAtkInit = 0;
    String sInitText = "";
    boolean bNewAggro = false;
    nCombatRounds++;
    nDamageThisRound = 0; // new round, clear damage taken count
    for (int j = 0; j < lAttacks.size(); j++) {
      CreatureCombat oA = (CreatureCombat) lAttacks.get(j);
      // this is just incase they never attacked with a ready npc
      if (oA.initThisAttackReady()) oA.initSetAttackDeactive();
      int nCurrentAttack = j + 1;
      switch (lAttacks.size()) {
        case 1: // single attack only
          oA.nInit = oParent.MyRandom(10);
          sInitText = "Init : " + oA.nInit;
          break;

        case 2: // two attacks per round
          if (nCurrentAttack == 1) {
            oA.nInit = oParent.MyRandom(5);
            sInitText = "Init : " + oA.nInit;
            nFirstAtkInit = oA.nInit;
          }
          else
          if (nCurrentAttack == 2) {
            oA.nInit = nFirstAtkInit + 5;
            sInitText += ", " + oA.nInit;
          }
          break;

        case 3: // 3 attacks per round
          if (nCurrentAttack == 1) {
            oA.nInit = oParent.MyRandom(3);
            sInitText = "Init : " + oA.nInit;
            nFirstAtkInit = oA.nInit;
          }
          else
          if (nCurrentAttack == 2) {
            oA.nInit = nFirstAtkInit + 3;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 3) {
            oA.nInit = nFirstAtkInit + 6;
            sInitText += ", " + oA.nInit;
          }
          break;

        case 4: // 4 attacks per round
          if (nCurrentAttack == 1) {
            oA.nInit = oParent.MyRandom(3);
            sInitText = "Init : " + oA.nInit;
            nFirstAtkInit = oA.nInit;
          }
          else
          if (nCurrentAttack == 2) {
            oA.nInit = nFirstAtkInit + 2;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 3) {
            oA.nInit = nFirstAtkInit + 4;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 4) {
            oA.nInit = nFirstAtkInit + 6;
            sInitText += ", " + oA.nInit;
          }
          break;

        case 5: // 5 attacks per round
          if (nCurrentAttack == 1) {
            oA.nInit = oParent.MyRandom(2);
            nFirstAtkInit = oA.nInit;
            sInitText = "Init : " + oA.nInit;
          }
          else
          if (nCurrentAttack == 2) {
            oA.nInit = nFirstAtkInit + 2;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 3) {
            oA.nInit = nFirstAtkInit + 4;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 4) {
            oA.nInit = nFirstAtkInit + 6;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 5) {
            oA.nInit = nFirstAtkInit + 8;
            sInitText += ", " + oA.nInit;
          }
          break;

        default: // more than 5 attacks per round
          if (nCurrentAttack == 1) {
            oA.nInit = oParent.MyRandom(2);
            nFirstAtkInit = oA.nInit;
            sInitText = "Init : " + oA.nInit;
          }
          else
          if (nCurrentAttack == 2) {
            oA.nInit = nFirstAtkInit + 2;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 3) {
            oA.nInit = nFirstAtkInit + 4;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack == 4) {
            oA.nInit = nFirstAtkInit + 6;
            sInitText += ", " + oA.nInit;
          }
          else
          if (nCurrentAttack >= 5) { // anymore attacks go on 5
            oA.nInit = nFirstAtkInit + 8;
            sInitText += ", " + oA.nInit;
          }
          break;
      } // end switch

      if (oA.nInit <= 1) { // we're red hot out of the gates!
        oA.initSetAttackActive();
        bNewAggro = true; // use this to store aggro status because
                          // we deactivate stuff before activate down here
                          // and that turns off aggro
      }

    }
    // set bAggro if I have init on 1
    bAggro = bNewAggro;
    jInitiative.setText(sInitText);
//    oParent.fBattleSheetFrame.jCreatureList.repaint();
  }

  // build a nice panel and return so battle sheet can display.
  JPanel getCreatureBattleInfo(Color cColor) {
//    Font fSmallCharacter = new Font("Dialog", Font.PLAIN, 7);
    Font fSmallText = new Font("Dialog", Font.PLAIN, 9);
    Font fLargeText = new Font("Dialog", Font.BOLD, 11);
    Font fReallyLargeText = new Font("Dialog", Font.BOLD, 14);

    VerticalFlowLayout vLayout = new VerticalFlowLayout();
    vLayout.setVgap(0);
    vLayout.setHgap(0);
//    vLayout.setAlignment(vLayout.TOP);
    JPanel jThisBattleSheet = new JPanel(vLayout);
    jThisBattleSheet.setBackground(cColor);

    Border bssheetborder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        Color.white,
        Color.white,
        Color.white,
        Color.white);
    TitledBorder bssheettitleborder = new TitledBorder(bssheetborder,
        sCreatureName);
    bssheettitleborder.setTitleColor(Color.RED);
    bssheettitleborder.setTitleFont(fLargeText);
    jThisBattleSheet.setBorder(bssheettitleborder);

//    VerticalFlowLayout vLayout2 = new VerticalFlowLayout();
//    vLayout2.setVgap(0);
//    vLayout2.setAlignment(vLayout.TOP);
    JPanel thisCreaturePanel = new JPanel(vLayout);
    thisCreaturePanel.setBackground(cColor);
    Border bCreatureBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
            Color.white,
            Color.white,
            Color.white,
            Color.white);
        TitledBorder bCreatureBorderTitle = new TitledBorder(bCreatureBorder,"Stats");
        bCreatureBorderTitle.setTitleColor(Color.WHITE);
        bCreatureBorderTitle.setTitleFont(fSmallText);
        thisCreaturePanel.setBorder(bCreatureBorderTitle);

    jThisBattleSheet.add(thisCreaturePanel);

    FlowLayout fFlow1 = new FlowLayout(FlowLayout.LEFT);
    fFlow1.setVgap(0);
    fFlow1.setHgap(2);
    JPanel thisCreatureStatsPanel = new JPanel(fFlow1);
    thisCreatureStatsPanel.setBackground(cColor);

    FlowLayout fFlow2 = new FlowLayout(FlowLayout.LEFT);
    fFlow2.setVgap(0);
    fFlow2.setHgap(2);
    JPanel jSpecialModesPanel = new JPanel(fFlow2);
    jSpecialModesPanel.setBackground(cColor);

    FlowLayout fFlow3 = new FlowLayout(FlowLayout.LEFT);
    fFlow3.setVgap(0);
    fFlow3.setHgap(2);
    JPanel thisCreaturePanel2 = new JPanel(fFlow3);
    thisCreaturePanel2.setBackground(cColor);
    Border bTargetBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        Color.white,
        Color.white,
        Color.white,
        Color.white);
    TitledBorder bTargetBorderTitle = new TitledBorder(bTargetBorder,"Target");
    bTargetBorderTitle.setTitleColor(Color.WHITE);
    bTargetBorderTitle.setTitleFont(fSmallText);
    thisCreaturePanel2.setBorder(bTargetBorderTitle);

    jNameLabel.setText(sCreatureName);

    // if fatigued appeand fatigue marker
//    jCreatureName.setText(
//          thisCreature.sCreatureName+(thisCreature.bFatigued?"<"+thisCreature.jFatiguedLabel+">":"") );

    /*
        jNameLabel.setFont(fLargeText);
        jNameLabel.setForeground(Color.red);
        thisCreaturePanel.add(jNameLabel);
     */

    JLabel jtitleLevel = new JLabel("LVL");
    jtitleLevel.setFont(fSmallText);
    jtitleLevel.setToolTipText("Level of creature.");
    jtitleLevel.setForeground(Color.GREEN);

    thisCreatureStatsPanel.add(jtitleLevel);

    JLabel jLevelLable = new JLabel(Integer.toString(nLevel));
    jLevelLable.setFont(fLargeText);
    jLevelLable.setForeground(Color.WHITE);

    thisCreatureStatsPanel.add(jLevelLable);

    JLabel jtitleAC = new JLabel("AC ");
    jtitleAC.setToolTipText("Armor class of this creature.");
    jtitleAC.setFont(fSmallText);
    jtitleAC.setForeground(Color.GREEN);
    thisCreatureStatsPanel.add(jtitleAC);

//    JLabel jAcLabel = new JLabel(Integer.toString(thisCreature.nAC));
    lAC.setFont(fReallyLargeText);
    lAC.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(lAC);

    jACMagicLabel.setText("");
    jACMagicLabel.setFont(fSmallText);
    jACMagicLabel.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jACMagicLabel);
    setArmorClassLabel(); // use this to set the proper values first time

    JLabel jtitleHPADJ = new JLabel("HP-Adj");
    jtitleHPADJ.setFont(fSmallText);
    jtitleHPADJ.setForeground(Color.GREEN);
    jtitleHPADJ.setToolTipText("Enter adjustment to health and hit <enter>.example: +10 would add 10 health, -10 would remove 10");
    thisCreatureStatsPanel.add(jtitleHPADJ);

    tHPAdj.setPreferredSize(new Dimension(35, 20));
    tHPAdj.setText("");
    thisCreatureStatsPanel.add(tHPAdj);

    JLabel jtitleCurHP = new JLabel("HP");
    jtitleCurHP.setToolTipText("Current hitpoints of this creature.");
    jtitleCurHP.setFont(fSmallText);
    jtitleCurHP.setForeground(Color.GREEN);
    thisCreatureStatsPanel.add(jtitleCurHP);

    // only run this if we're at NA health which is what is set manually
    // otherwise we load saved state.
    if (jHitPointLabel.getText().equalsIgnoreCase("NA")) {
      jHitPointLabel.setText(Integer.toString(nHitPoints));
    }

    jHitPointLabel.setFont(fReallyLargeText);
    jHitPointLabel.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jHitPointLabel);

    // movebase
    JLabel jBASEMoveLabel = new JLabel("MV");
    JLabel jBASEMoveLabelDetails = new JLabel(nBASEMove + "");
    jBASEMoveLabel.setToolTipText("Creatures move base.");
    jBASEMoveLabelDetails.setToolTipText(jBASEMoveLabel.getToolTipText());
    jBASEMoveLabel.setFont(fSmallText);
    jBASEMoveLabelDetails.setFont(fLargeText);
    jBASEMoveLabelDetails.setForeground(Color.WHITE);
    jBASEMoveLabel.setForeground(Color.GREEN);
    thisCreatureStatsPanel.add(jBASEMoveLabel);
    thisCreatureStatsPanel.add(jBASEMoveLabelDetails);

    // morale
    JLabel jBASEMoraleLabel = new JLabel("ML");
    JLabel jBASEMoraleLabelDetails = new JLabel(nBASEMorale + "");
    jBASEMoraleLabel.setToolTipText("Creatures morale rating.");
    jBASEMoraleLabelDetails.setToolTipText(jBASEMoraleLabel.getToolTipText());
    jBASEMoraleLabel.setFont(fSmallText);
    jBASEMoraleLabelDetails.setFont(fLargeText);
    jBASEMoraleLabelDetails.setForeground(Color.WHITE);
    jBASEMoraleLabel.setForeground(Color.GREEN);
    thisCreatureStatsPanel.add(jBASEMoraleLabel);
    thisCreatureStatsPanel.add(jBASEMoraleLabelDetails);

    // size
    JLabel jBASESizeLabel = new JLabel("SZ");
    JLabel jBASESizeLabelDetails = new JLabel(oParent.gmSizeTable[
                                              nBASESizeIndex]);
    jBASESizeLabel.setToolTipText("Creatures size.");
    jBASESizeLabelDetails.setToolTipText(jBASESizeLabel.getToolTipText());
    jBASESizeLabel.setFont(fSmallText);
    jBASESizeLabelDetails.setFont(fLargeText);
    jBASESizeLabel.setForeground(Color.GREEN);
    jBASESizeLabelDetails.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jBASESizeLabel);
    thisCreatureStatsPanel.add(jBASESizeLabelDetails);

    // alignment
    JLabel jBASEALignmentLabel = new JLabel("AL");
    JLabel jBASEALignmentLabelDetails = new JLabel(oParent.gmAlignmentTable[
        nBASEAlignmentIndex]);
    jBASEALignmentLabel.setToolTipText("Creatures alignment.");
    jBASEALignmentLabelDetails.setToolTipText(jBASEALignmentLabel.
                                              getToolTipText());
    jBASEALignmentLabel.setFont(fSmallText);
    jBASEALignmentLabelDetails.setFont(fLargeText);
    jBASEALignmentLabel.setForeground(Color.GREEN);
    jBASEALignmentLabelDetails.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jBASEALignmentLabel);
    thisCreatureStatsPanel.add(jBASEALignmentLabelDetails);

    // FATIGUE INFO
    JLabel jFatigueLabel = new JLabel("FF");
    JLabel jFatigueLabelDetails = new JLabel(nFatigueFactor + "");
    jFatigueLabel.setToolTipText("Creatures Fatigue Factor.");
    jFatigueLabelDetails.setToolTipText(jFatigueLabel.getToolTipText());
    jFatigueLabel.setFont(fSmallText);
    jFatigueLabelDetails.setFont(fLargeText);
    jFatigueLabel.setForeground(Color.GREEN);
    jFatigueLabelDetails.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jFatigueLabel);
    thisCreatureStatsPanel.add(jFatigueLabelDetails);

    // HOB loc
    JLabel jHoBLabel = new JLabel("HOB");
    JLabel jHoBLabelDetails = new JLabel(sHOBLocation);
    jHoBLabel.setToolTipText("Hacklopedia of Beasts this creature appears in.");
    jHoBLabelDetails.setToolTipText(jHoBLabel.getToolTipText());
    jHoBLabel.setFont(fSmallText);
    jHoBLabelDetails.setFont(fLargeText);
    jHoBLabel.setForeground(Color.GREEN);
    jHoBLabelDetails.setForeground(Color.WHITE);
    thisCreatureStatsPanel.add(jHoBLabel);
    thisCreatureStatsPanel.add(jHoBLabelDetails);

    jIgnored.setFont(fSmallText);
    jIgnored.setToolTipText("Flag this creature as ignored for now.");
    jIgnored.setForeground(Color.GREEN);
    jIgnored.setBackground(cColor);
    thisCreatureStatsPanel.add(jIgnored);

//    jThisBattleSheet.add(thisCreatureStatsPanel);
    thisCreaturePanel.add(thisCreatureStatsPanel);

    // special attack/defense details
    // I check this here also so I don't add the SA/SD panel unless we need it
    if (sSpecialAttack.equalsIgnoreCase("Y") || sSpecialAttack.length() > 2 ||
        sSpecialDefense.equalsIgnoreCase("Y") || sSpecialDefense.length() > 2) {
      if (sSpecialAttack.equalsIgnoreCase("Y") ||
          sSpecialAttack.length() > 2) {
        JLabel jSADetails = new JLabel("SA");
        jSADetails.setForeground(Color.GREEN);
        jSADetails.setFont(fSmallText);
        JLabel jSA = new JLabel(sSpecialAttack);
        jSA.setForeground(Color.WHITE);
        jSA.setFont(fLargeText);
        jSpecialModesPanel.add(jSADetails);
        jSpecialModesPanel.add(jSA);
      }
      if (sSpecialDefense.equalsIgnoreCase("Y") ||
          sSpecialDefense.length() > 2) {
        JLabel jSDDetails = new JLabel("SD");
        jSDDetails.setForeground(Color.GREEN);
        jSDDetails.setFont(fSmallText);
        JLabel jSD = new JLabel(sSpecialDefense);
        jSD.setForeground(Color.WHITE);
        jSD.setFont(fLargeText);
        jSpecialModesPanel.add(jSDDetails);
        jSpecialModesPanel.add(jSD);
      }

//      jThisBattleSheet.add(jSpecialModesPanel);
      thisCreaturePanel.add(jSpecialModesPanel);
    }

    /// armor panel
    FlowLayout vFlowLayoutAP = new FlowLayout(FlowLayout.LEFT);
    vFlowLayoutAP.setVgap(0);
    vFlowLayoutAP.setHgap(2);
    JPanel jArmorPanel = new JPanel(vFlowLayoutAP);
    jArmorPanel.setBackground(cColor);

    bArmorShieldSelect.setFont(fSmallText);
    bArmorShieldSelect.setBackground(Color.darkGray);
    jArmorPanel.add(bArmorShieldSelect);

    JLabel jArmorDetails2 = new JLabel("Armor Dmg");
    jArmorDetails2.setToolTipText("Damage armor has taken, -N or +N and press enter, where N is a number.");
    jArmorDetails2.setFont(fSmallText);
    jArmorDetails2.setForeground(Color.GREEN);
    lArmorHPAdj.setPreferredSize(new Dimension(35, 20));
    jArmorPanel.add(jArmorDetails2);
    jArmorPanel.add(lArmorHPAdj);

    // shield section
      JLabel jShieldDetails2 = new JLabel("Shield Dmg");
      jShieldDetails2.setToolTipText("Damage shield has taken, -N or +N and press enter, where N is a number.");
      jShieldDetails2.setFont(fSmallText);
      jShieldDetails2.setForeground(Color.GREEN);
      tShieldHPAdj.setPreferredSize(new Dimension(35, 20));
      jArmorPanel.add(jShieldDetails2);
      jArmorPanel.add(tShieldHPAdj);
      // end shield section

      JLabel jArmorDetails3a = new JLabel("Armor");
      jArmorDetails3a.setToolTipText("The type of armor worn.");
      jArmorDetails3a.setFont(fSmallText);
      jArmorDetails3a.setForeground(Color.GREEN);
      jArmorPanel.add(jArmorDetails3a);
      jArmorWornName.setFont(fLargeText);
      jArmorWornName.setToolTipText(jArmorDetails3a.getToolTipText());
      jArmorWornName.setForeground(Color.WHITE);
      jArmorPanel.add(jArmorWornName);

      JLabel jArmorDetails3 = new JLabel("AC");
      jArmorDetails3.setToolTipText("The AC granted by armor worn.");
      jArmorDetails3.setFont(fSmallText);
      jArmorDetails3.setForeground(Color.GREEN);
      jArmorPanel.add(jArmorDetails3);
      jACWornLabel.setFont(fLargeText);
      jACWornLabel.setForeground(Color.WHITE);
      jACWornLabel.setToolTipText(jArmorDetails3.getToolTipText());
      jACMagicLabel.setFont(fSmallText);
      jACMagicLabel.setForeground(Color.WHITE);
      jArmorPanel.add(jACWornLabel);
      jArmorPanel.add(jACMagicLabel);

      JLabel jArmorDetails4a = new JLabel("Shield");
      jArmorDetails4a.setToolTipText("The type of shield used.");
      jArmorDetails4a.setFont(fSmallText);
      jArmorDetails4a.setForeground(Color.GREEN);
      jArmorPanel.add(jArmorDetails4a);
      jShieldWornName.setFont(fLargeText);
      jShieldWornName.setToolTipText(jArmorDetails4a.getToolTipText());
      jShieldWornName.setForeground(Color.WHITE);
      jArmorPanel.add(jShieldWornName);

      JLabel jArmorDetails4 = new JLabel("AC");
      jArmorDetails4.setToolTipText("AC granted by shield used.");
      jArmorDetails4.setFont(fSmallText);
      jArmorDetails4.setForeground(Color.GREEN);
      jArmorPanel.add(jArmorDetails4);
      jShieldWornLabel.setFont(fLargeText);
      jShieldWornLabel.setToolTipText(jArmorDetails4.getToolTipText());
      jShieldWornLabel.setForeground(Color.WHITE);
      jShieldMagicLabel.setFont(fSmallText);
      jShieldMagicLabel.setForeground(Color.WHITE);
      jArmorPanel.add(jShieldWornLabel);
      jArmorPanel.add(jShieldMagicLabel);

      JLabel jArmorDetails5 = new JLabel("AC Adj");
      jArmorDetails5.setToolTipText("Adjust AC, this could be dex or adjustments from spells.+1 is improved, -1 is worse.");
      jArmorDetails5.setFont(fSmallText);
      jArmorDetails5.setForeground(Color.GREEN);
      jArmorPanel.add(jArmorDetails5);

      jACMod.setPreferredSize(new Dimension(35,20));
      jArmorPanel.add(jACMod);

//      jThisBattleSheet.add(jArmorPanel);
    thisCreaturePanel.add(jArmorPanel);
    // end armor panel

    // select who are are attacking directly
    JLabel jAttackingWhom = new JLabel("Attacking");
    jAttackingWhom.setFont(fSmallText);
    jAttackingWhom.setForeground(Color.GREEN);
    jAttackingWhom.setToolTipText("Who is this creature attacking?");
    JLabel jTargetACLabel = new JLabel("AC");
    jTargetACLabel.setToolTipText("What AC should this creature attack on the target? From rear, from shieldless side, surprised them or just normal.");
    jTargetACLabel.setForeground(Color.GREEN);
    jTargetACLabel.setFont(fSmallText);

    // mark currently selected player
//    int nAtkSelect = jAttackingThisPerson.getSelectedIndex();
    Object oTargetObject = jAttackingThisPerson.getSelectedItem();
    int nAtkACSelect = jTargetAC.getSelectedIndex();

    jAttackingThisPerson.removeAllItems();
    jAttackingThisPerson.setFont(fSmallText);
    this.jTargetAC.setFont(fSmallText);
    jAttackingThisPerson.addItem("DEFAULT");
    for (int nAtkList = 0; nAtkList < oParent.gplGroupLog.lPlayers.size(); nAtkList++) {
      TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(nAtkList);
      if (!oPlayer.jAbsent.isSelected()) {
        jAttackingThisPerson.addItem(oPlayer);
      }
    }
    // set selected to player we had selected if we had it
    jAttackingThisPerson.setSelectedItem(oTargetObject);
    // if that object not exist we just set to 0.
    if (jAttackingThisPerson.getSelectedIndex() < 0) {
      jAttackingThisPerson.setSelectedIndex(0);

//    if (nAtkSelect >= 0) {
//      jAttackingThisPerson.setSelectedIndex(nAtkSelect);
//    }

    }
    if (nAtkACSelect >= 0) {
      jTargetAC.setSelectedIndex(nAtkACSelect);
    }

    thisCreaturePanel2.add(jAttackingWhom);
    thisCreaturePanel2.add(jAttackingThisPerson);
    thisCreaturePanel2.add(jTargetACLabel);
    thisCreaturePanel2.add(jTargetAC);

    // end selected target section

    jInitiative.setFont(fLargeText);
    jInitiative.setForeground(Color.WHITE);
    thisCreaturePanel2.add(jInitiative); // initiative label
//    thisCreaturePanel.add(jInitiative); // initiative label

    jThisBattleSheet.add(thisCreaturePanel2);
//    thisCreaturePanel.add(thisCreaturePanel2);

    // who is creature attacking section...

    // now we display all the creatures attacks...
    boolean bDarkGrey = true;
//    VerticalFlowLayout layCombatLay = new VerticalFlowLayout(VerticalFlowLayout.TOP);
//    layCombatLay.setVgap(5);
//    layCombatLay.setHgap(3);
    JPanel jThisCombatPanel = new JPanel(vLayout);
    Border combatborder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        Color.white,
        Color.white,
        Color.white,
        Color.white);
    TitledBorder combattitledBorder = new TitledBorder(combatborder, "Attacks");
    combattitledBorder.setTitleColor(Color.WHITE);
    combattitledBorder.setTitleFont(fSmallText);
    jThisCombatPanel.setBorder(combattitledBorder);
    jThisCombatPanel.setBackground(cColor);
    jThisCombatPanel.setForeground(Color.WHITE);

    jMyAttackTabPane.setBackground(cColor);
    jMyAttackTabPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    jMyAttackTabPane.setFont(new Font("Dialog",Font.PLAIN,11));
    jMyAttackTabPane.removeAll();

    for (int j = 0; j < lAttacks.size(); j++) {
      CreatureCombat thisAttack = (CreatureCombat) lAttacks.get(j);
      JTabbedPane jAttackTabPane = new JTabbedPane();
//      JTabbedPane jAttackTabPane = thisAttack.jThisAttackTab;

      jAttackTabPane.setTabLayoutPolicy(JTabbedPane.TOP);
      jAttackTabPane.setTabPlacement(JTabbedPane.TOP);
      jAttackTabPane.setFont(fSmallText);

      jMyAttackTabPane.add(jAttackTabPane,"Attack #"+(j+1));
      if (thisAttack.initThisAttackReady()) {
        thisAttack.initSetAttackActive();
      }
      // attack button, roll/etc
      JPanel combatPanel = new JPanel(vFlowLayoutAP);

      // the set xDx+/-x panel
      JPanel combatPanel2 = new JPanel(vFlowLayoutAP);

      // the display xDx+/-x panel
      JPanel combatPanel3 = new JPanel(vFlowLayoutAP);

      // attack tab
      jAttackTabPane.add(combatPanel3, "Attack");
      jAttackTabPane.setToolTipTextAt(0, "Attack button, weapon select.");
//      jAttackTabPane.setBackgroundAt(0,Color.BLACK);
//      jAttackTabPane.setForegroundAt(0,Color.WHITE);
      // modify tab
      jAttackTabPane.add(combatPanel2, "Modify");
      jAttackTabPane.setToolTipTextAt(1,
          "Modifiy to hit, to damage and monster-natural weapon attacks.");
//      jAttackTabPane.setBackgroundAt(1,Color.BLACK);
//      jAttackTabPane.setForegroundAt(1,Color.GREEN);
      // special mod tab
      jAttackTabPane.add(combatPanel, "Special");
      jAttackTabPane.setToolTipTextAt(2,
          "Modify penetration, crit and fumble chances.");
//      jAttackTabPane.setBackgroundAt(2,Color.BLACK);
//      jAttackTabPane.setForegroundAt(2,Color.YELLOW);
      // end tabs

      // flip colours
      if (bDarkGrey) {
        jAttackTabPane.setBackground(Color.gray);
        combatPanel3.setBackground(Color.gray);
        combatPanel2.setBackground(Color.gray);
        combatPanel.setBackground(Color.gray);
      }
      else {
        jAttackTabPane.setBackground(Color.lightGray);
        combatPanel3.setBackground(Color.lightGray);
        combatPanel2.setBackground(Color.lightGray);
        combatPanel.setBackground(Color.lightGray);
      }
      bDarkGrey = ! (bDarkGrey); // flip value
      // end colours


      JLabel jLabelWeapon = new JLabel("Weapon Type");
      jLabelWeapon.setToolTipText(
          "Weapon type used, monster if claws, bite or the like.");
      jLabelWeapon.setFont(fSmallText);
      combatPanel.add(jLabelWeapon);
      combatPanel.add(thisAttack.jWeaponType);

      JLabel jLabelPenMod = new JLabel("Petration Mod");
      jLabelPenMod.setToolTipText(
          "Improve penetration roll so it occures on lower than MAX rolls.2 with d8 would penetrate at 6,7 and 8.");
      jLabelPenMod.setFont(fSmallText);
      combatPanel.add(jLabelPenMod);
      combatPanel.add(thisAttack.jModPenetration);

      JLabel jLabelFumbleMod = new JLabel("Fumble Mod");
      jLabelFumbleMod.setToolTipText(
          "Increase chance to fumble, default is 1.");
      jLabelFumbleMod.setFont(fSmallText);
      combatPanel.add(jLabelFumbleMod);
      combatPanel.add(thisAttack.jModFumble);

      JLabel jLabelCrit = new JLabel("Critical Mod");
      jLabelCrit.setToolTipText(
          "Increase chance to critical hit. Each point reduces cap from 20, so at 2 crit would occur on 18,19 or 20.");
      jLabelCrit.setFont(fSmallText);
      combatPanel.add(jLabelCrit);
      combatPanel.add(thisAttack.jModCrit);

// second attack panel
      combatPanel2.add(thisAttack.bAttackButton);

      JLabel jLabelToHitMod = new JLabel("To-Hit");
      jLabelToHitMod.setToolTipText("Adjustment applied to attack roll.");
      jLabelToHitMod.setFont(fSmallText);
      combatPanel2.add(jLabelToHitMod);
      combatPanel2.add(thisAttack.jToHitMod);

      JLabel jLabelDamageDice = new JLabel("Dam");
      jLabelDamageDice.setToolTipText(
          "Damage");
      jLabelDamageDice.setFont(fSmallText);
      combatPanel2.add(jLabelDamageDice);
      combatPanel2.add(thisAttack.jDamageDice);
      // set proper values on the label
      thisAttack.jDamageDice_propertyChange(null);

      /*      JLabel jLabelNumSides = new JLabel("D");
            jLabelNumSides.setToolTipText(
                "Number of sides on a dice, in 2d4 this would be 4.");
            jLabelNumSides.setFont(fSmallText);
            combatPanel2.add(jLabelNumSides);
            combatPanel2.add(thisAttack.jDiceSides);

            JLabel jLabelPerDiceMod = new JLabel("Dice");
            jLabelPerDiceMod.setToolTipText("This adjustment applied to every dice rolled, on 2d4-2 each roll would be -2 (minimum of 1)");
            jLabelPerDiceMod.setFont(fSmallText);
            combatPanel2.add(jLabelPerDiceMod);
            combatPanel2.add(thisAttack.jPerDiceMod);
       */
      JLabel jLabelTotalMod = new JLabel("Total");
      jLabelTotalMod.setToolTipText(
          "Adjustment applied after all damage dice rolled.");
      jLabelTotalMod.setFont(fSmallText);
      combatPanel2.add(jLabelTotalMod);
      combatPanel2.add(thisAttack.jTotalMod);

// ** new attack panel
      combatPanel3.add(thisAttack.bAttackButton);

      // melee weapon?
      thisAttack.setupWeaponUsedBox(oParent.lGear);
      JLabel lWeaponUsedLabel = new JLabel("Weapon");
      lWeaponUsedLabel.setFont(fSmallText);
      lWeaponUsedLabel.setToolTipText("Weapon used if not natural weapon.");
      combatPanel3.add(lWeaponUsedLabel);
      thisAttack.jWeaponUsed.setFont(fSmallText);
      combatPanel3.add(thisAttack.jWeaponUsed);
      //

      JLabel jLabelSpecRoll = new JLabel("Roll");
      jLabelSpecRoll.setToolTipText(
          "Specify dice roll, use this one and do not generate one.");
      jLabelSpecRoll.setFont(fSmallText);
      combatPanel3.add(jLabelSpecRoll);
      combatPanel3.add(thisAttack.jSpecRoll);

      // tohit mods
      thisAttack.jLabelToHitModDetails.setToolTipText(
          "Adjustment applied to attack roll.");
      thisAttack.jLabelToHitModDetails.setFont(fLargeText);
      combatPanel3.add(thisAttack.jLabelToHitModDetails);

      // dice rolled
      thisAttack.jLabelDamageDiceDetails.setToolTipText(
          "Damage roll, xDx+(PerRollMod)");
      thisAttack.jLabelDamageDiceDetails.setFont(fLargeText);
      combatPanel3.add(thisAttack.jLabelDamageDiceDetails);

      // damage applied
      thisAttack.jLabelToDamModDetails.setToolTipText(
          "Damage applied to final rolls.");
      thisAttack.jLabelToDamModDetails.setFont(fLargeText);
      combatPanel3.add(thisAttack.jLabelToDamModDetails);

      JLabel jLabelACHit = new JLabel("AC");
      jLabelACHit.setToolTipText("AC Hit.");
      jLabelACHit.setFont(fSmallText);
      combatPanel3.add(jLabelACHit);
      combatPanel3.add(thisAttack.lACHit);

      JLabel jLabelDamage = new JLabel("DAM");
      jLabelDamage.setToolTipText("Damage from hit.");
      jLabelDamage.setFont(fSmallText);
      combatPanel3.add(jLabelDamage);
      combatPanel3.add(thisAttack.lDamage);

      JLabel jLabelSpec = new JLabel("SPEC");
      jLabelSpec.setToolTipText("Special effect, fumble or critical.");
      jLabelSpec.setFont(fSmallText);
      combatPanel3.add(jLabelSpec);
      combatPanel3.add(thisAttack.lCombat);

// ** end new attack panel

//      jThisCombatPanel.add(jAttackTabPane);
      jThisCombatPanel.add(jMyAttackTabPane);
      jThisBattleSheet.add(jThisCombatPanel);
    }

    FlowLayout fLeftFlow = new FlowLayout(FlowLayout.LEFT);

    JPanel attackedByPanel = new JPanel(fLeftFlow);
    attackedByPanel.setBackground(Color.gray);
    JLabel jAttackedByLabel = new JLabel("Attacked By:");
    jAttackedByLabel.setFont(fSmallText);
    jAttackedByLabel.setForeground(Color.BLACK);
    jAttackedBy.setToolTipText("Who is attacking this creature.");
    jAttackedByLabel.setToolTipText(jAttackedBy.getToolTipText());
    jAttackedBy.setFont(fLargeText);
    jAttackedBy.setForeground(Color.WHITE);

    attackedByPanel.add(jAttackedByLabel);
    attackedByPanel.add(jAttackedBy);

    JPanel combatFooterPanel = new JPanel(fLeftFlow);
    combatFooterPanel.setBackground(Color.lightGray);
    jNotePad.setColumns(60);
    JLabel jNoteLabelFooter = new JLabel("Note:");
    jNoteLabelFooter.setForeground(Color.black);

    bDesc.setFont(fSmallText);
    bKill.setFont(fSmallText);
    bKill.setToolTipText("Defeat this creature and award the exp to group log.");
    bDelete.setFont(fSmallText);
    bDelete.setToolTipText(
        "Delete this creature and do not award exp to the group log.");
    combatFooterPanel.add(bDesc);
    combatFooterPanel.add(bKill);
    combatFooterPanel.add(bDelete);

    combatFooterPanel.add(jNoteLabelFooter);
    combatFooterPanel.add(jNotePad);

    jThisBattleSheet.add(attackedByPanel);
    jThisBattleSheet.add(combatFooterPanel);

    return jThisBattleSheet;
  }

  // get all the weapons in the gear list and allow me to select
  // them as a weapon on the battle sheet
  public void setupMyWeaponUsedBoxs(ArrayList lGearList) {

    for (int j = 0; j < lAttacks.size(); j++) {
      CreatureCombat oAtk = (CreatureCombat) lAttacks.get(j);
      oAtk.setupWeaponUsedBox(lGearList);
    }

  }

  // get AC of armor w/magic bonuses
  public int getArmorAC() {
  int nACValue = 0;   // default is our static/natural AC

  if (oArmorWorn != null) {
    nACValue = oArmorWorn.nACBaseCurrent;
    nACValue -= oArmorWorn.nACMagicBonusCurrent;
  }
  return nACValue;
  }

  // get Shield AC with magic bonuses
  public int getShieldAC() {
  int nACValue = 0;

  if (oShieldWorn != null) {
    nACValue = oShieldWorn.nShieldACBaseCurrent;
    nACValue += oShieldWorn.nShieldACMagicBonusCurrent;
  }

  return nACValue;
  }

  // get the completely adjust armor w/armor and shield or natural
  public int getArmorClass() {
  int nACValue = nAC;   // default is our static/natural AC

  int nACMod = Integer.parseInt(jACMod.getValue().toString());

  if (oArmorWorn != null) {
    nACValue = getArmorAC();
  }

  if (oShieldWorn != null) {
    nACValue -= getShieldAC();
  }

  nACValue -= nACMod;

  return nACValue;
  }

  // return the magic bonus from this armor
  public int getArmorClassMagicBonus() {
  int nACValue = 0;

  if (oArmorWorn != null) {
    nACValue = oArmorWorn.nACMagicBonusCurrent;
  }

  return nACValue;
  }

  // return the magic bonus from this shield
  public int getShieldMagicBonus() {
  int nACValue = 0;

  if (oShieldWorn != null) {
    nACValue = oShieldWorn.nShieldACMagicBonusCurrent;
  }

  return nACValue;
  }

  // get the worn armor name (Platemail, chainmail/etc)
  public String getArmorName () {
    String sName = "none";
    if (oArmorWorn != null) {
      sName = oArmorWorn.sName.replaceFirst("Armor and Related Items:Armor,","");
      lArmorHPAdj.setEnabled(true);
    } else
      lArmorHPAdj.setEnabled(false);

    return sName.trim();
  }
  // get the shield name (Medium, body/etc)
  public String getShieldName() {
    String sName = "none";
    if (oShieldWorn != null) {
      sName = oShieldWorn.sName.replaceFirst("Armor and Related Items:Shield,","");
      tShieldHPAdj.setEnabled(true);
    }  else
      tShieldHPAdj.setEnabled(false);


    return sName.trim();
  }
  // set the armor/shield name labels to the current names
  public void setArmorSheildNameLabel()
  {
    jArmorWornName.setText(getArmorName());
    jShieldWornName.setText(getShieldName());
  }

  public void setArmorClassLabel() {
    lAC.setText(getArmorClass()+"");

    int nArmorMagic = getArmorClassMagicBonus();
    jACWornLabel.setText(""+getArmorAC());
    jACMagicLabel.setText((nArmorMagic > 0?"(+"+nArmorMagic+")":"")  );

    int nShieldMagic = getShieldMagicBonus();
    jShieldWornLabel.setText(""+getShieldAC());
    jShieldMagicLabel.setText((nShieldMagic > 0?"(+"+nShieldMagic+")":"")) ;

    //now set the name labels so we know what we got on.
    setArmorSheildNameLabel();
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("BSCreature");

    eItem.addContent(new Element("sCreatureName").setText(xmlControl.escapeChars(sCreatureName)));

    eItem.addContent(new Element("jACMod").setText(jACMod.getValue().toString()));
    eItem.addContent(new Element("jHitPointLabel").setText(jHitPointLabel.getText()));
    eItem.addContent(new Element("jNotePad").setText(jNotePad.getText()));
    eItem.addContent(new Element("jClassSelect").setText(""+jClassSelect.getSelectedIndex()));

    eItem.addContent(new Element("nCreatureID").setText(""+nCreatureID));
    eItem.addContent(new Element("nAC").setText(""+nAC));
    eItem.addContent(new Element("nACBase").setText(""+nACBase));
    eItem.addContent(new Element("nEXP").setText(""+nEXP));
    eItem.addContent(new Element("nHonor").setText(""+nHonor));
//    eItem.addContent(new Element("").setText(""+lAttacks.size()));
    eItem.addContent(new Element("nHitPoints").setText(""+nHitPoints));
    eItem.addContent(new Element("nLevel").setText(""+nLevel));
    eItem.addContent(new Element("nToHitRank").setText(""+nToHitRank));

    eItem.addContent(new Element("sDescription").setText(xmlControl.escapeChars(sDescription)));
    eItem.addContent(new Element("sSpecialAttack").setText(xmlControl.escapeChars(sSpecialAttack)));
    eItem.addContent(new Element("sSpecialDefense").setText(xmlControl.escapeChars(sSpecialDefense)));
    eItem.addContent(new Element("sMagicDefense").setText(xmlControl.escapeChars(sMagicDefense)));
    eItem.addContent(new Element("sPSIAttack").setText(xmlControl.escapeChars(sPSIAttack)));
    eItem.addContent(new Element("sPSIDefense").setText(xmlControl.escapeChars(sPSIDefense)));
    eItem.addContent(new Element("sHOBLocation").setText(xmlControl.escapeChars(sHOBLocation)));

    eItem.addContent(new Element("nBASENumAtks").setText(""+nBASENumAtks));
    eItem.addContent(new Element("nBASEMorale").setText(""+nBASEMorale));
    eItem.addContent(new Element("nBASEMove").setText(""+nBASEMove));
    eItem.addContent(new Element("nBASESizeIndex").setText(""+nBASESizeIndex));
    eItem.addContent(new Element("nBASEAlignmentIndex").setText(""+nBASEAlignmentIndex));
    eItem.addContent(new Element("nBASEHackFactor").setText(""+nBASEHackFactor));
    eItem.addContent(new Element("nFatigueFactor").setText(""+nFatigueFactor));
    eItem.addContent(new Element("nCombatRounds").setText(""+nCombatRounds));

    if (oShieldWorn != null) {
      Element eShieldWorn = new Element("Shield_Worn");
      eShieldWorn.addContent(oShieldWorn.xmlGetElements());
      eItem.addContent(eShieldWorn);
    }
    if (oArmorWorn != null) {
      Element eArmorWorn = new Element("Armor_Worn");
      eArmorWorn.addContent(oArmorWorn.xmlGetElements());
      eItem.addContent(eArmorWorn);
    }

    for (int i=0;i<lAttacks.size();i++) {
      CreatureCombat oC = (CreatureCombat)lAttacks.get(i);
      eItem.addContent(oC.xmlGetElements());
    }

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static CreatureCore xmlGetFromElements(Element eItem,
                                         HackSackFrame oParent) {
    CreatureCore oO = new CreatureCore(oParent,"");

    oO.sCreatureName = xmlControl.unEscapeChars(eItem.getChild("sCreatureName").getText());
    oO.sDescription = xmlControl.unEscapeChars(eItem.getChild("sDescription").getText());
    oO.sSpecialAttack = xmlControl.unEscapeChars(eItem.getChild("sSpecialAttack").getText());
    oO.sSpecialDefense = xmlControl.unEscapeChars(eItem.getChild("sSpecialDefense").getText());
    oO.sMagicDefense = xmlControl.unEscapeChars(eItem.getChild("sMagicDefense").getText());
    oO.sPSIAttack = xmlControl.unEscapeChars(eItem.getChild("sPSIAttack").getText());
    oO.sPSIDefense = xmlControl.unEscapeChars(eItem.getChild("sPSIDefense").getText());
    oO.sHOBLocation = xmlControl.unEscapeChars(eItem.getChild("sHOBLocation").getText());
    oO.jNotePad.setText(xmlControl.unEscapeChars(eItem.getChild("jNotePad").getText()));

    oO.nCreatureID = Integer.parseInt(eItem.getChild("nCreatureID").getText());
    oO.nAC = Integer.parseInt(eItem.getChild("nAC").getText());
    oO.nACBase = Integer.parseInt(eItem.getChild("nACBase").getText());
    oO.nEXP = Integer.parseInt(eItem.getChild("nEXP").getText());
    oO.nHonor = Integer.parseInt(eItem.getChild("nHonor").getText());
    oO.nHitPoints = Integer.parseInt(eItem.getChild("nHitPoints").getText());
    oO.nLevel = Integer.parseInt(eItem.getChild("nLevel").getText());
    oO.nToHitRank = Integer.parseInt(eItem.getChild("nToHitRank").getText());

    oO.nBASENumAtks = Integer.parseInt(eItem.getChild("nBASENumAtks").getText());
    oO.nBASEMorale = Integer.parseInt(eItem.getChild("nBASEMorale").getText());
    oO.nBASEMove = Integer.parseInt(eItem.getChild("nBASEMove").getText());
    oO.nBASESizeIndex = Integer.parseInt(eItem.getChild("nBASESizeIndex").getText());
    oO.nBASEAlignmentIndex = Integer.parseInt(eItem.getChild("nBASEAlignmentIndex").getText());
    oO.nBASEHackFactor = Integer.parseInt(eItem.getChild("nBASEHackFactor").getText());
    oO.nFatigueFactor = Integer.parseInt(eItem.getChild("nFatigueFactor").getText());
    oO.nCombatRounds = Integer.parseInt(eItem.getChild("nCombatRounds").getText());

    oO.jACMod.setValue(new Integer(Integer.parseInt(eItem.getChild("jACMod").getText())));
    oO.jHitPointLabel.setText(eItem.getChild("jHitPointLabel").getText());
    oO.jClassSelect.setSelectedIndex(Integer.parseInt(eItem.getChild("jClassSelect").getText()));

    Element eShieldWorn = eItem.getChild("Shield_Worn");
    if (eShieldWorn != null) {
      oO.oShieldWorn = Gear.xmlGetFromElements(eShieldWorn.getChild("Gear"));
    }

    Element eArmorWorn = eItem.getChild("Armor_Worn");
    if (eArmorWorn != null) {
      oO.oArmorWorn = Gear.xmlGetFromElements(eArmorWorn.getChild("Gear"));
    }

    java.util.List lAtks = eItem.getChildren("Attack");
    Iterator in = lAtks.iterator();
    while (in.hasNext()) {
      Element eO = (Element)in.next();
      oO.lAttacks.add(CreatureCombat.xmlGetFromElements(eO,oParent,oO));
    }


    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved
   * to an xml file.
   *
   * we do this one a bit differently and build the TableInformation
   * into this Doc as well.
   *
   * @param lBSCreatureList ArrayList
   * @param lInfoList ArrayList
   * @param nMaxID int
   * @return Document
   */
  static Document xmlBuildDocFromList(ArrayList lBSCreatureList,
                                         ArrayList lInfoList, int nMaxID) {
      Element eRoot = new Element("BattleSheets");
      eRoot.setAttribute(new Attribute("JDOM","10b"));
//      eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
      Document doc = new Document(eRoot);

      eRoot.addContent(TableInformation.xmlBuildElementFromList(lInfoList));

      for(int i=0;i<lBSCreatureList.size();i++) {

        CreatureCore oO = (CreatureCore)lBSCreatureList.get(i);
        eRoot.addContent(oO.xmlGetElements());

      }

      return doc;
     }

     /**
      * this builds an arraylist of this object type from a document that was
      * built from a xml file, Class.xml, Gear.xml/etc...
      *
      * @param oParent HackSackFrame
      * @param doc Document
      * @return ArrayList
      */
     static ArrayList xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {
       ArrayList lList = new ArrayList();

       try {
       Element eRoot = doc.getRootElement();

       java.util.List lItems = eRoot.getChildren("BSCreature");

       Iterator in = lItems.iterator();
       while (in.hasNext()) {
         Element eItem = (Element)in.next();
         CreatureCore oO = CreatureCore.xmlGetFromElements(eItem,oParent);

         lList.add(oO);
       }

       }
       catch (NullPointerException err) {
         oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                           "Error occured while trying to load creature in battlesheet from XML.");
       }

       return lList;
     }


     static void AskWhatFileToLoad(HackSackFrame oParent, Component oThis) {
     JFileChooser jFileChooser1 = new JFileChooser();
     jFileChooser1.setApproveButtonText("Load");
     jFileChooser1.setDialogTitle("Load Battle Sheet");
     if (System.getProperty("lastbattlesheet.dir") != null)
       jFileChooser1.setCurrentDirectory(new File(System.getProperty("lastbattlesheet.dir")));
       else
       jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                                  File.separatorChar +
                                                  oParent.sBattleSheetDir));


     int returnVal = jFileChooser1.showOpenDialog(oThis);
     if (returnVal == JFileChooser.APPROVE_OPTION) {
       String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
       String sFile = jFileChooser1.getSelectedFile().getName();

       if (!jFileChooser1.getSelectedFile().exists()) {
         oParent.gmLog(true, false, "File " + sFile + " does not exist.\n");
       }
       else {
         if (!oParent.jGMSaveBSCheckBox1.isSelected() && oParent.lCreatures.size() > 0) {
           oParent.fBattleSheetFrame.CleanBattleSheet(oParent);
         }

         FrameStartUp dlg = oParent.ShowStatusProgress(oThis,
                                                       "Loading","Loading battlesheet.");
         try {
           Document doc = xmlControl.loadDoc(oParent, oThis, sFileName);
           if (doc.getRootElement().getAttribute("JDOM") == null) {
             oParent.ShowError(oThis,
                 "Loading old format battlesheet.\nMake sure to save this battlesheet!");
             SaxParserForBS.loadUp(oParent, sFileName, true);
           }
           else {
             oParent.lCreatures.addAll(CreatureCore.xmlGetSavedFromDoc(oParent,
                 doc));
             oParent.lInformation.addAll(TableInformation.xmlGetSavedFromDoc(
                 oParent, doc));
           }
         } catch (OutOfMemoryError err) {
           oParent.ShowError(oThis,"Out Of Memory Error");
           // close this or it'll stay around
           oParent.ShowStatusProgressDone(dlg);
           System.gc();
         }

         oParent.ShowStatusProgressDone(dlg);

         oParent.fBattleSheetFrame.LoadBattleSheetPane(oParent);
         System.setProperty("lastbattlesheet.dir",jFileChooser1.getCurrentDirectory().getAbsolutePath());
         oParent.gmLog(true, false, "Loaded " + sFile + " battle sheet.\n");
       }
     }
     else {
       oParent.gmLog(true, false, "Load cancelled.\n");

     }
   }

   static void AskWhereToSaveBattleSheet(Component oThisFrame,
                                         HackSackFrame oParent) {
     JFileChooser jFileChooser1 = new JFileChooser();
     jFileChooser1.setApproveButtonText("Save");
     jFileChooser1.setDialogTitle("Save Battle Sheet");
     if (System.getProperty("lastbattlesheet.dir") != null) {
       jFileChooser1.setCurrentDirectory(new File(System.getProperty(
           "lastbattlesheet.dir")));
     }
     else {
       jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                                  File.separatorChar +
                                                  oParent.sBattleSheetDir));

     }
     int returnVal = jFileChooser1.showSaveDialog(oThisFrame);
     if (returnVal == JFileChooser.APPROVE_OPTION) {
       String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();

       FrameStartUp dlg = oParent.ShowStatusProgress(oThisFrame,
                                                     "Saving","Saving battlesheet.");
//       oParent.bsSaveBattleSheet.toFile(oParent, sFileName, oParent.lCreatures);
       xmlControl.saveDoc(oParent,oThisFrame,CreatureCore.xmlBuildDocFromList(
             oParent.lCreatures,oParent.lInformation,-1),sFileName);

          oParent.ShowStatusProgressDone(dlg);


       System.setProperty("lastbattlesheet.dir",
                          jFileChooser1.getCurrentDirectory().getAbsolutePath());
//      oParent.gmLog(true,false,"Saved battle sheet to file "+sFile+".\n");
     }
     else {
       oParent.gmLog(true, false, "Saved cancelled.\n");

     }

   }

} // end CreatureCore

class CreatureCoreButton
    extends JButton {
  private CreatureCore creature = null;
  private String sThisButtonName = null;

  private CreatureCoreButton() {
  }

  public CreatureCoreButton(CreatureCore creature, String sButtonName) {
    this.creature = creature;
    sThisButtonName = sButtonName;

    addActionListener(new CreatureCoreButtonListener(creature, sThisButtonName));
  }
}

class CreatureCoreButtonListener
    implements ActionListener {
  private CreatureCore creature = null;
  private String sThisButtonName = null;
  public CreatureCoreButtonListener(CreatureCore creature, String sButtonName) {
    this.creature = creature;
    sThisButtonName = sButtonName;

  }

  public void actionPerformed(ActionEvent e) {
    creature.updateButtonPressed(creature, sThisButtonName);
  }
}

class CreatureTextField
    extends JTextField {
  private CreatureCore creature = null;
  private String sName = null;

  private CreatureTextField() {
  }

  public CreatureTextField(CreatureCore creature, String sName) {
    this.creature = creature;
    this.sName = sName;
//    setText("SAVE");

    addActionListener(new CreatureTextFieldListener(creature, sName));
  }
}

class CreatureTextFieldListener
    implements ActionListener {
  private CreatureCore creature = null;
  private String sName = null;

  public CreatureTextFieldListener(CreatureCore creature, String sName) {
    this.creature = creature;
    this.sName = sName;
  }

  public void actionPerformed(ActionEvent e) {
    creature.updateTextField(creature, sName);
  }
}

//-----------------attacking this person pull down
class jAttackingThisPersonPullDownListener
    implements ActionListener {
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private CreatureCore oMe = null;

  public jAttackingThisPersonPullDownListener(HackSackFrame oParent,
                                              String sButtonName,
                                              CreatureCore oMe) {
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oMe = oMe;

  }

  public void actionPerformed(ActionEvent e) {
    oMe.updateAttackingPulldown(oParent, sThisButtonName, oMe);
  }
}

//-- armor mod and ac mod spinner
class jCreatureSpinnerListener
    implements javax.swing.event.ChangeListener {
  private String sThisButtonName = null;
  private CreatureCore oC = null;

  public jCreatureSpinnerListener(CreatureCore oC, String sButtonName) {
    this.sThisButtonName = sButtonName;
    this.oC = oC;
  }

  public void stateChanged(ChangeEvent e) {
    oC.updatedSpinner(oC, sThisButtonName);
  }
}
