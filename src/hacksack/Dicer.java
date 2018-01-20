package hacksack;

import java.util.*;
import java.util.regex.*;

import java.awt.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class Dicer {
  ArrayList lDice = null;
  Random Rand = null;
  int nResult = 0;

  public Dicer() {
    Rand = new Random(System.currentTimeMillis());
    lDice = new ArrayList();
  }

  // flush out the dice debug info
  public void dicerFlush() {
    while (lDice.size() > 0) {
      lDice.remove(0);
    }
  }

  // roll a d10,000 and return the result
  public int d10000() {
    return Random(10000);
  }

  public int d1000() {
    return Random(1000);
  }

  public int d20() {
    return Random(20);
  }

  public int d12() {
    return Random(12);
  }

  public int d10() {
    return Random(10);
  }

  public int d8() {
    return Random(8);
  }

  public int d6() {
    return Random(6);
  }

  public int d4() {
    return Random(4);
  }

  // standard random roll
  public int Random(int nMaxSizeDice) {
    Dice dDice = new Dice();
    int nResult = (Math.abs(Rand.nextInt() % (nMaxSizeDice)) + 1);
    dDice.nRolled = nResult;
    dDice.sDesc = "Dice Rolled = ";
    lDice.add(dDice);
    return nResult;
  } // end MyRandom

  // roll and use penetration
  public int rollRandomWithPenetration(int nMaxSizeDice, int nPenetrationPoint) {

    int nRoll = Random(nMaxSizeDice);
    int nRollTotal = nRoll;

    if (nPenetrationPoint != 0 && (nMaxSizeDice - 1) > nPenetrationPoint) {
      nPenetrationPoint = (nMaxSizeDice - nPenetrationPoint);
    }
    else {
      nPenetrationPoint = nMaxSizeDice;

      // deal with penetration damage...
    }
    while (nRoll >= nPenetrationPoint && nRoll != 1) {
      nRoll = (Math.abs(Rand.nextInt() % (nMaxSizeDice)) + 1);
      nRollTotal += (nRoll - 1);

      // track dice rolls
      Dice dDice = new Dice();
      dDice.nRolled = (nRoll - 1);
      dDice.sDesc = "*Penetrate Roll = ";
      lDice.add(dDice);
    } // end while

    return nRollTotal;
  }

//  public int rollDamage(int nDiceNum, int nDiceSize, int DiceModPer, int nDiceToTotal, int nPenetration)
  /**
   *
   * @param nDiceNum int
   * @param nDiceSize int
   * @param nDamageDiceMod int
   * @param nDiceToTotal int
   * @param nPenetration int
   * @param bDisablePenetration boolean, if true we do not penetrate
   * @return int
   */
  public int rollDamage(int nDiceNum, int nDiceSize, int nDamageDiceMod,
                        int nDiceToTotal, int nPenetration, boolean bDisablePenetration) {
    int nRollTotal = 0;
    int nRoll = 0;
    //nDiceNum = getNumberDice(sDamageDice);
    //nDiceSize = getNumDiceSides(sDamageDice);
    //nDamageDiceMod = getPerDiceMod(sDamageDice);

    for (int i = 0; i < nDiceNum; i++) {
      if (!bDisablePenetration)
        nRoll = rollRandomWithPenetration(nDiceSize, nPenetration);
      else
        nRoll = Random(nDiceSize);
      // if the roll and the modifier are < 1 set value to 1
      nRollTotal += nRoll;
    } // end for
    nRollTotal = ( (nRollTotal + nDamageDiceMod) < 1 ? 1 :
                  nRollTotal + nDamageDiceMod);
    nRollTotal += nDiceToTotal;

    if (nRollTotal < 1) { // incase nDiceToTotal reduced it below 1 also
      nRollTotal = 1; // min damage is always 1

    }
    return nRollTotal;
  }

  // {xdx}
  // {xdx+x}
  // {xdx-x}
  // {xdx*x}
  public String DiceParse(String sParseThis) {
    int nSides = 4;
    int nDice = 1;
    int nTotal = 0;
    int nTotalMod = 0;

    String sDiceMatcher1 = "(?si)\\{(\\d+)d(\\d+)\\}";
    String sDiceMatcher2 = "(?si)\\{(\\d+)d(\\d+)([+-\\{*}])(\\d+)\\}";

    Matcher mDiceMatcher1 = Pattern.compile(sDiceMatcher1).matcher(sParseThis);
    while (mDiceMatcher1.find()) {
      nDice = Integer.parseInt(mDiceMatcher1.group(1));
      nSides = Integer.parseInt(mDiceMatcher1.group(2));
      nTotal = 0;
//         jTextArea1.append(nDice + "d" + nSides + "\n");
      for (int i = 0; i < nDice; i++) {
        int nThisRoll = Random(nSides);
        nTotal += nThisRoll;
//          jTextArea1.append("Matcher1 roll (" + i + ") :" + nThisRoll +
//                            " for total of " + nTotal + "\n");
      }
      sParseThis = mDiceMatcher1.replaceFirst(Integer.toString(nTotal));
      mDiceMatcher1 = Pattern.compile(sDiceMatcher1).matcher(sParseThis);
    }

    Matcher mDiceMatcher2 = Pattern.compile(sDiceMatcher2).matcher(sParseThis);
    while (mDiceMatcher2.find()) {
      nDice = Integer.parseInt(mDiceMatcher2.group(1));
      nSides = Integer.parseInt(mDiceMatcher2.group(2));
      String sSign = mDiceMatcher2.group(3);
      nTotal = 0;
      nTotalMod = Integer.parseInt(mDiceMatcher2.group(4));
//jGMTextArea.append("Matcher2:"+nDice+"d"+nSides+":"+sSign+":"+nTotalMod+"\n");
      for (int i = 0; i < nDice; i++) {
        int nThisRoll = Random(nSides);
        nTotal += nThisRoll;
//jGMTextArea.append("Matcher2 roll ("+i+") :"+nThisRoll+" for total of "+nTotal+"\n");
      }
      if (sSign.equals("*")) {
        nTotal *= nTotalMod;
      }
      else if (sSign.equals("-")) {
        nTotal -= nTotalMod;
      }
      else {
        nTotal += nTotalMod;

      }
      sParseThis = mDiceMatcher2.replaceFirst(Integer.toString(nTotal));
      mDiceMatcher2 = Pattern.compile(sDiceMatcher2).matcher(sParseThis);
    }

    return sParseThis;
  }

  int rollLastRoll() {
    Dice oD = (Dice) lDice.get(lDice.size() - 1);

    return oD.nRolled;
  }

  // does the strign conform to a valid "dice" string?
  // valid is xDx, xDx+x or xDx-x
  static boolean isValidDamageDiceString(String sDiceString) {
    boolean bReturn =
        sDiceString.matches("(?si)(\\d+d\\d+)|(\\d+d\\d+[+-]\\d+)");
    return bReturn;
  }

  // does the strign conform to a valid "dice" string?
  // valid is xDx, xDx+x or xDx-x
  static boolean isValidSimpleDiceString(String sDiceString) {
    boolean bReturn =
        sDiceString.matches("(?si)(\\d+d\\d+)|(\\d+d\\d+[+-]\\d+)");
    return bReturn;
  }

  // get (x)Dx+/-x
  int getNumDiceSides(String sDiceString) {
    String sDice = "(?si)(\\d+)d(\\d+)([+-]\\d+)";
    String sDiceSetTwo = "(?si)(\\d+)d(\\d+)";
    Matcher mDiceMatcher = Pattern.compile(sDice).matcher(sDiceString);
    Matcher mDiceMatcher2 = Pattern.compile(sDiceSetTwo).matcher(sDiceString);
    int nDice = 0;
    int nNumDice = 0;
    int nPerDice = 0;
    if (mDiceMatcher.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher.group(1));
      nDice = Integer.parseInt(mDiceMatcher.group(2));
      // remove +'s if they are there, parseint dont like it
      nPerDice = Integer.parseInt(mDiceMatcher.group(3).replaceAll("[+]", ""));
    }
    else
    if (mDiceMatcher2.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher2.group(1));
      nDice = Integer.parseInt(mDiceMatcher2.group(2));
    }

    return nDice;
  }

  // get xD(x)+/-x
  int getNumberDice(String sDiceString) {
    String sDice = "(?si)(\\d+)d(\\d+)([+-]\\d+)";
    String sDiceSetTwo = "(?si)(\\d+)d(\\d+)";
    Matcher mDiceMatcher = Pattern.compile(sDice).matcher(sDiceString);
    Matcher mDiceMatcher2 = Pattern.compile(sDiceSetTwo).matcher(sDiceString);
    int nDice = 0;
    int nNumDice = 0;
    int nPerDice = 0;
    if (mDiceMatcher.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher.group(1));
      nDice = Integer.parseInt(mDiceMatcher.group(2));
      // remove +'s if they are there, parseint dont like it
      nPerDice = Integer.parseInt(mDiceMatcher.group(3).replaceAll("[+]", ""));
    }
    else
    if (mDiceMatcher2.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher2.group(1));
      nDice = Integer.parseInt(mDiceMatcher2.group(2));
    }

    return nNumDice;
  }

  // get xDx(+/-x)
  int getPerDiceMod(String sDiceString) {
    String sDice = "(?si)(\\d+)d(\\d+)([+-]\\d+)";
    String sDiceSetTwo = "(?si)(\\d+)d(\\d+)";
    Matcher mDiceMatcher = Pattern.compile(sDice).matcher(sDiceString);
    Matcher mDiceMatcher2 = Pattern.compile(sDiceSetTwo).matcher(sDiceString);
    int nDice = 0;
    int nNumDice = 0;
    int nPerDice = 0;
    if (mDiceMatcher.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher.group(1));
      nDice = Integer.parseInt(mDiceMatcher.group(2));
      // remove +'s if they are there, parseint dont like it
      nPerDice = Integer.parseInt(mDiceMatcher.group(3).replaceAll("[+]", ""));
    }
    else
    if (mDiceMatcher2.find(0)) {
      nNumDice = Integer.parseInt(mDiceMatcher2.group(1));
      nDice = Integer.parseInt(mDiceMatcher2.group(2));
    }

    return nPerDice;
  }

  // this will return a nHP value that calculates hp based on the dice string
  // passed such as 1d6+10, or 3d4-2 or 2d4 or just plain 12
  int getHitPoints(String sHPDice) {
    String sDiceMatch = "(\\d+d\\d+([+-]\\d+)?)";
    int nHP = 0;
    if (sHPDice.matches("^\\d+$")) {
      nHP = Integer.parseInt(sHPDice);
    }
    else if (sHPDice.matches("(?si)^" + sDiceMatch + "$")) {
      int nDiceSides = getNumDiceSides(sHPDice);
      int nDiceNumber = getNumberDice(sHPDice);
      int nDiceBonus = getPerDiceMod(sHPDice);
      // roll for number of dice
      for (int i = 0; i < nDiceNumber; i++) {
        nHP += Random(nDiceSides);
      }
      // add the +/- part like +10 on 2d4+10
      nHP += nDiceBonus;
      // sanity incase they do something stupid like 1d4-12
      if (nHP < 0)
        nHP = 0;
    }
    return nHP;
  }

  void setLastDiceDescription(String sDescription) {
    if (lDice.size() > 0) {
      Dice dDiceDebug = (Dice) lDice.get( (lDice.size() - 1));
      dDiceDebug.sDesc = sDescription;
    }
  }

  static void ShowDiceEntryError(HackSackFrame oParent, Component oComponent,
                                 String sError) {
    if (sError == null) {
      oParent.ShowError(oComponent,
                       "Damage dice entered is invalid, example:\n" +
                       "1d6, 1d6-1 or 1d6+1\n" +
                       "2d4-2, 2d4+1 or 2d4");
    }
    else {
      oParent.ShowDone(oComponent, sError);
    }
  }
}

class Dice {
  String sDesc = null;
  int nRolled = -1;

  public Dice() {

  }
}
