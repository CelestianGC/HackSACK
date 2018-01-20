package hacksack;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
//import com.borland.jbcl.layout.*;

public class FramePlayerGroup
    extends JFrame {
  JPanel jPLGrouptPanel1 = new JPanel();
  HackSackFrame oParent = null;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPLGrouptPanel3 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JButton jCloseButton1 = new JButton();
  GridLayout gridLayout1 = new GridLayout(0, 1);
  JButton jPLSaveButton = new JButton();
  JButton jPLLoadButton = new JButton();
  JButton jPLClearButton = new JButton();
  JTabbedPane jPLGroupTabbedPane = new JTabbedPane();
  Border border2;
  TitledBorder titledBorder2;
  JPanel jGroupDatePanel = new JPanel();
  JButton jButtonImportXML = new JButton();
  JButton jLoadPlayerButton = new JButton();
  JPanel jPlayerListPanel = new JPanel();
  JPanel jDetailedPlayerTab = new JPanel();
  JPanel jPartyLogTab = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPlayerDetailsPanel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  DefaultListModel mPartyPlayerList = new DefaultListModel();
  JList jPartyPlayerList = new JList(mPartyPlayerList);
  JSplitPane jSplitPane1 = new JSplitPane();
  Border border3;
  TitledBorder titledBorder3;
  Border border4;
  TitledBorder titledBorder4;
  BorderLayout borderLayout4 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPartyLogDetailsPanel = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
  JButton jNewPlayer = new JButton();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

  public FramePlayerGroup(HackSackFrame oParent, String sTitle) {
    try {
      jbInit();
      this.oParent = oParent;
      this.setTitle(sTitle);

      // setup calendar panel
      oParent.gplGroupLog.timeAddTimeOptions(jGroupDatePanel);
      // done

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, new Color(182, 182, 182),
        new Color(62, 62, 62), new Color(89, 89, 89)), "Player Group");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2, "Player Group");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder3 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134)),"Members");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder4 = new TitledBorder(border4,"Selected Player");
    titledBorder4.setTitleColor(Color.RED);
    titledBorder4.setTitleFont(new Font("Dialog",Font.BOLD,12));
    jPLGrouptPanel1.setLayout(borderLayout1);
    jPLGrouptPanel3.setBackground(Color.lightGray);
    jPLGrouptPanel3.setBorder(BorderFactory.createRaisedBevelBorder());
    jPLGrouptPanel3.setPreferredSize(new Dimension(20, 35));
    this.addWindowListener(new FramePlayerGroup_this_windowAdapter(this));
    jCloseButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jCloseButton1.setToolTipText("Close group panel.");
    jCloseButton1.setText("Close");
    jCloseButton1.addActionListener(new
        FramePlayerGroup_jCloseButton1_actionAdapter(this));
