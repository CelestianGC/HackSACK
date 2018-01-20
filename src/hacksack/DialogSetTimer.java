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

public class DialogSetTimer extends JDialog {
  HackSackFrame oParent = null;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jDataPanel = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jCancelButton = new JButton();
  JButton jSetTimerButton = new JButton();
  JComboBox jTypeComboBox = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JTextField jDescTextField = new JTextField();
  JLabel jLabel2 = new JLabel();
  JSpinner jDurationSpinner = new JSpinner(new SpinnerNumberModel(1,1,1000,1));

  public DialogSetTimer(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSetTimer(HackSackFrame oParent) {
    this(null, "", false);
    this.oParent = oParent;

    // fill up the combobox
    jTypeComboBox.removeAllItems();
    for (int i=0;i<oParent.gmTimers.length;i++) {
      jTypeComboBox.addItem(oParent.gmTimers[i]);
    }

  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134)),"Set Timer");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.lightGray);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(borderLayout2);
    jDataPanel.setBackground(Color.gray);
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Cancel current operation.");
    jCancelButton.setText("Cancel");
    jCancelButton.addActionListener(new DialogSetTimer_jCancelButton_actionAdapter(this));
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
    jSetTimerButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSetTimerButton.setToolTipText("Use selected options and set Timer.");
    jSetTimerButton.setText("Set Timer");
    jSetTimerButton.addActionListener(new DialogSetTimer_jSetTimerButton_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("The description of timer. ");
    jLabel1.setText("Description");
    jDescTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextField.setOpaque(true);
    jDescTextField.setPreferredSize(new Dimension(350, 21));
    jDescTextField.setRequestFocusEnabled(true);
    jDescTextField.setToolTipText("");
    jDescTextField.setSelectionEnd(0);
    jDescTextField.setText("");
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("Type of time measurement, year, month, day, turn, round or segment.");
    jLabel2.setText("Type");
    jDataPanel.add(jLabel2, null);
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jDataPanel, BorderLayout.CENTER);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jCancelButton, null);
    jDataPanel.add(jTypeComboBox, null);
    jDataPanel.add(jLabel1, null);
    jDataPanel.add(jDescTextField, null);
    jDataPanel.add(jDurationSpinner, null);
    jDataPanel.add(jSetTimerButton, null);
  }

  void jSetTimerButton_actionPerformed(ActionEvent e) {
    int nType = jTypeComboBox.getSelectedIndex();
    String sDesc = jDescTextField.getText();
    int nDuration = Integer.parseInt(jDurationSpinner.getValue().toString());
    long lSegmentDuration = Integer.parseInt(jDurationSpinner.getValue().toString());
    String sSetText = nDuration+" "+ oParent.gmTimers[nType]+(nDuration>1?"s":"");

    if (oParent.AskYN(this,"Set timer for "+sSetText+" for "+sDesc+" ?"))
    {
      Timer oTimer = new Timer(oParent.gplGroupLog);
      int nSetAt = -1;
      oTimer.sDesc = sDesc;
      oTimer.sSetText = sSetText;

      switch (nType) {
        case 0:
          lSegmentDuration *= oParent.gplGroupLog.A_YEAR;
          break;
        case 1:
          lSegmentDuration *= oParent.gplGroupLog.A_MONTH;
          break;
        case 2:
          lSegmentDuration *= oParent.gplGroupLog.A_DAY;
          break;
        case 3:
          lSegmentDuration *= oParent.gplGroupLog.A_TURN;
          break;
        case 4:
          lSegmentDuration *= oParent.gplGroupLog.A_ROUND;
          break;
        case 5:
          lSegmentDuration = lSegmentDuration;
          break;
      }

      oTimer.lTimer = lSegmentDuration+oParent.gplGroupLog.GetCurrentTimeStamp();
      oParent.gplGroupLog.lTimers.add(oTimer);
//      oParent.ShowDone(this,"Set timer for "+nDuration+" "+
//                      oParent.gmTimers[nType]+(nDuration>1?"s":""));
      oParent.gplGroupLog.groupLog("Set timer for "+sSetText+" for "+sDesc+"\n");
      oParent.fPlayerGroupFrame.AddPartyGroupLog();
//      oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
      this.setVisible(false);
//    this.hide();
    }

  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }
}

class DialogSetTimer_jSetTimerButton_actionAdapter implements java.awt.event.ActionListener {
  DialogSetTimer adaptee;

  DialogSetTimer_jSetTimerButton_actionAdapter(DialogSetTimer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSetTimerButton_actionPerformed(e);
  }
}

class DialogSetTimer_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogSetTimer adaptee;

  DialogSetTimer_jCancelButton_actionAdapter(DialogSetTimer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}
