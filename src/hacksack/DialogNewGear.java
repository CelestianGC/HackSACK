package hacksack;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

//import com.borland.jbcl.layout.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogNewGear
    extends JDialog {
  HackSackFrame oParent = null;
  TablePlayer oPlayer = null;
  TableGear oEditItem = null;
  TableGear oCurrentItem = null;
  int nGearID = -1;
  String sGearType = null;

  JPanel jDetailsPanel = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jNameTextField = new JTextField();
  JLabel jLabel2 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jDescTextArea = new JTextArea();
  JLabel jLabel3 = new JLabel();
  JButton jAddButton = new JButton();
  JButton jCancelButton = new JButton();
  Border border1;
  TitledBorder titledBorder1;
  JTextField jLocationTextField = new JTextField();
  JLabel jLabel4 = new JLabel();
  JSpinner jCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
  JLabel jLabel5 = new JLabel();
  JSpinner jWeightSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 0.1));
  JButton jSaveItemButton = new JButton();
  JButton jRemoveButton = new JButton();
  JPanel jButtonPanel = new JPanel();
  JLabel jCurLabel = new JLabel();
  JLabel jCurrentItemLabel = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jWeaponPanel = new JPanel();
  JPanel jContainerPanel = new JPanel();
  JRadioButton jGearRadio = new JRadioButton();
  JRadioButton jWeaponRadio = new JRadioButton();
  JRadioButton jContainerRadio = new JRadioButton();
  JPanel jItemTypePanel = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  Border border4;
  TitledBorder titledBorder4;
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jDamHuge = new JTextField();
  JLabel jLabel9 = new JLabel();
  JTextField jDamSmall = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextField jDamTiny = new JTextField();
  JLabel jLabel11 = new JLabel();
  JTextField jDamGigantic = new JTextField();
  JLabel jLabel12 = new JLabel();
  JTextField jDamLarge = new JTextField();
  JTextField jDamMedium = new JTextField();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JRadioButton jTypeHackRadio = new JRadioButton();
  JRadioButton jTypePierceRadio = new JRadioButton();
  JRadioButton jTypeCrushRadio = new JRadioButton();
  JLabel jLabel15 = new JLabel();
  JSpinner jMaxWeightCarriedSpinner = new JSpinner();
  JLabel jLabel16 = new JLabel();
  JSpinner jSpeedFactorSpinner = new JSpinner(new SpinnerNumberModel(0, -20, 20,
      1));
  JRadioButton jArmorRadio = new JRadioButton();
  JPanel jArmorPanel = new JPanel();
  JRadioButton jShieldRadio = new JRadioButton();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jACHPPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  GridLayout gridLayout2 = new GridLayout();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel19 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel jLabel21 = new JLabel();
  JLabel jLabel22 = new JLabel();
  JLabel jLabel23 = new JLabel();
  JLabel jLabel24 = new JLabel();
  JLabel jLabel25 = new JLabel();
  JLabel jLabel26 = new JLabel();
  JSpinner jAC8Spinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC5Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC6Spinner3 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC2Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC3Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC7Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC0Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC4Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC1Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JSpinner jAC9Spinner10 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JPanel jACSpecsPanel = new JPanel();
  JLabel jLabel27 = new JLabel();
  JSpinner jACBaseSpinner1 = new JSpinner(new SpinnerNumberModel(9, 0, 9, 1));
  JLabel jLabel28 = new JLabel();
  JComboBox jACBulkComboBox1 = new JComboBox();

  JLabel jLabel29 = new JLabel();
  JSpinner jACAbsorbSpinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLabel30 = new JLabel();
  JSpinner jACMagicBonusSpinner3 = new JSpinner(new SpinnerNumberModel(0, 0, 50,
      1));
  Border border5;
  TitledBorder titledBorder5;
  JPanel jShieldPanel1 = new JPanel();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel31 = new JLabel();
  JSpinner jShieldACBaseSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5,
      1));
  JLabel jLabel32 = new JLabel();
  JSpinner jShieldACMagicBonusSpinner = new JSpinner(new SpinnerNumberModel(0,
      0, 50, 1));
  JPanel jShieldACPanel2 = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  JLabel jLabel33 = new JLabel();
  JSpinner jShieldAC4Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JLabel jLabel34 = new JLabel();
  JSpinner jShieldAC1Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JLabel jLabel35 = new JLabel();
  JSpinner jShieldAC3Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JLabel jLabel36 = new JLabel();
  JSpinner jShieldAC2Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  JLabel jLabel37 = new JLabel();
  JSpinner jShieldAC5Spinner = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
  Border border6;
  TitledBorder titledBorder6;
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel jPanel2 = new JPanel();
  Border border7;
  TitledBorder titledBorder7;
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout2 = new BorderLayout();
  DefaultListModel mListGear = new DefaultListModel();
  JList jListGear = new JList(mListGear);
  JPanel jPanel3 = new JPanel();
  Border border8;
  Border border9;
  JPanel jPanel4 = new JPanel();
  Border border10;
  GridLayout gridLayout4 = new GridLayout();
  JRadioButton jGearShieldRadioButton1 = new JRadioButton();
  JRadioButton jGearArmorRadioButton2 = new JRadioButton();
  JRadioButton jGearWeaponRadioButton3 = new JRadioButton();
  JRadioButton jGearContRadioButton4 = new JRadioButton();
  JRadioButton jGearAllRadioButton = new JRadioButton();
  JLabel jLabel6 = new JLabel();
  JTextField jFilterTextField1 = new JTextField();

  public DialogNewGear(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogNewGear(HackSackFrame oParent,
                       TablePlayer oPlayer,
                       Gear oEditGear,
                       String sThisGearType) {

    this(null, "", false);
    this.oPlayer = oPlayer;
    this.oParent = oParent;

    if (oEditGear != null)
    this.oEditItem = TableGear.GetGearFromID(oParent,oEditGear.nGearID);

  // this will default the item filter to show the type of item
  // the player is editing.
  if (oEditItem != null && sThisGearType == null)
    sThisGearType = oEditItem.sItemType;

  if (sThisGearType != null) {
    jGearAllRadioButton.setSelected(false);
    jGearArmorRadioButton2.setSelected(false);
    jGearContRadioButton4.setSelected(false);
    jGearShieldRadioButton1.setSelected(false);
    jGearWeaponRadioButton3.setSelected(false);
    if (sThisGearType.equalsIgnoreCase(TableGear.ITEM_TYPE_ARMOR)) {
      jGearArmorRadioButton2.setSelected(true);
    }
    if (sThisGearType.equalsIgnoreCase(TableGear.ITEM_TYPE_CONTAINER)) {
      jGearContRadioButton4.setSelected(true);
    }
    if (sThisGearType.equalsIgnoreCase(TableGear.ITEM_TYPE_SHIELD)) {
      jGearShieldRadioButton1.setSelected(true);
    }
    if (sThisGearType.equalsIgnoreCase(TableGear.ITEM_TYPE_WEAPON)) {
      jGearWeaponRadioButton3.setSelected(true);
    }
    if (sThisGearType.equalsIgnoreCase(TableGear.ITEM_TYPE_GEAR)) {
      jGearAllRadioButton.setSelected(true);
    }

  }

//    jACBulkComboBox1 = new JComboBox(oParent.gmBulkyness);
    jACBulkComboBox1.removeAllItems();
    for (int i = 0; i < oParent.gmBulkyness.length; i++) {
      jACBulkComboBox1.addItem(oParent.gmBulkyness[i]);

    }

    Collections.sort(oParent.lGear);

    if (oPlayer == null) {
      jAddButton.setEnabled(false);

    }

    RebuildGearList();
    if (oEditItem != null)
      jListGear.setSelectedValue(oEditItem,true);

    jGearRadio_actionPerformed(null);
    jWeaponRadio_actionPerformed(null); // force these to run to set
    jContainerRadio_actionPerformed(null); // force these to run to set

  }

  private void jbInit() throws Exception {
    border10 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    jListGear.setCellRenderer(new CellRendererComboBox());
    jListGear.addListSelectionListener(new
                                       DialogNewGear_jListGear_listSelectionAdapter(this));
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, Color.white, new Color(115, 114, 105),
        new Color(165, 163, 151)), "New Item in Inventory");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2, "Item Type");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder3 = new TitledBorder(border3, "Container Details");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder4 = new TitledBorder(border4, "Weapon Details");
    border5 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder5 = new TitledBorder(border5, "Armor");
    border6 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder6 = new TitledBorder(border6, "Shield");
    border7 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder7 = new TitledBorder(border7, "Gear List");
    border8 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    border9 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    jDetailsPanel.setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel1.setToolTipText("Name of this item.");
    jLabel1.setText("New Item");
    jLabel1.setBounds(new Rectangle(6, 53, 75, 15));
    jNameTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jNameTextField.setPreferredSize(new Dimension(57, 21));
    jNameTextField.setText("");
    jNameTextField.setBounds(new Rectangle(83, 50, 264, 21));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel2.setToolTipText("Description of the item.");
    jLabel2.setText("Description");
    jLabel2.setBounds(new Rectangle(6, 162, 74, 15));
    jScrollPane1.setBounds(new Rectangle(81, 113, 264, 113));
    jDescTextArea.setFont(new java.awt.Font("Dialog", 0, 9));
    jDescTextArea.setText("");
    jDescTextArea.setLineWrap(true);
    jDescTextArea.setWrapStyleWord(true);

    jDescTextArea.setTabSize(8);
    jLabel3.setEnabled(false);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel3.setToolTipText("Where the item is located.");
    jLabel3.setText("Location");
    jLabel3.setBounds(new Rectangle(6, 230, 73, 15));
    jAddButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jAddButton.setToolTipText("Add this item to the players gear list.");
    jAddButton.setText("Add To Player");
    jAddButton.addActionListener(new DialogNewGear_jAddButton_actionAdapter(this));
    jCancelButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCancelButton.setToolTipText("Done.");
    jCancelButton.setText("Done");
    jCancelButton.addActionListener(new
                                    DialogNewGear_jCancelButton_actionAdapter(this));
    jDetailsPanel.setBackground(Color.lightGray);
    jDetailsPanel.setBorder(titledBorder1);
    jLocationTextField.setEnabled(false);
    jLocationTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jLocationTextField.setEditable(false);
    jLocationTextField.setText("");
    jLocationTextField.setBounds(new Rectangle(81, 230, 264, 21));
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel4.setToolTipText("Number of these items to add.");
    jLabel4.setText("Number");
    jLabel4.setBounds(new Rectangle(6, 255, 71, 15));
    jCountSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jCountSpinner.setBounds(new Rectangle(81, 255, 46, 21));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setToolTipText("Weight of the item.");
    jLabel5.setText("Weight");
    jLabel5.setBounds(new Rectangle(140, 255, 34, 15));
    jWeightSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jWeightSpinner.setBounds(new Rectangle(182, 255, 46, 21));
    jSaveItemButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jSaveItemButton.setToolTipText("Save this item to use for other players.");
    jSaveItemButton.setText("Save Item");
    jSaveItemButton.addActionListener(new
                                      DialogNewGear_jSaveItemButton_actionAdapter(this));
    jRemoveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveButton.setToolTipText("Delete this inventory item.");
    jRemoveButton.setText("Remove");
    jRemoveButton.addActionListener(new
                                    DialogNewGear_jRemoveButton_actionAdapter(this));
    jButtonPanel.setBackground(Color.gray);
    jButtonPanel.setBorder(border9);
    jCurLabel.setFont(new java.awt.Font("Dialog", 0, 11));
    jCurLabel.setText("Selected Item");
    jCurLabel.setBounds(new Rectangle(6, 20, 73, 18));
    jCurrentItemLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    jCurrentItemLabel.setText("Nothing selected...");
    jCurrentItemLabel.setBounds(new Rectangle(83, 20, 470, 18));
    this.getContentPane().setLayout(borderLayout1);
    this.setForeground(Color.black);
    jWeaponPanel.setBackground(Color.lightGray);
    jWeaponPanel.setFont(new java.awt.Font("Dialog", 0, 9));
    jWeaponPanel.setBorder(titledBorder4);
    jWeaponPanel.setBounds(new Rectangle(6, 287, 389, 106));
    jWeaponPanel.setLayout(null);
    jContainerPanel.setBackground(Color.lightGray);
    jContainerPanel.setBorder(titledBorder3);
    jContainerPanel.setBounds(new Rectangle(402, 287, 144, 106));
    jGearRadio.setBackground(Color.lightGray);
    jGearRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jGearRadio.setToolTipText("Normal gear item.");
    jGearRadio.setSelected(true);
    jGearRadio.setText("Gear");
    jGearRadio.addActionListener(new DialogNewGear_jGearRadio_actionAdapter(this));
    jWeaponRadio.setBackground(Color.lightGray);
    jWeaponRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jWeaponRadio.setToolTipText("Item is a weapon.");
    jWeaponRadio.setText("Weapon");
    jWeaponRadio.addActionListener(new DialogNewGear_jWeaponRadio_actionAdapter(this));
    jContainerRadio.setBackground(Color.lightGray);
    jContainerRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jContainerRadio.setToolTipText("Item is a Container.");
    jContainerRadio.setText("Container");
    jContainerRadio.addActionListener(new
                                      DialogNewGear_jContainerRadio_actionAdapter(this));
    jItemTypePanel.setBackground(Color.lightGray);
    jItemTypePanel.setFont(new java.awt.Font("Dialog", 0, 9));
    jItemTypePanel.setBorder(titledBorder2);
    jItemTypePanel.setBounds(new Rectangle(351, 44, 198, 81));
    jItemTypePanel.setLayout(gridLayout1);
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel7.setToolTipText("Damange to Tiny creatures.");
    jLabel7.setText("T");
    jLabel7.setBounds(new Rectangle(57, 30, 5, 12));
    jLabel8.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel8.setToolTipText("Damange to Small creatures.");
    jLabel8.setText("S");
    jLabel8.setBounds(new Rectangle(108, 30, 6, 12));
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel9.setToolTipText("Damange to Tiny creatures.");
    jLabel9.setText("M");
    jLabel9.setBounds(new Rectangle(160, 30, 7, 12));
    jDamSmall.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamSmall.setText("1d6");
    jDamSmall.setBounds(new Rectangle(119, 28, 36, 17));
    jDamSmall.addFocusListener(new DialogNewGear_jDamSmall_focusAdapter(this));
    jDamTiny.setToolTipText("");
    jLabel10.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel10.setToolTipText("Damange to Large creatures.");
    jLabel10.setText("L");
    jLabel10.setBounds(new Rectangle(213, 30, 5, 12));
    jDamTiny.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamTiny.setText("1d6");
    jDamTiny.setBounds(new Rectangle(67, 28, 36, 17));
    jDamTiny.addFocusListener(new DialogNewGear_jDamTiny_focusAdapter(this));
    jLabel11.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel11.setToolTipText("Damange to Huge creatures.");
    jLabel11.setText("H");
    jLabel11.setBounds(new Rectangle(264, 30, 7, 12));
    jDamGigantic.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamGigantic.setText("1d6");
    jDamGigantic.setBounds(new Rectangle(329, 28, 36, 17));
    jDamGigantic.addFocusListener(new DialogNewGear_jDamGigantic_focusAdapter(this));
    jLabel12.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel12.setToolTipText("Damange to Gigantic creatures.");
    jLabel12.setText("G");
    jLabel12.setBounds(new Rectangle(317, 30, 7, 12));
    jDamLarge.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamLarge.setText("1d6");
    jDamLarge.setBounds(new Rectangle(223, 28, 36, 17));
    jDamLarge.addFocusListener(new DialogNewGear_jDamLarge_focusAdapter(this));
    jDamHuge.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamHuge.setText("1d6");
    jDamHuge.setBounds(new Rectangle(276, 28, 36, 17));
    jDamHuge.addFocusListener(new DialogNewGear_jDamHuge_focusAdapter(this));
    jDamMedium.setFont(new java.awt.Font("Dialog", 0, 9));
    jDamMedium.setText("1d6");
    jDamMedium.setBounds(new Rectangle(172, 28, 36, 17));
    jDamMedium.addFocusListener(new DialogNewGear_jDamMedium_focusAdapter(this));
    jLabel13.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel13.setText("Damage: ");
    jLabel13.setBounds(new Rectangle(11, 30, 41, 12));
    jLabel14.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel14.setToolTipText(
        "Type of weapon this is, Hacking, Piercing or Crushing");
    jLabel14.setText("Weapon Type");
    jLabel14.setBounds(new Rectangle(11, 56, 58, 12));
    jTypeHackRadio.setBackground(Color.lightGray);
    jTypeHackRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jTypeHackRadio.setSelected(true);
    jTypeHackRadio.setText("Hack");
    jTypeHackRadio.setBounds(new Rectangle(74, 50, 51, 25));
    jTypeHackRadio.addActionListener(new
                                     DialogNewGear_jTypeHackRadio_actionAdapter(this));
    jTypePierceRadio.setBackground(Color.lightGray);
    jTypePierceRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jTypePierceRadio.setText("Pierce");
    jTypePierceRadio.setBounds(new Rectangle(130, 50, 55, 25));
    jTypePierceRadio.addActionListener(new
                                       DialogNewGear_jTypePierceRadio_actionAdapter(this));
    jTypeCrushRadio.setBackground(Color.lightGray);
    jTypeCrushRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jTypeCrushRadio.setText("Crush");
    jTypeCrushRadio.setBounds(new Rectangle(190, 50, 53, 25));
    jTypeCrushRadio.addActionListener(new
                                      DialogNewGear_jTypeCrushRadio_actionAdapter(this));
    jLabel15.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel15.setToolTipText(
        "Max weight that can be carried inside this container.");
    jLabel15.setText("Max Carried Wt");
    jMaxWeightCarriedSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel16.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel16.setText("Speed Factor");
    jLabel16.setBounds(new Rectangle(11, 80, 56, 12));
    jSpeedFactorSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jSpeedFactorSpinner.setBounds(new Rectangle(74, 77, 51, 18));
    jArmorRadio.setBackground(Color.lightGray);
    jArmorRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jArmorRadio.setText("Armor");
    jArmorRadio.addActionListener(new DialogNewGear_jArmorRadio_actionAdapter(this));
    jArmorPanel.setBackground(Color.lightGray);
    jArmorPanel.setBorder(titledBorder5);
    jArmorPanel.setBounds(new Rectangle(6, 397, 540, 121));
    jArmorPanel.setLayout(verticalFlowLayout1);
    jShieldRadio.setBackground(Color.lightGray);
    jShieldRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldRadio.setText("Shield");
    jShieldRadio.addActionListener(new DialogNewGear_jShieldRadio_actionAdapter(this));
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(0);
    gridLayout1.setRows(0);
    jACHPPanel.setLayout(gridLayout2);
    gridLayout2.setColumns(10);
    gridLayout2.setRows(2);
    jACHPPanel.setBackground(Color.lightGray);
    jLabel17.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel17.setToolTipText("HP for AC 0.");
    jLabel17.setText("AC0");
    jLabel18.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel18.setToolTipText("HP for AC 1");
    jLabel18.setText("AC1");
    jLabel19.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel19.setToolTipText("HP for AC 2");
    jLabel19.setText("AC2");
    jLabel20.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel20.setToolTipText("HP for AC 3");
    jLabel20.setText("AC3");
    jLabel21.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel21.setToolTipText("HP for AC 4");
    jLabel21.setText("AC4");
    jLabel22.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel22.setToolTipText("HP for AC 5");
    jLabel22.setText("AC5");
    jLabel23.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel23.setToolTipText("HP for AC 6");
    jLabel23.setText("AC6");
    jLabel24.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel24.setToolTipText("HP for AC 7");
    jLabel24.setText("AC7");
    jLabel25.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel25.setToolTipText("HP for AC 8");
    jLabel25.setText("AC8");
    jLabel26.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel26.setToolTipText("HP for AC 9");
    jLabel26.setText("AC9");
    jLabel27.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel27.setToolTipText("AC granted by wearing.");
    jLabel27.setText("AC");
    jLabel28.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel28.setToolTipText("Bulk of the armor.");
    jLabel28.setText("Bulk");
    jLabel29.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel29.setToolTipText("The damage absorbed per hit dice (typically 1).");
    jLabel29.setText("Absorb");
    jACSpecsPanel.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jLabel30.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel30.setToolTipText("Bonus AC granted by magic.");
    jLabel30.setText("Magical Bonus");
    jACBaseSpinner1.setFont(new java.awt.Font("Dialog", 0, 9));
    jACBaseSpinner1.addChangeListener(new
                                      DialogNewGear_jACBaseSpinner1_changeAdapter(this));
    jACMagicBonusSpinner3.setFont(new java.awt.Font("Dialog", 0, 9));
    jACAbsorbSpinner2.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC9Spinner10.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC8Spinner1.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC7Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC6Spinner3.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC5Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC4Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC3Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC2Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC1Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jAC0Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jACSpecsPanel.setBackground(Color.lightGray);
    jShieldPanel1.setBackground(Color.lightGray);
    jShieldPanel1.setBorder(titledBorder6);
    jShieldPanel1.setBounds(new Rectangle(351, 151, 198, 101));
    jShieldPanel1.setLayout(verticalFlowLayout2);
    verticalFlowLayout2.setHgap(0);
    verticalFlowLayout2.setVgap(0);
    jLabel31.setBackground(Color.lightGray);
    jLabel31.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel31.setToolTipText("Additional AC granted by this shield.");
    jLabel31.setText("AC");
    jLabel32.setBackground(Color.lightGray);
    jLabel32.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel32.setToolTipText("Magical bonus to this shield.");
    jLabel32.setText("Magic Bonus");
    jShieldACBaseSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldACBaseSpinner.addChangeListener(new
                                           DialogNewGear_jShieldACBaseSpinner_changeAdapter(this));
    jShieldACMagicBonusSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldACPanel2.setBackground(Color.lightGray);
    jShieldACPanel2.setLayout(gridLayout3);
    gridLayout3.setRows(2);
    jLabel33.setBackground(Color.lightGray);
    jLabel33.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel33.setToolTipText("Hitpoints for this additional AC.");
    jLabel33.setText("AC+1");
    jLabel34.setBackground(Color.lightGray);
    jLabel34.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel34.setToolTipText("Hitpoints for this additional AC.");
    jLabel34.setText("AC+4");
    jLabel35.setBackground(Color.lightGray);
    jLabel35.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel35.setToolTipText("Hitpoints for this additional AC.");
    jLabel35.setText("AC+3");
    jLabel36.setBackground(Color.lightGray);
    jLabel36.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel36.setToolTipText("Hitpoints for this additional AC.");
    jLabel36.setText("AC+2");
    jLabel37.setBackground(Color.lightGray);
    jLabel37.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel37.setToolTipText("Hitpoints for this additional AC.");
    jLabel37.setText("AC+5");
    jShieldAC1Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldAC2Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldAC3Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldAC4Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jShieldAC5Spinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setBorder(titledBorder7);
    jPanel2.setLayout(borderLayout2);
    jListGear.setBackground(Color.lightGray);
    jPanel3.setBackground(Color.gray);
    jPanel3.setBorder(border8);
    jPanel4.setBackground(Color.lightGray);
    jPanel4.setBorder(border10);
    jPanel4.setLayout(gridLayout4);
    gridLayout4.setColumns(2);
    gridLayout4.setRows(3);
    jGearShieldRadioButton1.setBackground(Color.lightGray);
    jGearShieldRadioButton1.setFont(new java.awt.Font("Dialog", 0, 11));
    jGearShieldRadioButton1.setToolTipText("List shield inventory types.");
    jGearShieldRadioButton1.setSelected(true);
    jGearShieldRadioButton1.setText("Shields");
    jGearShieldRadioButton1.addActionListener(new DialogNewGear_jGearShieldRadioButton1_actionAdapter(this));
    jGearArmorRadioButton2.setBackground(Color.lightGray);
    jGearArmorRadioButton2.setFont(new java.awt.Font("Dialog", 0, 11));
    jGearArmorRadioButton2.setToolTipText("List armor inventory types.");
    jGearArmorRadioButton2.setSelected(true);
    jGearArmorRadioButton2.setText("Armor");
    jGearArmorRadioButton2.addActionListener(new DialogNewGear_jGearArmorRadioButton2_actionAdapter(this));
    jGearWeaponRadioButton3.setBackground(Color.lightGray);
    jGearWeaponRadioButton3.setFont(new java.awt.Font("Dialog", 0, 11));
    jGearWeaponRadioButton3.setToolTipText("List weapon inventory types.");
    jGearWeaponRadioButton3.setSelected(true);
    jGearWeaponRadioButton3.setText("Weapons");
    jGearWeaponRadioButton3.addActionListener(new DialogNewGear_jGearWeaponRadioButton3_actionAdapter(this));
    jGearContRadioButton4.setBackground(Color.lightGray);
    jGearContRadioButton4.setFont(new java.awt.Font("Dialog", 0, 11));
    jGearContRadioButton4.setToolTipText("List container inventory types.");
    jGearContRadioButton4.setSelected(true);
    jGearContRadioButton4.setText("Containers");
    jGearContRadioButton4.addActionListener(new DialogNewGear_jGearContRadioButton4_actionAdapter(this));
    jGearAllRadioButton.setBackground(Color.lightGray);
    jGearAllRadioButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jGearAllRadioButton.setToolTipText("List gear inventory types.");
    jGearAllRadioButton.setSelected(true);
    jGearAllRadioButton.setText("Gear");
    jGearAllRadioButton.addActionListener(new DialogNewGear_jGearAllRadioButton_actionAdapter(this));
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel6.setText("Find");
    jFilterTextField1.setPreferredSize(new Dimension(70, 20));
    jFilterTextField1.setText("");
    jFilterTextField1.addKeyListener(new DialogNewGear_jFilterTextField1_keyAdapter(this));
    jArmorPanel.add(jACSpecsPanel, null);
    jItemTypePanel.add(jGearRadio, null);
    jItemTypePanel.add(jArmorRadio, null);
    jItemTypePanel.add(jContainerRadio, null);
    jItemTypePanel.add(jShieldRadio, null);
    jItemTypePanel.add(jWeaponRadio, null);
    jDetailsPanel.add(jContainerPanel, null);
    jDetailsPanel.add(jCurLabel, null);
    jDetailsPanel.add(jItemTypePanel, null);
    jDetailsPanel.add(jLabel1, null);
    jDetailsPanel.add(jNameTextField, null);
    jWeaponPanel.add(jLabel13, null);
    jWeaponPanel.add(jLabel7, null);
    jWeaponPanel.add(jDamTiny, null);
    jWeaponPanel.add(jLabel8, null);
    jWeaponPanel.add(jDamSmall, null);
    jWeaponPanel.add(jLabel9, null);
    jWeaponPanel.add(jDamMedium, null);
    jWeaponPanel.add(jLabel10, null);
    jWeaponPanel.add(jDamLarge, null);
    jWeaponPanel.add(jLabel11, null);
    jWeaponPanel.add(jDamHuge, null);
    jWeaponPanel.add(jLabel12, null);
    jWeaponPanel.add(jDamGigantic, null);
    jWeaponPanel.add(jLabel14, null);
    jWeaponPanel.add(jTypeHackRadio, null);
    jWeaponPanel.add(jTypePierceRadio, null);
    jWeaponPanel.add(jTypeCrushRadio, null);
    jWeaponPanel.add(jLabel16, null);
    jWeaponPanel.add(jSpeedFactorSpinner, null);
    jDetailsPanel.add(jShieldPanel1, null);
    jDetailsPanel.add(jArmorPanel, null);
    jArmorPanel.add(jACHPPanel, null);
    this.getContentPane().add(jDetailsPanel, BorderLayout.CENTER);
    jButtonPanel.add(jCancelButton, null);
    jButtonPanel.add(jSaveItemButton, null);
    jButtonPanel.add(jAddButton, null);
    this.getContentPane().add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jListGear, null);
    jDetailsPanel.add(jLabel2, null);
    jDetailsPanel.add(jScrollPane1, null);
    jDetailsPanel.add(jLabel3, null);
    jDetailsPanel.add(jLocationTextField, null);
    jDetailsPanel.add(jLabel4, null);
    jDetailsPanel.add(jCountSpinner, null);
    jDetailsPanel.add(jLabel5, null);
    jDetailsPanel.add(jWeightSpinner, null);
    jScrollPane1.getViewport().add(jDescTextArea, null);
    jDetailsPanel.add(jCurrentItemLabel, null);
    this.getContentPane().add(jButtonPanel, BorderLayout.SOUTH);
    jContainerPanel.add(jLabel15, null);
    jContainerPanel.add(jMaxWeightCarriedSpinner, null);
    jDetailsPanel.add(jWeaponPanel, null);
    jACHPPanel.add(jLabel17, null);
    jACHPPanel.add(jLabel18, null);
    jACHPPanel.add(jLabel19, null);
    jACHPPanel.add(jLabel20, null);
    jACHPPanel.add(jLabel21, null);
    jACHPPanel.add(jLabel22, null);
    jACHPPanel.add(jLabel23, null);
    jACHPPanel.add(jLabel24, null);
    jACHPPanel.add(jLabel25, null);
    jACHPPanel.add(jLabel26, null);
    jACHPPanel.add(jAC0Spinner, null);
    jACHPPanel.add(jAC1Spinner, null);
    jACHPPanel.add(jAC2Spinner, null);
    jACHPPanel.add(jAC3Spinner, null);
    jACHPPanel.add(jAC4Spinner, null);
    jACHPPanel.add(jAC5Spinner, null);
    jACHPPanel.add(jAC6Spinner3, null);
    jACHPPanel.add(jAC7Spinner, null);
    jACHPPanel.add(jAC8Spinner1, null);
    jACHPPanel.add(jAC9Spinner10, null);
    jACSpecsPanel.add(jLabel27, null);
    jACSpecsPanel.add(jACBaseSpinner1, null);
    jACSpecsPanel.add(jLabel28, null);
    jACBulkComboBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jACSpecsPanel.add(jACBulkComboBox1, null);
    jACSpecsPanel.add(jLabel29, null);
    jACSpecsPanel.add(jACAbsorbSpinner2, null);
    jACSpecsPanel.add(jLabel30, null);
    jACSpecsPanel.add(jACMagicBonusSpinner3, null);
    jPanel1.add(jLabel31, null);
    jPanel1.add(jShieldACBaseSpinner, null);
    jPanel1.add(jLabel32, null);
    jPanel1.add(jShieldACMagicBonusSpinner, null);
    jShieldPanel1.add(jShieldACPanel2, null);
    jShieldPanel1.add(jPanel1, null);
    jShieldACPanel2.add(jLabel37, null);
    jShieldACPanel2.add(jLabel34, null);
    jShieldACPanel2.add(jLabel35, null);
    jShieldACPanel2.add(jLabel36, null);
    jShieldACPanel2.add(jLabel33, null);
    jShieldACPanel2.add(jShieldAC5Spinner, null);
    jShieldACPanel2.add(jShieldAC4Spinner, null);
    jShieldACPanel2.add(jShieldAC3Spinner, null);
    jShieldACPanel2.add(jShieldAC2Spinner, null);
    jShieldACPanel2.add(jShieldAC1Spinner, null);
    jPanel2.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jLabel6, null);
    jPanel3.add(jFilterTextField1, null);
    jPanel3.add(jRemoveButton, null);
    jPanel2.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(jGearAllRadioButton, null);
    jPanel4.add(jGearArmorRadioButton2, null);
    jPanel4.add(jGearContRadioButton4, null);
    jPanel4.add(jGearShieldRadioButton1, null);
    jPanel4.add(jGearWeaponRadioButton3, null);
  }

  void jAddButton_actionPerformed(ActionEvent e) {
    if (oCurrentItem != null) {
      Gear oGear = new Gear();
      TableGear oGG = oCurrentItem;
      if (jNameTextField.getText().length() < 1) {
        oGear.sName = oGG.sName;
      }
      else {
        oGear.sName = jNameTextField.getText();

      }
      oGear.sDesc = jDescTextArea.getText();
      oGear.nCount = Integer.parseInt(jCountSpinner.getModel().getValue().
                                      toString());
      oGear.dWeight = Double.parseDouble(jWeightSpinner.getModel().getValue().
                                         toString());
      oGear.jMod.getModel().setValue(new Integer(Integer.parseInt(jCountSpinner.
          getModel().getValue().toString())));
      oGear.nGearID = nGearID;
      oGear.sLoc = jLocationTextField.getText();

      if (jGearRadio.isSelected()) {
        oGear.sItemType = oGear.ITEM_TYPE_WEAPON;
        oGear.sWeaponGroup = oGear.WEAPON_GROUP_MELEE; // this all we have right now
        if (jTypePierceRadio.isSelected()) {
          oGear.sWeaponType = oGear.WEAPON_TYPE_PIERCE;
        }
        else
        if (jTypeCrushRadio.isSelected()) {
          oGear.sWeaponType = oGear.WEAPON_TYPE_CRUSH;
        }
        else {
          oGear.sWeaponType = oGear.WEAPON_TYPE_HACK;

        }
        oGear.nSpeedFactor = Integer.parseInt(jSpeedFactorSpinner.getValue().
                                              toString());

        oGear.jDamage[oGear.DAMAGE_SIZE_TINY].setText(jDamTiny.getText());
        oGear.jDamage[oGear.DAMAGE_SIZE_SMALL].setText(jDamSmall.getText());
        oGear.jDamage[oGear.DAMAGE_SIZE_MEDIUM].setText(jDamMedium.getText());
        oGear.jDamage[oGear.DAMAGE_SIZE_LARGE].setText(jDamLarge.getText());
        oGear.jDamage[oGear.DAMAGE_SIZE_HUGE].setText(jDamHuge.getText());
        oGear.jDamage[oGear.DAMAGE_SIZE_GIANT].setText(jDamGigantic.getText());

      }
      else
      if (jContainerRadio.isSelected()) {
        oGear.sItemType = oGear.ITEM_TYPE_CONTAINER;
        oGear.dMaxWeightContained = Double.parseDouble(jMaxWeightCarriedSpinner.
            getValue().toString());
      }
      else {
        oGear.sItemType = oGear.ITEM_TYPE_GEAR;
      }

      oPlayer.aGear.add(oGear);
      DefaultMutableTreeNode oNewNode = new DefaultMutableTreeNode(oGear.sName);
      oNewNode.setUserObject(oGear);
      if (oGear.sLoc.equalsIgnoreCase("NOT-CARRIED")) {
        oPlayer.jGearUnCarriedNode.add(oNewNode);
        oPlayer.mGearTreeModel.reload(oPlayer.jGearUnCarriedNode);
      }
      else {
        oPlayer.jGearNode.add(oNewNode);
        oPlayer.mGearTreeModel.reload(oPlayer.jGearNode);
      }

      TableEncum.setEncumLabel(oParent.lEncumTable, oPlayer);
      oParent.ShowDone(this, "Item added to player.");
//      this.hide();
    }
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jSaveItemButton_actionPerformed(ActionEvent e) {
    boolean bOverWrite = true;
    TableGear oNew = null;
    if (jNameTextField.getText().length() > 0) {
      oNew = new TableGear();
      oNew.sName = jNameTextField.getText();
      oNew.nGearID = (oParent.nMaxGearID++);
      oParent.lGear.add(oNew);
    }
    else
    if (oCurrentItem != null) {
//      oNew = (TableGear) oParent.lGear.get(jItemComboBox.getSelectedIndex());
      oNew = oCurrentItem;
      bOverWrite = oParent.AskYN(this,
                                 "Are you sure you want to replace the gear " +
                                 oNew.sName + "?");
    }
    if (bOverWrite) { // new or replace
      oNew.sDesc = jDescTextArea.getText();
      oNew.dWeight = Double.parseDouble(jWeightSpinner.getModel().getValue().
                                        toString());

      if (jGearRadio.isSelected()) { // generic gear
        oNew.sItemType = oNew.ITEM_TYPE_GEAR;
      }
      else
      if (jShieldRadio.isSelected()) {
        oNew.sItemType = oNew.ITEM_TYPE_SHIELD;
        oNew.nShieldACBase = Integer.parseInt(jShieldACBaseSpinner.getValue().
                                              toString());

        oNew.nShieldACMagicBonus = Integer.parseInt(jShieldACMagicBonusSpinner.
            getValue().toString());
        oNew.nShieldHP[oNew.SHIELD_1] = Integer.parseInt(jShieldAC1Spinner.
            getValue().toString());
        oNew.nShieldHP[oNew.SHIELD_2] = Integer.parseInt(jShieldAC2Spinner.
            getValue().toString());
        oNew.nShieldHP[oNew.SHIELD_3] = Integer.parseInt(jShieldAC3Spinner.
            getValue().toString());
        oNew.nShieldHP[oNew.SHIELD_4] = Integer.parseInt(jShieldAC4Spinner.
            getValue().toString());
        oNew.nShieldHP[oNew.SHIELD_5] = Integer.parseInt(jShieldAC5Spinner.
            getValue().toString());
      }
      else
      if (jArmorRadio.isSelected()) {
        oNew.sItemType = oNew.ITEM_TYPE_ARMOR;
        oNew.nACBase = Integer.parseInt(jACBaseSpinner1.getValue().toString());
        oNew.nACBulkIndex = jACBulkComboBox1.getSelectedIndex();
        oNew.nACAbsorb = Integer.parseInt(jACAbsorbSpinner2.getValue().toString());
        oNew.nACMagicBonus = Integer.parseInt(jACMagicBonusSpinner3.getValue().
                                              toString());

        oNew.nACHP[oNew.AC_0] = Integer.parseInt(jAC0Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_1] = Integer.parseInt(jAC1Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_2] = Integer.parseInt(jAC2Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_3] = Integer.parseInt(jAC3Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_4] = Integer.parseInt(jAC4Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_5] = Integer.parseInt(jAC5Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_6] = Integer.parseInt(jAC6Spinner3.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_7] = Integer.parseInt(jAC7Spinner.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_8] = Integer.parseInt(jAC8Spinner1.getValue().
                                                 toString());
        oNew.nACHP[oNew.AC_9] = Integer.parseInt(jAC9Spinner10.getValue().
                                                 toString());

      }
      else
      if (jShieldRadio.isSelected()) {

      }
      else
      if (jWeaponRadio.isSelected()) { // weapon
        oNew.sItemType = oNew.ITEM_TYPE_WEAPON;
        oNew.nSpeedFactor = Integer.parseInt(jSpeedFactorSpinner.getValue().
                                             toString());
        oNew.sWeaponGroup = oNew.WEAPON_GROUP_MELEE; // dont have anything but melee right now
        if (jTypeCrushRadio.isSelected()) {
          oNew.sWeaponType = oNew.WEAPON_TYPE_CRUSH;
        }
        if (jTypeHackRadio.isSelected()) {
          oNew.sWeaponType = oNew.WEAPON_TYPE_HACK;
        }
        if (jTypePierceRadio.isSelected()) {
          oNew.sWeaponType = oNew.WEAPON_TYPE_PIERCE;

        }
        oNew.jDamage[oNew.DAMAGE_SIZE_TINY].setText(jDamTiny.getText());
        oNew.jDamage[oNew.DAMAGE_SIZE_SMALL].setText(jDamSmall.getText());
        oNew.jDamage[oNew.DAMAGE_SIZE_MEDIUM].setText(jDamMedium.getText());
        oNew.jDamage[oNew.DAMAGE_SIZE_LARGE].setText(jDamLarge.getText());
        oNew.jDamage[oNew.DAMAGE_SIZE_HUGE].setText(jDamHuge.getText());
        oNew.jDamage[oNew.DAMAGE_SIZE_GIANT].setText(jDamGigantic.getText());
      }
      else
      if (jContainerRadio.isSelected()) { // container
        oNew.sItemType = oNew.ITEM_TYPE_CONTAINER;
        oNew.dMaxWeightContained =
            Double.parseDouble(jMaxWeightCarriedSpinner.getValue().toString());
      }

//      SaveGear.toFile(oParent, oParent.sGearSaveFile, oParent.lGear);
      xmlControl.saveDoc(oParent, oParent,
                         TableGear.xmlBuildDocFromList(oParent.lGear,
          oParent.nMaxGearID), oParent.sGearSaveFile);

      // we added new item, rebuild weapon select boxes for battle sheet
      // creatures if any
      for (int i = 0; i < oParent.lCreatures.size(); i++) {
        CreatureCore oCreature = (CreatureCore) oParent.lCreatures.get(i);
        for (int ii = 0; ii < oCreature.lAttacks.size(); ii++) {
          CreatureCombat oAtk = (CreatureCombat) oCreature.lAttacks.get(ii);
          oAtk.setupWeaponUsedBox(oParent.lGear);
        }
      }

    }

    RebuildGearList();

    jNameTextField.setText("");
  }

  /*   void jItemComboBox_actionPerformed(ActionEvent e) {
      if (jItemComboBox.getSelectedIndex() >= 0) {
        TableGear oO = (TableGear) oParent.lGear.get(jItemComboBox.
            getSelectedIndex());
        jCurrentItemLabel.setText(oO.sName);

        jDescTextArea.setText(oO.sDesc);

        jLocationTextField.setText("");
        jWeightSpinner.getModel().setValue(new Double(oO.dWeight));

        // container
        if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_CONTAINER)) {
          jContainerRadio.setSelected(true);
          jContainerRadio_actionPerformed(null);
   jMaxWeightCarriedSpinner.setValue(new Double(oO.dMaxWeightContained));
        }
        else
        if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_SHIELD)) {
          jShieldRadio.setSelected(true);
          jShieldRadio_actionPerformed(null);

          jShieldACBaseSpinner.setValue(new Integer(oO.nShieldACBase));
   jShieldACMagicBonusSpinner.setValue(new Integer(oO.nShieldACMagicBonus));

          jShieldAC1Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_1]));
          jShieldAC2Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_2]));
          jShieldAC3Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_3]));
          jShieldAC4Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_4]));
          jShieldAC5Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_5]));
        }
        else

        // armor
        if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_ARMOR)) {
          jArmorRadio.setSelected(true);
          jArmorRadio_actionPerformed(null);
          jACBaseSpinner1.setValue(new Integer(oO.nACBase));
          jACBulkComboBox1.setSelectedIndex(oO.nACBulkIndex);
          jACAbsorbSpinner2.setValue(new Integer(oO.nACAbsorb));
          jACMagicBonusSpinner3.setValue(new Integer(oO.nACMagicBonus));

          jAC0Spinner.setValue(new Integer(oO.nACHP[oO.AC_0]));
          jAC1Spinner.setValue(new Integer(oO.nACHP[oO.AC_1]));
          jAC2Spinner.setValue(new Integer(oO.nACHP[oO.AC_2]));
          jAC3Spinner.setValue(new Integer(oO.nACHP[oO.AC_3]));
          jAC4Spinner.setValue(new Integer(oO.nACHP[oO.AC_4]));
          jAC5Spinner.setValue(new Integer(oO.nACHP[oO.AC_5]));
          jAC6Spinner3.setValue(new Integer(oO.nACHP[oO.AC_6]));
          jAC7Spinner.setValue(new Integer(oO.nACHP[oO.AC_7]));
          jAC8Spinner1.setValue(new Integer(oO.nACHP[oO.AC_8]));
          jAC9Spinner10.setValue(new Integer(oO.nACHP[oO.AC_9]));

        }
        else

        // weapon
        if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_WEAPON)) {
          jWeaponRadio.setSelected(true);
          jWeaponRadio_actionPerformed(null);

          jTypeCrushRadio.setSelected(false);
          jTypeHackRadio.setSelected(false);
          jTypePierceRadio.setSelected(false);

          if (oO.sWeaponType.equalsIgnoreCase(oO.WEAPON_TYPE_CRUSH)) {
            jTypeCrushRadio.setSelected(true);
          }
          else
          if (oO.sWeaponType.equalsIgnoreCase(oO.WEAPON_TYPE_PIERCE)) {
            jTypePierceRadio.setSelected(true);
          }
          else {
            jTypeHackRadio.setSelected(true);

          }
          jSpeedFactorSpinner.setValue(new Integer(oO.nSpeedFactor));

          jDamTiny.setText(oO.jDamage[oO.DAMAGE_SIZE_TINY].getText());
          jDamSmall.setText(oO.jDamage[oO.DAMAGE_SIZE_SMALL].getText());
          jDamMedium.setText(oO.jDamage[oO.DAMAGE_SIZE_MEDIUM].getText());
          jDamLarge.setText(oO.jDamage[oO.DAMAGE_SIZE_LARGE].getText());
          jDamHuge.setText(oO.jDamage[oO.DAMAGE_SIZE_HUGE].getText());
          jDamGigantic.setText(oO.jDamage[oO.DAMAGE_SIZE_GIANT].getText());

        }
        else {
          jGearRadio.setSelected(true);
          jGearRadio_actionPerformed(null);
        }

        nGearID = oO.nGearID;
      }
    } */

  void jRemoveButton_actionPerformed(ActionEvent e) {
    if (oCurrentItem != null) {
//    TableGear oGear = (TableGear) oParent.lGear.get(jItemComboBox.getSelectedIndex());
      TableGear oGear = oCurrentItem;
      if (oParent.AskYN(this,
                        "Are you sure you want to remove the gear " +
                        oGear.sName +
                        "?")) {

        oParent.lGear.remove(oGear);
        RebuildGearList();

        xmlControl.saveDoc(oParent, oParent,
                           TableGear.xmlBuildDocFromList(oParent.lGear,
            oParent.nMaxGearID), oParent.sGearSaveFile);

        // we removed item, rebuild weapon select boxes for battle sheet
        // creatures if any
        for (int i = 0; i < oParent.lCreatures.size(); i++) {
          CreatureCore oCreature = (CreatureCore) oParent.lCreatures.get(i);
          for (int ii = 0; ii < oCreature.lAttacks.size(); ii++) {
            CreatureCombat oAtk = (CreatureCombat) oCreature.lAttacks.get(ii);
            oAtk.setupWeaponUsedBox(oParent.lGear);
          }
        }

        oParent.ShowDone(this, "Removed item.");
      }
    }

  }

  void jDamTiny_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamTiny.getText())) {
      jDamTiny.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamTiny, null);
    }
  }

  void jDamSmall_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamSmall.getText())) {
      jDamSmall.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamSmall, null);
    }

  }

  void jDamMedium_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamMedium.getText())) {
      jDamMedium.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamMedium, null);
    }

  }

  void jDamLarge_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamLarge.getText())) {
      jDamLarge.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamLarge, null);
    }

  }

  void jDamHuge_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamHuge.getText())) {
      jDamHuge.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamHuge, null);
    }

  }

  void jDamGigantic_focusLost(FocusEvent e) {
    if (!Dicer.isValidDamageDiceString(jDamGigantic.getText())) {
      jDamGigantic.setText("1d6");
      Dicer.ShowDiceEntryError(oParent, jDamGigantic, null);
    }

  }

  void enableWeaponOptions(boolean bB) {
    jDamGigantic.setEnabled(bB);
    jDamHuge.setEnabled(bB);
    jDamLarge.setEnabled(bB);
    jDamMedium.setEnabled(bB);
    jDamSmall.setEnabled(bB);
    jDamTiny.setEnabled(bB);
    jTypeCrushRadio.setEnabled(bB);
    jTypeHackRadio.setEnabled(bB);
    jTypePierceRadio.setEnabled(bB);
    jSpeedFactorSpinner.setEnabled(bB);
  }

  void enableContainerOptions(boolean bB) {
    jMaxWeightCarriedSpinner.setEnabled(bB);
  }

  void enableArmorOptions(boolean bB) {
    jAC0Spinner.setEnabled(bB);
    jAC1Spinner.setEnabled(bB);
    jAC2Spinner.setEnabled(bB);
    jAC3Spinner.setEnabled(bB);
    jAC4Spinner.setEnabled(bB);
    jAC5Spinner.setEnabled(bB);
    jAC6Spinner3.setEnabled(bB);
    jAC7Spinner.setEnabled(bB);
    jAC8Spinner1.setEnabled(bB);
    jAC9Spinner10.setEnabled(bB);
    jACAbsorbSpinner2.setEnabled(bB);
    jACBaseSpinner1.setEnabled(bB);
    jACBulkComboBox1.setEnabled(bB);
    jACHPPanel.setEnabled(bB);
    jACMagicBonusSpinner3.setEnabled(bB);
    if (bB) {
      jACBaseSpinner1_stateChanged(null);
    }
  }

  void enableShieldOptions(boolean bB) {
    jShieldAC1Spinner.setEnabled(bB);
    jShieldAC2Spinner.setEnabled(bB);
    jShieldAC3Spinner.setEnabled(bB);
    jShieldAC4Spinner.setEnabled(bB);
    jShieldAC5Spinner.setEnabled(bB);
    jShieldACBaseSpinner.setEnabled(bB);
    jShieldACMagicBonusSpinner.setEnabled(bB);
    if (bB) {
      jShieldACBaseSpinner_stateChanged(null);
    }
  }

  void jGearRadio_actionPerformed(ActionEvent e) {
    if (jGearRadio.isSelected()) {
      jWeaponRadio.setSelected(false);
      jContainerRadio.setSelected(false);
      jArmorRadio.setSelected(false);
      jShieldRadio.setSelected(false);
      enableWeaponOptions(false);
      enableContainerOptions(false);
      enableArmorOptions(false);
      enableShieldOptions(false);
    }
  }

  void jWeaponRadio_actionPerformed(ActionEvent e) {
    if (jWeaponRadio.isSelected()) {
      jGearRadio.setSelected(false);
      jContainerRadio.setSelected(false);
      jArmorRadio.setSelected(false);
      jShieldRadio.setSelected(false);

      enableContainerOptions(false);
      enableShieldOptions(false);
      enableArmorOptions(false);
      enableWeaponOptions(true);
    }
  }

  void jContainerRadio_actionPerformed(ActionEvent e) {
    if (jContainerRadio.isSelected()) {
      jWeaponRadio.setSelected(false);
      jGearRadio.setSelected(false);
      jArmorRadio.setSelected(false);
      jShieldRadio.setSelected(false);

      enableContainerOptions(true);
      enableArmorOptions(false);
      enableShieldOptions(false);
      enableWeaponOptions(false);
    }
  }

  void jArmorRadio_actionPerformed(ActionEvent e) {
    if (jArmorRadio.isSelected()) {
      jWeaponRadio.setSelected(false);
      jGearRadio.setSelected(false);
//    jArmorRadio.setSelected(false);
      jShieldRadio.setSelected(false);
      jContainerRadio.setSelected(false);

      enableContainerOptions(false);
      enableShieldOptions(false);
      enableArmorOptions(true);
      enableWeaponOptions(false);
    }
  }

  void jShieldRadio_actionPerformed(ActionEvent e) {
    if (jShieldRadio.isSelected()) {
      jWeaponRadio.setSelected(false);
      jGearRadio.setSelected(false);
      jArmorRadio.setSelected(false);
      jContainerRadio.setSelected(false);

      enableContainerOptions(false);
      enableArmorOptions(false);
      enableWeaponOptions(false);
      enableShieldOptions(true);
    }
  }

  void jTypeHackRadio_actionPerformed(ActionEvent e) {
    if (jTypeHackRadio.isSelected()) {
      jTypeCrushRadio.setSelected(false);
      jTypePierceRadio.setSelected(false);
    }

  }

  void jTypePierceRadio_actionPerformed(ActionEvent e) {
    if (jTypePierceRadio.isSelected()) {
      jTypeCrushRadio.setSelected(false);
      jTypeHackRadio.setSelected(false);
    }

  }

  void jTypeCrushRadio_actionPerformed(ActionEvent e) {
    if (jTypeCrushRadio.isSelected()) {
      jTypeHackRadio.setSelected(false);
      jTypePierceRadio.setSelected(false);
    }

  }

  void jACBaseSpinner1_stateChanged(ChangeEvent e) {
    int nAC = Integer.parseInt(jACBaseSpinner1.getValue().toString());
//    oParent.jGMTextArea.append("nAC:"+nAC+"\n");
    if (nAC <= 8) {
      jAC8Spinner1.setEnabled(true);
    }
    else {
      jAC8Spinner1.setEnabled(false);
    }
    if (nAC <= 7) {
      jAC7Spinner.setEnabled(true);
    }
    else {
      jAC7Spinner.setEnabled(false);
    }
    if (nAC <= 6) {
      jAC6Spinner3.setEnabled(true);
    }
    else {
      jAC6Spinner3.setEnabled(false);
    }
    if (nAC <= 5) {
      jAC5Spinner.setEnabled(true);
    }
    else {
      jAC5Spinner.setEnabled(false);
    }
    if (nAC <= 4) {
      jAC4Spinner.setEnabled(true);
    }
    else {
      jAC4Spinner.setEnabled(false);
    }
    if (nAC <= 3) {
      jAC3Spinner.setEnabled(true);
    }
    else {
      jAC3Spinner.setEnabled(false);
    }
    if (nAC <= 2) {
      jAC2Spinner.setEnabled(true);
    }
    else {
      jAC2Spinner.setEnabled(false);
    }
    if (nAC <= 1) {
      jAC1Spinner.setEnabled(true);
    }
    else {
      jAC1Spinner.setEnabled(false);
    }
    if (nAC == 0) {
      jAC0Spinner.setEnabled(true);
    }
    else {
      jAC0Spinner.setEnabled(false);

    }
  }

  void jShieldACBaseSpinner_stateChanged(ChangeEvent e) {
    int nBonusAC = Integer.parseInt(jShieldACBaseSpinner.getValue().toString());
    if (nBonusAC == 5) {
      jShieldAC5Spinner.setEnabled(true);
    }
    else {
      jShieldAC5Spinner.setEnabled(false);
    }
    if (nBonusAC >= 4) {
      jShieldAC4Spinner.setEnabled(true);
    }
    else {
      jShieldAC4Spinner.setEnabled(false);
    }
    if (nBonusAC >= 3) {
      jShieldAC3Spinner.setEnabled(true);
    }
    else {
      jShieldAC3Spinner.setEnabled(false);
    }
    if (nBonusAC >= 2) {
      jShieldAC2Spinner.setEnabled(true);
    }
    else {
      jShieldAC2Spinner.setEnabled(false);
    }
    if (nBonusAC >= 1) {
      jShieldAC1Spinner.setEnabled(true);
    }
    else {
      jShieldAC1Spinner.setEnabled(false);
    }
  }

  /**
   * this is run when the user selected with mouse or moves the arrow
   * keys up and changes the selected item.
   *
   * @param e ListSelectionEvent
   */
  void jListGear_valueChanged(ListSelectionEvent e) {
    if (!mListGear.isEmpty()) {
      Object oObj = jListGear.getSelectedValue();
      if (oObj != null) {
        if (oObj instanceof TableGear) {
          TableGear oO = (TableGear) oObj;

          oCurrentItem = oO;

          jCurrentItemLabel.setText(oO.sName);

          jDescTextArea.setText(oO.sDesc);

          jLocationTextField.setText("");
          jWeightSpinner.getModel().setValue(new Double(oO.dWeight));

          // container
          if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_CONTAINER)) {
            jContainerRadio.setSelected(true);
            jContainerRadio_actionPerformed(null);
            jMaxWeightCarriedSpinner.setValue(new Double(oO.dMaxWeightContained));
          }
          else
          if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_SHIELD)) {
            jShieldRadio.setSelected(true);
            jShieldRadio_actionPerformed(null);

            jShieldACBaseSpinner.setValue(new Integer(oO.nShieldACBase));
            jShieldACMagicBonusSpinner.setValue(new Integer(oO.
                nShieldACMagicBonus));

            jShieldAC1Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_1]));
            jShieldAC2Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_2]));
            jShieldAC3Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_3]));
            jShieldAC4Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_4]));
            jShieldAC5Spinner.setValue(new Integer(oO.nShieldHP[oO.SHIELD_5]));
          }
          else

          // armor
          if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_ARMOR)) {
            jArmorRadio.setSelected(true);
            jArmorRadio_actionPerformed(null);
            jACBaseSpinner1.setValue(new Integer(oO.nACBase));
            jACBulkComboBox1.setSelectedIndex(oO.nACBulkIndex);
            jACAbsorbSpinner2.setValue(new Integer(oO.nACAbsorb));
            jACMagicBonusSpinner3.setValue(new Integer(oO.nACMagicBonus));

            jAC0Spinner.setValue(new Integer(oO.nACHP[oO.AC_0]));
            jAC1Spinner.setValue(new Integer(oO.nACHP[oO.AC_1]));
            jAC2Spinner.setValue(new Integer(oO.nACHP[oO.AC_2]));
            jAC3Spinner.setValue(new Integer(oO.nACHP[oO.AC_3]));
            jAC4Spinner.setValue(new Integer(oO.nACHP[oO.AC_4]));
            jAC5Spinner.setValue(new Integer(oO.nACHP[oO.AC_5]));
            jAC6Spinner3.setValue(new Integer(oO.nACHP[oO.AC_6]));
            jAC7Spinner.setValue(new Integer(oO.nACHP[oO.AC_7]));
            jAC8Spinner1.setValue(new Integer(oO.nACHP[oO.AC_8]));
            jAC9Spinner10.setValue(new Integer(oO.nACHP[oO.AC_9]));

          }
          else

          // weapon
          if (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_WEAPON)) {
            jWeaponRadio.setSelected(true);
            jWeaponRadio_actionPerformed(null);

            jTypeCrushRadio.setSelected(false);
            jTypeHackRadio.setSelected(false);
            jTypePierceRadio.setSelected(false);

            if (oO.sWeaponType.equalsIgnoreCase(oO.WEAPON_TYPE_CRUSH)) {
              jTypeCrushRadio.setSelected(true);
            }
            else
            if (oO.sWeaponType.equalsIgnoreCase(oO.WEAPON_TYPE_PIERCE)) {
              jTypePierceRadio.setSelected(true);
            }
            else {
              jTypeHackRadio.setSelected(true);

            }
            jSpeedFactorSpinner.setValue(new Integer(oO.nSpeedFactor));

            jDamTiny.setText(oO.jDamage[oO.DAMAGE_SIZE_TINY].getText());
            jDamSmall.setText(oO.jDamage[oO.DAMAGE_SIZE_SMALL].getText());
            jDamMedium.setText(oO.jDamage[oO.DAMAGE_SIZE_MEDIUM].getText());
            jDamLarge.setText(oO.jDamage[oO.DAMAGE_SIZE_LARGE].getText());
            jDamHuge.setText(oO.jDamage[oO.DAMAGE_SIZE_HUGE].getText());
            jDamGigantic.setText(oO.jDamage[oO.DAMAGE_SIZE_GIANT].getText());

          }
          else {
            jGearRadio.setSelected(true);
            jGearRadio_actionPerformed(null);
          }

          nGearID = oO.nGearID;
        }
      }
    }

  }

  /**
   *
   * rebuild the jListGear box
   *
   */
  void RebuildGearList() {
    if (oParent.lGear.size() > 0) {
      String sSearchPattern = jFilterTextField1.getText();
      Collections.sort(oParent.lGear);
      mListGear.removeAllElements();
      for (int i = 0; i < oParent.lGear.size(); i++) {
        TableGear oO = (TableGear) oParent.lGear.get(i);
        if ( (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_ARMOR) &&
              jGearArmorRadioButton2.isSelected()) ||
            (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_CONTAINER) &&
             jGearContRadioButton4.isSelected()) ||
            (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_WEAPON) &&
             jGearWeaponRadioButton3.isSelected()) ||
            (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_SHIELD) &&
             jGearShieldRadioButton1.isSelected()) ||
            (oO.sItemType.equalsIgnoreCase(oO.ITEM_TYPE_GEAR) &&
             jGearAllRadioButton.isSelected())) {

         if (sSearchPattern == null ||
             oO.sName.matches("(?si)(.*)?"+sSearchPattern+"(.*)?"))
          mListGear.addElement(oO);
        }

      }
    }
  }

  void jGearAllRadioButton_actionPerformed(ActionEvent e) {
    RebuildGearList();
  }

  void jGearContRadioButton4_actionPerformed(ActionEvent e) {
    RebuildGearList();
  }

  void jGearWeaponRadioButton3_actionPerformed(ActionEvent e) {
    RebuildGearList();
  }

  void jGearArmorRadioButton2_actionPerformed(ActionEvent e) {
    RebuildGearList();
  }

  void jGearShieldRadioButton1_actionPerformed(ActionEvent e) {
    RebuildGearList();
  }
  void jFilterTextField1_keyReleased(KeyEvent e) {
    RebuildGearList();
  }



}

