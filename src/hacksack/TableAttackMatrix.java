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

public class TableAttackMatrix {
  // -20 .. 15
  int nAC;
  // level 1..21
  int[] nTh = new int[35];

  public TableAttackMatrix() {
  }

  static void LoadAttackMatrix(HackSackFrame oParent, String sFileName,String sTable, ArrayList aMatrix)
    {
      sFileName = "tables" + File.separatorChar + sFileName;
      try {
        FileReader fFile = new FileReader(sFileName);
        BufferedReader fBuff = new BufferedReader(fFile);
        String sLine;
        int i = 0;
        while (null != (sLine = fBuff.readLine())) {
          i++;
          sLine = sLine.replaceAll("\\s","");
         if (!sLine.matches("^#.*?")) {
            Matcher pColonCheck = Pattern.compile("(.*?):").matcher(sLine);
            if (pColonCheck.find()) {
              int nAC = Integer.parseInt(pColonCheck.group(1));
              sLine = pColonCheck.replaceAll("");
              TableAttackMatrix oM = new TableAttackMatrix();
              oM.nAC = nAC;
//oParent.DiceOutTextArea.append(sTable + " = " + oM.nAC + "\n");
              Matcher pCommaCheck = Pattern.compile(",").matcher(sLine);
              if (pCommaCheck.find()) {
                String[] sTh = sLine.split(",");

          for (int ii = 0; ii < sTh.length-1; ii++) {
                  oM.nTh[ii] = Integer.parseInt(sTh[ii]);
//oParent.DiceOutTextArea.append(ii+">" + oM.nTh[ii] + "\n");
                }
                aMatrix.add(oM);
              }
              else
                oParent.DiceOutTextArea.append("Error with Table " + sTable +
                                               " at line (,)" + i +
                                               ":" + sFileName + "\n");
            }
            else
              oParent.DiceOutTextArea.append("Error with Table " + sTable +
                                             " at line (:)" + i +
                                             ":" + sFileName + "\n");
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