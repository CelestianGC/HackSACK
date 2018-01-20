package hacksack;
// LoadPlayer XML file
import java.io.*;
import javax.xml.parsers.*;

import java.awt.*;
import javax.swing.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SaxParserForPL extends DefaultHandler {
  public static HackSackFrame oParent = null;
  TablePlayer oNew;
  Abilities oA;
//  AbilityMods oM;
  Saves oSave;
  Coins oCoin;
  Class oClass;
  Skills oSkill;
  Gear oGear;
  Quirks oQuirk;

  public static void loadUp(HackSackFrame oThis, String sFileName,boolean bFullPath) {
    oParent = oThis;
    System.setProperty("javax.xml.parsers.SAXParserFactory",
                       "org.apache.xerces.jaxp.SAXParserFactoryImpl");
//    String url = "file:\\\\\\" + sFileName;
    String url = (!bFullPath?"file:\\\\\\":"")+sFileName;

    try {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setValidating(false);
      parserFactory.setNamespaceAware(false);
      SaxParserForPL SaxXMLParserForPLInstance = new SaxParserForPL();
      SAXParser parser = parserFactory.newSAXParser();
      parser.parse(url, SaxXMLParserForPLInstance);
    }
    catch (IOException ex) {
      oParent.gmLog(true, false,
                    "IOException in SaxXMLParser.loadUP() on file " + sFileName +
                    "\n");
      ex.printStackTrace();
    }
    catch (SAXException ex) {
      oParent.gmLog(true, false,
                    "SAXException in SaxXMLParser.loadUP() on file " +
                    sFileName + "\n");
      ex.printStackTrace();
    }
    catch (ParserConfigurationException ex) {
      oParent.gmLog(true, false,
          "ParserConfigurationException in SaxXMLParser.loadUP() on file " +
          sFileName + "\n");
      ex.printStackTrace();
    }
    catch (FactoryConfigurationError ex) {
      oParent.gmLog(true, false,
          "FactoryConfigurationError in SaxXMLParser.loadUP() on file " +
          sFileName + "\n");
      ex.printStackTrace();
    }

  }

  public void characters(char[] ch, int start, int length) throws SAXException {
    String s = new String(ch, start, length);
//oParent.jTextArea1.append(" Value: " + s);
  }

  public void endDocument() throws SAXException {
//oParent.jGMTextArea.append("end document");
  }

  public void endElement(String uri, String localName, String qName) throws
      SAXException {
//oParent.jTextArea1.append("end element");
    if (qName.equalsIgnoreCase("Player")) { // creature finished, load to mem
      if (oNew.lPlayerID == -1)
        oNew.lPlayerID = System.currentTimeMillis();
      oParent.gplPlayer = oNew;

//      oParent.lPlayers.add(oNew);
    }
    if (qName.equalsIgnoreCase("abilities")) {
      oNew.aAbilities.add(oA);
    }

    if (qName.equalsIgnoreCase("abilitymods")) {
//      oA.aMods.add(oM);
    }

    if (qName.equalsIgnoreCase("saves")) {
      oNew.aSaves.add(oSave);
    }
    if (qName.equalsIgnoreCase("coins")) {
      oNew.aCoins.add(oCoin);
    }
    if (qName.equalsIgnoreCase("class")) {
      oNew.aClass.add(oClass);
    }
    if (qName.equalsIgnoreCase("gear")) {
      oNew.aGear.add(oGear);
    }
    if (qName.equalsIgnoreCase("skills")) {
      oNew.aSkills.add(oSkill);
    }
    if (qName.equalsIgnoreCase("quirks")) {
      oNew.aQuirks.add(oQuirk);
    }
  }

  public void startDocument() throws SAXException {
//oParent.jGMTextArea.append("start document: ");
  }

  public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {

    if (qName.equalsIgnoreCase("Player")) {
      oNew = new TablePlayer(oParent);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("lPlayerID"))
          oNew.lPlayerID = Long.parseLong(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("sPlayerName"))
          oNew.sPlayerName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sCharacter"))
          oNew.sCharacter = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sFamilyClan"))
          oNew.sFamilyClan = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sHomeland"))
          oNew.sHomeland = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sGawd"))
          oNew.sGawd = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sPatron"))
          oNew.sPatron = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sAppearance"))
          oNew.sAppearance = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sBirthDate"))
          oNew.sBirthDate = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sFamilyHistory"))
          oNew.sFamilyHistory = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sHairColour"))
          oNew.sHairColour = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sEyeColour"))
          oNew.sEyeColour = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sSpecialAbilities"))
          oNew.sSpecialAbilities = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sMiscInfo"))
          oNew.sMiscInfo = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sRace"))
          oNew.sRace = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jPlayerLog"))
          oNew.jPlayerLog.setText(oParent.unEscapeChars(attributes.getValue(i)));

        if (attributes.getQName(i).equalsIgnoreCase("nBirthRank"))
          oNew.nBirthRank = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nAge"))
          oNew.nAge = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nHeight"))
          oNew.nHeight = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nWeight"))
          oNew.nWeight = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSocialClassIndex"))
          oNew.nSocialClassIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nNumberSiblings"))
          oNew.nNumberSiblings = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nHandIndex"))
          oNew.nHandIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nAlignementIndex"))
          oNew.nAlignementIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSexIndex"))
          oNew.nSexIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nAIP"))
          oNew.nAIP = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jSizeComboBox"))
          oNew.jSizeComboBox.setSelectedIndex(Integer.parseInt(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("AC_NORMAL"))
          oNew.nAC[oNew.AC_NORMAL].getModel().setValue(new Integer(Integer.
              parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("AC_REAR"))
          oNew.nAC[oNew.AC_REAR].getModel().setValue(new Integer(Integer.
              parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("AC_SHIELDLESS"))
          oNew.nAC[oNew.AC_SHIELDLESS].getModel().setValue(new Integer(Integer.
              parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("AC_SUPRISED"))
          oNew.nAC[oNew.AC_SUPRISED].getModel().setValue(new Integer(Integer.
              parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jHealthMax"))
          oNew.jHealthMax.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jHealthLabel"))
          oNew.jHealthLabel.setText(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jMove"))
          oNew.jMove.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jHonor"))
          oNew.jHonor.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));

        if (attributes.getQName(i).equalsIgnoreCase("nTemporalHonor"))
          oNew.nTemporalHonor = (Integer.parseInt(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("jCoinWeightIncluded"))
          oNew.jCoinWeightIncluded.setSelected(attributes.getValue(i).equalsIgnoreCase("0")?false:true);
      }

    }

    if (qName.equalsIgnoreCase("Abilities")) {
      oA = new Abilities(oParent, oNew);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oA.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jAdjScore"))
          oA.jAdjScore.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jAdjPercent"))
          oA.jAdjPercent.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
      }

    }

/*    if (qName.equalsIgnoreCase("AbilityMods")) {
      oM = new AbilityMods(oParent);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oM.jName.setText(oParent.unEscapeChars(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("nMod"))
          oM.nMod = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jMod"))
          oM.jMod.setText(attributes.getValue(i));
      }
    }
  */
    if (qName.equalsIgnoreCase("Class")) {
      oClass = new Class(oParent, oNew);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oClass.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nFightAs"))
          oClass.nFightAs = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSaveAs"))
          oClass.nSaveAs = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nClassindex"))
          oClass.nClassindex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nClassID"))
          oClass.nClassID =Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jEXP"))
          oClass.jEXP.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jLevel"))
          oClass.jLevel.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
      }
    }

    if (qName.equalsIgnoreCase("Saves")) {
      oSave = new Saves(oParent, oNew);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oSave.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSaveType"))
          oSave.nSaveType = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nBaseSave"))
          oSave.nBaseSave = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jSave"))
          oSave.jSave.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jSaveMod"))
          oSave.jSaveMod.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jSaveOther"))
          oSave.jSaveOther.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
      }
    }
    if (qName.equalsIgnoreCase("Coins")) {
      oCoin = new Coins(oParent, oNew);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oCoin.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jCount"))
          oCoin.jCount.setText(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jCount"))
          oCoin.jMod.setValue(new Integer(Integer.parseInt(attributes.getValue(i))));
      }
    }
    if (qName.equalsIgnoreCase("Gear")) {
      oGear = new Gear();
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oGear.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sDesc"))
          oGear.sDesc = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sLoc"))
          oGear.sLoc = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nCount")) {
          oGear.nCount = Integer.parseInt(attributes.getValue(i));
          oGear.jMod.setValue(new Integer(oGear.nCount));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nGearID"))
          oGear.nGearID = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("dWeight"))
          oGear.dWeight = Double.parseDouble(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("sItemType"))
          oGear.sItemType = oParent.unEscapeChars(attributes.getValue(i));

          // weapons specific
        if (attributes.getQName(i).equalsIgnoreCase("sWeaponGroup"))
          oGear.sWeaponGroup = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sWeaponType"))
          oGear.sWeaponType = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSpeedFactor"))
          oGear.nSpeedFactor = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_TINY"))
          oGear.jDamage[oGear.DAMAGE_SIZE_TINY].setText(oParent.unEscapeChars(
              attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_SMALL"))
          oGear.jDamage[oGear.DAMAGE_SIZE_SMALL].setText(oParent.unEscapeChars(
              attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_MEDIUM"))
          oGear.jDamage[oGear.DAMAGE_SIZE_MEDIUM].setText(oParent.unEscapeChars(
              attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_LARGE"))
          oGear.jDamage[oGear.DAMAGE_SIZE_LARGE].setText(oParent.unEscapeChars(
              attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_HUGE"))
          oGear.jDamage[oGear.DAMAGE_SIZE_HUGE].setText(oParent.unEscapeChars(
              attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("DAMAGE_SIZE_GIANT"))
          oGear.jDamage[oGear.DAMAGE_SIZE_GIANT].setText(oParent.unEscapeChars(
              attributes.getValue(i)));

          // container specific
        if (attributes.getQName(i).equalsIgnoreCase("dMaxWeightContained"))
          oGear.dMaxWeightContained = Double.parseDouble(attributes.getValue(i));
      }

    }
    if (qName.equalsIgnoreCase("Skills")) {
      oSkill = new Skills();
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oSkill.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sDesc"))
          oSkill.sDesc = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jSkillSpinner"))
          oSkill.jSkillSpinner.getModel().setValue(new Integer(Integer.parseInt(
              attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("nSkillID"))
          oSkill.nSkillID = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nSkillType"))
          oSkill.nSkillType = Integer.parseInt(attributes.getValue(i));
      }
    }
    if (qName.equalsIgnoreCase("Quirks")) {
      oQuirk = new Quirks();
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oQuirk.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sDesc"))
          oQuirk.sDesc = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nQuirkID"))
          oQuirk.nQuirkID = Integer.parseInt(attributes.getValue(i));
      }
    }

  }
}

