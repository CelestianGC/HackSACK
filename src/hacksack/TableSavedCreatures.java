package hacksack;

import java.util.*;
import java.awt.event.*;
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
// implments Comparable { ... }
public class TableSavedCreatures
    implements Comparable {
  HackSackFrame oParent = null;
  int nCreatureID = -1;

  String sCreatureName = null;
  String sDescription = null;
  String sSpecialAttack = "NA";
  String sSpecialDefense = "NA";
  String sMagicDefense = "Normal";
  String sPSIAttack = "NA";
  String sPSIDefense = "NA";
  TableSaveCreatureDeleteButton bDelete = null;
  TableSaveCreatureLoadButton bLoad = null;
  int nBASENumAtks = 1;
  int nBASEClassIndex = 4;
  int nBASEHD = 0;
  int nBASEHDMod = 0;

//  int nBASESpecHP = 0;
  int nBASEAC = 10;
  int nBASEEXP = -1;
  int nBASEHonor = -1;
  int nBASEMorale = 10;
  int nBASEMove = 12;
  int nBASESizeIndex = 2; // medium
  String sBASESpecHP = "0"; // special hp, either specific amount or like 1d4+10

//  int nBASENumAppearDice = 1; // number of dice, 0 sides means this many
//  int nBASENumAppearSides = 0; // number of sides on dice
  int nBASEAlignmentIndex = 4;
  int nBASEIntel = 10;
  int nBASEHackFactor = 1;
  int nBASEFatigueFactor = 10;
  int nBASEHonorIndex = 1;
  String sBASENumAppearing = "";

  ArrayList aClimate = null;
  ArrayList aTerrain = null;
  int nFrequency = 0;
  int nActivityCycle = 0;
  int nAppearingIn = 0;

  ArrayList lMyAttacks = null;

//  TableArmor oA = null;

  // battlesheet needed fields
  /*
     int nBSHitPoints = 0;
     int nBSLevel = 0;
     int nBSThaco = 0;
     String sBSNote = null;
     ArrayList lMyAttacks = null;
   */
  //

  public TableSavedCreatures() {
    bDelete = new TableSaveCreatureDeleteButton(this);
    bDelete.setText("Remove");
    bLoad = new TableSaveCreatureLoadButton(this);
    bLoad.setText("Load");
    lMyAttacks = new ArrayList();
    aTerrain = new ArrayList();
    aClimate = new ArrayList();

  }

  public TableSavedCreatures(HackSackFrame oThisFrame, String name) {
    this(); //call the above contructor
    this.sCreatureName = name;
    this.oParent = oThisFrame;

    /*  for (int i = 0; i < oParent.sTerrain.length; i++) {
          Terrain oT = new Terrain(oParent.sTerrain[i]);
          aTerrain.add(oT);
        }
        for (int i = 0; i < oParent.sClimate.length; i++) {
          Climate oT = new Climate(oParent.sClimate[i]);
          aClimate.add(oT);
        }
          }
     */
  }

  public int compareTo(Object o) {
    String sThisName = ( (TableSavedCreatures) o).sCreatureName.toUpperCase();
    String sComp = sCreatureName.toUpperCase();
    return sComp.compareTo(sThisName);
  }

  // get creature by name
  public TableSavedCreatures getCreatureByName(String sName) {
    TableSavedCreatures oFound = null;

    for (int i = 0; i < oParent.lSavedCreatures.size() && oFound == null; i++) {
      TableSavedCreatures oRec = (TableSavedCreatures) oParent.lSavedCreatures.
          get(i);
      if (oRec.sCreatureName.equalsIgnoreCase(sName) &&
          oRec != this) {
        oFound = oRec;
      }
    }

    return oFound;
  }

  // does this creatureID exist? if so return it
  public TableSavedCreatures exists(ArrayList lList) {
    TableSavedCreatures oCreature = this;
    TableSavedCreatures oFound = null;

    for (int i = 0; i < lList.size() && oFound == null; i++) {
      TableSavedCreatures oRec = (TableSavedCreatures) lList.get(i);
      if ( //oRec.sCreatureName.equalsIgnoreCase(oCreature.sCreatureName) ||
          oRec.nCreatureID == oCreature.nCreatureID) {
        oFound = oRec;
      }
    }

    return oFound;
  }

  public void deleteButtonPressed(TableSavedCreatures oThisCreature) {
    if (oParent.AskYN(oParent,
                      "Are you sure you want to remove " +
                      oThisCreature.sCreatureName + "?")) {
//    if (true) {
      String sName = oThisCreature.sCreatureName;
      oParent.lSavedCreatures.remove(oThisCreature);

      xmlControl.saveDoc(oParent, oParent,
                         TableSavedCreatures.xmlBuildDocFromList(
          oParent.lSavedCreatures, oParent.nMaxCreatureID),
                         oParent.sCreatureSaveFileName);

      oParent.DisplayCreatureList(oParent.jFindCreatureTextField.getText().
                                  toUpperCase());
      oParent.gmLog(true, true, "Deleted " + oThisCreature.sCreatureName + "\n");
      oParent.ShowDone(oParent, "Deleted " + sName);
    }
  }

  public void loadButtonPressed(TableSavedCreatures oThisCreature) {

    // if creature not loaded right now..
    if (oParent.nCurrentCreatureID != nCreatureID) {
      // if current creature loaded has been changed ask to save before loading
      // something new...
      if (oParent.bCreatureChanged) {
        if (oParent.AskYN(oParent,
                          "Save changes to current creature before loading new one?")) {
          oParent.jGMSaveButton_actionPerformed(null);
        }
      }
      // set the current selection to this creature incase the above caused
      // a save which changed selection to the saved creature
      oParent.jCreatureList.setSelectedValue(this, true);

      // current creature loaded in memory ID.
      oParent.nCurrentCreatureID = oThisCreature.nCreatureID;

      oParent.jGMNameTextField.setText(oThisCreature.sCreatureName);
      oParent.jGMACSpinner.getModel().setValue(new Integer(oThisCreature.
          nBASEAC));
      oParent.jGMClassComboBox.setSelectedIndex(oThisCreature.nBASEClassIndex);

      oParent.jGMHDiceSpinner.getModel().setValue(new Integer(oThisCreature.
          nBASEHD));
      oParent.jGMHDModSpinner.getModel().setValue(new Integer(oThisCreature.
          nBASEHDMod));
      oParent.jGMSpecHP.setText(oThisCreature.sBASESpecHP);

      oParent.jGMSpecialAttack.setText(oThisCreature.sSpecialAttack);
      oParent.JGMSpecialDefense.setText(oThisCreature.sSpecialDefense);
      oParent.jGMEXPSpinner.getModel().setValue( (new Integer(oThisCreature.
          nBASEEXP)));
      oParent.jGMMoraleSpinner.getModel().setValue( (new Integer(oThisCreature.
          nBASEMorale)));
      oParent.jGMMoveSpinner.getModel().setValue( (new Integer(oThisCreature.
          nBASEMove)));
      oParent.jGMSizeComboBox.setSelectedIndex(oThisCreature.nBASESizeIndex);
      oParent.jGMAlignmentComboBox.setSelectedIndex(oThisCreature.
          nBASEAlignmentIndex);
      oParent.jGMHackFactorSpinner.getModel().setValue( (new Integer(
          oThisCreature.nBASEHackFactor)));
      oParent.jGMDescTextArea.setText(oThisCreature.sDescription);

      oParent.jGMHonorComboBox.setSelectedIndex(oThisCreature.nBASEHonorIndex);

      oParent.jGMFatigueFactorSpinner.getModel().setValue( (new Integer(
          oThisCreature.nBASEFatigueFactor)));

      // this will force the attack section to be drawn properly since
      // the action is triggered by change
      oParent.jGMNumAtksSpinner.getModel().setValue(new Integer(0));

      for (int i = 0; i < oThisCreature.lMyAttacks.size(); i++) {
        CreatureCombat oAtk = new CreatureCombat(oParent, null);
        TableSavedCreatureCombat oThisATK = (TableSavedCreatureCombat)
            oThisCreature.lMyAttacks.get(i);
        oAtk.jWeaponType.setSelectedIndex(oThisATK.nWeaponType);
        oAtk.jDamageDice.setText(oThisATK.sDamageDice);
        oAtk.jToHitMod.getModel().setValue(new Integer(oThisATK.nToHitMod));
        oAtk.jTotalMod.getModel().setValue(new Integer(oThisATK.nTotalMod));
        oAtk.jModCrit.getModel().setValue(new Integer(oThisATK.nModCrit));
        oAtk.jModFumble.getModel().setValue(new Integer(oThisATK.nModFumble));
        oAtk.jModPenetration.getModel().setValue(new Integer(oThisATK.
            nModPenetration));

        oParent.lAttacks.add(oAtk);
      }

      // clear values first
      for (int i = 0; i < oParent.aClimate.size(); i++) {
        Climate oN = (Climate) oParent.aClimate.get(i);
        oN.jActive.setSelected(false);
      }
      for (int i = 0; i < oParent.aTerrain.size(); i++) {
        Terrain oN = (Terrain) oParent.aTerrain.get(i);
        oN.jActive.setSelected(false);
      } // cleared...

      // now set values we have saved into creature builder vars
      for (int i = 0; i < oThisCreature.aClimate.size(); i++) {
        Climate oC = (Climate) oThisCreature.aClimate.get(i);
        Climate oN = oC.getSameClimate(oC, oParent.aClimate);
        if (oN != null) {
//      Climate oN = (Climate)oParent.aClimate.get(i);
          oN.jActive.setSelected(oC.jActive.isSelected());
          oN.sName = oC.sName;
        }
      }
      for (int i = 0; i < oThisCreature.aTerrain.size(); i++) {
        Terrain oC = (Terrain) oThisCreature.aTerrain.get(i);
//      Terrain oN = (Terrain)oParent.aTerrain.get(i);
        Terrain oN = oC.getSameTerrain(oC, oParent.aTerrain);
        if (oN != null) {
          oN.jActive.setSelected(oC.jActive.isSelected());
          oN.sName = oC.sName;
        }
      } // end terrain/climate

      oParent.jFrequencyComboBox.setSelectedIndex(oThisCreature.nFrequency);
      oParent.jActivityCycleComboBox.setSelectedIndex(oThisCreature.
          nActivityCycle);
      oParent.jAppearingInComboBox.setSelectedIndex(oThisCreature.nAppearingIn);
      if (oParent.lAttacks.size() == 0) {
        oParent.jGMNumAtksSpinner.getModel().setValue(new Integer(0));
        oParent.jGMAttackPanel.removeAll();
        oParent.repaint();
      }
      else {
        oParent.jGMNumAtksSpinner.getModel().setValue(new Integer(oThisCreature.
            nBASENumAtks));
      }
      oParent.gmLog(true, true, "Loaded " + oThisCreature.sCreatureName + "\n");

      // set this to false cause new creature loaded
      // done last because all the value changing triggers it to be true...
      oParent.bCreatureChanged = false;
    }
    else {
      oParent.ShowError(oParent, sCreatureName + " is already loaded.\n");
    }
  }

// return terrain structure if you find the name
  Terrain getTerrainByName(String sFindName) {
    Terrain oFound = null;
    for (int i = 0; i < aTerrain.size(); i++) {
      Terrain oT = (Terrain) aTerrain.get(i);
      if (oT.sName.equalsIgnoreCase(sFindName)) {
        oFound = oT;
      }
    }
    return oFound;
  }

// return climate structure if you find the name
  Climate getClimateByName(String sFindName) {
    Climate oFound = null;
    for (int i = 0; i < aClimate.size(); i++) {
      Climate oC = (Climate) aClimate.get(i);
      if (oC.sName.equalsIgnoreCase(sFindName)) {
        oFound = oC;
      }
    }
    return oFound;
  }

  /* 	LOAD
          lClass = TableClass.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sClassSaveFile));

          SAVE
          xmlControl.saveDoc(oParent,oParent,TableClass.xmlBuildDocFromList(
   oParent.lClass,oParent.nMaxClassID),oParent.sClassSaveFile+".NEW");
   */


  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eCreature = new Element("Creature");

    try {
      eCreature.addContent(new Element("sCreatureName").setText(xmlControl.
          escapeChars(sCreatureName)));
      eCreature.addContent(new Element("sDescription").setText(xmlControl.
          escapeChars(sDescription)));
      eCreature.addContent(new Element("nCreatureID").setText("" + nCreatureID));
      eCreature.addContent(new Element("nBASEAC").setText("" + nBASEAC));
      eCreature.addContent(new Element("nBASEHD").setText("" + nBASEHD));
      eCreature.addContent(new Element("nBASEHDMod").setText("" + nBASEHDMod));
      eCreature.addContent(new Element("sBASESpecHP").setText(sBASESpecHP));
      eCreature.addContent(new Element("nBASEClassIndex").setText("" +
          nBASEClassIndex));
      eCreature.addContent(new Element("nBASEEXP").setText("" + nBASEEXP));
      eCreature.addContent(new Element("nBASEHonor").setText("" + nBASEHonor));
      eCreature.addContent(new Element("nBASENumAtks").setText("" +
          nBASENumAtks));
      eCreature.addContent(new Element("sSpecialAttack").setText(sSpecialAttack));
      eCreature.addContent(new Element("sSpecialDefense").setText(
          sSpecialDefense));
      eCreature.addContent(new Element("nBASEAlignmentIndex").setText("" +
          nBASEAlignmentIndex));
      eCreature.addContent(new Element("nBASEHackFactor").setText("" +
          nBASEHackFactor));
      eCreature.addContent(new Element("nBASEMorale").setText("" + nBASEMorale));
      eCreature.addContent(new Element("nBASEMove").setText("" + nBASEMove));
      eCreature.addContent(new Element("nBASESizeIndex").setText("" +
          nBASESizeIndex));
      eCreature.addContent(new Element("nBASEHonorIndex").setText("" +
          nBASEHonorIndex));
      eCreature.addContent(new Element("nBASEFatigueFactor").setText("" +
          nBASEFatigueFactor));
      eCreature.addContent(new Element("nFrequency").setText("" + nFrequency));
      eCreature.addContent(new Element("nActivityCycle").setText("" +
          nActivityCycle));
      eCreature.addContent(new Element("nAppearingIn").setText("" +
          nAppearingIn));

      for (int i = 0; i < lMyAttacks.size(); i++) {
        TableSavedCreatureCombat oA = (TableSavedCreatureCombat) lMyAttacks.get(
            i);
        eCreature.addContent(oA.xmlGetElements());
      }

      if (aTerrain.size() > 0) {
        Element eTerrain = new Element("Terrain");
        for (int i = 0; i < aTerrain.size(); i++) {
          Terrain oT = (Terrain) aTerrain.get(i);
          if (oT.jActive.isSelected()) {
            eTerrain.addContent(oT.xmlGetElements());
          }
        }
        eCreature.addContent(eTerrain);
      }

      if (aClimate.size() > 0) {
        Element eClimate = new Element("Climate");
        for (int i = 0; i < aClimate.size(); i++) {
          Climate oC = (Climate) aClimate.get(i);
          if (oC.jActive.isSelected()) {
            eClimate.addContent(oC.xmlGetElements());
          }
        }
        eCreature.addContent(eClimate);
      }

    }
    catch (java.lang.OutOfMemoryError err) {
      oParent.ShowError(oParent,
          "Out of memory error while trying to save creatures.\nError:" +
                        err.getLocalizedMessage());
    }

    return eCreature;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static TableSavedCreatures xmlGetFromElements(Element eItem,
                                                HackSackFrame oParent) {
    TableSavedCreatures oO = new TableSavedCreatures(oParent, "");

    oO.sCreatureName = xmlControl.unEscapeChars(eItem.getChild("sCreatureName").
                                                getText());
    oO.sDescription = xmlControl.unEscapeChars(eItem.getChild("sDescription").
                                               getText());

    oO.sBASESpecHP = eItem.getChild("sBASESpecHP").getText();
    oO.sSpecialAttack = eItem.getChild("sSpecialAttack").getText();
    oO.sSpecialDefense = eItem.getChild("sSpecialDefense").getText();

    oO.nCreatureID = Integer.parseInt(eItem.getChild("nCreatureID").getText());
    oO.nBASEAC = Integer.parseInt(eItem.getChild("nBASEAC").getText());
    oO.nBASEHD = Integer.parseInt(eItem.getChild("nBASEHD").getText());
    oO.nBASEHDMod = Integer.parseInt(eItem.getChild("nBASEHDMod").getText());
    oO.nBASEClassIndex = Integer.parseInt(eItem.getChild("nBASEClassIndex").
                                          getText());
    oO.nBASEEXP = Integer.parseInt(eItem.getChild("nBASEEXP").getText());
    oO.nBASEHonor = Integer.parseInt(eItem.getChild("nBASEHonor").getText());
    oO.nBASENumAtks = Integer.parseInt(eItem.getChild("nBASENumAtks").getText());
    oO.nBASEAlignmentIndex = Integer.parseInt(eItem.getChild(
        "nBASEAlignmentIndex").getText());
    oO.nBASEHackFactor = Integer.parseInt(eItem.getChild("nBASEHackFactor").
                                          getText());
    oO.nBASEMorale = Integer.parseInt(eItem.getChild("nBASEMorale").getText());
    oO.nBASEMove = Integer.parseInt(eItem.getChild("nBASEMove").getText());
    oO.nBASESizeIndex = Integer.parseInt(eItem.getChild("nBASESizeIndex").
                                         getText());
    oO.nBASEHonorIndex = Integer.parseInt(eItem.getChild("nBASEHonorIndex").
                                          getText());
    oO.nBASEFatigueFactor = Integer.parseInt(eItem.getChild(
        "nBASEFatigueFactor").getText());
    oO.nFrequency = Integer.parseInt(eItem.getChild("nFrequency").getText());
    oO.nActivityCycle = Integer.parseInt(eItem.getChild("nActivityCycle").
                                         getText());
    oO.nAppearingIn = Integer.parseInt(eItem.getChild("nAppearingIn").getText());

    java.util.List lAtks = eItem.getChildren("Attack");
    Iterator in = lAtks.iterator();
    while (in.hasNext()) {
      Element eA = (Element) in.next();
      TableSavedCreatureCombat oA = TableSavedCreatureCombat.xmlGetFromElements(
          eA);
      oO.lMyAttacks.add(oA);
    }

    Element eTerrain = eItem.getChild("Terrain");
    if (eTerrain != null) {
      java.util.List lT = eTerrain.getChildren("Type");
      if (lT.size() > 0) {
        Iterator jn = lT.iterator();
        while (jn.hasNext()) {
          Element eA = (Element) jn.next();
          Terrain oA = Terrain.xmlGetFromElements(eA);
          oO.aTerrain.add(oA);
        }
      }
    }

    Element eClimate = eItem.getChild("Climate");
    if (eClimate != null) {
      java.util.List lC = eClimate.getChildren("Type");
      if (lC.size() > 0) {
        Iterator kn = lC.iterator();
        while (kn.hasNext()) {
          Element eA = (Element) kn.next();
          Climate oA = Climate.xmlGetFromElements(eA);
          oO.aClimate.add(oA);
        }
      }
    }

    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved to
   * an xml file.
   *
   * @param lList ArrayList
   * @param nMaxID int
   * @return Document
   */
  static Document xmlBuildDocFromList(ArrayList lList, int nMaxID) {
    Element eRoot = new Element("MonsterList");
    eRoot.setAttribute(new Attribute("JDOM", "10b"));
    eRoot.addContent(new Element("nMaxID").setText("" + nMaxID));
    Document doc = new Document(eRoot);

    for (int i = 0; i < lList.size(); i++) {

      TableSavedCreatures oO = (TableSavedCreatures) lList.get(i);

      /*       // all this was to trim down the creatures file
       TableSavedCreatures oOld = oO.getCreatureByName(oO.sCreatureName);
              if (oOld == null) {
                 if (oO.nBASEMorale == 10 && oO.nBASEMove == 12 &&
                     !oO.sDescription.matches("(?si)^(AD&D:).*") &&
                     oO.aTerrain.size() == 0 &&
                     oO.aClimate.size() == 0 &&
                     oO.nAppearingIn == 0)
                 {
                 // do nothing
                 } else
                if (!oO.sDescription.equalsIgnoreCase("null"))
       */
      eRoot.addContent(oO.xmlGetElements());
      /*        } else {
                if ((oO.sDescription.matches("(?si)^(AD&D:).*") ||
                    oO.sCreatureName.matches("(?si)^(npc:).*") ||
                    oO.sDescription.length() > 30 ||
                    oO.aTerrain.size() > 0 ||
                    oO.aClimate.size() > 0) &&
                   !oO.sDescription.equalsIgnoreCase("null")) {
                  eRoot.addContent(oO.xmlGetElements());
                }
              }
       */
    }

    return doc;
  }

  /**
   * this builds an arraylist of this object type from a document that was
   * built from a xml file, Class.xml, Gear.xml/etc...
   *
   * @param oParent HackSackFrame
   * @param doc Document
   * @return ArrayList
   */
  static ArrayList xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {
    ArrayList lList = new ArrayList();
    boolean bChanged = false;

    try {
      // <MonsterList> .. </MonsterList>
      Element eRoot = doc.getRootElement();
      oParent.nMaxCreatureID = Integer.parseInt(eRoot.getChild("nMaxID").
                                                getText());

      java.util.List lItems = eRoot.getChildren("Creature");

      Iterator in = lItems.iterator();
      while (in.hasNext()) {
        Element eItem = (Element) in.next();
        TableSavedCreatures oO = TableSavedCreatures.xmlGetFromElements(eItem,
            oParent);

        TableSavedCreatures oOld = oO.exists(lList);
        if (oOld != null) {
          oO.nCreatureID = (oParent.nMaxCreatureID++);
          oParent.gmLog(false,false,oO.sCreatureName+" had duplicate ID as "+oOld.sCreatureName+" changed to "+oO.nCreatureID+"\n");
          bChanged = true;
        }

        if (oO.nCreatureID > oParent.nMaxCreatureID) {
          oParent.nMaxClassID = (oO.nCreatureID + 1);

        }
        lList.add(oO);
      }
      if (bChanged) {
        xmlControl.saveDoc(oParent,oParent,TableSavedCreatures.xmlBuildDocFromList(
              oParent.lSavedCreatures,oParent.nMaxCreatureID),oParent.sCreatureSaveFileName);
      }
    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load saved creatures from XML.");
    }
    return lList;
  }

}

class Terrain {
  String sName = null;
  JCheckBox jActive = null;

  public Terrain(String sSubmitName) {
    sName = sSubmitName;
    jActive = new JCheckBox(sSubmitName, false);
  }

  Terrain getSameTerrain(Terrain oSource, ArrayList aList) {
    Terrain oC = null;
    for (int i = 0; i < aList.size(); i++) {
      Terrain oTest = (Terrain) aList.get(i);
      if (oTest.sName.equalsIgnoreCase(oSource.sName)) {
        oC = oTest;
      }
    }
    return oC;
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Type");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
//    eItem.addContent(new Element("jActive").setText(jActive.isSelected()?"true":"false"));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static Terrain xmlGetFromElements(Element eItem) {
    Terrain oO = new Terrain("");

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
//    oO.jActive.setSelected(eItem.getChild("jActive").getText().equalsIgnoreCase("true"));
    oO.jActive.setSelected(true);

    return oO;
  }

}

class Climate {
  String sName = null;
  JCheckBox jActive = null;

  public Climate(String sSubmitName) {
    sName = sSubmitName;
    jActive = new JCheckBox(sSubmitName, false);
  }

  Climate getSameClimate(Climate oSource, ArrayList aList) {
    Climate oC = null;
    for (int i = 0; i < aList.size(); i++) {
      Climate oTest = (Climate) aList.get(i);
      if (oTest.sName.equalsIgnoreCase(oSource.sName)) {
        oC = oTest;
      }
    }
    return oC;
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Type");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
//    eItem.addContent(new Element("jActive").setText(jActive.isSelected()?"true":"false"));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static Climate xmlGetFromElements(Element eItem) {
    Climate oO = new Climate("");

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
//    oO.jActive.setSelected(eItem.getChild("jActive").getText().equalsIgnoreCase("true"));
    // if it's listed on the creature then it's TRUE
    // we dont list non-true stuff
    oO.jActive.setSelected(true);

    return oO;
  }

}

// load button
class TableSaveCreatureLoadButton
    extends JButton {
  private TableSavedCreatures oMe = null;

  private TableSaveCreatureLoadButton() {
  }

  public TableSaveCreatureLoadButton(TableSavedCreatures oMe) {
    this.oMe = oMe;
    addActionListener(new TableSaveCreatureLoadButtonListener(oMe));
  }
}

class TableSaveCreatureLoadButtonListener
    implements ActionListener {
  private TableSavedCreatures oMe = null;

  public TableSaveCreatureLoadButtonListener(TableSavedCreatures oMe) {
    this.oMe = oMe;
  }

  public void actionPerformed(ActionEvent e) {
    oMe.loadButtonPressed(oMe);
  }
}

// delete stuff
class TableSaveCreatureDeleteButton
    extends JButton {
  private TableSavedCreatures oMe = null;

  private TableSaveCreatureDeleteButton() {
  }

  public TableSaveCreatureDeleteButton(TableSavedCreatures oMe) {
    this.oMe = oMe;
    addActionListener(new TableSaveCreatureDeleteButtonListener(oMe));
  }
}

class TableSaveCreatureDeleteButtonListener
    implements ActionListener {
  private TableSavedCreatures oMe = null;

  public TableSaveCreatureDeleteButtonListener(TableSavedCreatures oMe) {
    this.oMe = oMe;
  }

  public void actionPerformed(ActionEvent e) {
    oMe.deleteButtonPressed(oMe);
  }
}
