package hacksack;
import java.awt.*;
import java.text.*;
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

public class CellRendererGearTree extends DefaultTreeCellRenderer {
  public CellRendererGearTree() {
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
      if (node.getUserObject() instanceof Gear) {
        Gear oG = (Gear)node.getUserObject();
        int nCount = Integer.parseInt(oG.jMod.getValue().toString());
        NumberFormat fNumberFormat = NumberFormat.getInstance();
        fNumberFormat.setMaximumFractionDigits(2);
        String sWeight = fNumberFormat.format((nCount*oG.dWeight));
        setText("("+oG.jMod.getValue().toString()+"x) "+
                oG.sName+
                 " "+(sWeight+"lb")
                 );
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
