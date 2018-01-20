package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogFancyCrit extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  BorderLayout borderLayout2 = new BorderLayout();
//  JPanel jCritDisplayPanel = new JPanel();
    Box jCritDisplayPanel = Box.createVerticalBox();
  JPanel jButtonPanel = new JPanel();
  JButton jCloseButton = new JButton();

  public DialogFancyCrit(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogFancyCrit(CreatureCombat oAtk, TablePlayer oPlayer, CritResults oCrit) {
    this(null, "", false);

    boolean bFull = (oAtk != null && oPlayer != null);
    if (bFull) {
      if (oAtk.oParent.jGMDetailsCheckBox.isSelected()) {
//        oAtk.oParent.jGMTextArea.append("Section used:"+oCrit.sSectionUsed+" weapon type "+oCrit.nWeaponType+"\n");
      }
      this.setTitle(oPlayer.sCharacter + " critically hit by " +
                    oAtk.oCreature.sCreatureName + "(" +
                    oCrit.nDiceRoll + " on a d10,000 w/" + oCrit.nBSL + " BSL)");

      if (oAtk.oCreature.oParent.jGMConfigCritAwardCheckBox2.isSelected())
      {
        // setup honor awards for crits
        HonorAward oHonorAward = new HonorAward(oAtk.oCreature.oParent);
        oHonorAward.nID = -20;
        oHonorAward.lIndividual = oPlayer.lPlayerID;
        oHonorAward.sDetails = oPlayer.sCharacter +
            " received critical hit for " + oCrit.nSeverity + " severity.";
        oHonorAward.nSpecificAward = oCrit.nSeverity;
        oAtk.oCreature.oParent.gplGroupLog.lHonor.add(oHonorAward);
      }

    }
  else
    this.setTitle("Critical Hit");

    Font fFont = new Font("Dialog",Font.BOLD,12);
    Font fpFont = new Font("Dialog",Font.ITALIC,12);

    JPanel jLocation = new JPanel (new FlowLayout(FlowLayout.LEFT));
    jLocation.setBackground(Color.yellow);
    JLabel jLocationLabel = new JLabel("Location");
    int nMaxDamageForLoc = (int)(oCrit.dMaxDamageAtLocation*100);
    JLabel jLocationLabeld = new JLabel(oCrit.sLocation+"("+oCrit.sSide+")"+
                                        ", max damage for location "+nMaxDamageForLoc+"%");
    jLocationLabel.setFont(fFont);
    jLocationLabel.setForeground(Color.BLACK);
    jLocationLabeld.setFont(fpFont);
    jLocationLabeld.setForeground(Color.BLACK);
    jLocation.add(jLocationLabel);
    jLocation.add(jLocationLabeld);
    jCritDisplayPanel.add(jLocation);

    JPanel jSeverity = new JPanel (new FlowLayout(FlowLayout.LEFT));
    JLabel jSeverityLabel = new JLabel("Severity");
    JLabel jSeverityLabeld = new JLabel(""+oCrit.nSeverity);
    jSeverityLabel.setFont(fFont);
    jSeverityLabel.setForeground(Color.BLACK);
    jSeverityLabeld.setFont(fpFont);
    jSeverityLabeld.setForeground(Color.BLACK);
    jSeverity.add(jSeverityLabel);
    jSeverity.add(jSeverityLabeld);
    jCritDisplayPanel.add(jSeverity);

    boolean bFlip = true;
    if (oCrit.bHasPermScar || !oCrit.bCanCrit || !oCrit.bCanFollowThrough || !oCrit.bCanPenetrate )
    {
      JPanel jAddtional = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jAddtional.setBackground(Color.yellow);

      String sAdd = "";
      if (oCrit.bHasPermScar)
        sAdd += "A permanent scar. ";
      if (!oCrit.bCanCrit || !oCrit.bCanFollowThrough || !oCrit.bCanPenetrate)
      {
        sAdd += "Target can no longer perform ";
        if (!oCrit.bCanFollowThrough)
          sAdd += "follow through damage";
        if (!oCrit.bCanCrit)
          sAdd += (oCrit.bCanPenetrate ? " or " : ",") + "criticals ";
        if (!oCrit.bCanPenetrate)
          sAdd += "or penetration damage";

      }

      JLabel jAdditionalLabel = new JLabel("Critical Caused");
      jAdditionalLabel.setFont(fFont);
      jAdditionalLabel.setForeground(Color.BLACK);
      jAddtional.add(jAdditionalLabel);

      JLabel jAdditionalLabeld = new JLabel(sAdd);
      jAdditionalLabeld.setFont(fpFont);
      jAdditionalLabeld.setForeground(Color.BLACK);
      jAddtional.add(jAdditionalLabeld);
      jCritDisplayPanel.add(jAddtional);
    } else bFlip = false;

    boolean bCritHealInfoRequired = false;
    for (int i=0;i<oCrit.lCritEffects.size();i++)
    {
      CritEffect oE = (CritEffect)oCrit.lCritEffects.get(i);

      // if we hit any of these locations then we display heal info
      if (oE.sName.matches("(?i).*?tendon.*?") || oE.sDesc.matches("(?i).*?tendon.*?") ||
          oE.sName.matches("(?i).*?ligament.*?") || oE.sDesc.matches("(?i).*?ligament.*?") ||
          oE.sName.matches("(?i).*?bone.*?") || oE.sDesc.matches("(?i).*?bone.*?") ||
          oE.sName.matches("(?i).*?concussion.*?") || oE.sDesc.matches("(?i).*?concussion.*?") ||
          oE.sName.matches("(?i).*?nerve.*?") || oE.sDesc.matches("(?i).*?nerve.*?") ||
          oE.sName.matches("(?i).*?paralysis.*?") || oE.sDesc.matches("(?i).*?paralysis.*?") ||
          oE.sName.matches("(?i).*?severed.*?") || oE.sDesc.matches("(?i).*?severed.*?") )
          bCritHealInfoRequired = true;

      JPanel jEffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      JLabel jEff = new JLabel(oE.sName);

    // extra damage and disabled body parts
      if (oE.sName.equalsIgnoreCase("Additional Damage Dice")  &&
          (oCrit.oAtk != null)) {
        if (oE.sDesc.matches("(\\d+)")) {
          int nDamageMultiplier = Integer.parseInt(oE.sDesc);

          TablePlayer oTarget = oCrit.oAtk.oCreature.getWhoImAttacking();
          int nMaxHealth = Integer.parseInt(oTarget.jHealthMax.getValue().toString());
          int nMaxDamageCanTake = (int)(nMaxHealth*oCrit.dMaxDamageAtLocation);

          Dicer dDicer = new Dicer();
          int nDiceSize = dDicer.getNumDiceSides(oCrit.sDamageDice);
          int nDamageDiceMod = dDicer.getPerDiceMod(oCrit.sDamageDice);
          int nPenetration = Integer.parseInt(oCrit.oAtk.jModPenetration.getModel().getValue().toString());
          int nRollTotal = 0;


          for (int nDamBonus = 0;nDamBonus < nDamageMultiplier;nDamBonus++) {
            nRollTotal += dDicer.rollDamage(1, nDiceSize, nDamageDiceMod,
                              0, nPenetration,oAtk.oParent.jADDPenetrationCheckBox.isSelected());
          }
          if (oCrit.oAtk.oParent.jGMDetailsCheckBox.isSelected()) {
            oCrit.oAtk.log(oCrit.oAtk, true, false,oCrit.oAtk.oCreature.sCreatureName+" critically hit for additional damage.\n");
            for (int iz = 0; iz < dDicer.lDice.size(); iz++) {
              Dice dDice = (Dice) dDicer.lDice.get(iz);
              oCrit.oAtk.log(oCrit.oAtk, true, false, dDice.sDesc + dDice.nRolled + "\n");
            }
            oCrit.oAtk.log(oCrit.oAtk, true, false,"Extra Crit Damage :"+nRollTotal+"\n");
          }

//          int nCritAdditionalDamage = nRollTotal;
          int nOverFlowDamage = 0;
          // if basedamage is smaller than nmax damage can take for loc
          if (oCrit.nBaseDamageDone < nMaxDamageCanTake) {
            nRollTotal += oCrit.nBaseDamageDone;
            // total more than nMax now, remove overflow damage
            if (nRollTotal > nMaxDamageCanTake) {
              nOverFlowDamage = nRollTotal - nMaxDamageCanTake;
              nRollTotal = nMaxDamageCanTake;
            }
          } else {
            // damage already at max for location, we dont lose any of base
            // roll of damage
            nOverFlowDamage = nRollTotal;
            nRollTotal = oCrit.nBaseDamageDone;
          }

          oE.sDesc += ", TOTAL DAMAGE:"+(nRollTotal)+
              (nRollTotal>=nMaxDamageCanTake?" *DISABLED BODYPART*":
               ", max for location "+nMaxDamageCanTake)+
              (nOverFlowDamage>0?", overflow dam "+nOverFlowDamage+"":"");
          // set BS label
          oCrit.oAtk.lDamage.setText(""+(nRollTotal));
        }
      } else
      if (oE.sName.equalsIgnoreCase("Extra Damage")  &&
          (oCrit.oAtk != null)) {
        if (oE.sDesc.matches("^[+]\\d+")) {
          String sText = oE.sDesc.replaceAll("^[+]", "");
          TablePlayer oTarget = oCrit.oAtk.oCreature.getWhoImAttacking();
          int nMaxHealth = Integer.parseInt(oTarget.jHealthMax.getValue().toString());
          int nMaxDamageCanTake = (int)(nMaxHealth*oCrit.dMaxDamageAtLocation);
          int nDamBonus = Integer.parseInt(sText);
          int nRollTotal = nDamBonus;
          int nOverFlowDamage = 0;
          // if base damage is smaller than max damage can take for loc
          if (oCrit.nBaseDamageDone < nMaxDamageCanTake) {
            nRollTotal += oCrit.nBaseDamageDone;
            // total more than nMax now, remove overflow damage
            if (nRollTotal > nMaxDamageCanTake) {
              nOverFlowDamage = nRollTotal - nMaxDamageCanTake;
              nRollTotal = nMaxDamageCanTake;
            }
          } else {
            // damage already at max for location, we dont lose any of base
            // roll of damage
            nOverFlowDamage = nRollTotal;
            nRollTotal = oCrit.nBaseDamageDone;
          }

          oE.sDesc += ", TOTAL DAMAGE:"+(nRollTotal)+
              (nRollTotal>=nMaxDamageCanTake?" *DISABLED BODYPART*":
               ", max for location "+nMaxDamageCanTake)+
              (nOverFlowDamage>0?", overflow dam "+nOverFlowDamage+"":"");
          // set BS label
          oCrit.oAtk.lDamage.setText("" + (nRollTotal));
        }
      }
      // end extra damage and disabled body parts




      JLabel jEffd = new JLabel(oE.sDesc);
      jEff.setFont(fFont);
      jEffd.setFont(fpFont);
      if (!bFlip) {
        jEffPanel.setBackground(Color.YELLOW);
        jEff.setForeground(Color.black);
        jEffd.setForeground(Color.black);
      }
      else {
//        jEffPanel.setBackground(Color.gray);
        jEff.setForeground(Color.black);
        jEffd.setForeground(Color.black);
      }

      jEffPanel.add(jEff);
      jEffPanel.add(jEffd);
      jCritDisplayPanel.add(jEffPanel);

      // rotate colors
      bFlip = !bFlip;
    }

    // display perm effects of crits info
    if (oCrit.nSeverity >= 13 || bCritHealInfoRequired) {
      JPanel jEffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      JLabel jTopic = new JLabel("Note");
      jTopic.setFont(fFont);
      JLabel jInfo = new JLabel("If left to heal naturally 50% (25% if magically) of the reductions/penalties will remain permanently.");
      jInfo.setFont(fpFont);
      jTopic.setForeground(Color.black);
      jInfo.setForeground(Color.black);
      if (!bFlip) {
        jEffPanel.setBackground(Color.YELLOW);
      }
      else {
        // we leave it as is
      }
      jEffPanel.add(jTopic);
      jEffPanel.add(jInfo);
      jCritDisplayPanel.add(jEffPanel);
    }

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Critical Hit!");
    panel1.setLayout(borderLayout1);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(borderLayout2);
    jCritDisplayPanel.setBackground(Color.gray);
    jCloseButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCloseButton.setToolTipText("Close display panel.");
    jCloseButton.setText("Close");
    jCloseButton.addActionListener(new DialogFancyCrit_jCloseButton_actionAdapter(this));
    jButtonPanel.setBackground(Color.lightGray);
    jButtonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jCritDisplayPanel, BorderLayout.CENTER);
    jPanel1.add(jButtonPanel, BorderLayout.SOUTH);
    jButtonPanel.add(jCloseButton, null);
  }

  void jCloseButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }
}

class DialogFancyCrit_jCloseButton_actionAdapter implements java.awt.event.ActionListener {
  DialogFancyCrit adaptee;

  DialogFancyCrit_jCloseButton_actionAdapter(DialogFancyCrit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseButton_actionPerformed(e);
  }
}