class DialogNewGear_jAddButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jAddButton_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jAddButton_actionPerformed(e);
  }
}

class DialogNewGear_jCancelButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jCancelButton_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}

class DialogNewGear_jSaveItemButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jSaveItemButton_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jSaveItemButton_actionPerformed(e);
  }
}

class DialogNewGear_jRemoveButton_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jRemoveButton_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveButton_actionPerformed(e);
  }
}

class DialogNewGear_jDamTiny_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamTiny_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamTiny_focusLost(e);
  }
}

class DialogNewGear_jDamSmall_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamSmall_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamSmall_focusLost(e);
  }
}

class DialogNewGear_jDamMedium_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamMedium_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamMedium_focusLost(e);
  }
}

class DialogNewGear_jDamLarge_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamLarge_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamLarge_focusLost(e);
  }
}

class DialogNewGear_jDamHuge_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamHuge_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamHuge_focusLost(e);
  }
}

class DialogNewGear_jDamGigantic_focusAdapter
    extends java.awt.event.FocusAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jDamGigantic_focusAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jDamGigantic_focusLost(e);
  }
}

class DialogNewGear_jGearRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGearRadio_actionPerformed(e);
  }
}

class DialogNewGear_jWeaponRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jWeaponRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jWeaponRadio_actionPerformed(e);
  }
}

