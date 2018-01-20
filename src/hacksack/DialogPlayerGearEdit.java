package hacksack;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
//import com.borland.jbcl.layout.*;


/**
*  The GM can edit amount of items carried or remove it with this
*
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogPlayerGearEdit extends JDialog {
  HackSackFrame oThisParent = null;
  TablePlayer oThisPlayer = null;
  Gear oEditThis = null;
  DefaultMutableTreeNode oThisNode = null;
  int nSaveCount = 0;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jGearDetailsPanel = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jDelete = new JButton();
  JButton jCancel = new JButton();
  JButton jSave = new JButton();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  public DialogPlayerGearEdit(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogPlayerGearEdit(HackSackFrame oParent,
                              TablePlayer oPlayer, Gear  oG,
                              DefaultMutableTreeNode oNode) {
    this(null, "", false);
    this.oThisParent = oParent;
    this.oThisPlayer = oPlayer;
    this.oEditThis = oG;
    this.oThisNode = oNode;
    this.nSaveCount = Integer.parseInt(oG.jMod.getValue().toString());

    JLabel lName = new JLabel("Name");
    JLabel lDesc = new JLabel("Description");
    JTextArea jNamePanel = new JTextArea(oEditThis.sName);
    JTextArea jDescPanel = new JTextArea(oEditThis.sDesc);
    jNamePanel.setLineWrap(true);
    jNamePanel.setEditable(false);
    jDescPanel.setLineWrap(true);
    jNamePanel.setWrapStyleWord(true);
    jDescPanel.setWrapStyleWord(true);
    jDescPanel.setEditable(false);

    jNamePanel.setBackground(Color.lightGray);
    jDescPanel.setBackground(Color.lightGray);

    jGearDetailsPanel.add(lName);
    jGearDetailsPanel.add(jNamePanel);
    jGearDetailsPanel.add(lDesc);
    jGearDetailsPanel.add(jDescPanel);

    JPanel jCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    jCountPanel.setBackground(Color.GRAY);
    JLabel lCountLabel = new JLabel("Amount");
    jCountPanel.add(lCountLabel);
    jCountPanel.add(oEditThis.jMod);
    jGearDetailsPanel.add(jCountPanel);

    this.setTitle("View Gear");
    this.pack();
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"View, Edit Gear & Flaws");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.darkGray);
    panel1.setBorder(titledBorder1);
    jGearDetailsPanel.setBackground(Color.gray);
    jGearDetailsPanel.setLayout(verticalFlowLayout1);
    jPanel2.setBackground(Color.darkGray);
    jDelete.setText("Delete");
    jDelete.addActionListener(new DialogPlayerGearEdit_jDelete_actionAdapter(this));
    jCancel.setText("Done");
    jCancel.addActionListener(new DialogPlayerGearEdit_jCancel_actionAdapter(this));
    jSave.setText("Save");
    jSave.addActionListener(new DialogPlayerGearEdit_jSave_actionAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jCancel, null);
    jPanel2.add(jDelete, null);
    jPanel2.add(jSave, null);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jGearDetailsPanel, null);
  }

  void jDelete_actionPerformed(ActionEvent e) {
    oThisPlayer.aGear.remove(oEditThis);
    DefaultMutableTreeNode oParentNode =
        (DefaultMutableTreeNode)oThisPlayer.jGearTree.getSelectionPath().getPathComponent(1);
    oThisPlayer.mGearTreeModel.removeNodeFromParent(oThisNode);
    oThisPlayer.mGearTreeModel.reload(oParentNode);
    TableEncum.setEncumLabel(oThisParent.lEncumTable,oThisPlayer);
    this.setVisible(false);
//    this.hide();
  }

  void jCancel_actionPerformed(ActionEvent e) {
    oEditThis.jMod.setValue(new Integer(nSaveCount));
    this.setVisible(false);
//    this.hide();
  }

  void jSave_actionPerformed(ActionEvent e) {
    // actually saved since stored in var, just set to encum values
    oEditThis.nCount = Integer.parseInt(oEditThis.jMod.getValue().toString());
    TableEncum.setEncumLabel(oThisParent.lEncumTable,oThisPlayer);
    this.setVisible(false);
//    this.hide();
  }

  // load up this dialog.
static void LoadDialog(Component oThis, HackSackFrame oParent,
                       TablePlayer oPlayer,
                       Gear oG, DefaultMutableTreeNode oNode) {
    DialogPlayerGearEdit dlg =
        new DialogPlayerGearEdit(oParent,oPlayer, oG, oNode);
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

class DialogPlayerGearEdit_jDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerGearEdit adaptee;

  DialogPlayerGearEdit_jDelete_actionAdapter(DialogPlayerGearEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDelete_actionPerformed(e);
  }
}

class DialogPlayerGearEdit_jCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerGearEdit adaptee;

  DialogPlayerGearEdit_jCancel_actionAdapter(DialogPlayerGearEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancel_actionPerformed(e);
  }
}

class DialogPlayerGearEdit_jSave_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerGearEdit adaptee;

  DialogPlayerGearEdit_jSave_actionAdapter(DialogPlayerGearEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSave_actionPerformed(e);
  }
}
