package hacksack;

import java.io.*;
import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import org.jdom.*;
//import com.borland.jbcl.layout.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */
public class TablePlayer {
  protected HackSackFrame oParent = null;
  long lPlayerID = -1;
  String sPlayerName = null;
  JTextField jPlayerName = null;

  String sCharacter = null;
  JTextField jCharacter = null;

  String sFamilyClan = null;
  String sHomeland = null;
  String sGawd = "";
  String sPatron = null;
  String sAppearance = null;
  String sBirthDate = null;
  String sFamilyHistory = null;
  String sHairColour = null;
  String sEyeColour = null;
//  String sQuirksAndFlaws = null;
  String sSpecialAbilities = null;
  String sMiscInfo = null;

  String sRace = null;
  JTextField jRace = null;

  int nAlignementIndex = 4; //
  JComboBox jAlignmentSelect = null;

  int nSexIndex = 0;
  int nHandIndex = 0;
  int nSocialClassIndex = 0;

  int nLevel = 1;
  int nBirthRank = 0;
  int nNumberSiblings = 0;
  int nAge = 0;
  int nHeight = 0;
  int nWeight = 0;
  int nAIP = 0;
  JComboBox jSizeComboBox = null;

  ArrayList aAbilities = null;
  ArrayList aSaves = null;
  JSpinner jMove = null;
  JLabel jMoveAdjustedLabel = null;
  JSpinner[] nAC = null;
  JSpinner jAC = null;
  JSpinner jACMod = null;
  JSpinner jACModWODex = null;
  JSpinner jACModDex = null;
  JComboBox jArmorType = null;

  int nArmorHealth = 0;
  int nShieldHealth = 0;
  JTextField jAdjHealth = null;
  JLabel jHealthLabel = null;
  JSpinner jHealthMax = null;
  // player sheet stuff
  JTextField jBSAdjHealth = null;
  JLabel jBSHealthLabel = null;
  JLabel jBSAttackLabel = null;
  JTextField jBSAttackRoll = null;
//  double dWeightCarried = 0.0;
  JLabel jEncumValue = null;
  //

  JSpinner jHonor = null;
  JTextField jHonorMod = null;
  int nTemporalHonor = 0;

  JTextArea jPlayerLog = null;

  ArrayList aCoins = null;
  JCheckBox jCoinWeightIncluded = null;

  ArrayList aHenchmen = null;
  ArrayList aFollowers = null;
  ArrayList aPets = null;
  ArrayList aHirelings = null;

  ArrayList aClass = null;
  ArrayList aSkills = null;
  ArrayList aGear = null;
  ArrayList aQuirks = null;

  PlayerButton bAddClass = null;
  PlayerButton bAddGear = null;
  PlayerButton bAddSkill = null;
  PlayerButton bAddQuirk = null;

  PlayerButton bAddHonorAward = null;
  PlayerButton bAddEXPAward = null;

  PlayerButton bSavePlayer = null;
  PlayerButton bRemovePlayer = null;
  PlayerButton bViewPlayerLog = null;

  PlayerButton bFindMyTarget = null;

  JCheckBox bSelected = null;

  // SkillTree
  JTree jSkillTree = null;
  DefaultMutableTreeNode jSkillsRootNode = null;
  DefaultMutableTreeNode jSkillsAbilitiesNode;
  DefaultMutableTreeNode jSkillsTalentsNode;
  DefaultMutableTreeNode jSkillsProfsNode;
  DefaultMutableTreeNode jSkillsNode;
  DefaultTreeModel mTreeModel = null;
  CellRendererSkillTree oSkillTreeRenderer = null;
  JLabel lSkillTreeSelection = null;

  // Quirk Tree
  JTree jQuirkTree = null;
  DefaultMutableTreeNode jQuirksRootNode = null;
  DefaultMutableTreeNode jQuirksNode = null;
  DefaultTreeModel mQuirkTreeModel = null;
  CellRendererQuirkTree oQuirkTreeRenderer = null;
  JLabel lQuirkTreeSelection = null;

  // Gear Tree
  JTree jGearTree = null;
  DefaultMutableTreeNode jGearRootNode = null;
  DefaultMutableTreeNode jGearNode = null;
  DefaultMutableTreeNode jGearUnCarriedNode = null;
  DefaultMutableTreeNode jGearWornNode = null;
  DefaultTreeModel mGearTreeModel = null;
  CellRendererGearTree oGearTreeRenderer = null;
  JLabel lGearTreeSelection = null;

  // popup menu for gear items
  JPopupMenu jPopupMenuGear;
  JMenuItem jMenuItemChange;
  JMenuItem jMenuItemDelete;
  JMenuItem jMenuItemUnCarried;
  JMenuItem jMenuItemCarried;
  JMenuItem jMenuItemAdd;
  JMenuItem jMenuItemEdit;
  // end pop up menu for gear

  // popup for skilltree
  JPopupMenu jPopupMenuSkillTree;
  JMenuItem jMenuItemSkillAdd;
  JMenuItem jMenuItemSkillRemove;
  JMenuItem jMenuItemSkillChange;
  JMenuItem jMenuItemSkillEdit;
  // end skilltree popup

  // popup for quirktree
  JPopupMenu jPopupMenuQuirkTree;
  JMenuItem jMenuItemQuirkAdd;
  JMenuItem jMenuItemQuirkRemove;
  JMenuItem jMenuItemQuirkChange;
  JMenuItem jMenuItemQuirkEdit;
  // end quirktree popup
  // data for battle sheet
  JComboBox jAttackingThisCreature = null;

  JCheckBox jAbsent = null;
  JCheckBox jHireling = null;

  // constants
  // ac consts
  int AC_NORMAL = 0;
  int AC_SUPRISED = 1;
  int AC_SHIELDLESS = 2;
  int AC_REAR = 3;
  int AC_LAST = 4;

  //

  String[] sAC = {
      "Normal", "Suprised", "Shieldless", "Rear"};
  public TablePlayer() {

    lPlayerID = System.currentTimeMillis();

    jSkillsRootNode = new DefaultMutableTreeNode("Skills List");
    jSkillsAbilitiesNode = new DefaultMutableTreeNode("Abilities");
    jSkillsTalentsNode = new DefaultMutableTreeNode("Talents");
    jSkillsProfsNode = new DefaultMutableTreeNode("Proficiencies");
    jSkillsNode = new DefaultMutableTreeNode("Skills");
    mTreeModel = new DefaultTreeModel(jSkillsRootNode);
    jSkillTree = new JTree(mTreeModel);
    oSkillTreeRenderer = new CellRendererSkillTree();
    jSkillTree.setCellRenderer(oSkillTreeRenderer);
    lSkillTreeSelection = new JLabel(
        "Click item to view, double click to edit.");
    lSkillTreeSelection.setBackground(Color.GRAY);
    lSkillTreeSelection.setForeground(Color.GREEN);
    lSkillTreeSelection.setFont(new Font("Dialog", Font.PLAIN, 9));

    jQuirksRootNode = new DefaultMutableTreeNode("Quirks & Flaws List");
    jQuirksNode = new DefaultMutableTreeNode("Quirks");
    mQuirkTreeModel = new DefaultTreeModel(jQuirksRootNode);
    jQuirkTree = new JTree(mQuirkTreeModel);
    oQuirkTreeRenderer = new CellRendererQuirkTree();
    jQuirkTree.setCellRenderer(oQuirkTreeRenderer);
    lQuirkTreeSelection = new JLabel(
        "Click item to view, double click to edit.");
    lQuirkTreeSelection.setBackground(Color.GRAY);
    lQuirkTreeSelection.setForeground(Color.GREEN);
    lQuirkTreeSelection.setFont(new Font("Dialog", Font.PLAIN, 9));

    jGearRootNode = new DefaultMutableTreeNode("Gear List");
    jGearNode = new DefaultMutableTreeNode("Carried");
    jGearUnCarriedNode = new DefaultMutableTreeNode("Not Carried");
    jGearWornNode = new DefaultMutableTreeNode("Equipped");
    mGearTreeModel = new DefaultTreeModel(jGearRootNode);
    jGearTree = new JTree(mGearTreeModel);
    jGearTree.setToolTipText(
        "Select for details, double click to edit or select+right click");
    oGearTreeRenderer = new CellRendererGearTree();
    jGearTree.setCellRenderer(oGearTreeRenderer);
    lGearTreeSelection = new JLabel(
        "Click item to view, double or right click to edit.");
    lGearTreeSelection.setToolTipText(lGearTreeSelection.getText());
    lGearTreeSelection.setBackground(Color.GRAY);
    lGearTreeSelection.setForeground(Color.GREEN);
    lGearTreeSelection.setFont(new Font("Dialog", Font.PLAIN, 9));

    // geartree popup
    jPopupMenuGear = new JPopupMenu();
    jMenuItemChange = new JMenuItem();
    jMenuItemDelete = new JMenuItem();
    jMenuItemUnCarried = new JMenuItem();
    jMenuItemCarried = new JMenuItem();
    jMenuItemAdd = new JMenuItem();
    jMenuItemEdit = new JMenuItem();
    jMenuItemChange.setToolTipText("Edit number of items or view details.");
    jMenuItemChange.setText("View/Change");
    jMenuItemChange.addActionListener(new Player_jMenuItemChange_actionAdapter(this));
    jMenuItemUnCarried.setToolTipText("Keep item but do not carry it.");
    jMenuItemUnCarried.setText("Store Item");
    jMenuItemUnCarried.addActionListener(new
                                         Player_jMenuItemUnCarried_actionAdapter(this));
    jMenuItemCarried.setToolTipText("Move item to carried inventory.");
    jMenuItemCarried.setText("Carry Item");
    jMenuItemCarried.addActionListener(new
                                       Player_jMenuItemCarried_actionAdapter(this));
    jMenuItemAdd.setToolTipText("Add item to players inventory.");
    jMenuItemAdd.setText("Add Item");
    jMenuItemAdd.addActionListener(new Player_jMenuItemAdd_actionAdapter(this));

    jMenuItemDelete.setToolTipText("Remove this item.");
    jMenuItemDelete.setText("Delete Item");
    jMenuItemDelete.addActionListener(new Player_jMenuItemDelete_actionAdapter(this));

    jMenuItemEdit.setToolTipText("Edit item details.");
    jMenuItemEdit.setText("Edit Item");
    jMenuItemEdit.addActionListener(new Player_jMenuItemEdit_actionAdapter(this));

    jPopupMenuGear.add(jMenuItemAdd);
    jPopupMenuGear.add(jMenuItemUnCarried);
    jPopupMenuGear.add(jMenuItemCarried);
    jPopupMenuGear.add(jMenuItemDelete);
    jPopupMenuGear.add(jMenuItemChange);
    jPopupMenuGear.add(jMenuItemEdit);
    // end geartree popup

    // skilltree popup
    jPopupMenuSkillTree = new JPopupMenu();
    jMenuItemSkillAdd = new JMenuItem();
    jMenuItemSkillRemove = new JMenuItem();
    jMenuItemSkillChange = new JMenuItem();
    jMenuItemSkillEdit = new JMenuItem();
    jMenuItemSkillAdd.setText("Add Skill");
    jMenuItemSkillAdd.addActionListener(new jMenuItemSkillAdd_actionAdapter(this));
    jMenuItemSkillRemove.setText("Delete Skill");
    jMenuItemSkillRemove.addActionListener(new
                                           jMenuItemSkillRemove_actionAdapter(this));
    jMenuItemSkillChange.setText("Change Skill");
    jMenuItemSkillChange.addActionListener(new
                                           jMenuItemSkillChange_actionAdapter(this));
    jMenuItemSkillEdit.setText("Edit Skill");
    jMenuItemSkillEdit.addActionListener(new jMenuItemSkillEdit_actionAdapter(this));

    jPopupMenuSkillTree.add(jMenuItemSkillAdd);
    jPopupMenuSkillTree.add(jMenuItemSkillRemove);
    jPopupMenuSkillTree.add(jMenuItemSkillChange);
    jPopupMenuSkillTree.add(jMenuItemSkillEdit);
    // end skilltree popup

    // quirktree popup
    jPopupMenuQuirkTree = new JPopupMenu();
    jMenuItemQuirkAdd = new JMenuItem();
    jMenuItemQuirkRemove = new JMenuItem();
    jMenuItemQuirkChange = new JMenuItem();
    jMenuItemQuirkEdit = new JMenuItem();

    jPopupMenuQuirkTree.add(jMenuItemQuirkAdd);
    jPopupMenuQuirkTree.add(jMenuItemQuirkRemove);
    jPopupMenuQuirkTree.add(jMenuItemQuirkChange);
    jPopupMenuQuirkTree.add(jMenuItemQuirkEdit);

    jMenuItemQuirkAdd.setText("Add Quirk");
    jMenuItemQuirkAdd.addActionListener(new jMenuItemQuirkAdd_actionAdapter(this));
    jMenuItemQuirkRemove.setText("Delete Quirk");
    jMenuItemQuirkRemove.addActionListener(new jMenuItemQuirkRemove_actionAdapter(this));
    jMenuItemQuirkChange.setText("Change Quirk");
    jMenuItemQuirkChange.addActionListener(new jMenuItemQuirkChange_actionAdapter(this));
    jMenuItemQuirkEdit.setText("Edit Quirk");
    jMenuItemQuirkEdit.addActionListener(new jMenuItemQuirkEdit_actionAdapter(this));

    // endquirktree popup
    jEncumValue = new JLabel("");

    aAbilities = new ArrayList();
    aSaves = new ArrayList();

    aCoins = new ArrayList();

    aSkills = new ArrayList(); // array of Skills class
    aGear = new ArrayList(); // array of Gear class
    aQuirks = new ArrayList(); // array of class Quirk
    aClass = new ArrayList(); // array of Class class

    /*
            aHenchmen = new ArrayList(); // array of player class
        aFollowers = new ArrayList(); // array of player class
        aPets = new ArrayList(); // array of player class
        aHirelings = new ArrayList(); // array of player class
     */

    jPlayerName = new JTextField();
    jCharacter = new JTextField();
    jRace = new JTextField();

    jAbsent = new JCheckBox("Absent",false);
    jAbsent.setToolTipText("Select this if the player is absent for whatever reason and shouldn't get any experience/honor awards.");
    jHireling = new JCheckBox("Hireling",false);
    jHireling.setToolTipText("Select this if the character is a hireling/henchman/follower. (they will not get honor/exp awards)");
  }

  // give EXP to player and spread it across all classes
  // if they have more than one.
  void GiveEXPToPlayer(TablePlayer oPlayer, int nAward) {
    int nNumberOfClasses = oPlayer.aClass.size();
    for (int j = 0; j < oPlayer.aClass.size(); j++) {
      Class oClass = (Class) oPlayer.aClass.get(j);
      int nCurrentEXP = Integer.parseInt(oClass.jEXP.getModel().getValue().
                                         toString());
      int nReward = (nAward / nNumberOfClasses);
      int nEXPBonus = Integer.parseInt(oClass.jEXPBonus.getModel().getValue().toString());
      double bBonus = nReward*(nEXPBonus*0.01);
      int nNewEXP = (nCurrentEXP + nReward +(int)bBonus);
      oClass.jEXP.getModel().setValue(new Integer(nNewEXP));
      oPlayer.playerLog("Received EXP award of " + nReward +
                        (bBonus > 0?" (bonus:"+(int)bBonus+")":"")+
                        " points to the " + oClass.sName +
                        " class for a total of " + nNewEXP +
                        " experience.\n");
    }

  }

  int GetPlayerMaxLevel() {
    int nMaxLevel = 0;

    for (int i = 0; i < aClass.size(); i++) {
      Class oClass = (Class) aClass.get(i);
      int nLevel = Integer.parseInt(oClass.jLevel.getModel().getValue().
                                    toString());
      if (nLevel > nMaxLevel) {
        nMaxLevel = nLevel;
      }
    }

    return nMaxLevel;
  }

