package hacksack;

import java.io.*;
import javax.xml.parsers.*;
import java.awt.*;
import javax.swing.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
//import com.borland.jb.io.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 *  this is what I use to import HMTK/XML character sheets.
 */

public class SaxParserXMLCharacter
    extends DefaultHandler {
  public static HackSackFrame oParent = null;
  TablePlayer oNewPlayer;
  TableQuirks oQ;
  Class oClass;
  Abilities oA;
  Skills oS;
  Gear oG;
  Coins oC;
  String sDataField = "";

  boolean bHACKMASTER_CHARACTER = false;
  public static boolean bHACKMASTER_CHARACTER_FOUND = false;
  boolean bBIOGRAPHICAL_INFORMATION = false;
  boolean bATTRIBUTES = false;
  boolean bABILITY_SCORE = false;
  boolean bSCORE = false;
  boolean bHONOR = false;
  boolean bMOVEMENT_RATES = false;
  boolean bMOVEMENT_RATES_CATEGORY = false;
  boolean bMOVEMENT_GET_RATE = false;
  boolean bRACIAL_ATTRIBUTES = false;
  boolean bQUIRKS_AND_FLAWS = false;
  boolean bSTP_LIST = false;
  boolean bINVENTORY = false;
  boolean bITEM = false;
  boolean bEXPERIENCE_POINTS = false;
  boolean bSKILL_LIST = false;
  boolean bSKILL_RATING = false;
//  boolean bCOINS_AND_MONEY = false;
  boolean bFoundCOIN = false;
  boolean bWEAPON_GROUP = false;
  boolean bWEAPON = false;
  String sWeaponGroup = "";
  String sDamageSize = "";
  String sEncumType = "";
  String sInventoryCatagory = "";
  String[] sLevelParams;
  String sCoinMatchRegX = "(?si)^coin.*";
  String sVersion = null;
  String sValidVersion = "4.2.1";

  public static boolean loadUp(HackSackFrame oThis, String sFileName) {
    oParent = oThis;

    System.setProperty("javax.xml.parsers.SAXParserFactory",
                       "org.apache.xerces.jaxp.SAXParserFactoryImpl");
    String uri = sFileName;
    try {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setValidating(false);
      parserFactory.setNamespaceAware(false);
      SaxParserXMLCharacter SaxParserXMLCharacterInstance = new
          SaxParserXMLCharacter();
      SAXParser parser = parserFactory.newSAXParser();
      parser.parse(uri, SaxParserXMLCharacterInstance);
    }
    catch (IOException ex) {
      oParent.gmLog(true, false,
                    "IOException in SaxParserXMLCharacter.loadUP() on file " +
                    sFileName + "(" + ex.getMessage() + ")\n");
      ex.printStackTrace();
    }
    catch (SAXException ex) {
      oParent.gmLog(true, false,
                    "SAXException in SaxParserXMLCharacter.loadUP() on file " +
                    sFileName + "(" + ex.getMessage() + ")\n");
      oParent.ShowDone(oParent,"SAXException in SaxParserXMLCharacter.loadUP()\non file " +
                    sFileName + "\n(" + ex.getMessage() + ")\n");
      ex.printStackTrace();
    }
    catch (ParserConfigurationException ex) {
      oParent.gmLog(true, false,
                    "ParserConfigurationException in SaxParserXMLCharacter.loadUP() on file " +
                    sFileName + "(" + ex.getMessage() + ")\n");
      oParent.ShowDone(oParent,"ParserConfigurationException in SaxParserXMLCharacter.loadUP()\non file " +
                    sFileName + "\n(" + ex.getMessage() + ")\n");
      ex.printStackTrace();
    }
    catch (FactoryConfigurationError ex) {
      oParent.gmLog(true, false,
                    "FactoryConfigurationError in SaxParserXMLCharacter.loadUP() on file " +
                    sFileName + "(" + ex.getMessage() + ")\n");
      ex.printStackTrace();
    }

    return bHACKMASTER_CHARACTER_FOUND;
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
    String s = new String(ch, start, length);
//oParent.DiceOutTextArea.append(" Value: " + s);
    s = s.replaceAll("(\n)|(\r)", "");
    if (!s.equals("")) {
      sDataField += s; // gotta append cause ' <> & and such will cause it to
                       // reset, this makes it continue properly, its cleared
                       // after an endElement()
    }

  }

  public void endDocument() throws SAXException {
//oParent.jGMTextArea.append("end document");


    if (bHACKMASTER_CHARACTER_FOUND) {
      if (!sVersion.equals(sValidVersion)) {
        oParent.ShowDone(oParent.fPlayerGroupFrame,
                         "This XML character sheet was not created with HMTK v"+
                         sValidVersion+"\n"+
                         "There might be errors with this character.\n");
      }
      // Save new quirks.
//      SaveQuirks.toFile(oParent, oParent.sQuirksSaveFile, oParent.lQuirks);
      xmlControl.saveDoc(oParent,oParent,TableQuirks.xmlBuildDocFromList(
        oParent.lQuirks,oParent.nMaxQuirksID),oParent.sQuirksSaveFile);

      // Save new skills.
//      SaveSkills.toFile(oParent, oParent.sSkillsSaveFile, oParent.lSkills);
      xmlControl.saveDoc(oParent,oParent,TableSkills.xmlBuildDocFromList(
            oParent.lSkills,oParent.nMaxSkillsID),oParent.sSkillsSaveFile);

      // Save new Gear.
//      SaveGear.toFile(oParent, oParent.sGearSaveFile, oParent.lGear);
      xmlControl.saveDoc(oParent,oParent,
                         TableGear.xmlBuildDocFromList(oParent.lGear,oParent.nMaxGearID),oParent.sGearSaveFile);
      // Save new class.
//      SaveClass.toFile(oParent, oParent.sClassSaveFile, oParent.lClass);
      xmlControl.saveDoc(oParent,oParent,TableClass.xmlBuildDocFromList(
        oParent.lClass,oParent.nMaxClassID),oParent.sClassSaveFile);

      // set the playerID
      oNewPlayer.lPlayerID = System.currentTimeMillis();

      DialogImportCharacterName dlg =
          new DialogImportCharacterName(oParent,oNewPlayer);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.setVisible(true);

      // add the new character to the group panel
      oParent.gplGroupLog.lPlayers.add(oNewPlayer);
      oParent.fPlayerGroupFrame.mPartyPlayerList.addElement(oNewPlayer);
//      oParent.fPlayerGroupFrame.LoadPartyGroupPane(oParent, oNewPlayer);
    }
  }

  public void endElement(String uri, String localName, String qName) throws
      SAXException {

    // add character to group/etc.
    if (qName.equalsIgnoreCase("HACKMASTER_CHARACTER")) {
      bHACKMASTER_CHARACTER = false;
    }

    if (qName.equalsIgnoreCase("VERSION")) {
      sVersion = sDataField.trim();
    }

    // bio info
    if (qName.equalsIgnoreCase("BIOGRAPHICAL_INFORMATION")) {
      bBIOGRAPHICAL_INFORMATION = false;
    }
    if (bBIOGRAPHICAL_INFORMATION) {
      if (qName.equalsIgnoreCase("CHARACTER_NAME")) {
        oNewPlayer.sCharacter = sDataField;
      }
      if (qName.equalsIgnoreCase("PLAYER_NAME")) {
        oNewPlayer.sPlayerName = sDataField;
      }
      if (qName.equalsIgnoreCase("PLAYER_NAME")) {
        oNewPlayer.sRace = sDataField;
      }
      if (qName.equalsIgnoreCase("RACE")) {
        oNewPlayer.sRace = sDataField;
      }
      if (qName.equalsIgnoreCase("DESCRIPTION")) {
        oNewPlayer.sMiscInfo = sDataField;
      }
      if (qName.equalsIgnoreCase("ALIGNMENT")) {
        sDataField = sDataField.replaceAll("\\s+", ""); // strip out all white space
        for (int i = 0; i < oParent.gmAlignmentTable.length; i++) {
          if (oParent.gmAlignmentTable[i].equalsIgnoreCase(sDataField)) {
            oNewPlayer.nAlignementIndex = i;
          }
        }
      }
      if (qName.equalsIgnoreCase("SOCIAL_CLASS")) {
        for (int i = 0; i < oParent.gmSocialClassTable.length; i++) {
          if (oParent.gmSocialClassTable[i].equalsIgnoreCase(sDataField)) {
            oNewPlayer.nSocialClassIndex = i;
          }
        }
      }
      if (qName.equalsIgnoreCase("HEIGHT")) {
        oNewPlayer.nHeight = Integer.parseInt(sDataField);
      }
//      if (qName.equalsIgnoreCase("WEIGHT")) {
      if (qName.equalsIgnoreCase("QUANTITY")) {
        oNewPlayer.nWeight = Integer.parseInt(sDataField);
      }
//      if (qName.equalsIgnoreCase("HAIR_COLOR")) {
//        oNewPlayer. = sDataField;
//      }
//      if (qName.equalsIgnoreCase("EYE_COLOR")) {
//        oNewPlayer. = sDataField;
//      }
      if (qName.equalsIgnoreCase("SEX")) {
        oNewPlayer.nSexIndex = (sDataField.equalsIgnoreCase("male") ? 0 : 1);
      }
      if (qName.equalsIgnoreCase("HANDEDNESS")) {
        for (int i = 0; i < oParent.handnessTable.length; i++) {
          if (oParent.handnessTable[i].equalsIgnoreCase(sDataField)) {
            oNewPlayer.nHandIndex = i;
          }
        }
      }
      if (qName.equalsIgnoreCase("AGE")) {
        oNewPlayer.nAge = Integer.parseInt(sDataField);
      }
      if (qName.equalsIgnoreCase("LEVEL")) {
        String sLevelField = sDataField;
        sLevelParams = sLevelField.split("/");
      }
    } // end bio information

    if (qName.equalsIgnoreCase("ATTRIBUTES")) {
      bATTRIBUTES = false;
    }
    if (qName.equalsIgnoreCase("ABILITY_SCORE")) {
      bABILITY_SCORE = false;
    }
    if (qName.equalsIgnoreCase("SCORE")) {
      bSCORE = false;

    }
    if (bATTRIBUTES && bABILITY_SCORE) {
      // attribute name like strength, charisma/etc
      //     oPlayer.aAbilities; // 0=STR,1=DEX,2=CON,3=INT,4=WIS,5=CHA,6=COM
      if (!bSCORE && qName.equalsIgnoreCase("FULL")) {
        if (sDataField.equalsIgnoreCase("Strength")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(0);
        }
        else
        if (sDataField.equalsIgnoreCase("Dexterity")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(1);
        }
        else
        if (sDataField.equalsIgnoreCase("Constitution")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(2);
        }
        else
        if (sDataField.equalsIgnoreCase("Intelligence")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(3);
        }
        else
        if (sDataField.equalsIgnoreCase("Wisdom")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(4);
        }
        else
        if (sDataField.equalsIgnoreCase("Charisma")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(5);
        }
        else
        if (sDataField.equalsIgnoreCase("Comeliness")) {
          oA = (Abilities) oNewPlayer.aAbilities.get(6);
        }
        else {
          if (sDataField.equalsIgnoreCase("Honor")) {
            bHONOR = true;
          }
          oA = null;
        }
      }
      if (bSCORE && qName.equalsIgnoreCase("FULL") && oA != null) {
        oA.jAdjScore.setValue(new Integer(Integer.parseInt(sDataField)));
      }
      else
      if (bSCORE && bHONOR && qName.equalsIgnoreCase("FULL")) {
        oNewPlayer.jHonor.setValue(new Integer(Integer.parseInt(sDataField)));
        bHONOR = false;
      }
      if (bSCORE && qName.equalsIgnoreCase("PERCENTILE") && oA != null) {
        oA.jAdjPercent.setValue(new Integer(Integer.parseInt(sDataField)));
      }
    }

    if (qName.equalsIgnoreCase("MOVEMENT_RATES")) {
      bMOVEMENT_RATES = false;
    }
    if (bMOVEMENT_RATES) {
      if (qName.equalsIgnoreCase("CATEGORY")) {
        bMOVEMENT_RATES_CATEGORY = false;
      }
      if (bMOVEMENT_RATES_CATEGORY) {
        if (!bMOVEMENT_GET_RATE && qName.equalsIgnoreCase("Name") &&
            sDataField.equalsIgnoreCase("Unencumbered")) {
          bMOVEMENT_GET_RATE = true;
        }
        if (bMOVEMENT_GET_RATE && qName.equalsIgnoreCase("RATE")) {
          oNewPlayer.jMove.setValue(new Integer(Integer.parseInt(sDataField)));
          bMOVEMENT_GET_RATE = false;
        }
      }
    }

    if (qName.equalsIgnoreCase("RACIAL_ATTRIBUTES")) {
      bRACIAL_ATTRIBUTES = false;
    }
    if (bRACIAL_ATTRIBUTES) {
      if ((qName.equalsIgnoreCase("RACIAL_BONUS") ||
          qName.equalsIgnoreCase("RACIAL_TALENT") ||
          qName.equalsIgnoreCase("RACIAL_DOWNSIDE"))
         // dont do anything if "none",from downside
          && !sDataField.matches("(?i)((.*)(none)(.*))?")) {
        oS.sName = oNewPlayer.sRace + ":" + sDataField;
        TableSkills oSS = new TableSkills(oParent);
        oSS = oSS.GetSkillsFromName(oParent, oS.sName);
        if (oSS == null) {
          oSS = new TableSkills(oParent);
          oSS.nSkillID = (oParent.nMaxSkillsID++);
          oSS.sName = oS.sName;
          oSS.sDesc = "Ability created by import.\n"+oS.sName;
          oSS.nSkillRating = 100;
          oSS.nSkillType = oS.nSkillType;
          oParent.lSkills.add(oSS);
        }
        oS.nSkillID = oSS.nSkillID;
        oS.jSkillSpinner.setValue(new Integer(100));
        oNewPlayer.aSkills.add(oS);
      }
    }

    if (qName.equalsIgnoreCase("QUIRKS_AND_FLAWS")) {
      bQUIRKS_AND_FLAWS = false;
    }
    if (bQUIRKS_AND_FLAWS) {
      if (qName.equalsIgnoreCase("FULL")) {
        oQ = new TableQuirks();
        oQ = oQ.GetQuirkFromName(oParent, sDataField);
        if (oQ == null) {
          oQ = new TableQuirks();
          oQ.sName = sDataField;
          oQ.sDesc = "Quirk/Flaw created by import.\n"+oQ.sName;
          oQ.nQuirkID = (oParent.nMaxQuirksID++);
          oParent.lQuirks.add(oQ);
        }
        Quirks oQQ = new Quirks();
        oQQ.sName = oQ.sName;
        oQQ.sDesc = oQ.sDesc;
        oQQ.nQuirkID = oQ.nQuirkID;
        oNewPlayer.aQuirks.add(oQQ);
      }
    }

    if (qName.equalsIgnoreCase("STP_LIST")) {
      bSTP_LIST = false;
    }
    if (bSTP_LIST) {
      if (qName.equalsIgnoreCase("FULL")) { // fullname of skill
        oS.sName = sDataField;
      }
      if (qName.equalsIgnoreCase("RATING")) {
        if (sDataField.equalsIgnoreCase("basic")) { // profs
          oS.jSkillSpinner.setValue(new Integer(100));
        }
        else if (sDataField.startsWith("x")) { // "bonus" skill
          oS.jSkillSpinner.setValue(new Integer(100));
        }
        else {
          if (sDataField.matches("\\d+"))
          oS.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          else
            oS.jSkillSpinner.setValue(new Integer(1));
        }
      }
      if (qName.equalsIgnoreCase("STP")) {
        TableSkills oSS = new TableSkills(oParent);
        oSS = oSS.GetSkillsFromName(oParent, oS.sName);
        if (oSS == null) {
          oSS = new TableSkills(oParent);
          oSS.nSkillID = (oParent.nMaxSkillsID++);
          oSS.sName = oS.sName;
          oSS.sDesc = "Skill created by import.\n"+oS.sName;
          oSS.nSkillRating = 0;
          oSS.nSkillType = oS.nSkillType;
          oParent.lSkills.add(oSS);
        }

        oS.nSkillID = oSS.nSkillID;
        oS.sDesc = oSS.sDesc;
        oNewPlayer.aSkills.add(oS);
      }
    }

    if (qName.equalsIgnoreCase("INVENTORY")) {
      bINVENTORY = false;
    }
//    if (qName.equalsIgnoreCase("COINS_AND_MONEY")) {
//     bCOINS_AND_MONEY = false;
//    }
//    if (qName.equalsIgnoreCase("COIN")) {
//      Coins oCoin = oNewPlayer.getCoinByABBR(oC.sName);
//      oCoin.jMod.setValue(new Integer(Integer.parseInt(oC.jMod.getValue().toString())));
//      bCOIN = false;
//    }
    if (bINVENTORY) {
      if (qName.equalsIgnoreCase("ITEM")) {
        bITEM = false;
      }
      if (bITEM) {
        // coins abbreviation name
        if (sInventoryCatagory.matches(sCoinMatchRegX) &&
            qName.equalsIgnoreCase("ABBR")) {
          oC.sName = sDataField;
        }
        // amount of coins/items
        if (qName.equalsIgnoreCase("QUANTITY")) {
          if (sInventoryCatagory.matches(sCoinMatchRegX)) { // coins
            int nCount = Integer.parseInt(sDataField);
            if (nCount < 0) nCount = 0;
            oC.jMod.setValue(new Integer(nCount));
          } else { // items
            oG.nCount = Integer.parseInt(sDataField);
            oG.jMod.setValue(new Integer(Integer.parseInt(sDataField)));
          }
        }
        if (!sInventoryCatagory.matches(sCoinMatchRegX)) {
          if (qName.equalsIgnoreCase("FULL")) {
            sDataField = sDataField.replaceAll("\\s+$", ""); // strip whitespace
            oG.sName = sInventoryCatagory + ":" + sDataField;
            oG.sDesc = "Item created by import.\n" + oG.sName;
            oG.sLoc = "Item created by import.";
          }
          if (qName.equalsIgnoreCase("WEIGHT")) {
            oG.dWeight = Double.parseDouble(sDataField);
          }
        }
        if (qName.equalsIgnoreCase("CATEGORY")) {
          sInventoryCatagory = ""; // clear this out each catagory.
        }
      }
        if (qName.equalsIgnoreCase("ITEM")) {
          if (sInventoryCatagory.matches(sCoinMatchRegX)) { // coins
            Coins oCoin = oNewPlayer.getCoinByABBR(oC.sName);
            oCoin.jMod.setValue(new Integer(Integer.parseInt(oC.jMod.getValue().toString())));
          } else { // otherwise its items
            TableGear oGG = new TableGear();
            oGG = oGG.GetGearFromName(oParent, oG.sName);
            oG.sItemType = oG.ITEM_TYPE_GEAR;
            if (oGG == null) {
              oGG = new TableGear();
              oGG.dWeight = (oG.dWeight);
              oGG.nGearID = (oParent.nMaxGearID++);
              oGG.sDesc = oG.sDesc;
              oGG.sName = oG.sName;
              oGG.sItemType = oG.sItemType;
              oParent.lGear.add(oGG);
            }

            oG.sLoc = ""; // not used in HMTK
            oG.sDesc = oGG.sDesc;
            oG.nGearID = oGG.nGearID;
            oG.dWeight = oGG.dWeight;
            oNewPlayer.aGear.add(oG);
          }
        }
    } // end binventory

    if (qName.equalsIgnoreCase("HIT_POINTS")) {
      oNewPlayer.jHealthMax.setValue(new Integer(Integer.parseInt(sDataField)));
      oNewPlayer.jHealthLabel.setText(sDataField);
    }

    if (qName.equalsIgnoreCase("SKILL_LIST")) {
      bSKILL_LIST = false;
    }
    if (qName.equalsIgnoreCase("SKILL_RATING")) {
      bSKILL_RATING = false;
    }
    if (bSKILL_LIST) {
      if (bSKILL_RATING) {
        if (qName.equalsIgnoreCase("ENCUMBRANCE")) {
          sEncumType = sDataField;
        }
        else {
          Skills oT = null;
          // we only want "base" thief skills
          if (qName.equalsIgnoreCase("PICK_POCKETS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Pick Pockets";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("OPEN_LOCKS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Open Locks";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("FIND_TRAPS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Find Traps";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("REMOVE_TRAPS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Remove Traps";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("MOVE_SILENTLY") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Move Silently";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("HIDE_IN_SHADOWS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Hide In Shadows";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("DETECT_NOISE") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Detect Noise";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("CLIMB_WALLS") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Climb Walls";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (qName.equalsIgnoreCase("READ_LANGUAGES") &&
              sEncumType.equalsIgnoreCase("BASE")) {
            oT = new Skills();
            oT.sName = "Read Languages";
            oT.nSkillType = 0;
            oT.jSkillSpinner.setValue(new Integer(Integer.parseInt(sDataField)));
          }
          if (oT != null) { // got a thief skill
            TableSkills oSS = new TableSkills(oParent);
            oSS = oSS.GetSkillsFromName(oParent, oT.sName);
            if (oSS == null) {
              oSS = new TableSkills(oParent);
              oSS.nSkillID = (oParent.nMaxSkillsID++);
              oSS.sName = oT.sName;
              oSS.sDesc = "Thief Skill created by import.";
              oSS.nSkillRating = 0;
              oSS.nSkillType = oT.nSkillType;
              oParent.lSkills.add(oSS);
            }

            oT.nSkillType = oSS.nSkillType; // if skill existed and is diff
            oT.nSkillID = oSS.nSkillID; // ditto
            oT.sDesc = oSS.sDesc; // ditto
            oNewPlayer.aSkills.add(oT);
          }
        }
      }
    } // end bSKILL_LIST

    if (qName.equalsIgnoreCase("EXPERIENCE_POINTS")) {
      bEXPERIENCE_POINTS = false;
    }
    if (bEXPERIENCE_POINTS) {
      if (qName.equalsIgnoreCase("CLASS_NAME")) {
        TableClass oNewTClass = null;
//             Class oNewClass = new Class(oParent,oNewPlayer);
        for (int i = 0; i < oParent.lClass.size(); i++) {
          TableClass oC = (TableClass) oParent.lClass.get(i);
          if (oC.sName.equalsIgnoreCase(sDataField)) {
            oNewTClass = oC;
          }
        }
        if (oNewTClass == null) { // new class, prompt for new fields?
          oNewTClass = new TableClass();
          oNewTClass.sName = sDataField;
          oNewTClass.sDesc = "Imported Class from HMTK\n"+sDataField;
          oNewTClass.nFightAs = 0;
          oNewTClass.nSaveAs = 0;
          oParent.nMaxClassID++;
          oNewTClass.nClassID = oParent.nMaxClassID;
          oParent.lClass.add(oNewTClass);
          oParent.ShowDone(oParent.fPlayerGroupFrame,
                           "A new class has been created from this imported character.\n"+
                           "Set the proper FightAs and SaveAs information and save the class.\n");
          // prompt to verify new class
          TableClass.DoNewClass(oParent.fPlayerGroupFrame,
                                oParent,
                                null,oNewTClass);
        }
        // now that we know the values, set them
        oClass.nClassID = oNewTClass.nClassID;
        oClass.sName = oNewTClass.sName;
        oClass.nClassindex = oParent.lClass.indexOf(oNewTClass);
        oClass.nSaveAs = oNewTClass.nSaveAs;
        oClass.nFightAs = oNewTClass.nFightAs;
        oClass.jEXP.setValue(new Integer(0));

        oNewPlayer.aClass.add(oClass);
//           oClass.jLevel.setValue(new Integer(1)); // so saves are set properly

        // set the level to what was mentioned in bio section.
        // this will force saves to be set properly also
        oClass.jLevel.setValue(
            new Integer(Integer.parseInt(
            sLevelParams[ (oNewPlayer.aClass.size() - 1)])));
      }
      if (qName.equalsIgnoreCase("VALUE")) { // EXP in this class
        if (sDataField.matches("^\\d+$"))
        oClass.jEXP.setValue(new Integer(Integer.parseInt(sDataField)));
      }
    } // end experience_points

    if (qName.equalsIgnoreCase("WEAPON_GROUP")) {
      bWEAPON_GROUP = false;
    }
    if (bWEAPON_GROUP && qName.equalsIgnoreCase("WEAPON")) {
      bWEAPON = false;

      oG.sWeaponGroup = sWeaponGroup; // melee or missle
      oG.sItemType = oG.ITEM_TYPE_WEAPON; // gear, weapon or container

      // if weapon doesnt have tiny damage just ignore it for now
      if (oG.jDamage[oG.DAMAGE_SIZE_TINY].getText().length() > 2) {
        TableGear oGG = new TableGear();
        oGG = oGG.GetGearFromName(oParent, "Weapons:"+oG.sName);
        if (oGG == null) {
          oGG = new TableGear();
          oGG.dWeight = (oG.dWeight);
          oGG.nGearID = (oParent.nMaxGearID++);
          oGG.sName = "Weapons:" + oG.sName;
          oGG.sDesc = "Weapon created by import.\n" + oGG.sName;
          oGG.sWeaponGroup = oG.sWeaponGroup;
          oGG.sWeaponType = oG.sWeaponType;
          oGG.sItemType = oG.sItemType;

          oGG.nSpeedFactor = oG.nSpeedFactor;

          oGG.jDamage[oGG.DAMAGE_SIZE_TINY].setText(oG.jDamage[oG.
              DAMAGE_SIZE_TINY].getText());
          oGG.jDamage[oGG.DAMAGE_SIZE_SMALL].setText(oG.jDamage[oG.
              DAMAGE_SIZE_SMALL].getText());
          oGG.jDamage[oGG.DAMAGE_SIZE_MEDIUM].setText(oG.jDamage[oG.
              DAMAGE_SIZE_MEDIUM].getText());
          oGG.jDamage[oGG.DAMAGE_SIZE_LARGE].setText(oG.jDamage[oG.
              DAMAGE_SIZE_LARGE].getText());
          oGG.jDamage[oGG.DAMAGE_SIZE_HUGE].setText(oG.jDamage[oG.
              DAMAGE_SIZE_HUGE].getText());
          oGG.jDamage[oGG.DAMAGE_SIZE_GIANT].setText(oG.jDamage[oG.
              DAMAGE_SIZE_GIANT].getText());

          oParent.lGear.add(oGG);
        }

/*
         // commented cause this is prof, not actual weapon... go figure
         // the actual weapon will be an item without specs!
         // we just want this stuff to import the weapon specs.
        oG.sLoc = ""; // not used in HMTK
        oG.sName = oGG.sName;
        oG.sDesc = oGG.sDesc;
        oG.nGearID = oGG.nGearID;
        oG.dWeight = oGG.dWeight;
        oNewPlayer.aGear.add(oG);
     */
      }
    }
    if (bWEAPON_GROUP && bWEAPON) {
      if (qName.equalsIgnoreCase("FULL")) {
        oG.sName = sDataField.trim();
      }
      if (qName.equalsIgnoreCase("TO_HIT")) {
        if (sDataField.matches("^(\\d+)$"))
        oG.nToHit = Integer.parseInt(sDataField);
      }
      if (qName.equalsIgnoreCase("TO_DAMAGE")) {
        if (sDataField.matches("^(\\d+)$"))
        oG.nToDamage = Integer.parseInt(sDataField);
      }
      if (qName.equalsIgnoreCase("SPEED_FACTOR")) {
        oG.nSpeedFactor = Integer.parseInt(sDataField);
      }
      if (qName.equalsIgnoreCase("TYPE")) {
        oG.sWeaponType = sDataField.trim();
      }
      if (qName.equalsIgnoreCase("DAMAGE")) {
        if (sDamageSize.equalsIgnoreCase("T"))
          oG.jDamage[oG.DAMAGE_SIZE_TINY].setText(sDataField.trim());
        if (sDamageSize.equalsIgnoreCase("S"))
          oG.jDamage[oG.DAMAGE_SIZE_SMALL].setText(sDataField.trim());
        if (sDamageSize.equalsIgnoreCase("M"))
          oG.jDamage[oG.DAMAGE_SIZE_MEDIUM].setText(sDataField.trim());
        if (sDamageSize.equalsIgnoreCase("L"))
          oG.jDamage[oG.DAMAGE_SIZE_LARGE].setText(sDataField.trim());
        if (sDamageSize.equalsIgnoreCase("H"))
          oG.jDamage[oG.DAMAGE_SIZE_HUGE].setText(sDataField.trim());
        if (sDamageSize.equalsIgnoreCase("G"))
          oG.jDamage[oG.DAMAGE_SIZE_GIANT].setText(sDataField.trim());
      }

    }


    // done with all that

    // done with this field so clear values
    sDataField = "";

  } // end endElement

  public void startDocument() throws SAXException {
//oParent.jGMTextArea.append("start document: ");
    oNewPlayer = new TablePlayer(oParent);
    oC = new Coins(oParent,oNewPlayer);
    oNewPlayer.SetupBasicAbilities(oParent, oNewPlayer);
    TablePlayer.SetupBasicSaves(oParent, oNewPlayer);
    TablePlayer.SetupBasicCoins(oParent, oNewPlayer);
  }

  public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {

    sDataField = ""; // clear out this for next element.

    if (qName.equalsIgnoreCase("HACKMASTER_CHARACTER")) {
      bHACKMASTER_CHARACTER_FOUND = true;
      bHACKMASTER_CHARACTER = true;
    }

    if (qName.equalsIgnoreCase("BIOGRAPHICAL_INFORMATION")) {
      bBIOGRAPHICAL_INFORMATION = true;
    }

    if (qName.equalsIgnoreCase("ATTRIBUTES")) {
      bATTRIBUTES = true;
    }
    if (qName.equalsIgnoreCase("ABILITY_SCORE")) {
      bABILITY_SCORE = true;
    }
    if (bABILITY_SCORE && qName.equalsIgnoreCase("SCORE")) {
      bSCORE = true;
    }

    if (qName.equalsIgnoreCase("EXPERIENCE_POINTS")) {
      bEXPERIENCE_POINTS = true;
    }

    if (qName.equalsIgnoreCase("EXPERIENCE")) {
      oClass = new Class(oParent, oNewPlayer);
    }

    if (qName.equalsIgnoreCase("SKILL_LIST")) {
      bSKILL_LIST = true;
    }
    if (bSKILL_LIST && qName.equalsIgnoreCase("SKILL_RATING")) {
      bSKILL_RATING = true;
    }

    //WEAPON_DATA
    //WEAPON_GROUP = (melee/missle)
    if (qName.equalsIgnoreCase("WEAPON_GROUP")) {
      bWEAPON_GROUP = true;
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("TYPE"))
          sWeaponGroup = attributes.getValue(i);
      }

    }
    //WEAPON
    if (bWEAPON_GROUP && qName.equalsIgnoreCase("WEAPON")) {
      bWEAPON = true;
      oG = new Gear();
    }

    //NAME
    //FULL
    //ADJUSTMENTS
    //TO_HIT
    //TO_DAMAGE
    //SPEED_FACTOR
    //TYPE
    // (P,C,H)
    //DAMAGE_DATA

    //DAMAGE SIZE = T
    if (bWEAPON_GROUP && bWEAPON &&
        qName.equalsIgnoreCase("DAMAGE")) {
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("SIZE"))
          sDamageSize = attributes.getValue(i);
      }
    }

    //DAMAGE SIZE = S
    //DAMAGE SIZE = M
    //DAMAGE SIZE = L
    //DAMAGE SIZE = H

    if (qName.equalsIgnoreCase("MOVEMENT_RATES")) {
      bMOVEMENT_RATES = true;
    }
    if (bMOVEMENT_RATES && qName.equalsIgnoreCase("CATEGORY")) {
      bMOVEMENT_RATES_CATEGORY = true;
    }

    if (qName.equalsIgnoreCase("RACIAL_ATTRIBUTES")) {
      bRACIAL_ATTRIBUTES = true;
    }

    if (bRACIAL_ATTRIBUTES && qName.equalsIgnoreCase("RACIAL_BONUS")) {
      oS = new Skills();
      oS.nSkillType = 3; // racial ability
    }
    if (bRACIAL_ATTRIBUTES && qName.equalsIgnoreCase("RACIAL_TALENT")) {
      oS = new Skills();
      oS.nSkillType = 3; // racial ability
    }
    if (bRACIAL_ATTRIBUTES && qName.equalsIgnoreCase("RACIAL_DOWNSIDE")) {
      oS = new Skills();
      oS.nSkillType = 3; // racial ability
    }

    //SKILL_LIST TYPE=""
    //SKILL_RATING

    if (qName.equalsIgnoreCase("QUIRKS_AND_FLAWS")) {
      bQUIRKS_AND_FLAWS = true;
    }

    if (qName.equalsIgnoreCase("STP_LIST")) {
      bSTP_LIST = true;
    }
    if (qName.equalsIgnoreCase("STP")) {
      oS = new Skills();
      String sType = attributes.getValue(0);
      for (int i = 0; i < oParent.gmSkillTypeTable.length; i++) {
        if (sType.equalsIgnoreCase(oParent.gmSkillTypeTable[i])) {
          oS.nSkillType = i;
        }
      }
    }

    if (qName.equalsIgnoreCase("INVENTORY")) {
      bINVENTORY = true;
    }
    if (bINVENTORY && qName.equalsIgnoreCase("CATEGORY")) {

      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("TYPE"))
          sInventoryCatagory = attributes.getValue(i);
      }

    }
    if (bINVENTORY && qName.equalsIgnoreCase("ITEM")) {
      bITEM = true;
      oG = new Gear();
    }
    if (qName.equalsIgnoreCase("COIN") && !bFoundCOIN) {
      bFoundCOIN = true;
      oParent.ShowDone(oParent.fPlayerGroupFrame,
                       "This is an old and invalid XML character format.\n"+
                       "Please save with HMTK v"+sValidVersion+".\n"+
                       "The imported character will have invalid fields.");
    }

    //<INVENTORY>
    //<COINS_AND_MONEY>
    //<COIN>
    //<NAME>
    //<FULL>
    //<ABBR>
    //<QUANTITY>
    //</INVENTORY>

