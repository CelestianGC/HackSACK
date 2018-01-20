package hacksack;

import java.util.regex.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class ParseMagicItemTables {
//  static HackSackFrame oParent = null;

  public ParseMagicItemTables() {
  }

  public ParseMagicItemTables(HackSackFrame oThis) {
    this();
//    this.oParent = oThis;
  }


  // parse out {A2} to a roll on table A2/etc...
  public static String ParseMagicItemTable(HackSackFrame oParent, String sParseThis) {
    int nRoll = 0;

    String sMagicTableMatcher = "(?si)\\{(.*?)\\}";
    Matcher mMagicTableMatcher = Pattern.compile(sMagicTableMatcher).matcher(
        sParseThis);
    while (mMagicTableMatcher.find()) {
      TableMagicItem oThisRecord = null;
      String sSimpleItem = null;
      String sSpecialInfo = "";
      String sTable = mMagicTableMatcher.group(1).toUpperCase();
      String sWork = "";

      if (sTable.equalsIgnoreCase("A18")) {
        TableBlock oRecord = null;
        oThisRecord = new TableMagicItem( -1, "-Temp-");
        nRoll = oParent.MyRandom(20);
        String sMagicAdj = null;

        if (nRoll == 20) {
          // get info from right place for this special roll
        oThisRecord = oParent.GetMagiItemFromTable(oParent.MyRandom(20), "A20");
        }
        else {
          sSimpleItem = TableBlock.GetTableRecord(oParent,sTable, nRoll, true);
          oRecord = oParent.GetMagicTableRecord("A19", 20, false);
          sMagicAdj = oRecord.sText;
          int nMagicAdj = Integer.parseInt(sMagicAdj);
          if (nMagicAdj > 0)
            sMagicAdj = "+" + nMagicAdj;
                oThisRecord.sName = sSimpleItem + " " + sMagicAdj;
                oThisRecord.nEXP = Integer.parseInt(oRecord.sExtra);
                oThisRecord.nGold = 0;
                oThisRecord.sPage = "NA";
        }
      }
      else if (sTable.equalsIgnoreCase("A21")) { // weapon rolls
        TableBlock oRecord = null;
        oThisRecord = new TableMagicItem( -1, "-Temp-");
        nRoll = oParent.MyRandom(100);
        String sMagicAdj = null;

        if (nRoll >= 99) {
          oThisRecord.sName = "SPECIAL, ROLL TABLE A23";
        }
        else {
          sSimpleItem = TableBlock.GetTableRecord(oParent,sTable, nRoll, true);
          sSimpleItem = oParent.DiceParse(sSimpleItem);

          if (sSimpleItem.matches("(?i).*?sword.*?"))
            oRecord = oParent.GetMagicTableRecord("A22_A", 20, false);
          else
            oRecord = oParent.GetMagicTableRecord("A22_B", 20, false);
          sMagicAdj = oRecord.sText;

//treasureLog(false,false,"MAgicreturn:"+sMagicAdj+"\n");
          int nMagicAdj = Integer.parseInt(sMagicAdj);
          if (nMagicAdj > 0)
            sMagicAdj = "+" + nMagicAdj;
          oThisRecord.sName = sSimpleItem + " " + sMagicAdj;
          if (!sSimpleItem.matches("(?i).*?arrow.*?") &&
              !sSimpleItem.matches("(?i).*?bolt.*?") &&
              !sSimpleItem.matches("(?i).*?bullet.*?"))
            oThisRecord.nEXP = Integer.parseInt(oRecord.sExtra);
          else
            oThisRecord.nEXP = 0;

          oThisRecord.nGold = 0;
          oThisRecord.sPage = "NA";
        }
      }
      else { // default route for most tables V
        if (sTable.equalsIgnoreCase("A8") ||
            sTable.equalsIgnoreCase("A5") )
          nRoll = oParent.MyRandom(20);
          else
          nRoll = oParent.MyRandom(100);

        oThisRecord = oParent.GetMagiItemFromTable(nRoll, sTable);
      }

      if (oThisRecord == null) { // no table record found
        sWork = "(NONE)-" + sTable + "\n";
      }
      else { // found record, lets rock

        // gotta deal with scrolls here...
        if (sTable.equalsIgnoreCase("A3") &&
            oThisRecord.sName.matches("^\\d+.*?")) { // scrolls with spell types
          nRoll = oParent.MyRandom(10);
          boolean bIsMageSpell = nRoll < 6;
          String sSpellType = bIsMageSpell ? "magic-user" : "cleric";
          int nSpells = 0;
          int nTotalSpellLevels = 0;
          String sLevel = "";
          String sText = oThisRecord.sName;
          String[] sFields = sText.split(",");
          nSpells = Integer.parseInt(sFields[0]);
          sLevel = sFields[1];
          sWork = "" + sSpellType + " scroll with " + nSpells +
              " spells Level:[";
          for (int i = 0; i < nSpells; i++) {
            int nSpellLevel = 0;
            if (sFields.length == 3 && !bIsMageSpell)
              nSpellLevel = Integer.parseInt(oParent.DiceParse(sFields[2]));
            else
              nSpellLevel = Integer.parseInt(oParent.DiceParse(sLevel));
            nTotalSpellLevels += nSpellLevel;
            sWork += Integer.toString(nSpellLevel);
            if (i + 1 < nSpells) // got more, add comma
              sWork += ",";
          }
          sWork += "] ep:" + (nTotalSpellLevels * 100) + " gp:" +
              (nTotalSpellLevels * 100 * 3 + "\n");
        }
        else if (sTable.equalsIgnoreCase("A3")) { // scroll
          switch (nRoll) { // special scrolls
            case 50:
            case 51:
            case 52:
            case 53:  // elemental protection scrolls
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B16", 100, false) + ")";
              break;
          }
          sWork = "" + oThisRecord.sName + sSpecialInfo + " ep:" +
              oThisRecord.nEXP +
              " gp:" + oThisRecord.nGold + " page:" + oThisRecord.sPage +
              "\n";
        } // done with scrolls
        else { // special info stuff from here down

          if (sTable.equalsIgnoreCase("A2")) { // special info for potions
              switch(nRoll) {
                case 1: // animal control
                  sSpecialInfo = "("+TableBlock.GetTableRecord(oParent,"B2",20,false) +")";
                  break;
                case 12:
                case 13: // dragon potion
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B4",100,false) +")";
                  break;
                case 32: // giant control
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B5",20,false) +")";
                  break;
                case 33:
                case 34: // giant strength
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B6",20,false) +")";
                  break;
                case 42:
                case 43: // human control
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B8",20,false) +")";
                  break;
                case 53: // elemental
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B9",4,false) +")";
                  break;
                case 76:
                case 77: // poly insect
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B10",100,false) +")";
                  break;
                case 79:
                case 80: // poly
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B11",100,false) +")";
                  break;
                case 92:
                case 93: // control undead
                  sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B13",12,false) +")";
                  break;
                default:
                  break;
              }
          } else
          if (sTable.equalsIgnoreCase("A4")) {
            switch(nRoll) {
              case 11:
              case 12: // clumsiness
                sSpecialInfo = " (acts like:"+TableBlock.GetTableRecord(oParent,"B18",100,false) +")";
                break;
              case 24: // ring of elemental command
                sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B9",4,false) +" or GM Choice)";
                break;
              case 48: // ring of many deaths
                sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B19",100,false) +")";
                break;
              case 61:
              case 62: // ring of protection
                sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B20",100,false) +")";
                break;
              case 65: // regeneration
                sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B22",100,false) +")";
                break;
              case 80: // teleken
                sSpecialInfo = " ("+TableBlock.GetTableRecord(oParent,"B24",100,false) +")";
                break;
              case 97: // ring of wizadry
                sSpecialInfo = " (doubles "+TableBlock.GetTableRecord(oParent,"B25",100,false) +")";
                break;
            }
          }
          else // rods
          if (sTable.equalsIgnoreCase("A5")) {
            sSpecialInfo += oParent.DiceParse(" {1d10+40} charges");
          }
          else // staves
          if (sTable.equalsIgnoreCase("A6")) {
            int nPRoll;
            switch (nRoll) {
              case 64:
              case 65:
              case 66:
              case 67:
              case 68: // spear staff
                nPRoll = oParent.MyRandom(20);
                sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B31", nPRoll, true) + ")";
                oThisRecord.nEXP = Integer.parseInt(TableBlock.GetTableRecord(oParent,"B31",nPRoll,true));
                break;
            }
            sSpecialInfo += oParent.DiceParse(" {1d6+19} charges");

          }
          else // wands
          if (sTable.equalsIgnoreCase("A7")) {
            sSpecialInfo += oParent.DiceParse(" {1d10+80} charges");

          }
          else // books
          if (sTable.equalsIgnoreCase("A8")) {
            switch (nRoll) {
              case 11: // manual of golems
                sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B80", 20, false) + ")";
                break;
            }
          }
          else // jewles, jewlery phlactries
          if (sTable.equalsIgnoreCase("A9")) {
            switch (nRoll) {
              case 1:
              case 2:
                sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B34", 100, false) + ")";
              break;
             case 11:
             case 12:
               sSpecialInfo = " (effective cleric level " + TableBlock.GetTableRecord(oParent,"B37", 100, false) + ")";
               break;
             case 13:
             case 14:
               sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B38", 100, false) + ")";
               break;
             case 47: // necklace of missles
               sSpecialInfo = oParent.DiceParse(" ({1d12} globes of " + TableBlock.GetTableRecord(oParent,"B81", 20, false) + ")");
               break;
             case 52:
             case 53:// pearl of power
               sSpecialInfo = oParent.DiceParse(" (recalls spell " + TableBlock.GetTableRecord(oParent,"B83", 20, false) + ")");
               break;
            }
        }
        else // cloaks, capes, robes
        if (sTable.equalsIgnoreCase("A10")) {
          switch (nRoll) {
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B60", 100, false) + ")";
              break;
            case 68: // robe of deep pockets
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B85", 100, false) + ")";
              break;
          }
        }
        else // boots, bracers, gauntlets
        if (sTable.equalsIgnoreCase("A11")) {
          switch (nRoll) {
            case 8:
            case 9:
            case 10: // boots of ernest
              sSpecialInfo = " (sized for " + TableBlock.GetTableRecord(oParent,"B48", 100, false) + ")";
              break;
           case 14:
           case 15: // groin stomping
             sSpecialInfo = " (sized for " + TableBlock.GetTableRecord(oParent,"B49", 100, false) + ")";
             break;
           case 41:
           case 42: // bracers of defense
             sSpecialInfo = " (ac " + TableBlock.GetTableRecord(oParent,"B53", 100, false) + ")";
            break;
            case 47:
            case 48: // bracers of giant strength
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B54", 100, false) + ")";
              break;
          }
        }
        else // girdles
        if (sTable.equalsIgnoreCase("A12")) {
          switch (nRoll) {
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B72", 100, false) + ")";
              break;
          }
        }
        else // bags/bottles
        if (sTable.equalsIgnoreCase("A13")) {
          switch(nRoll) {
            case 3:
            case 4:
            case 5: // baack pack of hefty capacity
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B39", 100, false) + ")";
              break;
            case 17:
            case 18: // bag of endless storage
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B41", 100, false) + ")";
              break;
            case 19:
            case 20: // bag of hefty
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B42", 100, false) + ")";
              break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25: // bag of holding
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B43", 100, false) + ")";
              break;
            case 43:
            case 44:
            case 45:
            case 46: // chest of massiv volume
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B58", 100, false) + ")";
              break;
            case 71:
            case 72:
            case 73:
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B79", 100, false) + ")";
              break;
            case 95: // saddle bags B89
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B89", 100, false) + ")";
              break;
          }
        }
        else // gems/candles, dust
        if (sTable.equalsIgnoreCase("A14")) {
          switch (nRoll) {
            case 45:
            case 46:
            case 47:
            case 48: // ion stones
              sSpecialInfo = oParent.DiceParse(" (" + TableBlock.GetTableRecord(oParent,"B78", 20, false) + ")");
              break;
            case 33:
            case 34:
            case 35:
            case 36: // gut stone
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B73", 100, false) + ")";
              break;
          }
        }
        else
        if (sTable.equalsIgnoreCase("A15")) {// carpets house hold items and tools
          switch (nRoll) {
            case 18:
            case 19:
            case 20: // carpet of flying
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B57", 100, false) + ")";
              break;
          }
        }
        else
        if (sTable.equalsIgnoreCase("A16")) { // musical instruments
          switch (nRoll) {
            case 61:
            case 62:
            case 63:
            case 64: // horn vahlla
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B77", 20, false) + ")";
              break;
          }
        }
        else
        if (sTable.equalsIgnoreCase("A17")) {// crystal balls, wierd stuff
          switch (nRoll) {
            case 16:
            case 17:
            case 18:
            case 19:
            case 20: // crystal ball type
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B63", 100, false) + ")";
              break;
            case 46:
            case 47:
            case 48: // figurine of wonderous
              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B69", 100, false) + ")";
              break;
           case 58:
           case 59:
           case 60: // holy amylet of prot
             sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B76", 100, false) + ")";
             break;
           case 74:
           case 75:
           case 76:
           case 77: // quetzaalytochtipli's  feather
             sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"B84", 20, false) + ")";
             break;
          }
        }
        if (sTable.equalsIgnoreCase("A20")) {// special armor and shields
          switch (nRoll) {
            default:
//              sSpecialInfo = " (" + TableBlock.GetTableRecord(oParent,"A20", 20, false) + ")";
              break;
          }
        }


          sWork = "" + oThisRecord.sName +sSpecialInfo+ " ep:" + oThisRecord.nEXP +
              " gp:" + oThisRecord.nGold + " page:" + oThisRecord.sPage +
              "\n";
        }

      }
      sParseThis = mMagicTableMatcher.replaceFirst(sWork);
      mMagicTableMatcher = Pattern.compile(sMagicTableMatcher).matcher(
          sParseThis);
    }

    return sParseThis;
  }