  TableClass GetClassFromID(HackSackFrame oParent, int nID) {
    TableClass oClass = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lClass.size() && !bFound; i++) {
      TableClass oFind = (TableClass) oParent.lClass.get(i);
      if (oFind.nClassID == nID) {
        oClass = oFind;
        bFound = true;
      }
    }
    return oClass;
  }

  TableSkills GetSkillFromID(HackSackFrame oParent, int nID) {
    TableSkills oFound = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lSkills.size() && !bFound; i++) {
      TableSkills oFind = (TableSkills) oParent.lSkills.get(i);
      if (oFind.nSkillID == nID) {
        oFound = oFind;
        bFound = true;
      }
    }
    return oFound;
  }

  TableQuirks GetQuirkFromID(HackSackFrame oParent, int nID) {
    TableQuirks oFound = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lQuirks.size() && !bFound; i++) {
      TableQuirks oFind = (TableQuirks) oParent.lQuirks.get(i);
      if (oFind.nQuirkID == nID) {
        oFound = oFind;
        bFound = true;
      }
    }
    return oFound;
  }

  TableGear GetGearFromID(HackSackFrame oParent, int nID) {
    TableGear oFound = null;
    boolean bFound = false;
    for (int i = 0; i < oParent.lGear.size() && !bFound; i++) {
      TableGear oFind = (TableGear) oParent.lGear.get(i);
      if (oFind.nGearID == nID) {
        oFound = oFind;
        bFound = true;
      }
    }
    return oFound;
  }

  double getWeightCarried() {
    double dWeightCarried = 0.0;
    int nCount;

    if (jCoinWeightIncluded.isSelected()) { // include coin weight?
      int nONE_POUND_OF_COINS = 10; // this many coins is 1 pound
      for (int i = 0; i < aCoins.size(); i++) {
        Coins oC = (Coins) aCoins.get(i);
        nCount = Integer.parseInt(oC.jMod.getValue().toString());
        dWeightCarried += (nCount / nONE_POUND_OF_COINS);
      }
    }

    for (int i = 0; i < aGear.size(); i++) {
      Gear oG = (Gear) aGear.get(i);
      if (!oG.sLoc.equalsIgnoreCase("NOT-CARRIED")) {
        nCount = Integer.parseInt(oG.jMod.getValue().toString());
        dWeightCarried += (nCount * oG.dWeight);
      }
    }
    return dWeightCarried;
  }

  double getWeightNotCarried() {
    double dWeightNotCarried = 0.0;
    int nCount;

    if (!jCoinWeightIncluded.isSelected()) { // include coin weight?
      int nONE_POUND_OF_COINS = 10; // this many coins is 1 pound
      for (int i = 0; i < aCoins.size(); i++) {
        Coins oC = (Coins) aCoins.get(i);
        nCount = Integer.parseInt(oC.jMod.getValue().toString());
        dWeightNotCarried += (nCount / nONE_POUND_OF_COINS);
      }
    }

    for (int i = 0; i < aGear.size(); i++) {
      Gear oG = (Gear) aGear.get(i);
      if (oG.sLoc.equalsIgnoreCase("NOT-CARRIED")) {
        nCount = Integer.parseInt(oG.jMod.getValue().toString());
        dWeightNotCarried += (nCount * oG.dWeight);
      }
    }
    return dWeightNotCarried;
  }

  Coins getCoinByABBR(String sABBR) {
    Coins oReturn = null;
    for (int i = 0; i < aCoins.size() && oReturn == null; i++) {
      Coins oCC = (Coins) aCoins.get(i);
      if (oCC.sName.equalsIgnoreCase("Copper") && sABBR.equalsIgnoreCase("CP")) {
        oReturn = oCC;
      }
      else
      if (oCC.sName.equalsIgnoreCase("Silver") && sABBR.equalsIgnoreCase("SP")) {
        oReturn = oCC;
      }
      else
      if (oCC.sName.equalsIgnoreCase("Electrum") &&
          sABBR.equalsIgnoreCase("ELP")) {
        oReturn = oCC;
      }
      else
      if (oCC.sName.equalsIgnoreCase("Gold") && sABBR.equalsIgnoreCase("GP")) {
        oReturn = oCC;
      }
      else
      if (oCC.sName.equalsIgnoreCase("Hardsilver") &&
          sABBR.equalsIgnoreCase("HSP")) {
        oReturn = oCC;
      }
      else
      if (oCC.sName.equalsIgnoreCase("Platinum") && sABBR.equalsIgnoreCase("PP")) {
        oReturn = oCC;
      }
    }
    return oReturn;
  }

  public static void SetupBasicCoins(HackSackFrame oParent, TablePlayer oPlayer) {
    String[] sCoins = {
        "Copper", "Silver", "Electrum", "Gold", "Hardsilver", "Platinum"};
//    "CP", "SP", "ELP", "GP", "HSP", "PP"};
    for (int i = 0; i < sCoins.length; i++) {
      Coins oCoin = new Coins(oParent, oPlayer);
      oCoin.sName = sCoins[i];
      oPlayer.aCoins.add(oCoin);
    }

  }

  public static void SetupBasicSaves(HackSackFrame oParent, TablePlayer oPlayer) {
    String[] sSaves = {
        "Paralyzation, Poison, Death Magic",
        "Rod, Staff, Wand", "Petrifaction, HackFrenzy, HackLust, PolyMorph",
        "Breath Weapon", "Apology", "Spells"};
    for (int i = 0; i < sSaves.length; i++) {
      Saves oSave = new Saves(oParent, oPlayer);
      oSave.sName = sSaves[i];
      oSave.nSaveType = i;
      oPlayer.aSaves.add(oSave);
    }

  }

  public void SetupBasicAbilities(HackSackFrame oParent,
                                         TablePlayer oPlayer) {

    Abilities oA;

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Strength";
/*    for (int i = 0; i < oParent.sStrength.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sStrength[i]);
      oA.aMods.add(oModNew);
    } */
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Dexterity";
/*    for (int i = 0; i < oParent.sDex.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sDex[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Constitution";
 /*   for (int i = 0; i < oParent.sCon.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sCon[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Intelligence";
  /*  for (int i = 0; i < oParent.sInt.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sInt[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Wisdom";
  /*  for (int i = 0; i < oParent.sWis.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sWis[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Charisma";
  /*  for (int i = 0; i < oParent.sChr.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sChr[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

    oA = new Abilities(oParent, oPlayer);
    oA.sName = "Comeliness";
  /*  for (int i = 0; i < oParent.sCom.length; i++) {
      AbilityMods oModNew = new AbilityMods(oParent);
      oModNew.jName.setText(oParent.sCom[i]);
      oA.aMods.add(oModNew);
    }*/
    oPlayer.aAbilities.add(oA);

  }

  public TablePlayer(HackSackFrame oThis) {
    this();
    this.oParent = oThis;

    jSizeComboBox = new JComboBox(oParent.gmSizeTable);
    jSizeComboBox.setSelectedIndex(2); // default is medium

    bAddClass = new PlayerButton(this.oParent, this, "Class");
    bAddGear = new PlayerButton(this.oParent, this, "Gear");
    bAddSkill = new PlayerButton(this.oParent, this, "Skill");
    bAddQuirk = new PlayerButton(this.oParent, this, "Quirk");

    bAddHonorAward = new PlayerButton(this.oParent, this, "HonorAward");
    bAddEXPAward = new PlayerButton(this.oParent, this, "EXPAward");

    bRemovePlayer = new PlayerButton(this.oParent, this, "RemovePlayer");
    bRemovePlayer.setText("Remove Player");
    bSavePlayer = new PlayerButton(this.oParent, this, "SavePlayer");
    bSavePlayer.setText("Save Player");

    bViewPlayerLog = new PlayerButton(this.oParent, this, "ViewPlayerLog");
    bViewPlayerLog.setText("View Log");

    bFindMyTarget = new PlayerButton(this.oParent, this, "FindMyTarget");
    bFindMyTarget.setText("Target");

    jAdjHealth = new JTextField();
    jAdjHealth.addActionListener(new PlayerTextFieldListener(this,
        "Health_GroupPanel"));
    jHealthLabel = new JLabel();

    jBSAdjHealth = new JTextField();
    jBSAdjHealth.addActionListener(new PlayerTextFieldListener(this,
        "Health_BattleSheet"));
    jBSAttackRoll = new JTextField();
    jBSAttackRoll.addActionListener(new PlayerTextFieldListener(this,
        "Attack_Roll"));
    jBSHealthLabel = new JLabel();
    jBSAttackLabel = new JLabel();

    jHealthMax = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
    jHealthMax.addChangeListener(new jHealthMaxSpinnerListener(this.oParent, this,
        ""));

    jPlayerLog = new JTextArea();

    nAC = new JSpinner[4];
    nAC[AC_NORMAL] = new JSpinner(new SpinnerNumberModel(10, -20, 15, 1));
    nAC[AC_REAR] = new JSpinner(new SpinnerNumberModel(10, -20, 15, 1));
    nAC[AC_SHIELDLESS] = new JSpinner(new SpinnerNumberModel(10, -20, 15, 1));
    nAC[AC_SUPRISED] = new JSpinner(new SpinnerNumberModel(10, -20, 15, 1));

    jMove = new JSpinner(new SpinnerNumberModel(12, 1, 48, 1));
    jMoveAdjustedLabel = new JLabel("");

    jHonor = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
    jHonorMod = new JTextField();
    jHonorMod.addActionListener(new PlayerHonorListener(oParent, this));

    bSelected = new JCheckBox();

    jSkillTree.addTreeSelectionListener(new jSkillTree_treeSelectionAdapter(this));
    jSkillTree.addMouseListener(new jSkillTree_mouseAdapter(this));

    jQuirkTree.addTreeSelectionListener(new jQuirkTree_treeSelectionAdapter(this));
    jQuirkTree.addMouseListener(new jQuirkTree_mouseAdapter(this));

    jGearTree.addTreeSelectionListener(new jGearTree_treeSelectionAdapter(this));
    jGearTree.addMouseListener(new jGearTree_mouseAdapter(this));

    jCoinWeightIncluded = new JCheckBox();
    jCoinWeightIncluded.setBackground(Color.darkGray);
    jCoinWeightIncluded.setForeground(Color.GREEN);
    jCoinWeightIncluded.setFont(new Font("Dialog", Font.PLAIN, 9));
    jCoinWeightIncluded.setText("Include Weight");
    jCoinWeightIncluded.setSelected(true);
    jCoinWeightIncluded.setToolTipText(
        "Include coin weight in players total weight carried. (10 coins, 1 pound)");
    jCoinWeightIncluded.addActionListener(new jCoinWeightIncluded_actionAdapter(this));

    jMove.addChangeListener(new jMoveSpinnerListener(this));

    jAttackingThisCreature = new JComboBox();
    jAttackingThisCreature.setFont(new Font("Dialog", Font.PLAIN, 10));
    jAttackingThisCreature.setRenderer(new CellRendererPLCreatureList());
    jAttackingThisCreature.addActionListener(new jAttackingThisCreature_actionAdapter(this));

    int nAlignment = nAlignementIndex;
    jAlignmentSelect = new JComboBox(oParent.gmAlignmentTable);
    jAlignmentSelect.addActionListener(new jGenericAction_actionAdapter(this,"jAlignmentSelect"));
    jAlignmentSelect.setSelectedIndex(nAlignment);

    jAbsent.addActionListener(new jGenericAction_actionAdapter(this,"jAbsent"));
    jHireling.addActionListener(new jGenericAction_actionAdapter(this,"jHireling"));

    jPlayerName.addFocusListener(new jFocusEvent_focusAdapter(this,"jPlayerName"));
    jCharacter.addFocusListener(new jFocusEvent_focusAdapter(this,"jCharacter"));
    jRace.addFocusListener(new jFocusEvent_focusAdapter(this,"jRace"));

  }


  void updateHonorField(HackSackFrame oParent, TablePlayer oPlayer) {
    int nCurrentHonor = Integer.parseInt(oPlayer.jHonor.getValue().toString());
    String sAdjustment = oPlayer.jHonorMod.getText();

    // remove + signs
    if (sAdjustment.matches("(?i).*?(\\+).*?")) {
      sAdjustment = sAdjustment.replaceAll("(?i).*?(\\+).*?", "");
    }

    int nHonorAdjustment = Integer.parseInt(sAdjustment);
    int nHonorResult = (nCurrentHonor + nHonorAdjustment);

    String sMod = nHonorAdjustment < 0 ? "" : "+";
    oPlayer.oParent.gmLog(true, false,
                          oPlayer.sCharacter + " adjusted honor " +
                          nCurrentHonor +
                          sMod + nHonorAdjustment + "=" + nHonorResult + "\n");

    oPlayer.jHonor.getModel().setValue(new Integer(nHonorResult));
    oPlayer.jHonorMod.setText("");
  }

  // get best combat class, fighter, cleric, thief then magic-user
  int getBestCombatClass() {
    int nBestClass = 0;
    int nBestTHAC = 20;

    // flip through classes and find best fighter class and store level
    for (int i = 0; i < aClass.size(); i++) {
      Class oClass = (Class) aClass.get(i);

      AttackRank oA = new AttackRank();
      int nLevel = Integer.parseInt(oClass.jLevel.getValue().toString());
      // find roll needed to hit AC 15
      int nMinTHAC = oA.getTHAC(oParent, oClass.nFightAs,
                                AttackRank.
                                GetToHitRating(oClass.nFightAs, nLevel, 0), 15);
      // is this roll smaller than what we needed before?
      if (nMinTHAC < nBestTHAC) {
        nBestTHAC = nMinTHAC;
        nBestClass = oClass.nFightAs;
      }
    }
    return nBestClass;
  }

  // get players best combat class level
  int getBestCombatLevel() {
    int nReturn = GetPlayerMaxLevel();
    int nBestTHAC = 20;

    // flip through classes and find best fighter class and store level
    for (int i = 0; i < aClass.size(); i++) {
      Class oClass = (Class) aClass.get(i);

      AttackRank oA = new AttackRank();
      int nLevel = Integer.parseInt(oClass.jLevel.getValue().toString());
      int nMinTHAC = oA.getTHAC(oParent, oClass.nFightAs,
                                AttackRank.
                                GetToHitRating(oClass.nFightAs, nLevel, 0), 15);
      if (nMinTHAC < nBestTHAC) {
        nBestTHAC = nMinTHAC;
        nReturn = nLevel;
      }

    }

    return nReturn;
  }

  void updatePlayerTextField(TablePlayer oPlayer, String sVar) {
    if (sVar.equalsIgnoreCase("Attack_Roll")) {
      String sRoll = oPlayer.jBSAttackRoll.getText();
      if (sRoll.matches("\\d+")) {
        CreatureCore oTarget = null;
        int nRoll = Integer.parseInt(sRoll);
        int nToHitRank = AttackRank.GetToHitRating(getBestCombatClass(),
            getBestCombatLevel(), 0);

      Object oObj = jAttackingThisCreature.getSelectedItem();
      if (oObj instanceof CreatureCore)
        oTarget = (CreatureCore)oObj;

        AttackRank oA = new AttackRank();
        oA.getACHit(oParent, getBestCombatClass(), nToHitRank,
                    ( (nRoll) < 1 ? 1 : nRoll));
        String sAcHit = null;
        String sHitMessage = null;
        if (oA.nEndAC == oA.nStartAC) {
          sAcHit = "" + oA.nEndAC;
        }
        else {
          sAcHit = oA.nStartAC + ".." + oA.nEndAC;

        }
        if ((oTarget!= null))
         if ((oA.nEndAC <= Integer.parseInt(oTarget.lAC.getText()))) {
          sHitMessage = "HIT("+sAcHit+")";
        } else {
          sHitMessage = "MISSED("+sAcHit+")";
        }
        oPlayer.jBSAttackLabel.setText("  "+(sHitMessage!= null?sHitMessage:sAcHit));
      }
      else {
        oPlayer.jBSAttackLabel.setText("Invalid Roll");

      }
      oPlayer.jBSAttackRoll.setText("");
    }

    else {
      int nMaxHP = Integer.parseInt(oPlayer.jHealthMax.getModel().getValue().
                                    toString());
      int nCurrentHP = Integer.parseInt(oPlayer.jHealthLabel.getText().trim());
      String sAdjustment = "";
      // get adjustment from the proper place
      // group panel hp adjustment
      if (sVar.equalsIgnoreCase("Health_GroupPanel")) {
        sAdjustment = oPlayer.jAdjHealth.getText();
      }
      else

      // group battle sheet adjustment
      if (sVar.equalsIgnoreCase("Health_BattleSheet")) {
        sAdjustment = oPlayer.jBSAdjHealth.getText();
      }

      // remove + signs
      if (sAdjustment.matches("(?i).*?(\\+).*?")) {
        sAdjustment = sAdjustment.replaceAll("(?i).*?(\\+).*?", "");
      }
      int nHPAdjustment = Integer.parseInt(sAdjustment);
      int nHPResult = (nCurrentHP + nHPAdjustment);
      if (nHPResult < -10) { // some sanity
        nHPResult = -10;
      }
      else
      if (nHPResult > nMaxHP) {
        nHPResult = nMaxHP;

      }
      String sMod = nHPAdjustment < 0 ? "" : "+";
      oParent.gplGroupLog.groupLog(oPlayer.sCharacter + " adjusted health " +
                                   nCurrentHP + sMod + nHPAdjustment + "=" +
                                   nHPResult + "\n");
      oPlayer.playerLog("Your health was adjusted " + nCurrentHP + sMod +
                        nHPAdjustment + "=" + nHPResult + "\n");

      // apply change and reflect it on all sections
      oPlayer.jBSHealthLabel.setText("  "+nHPResult);
      oPlayer.jHealthLabel.setText("  "+nHPResult);
      oPlayer.jBSAdjHealth.setText("");
      oPlayer.jAdjHealth.setText("");
    }

  }

  void updateMaxHealthSpinner(HackSackFrame oParent, TablePlayer oPlayer,
                              String sType) {
    jHealthLabel.setText(jHealthMax.getValue().toString());
  }

  /* static void FillPlayerPanel(HackSackFrame oParent, TablePlayer oPlayer) {
    // if this is first time creation of the player
    if (oPlayer.aAbilities.size() < 1) {
      oPlayer.SetupBasicAbilities(oParent, oPlayer);
    }
    if (oPlayer.aCoins.size() < 1) {
      TablePlayer.SetupBasicCoins(oParent, oPlayer);
    }
    if (oPlayer.aSaves.size() < 1) {
      TablePlayer.SetupBasicSaves(oParent, oPlayer);

    }
    oParent.jplCharacterNameTextArea.setText(oPlayer.sCharacter);
    oParent.jplPlayerNameTextField.setText(oPlayer.sPlayerName);
    oParent.jplClanTextField.setText(oPlayer.sFamilyClan);
    oParent.jplHomelandTextField.setText(oPlayer.sHomeland);
    oParent.jplGawdTextField.setText(oPlayer.sGawd);
    oParent.jplPatronTextField.setText(oPlayer.sPatron);
    oParent.jplAppearanceTextField.setText(oPlayer.sAppearance);
    oParent.jplBirthdateTextField.setText(oPlayer.sBirthDate);
    oParent.jplFamilyHistoryTextField.setText(oPlayer.sFamilyHistory);
    oParent.jplHairColourTextField.setText(oPlayer.sHairColour);
    oParent.jplEyeColourTextField.setText(oPlayer.sEyeColour);
    oParent.jplSpecialTextField.setText(oPlayer.sSpecialAbilities);
    oParent.jplMiscTextField.setText(oPlayer.sMiscInfo);
    oParent.jplRaceTextField.setText(oPlayer.sRace);

    oParent.jplHandComboBox.setSelectedIndex(oPlayer.nHandIndex);
    oParent.jplSexComboBox.setSelectedIndex(oPlayer.nSexIndex);
    oParent.jplAlignmentComboBox.setSelectedIndex(oPlayer.nAlignementIndex);
    oParent.jplSocialClassComboBox.setSelectedIndex(oPlayer.nSocialClassIndex);

    oParent.jplBirthRankSpinner.getModel().setValue(new Integer(oPlayer.
        nBirthRank));
    oParent.jplSiblingsSpinner.getModel().setValue(new Integer(oPlayer.
        nNumberSiblings));
    oParent.jplAgeSpinner.getModel().setValue(new Integer(oPlayer.nAge));
    oParent.jplHeightSpinner.getModel().setValue(new Integer(oPlayer.nHeight));
    oParent.jplWeightSpinner.getModel().setValue(new Integer(oPlayer.nWeight));


    oParent.jplMiscPanel.removeAll();
    Font fFont = new Font("Dialog", Font.PLAIN, 9);
    JLabel jACNormal = new JLabel("AC Normal");
    jACNormal.setFont(fFont);
    oParent.jplMiscPanel.add(jACNormal);
    oParent.jplMiscPanel.add(oPlayer.nAC[oPlayer.AC_NORMAL]);

    JLabel jACRear = new JLabel("AC Rear");
    jACRear.setFont(fFont);
    oParent.jplMiscPanel.add(jACRear);
    oParent.jplMiscPanel.add(oPlayer.nAC[oPlayer.AC_REAR]);

    JLabel jACShieldless = new JLabel("AC Shieldless");
    jACShieldless.setFont(fFont);
    oParent.jplMiscPanel.add(jACShieldless);
    oParent.jplMiscPanel.add(oPlayer.nAC[oPlayer.AC_SHIELDLESS]);

    JLabel jACSuprised = new JLabel("AC Suprised");
    jACSuprised.setFont(fFont);
    oParent.jplMiscPanel.add(jACSuprised);
    oParent.jplMiscPanel.add(oPlayer.nAC[oPlayer.AC_SUPRISED]);

    JLabel jHPMax = new JLabel("Health Max");
    jHPMax.setFont(fFont);
    oParent.jplMiscPanel.add(jHPMax);
    oParent.jplMiscPanel.add(oPlayer.jHealthMax);

    JLabel jMoveBase = new JLabel("Move Base");
    jMoveBase.setFont(fFont);
    oParent.jplMiscPanel.add(jMoveBase);
    oParent.jplMiscPanel.add(oPlayer.jMove);

    JLabel jHonor = new JLabel("Honor");
    jHonor.setFont(fFont);
    oParent.jplMiscPanel.add(jHonor);
    oParent.jplMiscPanel.add(oPlayer.jHonor);

    oParent.repaint();
  } //end FillPlayerPanel
*/
  // GearPopup menu, Uncarried targeted item
  void jMenuItemUnCarried_actionPerformed(ActionEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        oG.sLoc = "NOT-CARRIED";
        jGearUnCarriedNode.add(oNode);
        mGearTreeModel.reload(jGearNode);
        mGearTreeModel.reload(jGearUnCarriedNode);
        TableEncum.setEncumLabel(oParent.lEncumTable, this);
      }
    }
  }

  // GearPopup menu, Add item to player targeted item
  void jMenuItemAdd_actionPerformed(ActionEvent e) {
    String sThisGearType = null;
    /*
    // this is unused till I get trees in gear for different types I guess
    // like weapon slot, armor slot, bag slot nodes

     TreePath oTree = this.jGearTree.getAnchorSelectionPath();

        DefaultMutableTreeNode oNode = (DefaultMutableTreeNode)oTree.getPathComponent((oTree.getPathCount()-1));
        if (oNode == jGearNode) {
          sThisGearType = TableGear.ITEM_TYPE_ARMOR;
        }
        if (oNode == jGearUnCarriedNode) {
          sThisGearType = TableGear.ITEM_TYPE_ARMOR;
        }
        if (oNode == jGearWornNode) {
          sThisGearType = TableGear.ITEM_TYPE_ARMOR;
        }
        if (oNode == jSkillsNode) {
          sThisGearType = TableGear.ITEM_TYPE_ARMOR;
        }

*/

    TableGear.DoNewGear(oParent.fPlayerGroupFrame, oParent, this,null,sThisGearType);
  }

  // GearPopup menu, Carry targeted item
  void jMenuItemCarried_actionPerformed(ActionEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        oG.sLoc = "";
        jGearNode.add(oNode);
        mGearTreeModel.reload(jGearNode);
        mGearTreeModel.reload(jGearUnCarriedNode);
        TableEncum.setEncumLabel(oParent.lEncumTable, this);
      }
    }
  }

  // GearPopup menu, Delete targeted item
  void jMenuItemDelete_actionPerformed(ActionEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        if (oParent.AskYN(oParent.fPlayerGroupFrame,
                          "Remove " + oG.sName + " from player?")) {
          aGear.remove(oG);
          oNode.removeFromParent();
          mGearTreeModel.reload(jGearNode);
          mGearTreeModel.reload(jGearUnCarriedNode);
        }
      }
    }

  }

  // GearPopup menu, Edit/Change carried amount View targeted item
  void jMenuItemChange_actionPerformed(ActionEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        DialogPlayerGearEdit dlg =
            new DialogPlayerGearEdit(oParent, this, oG, oNode);
        dlg.setTitle("View/Change " + oG.sName);
        Dimension dlgSize = dlg.panel1.getPreferredSize();
        dlg.setSize(dlg.getWidth() + 20, dlg.getHeight() + 40);
        Dimension frmSize = oParent.fPlayerGroupFrame.getSize();
        Point loc = oParent.fPlayerGroupFrame.getLocation();
        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setVisible(true);
//        dlg.show();
      }
    }

  }

  // GearPopup menu, Edit w targeted item
  void jMenuItemEdit_actionPerformed(ActionEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        TableGear.DoNewGear(oParent.fPlayerGroupFrame,oParent,this,oG,null);
      }
    }

  }

