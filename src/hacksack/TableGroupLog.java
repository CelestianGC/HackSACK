package hacksack;

import java.io.*;
import java.text.*;
import java.util.*;
//import com.borland.jbcl.layout.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import org.jdom.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableGroupLog {
  HackSackFrame oParent = null;
  ArrayList lPlayers = null;
  ArrayList lMonsters = null;
  ArrayList lEXP = null;
  ArrayList lHonor = null;
  ArrayList lTimers = null;

  JSpinner jYear = null;
  JSpinner jMonth = null;
  JLabel jMonthLabel = null;
  JLabel jWeekLabel = null;
  JSpinner jDay = null;
  JLabel jDayLabel = null;
  JSpinner jTurn = null;
  JSpinner jRound = null;
  JSpinner jSegment = null;
//  boolean bGroupPanelTimeKeep = true;
//  JButton jTimerPlace = null;
  JLabel jYearLabel = null;
  JLabel jMonthLabel2 = null;
  JLabel jDayLabel2 = null;
  JLabel jTurnLabel = null;
  JLabel jRoundLabel = null;
  JLabel jSegmentLabel = null;

  JSpinner jYearBS = null;
  JSpinner jMonthBS = null;
  JLabel jMonthLabelBS = null;
  JLabel jWeekLabelBS = null;
  JSpinner jDayBS = null;
  JLabel jDayLabelBS = null;
  JSpinner jTurnBS = null;
  JSpinner jRoundBS = null;
  JSpinner jSegmentBS = null;
//  JButton jTimerPlaceBS = null;
  JLabel jYearLabelBS = null;
  JLabel jMonthLabel2BS = null;
  JLabel jDayLabel2BS = null;
  JLabel jTurnLabelBS = null;
  JLabel jRoundLabelBS = null;
  JLabel jSegmentLabelBS = null;

  JButton bTimer = null;
  JButton bTimerBS = null;

  JButton bHonor = null;
  JButton bGrantHonor = null;
  JButton bEXPBonus = null;
  JButton bGrantEXPBonus = null;
  JButton bEXP = null;
  JButton bApplyTemporalHonor = null;
//  JButton bAddArmor = null;
  JTextArea jLog = null;

  int TIMER_YEAR = 0;
  int TIMER_MONTH = 1;
  int TIMER_DAY = 2;
  int TIMER_TURN = 3;
  int TIMER_ROUND = 4;
  int TIMER_SEGMENT = 5;

  int A_SEGMENT = 10;
  int A_ROUND = A_SEGMENT * 10; // how many segments in a round/turn/day/etc
  int A_TURN = A_ROUND * 10;
  int A_DAY = A_TURN * 144;
  int A_MONTH = A_DAY * 28;
  int A_YEAR = A_MONTH * 14;

