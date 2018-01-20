package hacksack;

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

public class DialogImportCharacterName extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oCharacter = null;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel2 = new JPanel();
  JLabel jImportNameLabel = new JLabel();
  JTextField jCharacterName = new JTextField();
  JLabel jImportPlayerNameLabel = new JLabel();
  JTextField jPlayerName = new JTextField();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

  public DialogImportCharacterName(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogImportCharacterName(HackSackFrame oP, TablePlayer oC) {
    this(null, "", false);
    oParent = oP;
    oCharacter = oC;

    jCharacterName.setText(oCharacter.sCharacter);
    jPlayerName.setText(oCharacter.sPlayerName);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
    titledBorder1 = new TitledBorder(border1,"Import Character Name");
    panel1.setLayout(borderLayout1);
    jButton1.setToolTipText("");
    jButton1.setText("Done");
    jButton1.addActionListener(new DialogImportCharacterName_jButton1_actionAdapter(this));
    jPanel1.setBackground(Color.lightGray);
    panel1.setBackground(Color.gray);
    panel1.setBorder(titledBorder1);
    jImportNameLabel.setToolTipText("The imported character\'s name.");
    jImportNameLabel.setText("Character Name");
    jCharacterName.setMinimumSize(new Dimension(11, 23));
    jCharacterName.setPreferredSize(new Dimension(220, 23));
    jCharacterName.setRequestFocusEnabled(true);
    jCharacterName.setText("");
    jImportPlayerNameLabel.setToolTipText("The player name of this character.");
    jImportPlayerNameLabel.setText("Player Name");
    jPlayerName.setPreferredSize(new Dimension(220, 23));
    jPlayerName.setText("");
    jPanel2.setLayout(verticalFlowLayout1);
    jPanel2.setBackground(Color.gray);
    this.addWindowListener(new DialogImportCharacterName_this_windowAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jButton1, null);
    panel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jImportNameLabel, null);
    jPanel2.add(jCharacterName, null);
    jPanel2.add(jImportPlayerNameLabel, null);
    jPanel2.add(jPlayerName, null);
  }

  // setup the names now.
  void SetNames() {
    oCharacter.sCharacter = jCharacterName.getText();
    oCharacter.sPlayerName = jPlayerName.getText();
  }
  void jButton1_actionPerformed(ActionEvent e) {
    SetNames();
    this.setVisible(false);
//    this.hide();
  }

  void this_windowClosed(WindowEvent e) {
    SetNames();
  }
}

class DialogImportCharacterName_jButton1_actionAdapter implements java.awt.event.ActionListener {
  DialogImportCharacterName adaptee;

  DialogImportCharacterName_jButton1_actionAdapter(DialogImportCharacterName adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class DialogImportCharacterName_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogImportCharacterName adaptee;

  DialogImportCharacterName_this_windowAdapter(DialogImportCharacterName adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}
