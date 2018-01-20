package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * this is where you select the exp award to give to a group/player
 *
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogGiveEXPBonus extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;

  JPanel panel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JComboBox jEXPSelectComboBox = new JComboBox();
  JButton jCancelButton = new JButton();
  JButton jAcceptButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JTextField jDetailsTextField = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border2;

  public DialogGiveEXPBonus(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogGiveEXPBonus(HackSackFrame oParent,TablePlayer oPlayer) {
    this(null, "", false);
    this.oParent = oParent;
    this.oPlayer = oPlayer;

    jEXPSelectComboBox.removeAllItems();
    for (int i=0;i<oParent.lEXPBonusType.size();i++)
    {
      EXPBonus oE = (EXPBonus)oParent.lEXPBonusType.get(i);
      jEXPSelectComboBox.addItem(oE.sName);
    }

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Grant EXP Bonus");
    border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(titledBorder1);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("Selected Award type.");
    jLabel1.setText("Select");
    jLabel1.setBounds(new Rectangle(26, 8, 30, 15));
    jEXPSelectComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jEXPSelectComboBox.setPreferredSize(new Dimension(300, 21));
    jEXPSelectComboBox.setBounds(new Rectangle(61, 5, 430, 21));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Abort selection");
    jCancelButton.setText("Cancel");
    jCancelButton.addActionListener(new DialogGiveEXPBonus_jCancelButton_actionAdapter(this));
    jAcceptButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jAcceptButton.setToolTipText("Accept Selected award and add it to the group log.");
    jAcceptButton.setText("Accept");
    jAcceptButton.addActionListener(new DialogGiveEXPBonus_jAcceptButton_actionAdapter(this));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("Notes/Details on this specific award.");
    jLabel2.setText("Details");
    jLabel2.setBounds(new Rectangle(26, 30, 34, 15));
    jDetailsTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jDetailsTextField.setText("");
    jDetailsTextField.setBounds(new Rectangle(61, 30, 430, 21));
    jPanel2.setBackground(Color.gray);
    jPanel2.setBorder(border2);
    getContentPane().add(panel1);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    panel1.add(jPanel2,  BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(jPanel1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jEXPSelectComboBox, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jDetailsTextField, null);
    jPanel2.add(jCancelButton, null);
    jPanel2.add(jAcceptButton, null);
    panel1.setPreferredSize(new Dimension(520,140));
  }

  void jAcceptButton_actionPerformed(ActionEvent e) {
    EXPBonus oE = (EXPBonus)oParent.lEXPBonusType.get(jEXPSelectComboBox.getSelectedIndex());
    EXPAwards oEA = new EXPAwards(oParent);
    oEA.nID = oE.nEXPBonusID;
    oEA.sDetails = jDetailsTextField.getText();
    oEA.jApplied.setSelected(false);

    if (oPlayer != null)
    {
      oEA.lIndividual = oPlayer.lPlayerID;
      oEA.sDetails = "Individual EXP award for "+oPlayer.sCharacter+" "+oEA.sDetails;
    }
    oParent.gplGroupLog.lEXP.add(oEA);
    oParent.gplGroupLog.groupLog("Granted EXP AWARD:"+oE.sName+" to group"+
                                 (oEA.sDetails.equals("")?"":", DETAILS: "+oEA.sDetails)+".\n" );

//    oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
    oParent.fPlayerGroupFrame.AddPartyGroupLog();
    this.setVisible(false);
//    this.hide();
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }
}

class DialogGiveEXPBonus_jAcceptButton_actionAdapter implements java.awt.event.ActionListener {
  DialogGiveEXPBonus adaptee;

  DialogGiveEXPBonus_jAcceptButton_actionAdapter(DialogGiveEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jAcceptButton_actionPerformed(e);
  }
}

class DialogGiveEXPBonus_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogGiveEXPBonus adaptee;

  DialogGiveEXPBonus_jCancelButton_actionAdapter(DialogGiveEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}