// Garweeze Wurld month names
  String[] gwMonths = {
      "Haar'Kiev", "Jevar'Kiev", "Nardur'Kiev", "Tomar'Kiev",
      "Blain'Sa", "Sirn'Sa", "Yurn'Sa", "Monz'Tera", "Barz'Tera", "Quay'Tera",
      "Tarn'Tera", "Adnar'Kerz", "Kras'Kerz", "Freta'Kerz", ""};
  // GW day of week names
  String[] gwDayOfWeek = {
      "Sa'Mar", "Tu'Mar", "Quay'Mar", "Run'Mar", "Ara'Mar", "Gart'Mar",
      "Pin'Mar"};
  public TableGroupLog() {

    lMonsters = new ArrayList();
    lEXP = new ArrayList();
    lHonor = new ArrayList();
    lTimers = new ArrayList();
    lPlayers = new ArrayList();

    jLog = new JTextArea();

    bTimer = new JButton();
    bTimerBS = new JButton();

    bHonor = new JButton();
    bGrantHonor = new JButton();
    bEXPBonus = new JButton();
    bGrantEXPBonus = new JButton();
    bEXP = new JButton();
    bApplyTemporalHonor = new JButton();
    //   jTimerPlace = new JButton();

//    bAddArmor = new JButton();
//    bAddArmor.setEnabled(false);

    jYear = new JSpinner(new SpinnerNumberModel(2, 1, 2000, 1));
    jMonth = new JSpinner(new SpinnerNumberModel(2, 1, 15, 1));
    jDay = new JSpinner(new SpinnerNumberModel(2, 1, 29, 1));
    jTurn = new JSpinner(new SpinnerNumberModel(2, 1, 145, 1));
    jRound = new JSpinner(new SpinnerNumberModel(2, 1, 11, 1));
    jSegment = new JSpinner(new SpinnerNumberModel(2, 1, 11, 1));
    jMonthLabel = new JLabel("Month");
    jMonthLabel.setToolTipText("Current Garweeze Wurld Month.");
    jWeekLabel = new JLabel("Week");
    jWeekLabel.setToolTipText("Current Garweeze Wurld Week.");
    jDayLabel = new JLabel("Day");
    jDayLabel.setToolTipText("Current Garweeze Wurld Day of Week.");

    jYearLabel = new JLabel("Year");
    jMonthLabel2 = new JLabel("Month");
    jDayLabel2 = new JLabel("Day");
    jTurnLabel = new JLabel("Turn");
    jRoundLabel = new JLabel("Round");
    jSegmentLabel = new JLabel("Segment");

    jYearBS = new JSpinner(new SpinnerNumberModel(2, 1, 2000, 1));
    jMonthBS = new JSpinner(new SpinnerNumberModel(2, 1, 15, 1));
    jDayBS = new JSpinner(new SpinnerNumberModel(2, 1, 29, 1));
    jTurnBS = new JSpinner(new SpinnerNumberModel(2, 1, 145, 1));
    jRoundBS = new JSpinner(new SpinnerNumberModel(2, 1, 11, 1));
    jSegmentBS = new JSpinner(new SpinnerNumberModel(2, 1, 11, 1));
    jMonthLabelBS = new JLabel("Month");
    jMonthLabelBS.setToolTipText("Current Garweeze Wurld Month.");
    jWeekLabelBS = new JLabel("Week");
    jWeekLabelBS.setToolTipText("Current Garweeze Wurld Week.");
    jDayLabelBS = new JLabel("Day");
    jDayLabelBS.setToolTipText("Current Garweeze Wurld Day of Week.");

    jYearLabelBS = new JLabel("Year");
    jMonthLabel2BS = new JLabel("Month");
    jDayLabel2BS = new JLabel("Day");
    jTurnLabelBS = new JLabel("Turn");
    jRoundLabelBS = new JLabel("Round");
    jSegmentLabelBS = new JLabel("Segment");

  }

  public TableGroupLog(HackSackFrame oParent) {
    this();
    this.oParent = oParent;
//    this.lPlayers = oParent.gplGroupLog.lPlayers;

    Font fFont = new Font("Dialog", Font.PLAIN, 9);

    jYear.addChangeListener(new jGroupSpinnerListener(oParent, this, "YEAR"));
    jMonth.addChangeListener(new jGroupSpinnerListener(oParent, this, "MONTH"));
    jDay.addChangeListener(new jGroupSpinnerListener(oParent, this, "DAY"));
    jTurn.addChangeListener(new jGroupSpinnerListener(oParent, this, "TURN"));
    jRound.addChangeListener(new jGroupSpinnerListener(oParent, this, "ROUND"));
    jSegment.addChangeListener(new jGroupSpinnerListener(oParent, this,
        "SEGMENT"));
    jYear.setValue(new Integer(1)); // do this so it'll reset all the name labels to proper names

    jYearBS.addChangeListener(new jGroupSpinnerListener(oParent, this, "YEARBS"));
    jMonthBS.addChangeListener(new jGroupSpinnerListener(oParent, this,
        "MONTHBS"));
    jDayBS.addChangeListener(new jGroupSpinnerListener(oParent, this, "DAYBS"));
    jTurnBS.addChangeListener(new jGroupSpinnerListener(oParent, this, "TURNBS"));
    jRoundBS.addChangeListener(new jGroupSpinnerListener(oParent, this,
        "ROUNDBS"));
    jSegmentBS.addChangeListener(new jGroupSpinnerListener(oParent, this,
        "SEGMENTBS"));
    jYearBS.setValue(new Integer(1)); // do this so it'll reset all the name labels to proper names

    bTimer.addActionListener(new jLogButtonListener(oParent, "bTimer", this));
    bTimer.setText("Set Timer");
    bTimer.setFont(fFont);
    bTimer.setToolTipText("Set timer to keep track of something in game time. Bob paralyzed for 3 rounds, or turns or ...");

    bTimerBS.addActionListener(new jLogButtonListener(oParent, "bTimerBS", this));
    bTimerBS.setText("Set Timer");
    bTimer.setFont(fFont);
    bTimerBS.setToolTipText("Set timer to keep track of something in game time. Bob paralyzed for 3 rounds, or turns or ...");

    bTimerBS.setFont(fFont);

    bHonor.addActionListener(new jLogButtonListener(oParent, "bHonor", this));
    bHonor.setText("New Honor Type");
    bHonor.setToolTipText("Create a new type of honor award.");
    bGrantHonor.addActionListener(new jLogButtonListener(oParent, "bGrantHonor", this));
    bGrantHonor.setToolTipText(
        "Grant an existing honor award type that will be applied at end of session.");
    bGrantHonor.setText("Grant Honor");

    bEXPBonus.addActionListener(new jLogButtonListener(oParent, "bEXPBonus", this));
    bEXPBonus.setText("New EXP Type");
    bEXPBonus.setToolTipText("Create a new EXP bonus award type.");

    bGrantEXPBonus.addActionListener(new jLogButtonListener(oParent,
        "bGrantEXPBonus", this));
    bGrantEXPBonus.setText("Grant EXP");
    bGrantEXPBonus.setToolTipText(
        "Grant an existing EXP award that will be applied at end of session.");

    bEXP.addActionListener(new jLogButtonListener(oParent, "bEXP", this));

    bApplyTemporalHonor.addActionListener(new jLogButtonListener(oParent,
        "bApplyTemporalHonor", this));
    bApplyTemporalHonor.setText("Apply Temporal Honor");
    bApplyTemporalHonor.setToolTipText("Apply all the temporal adjustments to players honor (divide temporal honor by 4).");

//    bAddArmor.addActionListener(new jLogButtonListener(oParent, "bAddArmor", this));
//    bAddArmor.setText("Add Armor");
//    bAddArmor.setToolTipText("Add a new armor type to the armor list.");

//    jTimerPlace.addActionListener(new jLogButtonListener(oParent,"jTimerPlace",this));
//    jTimerPlace.setText("Time Track Here");
//    jTimerPlace.setToolTipText("Move the time tracking options to this panel.");
  }

  long GetCurrentTimeStamp() {
    long lTimeStamp = 0;
    int nYear = Integer.parseInt(jYear.getValue().toString());
    int nMonth = Integer.parseInt(jMonth.getValue().toString());
    int nDay = Integer.parseInt(jDay.getValue().toString());
    int nTurn = Integer.parseInt(jTurn.getValue().toString());
    int nRound = Integer.parseInt(jRound.getValue().toString());
    int nSegment = Integer.parseInt(jSegment.getValue().toString());

    // this is because we set the spinnder to Max+1 so that when it reaches
    // max+1 we roll over to next value, this will keep timer from having
    // having a new turn PLUS the additional 10 rounds that we just removed.
    if (nMonth == 15) {
      nMonth = 0;
    }
    if (nDay == 29) {
      nDay = 0;
    }
    if (nTurn == 145) {
      nTurn = 0;
    }
    if (nRound == 11) {
      nRound = 0;
    }
    if (nSegment == 11) {
      nSegment = 0;

    }
    lTimeStamp += A_YEAR * nYear;
    lTimeStamp += A_MONTH * nMonth;
    lTimeStamp += A_DAY * nDay;
    lTimeStamp += A_TURN * nTurn;
    lTimeStamp += A_ROUND * nRound;
    lTimeStamp += nSegment;

    return lTimeStamp;
  }

  // advance the timers they might have set.
  void AdvanceTimers() {
    if (lTimers.size() > 0) { // just for sanity
      for (int i = 0; i < lTimers.size(); i++) {
        Timer oT = (Timer) lTimers.get(i);
        long lCurrentTime = GetCurrentTimeStamp();
//groupLog("Checking Timer for [" + oT.sDesc + "]. Currnet=("+lCurrentTime+") Alarm=("+oT.lTimer+")\n");
        if (lCurrentTime >= oT.lTimer) {
          lTimers.remove(oT); // remove before dialog.
          oParent.ShowDoneFrame(oParent, "Timer Expired (" + oT.sSetText + ")",
                                "Timer set for " + oT.sSetText +
                                ", expired.\nDetails: " + oT.sDesc);
          groupLog("Timer expired for [" + oT.sDesc + "]\n");
          // refresh grouplog
          oParent.fPlayerGroupFrame.AddPartyGroupLog();
        }
      }
    }
  }

  // re-roll all initiatives (called when round or higher is manually advanced
  void initNextRound() {
    for (int i = 0; i < oParent.lCreatures.size(); i++) {
      CreatureCore oC = (CreatureCore) oParent.lCreatures.get(i);
      if (oC.initReadyForNextRound()) {
        oC.initRollCreatureInitiative();
      }
    }

    if (oParent.fBattleSheetFrame != null) {
      oParent.fBattleSheetFrame.jCreatureList.repaint();
    }
  }

  // advance to next segment and notify the creatures to attack
  void initAdvanceNextSegment(int nSegment) {
    boolean bMovedView = false;
    if (nSegment <= 10) {
      for (int i = 0; i < oParent.lCreatures.size(); i++) {
        CreatureCore oC = (CreatureCore) oParent.lCreatures.get(i);
        CreatureCombat oA = oC.initMyAttackRound(nSegment);
        if (oA != null) {
          // ACTIVATE!
          oA.initSetAttackActive();

          /*
               // disabled since we only list one mob at a time now

                            // move view to first attacker that is ready
                  if (!bMovedView) {
                    oC.jNameLabel.scrollRectToVisible(
                        oC.oParent.fBattleSheetFrame.jBattleSheetScrollPane1.
                        getVisibleRect());
                    bMovedView = true;
                  } // end move to first attacker
           */
        }
      }
    }
    if (oParent.lCreatures.size() > 0) {
      oParent.fBattleSheetFrame.jCreatureList.repaint();
    }
  }

  // return a groups average level
  int GetGroupAverageLevel(ArrayList lPlayers) {
    int nGroupLevel = 0;
    int nGroupCount = lPlayers.size();
    int nGroup = 0;

    for (int i = 0; i < lPlayers.size(); i++) {
      TablePlayer oPlayer = (TablePlayer) lPlayers.get(i);
      if (!oPlayer.jAbsent.isSelected() && !oPlayer.jHireling.isSelected()) {
        nGroup += oPlayer.GetPlayerMaxLevel();
      }
    }
    nGroupLevel = nGroup / nGroupCount;
    return nGroupLevel;
  }

  // log to the Group Log section
  void groupLog(String sLog) {
    if (oParent.gplGroupLog.lPlayers.size() > 0) {
      Locale currentLocale = new Locale("en", "US");
      Date today;
      String dateOut;
      DateFormat dateFormatter;

      dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,
                                                 currentLocale);
      today = new Date();
      dateOut = dateFormatter.format(today);
      int nGYear = Integer.parseInt(oParent.gplGroupLog.jYear.getValue().
                                    toString());
      int nGMonth = Integer.parseInt(oParent.gplGroupLog.jMonth.getValue().
                                     toString());
      int nGDay = Integer.parseInt(oParent.gplGroupLog.jDay.getValue().toString());
//      String sGameDate = "GYear:"+nGYear+" GMonth:"+nGMonth+" GDay:"+nGDay;
      String sGameDate = +nGYear + "/" + nGMonth + "/" + nGDay;
      jLog.append("*" + dateOut + " GameDate: " + sGameDate + ":" + sLog);
    }
  }

  void updateButtonPressed(HackSackFrame oParent, String sThisButtonName,
                           TableGroupLog oMe) {
    if (sThisButtonName.equalsIgnoreCase("bTimer") ||
        sThisButtonName.equalsIgnoreCase("bTimerBS")) {
      DialogSetTimer dlg = new DialogSetTimer(oParent);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Set Timer");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.setVisible(true); ;
    }
    else
    if (sThisButtonName.equalsIgnoreCase("bHonor")) {
      TableGroupLog.DoNewHonorAward(oParent, oParent, false);
    }
    else
    if (sThisButtonName.equalsIgnoreCase("bGrantHonor")) {

      DialogGiveHonorAward dlg = new DialogGiveHonorAward(oParent, null);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Grant Honor Award");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
    }
    else
    if (sThisButtonName.equalsIgnoreCase("bApplyTemporalHonor")) {
      for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
        TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
        if (!oPlayer.jAbsent.isSelected() && !oPlayer.jHireling.isSelected()) {
          if (oPlayer.nTemporalHonor > 0) {
            int nCurrentHonor = Integer.parseInt(oPlayer.jHonor.getModel().
                                                 getValue().toString());
            int nCurrentTemporal = oPlayer.nTemporalHonor;
            int nResult = nCurrentHonor + (oPlayer.nTemporalHonor / 4);
            oPlayer.jHonor.getModel().setValue(new Integer(nResult));
            oPlayer.nTemporalHonor = 0;
            oPlayer.playerLog("Honor adjusted: " + nCurrentTemporal + "/4+" +
                              nCurrentHonor + "=" + nResult + "\n");
            oParent.gplGroupLog.groupLog("Player:" + oPlayer.sCharacter +
                                         " honor adjusted: " + nCurrentTemporal +
                                         "/4+" +
                                         nCurrentHonor + "=" + nResult + "\n");
          }
        }
      }
    }
    else
    if (sThisButtonName.equalsIgnoreCase("bEXPBonus")) {
      TableGroupLog.DoNewEXPBonus(oParent.fPlayerGroupFrame, oParent);
    }
    else
    if (sThisButtonName.equalsIgnoreCase("bGrantEXPBonus")) {
      DialogGiveEXPBonus dlg = new DialogGiveEXPBonus(oParent, null);
//      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Grant EXP Award");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);

    }
    else if (sThisButtonName.equalsIgnoreCase("bEXP")) {
    }
    else if (sThisButtonName.equalsIgnoreCase("jTimerPlace")) {
//      oMe.bGroupPanelTimeKeep = !oMe.bGroupPanelTimeKeep;
//      oMe.timeMoveBar();
    }

  }

  // add new honor awards
  static void DoNewHonorAward(Component oThis, HackSackFrame oParent,
                              boolean bDisplayPlayerAdd) {
    DialogNewHonorAwards dlg = new DialogNewHonorAwards(oParent,
        bDisplayPlayerAdd);
    Dimension dlgSize = dlg.jPanel1.getPreferredSize();
    Dimension frmSize = oThis.getSize();
    Point loc = oThis.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setTitle("Honor Type Awards");
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }

  // make new EXP bonus dialog
  static void DoNewEXPBonus(Component oThis, HackSackFrame oParent) {
    DialogNewEXPBonus dlg = new DialogNewEXPBonus(oParent);
    Dimension dlgSize = dlg.jPanel1.getPreferredSize();
//      Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = oThis.getSize();
    Point loc = oThis.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setTitle("New EXP Bonus Awards");
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }

  void timeAddTimeOptions(JPanel jGroupDatePanel) {
    jGroupDatePanel.removeAll();
    // setup calendar panel
//      JLabel jYearLabel = new JLabel("Year");
    jYearLabel.setToolTipText("Current year.");
    jGroupDatePanel.add(jYearLabel);
    jGroupDatePanel.add(jYear);

//      JLabel jMonthLabel = new JLabel("Month:");
    jMonthLabel.setFont(new Font("Dialog", Font.ITALIC, 11));
    jMonthLabel.setToolTipText("Current Month, 14 months in a year.");
    jGroupDatePanel.add(jMonthLabel);
    jGroupDatePanel.add(jMonthLabel2);
    jGroupDatePanel.add(jMonth);

//      JLabel jDayLabel = new JLabel("Day:");
    jDayLabel.setFont(new Font("Dialog", Font.ITALIC, 11));
    jDayLabel2.setToolTipText("Current Day, 28 days in a month.");
    jGroupDatePanel.add(jWeekLabel);
    jGroupDatePanel.add(jDayLabel);
    jGroupDatePanel.add(jDayLabel2);
    jGroupDatePanel.add(jDay);

//      JLabel jTurnLabel = new JLabel("Turn");
    jTurnLabel.setToolTipText("Current Combat Turn, 144 turns in a day.");
    jGroupDatePanel.add(jTurnLabel);
    jGroupDatePanel.add(jTurn);

//      JLabel jRoundLabel = new JLabel("Round");
    jRoundLabel.setToolTipText("Current Combat Round, 10 rounds in a turn.");
    jGroupDatePanel.add(jRoundLabel);
    jGroupDatePanel.add(jRound);

//      JLabel jSegmentLabel = new JLabel("Segment");
    jSegmentLabel.setToolTipText("Current segment.");
    jGroupDatePanel.add(jSegmentLabel);
    jGroupDatePanel.add(jSegment);

    jGroupDatePanel.add(bTimer); // timer button

//      jGroupDatePanel.repaint();
    // done

  }

  void timeAddTimeOptionsBS(JPanel jGroupDatePanel) {
    jGroupDatePanel.removeAll();
    // setup calendar panel
//      JLabel jYearLabel = new JLabel("Year");
    jYearLabelBS.setToolTipText("Current year.");
    jGroupDatePanel.add(jYearLabelBS);
    jGroupDatePanel.add(jYearBS);

//      JLabel jMonthLabel = new JLabel("Month:");
    jMonthLabelBS.setFont(new Font("Dialog", Font.ITALIC, 11));
    jMonthLabelBS.setToolTipText("Current Month, 14 months in a year.");
    jGroupDatePanel.add(jMonthLabelBS);
    jGroupDatePanel.add(jMonthLabel2BS);
    jGroupDatePanel.add(jMonthBS);

//      JLabel jDayLabel = new JLabel("Day:");
    jDayLabelBS.setFont(new Font("Dialog", Font.ITALIC, 11));
    jDayLabel2BS.setToolTipText("Current Day, 28 days in a month.");
    jGroupDatePanel.add(jWeekLabelBS);
    jGroupDatePanel.add(jDayLabelBS);
    jGroupDatePanel.add(jDayLabel2BS);
    jGroupDatePanel.add(jDayBS);

//      JLabel jTurnLabel = new JLabel("Turn");
    jTurnLabelBS.setToolTipText("Current Combat Turn, 144 turns in a day.");
    jGroupDatePanel.add(jTurnLabelBS);
    jGroupDatePanel.add(oParent.gplGroupLog.jTurnBS);

//      JLabel jRoundLabel = new JLabel("Round");
    jRoundLabelBS.setToolTipText("Current Combat Round, 10 rounds in a turn.");
    jGroupDatePanel.add(jRoundLabelBS);
    jGroupDatePanel.add(oParent.gplGroupLog.jRoundBS);

//      JLabel jSegmentLabel = new JLabel("Segment");
    jSegmentLabelBS.setToolTipText("Current segment.");
    jGroupDatePanel.add(jSegmentLabelBS);
    jGroupDatePanel.add(oParent.gplGroupLog.jSegmentBS);

    jGroupDatePanel.add(oParent.gplGroupLog.bTimerBS); // timer button

//      jGroupDatePanel.repaint();
    // done

  }

  void updateSpinner(HackSackFrame oParent, TableGroupLog oGroup,
                     String sSpinnerName, ChangeEvent eE) {
    oGroup.AdvanceTimers(); // advance timers if any set

    if (sSpinnerName.equalsIgnoreCase("YEARBS")) {
      int nOther = Integer.parseInt(jYear.getValue().toString());
      int nMe = Integer.parseInt(jYearBS.getValue().toString());
      if (nOther != nMe) {
        jYear.setValue(new Integer(nMe));
      }
    }
    else if (sSpinnerName.equals("MONTHBS")) {
      int nOther = Integer.parseInt(jMonth.getValue().toString());
      int nMe = Integer.parseInt(jMonthBS.getValue().toString());
      if (nOther != nMe) {
        jMonth.setValue(new Integer(nMe));
      }
    }
    else if (sSpinnerName.equals("DAYBS")) {
      int nOther = Integer.parseInt(jDay.getValue().toString());
      int nMe = Integer.parseInt(jDayBS.getValue().toString());
      if (nOther != nMe) {
        jDay.setValue(new Integer(nMe));
      }
    }
    else if (sSpinnerName.equals("TURNBS")) {
      int nOther = Integer.parseInt(jTurn.getValue().toString());
      int nMe = Integer.parseInt(jTurnBS.getValue().toString());
      if (nOther != nMe) {
        jTurn.setValue(new Integer(nMe));
      }
    }
    else if (sSpinnerName.equals("ROUNDBS")) {
      int nOther = Integer.parseInt(jRound.getValue().toString());
      int nMe = Integer.parseInt(jRoundBS.getValue().toString());
      if (nOther != nMe) {
        jRound.setValue(new Integer(nMe));
      }
    }
    else if (sSpinnerName.equals("SEGMENTBS")) {
      int nOther = Integer.parseInt(jSegment.getValue().toString());
      int nMe = Integer.parseInt(jSegmentBS.getValue().toString());
      if (nOther != nMe) {
        jSegment.setValue(new Integer(nMe));
      }
    }
    else

    ///////////////-------------------- real timers, above just copies
    if (sSpinnerName.equalsIgnoreCase("YEAR")) {
      jYearBS.setValue(jYear.getValue());
      // if we alter year we set month to 1
      jMonth.setValue(new Integer(1));
    }
    else if (sSpinnerName.equalsIgnoreCase("MONTH")) {
      jMonthBS.setValue(jMonth.getValue());

      int nCurrentMonth = Integer.parseInt(oGroup.jMonth.getValue().toString());
      int nCurrentYear = Integer.parseInt(oGroup.jYear.getValue().toString());

      // if we alter month we set day to 1.
      oGroup.jDay.setValue(new Integer(1));
      if (nCurrentMonth > 14) {
        nCurrentYear++;
        jYear.setValue(new Integer(nCurrentYear));
      }
      else {
        jMonthLabel.setText(gwMonths[nCurrentMonth - 1]);
        jMonthLabelBS.setText(gwMonths[nCurrentMonth - 1]);
      }
    }
    else if (sSpinnerName.equalsIgnoreCase("DAY")) {
      jDayBS.setValue(jDay.getValue());

      int nCurrentDay = Integer.parseInt(oGroup.jDay.getValue().toString());
      int nCurrentDayOfWeek = (Math.abs( (nCurrentDay - 1) % (7)));
      int nCurrentWeek = (Math.abs( ( (nCurrentDay - 1) / 7)));
      int nCurrentMonth = Integer.parseInt(oGroup.jMonth.getValue().toString());

      // if we change day reset turn to 1
      oGroup.jTurn.setValue(new Integer(1));
      if (nCurrentDay > 28) {
        nCurrentMonth++;
        oGroup.jMonth.setValue(new Integer(nCurrentMonth));
      }
      else {
        jWeekLabel.setText("(Week " + (nCurrentWeek + 1) + ")");
        jWeekLabelBS.setText("(Week " + (nCurrentWeek + 1) + ")");

        jDayLabel.setText(gwDayOfWeek[nCurrentDayOfWeek]);
        jDayLabelBS.setText(gwDayOfWeek[nCurrentDayOfWeek]);
//        oGroup.jDayLabel.setText("Day "+nCurrentDayOfWeek);
      }
    }
    else if (sSpinnerName.equalsIgnoreCase("TURN")) {
      jTurnBS.setValue(jTurn.getValue());

      int nCurrentTurn = Integer.parseInt(oGroup.jTurn.getValue().toString());
      int nCurrentDay = Integer.parseInt(oGroup.jDay.getValue().toString());

      // if we increase the turn round/segments set to 1
      oGroup.jRound.setValue(new Integer(1));
      if (nCurrentTurn > 144) { // 144 turns = 24 hours, bump up day
        nCurrentDay++;
        oGroup.jDay.setValue(new Integer(nCurrentDay));
      }
    }
    else if (sSpinnerName.equalsIgnoreCase("ROUND")) {
      jRoundBS.setValue(jRound.getValue());

      int nCurrentRound = Integer.parseInt(oGroup.jRound.getValue().toString());
      int nCurrentTurn = Integer.parseInt(oGroup.jTurn.getValue().toString());

      // if we change round, segment set to 1
      oGroup.jSegment.setValue(new Integer(1));
      if (nCurrentRound > 10) { // 10 rounds = turn
        // increase turn +1
        nCurrentTurn++;
        oGroup.jTurn.setValue(new Integer(nCurrentTurn));
      }
      else {
        oGroup.initNextRound();
      }
    }
    else if (sSpinnerName.equalsIgnoreCase("SEGMENT")) {
      jSegmentBS.setValue(jSegment.getValue());

      int nCurrentSegment = Integer.parseInt(oGroup.jSegment.getValue().
                                             toString());
      int nCurrentRound = Integer.parseInt(oGroup.jRound.getValue().toString());

      if (nCurrentSegment > 10) { // 10 segments = 1 round
        // increase round +1
        nCurrentRound++;
        oGroup.jRound.setValue(new Integer(nCurrentRound));
      }
      else {
        oGroup.initAdvanceNextSegment(nCurrentSegment);

      }
    }

  } // end spinner listener

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("PartyLog");

    eItem.addContent(new Element("jLog").setText(xmlControl.escapeChars(jLog.
        getText())));
    eItem.addContent(new Element("jYear").setText(jYear.getValue().toString()));
    eItem.addContent(new Element("jMonth").setText(jMonth.getValue().toString()));
    eItem.addContent(new Element("jDay").setText(jDay.getValue().toString()));
    eItem.addContent(new Element("jTurn").setText(jTurn.getValue().toString()));
    eItem.addContent(new Element("jRound").setText(jRound.getValue().toString()));
    eItem.addContent(new Element("jSegment").setText(jSegment.getValue().
        toString()));

