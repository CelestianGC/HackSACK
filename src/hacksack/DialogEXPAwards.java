package hacksack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 *
 * this is the APPLY EXP award dialog
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogEXPAwards extends JDialog {
  HackSackFrame oParent = null;
  EXPAwards oEXPAward = null;
  EXPBonus oEB = null;
  JPanel panel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jWorkPanel = new JPanel(new GridLayout(0,1));
  JPanel jPanel1 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jApplyButton = new JButton();
  JButton jDiscardButton = new JButton();
  JSpinner jRewardSpinner;
  JSpinner jMultiplierSpinner;

  BorderLayout borderLayout1 = new BorderLayout();

  public DialogEXPAwards(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogEXPAwards(HackSackFrame oParent,EXPAwards oEA) {
    this(null, "", false);
    this.oParent = oParent;
    this.oEXPAward = oEA;

    oEB = oEA.FindEXPBonus(oParent.lEXPBonusType,oEA.nID);

    Box jEXPPanel = Box.createVerticalBox();
    jEXPPanel.setBackground(Color.lightGray);

      // name of reward
      JTextArea jText = new JTextArea(""+oEB.sName);
      jText.setLineWrap(true);
      jText.setWrapStyleWord(true);
      jText.setEditable(false);
      jText.setBackground(Color.lightGray);
      jText.setFont(new Font("Dialog",Font.PLAIN,12));
      jEXPPanel.add(jText);

      // description of reward
      JTextArea jDescText = new JTextArea(oEB.sDesc+"\n");
      jDescText.setLineWrap(true);
      jDescText.setWrapStyleWord(true);
      jDescText.setEditable(false);
      jDescText.setBackground(Color.lightGray);
      jDescText.setFont(new Font("Dialog",Font.ITALIC,12));
      jEXPPanel.add(jDescText);

      // notes/details on reward
      JTextArea jDetails = new JTextArea("Details: "+oEXPAward.sDetails+"\n");
      jDetails.setLineWrap(true);
      jDetails.setWrapStyleWord(true);
      jDetails.setEditable(false);
      jDetails.setBackground(Color.lightGray);
      jDetails.setFont(new Font("Dialog",Font.BOLD,12));
      jEXPPanel.add(jDetails);

      // display reward value
      JPanel jRewardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jRewardPanel.setBackground(Color.lightGray);
      JLabel jRewardLabel = new JLabel("Reward");
      jRewardLabel.setToolTipText("EXP reward value before multiplier if any.");
      jRewardSpinner = new JSpinner(new SpinnerNumberModel(oEB.nReward,1,100000000,1));
      jRewardPanel.add(jRewardLabel);
      jRewardPanel.add(jRewardSpinner);

      jEXPPanel.add(jRewardPanel);

      // if we use a manual set multiplier then display it
      if (oEB.nMultiplier > 0)
      {
        JLabel jMultiplierLabel = new JLabel("Multiplier");
        jMultiplierLabel.setToolTipText("Multiplier to apply to reward.");
        jMultiplierSpinner = new JSpinner(new SpinnerNumberModel(oEB.nMultiplier,
            1, 100000000, 1));
        jRewardPanel.add(jMultiplierLabel);
        jRewardPanel.add(jMultiplierSpinner);
      }

      // display reward flags
      JPanel jRewardFlagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jRewardFlagsPanel.setBackground(Color.lightGray);
      JLabel jRewardFlagsLabel = new JLabel("");
      String sRewardFlagsText =
          (oEB.bLevelMultiplier?"Reward multiplied by player level.\n":"")+
          (oEB.bPartyLevelMultiplier?"Reward multiplied by party average level.\n":"")+
          (oEB.bSplitReward?"Reward is split among all those that are selected.":"");
      jRewardFlagsLabel.setText(sRewardFlagsText);
      jRewardFlagsLabel.setFont(new Font("Dialog",Font.PLAIN,12));
      jRewardFlagsLabel.setToolTipText(sRewardFlagsText);
      jRewardFlagsPanel.add(jRewardFlagsLabel);
      jEXPPanel.add(jRewardFlagsPanel);

      // display players to select
      JPanel jText2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jText2Panel.setBackground(Color.lightGray);
      JLabel jText2 = new JLabel("Select those whom should receive this award!");
      jText2Panel.add(jText2);
      jEXPPanel.add(jText2Panel);

      // add all this to the big panel
      jWorkPanel.add(jEXPPanel);

      JPanel jPlayerPanel = new JPanel(new GridLayout(0,2));
      jPlayerPanel.setPreferredSize(new Dimension(180,20));
      jPlayerPanel.setBackground(Color.lightGray);

      for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
        TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
        if (!oPlayer.jAbsent.isSelected() && !oPlayer.jHireling.isSelected()) {
          // individual stuff
          if (oPlayer.lPlayerID == oEXPAward.lIndividual)
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

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"EXP Awards");
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
    jApplyButton.addActionListener(new DialogEXPAwards_jApplyButton_actionAdapter(this));
    jDiscardButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jDiscardButton.setToolTipText("Do not award anyone EXP for this creature.");
    jDiscardButton.setText("Discard");
    jDiscardButton.addActionListener(new DialogEXPAwards_jDiscardButton_actionAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(400, 250));
    getContentPane().add(panel1);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jWorkPanel, null);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jApplyButton, null);
    jPanel1.add(jDiscardButton, null);
  }

  void jDiscardButton_actionPerformed(ActionEvent e) {
    // flag EXP as applied and close
    oEXPAward.jApplied.setSelected(true);
    oParent.gplGroupLog.groupLog("Discarded EXP award "+
                                 (oEXPAward.sDetails.equals("")?"":", DETAILS: "+
                                  oEXPAward.sDetails)+".\n" );
//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  void jApplyButton_actionPerformed(ActionEvent e) {
    int nSelectedCount=0;

    // we do this to get all the selected people counter
    // used for reward split
    for (int i=0;i<oParent.gplGroupLog.lPlayers.size();i++) {
      TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
      if (oPlayer.bSelected.isSelected()) {
        nSelectedCount++;
      }
    }

    for (int i=0;i<oParent.gplGroupLog.lPlayers.size();i++) {
      TablePlayer oPlayer = (TablePlayer)oParent.gplGroupLog.lPlayers.get(i);
      if (oPlayer.bSelected.isSelected()) {
        int nMyAward = 0;
        int nMultiplier = 0;
        if (oEB.bLevelMultiplier)
        {
          nMultiplier = oPlayer.GetPlayerMaxLevel();
        }
        else if (oEB.bPartyLevelMultiplier)
        {
          nMultiplier = oParent.gplGroupLog.GetGroupAverageLevel(oParent.gplGroupLog.lPlayers);
        }
        else // must be no or manual multiplier
        {
//         nMultiplier = oEB.nMultiplier;
         nMultiplier = Integer.parseInt(jMultiplierSpinner.getModel().getValue().toString());
        }

        int nReward =Integer.parseInt(jRewardSpinner.getModel().getValue().toString());
        if (oEB.bSplitReward) {
          nReward = nReward/nSelectedCount;
        }
        nMyAward = nReward*nMultiplier;

        oPlayer.GiveEXPToPlayer(oPlayer,nMyAward);

        oEXPAward.jApplied.setSelected(true);
        oPlayer.playerLog("Awarded "+nMyAward+" EXP for "+oEB.sName+
                          (oEXPAward.sDetails.equals("")?"":", DETAILS: "+oEXPAward.sDetails)+".\n" );
        oParent.gplGroupLog.groupLog("Awarded "+oPlayer.sCharacter+" "+nMyAward+
                                     " EXP for "+oEB.sName+
                                     " Multiplier :x"+nMultiplier+" "+
                                     (oEXPAward.sDetails.equals("")?"":", DETAILS: "+
                                      oEXPAward.sDetails)+".\n" );
      }
    }

//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  // return a panel that has word wrapped the passed text in it.
  JPanel WordWrapTextPanel(String sText)
  {
    JPanel jReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // split up the text by spaces
    String[] sParts = sText.split(" ");
    for (int i=0;i<sParts.length;i++)
    {
      JLabel jLabel = new JLabel(sParts[i]);
      jReturn.add(jLabel);
    }

    return jReturn;
  }
}

class DialogEXPAwards_jDiscardButton_actionAdapter implements java.awt.event.ActionListener {
  DialogEXPAwards adaptee;

  DialogEXPAwards_jDiscardButton_actionAdapter(DialogEXPAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDiscardButton_actionPerformed(e);
  }
}

class DialogEXPAwards_jApplyButton_actionAdapter implements java.awt.event.ActionListener {
  DialogEXPAwards adaptee;

  DialogEXPAwards_jApplyButton_actionAdapter(DialogEXPAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jApplyButton_actionPerformed(e);
  }
}