// actions for skilltree popup
  void jMenuItemSkillAdd_actionPerformed(ActionEvent e) {
    int nSkillType = -1;
    // figure out the node type and set -1 to the SkillType to list
        TreePath oTree = this.jSkillTree.getAnchorSelectionPath();

        DefaultMutableTreeNode oNode = (DefaultMutableTreeNode)oTree.getPathComponent((oTree.getPathCount()-1));
        if (oNode == jSkillsAbilitiesNode) {
          nSkillType = TableSkills.TYPE_ABILITY;
        }
        if (oNode == jSkillsProfsNode) {
          nSkillType = TableSkills.TYPE_PROF;
        }
        if (oNode == jSkillsTalentsNode) {
          nSkillType = TableSkills.TYPE_TALENT;
        }
        if (oNode == jSkillsNode) {
          nSkillType = TableSkills.TYPE_SKILL;
        }


    TableSkills.DoNewSkill(oParent.fPlayerGroupFrame,oParent,this,null,nSkillType);
  }

  void jMenuItemSkillRemove_actionPerformed(ActionEvent e) {
    Skills oS = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jSkillTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Skills) {
        oS = (Skills) oNode.getUserObject();
        if (oParent.AskYN(oParent.fPlayerGroupFrame,
                          "Remove skill " + oS.sName + " from player?")) {
          aSkills.remove(oS);
          oNode.removeFromParent();
          mTreeModel.reload(jSkillsAbilitiesNode);
          mTreeModel.reload(jSkillsProfsNode);
          mTreeModel.reload(jSkillsTalentsNode);
          mTreeModel.reload(jSkillsNode);
        }
      }
    }

  }

  void jMenuItemSkillChange_actionPerformed(ActionEvent e) {
    Skills oS = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jSkillTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Skills) {
        oS = (Skills) oNode.getUserObject();
        DialogPlayerSkillEdit.LoadDialog(oParent.fPlayerGroupFrame, oParent, this, oS, oNode);
      }
    }
  }

  void jMenuItemSkillEdit_actionPerformed(ActionEvent e) {
    Skills oS = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jSkillTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Skills) {
        oS = (Skills) oNode.getUserObject();
        TableSkills.DoNewSkill(oParent.fPlayerGroupFrame,oParent,this,oS,-1);
      }
    }
  }

  // quirk popup menu commands
  void jMenuItemQuirkAdd_actionPerformed(ActionEvent e) {
    TableQuirks.DoNewQuirk(oParent.fPlayerGroupFrame,oParent,this,null);
  }

  void jMenuItemQuirkRemove_actionPerformed(ActionEvent e) {
    Quirks oQ = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jQuirkTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Quirks) {
        oQ = (Quirks) oNode.getUserObject();
        if (oParent.AskYN(oParent.fPlayerGroupFrame,
                          "Remove quirk " + oQ.sName + " from player?")) {
          aQuirks.remove(oQ);
          oNode.removeFromParent();
          mQuirkTreeModel.reload(jQuirksNode);
        }
      }
    }

  }

  void jMenuItemQuirkChange_actionPerformed(ActionEvent e) {
    Quirks oQ = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jQuirkTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Quirks) {
        oQ = (Quirks) oNode.getUserObject();
        DialogPlayerQuirkEdit.LoadDialog(oParent.fPlayerGroupFrame, oParent, this, oQ, oNode);
      }
    }
  }

  void jMenuItemQuirkEdit_actionPerformed(ActionEvent e) {
    Quirks oQ = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jQuirkTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Quirks) {
        oQ = (Quirks) oNode.getUserObject();
        TableQuirks.DoNewQuirk(oParent.fPlayerGroupFrame,oParent,this,oQ);
      }
    }
  }

  // end quirk popup menu commands
  void updateButtonPressed(HackSackFrame oParent, TablePlayer oPlayer,
                           String sButtonName) {

    if (sButtonName.equalsIgnoreCase("ViewPlayerLog")) {
      oParent.ShowDoneFrame(oParent.fPlayerGroupFrame,"Log: "+sCharacter,jPlayerLog.getText());
    } else
    if (sButtonName.equalsIgnoreCase("Skill")) {
      TableSkills.DoNewSkill(oParent.fPlayerGroupFrame, oParent, oPlayer,null,-1);
    }
    else
    if (sButtonName.equalsIgnoreCase("Gear")) {
      TableGear.DoNewGear(oParent.fPlayerGroupFrame, oParent, oPlayer,null,null);
    }
    else
    if (sButtonName.equalsIgnoreCase("Quirk")) {
      TableQuirks.DoNewQuirk(oParent.fPlayerGroupFrame, oParent, oPlayer,null);
    }
    else
    if (sButtonName.equalsIgnoreCase("Class")) {
      TableClass.DoNewClass(oParent.fPlayerGroupFrame, oParent, oPlayer, null);
    }
    else
    if (sButtonName.equalsIgnoreCase("FindMyTarget")) {
      getMyTargetOnBattleSheet();
    }
    else
    if (sButtonName.equalsIgnoreCase("RemovePlayer")) {
      if (oParent.AskYN(oParent.fPlayerGroupFrame,
                        "Are you sure you want to remove player " +
                        oPlayer.sCharacter + "?")) {
        oParent.gplGroupLog.lPlayers.remove(oPlayer);

        // delete player to the party list
        oParent.fPlayerGroupFrame.mPartyPlayerList.removeElement(oPlayer);
        // refresh this cause player dropped out
        oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
        oParent.fPlayerGroupFrame.jPlayerDetailsPanel.removeAll();
        // clear this out...
        oParent.fPlayerGroupFrame.titledBorder4.setTitle("Select a Player");

//        oParent.fPlayerGroupFrame.RefreshPlayerGroupSheet();
        oParent.ShowDone(oParent.fPlayerGroupFrame,
                         "Removed " + oPlayer.sCharacter + " from group.");
      }
    }
    else
    if (sButtonName.equalsIgnoreCase("SavePlayer")) {
      // incase they go straight from the creator pad to the
      // player sheet and save there instead
      if (oPlayer.lPlayerID <= 0) {
        oPlayer.lPlayerID = System.currentTimeMillis();
      }
//      SavePlayer.AskWhereToSavePlayer(oParent, bSavePlayer, oPlayer);
      TablePlayer.AskWhereToSavePlayer(oParent,bSavePlayer,oPlayer);

    }
    else
    if (sButtonName.equalsIgnoreCase("HonorAward")) {
      // load up the dialog and such...
      DialogGiveHonorAward dlg = new DialogGiveHonorAward(oParent, oPlayer);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Grant Honor Award");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.show();

    }
    else
    if (sButtonName.equalsIgnoreCase("EXPAward")) {
      DialogGiveEXPBonus dlg = new DialogGiveEXPBonus(oParent, oPlayer);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setTitle("Grant Individual EXP Award");
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.show();

    }

  }

  void jSkillTree_mouseClicked(MouseEvent e) {
    Skills oS = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jSkillTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Skills)
        oS = (Skills) oNode.getUserObject();

        // right click on item and we do these
        if (e.getButton() == e.BUTTON3) {
          if (oS != null) {
            jMenuItemSkillAdd.setEnabled(true);
            jMenuItemSkillRemove.setEnabled(true);
            jMenuItemSkillChange.setEnabled(true);
            jMenuItemSkillEdit.setEnabled(true);
          } else {
            jMenuItemSkillAdd.setEnabled(true);
            jMenuItemSkillRemove.setEnabled(false);
            jMenuItemSkillChange.setEnabled(false);
            jMenuItemSkillEdit.setEnabled(false);
          }
          jPopupMenuSkillTree.show(jSkillTree,e.getX(),e.getY());
        }
        // single click and we just display description
        if (e.getClickCount() != 2 && oS != null) {
          if (oS.sDesc.length() > 0) {
            lSkillTreeSelection.setText(oS.sDesc);
          }
          else {
            lSkillTreeSelection.setText("No description avaliable.");
          }
        }
      // double click and we load up change mode
      if (e.getClickCount() >= 2 && oS != null) {
        DialogPlayerSkillEdit.LoadDialog(oParent.fPlayerGroupFrame,oParent,this,oS,oNode);
      }
    }

  }

  void jSkillTree_valueChanged(TreeSelectionEvent e) {

  }

  /*
      Skills getSkillFromTreeNode(DefaultMutableTreeNode oSearchForThisNode) {
      Skills oReturn = null;
      for (int i=0;i<this.aSkills.size() && oReturn==null;i++) {
        Skills oS = (Skills)this.aSkills.get(i);
        if (oS.oNode == oSearchForThisNode)
          oReturn = oS;
      }
      return oReturn;
    }
   */

  void jQuirkTree_mouseClicked(MouseEvent e) {
    Quirks oQ = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jQuirkTree.getLastSelectedPathComponent();
    if (oNode != null) {
      if (oNode.getUserObject() instanceof Quirks)
        oQ = (Quirks) oNode.getUserObject();

      if (e.getButton() == e.BUTTON3) {
        if (oQ != null) {
          jMenuItemQuirkAdd.setEnabled(true);
          jMenuItemQuirkRemove.setEnabled(true);
          jMenuItemQuirkChange.setEnabled(true);
          jMenuItemQuirkEdit.setEnabled(true);
        } else {
          jMenuItemQuirkAdd.setEnabled(true);
          jMenuItemQuirkRemove.setEnabled(false);
          jMenuItemQuirkChange.setEnabled(false);
          jMenuItemQuirkEdit.setEnabled(false);
        }
        jPopupMenuQuirkTree.show(jQuirkTree,e.getX(),e.getY());
      }

      if (e.getClickCount() >= 2 && oQ != null) {
        DialogPlayerQuirkEdit.LoadDialog(oParent.fPlayerGroupFrame,oParent,this,oQ,oNode);
      }
      if (e.getClickCount() != 2 && oQ != null) {
        if (oQ.sDesc.length() > 0) {
          lQuirkTreeSelection.setText(oQ.sDesc);
        }
        else {
          lQuirkTreeSelection.setText("No description avaliable.");
        }
      }
    }

  }

  void jQuirkTree_valueChanged(TreeSelectionEvent e) {

  }

  /*
      Skills getSkillFromTreeNode(DefaultMutableTreeNode oSearchForThisNode) {
      Skills oReturn = null;
      for (int i=0;i<this.aSkills.size() && oReturn==null;i++) {
        Skills oS = (Skills)this.aSkills.get(i);
        if (oS.oNode == oSearchForThisNode)
          oReturn = oS;
      }
      return oReturn;
    }
   */

  void jGearTree_mouseClicked(MouseEvent e) {
    Gear oG = null;
    DefaultMutableTreeNode oNode =
        (DefaultMutableTreeNode)this.jGearTree.getLastSelectedPathComponent();
    // this we always allow
    jMenuItemAdd.setEnabled(true);

    if (oNode != null) {
      if (oNode.getUserObject() instanceof Gear) {
        oG = (Gear) oNode.getUserObject();
        if (e.getButton() == e.BUTTON3 && oG != null) {
          // be sure these two are enabled when we have item
          jMenuItemDelete.setEnabled(true);
          jMenuItemChange.setEnabled(true);
          jMenuItemEdit.setEnabled(true);
          // if uncarried we dont show move to uncarried
          if (oG.sLoc.equalsIgnoreCase("NOT-CARRIED")) { // item is not carried
            jMenuItemUnCarried.setEnabled(false);
            jMenuItemCarried.setEnabled(true);
          }
          else { // iitem is carried...
            jMenuItemUnCarried.setEnabled(true);
            jMenuItemCarried.setEnabled(false);
          }

          jPopupMenuGear.show(jGearTree, e.getX(), e.getY());
        }
        else
        if (e.getClickCount() >= 2 && oG != null) {
          DialogPlayerGearEdit.LoadDialog(oParent.fPlayerGroupFrame,oParent,this,oG,oNode);
        }
        if (e.getClickCount() != 2 && oG != null) {
          if (oG.sDesc.length() > 0) {
            lGearTreeSelection.setText(oG.sDesc);
          }
          else {
            lGearTreeSelection.setText("No description avaliable.");

          }
        }
      }
      else // no vaid object selected, lets show "add item" only
      if (e.getButton() == e.BUTTON3) {
        jMenuItemUnCarried.setEnabled(false);
        jMenuItemCarried.setEnabled(false);
        jMenuItemDelete.setEnabled(false);
        jMenuItemChange.setEnabled(false);
        jMenuItemEdit.setEnabled(false);
        jPopupMenuGear.show(jGearTree, e.getX(), e.getY());
      }

    }
  }

  void jGearTree_valueChanged(TreeSelectionEvent e) {

  }

  /*
      Skills getSkillFromTreeNode(DefaultMutableTreeNode oSearchForThisNode) {
      Skills oReturn = null;
      for (int i=0;i<this.aSkills.size() && oReturn==null;i++) {
        Skills oS = (Skills)this.aSkills.get(i);
        if (oS.oNode == oSearchForThisNode)
          oReturn = oS;
      }
      return oReturn;
    }
   */

  void jCoinWeightIncluded_actionPerformed(ActionEvent e) {
    TableEncum.setEncumLabel(this.oParent.lEncumTable, this);
  }

  void updatejMoveSpinner(ChangeEvent e) {
    // recalculate movebase when basemove changed
    TableEncum.setEncumLabel(this.oParent.lEncumTable, this);
  }

  static void clearAttackingThisCreatureList(HackSackFrame oParent) {
    for (int i = 0; i < oParent.gplGroupLog.lPlayers.size(); i++) {
      TablePlayer oPlayer = (TablePlayer) oParent.gplGroupLog.lPlayers.get(i);
      oPlayer.jAttackingThisCreature.removeAllItems();
    }
  }

  void buildAttackingThisCreatureList(ArrayList aCreatures) {
    Object oObject = jAttackingThisCreature.getSelectedItem();
    jAttackingThisCreature.removeAllItems();

    // we have nothing in 0 slot
    jAttackingThisCreature.addItem("NO-TARGET");

    for (int i = 0; i < aCreatures.size(); i++) {
      CreatureCore oC = (CreatureCore) aCreatures.get(i);
      jAttackingThisCreature.addItem(oC);
    }
    jAttackingThisCreature.setSelectedItem(oObject);
    if (jAttackingThisCreature.getSelectedIndex() < 0) {
      jAttackingThisCreature.setSelectedIndex(0);
    }
  }

  // jump to the creature this player has selected as their target
  void getMyTargetOnBattleSheet() {
    Object oObject = jAttackingThisCreature.getSelectedItem();
    if (oObject instanceof CreatureCore) {
      oParent.fBattleSheetFrame.jCreatureList.setSelectedValue(oObject,true);
    }
  }

  // event to run when player changes enemy target on battle sheet
  void jGenericAction_actionPerformed(ActionEvent e,String sName) {
   if (sName.equalsIgnoreCase("jAlignmentSelect"))  {
     nAlignementIndex = jAlignmentSelect.getSelectedIndex();
   } else
   if (sName.equalsIgnoreCase("jAbsent"))  {
     oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
   } else
   if (sName.equalsIgnoreCase("jHireling"))  {
     oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
   }
  }

  // generic focus event
  void jFocusEvent_focusLost(FocusEvent e,String sVar) {
    if (sVar.equalsIgnoreCase("jPlayerName")) {
      sPlayerName = jPlayerName.getText();
    } else
    if (sVar.equalsIgnoreCase("jRace")) {
      sRace = jRace.getText();
    } else
    if (sVar.equalsIgnoreCase("jCharacter")) {
      sCharacter = jCharacter.getText();
    }
  }

  // event to run when player changes enemy target on battle sheet
  void jAttackingThisCreature_actionPerformed(ActionEvent e) {
    Object oObject = jAttackingThisCreature.getSelectedItem();
    int nSelectedIndex = jAttackingThisCreature.getSelectedIndex();
    if (nSelectedIndex < 0) // when you clear the battlesheet disable it also
      bFindMyTarget.setEnabled(false);
    // NO-TARGET
    if (oObject instanceof String) {
      bFindMyTarget.setEnabled(false);
      // now we reset every creatures list of "attacked by"
      // do this because the player might be switching targets
      boolean bDone = false;
      for (int j = 0; j < oParent.lCreatures.size() && !bDone; j++) {
        CreatureCore oCreature = (CreatureCore) oParent.lCreatures.get(j);
        // remove the player if we find it somewhere, we just unselected
        // our target
        if (oCreature.aAttackerList.contains(this)) {
          oCreature.aAttackerList.remove(this);
          // found player, just stop here
          bDone = true;

          // rebuild label now he is gone
          String sLabel = "";
          if (oCreature.aAttackerList.size() <= 0) {
            oCreature.jAttackedBy.setText("None");
          }
          else {
            for (int i = 0; i < oCreature.aAttackerList.size(); i++) {
              TablePlayer oPlayer = (TablePlayer) oCreature.aAttackerList.get(i);
              sLabel += oPlayer.sCharacter;
              // add comma and space if not last one
              if (i + 1 < oCreature.aAttackerList.size()) {
                sLabel += ", ";
              }
            }
            oCreature.jAttackedBy.setText(sLabel);
          }
        }
      }

    }
    else

    // item listed is a Creature, lefts do it.
    if (oObject instanceof CreatureCore) {
      bFindMyTarget.setEnabled(true);
      CreatureCore oC = (CreatureCore) oObject;
      if (!oC.aAttackerList.contains(this)) {
        oC.aAttackerList.add(this);

        // now we reset every creatures list of "attacked by"
        // do this because the player might be switching targets
        for (int j = 0; j < oParent.lCreatures.size(); j++) {
          CreatureCore oCreature = (CreatureCore) oParent.lCreatures.get(j);
          String sLabel = "";
          // if the changed creature != this creature and we show player there
          // remove it from that creatures attackedby list since we only allow
          // one attack target per player
          if (oC != oCreature && oCreature.aAttackerList.contains(this)) {
            oCreature.aAttackerList.remove(this);
          }
          if (oCreature.aAttackerList.size() <= 0) {
            oCreature.jAttackedBy.setText("None");
          }
          else {
            for (int i = 0; i < oCreature.aAttackerList.size(); i++) {
              TablePlayer oPlayer = (TablePlayer) oCreature.aAttackerList.get(i);
              // otherwise set label with player name
              sLabel += oPlayer.sCharacter;
              // add comma and space if not last one
              if (i + 1 < oCreature.aAttackerList.size()) {
                sLabel += ", ";
              }
            }
            oCreature.jAttackedBy.setText(sLabel);
          }
        }
        // end setting creature attackedby labels
      }
    }

  }

  void playerLog(String sLog) {
    Locale currentLocale = new Locale("en", "US");
    Date today;
    String dateOut;
    DateFormat dateFormatter;

    dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,
                                               currentLocale);
    today = new Date();
    dateOut = dateFormatter.format(today);

    int nGYear = Integer.parseInt(oParent.gplGroupLog.jYear.getValue().toString());
    int nGMonth = Integer.parseInt(oParent.gplGroupLog.jMonth.getValue().
                                   toString());
    int nGDay = Integer.parseInt(oParent.gplGroupLog.jDay.getValue().toString());
    String sGameDate = "GYear:" + nGYear + " GMonth:" + nGMonth + " GDay:" +
        nGDay;
    jPlayerLog.append("*" + dateOut + " GameDate: " + sGameDate + ":" + sLog);
  }

  // add new character tab
