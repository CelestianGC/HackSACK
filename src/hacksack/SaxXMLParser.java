package hacksack;

/*
 This is where we load up the Creatures.xml file

 */
import java.io.*;
import javax.xml.parsers.*;
import java.util.*;
import java.util.regex.*;

import java.awt.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SaxXMLParser
    extends DefaultHandler {
  public static HackSackFrame oParent = null;
  TableSavedCreatures oNew;
  TableSavedCreatureCombat oAtk;
  Climate oClimate;
  Terrain oTerrain;
//  TableArmor oA;
  String sNumDice = null;
  String sNumSides = null;
  String sPerDice = null;

  public static void loadUp(HackSackFrame oThis, String sFileName) {
    oParent = oThis;
//    sFileName = "creatures" + File.separatorChar + sFileName;
//    sFileName = "file:\\\\"+ sFileName;

    System.setProperty("javax.xml.parsers.SAXParserFactory",
                       "org.apache.xerces.jaxp.SAXParserFactoryImpl");
    String uri = sFileName;
    try {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setValidating(false);
      parserFactory.setNamespaceAware(false);
      SaxXMLParser SaxXMLParserInstance = new SaxXMLParser();
      SAXParser parser = parserFactory.newSAXParser();
      parser.parse(uri, SaxXMLParserInstance);
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
//    Collections.copy(lWorkList,oParent.lSavedCreatures);
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
    if (qName.equalsIgnoreCase("creature")) {
      // if somehow the nCreatureID > than our max ide bump it up 1
      if (oNew.nCreatureID > oParent.nMaxCreatureID) {
        oParent.nMaxCreatureID = (oNew.nCreatureID+1);
      }
// dont need this anymore
      TableSavedCreatures oOld = oNew.exists(oParent.lSavedCreatures);
      if (oOld != null) {
        oNew.nCreatureID = (oParent.nMaxCreatureID++);
        oParent.DiceOutTextArea.append(oNew.sCreatureName+" had duplicate ID as "+oOld.sCreatureName+
                                       ", changed to "+oParent.nMaxCreatureID+".\n");
      }


/*
      if (oNew.sCreatureName.matches("(?si)^(AD&D:).*")) {
        oNew.sCreatureName = oNew.sCreatureName.replaceAll ("(?si)^(AD&D:)","");
        oNew.sDescription = "AD&D creature.\n"+oNew.sDescription;
      }
      if (oNew.nBASEMorale <= 0) // fix a problem with some of them
        oNew.nBASEMorale = 10;
      if (oNew.nBASEEXP <= 0) // fix a problem with some of them
        oNew.nBASEEXP = 1;
      if (oNew.nBASEAC < -20 ||
          oNew.nBASEAC > 20) // fix a problem with some of them
        oNew.nBASEAC = 10;
*/
/*      // disabled, this was to fix a messed up morale import
        if (oNew.sDescription.matches("(?si)(.*)?(Morale :\\d+[-](\\d+))(.*)?")) {
        int nNewMorale = 0;
        Matcher mMatcher = Pattern.compile("(?si)(.*)?(Morale :\\d+[-](\\d+))(.*)?").matcher(oNew.sDescription);
        if (mMatcher.find(0)) {
//          oParent.DiceOutTextArea.append(mMatcher.group(0)+" (0)\n");
//          oParent.DiceOutTextArea.append(mMatcher.group(1)+" (1)\n");
//          oParent.DiceOutTextArea.append(mMatcher.group(2)+" (2)\n");
//          oParent.DiceOutTextArea.append(mMatcher.group(3).trim()+" (3)\n");
//          oParent.DiceOutTextArea.append(mMatcher.group(4)+" (4)\n");
//          nNewMorale = Integer.parseInt(mMatcher.group(2));
          nNewMorale = Integer.parseInt(mMatcher.group(3).trim());
          oNew.nBASEMorale = nNewMorale;
        }

//        oParent.DiceOutTextArea.append(oNew.sCreatureName+" current morale "+oNew.nBASEMorale+" new "+nNewMorale+"\n");
//        oParent.DiceOutTextArea.append("=======================================\n");
      }
      */
       oParent.lSavedCreatures.add(oNew);
    }
    if (qName.equalsIgnoreCase("attack")) {

      // compat section with old style stuff for a while
      if ((oAtk.sDamageDice==null) || (oAtk.sDamageDice.length() < 1)) {
        String sPerDiceField = "";
       if ((Integer.parseInt(sPerDice)>0) &&
           (Integer.parseInt(sPerDice)!=0))
        sPerDiceField="+"+sPerDice;
        else
        if ((Integer.parseInt(sPerDice)<0))
         sPerDiceField=sPerDice;

       oAtk.sDamageDice = (sNumDice + "d" + sNumSides +
                                sPerDiceField);
      }
      sNumDice = null;
      sNumSides = null;
      sPerDice = null;
      // end compat section

      oNew.lMyAttacks.add(oAtk);
    }
    if (qName.equalsIgnoreCase("Climate")) {
      oNew.aClimate.add(oClimate);
    }
    if (qName.equalsIgnoreCase("Terrain")) {
      oNew.aTerrain.add(oTerrain);
    }

  }

  public void startDocument() throws SAXException {
//oParent.jGMTextArea.append("start document: ");
  }

  public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {

    if (qName.equalsIgnoreCase("MonsterList")) {
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("MaxID")) {
          oParent.nMaxCreatureID = Integer.parseInt(attributes.getValue(i));
        }
      }
    }

    if (qName.equalsIgnoreCase("creature")) {
      oNew = new TableSavedCreatures(oParent, "");
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sCreatureName")) {
          oNew.sCreatureName = oParent.unEscapeChars(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEAC")) {
          oNew.nBASEAC = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHD")) {
          oNew.nBASEHD = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHDMod")) {
          oNew.nBASEHDMod = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEClassIndex")) {
          oNew.nBASEClassIndex = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASENumAtks")) {
          oNew.nBASENumAtks = Integer.parseInt(attributes.getValue(i));
        }
        // left over from previous version of struct
        if (attributes.getQName(i).equalsIgnoreCase("nBASESpecHP")) {
          oNew.sBASESpecHP = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEEXP")) {
          oNew.nBASEEXP = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHonor")) {
          oNew.nBASEHonor = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEFatigueFactor")) {
          oNew.nBASEFatigueFactor = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHonorIndex")) {
          oNew.nBASEHonorIndex = Integer.parseInt(attributes.getValue(i));

        }
        if (attributes.getQName(i).equalsIgnoreCase("sSpecialAttack")) {
          oNew.sSpecialAttack = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("sSpecialDefense")) {
          oNew.sSpecialDefense = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEMorale")) {
          oNew.nBASEMorale = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEMove")) {
          oNew.nBASEMove = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASESizeIndex")) {
          oNew.nBASESizeIndex = Integer.parseInt(attributes.getValue(i));

        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEAlignmentIndex")) {
          oNew.nBASEAlignmentIndex = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHackFactor")) {
          oNew.nBASEHackFactor = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nCreatureID")) {
          oNew.nCreatureID = Integer.parseInt(attributes.getValue(i));

        }
        if (attributes.getQName(i).equalsIgnoreCase("sDescription")) {
          oNew.sDescription = oParent.unEscapeChars(attributes.getValue(i));

        }
        if (attributes.getQName(i).equalsIgnoreCase("nFrequency")) {
          oNew.nFrequency = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nActivityCycle")) {
          oNew.nActivityCycle = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nAppearingIn")) {
          oNew.nAppearingIn = Integer.parseInt(attributes.getValue(i));
        }
      }

    }

