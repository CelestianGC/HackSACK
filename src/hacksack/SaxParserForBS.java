package hacksack;

import java.io.*;
import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SaxParserForBS extends DefaultHandler {
  public static HackSackFrame oParent = null;
  CreatureCore oNew;
  TableInformation oNewInfo;
  CreatureCombat oAtk;
  //TableArmor oA;

  String sPerDice = null;
  String sNumSides = null;
  String sNumDice = null;

  public static void loadUp(HackSackFrame oThis, String sFileName,boolean bFullPath) {
    oParent = oThis;
    System.setProperty("javax.xml.parsers.SAXParserFactory",
                       "org.apache.xerces.jaxp.SAXParserFactoryImpl");
//    String url = "battlesheets"+File.separatorChar+sFileName;
    String url = (!bFullPath?"file:\\\\\\":"")+sFileName;
//        String url = "file:\\\\\\"+sFileName;
    try {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setValidating(false);
      parserFactory.setNamespaceAware(false);
      SaxParserForBS SaxXMLParserBSInstance = new SaxParserForBS();
      SAXParser parser = parserFactory.newSAXParser();
      parser.parse(url, SaxXMLParserBSInstance);
    }
    catch (IOException ex) {
      oParent.gmLog(true, false, "IOException in SaxXMLParser.loadUP() on file "+sFileName+"\n");
      ex.printStackTrace();
    }
    catch (SAXException ex) {
      oParent.gmLog(true, false, "SAXException in SaxXMLParser.loadUP() on file "+sFileName+"\n");
      ex.printStackTrace();
    }
    catch (ParserConfigurationException ex) {
      oParent.gmLog(true, false,
                    "ParserConfigurationException in SaxXMLParser.loadUP() on file "+sFileName+"\n");
      ex.printStackTrace();
    }
    catch (FactoryConfigurationError ex) {
      oParent.gmLog(true, false,
                    "FactoryConfigurationError in SaxXMLParser.loadUP() on file "+sFileName+"\n");
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
    if (qName.equalsIgnoreCase("BSInfo")) { // Info finished
      oParent.lInformation.add(oNewInfo);
    }
    if (qName.equalsIgnoreCase("BScreature")) { // creature finished, load to mem
//      oNew.oA = oA;
      if (oNew.nACBase == -100)
        oNew.nACBase = oNew.nAC;
      if (oNew.jArmorType.getSelectedIndex()>0)
        oNew.jArmorType.setEnabled(false);
      oParent.lCreatures.add(oNew);
    }
    if (qName.equalsIgnoreCase("attack")) {

      // this is to make sure we are compatable with old style for a bit
      if (oAtk.jDamageDice.getText().length() < 1) {
       oAtk.jDamageDice.setText(sNumDice + "d" + sNumSides +
                                (Integer.parseInt(sPerDice)>0?"+"+sPerDice:sPerDice));

      String sPerDiceField = "";
      if ((Integer.parseInt(sPerDice)>0) &&
          (Integer.parseInt(sPerDice)!=0))
       sPerDiceField="+"+sPerDice;
       else
       if ((Integer.parseInt(sPerDice)<0))
        sPerDiceField=sPerDice;
      oAtk.jDamageDice.setText(sNumDice + "d" + sNumSides +
                               sPerDiceField);
      }
      sNumDice = null;
      sNumSides = null;
      sPerDice = null;
      // end compat section

      oNew.lAttacks.add(oAtk); // attacks for creature done, load to mem
    }

  }

  public void startDocument() throws SAXException {
//oParent.jGMTextArea.append("start document: ");
  }

  public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {

    if (qName.equalsIgnoreCase("BSInfo")) {
      oNewInfo = new TableInformation(oParent);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("jName"))
          oNewInfo.jName.setText(oParent.unEscapeChars(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("jRoomDescPlayer"))
          oNewInfo.jRoomDescPlayer.setText(oParent.unEscapeChars(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("jRoomDescDM"))
          oNewInfo.jRoomDescDM.setText(oParent.unEscapeChars(attributes.getValue(i)));
      }
    }

    if (qName.equalsIgnoreCase("BScreature")) {
      oNew = new CreatureCore(oParent,"");
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sCreatureName"))
          oNew.sCreatureName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sDescription"))
          oNew.sDescription = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nAC"))
          oNew.nAC = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jHitPointLabel"))
          oNew.jHitPointLabel.setText(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("jClassSelect"))
          oNew.jClassSelect.setSelectedIndex(Integer.parseInt(attributes.getValue(i)));
        if (attributes.getQName(i).equalsIgnoreCase("nEXP"))
          oNew.nEXP = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nHonor"))
          oNew.nHonor = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nHitPoints"))
          oNew.nHitPoints = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nLevel"))
          oNew.nLevel = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nToHitRank"))
          oNew.nToHitRank = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("nFatigueFactor"))
          oNew.nFatigueFactor = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nCombatRounds"))
          oNew.nCombatRounds = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("nCreatureID"))
          oNew.nCreatureID = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("nACBase"))
          oNew.nACBase = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("nBASENumAtks"))
          oNew.nBASENumAtks = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nBASEMorale"))
          oNew.nBASEMorale = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nBASESizeIndex"))
          oNew.nBASESizeIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nBASEAlignmentIndex"))
          oNew.nBASEAlignmentIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nBASEHackFactor"))
          oNew.nBASEHackFactor = Integer.parseInt(attributes.getValue(i));
