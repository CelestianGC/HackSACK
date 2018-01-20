package hacksack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *
 * this is the where the GM gives honor awards to a group dialog
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogGiveHonorAward extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;
  JPanel panel1 = new JPanel();
  JComboBox jAwardsListComboBox = new JComboBox();
  JButton jGrantAwardButton = new JButton();
  JButton jCloseButton = new JButton();
  JTextField jDetailsTextField = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  Border border1;
  TitledBorder titledBorder1;
  JLabel jLabel3 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  Border border2;

  public DialogGiveHonorAward(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogGiveHonorAward(HackSackFrame oParent, TablePlayer oPlayer) {
    this(null, "", false);
    this.oParent = oParent;
    this.oPlayer = oPlayer;

    jAwardsListComboBox.removeAllItems();
    for (int i=0;i<oParent.lHonorTypes.size();i++)
    {
      Honor oH = (Honor)oParent.lHonorTypes.get(i);
      jAwardsListComboBox.addItem(oH.sName);
    }
    Honor oH = (Honor)oParent.lHonorTypes.get(jAwardsListComboBox.getSelectedIndex());
    jDescTextArea.setText(oH.sDesc);


  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Grant Honor Award");
    border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(103, 101, 98),new Color(148, 145, 140));
    panel1.setLayout(null);
    jGrantAwardButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGrantAwardButton.setToolTipText("Place the award into the que to be awarded at end of session.");
    jGrantAwardButton.setText("Accept");
    jGrantAwardButton.addActionListener(new DialogGiveHonorAward_jGrantAwardButton_actionAdapter(this));
    jAwardsListComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jAwardsListComboBox.setBounds(new Rectangle(57, 19, 422, 21));
    jAwardsListComboBox.addActionListener(new DialogGiveHonorAward_jAwardsListComboBox_actionAdapter(this));
    jCloseButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCloseButton.setToolTipText("Abort granting this award.");
    jCloseButton.setText("Close");
    jCloseButton.addActionListener(new DialogGiveHonorAward_jCloseButton_actionAdapter(this));
    jDetailsTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jDetailsTextField.setText("");
    jDetailsTextField.setBounds(new Rectangle(58, 121, 422, 21));
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setText("Selected");
    jLabel1.setBounds(new Rectangle(6, 19, 50, 15));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("Notes/Reason for award.");
    jLabel2.setText("Reason");
    jLabel2.setBounds(new Rectangle(10, 121, 50, 15));
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(titledBorder1);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel3.setToolTipText("Description of this award.");
    jLabel3.setText("Description");
    jLabel3.setBounds(new Rectangle(8, 54, 54, 15));
    jDescTextArea.setBackground(Color.lightGray);
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextArea.setEditable(false);
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);
    this.getContentPane().setLayout(borderLayout2);
    jPanel2.setBackground(Color.gray);
    jPanel2.setBorder(border2);
    jScrollPane1.setBounds(new Rectangle(57, 56, 422, 58));
    getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jAwardsListComboBox, null);
    panel1.add(jLabel1, null);
    panel1.add(jLabel3, null);
    panel1.add(jDetailsTextField, null);
    panel1.add(jLabel2, null);
    panel1.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    this.getContentPane().add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(jCloseButton, null);
    jPanel2.add(jGrantAwardButton, null);
    panel1.setPreferredSize(new Dimension(520,175));
  }

  void jCloseButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jGrantAwardButton_actionPerformed(ActionEvent e) {
    Honor oH = (Honor)oParent.lHonorTypes.get(jAwardsListComboBox.getSelectedIndex());
    HonorAward oHonorAward = new HonorAward(oParent);
    oHonorAward.nID = oH.nHonorTypeID;
    oHonorAward.sDetails = jDetailsTextField.getText();
    oHonorAward.jApplied.setSelected(false);

    if (oPlayer != null)
    {
      oHonorAward.lIndividual = oPlayer.lPlayerID;
      oHonorAward.sDetails = "Individual honor award for "+oPlayer.sCharacter+" "+oHonorAward.sDetails;
//      oHonorAward.lWinners.add(oPlayer);
    }
    oParent.gplGroupLog.lHonor.add(oHonorAward);
    oParent.gplGroupLog.groupLog("Granted HONOR AWARD:"+oH.sName+" to group"+
                                 (oHonorAward.sDetails.equals("")?"":", DETAILS: "+oHonorAward.sDetails)+".\n" );

//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  void jAwardsListComboBox_actionPerformed(ActionEvent e) {
    Honor oH = (Honor)oParent.lHonorTypes.get(jAwardsListComboBox.getSelectedIndex());
    jDescTextArea.setText(oH.sDesc);
  }
}

class DialogGiveHonorAward_jCloseButton_actionAdapter implements java.awt.event.ActionListener {
  DialogGiveHonorAward adaptee;

  DialogGiveHonorAward_jCloseButton_actionAdapter(DialogGiveHonorAward adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseButton_actionPerformed(e);
  }
}

class DialogGiveHonorAward_jGrantAwardButton_actionAdapter implements java.awt.event.ActionListener {
  DialogGiveHonorAward adaptee;

  DialogGiveHonorAward_jGrantAwardButton_actionAdapter(DialogGiveHonorAward adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGrantAwardButton_actionPerformed(e);
  }
}

class DialogGiveHonorAward_jAwardsListComboBox_actionAdapter implements java.awt.event.ActionListener {
  DialogGiveHonorAward adaptee;

  DialogGiveHonorAward_jAwardsListComboBox_actionAdapter(DialogGiveHonorAward adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jAwardsListComboBox_actionPerformed(e);
  }
}
