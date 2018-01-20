package hacksack;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableBlock {
  public  int nRoll =-1;
  public String sText = null;
  public String sExtra = null;
  public String sTable = null;

  public TableBlock() {

  }


  //
  public TableBlock(int nThisRoll, String sThisText, String sThisTable, String sThisExtra) {
    this();
    this.nRoll= nThisRoll;
    this.sText = sThisText;
    this.sExtra = sThisExtra;
    this.sTable = sThisTable;
  }

  // return text from simple 1-100 sTable rolling d100
   static public String GetTableRecord(HackSackFrame oParent, String sTable, int nTableSize,
                                boolean bStaticRoll) {
     // if Static just use the TableSize sent and not random
     int nRoll = bStaticRoll ? nTableSize : oParent.MyRandom(nTableSize);
     String sThisText = ">GTR-ERROR-" + sTable + "(" + nTableSize + ")" + "[" +
         nRoll + "]<";

     // start at top of array and go down till you find a .nRoll that
     // our roll is > or == to.
     for (int i = 0; i < oParent.lTables.size(); i++) {
       TableBlock oThisRecord = (TableBlock) oParent.lTables.get(i);
       if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sTable)) {
         sThisText = oThisRecord.sText;
         return (sThisText);
       }
     }

     return (sThisText);
   }

   // return text from simple 1-100 sTable rolling d100
   static public String GetTableRecordExtra(HackSackFrame oParent, String sTable, int nTableSize,
                                     boolean bStaticRoll) {
     // if Static just use the TableSize sent and not random
     int nRoll = bStaticRoll ? nTableSize : oParent.MyRandom(nTableSize);
     String sThisText = ">GTR-ERROR-" + sTable + "(" + nTableSize + ")" + "[" +
         nRoll + "]<";

     // start at top of array and go down till you find a .nRoll that
     // our roll is > or == to.
     for (int i = 0; i < oParent.lTables.size(); i++) {
       TableBlock oThisRecord = (TableBlock) oParent.lTables.get(i);
       if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sTable)) {
         sThisText = oThisRecord.sExtra;
         return (sThisText);
       }
     }

     return (sThisText);
   }

   // insert table 7G results
   static public String Get7GTable(HackSackFrame oParent) {
     int nRoll = oParent.MyRandom(100); // d100
     String sThisText = "BOGUS-7G [" + nRoll + "]";
     // start at top of array and go down till you find a .nRoll that
     // our roll is > or == to.
     for (int i = 0; i < oParent.lTables.size(); i++) {
       TableBlock oThisSpellMishap = (TableBlock) oParent.lTables.get(i);
       if (nRoll <= oThisSpellMishap.nRoll &&
           oThisSpellMishap.sTable.equals("7G")) {
         sThisText = oThisSpellMishap.sText;
         // add 2 colors
         if (nRoll >= 86) {
           for (int k = 1; k <= 2; k++) {
             int nSecondRoll = oParent.MyRandom(85);
             for (int j = 0; j < oParent.lTables.size(); j++) {
               TableBlock oThisColor = (TableBlock) oParent.lTables.get(j);
               if (nSecondRoll <= oThisColor.nRoll &&
                   oThisColor.sTable.equals("7G")) {
                 sThisText += (" " + oThisColor.sText + oParent.GetColorShade());
                 break;
               }
             }
           }
         }
         else { // only add this if we were below 86
           sThisText += oParent.GetColorShade();
           // add in the dice roll for information..
//       sThisText += "["+nRoll+"]";

         }
         return (sThisText);
       }
     }
     return (sThisText);
   }

}