//        if (attributes.getQName(i).equalsIgnoreCase("nArmorDamage"))
//          oNew.nArmorDamage = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("jACMod"))
          oNew.jACMod.setValue(new Integer(attributes.getValue(i)));

        if (attributes.getQName(i).equalsIgnoreCase("sHOBLocation"))
          oNew.sHOBLocation = oParent.unEscapeChars(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("jNotePad"))
          oNew.jNotePad.setText(oParent.unEscapeChars(attributes.getValue(i)));
//        if (attributes.getQName(i).equalsIgnoreCase("lAttacks"))
        if (attributes.getQName(i).equalsIgnoreCase("sSpecialAttack"))
          oNew.sSpecialAttack = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("sSpecialDefense"))
          oNew.sSpecialDefense = oParent.unEscapeChars(attributes.getValue(i));
      }
    }

    // shield worn
    if (qName.equalsIgnoreCase("ShieldType")) {
      oNew.oShieldWorn = new Gear();
      oNew.oShieldWorn.sItemType = oNew.oShieldWorn.ITEM_TYPE_SHIELD;
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oNew.oShieldWorn.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nGearID"))
          oNew.oShieldWorn.nGearID = Integer.parseInt(attributes.getValue(i));


        if (attributes.getQName(i).equalsIgnoreCase("nShieldACBase"))
          oNew.oShieldWorn.nShieldACBase = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nShieldACBaseCurrent"))
          oNew.oShieldWorn.nShieldACBaseCurrent = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nShieldACMagicBonus"))
          oNew.oShieldWorn.nShieldACMagicBonus = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nShieldACMagicBonusCurrent"))
          oNew.oShieldWorn.nShieldACMagicBonusCurrent = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nShieldACMagicHealth"))
          oNew.oShieldWorn.nShieldACMagicHealth = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nShieldACMagicHealthCurrent"))
          oNew.oShieldWorn.nShieldACMagicHealthCurrent = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_1"))
          oNew.oShieldWorn.nShieldHP[oNew.oShieldWorn.SHIELD_1] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_2"))
          oNew.oShieldWorn.nShieldHP[oNew.oShieldWorn.SHIELD_2] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_3"))
          oNew.oShieldWorn.nShieldHP[oNew.oShieldWorn.SHIELD_3] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_4"))
          oNew.oShieldWorn.nShieldHP[oNew.oShieldWorn.SHIELD_4] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_5"))
          oNew.oShieldWorn.nShieldHP[oNew.oShieldWorn.SHIELD_5] = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_1_CURRENT"))
          oNew.oShieldWorn.nShieldHPCurrent[oNew.oShieldWorn.SHIELD_1] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_2_CURRENT"))
          oNew.oShieldWorn.nShieldHPCurrent[oNew.oShieldWorn.SHIELD_2] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_3_CURRENT"))
          oNew.oShieldWorn.nShieldHPCurrent[oNew.oShieldWorn.SHIELD_3] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_4_CURRENT"))
          oNew.oShieldWorn.nShieldHPCurrent[oNew.oShieldWorn.SHIELD_4] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("SHIELD_5_CURRENT"))
          oNew.oShieldWorn.nShieldHPCurrent[oNew.oShieldWorn.SHIELD_5] = Integer.parseInt(attributes.getValue(i));

      }
    }

    // armor this creature wears
    if (qName.equalsIgnoreCase("ArmorType")) {
      oNew.oArmorWorn = new Gear();
      oNew.oArmorWorn.sItemType = oNew.oArmorWorn.ITEM_TYPE_ARMOR;
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("sName"))
          oNew.oArmorWorn.sName = oParent.unEscapeChars(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nGearID"))
          oNew.oArmorWorn.nGearID = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACAbsorb"))
          oNew.oArmorWorn.nACAbsorb = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACBase"))
          oNew.oArmorWorn.nACBase = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACBaseCurrent"))
          oNew.oArmorWorn.nACBaseCurrent = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACBulkIndex"))
          oNew.oArmorWorn.nACBulkIndex = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACMagicBonus"))
          oNew.oArmorWorn.nACMagicBonus = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACMagicBonusCurrent"))
          oNew.oArmorWorn.nACMagicBonusCurrent = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACMagicBonusHealth"))
          oNew.oArmorWorn.nACMagicBonusHealth = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("nACMagicBonusHealthCurrent"))
          oNew.oArmorWorn.nACMagicBonusHealthCurrent = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("AC_0"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_0] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_1"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_1] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_2"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_2] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_3"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_3] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_4"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_4] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_5"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_5] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_6"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_6] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_7"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_7] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_8"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_8] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_9"))
          oNew.oArmorWorn.nACHP[oNew.oArmorWorn.AC_9] = Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("AC_0_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_0] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_1_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_1] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_2_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_2] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_3_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_3] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_4_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_4] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_5_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_5] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_6_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_6] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_7_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_7] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_8_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_8] = Integer.parseInt(attributes.getValue(i));
        if (attributes.getQName(i).equalsIgnoreCase("AC_9_CURRENT"))
          oNew.oArmorWorn.nACHPCurrent[oNew.oArmorWorn.AC_9] = Integer.parseInt(attributes.getValue(i));
      }
    }// end armor worn

    if (qName.equalsIgnoreCase("attack")) {
      oAtk = new CreatureCombat(oParent,oNew);
      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i).equalsIgnoreCase("jWeaponType"))
          oAtk.jWeaponType.setSelectedIndex(Integer.parseInt(attributes.getValue(i)));

        if (attributes.getQName(i).equalsIgnoreCase("jToHitMod"))
          oAtk.jToHitMod.getModel().setValue(new Integer(Integer.parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jTotalMod"))
          oAtk.jTotalMod.getModel().setValue(new Integer(Integer.parseInt(attributes.getValue(i))));

        if (attributes.getQName(i).equalsIgnoreCase("jPerDiceMod"))
          sPerDice = attributes.getValue(i);
        if (attributes.getQName(i).equalsIgnoreCase("jNumDice"))
          sNumDice = attributes.getValue(i);
        if (attributes.getQName(i).equalsIgnoreCase("jDiceSides"))
          sNumSides = attributes.getValue(i);
        if (attributes.getQName(i).equalsIgnoreCase("jDamageDice"))
          oAtk.jDamageDice.setText(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("nWeaponSelectedID"))
          oAtk.nWeaponSelectedID =  Integer.parseInt(attributes.getValue(i));

        if (attributes.getQName(i).equalsIgnoreCase("jModPenetration"))
          oAtk.jModPenetration.getModel().setValue(new Integer(Integer.parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jModFumble"))
          oAtk.jModFumble.getModel().setValue(new Integer(Integer.parseInt(attributes.getValue(i))));
        if (attributes.getQName(i).equalsIgnoreCase("jModCrit"))
          oAtk.jModCrit.getModel().setValue(new Integer(Integer.parseInt(attributes.getValue(i))));
      }
    }


  }


}