//    jBattleSheetPanel1.setPreferredSize(new Dimension(800,600));
//    gridLayout1.setRows(25);
//    jPLGroupPanel.setLayout(gridLayout1);
    jPLSaveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jPLSaveButton.setToolTipText("Save this group to file.");
    jPLSaveButton.setText("Save");
    jPLSaveButton.addActionListener(new
        FramePlayerGroup_jPLSaveButton_actionAdapter(this));
    jPLLoadButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jPLLoadButton.setToolTipText("Load saved group.");
    jPLLoadButton.setText("Group");
    jPLLoadButton.addActionListener(new
        FramePlayerGroup_jPLLoadButton_actionAdapter(this));
    jPLClearButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jPLClearButton.setToolTipText("Remove everyone from group.");
    jPLClearButton.setText("Clear");
    jPLClearButton.addActionListener(new
        FramePlayerGroup_jPLClearButton_actionAdapter(this));
    jPLGroupTabbedPane.setBackground(Color.lightGray);
    jPLGroupTabbedPane.setFont(new Font("Dialog",Font.BOLD,11));
    jPLGroupTabbedPane.setBorder(titledBorder2);
    jPLGroupTabbedPane.addChangeListener(new FramePlayerGroup_jPLGroupTabbedPane_changeAdapter(this));
    jGroupDatePanel.setBackground(Color.lightGray);
    jButtonImportXML.setFont(new java.awt.Font("Dialog", 0, 9));
    jButtonImportXML.setToolTipText("Import HMTK XML character sheet.");
    jButtonImportXML.setText("Import");
    jButtonImportXML.addActionListener(new
        FramePlayerGroup_jButtonImportXML_actionAdapter(this));
    jLoadPlayerButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jLoadPlayerButton.setToolTipText("Load a Player, this must be a character SAVED in HackSACK.");
    jLoadPlayerButton.setText("Player");
    jLoadPlayerButton.addActionListener(new FramePlayerGroup_jLoadPlayerButton_actionAdapter(this));
    jPlayerListPanel.setBackground(Color.lightGray);
    jPlayerListPanel.setBorder(titledBorder3);
    jPlayerListPanel.setPreferredSize(new Dimension(100, 110));
    jPlayerListPanel.setLayout(borderLayout3);
    jDetailedPlayerTab.setLayout(borderLayout2);
    jPartyPlayerList.setBackground(Color.lightGray);
    jPartyPlayerList.setFont(new java.awt.Font("Dialog", 0, 9));
    jPartyPlayerList.setPreferredSize(new Dimension(0, 0));
    jPartyPlayerList.addListSelectionListener(new FramePlayerGroup_jPartyPlayerList_listSelectionAdapter(this));
    jPlayerDetailsPanel.setBackground(Color.darkGray);
    jPlayerDetailsPanel.setAlignmentY((float) 0.5);
    jPlayerDetailsPanel.setBorder(titledBorder4);
    jPlayerDetailsPanel.setLayout(borderLayout4);
    jPLGrouptPanel1.setBackground(Color.lightGray);
    this.getContentPane().setBackground(Color.lightGray);
    this.getContentPane().setLayout(borderLayout6);
    jPartyLogTab.setLayout(borderLayout5);
    jPartyLogDetailsPanel.setBackground(Color.lightGray);
    jPartyLogDetailsPanel.setLayout(verticalFlowLayout1);
    jNewPlayer.setFont(new java.awt.Font("Dialog", 0, 9));
    jNewPlayer.setToolTipText("Add a new player.");
    jNewPlayer.setText("New Player");
    jNewPlayer.addActionListener(new FramePlayerGroup_jNewPlayer_actionAdapter(this));
    verticalFlowLayout1.setHgap(1);
    verticalFlowLayout1.setVgap(1);
    this.getContentPane().add(jPLGrouptPanel1, BorderLayout.CENTER);
    jPLGrouptPanel1.add(jGroupDatePanel,  BorderLayout.SOUTH);
    jPLGrouptPanel1.add(jPLGroupTabbedPane,  BorderLayout.CENTER);
    jSplitPane1.add(jPlayerListPanel, JSplitPane.LEFT);
    jPlayerListPanel.add(jPartyPlayerList, BorderLayout.CENTER);
    jSplitPane1.add(jPlayerDetailsPanel, JSplitPane.RIGHT);
    jPLGroupTabbedPane.add(jDetailedPlayerTab, "Party Members");
    jDetailedPlayerTab.add(jScrollPane1, BorderLayout.CENTER);
    jPLGroupTabbedPane.add(jPartyLogTab, "Group Log");
    jScrollPane1.getViewport().add(jSplitPane1, null);
    this.getContentPane().add(jPLGrouptPanel3, BorderLayout.SOUTH);
    jPLGrouptPanel3.add(jPLSaveButton, null);
    jPLGrouptPanel3.add(jPLClearButton, null);
    jPLGrouptPanel3.add(jPLLoadButton, null);
    jPLGrouptPanel3.add(jLoadPlayerButton, null);
    jPLGrouptPanel3.add(jNewPlayer, null);
    jPLGrouptPanel3.add(jButtonImportXML, null);
    jPLGrouptPanel3.add(jCloseButton1, null);
    jPartyLogTab.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jPartyLogDetailsPanel, null);

    jPartyPlayerList.setCellRenderer(new CellRendererPlayerBox());
    jPartyPlayerList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

  }

  void this_windowClosed(WindowEvent e) {
//    jPLGroupTabbedPane.removeAll();
      this.setVisible(false);
//    this.hide();
  }

  void jCloseButton1_actionPerformed(ActionEvent e) {
//    jPLGroupTabbedPane.removeAll();
      this.setVisible(false);
//    this.hide();
  }

  void jPLSaveButton_actionPerformed(ActionEvent e) {
    TableGroupLog.AskWhereToSaveGroup(oParent,this);
  }

  static void LoadPartyGroupPane(HackSackFrame oParent) {
    if (!oParent.fPlayerGroupFrame.isVisible()) {
      Dimension dlgSize = oParent.fPlayerGroupFrame.getPreferredSize();
      Dimension frmSize = oParent.fPlayerGroupFrame.getSize();
      Point loc = oParent.fPlayerGroupFrame.getLocation();

      String sX = oParent.Prefs.getProperty("pref-plX");
      String sY = oParent.Prefs.getProperty("pref-plY");
      String sHx = oParent.Prefs.getProperty("pref-plHx");
      String sHy = oParent.Prefs.getProperty("pref-plHy");
      if (sX != null && sY != null & sHx != null && sHy != null) {
        int nX = Integer.parseInt(sX);
        int nY = Integer.parseInt(sY);
        int nHx = Integer.parseInt(sHx);
        int nHy = Integer.parseInt(sHy);
        oParent.fPlayerGroupFrame.setLocation(nX, nY);
        oParent.fPlayerGroupFrame.setSize(nHx, nHy);
        oParent.fPlayerGroupFrame.jPLGrouptPanel1.setPreferredSize(new
            Dimension(nHy, nHx));
      }
      else {
        oParent.fPlayerGroupFrame.jPLGrouptPanel1.setPreferredSize(new
            Dimension(800, 600));
        oParent.fPlayerGroupFrame.pack();
      }

      oParent.fPlayerGroupFrame.GeneratePartyGroupSheet();
      oParent.fPlayerGroupFrame.setVisible(true);
//      oParent.fPlayerGroupFrame.show();
    }
//    else if (oPlayer != null) {
//      oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet(); // refresh so log is at back
//    }
  }

  void jPLLoadButton_actionPerformed(ActionEvent e) {
    TableGroupLog.AskWhatFileToLoad(oParent, this);
//    RefreshPlayerGroupSheet();
  }

  void jPLClearButton_actionPerformed(ActionEvent e) {
    CleanPlayerGroupSheet();
  }



  void CleanPlayerGroupSheet() {
//    jPLGroupTabbedPane.removeAll();

    // delete All players in the party list
    oParent.fPlayerGroupFrame.mPartyPlayerList.removeAllElements();
    jPlayerDetailsPanel.removeAll();
    jPLGroupTabbedPane.repaint();

    while (oParent.gplGroupLog.lPlayers.size() != 0) {
      oParent.gplGroupLog.lPlayers.remove(0);
      // reset the global group variable to new
    }
    oParent.gplGroupLog = new TableGroupLog(oParent);
    titledBorder4.setTitle("Select a Player");
    // refresh this cause player dropped out
    oParent.fBattleSheetFrame.LoadPartyPanel(oParent);

    AddPartyGroupLog(); // grouplog is cleaned before we load...
  }

  void jButtonImportXML_actionPerformed(ActionEvent e) {
    SaxParserXMLCharacter.AskWhatFileToLoad(oParent, this);
  }

  void jLoadPlayerButton_actionPerformed(ActionEvent e) {
//    SaxParserForPL.AskWhatFileToLoad(oParent,this,true);
        TablePlayer.AskWhatFileToLoad(oParent,this,true);
  }

  void jPartyPlayerList_valueChanged(ListSelectionEvent e) {
    if (mPartyPlayerList.getSize() > 0) {
      TablePlayer oPlayer = (TablePlayer) jPartyPlayerList.getSelectedValue();
      if (oPlayer != null) {
        jPlayerDetailsPanel.removeAll();
//      jPlayerDetailsPanel.repaint();
        jPLGroupTabbedPane.repaint();
        jPlayerDetailsPanel.add(oPlayer.getPlayerDisplayPanel(),
                                BorderLayout.CENTER);
        titledBorder4.setTitle(oPlayer.sCharacter);
        jDetailedPlayerTab.setName(oPlayer.sCharacter);

        // this should flush out any crap in memory we dont need
//        System.gc();
        //
      }

    }

  }

  void jPLGroupTabbedPane_stateChanged(ChangeEvent e) {
    // refresh playerlog
    AddPartyGroupLog();

  }

  void jNewPlayer_actionPerformed(ActionEvent e) {
    TablePlayer oNew = new TablePlayer(oParent);
    oNew.sPlayerName = "New Player";
    oNew.sCharacter = "New Character";
    oNew.sRace = "Human";

    oNew.SetupBasicAbilities(oParent,oNew);
    oNew.SetupBasicCoins(oParent,oNew);
    oNew.SetupBasicSaves(oParent,oNew);

    oParent.gplGroupLog.lPlayers.add(oNew);
    mPartyPlayerList.addElement(oNew);
    jPartyPlayerList.setSelectedValue(oNew,true);
    oParent.fBattleSheetFrame.LoadPartyPanel(oParent);

    oParent.ShowDone(this,"Added a new player to group.");
  }


  void AddPartyGroupLog() {
    if (oParent != null) {
      jPartyLogDetailsPanel.removeAll();
      jPartyLogDetailsPanel.add(oParent.gplGroupLog.getPartyGroupLogPanel());
    }
  }

  // add every player in the lPlayers list to the sheet
  void GeneratePartyGroupSheet() {
    if (oParent.gplGroupLog.lPlayers.size() > 0) {
      AddPartyGroupLog();
    }

    // refresh the battle sheet if its open cause we added someone perhaps
    if (oParent.fBattleSheetFrame.isVisible() && oParent.lCreatures.size() > 0) {
      oParent.fBattleSheetFrame.LoadBattleSheetPane(oParent);
    }
  }

}