//          lPlayers
    if (lPlayers.size() > 0) {
      Element ePartyMember = new Element("PartyMembers");
      for (int i = 0; i < lPlayers.size(); i++) {
        TablePlayer oP = (TablePlayer) lPlayers.get(i);
        ePartyMember.addContent(oP.xmlGetElements());
      }
      eItem.addContent(ePartyMember);
    }

//          lTimers
    if (lTimers.size() > 0) {
      Element eTimers = new Element("Timers");
      for (int i = 0; i < lTimers.size(); i++) {
        Timer oP = (Timer) lTimers.get(i);
        eTimers.addContent(oP.xmlGetElements());
      }
      eItem.addContent(eTimers);
    }

//          lEXP
    if (lEXP.size() > 0) {
      Element eEXPAwards = new Element("EXPAwards");
      for (int i = 0; i < lEXP.size(); i++) {
        EXPAwards oP = (EXPAwards) lEXP.get(i);
        if (!oP.jApplied.isSelected()) {
          eEXPAwards.addContent(oP.xmlGetElements());
        }
      }
      eItem.addContent(eEXPAwards);
    }

//          lHonor
    if (lHonor.size() > 0) {
      Element eHonorAwards = new Element("HonorAwards");
      for (int i = 0; i < lHonor.size(); i++) {
        HonorAward oP = (HonorAward) lHonor.get(i);
        if (!oP.jApplied.isSelected()) {
          eHonorAwards.addContent(oP.xmlGetElements());
        }
      }
      eItem.addContent(eHonorAwards);
    }

