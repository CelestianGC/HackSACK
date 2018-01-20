package hacksack;
import java.io.*;
import java.util.*;
import java.util.regex.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableEncounter {
  HackSackFrame oParent = null;
  JComboBox jTerrainTypes = null;
  JComboBox jClimateType = null;
  JComboBox jPopulationType = null;

  JButton jGenerateEncounterButton = null;
  JButton jRollEncounterButton = null;
  JSpinner jHackFactor = null;

  ArrayList lTableNames = null;
  ArrayList lTables = null;

  public TableEncounter(HackSackFrame oParent, ArrayList aClimate, ArrayList aTerrain)
  {
    this.oParent = oParent;

    lTables = new ArrayList();
    lTableNames = new ArrayList();

    jTerrainTypes = new JComboBox();
    jTerrainTypes.setToolTipText("Select terrain you wish to check for encounter in.");
    for (int i=0;i<aTerrain.size();i++) {
      Terrain oC = (Terrain) aTerrain.get(i);
      jTerrainTypes.addItem(oC.sName);
    }

    jClimateType = new JComboBox();
    jClimateType.setToolTipText("Select climate where the encounter is to occur.");
    for (int i=0;i<aClimate.size();i++) {
      Climate oC = (Climate) aClimate.get(i);
      jClimateType.addItem(oC.sName);
    }
    jTerrainTypes.addActionListener(new Encounters_jTerrainTypes_actionAdapter(this));
    jClimateType.addActionListener(new Encounters_jClimateType_actionAdapter(this));

    jPopulationType = new JComboBox();
    jPopulationType.addItem("Relatively dense");
    jPopulationType.addItem("Moderate to sparse");
    jPopulationType.addItem("Uninhabited wilderness/subterranean");
    jPopulationType.setToolTipText("What is the population of this region? Civilizations, villages/etc.");

    jGenerateEncounterButton = new JButton();
    jGenerateEncounterButton.addActionListener(new jTableEncounterButtonListener("bGenerate",this));
    jGenerateEncounterButton.setText("Generate"); jGenerateEncounterButton.setToolTipText("Generate an encounter.");

    jRollEncounterButton = new JButton();
    jRollEncounterButton.addActionListener(new jTableEncounterButtonListener("bRollEncounter",this));
    jRollEncounterButton.setText("Roll"); jRollEncounterButton.setToolTipText("Roll to see if encounter occurs.");

    // this sets the base value on the encounter table
    jTerrainTypes_actionPerformed(null);
  }

   public void LoadEncounterTable(String sFileName,String sTable, JTextArea jLog)
   {
     sFileName = "tables" + File.separatorChar + sFileName;
     try {
       FileReader fFile = new FileReader(sFileName);
       BufferedReader fBuff = new BufferedReader(fFile);
       String sLine;
       int i = 0;
       lTableNames.add(sTable);
       jTerrainTypes.addItem(sTable);
       while (null != (sLine = fBuff.readLine())) {
         i++;
         sLine.trim(); // trim whitespace
         Matcher pColonCheck = Pattern.compile(":").matcher(sLine);
         if (pColonCheck.find()) {
             String[] sFields = sLine.split(":"); // ##:##:##:##:##:etc
             if (sFields.length == 8)
             {
               EncounterRegion oE = new EncounterRegion();
               oE.sCreatureName = sFields[0];
               oE.nHackFactor = Integer.parseInt(sFields[1]);
               oE.nHoB = Integer.parseInt(sFields[2]);

               oE.nRoll[oE.TYPE_TROPICAL] = Integer.parseInt(sFields[3]);
               oE.nRoll[oE.TYPE_SUBTROPICAL] = Integer.parseInt(sFields[4]);
               oE.nRoll[oE.TYPE_TEMPERATE] = Integer.parseInt(sFields[5]);
               oE.nRoll[oE.TYPE_SUBARCTIC] = Integer.parseInt(sFields[6]);
               oE.nRoll[oE.TYPE_ARCTIC] = Integer.parseInt(sFields[7]);

               oE.sTable = sTable;
               lTables.add(oE);
             }
             else
               jLog.append("Error with Table " + sTable + " at line " + i +
                                              ":" + sFileName + "\n");
         }
         else
           jLog.append("Error with Table " + sTable + " at line " + i +
                                  ":" + sFileName + "\n");
       }
       fBuff.close();
       fFile.close();

     }
     catch (IOException e) {
       File oFile = new File("");
       sFileName = oFile.getAbsolutePath() + File.separatorChar + sFileName;
       jLog.append("Error opening Table " + sTable + " file:" +
                              sFileName + "\n");
     }

   }

public void encounterGenerate() {
   Dicer dDicer = new Dicer();
   int nMaxSize = 0;
//   int nRoll = dDicer.d10000();
   String sTerrainName = (String)jTerrainTypes.getSelectedItem();
   String sClimateName = (String)jClimateType.getSelectedItem();
   ArrayList aCreatureList = new ArrayList();

   for (int i=0;i<oParent.lSavedCreatures.size();i++) {
     TableSavedCreatures oCreature = (TableSavedCreatures) oParent.lSavedCreatures.get(i);
     // only check monsters with climate/terrain set.
     if (oCreature.aClimate.size() > 0 && oCreature.aTerrain.size() > 0) {
       Terrain oTerrain = oCreature.getTerrainByName(sTerrainName);
       Climate oClimate = oCreature.getClimateByName(sClimateName);
       // build array with encounter info, creature and dice range
       if (oClimate != null && oTerrain != null) {
         if (oClimate.jActive.isSelected() && oTerrain.jActive.isSelected()) {
           CreatureEncounter oE = new CreatureEncounter();
           oE.oCreature = oCreature;
           oE.nLowRange = nMaxSize + 1;
           nMaxSize += oParent.nFrequencyWeight[oCreature.nFrequency];
           oE.nHighRange = nMaxSize;
           aCreatureList.add(oE);
           oParent.gmLog(true, true,
                         "ADD Encounter:" + oE.oCreature.sCreatureName +
                         " range:" + oE.nLowRange + "-" + oE.nHighRange + "\n");
         }
       }
     }
   }
   int nRoll = dDicer.Random(nMaxSize);
   for (int i=0;i<aCreatureList.size();i++) {
     CreatureEncounter oE = (CreatureEncounter)aCreatureList.get(i);
     if (nRoll >= oE.nLowRange && nRoll <= oE.nHighRange) {

       oParent.dmEncounterGenerated.insertElementAt(oE,0);

       if (oParent.jGMDetailsCheckBox.isSelected())
         oParent.gmLog(false,true,"Rolled "+dDicer.rollLastRoll()+"\n");

       String sFound = "Encounter Generated is "+oE.oCreature.sCreatureName+
           " ("+dDicer.rollLastRoll()+") located in "+
           oParent.sAppearingIn[oE.oCreature.nAppearingIn]+"\n";
       oParent.jEncounterResultsTextArea.append(sFound);
       oParent.gmLog(true,false,sFound);
     }
   }

//   TableSavedCreatures oCreature = (TableSavedCreatures)
}

 public void updateButtonPressed(String sButtonName)
{
  if (sButtonName.equalsIgnoreCase("bGenerate")) // force encounter to generate
  {
    encounterGenerate();
  }
  else if (sButtonName.equalsIgnoreCase("bRollEncounter")) // randomly checkfor encounter
  {
    Dicer bDice = new Dicer();
    int nDice = 20;
    switch(jPopulationType.getSelectedIndex())
    {
      case 0:
        nDice = 20;
        break;
      case 1:
        nDice = 12;
        break;
      case 2:
        nDice = 10;
        break;

        default:
          nDice = 20;
          break;
    }

    int nRoll = bDice.Random(nDice);
    if (nRoll == 1)
      encounterGenerate();
      else
        oParent.jEncounterResultsTextArea.append("Nothing around at this time (d"+nDice+"="+nRoll+")\n");
  }

}
   // put the pulldowns and such for this junk on a panel
   public void encounterGenerateEncounterPanel(JPanel jEP)
   {
     JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     jPanel.setBackground(Color.lightGray);

     jPanel.add(jRollEncounterButton);
     jPanel.add(jPopulationType);
     jPanel.add(jTerrainTypes);
     jPanel.add(jClimateType);
     jPanel.add(jGenerateEncounterButton);

     jEP.add(jPanel);
   }


   public void encounterGenerateOLD()
   {
     Dicer dDicer = new Dicer();
     int nRoll = dDicer.d10000();

     String sTableName = (String)jTerrainTypes.getSelectedItem();
     int nRegionType = jClimateType.getSelectedIndex();

     EncounterRegion oFound = null;

     for (int i=0;i<lTables.size();i++)
     {
       EncounterRegion oR = (EncounterRegion)lTables.get(i);
       if (nRoll <= oR.nRoll[nRegionType] && oR.nRoll[nRegionType] != 0)
         if (oFound == null)
           oFound = oR;
           else // make sure this one is higher than the last one we found
           if (oR.nRoll[nRegionType] < oFound.nRoll[nRegionType])
             oFound = oR;
     }
     if (oFound == null) {
       oFound = new EncounterRegion();
       oFound.sCreatureName = "Not Found";
       oFound.nHoB = -1;
     }
     if (oParent.jGMDetailsCheckBox.isSelected())
       oParent.gmLog(false,true,"Rolled "+dDicer.rollLastRoll()+"\n");

     String sFound = "Encounter Generated is "+oFound.sCreatureName+" ("+dDicer.rollLastRoll()+") located in HOB"+oFound.nHoB+"\n";
     oParent.jEncounterResultsTextArea.append(sFound);
//   oParent.ShowDone(oParent,sFound);
     oParent.gmLog(true,false,sFound);
   }

   public void updateButtonPressedOLD(String sButtonName)
   {
     if (sButtonName.equalsIgnoreCase("bGenerate"))
     {
       encounterGenerateOLD();
     }
     else if (sButtonName.equalsIgnoreCase("bRollEncounter"))
     {
       Dicer bDice = new Dicer();
       int nDice = 20;
       switch(jPopulationType.getSelectedIndex())
       {
         case 0:
           nDice = 20;
           break;
         case 1:
           nDice = 12;
           break;
         case 2:
           nDice = 10;
           break;

           default:
             nDice = 20;
             break;
       }

       int nRoll = bDice.Random(nDice);
       if (nRoll == 1)
         encounterGenerate();
         else
           oParent.jEncounterResultsTextArea.append("Nothing around at this time (d"+nDice+"="+nRoll+")\n");
     }

   }

   void jTerrainTypes_actionPerformed(ActionEvent e) {
//     oParent.dmEncounterGenerated.removeAllElements();
     oParent.dmEncounterTable.removeAllElements();
     int nMaxSize = 0;
     String sTerrainName = (String)jTerrainTypes.getSelectedItem();
     String sClimateName = (String)jClimateType.getSelectedItem();

     for (int i=0;i<oParent.lSavedCreatures.size();i++) {
       TableSavedCreatures oCreature = (TableSavedCreatures) oParent.lSavedCreatures.get(i);
       // only check monsters with climate/terrain set.
       if (oCreature.aClimate.size() > 0 && oCreature.aTerrain.size() > 0) {
         Terrain oTerrain = oCreature.getTerrainByName(sTerrainName);
         Climate oClimate = oCreature.getClimateByName(sClimateName);
         // build array with encounter info, creature and dice range
         if (oClimate != null && oTerrain != null) {
           if (oClimate.jActive.isSelected() && oTerrain.jActive.isSelected()) {
             CreatureEncounter oE = new CreatureEncounter();
             oE.oCreature = oCreature;
             oE.nLowRange = nMaxSize + 1;
             nMaxSize += oParent.nFrequencyWeight[oCreature.nFrequency];
             oE.nHighRange = nMaxSize;
             oParent.dmEncounterTable.addElement(oE);
             oParent.gmLog(true, true,
                           "ADD Encounter:" + oE.oCreature.sCreatureName +
                           " range:" + oE.nLowRange + "-" + oE.nHighRange + "\n");
           }
         }
       }
     }

    }

}
class CreatureEncounter
{
  TableSavedCreatures oCreature = null;
  int nLowRange = 0;
  int nHighRange = 0;

