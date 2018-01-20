package hacksack;

import java.util.*;

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

public class DialogNewRandomName extends JDialog {
  HackSackFrame oParent = null;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jDialogPanel = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jDataPanel = new JPanel();
  JComboBox jRaceComboBox = new JComboBox();
  JButton jNewRaceButton = new JButton();
  JTextField jNewRaceTextField = new JTextField();
  JButton jNewNameButton = new JButton();
  JTextField jNewNameTextField = new JTextField();
  JCheckBox jLastNameCheckBox = new JCheckBox();
  JPanel jPanel1 = new JPanel();
  JButton jCloseButton = new JButton();
  JButton jDeleteNameButton = new JButton();
  JComboBox jNameComboBox = new JComboBox();
  JComboBox jRaceSelectComboBox = new JComboBox();
  JButton jDeleteRaceButton = new JButton();
  JCheckBox jFemaleCheckBox = new JCheckBox();
  JCheckBox jMaleCheckBox = new JCheckBox();

  public DialogNewRandomName(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewRandomName(HackSackFrame oParent) {
    this(null, "", false);
    this.oParent = oParent;
    RebuildComboBoxes();
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Random Names");
    panel1.setLayout(borderLayout1);
    jDialogPanel.setBackground(Color.lightGray);
    jDialogPanel.setBorder(titledBorder1);
    jDialogPanel.setDebugGraphicsOptions(0);
    jDialogPanel.setLayout(borderLayout2);
    jDataPanel.setBackground(Color.lightGray);
    jDataPanel.setLayout(null);
    jNewRaceButton.setBounds(new Rectangle(8, 7, 95, 21));
    jNewRaceButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jNewRaceButton.setToolTipText("Add this new race with the new name entered.");
    jNewRaceButton.setText("Add Race");
    jNewRaceButton.addActionListener(new DialogNewRandomName_jNewRaceButton_actionAdapter(this));
    jNewRaceTextField.setText("");
    jNewRaceTextField.setBounds(new Rectangle(115, 7, 162, 21));
    jNewNameButton.setBounds(new Rectangle(8, 89, 95, 21));
    jNewNameButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jNewNameButton.setToolTipText("Add this new name to the currently selected race.");
    jNewNameButton.setText("Add Name");
    jNewNameButton.addActionListener(new DialogNewRandomName_jNewNameButton_actionAdapter(this));
    jNewNameTextField.setText("");
    jNewNameTextField.setBounds(new Rectangle(112, 89, 162, 21));
    jRaceComboBox.setToolTipText("Select race for name.");
    jRaceComboBox.setBounds(new Rectangle(114, 48, 162, 21));
    jLastNameCheckBox.setBackground(Color.lightGray);
    jLastNameCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jLastNameCheckBox.setToolTipText("Is this a last name?");
    jLastNameCheckBox.setText("Last Name");
    jLastNameCheckBox.setBounds(new Rectangle(402, 89, 75, 23));
    jCloseButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCloseButton.setText("Done");
    jCloseButton.addActionListener(new DialogNewRandomName_jCloseButton_actionAdapter(this));
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jDeleteNameButton.setBounds(new Rectangle(8, 130, 95, 21));
    jDeleteNameButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jDeleteNameButton.setToolTipText("Remove this name.");
    jDeleteNameButton.setText("Delete Name");
    jDeleteNameButton.addActionListener(new DialogNewRandomName_jDeleteNameButton_actionAdapter(this));
    jNameComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jNameComboBox.setBounds(new Rectangle(286, 130, 162, 21));
    jRaceSelectComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jRaceSelectComboBox.setBounds(new Rectangle(112, 130, 162, 21));
    jRaceSelectComboBox.addActionListener(new DialogNewRandomName_jRaceSelectComboBox_actionAdapter(this));
    jDeleteRaceButton.setBounds(new Rectangle(8, 48, 95, 21));
    jDeleteRaceButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jDeleteRaceButton.setToolTipText("Delete the selected race.");
    jDeleteRaceButton.setText("Delete Race");
    jDeleteRaceButton.addActionListener(new DialogNewRandomName_jDeleteRaceButton_actionAdapter(this));
    jFemaleCheckBox.setBackground(Color.lightGray);
    jFemaleCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jFemaleCheckBox.setToolTipText("Famel Name?");
    jFemaleCheckBox.setSelected(true);
    jFemaleCheckBox.setText("Female");
    jFemaleCheckBox.setBounds(new Rectangle(342, 89, 59, 23));
    jFemaleCheckBox.addActionListener(new DialogNewRandomName_jFemaleCheckBox_actionAdapter(this));
    jMaleCheckBox.setBackground(Color.lightGray);
    jMaleCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jMaleCheckBox.setToolTipText("Male name?");
    jMaleCheckBox.setSelected(true);
    jMaleCheckBox.setText("Male");
    jMaleCheckBox.setBounds(new Rectangle(281, 89, 59, 23));
    jMaleCheckBox.addActionListener(new DialogNewRandomName_jMaleCheckBox_actionAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jDialogPanel, BorderLayout.CENTER);
    jDialogPanel.add(jDataPanel, BorderLayout.CENTER);
    jDataPanel.add(jNewRaceButton, null);
    jDataPanel.add(jNewRaceTextField, null);
    jDialogPanel.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jCloseButton, null);
    jDataPanel.add(jRaceSelectComboBox, null);
    jDataPanel.add(jNewNameButton, null);
    jDataPanel.add(jNewNameTextField, null);
    jDataPanel.add(jNameComboBox, null);
    jDataPanel.add(jDeleteNameButton, null);
    jDataPanel.add(jRaceComboBox, null);
    jDataPanel.add(jDeleteRaceButton, null);
    jDataPanel.add(jMaleCheckBox, null);
    jDataPanel.add(jFemaleCheckBox, null);
    jDataPanel.add(jLastNameCheckBox, null);
  }

