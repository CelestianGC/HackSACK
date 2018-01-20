package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class CellRendererBSCreatureList
    extends DefaultListCellRenderer {
  public CellRendererBSCreatureList() {
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int nIndex,
                                                boolean bisSelected,
                                                boolean bHasFocus) {

    if (value instanceof CreatureCore) {
      CreatureCore oC = (CreatureCore) value;
      int nCurrentHP = 1;
      if (oC.jHitPointLabel.getText().matches("(-\\d+)|(\\d+)")) {
        nCurrentHP = Integer.parseInt(oC.jHitPointLabel.getText());
      }
      setText(oC.sCreatureName);

      // set text red if I am aggro
      if (!bisSelected) {
        setFont(new Font("Dialog", Font.PLAIN, 10));
        setBackground(Color.lightGray);
        setForeground(Color.BLACK);
        if (oC.jIgnored.isSelected()) {
          setFont(new Font("Dialog", Font.ITALIC, 10));
          setForeground(Color.BLUE);
        }
        // creature dead or near it
        else if (nCurrentHP <= 0) {
          setFont(new Font("Dialog", Font.ITALIC, 10));
          setBackground(Color.gray);
        }
        // creature has init ready
        else if (oC.bAggro) {
          setFont(new Font("Dialog", Font.BOLD, 10));
          setForeground(Color.RED);
        }
      }
      else {
        setFont(new Font("Dialog", Font.PLAIN, 10));
        setBackground(bisSelected ? Color.BLACK : Color.lightGray);
        setForeground(bisSelected ? Color.YELLOW : Color.black);
      }
    }
    return this;
  }

}
