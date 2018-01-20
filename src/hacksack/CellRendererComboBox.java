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
 * generic combobox cell in pulldown
 */

public class CellRendererComboBox
    extends DefaultListCellRenderer {
  public CellRendererComboBox() {
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int nIndex,
                                                boolean bisSelected,
                                                boolean bHasFocus) {

    if (value instanceof TablePlayer) {
      TablePlayer oO = (TablePlayer) value;
      setText(oO.sCharacter);
    }
    else
    if (value instanceof TableSavedCreatures) {
      TableSavedCreatures oO = (TableSavedCreatures) value;
      boolean bHasAtks = (oO.lMyAttacks.size() > 0);
      setText(oO.sCreatureName + (!bHasAtks ? "*" : ""));
    }
    else
    if (value instanceof CreatureEncounter) {
      CreatureEncounter oO = (CreatureEncounter) value;
      setText("[" + oO.nLowRange + "-" + oO.nHighRange + "] " + oO.oCreature.sCreatureName);
    }
    else
    if (value instanceof TableSkills) {
      TableSkills oO = (TableSkills) value;
      // if name > 15 chars trunc it
      String sName = oO.sName.replaceAll(".*:","");
      sName = oO.sName.length() > 15 ? oO.sName.substring(0, 15) + "..." :oO.sName;
      setText("[" + oO.oParent.gmSkillTypeTable[oO.nSkillType] + "] " + sName);
    }
    else
    if (value instanceof TableGear) {
      TableGear oO = (TableGear) value;
      // if name > 20 chars trunc it
      String sName = oO.sName.replaceAll(".*:","");
      sName = sName.length() > 20 ? sName.substring(0, 20) + "..." :sName;
      setText("["+oO.sItemType.toLowerCase()+"] "+sName.trim());
    }
    else
    if (value instanceof TableQuirks) {
      TableQuirks oO = (TableQuirks) value;
      // if name > 28 chars trunc it
      String sName = oO.sName.replaceAll(".*:","");
      sName = sName.length() > 28 ? sName.substring(0, 28) + "..." :sName;
      setText(sName.trim());
    }

    else { // just string, not a creature, probably NO-TARGET
      setText( (String) value);
    }

    setFont(new Font("Dialog", Font.PLAIN, 10));
    setBackground(bisSelected ? Color.BLACK : Color.lightGray);
    setForeground(bisSelected ? Color.YELLOW : Color.black);
    return this;
  }

}
