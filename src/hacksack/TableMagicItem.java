package hacksack;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableMagicItem {
  public int nRoll;
  public String sName = null;
  public int nEXP;
  public int nGold;
  public String sPage = null;
  public String sTable = null;

  public TableMagicItem() {

  }

  public TableMagicItem(int nThisRoll, String sThisTable) {
    this();
    this.nRoll = nThisRoll;
    this.sTable = sThisTable;
  }

  // magic items
  static void LoadTableMagicItems(HackSackFrame oParent, String sFileName, String sTable) {
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
          if (sFields.length >= 4) {
            String sTemp = (sFields.length < 3 ? "" : sFields[2]);
            TableMagicItem oRecord = new TableMagicItem(Integer.parseInt(
                sFields[0]), sTable);
            oRecord.sName = sFields[1];
            oRecord.nEXP = Integer.parseInt(sFields[2]);
            oRecord.nGold = Integer.parseInt(sFields[3]);
            if (sFields.length >= 5) {
              oRecord.sPage = sFields[4];
            }
            oParent.lTableMagicItems.add(oRecord);
          }
          else {
            oParent.DiceOutTextArea.append("Error parsing Table " + sTable +
                                   " at line " + i + ":" + sFileName + "\n");
          }
        }
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
