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
* list of players in pulldown
 */

public class CellRendererPlayerBox
    extends DefaultListCellRenderer {
  public CellRendererPlayerBox() {
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int nIndex,
                                                boolean bisSelected,
                                                boolean bHasFocus) {

    setFont(new Font("Dialog", Font.PLAIN, 10));
    setBackground(bisSelected ? Color.BLACK : Color.lightGray);
    setForeground(bisSelected ? Color.YELLOW : Color.black);

    if (value instanceof TablePlayer) {
      TablePlayer oO = (TablePlayer) value;
      if (oO.jAbsent.isSelected() && !bisSelected) {
        setFont(new Font("Dialog", Font.ITALIC, 9));
        setBackground(Color.darkGray);
        setForeground(Color.GRAY);
      }
      if (oO.jHireling.isSelected() &&!bisSelected) {
        setFont(new Font("Dialog", Font.ITALIC, 9));
        setBackground(Color.BLUE);
        setForeground(Color.GRAY);
      }
      setText(oO.sCharacter);
    } else { // just string, not a creature, probably NO-TARGET
      setText((String)value);
    }

    return this;
  }

}