JPanel getPlayerDisplayPanel() {
     Color cColor = Color.darkGray;
     TablePlayer oPlayer = this;
     FlowLayout fFlowTight = new FlowLayout(FlowLayout.LEFT);
     fFlowTight.setHgap(0);
     fFlowTight.setVgap(0);


     FlowLayout fFlowLoose = new FlowLayout(FlowLayout.LEFT);
     fFlowLoose.setHgap(3);
     fFlowLoose.setVgap(0);

     VerticalFlowLayout fVFlow = new VerticalFlowLayout();
     fVFlow.setHgap(3);
     fVFlow.setVgap(0);
     //fVFlow.setHorizontalFill(true);
     //fVFlow.setVerticalFill(false);

     JPanel jNewPanel = new JPanel(fVFlow);

     jNewPanel.setBackground(cColor);

     Font fFont = new Font("Dialog", Font.BOLD, 12);

     // upper button panel
     JPanel jButtonPanel = new JPanel(fFlowTight);
     jButtonPanel.setBackground(Color.lightGray);
     oPlayer.bSavePlayer.setFont(new Font("Dialog",Font.PLAIN,9));
     oPlayer.bRemovePlayer.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bSavePlayer);
     jButtonPanel.add(oPlayer.bRemovePlayer);

     oPlayer.bAddSkill.setText("Add Skill");
     oPlayer.bAddSkill.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddSkill);

     oPlayer.bAddClass.setText("Add Class");
     oPlayer.bAddClass.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddClass);

     oPlayer.bAddGear.setText("Add Gear");
     oPlayer.bAddGear.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddGear);

     oPlayer.bAddQuirk.setText("Add Quirk");
     oPlayer.bAddQuirk.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddQuirk);

     oPlayer.bAddHonorAward.setText("Honor Award");
     oPlayer.bAddHonorAward.setToolTipText("Grant an individual honor award.");
     oPlayer.bAddHonorAward.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddHonorAward);

     oPlayer.bAddEXPAward.setText("EXP Award");
     oPlayer.bAddEXPAward.setToolTipText("Grant an individual EXP award.");
     oPlayer.bAddEXPAward.setFont(new Font("Dialog",Font.PLAIN,9));
     jButtonPanel.add(oPlayer.bAddEXPAward);
     jNewPanel.add(jButtonPanel);

     // ooC Panel, Player name, Absent/Hireling flags
     JPanel jOOCPanel = new JPanel(fFlowLoose);
     jOOCPanel.setBackground(Color.darkGray);

     JLabel jpName1 = new JLabel("Player");
     jpName1.setForeground(Color.GREEN);
     jOOCPanel.add(jpName1);

     oPlayer.jPlayerName.setBackground(cColor);
     oPlayer.jPlayerName.setForeground(Color.WHITE);
     oPlayer.jPlayerName.setFont(fFont);
     oPlayer.jPlayerName.setText(oPlayer.sPlayerName);
     jOOCPanel.add(oPlayer.jPlayerName);

     // absent
     oPlayer.jAbsent.setBackground(cColor);
     oPlayer.jAbsent.setForeground(Color.GREEN);
//     oPlayer.jAbsent.setFont(fFont);
     jOOCPanel.add(oPlayer.jAbsent);

     //hireling
     oPlayer.jHireling.setBackground(cColor);
     oPlayer.jHireling.setForeground(Color.GREEN);
//     oPlayer.jHireling.setFont(fFont);
     jOOCPanel.add(oPlayer.jHireling);

     jNewPanel.add(jOOCPanel);

     // info panel (name/race/etc)
     JPanel jInfoPanel = new JPanel(fFlowLoose);
     jInfoPanel.setBackground(Color.darkGray);

  // dont need this, listed in border
     JLabel jcName1 = new JLabel("Name");
     jcName1.setForeground(Color.GREEN);
     jInfoPanel.add(jcName1);

     oPlayer.jCharacter.setBackground(cColor);
     oPlayer.jCharacter.setForeground(Color.WHITE);
     oPlayer.jCharacter.setFont(fFont);
     oPlayer.jCharacter.setText(oPlayer.sCharacter);
     jInfoPanel.add(oPlayer.jCharacter);

     JLabel jRace1 = new JLabel("Race");
     jRace1.setForeground(Color.GREEN);
     jInfoPanel.add(jRace1);

     oPlayer.jRace.setBackground(cColor);
     oPlayer.jRace.setForeground(Color.WHITE);
     oPlayer.jRace.setFont(fFont);
     oPlayer.jRace.setText(oPlayer.sRace);
     jInfoPanel.add(oPlayer.jRace);

     JLabel jAlign1 = new JLabel("Alignment");
     jAlign1.setForeground(Color.GREEN);
     jInfoPanel.add(jAlign1);

     oPlayer.jAlignmentSelect.setFont(fFont);
//     oPlayer.jAlignmentSelect.setBackground(cColor);
//     oPlayer.jAlignmentSelect.setForeground(Color.WHITE);
     oPlayer.jAlignmentSelect.setSelectedIndex(oPlayer.nAlignementIndex);
     jInfoPanel.add(oPlayer.jAlignmentSelect);

     JLabel jMove1 = new JLabel("Move");
     jMove1.setForeground(Color.GREEN);
     jInfoPanel.add(jMove1);

     oPlayer.jMove.setFont(fFont);
     oPlayer.jMove.setBackground(cColor);
     oPlayer.jMove.setForeground(Color.WHITE);
     jInfoPanel.add(oPlayer.jMove);
     jInfoPanel.add(oPlayer.jMoveAdjustedLabel);

     JLabel jSize = new JLabel("Size");
     jSize.setForeground(Color.GREEN);
     jInfoPanel.add(jSize);
     oPlayer.jSizeComboBox.setFont(fFont);
//     oPlayer.jSizeComboBox.setBackground(cColor);
//     oPlayer.jSizeComboBox.setForeground(Color.WHITE);
     jInfoPanel.add(oPlayer.jSizeComboBox);

     jNewPanel.add(jInfoPanel);

     // this panel holds HP/AC/SAVES
     //BoxLayout2 bLayout = new BoxLayout2();
     GridLayout bLayout = new GridLayout();
     //bLayout.setAxis(BoxLayout.X_AXIS);
     JPanel jSecondPanel = new JPanel(bLayout);
     jSecondPanel.setBackground(Color.darkGray);

     // health/HP
     JPanel jHPPanel = new JPanel(fVFlow);
     jHPPanel.setBackground(Color.darkGray);
     Border hpBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
         Color.WHITE,
         Color.WHITE,
         Color.WHITE,
         Color.WHITE);
     TitledBorder titleHPBorder = new TitledBorder(hpBorder,
                                                   "Hit Points & Honor");
     titleHPBorder.setTitleColor(Color.WHITE);
     jHPPanel.setBorder(titleHPBorder);

     JPanel jHPPanel0 = new JPanel(fVFlow);
     jHPPanel0.setBackground(Color.darkGray);
     JPanel jHPPanel1 = new JPanel(fFlowTight);
     jHPPanel1.setBackground(Color.darkGray);
     oPlayer.jHealthLabel.setText(oPlayer.jHealthMax.getModel().getValue().
                                  toString());
     oPlayer.jHealthLabel.setFont(fFont);
     oPlayer.jHealthLabel.setForeground(Color.WHITE);
     jHPPanel1.add(oPlayer.jHealthLabel);
     JLabel jHealth1 = new JLabel(" HP Current");
     jHealth1.setToolTipText("Players current hitpoints.");
     jHealth1.setForeground(Color.GREEN);
     jHPPanel1.add(jHealth1);
     jHPPanel0.add(jHPPanel1);

     JPanel jHPPanel2 = new JPanel(fFlowLoose);
     jHPPanel2.setBackground(Color.darkGray);
     oPlayer.jAdjHealth.setPreferredSize(new Dimension(60, 20));
     jHPPanel2.add(oPlayer.jAdjHealth);

     JLabel jHealthA = new JLabel("HP Adjust");
     jHealthA.setToolTipText("Enter health adjustment, -10 or +10 and press enter");
     jHealthA.setForeground(Color.GREEN);
     jHPPanel2.add(jHealthA);
     jHPPanel0.add(jHPPanel2);

     JPanel jHPPanel3 = new JPanel(fFlowLoose);
     jHPPanel3.setBackground(Color.darkGray);
     oPlayer.jHealthMax.setPreferredSize(new Dimension(60, 20));
     jHPPanel3.add(oPlayer.jHealthMax);
     JLabel jMaxHealth = new JLabel("HP Max");
     jMaxHealth.setToolTipText("Max HP for player, if changed resets HP Current to new value.");
     jMaxHealth.setForeground(Color.GREEN);
     jHPPanel3.add(jMaxHealth);
     jHPPanel0.add(jHPPanel3);

     JPanel jHPPanel4 = new JPanel(fFlowLoose);
     jHPPanel4.setBackground(Color.darkGray);
     oPlayer.jHonor.setPreferredSize(new Dimension(60, 20));
     jHPPanel4.add(oPlayer.jHonor);

     JLabel jCurHonor = new JLabel("Honor");
     jCurHonor.setForeground(Color.GREEN);
     jHPPanel4.add(jCurHonor);
     jHPPanel0.add(jHPPanel4);

     JPanel jHPPanel5 = new JPanel(fFlowLoose);
     jHPPanel5.setBackground(Color.darkGray);
     oPlayer.jHonorMod.setPreferredSize(new Dimension(60, 20));
     jHPPanel5.add(oPlayer.jHonorMod);
     JLabel jModHonor = new JLabel("Honor Adjust");
     jModHonor.setForeground(Color.GREEN);
     jHPPanel5.add(jModHonor);
     jHPPanel0.add(jHPPanel5);
     jHPPanel.add(jHPPanel0);

     jSecondPanel.add(jHPPanel);

     // AC
      JPanel jACPanel = new JPanel(fVFlow);
      jACPanel.setBackground(Color.darkGray);
      Border acBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE);

      TitledBorder titleACBorder = new TitledBorder(acBorder, "AC");
      titleACBorder.setTitleColor(Color.WHITE);
      jACPanel.setBorder(titleACBorder);

      JPanel jACPanel1 = new JPanel(fFlowLoose);
      jACPanel1.setBackground(Color.darkGray);
      oPlayer.nAC[oPlayer.AC_NORMAL].setPreferredSize(new Dimension(60, 20));
      jACPanel1.add(oPlayer.nAC[oPlayer.AC_NORMAL]);
      jACPanel.add(jACPanel1);

      JLabel jACN1 = new JLabel("Normal");
      jACN1.setForeground(Color.GREEN);
      jACPanel1.add(jACN1);

      JPanel jACPanel2 = new JPanel(fFlowLoose);
      jACPanel2.setBackground(Color.darkGray);
      oPlayer.nAC[oPlayer.AC_SUPRISED].setPreferredSize(new Dimension(60, 20));
      jACPanel2.add(oPlayer.nAC[oPlayer.AC_SUPRISED]);
      jACPanel.add(jACPanel2);

      JLabel jACS1 = new JLabel("Suprised");
      jACS1.setForeground(Color.GREEN);
      jACPanel2.add(jACS1);

      JPanel jACPanel3 = new JPanel(fFlowLoose);
      jACPanel3.setBackground(Color.darkGray);
      oPlayer.nAC[oPlayer.AC_SHIELDLESS].setPreferredSize(new Dimension(60, 20));
      jACPanel3.add(oPlayer.nAC[oPlayer.AC_SHIELDLESS]);
      jACPanel.add(jACPanel3);

      JLabel jACH1 = new JLabel("Shieldless");
      jACH1.setForeground(Color.GREEN);
      jACPanel3.add(jACH1);

      JPanel jACPanel4 = new JPanel(fFlowLoose);
      jACPanel4.setBackground(Color.darkGray);
      oPlayer.nAC[oPlayer.AC_REAR].setPreferredSize(new Dimension(60, 20));
      jACPanel4.add(oPlayer.nAC[oPlayer.AC_REAR]);
      jACPanel.add(jACPanel4);

      JLabel jACR1 = new JLabel("Rear");
      jACR1.setForeground(Color.GREEN);
      jACPanel4.add(jACR1);

      jSecondPanel.add(jACPanel);

      // --------- saves
      JPanel jSavePanel = new JPanel(fVFlow);
      jSavePanel.setBackground(Color.darkGray);
      Border saveBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE);
      TitledBorder titleSaveBorder = new TitledBorder(saveBorder, "Saves");
      titleSaveBorder.setTitleColor(Color.WHITE);
      jSavePanel.setBorder(titleSaveBorder);

      for (int i = 0; i < oPlayer.aSaves.size(); i++) {
        Saves oSave = (Saves) oPlayer.aSaves.get(i);
        JPanel jSavesType = new JPanel(fFlowLoose);
        jSavesType.setBackground(Color.darkGray);

        oSave.jSave.setPreferredSize(new Dimension(35,20));
        jSavesType.add(oSave.jSave);
// we dont use any of these yet
//        jSavesType.add(oSave.jSaveMod);
//        jSavesType.add(oSave.jSaveOther);
        jSavePanel.add(jSavesType);

        JLabel jName = new JLabel(oSave.sName);
        jName.setForeground(Color.GREEN);
        jName.setFont(new Font("Dialog",Font.PLAIN,9));
        jSavesType.add(jName);
      }
      jSecondPanel.add(jSavePanel);
      jNewPanel.add(jSecondPanel);


   //---- ability scores
   JPanel jAbilityScoresPanel = new JPanel(fVFlow);
   jAbilityScoresPanel.setBackground(Color.darkGray);
   Border jAbilityScoresPanelBorder = BorderFactory.createBevelBorder(
       BevelBorder.LOWERED, Color.WHITE,
       Color.WHITE,
       Color.WHITE,
       Color.WHITE);
   TitledBorder titlejAbilityScoresPanelBorder = new TitledBorder(
       jAbilityScoresPanelBorder, "Ability Scores");
   titlejAbilityScoresPanelBorder.setTitleColor(Color.WHITE);
   jAbilityScoresPanel.setBorder(titlejAbilityScoresPanelBorder);

   Font fSmallerFont = new Font("Dialog", Font.PLAIN, 9);
   boolean bFlip = false;
   Color cFlipColor = Color.darkGray;
   for (int i = 0; i < oPlayer.aAbilities.size(); i++) {
     Abilities oA = (Abilities) oPlayer.aAbilities.get(i);

     // force text update
     oA.updatedSpinner(oParent,oPlayer,"",oA);
     // toggle colours between ability scores
     cFlipColor = (bFlip?Color.darkGray:Color.gray);
     bFlip=!bFlip;

     JLabel jAbilityName = new JLabel(oA.sName);
     jAbilityName.setForeground(Color.GREEN);
     jAbilityName.setFont(fFont);
     jAbilityName.setText(oA.sName.substring(0, 3));

     JPanel jAbilityNameScorePanel = new JPanel(fVFlow);
     jAbilityNameScorePanel.setBackground(cFlipColor);

     JPanel jRemain = new JPanel(fFlowLoose);
     jRemain.setBackground(cFlipColor);

     oA.jAdjScore.setPreferredSize(new Dimension(40,20));
     oA.jAdjPercent.setPreferredSize(new Dimension(40,20));
     oA.jAdjPercent.setForeground(Color.WHITE);
     oA.jAdjScore.setForeground(Color.WHITE);
     oA.jAdjPercent.setBackground(cFlipColor);
     oA.jAdjScore.setBackground(cFlipColor);

     jAbilityNameScorePanel.add(jAbilityName);
     jAbilityNameScorePanel.add(oA.jAdjScore);
     jRemain.add(jAbilityNameScorePanel);

//     JLabel jSlash = new JLabel("/");
//     jSlash.setFont(fSmallerFont);
//     jSlash.setForeground(Color.green);
//     jRemain.add(jSlash);

     jRemain.add(oA.jAdjPercent);
     JLabel jPercent = new JLabel("%");
     jPercent.setForeground(Color.green);
     jPercent.setFont(fSmallerFont);
     jRemain.add(jPercent);

     JPanel jModsPanel = new JPanel(fFlowLoose);
     jModsPanel.setBackground(cFlipColor);
     for (int j = 0; j < oA.aMods.size(); j++) {
       AbilityMods oM = (AbilityMods) oA.aMods.get(j);
//       JLabel jName = new JLabel(oM.sName);
       oM.jName.setFont(fSmallerFont);
       oM.jName.setForeground(Color.white);
       jModsPanel.add(oM.jName);
       oM.jMod.setFont(fSmallerFont);
       jModsPanel.add(oM.jMod);
       jRemain.add(jModsPanel);
     }
     jAbilityScoresPanel.add(jRemain);
   }
   jNewPanel.add(jAbilityScoresPanel);



// -----------------------------------------------------TREES
   // skill tree

   //BoxLayout2 bLayout2 = new BoxLayout2();
   GridLayout bLayout2 = new GridLayout();
   //bLayout2.setAxis(BoxLayout.X_AXIS);
   JPanel jTreesSkill_Quirk = new JPanel(bLayout2);
//     JPanel jSkillInfoPanel = new JPanel(new GridLayout(0,1));
     JPanel jSkillInfoPanel = new JPanel(fFlowLoose);
     jSkillInfoPanel.setBackground(Color.gray);
     jSkillInfoPanel.add(oPlayer.lSkillTreeSelection);

     oPlayer.jSkillsAbilitiesNode.removeAllChildren();
     oPlayer.jSkillsProfsNode.removeAllChildren();
     oPlayer.jSkillsNode.removeAllChildren();
     oPlayer.jSkillsTalentsNode.removeAllChildren();
     for (int i = 0; i < oPlayer.aSkills.size(); i++) {
       Skills oSkill = (Skills) oPlayer.aSkills.get(i);
       DefaultMutableTreeNode oChildNode = null;
       switch (oSkill.nSkillType) {
         case 0:
           oChildNode = new DefaultMutableTreeNode("["+
                            oSkill.jSkillSpinner.getValue().toString()+"%] "+
                            oSkill.sName);
           oPlayer.jSkillsNode.add(oChildNode);
           break;
         case 1:
           oChildNode = new DefaultMutableTreeNode(oSkill.sName);
           oPlayer.jSkillsTalentsNode.add(oChildNode);
           break;
         case 2:
           oChildNode = new DefaultMutableTreeNode(oSkill.sName);
           oPlayer.jSkillsProfsNode.add(oChildNode);
           break;
         case 3:
           oChildNode = new DefaultMutableTreeNode(oSkill.sName);
           oPlayer.jSkillsAbilitiesNode.add(oChildNode);
           break;
       }
       if (oChildNode != null) {
         oChildNode.setUserObject(oSkill);
       }

     }
//     if (oPlayer.jSkillsAbilitiesNode.getChildCount() > 0) {
       oPlayer.jSkillsRootNode.add(oPlayer.jSkillsAbilitiesNode);
//     }
//     if (oPlayer.jSkillsTalentsNode.getChildCount() > 0) {
       oPlayer.jSkillsRootNode.add(oPlayer.jSkillsTalentsNode);
//     }
//     if (oPlayer.jSkillsProfsNode.getChildCount() > 0) {
       oPlayer.jSkillsRootNode.add(oPlayer.jSkillsProfsNode);
//     }
//     if (oPlayer.jSkillsNode.getChildCount() > 0) {
       oPlayer.jSkillsRootNode.add(oPlayer.jSkillsNode);
//     }
     oPlayer.jSkillTree.expandRow(0);

     JPanel jSkillTreePanel = new JPanel(new BorderLayout());
     JScrollPane jSkillTreeScrollPanel = new JScrollPane();
     jSkillTreeScrollPanel.getViewport().add(oPlayer.jSkillTree, null);

     oPlayer.jSkillTree.setBackground(Color.darkGray);

     oPlayer.oSkillTreeRenderer.setBackground(Color.darkGray);
     oPlayer.oSkillTreeRenderer.setForeground(Color.green);

     oPlayer.oSkillTreeRenderer.setBackgroundNonSelectionColor(Color.darkGray);
     oPlayer.oSkillTreeRenderer.setTextNonSelectionColor(Color.green);

     oPlayer.oSkillTreeRenderer.setBackgroundSelectionColor(Color.GRAY);
     oPlayer.oSkillTreeRenderer.setTextSelectionColor(Color.YELLOW);

     oPlayer.jSkillTree.getSelectionModel().
         setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

