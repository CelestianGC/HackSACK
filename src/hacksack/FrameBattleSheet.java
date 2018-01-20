package hacksack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

//import com.borland.jbcl.layout.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class FrameBattleSheet
    extends JFrame {
  JPanel jBattleSheetPanel1 = new JPanel();
  HackSackFrame oParent = null;
  JScrollPane jBattleSheetScrollPane1 = new JScrollPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jBattleSheetPanel3 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JButton jButton1 = new JButton();
//  GridLayout gridLayout1 = new GridLayout(0, 1);
  JPanel jBattleSheetPanel2 = new JPanel(new VerticalFlowLayout());
//  Box jBattleSheetPanel2 = Box.createVerticalBox();

  JButton jBSSaveButton = new JButton();
  JButton jBSLoadButton = new JButton();
  JButton jBSClearButton = new JButton();
  JButton jRollInitButton = new JButton();
  JPanel jGroupDatePanel;
  JTabbedPane jBSTabbedPane1 = new JTabbedPane();
  Border border2;
  TitledBorder titledBorder2;
  JScrollPane jBattleSheetScrollPanel3 = new JScrollPane();
  JPanel jBSInfoPanel = new JPanel();
//  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  Border border3;
  TitledBorder titledBorder3;
  JButton jButtonAddArea = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jBSCreaturePanel = new JPanel(new VerticalFlowLayout());
  JPanel jBSCreatureListPanel = new JPanel();
  JScrollPane jBSCreatureListScrollPane = new JScrollPane();
  BorderLayout borderLayout3 = new BorderLayout();

  DefaultListModel mCreatureList = new DefaultListModel();
  JList jCreatureList = new JList(mCreatureList);
  Border border4;
  TitledBorder titledBorder4;
  JScrollPane jBSCreatureListScrollPane1 = new JScrollPane();
  JSplitPane jBSSplitPane = new JSplitPane();
  JSplitPane jBSCreature_PlayerSplitPane = new JSplitPane();
  JPanel jBSGroupPanel = new JPanel();
  Border border5;
  Border border6;
  TitledBorder titledBorder6;
  Border border7;
  Border border8;
  TitledBorder titledBorder8;
  Border border9;
  TitledBorder titledBorder9;
  JScrollPane jBSCreatureScrollPane = new JScrollPane();
  JScrollPane jGroupScrollPane = new JScrollPane();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jNextSegmentButton = new JButton();

  public FrameBattleSheet(HackSackFrame oParent, String sTitle) {
    try {
      jbInit();
      this.oParent = oParent;
      this.setTitle(sTitle);
      // setup calendar panel
      oParent.gplGroupLog.timeAddTimeOptionsBS(jGroupDatePanel);
      // done

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    jGroupDatePanel = new JPanel();
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    titledBorder1 = new TitledBorder(border1, "Battle Sheet");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    titledBorder2 = new TitledBorder(border2, "Party Sheet");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    titledBorder3 = new TitledBorder(border3, "Information");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder4 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"Creature List");
//    border5 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
//    border6 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
//    titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89)),"Current Creature");
//    border7 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(140, 5, 5),new Color(98, 4, 4),new Color(33, 1, 1),new Color(48, 2, 2));
    border9 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(4, 4, 4),new Color(3, 3, 3),Color.black,Color.black);
    titledBorder9 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.lightGray,Color.gray,Color.black,Color.black),"Current Creature");
    titledBorder9.setTitleColor(Color.lightGray);
    border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(82, 114, 140),new Color(58, 80, 98),new Color(19, 27, 33),new Color(28, 39, 48));
    titledBorder8 = new TitledBorder(border8,"Party Members");
    titledBorder8.setTitleColor(Color.lightGray);
    jBattleSheetPanel1.setLayout(borderLayout1);
    jBattleSheetPanel3.setBackground(Color.lightGray);
    jBattleSheetPanel3.setBorder(BorderFactory.createRaisedBevelBorder());
    jBattleSheetPanel3.setPreferredSize(new Dimension(20, 35));
    this.addWindowListener(new FrameBattleSheet_this_windowAdapter(this));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton1.setToolTipText("Close Battle Sheet and Clear Entries.");
    jButton1.setHorizontalAlignment(SwingConstants.CENTER);
    jButton1.setText("Close");
    jButton1.addActionListener(new FrameBattleSheet_jButton1_actionAdapter(this));
    jBattleSheetPanel2.setBackground(Color.GRAY);
    jBattleSheetPanel2.setBorder(titledBorder1);
    jBattleSheetPanel2.setLayout(borderLayout2);

    jBSSaveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jBSSaveButton.setToolTipText("Save This Battle Sheet");
    jBSSaveButton.setHorizontalAlignment(SwingConstants.CENTER);
    jBSSaveButton.setText("Save");
    jBSSaveButton.addActionListener(new
                                    FrameBattleSheet_jBSSaveButton_actionAdapter(this));
    jBSLoadButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jBSLoadButton.setToolTipText("Load Saved Battle Sheet.");
    jBSLoadButton.setHorizontalAlignment(SwingConstants.CENTER);
    jBSLoadButton.setText("Load");
    jBSLoadButton.addActionListener(new
                                    FrameBattleSheet_jBSLoadButton_actionAdapter(this));
    jBSClearButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jBSClearButton.setToolTipText("Clear Battle Sheet panel of all creatures.");
    jBSClearButton.setHorizontalAlignment(SwingConstants.CENTER);
    jBSClearButton.setText("Clear");
    jBSClearButton.addActionListener(new
                                     FrameBattleSheet_jBSClearButton_actionAdapter(this));
    jRollInitButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRollInitButton.setForeground(Color.blue);
    jRollInitButton.setPreferredSize(new Dimension(66, 22));
    jRollInitButton.setToolTipText("Roll initiative, reset fatigue cycle to 0.");
    jRollInitButton.setText("Roll Init");
    jRollInitButton.addActionListener(new
                                      FrameBattleSheet_jRollInitButton_actionAdapter(this));
    jGroupDatePanel.setBackground(Color.lightGray);
    jBattleSheetPanel1.setBackground(Color.lightGray);
    jBattleSheetScrollPane1.setBackground(Color.lightGray);
    jBSTabbedPane1.setBackground(Color.lightGray);
    jBSTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 11));
    jBSTabbedPane1.addChangeListener(new FrameBattleSheet_jBSTabbedPane1_changeAdapter(this));
    jBSInfoPanel.setLayout(new VerticalFlowLayout());
    jBSInfoPanel.setBackground(Color.darkGray);
    jBSInfoPanel.setBorder(titledBorder3);
    jButtonAddArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jButtonAddArea.setToolTipText(
        "Add an additional area to this battle sheet.");
    jButtonAddArea.setHorizontalAlignment(SwingConstants.CENTER);
    jButtonAddArea.setText("Add Area");
    jButtonAddArea.addActionListener(new
                                     FrameBattleSheet_jButtonAddArea_actionAdapter(this));
    jBSCreaturePanel.setBackground(Color.black);
    jBSCreaturePanel.setBorder(titledBorder9);
    jBSCreatureListPanel.setLayout(borderLayout3);
    jBSCreatureListPanel.setBackground(Color.lightGray);
    jBSCreatureListPanel.setBorder(titledBorder4);
    jCreatureList.setBackground(Color.lightGray);
    jCreatureList.setFont(new java.awt.Font("Dialog", 0, 9));
    jCreatureList.setToolTipText("RED names ready to attack, BLUE are flagged ignore, GRAY shaded at 0 or lower health.");