//          lMonsters
    if (lMonsters.size() > 0) {
      Element eMonsterAwards = new Element("MonsterAwards");
      for (int i = 0; i < lMonsters.size(); i++) {
        Monster oP = (Monster) lMonsters.get(i);
        if (!oP.jApplied.isSelected()) {
          eMonsterAwards.addContent(oP.xmlGetElements());
        }
      }
      eItem.addContent(eMonsterAwards);
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
  static TableGroupLog xmlGetFromElements(Element eItem,
                                          HackSackFrame oParent) {
    TableGroupLog oO = oParent.gplGroupLog;

    oO.jLog.setText(xmlControl.unEscapeChars(eItem.getChild("jLog").getText()));
    oO.jYear.setValue(new Integer(Integer.parseInt(eItem.getChild("jYear").
        getText())));
    oO.jMonth.setValue(new Integer(Integer.parseInt(eItem.getChild("jMonth").
        getText())));
    oO.jDay.setValue(new Integer(Integer.parseInt(eItem.getChild("jDay").
                                                  getText())));
    oO.jTurn.setValue(new Integer(Integer.parseInt(eItem.getChild("jTurn").
        getText())));
    oO.jRound.setValue(new Integer(Integer.parseInt(eItem.getChild("jRound").
        getText())));
    oO.jSegment.setValue(new Integer(Integer.parseInt(eItem.getChild("jSegment").
        getText())));

    Element ePartyMembers = eItem.getChild("PartyMembers");
    if (ePartyMembers != null) {
      java.util.List lList = ePartyMembers.getChildren("Player");
      Iterator in = lList.iterator();
      while (in.hasNext()) {
        Element eO = (Element) in.next();
        TablePlayer oNew = TablePlayer.xmlGetFromElements(eO, oParent);
        oO.lPlayers.add(oNew);
        oParent.fPlayerGroupFrame.mPartyPlayerList.addElement(oNew);
      }
    }

    Element eTimers = eItem.getChild("Timers");
    if (eTimers != null) {
      java.util.List lList = eTimers.getChildren("Timer");
      Iterator in = lList.iterator();
      while (in.hasNext()) {
        Element eO = (Element) in.next();
        oO.lTimers.add(Timer.xmlGetFromElements(eO, oO));
      }
    }

    Element eEXPAwards = eItem.getChild("EXPAwards");
    if (eEXPAwards != null) {
      java.util.List lList = eEXPAwards.getChildren("Award");
      Iterator in = lList.iterator();
      while (in.hasNext()) {
        Element eO = (Element) in.next();
        oO.lEXP.add(EXPAwards.xmlGetFromElements(eO, oParent));
      }
    }

    Element eHonorAwards = eItem.getChild("HonorAwards");
    if (eHonorAwards != null) {
      java.util.List lList = eHonorAwards.getChildren("Award");
      Iterator in = lList.iterator();
      while (in.hasNext()) {
        Element eO = (Element) in.next();
        oO.lHonor.add(HonorAward.xmlGetFromElements(eO, oParent));
      }
    }

    Element eMonsterAwards = eItem.getChild("MonsterAwards");
    if (eMonsterAwards != null) {
      java.util.List lList = eMonsterAwards.getChildren("Award");
      Iterator in = lList.iterator();
      while (in.hasNext()) {
        Element eO = (Element) in.next();
        oO.lMonsters.add(Monster.xmlGetFromElements(eO, oParent));
      }
    }

    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved to
   * an xml file.
   *
   * @param oGroup ArrayList
   * @param nMaxID int
   * @return Document
   */
  static Document xmlBuildDocFromVar(TableGroupLog oGroup, int nMaxID) {
    Element eRoot = new Element("PartySheet");
    eRoot.setAttribute(new Attribute("JDOM", "10b"));
    Document doc = new Document(eRoot);
    eRoot.addContent(oGroup.xmlGetElements());
    return doc;
  }

  /**
   * this builds an arraylist of this object type from a document that was built
   * from a xml file, Class.xml, Gear.xml/etc...
   *
   * @param oParent HackSackFrame
   * @param doc Document
   * @return ArrayList
   */
  static TableGroupLog xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {

    TableGroupLog oThisGroup = oParent.gplGroupLog;

    try {
      Element eRoot = doc.getRootElement();
      Element ePartyLog = eRoot.getChild("PartyLog");
      oThisGroup = TableGroupLog.xmlGetFromElements(ePartyLog, oParent);
    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load party log from XML.");
    }

    return oThisGroup;
  }

  /**
   *
   * @param oParent HackSackFrame
   * @param oThis Component
   */
  static void AskWhatFileToLoad(HackSackFrame oParent, Component oThis) {
    JFileChooser jFileChooser1 = new JFileChooser();
    jFileChooser1.setApproveButtonText("Load");
    jFileChooser1.setDialogTitle("Load Group");
    if (System.getProperty("lastgroup.dir") != null) {
      jFileChooser1.setCurrentDirectory(new File(System.getProperty(
          "lastgroup.dir")));
    }
    else {

      jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                                 File.separatorChar +
                                                 oParent.sPlayerGroupDir));

    }
    int returnVal = jFileChooser1.showOpenDialog(oThis);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
      String sFile = jFileChooser1.getSelectedFile().getName();

      if (!jFileChooser1.getSelectedFile().exists()) {
        oParent.gmLog(true, false, "File " + sFile + " does not exist.\n");
      }
      else {
        FrameStartUp dlg = oParent.ShowStatusProgress(oThis,
            "Loading", "Loading party information.");

        try {
//                 SaxParserGroup.loadUp(oParent, sFileName,true);

          Document doc = xmlControl.loadDoc(oParent, oThis, sFileName);
          if (doc.getRootElement().getAttribute("JDOM") == null) {
            oParent.ShowError(oThis,
                              "Loading old format GroupLog.\nMake sure to save this Group!");
            SaxParserGroup.loadUp(oParent, sFileName, true);
          }
          else {
            oParent.gplGroupLog = TableGroupLog.xmlGetSavedFromDoc(oParent, doc);

          }
          oParent.ShowStatusProgressDone(dlg);
        }
        catch (OutOfMemoryError err) {
          oParent.ShowError(oThis, "Out Of Memory Error");
          // close this or it'll stay around
          oParent.ShowStatusProgressDone(dlg);
          System.gc();
        }

//
        if (!oParent.fPlayerGroupFrame.isVisible()) {
          oParent.fPlayerGroupFrame.LoadPartyGroupPane(oParent);
          // if this first character loaded, select it so its displayed
          // in the panel
        }
        if (oParent.fPlayerGroupFrame.jPartyPlayerList.getSelectedIndex() < 0) {
          oParent.fPlayerGroupFrame.jPartyPlayerList.setSelectedIndex(0);
        }
        // refresh cause we added someone
        oParent.fBattleSheetFrame.LoadPartyPanel(oParent);

        System.setProperty("lastgroup.dir",
                           jFileChooser1.getCurrentDirectory().getAbsolutePath());
        oParent.gmLog(true, false, "Loaded " + sFile + " group.\n");
      }
    }
    else {
      oParent.gmLog(true, false, "Load cancelled.\n");

    }

  }

  /**
   *
   * @param oParent HackSackFrame
   * @param oComponent Component
   */
  static void AskWhereToSaveGroup(HackSackFrame oParent, Component oComponent) {
    JFileChooser jFileChooser1 = new JFileChooser();
    jFileChooser1.setApproveButtonText("Save");
    jFileChooser1.setDialogTitle("Save Group");
    jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                               File.separatorChar +
                                               oParent.sPlayerGroupDir));

    int returnVal = jFileChooser1.showSaveDialog(oComponent);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
      String sFile = jFileChooser1.getSelectedFile().getName();
