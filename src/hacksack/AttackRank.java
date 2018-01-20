package hacksack;
import java.io.*;
import java.util.*;
import java.util.regex.*;


/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class AttackRank {
  int nStartAC = 20;
  int nEndAC = 20;


  public AttackRank() {

  }

// get the AC hit by this person with this roll
void getACHit(HackSackFrame oParent, int nClass, int nHitRank, int nRoll)
  {
    ArrayList lMatrix = getAttackMatrix(oParent,nClass);
    nStartAC = 20;
    nEndAC = 20;

    for ( int i = 0; i< lMatrix.size();i++)
    {
      TableAttackMatrix oT = (TableAttackMatrix)lMatrix.get(i);
//oParent.jPlayerTextArea.append("1AC:" + oT.nAC +" NEED:("+oT.nTh[nHitRank] +") Rolled:("+nRoll+")\n");
      if (oT.nTh[nHitRank] == nRoll) {
        nStartAC = oT.nAC;
        if (nEndAC == 20) // first AC hit will be the end in list.
          nEndAC = oT.nAC;
      }
      if ( (oT.nTh[nHitRank] <= nRoll) &&
          (oT.nAC < nStartAC) &&
          (nEndAC == 20))
        nEndAC = oT.nAC;
    }
  }

// find the roll needed TO HIT AC X
  int getTHAC(HackSackFrame oParent, int nClass, int nHitRank, int nAC)
    {
      int nTHAC = 20;
      ArrayList lMatrix = getAttackMatrix(oParent,nClass);

      for ( int i = 0; i< lMatrix.size();i++)
      {
        TableAttackMatrix oT = (TableAttackMatrix)lMatrix.get(i);
        if (oT.nAC == nAC) {
          nTHAC = oT.nTh[nHitRank];
        }
      }
      return nTHAC;
    }

ArrayList getAttackMatrix(HackSackFrame oParent,int nClass)
  {
    ArrayList lMatrix= null;
        switch(nClass) {


          case 0: // fighter
            lMatrix = oParent.lWarriorMatrix;
          break;

          case 1: // cleric
            lMatrix = oParent.lClericMatrix;
          break;

          case 2: // thief
            lMatrix = oParent.lThiefMatrix;
          break;

          case 3: // magic-user
            lMatrix = oParent.lMageMatrix;
          break;

          default: // everthing else, monster
            lMatrix = oParent.lMonsterMatrix;
          break;
        }

    return lMatrix;
  }
static  int GetToHitRating(int nClassIndex, int nLevel, int nHDMod) {
    int nReturnToHit = 20;
    int nHD = nLevel;

    if (nClassIndex > 3) { // creature type monster
      if (nLevel > 20)
        nReturnToHit = 20+2; // cap is 20
      if (nLevel >= 2)
        nReturnToHit = nLevel+2;
      else
      if (nHD < 1) // HD below 1
        nReturnToHit = 0;
      else
      if (nHD == 1 && nHDMod > 0) // HD 1+
        nReturnToHit = 3;
      else
      if (nHD == 1 && nHDMod == 0) // HD == 1
        nReturnToHit = 2;
      else
      if (nHD == 1 && nHDMod == -1) // HD 1-1
        nReturnToHit = 1;
      else
      if (nHD == 1 && nHDMod < -1) // HD <1-1
        nReturnToHit = 0;
      else
        nReturnToHit = 0;
    }
    else { // creature is a cleric/mage/thief/fighter type...
      switch (nClassIndex) {
        case 0: // fighter
          nReturnToHit = nLevel+1; // cause there is <0 and 0 level
          break;
        case 1: // thief/mage/cleric
        case 2:
        case 3:
          nReturnToHit = nLevel-1;
          break;
      }
    }

    return nReturnToHit;
  }



}