package hacksack;

import java.io.*;

import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableCritBody {
  public  int nRoll =-1;
  public String sTable = null;
  public String sLocation = null;
  public String[] sEffect;

  public TableCritBody() {
    sEffect = new String [8];

  }


    //
    public TableCritBody(String sThisTable, JTextArea jErrorOut,int nThisRoll, String sThisLocation,String sThisFields) {
      this();
      this.nRoll= nThisRoll;
      this.sLocation = sThisLocation;
      this.sTable = sThisTable;

      String[] sFields = sThisFields.split(":");

      for (int j=0;j<sFields.length;j++) {
       if (sFields[j].length()>0) {
         this.sEffect[j] = sFields[j];
//jErrorOut.append(sThisLocation+": ("+j+")"+this.sEffect[j]+"\n");
       }
      }

    }

    // load CritTableBody into lTableCritBody
    static void LoadCritTableBody(HackSackFrame oParent,String sFileName, String sTable, int nPads) {
      sFileName = "tables" + File.separatorChar + sFileName;

      try {
        FileReader fFile = new FileReader(sFileName);
        BufferedReader fBuff = new BufferedReader(fFile);
        String sLine;
        int i = 0;
        while (null != (sLine = fBuff.readLine())) {
          i++;
          sLine.trim(); // trim whitespace
          String[] sFields = sLine.split(":", nPads + 1);
          if (sFields[0] != null && sFields[1] != null) {
            TableCritBody oTableCritRecord = new TableCritBody(sTable,
                oParent.DiceOutTextArea,
                Integer.parseInt(sFields[0]), sFields[1], sFields[2]);
            oParent.lTableCritBody.add(oTableCritRecord);
          }
        }
        fBuff.close();
        fFile.close();
      }
      catch (IOException e) {
        oParent.DiceOutTextArea.append("Error opening " + sFileName + "\n");
      }
    }

    static   public String GetTableBody(HackSackFrame oParent, int nRoll, String sTable, int nIndex) {
        String sThisText = "";
        nIndex--; // reduce it by 1 to properly get the record (0,1,2,3etc..)
        if (nIndex < 0) {
          sThisText = "\n\n<ind1=" + nIndex + ",for roll " + nRoll + ">\n\n";
          nIndex = 0;
        }
        else {

          // start at top of array and go down till you find a .nRoll that
          // our roll is > or == to.
          for (int i = 0; i < oParent.lTableCritBody.size(); i++) {
            TableCritBody oThisRecord = (TableCritBody) oParent.lTableCritBody.get(i);
            if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sTable)) {
              if (oThisRecord.sEffect[nIndex] == null) {
                sThisText = "\n\n<ind2=" + nIndex + ",for roll " + nRoll + ">\n\n";
              }
              else {
                sThisText = oThisRecord.sEffect[nIndex];

              }
              return (sThisText);
            }
          }
        }

        return (sThisText);
      }


// just return a string of where the attack hit for flavor
//static String critGetLocation(HackSackFrame oParent, int nTargetSize, int nAttackerSize, int nRoll)
      static CritLocation critGetLocation(HackSackFrame oParent, int nTargetSize,
                                   int nAttackerSize, int nRoll) {
        CritLocation oCritLoc = null;
        if (nRoll == 0) {
          String sCritDiceSize = TableCritBody.GetTableBody(oParent, nTargetSize,
              "8EE",
              (nAttackerSize + 1));
          // parse out the dice to roll from table
          sCritDiceSize = oParent.DiceParse(sCritDiceSize);

          int nCritDiceSize = Integer.parseInt(sCritDiceSize);
          nRoll = oParent.MyRandom(nCritDiceSize);
        }

        for (int j = 0; j < oParent.lTableCrit.size() && oCritLoc == null; j++) {
          TableCrit oCritRecord = (TableCrit) oParent.lTableCrit.get(j);
          if (nRoll <= oCritRecord.nRoll) {
            oCritLoc = new CritLocation();
            oCritLoc.sLocation =
                "(" + ( (oParent.MyRandom(2) > 1) ? "right" : "left") +") " + oCritRecord.sLocation;
            oCritLoc.dMaxDamageAtLocation = oCritRecord.dMaxDamageAtLocation;
//        sLocation = "(" + ( (oParent.MyRandom(2) > 1) ? "right" : "left") +
//            ") " + oCritRecord.sLocation;
          }
        }

        return oCritLoc;
      }


  }

  // used to hold location and max damage location can take
  class CritLocation {
    String sLocation = null;
    double dMaxDamageAtLocation = 0.0;

    public CritLocation() {

    }
  }



