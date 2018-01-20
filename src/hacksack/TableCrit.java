package hacksack;
import java.awt.*;
import java.util.*;
import org.jdom.*;

import java.util.*;
import java.util.regex.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableCrit {
  public int nRoll = -1;
  public String sLocation = null;
  double dMaxDamageAtLocation = 0.0;
//  public String[] sSeverity;

  public String[] sAll;
  public String[] sHack;
  public String[] sCrush;
  public String[] sPierce;

  public TableCrit() {
//    sSeverity = new String[25];

    sAll = new String[25];
    sHack = new String[25];
    sCrush = new String[25];
    sPierce = new String[25];

  }

  //
  public TableCrit(int nThisRoll, String sThisLocation) {
    this();
    this.nRoll = nThisRoll;
    this.sLocation = sThisLocation;
  }


static TableCrit getCritFromRoll(HackSackFrame oParent, int nRoll) {
  TableCrit oCrit = null;
  TableCrit oCurrent = null;

  for (int i=0;i<oParent.lTableCrit.size();i++) {
//oParent.jGMTextArea.append("<------------->\n");
    oCurrent = (TableCrit)oParent.lTableCrit.get(i);
    if (oCrit == null) {
        oCrit = oCurrent;
    } else {
//oParent.jGMTextArea.append("if "+nRoll+" >= "+oCrit.nRoll+" && "+
//                                 nRoll+" <= "+oCurrent.nRoll+" && "+
//                                 oCurrent.nRoll+" > "+oCrit.nRoll+"...\n");
        if (nRoll >= oCrit.nRoll &&
            nRoll <= oCurrent.nRoll &&
            oCurrent.nRoll > oCrit.nRoll) {
          oCrit = oCurrent;
        }
      }
//oParent.jGMTextArea.append("cRoll:"+oCurrent.nRoll+" critRoll:"+oCrit.nRoll+" nRoll:"+nRoll+"\n");
//oParent.jGMTextArea.append("</------------->\n");

  }

//oParent.jGMTextArea.append("END---cRoll:"+oCurrent.nRoll+" critRoll:"+oCrit.nRoll+" nRoll:"+nRoll+"\n");
  return oCrit;
}


/**
 * this returns an element of this class used to place into a doc
 * and save as xml
 *
 * @return Element
 */
Element xmlGetElements() {
  Element eItem= new Element("HitLocation");

  eItem.addContent(new Element("BodyPart").setText(xmlControl.escapeChars(sLocation)));

  eItem.addContent(new Element("MaxDamage").setText(""+dMaxDamageAtLocation));
  eItem.addContent(new Element("DiceRange").setText(""+nRoll));

  for (int i=1;i<=24;i++) {
    Element eSeverity = new Element("Severity_"+i);

    eSeverity.addContent(new Element("EffectALL").setText(sAll[i]));
    eSeverity.addContent(new Element("EffectHACK").setText(sHack[i]));
    eSeverity.addContent(new Element("EffectCRUSH").setText(sCrush[i]));
    eSeverity.addContent(new Element("EffectPIERCE").setText(sPierce[i]));
    eItem.addContent(eSeverity);
  }

  return eItem;
}

/**
 * this gets an class from a element
 *
 * @param eItem Element
 * @return TableClass
 */
static TableCrit xmlGetFromElements(Element eItem) {
  TableCrit oO = new TableCrit();

  oO.sLocation = xmlControl.unEscapeChars(eItem.getChild("BodyPart").getText());
  oO.dMaxDamageAtLocation = Double.parseDouble(eItem.getChild("MaxDamage").getText());
  oO.nRoll = Integer.parseInt(eItem.getChild("DiceRange").getText().trim());

  for (int i=1;i<=24;i++) {
    Element eSeverity = eItem.getChild("Severity_"+i);
    oO.sAll[i] = eSeverity.getChild("EffectALL").getText().trim();
    oO.sCrush[i] = eSeverity.getChild("EffectCRUSH").getText().trim();
    oO.sHack[i] = eSeverity.getChild("EffectHACK").getText().trim();
    oO.sPierce[i] = eSeverity.getChild("EffectPIERCE").getText().trim();
  }

  return oO;
}

/**
 * this builds a document of elements from an arraylist so it can be saved to
 * an xml file.
 *
 * not used in the code, left in case I need it later
 *
 * @param lList ArrayList
 * @param nMaxID int
 * @return Document
 */
static Document xmlBuildDocFromList(ArrayList lList,int nMaxID) {
 Element eRoot = new Element("HM_CritTable");
 eRoot.setAttribute(new Attribute("JDOM","10b"));
 Document doc = new Document(eRoot);

 for(int i=0;i<lList.size();i++) {

   TableCrit oO = (TableCrit)lList.get(i);
   eRoot.addContent(oO.xmlGetElements());

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

  try {
  Element eRoot = doc.getRootElement();

  java.util.List lItems = eRoot.getChildren("HitLocation");

  Iterator in = lItems.iterator();
  while (in.hasNext()) {
    Element eItem = (Element)in.next();
    TableCrit oO = TableCrit.xmlGetFromElements(eItem);

    lList.add(oO);
  }

  }
  catch (NullPointerException err) {
    oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                      "Error occured while trying to load hitlocation from XML.");
  }

  return lList;
}


} // end TableCrit

