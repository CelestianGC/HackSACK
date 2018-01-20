package hacksack;
import java.text.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import com.borland.jbcl.layout.*;
import java.awt.FlowLayout;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
* This section contains the "Check" items in HackSACK
* like check Suprise, Morale etc...
 */

public class checkSpecial {
  public checkSpecial() {
  }

static void checkSuprise(HackSackFrame oParent) {
    int nSupriseRange = Integer.parseInt(oParent.jSupriseRangeSpinner.getValue().toString());
    int nSupriseMod = Integer.parseInt(oParent.jSupriseModSpinner.getValue().toString());
    int nSupriseDiceSize = Integer.parseInt(oParent.jSupriseDiceSizeSpinner.getValue().toString());

    int nSpecificRoll = Integer.parseInt(oParent.jSupriseSpecificSpinner.getValue().toString());
    oParent.jSupriseSpecificSpinner.setValue(new Integer(0)); // reset to 0 since we got it
    if (nSpecificRoll > nSupriseDiceSize) // if specific > max size...
      nSpecificRoll = nSupriseDiceSize;

    // if -4 suprise mod, range is now range+4 so 1-7 for normal folks
    if (nSupriseMod != 0) {
      nSupriseRange += (-1*nSupriseMod);
    }
    // sanity checks
    if (nSupriseRange < 1)
      nSupriseRange = 1;
    if (nSupriseRange > nSupriseDiceSize)
      nSupriseRange = nSupriseDiceSize;

    int nSupriseDuration = 0;
    Dicer dDice = new Dicer();
    // if we have specific roll use it, otherwise roll
    int nRoll = (nSpecificRoll>0?nSpecificRoll:
                 dDice.Random(nSupriseDiceSize));

    if (nRoll <= nSupriseRange) {
      nSupriseDuration = ((nSupriseRange+1)-nRoll);
    }

    if (nSupriseDuration > 0)
      oParent.jSupriseTextArea.append("*SUPRISE SUPRISE!*\nSuprised for "+nSupriseDuration+" segments!\n"+
               "(suprise range 1-"+nSupriseRange+" on d"+nSupriseDiceSize+", rolled "+nRoll+")\n");
      else
      oParent.jSupriseTextArea.append("Not suprised.\n"+
               "(suprise range 1-"+nSupriseRange+" on d"+nSupriseDiceSize+", rolled "+nRoll+")\n");

    oParent.jSupriseTextArea.append("------------------------------------------------------\n");

}
  static void checkMorale(HackSackFrame oParent) {
    int nBaseMorale = Integer.parseInt(oParent.mGMMoraleRating.getNumber().toString());
    nBaseMorale *= 5;
    int nMoraleAdjustment = 0;

    for (int i = 0; i < oParent.lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) oParent.lTableMorale.get(i);
      if (oThis.jSelect != null) {
        if (oThis.jSelect.isSelected()) {
          nMoraleAdjustment += oThis.nModifier;
        }
      }
    }

    for (int i = 0; i < oParent.lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) oParent.lTableMorale.get(i);

      if (oThis.jNumber != null) {
        int nNumber = Integer.parseInt(oThis.jNumber.getModel().getValue().
                                       toString());
        if (nNumber != 0) {
          nMoraleAdjustment += (oThis.nModifier * nNumber);

        }
      }
    }

    int nRoll = oParent.MyRandom(100);
    nRoll += nMoraleAdjustment;
    String sCheck = null;
    String sResponse = null;

    if (nRoll > nBaseMorale) {
      int nFailedBy = (nRoll - nBaseMorale);
      String sFailedAction = TableBlock.GetTableRecord(oParent,"8BB", nFailedBy, true);
      sCheck = "Failed morale check.\n";
      sResponse = "Response to failure:" + sFailedAction + "\n";

      oParent.gmLog(true, false, sCheck);
      oParent.gmLog(false, false, sResponse);

      oParent.gmLog(false, true,
            "Roll (" + nRoll + ") Morale Rating (" + nBaseMorale +
            ") Failed by [" +
            nFailedBy + "] Adjustment was " + nMoraleAdjustment + "\n");
    }
    else {
      sCheck = "Succeded morale check.\n";
      oParent.gmLog(true, false, sCheck);
      oParent.gmLog(false, true,
            "Roll (" + nRoll + ") Morale Rating (" + nBaseMorale +
            ") Adjustment was " +
            nMoraleAdjustment + "\n");
    }

    oParent.jMoraleTextArea.append(sCheck+(sResponse!=null?sResponse:"")+"\n");
    oParent.jMoraleTextArea.append("----------------------------------\n");
    // popup dialog
