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

public class DialogNewSkill extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;
  TableSkills oEditSkill = null;
  TableSkills oCurrentSkill = null;
  int nSkillEditIndex = -1;
  int nSkillID = -1;
  JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jNameTextField = new JTextField();
  JLabel jLabel2 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JLabel jLabel3 = new JLabel();
  JSpinner jRatingSpinner = new JSpinner(new SpinnerNumberModel(1,1,150,1));
  JButton jAddButton = new JButton();
  JButton jCancelButton = new JButton();
  Border border1;
  TitledBorder titledBorder1;
  JComboBox jTypeComboBox = new JComboBox();
  JLabel jLabel4 = new JLabel();
  JButton jSaveSkillButton = new JButton();
  JLabel jLabel5 = new JLabel();
  JPanel jButtonPanel = new JPanel();
  JButton jRemoveButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  DefaultListModel mListSkill = new DefaultListModel();
  JList jListSkill = new JList(mListSkill);
  BorderLayout borderLayout2 = new BorderLayout();
  Border border2;
  TitledBorder titledBorder2;
  JPanel jPanel2 = new JPanel();
  Border border3;
  TitledBorder titledBorder3;
  JRadioButton jSkillTalentRadioButton2 = new JRadioButton();
  JRadioButton jSkillSKRadioButton3 = new JRadioButton();
  JRadioButton jSkillProfRadioButton4 = new JRadioButton();
  JRadioButton jSkillAbilityRadioButton5 = new JRadioButton();
  GridLayout gridLayout1 = new GridLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  JPanel jPanel3 = new JPanel();
  Border border4;
  Border border5;
  JLabel jSkillSelectedLabel = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField jFilterTextField1 = new JTextField();

  public DialogNewSkill(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewSkill(HackSackFrame oParent, TablePlayer oPlayer,
                        Skills oEdit, int nSkillType) {
    this(null, "", false);
    this.oPlayer = oPlayer;
    this.oParent = oParent;
//    this.oEditSkill = oEdit;

    if (oEdit != null) {
      oEditSkill = TableSkills.GetSkillsFromID(oParent,oEdit.nSkillID);
    }

    // this will set the skill filter to the type of skill
    // the gm is editing.
    if (oEditSkill != null && nSkillType == -1) {
      nSkillType = oEditSkill.nSkillType;
    }

    if (nSkillType != -1) {
      jSkillAbilityRadioButton5.setSelected(false);
      jSkillProfRadioButton4.setSelected(false);
      jSkillTalentRadioButton2.setSelected(false);
      jSkillSKRadioButton3.setSelected(false);

      if (nSkillType == oEditSkill.TYPE_ABILITY) {
        jSkillAbilityRadioButton5.setSelected(true);
      }
      if (nSkillType == oEditSkill.TYPE_PROF) {
        jSkillProfRadioButton4.setSelected(true);
      }
      if (nSkillType == oEditSkill.TYPE_SKILL) {
        jSkillSKRadioButton3.setSelected(true);
      }
      if (nSkillType == oEditSkill.TYPE_TALENT) {
        jSkillTalentRadioButton2.setSelected(true);
      }
    }

    jTypeComboBox.removeAllItems();
    for (int i=0;i<oParent.gmSkillTypeTable.length;i++) {
      jTypeComboBox.addItem(oParent.gmSkillTypeTable[i]);
    }
    RebuildSkillComboBox();
    if (oEditSkill != null)
      jListSkill.setSelectedValue(oEditSkill, true);

    if (oPlayer == null)
      jAddButton.setEnabled(false);

  }

  private void jbInit() throws Exception {
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder3 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134)),"");
    border4 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
    border5 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
    jListSkill.setCellRenderer(new CellRendererComboBox());
    jListSkill.setFixedCellWidth(-1);
    jListSkill.addListSelectionListener(new DialogNewSkill_jListSkill_listSelectionAdapter(this));

    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"New Skill, Talent or Proficency");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Skill List");
    panel1.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel1.setToolTipText("This is the name of a new skill, talent, prof or ability you wish " +
    "to add.");
    jLabel1.setText("New Skill");
    jLabel1.setBounds(new Rectangle(10, 94, 61, 15));
    jNameTextField.setFont(new java.awt.Font("Dialog", 0, 11));
    jNameTextField.setPreferredSize(new Dimension(57, 21));
    jNameTextField.setText("");
    jNameTextField.setBounds(new Rectangle(80, 91, 389, 21));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel2.setText("Description");
    jLabel2.setBounds(new Rectangle(11, 282, 54, 15));
    jScrollPane1.setBounds(new Rectangle(81, 204, 389, 170));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 11));
    jDescTextArea.setText("");
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);

    jDescTextArea.setTabSize(8);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel3.setText("Skill Rating");
    jLabel3.setBounds(new Rectangle(199, 141, 54, 15));
    jRatingSpinner.setFont(new java.awt.Font("Dialog", 0, 11));
    jRatingSpinner.setBounds(new Rectangle(269, 138, 80, 21));
    jAddButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jAddButton.setToolTipText("Add this skill/talent to player.");
    jAddButton.setText("Add To Player");
    jAddButton.addActionListener(new DialogNewSkill_jAddButton_actionAdapter(this));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Done.");
    jCancelButton.setText("Done");
    jCancelButton.addActionListener(new DialogNewSkill_jCancelButton_actionAdapter(this));
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(titledBorder1);
    panel1.setPreferredSize(new Dimension(600, 400));
    jTypeComboBox.setFont(new java.awt.Font("Dialog", 0, 11));
    jTypeComboBox.setBounds(new Rectangle(80, 138, 80, 21));
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel4.setText("Type");
    jLabel4.setBounds(new Rectangle(10, 141, 52, 15));
    jSaveSkillButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveSkillButton.setToolTipText("Save this skill to give to other players as well.");
    jSaveSkillButton.setText("Save Skill");
    jSaveSkillButton.addActionListener(new DialogNewSkill_jSaveSkillButton_actionAdapter(this));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel5.setText("Selected Skill");
    jLabel5.setBounds(new Rectangle(11, 38, 64, 15));
    jButtonPanel.setBackground(Color.gray);
    jButtonPanel.setBorder(border5);
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveButton.setToolTipText("Delete this skill.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new DialogNewSkill_jRemoveButton_actionAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(titledBorder2);
    jListSkill.setBackground(Color.lightGray);
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setBorder(titledBorder3);
    jPanel2.setLayout(gridLayout1);
    jSkillTalentRadioButton2.setBackground(Color.lightGray);
    jSkillTalentRadioButton2.setFont(new java.awt.Font("Dialog", 0, 11));
    jSkillTalentRadioButton2.setToolTipText("Display talent  type skills.");
    jSkillTalentRadioButton2.setSelected(true);
    jSkillTalentRadioButton2.setText("Talents");
    jSkillTalentRadioButton2.addActionListener(new DialogNewSkill_jSkillTalentRadioButton2_actionAdapter(this));
    jSkillSKRadioButton3.setBackground(Color.lightGray);
    jSkillSKRadioButton3.setFont(new java.awt.Font("Dialog", 0, 11));
    jSkillSKRadioButton3.setToolTipText("Display skill type skills.");
    jSkillSKRadioButton3.setSelected(true);
    jSkillSKRadioButton3.setText("Skills");
    jSkillSKRadioButton3.addActionListener(new DialogNewSkill_jSkillSKRadioButton3_actionAdapter(this));
    jSkillProfRadioButton4.setBackground(Color.lightGray);
    jSkillProfRadioButton4.setFont(new java.awt.Font("Dialog", 0, 11));
    jSkillProfRadioButton4.setToolTipText("Display proficiency type skills.");
    jSkillProfRadioButton4.setSelected(true);
    jSkillProfRadioButton4.setText("Proficiencies");
    jSkillProfRadioButton4.addActionListener(new DialogNewSkill_jSkillProfRadioButton4_actionAdapter(this));
    jSkillAbilityRadioButton5.setBackground(Color.lightGray);
    jSkillAbilityRadioButton5.setFont(new java.awt.Font("Dialog", 0, 11));
    jSkillAbilityRadioButton5.setToolTipText("Display ability type skills.");
    jSkillAbilityRadioButton5.setSelected(true);
    jSkillAbilityRadioButton5.setText("Abilities");
    jSkillAbilityRadioButton5.addActionListener(new DialogNewSkill_jSkillAbilityRadioButton5_actionAdapter(this));
    gridLayout1.setColumns(2);
    gridLayout1.setRows(2);
//    jScrollPane2.setPreferredSize(new Dimension(-1, -1));
    jPanel3.setBackground(Color.gray);
    jPanel3.setBorder(border4);
    jSkillSelectedLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    jSkillSelectedLabel.setText("None selected yet....");
    jSkillSelectedLabel.setBounds(new Rectangle(81, 34, 389, 22));
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel6.setText("Find");
    jFilterTextField1.setPreferredSize(new Dimension(70, 20));
    jFilterTextField1.setText("");
    jFilterTextField1.setScrollOffset(0);
    jFilterTextField1.addKeyListener(new DialogNewSkill_jFilterTextField1_keyAdapter(this));
    panel1.add(jLabel5, null);
    jButtonPanel.add(jCancelButton, null);
    jButtonPanel.add(jAddButton, null);
    jButtonPanel.add(jSaveSkillButton, null);
    jPanel2.add(jSkillAbilityRadioButton5, null);
    jPanel2.add(jSkillTalentRadioButton2, null);
    jPanel2.add(jSkillProfRadioButton4, null);
    jPanel2.add(jSkillSKRadioButton3, null);
    panel1.add(jSkillSelectedLabel, null);
    panel1.add(jScrollPane1, null);
    panel1.add(jLabel2, null);
    panel1.add(jNameTextField, null);
    panel1.add(jLabel1, null);
    panel1.add(jTypeComboBox, null);
    panel1.add(jLabel4, null);
    panel1.add(jLabel3, null);
    panel1.add(jRatingSpinner, null);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    this.getContentPane().add(jPanel1,  BorderLayout.WEST);
    jPanel1.add(jScrollPane2,  BorderLayout.CENTER);
    jPanel1.add(jPanel3,  BorderLayout.SOUTH);
    jPanel3.add(jLabel6, null);
    jPanel3.add(jFilterTextField1, null);
    jPanel3.add(jRemoveButton, null);
    jPanel1.add(jPanel2, BorderLayout.NORTH);
    jScrollPane2.getViewport().add(jListSkill, null);
    this.getContentPane().add(jButtonPanel, BorderLayout.SOUTH);
    this.getContentPane().add(panel1,  BorderLayout.CENTER);
    jListSkill.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

  }

  void jAddButton_actionPerformed(ActionEvent e) {
    if (oCurrentSkill != null) {
      Skills oSkill = new Skills();
//    TableSkills oSS = (TableSkills) oParent.lSkills.get(jSkillListComboBox.getSelectedIndex());
      TableSkills oSS = oCurrentSkill;
      oSkill.sName = oSS.sName;
      oSkill.sDesc = jDescTextArea.getText();
      oSkill.jSkillSpinner.getModel().setValue(new Integer(Integer.parseInt(
          jRatingSpinner.getModel().getValue().toString())));
      oSkill.nSkillType = jTypeComboBox.getSelectedIndex();
      oSkill.nSkillID = nSkillID;

      oPlayer.aSkills.add(oSkill);
      DefaultMutableTreeNode oNewNode = new DefaultMutableTreeNode(oSkill.sName);
      oNewNode.setUserObject(oSkill);
      if (oSkill.nSkillType == oSkill.TYPE_ABILITY) {
        oPlayer.jSkillsAbilitiesNode.add(oNewNode);
        oPlayer.mTreeModel.reload(oPlayer.jSkillsAbilitiesNode);
      }
      else
      if (oSkill.nSkillType == oSkill.TYPE_PROF) {
        oPlayer.jSkillsProfsNode.add(oNewNode);
        oPlayer.mTreeModel.reload(oPlayer.jSkillsProfsNode);
      }
      else
      if (oSkill.nSkillType == oSkill.TYPE_SKILL) {
        oPlayer.jSkillsNode.add(oNewNode);
        oPlayer.mTreeModel.reload(oPlayer.jSkillsNode);
      }
      else
      if (oSkill.nSkillType == oSkill.TYPE_TALENT) {
        oPlayer.jSkillsTalentsNode.add(oNewNode);
        oPlayer.mTreeModel.reload(oPlayer.jSkillsTalentsNode);
      }

      oParent.ShowDone(this, "Added skill to player.");
//   this.hide();
    }
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jSaveSkillButton_actionPerformed(ActionEvent e) {
    if (jNameTextField.getText().length() > 0) {
      TableSkills oSkill = new TableSkills(oParent);
      oSkill.sName = jNameTextField.getText();
      jNameTextField.setText(""); // clear this once we get it
      oSkill.sDesc = jDescTextArea.getText();
      oSkill.nSkillType = jTypeComboBox.getSelectedIndex();
      oSkill.nSkillID = (oParent.nMaxSkillsID++);
      oParent.lSkills.add(oSkill);
    } else
    if (oCurrentSkill != null) {
//      TableSkills oSS = (TableSkills) oParent.lSkills.get(jSkillListComboBox.getSelectedIndex());
      TableSkills oSS = oCurrentSkill;
        if (oParent.AskYN(this,
                          "Are you sure you want to replace the skill " +
                          oSS.sName + "?")) {
          oSS.sDesc = jDescTextArea.getText();
          oSS.nSkillType = jTypeComboBox.getSelectedIndex();
        }
      }

//    SaveSkills.toFile(oParent,oParent.sSkillsSaveFile,oParent.lSkills);
      xmlControl.saveDoc(oParent,oParent,TableSkills.xmlBuildDocFromList(
            oParent.lSkills,oParent.nMaxSkillsID),oParent.sSkillsSaveFile);
    RebuildSkillComboBox();

  }

  /**
   * this builds tbe skill list
   */
  void RebuildSkillComboBox()
  {
      String sSearchPattern = jFilterTextField1.getText();

      jSkillSelectedLabel.setText("");
      // new jList
      mListSkill.removeAllElements();
      Collections.sort(oParent.lSkills);

      for (int i = 0; i < oParent.lSkills.size(); i++) {
        TableSkills oRecord = (TableSkills) oParent.lSkills.get(i);
        if ( (oRecord.nSkillType == oRecord.TYPE_SKILL &&
              jSkillSKRadioButton3.isSelected()) ||
            (oRecord.nSkillType == oRecord.TYPE_ABILITY &&
             jSkillAbilityRadioButton5.isSelected()) ||
            (oRecord.nSkillType == oRecord.TYPE_PROF &&
             jSkillProfRadioButton4.isSelected()) ||
            (oRecord.nSkillType == oRecord.TYPE_TALENT &&
             jSkillTalentRadioButton2.isSelected())) {

         if (sSearchPattern == null ||
             oRecord.sName.matches("(?si)(.*)?"+sSearchPattern+"(.*)?"))
         mListSkill.addElement(oRecord);
       }
      }
  }

/*  void jSkillListComboBox_actionPerformed(ActionEvent e) {
    if (jSkillListComboBox.getSelectedIndex() >= 0) {
      TableSkills oSkill = (TableSkills) oParent.lSkills.get(jSkillListComboBox.getSelectedIndex());
      jDescTextArea.setText(oSkill.sDesc);
      nSkillID = oSkill.nSkillID;
      jTypeComboBox.setSelectedIndex(oSkill.nSkillType);
    }
  }
*/
  void jRemoveButton_actionPerformed(ActionEvent e) {
    if (oCurrentSkill != null) {
//    TableSkills oSkill = (TableSkills)oParent.lSkills.get(jSkillListComboBox.getSelectedIndex());
      TableSkills oSkill = oCurrentSkill;
      if (oParent.AskYN(this,
                        "Are you sure you want to remove the skill " +
                        oSkill.sName + "?")) {

        oParent.lSkills.remove(oSkill);

        RebuildSkillComboBox();
//      SaveSkills.toFile(oParent, oParent.sSkillsSaveFile, oParent.lSkills);
        xmlControl.saveDoc(oParent, oParent, TableSkills.xmlBuildDocFromList(
            oParent.lSkills, oParent.nMaxSkillsID), oParent.sSkillsSaveFile);
        oParent.ShowDone(this, "Removed skill.");
      }
    }

  }

  void jSkillAbilityRadioButton5_actionPerformed(ActionEvent e) {
    RebuildSkillComboBox();
  }

  void jSkillProfRadioButton4_actionPerformed(ActionEvent e) {
    RebuildSkillComboBox();
  }

  void jSkillTalentRadioButton2_actionPerformed(ActionEvent e) {
    RebuildSkillComboBox();
  }

  void jSkillSKRadioButton3_actionPerformed(ActionEvent e) {
    RebuildSkillComboBox();
  }

  /**
   * this is run when someone selects a skill in the skill list
   * for the object selected
   *
   * @param e ListSelectionEvent
   */
  void jListSkill_valueChanged(ListSelectionEvent e) {
    if (!mListSkill.isEmpty()) {
      Object oObj = jListSkill.getSelectedValue();
      if (oObj != null) {
        if (oObj instanceof TableSkills) {
          TableSkills oS = (TableSkills) oObj;
          oCurrentSkill = oS;
          jSkillSelectedLabel.setText(oS.sName);
          jDescTextArea.setText(oS.sDesc);
          jTypeComboBox.setSelectedIndex(oS.nSkillType);
          nSkillID = oS.nSkillID;
        }
      }
    }
  }

  void jFilterTextField1_keyReleased(KeyEvent e) {
    RebuildSkillComboBox();
  }



}

