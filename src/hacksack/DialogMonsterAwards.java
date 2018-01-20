package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
//import com.borland.jbcl.layout.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogMonsterAwards extends JDialog {
  HackSackFrame oParent = null;
  Monster oMonster = null;
  JPanel panel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jWorkPanel = new JPanel(new GridLayout(0,1));
  JPanel jPanel1 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jApplyButton = new JButton();
  JButton jDiscardButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();

  public DialogMonsterAwards(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogMonsterAwards(HackSackFrame oParent,Monster oMonster) {
    this(null, "", false);
    this.oParent = oParent;
    this.oMonster = oMonster;


    if (oMonster != null) {
      JPanel jMonsterPanel = new JPanel(new GridLayout(0,1));
      jMonsterPanel.setBackground(Color.lightGray);
      JLabel jText = new JLabel("Award for defeating "+oMonster.sName+" is "+
                                oMonster.nEXPValue+" experince.");
      jText.setFont(new Font("Dialog",Font.BOLD,14));
      JLabel jText2 = new JLabel("Select those whom should receive a share!");
      jMonsterPanel.add(jText);
      jMonsterPanel.add(jText2);
      jWorkPanel.add(jMonsterPanel);

      JPanel jPlayerPanel = new JPanel(new GridLayout(0, 3));
      jPlayerPanel.setBackground(Color.lightGray);
      for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
        TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
        if (!oPlayer.jAbsent.isSelected() && !oPlayer.jHireling.isSelected()) {
          oPlayer.bSelected.setSelected(true);
          oPlayer.bSelected.setText(oPlayer.sCharacter);
          oPlayer.bSelected.setBackground(Color.lightGray);
          jPlayerPanel.add(oPlayer.bSelected);
        }
      }
      jWorkPanel.add(jPlayerPanel);
    }

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"Monster EXP Awards");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(titledBorder1);
    jWorkPanel.setBackground(Color.lightGray);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.CENTER);
    jApplyButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jApplyButton.setToolTipText("Apply awards to all those currently selected.");
    jApplyButton.setText("Apply");
    jApplyButton.addActionListener(new DialogMonsterAwards_jApplyButton_actionAdapter(this));
    jDiscardButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jDiscardButton.setToolTipText("Do not award anyone EXP for this creature.");
    jDiscardButton.setText("Discard");
    jDiscardButton.addActionListener(new DialogMonsterAwards_jDiscardButton_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(400, 250));
    getContentPane().add(panel1);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jWorkPanel, null);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jApplyButton, null);
    jPanel1.add(jDiscardButton, null);
  }

  void jDiscardButton_actionPerformed(ActionEvent e) {
    // flag monster as applied and close
    oMonster.jApplied.setSelected(true);
//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  void jApplyButton_actionPerformed(ActionEvent e) {
    int nCount = 0;
    for (int i=0;i<oParent.gplGroupLog.lPlayers.size();i++) {
      TablePlayer oPlayer = (TablePlayer)oParent.gplGroupLog.lPlayers.get(i);
      if (oPlayer.bSelected.isSelected()) {
        nCount++;
      }
    }
    int nAwardEach = oMonster.nEXPValue/nCount;

    for (int i=0;i<oParent.gplGroupLog.lPlayers.size();i++) {
      TablePlayer oPlayer = (TablePlayer)oParent.gplGroupLog.lPlayers.get(i);
      if (oPlayer.bSelected.isSelected()) {
        oParent.gplGroupLog.groupLog("Awarded "+nAwardEach+" experience to "+
                                        oPlayer.sCharacter+" for defeating "+
                                        oMonster.sName+"\n");

        int nNumberOfClasses = oPlayer.aClass.size();
        for (int j=0;j<oPlayer.aClass.size();j++) {
          Class oClass = (Class)oPlayer.aClass.get(j);
          int nCurrentEXP =Integer.parseInt(oClass.jEXP.getModel().getValue().toString());
          int nReward = (nAwardEach/nNumberOfClasses);
          int nEXPBonus = Integer.parseInt(oClass.jEXPBonus.getModel().getValue().toString());
          double bBonus = nReward*(nEXPBonus*0.01);
          int nNewEXP = (nCurrentEXP + nReward +(int)bBonus);
          oClass.jEXP.getModel().setValue(new Integer(nNewEXP));
          oPlayer.playerLog("Received EXP award of "+nReward+
                            (bBonus > 0?" (bonus:"+(int)bBonus+")":"")+
                                    " points to the "+oClass.sName+
                                    " class for a total of "+nNewEXP+
                                    " experience.\n");
        }

      }
    }
    oMonster.jApplied.setSelected(true);
    // refresh log
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }
}

class DialogMonsterAwards_jDiscardButton_actionAdapter implements java.awt.event.ActionListener {
  DialogMonsterAwards adaptee;

  DialogMonsterAwards_jDiscardButton_actionAdapter(DialogMonsterAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDiscardButton_actionPerformed(e);
  }
}

class DialogMonsterAwards_jApplyButton_actionAdapter implements java.awt.event.ActionListener {
  DialogMonsterAwards adaptee;

  DialogMonsterAwards_jApplyButton_actionAdapter(DialogMonsterAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jApplyButton_actionPerformed(e);
  }
}