class FramePlayerGroup_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  FramePlayerGroup adaptee;

  FramePlayerGroup_this_windowAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}

class FramePlayerGroup_jCloseButton1_actionAdapter
    implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jCloseButton1_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseButton1_actionPerformed(e);
  }
}

class FramePlayerGroup_jPLSaveButton_actionAdapter
    implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jPLSaveButton_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jPLSaveButton_actionPerformed(e);
  }
}

class FramePlayerGroup_jPLLoadButton_actionAdapter
    implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jPLLoadButton_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jPLLoadButton_actionPerformed(e);
  }
}

class FramePlayerGroup_jPLClearButton_actionAdapter
    implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jPLClearButton_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jPLClearButton_actionPerformed(e);
  }
}

class FramePlayerGroup_jButtonImportXML_actionAdapter
    implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jButtonImportXML_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonImportXML_actionPerformed(e);
  }
}

class FramePlayerGroup_jLoadPlayerButton_actionAdapter implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jLoadPlayerButton_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jLoadPlayerButton_actionPerformed(e);
  }
}

class FramePlayerGroup_jPartyPlayerList_listSelectionAdapter implements javax.swing.event.ListSelectionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jPartyPlayerList_listSelectionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(ListSelectionEvent e) {
    adaptee.jPartyPlayerList_valueChanged(e);
  }
}

class FramePlayerGroup_jPLGroupTabbedPane_changeAdapter implements javax.swing.event.ChangeListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jPLGroupTabbedPane_changeAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.jPLGroupTabbedPane_stateChanged(e);
  }
}

class FramePlayerGroup_jNewPlayer_actionAdapter implements java.awt.event.ActionListener {
  FramePlayerGroup adaptee;

  FramePlayerGroup_jNewPlayer_actionAdapter(FramePlayerGroup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jNewPlayer_actionPerformed(e);
  }
}

