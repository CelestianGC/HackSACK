package hacksack;
import java.io.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableSave {
  String sTable = null;
  int nLevel = -1;
  int nPara = 20;
  int nRod = 20;
  int nPetri = 20;
  int nBreath = 20;
  int nApology = 20;
  int nSpells = 20;

  public TableSave() {
  }

static  void LoadSaveTables(HackSackFrame oParent, String sFileName, String sTable) {
    sFileName = "tables" + File.separatorChar + sFileName;
    try {
      FileReader fFile = new FileReader(sFileName);
      BufferedReader fBuff = new BufferedReader(fFile);
      String sLine;
      int i = 0;
      while (null != (sLine = fBuff.readLine())) {
        i++;
        sLine.trim(); // trim whitespace
          if (!sLine.equals("")) {
            String[] sFields = sLine.split(":");
            if (sFields.length == 7) {
              TableSave oRecord = new TableSave();
              oRecord.sTable = sTable;
              oRecord.nLevel = Integer.parseInt(sFields[0]);
              oRecord.nPara = Integer.parseInt(sFields[1]);
              oRecord.nRod = Integer.parseInt(sFields[2]);
              oRecord.nPetri = Integer.parseInt(sFields[3]);
              oRecord.nBreath = Integer.parseInt(sFields[4]);
              oRecord.nApology = Integer.parseInt(sFields[5]);
              oRecord.nSpells = Integer.parseInt(sFields[6]);
              oParent.lSaveTables.add(oRecord);
            }
            else
              oParent.DiceOutTextArea.append("Error with Table " + sTable + " at line " + i +
                                     ":" + sFileName + "\n");
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