  public CreatureEncounter() {

  }
}
// meat of the table
class EncounterRegion
{
   String sCreatureName = null;
   int nHackFactor = 0;
   int nHoB = 0;
   int[] nRoll = null; // tropical, sub, temperate, sub arctic, arctic
   String sTable = null;

   int TYPE_TROPICAL = 0;
   int TYPE_SUBTROPICAL = 1;
   int TYPE_TEMPERATE = 2;
   int TYPE_SUBARCTIC = 3;
   int TYPE_ARCTIC = 4;

   public EncounterRegion() {
     nRoll = new int[5];
   }
}

 class jTableEncounterButtonListener implements ActionListener {
   private String sThisButtonName = null;
   private TableEncounter oE = null;

   public jTableEncounterButtonListener(String sButtonName, TableEncounter oE) {
     this.sThisButtonName = sButtonName;
     this.oE = oE;

   }
   public void actionPerformed(ActionEvent e) {
     oE.updateButtonPressed(sThisButtonName);
   }
 }

 class Encounters_jTerrainTypes_actionAdapter implements java.awt.event.ActionListener {
   TableEncounter adaptee;

   Encounters_jTerrainTypes_actionAdapter(TableEncounter adaptee) {
     this.adaptee = adaptee;
   }
   public void actionPerformed(ActionEvent e) {
     adaptee.jTerrainTypes_actionPerformed(e);
   }
 }
 class Encounters_jClimateType_actionAdapter implements java.awt.event.ActionListener {
   TableEncounter adaptee;

   Encounters_jClimateType_actionAdapter(TableEncounter adaptee) {
     this.adaptee = adaptee;
   }
   public void actionPerformed(ActionEvent e) {
     adaptee.jTerrainTypes_actionPerformed(e);
   }
 }