// each effect of the crit, broken bone and side effects from that per effect
class CritEffect {
  String sName = null;
  String sDesc = null;

  public CritEffect() {

  }
}

class CritResults {
  int nSeverity = 0;
  int nDiceRoll = 0;
  int nBSL = 0;
  int nSeverityRoll = 0;
  boolean bHasPermScar = false;
  boolean bCanCrit = true;
  boolean bCanFollowThrough = true;
  boolean bCanPenetrate = true;
  String sSide = null;
  String sLocation = null;
  String sResult = null;

  String sSectionUsed = null;
  int nWeaponType = 0;
  String sDamageDice = null;
  int nBaseDamageDone = 0;
  CreatureCombat oAtk = null;
  double dMaxDamageAtLocation = 0.0;

  ArrayList lCritEffects = null;

  public CritResults() {
    lCritEffects = new ArrayList();
  }

  // do the backwards and forwards penetration/etc for severity, return 0 if below 0
  static int GetSeverityRoll(HackSackFrame oParent) {
    int nRoll = oParent.MyRandom(8);
    int nRollTotal = nRoll;
//    gmLog(true,"--SEV:"+nRoll+"\n");
    while (nRoll == 8 || nRoll == 1) {
      switch (nRoll) {
        case 1:
          nRoll = oParent.MyRandom(8);
          nRollTotal -= (nRoll - 1);
          break;
        case 8:
          nRoll = oParent.MyRandom(8);
          nRollTotal += (nRoll - 1);
          break;
      }
//    gmLog(true,"--SEVnRoll:"+nRoll+"\n");
//    gmLog(true,"--SEVnTotalRoll:"+nRollTotal+"\n");
    }
    return nRollTotal;
  }