//    jCreatureList.setFixedCellWidth(100);
    jBSCreatureListScrollPane1.setPreferredSize(new Dimension(100, 130));
    jBSCreature_PlayerSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jBSGroupPanel.setBackground(new Color(41, 56, 69));
    jBSGroupPanel.setAlignmentX((float) 0.5);
    jBSGroupPanel.setBorder(titledBorder8);
    jBSGroupPanel.setDebugGraphicsOptions(0);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jBSGroupPanel.setLayout(flowLayout1);
    jNextSegmentButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jNextSegmentButton.setForeground(Color.blue);
    jNextSegmentButton.setPreferredSize(new Dimension(94, 22));
    jNextSegmentButton.setText("Next Segment");
    jNextSegmentButton.addActionListener(new FrameBattleSheet_jNextSegmentButton_actionAdapter(this));
    this.getContentPane().add(jBattleSheetPanel1, BorderLayout.CENTER);
    jBattleSheetPanel1.add(jGroupDatePanel, BorderLayout.SOUTH);
    jBattleSheetPanel1.add(jBSTabbedPane1, BorderLayout.CENTER);
    jBSTabbedPane1.add(jBattleSheetScrollPane1, "Battle Sheet");
    jBSTabbedPane1.add(jBattleSheetScrollPanel3, "Information");
    jBattleSheetScrollPane1.getViewport().add(jBattleSheetPanel2, null);
