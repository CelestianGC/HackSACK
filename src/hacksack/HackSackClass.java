package hacksack;

import javax.swing.UIManager;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class HackSackClass {
  boolean packFrame = false;
  HackSackFrame frame;

  //Construct the application
  public HackSackClass() {
    try {
      frame = new HackSackFrame();
      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame) {
        frame.pack();
      }
      else {
        frame.validate();
      }
      //Center the window
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setSize(1000, 740);
//    frame.setSize(frame.getPreferredSize());
      Dimension frameSize = frame.getSize();

      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      /*
              String sX = frame.Prefs.getProperty("pref-mainX");
          String sY = frame.Prefs.getProperty("pref-mainY");
          String sHx = frame.Prefs.getProperty("pref-mainHx");
          String sHy = frame.Prefs.getProperty("pref-mainYx");
          if (sX != null && sY != null & sHx != null && sHy != null) {
            int nX = Integer.parseInt(sX);
            int nY = Integer.parseInt(sY);
            int nHx = Integer.parseInt(sHx);
            int nHy = Integer.parseInt(sHy);

            frame.setLocation(nX, nY);
            frame.setSize(nHx, nHy);
          } else
       */
      frame.setLocation( (screenSize.width - frameSize.width) / 2,
                        (screenSize.height - frameSize.height) / 2);
      frame.setVisible(true);
    }
    catch (Exception err) {
      frame.ShowError(frame,"Exception\n"+err.getCause().toString());
    }
  }

  //Main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new HackSackClass();
  }

}