//     jSkillTreePanel.setPreferredSize(new Dimension(350,250)); // width, height
     jSkillTreePanel.add(jSkillTreeScrollPanel,BorderLayout.CENTER);
     jSkillTreePanel.add(jSkillInfoPanel,BorderLayout.SOUTH);
     jTreesSkill_Quirk.add(jSkillTreePanel);

     // not sure why, but for some reason I must reload the tree at least
     // once so the deleted skill nodes reload will work the very first reload.
     oPlayer.mTreeModel.reload(oPlayer.jSkillsRootNode);
     // end skills tree

     // quirks tree
   JPanel jQuirkInfoPanel = new JPanel(new GridLayout(0,1));
   jQuirkInfoPanel.setBackground(Color.gray);
   jQuirkInfoPanel.add(oPlayer.lQuirkTreeSelection);

   oPlayer.jQuirksNode.removeAllChildren();
   oPlayer.jQuirksRootNode.removeAllChildren();
   for (int i=0;i<oPlayer.aQuirks.size();i++) {
     Quirks oQuirk = (Quirks)oPlayer.aQuirks.get(i);
     DefaultMutableTreeNode oChildNode = new
         DefaultMutableTreeNode(oQuirk.sName);
     oChildNode.setUserObject(oQuirk);
     oPlayer.jQuirksNode.add(oChildNode);
   }
   oPlayer.jQuirksRootNode.add(oPlayer.jQuirksNode);
   oPlayer.jQuirkTree.expandRow(0);
   JPanel jQuirkTreePanel = new JPanel(new BorderLayout());
   JScrollPane jQuirkTreeScrollPanel = new JScrollPane();
   jQuirkTreeScrollPanel.getViewport().add(oPlayer.jQuirkTree, null);

   oPlayer.jQuirkTree.setBackground(Color.darkGray);

   oPlayer.oQuirkTreeRenderer.setBackground(Color.darkGray);
   oPlayer.oQuirkTreeRenderer.setForeground(Color.green);

   oPlayer.oQuirkTreeRenderer.setBackgroundNonSelectionColor(Color.darkGray);
   oPlayer.oQuirkTreeRenderer.setTextNonSelectionColor(Color.green);

   oPlayer.oQuirkTreeRenderer.setBackgroundSelectionColor(Color.GRAY);
   oPlayer.oQuirkTreeRenderer.setTextSelectionColor(Color.YELLOW);

   oPlayer.jQuirkTree.getSelectionModel().
       setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

//     jQuirkTreePanel.setPreferredSize(new Dimension(350,250)); // width, height
   jQuirkTreePanel.add(jQuirkTreeScrollPanel,BorderLayout.CENTER);
   jQuirkTreePanel.add(jQuirkInfoPanel,BorderLayout.SOUTH);
   jTreesSkill_Quirk.add(jQuirkTreePanel);
//     jNewPanel.add(jQuirkTreePanel);
   // not sure why, but for some reason I must reload the tree at least
   // once so the deleted skill nodes reload will work the very first reload.
   oPlayer.mQuirkTreeModel.reload(oPlayer.jQuirksRootNode);
   // end quirks tree

   jNewPanel.add(jTreesSkill_Quirk);

   //BoxLayout2 bLayout3 = new BoxLayout2();
   GridLayout bLayout3 = new GridLayout();
   //bLayout3.setAxis(BoxLayout.X_AXIS);
   JPanel jGear_Coins = new JPanel(bLayout3);


     // gear tree
     JPanel jGearInfoPanel = new JPanel(fFlowLoose);
     jGearInfoPanel.setBackground(Color.gray);

     oPlayer.jGearRootNode.removeAllChildren();
     oPlayer.jGearNode.removeAllChildren();
     oPlayer.jGearWornNode.removeAllChildren();
     oPlayer.jGearUnCarriedNode.removeAllChildren();
     for (int i=0;i<oPlayer.aGear.size();i++) {
       Gear oG =(Gear)oPlayer.aGear.get(i);
       DefaultMutableTreeNode oChildNode = new DefaultMutableTreeNode(oG.sName);
       oChildNode.setUserObject(oG);
       if (oG.sLoc.equalsIgnoreCase("NOT-CARRIED"))
         oPlayer.jGearUnCarriedNode.add(oChildNode);
       else
         oPlayer.jGearNode.add(oChildNode);
     }
     TableEncum.setEncumLabel(oParent.lEncumTable, oPlayer);
     jGearInfoPanel.add(oPlayer.jEncumValue);
     jGearInfoPanel.add(oPlayer.lGearTreeSelection);

     oPlayer.jGearRootNode.add(oPlayer.jGearNode);
     oPlayer.jGearRootNode.add(oPlayer.jGearUnCarriedNode);
     oPlayer.jGearRootNode.add(oPlayer.jGearWornNode);
     oPlayer.jGearTree.expandRow(0);
     JPanel jGearTreePanel = new JPanel(new BorderLayout());
     JScrollPane jGearTreeScrollPanel = new JScrollPane();
     jGearTreeScrollPanel.getViewport().add(oPlayer.jGearTree, null);

     oPlayer.jGearTree.setBackground(Color.darkGray);

     oPlayer.oGearTreeRenderer.setBackground(Color.darkGray);
     oPlayer.oGearTreeRenderer.setForeground(Color.green);

     oPlayer.oGearTreeRenderer.setBackgroundNonSelectionColor(Color.darkGray);
     oPlayer.oGearTreeRenderer.setTextNonSelectionColor(Color.green);

     oPlayer.oGearTreeRenderer.setBackgroundSelectionColor(Color.GRAY);
     oPlayer.oGearTreeRenderer.setTextSelectionColor(Color.YELLOW);

     oPlayer.jGearTree.getSelectionModel().
         setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

//     jGearTreePanel.setPreferredSize(new Dimension(350,250)); // width, height
     jGearTreePanel.add(jGearTreeScrollPanel,BorderLayout.CENTER);
     jGearTreePanel.add(jGearInfoPanel,BorderLayout.SOUTH);
//     jFourthPanel.add(jGearTreePanel);
     jGear_Coins.add(jGearTreePanel);

     // not sure why, but for some reason I must reload the tree at least
     // once so the deleted skill nodes reload will work the very first reload.
     oPlayer.mGearTreeModel.reload(oPlayer.jGearRootNode);
     // end gear tree


     //-- Coins
     JPanel jCoinsPanel = new JPanel(fVFlow);
      jCoinsPanel.setBackground(Color.darkGray);
      Border jCoinsPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
          LOWERED, Color.WHITE,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE);
      TitledBorder titlejCoinsPanelBorder = new TitledBorder(jCoinsPanelBorder,
          "Coins");
      titlejCoinsPanelBorder.setTitleColor(Color.WHITE);
      jCoinsPanel.setBorder(titlejCoinsPanelBorder);

      for (int i = 0; i < oPlayer.aCoins.size(); i++) {
        Coins oCoin = (Coins) oPlayer.aCoins.get(i);
        JPanel jCoinType = new JPanel(fFlowLoose);
        jCoinType.setBackground(Color.darkGray);

        oCoin.jMod.setPreferredSize(new Dimension(60, 20));
        jCoinType.add(oCoin.jMod);

        JLabel jName = new JLabel(oCoin.sName);
        jName.setForeground(Color.GREEN);
        jCoinType.add(jName);

        jCoinsPanel.add(jCoinType);
      }
      JPanel jCoinCarried = new JPanel(fFlowLoose);
      jCoinCarried.setBackground(Color.darkGray);
      jCoinCarried.add(oPlayer.jCoinWeightIncluded);
      jCoinsPanel.add(jCoinCarried);
      jGear_Coins.add(jCoinsPanel);

      jNewPanel.add(jGear_Coins);


      //-- Classes
      JPanel jClassPanel = new JPanel(fVFlow);
      jClassPanel.setBackground(Color.darkGray);
      Border jClassPanelBorder = BorderFactory.createBevelBorder(BevelBorder.
          LOWERED, Color.WHITE,
          Color.WHITE,
          Color.WHITE,
          Color.WHITE);
      TitledBorder titlejClassPanelBorder = new TitledBorder(jClassPanelBorder,
          "Classes");
      titlejClassPanelBorder.setTitleColor(Color.WHITE);
      jClassPanel.setBorder(titlejClassPanelBorder);

      if (oPlayer.aClass.size() <= 0) {
        JLabel jNone = new JLabel("******NONE******");
        jNone.setForeground(Color.GREEN);
        jClassPanel.add(jNone);
      }
      else {
        for (int i = 0; i < oPlayer.aClass.size(); i++) {
          Class oClass = (Class) oPlayer.aClass.get(i);

          JPanel jClassListPanel1 = new JPanel(fFlowLoose);
          jClassListPanel1.setBackground(Color.darkGray);

          oClass.bButton.setText("-");
          oClass.bButton.setToolTipText("Remove this class from player.");
          oClass.bButton.setSize(10, 10);
          oClass.bDetailsButton.setSize(10, 10);
          jClassListPanel1.add(oClass.bButton);
          jClassListPanel1.add(oClass.bDetailsButton);

          JLabel jName = new JLabel(oClass.sName);
          jName.setFont(new Font("Dialog", Font.BOLD, 12));
          jName.setForeground(Color.WHITE);
          jClassListPanel1.add(jName);

          JLabel jLevelLabel = new JLabel("Level");
          jLevelLabel.setForeground(Color.GREEN);
          jClassListPanel1.add(jLevelLabel);

          oClass.jLevel.setSize(30, 20);
          jClassListPanel1.add(oClass.jLevel);

          JLabel jEXPLabel = new JLabel("EXP");
          jEXPLabel.setForeground(Color.GREEN);
          jClassListPanel1.add(jEXPLabel);

          oClass.jEXP.setSize(60, 20);
          jClassListPanel1.add(oClass.jEXP);

          JLabel jEXPLabel2 = new JLabel("EXP Bonus");
          jEXPLabel2.setForeground(Color.GREEN);
          jEXPLabel2.setToolTipText("Percent bonus to EXP gained. 0-100%");
          jClassListPanel1.add(jEXPLabel2);

          oClass.jEXPBonus.setSize(60, 20);
          jClassListPanel1.add(oClass.jEXPBonus);

          jClassPanel.add(jClassListPanel1);
        }
      }
    jNewPanel.add(jClassPanel);


     //-- player log area
//     JPanel jLogPanel = new JPanel(new BorderLayout());
//     jLogPanel.setMaximumSize(new Dimension(200,400));

//     JScrollPane jScrollPanel = new JScrollPane();
//     jScrollPanel.setPreferredSize(new Dimension(200,400));
//     jScrollPanel.getViewport().add(oPlayer.jPlayerLog, null);
     oPlayer.jPlayerLog.setLineWrap(true);
     oPlayer.jPlayerLog.setWrapStyleWord(true);
     oPlayer.jPlayerLog.setBackground(Color.darkGray);
     oPlayer.jPlayerLog.setForeground(Color.YELLOW);

  //   jLogPanel.add(oPlayer.jPlayerLog,BorderLayout.CENTER);
//     jNewPanel.add(jLogPanel);
//     jLogPanel.add(jScrollPanel,BorderLayout.CENTER);
//     jNewPanel.add(jLogPanel);
//     jNewPanel.add(oPlayer.jPlayerLog);

       jNewPanel.add(oPlayer.bViewPlayerLog);

     return jNewPanel;
   } // all done



   /**
    * this returns an element of this class used to place into a doc
    * and save as xml
    *
    * @return Element
    */
   Element xmlGetElements() {
     Element eItem= new Element("Player");

     eItem.addContent(new Element("sPlayerName").setText(xmlControl.escapeChars(sPlayerName)));
     eItem.addContent(new Element("sCharacter").setText(xmlControl.escapeChars(sCharacter)));
     eItem.addContent(new Element("sRace").setText(xmlControl.escapeChars(sRace)));
     eItem.addContent(new Element("lPlayerID").setText(""+lPlayerID));
     eItem.addContent(new Element("jAbsent").setText(jAbsent.isSelected()?"true":"false"));
     eItem.addContent(new Element("jHireling").setText(jHireling.isSelected()?"true":"false"));

     eItem.addContent(new Element("sFamilyClan").setText(xmlControl.escapeChars(sFamilyClan)));
     eItem.addContent(new Element("sHomeland").setText(xmlControl.escapeChars(sHomeland)));
     eItem.addContent(new Element("sGawd").setText(xmlControl.escapeChars(sGawd)));
     eItem.addContent(new Element("sPatron").setText(xmlControl.escapeChars(sPatron)));
     eItem.addContent(new Element("sAppearance").setText(xmlControl.escapeChars(sAppearance)));
     eItem.addContent(new Element("sBirthDate").setText(xmlControl.escapeChars(sBirthDate)));
     eItem.addContent(new Element("sFamilyHistory").setText(xmlControl.escapeChars(sFamilyHistory)));
     eItem.addContent(new Element("sHairColour").setText(xmlControl.escapeChars(sHairColour)));
     eItem.addContent(new Element("sEyeColour").setText(xmlControl.escapeChars(sEyeColour)));
     eItem.addContent(new Element("sSpecialAbilities").setText(xmlControl.escapeChars(sSpecialAbilities)));
     eItem.addContent(new Element("sMiscInfo").setText(xmlControl.escapeChars(sMiscInfo)));

     eItem.addContent(new Element("jPlayerLog").setText(xmlControl.escapeChars(jPlayerLog.getText())));

     eItem.addContent(new Element("nBirthRank").setText(""+nBirthRank));
     eItem.addContent(new Element("nAge").setText(""+nAge));
     eItem.addContent(new Element("nHeight").setText(""+nHeight));
     eItem.addContent(new Element("nWeight").setText(""+nWeight));
     eItem.addContent(new Element("nSocialClassIndex").setText(""+nSocialClassIndex));
     eItem.addContent(new Element("nNumberSiblings").setText(""+nNumberSiblings));
     eItem.addContent(new Element("nHandIndex").setText(""+nHandIndex));
     eItem.addContent(new Element("nAlignementIndex").setText(""+nAlignementIndex));
     eItem.addContent(new Element("nSexIndex").setText(""+nSexIndex));
     eItem.addContent(new Element("nAIP").setText(""+nAIP));

     eItem.addContent(new Element("jSizeComboBox").setText(""+jSizeComboBox.getSelectedIndex()));


     eItem.addContent(new Element("jHealthMax").setText(""+jHealthMax.getValue().toString()));
     eItem.addContent(new Element("jHealthLabel").setText(""+jHealthLabel.getText()));
     eItem.addContent(new Element("jMove").setText(""+jMove.getValue().toString()));
     eItem.addContent(new Element("jHonor").setText(""+jHonor.getValue().toString()));
     eItem.addContent(new Element("nTemporalHonor").setText(""+nTemporalHonor));

     Element eAC = new Element("AC");
     eAC.addContent(new Element("AC_NORMAL").setText(nAC[AC_NORMAL].getValue().toString()));
     eAC.addContent(new Element("AC_REAR").setText(nAC[AC_REAR].getValue().toString()));
     eAC.addContent(new Element("AC_SHIELDLESS").setText(nAC[AC_SHIELDLESS].getValue().toString()));
     eAC.addContent(new Element("AC_SUPRISED").setText(nAC[AC_SUPRISED].getValue().toString()));
     eItem.addContent(eAC);

     Element eAbilities = new Element("Abilities");
     for (int i=0;i<aAbilities.size();i++) {
       Abilities oA = (Abilities)aAbilities.get(i); //<Ability>..</Ability>
       eAbilities.addContent(oA.xmlGetElements());
     }
     eItem.addContent(eAbilities);

     Element eClasses = new Element("Classes");
     for (int i=0;i<aClass.size();i++) {
       Class oO = (Class)aClass.get(i); //<Class>..</Class>
       eClasses.addContent(oO.xmlGetElements());
     }
     eItem.addContent(eClasses);

     Element eSaves = new Element("SavingThrows");
     for (int i=0;i<aSaves.size();i++) {
       Saves oO = (Saves)aSaves.get(i);
       eSaves.addContent(oO.xmlGetElements()); //<Save>..</Save>
     }
     eItem.addContent(eSaves);

     Element eCoins = new Element("Coins");
     for (int i=0;i<aCoins.size();i++) {
       Coins oO = (Coins)aCoins.get(i);
       eCoins.addContent(oO.xmlGetElements()); //<Coin>..</Coin>
     }
     eItem.addContent(eCoins);

     Element eInventory = new Element("Inventory");
     for (int i=0;i<aGear.size();i++) {
       Gear oO = (Gear)aGear.get(i);
       eInventory.addContent(oO.xmlGetElements()); //<Gear>..</Gear>
     }
     eItem.addContent(eInventory);

     Element eSkills = new Element("Skills");
     for (int i=0;i<aSkills.size();i++) {
       Skills oO = (Skills)aSkills.get(i);
       eSkills.addContent(oO.xmlGetElements()); //<Skill>..</Skill>
     }
     eItem.addContent(eSkills);

     Element eQuirks = new Element("Quirks");
     for (int i=0;i<aQuirks.size();i++) {
       Quirks oO = (Quirks)aQuirks.get(i);
       eQuirks.addContent(oO.xmlGetElements()); //<Quirk>..</Quirk>
     }
     eItem.addContent(eQuirks);

     return eItem;
   }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static TablePlayer xmlGetFromElements(Element eItem,
                                         HackSackFrame oParent) {
     TablePlayer oO = new TablePlayer(oParent);

     oO.sPlayerName = xmlControl.unEscapeChars(eItem.getChild("sPlayerName").getText());
     oO.sCharacter = xmlControl.unEscapeChars(eItem.getChild("sCharacter").getText());
     oO.sRace = xmlControl.unEscapeChars(eItem.getChild("sRace").getText());

     oO.lPlayerID = Long.parseLong(eItem.getChild("lPlayerID").getText());

     oO.jAbsent.setSelected(eItem.getChild("jAbsent").getText().equalsIgnoreCase("true"));
     oO.jHireling.setSelected(eItem.getChild("jHireling").getText().equalsIgnoreCase("true"));

     oO.sFamilyClan = xmlControl.unEscapeChars(eItem.getChild("sFamilyClan").getText());
     oO.sHomeland = xmlControl.unEscapeChars(eItem.getChild("sHomeland").getText());
     oO.sGawd = xmlControl.unEscapeChars(eItem.getChild("sGawd").getText());
     oO.sPatron = xmlControl.unEscapeChars(eItem.getChild("sPatron").getText());
     oO.sAppearance = xmlControl.unEscapeChars(eItem.getChild("sAppearance").getText());
     oO.sBirthDate = xmlControl.unEscapeChars(eItem.getChild("sBirthDate").getText());
     oO.sFamilyClan = xmlControl.unEscapeChars(eItem.getChild("sFamilyHistory").getText());
     oO.sFamilyHistory = xmlControl.unEscapeChars(eItem.getChild("sFamilyHistory").getText());
     oO.sHairColour = xmlControl.unEscapeChars(eItem.getChild("sHairColour").getText());
     oO.sEyeColour = xmlControl.unEscapeChars(eItem.getChild("sEyeColour").getText());
     oO.sSpecialAbilities = xmlControl.unEscapeChars(eItem.getChild("sSpecialAbilities").getText());
     oO.sMiscInfo = xmlControl.unEscapeChars(eItem.getChild("sMiscInfo").getText());

     oO.jPlayerLog.setText(xmlControl.unEscapeChars(eItem.getChild("jPlayerLog").getText()));

     oO.nBirthRank = Integer.parseInt(eItem.getChild("nBirthRank").getText());
     oO.nAge = Integer.parseInt(eItem.getChild("nAge").getText());
     oO.nHeight = Integer.parseInt(eItem.getChild("nHeight").getText());
     oO.nWeight = Integer.parseInt(eItem.getChild("nWeight").getText());
     oO.nSocialClassIndex = Integer.parseInt(eItem.getChild("nSocialClassIndex").getText());
     oO.nNumberSiblings = Integer.parseInt(eItem.getChild("nNumberSiblings").getText());
     oO.nHandIndex = Integer.parseInt(eItem.getChild("nHandIndex").getText());
     oO.nAlignementIndex = Integer.parseInt(eItem.getChild("nAlignementIndex").getText());
     oO.nSexIndex = Integer.parseInt(eItem.getChild("nSexIndex").getText());
     oO.nAIP = Integer.parseInt(eItem.getChild("nAIP").getText());

     oO.jSizeComboBox.setSelectedIndex(Integer.parseInt(eItem.getChild("jSizeComboBox").getText()));
     oO.jHealthLabel.setText(eItem.getChild("jHealthLabel").getText());
     oO.jHealthMax.setValue(new Integer(Integer.parseInt(eItem.getChild("jHealthMax").getText())));
     oO.jMove.setValue(new Integer(Integer.parseInt(eItem.getChild("jMove").getText())));
     oO.jHonor.setValue(new Integer(Integer.parseInt(eItem.getChild("jHonor").getText())));
     oO.nTemporalHonor = Integer.parseInt(eItem.getChild("nTemporalHonor").getText());

     Element eAC = eItem.getChild("AC");
     oO.nAC[oO.AC_NORMAL].setValue(new Integer(Integer.parseInt(eAC.getChild("AC_NORMAL").getText())));
     oO.nAC[oO.AC_REAR].setValue(new Integer(Integer.parseInt(eAC.getChild("AC_REAR").getText())));
     oO.nAC[oO.AC_SHIELDLESS].setValue(new Integer(Integer.parseInt(eAC.getChild("AC_SHIELDLESS").getText())));
     oO.nAC[oO.AC_SUPRISED].setValue(new Integer(Integer.parseInt(eAC.getChild("AC_SUPRISED").getText())));

     Element eAbilities = eItem.getChild("Abilities");
     if (eAbilities != null) {
       java.util.List lList = eAbilities.getChildren("Ability");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         oO.aAbilities.add(Abilities.xmlGetFromElements(eO,oParent,oO));
       }
     }

     Element eClasses = eItem.getChild("Classes");
     if (eClasses != null) {
       java.util.List lList = eClasses.getChildren("Class");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         oO.aClass.add(Class.xmlGetFromElements(eO,oParent,oO));
       }
     }

     Element eSaves = eItem.getChild("SavingThrows");
     if (eSaves != null) {
       java.util.List lList = eSaves.getChildren("Save");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         oO.aSaves.add(Saves.xmlGetFromElements(eO,oParent,oO));
       }
     }

     Element eCoins = eItem.getChild("Coins");
     if (eCoins != null) {
       java.util.List lList = eCoins.getChildren("Coin");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         oO.aCoins.add(Coins.xmlGetFromElements(eO,oParent,oO));
       }
     }

     Element eInventory = eItem.getChild("Inventory");
     if (eInventory != null) {
       boolean bChanged = false;
       java.util.List lList = eInventory.getChildren("Gear");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         Gear oG = Gear.xmlGetFromElements(eO);

         /**
          * this bit is to import new quirks, fixed quirks broken
          * and the like
          *
          */
         TableGear oOld = TableGear.GetGearFromID(oParent,oG.nGearID);
         if (oOld == null)
           oOld = TableGear.GetGearFromName(oParent,oG.sName);

        if (oOld != null) {
          // same name, nID not the same
          // set the loaded one to the system one
          if (oOld.sName.equalsIgnoreCase(oG.sName) &&
            oOld.nGearID != oG.nGearID) {
            oG.nGearID = oOld.nGearID;
            oParent.gmLog(false,false,oG.sName+" same name as existing "+oOld.sName+", nID changed.\n");
            bChanged = true;
          } else
          // name not the same but ID's are
          // change the nID to max+1
          // then add to system db
          if (!oOld.sName.equalsIgnoreCase(oG.sName) &&
            oOld.nGearID == oG.nGearID) {
            oG.nGearID = (oParent.nMaxGearID++);
            oParent.lGear.add(oG.getSystemStruct());
            oParent.gmLog(false,false,oG.sName+" added to local gear database.\n");
            bChanged = true;
          }
        } else {
          // we couldn't find this, add to system
          oG.nGearID = (oParent.nMaxGearID++);
          oParent.lGear.add(oG.getSystemStruct());
          oParent.gmLog(false,false,oG.sName+" not found, added to local gear database.\n");
          bChanged = true;
        }
        /**
         * end import new/broken quirks
         */

         oO.aGear.add(oG);
       }
       if (bChanged) {
         xmlControl.saveDoc(oParent, oParent,
                            TableGear.xmlBuildDocFromList(oParent.lGear,oParent.nMaxGearID), oParent.sGearSaveFile);
       }
     }

     Element eSkills = eItem.getChild("Skills");
     if (eSkills != null) {
       boolean bChanged = false;
       java.util.List lList = eSkills.getChildren("Skill");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();
         Skills oS = Skills.xmlGetFromElements(eO);

         /**
          * this bit is to import new quirks, fixed quirks broken
          * and the like
          *
          */
         TableSkills oOld = TableSkills.GetSkillsFromID(oParent,oS.nSkillID);
         if (oOld == null)
           oOld = TableSkills.GetSkillsFromName(oParent,oS.sName);

        if (oOld != null) {
          // same name, nID not the same
          // set the loaded one to the systemone
          if (oOld.sName.equalsIgnoreCase(oS.sName) &&
            oOld.nSkillID != oS.nSkillID) {
            oS.nSkillID = oOld.nSkillID;
            oParent.gmLog(false,false,oS.sName+" same name, nID changed.\n");
            bChanged = true;
          } else
          // name not the same but ID's are
          // change the nID to max+1
          // then add to system db
          if (!oOld.sName.equalsIgnoreCase(oS.sName) &&
            oOld.nSkillID == oS.nSkillID) {
            oS.nSkillID = (oParent.nMaxSkillsID++);
            oParent.lSkills.add(oS.getSystemStruct(oParent));
            oParent.gmLog(false,false,oS.sName+" added to local skill database.\n");
            bChanged = true;
          }
        } else {
          // we couldn't find this, add to system
          oS.nSkillID = (oParent.nMaxSkillsID++);
          oParent.lSkills.add(oS.getSystemStruct(oParent));
          oParent.gmLog(false,false,oS.sName+" not found, added to local skill database.\n");
          bChanged = true;
        }
        /**
         * end import new/broken quirks
         */


         oO.aSkills.add(oS);
       }
       if (bChanged) {
         xmlControl.saveDoc(oParent, oParent, TableSkills.xmlBuildDocFromList(
             oParent.lSkills, oParent.nMaxSkillsID), oParent.sSkillsSaveFile);
       }
     }

     Element eQuirks = eItem.getChild("Quirks");
     if (eQuirks != null) {
       boolean bChanged = false;
       java.util.List lList = eQuirks.getChildren("Quirk");
       Iterator in = lList.iterator();
       while (in.hasNext()) {
         Element eO = (Element)in.next();

         Quirks oQ = Quirks.xmlGetFromElements(eO);

         /**
          * this bit is to import new quirks, fixed quirks broken
          * and the like
          *
          */
         TableQuirks oOld = TableQuirks.GetQuirkFromID(oParent,oQ.nQuirkID);
         if (oOld == null)
           oOld = TableQuirks.GetQuirkFromName(oParent,oQ.sName);

        if (oOld != null) {
          // same name, nQuirkID not the same
          // set the loaded one to the systemone
          if (oOld.sName.equalsIgnoreCase(oQ.sName) &&
            oOld.nQuirkID != oQ.nQuirkID) {
            oQ.nQuirkID = oOld.nQuirkID;
            oParent.gmLog(false,false,oQ.sName+" same name, nID changed.\n");
            bChanged = true;
          } else
          // name not the same but ID's are
          // change the nQuirkID to max+1
          // then add to system db
          if (!oOld.sName.equalsIgnoreCase(oQ.sName) &&
            oOld.nQuirkID == oQ.nQuirkID) {
            oQ.nQuirkID = (oParent.nMaxQuirksID++);
            oParent.lQuirks.add(oQ.getSystemStruct());
            oParent.gmLog(false,false,oQ.sName+" added to local quirk database.\n");
            bChanged = true;
          }
        } else {
          // we couldn't find this quirk, add to system
          oQ.nQuirkID = (oParent.nMaxQuirksID++);
          oParent.lQuirks.add(oQ.getSystemStruct());
          oParent.gmLog(false,false,oQ.sName+" not found, added to local quirk database.\n");
          bChanged = true;
        }
        /**
         * end import new/broken quirks
         */

         oO.aQuirks.add(oQ);
       }
       if (bChanged) {
         xmlControl.saveDoc(oParent, oParent, TableQuirks.xmlBuildDocFromList(
             oParent.lQuirks, oParent.nMaxQuirksID), oParent.sQuirksSaveFile);
       }
     }

     return oO;
   }

   /**
    * this builds a document of elements from an arraylist so it can be saved to
    * an xml file.
    *
    * @param lList ArrayList
    * @param nMaxID int
    * @return Document
    */
   static Document xmlBuildDocFromList(ArrayList lList,int nMaxID) {
    Element eRoot = new Element("PlayerFile");
    eRoot.setAttribute(new Attribute("JDOM","10b"));
    eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
    Document doc = new Document(eRoot);

    for(int i=0;i<lList.size();i++) {

      TablePlayer oO = (TablePlayer)lList.get(i);
      eRoot.addContent(oO.xmlGetElements());

    }

    return doc;
   }

   /**
    * this builds a single player doc for saving to XML
    *
    * @return Document
    */
   Document xmlBuildDoc() {
    Element eRoot = new Element("PlayerFile");
    eRoot.setAttribute(new Attribute("JDOM","10b"));
//    eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
    Document doc = new Document(eRoot);
    eRoot.addContent(xmlGetElements());
    return doc;
   }

   /**
    * this builds an arraylist of this object type from a document that was
    * built from a xml file
    *
    * @param oParent HackSackFrame
    * @param doc Document
    * @return ArrayList
    */
   static ArrayList xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {
     ArrayList lList = new ArrayList();

     try {
     Element eRoot = doc.getRootElement();

     java.util.List lItems = eRoot.getChildren("Player");

     Iterator in = lItems.iterator();
     while (in.hasNext()) {
       Element eItem = (Element)in.next();
       TablePlayer oO = TablePlayer.xmlGetFromElements(eItem,oParent);

       lList.add(oO);
     }

     }
     catch (NullPointerException err) {
       oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                         "Error occured while trying to load player from XML.");
     }

     return lList;
   }

   /**
    * this will load up a single player from a xml file
    *
    * @param oParent HackSackFrame
    * @param doc Document
    * @return TablePlayer
    */
   static TablePlayer xmlGetSavedPlayerFromDoc(HackSackFrame oParent, Document doc) {
     TablePlayer oPlayer = null;

     try {
     Element eRoot = doc.getRootElement();

     Element ePlayer = eRoot.getChild("Player");
     oPlayer = TablePlayer.xmlGetFromElements(ePlayer,oParent);
     }
     catch (NullPointerException err) {
       oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                         "Error occured while trying to load a single player from XML file.");
     }

     return oPlayer;
   }

   static void AskWhereToSavePlayer(HackSackFrame oParent, Component oComponent,
                                    TablePlayer oSaveThis) {
      JFileChooser jFileChooser1 = new JFileChooser();
      jFileChooser1.setApproveButtonText("Save");
      jFileChooser1.setDialogTitle("Save Player Sheet");
      jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                                 File.separatorChar +
                                                 oParent.sPlayerSheetDir));

      int returnVal = jFileChooser1.showSaveDialog(oComponent);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
        String sFile = jFileChooser1.getSelectedFile().getName();