  void RebuildComboBoxes()
  {
    String sSelectedRace = (String)jRaceSelectComboBox.getSelectedItem();
    if (sSelectedRace == null)
      sSelectedRace = "generic";
    Collections.sort(oParent.lRandomNames);
    jNameComboBox.removeAllItems();
    jRaceComboBox.removeAllItems();
    jRaceSelectComboBox.removeAllItems(); // select box only, we delete from other
    oParent.jGMRandomNameRaceComboBox.removeAllItems();
    ArrayList lRaces = new ArrayList();
    for (int i=0;i<oParent.lRandomNames.size();i++)
    {
      TableRandomName oR = (TableRandomName)oParent.lRandomNames.get(i);
      if (sSelectedRace.equalsIgnoreCase(oR.sRace))
        jNameComboBox.addItem(oR.sName+(oR.bLast?"(lastname)":"(firstname)"));
      boolean bFound = false;
      for (int j=0;j<lRaces.size() && !bFound;j++)  // lets see if we've listed this race already
      {
        String sRace = (String)lRaces.get(j);
        if (sRace.equalsIgnoreCase(oR.sRace))
          bFound = true;
      }
      if (!bFound)
      {
        lRaces.add(oR.sRace);
        jRaceComboBox.addItem(oR.sRace);
        jRaceSelectComboBox.addItem(oR.sRace);
        oParent.jGMRandomNameRaceComboBox.addItem(oR.sRace);
      }
    }

  }

  void jDeleteRaceButton_actionPerformed(ActionEvent e) {
    String sRaceToRemove = (String)jRaceComboBox.getSelectedItem();
    if (oParent.AskYN(this,"Are you sure you want to remove the race "+sRaceToRemove+"?")){
      for (int i = 0; i < oParent.lRandomNames.size(); i++) {
        TableRandomName oR = (TableRandomName) oParent.lRandomNames.get(i);
        if (oR.sRace.equalsIgnoreCase(sRaceToRemove))
          oParent.lRandomNames.remove(oR);
      }
      // save to file here
//      SaveRandomNames.toFile(oParent, oParent.sRandomNamesSaveFile,
//                             oParent.lRandomNames);
      xmlControl.saveDoc(oParent,oParent,TableRandomName.xmlBuildDocFromList(
            oParent.lRandomNames,oParent.nMaxRandomNames),oParent.sRandomNamesSaveFile);

      // reload combo boxes
      RebuildComboBoxes();
      oParent.ShowDone(this,"Removed race.");
    }
  }

  void jDeleteNameButton_actionPerformed(ActionEvent e) {
    TableRandomName oR = (TableRandomName)oParent.lRandomNames.get(jNameComboBox.getSelectedIndex());
    if (oParent.AskYN(this,"Are you sure you want to remove the name "+oR.sName+"?")){

      oParent.lRandomNames.remove(oR);

      // save to file here
//      SaveRandomNames.toFile(oParent, oParent.sRandomNamesSaveFile,
//                             oParent.lRandomNames);
      xmlControl.saveDoc(oParent,oParent,TableRandomName.xmlBuildDocFromList(
            oParent.lRandomNames,oParent.nMaxRandomNames),oParent.sRandomNamesSaveFile);

      // reload combo boxes
      RebuildComboBoxes();
      oParent.ShowDone(this,"Removed name.");
    }
  }

