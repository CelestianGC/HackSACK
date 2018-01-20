package hacksack;

import java.awt.*;
import javax.swing.*;


/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
*  combobox cell in pulldown for encounters with name only
 */

public class CellRendererEncountersGenerated
    extends DefaultListCellRenderer {
  public CellRendererEncountersGenerated() {
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int nIndex,
                                                boolean bisSelected,
                                                boolean bHasFocus) {

    if (value instanceof CreatureEncounter) {
      CreatureEncounter oO = (CreatureEncounter) value;
      setText(oO.oCreature.sCreatureName);
    }

    else { // just string, not a creature, probably NO-TARGET
      setText((String)value);
    }

    setFont(new Font("Dialog", Font.PLAIN, 10));
    setBackground(bisSelected ? Color.BLACK : Color.lightGray);
    setForeground(bisSelected ? Color.YELLOW : Color.black);
    return this;
  }

}