//        SavePlayer.toFile(oParent,oSaveThis ,sFileName);
        xmlControl.saveDoc(oParent,oParent,oSaveThis.xmlBuildDoc(),sFileName);

         oParent.gmLog(true,false,"Saved character to file "+sFile+".\n");
      }
      else
        oParent.gmLog(true, true, "Saved cancelled.\n");
    }

   static void AskWhatFileToLoad(HackSackFrame oParent, Component oThis,
                                 boolean bDirectToGroup) {

     JFileChooser jFileChooser1 = new JFileChooser();
     jFileChooser1.setApproveButtonText("Load");
     jFileChooser1.setDialogTitle("Load Player");
     jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir") +
                                                File.separatorChar +
                                                oParent.sPlayerSheetDir));

     int returnVal = jFileChooser1.showOpenDialog(oThis);
     if (returnVal == JFileChooser.APPROVE_OPTION) {
       String sFileName = jFileChooser1.getSelectedFile().getAbsolutePath();
       String sFile = jFileChooser1.getSelectedFile().getName();

       if (!jFileChooser1.getSelectedFile().exists()) {
         oParent.gmLog(true, false, "File " + sFile + " does not exist.\n");
       }
       else {
         Document doc = xmlControl.loadDoc(oParent,oThis,sFileName);
         Attribute aO = doc.getRootElement().getAttribute("JDOM");
         if (aO == null) {
           oParent.ShowError(oThis,"Loading old format playerfile.\nMake sure to save this playerfile!");
           SaxParserForPL.loadUp(oParent, sFileName, true);
         } else {
           oParent.gplPlayer = TablePlayer.xmlGetSavedPlayerFromDoc(oParent,doc);
         }


         if (bDirectToGroup) {
           oParent.gplGroupLog.lPlayers.add(oParent.gplPlayer);
           // add player to the party list
           oParent.fPlayerGroupFrame.mPartyPlayerList.addElement(oParent.gplPlayer);
           if (!oParent.fPlayerGroupFrame.isVisible())
             oParent.fPlayerGroupFrame.LoadPartyGroupPane(oParent);
             // if this first character loaded, select it so its displayed
             // in the panel
             if (oParent.fPlayerGroupFrame.jPartyPlayerList.getSelectedIndex() < 0)
               oParent.fPlayerGroupFrame.jPartyPlayerList.setSelectedIndex(0);

               // refresh cause we added someone
               oParent.fBattleSheetFrame.LoadPartyPanel(oParent);
         }// else {
          // TablePlayer.FillPlayerPanel(oParent, oParent.gplPlayer);
         //}
         oParent.gmLog(true, false, "Loaded " + sFile + " player file.\n");
       }
     }
     else {
       oParent.gmLog(true, false, "Load cancelled.\n");

     }
   }



} // end TablePlayer class

class Abilities {
  String sName = null;
  JSpinner jAdjScore = null;
  JSpinner jAdjPercent = null;
  ArrayList aMods = null;

  public Abilities(HackSackFrame oParent, TablePlayer oPlayer) {
    jAdjScore = new JSpinner(new SpinnerNumberModel(1, -20, 25, 1));
    jAdjPercent = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    aMods = new ArrayList();
    jAdjScore.addChangeListener(new jAbilitySpinnerListener(oParent, oPlayer,
        "Score", this));
    jAdjPercent.addChangeListener(new jAbilitySpinnerListener(oParent, oPlayer,
        "Percent", this));
  }



  void updatedSpinner(HackSackFrame oParent, TablePlayer oPlayer, String sType,
                      Abilities oA) {
    /*
        // dont really matter which, we need to flip if either is messed with.

         if (sType.equalsIgnoreCase("score")) {

        }
        else
        if (sType.equalsIgnoreCase("percent")) {

        }
     */
    int nScore = Integer.parseInt(oA.jAdjScore.getValue().toString());
    int nPercent = Integer.parseInt(oA.jAdjPercent.getValue().toString());
    int nAbilityType = oPlayer.aAbilities.indexOf(oA); // 0=STR,1=DEX,2=CON,3=INT,4=WIS,5=CHA,6=COM

    if (nAbilityType <= 6) { // just for sanity
      TableAbilityScores oAb = TableAbilityScores.FindAbilityTable(oParent,nAbilityType, nScore, nPercent);

      if (oAb != null) {
        String[] sAbilityModText = null;
        switch(nAbilityType) {
          case 0:
            sAbilityModText = oParent.sStrength;
            break;
          case 1:
            sAbilityModText = oParent.sDex;
            break;
          case 2:
            sAbilityModText = oParent.sCon;
            break;
          case 3:
            sAbilityModText = oParent.sInt;
            break;
          case 4:
            sAbilityModText = oParent.sWis;
            break;
          case 5:
            sAbilityModText = oParent.sChr;
            break;
          case 6:
            sAbilityModText = oParent.sCom;
            break;
        }

        // if we dont have enough "mod" slots add some
        while(oA.aMods.size() < oAb.nMaxMods) {
          oA.aMods.add(new AbilityMods(oParent));
        }

        for (int i = 0; i < oA.aMods.size(); i++) {
          AbilityMods oMod = (AbilityMods) oA.aMods.get(i);
//oParent.jGMTextArea.append("Ability :("+i+")"+sAbilityModText[i]+"\n");
          oMod.jName.setText(sAbilityModText[i]);
          oMod.jMod.setText(oAb.sMod[i]);
        }
      }

    }
    if (nAbilityType == 0) { // if adjusting strength set jEncValue label
      TableEncum.setEncumLabel(oParent.lEncumTable, oPlayer);
    }

  }

  void updateText(HackSackFrame oParent, TablePlayer oPlayer, String sType,
                  Abilities oA) {
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Ability");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("jAdjScore").setText(jAdjScore.getValue().toString()));
    eItem.addContent(new Element("jAdjPercent").setText(jAdjPercent.getValue().toString()));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @param oPlayer TablePlayer
   * @return Abilities
   */
  static Abilities xmlGetFromElements(Element eItem,
                                      HackSackFrame oParent, TablePlayer oPlayer) {
    Abilities oO = new Abilities(oParent,oPlayer);

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.jAdjScore.setValue(new Integer(Integer.parseInt(eItem.getChild("jAdjScore").getText())));
    oO.jAdjPercent.setValue(new Integer(Integer.parseInt(eItem.getChild("jAdjPercent").getText())));

    return oO;
  }

}

class AbilityMods {
//  String sName = null;
  JLabel jName = null;
  JLabel jMod = null;
  int nMod = -1;

  public AbilityMods(HackSackFrame oParent) {
    jName = new JLabel("");
    jMod = new JLabel("NA");
    jMod.setForeground(Color.GREEN);

  }

  void updateText(HackSackFrame oParent, TablePlayer oPlayer, String sType,
                  AbilityMods oM) {
//     oM.jAdjPercent.getText()
  }
}

class Saves {
  String sName = new String();
  JSpinner jSaveOther = null;
  JSpinner jSaveMod = null;
  JSpinner jSave = null;
  int nSaveType = -1;
  int nBaseSave = 20;

  public Saves(HackSackFrame oParent, TablePlayer oPlayer) {
    jSaveMod = new JSpinner();
    jSaveOther = new JSpinner();
    jSave = new JSpinner();
    jSave.addChangeListener(new jSavesSpinnerListener(oParent, oPlayer, "Save", this));
    jSaveOther.addChangeListener(new jSavesSpinnerListener(oParent, oPlayer,
        "Other", this));
    jSaveMod.addChangeListener(new jSavesSpinnerListener(oParent, oPlayer,
        "Mod", this));

  }

  void updatedSpinner(HackSackFrame oParent, TablePlayer oPlayer, String sType,
                      Saves oM) {
    if (sType.equalsIgnoreCase("save")) {
    }
    else
    if (sType.equalsIgnoreCase("Other")) {

    }
    else
    if (sType.equalsIgnoreCase("mod")) {

    }
  }
  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Save");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("nSaveType").setText(""+nSaveType));
    eItem.addContent(new Element("nBaseSave").setText(""+nBaseSave));
    eItem.addContent(new Element("jSave").setText(jSave.getValue().toString()));
    eItem.addContent(new Element("jSaveMod").setText(jSaveMod.getValue().toString()));
    eItem.addContent(new Element("jSaveOther").setText(jSaveOther.getValue().toString()));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @param oPlayer TablePlayer
   * @return TableClass
   */
  static Saves xmlGetFromElements(Element eItem,
                                  HackSackFrame oParent, TablePlayer oPlayer) {
    Saves oO = new Saves(oParent,oPlayer);

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.nSaveType = Integer.parseInt(eItem.getChild("nSaveType").getText());
    oO.nBaseSave = Integer.parseInt(eItem.getChild("nBaseSave").getText());

    oO.jSave.setValue(new Integer(Integer.parseInt(eItem.getChild("jSave").getText())));
    oO.jSaveMod.setValue(new Integer(Integer.parseInt(eItem.getChild("jSaveMod").getText())));
    oO.jSaveOther.setValue(new Integer(Integer.parseInt(eItem.getChild("jSaveOther").getText())));

    return oO;
  }


}

class Coins {
  String sName = null;
  JLabel jCount = new JLabel("0");
  jCoinSpinner jMod = null;
  public Coins() {
  }

  public Coins(HackSackFrame oParent, TablePlayer oPlayer) {
    this();
    jMod = new jCoinSpinner(oParent, oPlayer, this);
    jMod.setModel(new SpinnerNumberModel(0, 0, 10000000, 1));
  }

  void updateSpinner(HackSackFrame oParent, TablePlayer oPlayer, Coins oCoin) {
    oCoin.jCount.setText(oCoin.jMod.getModel().getValue().toString());
    TableEncum.setEncumLabel(oParent.lEncumTable, oPlayer);
  }
  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Coin");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("jCount").setText(jCount.getText()));
    eItem.addContent(new Element("jMod").setText(jMod.getValue().toString()));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @param oPlayer TablePlayer
   * @return TableClass
   */
  static Coins xmlGetFromElements(Element eItem,
                                  HackSackFrame oParent, TablePlayer oPlayer) {
    Coins oO = new Coins(oParent,oPlayer);

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.jCount.setText(eItem.getChild("jCount").getText().trim());
    oO.jMod.setValue(new Integer(Integer.parseInt(eItem.getChild("jMod").getText())));

    return oO;
  }

}

class Class {
  String sName = null;
  String sDesc = null;
  jClassSpinner jLevel = null;
  jClassSpinner jEXP = null;
  jClassSpinner jEXPBonus = null;

  int nClassID = -1;
  int nClassindex = 0;
  int nSaveAs = 0; // 0=fighter,1=cleric etc...
  int nFightAs = 0; // ditto
  ClassRemoveButton bButton = null;
  ClassRemoveButton bDetailsButton = null;

  public Class() {
  }