//    jBattleSheetScrollPane1.add(jBattleSheetPanel2, null);
    this.getContentPane().add(jBattleSheetPanel3, BorderLayout.SOUTH);
    jBattleSheetPanel3.add(jBSSaveButton, null);
    jBattleSheetPanel3.add(jButtonAddArea, null);
    jBattleSheetPanel3.add(jBSClearButton, null);
    jBattleSheetPanel3.add(jBSLoadButton, null);
    jBattleSheetPanel3.add(jButton1, null);
    jBattleSheetPanel3.add(jRollInitButton, null);
    jBattleSheetPanel3.add(jNextSegmentButton, null);
    jBSCreature_PlayerSplitPane.add(jGroupScrollPane, JSplitPane.BOTTOM);
    jBSCreature_PlayerSplitPane.add(jBSCreatureScrollPane, JSplitPane.TOP);
    jBSCreatureScrollPane.getViewport().add(jBSCreaturePanel, null);
    jGroupScrollPane.getViewport().add(jBSGroupPanel, null);
    jBattleSheetScrollPanel3.getViewport().add(jBSInfoPanel, null);
    jBattleSheetPanel2.add(jBSSplitPane, BorderLayout.CENTER);
    jBSSplitPane.add(jBSCreatureListPanel, JSplitPane.LEFT);
    jBSCreatureListPanel.add(jBSCreatureListScrollPane1,  BorderLayout.CENTER);
    jBSSplitPane.add(jBSCreature_PlayerSplitPane, JSplitPane.RIGHT);
    jBSCreatureListScrollPane1.getViewport().add(jCreatureList, null);
    jCreatureList.setCellRenderer(new CellRendererBSCreatureList());
    jCreatureList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jCreatureList.addListSelectionListener(new FrameBattleSheet_jCreatureList_listSelectionAdapter(this));
    jBSCreature_PlayerSplitPane.setDividerLocation(40);
  }

  void this_windowClosed(WindowEvent e) {
    CleanBattleSheet(oParent);
    this.setVisible(false);
//    this.hide();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    CleanBattleSheet(oParent);
    this.setVisible(false);
//    this.hide();
  }

  void jBSSaveButton_actionPerformed(ActionEvent e) {
//    SaveBattleSheet.AskWhereToSaveBattleSheet(this, oParent);
    CreatureCore.AskWhereToSaveBattleSheet(this,oParent);
  }

  /**
   *
   * @param oParent HackSackFrame
   */
  void LoadBattleSheetPane(HackSackFrame oParent) {
    if (!oParent.fBattleSheetFrame.isVisible()) {
      Dimension dlgSize = oParent.fBattleSheetFrame.getPreferredSize();
      Dimension frmSize = oParent.fBattleSheetFrame.getSize();
      Point loc = oParent.fBattleSheetFrame.getLocation();

      String sX = oParent.Prefs.getProperty("pref-bsX");
      String sY = oParent.Prefs.getProperty("pref-bsY");
      String sHx = oParent.Prefs.getProperty("pref-bsHx");
      String sHy = oParent.Prefs.getProperty("pref-bsHy");
      if (sX != null && sY != null & sHx != null && sHy != null) {
        int nX = Integer.parseInt(sX);
        int nY = Integer.parseInt(sY);
        int nHx = Integer.parseInt(sHx);
        int nHy = Integer.parseInt(sHy);
        oParent.fBattleSheetFrame.setLocation(nX, nY);
        oParent.fBattleSheetFrame.setSize(nHx, nHy);
      }
      else {
        oParent.fBattleSheetFrame.jBattleSheetPanel1.setPreferredSize(new
            Dimension(800, 600));
        oParent.fBattleSheetFrame.pack();
      }
      oParent.fBattleSheetFrame.setVisible(true);
//      oParent.fBattleSheetFrame.show();
    }

    // clean the sheet
//    CleanBattleSheet(oParent);

    // load creatures
    AppendToBattleSheet(oParent);
    //

    // load the player sheet section
    oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
    //
    oParent.fBattleSheetFrame.LoadInformationSheetTab(oParent);
  }

  void jBSLoadButton_actionPerformed(ActionEvent e) {
//    SaxParserForBS.AskWhatFileToLoad(oParent, this);
    CreatureCore.AskWhatFileToLoad(oParent,this);
//    oParent.LoadBSFromFile();
  }

  void jBSClearButton_actionPerformed(ActionEvent e) {
    CleanBattleSheet(oParent);
  }

  /**
   *
   * @param oParent HackSackFrame
   */
  static void LoadInformationSheetTab(HackSackFrame oParent) {
    oParent.fBattleSheetFrame.jBSInfoPanel.removeAll();
    if (oParent.lInformation.size() <= 0) {
      TableInformation oT = new TableInformation(oParent);
      oParent.lInformation.add(oT);
    }
    boolean bColor = false;
    for (int i = 0; i < oParent.lInformation.size(); i++) {
      TableInformation oT = (TableInformation) oParent.lInformation.get(i);
      if (i==0) {
        oParent.fBattleSheetFrame.setTitle("Battle Sheet, "+oT.jName.getText());
      }
      JPanel jInfoSheet = oT.getInformationPanel();
      jInfoSheet.setBackground( (bColor ? Color.gray : Color.lightGray));
      bColor = !bColor;
      oParent.fBattleSheetFrame.jBSInfoPanel.add(jInfoSheet);
    }
  }

  /*
     Load Party sheet Tab, this is no longer a tab, just a panel
   */
  /**
   *
   * @param oParent HackSackFrame
   */
  static void LoadPartyPanel(HackSackFrame oParent) {
    if (oParent.fBattleSheetFrame.isVisible()) {
      // incase we call this twice when player added
      oParent.fBattleSheetFrame.jBSGroupPanel.removeAll();
      oParent.fBattleSheetFrame.jBSGroupPanel.repaint();
      //
      if (oParent.gplGroupLog.lPlayers.size() > 0) {
        Font fSmallText = new Font("Dialog", Font.PLAIN, 9);
        Font fLargeText = new Font("Dialog", Font.BOLD, 10);
        Font fReallyLargeText = new Font("Dialog", Font.BOLD, 13);
        Color cLightColor = Color.lightGray;
        Color cDarkColor = Color.lightGray;

//    JPanel jBSMemberPanelSub = new JPanel(new FlowLayout(FlowLayout.LEFT));
//    oParent.fBattleSheetFrame.jBSGroupPanel.add(jBSMemberPanelSub);

        GridLayout gLayout = new GridLayout(0, 1);
        JPanel jNamePanel = new JPanel(gLayout);
        jNamePanel.setBackground(cLightColor);

        JPanel jHPAdjPanel = new JPanel(gLayout);
        jHPAdjPanel.setBackground(cDarkColor);

        JPanel jCurrentHPPanel = new JPanel(gLayout);
        jCurrentHPPanel.setBackground(cLightColor);

        JPanel jAttackingPanel = new JPanel(gLayout);
        jAttackingPanel.setBackground(cDarkColor);

        JPanel jFindAttackingPanel = new JPanel(gLayout);
        jFindAttackingPanel.setBackground(cDarkColor);

        JPanel jAttckRollPanel = new JPanel(gLayout);
        jAttckRollPanel.setBackground(cLightColor);

        JPanel jACHitPanel = new JPanel(gLayout);
        jACHitPanel.setBackground(cDarkColor);

//        BoxLayout2 bLayout = new BoxLayout2();
//      bLayout.setAxis(BoxLayout.X_AXIS);
        GridBagLayout bLayout = new GridBagLayout();

        
        JPanel jPlayerSheetGrid = new JPanel(bLayout);
        jPlayerSheetGrid.add(jNamePanel);
        jPlayerSheetGrid.add(jHPAdjPanel);
        jPlayerSheetGrid.add(jCurrentHPPanel);
        jPlayerSheetGrid.add(jAttackingPanel);
        jPlayerSheetGrid.add(jFindAttackingPanel);
        jPlayerSheetGrid.add(jAttckRollPanel);
        jPlayerSheetGrid.add(jACHitPanel);

        oParent.fBattleSheetFrame.jBSGroupPanel.add(jPlayerSheetGrid,
            BorderLayout.CENTER);
        jPlayerSheetGrid.setBackground(cLightColor);

        JLabel jSheetLabel1 = new JLabel("Character");
        jSheetLabel1.setFont(fLargeText);
        jNamePanel.add(jSheetLabel1);
        jSheetLabel1.setToolTipText("Character's Name.");

        JLabel jSheetLabel2 = new JLabel("HP Adjust.");
        jSheetLabel2.setToolTipText(
            "Adjustments to health,eg. -10 or +5 then press enter.");
        jSheetLabel2.setFont(fLargeText);
        jHPAdjPanel.add(jSheetLabel2);

        JLabel jSheetLabel3 = new JLabel("Current HP");
        jSheetLabel3.setFont(fLargeText);
        jSheetLabel3.setToolTipText("Current character's hitpoints.");
        jCurrentHPPanel.add(jSheetLabel3);

        JLabel jSheetLabel1a = new JLabel("Attacking");
        jSheetLabel1a.setFont(fLargeText);
        jAttackingPanel.add(jSheetLabel1a);
        jSheetLabel1a.setToolTipText("Creature this player is attacking.");

        JLabel jSheetLabel1b = new JLabel("Target");
        jSheetLabel1b.setFont(fLargeText);
        jFindAttackingPanel.add(jSheetLabel1b);
        jSheetLabel1b.setToolTipText("Load creature up player is attacking.");

        JLabel jSheetLabel4 = new JLabel("Attack Roll");
        jSheetLabel4.setFont(fLargeText);
        jSheetLabel4.setToolTipText(
            "Character's attack roll, eg, 23 or 16 then press enter.");
        jAttckRollPanel.add(jSheetLabel4);

        JLabel jSheetLabel5 = new JLabel("AC Hit");
        jSheetLabel5.setFont(fLargeText);
        jSheetLabel5.setToolTipText(
            "Armor Class the character hit with their last attack.");
        jACHitPanel.add(jSheetLabel5);
        // add grid
//    jBSMemberPanelSub.add(jPlayerSheetGrid);

        for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
          TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);

          // if player isn't absent then...
          if (!oPlayer.jAbsent.isSelected()) {

            // set the health lable to same as group panel shows
            oPlayer.jBSHealthLabel.setFont(fReallyLargeText);
            oPlayer.jBSHealthLabel.setText("  " + oPlayer.jHealthLabel.getText());
            oPlayer.jBSHealthLabel.setToolTipText(
                "Current character's hitpoints.");
            oPlayer.jBSHealthLabel.setForeground(Color.RED);
            oPlayer.jBSAttackLabel.setToolTipText(
                "If has target selected HIT(ACHIT) or MISS(AC), otherwise ACHIT displayed.");
            oPlayer.jBSAttackLabel.setForeground(Color.BLUE);
            oPlayer.jBSAttackLabel.setFont(fReallyLargeText);

            JLabel jPlayerName = new JLabel(oPlayer.sCharacter);
            jPlayerName.setFont(fLargeText);
            if (oPlayer.jHireling.isSelected()) {
              jPlayerName.setForeground(Color.BLUE);
            }
            jNamePanel.add(jPlayerName);

            oPlayer.jBSAdjHealth.setFont(fSmallText);
            jHPAdjPanel.add(oPlayer.jBSAdjHealth);
            jCurrentHPPanel.add(oPlayer.jBSHealthLabel);

            // reset/build the combo box to select creatures with
            oPlayer.buildAttackingThisCreatureList(oParent.lCreatures);
            jAttackingPanel.add(oPlayer.jAttackingThisCreature);
            oPlayer.bFindMyTarget.setFont(fSmallText);
            jFindAttackingPanel.add(oPlayer.bFindMyTarget);

            jAttckRollPanel.add(oPlayer.jBSAttackRoll);
            jACHitPanel.add(oPlayer.jBSAttackLabel);

          }
        } // end for player list loop
      }

      // end load party function
    }
  }

  /*
   Append/add creature to current battle sheet panel

   */
  void AppendToBattleSheet(HackSackFrame oParent) {
    JPanel jThisBattleSheet = jBSCreaturePanel;

    int nCurrentlySelectedIndex = jCreatureList.getSelectedIndex();
    int nDividerLocation = jBSCreature_PlayerSplitPane.getLastDividerLocation();
    mCreatureList.removeAllElements();
    jThisBattleSheet.removeAll();
    jThisBattleSheet.repaint();
    for (int i = 0; i < oParent.lCreatures.size(); i++) {
      CreatureCore thisCreature = (CreatureCore) oParent.lCreatures.get(i);
      mCreatureList.addElement(thisCreature);
    }
    if (oParent.lCreatures.size() > 0) {
      if (nCurrentlySelectedIndex >= mCreatureList.size() ||
          nCurrentlySelectedIndex < 0) { // show first creature loaded
          jCreatureList.setSelectedIndex(0);
        }
        else if (nCurrentlySelectedIndex < mCreatureList.size()) { // set to previous index
          jCreatureList.setSelectedIndex(nCurrentlySelectedIndex);
        }
    }
    if (nDividerLocation != 40) // 40 is what it starts with
    jBSCreature_PlayerSplitPane.setDividerLocation(nDividerLocation);

    // update nLastCreature
    oParent.nLastCreature = oParent.lCreatures.size();
    oParent.jSheetTotalLabel.setText("Total Creatures:" + oParent.nLastCreature);

  }

  void jRollInitButton_actionPerformed(ActionEvent e) {
    for (int i = 0; i < oParent.lCreatures.size(); i++) {
      CreatureCore oCreature = (CreatureCore) oParent.lCreatures.get(i);
      // reset fatigue counter with this new init cycle.
      oCreature.nCombatRounds = 0;
      oCreature.bFatigued = false;

      oCreature.initRollCreatureInitiative();
    }

    jCreatureList.repaint();
  }

  // I had a refresh button but just moved it to the
  // player panel loading section since thats why you'd
  // want to reload
  void jRefreshButton_actionPerformed(ActionEvent e) {
    JPanel jThisBattleSheet = oParent.fBattleSheetFrame.jBSCreaturePanel;

    oParent.nLastCreature = 0;
//    oParent.fBattleSheetFrame.jBattleSheetPanel2.removeAll();
    jThisBattleSheet.removeAll();
    oParent.fBattleSheetFrame.mCreatureList.removeAllElements();

    oParent.jSheetTotalLabel.setText("");
//    oParent.fBattleSheetFrame.jBattleSheetTotalLabel.setText(" ");
    LoadBattleSheetPane(oParent);

  }

  // load a creature up into array
  /**
   * Loads a creature into the battlesheet array
   *
   * @param oParent HackSackFrame
   * @param nNumberOfCreatures int
   */
  static void LoadCreature(HackSackFrame oParent, int nNumberOfCreatures) {
    String sCreatureName = oParent.jGMNameTextField.getText();

    oParent.gmLog(true, true, "Creature Adding:" + nNumberOfCreatures + "\n");

    for (int j = 0; j < nNumberOfCreatures; j++) {
      CreatureCore oNewCreature = new CreatureCore(oParent, sCreatureName);
      int nHD = oParent.mGMHDice.getNumber().intValue();
      int nHDMod = oParent.mGMHDMod.getNumber().intValue();

      Dicer dDicer = new Dicer();
      int nHitPoints = dDicer.getHitPoints(oParent.jGMSpecHP.getText());

      if (nNumberOfCreatures > 1) {
        oNewCreature.sCreatureName = (sCreatureName + "#" +
                                      Integer.toString(j + 1));
      }
      else {
        oNewCreature.sCreatureName = sCreatureName;

      }
      oNewCreature.nAC = oParent.mGMAC.getNumber().intValue();
      oNewCreature.nACBase = oNewCreature.nAC;
      // set hp adj box to 0 for first
      oNewCreature.tHPAdj.setText("");
      // nLevel - Hit Die unless hit dice mod >= 3
      int nLevel = nHD;
      if (nHDMod >= 3) { // more than +2 hd, they get boost
        nLevel = (nHD + 1);
      }
      else if (nHDMod <= -3) { // more than -2 hd they get reduction
        nLevel = (nHD - 1);

      }
      if (nLevel < 0) {
        nLevel = 0; // for sanity
      }
      oNewCreature.nLevel = nLevel;

      // set pull down box to same class
      int nClassIndex = oParent.jGMClassComboBox.getSelectedIndex();
      oNewCreature.nToHitRank = AttackRank.GetToHitRating(nClassIndex, nHD,
          nHDMod);
      oNewCreature.jClassSelect.setSelectedIndex(nClassIndex);

      if (nHitPoints == 0) { // if they didnt specify the hit points...
        // add a D8 for each HD they have
        for (int i = 0; i < nHD; i++) {
          nHitPoints += (Math.abs(oParent.rand.nextInt() % (8)) + 1);
        }
        // adjust hitpoints by HDMod
        nHitPoints += nHDMod;
        // add 20 hit point kicker if we dont have the disable box checked
        if (!oParent.jADDKickerCheckBox.isSelected())
          nHitPoints += 20;
      }
      oNewCreature.nHitPoints = nHitPoints;

      for (int i = 0; i < oParent.lAttacks.size(); i++) {
        CreatureCombat oAtk = (CreatureCombat) oParent.lAttacks.get(i);
        CreatureCombat oNew = new CreatureCombat(oParent, oNewCreature);

        oNew.jWeaponType.setSelectedIndex(oAtk.jWeaponType.getSelectedIndex());
       oNew.jDamageDice.setText(oAtk.jDamageDice.getText());

        oNew.jToHitMod.getModel().setValue(new Integer(Integer.parseInt(oAtk.
            jToHitMod.getModel().getValue().toString())));
        oNew.jTotalMod.getModel().setValue(new Integer(Integer.parseInt(oAtk.
            jTotalMod.getModel().getValue().toString())));
        oNew.jModCrit.getModel().setValue(new Integer(Integer.parseInt(oAtk.
            jModCrit.getModel().getValue().toString())));
        oNew.jModFumble.getModel().setValue(new Integer(Integer.parseInt(oAtk.
            jModFumble.getModel().getValue().toString())));
        oNew.jModPenetration.getModel().setValue(new Integer(Integer.parseInt(
            oAtk.jModPenetration.getModel().getValue().toString())));

        oNewCreature.lAttacks.add(oNew);
      }

      oNewCreature.sDescription = oParent.jGMDescTextArea.getText();
      oNewCreature.sSpecialAttack = oParent.jGMSpecialAttack.getText();
      oNewCreature.sSpecialDefense = oParent.JGMSpecialDefense.getText();
      // unused...
      oNewCreature.sMagicDefense = "Normal";
      oNewCreature.sPSIAttack = "N";
      oNewCreature.sPSIDefense = "N";
      //
      oNewCreature.nBASENumAtks = oParent.mGMNumAtks.getNumber().intValue();
      oNewCreature.nBASEMorale = oParent.mGMMoraleSpinner.getNumber().intValue();
      oNewCreature.nBASEMove = oParent.mGMMove.getNumber().intValue();
      oNewCreature.nBASESizeIndex = oParent.jGMSizeComboBox.getSelectedIndex();
      oNewCreature.nBASEAlignmentIndex = oParent.jGMAlignmentComboBox.
          getSelectedIndex();
      oNewCreature.nBASEHackFactor = oParent.mGMHackFactor.getNumber().intValue();
      oNewCreature.nEXP = oParent.mGMEXP.getNumber().intValue();

      oNewCreature.nCreatureID = oParent.nCurrentCreatureID;

      oNewCreature.nHonorIndex = oParent.jGMHonorComboBox.getSelectedIndex();
      oNewCreature.nFatigueFactor = Integer.parseInt(oParent.jGMFatigueFactorSpinner.getValue().toString());

      oNewCreature.sHOBLocation =
          oParent.sAppearingIn[oParent.jAppearingInComboBox.getSelectedIndex()];

      // Finally, add it to the list
      oParent.lCreatures.add(oNewCreature);
    }

  }
  /**
   *
   * @param oParent HackSackFrame
   */
  void CleanBattleSheet(HackSackFrame oParent) { // remove all entries from the battlesheet
    int nCreaturesMax = oParent.lCreatures.size();
    JPanel jThisBattleSheet = oParent.fBattleSheetFrame.jBSCreaturePanel;
    oParent.gmLog(true, true,
                  "Removing Creatures:" + oParent.lCreatures.size() + "\n");

    for (int i = 0; i < nCreaturesMax; i++) {
      oParent.lCreatures.remove(0);
    }

    // clear out the attacking combobox
    TablePlayer.clearAttackingThisCreatureList(oParent);

    while (oParent.lInformation.size() > 0) { // remove all
      oParent.lInformation.remove(0);

//    oParent.fBattleSheetFrame.gridLayout1.setRows(oParent.nMaxSheetRows);
    }
    if (oParent.fBattleSheetFrame.isVisible()) {
      jThisBattleSheet.removeAll();
      jThisBattleSheet.repaint();
      oParent.fBattleSheetFrame.mCreatureList.removeAllElements();

      oParent.fBattleSheetFrame.jBSInfoPanel.removeAll();
      oParent.fBattleSheetFrame.jBSInfoPanel.repaint();
    }
    oParent.nLastCreature = 0;
    oParent.jSheetTotalLabel.setText("");

    // force a free memory to run
    System.gc();
  }

  void jButtonAddArea_actionPerformed(ActionEvent e) {
    TableInformation oT = new TableInformation(oParent);
    oParent.lInformation.add(oT);
    LoadInformationSheetTab(oParent);
    oParent.fBattleSheetFrame.jBSTabbedPane1.repaint();
  }

  void jCreatureList_valueChanged(ListSelectionEvent e) {
    if (mCreatureList.getSize() > 0) {
      JPanel jThisBattleSheet = jBSCreaturePanel;

      CreatureCore oCreature = (CreatureCore) jCreatureList.getSelectedValue();
      if (oCreature!=null) {
        jThisBattleSheet.removeAll();
        jBSTabbedPane1.repaint();
        jThisBattleSheet.add(oCreature.getCreatureBattleInfo(Color.BLACK));
      }
      // this should flush out any crap in memory we dont need
//      System.gc();
      //
    }
  }

  void jBSTabbedPane1_stateChanged(ChangeEvent e) {
  // this sets the title to whatever the first area name is when
  // tab is changed back to the main battle sheet.
    if (oParent != null) {
      if (oParent.lInformation.size() > 0) {
        TableInformation oT = (TableInformation) oParent.lInformation.get(0);
        oParent.fBattleSheetFrame.setTitle("Battle Sheet, " + oT.jName.getText());
      }
    }


  }

  void jNextSegmentButton_actionPerformed(ActionEvent e) {
    int nNextSegment = Integer.parseInt(oParent.gplGroupLog.jSegmentBS.getValue().toString());
    nNextSegment++;
    oParent.gplGroupLog.jSegmentBS.setValue(new Integer(nNextSegment));
  }

  void jButton2_actionPerformed(ActionEvent e) {
    jCreatureList.repaint();
  }

} // end class

