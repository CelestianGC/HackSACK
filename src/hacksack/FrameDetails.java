package hacksack;

import javax.swing.*;
import java.awt.*;
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

public class FrameDetails extends JFrame {
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JButton jButton1 = new JButton();
  Border border2;
  TitledBorder titledBorder2;
  JScrollPane jDetailsScrollPane = new JScrollPane();
  JTextArea jDetailsTextArea = new JTextArea();

  public FrameDetails(String sTitle, String sThisDetails) {
    try {
      jbInit();
      this.setTitle(sTitle);
      jDetailsTextArea.setText(sThisDetails);

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"Details");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Details");
    this.getContentPane().setBackground(SystemColor.control);
    this.addWindowListener(new FrameDetails_this_windowAdapter(this));
    jPanel1.setBackground(Color.gray);
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(borderLayout1);
    jButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton1.setToolTipText("Close window.");
    jButton1.setSelected(false);
    jButton1.setText("Close");
    jButton1.addActionListener(new FrameDetails_jButton1_actionAdapter(this));
    jPanel4.setBackground(Color.gray);
    jPanel4.setBorder(BorderFactory.createRaisedBevelBorder());
    jDetailsTextArea.setBackground(Color.lightGray);
    jDetailsTextArea.setEditable(false);
    jDetailsTextArea.setText("");
    jDetailsTextArea.setLineWrap(true);
    jDetailsTextArea.setWrapStyleWord(true);
    jDetailsScrollPane.setPreferredSize(new Dimension(400, 250));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel4, BorderLayout.SOUTH);
    jPanel4.add(jButton1, null);
    jPanel1.add(jDetailsScrollPane, BorderLayout.CENTER);
    jDetailsScrollPane.getViewport().add(jDetailsTextArea, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
//    this.dispose();
  }

  void this_windowClosed(WindowEvent e) {
  this.setVisible(false);
  //this.hide();
//    this.dispose();
  }
}

class FrameDetails_jButton1_actionAdapter implements java.awt.event.ActionListener {
  FrameDetails adaptee;

  FrameDetails_jButton1_actionAdapter(FrameDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class FrameDetails_this_windowAdapter extends java.awt.event.WindowAdapter {
  FrameDetails adaptee;

  FrameDetails_this_windowAdapter(FrameDetails adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}