//                SaveGroup.toFile(oParent,oParent.lPlayers ,sFileName);
      FrameStartUp dlg = oParent.ShowStatusProgress(oComponent,
          "Saving", "Saving party information.");

      xmlControl.saveDoc(oParent, oParent,
                         TableGroupLog.xmlBuildDocFromVar(oParent.gplGroupLog,
          -1), sFileName);

      oParent.ShowStatusProgressDone(dlg);

      oParent.gmLog(true, false, "Saved group to file " + sFile + ".\n");
    }
    else {
      oParent.gmLog(true, true, "Saved cancelled.\n");
    }
  }

  /**
   * This loads
   *
   * @return JPanel
   */
  JPanel getPartyGroupLogPanel() {
    JPanel jPartyLogDetailsPanel = null;
    Color cbColor = Color.darkGray;
    VerticalFlowLayout vFlowVert = new VerticalFlowLayout();
    FlowLayout vFlowLeft = new FlowLayout(FlowLayout.LEFT);

    Font fFontBold = new Font("Dialog", Font.BOLD, 12);
    Font fFontNormal = new Font("Dialog", Font.PLAIN, 11);
    Font fFontSmall = new Font("Dialog", Font.PLAIN, 9);

    jPartyLogDetailsPanel = new JPanel(vFlowVert);
    // clean it to make sure we're starting over

    JPanel jButtonPanel = new JPanel(vFlowLeft);
    jButtonPanel.setBackground(cbColor);

    bGrantHonor.setFont(fFontNormal);
    bHonor.setFont(fFontNormal);
    bApplyTemporalHonor.setFont(fFontNormal);
    bGrantEXPBonus.setFont(fFontNormal);
    bEXPBonus.setFont(fFontNormal);

    jButtonPanel.add(bGrantHonor);
    jButtonPanel.add(bHonor);
    jButtonPanel.add(bApplyTemporalHonor);
    jButtonPanel.add(bGrantEXPBonus);
    jButtonPanel.add(bEXPBonus);

    jPartyLogDetailsPanel.add(jButtonPanel);

    // -- Monster Kill
    JPanel jMonsterPanel = new JPanel(vFlowVert);
    jMonsterPanel.setBackground(cbColor);

    Border jMonsterPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
        LOWERED, Color.WHITE,
        Color.WHITE,
        Color.WHITE,
        Color.WHITE);
    TitledBorder titlejMonsterPanelBorder = new TitledBorder(
        jMonsterPanelBorder, "Monster Kill Awards");
    titlejMonsterPanelBorder.setTitleColor(Color.WHITE);
    jMonsterPanel.setBorder(titlejMonsterPanelBorder);

/*
    JPanel jMonsterPanel_Creature = new JPanel(vBorderLayout);
    jMonsterPanel_Creature.setBackground(cbColor);
    JLabel jNameLabel2 = new JLabel("Creature Defeated");
    jNameLabel2.setForeground(Color.GREEN);
    jNameLabel2.setFont(fFontBold);
    jMonsterPanel_Creature.add(jNameLabel2,BorderLayout.WEST);

    JPanel jMonsterPanel_EXP = new JPanel(vBorderLayout);
    jMonsterPanel_EXP.setBackground(cbColor);
    JLabel jValueLabel2 = new JLabel("EXP Value");
    jValueLabel2.setForeground(Color.GREEN);
    jValueLabel2.setFont(fFontBold);
    jMonsterPanel_EXP.add(jValueLabel2,BorderLayout.CENTER);

    JPanel jMonsterPanel_Button = new JPanel(vBorderLayout);
    jMonsterPanel_Button.setBackground(cbColor);
    JLabel jButtonLabel2 = new JLabel("Apply Award Now");
    jButtonLabel2.setForeground(Color.GREEN);
    jButtonLabel2.setFont(fFontBold);
    jMonsterPanel_Button.add(jButtonLabel2,BorderLayout.EAST);
*/
    for (int i = 0; i < lMonsters.size(); i++) {
      Monster oE = (Monster) lMonsters.get(i);
      if (!oE.jApplied.isSelected()) {
        JPanel jThisAwardPanel = new JPanel(new BorderLayout());
        jThisAwardPanel.setBackground(cbColor);

        JLabel jName = new JLabel(oE.sName);
        jName.setForeground(Color.GREEN);
        JLabel jValue = new JLabel(" Reward:" + oE.nEXPValue);
        jValue.setForeground(Color.GREEN);

        oE.bApply.setFont(fFontNormal);

        jThisAwardPanel.add(jName,BorderLayout.CENTER);
        jThisAwardPanel.add(jValue,BorderLayout.EAST);
        jThisAwardPanel.add(oE.bApply,BorderLayout.WEST);
        jMonsterPanel.add(jThisAwardPanel);
      }
    }

    jPartyLogDetailsPanel.add(jMonsterPanel);

    // -- exp
    JPanel jEXPPanel = new JPanel(vFlowVert);
    jEXPPanel.setBackground(cbColor);

    Border jThisPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
        LOWERED, Color.WHITE,
        Color.WHITE,
        Color.WHITE,
        Color.WHITE);
    TitledBorder titlejThisPanelBorder = new TitledBorder(jThisPanelBorder,
        "EXP Awards");
    titlejThisPanelBorder.setTitleColor(Color.WHITE);
    jEXPPanel.setBorder(titlejThisPanelBorder);

