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
* the gm can remove/view a quirk with this
*
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogPlayerQuirkEdit extends JDialog {
  TablePlayer oThisPlayer = null;
  Quirks oEditThis = null;
  DefaultMutableTreeNode oThisNode = null;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jQuirkDetailsPanel = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jDelete = new JButton();
  JButton jCancel = new JButton();
  JButton jSave = new JButton();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  public DialogPlayerQuirkEdit(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogPlayerQuirkEdit(TablePlayer oPlayer, Quirks  oQ,
                               DefaultMutableTreeNode oNode) {
    this(null, "", false);
    this.oThisPlayer = oPlayer;
    this.oEditThis = oQ;
    this.oThisNode = oNode;

    JLabel lName = new JLabel("Name");
    JLabel lDesc = new JLabel("Description");
    JTextArea jNamePanel = new JTextArea(oEditThis.sName);
    JTextArea jDescPanel = new JTextArea(oEditThis.sDesc);
    jNamePanel.setLineWrap(true);
    jNamePanel.setEditable(false);
    jDescPanel.setLineWrap(true);
    jDescPanel.setEditable(false);
    jNamePanel.setWrapStyleWord(true);
    jDescPanel.setWrapStyleWord(true);

    jNamePanel.setBackground(Color.lightGray);
    jDescPanel.setBackground(Color.lightGray);

    jQuirkDetailsPanel.add(lName);
    jQuirkDetailsPanel.add(jNamePanel);
    jQuirkDetailsPanel.add(lDesc);
    jQuirkDetailsPanel.add(jDescPanel);

    this.setTitle("View Quirk");
    this.pack();
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"View, Edit Quirk & Flaws");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.darkGray);
    panel1.setBorder(titledBorder1);
    jQuirkDetailsPanel.setBackground(Color.gray);
    jQuirkDetailsPanel.setLayout(verticalFlowLayout1);
    jPanel2.setBackground(Color.darkGray);
    jDelete.setText("Delete");
    jDelete.addActionListener(new DialogPlayerQuirkEdit_jDelete_actionAdapter(this));
    jCancel.setText("Done");
    jCancel.addActionListener(new DialogPlayerQuirkEdit_jCancel_actionAdapter(this));
    jSave.setText("Save");
    jSave.addActionListener(new DialogPlayerQuirkEdit_jSave_actionAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jCancel, null);
    jPanel2.add(jDelete, null);
    jPanel2.add(jSave, null);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jQuirkDetailsPanel, null);
  }

  void jDelete_actionPerformed(ActionEvent e) {
    oThisPlayer.aQuirks.remove(oEditThis);
    DefaultMutableTreeNode oParentNode =
        (DefaultMutableTreeNode)oThisPlayer.jQuirkTree.getSelectionPath().getPathComponent(1);
    oThisPlayer.mQuirkTreeModel.removeNodeFromParent(oThisNode);
    oThisPlayer.mQuirkTreeModel.reload(oParentNode);
    this.setVisible(false);
//    this.hide();
  }

  void jCancel_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jSave_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  // load up this dialog.
static void LoadDialog(Component oThis, HackSackFrame oParent,
                       TablePlayer oPlayer,
                       Quirks oQ, DefaultMutableTreeNode oNode) {
    DialogPlayerQuirkEdit dlg =
        new DialogPlayerQuirkEdit(oPlayer, oQ, oNode);
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

class DialogPlayerQuirkEdit_jDelete_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerQuirkEdit adaptee;

  DialogPlayerQuirkEdit_jDelete_actionAdapter(DialogPlayerQuirkEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDelete_actionPerformed(e);
  }
}

class DialogPlayerQuirkEdit_jCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerQuirkEdit adaptee;

  DialogPlayerQuirkEdit_jCancel_actionAdapter(DialogPlayerQuirkEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancel_actionPerformed(e);
  }
}

class DialogPlayerQuirkEdit_jSave_actionAdapter implements java.awt.event.ActionListener {
  DialogPlayerQuirkEdit adaptee;

  DialogPlayerQuirkEdit_jSave_actionAdapter(DialogPlayerQuirkEdit adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSave_actionPerformed(e);
  }
}
