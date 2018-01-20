package hacksack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class HackSackFrame_AboutBox extends JDialog implements ActionListener {
      String sSupportText = "Thanks to those that have helped add features to HackSACK : ";
      String[] sSupporters = {"Legba","Axegrinder01","Al Beddow"};
      String product = "HackSACK";
      String version = "v2005.06.28 (beta)";
      String copyright = "Copyright (c) 2003-2005";
      String comments = "Hackmaster GM Tool";

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  JLabel label1 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout(0,1);
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jSupportersTextPane = new JTextArea();
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();

  public HackSackFrame_AboutBox(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();

//      jSupportersTextPane.setRows(3);
      jSupportersTextPane.setEditable(false);
      jSupportersTextPane.setWrapStyleWord(true);
      jSupportersTextPane.setLineWrap(true);
      jSupportersTextPane.setText(sSupportText);
      for (int i=0;i<sSupporters.length;i++)
      {
        jSupportersTextPane.append(sSupporters[i]);
        if ((sSupporters.length-i) == 2)
        {
          jSupportersTextPane.append(" and ");
        } else
        if (i+1<sSupporters.length)
          jSupportersTextPane.append(", ");
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(hacksack.HackSackFrame.class.getResource("about.png"));
    imageLabel.setIcon(image1);
    this.setTitle("About");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    insetsPanel1.setLayout(flowLayout1);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBackground(Color.lightGray);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    label1.setText(product+" "+version);
//    label2.setText(version);
    label3.setText(copyright);
    label4.setText(comments);
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBackground(Color.gray);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    button1.setText("Ok");
    button1.addActionListener(this);
    jLabel1.setText("by Mike Wilson, HMGMA TX-1-00396-02, uce_mike@yahoo.com");
    jLabel2.setText("Hackmaster is copyright by Kenzer Co.");
    insetsPanel1.setBackground(Color.lightGray);
    panel2.setBackground(Color.lightGray);
    jSupportersTextPane.setBackground(Color.lightGray);
    jSupportersTextPane.setEnabled(true);
    jSupportersTextPane.setFont(new java.awt.Font("Dialog", 0, 9));
    jSupportersTextPane.setDoubleBuffered(false);
    jSupportersTextPane.setMaximumSize(new Dimension(600, 600));
    jSupportersTextPane.setDisabledTextColor(Color.lightGray);
    jSupportersTextPane.setEditable(false);
    jSupportersTextPane.setText("");
    jScrollPane1.setEnabled(false);
    jPanel1.setLayout(gridLayout2);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(0);
    jPanel1.setBackground(Color.gray);
    panel1.setMaximumSize(new Dimension(600, 600));
    panel1.setMinimumSize(new Dimension(0, 0));
    insetsPanel2.add(imageLabel, null);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(jPanel1, null);
    jPanel1.add(label1, null);
    jPanel1.add(label4, null);
    jPanel1.add(label3, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel2, null);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    insetsPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jSupportersTextPane, null);
    setResizable(true);
    this.pack();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  //Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