  // manage critical hit
  // frame,
  // TargetAC, AttackerTHACO, AttackerToHit
  // TargetSize, AttackerSize
  // WeaponType Index (hack=0,crush=1,pierce=2)
  // DiceRolled,  Specific Location
  /**
   *
   * @param oParent HackSackFrame
   * @param nClass int
   * @param nAC int
   * @param nToHitRating int
   * @param nToHit int
   * @param nTSize int
   * @param nASize int
   * @param nWeaponTypeIndex int
   * @param nRoll int
   * @param nLocationIndex int
   * @param nSpecSev int
   * @param bSpecificRoll boolean
   * @param sDamageDice String
   * @param nBaseDamageDone int
   * @param oAtk CreatureCombat
   * @return CritResults
   */
  static CritResults GetCritResults(HackSackFrame oParent,
                                    int nClass,
                                    int nAC, int nToHitRating, int nToHit,
                                    int nTSize, int nASize,
                                    int nWeaponTypeIndex,
                                    int nRoll,
                                    int nLocationIndex,
                                    int nSpecSev,
                                    boolean bSpecificRoll,
                                    String sDamageDice,
                                    int nBaseDamageDone,
                                    CreatureCombat oAtk) {
    CritResults oCrit = new CritResults();
//oParent.jGMTextArea.append("Crit 1 ("+nWeaponTypeIndex+")\n");

//    int nBSL = (nAC - (nThaco - 15) + nToHit);
    AttackRank oA = new AttackRank();
    int nBSL = (nAC - (oA.getTHAC(oParent, nClass, nToHitRating, 15)) + nToHit);
    oCrit.nBSL = nBSL;
    String sSide = (oParent.MyRandom(2) > 1) ? "right" : "left";
    int nSeverityRoll = (nSpecSev>0?nSpecSev:GetSeverityRoll(oParent));
    oCrit.nSeverityRoll = nSeverityRoll;

    int nSeverity = (nBSL + nSeverityRoll);
    if (nSeverity < 0) { // below 0? set to 0, normal hit
      nSeverity = 0;
    }
    else
    if (nSeverity > 24) { // above 24? to high, max is 24
      nSeverity = 24;

    }
    if (nSeverity > 0) {
      // set location if its not "normal hit"
      if (nLocationIndex > 0) {
        int[] nCritLocationValues = LoadCritLocationValues(oParent);
        nRoll = nCritLocationValues[nLocationIndex-1];
        sSide = "(if not specific:" + sSide + ")"; // side location dont matter, its specific to target
      }
      else
      if (!bSpecificRoll) { // figure out size diff and roll dice
        String sCritDiceSize = TableCritBody.GetTableBody(oParent, nTSize,
            "8EE", (nASize + 1));
        sCritDiceSize = oParent.DiceParse(sCritDiceSize);
        int nCritDiceSize = Integer.parseInt(sCritDiceSize);
        nRoll = oParent.MyRandom(nCritDiceSize);
      }

      TableCrit oCritRec = TableCrit.getCritFromRoll(oParent,nRoll);
      String sResultString = oCritRec.sAll[nSeverity]; // the "all" field, every type gets these bits
      String sBodyLocation = oCritRec.sLocation; // body part hit
      oCrit.dMaxDamageAtLocation = oCritRec.dMaxDamageAtLocation;

      switch ( (nWeaponTypeIndex)) {
        case 1: // slash
          sResultString += "," + oCritRec.sHack[nSeverity];
          break;
        case 2: // crush
          sResultString += "," + oCritRec.sCrush[nSeverity];
          break;
        case 3: // pierce
          sResultString += "," + oCritRec.sPierce[nSeverity];
          break;

          // add CREATURE BITE type here???
//        case 4:
//          sResultString += "," + oCritRec.sCreatureBite[nSeverity];
//          break;

          // default to slash type damage.
        default:
          sResultString += "," + oCritRec.sHack[nSeverity];
          break;

      }

      oCrit.sSectionUsed = sResultString;
oParent.gmLog(true,true,"Crit DBG1, nRoll:"+nRoll+" Sev:"+nSeverity+" nBSL:"+nBSL+", "+sResultString+"\n"+
                                 "Location:"+sBodyLocation.trim()+"\n");

//      gmLog(true,"BSL:"+nBSL+"\n");
//      gmLog(true,"Severity:"+nSeverity+"\n");
      sResultString = PreParseCritTableText(oParent, oCrit,sResultString,
                                            nSeverity,sBodyLocation,nRoll, nWeaponTypeIndex);

oParent.gmLog(true,true,"Crit DBG2, nRoll:"+nRoll+" Sev:"+nSeverity+" nBSL:"+nBSL+", "+sResultString+"\n"+
                                      "Location:"+sBodyLocation.trim()+"\n");

      oCrit.oAtk = oAtk;
      oCrit.sDamageDice = sDamageDice;
      oCrit.nBaseDamageDone = nBaseDamageDone;
      oCrit.nDiceRoll = nRoll;
      oCrit.sSide = sSide;
      oCrit.sLocation = sBodyLocation;

      oCrit.nSeverity = nSeverity;
      // check for null case the location came up bad.
      if (oCrit.sResult == null) {
        oCrit.sResult = sResultString;
      }
      oCrit.bHasPermScar = (nSeverity * 10 >= oParent.MyRandom(100));
      oCrit.bCanFollowThrough = nSeverity < 5; // otherwise we cannot
      oCrit.bCanCrit = nSeverity < 10; // otherwise we cannot.
      oCrit.bCanPenetrate = nSeverity < 15; // otherwise we cannot
    }
    else { // else severity == 0 or lower...
//          gmCritLog(true, false, "Result: NORMAL HIT\n");
      oCrit.sResult = "NORMAL HIT";
      oCrit.sLocation = "NORMAL HIT";
      oCrit.sSide = "";
    }

    // diceparse up the descriptions
    for (int i = 0; i < oCrit.lCritEffects.size(); i++) {
      CritEffect oE = (CritEffect) oCrit.lCritEffects.get(i);
      oE.sDesc = oParent.DiceParse(oE.sDesc);
    }

    return oCrit;
  }

