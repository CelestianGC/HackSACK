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

public class DialogDetails extends JDialog {
  JPanel panel1 = new JPanel();
//  String sDesc = null;
  Border border1;
  TitledBorder titledBorder1;
//  JPanel jWorkPanel = new JPanel();

  JPanel jPanel2 = new JPanel();
  JButton jCloseButton = new JButton();
  GridLayout gridLayout1 = new GridLayout(0,1);
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jDetailsScrollPane = new JScrollPane();
  JTextArea jDetailsTextArea = new JTextArea();

  public DialogDetails(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogDetails(String sDesc, String sTitle) {
    this(null, "", false);
    this.setTitle(sTitle);

//    jDetailsTextArea.setName("\n"+sDesc+"\n");
    jDetailsTextArea.setText(sDesc);
    jDetailsTextArea.setFont(new Font("Dialog",Font.PLAIN,12));
    jDetailsTextArea.setEditable(false);
    jDetailsTextArea.setBackground(Color.lightGray);
//    int nHeight = jDetailsScrollPane.getHeight()+60;
//    int nWidth = 400;
//    jDetailsTextArea.setText("\n"+sDesc+"("+nHeight+")("+nWidth+")\n");
//    panel1.setPreferredSize(new Dimension(nWidth,nHeight));
//    jDetailsTextArea.scrollRectToVisible(jDetailsScrollPane.getVisibleRect());
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Details");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.gray);
    panel1.setBorder(titledBorder1);

    jCloseButton.setToolTipText("Close details panel.");
    jCloseButton.setText("Close");
    jCloseButton.addActionListener(new DialogDetails_jCloseButton_actionAdapter(this));
    jPanel2.setBackground(Color.gray);
    jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel2.setPreferredSize(new Dimension(200, 41));
    jDetailsTextArea.setBackground(Color.lightGray);
    jDetailsTextArea.setFont(new java.awt.Font("Dialog", 0, 11));
    jDetailsTextArea.setDebugGraphicsOptions(0);
    jDetailsTextArea.setEditable(false);
    jDetailsTextArea.setText("");
    jDetailsTextArea.setColumns(0);
    jDetailsTextArea.setLineWrap(true);
    jDetailsTextArea.setWrapStyleWord(true);
    jDetailsScrollPane.setPreferredSize(new Dimension(400, 250));
    getContentPane().add(panel1);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jCloseButton, null);
    panel1.add(jDetailsScrollPane, BorderLayout.CENTER);
    jDetailsScrollPane.getViewport().add(jDetailsTextArea, null);
//    oC.jNameLabel.scrollRectToVisible(
//                  oC.oParent.fBattleSheetFrame.jBattleSheetScrollPane1.
//                  getVisibleRect());

  }

  void jCloseButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }
}

class DialogDetails_jCloseButton_actionAdapter implements java.awt.event.ActionListener {
  DialogDetails adaptee;

  DialogDetails_jCloseButton_actionAdapter(DialogDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCloseButton_actionPerformed(e);
  }
}