//--------------------------------------------------------------------
  // roll up all the magic items they have in this treasure type
  // and return it.
  public static String[] GetMagicItemDetails(HackSackFrame oParent, String sThisText) {
    int nRoll = 0;
    String sAnyMatcher = "(?si)A(\\d+)";
    String sAnyExceptWeaponMatcher = "(?si)AeW(\\d+)";
    String sAnyWeaponMatcher = "(?si)AW(\\d+)";
    String sAnyScrollMatcher = "(?si)S(\\d+)";
    String sAnyPotionMatcher = "(?si)P(\\d+)";

    // parse out all the dice rolls first
    sThisText = oParent.DiceParse(sThisText);

    int nCount = 0;
    Matcher mAnyMatcher = Pattern.compile(sAnyMatcher).matcher(sThisText);
    while (mAnyMatcher.find()) {
      nCount += Integer.parseInt(mAnyMatcher.group(1));
      sThisText = mAnyMatcher.replaceFirst("");
      mAnyMatcher = Pattern.compile(sAnyMatcher).matcher(sThisText);
    }
    if (nCount > 0) {
//      sThisText = "Any "+nCount+" magic items."+sThisText;
      for (int i = 0; i < nCount; i++) {
        nRoll = oParent.MyRandom(100);
        TableBlock oRecord = oParent.GetMagicTableRecord("A1", nRoll, true);
        sThisText = oRecord.sText + ":{" + oRecord.sExtra + "}" + sThisText;
      }

    }

    nCount = 0;
    Matcher mAnyExceptWeaponMatcher = Pattern.compile(sAnyExceptWeaponMatcher).
        matcher(sThisText);
    while (mAnyExceptWeaponMatcher.find()) {
      nCount += Integer.parseInt(mAnyExceptWeaponMatcher.group(1));
      sThisText = mAnyExceptWeaponMatcher.replaceFirst("");
      mAnyExceptWeaponMatcher = Pattern.compile(sAnyExceptWeaponMatcher).
          matcher(sThisText);
    }
    if (nCount > 0) {
//      sThisText = "Any " + nCount + " magic items except weapons." + sThisText;
      for (int i = 0; i < nCount; i++) {
        nRoll = oParent.MyRandom(100);
        while (nRoll >= 76 && nRoll < 100) // cant have weapons
          nRoll = oParent.MyRandom(100);

        TableBlock oRecord = oParent.GetMagicTableRecord("A1", nRoll, true);
        sThisText = oRecord.sText + ":{" + oRecord.sExtra + "}" + sThisText;
      }
    }

    nCount = 0;
    Matcher mAnyWeaponMatcher = Pattern.compile(sAnyWeaponMatcher).matcher(
        sThisText);
    while (mAnyWeaponMatcher.find()) {
      nCount += Integer.parseInt(mAnyWeaponMatcher.group(1));
      sThisText = mAnyWeaponMatcher.replaceFirst("");
      mAnyWeaponMatcher = Pattern.compile(sAnyWeaponMatcher).matcher(sThisText);
    }
    if (nCount > 0) {
//      sThisText = "Any "+nCount+" magic weapons."+sThisText;
      for (int i = 0; i < nCount; i++) {
        sThisText = "Weapon:{A21}" + sThisText;
      }
    }

    nCount = 0;
    Matcher mAnyScrollMatcher = Pattern.compile(sAnyScrollMatcher).matcher(
        sThisText);
    while (mAnyScrollMatcher.find()) {
      nCount += Integer.parseInt(mAnyScrollMatcher.group(1));
      sThisText = mAnyScrollMatcher.replaceFirst("");
      mAnyScrollMatcher = Pattern.compile(sAnyScrollMatcher).matcher(sThisText);
    }
    if (nCount > 0) {
//      sThisText = "Any "+nCount+" magic scrolls."+sThisText;
      for (int i = 0; i < nCount; i++) {
        sThisText = "Scroll:{A3}";
      }
    }

    nCount = 0;
    Matcher mAnyPotionMatcher = Pattern.compile(sAnyPotionMatcher).matcher(
        sThisText);
    while (mAnyPotionMatcher.find()) {
      nCount += Integer.parseInt(mAnyPotionMatcher.group(1));
      sThisText = mAnyPotionMatcher.replaceFirst("");
      mAnyPotionMatcher = Pattern.compile(sAnyPotionMatcher).matcher(sThisText);
    }
    if (nCount > 0) {
//      sThisText = "Any "+nCount+" magic potions."+sThisText;
      for (int i = 0; i < nCount; i++) {
        sThisText = "Potion:{A2}" + sThisText;
      }
    }

    // might want to seperate this later and make it so each
    // element in array is different magic type?
    String[] sReturn = new String[1];
    sReturn[0] = ParseMagicItemTables.ParseMagicItemTable(oParent,sThisText);
    return sReturn;
  }


}