/*
    JPanel jEXPPanel_Name = new JPanel(vFlowVert);
    jEXPPanel_Name.setBackground(cbColor);
    JLabel jNameLabel = new JLabel("Name");
    jNameLabel.setForeground(Color.GREEN);
    jNameLabel.setFont(fFontBold);
    jEXPPanel_Name.add(jNameLabel);

    JPanel jEXPPanel_EXP = new JPanel(vFlowVert);
    jEXPPanel_EXP.setBackground(cbColor);
    JLabel jValueLabel = new JLabel("EXP Value");
    jValueLabel.setForeground(Color.GREEN);
    jValueLabel.setFont(fFontBold);
    jEXPPanel_EXP.add(jValueLabel);

    JPanel jEXPPanel_Button = new JPanel(vFlowVert);
    jEXPPanel_Button.setBackground(cbColor);
    JLabel jButtonLabel = new JLabel("Apply Award Now");
    jButtonLabel.setForeground(Color.GREEN);
    jButtonLabel.setFont(fFontBold);
    jEXPPanel_Button.add(jButtonLabel);
*/
    for (int i = 0; i < lEXP.size(); i++) {
      EXPAwards oE = (EXPAwards) lEXP.get(i);
      EXPBonus oEB = EXPAwards.FindEXPBonus(oParent.lEXPBonusType, oE.nID);
      if (oEB != null) {
        if (!oE.jApplied.isSelected()) {
          JPanel jThisAwardPanel = new JPanel(new BorderLayout());
          jThisAwardPanel.setBackground(cbColor);

          JLabel jName = new JLabel(oEB.sName);
          jName.setForeground(Color.GREEN);
          JLabel jValue = new JLabel(" Reward:" + oEB.nReward);
          jValue.setForeground(Color.GREEN);

          oE.bApply.setFont(fFontNormal);

          jThisAwardPanel.add(jName,BorderLayout.CENTER);
          jThisAwardPanel.add(jValue,BorderLayout.EAST);
          jThisAwardPanel.add(oE.bApply,BorderLayout.WEST);
          jEXPPanel.add(jThisAwardPanel);
        }
      }
    }
    jPartyLogDetailsPanel.add(jEXPPanel);

    // -- Honor
    JPanel jHonorPanel = new JPanel(vFlowVert);
    jHonorPanel.setBackground(cbColor);

    Border jHonorPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
        LOWERED, Color.WHITE,
        Color.WHITE,
        Color.WHITE,
        Color.WHITE);
    TitledBorder titlejHonorPanelBorder = new TitledBorder(jHonorPanelBorder,
        "Honor Awards");
    titlejHonorPanelBorder.setTitleColor(Color.WHITE);
    jHonorPanel.setBorder(titlejHonorPanelBorder);

/*
    JPanel jHonorPanel_Name = new JPanel(vFlowVert);
    jHonorPanel_Name.setBackground(cbColor);
    JLabel jNameLabel1 = new JLabel("Name");
    jNameLabel1.setForeground(Color.GREEN);
    jNameLabel1.setFont(fFontBold);
    jHonorPanel_Name.add(jNameLabel1);

    JPanel jHonorPanel_Button = new JPanel(vFlowVert);
    jHonorPanel_Button.setBackground(cbColor);
    JLabel jButtonLabel1 = new JLabel("Apply Award Now");
    jButtonLabel1.setForeground(Color.GREEN);
    jButtonLabel1.setFont(fFontBold);
    jHonorPanel_Button.add(jButtonLabel1);
*/
//    oParent.gplGroupLog.groupLog("lHonor Size = "+Integer.toString(oParent.gplGroupLog.lHonor.size())+"\n");
    for (int i = 0; i < lHonor.size(); i++) {
      HonorAward oE = (HonorAward) lHonor.get(i);
      Honor oH = HonorAward.FindHonorAward(oParent.lHonorTypes, oE.nID);
      if (!oE.jApplied.isSelected()) {
        if (oE.nID == -10) {
          oH = new Honor();
          oH.sName = "Defeated worthy opponent.";
          oH.sDesc = "Defeated worthy opponent.";
        }
        else
        if (oE.nID == -20) {
          oH = new Honor();
          oH.sName = oE.sDetails;
          oH.sDesc = oE.sDetails;
        }
        JPanel jThisAwardPanel = new JPanel(new BorderLayout());
        jThisAwardPanel.setBackground(cbColor);

        JLabel jName = new JLabel(oH.sName);
        jName.setForeground(Color.GREEN);

        oE.bApply.setFont(fFontNormal);

        jThisAwardPanel.add(oE.bApply,BorderLayout.WEST);
        jThisAwardPanel.add(jName,BorderLayout.CENTER);
        jHonorPanel.add(jThisAwardPanel);
      }
    }
    jPartyLogDetailsPanel.add(jHonorPanel);

    // timer details
    JPanel jTimerPanel = new JPanel(vFlowVert);
    jTimerPanel.setBackground(cbColor);

    Border jTimerPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
        LOWERED, Color.WHITE,
        Color.WHITE,
        Color.WHITE,
        Color.WHITE);
    TitledBorder titlejTimerPanel = new TitledBorder(jTimerPanelBorder,
        "Timers");
    titlejTimerPanel.setTitleColor(Color.WHITE);
    jTimerPanel.setBorder(titlejTimerPanel);

    for (int i = 0; i < lTimers.size(); i++) {
      Timer oT = (Timer) lTimers.get(i);
      JPanel jTimerPanelDetail = new JPanel(new BorderLayout());
      jTimerPanelDetail.setBackground(cbColor);

      oT.bRemove.setFont(fFontNormal);

      jTimerPanelDetail.add(oT.bRemove,BorderLayout.WEST);

      JLabel jTimerLabel = new JLabel(oT.sDesc + "/" + oT.sSetText);
      jTimerLabel.setFont(fFontBold);
      jTimerLabel.setForeground(Color.GREEN);
      jTimerPanelDetail.add(jTimerLabel,BorderLayout.CENTER);
      jTimerPanel.add(jTimerPanelDetail);
    }
    jPartyLogDetailsPanel.add(jTimerPanel);

    // gm log area
    JScrollPane jScrollPanel = new JScrollPane();
//    jScrollPanel.setPreferredSize(new Dimension(500, 500));
    jScrollPanel.getViewport().add(jLog, null);
    jLog.setLineWrap(true);
    jLog.setWrapStyleWord(true);
    jLog.setBackground(Color.darkGray);
    jLog.setForeground(Color.YELLOW);

    jPartyLogDetailsPanel.add(jScrollPanel, null);
//    jPartyLogDetailsPanel.add(jLog, null);

    return jPartyLogDetailsPanel;
  }

} // end TableGroupLog()

// types of honor awards
class Honor
    implements Comparable {
  String sName = null;
  String sDesc = null;
  JButton bRemove = null;
  int nHonorTypeID = -1;
  JSpinner jAwardSpecific = null;
  boolean bPercentage = false;
  boolean bPerLevel = false;

  int ALIGN_GOOD = 0;
  int ALIGN_NEUTRAL = 1;
  int ALIGN_EVIL = 2;

  int nLawful[] = new int[3];
  int nNeutral[] = new int[3];
  int nChaotic[] = new int[3];

  public int compareTo(Object o) {
    String sThisName = ( (Honor) o).sName.toUpperCase();
    String sComp = sName.toUpperCase();
    return sComp.compareTo(sThisName);
  }

  public Honor() {
    jAwardSpecific = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));

  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("HonorType");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));

    eItem.addContent(new Element("nHonorTypeID").setText("" + nHonorTypeID));

    eItem.addContent(new Element("jAwardSpecific").setText(jAwardSpecific.
        getValue().toString()));
    eItem.addContent(new Element("bPercentage").setText(bPercentage ? "true" :
        "false"));
    eItem.addContent(new Element("bPerLevel").setText(bPerLevel ? "true" :
        "false"));

    Element eLawful = new Element("nLawful");
    eLawful.addContent(new Element("ALIGN_GOOD").setText("" +
        nLawful[ALIGN_GOOD]));
    eLawful.addContent(new Element("ALIGN_NEUTRAL").setText("" +
        nLawful[ALIGN_NEUTRAL]));
    eLawful.addContent(new Element("ALIGN_EVIL").setText("" +
        nLawful[ALIGN_EVIL]));
    eItem.addContent(eLawful);

    Element eChaotic = new Element("nChaotic");
    eChaotic.addContent(new Element("ALIGN_GOOD").setText("" +
        nChaotic[ALIGN_GOOD]));
    eChaotic.addContent(new Element("ALIGN_NEUTRAL").setText("" +
        nChaotic[ALIGN_NEUTRAL]));
    eChaotic.addContent(new Element("ALIGN_EVIL").setText("" +
        nChaotic[ALIGN_EVIL]));
    eItem.addContent(eChaotic);

    Element eNeutral = new Element("nNeutral");
    eNeutral.addContent(new Element("ALIGN_GOOD").setText("" +
        nNeutral[ALIGN_GOOD]));
    eNeutral.addContent(new Element("ALIGN_NEUTRAL").setText("" +
        nNeutral[ALIGN_NEUTRAL]));
    eNeutral.addContent(new Element("ALIGN_EVIL").setText("" +
        nNeutral[ALIGN_EVIL]));
    eItem.addContent(eNeutral);

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static Honor xmlGetFromElements(Element eItem) {
    Honor oO = new Honor();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());

    oO.nHonorTypeID = Integer.parseInt(eItem.getChild("nHonorTypeID").getText());
    oO.jAwardSpecific.setValue(new Integer(Integer.parseInt(eItem.getChild(
        "jAwardSpecific").getText())));
    oO.bPercentage = eItem.getChild("bPercentage").getText().equalsIgnoreCase(
        "true");
    oO.bPerLevel = eItem.getChild("bPerLevel").getText().equalsIgnoreCase(
        "true");

    Element eLawful = eItem.getChild("nLawful");
    oO.nLawful[oO.ALIGN_GOOD] = Integer.parseInt(eLawful.getChild("ALIGN_GOOD").
                                                 getText());
    oO.nLawful[oO.ALIGN_NEUTRAL] = Integer.parseInt(eLawful.getChild(
        "ALIGN_NEUTRAL").getText());
    oO.nLawful[oO.ALIGN_EVIL] = Integer.parseInt(eLawful.getChild("ALIGN_EVIL").
                                                 getText());

    Element eNeutral = eItem.getChild("nNeutral");
    oO.nNeutral[oO.ALIGN_GOOD] = Integer.parseInt(eNeutral.getChild(
        "ALIGN_GOOD").getText());
    oO.nNeutral[oO.ALIGN_NEUTRAL] = Integer.parseInt(eNeutral.getChild(
        "ALIGN_NEUTRAL").getText());
    oO.nNeutral[oO.ALIGN_EVIL] = Integer.parseInt(eNeutral.getChild(
        "ALIGN_EVIL").getText());

    Element eChaotic = eItem.getChild("nChaotic");
    oO.nChaotic[oO.ALIGN_GOOD] = Integer.parseInt(eChaotic.getChild(
        "ALIGN_GOOD").getText());
    oO.nChaotic[oO.ALIGN_NEUTRAL] = Integer.parseInt(eChaotic.getChild(
        "ALIGN_NEUTRAL").getText());
    oO.nChaotic[oO.ALIGN_EVIL] = Integer.parseInt(eChaotic.getChild(
        "ALIGN_EVIL").getText());

    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved to
   * an xml file.
   *
   * @param lList ArrayList
   * @param nMaxID int
   * @return Document
   */
  static Document xmlBuildDocFromList(ArrayList lList, int nMaxID) {
    Element eRoot = new Element("HonorTypeList");
    eRoot.addContent(new Element("nMaxID").setText("" + nMaxID));
    Document doc = new Document(eRoot);

    for (int i = 0; i < lList.size(); i++) {

      Honor oO = (Honor) lList.get(i);
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
      oParent.nMaxCreatureID = Integer.parseInt(eRoot.getChild("nMaxID").
                                                getText());

      java.util.List lItems = eRoot.getChildren("HonorType");

      Iterator in = lItems.iterator();
      while (in.hasNext()) {
        Element eItem = (Element) in.next();
        Honor oO = Honor.xmlGetFromElements(eItem);

        if (oO.nHonorTypeID > oParent.nMaxHonorTypeID) {
          oParent.nMaxHonorTypeID = (oO.nHonorTypeID + 1);

        }
        lList.add(oO);
      }

    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load HonorType from XML.");
    }

    return lList;
  }

}

