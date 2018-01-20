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

public class TableTreasure {
  public  char cType;
  public String sTable = null;
  public String[] sReward;
  public int[] nChance;

  public TableTreasure() {
    sReward = new String [10];
    nChance = new int [10];
  }


    //
    public TableTreasure(String sThisTable,JTextArea jErrorOut, char cThisType,String sThisFields) {
      this();
      this.cType = cThisType; // treasure type
      this.sTable = sThisTable; // table name

        String[] sFields = sThisFields.split(":");
        // should be 8 entries
        for (int j = 0; j < sFields.length; j++) {
          if (sFields[j].length() > 0) {
            // Change,Reward (55,{1d6}
            String[] sList = sFields[j].split(",");
            this.nChance[j] = Integer.parseInt(sList[0]);
            this.sReward[j] = sList[1];
//jErrorOut.append(this.cType+": ("+j+")"+this.nChance[j]+"["+this.sReward[j]+"]\n");
          }
//          else { // no chance, no rewards
//            this.nChance[j] = -1;
//            this.sReward[j] = "";
//jErrorOut.append(this.cType+": ("+j+")"+((this.sReward[j]!=null)?"NOTNULL":"NULL!!!")+"["+this.nChance[j]+"]\n");
//          }
        }

    }

    static  // load up Trasure tables into lTableTreasures
      void LoadTableTreasure(HackSackFrame oParent, String sFileName, String sTable) {
        sFileName = "tables" + File.separatorChar + sFileName;
        int nPads = 1; // number of blocks in front of data
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
              TableTreasure oRecord = new TableTreasure(sTable, oParent.DiceOutTextArea,
                  sFields[0].charAt(0), sFields[1]);
              oParent.lTableTreasures.add(oRecord);
            }
          }
          fBuff.close();
          fFile.close();
        }
        catch (IOException e) {
          oParent.DiceOutTextArea.append("Error opening " + sFileName + "\n");
        }
      }

  }