  /**
   * text comes in as "A,B,C,D" and is returned as "{A}{B}{C}{D}"
   *
   * @param oParent HackSackFrame
   * @param oCrit CritResults
   * @param sPreParseThis String
   * @param nSeverityLevel int
   * @param sLocation String
   * @param nRoll int
   * @param nWeaponType int
   * @return String
   */
  static String PreParseCritTableText(HackSackFrame oParent, CritResults oCrit,
                                      String sPreParseThis, int nSeverityLevel,
                                      String sLocation, int nRoll,
                                      int nWeaponType) {
    String sResult = sPreParseThis;
//  int nCount = 0;
    String sWorking = "";
    String[] sFields = sResult.split(",");

    for (int i = 0; i < sFields.length; i++) {
      sWorking += "{" + sFields[i] + "}";
      if ( (i + 1) != sFields.length) {
        sWorking += ", ";
      }
    }

    sPreParseThis = sWorking;

    // alright, lets figure it out, cut up the table names and get results
    sPreParseThis = ParseCritTableText(oParent, oCrit, sPreParseThis,
                                       nSeverityLevel,
                                       sLocation, nRoll, nWeaponType, false);
    //ok, now lets parse up the dice rolls in it
    sPreParseThis = oParent.DiceParse(sPreParseThis);
    return sPreParseThis;
  }

  /**
   * this parses up the x1,ls1, s3, d3 stuff
   *
   * @param oParent HackSackFrame
   * @param oCrit CritResults
   * @param sParseThis String
   * @param nSeverityLevel int
   * @param sLocation String
   * @param nRoll int
   * @param nWeaponType int
   * @param bPreviousCause boolean
   * @return String
   */
  static String ParseCritTableText(HackSackFrame oParent, CritResults oCrit,
                                   String sParseThis, int nSeverityLevel,
                                   String sLocation, int nRoll, int nWeaponType,
                                   boolean bPreviousCause) {

    Matcher mExtraDamage = Pattern.compile("\\{(\\d+)\\}").matcher(sParseThis);
    if (mExtraDamage.find()) {

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Extra Damage";
        oEff.sDesc = "+" + mExtraDamage.group(1);
        oCrit.lCritEffects.add(oEff);
      }

      sParseThis = mExtraDamage.replaceAll("+" + mExtraDamage.group(1) +
                                           " extra damage");
    }

    Matcher mToHitReduction = Pattern.compile("\\{a(\\d+)\\}").matcher(
        sParseThis);
    if (mToHitReduction.find()) {
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "To Hit Reduction";
        oEff.sDesc = "-" + mToHitReduction.group(1);
        oCrit.lCritEffects.add(oEff);
      }

      sParseThis = mToHitReduction.replaceAll("-" + mToHitReduction.group(1) +
                                              " to-hit");
    }
