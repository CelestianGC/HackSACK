package hacksack;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003,2004</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class FrameStartUp extends JFrame {
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JProgressBar jStartUpBar = new JProgressBar();
  Border border3;
  TitledBorder titledBorder3;

  public FrameStartUp() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,new Color(130, 130, 130),new Color(91, 91, 91),new Color(30, 30, 30),new Color(44, 44, 44));
    titledBorder3 = new TitledBorder(border3,"HackSACK Starting");
    this.getContentPane().setLayout(borderLayout1);

    jPanel1.setBackground(Color.darkGray);
    jPanel1.setForeground(Color.red);
    jPanel1.setBorder(titledBorder3);
    jPanel1.setPreferredSize(new Dimension(360, 40));
    jPanel1.setLayout(borderLayout2);
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jStartUpBar,  BorderLayout.CENTER);
    titledBorder3.setTitleColor(Color.lightGray);
    jStartUpBar.setForeground(Color.RED);
    this.setUndecorated(true);
  }
}
