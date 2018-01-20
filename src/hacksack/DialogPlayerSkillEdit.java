package hacksack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;

//import com.borland.jbcl.layout.*;

/**
* the GM can edit a skill rank, view a skill with this
*
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogPlayerSkillEdit extends JDialog {
//  HackSackFrame oParent = null;
  TablePlayer oThisPlayer = null;
  Skills oEditThis = null;
  int nSavedSkillRank = 1;
  DefaultMutableTreeNode oThisNode = null;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jEditPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JPanel jPanel2 = new JPanel();
  JButton jDelete = new JButton();
  JButton jCancel = new JButton();
  JButton jSave = new JButton();
  JTextArea lNameArea = null;
  JTextArea lDescArea = null;
  JLabel lSkillRank = null;
  Border border2;
  TitledBorder titledBorder2;
  JScrollPane jScrollPane1 = new JScrollPane();

  public DialogPlayerSkillEdit(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogPlayerSkillEdit(TablePlayer oPlayer, Skills oS,
                               DefaultMutableTreeNode oNode) {
    this(null, "", false);
    this.oEditThis = oS;
    this.oThisPlayer = oPlayer;
    this.oThisNode = oNode;
    this.nSavedSkillRank = Integer.parseInt(
      oS.jSkillSpinner.getValue().toString());


    lNameArea = new JTextArea(oEditThis.sName);
    JLabel lNameLabel = new JLabel("Name");
//    lNameLabel.setForeground(Color.white);
    JLabel lDescLabel = new JLabel("Description");
//    lDescLabel.setForeground(Color.white);
    lDescArea = new JTextArea(oEditThis.sDesc);
    lDescArea.setBackground(Color.lightGray);
    lNameArea.setBackground(Color.lightGray);
    lDescArea.setEditable(false);
    lNameArea.setEditable(false);
    lNameArea.setWrapStyleWord(true);
    lDescArea.setWrapStyleWord(true);
    lDescArea.setLineWrap(true);
    lNameArea.setLineWrap(true);
    jEditPanel.add(lNameLabel);
    jEditPanel.add(lNameArea);
    jEditPanel.add(lDescLabel);
    jEditPanel.add(lDescArea);

    if (oEditThis.nSkillType == oEditThis.TYPE_ABILITY) {
    } else
    if (oEditThis.nSkillType == oEditThis.TYPE_PROF) {
    } else
    if (oEditThis.nSkillType == oEditThis.TYPE_SKILL) {
      JPanel jRankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      jRankPanel.setBackground(Color.GRAY);
      lSkillRank = new JLabel("Skill Rank");
      jRankPanel.add(lSkillRank);
      jRankPanel.add(oEditThis.jSkillSpinner);
      jEditPanel.add(jRankPanel);
    } else
    if (oEditThis.nSkillType == oEditThis.TYPE_TALENT) {
    }

    this.setTitle("View Skill");
    this.pack();
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"View, Edit, Delete Player Skills");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(130, 130, 130),new Color(91, 91, 91),new Color(30, 30, 30),new Color(44, 44, 44));
    titledBorder2 = new TitledBorder(border2,"View, Edit, Remove Skills");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.darkGray);
    panel1.setForeground(Color.white);
    panel1.setAlignmentY((float) 0.5);
    panel1.setBorder(titledBorder2);
    jEditPanel.setLayout(verticalFlowLayout1);
    jEditPanel.setBackground(Color.gray);
    jDelete.setText("Delete");
    jDelete.addActionListener(new DialogPlayerSkillEdit_jDelete_actionAdapter(this));
    jCancel.setText("Done");
    jCancel.addActionListener(new DialogPlayerSkillEdit_jCancel_actionAdapter(this));
    jSave.setText("Save");
    jSave.addActionListener(new DialogPlayerSkillEdit_jSave_actionAdapter(this));
    jPanel2.setBackground(Color.darkGray);
    getContentPane().add(panel1);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jCancel, null);
    jPanel2.add(jDelete, null);
    jPanel2.add(jSave, null);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jEditPanel, null);
  }

  void jDelete_actionPerformed(ActionEvent e) {
    oThisPlayer.aSkills.remove(oEditThis);
    DefaultMutableTreeNode oParentNode =
        (DefaultMutableTreeNode)oThisPlayer.jSkillTree.getSelectionPath().getPathComponent(1);
    oThisPlayer.mTreeModel.removeNodeFromParent(oThisNode);
    oThisPlayer.mTreeModel.reload(oParentNode);
    this.setVisible(false);
//    this.hide();
  }

  void jCancel_actionPerformed(ActionEvent e) {
    oEditThis.jSkillSpinner.setValue(new Integer(nSavedSkillRank));
    this.setVisible(false);
//    this.hide();
  }

  void jSave_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }              // this just here to make them feel better

// load up this dialog.
static void LoadDialog(Component oThis, HackSackFrame oParent,
                       TablePlayer oPlayer,
                       Skills oS, DefaultMutableTreeNode oNode) {
    DialogPlayerSkillEdit dlg =
        new DialogPlayerSkillEdit(oPlayer, oS, oNode);
    Dimension dlgSize = dlg.panel1.getPreferredSize();
    dlg.setSize(dlg.getWidth() + 20, dlg.getHeight() + 40);
    Dimension frmSize = oThis.getSize();
    Point loc = oThis.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.setVisible(true);
  }

}

class DialogPlayerSkillEdit_jDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerSkillEdit adaptee;

  DialogPlayerSkillEdit_jDelete_actionAdapter(DialogPlayerSkillEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDelete_actionPerformed(e);
  }
}

class DialogPlayerSkillEdit_jCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerSkillEdit adaptee;

  DialogPlayerSkillEdit_jCancel_actionAdapter(DialogPlayerSkillEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancel_actionPerformed(e);
  }
}

class DialogPlayerSkillEdit_jSave_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerSkillEdit adaptee;

  DialogPlayerSkillEdit_jSave_actionAdapter(DialogPlayerSkillEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSave_actionPerformed(e);
  }
}