  public Class(HackSackFrame oParent, TablePlayer oPlayer) {
    this();
    jLevel = new jClassSpinner(oParent, oPlayer, this, "level");
    jLevel.setModel(new SpinnerNumberModel(0, 0, 20, 1));

    jEXP = new jClassSpinner(oParent, oPlayer, this, "exp");
    jEXP.setModel(new SpinnerNumberModel(0, 0, 10000000, 1));

    jEXPBonus = new jClassSpinner(oParent, oPlayer, this, "jEXPBonus");
    jEXPBonus.setModel(new SpinnerNumberModel(0, 0, 100, 1));

    bButton = new ClassRemoveButton(oParent, oPlayer, this, "Remove");
    bButton.setText("Remove");

    bDetailsButton = new ClassRemoveButton(oParent, oPlayer, this, "Details");
    bDetailsButton.setText("*");
    bDetailsButton.setToolTipText("See class description field.");
  }

  static int GetSave(HackSackFrame oParent, String sClass, int nSaveType,
                     int nLevel) {
    int nSave = 20;
    boolean bFound = false;
    for (int i = 0; i < oParent.lSaveTables.size() && !bFound; i++) {
      TableSave oS = (TableSave) oParent.lSaveTables.get(i);
      if (oS.sTable.equalsIgnoreCase(sClass) && oS.nLevel == nLevel) {
//oParent.jGMTextArea.append("found entry for class ("+sClass+") and type "+nSaveType+" for level "+nLevel+"\n");
        switch (nSaveType) {
          case 0:
            nSave = oS.nPara;
            break;
          case 1:
            nSave = oS.nRod;
            break;
          case 2:
            nSave = oS.nPetri;
            break;
          case 3:
            nSave = oS.nBreath;
            break;
          case 4:
            nSave = oS.nApology;
            break;
          case 5:
            nSave = oS.nSpells;
            break;
        }
        bFound = true;
      }
    }
    return nSave;
  }

  // update the class level/exp label
  void updateClassSpinner(HackSackFrame oParent, TablePlayer oPlayer,
                          Class oClass, String sType) {
    if (sType.equalsIgnoreCase("level")) {
      //--
//oParent.jGMTextArea.append("Running update class spinner name:"+oClass.sName+" save as:"+
//                               oClass.nSaveAs+" level:"+oClass.jLevel.getModel().getValue().toString());
      for (int j = 0; j < oPlayer.aSaves.size(); j++) {
        Saves oM = (Saves) oPlayer.aSaves.get(j);
        int nSave = 20;
        for (int i = 0; i < oPlayer.aClass.size(); i++) {
          Class oThisClass = (Class) oPlayer.aClass.get(i);
          int nCurrentSave = 20;
          int nSaveAs = oThisClass.nSaveAs;
          int nLevel = Integer.parseInt(oThisClass.jLevel.getModel().getValue().
                                        toString());
          switch (nSaveAs) {
            case 0: // fighter
              nCurrentSave = GetSave(oParent, "FIGHTER", oM.nSaveType, nLevel);
              break;
            case 1: // cleric
              nCurrentSave = GetSave(oParent, "CLERIC", oM.nSaveType, nLevel);
              break;
            case 2: // thief
              nCurrentSave = GetSave(oParent, "THIEF", oM.nSaveType, nLevel);
              break;
            case 3: // mage
              nCurrentSave = GetSave(oParent, "MAGE", oM.nSaveType, nLevel);
              break;
            case 4: // monster
              nCurrentSave = GetSave(oParent, "FIGHTER", oM.nSaveType, nLevel);
              break;
          }
          if (nCurrentSave < nSave) {
            nSave = nCurrentSave;
          }
        }
        oM.jSave.getModel().setValue(new Integer(nSave));
        oM.nBaseSave = nSave;
      }

      //--

    }
    else
    if (sType.equalsIgnoreCase("exp")) {
    }
    else
    if (sType.equalsIgnoreCase("jEXPBonus")) {
    }

  } // remove class/level/etc...

  void updateClassButtonPressed(HackSackFrame oParent, TablePlayer oPlayer,
                                Class oClass, String sThisButton) {
    if (sThisButton.equalsIgnoreCase("REMOVE")) {
      if (oParent.AskYN(oParent.fPlayerGroupFrame,
                        "Are you sure you want to remove the class " +
                        oClass.sName + "?")) {
        oPlayer.aClass.remove(oClass);
        oParent.fPlayerGroupFrame.jPartyPlayerList_valueChanged(null);
        oParent.ShowDone(oParent.fPlayerGroupFrame,
                         "Deleted class " + oClass.sName + " from player.");
      }
    }
    else
    if (sThisButton.equalsIgnoreCase("DETAILS")) {
      // get the class description from its nClassID
      TableClass oOrigClass = oPlayer.GetClassFromID(oParent, oClass.nClassID);
      if (oOrigClass == null) {
        oOrigClass = new TableClass();
        oOrigClass.sDesc = "Not found.";
        oOrigClass.sName = "Unknown.";
      }
      //

      DialogDetails dlg = new DialogDetails(oOrigClass.sDesc,
                                            "Details: " + oOrigClass.sName);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = oParent.getSize();
      Point loc = oParent.getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.show();
    }

  }

  /**
 * this returns an element of this class used to place into a doc
 * and save as xml
 *
 * @return Element
 */
Element xmlGetElements() {
  Element eItem= new Element("Class");

  eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
 // eItem.addContent(new Element("nClassindex").setText(""+nClassindex));
  eItem.addContent(new Element("nClassID").setText(""+nClassID));
  eItem.addContent(new Element("nSaveAs").setText(""+nSaveAs));
  eItem.addContent(new Element("nFightAs").setText(""+nFightAs));
  eItem.addContent(new Element("jLevel").setText(jLevel.getValue().toString()));
  eItem.addContent(new Element("jEXP").setText(jEXP.getValue().toString()));
  eItem.addContent(new Element("jEXPBonus").setText(jEXPBonus.getValue().toString()));

  return eItem;
}

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @param oPlayer TablePlayer
   * @return TableClass
   */
  static Class xmlGetFromElements(Element eItem,
                                HackSackFrame oParent,TablePlayer oPlayer) {
  Class oO = new Class(oParent,oPlayer);

  oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
//  oO.nClassindex = Integer.parseInt(eItem.getChild("nClassIndex").getText());
  oO.nClassID = Integer.parseInt(eItem.getChild("nClassID").getText());
  oO.nSaveAs = Integer.parseInt(eItem.getChild("nSaveAs").getText());
  oO.nFightAs = Integer.parseInt(eItem.getChild("nFightAs").getText());
  oO.jLevel.setValue(new Integer(Integer.parseInt(eItem.getChild("jLevel").getText())));
  oO.jEXP.setValue(new Integer(Integer.parseInt(eItem.getChild("jEXP").getText())));
  if (eItem.getChild("jEXPBonus")!=null)
  oO.jEXPBonus.setValue(new Integer(Integer.parseInt(eItem.getChild("jEXPBonus").getText())));

  return oO;
}

}

class Gear {
  String sName = new String();
  String sDesc = new String();
  String sLoc = new String();
  int nGearID = -1;
  int nCount = 1;
  double dWeight = 1.0;
  JSpinner jMod = null;

  String sItemType = null; // Gear, Weapon, Container

  // weapon specific stuff
  String sWeaponGroup = null; // melee, missle
  String WEAPON_GROUP_MELEE = "MELEE";
  String WEAPON_GROUP_RANGE = "RANGE";

  String sWeaponType = null; // crush, pierce, hack
  String WEAPON_TYPE_CRUSH = "C";
  String WEAPON_TYPE_PIERCE = "P";
  String WEAPON_TYPE_HACK = "H";

  int nToHit = 0; // not used right now
  int nToDamage = 0; // not used right now
  int nSpeedFactor = 0;

  JTextField[] jDamage = null;

  int DAMAGE_SIZE_TINY = 0;
  int DAMAGE_SIZE_SMALL = 1;
  int DAMAGE_SIZE_MEDIUM = 2;
  int DAMAGE_SIZE_LARGE = 3;
  int DAMAGE_SIZE_HUGE = 4;
  int DAMAGE_SIZE_GIANT = 5;
  // end weapons

// Armor specs
  int nACBase = 9;
  int nACBaseCurrent = 9;
  int nACBulkIndex = 0;
  int nACMagicBonus = 0;
  int nACMagicBonusCurrent = 0;
  int nACMagicBonusHealth = 0;
  int nACMagicBonusHealthCurrent = 0;
  int nACAbsorb = 1;

  int[] nACHP = null;
  int[] nACHPCurrent = null;
  int AC_0 = 0;
  int AC_1 = 1;
  int AC_2 = 2;
  int AC_3 = 3;
  int AC_4 = 4;
  int AC_5 = 5;
  int AC_6 = 6;
  int AC_7 = 7;
  int AC_8 = 8;
  int AC_9 = 9;
  // end armor specs

  // shield specs
  int nShieldACBase = 1;
  int nShieldACBaseCurrent = 1;
  int nShieldACMagicBonus = 0;
  int nShieldACMagicHealth = 0;
  int nShieldACMagicHealthCurrent = 0;
  int nShieldACMagicBonusCurrent = 0;

  int []nShieldHPCurrent = null;
  int []nShieldHP = null;
  int SHIELD_1 = 1;
  int SHIELD_2 = 2;
  int SHIELD_3 = 3;
  int SHIELD_4 = 4;
  int SHIELD_5 = 5;
  int SHIELD_6 = 6;
  int SHIELD_7 = 7;
  int SHIELD_8 = 8;
  int SHIELD_9 = 9;
  int SHIELD_10 = 10;
  // end shield specs

  // container specific stuff
  double dMaxWeightContained = 0.0;
  // end containers

  String ITEM_TYPE_CONTAINER = "CONTAINER";
  String ITEM_TYPE_WEAPON = "WEAPON";
  String ITEM_TYPE_GEAR = "GEAR";
  String ITEM_TYPE_ARMOR = "ARMOR";
  String ITEM_TYPE_SHIELD = "SHIELD";

  // GearTree node spot for this item if its a container
  DefaultMutableTreeNode oNode = null;

  public Gear() {
    nACHP = new int[(AC_9+1)];
    nACHPCurrent = new int[(AC_9+1)];
    nShieldHP = new int[(SHIELD_10+1)];
    nShieldHPCurrent = new int[(SHIELD_10+1)];

    jMod = new JSpinner(new SpinnerNumberModel(1, 1, 100000, 1));

    jDamage = new JTextField[DAMAGE_SIZE_GIANT+1];
    jDamage[DAMAGE_SIZE_TINY] = new JTextField();
    jDamage[DAMAGE_SIZE_SMALL] = new JTextField();
    jDamage[DAMAGE_SIZE_MEDIUM] = new JTextField();
    jDamage[DAMAGE_SIZE_LARGE] = new JTextField();
    jDamage[DAMAGE_SIZE_HUGE] = new JTextField();
    jDamage[DAMAGE_SIZE_GIANT] = new JTextField();

  }

  /**
   * this returns a TableGear version of a Gear class that
   * comes from a player
   *
   * @return TableGear
   */
  TableGear getSystemStruct() {
  TableGear oG = new TableGear();

    oG.sName = sName;
    oG.sDesc = sDesc;
    oG.nGearID = nGearID;
    oG.dWeight = dWeight;
    oG.sItemType = sItemType;

    oG.sWeaponGroup = sWeaponGroup;
    oG.sWeaponType = sWeaponType;
    oG.nToHit = nToHit;
    oG.nToDamage = nToDamage;
    oG.nSpeedFactor = nSpeedFactor;

    oG.nACBase = nACBase;
    oG.nACBulkIndex = nACBulkIndex;
    oG.nACMagicBonus = nACMagicBonus;

    for (int i=0;i<nACHP.length;i++) {
      oG.nACHP[i] = nACHP[i];
    }

    oG.nShieldACBase = nShieldACBase;
    oG.nShieldACMagicBonus = nShieldACMagicBonus;

    for (int i=0;i<nShieldHP.length;i++) {
      oG.nShieldHP[i] = nShieldHP[i];
    }

    for (int i=0;i<jDamage.length;i++) {
      oG.jDamage[i].setText(jDamage[i].getText());
    }

    oG.dMaxWeightContained = dMaxWeightContained;

    return oG;
  }
  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eGearRoot = new Element("Gear");

    eGearRoot.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eGearRoot.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eGearRoot.addContent(new Element("sLoc").setText(xmlControl.escapeChars(sLoc)));
    eGearRoot.addContent(new Element("nCount").setText("" + nCount));
    eGearRoot.addContent(new Element("dWeight").setText("" + dWeight));
    eGearRoot.addContent(new Element("nGearID").setText("" + nGearID));
    eGearRoot.addContent(new Element("sItemType").setText(sItemType));

    if (sItemType == null ) // this popped up on older characters
      sItemType = ITEM_TYPE_GEAR;

      if (sItemType.equalsIgnoreCase(ITEM_TYPE_SHIELD)) {
        eGearRoot.addContent(new Element("nShieldACBase").setText("" +
            nShieldACBase));
        eGearRoot.addContent(new Element("nShieldACMagicBonus").setText("" +
            nShieldACMagicBonus));

        eGearRoot.addContent(new Element("nShieldACBaseCurrent").setText("" +
            nShieldACBaseCurrent));
        eGearRoot.addContent(new Element("nShieldACMagicBonusCurrent").setText(
            "" +
            nShieldACMagicBonusCurrent));

        Element eShield_Original = new Element("Shield_Original");
        eShield_Original.addContent(new Element("SHIELD_1").setText("" +
            nShieldHP[SHIELD_1]));
        eShield_Original.addContent(new Element("SHIELD_2").setText("" +
            nShieldHP[SHIELD_2]));
        eShield_Original.addContent(new Element("SHIELD_3").setText("" +
            nShieldHP[SHIELD_3]));
        eShield_Original.addContent(new Element("SHIELD_4").setText("" +
            nShieldHP[SHIELD_4]));
        eShield_Original.addContent(new Element("SHIELD_5").setText("" +
            nShieldHP[SHIELD_5]));
        eGearRoot.addContent(eShield_Original);

        Element eShield_Current = new Element("Shield_Current");
        eShield_Current.addContent(new Element("SHIELD_1").setText("" +
            nShieldHPCurrent[SHIELD_1]));
        eShield_Current.addContent(new Element("SHIELD_2").setText("" +
            nShieldHPCurrent[SHIELD_2]));
        eShield_Current.addContent(new Element("SHIELD_3").setText("" +
            nShieldHPCurrent[SHIELD_3]));
        eShield_Current.addContent(new Element("SHIELD_4").setText("" +
            nShieldHPCurrent[SHIELD_4]));
        eShield_Current.addContent(new Element("SHIELD_5").setText("" +
            nShieldHPCurrent[SHIELD_5]));
        eGearRoot.addContent(eShield_Current);
      }
      else
      if (sItemType.equalsIgnoreCase(ITEM_TYPE_ARMOR)) {
        eGearRoot.addContent(new Element("nACAbsorb").setText("" + nACAbsorb));
        eGearRoot.addContent(new Element("nACBulkIndex").setText("" +
            nACBulkIndex));
        eGearRoot.addContent(new Element("nACBase").setText("" + nACBase));
        eGearRoot.addContent(new Element("nACMagicBonus").setText("" +
            nACMagicBonus));

        eGearRoot.addContent(new Element("nACBaseCurrent").setText("" +
            nACBaseCurrent));
        eGearRoot.addContent(new Element("nACMagicBonusCurrent").setText("" +
            nACMagicBonusCurrent));

        Element eArmor_Original = new Element("Armor_Original");
        eArmor_Original.addContent(new Element("AC_0").setText("" + nACHP[AC_0]));
        eArmor_Original.addContent(new Element("AC_1").setText("" + nACHP[AC_1]));
        eArmor_Original.addContent(new Element("AC_2").setText("" + nACHP[AC_2]));
        eArmor_Original.addContent(new Element("AC_3").setText("" + nACHP[AC_3]));
        eArmor_Original.addContent(new Element("AC_4").setText("" + nACHP[AC_4]));
        eArmor_Original.addContent(new Element("AC_5").setText("" + nACHP[AC_5]));
        eArmor_Original.addContent(new Element("AC_6").setText("" + nACHP[AC_6]));
        eArmor_Original.addContent(new Element("AC_7").setText("" + nACHP[AC_7]));
        eArmor_Original.addContent(new Element("AC_8").setText("" + nACHP[AC_8]));
        eArmor_Original.addContent(new Element("AC_9").setText("" + nACHP[AC_9]));
        eGearRoot.addContent(eArmor_Original);

        Element eArmor_Current = new Element("Armor_Current");
        eArmor_Current.addContent(new Element("AC_0").setText("" +
            nACHPCurrent[AC_0]));
        eArmor_Current.addContent(new Element("AC_1").setText("" +
            nACHPCurrent[AC_1]));
        eArmor_Current.addContent(new Element("AC_2").setText("" +
            nACHPCurrent[AC_2]));
        eArmor_Current.addContent(new Element("AC_3").setText("" +
            nACHPCurrent[AC_3]));
        eArmor_Current.addContent(new Element("AC_4").setText("" +
            nACHPCurrent[AC_4]));
        eArmor_Current.addContent(new Element("AC_5").setText("" +
            nACHPCurrent[AC_5]));
        eArmor_Current.addContent(new Element("AC_6").setText("" +
            nACHPCurrent[AC_6]));
        eArmor_Current.addContent(new Element("AC_7").setText("" +
            nACHPCurrent[AC_7]));
        eArmor_Current.addContent(new Element("AC_8").setText("" +
            nACHPCurrent[AC_8]));
        eArmor_Current.addContent(new Element("AC_9").setText("" +
            nACHPCurrent[AC_9]));
        eGearRoot.addContent(eArmor_Current);

      }
      else
      if (sItemType.equalsIgnoreCase(ITEM_TYPE_CONTAINER)) {
        eGearRoot.addContent(new Element("dMaxWeightContained").setText("" +
            dMaxWeightContained));
      }
      else
      if (sItemType.equalsIgnoreCase(ITEM_TYPE_WEAPON)) {
        eGearRoot.addContent(new Element("sWeaponGroup").setText(sWeaponGroup));
        eGearRoot.addContent(new Element("nSpeedFactor").setText("" +
            nSpeedFactor));
        eGearRoot.addContent(new Element("sWeaponType").setText(sWeaponType));

        eGearRoot.addContent(new Element("DAMAGE_SIZE_TINY").setText(jDamage[
            DAMAGE_SIZE_TINY].getText()));
        eGearRoot.addContent(new Element("DAMAGE_SIZE_SMALL").setText(jDamage[
            DAMAGE_SIZE_SMALL].getText()));
        eGearRoot.addContent(new Element("DAMAGE_SIZE_MEDIUM").setText(jDamage[
            DAMAGE_SIZE_MEDIUM].getText()));
        eGearRoot.addContent(new Element("DAMAGE_SIZE_LARGE").setText(jDamage[
            DAMAGE_SIZE_LARGE].getText()));
        eGearRoot.addContent(new Element("DAMAGE_SIZE_HUGE").setText(jDamage[
            DAMAGE_SIZE_HUGE].getText()));
        eGearRoot.addContent(new Element("DAMAGE_SIZE_GIANT").setText(jDamage[
            DAMAGE_SIZE_GIANT].getText()));
      }
      else {
        // we assume it's a "gear" items
        // we have no specific stuff other that what we have for every item for "GEAR"
        //   if (sItemType.equalsIgnoreCase(ITEM_TYPE_GEAR)) {
      }

    return eGearRoot;
  }