class DialogNewGear_jContainerRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jContainerRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jContainerRadio_actionPerformed(e);
  }
}

class DialogNewGear_jTypeHackRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jTypeHackRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTypeHackRadio_actionPerformed(e);
  }
}

class DialogNewGear_jTypePierceRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jTypePierceRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTypePierceRadio_actionPerformed(e);
  }
}

class DialogNewGear_jTypeCrushRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jTypeCrushRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTypeCrushRadio_actionPerformed(e);
  }
}

class DialogNewGear_jArmorRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jArmorRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jArmorRadio_actionPerformed(e);
  }
}

class DialogNewGear_jShieldRadio_actionAdapter
    implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jShieldRadio_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jShieldRadio_actionPerformed(e);
  }
}

class DialogNewGear_jACBaseSpinner1_changeAdapter
    implements javax.swing.event.ChangeListener {
  DialogNewGear adaptee;

  DialogNewGear_jACBaseSpinner1_changeAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jACBaseSpinner1_stateChanged(e);
  }
}

class DialogNewGear_jShieldACBaseSpinner_changeAdapter
    implements javax.swing.event.ChangeListener {
  DialogNewGear adaptee;

  DialogNewGear_jShieldACBaseSpinner_changeAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jShieldACBaseSpinner_stateChanged(e);
  }
}

