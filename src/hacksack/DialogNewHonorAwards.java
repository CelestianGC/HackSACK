package hacksack;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * This is the "create a new" honor award dialog
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNewHonorAwards extends JDialog {
  HackSackFrame oParent = null;
  JPanel jButtonPanel = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jNameTextField = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JLabel jGOODLabel = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLGLabel = new JLabel();
  JLabel jNGLabel = new JLabel();
  JLabel jCGLabel = new JLabel();
  JSpinner jLGSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JSpinner jNGSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JSpinner jCGSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel3 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JSpinner jNNSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JSpinner jLNSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JSpinner jCNSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JSpinner jCESpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel9 = new JLabel();
  JSpinner jNESpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel10 = new JLabel();
  JSpinner jLESpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel11 = new JLabel();
  JButton jSaveHonorTypeButton = new JButton();
  JButton jCloseWindowButton = new JButton();
  JPanel jPanel1 = new JPanel();
  JComboBox jAwardsComboBox = new JComboBox();
  JButton jRemoveButton = new JButton();
  JButton jResetButton = new JButton();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JSpinner jSpecificSpinner1 = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
  JLabel jLabel12 = new JLabel();
  JCheckBox jPercentageCheckBox1 = new JCheckBox();
  JCheckBox jPerLevelCheckBox1 = new JCheckBox();
  BorderLayout borderLayout1 = new BorderLayout();

  public DialogNewHonorAwards(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewHonorAwards(HackSackFrame oParent, boolean bDisplayPlayerAdd) {
    this(null, "", false);
    this.oParent = oParent;

    ResetFieldsToDefault(); // so the fields are empty by default
                            // they get filled by the above listener changes

  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Honor Award Type");
    jButtonPanel.setLayout(null);
    jButtonPanel.setBackground(Color.lightGray);
    jButtonPanel.setBorder(titledBorder1);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("The type of award given.");
    jLabel1.setText("Name");
    jLabel1.setBounds(new Rectangle(12, 53, 34, 15));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("The Description of this award type.");
    jLabel2.setText("Description");
    jLabel2.setBounds(new Rectangle(12, 90, 57, 15));
    jNameTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jNameTextField.setText("");
    jNameTextField.setBounds(new Rectangle(72, 56, 481, 21));
    jScrollPane1.setBounds(new Rectangle(72, 90, 481, 81));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextArea.setText("");
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);
    jGOODLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jGOODLabel.setToolTipText("Good (Lawful Good, Neutral Good, Chaotic Good)");
    jGOODLabel.setText("Good");
    jGOODLabel.setBounds(new Rectangle(62, 12, 34, 15));
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel4.setToolTipText("Neutral (Neutral Good, True Neutral, Chaotic Neutral)");
    jLabel4.setText("Neutral");
    jLabel4.setBounds(new Rectangle(67, 13, 48, 15));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setToolTipText("Evil (Lawful Evil, Neutral Evil, Chaotic Evil)");
    jLabel5.setText("Evil");
    jLabel5.setBounds(new Rectangle(66, 10, 34, 15));
    jLGLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jLGLabel.setText("Lawful");
    jLGLabel.setBounds(new Rectangle(11, 35, 34, 15));
    jNGLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jNGLabel.setText("Neutral");
    jNGLabel.setBounds(new Rectangle(62, 35, 34, 15));
    jCGLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jCGLabel.setText("Chaotic");
    jCGLabel.setBounds(new Rectangle(113, 35, 41, 15));
    jLGSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jLGSpinner.setBounds(new Rectangle(11, 54, 45, 24));
    jNGSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jNGSpinner.setBounds(new Rectangle(62, 54, 45, 24));
    jCGSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jCGSpinner.setBounds(new Rectangle(113, 54, 45, 24));
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel3.setText("Lawful");
    jLabel3.setBounds(new Rectangle(20, 34, 34, 15));
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel6.setText("Chaotic");
    jLabel6.setBounds(new Rectangle(122, 34, 41, 15));
    jNNSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jNNSpinner.setBounds(new Rectangle(71, 53, 45, 24));
    jLNSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jLNSpinner.setBounds(new Rectangle(20, 53, 45, 24));
    jCNSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jCNSpinner.setBounds(new Rectangle(122, 53, 45, 24));
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel7.setText("Neutral");
    jLabel7.setBounds(new Rectangle(71, 34, 34, 15));
    jLabel8.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel8.setText("Chaotic");
    jLabel8.setBounds(new Rectangle(117, 33, 41, 15));
    jCESpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jCESpinner.setBounds(new Rectangle(117, 52, 45, 24));
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel9.setText("Lawful");
    jLabel9.setBounds(new Rectangle(15, 33, 34, 15));
    jNESpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jNESpinner.setBounds(new Rectangle(66, 52, 45, 24));
    jLabel10.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel10.setText("Neutral");
    jLabel10.setBounds(new Rectangle(66, 33, 34, 15));
    jLESpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jLESpinner.setBounds(new Rectangle(15, 52, 45, 24));
    jLabel11.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel11.setText("Awards Values For Specific Alignments");
    jLabel11.setBounds(new Rectangle(221, 198, 180, 15));
    jSaveHonorTypeButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveHonorTypeButton.setToolTipText("Save this honor award type.");
    jSaveHonorTypeButton.setText("Save");
    jSaveHonorTypeButton.addActionListener(new DialogNewHonorAwards_jSaveHonorTypeButton_actionAdapter(this));
    jCloseWindowButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCloseWindowButton.setToolTipText("Close this Window");
    jCloseWindowButton.setText("Done");
    jCloseWindowButton.addActionListener(new DialogNewHonorAwards_jCloseWindowButton_actionAdapter(this));
    jPanel1.setBackground(Color.darkGray);
    jPanel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jPanel1.setAlignmentY((float) 0.5);
    jAwardsComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jAwardsComboBox.setBounds(new Rectangle(72, 23, 377, 21));
    jAwardsComboBox.addActionListener(new DialogNewHonorAwards_jAwardsComboBox_actionAdapter(this));
    jRemoveButton.setBounds(new Rectangle(465, 23, 88, 21));
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveButton.setToolTipText("Remove this Honor Award Type.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new DialogNewHonorAwards_jRemoveButton_actionAdapter(this));
    jResetButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jResetButton.setToolTipText("Reset all values to default.");
    jResetButton.setText("Reset");
    jResetButton.addActionListener(new DialogNewHonorAwards_jResetButton_actionAdapter(this));
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setBounds(new Rectangle(15, 212, 182, 90));
    jPanel2.setLayout(null);
    jPanel3.setBackground(Color.lightGray);
    jPanel3.setBounds(new Rectangle(411, 214, 182, 90));
    jPanel3.setLayout(null);
    jPanel4.setBackground(Color.lightGray);
    jPanel4.setBounds(new Rectangle(213, 213, 182, 90));
    jPanel4.setLayout(null);
    jSpecificSpinner1.setFont(new java.awt.Font("Dialog", 0, 9));
    jSpecificSpinner1.setBounds(new Rectangle(73, 181, 50, 24));
    jLabel12.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel12.setToolTipText("Specific award regardless of honor.");
    jLabel12.setText("Specific");
    jLabel12.setBounds(new Rectangle(12, 181, 40, 15));
    jPercentageCheckBox1.setBackground(Color.lightGray);
    jPercentageCheckBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jPercentageCheckBox1.setToolTipText("Percentage award/penalty. Remeber percentages are not temporal honor " +
    "adjustments.");
    jPercentageCheckBox1.setText("%");
    jPercentageCheckBox1.setBounds(new Rectangle(124, 181, 39, 11));
    jPercentageCheckBox1.addActionListener(new DialogNewHonorAwards_jPercentageCheckBox1_actionAdapter(this));
    jPerLevelCheckBox1.setBackground(Color.lightGray);
    jPerLevelCheckBox1.setFont(new java.awt.Font("Dialog", 0, 7));
    jPerLevelCheckBox1.setToolTipText("Apply specific adjustment per level.");
    jPerLevelCheckBox1.setText("Per Level");
    jPerLevelCheckBox1.setBounds(new Rectangle(124, 194, 83, 11));
    jPerLevelCheckBox1.addActionListener(new DialogNewHonorAwards_jPerLevelCheckBox1_actionAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.add(jCloseWindowButton, null);
    jPanel1.add(jResetButton, null);
    jPanel1.add(jSaveHonorTypeButton, null);
    jButtonPanel.add(jScrollPane1, null);
    jButtonPanel.add(jNameTextField, null);
    jButtonPanel.add(jLabel2, null);
    jButtonPanel.add(jLabel1, null);
    jButtonPanel.add(jAwardsComboBox, null);
    jButtonPanel.add(jRemoveButton, null);
    jPanel2.add(jNGSpinner, null);
    jPanel2.add(jLGSpinner, null);
    jPanel2.add(jCGSpinner, null);
    jPanel2.add(jCGLabel, null);
    jPanel2.add(jNGLabel, null);
    jPanel2.add(jLGLabel, null);
    jPanel2.add(jGOODLabel, null);
    jButtonPanel.add(jPanel4, null);
    jPanel3.add(jNESpinner, null);
    jPanel3.add(jLESpinner, null);
    jPanel3.add(jCESpinner, null);
    jPanel3.add(jLabel8, null);
    jPanel3.add(jLabel10, null);
    jPanel3.add(jLabel9, null);
    jPanel3.add(jLabel5, null);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jButtonPanel.add(jPerLevelCheckBox1, null);
    jButtonPanel.add(jPercentageCheckBox1, null);
    jButtonPanel.add(jLabel11, null);
    jButtonPanel.add(jLabel12, null);
    jButtonPanel.add(jSpecificSpinner1, null);
    jButtonPanel.add(jPanel2, null);
    jPanel4.add(jLabel6, null);
    jPanel4.add(jLabel7, null);
    jPanel4.add(jLNSpinner, null);
    jPanel4.add(jNNSpinner, null);
    jPanel4.add(jCNSpinner, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(jLabel4, null);
    jButtonPanel.add(jPanel3, null);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    this.getContentPane().add(jButtonPanel, BorderLayout.CENTER);
    jButtonPanel.setPreferredSize(new Dimension(600,350));
  }

  void jSaveHonorTypeButton_actionPerformed(ActionEvent e) {
    Honor oHonorType;
    boolean bOverWrite = true;

    if (jNameTextField.getText().length() < 1) {
      oHonorType = (Honor)oParent.lHonorTypes.get(jAwardsComboBox.getSelectedIndex());
      bOverWrite = oParent.AskYN(jSaveHonorTypeButton,"Replace "+oHonorType.sName+"?");
    } else {
      oHonorType = new Honor();
      oHonorType.sName = jNameTextField.getText();
      oHonorType.nHonorTypeID = (oParent.nMaxHonorTypeID++);

      oParent.lHonorTypes.add(oHonorType);
      Collections.sort(oParent.lHonorTypes); // resort
    }

    if (bOverWrite) { // if new or overwrite...
      oHonorType.sDesc = jDescTextArea.getText();
      int nSpecific = Integer.parseInt(jSpecificSpinner1.getValue().toString());
      if (nSpecific == 0) {
        oHonorType.nLawful[oHonorType.ALIGN_GOOD] = Integer.parseInt(jLGSpinner.
            getModel().getValue().toString());
        oHonorType.nLawful[oHonorType.ALIGN_NEUTRAL] = Integer.parseInt(
            jLNSpinner.getModel().getValue().toString());
        oHonorType.nLawful[oHonorType.ALIGN_EVIL] = Integer.parseInt(jLESpinner.
            getModel().getValue().toString());

        oHonorType.nChaotic[oHonorType.ALIGN_GOOD] = Integer.parseInt(
            jCGSpinner.
            getModel().getValue().toString());
        oHonorType.nChaotic[oHonorType.ALIGN_NEUTRAL] = Integer.parseInt(
            jCNSpinner.getModel().getValue().toString());
        oHonorType.nChaotic[oHonorType.ALIGN_EVIL] = Integer.parseInt(
            jCESpinner.
            getModel().getValue().toString());

        oHonorType.nNeutral[oHonorType.ALIGN_GOOD] = Integer.parseInt(
            jNGSpinner.
            getModel().getValue().toString());
        oHonorType.nNeutral[oHonorType.ALIGN_NEUTRAL] = Integer.parseInt(
            jNNSpinner.getModel().getValue().toString());
        oHonorType.nNeutral[oHonorType.ALIGN_EVIL] = Integer.parseInt(
            jNESpinner.
            getModel().getValue().toString());
      }
      else {
        oHonorType.jAwardSpecific.setValue(new Integer(nSpecific));
        oHonorType.bPercentage = jPercentageCheckBox1.isSelected();
        oHonorType.bPerLevel = jPerLevelCheckBox1.isSelected();
      }

//      SaveHonorTypes.toFile(oParent, oParent.sHonorSaveFile,
//                            oParent.lHonorTypes);
      xmlControl.saveDoc(oParent,oParent,Honor.xmlBuildDocFromList(
            oParent.lHonorTypes,oParent.nMaxHonorTypeID),oParent.sHonorSaveFile);
    }

    ResetFieldsToDefault();

  }

  void jCloseWindowButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jRemoveButton_actionPerformed(ActionEvent e) {
    Honor oH = (Honor)oParent.lHonorTypes.get(jAwardsComboBox.getSelectedIndex());
    if (oParent.AskYN(this,"Are you sure you want to remove the honor award "+oH.sName+"?")){

      oParent.lHonorTypes.remove(oH);
      Collections.sort(oParent.lHonorTypes); // resort
//      SaveHonorTypes.toFile(oParent, oParent.sHonorSaveFile,
//                            oParent.lHonorTypes);
      xmlControl.saveDoc(oParent,oParent,Honor.xmlBuildDocFromList(
            oParent.lHonorTypes,oParent.nMaxHonorTypeID),oParent.sHonorSaveFile);
      ResetFieldsToDefault();
      oParent.ShowDone(this,"Removed honor award.");
    }
  }

  void jAwardsComboBox_actionPerformed(ActionEvent e) {
    if (jAwardsComboBox.getSelectedIndex() >= 0) {
      Honor oH = (Honor) oParent.lHonorTypes.get(jAwardsComboBox.
                                                 getSelectedIndex());

//    jNameTextField.setText(oH.sName);
      jDescTextArea.setText(oH.sDesc);
      jSpecificSpinner1.setValue(oH.jAwardSpecific.getValue());
      jPercentageCheckBox1.setSelected(oH.bPercentage);
      jPerLevelCheckBox1.setSelected(oH.bPerLevel);

      jLGSpinner.getModel().setValue(new Integer(oH.nLawful[oH.ALIGN_GOOD]));
      jLNSpinner.getModel().setValue(new Integer(oH.nLawful[oH.ALIGN_NEUTRAL]));
      jLESpinner.getModel().setValue(new Integer(oH.nLawful[oH.ALIGN_EVIL]));

      jCGSpinner.getModel().setValue(new Integer(oH.nChaotic[oH.ALIGN_GOOD]));
      jCNSpinner.getModel().setValue(new Integer(oH.nChaotic[oH.ALIGN_NEUTRAL]));
      jCESpinner.getModel().setValue(new Integer(oH.nChaotic[oH.ALIGN_EVIL]));

      jNGSpinner.getModel().setValue(new Integer(oH.nNeutral[oH.ALIGN_GOOD]));
      jNNSpinner.getModel().setValue(new Integer(oH.nNeutral[oH.ALIGN_NEUTRAL]));
      jNESpinner.getModel().setValue(new Integer(oH.nNeutral[oH.ALIGN_EVIL]));
    }
  }

  void jResetButton_actionPerformed(ActionEvent e) {
    ResetFieldsToDefault();
  }

  void ResetFieldsToDefault()
  {
    jAwardsComboBox.removeAllItems();
    for (int i=0;i<oParent.lHonorTypes.size();i++)
    {
      Honor oHo = (Honor) oParent.lHonorTypes.get(i);
      jAwardsComboBox.addItem(oHo.sName);
    }

    jNameTextField.setText("");
    jDescTextArea.setText("");

    jSpecificSpinner1.setValue(new Integer(0));
    jPercentageCheckBox1.setSelected(false);
    jPerLevelCheckBox1.setSelected(false);

    jLGSpinner.getModel().setValue(new Integer(0));
    jLNSpinner.getModel().setValue(new Integer(0));
    jLESpinner.getModel().setValue(new Integer(0));

    jCGSpinner.getModel().setValue(new Integer(0));
    jCNSpinner.getModel().setValue(new Integer(0));
    jCESpinner.getModel().setValue(new Integer(0));

    jNGSpinner.getModel().setValue(new Integer(0));
    jNNSpinner.getModel().setValue(new Integer(0));
    jNESpinner.getModel().setValue(new Integer(0));

  }

  void jPercentageCheckBox1_actionPerformed(ActionEvent e) {
    if (jPercentageCheckBox1.isSelected() &&
      jPerLevelCheckBox1.isSelected())
  {
    jPerLevelCheckBox1.setSelected(false);
  }

  }

  void jPerLevelCheckBox1_actionPerformed(ActionEvent e) {
    if (jPercentageCheckBox1.isSelected() &&
      jPerLevelCheckBox1.isSelected())
  {
    jPercentageCheckBox1.setSelected(false);
  }

  }

}

class DialogNewHonorAwards_jSaveHonorTypeButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jSaveHonorTypeButton_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveHonorTypeButton_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jCloseWindowButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jCloseWindowButton_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseWindowButton_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jRemoveButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jRemoveButton_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jAwardsComboBox_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jAwardsComboBox_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jAwardsComboBox_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jResetButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jResetButton_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jResetButton_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jPercentageCheckBox1_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jPercentageCheckBox1_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPercentageCheckBox1_actionPerformed(e);
  }
}

class DialogNewHonorAwards_jPerLevelCheckBox1_actionAdapter implements java.awt.event.ActionListener {
  DialogNewHonorAwards adaptee;

  DialogNewHonorAwards_jPerLevelCheckBox1_actionAdapter(DialogNewHonorAwards adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPerLevelCheckBox1_actionPerformed(e);
  }
}
