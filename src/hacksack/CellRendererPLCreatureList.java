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
* list of monsters in battle sheet, target of this player
 */

public class CellRendererPLCreatureList
    extends DefaultListCellRenderer {
  public CellRendererPLCreatureList() {
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int nIndex,
                                                boolean bisSelected,
                                                boolean bHasFocus) {

    if (value instanceof CreatureCore) {
      CreatureCore oC = (CreatureCore) value;
      setText(oC.sCreatureName);
    } else { // just string, not a creature, probably NO-TARGET
      setText((String)value);
    }

    setFont(new Font("Dialog", Font.PLAIN, 10));
    setBackground(bisSelected ? Color.BLACK : Color.lightGray);
    setForeground(bisSelected ? Color.YELLOW : Color.black);
    return this;
  }

}