/*    if (qName.equalsIgnoreCase("ArmorType")) {
      oA = new TableArmor();
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName")) {
          oA.sName = oParent.unEscapeChars(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nAborb")) {
          oA.nAborb = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nAC")) {
          oA.nAC = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nArmorID")) {
          oA.nArmorID = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nBulkness")) {
          oA.nBulkness = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nHP")) {
          oA.nHP = Integer.parseInt(attributes.getValue(i));

        }
        for (int j = 0; j < oA.nHPAC.length; j++) {
          if (attributes.getQName(i).equalsIgnoreCase("nHPAC" + (j + 1))) {
            oA.nHPAC[j] = Integer.parseInt(attributes.getValue(i));
          }
        }

        if (attributes.getQName(i).equalsIgnoreCase("dWeight")) {
          oA.dWT = Double.parseDouble(attributes.getValue(i));
        }
      }
    }*/

    if (qName.equals("Climate")) {
      oClimate = new Climate("");
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName")) {
          oClimate.sName = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("jActive")) {
          if (attributes.getValue(i).equalsIgnoreCase("TRUE")) {
            oClimate.jActive.setSelected(true);
          }
          else {
            oClimate.jActive.setSelected(false);
          }
        }
      }
    }

    if (qName.equals("Terrain")) {
      oTerrain = new Terrain("");
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName")) {
          oTerrain.sName = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("jActive")) {
          if (attributes.getValue(i).equalsIgnoreCase("TRUE")) {
            oTerrain.jActive.setSelected(true);
          }
          else {
            oTerrain.jActive.setSelected(false);
          }
        }
      }
    }

    if (qName.equalsIgnoreCase("attack")) {
      oAtk = new TableSavedCreatureCombat();
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("nDiceSides")) {
          sNumSides = attributes.getValue(i);
//          oAtk.nDiceSides = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nWeaponType")) {
          oAtk.nWeaponType = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nNumDice")) {
          sNumDice = attributes.getValue(i);
//          oAtk.nNumDice = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nPerDiceMod")) {
          sPerDice = attributes.getValue(i);
//          oAtk.nPerDiceMod = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("sDamageDice")) {
          oAtk.sDamageDice = attributes.getValue(i);
        }
        if (attributes.getQName(i).equalsIgnoreCase("nToHitMod")) {
          oAtk.nToHitMod = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nTotalMod")) {
          oAtk.nTotalMod = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nModCrit")) {
          oAtk.nModCrit = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nModFumble")) {
          oAtk.nModFumble = Integer.parseInt(attributes.getValue(i));
        }
        if (attributes.getQName(i).equalsIgnoreCase("nModPenetration")) {
          oAtk.nModPenetration = Integer.parseInt(attributes.getValue(i));
        }
      }

    }

  }

}
