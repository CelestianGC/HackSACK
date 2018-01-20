package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
//import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003,2004</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNPCArmorSelect
    extends JDialog {
  JPanel panel1 = new JPanel();
  HackSackFrame oParent = null;
  CreatureCore oCreature = null;
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jButtonPanel1 = new JPanel();
  JButton jSelectArmorButton = new JButton();
  JButton jCancelButton = new JButton();
  JPanel jArmorSelectPanel = new JPanel();
  DefaultListModel mArmorList = new DefaultListModel();
  JList jArmorList = new JList(mArmorList);
  DefaultListModel mShieldList = new DefaultListModel();
  JList jShieldList = new JList(mShieldList);
  Border border2;
  JPanel jArmorListPanel1 = new JPanel();
  JPanel jShieldListPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  Border border3;
  TitledBorder titledBorder2;
  Border border4;
  TitledBorder titledBorder3;
  //BoxLayout2 boxLayout21 = new BoxLayout2();
  GridBagLayout boxLayout21 = new GridBagLayout();

  public DialogNPCArmorSelect(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNPCArmorSelect(HackSackFrame oParent, CreatureCore oC) {
    this(null, "", false);
    this.oParent = oParent;
    this.oCreature = oC;

    jArmorList.setCellRenderer(new CellRendererGearBox());
    mArmorList.removeAllElements();
    mArmorList.addElement("NATURAL-ARMOR");

    jShieldList.setCellRenderer(new CellRendererGearBox());
    mShieldList.removeAllElements();
    mShieldList.addElement("NO-SHIELD");

    for (int i = 0; i < oParent.lGear.size(); i++) {
      TableGear oG = (TableGear) oParent.lGear.get(i);
      if (oG.sItemType.equalsIgnoreCase(oG.ITEM_TYPE_ARMOR)) {
        mArmorList.addElement(oG);
      }
      if (oG.sItemType.equalsIgnoreCase(oG.ITEM_TYPE_SHIELD)) {
        mShieldList.addElement(oG);
      }
    }

    if (oCreature.oArmorWorn != null) {
      jArmorList.setSelectedValue(TableGear.GetGearFromID(oParent,oCreature.oArmorWorn.nGearID),true);
      if (jArmorList.getSelectedIndex() < 0)
        jArmorList.setSelectedIndex(0);
    } else jArmorList.setSelectedIndex(0);

    if (oCreature.oShieldWorn != null) {
      jShieldList.setSelectedValue(TableGear.GetGearFromID(oParent,oCreature.oShieldWorn.nGearID),true);
      if (jShieldList.getSelectedIndex() < 0)
        jShieldList.setSelectedIndex(0);
    } else jShieldList.setSelectedIndex(0);

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1, "NPC Pick Armor & Shield");
    border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border3,"Shields");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder3 = new TitledBorder(border4,"Armor");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.gray);
    panel1.setBorder(titledBorder1);
    jButtonPanel1.setBackground(Color.gray);
    jButtonPanel1.setBorder(border2);
    jSelectArmorButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSelectArmorButton.setText("Select");
    jSelectArmorButton.addActionListener(new DialogNPCArmorSelect_jSelectArmorButton_actionAdapter(this));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setText("Cancel");
    jCancelButton.addActionListener(new DialogNPCArmorSelect_jCancelButton_actionAdapter(this));
    jArmorSelectPanel.setLayout(boxLayout21);
    jShieldList.setBackground(Color.lightGray);
    jArmorList.setBackground(Color.lightGray);
    jShieldListPanel1.setLayout(borderLayout2);
    jArmorListPanel1.setLayout(borderLayout3);
    jShieldListPanel1.setBackground(Color.lightGray);
    jShieldListPanel1.setBorder(titledBorder2);
    jArmorListPanel1.setBackground(Color.lightGray);
    jArmorListPanel1.setBorder(titledBorder3);
    jArmorSelectPanel.add(jShieldListPanel1, null);
    jShieldListPanel1.add(jShieldList, BorderLayout.CENTER);
    jArmorSelectPanel.add(jArmorListPanel1, null);
    jArmorListPanel1.add(jArmorList, BorderLayout.CENTER);
    getContentPane().add(panel1);
    panel1.add(jButtonPanel1, BorderLayout.SOUTH);
    jButtonPanel1.add(jSelectArmorButton, null);
    jButtonPanel1.add(jCancelButton, null);
    panel1.add(jArmorSelectPanel, BorderLayout.CENTER);
  }

