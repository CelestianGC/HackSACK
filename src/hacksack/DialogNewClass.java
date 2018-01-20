package hacksack;

import java.beans.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;



/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNewClass extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;
  TableClass oThisClass = null;

  int nClassID = -1;
  JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JButton jAddButton = new JButton();
  JButton jCancelButton = new JButton();
  Border border1;
  TitledBorder titledBorder1;
  JComboBox jClassComboBox = new JComboBox();

  JLabel jLabel3 = new JLabel();
  JTextField jNewClassTextField = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JLabel jLabel4 = new JLabel();
  JComboBox jFightAsComboBox1 = new JComboBox();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JComboBox jSaveAsComboBox2 = new JComboBox();
  JButton jSaveClassButton = new JButton();
  JLabel jClassIDLabel = new JLabel();
  JPanel jPanel1 = new JPanel();
  JButton jRemoveButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();

  public DialogNewClass(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewClass(HackSackFrame oParent,
                        TablePlayer oPlayer, TableClass oClass) {
    this(null, "", false);
    this.oPlayer = oPlayer;
    this.oParent = oParent;
    this.oThisClass = oClass;

    jSaveAsComboBox2.removeAllItems();
    jFightAsComboBox1.removeAllItems();
    for (int i=0;i<oParent.gmClassTable.length;i++) {
      jSaveAsComboBox2.addItem(oParent.gmClassTable[i]);
      jFightAsComboBox1.addItem(oParent.gmClassTable[i]);
    }

    jClassComboBox.removeAllItems();
    Collections.sort(oParent.lClass);
    for (int i = 0; i < oParent.lClass.size(); i++) {
      TableClass oO = (TableClass) oParent.lClass.get(i);
      jClassComboBox.addItem(oO.sName);
    }
    if (oThisClass != null)
      jClassComboBox.setSelectedIndex(oParent.lClass.indexOf(oClass));

    if (oPlayer == null)
      jAddButton.setEnabled(false);

  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"Class");
    panel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("Class name.");
    jLabel1.setText("Class");
    jLabel1.setBounds(new Rectangle(15, 38, 55, 15));

    jAddButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jAddButton.setToolTipText("Add this class to the player.");
    jAddButton.setText("Add To Player");
    jAddButton.addActionListener(new DialogNewClass_jAddButton_actionAdapter(this));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Done.");
    jCancelButton.setText("Done");
    jCancelButton.addActionListener(new DialogNewClass_jCancelButton_actionAdapter(this));
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(titledBorder1);
    jClassComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jClassComboBox.setBounds(new Rectangle(83, 38, 202, 21));
    jClassComboBox.addActionListener(new DialogNewClass_jClassComboBox_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel3.setText("New Class");
    jLabel3.setBounds(new Rectangle(15, 73, 55, 15));
    jNewClassTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jNewClassTextField.setText("");
    jNewClassTextField.setBounds(new Rectangle(83, 69, 297, 21));
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 9));
    jScrollPane1.setBounds(new Rectangle(83, 100, 297, 80));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextArea.setText("");
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel4.setText("Description");
    jLabel4.setBounds(new Rectangle(15, 98, 55, 15));
    jFightAsComboBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jFightAsComboBox1.setBounds(new Rectangle(131, 186, 98, 21));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setToolTipText("This character uses the combat matrix for what class? (Fighter, Thief, " +
    "Magic-User, Cleric)");
    jLabel5.setText("Fight As");
    jLabel5.setBounds(new Rectangle(84, 190, 53, 15));
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel7.setToolTipText("This character uses the saving throw matrix for what class? (Fighter, " +
    "Thief, Magic-User, Cleric)");
    jLabel7.setText("Save As");
    jLabel7.setBounds(new Rectangle(237, 188, 53, 15));
    jSaveAsComboBox2.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveAsComboBox2.setBounds(new Rectangle(284, 188, 98, 21));
    jSaveClassButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveClassButton.setToolTipText("Save this class to use for other players.");
    jSaveClassButton.setText("Save Class");
    jSaveClassButton.addActionListener(new DialogNewClass_jSaveClassButton_actionAdapter(this));
    jClassIDLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jClassIDLabel.setForeground(Color.lightGray);
    jClassIDLabel.setText("");
    jClassIDLabel.setBounds(new Rectangle(6, 164, 76, 15));
    jPanel1.setBackground(Color.gray);
    jRemoveButton.setBounds(new Rectangle(307, 38, 73, 21));
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveButton.setToolTipText("Remove this class.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new DialogNewClass_jRemoveButton_actionAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jLabel4, null);
    panel1.add(jScrollPane1, null);
    jPanel1.add(jCancelButton, null);
    jPanel1.add(jSaveClassButton, null);
    jPanel1.add(jAddButton, null);
    panel1.add(jClassIDLabel, null);
    panel1.add(jNewClassTextField, null);
    panel1.add(jLabel3, null);
    panel1.add(jClassComboBox, null);
    panel1.add(jLabel1, null);
    panel1.add(jFightAsComboBox1, null);
    panel1.add(jLabel5, null);
    panel1.add(jSaveAsComboBox2, null);
    panel1.add(jLabel7, null);
    panel1.add(jRemoveButton, null);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(jDescTextArea, null);
  }

  void jAddButton_actionPerformed(ActionEvent e) {
    Class oClass = new Class(oParent,oPlayer);

    oClass.jEXP.getModel().setValue(new Integer(0));
    oClass.jLevel.getModel().setValue(new Integer(0));

    oClass.nClassindex = jClassComboBox.getSelectedIndex();
    oClass.nFightAs = jFightAsComboBox1.getSelectedIndex();
    oClass.nSaveAs = jSaveAsComboBox2.getSelectedIndex();
    oClass.nClassindex = Integer.parseInt(jClassIDLabel.getText());
    oClass.sName = jClassComboBox.getSelectedItem().toString();
    oClass.sDesc = jDescTextArea.getText();
    oClass.nClassID = nClassID;
    oPlayer.aClass.add(oClass);
//    if (oPlayer.equals(oParent.gplPlayer))
//      TablePlayer.FillPlayerPanel(oParent,oPlayer);
//    else {
//..      int nThisSpot = oParent.fPlayerGroupFrame.jPLGroupTabbedPane.getSelectedIndex();
      oParent.fPlayerGroupFrame.jPartyPlayerList_valueChanged(null);
//    }
      this.setVisible(false);
//    this.hide();
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jSpinner1_propertyChange(PropertyChangeEvent e) {

  }

  void jSaveClassButton_actionPerformed(ActionEvent e) {
    if (jNewClassTextField.getText().length() > 0) {
      TableClass oNew = new TableClass();
      oNew.sName = jNewClassTextField.getText();
      oNew.sDesc = jDescTextArea.getText();
      oNew.nFightAs = jFightAsComboBox1.getSelectedIndex();
      oNew.nSaveAs = jSaveAsComboBox2.getSelectedIndex();
      oNew.nClassID = (oParent.nMaxClassID++);
      oParent.lClass.add(oNew);
    } else {
      TableClass oCC = (TableClass) oParent.lClass.get(jClassComboBox.
          getSelectedIndex());
      if (oParent.AskYN(this,
                        "Are you sure you want to replace the class " +
                        oCC.sName + "?")) {
        oCC.sDesc = jDescTextArea.getText();
        oCC.nFightAs = jFightAsComboBox1.getSelectedIndex();
        oCC.nSaveAs = jSaveAsComboBox2.getSelectedIndex();
      }
    }
    // sort all this out with new names/etc
//    SaveClass.toFile(oParent, oParent.sClassSaveFile, oParent.lClass);
    xmlControl.saveDoc(oParent,oParent,TableClass.xmlBuildDocFromList(
      oParent.lClass,oParent.nMaxClassID),oParent.sClassSaveFile);

    jClassComboBox.removeAllItems();
    Collections.sort(oParent.lClass);
    for (int i = 0; i < oParent.lClass.size(); i++) {
      TableClass oO = (TableClass) oParent.lClass.get(i);
      jClassComboBox.addItem(oO.sName);
    }

  }

  void jClassComboBox_actionPerformed(ActionEvent e) {
    if (jClassComboBox.getSelectedIndex() >= 0) {
      TableClass oO = (TableClass) oParent.lClass.get(jClassComboBox.
          getSelectedIndex());
      jNewClassTextField.setText("");
      jDescTextArea.setText(oO.sDesc);
      jFightAsComboBox1.setSelectedIndex(oO.nFightAs);
      jSaveAsComboBox2.setSelectedIndex(oO.nSaveAs);
      nClassID = oO.nClassID;
      jClassIDLabel.setText("" + nClassID);
    }
  }

  void jRemoveButton_actionPerformed(ActionEvent e) {
    TableClass oClass = (TableClass)oParent.lClass.get(jClassComboBox.getSelectedIndex());
    if (oParent.AskYN(this,"Are you sure you want to remove the class "+oClass.sName+"?")){
      oParent.lClass.remove(oClass);

      jClassComboBox.removeAllItems();
      Collections.sort(oParent.lClass);
      for (int i = 0; i < oParent.lClass.size(); i++) {
        TableClass oO = (TableClass) oParent.lClass.get(i);
        jClassComboBox.addItem(oO.sName);
      }
      xmlControl.saveDoc(oParent,oParent,TableClass.xmlBuildDocFromList(
        oParent.lClass,oParent.nMaxClassID),oParent.sClassSaveFile);
      oParent.ShowDone(this,"Removed class.");
    }

  }



}

class DialogNewClass_jAddButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewClass adaptee;

  DialogNewClass_jAddButton_actionAdapter(DialogNewClass adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jAddButton_actionPerformed(e);
  }
}

class DialogNewClass_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewClass adaptee;

  DialogNewClass_jCancelButton_actionAdapter(DialogNewClass adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}

class DialogNewClass_jSaveClassButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewClass adaptee;

  DialogNewClass_jSaveClassButton_actionAdapter(DialogNewClass adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveClassButton_actionPerformed(e);
  }
}

class DialogNewClass_jClassComboBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewClass adaptee;

  DialogNewClass_jClassComboBox_actionAdapter(DialogNewClass adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jClassComboBox_actionPerformed(e);
  }
}

class DialogNewClass_jRemoveButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewClass adaptee;

  DialogNewClass_jRemoveButton_actionAdapter(DialogNewClass adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