/*    DialogDetails dlg = new DialogDetails(sCheck +
                                          (sResponse != null ? sResponse : ""),
                                          "Morale Check");
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

  // check to see if someone detected the pick pocket.
  static void checkPickpocketDetect(HackSackFrame oParent) {
    int nLevel = Integer.parseInt(oParent.jPickPocketDetectTargetLevelSpinner1.getValue().toString());
    int nWis = Integer.parseInt(oParent.jPickPocketDetectTargetWisdomSpinner1.getValue().toString());
    int nPPRoll = Integer.parseInt(oParent.jPickPocketDetectThiefRollSpinner1.getValue().toString());
    int nTotalChance = 100-((nLevel*2)+nWis);

    if (nPPRoll >= nTotalChance) {
      oParent.jPickPocketDetectTextArea1.append("Detected! GUARDS!\n"+
                                        "Lvl:"+nLevel+" Wis:"+nWis+" PP:"+nPPRoll+" Detected on Pickpocket  roll+:"+nTotalChance+"\n");
    } else {
      oParent.jPickPocketDetectTextArea1.append("Thief remains undetected.\n"+
                                        "Lvl:"+nLevel+" Wis:"+nWis+" PP:"+nPPRoll+" Detected on Pickpocket roll+:"+nTotalChance+"\n");
    }
    oParent.jPickPocketDetectTextArea1.append("------------------------------------------------------\n");

  }
}

/**
 *
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003,2004</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */
class mergeCoin {

  JSpinner[] mergeCoins;
  JRadioButton[] mergeCoinRadio;
  JLabel mergeCoinLabel = null;

  JButton mergeResetButton = null;

  /**
   *
   */
  int COIN_COPPER =     0;
  int COIN_SILVER =     1;
  int COIN_ELECTRUM =   2;
  int COIN_GOLD =       3;
  int COIN_HARDSILVER = 4;
  int COIN_PLATINUM =   5;

  String[] sCoinTypes =
      {"Copper","Silver","Electrum",
      "Gold","Hard Silver","Platinum"};

  /**
   *
   */
  public mergeCoin() {

    mergeCoinLabel = new JLabel("");
    mergeCoins =  new JSpinner[6];
    mergeCoinRadio =  new JRadioButton[6];
    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      mergeCoins[i] = new JSpinner();
      mergeCoinRadio[i] = new JRadioButton();
    }