// load up a dialog
static void DoDialogNPCArmorSelect(HackSackFrame oParent,
                                     Component oComponent,
                                   CreatureCore oC) {
  DialogNPCArmorSelect dlg = new DialogNPCArmorSelect(oParent,oC);
//     dlg.panel1.setPreferredSize(new Dimension(560,540));
     Dimension dlgSize = dlg.getPreferredSize();
     Dimension frmSize = oComponent.getSize();
     Point loc = oComponent.getLocation();
     dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                     (frmSize.height - dlgSize.height) / 2 + loc.y);
     dlg.setTitle("Armor & Shield Select");
     dlg.setModal(true);
     dlg.pack();
     dlg.setVisible(true);
}

  void jSelectArmorButton_actionPerformed(ActionEvent e) {
    if (mArmorList.getSize() > 0) {
      Object oObj = jArmorList.getSelectedValue();
      if (oObj instanceof String) {
        oCreature.oArmorWorn = null;
        oCreature.lArmorHPAdj.setEnabled(false);
        oCreature.tShieldHPAdj.setEnabled(false);
      }
      else
      if (oObj instanceof TableGear) {
        TableGear oG = (TableGear) oObj;
        oCreature.lArmorHPAdj.setEnabled(true);
        oCreature.tShieldHPAdj.setEnabled(true);
        oCreature.oArmorWorn = new Gear();
        oCreature.oArmorWorn.sItemType = oG.sItemType;
        oCreature.oArmorWorn.nGearID = oG.nGearID;
        oCreature.oArmorWorn.sName = oG.sName;
        oCreature.oArmorWorn.nACAbsorb = oG.nACAbsorb;
        oCreature.oArmorWorn.nACBase = oG.nACBase;
        oCreature.oArmorWorn.nACBaseCurrent = oG.nACBase;
        oCreature.oArmorWorn.nACBulkIndex = oG.nACBulkIndex;
        oCreature.oArmorWorn.nACMagicBonus = oG.nACMagicBonus;
        oCreature.oArmorWorn.nACMagicBonusCurrent = oG.nACMagicBonus;
        // set magic bonus health to the max health of the armor;
        oCreature.oArmorWorn.nACMagicBonusHealth = oG.nACHP[oG.nACBase];
        oCreature.oArmorWorn.nACMagicBonusHealthCurrent = oG.nACHP[oG.nACBase];

        for (int i = oCreature.oArmorWorn.AC_0; i <= oCreature.oArmorWorn.AC_9; i++) {
          oCreature.oArmorWorn.nACHP[i] = oG.nACHP[i];
          oCreature.oArmorWorn.nACHPCurrent[i] = oG.nACHP[i];
        }
      }
    }
    if (mShieldList.getSize() > 0) {
      Object oObj = jShieldList.getSelectedValue();
      if (oObj instanceof String) {
        oCreature.oShieldWorn = null;
      }
      else
      if (oObj instanceof TableGear) {
        TableGear oG = (TableGear) oObj;
        oCreature.oShieldWorn = new Gear();
        oCreature.oShieldWorn.sItemType = oG.sItemType;
        oCreature.oShieldWorn.nGearID = oG.nGearID;
        oCreature.oShieldWorn.sName = oG.sName;
        oCreature.oShieldWorn.nShieldACBase = oG.nShieldACBase;
        oCreature.oShieldWorn.nShieldACBaseCurrent = oG.nShieldACBase;
        oCreature.oShieldWorn.nShieldACMagicBonus = oG.nShieldACMagicBonus;
        oCreature.oShieldWorn.nShieldACMagicBonusCurrent = oG.nShieldACMagicBonus;
        // set magic bonus health to the max health of the armor;
        oCreature.oShieldWorn.nShieldACMagicHealth = oG.nShieldHP[oG.nShieldACBase];
        oCreature.oShieldWorn.nShieldACMagicHealthCurrent = oG.nShieldHP[oG.nShieldACBase];

        for (int i = oCreature.oShieldWorn.SHIELD_1; i <= oCreature.oShieldWorn.nShieldACBase; i++) {
          oCreature.oShieldWorn.nShieldHP[i] = oG.nShieldHP[i];
          oCreature.oShieldWorn.nShieldHPCurrent[i] = oG.nShieldHP[i];
        }
      }
    }
  oCreature.setArmorClassLabel();
  this.setVisible(false);
//    this.hide();
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

}

class DialogNPCArmorSelect_jSelectArmorButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNPCArmorSelect adaptee;

  DialogNPCArmorSelect_jSelectArmorButton_actionAdapter(DialogNPCArmorSelect adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSelectArmorButton_actionPerformed(e);
  }
}

class DialogNPCArmorSelect_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNPCArmorSelect adaptee;

  DialogNPCArmorSelect_jCancelButton_actionAdapter(DialogNPCArmorSelect adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}