//<FAMILY_INFORMATION>
//   <FATHER>
//   <DESCRIPTION>
//   <QUALITY>
//   <MOTHER>
//   <DESCRIPTION>
//   <QUALITY>
//   <STEP_PARENT>
//   <BIRTH_ORDER>
//   <SIBLING NUMBER="1">
//   <INFORMATION>
//</FAMILY_INFORMATION>

//</HACKMASTER_CHARACTER>

  } // end startElement

// this is a kludge to remove []'s from HTML xml files and replace with ()s
// cause the internal characters() bit parses incorrectly with it.
  static void kludgeHMTKXMLFormat(String sFileName, String sExt) {
    try {
      String sLine;
      FileOutputStream oOut = new FileOutputStream(sFileName + sExt);

      FileReader fFile = new FileReader(sFileName);
      BufferedReader fBuff = new BufferedReader(fFile);

      Writer wout = new OutputStreamWriter(oOut, "UTF8");

      while (null != (sLine = fBuff.readLine())) {
        sLine = sLine.replaceAll("\\]", ")");
        sLine = sLine.replaceAll("\\[", "(");
        wout.write(sLine);
      }
      wout.flush();
      oOut.close();
      fBuff.close();
      fFile.close();
    }
    catch (java.io.IOException IOE) {
      oParent.gmLog(true, false, "IOException on file open of " + sFileName);
    }

  }

  static void AskWhatFileToLoad(HackSackFrame oParent, Component oThis) {
    JFileChooser jFileChooser1 = new JFileChooser();
    jFileChooser1.setApproveButtonText("Import");
    jFileChooser1.setDialogTitle("Import HMTK XML Character Sheet");
    if (System.getProperty("lastimport.dir") == null) {
      jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }
    else {
      jFileChooser1.setCurrentDirectory(new File(System.getProperty(
          "lastimport.dir")));

    }
    int returnVal = jFileChooser1.showOpenDialog(oThis);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
      String sFile = jFileChooser1.getSelectedFile().getName();

      if (!jFileChooser1.getSelectedFile().exists()) {
        oParent.gmLog(true, false, "File " + sFile + " does not exist.\n");
      }
      else {
//        SaxParserXMLCharacter.kludgeHMTKXMLFormat(sFileName, ".impt");
        if (SaxParserXMLCharacter.loadUp(oParent, sFileName)) {
          if (!oParent.fPlayerGroupFrame.isVisible())
            oParent.fPlayerGroupFrame.LoadPartyGroupPane(oParent);
            // if this first character loaded, select it so its displayed
            // in the panel
          if (oParent.fPlayerGroupFrame.jPartyPlayerList.getSelectedIndex() < 0)
            oParent.fPlayerGroupFrame.jPartyPlayerList.setSelectedIndex(0);
            // refresh cause we added someone
            oParent.fBattleSheetFrame.LoadPartyPanel(oParent);

//          oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
          oParent.gmLog(true, false, "Imported " + sFile + " .\n");
          System.setProperty("lastimport.dir",
                             jFileChooser1.getCurrentDirectory().
                             getAbsolutePath());
        }
        else {
          oParent.ShowDone(oThis, "Invalid HMTK XML Character sheet.");
        }
// dont do this, used to when we kludged/re-wrote files
//        FileUtil.deleteFile(sFileName);
      }
    }
    else {
      oParent.gmLog(true, false, "Import cancelled.\n");
    }
  }

}