  /**
   * this gets an class from a element
   *
   * @param eGear Element
   * @return TableClass
   */
  static Gear xmlGetFromElements(Element eGear) {
    Gear oGear = new Gear();

    oGear.sName = xmlControl.unEscapeChars(eGear.getChild("sName").getText());
    oGear.sDesc = xmlControl.unEscapeChars(eGear.getChild("sDesc").getText());
    oGear.dWeight = Double.parseDouble(eGear.getChild("dWeight").getText());
    oGear.nGearID = Integer.parseInt(eGear.getChild("nGearID").getText());
    oGear.sItemType = eGear.getChild("sItemType").getText();

    oGear.sLoc = xmlControl.unEscapeChars(eGear.getChild("sLoc").getText());
    oGear.nCount = Integer.parseInt(eGear.getChild("nCount").getText());


    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_SHIELD)) {
      oGear.nShieldACBase = Integer.parseInt(eGear.getChild("nShieldACBase").getText());
      oGear.nShieldACMagicBonus = Integer.parseInt(eGear.getChild("nShieldACMagicBonus").getText());

      oGear.nShieldACBaseCurrent = Integer.parseInt(eGear.getChild("nShieldACBaseCurrent").getText());
      oGear.nShieldACMagicBonusCurrent = Integer.parseInt(eGear.getChild("nShieldACMagicBonusCurrent").getText());

      Element eShield_Original = eGear.getChild("Shield_Original");
      oGear.nShieldHP[oGear.SHIELD_1] = Integer.parseInt(eShield_Original.getChild("SHIELD_1").getText());
      oGear.nShieldHP[oGear.SHIELD_2] = Integer.parseInt(eShield_Original.getChild("SHIELD_2").getText());
      oGear.nShieldHP[oGear.SHIELD_3] = Integer.parseInt(eShield_Original.getChild("SHIELD_3").getText());
      oGear.nShieldHP[oGear.SHIELD_4] = Integer.parseInt(eShield_Original.getChild("SHIELD_4").getText());
      oGear.nShieldHP[oGear.SHIELD_5] = Integer.parseInt(eShield_Original.getChild("SHIELD_5").getText());

      Element eShield_Current = eGear.getChild("Shield_Current");
      oGear.nShieldHPCurrent[oGear.SHIELD_1] = Integer.parseInt(eShield_Current.getChild("SHIELD_1").getText());
      oGear.nShieldHPCurrent[oGear.SHIELD_2] = Integer.parseInt(eShield_Current.getChild("SHIELD_2").getText());
      oGear.nShieldHPCurrent[oGear.SHIELD_3] = Integer.parseInt(eShield_Current.getChild("SHIELD_3").getText());
      oGear.nShieldHPCurrent[oGear.SHIELD_4] = Integer.parseInt(eShield_Current.getChild("SHIELD_4").getText());
      oGear.nShieldHPCurrent[oGear.SHIELD_5] = Integer.parseInt(eShield_Current.getChild("SHIELD_5").getText());

    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_ARMOR)) {
      oGear.nACAbsorb = Integer.parseInt(eGear.getChild("nACAbsorb").getText());
      oGear.nACBulkIndex = Integer.parseInt(eGear.getChild("nACBulkIndex").getText());

      oGear.nACBase = Integer.parseInt(eGear.getChild("nACBase").getText());
      oGear.nACMagicBonus = Integer.parseInt(eGear.getChild("nACMagicBonus").getText());
      oGear.nACBaseCurrent = Integer.parseInt(eGear.getChild("nACBaseCurrent").getText());
      oGear.nACMagicBonusCurrent = Integer.parseInt(eGear.getChild("nACMagicBonusCurrent").getText());

      Element eArmor_Original = eGear.getChild("Armor_Original");
      oGear.nACHP[oGear.AC_0] = Integer.parseInt(eArmor_Original.getChild("AC_0").getText());
      oGear.nACHP[oGear.AC_1] = Integer.parseInt(eArmor_Original.getChild("AC_1").getText());
      oGear.nACHP[oGear.AC_2] = Integer.parseInt(eArmor_Original.getChild("AC_2").getText());
      oGear.nACHP[oGear.AC_3] = Integer.parseInt(eArmor_Original.getChild("AC_3").getText());
      oGear.nACHP[oGear.AC_4] = Integer.parseInt(eArmor_Original.getChild("AC_4").getText());
      oGear.nACHP[oGear.AC_5] = Integer.parseInt(eArmor_Original.getChild("AC_5").getText());
      oGear.nACHP[oGear.AC_6] = Integer.parseInt(eArmor_Original.getChild("AC_6").getText());
      oGear.nACHP[oGear.AC_7] = Integer.parseInt(eArmor_Original.getChild("AC_7").getText());
      oGear.nACHP[oGear.AC_8] = Integer.parseInt(eArmor_Original.getChild("AC_8").getText());
      oGear.nACHP[oGear.AC_9] = Integer.parseInt(eArmor_Original.getChild("AC_9").getText());

      Element eArmor_Current = eGear.getChild("Armor_Current");
      oGear.nACHPCurrent[oGear.AC_0] = Integer.parseInt(eArmor_Current.getChild("AC_0").getText());
      oGear.nACHPCurrent[oGear.AC_1] = Integer.parseInt(eArmor_Current.getChild("AC_1").getText());
      oGear.nACHPCurrent[oGear.AC_2] = Integer.parseInt(eArmor_Current.getChild("AC_2").getText());
      oGear.nACHPCurrent[oGear.AC_3] = Integer.parseInt(eArmor_Current.getChild("AC_3").getText());
      oGear.nACHPCurrent[oGear.AC_4] = Integer.parseInt(eArmor_Current.getChild("AC_4").getText());
      oGear.nACHPCurrent[oGear.AC_5] = Integer.parseInt(eArmor_Current.getChild("AC_5").getText());
      oGear.nACHPCurrent[oGear.AC_6] = Integer.parseInt(eArmor_Current.getChild("AC_6").getText());
      oGear.nACHPCurrent[oGear.AC_7] = Integer.parseInt(eArmor_Current.getChild("AC_7").getText());
      oGear.nACHPCurrent[oGear.AC_8] = Integer.parseInt(eArmor_Current.getChild("AC_8").getText());
      oGear.nACHPCurrent[oGear.AC_9] = Integer.parseInt(eArmor_Current.getChild("AC_9").getText());

    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_WEAPON)) {
      oGear.sWeaponGroup = eGear.getChild("sWeaponGroup").getText();
      oGear.sWeaponType = eGear.getChild("sWeaponType").getText();
      oGear.nSpeedFactor =Integer.parseInt(eGear.getChild("nSpeedFactor").getText());

      oGear.jDamage[oGear.DAMAGE_SIZE_TINY].setText(eGear.getChild("DAMAGE_SIZE_TINY").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_SMALL].setText(eGear.getChild("DAMAGE_SIZE_SMALL").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_MEDIUM].setText(eGear.getChild("DAMAGE_SIZE_MEDIUM").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_LARGE].setText(eGear.getChild("DAMAGE_SIZE_LARGE").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_HUGE].setText(eGear.getChild("DAMAGE_SIZE_HUGE").getText());
      oGear.jDamage[oGear.DAMAGE_SIZE_GIANT].setText(eGear.getChild("DAMAGE_SIZE_GIANT").getText());
    } else
    if (oGear.sItemType.equalsIgnoreCase(oGear.ITEM_TYPE_CONTAINER)) {
      oGear.dMaxWeightContained = Double.parseDouble(eGear.getChild("dMaxWeightContained").getText());
    }

    return oGear;
  }

}

class Skills {
  String sName = new String();
  String sDesc = new String();
  JSpinner jSkillSpinner = null;
//  jSkillRemoveButton bButton = null;
//  jSkillRemoveButton bDetailsButton = null;

  int nSkillType = 0;
  int nSkillID = -1;

//  long lHash = -1;
//  DefaultMutableTreeNode oNode = null;

  int TYPE_SKILL = 0;
  int TYPE_TALENT = 1;
  int TYPE_PROF = 2;
  int TYPE_ABILITY = 3;

//  public Skills() {
//  }

  public Skills() {
//    this();
    jSkillSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 150, 1));
  }

  /**
   * this returns a TableSkills db version of Skills
   * which is the player version
   *
   * @param oParent HackSackFrame
   * @return TableSkills
   */
  TableSkills getSystemStruct(HackSackFrame oParent) {
    TableSkills oS = new TableSkills(oParent);

    oS.oParent = oParent;
    oS.nSkillID = nSkillID;
    oS.sName = sName;
    oS.sDesc = sDesc;
    oS.nSkillType = nSkillType;
    oS.nSkillRating = 1;

    return oS;
  }
  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Skill");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eItem.addContent(new Element("jSkillSpinner").setText(jSkillSpinner.getValue().toString()));
    eItem.addContent(new Element("nSkillID").setText(""+nSkillID));
    eItem.addContent(new Element("nSkillType").setText(""+nSkillType));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static Skills xmlGetFromElements(Element eItem) {
    Skills oO = new Skills();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
    oO.jSkillSpinner.setValue(new Integer(Integer.parseInt(eItem.getChild("jSkillSpinner").getText())));
    oO.nSkillID = Integer.parseInt(eItem.getChild("nSkillID").getText());
    oO.nSkillType = Integer.parseInt(eItem.getChild("nSkillType").getText());
    return oO;
  }

}

class Quirks {
  String sName = null;
  String sDesc = null;
//  jQuirkRemoveButton bButton = null;
//  jQuirkRemoveButton bDetailsButton = null;
  int nQuirkID = -1;

  public Quirks() {
  }

  /**
   * this returns a system/database version of this
   * class
   *
   * @return TableQuirks
   */
  TableQuirks getSystemStruct() {
    TableQuirks oQ = new TableQuirks();
    oQ.sName = sName;
    oQ.sDesc = sDesc;
    oQ.nQuirkID = nQuirkID;
    return oQ;
  }
  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Quirk");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
    eItem.addContent(new Element("nQuirkID").setText(""+nQuirkID));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static Quirks xmlGetFromElements(Element eItem) {
    Quirks oO = new Quirks();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
    oO.nQuirkID = Integer.parseInt(eItem.getChild("nQuirkID").getText());

    return oO;
  }

}

// buttons/spinners/text areas/etc
class PlayerButton
    extends JButton {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;

  private PlayerButton() {
  }

  public PlayerButton(HackSackFrame oParent, TablePlayer oPlayer,
                      String sButtonName) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;

    addActionListener(new PlayerButtonListener(oParent, oPlayer,
                                               sThisButtonName));
  }
}

class PlayerButtonListener
    implements ActionListener {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  public PlayerButtonListener(HackSackFrame oParent, TablePlayer oPlayer,
                              String sButtonName) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;

  }

  public void actionPerformed(ActionEvent e) {
    oPlayer.updateButtonPressed(oParent, oPlayer, sThisButtonName);
  }
}

//---------------------------------------------
class jCoinSpinner
    extends JSpinner {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Coins oCoin = null;

  private jCoinSpinner() {
  }

  public jCoinSpinner(HackSackFrame oParent, TablePlayer oPlayer, Coins oCoin) {
    this.oParent = oParent;
    this.oPlayer = oPlayer;
    this.oCoin = oCoin;

    addChangeListener(new jCoinSpinnerListener(oParent, oPlayer, oCoin));
  }
}

class jCoinSpinnerListener
    implements ChangeListener {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Coins oCoin = null;

  public jCoinSpinnerListener(HackSackFrame oParent, TablePlayer oPlayer,
                              Coins oCoin) {
    this.oParent = oParent;
    this.oCoin = oCoin;
    this.oPlayer = oPlayer;

  }

  public void stateChanged(ChangeEvent e) {
    oCoin.updateSpinner(oParent, oPlayer, oCoin);
  }
}

//---------------------------------

class jClassSpinner
    extends JSpinner {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Class oClass = null;
  private String sType = null;

  private jClassSpinner() {
  }

  public jClassSpinner(HackSackFrame oParent, TablePlayer oPlayer, Class oClass,
                       String sType) {
    this.oParent = oParent;
    this.oPlayer = oPlayer;
    this.oClass = oClass;
    this.sType = sType;

    addChangeListener(new jClassSpinnerListener(oParent, oPlayer, oClass, sType));
  }
}

class jClassSpinnerListener
    implements ChangeListener {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Class oClass = null;
  private String sType = null;

  public jClassSpinnerListener(HackSackFrame oParent, TablePlayer oPlayer,
                               Class oClass, String sType) {
    this.oParent = oParent;
    this.oPlayer = oPlayer;
    this.oClass = oClass;
    this.sType = sType;

  }

  public void stateChanged(ChangeEvent e) {
    oClass.updateClassSpinner(oParent, oPlayer, oClass, sType);
  }
}

//--------------------------------
class ClassRemoveButton
    extends JButton {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Class oClass = null;
  private String sThisButton = null;

  private ClassRemoveButton() {
  }

  public ClassRemoveButton(HackSackFrame oParent, TablePlayer oPlayer,
                           Class oClass, String sThisButton) {
    this.oParent = oParent;
    this.oClass = oClass;
    this.oPlayer = oPlayer;
    this.sThisButton = sThisButton;

    addActionListener(new ClassRemoveButtonListener(oParent, oPlayer, oClass,
        sThisButton));
  }
}

class ClassRemoveButtonListener
    implements ActionListener {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;
  private Class oClass = null;
  private String sThisButton = null;

  public ClassRemoveButtonListener(HackSackFrame oParent, TablePlayer oPlayer,
                                   Class oClass, String sThisButton) {
    this.oParent = oParent;
    this.oPlayer = oPlayer;
    this.oClass = oClass;
    this.sThisButton = sThisButton;

  }

  public void actionPerformed(ActionEvent e) {
    oClass.updateClassButtonPressed(oParent, oPlayer, oClass, sThisButton);
  }
}

///---------------------------------------------------------
class jAbilitiesScoreListener
    extends KeyAdapter {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private Abilities oA = null;

  public jAbilitiesScoreListener(HackSackFrame oParent, TablePlayer oPlayer,
                                 String sButtonName, Abilities oA) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oA = oA;

  }

  public void keyReleased(KeyEvent e) {
//      oA.updateText(oParent,oPlayer,sThisButtonName,oA);
  }
}

//--------------------------------------------
class jAbilityModsScoreListener
    extends KeyAdapter {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private AbilityMods oA = null;

  public jAbilityModsScoreListener(HackSackFrame oParent, TablePlayer oPlayer,
                                   String sButtonName, AbilityMods oA) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oA = oA;

  }

  public void keyReleased(KeyEvent e) {
    oA.updateText(oParent, oPlayer, sThisButtonName, oA);
  }
}

//---------------------------------------------------------

class jAbilitySpinnerListener
    implements javax.swing.event.ChangeListener {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private Abilities oA = null;

  public jAbilitySpinnerListener(HackSackFrame oParent, TablePlayer oPlayer,
                                 String sButtonName, Abilities oA) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oA = oA;

  }

  public void stateChanged(ChangeEvent e) {
    oA.updatedSpinner(oParent, oPlayer, sThisButtonName, oA);
  }
}

//-------------------------------------------------------
class jSavesSpinnerListener
    implements javax.swing.event.ChangeListener {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;
  private Saves oA = null;

  public jSavesSpinnerListener(HackSackFrame oParent, TablePlayer oPlayer,
                               String sButtonName, Saves oA) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;
    this.oA = oA;

  }

  public void stateChanged(ChangeEvent e) {
    oA.updatedSpinner(oParent, oPlayer, sThisButtonName, oA);
  }
}

//-------------------------------
class PlayerTextFieldListener
    implements ActionListener {
  private TablePlayer oPlayer = null;
  private String sVar = null;

  public PlayerTextFieldListener(TablePlayer oPlayer, String sVar) {
    this.oPlayer = oPlayer;
    this.sVar = sVar;
  }

  public void actionPerformed(ActionEvent e) {
    oPlayer.updatePlayerTextField(oPlayer, sVar);
  }
}

//---------------------------------------------------------

class jHealthMaxSpinnerListener
    implements javax.swing.event.ChangeListener {
  private TablePlayer oPlayer = null;
  private String sThisButtonName = null;
  private HackSackFrame oParent = null;

  public jHealthMaxSpinnerListener(HackSackFrame oParent, TablePlayer oPlayer,
                                   String sButtonName) {
    this.oPlayer = oPlayer;
    this.sThisButtonName = sButtonName;
    this.oParent = oParent;

  }

  public void stateChanged(ChangeEvent e) {
//jHealthMaxSpinnerListener
    oPlayer.updateMaxHealthSpinner(oParent, oPlayer, sThisButtonName);
  }
}

//-------------------------------
class PlayerHonorListener
    implements ActionListener {
  private HackSackFrame oParent = null;
  private TablePlayer oPlayer = null;

  public PlayerHonorListener(HackSackFrame oParent, TablePlayer oPlayer) {
    this.oPlayer = oPlayer;
  }

  public void actionPerformed(ActionEvent e) {
    oPlayer.updateHonorField(oParent, oPlayer);
  }
}

//jSkilTree listeners
class jSkillTree_treeSelectionAdapter
    implements javax.swing.event.TreeSelectionListener {
  TablePlayer adaptee;

  jSkillTree_treeSelectionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(TreeSelectionEvent e) {
    adaptee.jSkillTree_valueChanged(e);
  }
}

class jSkillTree_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TablePlayer adaptee;

  jSkillTree_mouseAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jSkillTree_mouseClicked(e);
  }
}

//quirk tree
class jQuirkTree_treeSelectionAdapter
    implements javax.swing.event.TreeSelectionListener {
  TablePlayer adaptee;

  jQuirkTree_treeSelectionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(TreeSelectionEvent e) {
    adaptee.jQuirkTree_valueChanged(e);
  }
}

class jQuirkTree_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TablePlayer adaptee;

  jQuirkTree_mouseAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jQuirkTree_mouseClicked(e);
  }
}

// gear tree
class jGearTree_treeSelectionAdapter
    implements javax.swing.event.TreeSelectionListener {
  TablePlayer adaptee;

  jGearTree_treeSelectionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void valueChanged(TreeSelectionEvent e) {
    adaptee.jGearTree_valueChanged(e);
  }
}

class jGearTree_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TablePlayer adaptee;

  jGearTree_mouseAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jGearTree_mouseClicked(e);
  }
}

// ---------- coin jcoinweightincluded checkbox
class jCoinWeightIncluded_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jCoinWeightIncluded_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCoinWeightIncluded_actionPerformed(e);
  }
}

// jmove spinner listener
class jMoveSpinnerListener
    implements javax.swing.event.ChangeListener {
  private TablePlayer oPlayer = null;

  public jMoveSpinnerListener(TablePlayer oPlayer) {
    this.oPlayer = oPlayer;
  }

  public void stateChanged(ChangeEvent e) {
    oPlayer.updatejMoveSpinner(e);
  }
}

// combobox for what creature player is attacking in battle sheet
class jAttackingThisCreature_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jAttackingThisCreature_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jAttackingThisCreature_actionPerformed(e);
  }
}

// popups for gear edits
class Player_jMenuItemUnCarried_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemUnCarried_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemUnCarried_actionPerformed(e);
  }
}

class Player_jMenuItemDelete_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemDelete_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemDelete_actionPerformed(e);
  }
}

class Player_jMenuItemChange_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemChange_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemChange_actionPerformed(e);
  }
}

class Player_jMenuItemCarried_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemCarried_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemCarried_actionPerformed(e);
  }
}

class Player_jMenuItemAdd_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemAdd_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemAdd_actionPerformed(e);
  }
}

class Player_jMenuItemEdit_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  Player_jMenuItemEdit_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemEdit_actionPerformed(e);
  }
}

// popup for skiltree
class jMenuItemSkillAdd_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemSkillAdd_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemSkillAdd_actionPerformed(e);
  }
}

class jMenuItemSkillRemove_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemSkillRemove_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemSkillRemove_actionPerformed(e);
  }
}

class jMenuItemSkillChange_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemSkillChange_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemSkillChange_actionPerformed(e);
  }
}

class jMenuItemSkillEdit_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemSkillEdit_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemSkillEdit_actionPerformed(e);
  }
}
// quirktree popups
class jMenuItemQuirkAdd_actionAdapter implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemQuirkAdd_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemQuirkAdd_actionPerformed(e);
  }
}

class jMenuItemQuirkRemove_actionAdapter implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemQuirkRemove_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemQuirkRemove_actionPerformed(e);
  }
}

class jMenuItemQuirkChange_actionAdapter implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemQuirkChange_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemQuirkChange_actionPerformed(e);
  }
}

class jMenuItemQuirkEdit_actionAdapter implements java.awt.event.ActionListener {
  TablePlayer adaptee;

  jMenuItemQuirkEdit_actionAdapter(TablePlayer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemQuirkEdit_actionPerformed(e);
  }
}

// combobox for what alignment of player
class jGenericAction_actionAdapter
    implements java.awt.event.ActionListener {
  TablePlayer adaptee;
  String sThisName;

  jGenericAction_actionAdapter(TablePlayer adaptee,String sBoxName) {
    this.adaptee = adaptee;
    this.sThisName = sBoxName;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGenericAction_actionPerformed(e,sThisName);
  }
}
// generic focus event for TablePlayer
class jFocusEvent_focusAdapter
    extends java.awt.event.FocusAdapter {
  TablePlayer adaptee;
  String sVar;

  jFocusEvent_focusAdapter(TablePlayer adaptee, String sThisVar) {
    this.adaptee = adaptee;
    this.sVar = sThisVar;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jFocusEvent_focusLost(e,sVar);
  }
}