//-----------------
    Matcher mBoneBroke = Pattern.compile("\\{b(\\d+)\\}").matcher(sParseThis);
    if (mBoneBroke.find()) { // resolve which type
//    String sText = "Broke bone " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "83",
                                                Integer.
                                                parseInt(mBoneBroke.group(1)));

      if (sLocation.matches("(?i).*?arm.*?") ||
          sLocation.matches("(?i).*?shoulder.*?")) { // drop weapon if arm
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {w}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (oParent.MyRandom(100) <= 15) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

      }
      if ( (sLocation.matches("(?i).*?chest.*?") ||
            sLocation.matches("(?i).*?side.*?") ||
            sLocation.matches("(?i).*?back.*?"))
          && oParent.MyRandom(100) <= 15) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ib}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      sParseThis = mBoneBroke.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Broken Bone";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mBoneFracture = Pattern.compile("\\{bf(\\d+)\\}").matcher(
        sParseThis);
    if (mBoneFracture.find()) { // resolve which type
//    String sText = "Compound Fracture of " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "83",
                                                Integer.
                                                parseInt(mBoneFracture.group(1)));
      if (sLocation.matches("(?i).*?arm.*?") ||
          sLocation.matches("(?i).*?shoulder.*?")) { // drop weapon if arm
        sText +=
            ParseCritTableText(oParent, oCrit, " also causing {ws}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (sLocation.matches("(?i).*?back.*?") ||
          sLocation.matches("(?i).*?neck.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " also causing {p}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " also causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if ( (sLocation.matches("(?i).*?chest.*?") ||
            sLocation.matches("(?i).*?side.*?") ||
            sLocation.matches("(?i).*?back.*?"))
          && oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " also causing {ib}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

      }
      sParseThis = mBoneFracture.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Compound Bone Fracture";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mBoneMultiple = Pattern.compile("\\{bm(\\d+)\\}").matcher(
        sParseThis);
    if (mBoneMultiple.find()) { // resolve which type
//    String sText = "Multiple Bone Fracture of " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "83",
                                                Integer.
                                                parseInt(mBoneMultiple.group(1)));
      if (sLocation.matches("(?i).*?arm.*?") ||
          sLocation.matches("(?i).*?shoulder.*?")) { // drop weapon if arm
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ws}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (sLocation.matches("(?i).*?back.*?") ||
          sLocation.matches("(?i).*?neck.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {p}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if ( (sLocation.matches("(?i).*?chest.*?") ||
            sLocation.matches("(?i).*?side.*?") ||
            sLocation.matches("(?i).*?back.*?"))
          && oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ib}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

      }
      sParseThis = mBoneMultiple.replaceAll(sText);
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Multiple Bone Frature";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mBoneShatter = Pattern.compile("\\{bs(\\d+)\\}").matcher(sParseThis);
    if (mBoneShatter.find()) { // resolve which type
//    String sText = "Bone Shatter of " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "83",
                                                Integer.
                                                parseInt(mBoneShatter.group(1)));
      if (sLocation.matches("(?i).*?arm.*?") ||
          sLocation.matches("(?i).*?shoulder.*?")) { // drop weapon if arm
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ws}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (sLocation.matches("(?i).*?back.*?") ||
          sLocation.matches("(?i).*?neck.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {p}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if ( (sLocation.matches("(?i).*?chest.*?") ||
            sLocation.matches("(?i).*?side.*?") ||
            sLocation.matches("(?i).*?back.*?"))
          && oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ib}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

      }
      sParseThis = mBoneShatter.replaceAll(sText);
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Bone Shatter";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }
//------------------
    Matcher mDexReduction = Pattern.compile("\\{d(\\d+)\\}").matcher(sParseThis);
    if (mDexReduction.find()) {
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Dexterity Reduction";
        oEff.sDesc = "-" + mDexReduction.group(1);
        oCrit.lCritEffects.add(oEff);
      }
      sParseThis = mDexReduction.replaceAll("-" + mDexReduction.group(1) +
                                            " to Dexterity");

    }

    Matcher mFallDown = Pattern.compile("\\{(f)\\}").matcher(sParseThis);
    if (mFallDown.find()) {
      sParseThis = mFallDown.replaceAll("Fall down prone");
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Fall";
        oEff.sDesc = "Fall down prone";
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mTemporalHonor = Pattern.compile("\\{h(\\d+)\\}").matcher(
        sParseThis);
    if (mTemporalHonor.find()) {
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Temporal Honor Reduction";
        oEff.sDesc = "-" + (Integer.parseInt(mTemporalHonor.group(1)) * 5);
        oCrit.lCritEffects.add(oEff);
      }
      sParseThis = mTemporalHonor.replaceAll("-" +
                                             (Integer.parseInt(mTemporalHonor.
          group(1)) * 5) + " to temporal honor");

    }

    Matcher mInternalBleed = Pattern.compile("\\{(ib)\\}").matcher(sParseThis);
    if (mInternalBleed.find()) {
      sParseThis = mInternalBleed.replaceAll(
          "Internal Bleeding 1d4 damage a hour;con check or system shock");

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Internal Bleeding";
        oEff.sDesc = "1d4 damage a hour;con check or system shock";
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mLimbSevered = Pattern.compile("\\{(ls)\\}").matcher(sParseThis);
    if (mLimbSevered.find()) {
//    String sText = "Limb Severed ";
      String sText = "";
      if (!sLocation.matches("(?i).*?toe.*?") &&
          !sLocation.matches("(?i).*?finger.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      sParseThis = mLimbSevered.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Severed Limb";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mMoveLoss = Pattern.compile("\\{m(\\d+)\\}").matcher(sParseThis);
    if (mMoveLoss.find()) {
      String sText = TableBlock.GetTableRecord(oParent, "8FF", 10, false);
      sParseThis = mMoveLoss.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Movement Reduction";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mMinorConcusion = Pattern.compile("\\{(mc)\\}").matcher(sParseThis);
    if (mMinorConcusion.find()) {
//    String sText =
//        "Minor Concussion, Temporarily gain migraines flaw and headache";
      String sText = "Temporarily gain migraines flaw and headache";
      if (oParent.MyRandom(100) <= (3 * nSeverityLevel)) {
        sText += " and seizure disorder flaw";
      }
      sParseThis = mMinorConcusion.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Concussion";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mMuscleTear = Pattern.compile("\\{mt(\\d+)\\}").matcher(sParseThis);
    if (mMuscleTear.find()) {
//    String sText = "Muscle Tear of the " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "84_5",
                                                Integer.
                                                parseInt(mMuscleTear.group(1)));
      if (sLocation.matches("(?i).*?arm.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ws}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

        // figure out type of tear
      }
      if (oParent.MyRandom(100) <= (3 * nSeverityLevel)) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      sParseThis = mMuscleTear.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Muscle Tear";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mParalyze = Pattern.compile("\\{(p)\\}").matcher(sParseThis);
    if (mParalyze.find()) {
      if (oParent.MyRandom(100) <= (5 * nSeverityLevel)) {
        sParseThis = mParalyze.replaceAll("Paralyzed");
        if (!bPreviousCause) {
          CritEffect oEff = new CritEffect();
          oEff.sName = "Paralysis";
          oEff.sDesc = "Paralysis";
          oCrit.lCritEffects.add(oEff);
        }
      }
      else {
        sParseThis = mParalyze.replaceAll("");
      }
    }

    Matcher mProfuseBleed = Pattern.compile("\\{(pb)\\}").matcher(sParseThis);
    if (mProfuseBleed.find()) {
//    sParseThis = mProfuseBleed.replaceAll(
//        "Profuse Bleeding, bleed to death in Con/2 rounds");
      sParseThis = mProfuseBleed.replaceAll(
          "bleed to death in Con/2 rounds");

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Profuse Bleeding";
        oEff.sDesc = "bleed to death in Con/2 rounds";
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mStrengthReduction = Pattern.compile("\\{s(\\d+)\\}").matcher(
        sParseThis);
    if (mStrengthReduction.find()) {
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Strength Reduction";
        oEff.sDesc = "-" + mStrengthReduction.group(1);
        oCrit.lCritEffects.add(oEff);
      }
      sParseThis = mStrengthReduction.replaceAll("-" +
                                                 mStrengthReduction.group(1) +
                                                 " strength");

    }

    Matcher mSevereConcussion = Pattern.compile("\\{(sc)\\}").matcher(
        sParseThis);
    if (mSevereConcussion.find()) {
//    String sText =
//        "Severe Concussion, Temporarily gain migraines and short term memory loss flaws";
      String sText =
          "Temporarily gain migraines and short term memory loss flaws";
      if (oParent.MyRandom(100) <= (5 * nSeverityLevel)) {
        sText += " and gains seizure disorder flaw";
      }
      sParseThis = mSevereConcussion.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Severe Concussion";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    // figure out type of tear
    Matcher mTornLigament = Pattern.compile("\\{t(\\d+)\\}").matcher(sParseThis);
    if (mTornLigament.find()) {
//    String sText = "Torn ligament of the " +
      String sText = TableCritBody.GetTableBody(oParent, nRoll, "84_5",
                                                Integer.
                                                parseInt(mTornLigament.group(1)));
      if (oParent.MyRandom(100) <= 30) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      if (sLocation.matches("(?i).*?arm.*?") ||
          sLocation.matches("(?i).*?shoulder.*?") ||
          sLocation.matches("(?i).*?hand.*?")) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {ws}", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);

      }
      sParseThis = mTornLigament.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Torn Ligament";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mUnconscious = Pattern.compile("\\{(u)\\}").matcher(sParseThis);
    if (mUnconscious.find()) {
//    sParseThis = mUnconscious.replaceAll("Unconscious, remains comatose until hit points suffered from wound are healed (naturally or magically)");
      sParseThis = mUnconscious.replaceAll("remains comatose until hit points suffered from wound are healed (naturally or magically)");

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Unconscious";
        oEff.sDesc = "remains comatose until hit points suffered from wound are healed (naturally or magically)";
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mWeaponDrop = Pattern.compile("\\{(w)\\}").matcher(sParseThis);
    if (mWeaponDrop.find()) {
      sParseThis = mWeaponDrop.replaceAll("drop all carried weapons or items");

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Weapon Drop";
        oEff.sDesc = "drop all carried weapons or items";
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mWeaponDropCheck = Pattern.compile("\\{(ws)\\}").matcher(sParseThis);
    if (mWeaponDropCheck.find()) {
//    sParseThis = mWeaponDropCheck.replaceAll(
//        "{w} unless check against 1/2 strength");
      sParseThis = mWeaponDropCheck.replaceAll(
          ParseCritTableText(oParent, oCrit,
                             "{w} unless check against 1/2 strength",
                             nSeverityLevel,
                             sLocation, nRoll, nWeaponType, true));

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Check for Weapon Drop";
        oEff.sDesc = "unless check against 1/2 strength";
        oCrit.lCritEffects.add(oEff);
      }
    }

    Matcher mVitalOrgan = Pattern.compile("\\{v(\\d+)\\}").matcher(sParseThis);
    if (mVitalOrgan.find()) {
      String sText = ParseCritTableText(oParent, oCrit, "{WT} ", nSeverityLevel,
                                        sLocation, nRoll, nWeaponType, true) +
          ParseCritTableText(oParent, oCrit,
                             TableCritBody.GetTableBody(oParent, nRoll, "86_7",
          Integer.parseInt(mVitalOrgan.group(1))), nSeverityLevel, sLocation,
                             nRoll, nWeaponType, true) +
//               ParseCritTableText(oParent,oCrit," (vital organ) ", nSeverityLevel,sLocation, nRoll, nWeaponType,true) +
          " " +
          ParseCritTableText(oParent, oCrit,
                             TableBlock.GetTableRecord(oParent, "8GG", 100, false),
                             nSeverityLevel, sLocation, nRoll, nWeaponType, true) +
          ParseCritTableText(oParent, oCrit, ",{ws}-10;{ib} ", nSeverityLevel,
                             sLocation, nRoll, nWeaponType, true);
      if (oParent.MyRandom(100) <= (3 * nSeverityLevel)) {
        sText +=
            ParseCritTableText(oParent, oCrit, " causing {pb} ", nSeverityLevel,
                               sLocation, nRoll, nWeaponType, true);
      }
      sParseThis = mVitalOrgan.replaceAll(sText);

      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Vital Organ Damage";
        oEff.sDesc = sText;
        oCrit.lCritEffects.add(oEff);
      }

    }

    Matcher mMultipleDamage = Pattern.compile("\\{x(\\d+)\\}").matcher(sParseThis);
    if (mMultipleDamage.find()) {
      if (!bPreviousCause) {
        CritEffect oEff = new CritEffect();
        oEff.sName = "Additional Damage Dice";
        oEff.sDesc = mMultipleDamage.group(1);
        oCrit.lCritEffects.add(oEff);
        sParseThis = mMultipleDamage.replaceAll(mMultipleDamage.group(1) +
                                                " additional damage dice, including penetration damage");
      }

    }

    Matcher mWeaponType = Pattern.compile("\\{WT\\}").matcher(sParseThis);
    if (mWeaponType.find()) {
      String sText;
      switch (nWeaponType) {
        case 1:
          sText = "slashed";
          break;
        case 2:
          sText = "crushed";
          break;
        case 3:
          sText = "punctured";
          break;

        default:
          sText = "smashed";
          break;
      }
      sParseThis = mWeaponType.replaceAll(sText);
    }

    /**
     * grab the rest like "BRain Goo", "Skull Caved-In"
     * and the like
     *
     */
    Matcher mSpecialFeature = Pattern.compile("\\{(BG)\\}").matcher(sParseThis);
    if (mSpecialFeature.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Brain goo!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature2 = Pattern.compile("\\{(SCI)\\}").matcher(sParseThis);
    if (mSpecialFeature2.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Skull caved in!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature2.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature3 = Pattern.compile("\\{(DOA)\\}").matcher(sParseThis);
    if (mSpecialFeature3.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Killed Dead!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature3.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature4 = Pattern.compile("\\{(CIT)\\}").matcher(sParseThis);
    if (mSpecialFeature4.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Cut in twain!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature4.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature5 = Pattern.compile("\\{(BCC)\\}").matcher(sParseThis);
    if (mSpecialFeature5.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Body Cavity crushed!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature5.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature6 = Pattern.compile("\\{(DECAP)\\}").matcher(sParseThis);
    if (mSpecialFeature6.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Decapitated!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature6.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature7 = Pattern.compile("\\{(NSD)\\}").matcher(sParseThis);
    if (mSpecialFeature7.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Neck snapped!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature7.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature8 = Pattern.compile("\\{(RT)\\}").matcher(sParseThis);
    if (mSpecialFeature8.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Run thru!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature8.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature9 = Pattern.compile("\\{(JR)\\}").matcher(sParseThis);
    if (mSpecialFeature9.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Jaw removed!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature9.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature10 = Pattern.compile("\\{(SCD)\\}").matcher(sParseThis);
    if (mSpecialFeature10.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Spine crushed, dead!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature10.replaceAll(oEff.sDesc);
    }
    Matcher mSpecialFeature11 = Pattern.compile("\\{(WPC)\\}").matcher(sParseThis);
    if (mSpecialFeature11.find()) {
      CritEffect oEff = new CritEffect();
      oEff.sName = "Special Effect";
      oEff.sDesc = "Windpipe crushed, choking!";
      oCrit.lCritEffects.add(oEff);
      sParseThis = mSpecialFeature11.replaceAll(oEff.sDesc);
    }

    return sParseThis;
  }

  // load up the crit locations into an array
  static String[] LoadCritLocationStrings(HackSackFrame oParent) {
    String[] sReturn = new String[oParent.lTableCrit.size() + 1];
    sReturn[0] = "normal attack";
    oParent.jGMCritLocComboBox.removeAllItems();
    oParent.jGMCritLocComboBox.addItem(sReturn[0]);
    for (int j = 0; j < oParent.lTableCrit.size(); j++) {
      TableCrit oCritRecord = (TableCrit) oParent.lTableCrit.get(j);
      sReturn[ (j + 1)] = oCritRecord.sLocation;

      oParent.jGMCritLocComboBox.addItem(oCritRecord.sLocation);
//oParent.DiceOutTextArea.append("CritLocSTR ("+oCritRecord.sLocation+"):"+j+"\n");
    }

    return sReturn;
  }

// load up the crit locations into an array
  static int[] LoadCritLocationValues(HackSackFrame oParent) {
    int[] nReturn = new int[oParent.lTableCrit.size() + 1];
    nReturn[0] = 1;
    for (int j = 0; j < oParent.lTableCrit.size(); j++) {
      TableCrit oCritRecord = (TableCrit) oParent.lTableCrit.get(j);
      nReturn[ (j + 1)] = oCritRecord.nRoll;
//oParent.DiceOutTextArea.append("CritLocINT ("+oCritRecord.sLocation+"):"+j+"\n");
    }

    return nReturn;
  }

}