class DialogNewSkill_jAddButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jAddButton_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jAddButton_actionPerformed(e);
  }
}

class DialogNewSkill_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jCancelButton_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}

class DialogNewSkill_jSaveSkillButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jSaveSkillButton_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveSkillButton_actionPerformed(e);
  }
}

class DialogNewSkill_jRemoveButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jRemoveButton_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

class DialogNewSkill_jSkillAbilityRadioButton5_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jSkillAbilityRadioButton5_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSkillAbilityRadioButton5_actionPerformed(e);
  }
}

class DialogNewSkill_jSkillProfRadioButton4_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jSkillProfRadioButton4_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSkillProfRadioButton4_actionPerformed(e);
  }
}

class DialogNewSkill_jSkillTalentRadioButton2_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jSkillTalentRadioButton2_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSkillTalentRadioButton2_actionPerformed(e);
  }
}

class DialogNewSkill_jSkillSKRadioButton3_actionAdapter implements java.awt.event.ActionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jSkillSKRadioButton3_actionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSkillSKRadioButton3_actionPerformed(e);
  }
}

class DialogNewSkill_jListSkill_listSelectionAdapter implements javax.swing.event.ListSelectionListener {
  DialogNewSkill adaptee;

  DialogNewSkill_jListSkill_listSelectionAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(ListSelectionEvent e) {
    adaptee.jListSkill_valueChanged(e);
  }
}

class DialogNewSkill_jFilterTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogNewSkill adaptee;

  DialogNewSkill_jFilterTextField1_keyAdapter(DialogNewSkill adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.jFilterTextField1_keyReleased(e);
  }
}

