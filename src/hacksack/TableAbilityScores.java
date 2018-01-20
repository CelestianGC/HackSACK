package hacksack;

import java.io.*;
import java.util.regex.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableAbilityScores {
  String sTable = null;
  int nType = -1;
  int nAbilityScore = 0;
  int nAbilityPercent = 0;

  String[] sMod = new String[8];
  int nMaxMods = 0;

  int ABIL_STR = 0;
  int ABIL_DEX = 1;
  int ABIL_CON = 2;
  int ABIL_INT = 3;
  int ABIL_WIS = 4;
  int ABIL_CHA = 5;

  public TableAbilityScores() {
  }


  static TableAbilityScores FindAbilityTable(HackSackFrame oParent,int nType, int nScore, int nPercent)
  {
    TableAbilityScores oA = null;

    for (int i=0;i<oParent.lAbilityTables.size();i++)
    {
      TableAbilityScores oRecord = (TableAbilityScores)oParent.lAbilityTables.get(i);
      if ( oRecord.nType == nType &&
           nScore >= oRecord.nAbilityScore && // ability score greater than this record value?
           nPercent >= oRecord.nAbilityPercent )
      {
        oA = oRecord;
      }
    }

    return oA;
  }

  static void LoadAbilityTable(HackSackFrame oParent, String sFileName,String sTable, int nType)
  {
    sFileName = "tables" + File.separatorChar + sFileName;
    try {
      FileReader fFile = new FileReader(sFileName);
      BufferedReader fBuff = new BufferedReader(fFile);
      String sLine;
      int i = 0;
      while (null != (sLine = fBuff.readLine())) {
        i++;
        sLine.trim(); // trim whitespace
        Matcher pColonCheck = Pattern.compile(":").matcher(sLine);
        if (pColonCheck.find()) {
            String[] sFields = sLine.split(":"); // ##,##:##:##:##:##:etc
            if (sFields.length > 1)
            {
              TableAbilityScores oA = new TableAbilityScores();
              String[] sScores = sFields[0].split(","); // ##,##
              oA.sTable = sTable;
              oA.nType = nType;
              oA.nAbilityScore = Integer.parseInt(sScores[0]);
              if (sScores.length < 2)
                oA.nAbilityPercent = 0;
              else
                oA.nAbilityPercent = Integer.parseInt(sScores[1]);

              oA.nMaxMods = 0;
              for (int j=1;j<sFields.length;j++)
              {
                oA.nMaxMods++;
                oA.sMod[j-1] =
                    sFields[j];
              }
              oParent.lAbilityTables.add(oA);
            }
        }
        else
          oParent.DiceOutTextArea.append("Error with Table " + sTable + " at line " + i +
                                 ":" + sFileName + "\n");
      }
      fBuff.close();
      fFile.close();
    }
    catch (IOException e) {
      File oFile = new File("");
      sFileName = oFile.getAbsolutePath() + File.separatorChar + sFileName;
      oParent.DiceOutTextArea.append("Error opening Table " + sTable + " file:" +
                             sFileName + "\n");
    }
  }


}