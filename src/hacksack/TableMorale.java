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

public class TableMorale {
  public String sText = null;
  public JSpinner jNumber = null;
  public JLabel jNumLabel = null;
  public JCheckBox jSelect = null;

  public int nModifier = 0;

  public TableMorale() {
//    SpinnerNumberModel mMorale = new SpinnerNumberModel(10,1,100,1);
//    jMorale = new JSpinner(mMorale);

//    jSelect = new JCheckBox(this.sText);
  }

  public TableMorale(String sThisText) {
    this();
    this.sText = sThisText;

  }

  static   // load up Trasure tables into lTableTreasures
    void LoadTableMorale(HackSackFrame oParent, String sFileName, String sTable) {
      sFileName = "tables" + File.separatorChar + sFileName;
      try {
        FileReader fFile = new FileReader(sFileName);
        BufferedReader fBuff = new BufferedReader(fFile);
        String sLine;
        int i = 0;
        while (null != (sLine = fBuff.readLine())) {
          i++;
          sLine.trim(); // trim whitespace
          String[] sFields = sLine.split(":");
          if (sFields.length >= 3) {
            TableMorale oRecord = new TableMorale(sFields[0]);
            if (sFields[2].equalsIgnoreCase("B")) {
              oRecord.jSelect = new JCheckBox(oRecord.sText);
              oRecord.nModifier = Integer.parseInt(sFields[1]);
            }
            else
            if (sFields[2].equals("#")) {
              SpinnerNumberModel mModel = new SpinnerNumberModel(0, 0, 1000, 1);
              oRecord.jNumber = new JSpinner(mModel);
              oRecord.jNumLabel = new JLabel(oRecord.sText);
              oRecord.nModifier = Integer.parseInt(sFields[1]);
            }
            oParent.lTableMorale.add(oRecord);
          }
          else {
            oParent.DiceOutTextArea.append("Error at line " + i + "with " + sFileName +
                                   "\n");
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
