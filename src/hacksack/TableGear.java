package hacksack;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import org.jdom.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableGear
    implements Comparable {
  String sName = null;
  String sDesc = null;
  int nGearID = -1;
  double dWeight = 0.0;
  String sItemType = null; // Gear, Weapon, Container, Armor, Shield

  // weapon specific stuff
  String sWeaponGroup = null; // melee, missle
  String WEAPON_GROUP_MELEE = "MELEE";
  String WEAPON_GROUP_RANGE = "RANGE";

  String sWeaponType = null; // crush, pierce, hack
  String WEAPON_TYPE_CRUSH = "C";
  String WEAPON_TYPE_PIERCE = "P";
  String WEAPON_TYPE_HACK = "H";

  int nToHit = 0; // not used right now
  int nToDamage = 0; // not used right now
  int nSpeedFactor = 0;

  // Armor specs
  int nACBase = 9;
  int nACBulkIndex = 0;
  int nACMagicBonus = 0;
  int nACAbsorb = 1;

  int[] nACHP = null;
  int AC_0 = 0;
  int AC_1 = 1;
  int AC_2 = 2;
  int AC_3 = 3;
  int AC_4 = 4;
  int AC_5 = 5;
  int AC_6 = 6;
  int AC_7 = 7;
  int AC_8 = 8;
  int AC_9 = 9;

  // end armor specs

  // shield specs
  int nShieldACBase = 1;
  int nShieldACMagicBonus = 0;
  int[] nShieldHP = null;
  int SHIELD_1 = 1;
  int SHIELD_2 = 2;
  int SHIELD_3 = 3;
  int SHIELD_4 = 4;
  int SHIELD_5 = 5;
  int SHIELD_6 = 6;
  int SHIELD_7 = 7;
  int SHIELD_8 = 8;
  int SHIELD_9 = 9;
  int SHIELD_10 = 10;

  // end shield specs

  JTextField[] jDamage = null;

  int DAMAGE_SIZE_TINY = 0;
  int DAMAGE_SIZE_SMALL = 1;
  int DAMAGE_SIZE_MEDIUM = 2;
  int DAMAGE_SIZE_LARGE = 3;
  int DAMAGE_SIZE_HUGE = 4;
  int DAMAGE_SIZE_GIANT = 5;

  // end weapons

  // container specific stuff
  double dMaxWeightContained = 0.0;

  // end containers

  static String ITEM_TYPE_CONTAINER = "CONTAINER";
  static String ITEM_TYPE_WEAPON = "WEAPON";
  static String ITEM_TYPE_GEAR = "GEAR";
  static String ITEM_TYPE_ARMOR = "ARMOR";
  static String ITEM_TYPE_SHIELD = "SHIELD";

  public TableGear() {
    nACHP = new int[ (AC_9 + 1)];
    nShieldHP = new int[ (SHIELD_10 + 1)];

    jDamage = new JTextField[DAMAGE_SIZE_GIANT + 1];
    jDamage[DAMAGE_SIZE_TINY] = new JTextField();
    jDamage[DAMAGE_SIZE_SMALL] = new JTextField();
    jDamage[DAMAGE_SIZE_MEDIUM] = new JTextField();
    jDamage[DAMAGE_SIZE_LARGE] = new JTextField();
    jDamage[DAMAGE_SIZE_HUGE] = new JTextField();
    jDamage[DAMAGE_SIZE_GIANT] = new JTextField();
  }

  public int compareTo(Object o) {
    String sThisName = ( (TableGear) o).sName.toUpperCase();
    String sComp = sName.toUpperCase();
    return sComp.compareTo(sThisName);
  }

  /**
   *    This returns a element list of this Gear item to store into
   * XML.
   * @return Element
   */
  Element xmlGetElements() {

    Element eGearRoot = new Element("Gear");

    eGearRoot.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eGearRoot.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eGearRoot.addContent(new Element("dWeight").setText("" + dWeight));
    eGearRoot.addContent(new Element("nGearID").setText("" + nGearID));
    eGearRoot.addContent(new Element("sItemType").setText(sItemType));

    if (sItemType.equalsIgnoreCase(ITEM_TYPE_SHIELD)) {
      eGearRoot.addContent(new Element("nShieldACBase").setText("" +
          nShieldACBase));
      eGearRoot.addContent(new Element("nShieldACMagicBonus").setText("" +
          nShieldACMagicBonus));

      eGearRoot.addContent(new Element("SHIELD_1").setText("" +
          nShieldHP[SHIELD_1]));
      eGearRoot.addContent(new Element("SHIELD_2").setText("" +
          nShieldHP[SHIELD_2]));
      eGearRoot.addContent(new Element("SHIELD_3").setText("" +
          nShieldHP[SHIELD_3]));
      eGearRoot.addContent(new Element("SHIELD_4").setText("" +
          nShieldHP[SHIELD_4]));
      eGearRoot.addContent(new Element("SHIELD_5").setText("" +
          nShieldHP[SHIELD_5]));
    }
    else
    if (sItemType.equalsIgnoreCase(ITEM_TYPE_ARMOR)) {
      eGearRoot.addContent(new Element("nACAbsorb").setText("" + nACAbsorb));
      eGearRoot.addContent(new Element("nACBase").setText("" + nACBase));
      eGearRoot.addContent(new Element("nACBulkIndex").setText("" +
          nACBulkIndex));
      eGearRoot.addContent(new Element("nACMagicBonus").setText("" +
          nACMagicBonus));

      eGearRoot.addContent(new Element("AC_0").setText("" + nACHP[AC_0]));
      eGearRoot.addContent(new Element("AC_1").setText("" + nACHP[AC_1]));
      eGearRoot.addContent(new Element("AC_2").setText("" + nACHP[AC_2]));
      eGearRoot.addContent(new Element("AC_3").setText("" + nACHP[AC_3]));
      eGearRoot.addContent(new Element("AC_4").setText("" + nACHP[AC_4]));
      eGearRoot.addContent(new Element("AC_5").setText("" + nACHP[AC_5]));
      eGearRoot.addContent(new Element("AC_6").setText("" + nACHP[AC_6]));
      eGearRoot.addContent(new Element("AC_7").setText("" + nACHP[AC_7]));
      eGearRoot.addContent(new Element("AC_8").setText("" + nACHP[AC_8]));
      eGearRoot.addContent(new Element("AC_9").setText("" + nACHP[AC_9]));
    }
    else
    if (sItemType.equalsIgnoreCase(ITEM_TYPE_CONTAINER)) {
      eGearRoot.addContent(new Element("dMaxWeightContained").setText("" +
          dMaxWeightContained));
    }
    else
    if (sItemType.equalsIgnoreCase(ITEM_TYPE_WEAPON)) {
      eGearRoot.addContent(new Element("sWeaponGroup").setText(sWeaponGroup));
      eGearRoot.addContent(new Element("nSpeedFactor").setText("" +
          nSpeedFactor));
      eGearRoot.addContent(new Element("sWeaponType").setText(sWeaponType));

      eGearRoot.addContent(new Element("DAMAGE_SIZE_TINY").setText(jDamage[
          DAMAGE_SIZE_TINY].getText()));
      eGearRoot.addContent(new Element("DAMAGE_SIZE_SMALL").setText(jDamage[
          DAMAGE_SIZE_SMALL].getText()));
      eGearRoot.addContent(new Element("DAMAGE_SIZE_MEDIUM").setText(jDamage[
          DAMAGE_SIZE_MEDIUM].getText()));
      eGearRoot.addContent(new Element("DAMAGE_SIZE_LARGE").setText(jDamage[
          DAMAGE_SIZE_LARGE].getText()));
      eGearRoot.addContent(new Element("DAMAGE_SIZE_HUGE").setText(jDamage[
          DAMAGE_SIZE_HUGE].getText()));
      eGearRoot.addContent(new Element("DAMAGE_SIZE_GIANT").setText(jDamage[
          DAMAGE_SIZE_GIANT].getText()));
    }
    else {
      // we assume it's a "gear" items
      // we have no specific stuff other that what we have for every item for "GEAR"
      //   if (sItemType.equalsIgnoreCase(ITEM_TYPE_GEAR)) {
    }

    return eGearRoot;
  }

  /**
   *
   *  this will build a complete Document from the lSavedGear
   * array that is passed so it can be dumped to Gear.xml
   *
   * @param lSavedGear ArrayList
   * @param nMaxGearID int
   * @return Document
   */
  static Document xmlBuildDocFromList(ArrayList lSavedGear, int nMaxGearID) {
    Element eRoot = new Element("GearList");
    eRoot.addContent(new Element("nMaxID").setText(""+nMaxGearID));
    Document doc = new Document(eRoot);

    for(int i=0;i<lSavedGear.size();i++) {
      TableGear oGear = (TableGear)lSavedGear.get(i);
      eRoot.addContent(oGear.xmlGetElements());
    }

    return doc;
  }

  /**
   * This returns a TableGear class using the values of a
   * Element object that is created by reading in a XML file.
   * <Gear> ... </Gear>
   * @param eGear Element
   * @return TableGear
   */
  static TableGear xmlGetFromElements(Element eGear) {
    TableGear oGear = new TableGear();

    oGear.sName = xmlControl.unEscapeChars(eGear.getChild("sName").getText());
    oGear.sDesc = xmlControl.unEscapeChars(eGear.getChild("sDesc").getText());
    oGear.dWeight = Double.parseDouble(eGear.getChild("dWeight").getText());
    oGear.nGearID = Integer.parseInt(eGear.getChild("nGearID").getText());
    oGear.sItemType = eGear.getChild("sItemType").getText();

    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_SHIELD)) {
      oGear.nShieldACBase = Integer.parseInt(eGear.getChild("nShieldACBase").getText());
      oGear.nShieldACMagicBonus = Integer.parseInt(eGear.getChild("nShieldACMagicBonus").getText());

      oGear.nShieldHP[oGear.SHIELD_1] = Integer.parseInt(eGear.getChild("SHIELD_1").getText());
      oGear.nShieldHP[oGear.SHIELD_2] = Integer.parseInt(eGear.getChild("SHIELD_2").getText());
      oGear.nShieldHP[oGear.SHIELD_3] = Integer.parseInt(eGear.getChild("SHIELD_3").getText());
      oGear.nShieldHP[oGear.SHIELD_4] = Integer.parseInt(eGear.getChild("SHIELD_4").getText());
      oGear.nShieldHP[oGear.SHIELD_5] = Integer.parseInt(eGear.getChild("SHIELD_5").getText());
    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_ARMOR)) {
      oGear.nACAbsorb = Integer.parseInt(eGear.getChild("nACAbsorb").getText());
      oGear.nACBase = Integer.parseInt(eGear.getChild("nACBase").getText());
      oGear.nACBulkIndex = Integer.parseInt(eGear.getChild("nACBulkIndex").getText());
      oGear.nACMagicBonus = Integer.parseInt(eGear.getChild("nACMagicBonus").getText());

      oGear.nACHP[oGear.AC_0] = Integer.parseInt(eGear.getChild("AC_0").getText());
      oGear.nACHP[oGear.AC_1] = Integer.parseInt(eGear.getChild("AC_1").getText());
      oGear.nACHP[oGear.AC_2] = Integer.parseInt(eGear.getChild("AC_2").getText());
      oGear.nACHP[oGear.AC_3] = Integer.parseInt(eGear.getChild("AC_3").getText());
      oGear.nACHP[oGear.AC_4] = Integer.parseInt(eGear.getChild("AC_4").getText());
      oGear.nACHP[oGear.AC_5] = Integer.parseInt(eGear.getChild("AC_5").getText());
      oGear.nACHP[oGear.AC_6] = Integer.parseInt(eGear.getChild("AC_6").getText());
      oGear.nACHP[oGear.AC_7] = Integer.parseInt(eGear.getChild("AC_7").getText());
      oGear.nACHP[oGear.AC_8] = Integer.parseInt(eGear.getChild("AC_8").getText());
      oGear.nACHP[oGear.AC_9] = Integer.parseInt(eGear.getChild("AC_9").getText());
    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_WEAPON)) {
      oGear.sWeaponGroup = eGear.getChild("sWeaponGroup").getText();
      oGear.sWeaponType = eGear.getChild("sWeaponType").getText();
      oGear.nSpeedFactor =Integer.parseInt(eGear.getChild("nSpeedFactor").getText());

      oGear.jDamage[oGear.DAMAGE_SIZE_TINY].setText(eGear.getChild("DAMAGE_SIZE_TINY").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_SMALL].setText(eGear.getChild("DAMAGE_SIZE_SMALL").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_MEDIUM].setText(eGear.getChild("DAMAGE_SIZE_MEDIUM").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_LARGE].setText(eGear.getChild("DAMAGE_SIZE_LARGE").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_HUGE].setText(eGear.getChild("DAMAGE_SIZE_HUGE").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_GIANT].setText(eGear.getChild("DAMAGE_SIZE_GIANT").getText());
    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_CONTAINER)) {
      oGear.dMaxWeightContained = Double.parseDouble(eGear.getChild("dMaxWeightContained").getText());
    }


    return oGear;
  }

  /**
   * this will build a ArrayList using a Document object that was created
   * by reading in the Gear.xml file
   *
   * @param oParent HackSackFrame
   * @param doc Document
   * @return ArrayList
   */
  static ArrayList xmlgetSavedFromDoc(HackSackFrame oParent, Document doc) {
    ArrayList lList = new ArrayList();
    boolean bChanged = false;
    try {
    Element eRoot = doc.getRootElement();
    oParent.nMaxGearID =Integer.parseInt(eRoot.getChild("nMaxID").getText());

    java.util.List lGearItems = eRoot.getChildren("Gear");

    Iterator in = lGearItems.iterator();
    while (in.hasNext()) {
      Element eGear = (Element)in.next();
      TableGear oGear = TableGear.xmlGetFromElements(eGear);

      // change our max ID if thisitem has ID greater than our max
      if (oGear.nGearID > oParent.nMaxGearID)
        oParent.nMaxGearID=(oGear.nGearID+1);

        // if the item doesn't have a default item type set it
        // this is for old items that got imported/screwed up at some point
      if (!oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_GEAR) &&
          !oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_ARMOR) &&
          !oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_CONTAINER) &&
          !oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_SHIELD) &&
          !oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_WEAPON)      ) {
        oParent.gmLog(false,false,oGear.sName+" has item type ("+oGear.sItemType+") set to GEAR\n");
        oGear.sItemType = oGear.ITEM_TYPE_GEAR;
        bChanged = true;
      }

        TableGear oOld = oGear.GetGearFromID(oParent,oGear.nGearID);
        if (oOld != null) {
          oGear.nGearID = (oParent.nMaxGearID++);
          oParent.gmLog(false,false,oGear.sName+" had duplicate ID as "+oOld.sName+" changed to "+oGear.nGearID+"\n");
          bChanged = true;
        }
      lList.add(oGear);
    }
    // if we had duplicate ID's or something
    // save new changes...
    if (bChanged)
      xmlControl.saveDoc(oParent, oParent,
                         TableGear.xmlBuildDocFromList(lList,oParent.nMaxGearID), oParent.sGearSaveFile);

  }
  catch (NullPointerException err) {
    oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                      "Error occured while trying to load gear from XML.");
  }
    return lList;
  }


  static TableGear GetGearFromName(HackSackFrame oParent, String sName) {
    TableGear oFound = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lGear.size() && !bFound; i++) {
      TableGear oFind = (TableGear) oParent.lGear.get(i);
      if (oFind.sName.equalsIgnoreCase(sName)) {
        oFound = oFind;
        bFound = true;
      }
    }
    return oFound;
  }

  // getgear by ID
  static TableGear GetGearFromID(HackSackFrame oParent, int nGearID) {
    TableGear oFound = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lGear.size() && !bFound; i++) {
      TableGear oFind = (TableGear) oParent.lGear.get(i);
      if (oFind.nGearID == nGearID) {
        oFound = oFind;
        bFound = true;
      }
    }
    return oFound;
  }

  static void DoNewGear(Component oThis, HackSackFrame oParent,
                        TablePlayer oPlayer,
                        Gear oGearEdit,
                        String sThisItemType) {
    DialogNewGear dlg = new DialogNewGear(oParent, oPlayer, oGearEdit,sThisItemType);
    dlg.jDetailsPanel.setPreferredSize(new Dimension(560, 540));
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = oThis.getSize();
    Point loc = oThis.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    if (oGearEdit != null) {
      dlg.setTitle("Item Edit " + oGearEdit.sName);
    }
    else {
      dlg.setTitle("Additional Inventory");
    }
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);

  }
}
