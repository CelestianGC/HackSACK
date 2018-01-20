package hacksack;
import java.io.*;
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

public class TableTurnUndead {
  String sNameType = null;
  int[] nTurnRoll = new int[12];
  // -1 == cannot turn this type at this level
  //  0 == auto turn
  // -10 == Destroy
  // -20 == Destroy +2d4 more

  public TableTurnUndead() {
  }

  static void LoadTurnTable(HackSackFrame oParent, String sFileName,String sTable)
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
             String[] sFields = sLine.split(":"); // ##:##:##:##:##:etc
             if (sFields.length == 13)
             {
               TableTurnUndead oT = new TableTurnUndead();
               oT.sNameType = sFields[0];
               for (int j=1;j<sFields.length;j++)
               {
                 oT.nTurnRoll[j-1] = Integer.parseInt(sFields[j]);
               }
               oParent.lTableTurn.add(oT);
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

   static void TurnUndead(HackSackFrame oParent, int nRoll, int nLevel, int nTurnTypeIndex)
   {
     int nAmountTurned = 0;
     TableTurnUndead oT = (TableTurnUndead)oParent.lTableTurn.get(nTurnTypeIndex);
     int nTurnRoll = -1;
     if (nLevel <= 9)
       nTurnRoll = oT.nTurnRoll[nLevel - 1];
     else if (nLevel == 10 || nLevel == 11)
       nTurnRoll = oT.nTurnRoll[9];
     else if (nLevel == 12 || nLevel == 13)
       nTurnRoll = oT.nTurnRoll[10];
     else if (nLevel > 13)
         nTurnRoll =  oT.nTurnRoll[11];

       if (nTurnRoll == -1)
         nAmountTurned = 0;
       else // T or D
       if (nTurnRoll == 0 || nTurnRoll == -10) // 2d4 turned
         nAmountTurned = oParent.MyRandom(4) + oParent.MyRandom(4);
       else // D*
       if (nTurnRoll == -20) // 4d4 turned
         nAmountTurned = oParent.MyRandom(4) + oParent.MyRandom(4) +
             oParent.MyRandom(4) + oParent.MyRandom(4);
       else // else we turn d4
         nAmountTurned = oParent.MyRandom(4);


//         int nRoll = oParent.MyRandom(20);
         String sTurnResult = "";

         if (nTurnRoll == -1) {
           sTurnResult = "Failed turning "+oT.sNameType+"!!";
         }
         else
         if (nTurnRoll == -10) {
           sTurnResult = "Destroyed "+nAmountTurned+" "+oT.sNameType;
         }
         else
         if (nTurnRoll == -20) {
           sTurnResult = "Destroyed "+nAmountTurned+" "+oT.sNameType+"!";
         }
         else // auto turn or we actually made our dice roll
         if (nTurnRoll == 0 || nRoll >= nTurnRoll) {
           sTurnResult = "Turned "+nAmountTurned+" "+oT.sNameType;
         }
         else
           sTurnResult = "Failed turning "+oT.sNameType+"!";

         oParent.gmLog(true,false,"Turn Undead Attempt:"+sTurnResult+"\n");
         oParent.gmLog(false,true,"Cleric Level:"+nLevel+",Turn Roll Required:"+nTurnRoll+
                       ", Dice roll:"+nRoll+", Amount turned roll:"+nAmountTurned+"");

         oParent.jTurnUndeadTextArea.append(sTurnResult+"\n");
         oParent.jTurnUndeadTextArea.append("------------------------------------------------------\n");

/*         DialogDetails dlg = new DialogDetails(sTurnResult,"Turn Attempt");
         Dimension dlgSize = dlg.getPreferredSize();
         Dimension frmSize = oParent.getSize();
         Point loc = oParent.getLocation();
         dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                         (frmSize.height - dlgSize.height) / 2 + loc.y);
         dlg.setModal(true);
         dlg.pack();
         dlg.show();
*/

   }
}