// types of EXP bonuses (MVP/Role played well)
class EXPBonus
    implements Comparable {
  String sName = null;
  String sDesc = null;
  JButton bRemove = null;
  int nEXPBonusID = -1;
  int nReward = 0;
  boolean bLevelMultiplier = false;
  boolean bPartyLevelMultiplier = false;
  boolean bSplitReward = false;
  int nMultiplier = 0;

  public int compareTo(Object o) {
    String sThisName = ( (EXPBonus) o).sName.toUpperCase();
    String sComp = sName.toUpperCase();
    return sComp.compareTo(sThisName);
  }

  public EXPBonus() {

  }

  /* 	LOAD
          lClass = TableClass.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sClassSaveFile));

          SAVE
   xmlControl.saveDoc(oParent,oParent,TableSavedCreatures.xmlBuildDocFromList(
                oParent.lSavedCreatures,oParent.nMaxCreatureID),oParent.sCreatureSaveFileName+".NEW");

   */


  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("EXPBonusType");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eItem.addContent(new Element("nEXPBonusID").setText("" + nEXPBonusID));
    eItem.addContent(new Element("bLevelMultiplier").setText(bLevelMultiplier ?
        "true" : "false"));
    eItem.addContent(new Element("bPartyLevelMultiplier").setText(
        bPartyLevelMultiplier ? "true" : "false"));
    eItem.addContent(new Element("bSplitReward").setText(bSplitReward ? "true" :
        "false"));
    eItem.addContent(new Element("nMultiplier").setText("" + nMultiplier));
    eItem.addContent(new Element("nReward").setText("" + nReward));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static EXPBonus xmlGetFromElements(Element eItem) {
    EXPBonus oO = new EXPBonus();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());

    oO.nEXPBonusID = Integer.parseInt(eItem.getChild("nEXPBonusID").getText());
    oO.nMultiplier = Integer.parseInt(eItem.getChild("nMultiplier").getText());
    oO.nReward = Integer.parseInt(eItem.getChild("nReward").getText());

    oO.bLevelMultiplier = eItem.getChild("bLevelMultiplier").getText().
        equalsIgnoreCase("true");
    oO.bPartyLevelMultiplier = eItem.getChild("bPartyLevelMultiplier").getText().
        equalsIgnoreCase("true");
    oO.bSplitReward = eItem.getChild("bSplitReward").getText().equalsIgnoreCase(
        "true");

    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved to
   * an xml file.
   *
   * @param lList ArrayList
   * @param nMaxID int
   * @return Document
   */
  static Document xmlBuildDocFromList(ArrayList lList, int nMaxID) {
    Element eRoot = new Element("EXPBonusTypeList");
    eRoot.addContent(new Element("nMaxID").setText("" + nMaxID));
    Document doc = new Document(eRoot);

    for (int i = 0; i < lList.size(); i++) {

      EXPBonus oO = (EXPBonus) lList.get(i);
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
      oParent.nMaxEXPBonusTypeID = Integer.parseInt(eRoot.getChild("nMaxID").
          getText());

      java.util.List lItems = eRoot.getChildren("EXPBonusType");

      Iterator in = lItems.iterator();
      while (in.hasNext()) {
        Element eItem = (Element) in.next();
        EXPBonus oO = EXPBonus.xmlGetFromElements(eItem);

        if (oO.nEXPBonusID > oParent.nMaxEXPBonusTypeID) {
          oParent.nMaxEXPBonusTypeID = (oO.nEXPBonusID + 1);

        }
        lList.add(oO);
      }

    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load EXBonusType from XML.");
    }

    return lList;
  }

}

// plain EXP, given out for whatever reason
class EXP {
  String sName = null;
  String sDesc = null;
  JButton bRemove = null;

}

// EXPAwards used for giving out EXP (role played well, MVP/etc)
/**
 * <p>Title: HackSACK</p>
 *
 * <p>Description: HackMaster GM Tool</p>
 *
 * <p>Copyright: Copyright (c) 2003,2004</p>
 *
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */
class EXPAwards {
  int nID = -1;
  String sDetails = null;
  long lIndividual = -1;

  JCheckBox jApplied = null;
  JButton bApply = null;
  JButton bDetails = null;

  public EXPAwards(HackSackFrame oParent) {
    jApplied = new JCheckBox();
    bDetails = new JButton();
    bApply = new JButton();
    bApply.addActionListener(new jEXPButtonListener(oParent, "bApply", this));
    bApply.setText("Apply");
    bApply.setToolTipText("Apply this EXP Award.");
    bDetails.addActionListener(new jEXPButtonListener(oParent, "bDetails", this));
  }

  void updateButtonPressed(HackSackFrame oParent, String sThisButtonName,
                           EXPAwards oE) {
    if (sThisButtonName.equalsIgnoreCase("bApply")) {
      DialogEXPAwards dlg = new DialogEXPAwards(oParent, oE);
//      dlg.panel1.setPreferredSize(new Dimension(408,310));

      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = oParent.fPlayerGroupFrame.getSize();
      Point loc = oParent.fPlayerGroupFrame.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);

      dlg.setTitle("EXP Awards");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);

    }
    else
    if (sThisButtonName.equalsIgnoreCase("bDetails")) {

    }

  }

  static EXPBonus FindEXPBonus(ArrayList AwardsList, int nID) {
    for (int i = 0; i < AwardsList.size(); i++) {
      EXPBonus oE = (EXPBonus) AwardsList.get(i);
      if (oE.nEXPBonusID == nID) {
        return oE;
      }
    }

    return null;
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Award");

    eItem.addContent(new Element("sDetails").setText(xmlControl.escapeChars(
        sDetails)));
    eItem.addContent(new Element("nID").setText("" + nID));
    eItem.addContent(new Element("lIndividual").setText("" + lIndividual));
    eItem.addContent(new Element("jApplied").setText(jApplied.isSelected() ?
        "true" : "false"));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static EXPAwards xmlGetFromElements(Element eItem,
                                      HackSackFrame oParent) {
    EXPAwards oO = new EXPAwards(oParent);

    oO.sDetails = xmlControl.unEscapeChars(eItem.getChild("sDetails").getText());
    oO.nID = Integer.parseInt(eItem.getChild("nID").getText());
    oO.lIndividual = Long.parseLong(eItem.getChild("lIndividual").getText());
    oO.jApplied.setSelected(eItem.getChild("jApplied").getText().
                            equalsIgnoreCase("true"));

    return oO;
  }

}

