package hacksack;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.tree.*;

import java.awt.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableEncum {

  int nStrScore = 0;
  int nPercent = 0;
  int[] nMaxWT = new int[5];

  public TableEncum() {
  }

static void setEncumLabel(ArrayList lEncTable, TablePlayer oPlayer)
{
  String[] sEncType = {"none","light","moderate","laden","severe/max"};
  String sEncValue = "EMPTY";
  double dWeightCarried = oPlayer.getWeightCarried();
  double dWeightUnCarried = oPlayer.getWeightNotCarried();

  if (oPlayer.aAbilities.size() > 0) { // loading up this belches otherwise
  Abilities oStrengthScore = (Abilities) oPlayer.aAbilities.get(0);
  int nStr = Integer.parseInt(oStrengthScore.jAdjScore.getModel().getValue().toString());
  int nStrP = Integer.parseInt(oStrengthScore.jAdjPercent.getModel().getValue().toString());

  int nCurrentMove = Integer.parseInt(oPlayer.jMove.getValue().toString());
  int nMoveRateAdjusted = nCurrentMove;

  if (lEncTable.size() > 0) {
    for (int i = 0; i < lEncTable.size(); i++) {
      TableEncum oE = (TableEncum) lEncTable.get(i);
      if (oE.nStrScore == nStr && nStrP >= oE.nPercent) {
        if (dWeightCarried <= oE.nMaxWT[0]) {
          sEncValue = sEncType[0];
          oPlayer.jMoveAdjustedLabel.setText("[" +
                                             oPlayer.jMove.getValue().toString() +
                                             "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Unencumbered move rate.");
          oPlayer.jEncumValue.setToolTipText("Unencumbered");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.PLAIN, 9));
          oPlayer.jEncumValue.setForeground(Color.GREEN);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.PLAIN,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.GREEN);
        }
        else
        if (dWeightCarried > oE.nMaxWT[0] &&
            dWeightCarried <= oE.nMaxWT[1]) {
          sEncValue = sEncType[1];
          // the +0.5 is to force round up since we drop .000's
          nMoveRateAdjusted = (nCurrentMove -
                               (int) ( ( (nCurrentMove * (0.333333)) + 0.5)));
          oPlayer.jMoveAdjustedLabel.setText("[" + nMoveRateAdjusted + "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Lightly Encumbered, 1/3 move");
          oPlayer.jEncumValue.setToolTipText("Lightly Encumbered, 1/3 move");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.BOLD, 12));
          oPlayer.jEncumValue.setForeground(Color.WHITE);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.BOLD,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.WHITE);
        }
        else
        if (dWeightCarried > oE.nMaxWT[1] &&
            dWeightCarried <= oE.nMaxWT[2]) {
          sEncValue = sEncType[2];
          nMoveRateAdjusted = (nCurrentMove -
                               (int) ( (nCurrentMove * (0.5) + 0.5)));
          oPlayer.jMoveAdjustedLabel.setText("[" + nMoveRateAdjusted + "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Moderately Encumbered, 1/2 move, -1 to hit");
          oPlayer.jEncumValue.setToolTipText(
              "Moderately Encumbered, 1/2 move, -1 to hit");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.BOLD, 12));
          oPlayer.jEncumValue.setForeground(Color.YELLOW);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.BOLD,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.YELLOW);
        }
        else
        if (dWeightCarried > oE.nMaxWT[2] &&
            dWeightCarried <= oE.nMaxWT[3]) {
          sEncValue = sEncType[3];
          nMoveRateAdjusted = (nCurrentMove -
                               (int) ( (nCurrentMove * (0.666667) + 0.5)));
          oPlayer.jMoveAdjustedLabel.setText("[" + nMoveRateAdjusted + "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Heavily Laden, 2/3 move, -2 to hit, worsen AC by +1");
          oPlayer.jEncumValue.setToolTipText(
              "Heavily Laden, 2/3 move, -2 to hit, worsen AC by +1");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.BOLD, 12));
          oPlayer.jEncumValue.setForeground(Color.RED);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.BOLD,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.RED);
        }
        else
        if (dWeightCarried > oE.nMaxWT[3] &&
            dWeightCarried <= oE.nMaxWT[4]) {
          sEncValue = sEncType[4];
          nMoveRateAdjusted = 1;
          oPlayer.jMoveAdjustedLabel.setText("[" + nMoveRateAdjusted + "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Severly Encumbered,move base 1, -4 to hit, worsen AC by +3");
          oPlayer.jEncumValue.setToolTipText(
              "Severly Encumbered,move base 1, -4 to hit, worsen AC by +3");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.BOLD, 12));
          oPlayer.jEncumValue.setForeground(Color.RED);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.BOLD,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.RED);
        }
        else {
          sEncValue = "OVERLOADED";
          nMoveRateAdjusted = 0;
          oPlayer.jMoveAdjustedLabel.setText("[" + nMoveRateAdjusted + "]");
          oPlayer.jMoveAdjustedLabel.setToolTipText(
              "Encumbered over MAXIMUM, Cannot Move");
          oPlayer.jEncumValue.setToolTipText(
              "Encumbered over MAXIMUM, Cannot Move");
          oPlayer.jEncumValue.setFont(new Font("Dialog", Font.BOLD, 12));
          oPlayer.jEncumValue.setForeground(Color.RED);
          oPlayer.jMoveAdjustedLabel.setFont(new Font("Dialog", Font.BOLD,
              12));
          oPlayer.jMoveAdjustedLabel.setForeground(Color.RED);
        }
      }
    }
  }

  }

  // set the label up now with proper font
  NumberFormat fNumberFormat = NumberFormat.getInstance();
  fNumberFormat.setMaximumFractionDigits(2);
  String sWeightCarried = fNumberFormat.format(dWeightCarried);
  String sWeightUnCarried = fNumberFormat.format(dWeightUnCarried);

  oPlayer.jEncumValue.setText("Total Carried Wt "+
                                  sWeightCarried+
                                  ", Encumbrance "+sEncValue+
                                  ", Stored Wt "+sWeightUnCarried+"");
}

  static void LoadEncumTable(HackSackFrame oParent, String sFileName)
    {
      sFileName = "tables" + File.separatorChar + sFileName;
      try {
        FileReader fFile = new FileReader(sFileName);
        BufferedReader fBuff = new BufferedReader(fFile);
        String sLine;
        int i = 0;
        while (null != (sLine = fBuff.readLine())) {
          i++; // line counter for debug output
          sLine.trim(); // trim whitespace
          Matcher pColonCheck = Pattern.compile(":").matcher(sLine);
          if (pColonCheck.find()) {
              String[] sFields = sLine.split(":"); // ##,##:##:##:##:##:etc
              if (sFields.length > 1)
              {
                TableEncum oA = new TableEncum();
                String[] sScores = sFields[0].split(","); // ##,##
                oA.nStrScore = Integer.parseInt(sScores[0]);
                if (sScores.length < 2)
                  oA.nPercent = 0;
                else
                  oA.nPercent = Integer.parseInt(sScores[1]);

                for (int j=1;j<sFields.length;j++)
                  oA.nMaxWT[j-1] = Integer.parseInt(sFields[j]);

                oParent.lEncumTable.add(oA);
              }
          }
          else
            oParent.DiceOutTextArea.append("Error with Table " +"Encum9Y" + " at line " + i +
                                   ":" + sFileName + "\n");
        }
        fBuff.close();
        fFile.close();
      }
      catch (IOException e) {
        File oFile = new File("");
        sFileName = oFile.getAbsolutePath() + File.separatorChar + sFileName;
        oParent.DiceOutTextArea.append("Error opening Table " + "Encum9Y" + " file:" +
                               sFileName + "\n");
      }
    }

}