    mergeResetButton = new JButton("Reset");
    mergeResetButton.addActionListener(new ResetButton_actionAdapter(this));
  }

  /**
   * returns a display panel listing coins, selections for convertion
   *
   * @return JPanel
   */
  JPanel getDisplayPanel() {
    //VerticalFlowLayout vFlow = new VerticalFlowLayout();
	FlowLayout vFlow = new FlowLayout();
    vFlow.setHgap(5);
    vFlow.setVgap(1);
    JPanel jTop = new JPanel(vFlow);
    jTop.setBackground(Color.GRAY);
    JTextArea jHowTo = new JTextArea();
    jHowTo.setWrapStyleWord(true);
    jHowTo.setLineWrap(true);
    jHowTo.setEditable(false);
    jHowTo.setBackground(Color.GRAY);
    jHowTo.setFont(new Font("Dialog",Font.ITALIC,11));
    jHowTo.append("Type in the amount of coins in the proper sections and then "+
                  "select the coin you wish to convert them to.\nMaximum value "+
                  "for each coin type is 99,999,999");
    jTop.add(jHowTo);

    /**
     * put all coins in panel
     */
    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      JPanel jCoinPanel = new JPanel(new BorderLayout());
      jCoinPanel.setBackground(Color.lightGray);

      mergeCoins[i].addChangeListener(new mergeCoinsSpinner_changeAdapter(this,i));
      mergeCoins[i].setModel(new SpinnerNumberModel(0,0,99999999,1));

      mergeCoinRadio[i].addActionListener(new RadioButton_actionAdapter(this,i));
      mergeCoinRadio[i].setText(sCoinTypes[i]);
      mergeCoinRadio[i].setFont(new Font("Dialog",Font.PLAIN,11));
      mergeCoinRadio[i].setBackground(Color.lightGray);


      jCoinPanel.add(mergeCoinRadio[i],BorderLayout.WEST);
      jCoinPanel.add(mergeCoins[i],BorderLayout.EAST);
      jTop.add(jCoinPanel);
    }
    /**
     * now add label
     */
    JPanel jCoinPanel = new JPanel(new BorderLayout());
    jCoinPanel.setBackground(Color.darkGray);
    mergeCoinLabel.setForeground(Color.YELLOW);
    mergeCoinLabel.setFont(new Font("Dialog",Font.BOLD,11));
    mergeCoinLabel.setText("Select Coin Type");
    jCoinPanel.add(mergeCoinLabel,BorderLayout.CENTER);
    jTop.add(jCoinPanel);

    mergeResetButton.setFont(new Font("Dialog",Font.PLAIN,11));
    jTop.add(mergeResetButton);

    return jTop;
  }

  /**
   * this is run when a radio/coin button is selected
   *
   * @param e ActionEvent
   * @param nMe int
   */
  void mergeCoins_actionPerformed(ActionEvent e, int nMe) {

    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      if (i != nMe)
        mergeCoinRadio[i].setSelected(false);
        else
        mergeCoinRadio[i].setSelected(true);
    }
    mergeCoinsSpinner_stateChanged(null,nMe);
  }

  /**
   * this is run when the coin values are changed
   *
   * @param e ChangeEvent
   * @param nMe int
   */
  void mergeCoinsSpinner_stateChanged(ChangeEvent e, int nMe) {
    double[] dCP_VALUE ={1,0.1,0.02,0.01,0.005,0.002};
    int nCount[] = new int[6];
    int nSelected = -1; // the coin type selected

    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      // get coin count
      nCount[i] = Integer.parseInt(mergeCoins[i].getValue().toString());
      // set the selected/convert to this/coin type
      if (mergeCoinRadio[i].isSelected())
        nSelected = i;
    }

    double  dTotal = 0;
    String sLabel = sCoinTypes[nSelected]+" Value: ";

    /**
     * nSelected == Convert Coin to This Type
     *
     * if nSelected is the current currency type then use it's value
     * if not then divide the number of coins, by the coins cp value for itself
     * then multiple that number by the cp value of the convertion type
     *
     * 300 silver converted to 60 Electrum
     * formula = (300/(1/10))*(1/50)
     *
     * 300/(1/10) = 3000
     * 3000*(1/50) = 60
     *
     */

    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      dTotal +=  (nSelected==i?nCount[i]:
                 nCount[i]/dCP_VALUE[i]*dCP_VALUE[nSelected]);
    }

    NumberFormat fNumberFormat = NumberFormat.getInstance();
    fNumberFormat.setMaximumFractionDigits(2);
    String sTotal = fNumberFormat.format(dTotal);

    mergeCoinLabel.setText(sLabel+sTotal);

    /*    // old methods

 // this was one
         dTotal =  (nSelected==COIN_COPPER?nCP:
                    nCP/dCP_VALUE[COIN_COPPER]*dCP_VALUE[nSelected]);
         dTotal += (nSelected==COIN_SILVER?nSP:
                    nSP/dCP_VALUE[COIN_SILVER]*dCP_VALUE[nSelected]);
         dTotal += (nSelected==COIN_ELECTRUM?nEP:
                    nEP/dCP_VALUE[COIN_ELECTRUM]*dCP_VALUE[nSelected]);
         dTotal += (nSelected==COIN_GOLD?nGP:
                    nGP/dCP_VALUE[COIN_GOLD]*dCP_VALUE[nSelected]);
         dTotal += (nSelected==COIN_HARDSILVER?nHS:
                    nHS/dCP_VALUE[COIN_HARDSILVER]*dCP_VALUE[nSelected]);
         dTotal += (nSelected==COIN_PLATINUM?nPP:
                    nPP/dCP_VALUE[COIN_PLATINUM]*dCP_VALUE[nSelected]);

// this was the first
          switch (nSelected) {
          // copper
          case 0:
            dTotal = nCP;
            dTotal += nSP/dCP_VALUE[COIN_SILVER];
            dTotal += nEP/dCP_VALUE[COIN_ELECTRUM];
            dTotal += nGP/dCP_VALUE[COIN_GOLD];
            dTotal += nHS/dCP_VALUE[COIN_HARDSILVER];
            dTotal += nPP/dCP_VALUE[COIN_PLATINUM];
            sLabel = "Copper Value: ";
            break;
          // silver
          case 1:
            dTotal = nCP*dCP_VALUE[COIN_SILVER];
            dTotal += nSP;
            dTotal += nEP/dCP_VALUE[COIN_ELECTRUM]*dCP_VALUE[COIN_SILVER];
            dTotal += nGP/dCP_VALUE[COIN_GOLD]*dCP_VALUE[COIN_SILVER];
            dTotal += nHS/dCP_VALUE[COIN_HARDSILVER]*dCP_VALUE[COIN_SILVER];
            dTotal += nPP/dCP_VALUE[COIN_PLATINUM]*dCP_VALUE[COIN_SILVER];
            sLabel = "Silver Value: ";
            break;

          // electrum
          case 2:
            dTotal =  nCP*dCP_VALUE[COIN_ELECTRUM];
            dTotal += nSP/dCP_VALUE[COIN_SILVER]*dCP_VALUE[COIN_ELECTRUM];
            dTotal += nEP;
            dTotal += nGP/dCP_VALUE[COIN_GOLD]*dCP_VALUE[COIN_ELECTRUM];
            dTotal += nHS/dCP_VALUE[COIN_HARDSILVER]*dCP_VALUE[COIN_ELECTRUM];
            dTotal += nPP/dCP_VALUE[COIN_PLATINUM]*dCP_VALUE[COIN_ELECTRUM];
            sLabel = "Electrum Value: ";
            break;

          // gold
          case 3:
            dTotal =  nCP*dCP_VALUE[COIN_GOLD];
            dTotal += nSP/dCP_VALUE[COIN_SILVER]*dCP_VALUE[COIN_GOLD];
            dTotal += nEP/dCP_VALUE[COIN_ELECTRUM]*dCP_VALUE[COIN_GOLD];
            dTotal += nGP;
            dTotal += nHS/dCP_VALUE[COIN_HARDSILVER]*dCP_VALUE[COIN_GOLD];
            dTotal += nPP/dCP_VALUE[COIN_PLATINUM]*dCP_VALUE[COIN_GOLD];
            sLabel = "Gold Value: ";
            break;

          // hard silver
          case 4:
            dTotal =  nCP*dCP_VALUE[COIN_HARDSILVER];
            dTotal += nSP/dCP_VALUE[COIN_SILVER]*dCP_VALUE[COIN_HARDSILVER];
            dTotal += nEP/dCP_VALUE[COIN_ELECTRUM]*dCP_VALUE[COIN_HARDSILVER];
            dTotal += nGP/dCP_VALUE[COIN_GOLD]*dCP_VALUE[COIN_HARDSILVER];
            dTotal += nHS;
            dTotal += nPP/dCP_VALUE[COIN_PLATINUM]*dCP_VALUE[COIN_HARDSILVER];
            sLabel = "Hard Silver Value: ";
            break;

          // plat
          case 5:
            dTotal =  nCP*dCP_VALUE[COIN_PLATINUM];
            dTotal += nSP/dCP_VALUE[COIN_SILVER]*dCP_VALUE[COIN_PLATINUM];
            dTotal += nEP/dCP_VALUE[COIN_ELECTRUM]*dCP_VALUE[COIN_PLATINUM];
            dTotal += nGP/dCP_VALUE[COIN_GOLD]*dCP_VALUE[COIN_PLATINUM];
            dTotal += nHS/dCP_VALUE[COIN_HARDSILVER]*dCP_VALUE[COIN_PLATINUM];
            dTotal += nPP;
            sLabel = "Platinum Value: ";
            break;

          default:
            break;
        }
    */

  }

  /**
   * reset all coin values to 0
   *
   * @param e ActionEvent
   */
  void ResetButton_actionPerformed(ActionEvent e) {
    for (int i=COIN_COPPER;i<=COIN_PLATINUM;i++) {
      mergeCoins[i].setValue(new Integer(0));
    }

    mergeCoinsSpinner_stateChanged(null,0);
  }

}

class RadioButton_actionAdapter implements java.awt.event.ActionListener {
  mergeCoin adaptee;
  int nMe;

  RadioButton_actionAdapter(mergeCoin adaptee, int nMe) {
    this.adaptee = adaptee;
    this.nMe = nMe;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.mergeCoins_actionPerformed(e,nMe);
  }
}

class mergeCoinsSpinner_changeAdapter implements javax.swing.event.ChangeListener {
  mergeCoin adaptee;
  int nMe;

  mergeCoinsSpinner_changeAdapter(mergeCoin adaptee, int nMe) {
    this.adaptee = adaptee;
    this.nMe = nMe;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.mergeCoinsSpinner_stateChanged(e,nMe);
  }
}

class ResetButton_actionAdapter implements java.awt.event.ActionListener {
  mergeCoin adaptee;

  ResetButton_actionAdapter(mergeCoin adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.ResetButton_actionPerformed(e);
  }
}