// will be used to apply honor awards
class HonorAward {
  int nID = -1;
  String sDetails = null;
  long lIndividual = -1;
  int nSpecificAward = 0;

//  ArrayList lWinners = null;

  JCheckBox jApplied = null;
  JButton bApply = null;
  JButton bDetails = null;

  public HonorAward(HackSackFrame oParent) {
//    lWinners = new ArrayList();

    jApplied = new JCheckBox();
    bDetails = new JButton();
    bApply = new JButton();
    bApply.addActionListener(new jHonorButtonListener(oParent, "bApply", this));
    bApply.setText("Apply");
    bApply.setToolTipText("Apply this temporal honor award now.");
    bDetails.addActionListener(new jHonorButtonListener(oParent, "bDetails", this));
    bDetails.setText("Details");

  }

  void updateButtonPressed(HackSackFrame oParent, String sThisButtonName,
                           HonorAward oE) {
    if (sThisButtonName.equalsIgnoreCase("bApply")) {
      DialogHonorAwards dlg = new DialogHonorAwards(oParent, oE);
//      dlg.panel1.setPreferredSize(new Dimension(408,310));
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = oParent.fPlayerGroupFrame.getSize();
      Point loc = oParent.fPlayerGroupFrame.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Honor Awards");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);

    }
    else
    if (sThisButtonName.equalsIgnoreCase("bDetails")) {

    }

  }

  static Honor FindHonorAward(ArrayList HonorAwardsList, int nID) {
    for (int i = 0; i < HonorAwardsList.size(); i++) {
      Honor oHonorType = (Honor) HonorAwardsList.get(i);
      if (oHonorType.nHonorTypeID == nID) {
        return oHonorType;
      }
    }

    return null;
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Award");

    eItem.addContent(new Element("sDetails").setText(xmlControl.escapeChars(
        sDetails)));
    eItem.addContent(new Element("nID").setText("" + nID));
    eItem.addContent(new Element("lIndividual").setText("" + lIndividual));
    eItem.addContent(new Element("jApplied").setText(jApplied.isSelected() ?
        "true" : "false"));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static HonorAward xmlGetFromElements(Element eItem,
                                       HackSackFrame oParent) {
    HonorAward oO = new HonorAward(oParent);

    oO.sDetails = xmlControl.unEscapeChars(eItem.getChild("sDetails").getText());
    oO.nID = Integer.parseInt(eItem.getChild("nID").getText());
    oO.lIndividual = Long.parseLong(eItem.getChild("lIndividual").getText());
    oO.jApplied.setSelected(eItem.getChild("jApplied").getText().
                            equalsIgnoreCase("true"));

    return oO;
  }

}

// apply EXP awards from defeating a creature
class Monster {
  String sName = null;
  String sDesc = null;
  JCheckBox jApplied = null;
  JButton bApply = null;
  JButton bDetails = null;
  int nEXPValue = 0;
  int nHonorValue = 0;

  public Monster(HackSackFrame oParent) {
    jApplied = new JCheckBox();
    bApply = new JButton();
    bDetails = new JButton();
    bApply.addActionListener(new jMonsterButtonListener(oParent, "bApply", this));
    bApply.setText("Apply");
    bDetails.addActionListener(new jMonsterButtonListener(oParent, "bDetails", this));
    bDetails.setText("Details");

  }

  void updateButtonPressed(HackSackFrame oParent, String sThisButtonName,
                           Monster oE) {
    if (sThisButtonName.equalsIgnoreCase("bApply")) {
      DialogMonsterAwards dlg = new DialogMonsterAwards(oParent, oE);
      dlg.panel1.setPreferredSize(new Dimension(408, 310));
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = oParent.fPlayerGroupFrame.getSize();
      Point loc = oParent.fPlayerGroupFrame.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle(oE.sName + " EXP Awards");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);

    }
    else
    if (sThisButtonName.equalsIgnoreCase("bDetails")) {

    }
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Award");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eItem.addContent(new Element("nEXPValue").setText("" + nEXPValue));
    eItem.addContent(new Element("nHonorValue").setText("" + nHonorValue));
    eItem.addContent(new Element("jApplied").setText(jApplied.isSelected() ?
        "true" : "false"));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static Monster xmlGetFromElements(Element eItem, HackSackFrame oParent) {
    Monster oO = new Monster(oParent);

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
    oO.nEXPValue = Integer.parseInt(eItem.getChild("nEXPValue").getText());
    oO.nHonorValue = Integer.parseInt(eItem.getChild("nHonorValue").getText());
    oO.jApplied.setSelected(eItem.getChild("jApplied").getText().
                            equalsIgnoreCase("true"));

    return oO;
  }

}

class Timer {
  TableGroupLog oGroup = null;
  long lTimer = -1;
  String sDesc = null;
  String sSetText = null;
  JButton bRemove = null;

//  public Timer() {

//  }

  public Timer(TableGroupLog oGroup) {
    this.oGroup = oGroup;

    bRemove = new JButton();
    bRemove.addActionListener(new jTimerButtonListener("bRemove", oGroup, this));
    bRemove.setText("Cancel");
    bRemove.setToolTipText("Cancel this timer.");

  }

  void updateButtonPressed(String sButtonName, TableGroupLog oGroup, Timer oT) {
    if (sButtonName.equalsIgnoreCase("bRemove")) { // cancel timer
      if (oGroup.oParent.AskYN(oGroup.oParent.fPlayerGroupFrame,
                               "Are you sure you want to remove this timer? (" +
                               oT.sDesc + "/" + oT.sSetText + ")?")) {
        oGroup.lTimers.remove(oT);
//       oGroup.oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
        // refresh grouplog
        oGroup.oParent.fPlayerGroupFrame.AddPartyGroupLog();

        oGroup.oParent.ShowDone(oGroup.oParent.fPlayerGroupFrame,
                                "Cancelled timer for " + oT.sDesc);
        oGroup.groupLog("Cancelled timer for " + oT.sDesc + "/" + oT.sSetText +
                        "\n");
      }

    }

  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Timer");

    eItem.addContent(new Element("sSetText").setText(xmlControl.escapeChars(
        sSetText)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eItem.addContent(new Element("lTimer").setText("" + lTimer));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oGroup TableGroupLog
   * @return TableClass
   */
  static Timer xmlGetFromElements(Element eItem, TableGroupLog oGroup) {
    Timer oO = new Timer(oGroup);

    oO.sSetText = xmlControl.unEscapeChars(eItem.getChild("sSetText").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
    oO.lTimer = Long.parseLong(eItem.getChild("lTimer").getText());
    return oO;
  }

}

//----------------------- Button Listener for EXP Awards
class jEXPButtonListener
    implements ActionListener {
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private EXPAwards oE = null;

  public jEXPButtonListener(HackSackFrame oParent, String sButtonName,
                            EXPAwards oE) {
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oE = oE;

  }

  public void actionPerformed(ActionEvent e) {
    oE.updateButtonPressed(oParent, sThisButtonName, oE);
  }
}

//----------------------- Button Listener for Honor Awards
class jHonorButtonListener
    implements ActionListener {
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private HonorAward oE = null;

  public jHonorButtonListener(HackSackFrame oParent, String sButtonName,
                              HonorAward oE) {
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oE = oE;

  }

  public void actionPerformed(ActionEvent e) {
    oE.updateButtonPressed(oParent, sThisButtonName, oE);
  }
}

//----------------------- Button Listener for Monster Kill
class jMonsterButtonListener
    implements ActionListener {
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private Monster oE = null;

  public jMonsterButtonListener(HackSackFrame oParent, String sButtonName,
                                Monster oE) {
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oE = oE;

  }

  public void actionPerformed(ActionEvent e) {
    oE.updateButtonPressed(oParent, sThisButtonName, oE);
  }
}

//----------------------- Button Listener GMLog
class jLogButtonListener
    implements ActionListener {
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private TableGroupLog oMe = null;

  public jLogButtonListener(HackSackFrame oParent, String sButtonName,
                            TableGroupLog oMe) {
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oMe = oMe;

  }

  public void actionPerformed(ActionEvent e) {
    oMe.updateButtonPressed(oParent, sThisButtonName, oMe);
  }
}

//----------------------- Button Listener Timers
class jTimerButtonListener
    implements ActionListener {
  private String sThisButtonName = null;
  private TableGroupLog oGroup = null;
  private Timer oT = null;

  public jTimerButtonListener(String sButtonName, TableGroupLog oGroup,
                              Timer oT) {
    this.sThisButtonName = sButtonName;
    this.oGroup = oGroup;
    this.oT = oT;
  }

  public void actionPerformed(ActionEvent e) {
    oT.updateButtonPressed(sThisButtonName, oGroup, oT);
  }
}

//-------------group spinner listener
class jGroupSpinnerListener
    implements javax.swing.event.ChangeListener {
  private TableGroupLog oGroup = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;

  public jGroupSpinnerListener(HackSackFrame oParent, TableGroupLog oGroup,
                               String sButtonName) {
    this.oGroup = oGroup;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;

  }

  public void stateChanged(ChangeEvent e) {
    oGroup.updateSpinner(oParent, oGroup, sThisButtonName, e);
  }
}
