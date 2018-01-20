package hacksack;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.event.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNewQuirk
    extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;
  TableQuirks oEditQuirk = null;
  TableQuirks oCurrentQuirk = null;
  int nQuirkID = -1;

  JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jNameTextField = new JTextField();
  JLabel jLabel2 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JButton jAddButton = new JButton();
  JButton jCancelButton = new JButton();
  Border border1;
  TitledBorder titledBorder1;
  JLabel jLabel3 = new JLabel();
  JButton jSaveQuirkButton = new JButton();
  JPanel jButtonPanel = new JPanel();
  JButton jRemoveButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout2 = new BorderLayout();
  DefaultListModel mListQuirk = new DefaultListModel();
  JList jListQuirk = new JList(mListQuirk);
  JPanel jPanel2 = new JPanel();
  Border border3;
  Border border4;
  JLabel jSelectedQuirkLabel = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jFilterTextField = new JTextField();

  public DialogNewQuirk(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewQuirk(HackSackFrame oParent,
                        TablePlayer oPlayer,
                        Quirks oThisQuirk) {
    this(null, "", false);
    this.oPlayer = oPlayer;
    this.oParent = oParent;

    if (oThisQuirk != null)
      this.oEditQuirk = TableQuirks.GetQuirkFromID(oParent,oThisQuirk.nQuirkID);

    RebuildList();
    if (oEditQuirk != null)
      jListQuirk.setSelectedValue(oEditQuirk,true);

    if (oPlayer == null) {
      jAddButton.setEnabled(false);

    }
  }

  private void jbInit() throws Exception {
    border3 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    border4 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    jListQuirk.setCellRenderer(new CellRendererComboBox());
    jListQuirk.addListSelectionListener(new
        DialogNewQuirk_jListQuirk_listSelectionAdapter(this));

    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, Color.white, new Color(115, 114, 105),
        new Color(165, 163, 151)), "New Quick or Flaw");
    border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, Color.white, new Color(93, 93, 93),
        new Color(134, 134, 134)), "Quirk & Flaw List");
    panel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel1.setText("New Quirk");
    jLabel1.setBounds(new Rectangle(10, 56, 66, 15));
    jNameTextField.setFont(new java.awt.Font("Dialog", 0, 11));
    jNameTextField.setPreferredSize(new Dimension(57, 21));
    jNameTextField.setText("");
    jNameTextField.setBounds(new Rectangle(101, 53, 264, 21));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel2.setText("Description");
    jLabel2.setBounds(new Rectangle(10, 124, 66, 15));
    jScrollPane1.setBounds(new Rectangle(101, 84, 264, 113));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 11));
    jDescTextArea.setText("");
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);

    jDescTextArea.setTabSize(8);
    jAddButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jAddButton.setToolTipText("Add this quirk/flaw to the player.");
    jAddButton.setText("Add To Player");
    jAddButton.addActionListener(new DialogNewQuirk_jAddButton_actionAdapter(this));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jCancelButton.setToolTipText("Done.");
    jCancelButton.setText("Done");
    jCancelButton.addActionListener(new
                                    DialogNewQuirk_jCancelButton_actionAdapter(this));
    panel1.setBackground(Color.lightGray);
    panel1.setFont(new java.awt.Font("Dialog", 0, 9));
    panel1.setBorder(titledBorder1);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel3.setText("Selected Quirk");
    jLabel3.setBounds(new Rectangle(10, 29, 89, 15));
    jSaveQuirkButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jSaveQuirkButton.setToolTipText(
        "Save this quirk or flaw to use for other players as well.");
    jSaveQuirkButton.setText("Save Quirk");
    jSaveQuirkButton.addActionListener(new
        DialogNewQuirk_jSaveQuirkButton_actionAdapter(this));
    jButtonPanel.setBackground(Color.gray);
    jButtonPanel.setBorder(border4);
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jRemoveButton.setToolTipText("Delete this quirk/flaw.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new
                                    DialogNewQuirk_jRemoveButton_actionAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(borderLayout2);
    jListQuirk.setBackground(Color.lightGray);
    jPanel2.setBackground(Color.gray);
    jPanel2.setBorder(border3);
    jSelectedQuirkLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    jSelectedQuirkLabel.setText("No quirk selected...");
    jSelectedQuirkLabel.setBounds(new Rectangle(101, 29, 264, 14));
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel4.setToolTipText("");
    jLabel4.setText("Find");
    jFilterTextField.setPreferredSize(new Dimension(70, 20));
    jFilterTextField.setText("");
    jFilterTextField.addKeyListener(new DialogNewQuirk_jFilterTextField_keyAdapter(this));
    panel1.add(jLabel1, null);
    panel1.add(jLabel3, null);
    panel1.add(jLabel2, null);
    panel1.add(jScrollPane1, null);
    panel1.add(jSelectedQuirkLabel, null);
    panel1.add(jNameTextField, null);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    jButtonPanel.add(jCancelButton, null);
    jButtonPanel.add(jSaveQuirkButton, null);
    jButtonPanel.add(jAddButton, null);
    this.getContentPane().add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jScrollPane2, BorderLayout.CENTER);
    jPanel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jLabel4, null);
    jPanel2.add(jFilterTextField, null);
    jPanel2.add(jRemoveButton, null);
    jScrollPane2.getViewport().add(jListQuirk, null);
    this.getContentPane().add(jButtonPanel, BorderLayout.SOUTH);
  }

  void jAddButton_actionPerformed(ActionEvent e) {
    if (oCurrentQuirk != null) {
      Quirks oQuirk = new Quirks();
//    TableQuirks oQQ = (TableQuirks) oParent.lQuirks.get(jQuirkComboBox.getSelectedIndex());
      TableQuirks oQQ = oCurrentQuirk;
      oQuirk.sName = oQQ.sName;
//    oQuirk.sName = jNameTextField.getText();
      oQuirk.sDesc = jDescTextArea.getText();
      oQuirk.nQuirkID = nQuirkID;

      oPlayer.aQuirks.add(oQuirk);
      DefaultMutableTreeNode oNewNode = new DefaultMutableTreeNode(oQuirk.sName);
      oNewNode.setUserObject(oQuirk);

      oPlayer.jQuirksNode.add(oNewNode);
      oPlayer.mQuirkTreeModel.reload(oPlayer.jQuirksNode);

    }
//    this.hide();
    oParent.ShowDone(this,"Added quirk to player.");
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jSaveQuirkButton_actionPerformed(ActionEvent e) {
    if (jNameTextField.getText().length() > 0) {
      TableQuirks oNew = new TableQuirks();
      oNew.sName = jNameTextField.getText();
      oNew.sDesc = jDescTextArea.getText();
      oNew.nQuirkID = (oParent.nMaxQuirksID++);
      oParent.lQuirks.add(oNew);
    }
    else
    if (oCurrentQuirk != null ) {
//      TableQuirks oO = (TableQuirks) oParent.lQuirks.get(jQuirkComboBox.getSelectedIndex());
      TableQuirks oO = oCurrentQuirk;
      if (oParent.AskYN(this,
                        "Are you sure you want to replace the quick/flaw " +
                        oO.sName + "?")) {

        oO.sDesc = jDescTextArea.getText();
      }
    }

//    SaveQuirks.toFile(oParent,oParent.sQuirksSaveFile,oParent.lQuirks);
    xmlControl.saveDoc(oParent, oParent, TableQuirks.xmlBuildDocFromList(
        oParent.lQuirks, oParent.nMaxQuirksID), oParent.sQuirksSaveFile);

      RebuildList();
  }

  void jRemoveButton_actionPerformed(ActionEvent e) {
    if (oCurrentQuirk != null) {
//      TableQuirks oO = (TableQuirks) oParent.lQuirks.get(jQuirkComboBox.getSelectedIndex());
      TableQuirks oO = oCurrentQuirk;
      if (oParent.AskYN(this,
                        "Are you sure you want to remove the quick/flaw " +
                        oO.sName + "?")) {

        oParent.lQuirks.remove(oO);

        RebuildList();

        xmlControl.saveDoc(oParent, oParent, TableQuirks.xmlBuildDocFromList(
            oParent.lQuirks, oParent.nMaxQuirksID), oParent.sQuirksSaveFile);
        oParent.ShowDone(this, "Removed quick/flaw.");
      }
    }

  }

  /**
   * this is run when a player selects a object in the list
   *
   * @param e ListSelectionEvent
   */
  void jListQuirk_valueChanged(ListSelectionEvent e) {
    if (!mListQuirk.isEmpty()) {
      Object oObj = jListQuirk.getSelectedValue();
      if (oObj != null) {
        if (oObj instanceof TableQuirks) {
          TableQuirks oO = (TableQuirks)oObj;
          oCurrentQuirk = oO;
          nQuirkID = oO.nQuirkID;
          jSelectedQuirkLabel.setText(oO.sName);
          jDescTextArea.setText(oO.sDesc);
        }
      }
    }
  }

  /**
   * rebuild the jListQuirk
   */
  void RebuildList() {
    if (oParent.lQuirks.size() > 0) {
      String sSearchPattern = jFilterTextField.getText();
      Collections.sort(oParent.lQuirks);
      mListQuirk.removeAllElements();
      for (int i = 0; i < oParent.lQuirks.size(); i++) {
        TableQuirks oO = (TableQuirks) oParent.lQuirks.get(i);
        if (sSearchPattern == null ||
            oO.sName.matches("(?si)(.*)?"+sSearchPattern+"(.*)?"))
        mListQuirk.addElement(oO);
      }

    }
  }
  void jFilterTextField_keyReleased(KeyEvent e) {
    RebuildList();
  }

}

class DialogNewQuirk_jAddButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jAddButton_actionAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jAddButton_actionPerformed(e);
  }
}

class DialogNewQuirk_jCancelButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jCancelButton_actionAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}

class DialogNewQuirk_jSaveQuirkButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jSaveQuirkButton_actionAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveQuirkButton_actionPerformed(e);
  }
}

class DialogNewQuirk_jRemoveButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jRemoveButton_actionAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

class DialogNewQuirk_jListQuirk_listSelectionAdapter
    implements javax.swing.event.ListSelectionListener {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jListQuirk_listSelectionAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(ListSelectionEvent e) {
    adaptee.jListQuirk_valueChanged(e);
  }
}

class DialogNewQuirk_jFilterTextField_keyAdapter extends java.awt.event.KeyAdapter {
  DialogNewQuirk adaptee;

  DialogNewQuirk_jFilterTextField_keyAdapter(DialogNewQuirk adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.jFilterTextField_keyReleased(e);
  }
}