  void jNewRaceButton_actionPerformed(ActionEvent e) {
    String sNewRace = jNewRaceTextField.getText();
    jNewRaceTextField.setText("");
    String sNewName = jNewNameTextField.getText();
    jNewNameTextField.setText("");
    TableRandomName oR = new TableRandomName();
    oR.sRace = sNewRace;
    oR.sName = sNewName;
    oR.bMale = jMaleCheckBox.isSelected();
    oR.bFemale = jFemaleCheckBox.isSelected();
    oR.bLast = jLastNameCheckBox.isSelected();

    oParent.lRandomNames.add(oR);
    // save to file here
//    SaveRandomNames.toFile(oParent,oParent.sRandomNamesSaveFile,oParent.lRandomNames);
    xmlControl.saveDoc(oParent,oParent,TableRandomName.xmlBuildDocFromList(
          oParent.lRandomNames,oParent.nMaxRandomNames),oParent.sRandomNamesSaveFile);

    // reload combo boxes
    RebuildComboBoxes();

  }

  void jNewNameButton_actionPerformed(ActionEvent e) {
    String sNewName = jNewNameTextField.getText();
    jNewNameTextField.setText("");
    String sRace = (String)jRaceComboBox.getSelectedItem();
    TableRandomName oR = new TableRandomName();
    oR.sRace = sRace;
    oR.sName = sNewName;
    oR.bMale = jMaleCheckBox.isSelected();
    oR.bFemale = jFemaleCheckBox.isSelected();
    oR.bLast = jLastNameCheckBox.isSelected();

    oParent.lRandomNames.add(oR);
    // save to file here
//    SaveRandomNames.toFile(oParent,oParent.sRandomNamesSaveFile,oParent.lRandomNames);
    xmlControl.saveDoc(oParent,oParent,TableRandomName.xmlBuildDocFromList(
          oParent.lRandomNames,oParent.nMaxRandomNames),oParent.sRandomNamesSaveFile);

    // reload combo boxes
    RebuildComboBoxes();
  }

  void jRaceSelectComboBox_actionPerformed(ActionEvent e) {
    jNameComboBox.removeAllItems();
    String sRace = (String)jRaceSelectComboBox.getSelectedItem();
    if (sRace == null)
      sRace = "generic";
    for (int i=0;i<oParent.lRandomNames.size();i++)
    {
      TableRandomName oR = (TableRandomName)oParent.lRandomNames.get(i);
      if (sRace.equalsIgnoreCase(oR.sRace))
        jNameComboBox.addItem(oR.sName+(oR.bLast?"(lastname)":"(firstname)"));
    }

  }

  void jCloseButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jMaleCheckBox_actionPerformed(ActionEvent e) {
    if (!jMaleCheckBox.isSelected() && !jFemaleCheckBox.isSelected())
    jMaleCheckBox.setSelected(true);
  }

  void jFemaleCheckBox_actionPerformed(ActionEvent e) {
    if (!jMaleCheckBox.isSelected() && !jFemaleCheckBox.isSelected())
    jMaleCheckBox.setSelected(true);

  }
}

class DialogNewRandomName_jDeleteRaceButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jDeleteRaceButton_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDeleteRaceButton_actionPerformed(e);
  }
}

class DialogNewRandomName_jDeleteNameButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jDeleteNameButton_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDeleteNameButton_actionPerformed(e);
  }
}

class DialogNewRandomName_jNewRaceButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jNewRaceButton_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jNewRaceButton_actionPerformed(e);
  }
}

class DialogNewRandomName_jNewNameButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jNewNameButton_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jNewNameButton_actionPerformed(e);
  }
}

class DialogNewRandomName_jRaceSelectComboBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jRaceSelectComboBox_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRaceSelectComboBox_actionPerformed(e);
  }
}

class DialogNewRandomName_jCloseButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jCloseButton_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseButton_actionPerformed(e);
  }
}

class DialogNewRandomName_jMaleCheckBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jMaleCheckBox_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMaleCheckBox_actionPerformed(e);
  }
}

class DialogNewRandomName_jFemaleCheckBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewRandomName adaptee;

  DialogNewRandomName_jFemaleCheckBox_actionAdapter(DialogNewRandomName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jFemaleCheckBox_actionPerformed(e);
  }
}
