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

public class CellRendererSkillTree
    extends DefaultTreeCellRenderer {
  public CellRendererSkillTree() {
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                boolean selected,
                                                boolean expanded,
                                                boolean leaf, int row,
                                                boolean hasFocus) {
    if (value instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
      if (node.getUserObject() instanceof String) {
        setText( (String) node.getUserObject());
      }
      else
      if (node.getUserObject() instanceof Skills) {
        Skills oS = (Skills)node.getUserObject();
//        setText( ( (Skills) node.getUserObject()).sName);
        setText((oS.nSkillType==oS.TYPE_SKILL?
                 ("["+oS.jSkillSpinner.getValue().toString()+"%] "+oS.sName):
                 oS.sName));
      }
      if (selected) {
        setBackground(getBackgroundSelectionColor()); // background dont set for some reason
        setForeground(getTextSelectionColor());
      }
      else {
        setBackground(getBackgroundNonSelectionColor()); // background dont set for some reason
        setForeground(getTextNonSelectionColor());
      }
    }
    return this;
  }
}
