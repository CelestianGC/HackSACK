package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
//import com.borland.jbcl.layout.*;

/**
 *
 *
 * this is the APPLY honor award dialog
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogHonorAwards extends JDialog {
  HackSackFrame oParent = null;
  HonorAward oHonorAward = null;
  Honor oH = null;
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

  public DialogHonorAwards(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogHonorAwards(HackSackFrame oParent,HonorAward oHonorAward) {
    this(null, "", false);
    this.oParent = oParent;
    this.oHonorAward = oHonorAward;


    if (oHonorAward != null) {
      oH = oHonorAward.FindHonorAward(oParent.lHonorTypes,oHonorAward.nID);
      if (oHonorAward.nID == -10)
      {
        oH = new Honor();
        oH.sName = "Defeated worthy opponent.";
        oH.sDesc = "Defeated worthy opponent.";
        for (int j = 0; j < 3; j++) {
          oH.nLawful[j] = 1;
          oH.nChaotic[j] = 1;
          oH.nNeutral[j] = 1;
        }

      } else
      if (oHonorAward.nID == -20)
      {
        oH = new Honor();
        oH.sName = oHonorAward.sDetails;
        oH.sDesc = oHonorAward.sDetails;
        oH.jAwardSpecific.setValue(new Integer(oHonorAward.nSpecificAward));
      }

//      JPanel jHonorPanel = new JPanel(new GridLayout(0,1));
//      jHonorPanel.setBackground(Color.lightGray);
      Box jHonorPanel = Box.createVerticalBox();
      jHonorPanel.setBackground(Color.lightGray);

      // name of honor reward
      JPanel jTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jTextPanel.setBackground(Color.lightGray);
      JTextArea jText = new JTextArea(""+oH.sName);
      jText.setColumns(25);
      jText.setLineWrap(true);
      jText.setWrapStyleWord(true);
      jText.setEditable(false);
      jText.setBackground(Color.lightGray);
      jText.setFont(new Font("Dialog",Font.BOLD,14));
      jTextPanel.add(jText);
      jHonorPanel.add(jTextPanel);

      // desc of honor reward
      JPanel jDescTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jDescTextPanel.setBackground(Color.lightGray);
      JTextArea jDescText = new JTextArea(""+oH.sDesc);
      jDescText.setColumns(25);
      jDescText.setLineWrap(true);
      jDescText.setWrapStyleWord(true);
      jDescText.setEditable(false);
      jDescText.setBackground(Color.lightGray);
      jDescText.setFont(new Font("Dialog",Font.ITALIC,10));
      jDescTextPanel.add(jDescText);
      jHonorPanel.add(jDescTextPanel);


      // reward details/notes
      JPanel jDetailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jDetailsPanel.setBackground(Color.lightGray);
      JTextArea jDetails = new JTextArea("Details: "+oHonorAward.sDetails);
      jDetails.setColumns(25);
      jDetails.setLineWrap(true);
      jDetails.setWrapStyleWord(true);
      jDetails.setEditable(false);
      jDetails.setBackground(Color.lightGray);
      jDetails.setFont(new Font("Dialog",Font.PLAIN,12));
      jDetailsPanel.add(jDetails);
      jHonorPanel.add(jDetailsPanel);


      int nSpecific = Integer.parseInt(oH.jAwardSpecific.getValue().toString());
      if (nSpecific != 0)
      {
        // select player text...
        JPanel jSpecHonorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jSpecHonorPanel.setBackground(Color.lightGray);
        JLabel jSpecHonorText = new JLabel("Specific Award value");
        jSpecHonorPanel.add(jSpecHonorText);
        jSpecHonorPanel.add(oH.jAwardSpecific);
        if (oH.bPercentage && !oH.bPerLevel)
        {
          JLabel jPercentLabel = new JLabel("%");
          jPercentLabel.setFont(new Font("Dialog",Font.BOLD,12));
          jSpecHonorPanel.add(jPercentLabel);

          JLabel jPercentLabel2 = new JLabel("(not temporal!)");
          jPercentLabel2.setFont(new Font("Dialog",Font.ITALIC,9));
          jSpecHonorPanel.add(jPercentLabel2);
        }
        else
        if (!oH.bPercentage && oH.bPerLevel)
        {
          JLabel jPercentLabel = new JLabel("X level");
          jPercentLabel.setFont(new Font("Dialog",Font.BOLD,12));
          jSpecHonorPanel.add(jPercentLabel);

          JLabel jPercentLabel2 = new JLabel("(multiplier)");
          jPercentLabel2.setFont(new Font("Dialog",Font.ITALIC,9));
          jSpecHonorPanel.add(jPercentLabel2);
        }

        jHonorPanel.add(jSpecHonorPanel);

        jWorkPanel.add(jHonorPanel);
      }

      // select player text...
      JPanel jText2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jText2Panel.setBackground(Color.lightGray);
      JLabel jText2 = new JLabel("Select those whom should receive this award!");
      jText2Panel.add(jText2);
      jHonorPanel.add(jText2Panel);

      jWorkPanel.add(jHonorPanel);

//      JPanel jPlayerPanel = new JPanel(new GridLayout(0, 3));
//      jPlayerPanel.setBackground(Color.lightGray);

      JPanel jPlayerPanel = new JPanel(new GridLayout(0,2));
      jPlayerPanel.setPreferredSize(new Dimension(180,20));
      jPlayerPanel.setBackground(Color.lightGray);

      for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
        TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
        if (!oPlayer.jAbsent.isSelected() && !oPlayer.jHireling.isSelected()) {
          // individual stuff
          if ( (oPlayer.lPlayerID == oHonorAward.lIndividual) ||
              oHonorAward.nID == -10) // if group killed mob, just select all by default
            oPlayer.bSelected.setSelected(true);
          else
            oPlayer.bSelected.setSelected(false);
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
    titledBorder1 = new TitledBorder(border1,"Honor Awards");
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
    jApplyButton.addActionListener(new DialogHonorAwards_jApplyButton_actionAdapter(this));
    jDiscardButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jDiscardButton.setToolTipText("Do not award anyone EXP for this creature.");
    jDiscardButton.setText("Discard");
    jDiscardButton.addActionListener(new DialogHonorAwards_jDiscardButton_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(400, 250));
    getContentPane().add(panel1);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jWorkPanel, null);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jApplyButton, null);
    jPanel1.add(jDiscardButton, null);
  }

  void jDiscardButton_actionPerformed(ActionEvent e) {
    // flag honor as applied and close
    oHonorAward.jApplied.setSelected(true);
    oParent.gplGroupLog.groupLog("Discarded Honor award "+
                                 (oHonorAward.sDetails.equals("")?"":", DETAILS: "+
                                  oHonorAward.sDetails)+".\n" );
//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  void jApplyButton_actionPerformed(ActionEvent e) {
    for (int i=0;i<oParent.gplGroupLog.lPlayers.size();i++) {
      TablePlayer oPlayer = (TablePlayer)oParent.gplGroupLog.lPlayers.get(i);
      if (oPlayer.bSelected.isSelected()) {
        int nMyAward = 0;
        int nHonorAdjustment = 0;
        int nSpecific = Integer.parseInt(oH.jAwardSpecific.getValue().toString());
        if (nSpecific != 0)
        {
          if (oH.bPercentage) // percentage award, not temporal, REAL!
          {
            int nCurrent = Integer.parseInt(oPlayer.jHonor.getValue().toString());
            double dPercent = (nSpecific*0.01); // convert the percent to decimal
            double bAdj = (nCurrent*dPercent); // figure out the adjustment amount
            nCurrent = (int)(nCurrent+bAdj);
            nHonorAdjustment = (int)bAdj; // to show down in logs
            oPlayer.jHonor.setValue(new Integer(nCurrent));
          }
          else
          if (oH.bPerLevel) // perlevel multiplier to temporal
          {
            int nLevel = oPlayer.GetPlayerMaxLevel();
            nMyAward = (nLevel*nSpecific);
          } else
          {
            nMyAward = nSpecific;
          }
        }
        else
        switch (oPlayer.nAlignementIndex)
        {
          case 0: // LG
            nMyAward = oH.nLawful[oH.ALIGN_GOOD];
          break;
          case 1: // LN
            nMyAward = oH.nLawful[oH.ALIGN_NEUTRAL];
          break;
          case 2: //LE
            nMyAward = oH.nLawful[oH.ALIGN_EVIL];
          break;
          case 3: // NG
            nMyAward = oH.nNeutral[oH.ALIGN_GOOD];
          break;
          case 4: // NN
            nMyAward = oH.nNeutral[oH.ALIGN_NEUTRAL];
          break;
          case 5: // NE
            nMyAward = oH.nNeutral[oH.ALIGN_EVIL];
          break;
          case 6: // CG
            nMyAward = oH.nChaotic[oH.ALIGN_GOOD];
          break;
          case 7: // CN
            nMyAward = oH.nChaotic[oH.ALIGN_NEUTRAL];
          break;
          case 8: // CE
            nMyAward = oH.nChaotic[oH.ALIGN_EVIL];
          break;
        }

      oPlayer.nTemporalHonor += nMyAward;
        oHonorAward.jApplied.setSelected(true);
        oPlayer.playerLog("Awarded "+(oH.bPercentage?nHonorAdjustment:nMyAward)+" "+
                          (oH.bPercentage?"":"temporal")+" honor for "+oH.sName+
                          (oHonorAward.sDetails.equals("")?"":", DETAILS: "+oHonorAward.sDetails)+".\n" );
        oParent.gplGroupLog.groupLog("Awarded "+oPlayer.sCharacter+" "+
                                     (oH.bPercentage?nHonorAdjustment:nMyAward)+
                                     " "+(oH.bPercentage?"":"temporal")+" honor for "+oH.sName+
                                     (oHonorAward.sDetails.equals("")?"":", DETAILS: "+
                                      oHonorAward.sDetails)+".\n" );
      }
    }

//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }
}

class DialogHonorAwards_jDiscardButton_actionAdapter implements java.awt.event.ActionListener {
  DialogHonorAwards adaptee;

  DialogHonorAwards_jDiscardButton_actionAdapter(DialogHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDiscardButton_actionPerformed(e);
  }
}

class DialogHonorAwards_jApplyButton_actionAdapter implements java.awt.event.ActionListener {
  DialogHonorAwards adaptee;

  DialogHonorAwards_jApplyButton_actionAdapter(DialogHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jApplyButton_actionPerformed(e);
  }
}