class DialogNewGear_jListGear_listSelectionAdapter
    implements javax.swing.event.ListSelectionListener {
  DialogNewGear adaptee;

  DialogNewGear_jListGear_listSelectionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(ListSelectionEvent e) {
    adaptee.jListGear_valueChanged(e);
  }
}

class DialogNewGear_jGearAllRadioButton_actionAdapter implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearAllRadioButton_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGearAllRadioButton_actionPerformed(e);
  }
}

class DialogNewGear_jGearContRadioButton4_actionAdapter implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearContRadioButton4_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGearContRadioButton4_actionPerformed(e);
  }
}

class DialogNewGear_jGearWeaponRadioButton3_actionAdapter implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearWeaponRadioButton3_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGearWeaponRadioButton3_actionPerformed(e);
  }
}

class DialogNewGear_jGearArmorRadioButton2_actionAdapter implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearArmorRadioButton2_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGearArmorRadioButton2_actionPerformed(e);
  }
}

class DialogNewGear_jGearShieldRadioButton1_actionAdapter implements java.awt.event.ActionListener {
  DialogNewGear adaptee;

  DialogNewGear_jGearShieldRadioButton1_actionAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jGearShieldRadioButton1_actionPerformed(e);
  }
}

class DialogNewGear_jFilterTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogNewGear adaptee;

  DialogNewGear_jFilterTextField1_keyAdapter(DialogNewGear adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.jFilterTextField1_keyReleased(e);
  }
}