class FrameBattleSheet_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  FrameBattleSheet adaptee;

  FrameBattleSheet_this_windowAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}

class FrameBattleSheet_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jButton1_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class FrameBattleSheet_jBSSaveButton_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jBSSaveButton_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jBSSaveButton_actionPerformed(e);
  }
}

class FrameBattleSheet_jBSLoadButton_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jBSLoadButton_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jBSLoadButton_actionPerformed(e);
  }
}

class FrameBattleSheet_jBSClearButton_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jBSClearButton_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jBSClearButton_actionPerformed(e);
  }
}

class FrameBattleSheet_jRollInitButton_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jRollInitButton_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRollInitButton_actionPerformed(e);
  }
}

class FrameBattleSheet_jButtonAddArea_actionAdapter
    implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jButtonAddArea_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonAddArea_actionPerformed(e);
  }
}

class FrameBattleSheet_jCreatureList_listSelectionAdapter
    implements javax.swing.event.ListSelectionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jCreatureList_listSelectionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(ListSelectionEvent e) {
    adaptee.jCreatureList_valueChanged(e);
  }
}

class FrameBattleSheet_jBSTabbedPane1_changeAdapter implements javax.swing.event.ChangeListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jBSTabbedPane1_changeAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.jBSTabbedPane1_stateChanged(e);
  }
}

class FrameBattleSheet_jNextSegmentButton_actionAdapter implements java.awt.event.ActionListener {
  FrameBattleSheet adaptee;

  FrameBattleSheet_jNextSegmentButton_actionAdapter(FrameBattleSheet adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jNextSegmentButton_actionPerformed(e);
  }
}

