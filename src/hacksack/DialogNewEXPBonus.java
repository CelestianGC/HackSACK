package hacksack;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * This is where the GM will create new
 * EXP Bonus options such as MVP, role playing awards
 * and largest hit made/etc...
 *
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNewEXPBonus extends JDialog {
  HackSackFrame oParent = null;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jNameTextField = new JTextField();
  JLabel jLabel2 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JCheckBox jPCLevelCheckBox = new JCheckBox();
  JCheckBox jGroupLevelCheckBox = new JCheckBox();
  JLabel jLabel3 = new JLabel();
  JSpinner jManualSetSpinner = new JSpinner();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JSpinner jEXPRewardSpinner = new JSpinner(new SpinnerNumberModel(1,1,1000000,1));
  JButton jCancelButton = new JButton();
  JButton jSaveButton = new JButton();
  JPanel jPanel2 = new JPanel();
  JButton jButton1 = new JButton();
  JComboBox jBonusComboBox = new JComboBox();
  JButton jRemoveButton = new JButton();
  JCheckBox jSplitRewardCheckBox = new JCheckBox();
  BorderLayout borderLayout2 = new BorderLayout();

  public DialogNewEXPBonus(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
  //    pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewEXPBonus(HackSackFrame oParent) {
    this(null, "", false);
    this.oParent = oParent;

    ResetToDefaultValues();

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"New EXP Bonus Type");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.lightGray);
    panel1.setFont(new java.awt.Font("Dialog", 0, 9));
    panel1.setBorder(titledBorder1);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jPanel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("Name of the new EXP Bonus type.");
    jLabel1.setText("Name         ");
    jLabel1.setBounds(new Rectangle(4, 33, 54, 15));
    jNameTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jNameTextField.setPreferredSize(new Dimension(350, 21));
    jNameTextField.setText("");
    jNameTextField.setBounds(new Rectangle(63, 29, 350, 21));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("Description of the new EXP Bonus type.");
    jLabel2.setText("Description");
    jLabel2.setBounds(new Rectangle(4, 98, 54, 15));
    jScrollPane1.setPreferredSize(new Dimension(350, 100));
    jScrollPane1.setBounds(new Rectangle(63, 56, 350, 100));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextArea.setText("");
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);

    jPCLevelCheckBox.setBackground(Color.lightGray);
    jPCLevelCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jPCLevelCheckBox.setToolTipText("This EXP bonus multiplies the characters level against the reward.");
    jPCLevelCheckBox.setText("Level Multiplier");
    jPCLevelCheckBox.setBounds(new Rectangle(98, 190, 95, 23));
    jPCLevelCheckBox.addActionListener(new DialogNewEXPBonus_jPCLevelCheckBox_actionAdapter(this));
    jGroupLevelCheckBox.setBackground(Color.lightGray);
    jGroupLevelCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGroupLevelCheckBox.setToolTipText("This EXP Bonus multiplies the groups average level against the reward.");
    jGroupLevelCheckBox.setText("Group Level Multiplier");
    jGroupLevelCheckBox.setBounds(new Rectangle(198, 190, 129, 23));
    jGroupLevelCheckBox.addActionListener(new DialogNewEXPBonus_jGroupLevelCheckBox_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel3.setToolTipText("Manually select the multiplier for this EXP bonus.");
    jLabel3.setText("Set Multiplier           ");
    jLabel3.setBounds(new Rectangle(2, 222, 93, 15));
    jManualSetSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jManualSetSpinner.setPreferredSize(new Dimension(40, 24));
    jManualSetSpinner.setBounds(new Rectangle(102, 218, 69, 24));
    jManualSetSpinner.addChangeListener(new DialogNewEXPBonus_jManualSetSpinner_changeAdapter(this));
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel4.setToolTipText("These are applied to the reward for the total EXP rewarded if selected.");
    jLabel4.setText("Reward Multipliers");
    jLabel4.setBounds(new Rectangle(4, 194, 89, 15));
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel7.setToolTipText("The EXP Bonus reward value.");
    jLabel7.setText("Reward                 ");
    jLabel7.setBounds(new Rectangle(2, 165, 90, 15));
    jEXPRewardSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jEXPRewardSpinner.setPreferredSize(new Dimension(40, 24));
    jEXPRewardSpinner.setBounds(new Rectangle(102, 165, 69, 24));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Close this dialog box.");
    jCancelButton.setText("Done");
    jCancelButton.addActionListener(new DialogNewEXPBonus_jCancelButton_actionAdapter(this));
    jSaveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveButton.setText("Save");
    jSaveButton.addActionListener(new DialogNewEXPBonus_jSaveButton_actionAdapter(this));
    jPanel2.setBackground(Color.gray);
    jButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton1.setText("Reset");
    jButton1.addActionListener(new DialogNewEXPBonus_jButton1_actionAdapter(this));
    jBonusComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jBonusComboBox.setBounds(new Rectangle(63, 2, 241, 21));
    jBonusComboBox.addActionListener(new DialogNewEXPBonus_jBonusComboBox_actionAdapter(this));
    jRemoveButton.setBounds(new Rectangle(340, 2, 73, 21));
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveButton.setToolTipText("Remove this EXP Bonus Type.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new DialogNewEXPBonus_jRemoveButton_actionAdapter(this));
    jSplitRewardCheckBox.setBackground(Color.lightGray);
    jSplitRewardCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jSplitRewardCheckBox.setToolTipText("Split the reward with everyone that is selected.");
    jSplitRewardCheckBox.setText("Split Reward");
    jSplitRewardCheckBox.setBounds(new Rectangle(198, 165, 99, 27));
    this.getContentPane().setLayout(borderLayout2);
    getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel2.add(jCancelButton, null);
    jPanel2.add(jButton1, null);
    jPanel2.add(jSaveButton, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jNameTextField, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jEXPRewardSpinner, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jPCLevelCheckBox, null);
    jPanel1.add(jGroupLevelCheckBox, null);
    jPanel1.add(jManualSetSpinner, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jScrollPane1, null);
    jPanel1.add(jBonusComboBox, null);
    jPanel1.add(jRemoveButton, null);
    jPanel1.add(jSplitRewardCheckBox, null);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    jPanel1.setPreferredSize(new Dimension(430,285));
  }

  void jManualSetSpinner_stateChanged(ChangeEvent e) {
    if (!jManualSetSpinner.getModel().getValue().toString().equals("0"))
    {
      jPCLevelCheckBox.setSelected(false);
      jGroupLevelCheckBox.setSelected(false);
    }
  }

  void jPCLevelCheckBox_actionPerformed(ActionEvent e) {
    jGroupLevelCheckBox.setSelected(false);
    jManualSetSpinner.getModel().setValue(new Integer(0));
  }

  void jGroupLevelCheckBox_actionPerformed(ActionEvent e) {
    jPCLevelCheckBox.setSelected(false);
    jManualSetSpinner.getModel().setValue(new Integer(0));
  }

  void jSaveButton_actionPerformed(ActionEvent e) {
    EXPBonus oE = null;
    boolean bOverWrite = true;
    if (jNameTextField.getText().length() < 1) {
      oE = (EXPBonus) oParent.lEXPBonusType.get(jBonusComboBox.getSelectedIndex());
      bOverWrite = oParent.AskYN(jSaveButton,"Replace "+oE.sName+"?");
    } else {
      oE  = new EXPBonus();
      oE.sName = jNameTextField.getText();
      oE.nEXPBonusID = (oParent.nMaxEXPBonusTypeID++);
      oParent.lEXPBonusType.add(oE);
      Collections.sort(oParent.lEXPBonusType); // resort
    }
    if (bOverWrite) { // if new or replace...
      oE.nMultiplier = Integer.parseInt(jManualSetSpinner.getModel().getValue().
                                        toString());
      oE.bLevelMultiplier = jPCLevelCheckBox.isSelected();
      oE.bPartyLevelMultiplier = jGroupLevelCheckBox.isSelected();
      oE.bSplitReward = jSplitRewardCheckBox.isSelected();
      oE.nReward = Integer.parseInt(jEXPRewardSpinner.getModel().getValue().
                                    toString());
      oE.sDesc = jDescTextArea.getText();

//      SaveEXPBonusTypes.toFile(oParent, oParent.sEXPBonusSaveFile,
//                               oParent.lEXPBonusType);
      xmlControl.saveDoc(oParent,oParent,EXPBonus.xmlBuildDocFromList(
            oParent.lEXPBonusType,oParent.nMaxEXPBonusTypeID),oParent.sEXPBonusSaveFile);
    }
    ResetToDefaultValues();
  }

  void jRemoveButton_actionPerformed(ActionEvent e) {
    EXPBonus oE = (EXPBonus) oParent.lEXPBonusType.get(jBonusComboBox.getSelectedIndex());
    if (oParent.AskYN(this,"Are you sure you want to remove the exp award "+oE.sName+"?")){

      oParent.lEXPBonusType.remove(oE);

      Collections.sort(oParent.lEXPBonusType); // resort
      // save new file
//      SaveEXPBonusTypes.toFile(oParent, oParent.sEXPBonusSaveFile,
//                               oParent.lEXPBonusType);
      xmlControl.saveDoc(oParent,oParent,EXPBonus.xmlBuildDocFromList(
            oParent.lEXPBonusType,oParent.nMaxEXPBonusTypeID),oParent.sEXPBonusSaveFile);

      ResetToDefaultValues();
      oParent.ShowDone(this,"Removed exp award.");
    }

  }

  void jBonusComboBox_actionPerformed(ActionEvent e) {
    if (jBonusComboBox.getSelectedIndex() >= 0) {
      EXPBonus oE = (EXPBonus) oParent.lEXPBonusType.get(jBonusComboBox.
          getSelectedIndex());

//    jNameTextField.setText(oE.sName);
      jDescTextArea.setText(oE.sDesc);
      jEXPRewardSpinner.setValue(new Integer(oE.nReward));
      jPCLevelCheckBox.setSelected(oE.bLevelMultiplier);
      jGroupLevelCheckBox.setSelected(oE.bPartyLevelMultiplier);
      jSplitRewardCheckBox.setSelected(oE.bSplitReward);
      jManualSetSpinner.setValue(new Integer(oE.nMultiplier));
    }

  }

  void jButton1_actionPerformed(ActionEvent e) {
    ResetToDefaultValues();
  }

  void ResetToDefaultValues()
  {
    jBonusComboBox.removeAllItems();
    for (int i=0;i<oParent.lEXPBonusType.size();i++)
    {
      EXPBonus oEb = (EXPBonus)oParent.lEXPBonusType.get(i);
      jBonusComboBox.addItem(oEb.sName);
    }
    jNameTextField.setText("");
    jDescTextArea.setText("");
    jEXPRewardSpinner.setValue(new Integer(1));
    jPCLevelCheckBox.setSelected(false);
    jGroupLevelCheckBox.setSelected(false);
    jManualSetSpinner.setValue(new Integer(1));
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }
}

class DialogNewEXPBonus_jManualSetSpinner_changeAdapter implements javax.swing.event.ChangeListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jManualSetSpinner_changeAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.jManualSetSpinner_stateChanged(e);
  }
}

class DialogNewEXPBonus_jPCLevelCheckBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jPCLevelCheckBox_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPCLevelCheckBox_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jGroupLevelCheckBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jGroupLevelCheckBox_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGroupLevelCheckBox_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jSaveButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jSaveButton_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveButton_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jRemoveButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jRemoveButton_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jBonusComboBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jBonusComboBox_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jBonusComboBox_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jButton1_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jButton1_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class DialogNewEXPBonus_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewEXPBonus adaptee;

  DialogNewEXPBonus_jCancelButton_actionAdapter(DialogNewEXPBonus adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}
