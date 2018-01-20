package hacksack;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import org.jdom.*;
//import com.borland.jbcl.layout.*;

/**
*  System.gc() clears stuff in que to be freed
 * */
/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003,2004</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */
public class HackSackFrame
    extends JFrame {
  String[] playerClassTable = {
      "Fighter", "Cleric", "Thief", "Magic-User"};
  String[] weapontypeTable = {
      "Hack", "Crush", "Pierce"};
  String[] gmClassTable = {
      "Fighter", "Cleric", "Thief", "Magic-User", "Monster"};
  String[] gmAlignmentTable = {
      "Lawful-Good", "Lawful-Neutral", "Lawful-Evil",
      "Neutral-Good", "True-Neutral", "Neutral-Evil",
      "Chaotic-Good", "Chaotic-Neutral", "Chaotic-Evil"};
  String[] gmSizeTable = {
      "Tiny", "Small", "Medium", "Large", "Huge", "Giant"};
  String[] sexTable = {
      "Male", "Female", "None"};
  String[] handnessTable = {
      "Left", "Right", "Ambidextrous"};
  String[] gmSocialClassTable = {
      "Slave", "Lower Lower", "Middle Lower", "Upper Lower", "Lower Middle",
      "Middle Middle",
      "Upper Middle", "Lower Upper", "Middle Upper", "Upper Upper"};
  String[] gmWeaponTable = {
      "Hack", "Crush", "Pierce"};
  String[] gmSkillTypeTable = {
      "Skill", "Talent", "Proficiency", "Ability"};
  String[] gmHonorTypeTable = {
      "High Honor", "Average Honor", "Low Honor", "No Honor"};
  String[] gmTimers = {
      "Year", "Month", "Day", "Turn", "Round", "Segment"};
  String[] gmBulkyness = {
      "Non-Bulky", "Fairly-Bulky", "Bulky"};
  String[] sClimate = {
      "Tropical", "SubTropical", "Temperate", "Arctic", "SubArctic"
  };
  String[] sTerrain = {
      "Plains", "Grasslands", "Desert",
      "Mountain", "Hills", "Tundra",
      "Subterranean", "Jungle", "Swamp",
      "Wilderness", "Forest", "Coastal",
      "Fresh Water", "Salt Water", "River"

  };
  String[] sFrequency = {
      "Common",
      "Uncommon",
      "Rare",
      "Very Rare",
      "Unique"
  };
  int[] nFrequencyWeight = {
      10, 8, 5, 2, 1
  };
  String[] sActivityCycle = {
      "Day",
      "Night",
      "Day/Night"
  };
  String[] sAppearingIn = {
      "Other",
      "HOB-1",
      "HOB-2",
      "HOB-3",
      "HOB-4",
      "HOB-5",
      "HOB-6",
      "HOB-7",
      "HOB-8",
      "Matrix",
      "Field"
  };

  String[] sStrength = {
      "Hit Adj", "Dam Adj", "Weight Allowance",
      "Max Press", "Open Doors", "BB/LG"};
  String[] sDex = {
      "Def Adj", "Reaction Adj", "Missle Adj"};
  String[] sCon = {
      "HP Adj", "SS", "RS",
      "Poison Save",
      "Immun Disease/Alchohol", "Regen"};
  String[] sInt = {
      "# Lang", "Spell Level", "Learning Ability",
      "Max # Spells/Level",
      "Illusion Immun", "Spell Mishap"};
  String[] sWis = {
      "Magical Adj", "Bonus Spells", "Spell Failure",
      "Spell Immun",
      "Improve Skill"};
  String[] sChr = {
      "Max # of Henchmen",
      "Loyalty Base", "Reaction Adj", "Comeliness Mod",
      "Starting Honor Mod"};
  String[] sCom = {
      "Special"};

  int nLastCreature = 0;

//  int nMaxBattleSheetPanels = 0;
  int nMaxCreatureID = 0;
  int nMaxSkillsID = 0;
  int nMaxQuirksID = 0;
  int nMaxGearID = 0;
  int nMaxClassID = 0;
  int nMaxHonorTypeID = 0;
  int nMaxEXPBonusTypeID = 0;
  int nMaxArmorID = 0;
  int nMaxRandomNames = 0;
  int nMaxWeaponID = 0;

  String sCreatureSaveFileName = "creatures" + File.separatorChar +"Creatures.xml";
  String sSkillsSaveFile = "data" + File.separatorChar + "Skills.xml";
  String sQuirksSaveFile = "data" + File.separatorChar + "Quirks.xml";
  String sGearSaveFile = "data" + File.separatorChar + "Gear.xml";
  String sWeaponSaveFile = "data" + File.separatorChar + "Weapons.xml";
  String sFileCritTable = "data" + File.separatorChar + "CritTable.xml";
  String sClassSaveFile = "data" + File.separatorChar + "Class.xml";
  String sHonorSaveFile = "data" + File.separatorChar + "Honor.xml";
  String sEXPBonusSaveFile = "data" + File.separatorChar + "EXPBonus.xml";
  String sRandomNamesSaveFile = "data" + File.separatorChar + "RandomNames.xml";
  String sArmorSaveFile = "data" + File.separatorChar + "Armor.xml";
  String sShieldSaveFile = "data" + File.separatorChar + "Shield.xml";
  String sPlayerSheetDir = "players";
  String sBattleSheetDir = "battlesheets";
  String sPlayerGroupDir = "playergroups";
  String sPrefsFile = "HackSack.pref";

  int nCurrentCreatureID = -1; // current creature ID in memory if loaded.

  boolean bCreatureChanged = false; // current creature loaded has been "edited/changed"
  JPanel contentPane;
  FrameBattleSheet fBattleSheetFrame;
  FramePlayerGroup fPlayerGroupFrame;
  TablePlayer gplPlayer;
  TableGroupLog gplGroupLog;

//  SaveBattleSheet bsSaveBattleSheet;

  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuSavePrefs = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JPanel DiceBag = new JPanel();
  JButton D20 = new JButton();
  JButton D12 = new JButton();
  JButton D100 = new JButton();
  JButton D10 = new JButton();
  JButton D8 = new JButton();
  JButton D6 = new JButton();
  JButton D5 = new JButton();
  JButton D4 = new JButton();
  JScrollPane DiceOutAreaScrollPane = new JScrollPane();
  JTextArea DiceOutTextArea = new JTextArea();
  JPanel DiceTypePane = new JPanel();
  JButton D1000 = new JButton();
  JButton D10000 = new JButton();

  SpinnerNumberModel DiceCountModel = new SpinnerNumberModel(1, 1, 20000, 1);
  JSpinner DiceCountSpinner = new JSpinner(DiceCountModel);

  SpinnerNumberModel DiceModifierModel = new SpinnerNumberModel(0, -1000, 1000,
      1);
  JSpinner DiceModifierSpinner = new JSpinner(DiceModifierModel);

  SpinnerNumberModel DiceTotalModifierModel = new SpinnerNumberModel(0, -1000,
      1000, 1);
  JSpinner DiceTotalModifier = new JSpinner(DiceTotalModifierModel);

  SpinnerNumberModel DiceSidesModel = new SpinnerNumberModel(20, 1, 20000, 1);
  JSpinner DiceSidesSpinner = new JSpinner(DiceSidesModel);
  JLabel NumberOfDiceLable = new JLabel();
  JLabel DiceModLabel = new JLabel();
  JButton ROLLDICE = new JButton();
  JButton ClearRollSpace = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();

  JButton D2 = new JButton();
  JButton D3 = new JButton();
  JCheckBox Penetration = new JCheckBox();
  JCheckBox DiceRollDetailRadio = new JCheckBox();
  JCheckBox AppendRollsToggle = new JCheckBox();

  SpinnerNumberModel mPlayerLevel = new SpinnerNumberModel(1, 1, 20, 1);

  SpinnerNumberModel mPlayerNumATK = new SpinnerNumberModel(1, 1, 4, 1);

  SpinnerNumberModel mPlayerNumDice = new SpinnerNumberModel(1, 1, 100, 1);

  SpinnerNumberModel mPlayerSizeofDice = new SpinnerNumberModel(6, 1, 100, 1);

  SpinnerNumberModel mPlayerPerDiceMod = new SpinnerNumberModel(0, -100, 100, 1);

  SpinnerNumberModel mPlayerTotalMod = new SpinnerNumberModel(0, -100, 100, 1);

  SpinnerNumberModel mPlayerHealth = new SpinnerNumberModel(1, 1, 100, 1);
  SpinnerNumberModel mPlayerToHitAdj = new SpinnerNumberModel(0, -100, 100, 1);
  SpinnerNumberModel mPlayerNumDice1 = new SpinnerNumberModel(1, 1, 100, 1);
  SpinnerNumberModel mPlayerSizeofDice1 = new SpinnerNumberModel(6, 1, 100, 1);
  SpinnerNumberModel mPlayerPerDiceMod1 = new SpinnerNumberModel(0, -100, 100,
      1);
  SpinnerNumberModel mPlayerTotalMod1 = new SpinnerNumberModel(0, -100, 100, 1);
  SpinnerNumberModel mPlayerToHitAdj1 = new SpinnerNumberModel(0, -100, 100, 1);

  JTextField jGMNameTextField = new JTextField();
  JComboBox jGMClassComboBox = new JComboBox(gmClassTable);
  SpinnerNumberModel mGMNumAtks = new SpinnerNumberModel(0, 0, 12, 1);
  JSpinner jGMNumAtksSpinner = new JSpinner(mGMNumAtks);
  JLabel jGMNameLabel10 = new JLabel();
  JLabel jGMClassLabel11 = new JLabel();
  JLabel jGMATKLabel12 = new JLabel();
  SpinnerNumberModel mGMHDice = new SpinnerNumberModel(1, -1, 120, 1);
  JSpinner jGMHDiceSpinner = new JSpinner(mGMHDice);
  JLabel jGMHDLabel13 = new JLabel();
  SpinnerNumberModel mGMHDMod = new SpinnerNumberModel(0, -1000, 1000, 1);
  JSpinner jGMHDModSpinner = new JSpinner(mGMHDMod);
  JLabel jGMHDModLabel14 = new JLabel();
  SpinnerNumberModel mGMAC = new SpinnerNumberModel(10, -10, 10, 1);
  JSpinner jGMACSpinner = new JSpinner(mGMAC);
  JLabel jGMACLabel16 = new JLabel();
  SpinnerNumberModel mGMNumCreatures = new SpinnerNumberModel(1, 1, 100, 1);
  JSpinner jGMNumCreaturesSpinner = new JSpinner(mGMNumCreatures);
  JLabel jGMNumMobLabel17 = new JLabel();
  JButton jGMMakeSheetButton = new JButton();

  protected ArrayList lCreatures = new ArrayList(); // creatures on battle sheet
  protected ArrayList lInformation = new ArrayList(); // battlesheet info
  protected ArrayList lSavedCreatures = new ArrayList();

//  protected ArrayList lPlayers = new ArrayList();

  protected ArrayList lClericMatrix = new ArrayList();
  protected ArrayList lThiefMatrix = new ArrayList();
  protected ArrayList lMageMatrix = new ArrayList();
  protected ArrayList lWarriorMatrix = new ArrayList();
  protected ArrayList lMonsterMatrix = new ArrayList();

  protected ArrayList lTables = new ArrayList();
  protected ArrayList lTableCrit = new ArrayList();
  protected ArrayList lTableCritBody = new ArrayList();
  protected ArrayList lTableTreasures = new ArrayList();
  protected ArrayList lTableTreasureOptions = new ArrayList();
  protected ArrayList lTableMorale = new ArrayList();
  protected ArrayList lTableMagicList = new ArrayList();
  protected ArrayList lTableMagicItems = new ArrayList();
  protected ArrayList lTableEncounters = new ArrayList();
  protected ArrayList lTableTurn = new ArrayList(); // the turn tables;
  protected ArrayList lRandomNames = new ArrayList(); // list of random names

  protected ArrayList lSkills = new ArrayList(); // types of skill/talents/abilities
  protected ArrayList lQuirks = new ArrayList(); // types of quirks
  protected ArrayList lGear = new ArrayList(); // types of gear
  protected ArrayList lWeapons = new ArrayList(); // types of weapons
  protected ArrayList lArmor = new ArrayList(); // types of armor
  protected ArrayList lClass = new ArrayList(); // types of classes
  protected ArrayList lSaveTables = new ArrayList(); // saving throw tables

  protected ArrayList lAbilityTables = new ArrayList(); // ability score mods

  protected ArrayList lEncumTable = new ArrayList(); // encum table

  protected ArrayList lHonorTypes = new ArrayList(); // all the honor type awards
  protected ArrayList lEXPBonusType = new ArrayList(); // all the exp bonus type awards

  protected ArrayList lAttacks = new ArrayList(); // used in the creature creator
  protected ArrayList aClimate = new ArrayList(); // used in the creature creator
  protected ArrayList aTerrain = new ArrayList(); // used in the creature creator
  protected Properties Prefs = new Properties();

//  private ArrayList lBattlePanels = new ArrayList();
//  private ArrayList lCombatPanels = new ArrayList();
  int nMaxSheetRows = 25;
  JScrollPane jGMScrollPane = new JScrollPane();
  JTextArea jGMTextArea = new JTextArea();
  JButton jGMClearButton = new JButton();
  JCheckBox jGMAppendCheckBox = new JCheckBox();
  JCheckBox jGMDetailsCheckBox = new JCheckBox();
  Random rand = new Random(System.currentTimeMillis());
  JPanel jGMCritPanel = new JPanel();
  JButton jGMFumbleButton = new JButton();
  JCheckBox jGMUnarmedCheckBox = new JCheckBox();
  JButton jGMSpellMishapButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton jGMCritButton = new JButton();
  JComboBox jGMCritClassComboBox = new JComboBox(gmClassTable);

  SpinnerNumberModel mGMCritLevel = new SpinnerNumberModel(1, 0, 20, 1);
  JSpinner jGMCritLevelSpinner = new JSpinner(mGMCritLevel);

  SpinnerNumberModel mGMCritToHit = new SpinnerNumberModel(0, -100, 100, 1);
  JSpinner jGMCritToHitSpinner = new JSpinner(mGMCritToHit);

  SpinnerNumberModel mGMCritAC = new SpinnerNumberModel(10, -10, 10, 1);
  JSpinner jGMCritACSpinner = new JSpinner(mGMCritAC);

  JComboBox jGMCritWeaponComboBox = new JComboBox(gmWeaponTable);

  JComboBox jGMCritTSizeComboBox = new JComboBox(gmSizeTable);
  JComboBox jGMCritASizeComboBox = new JComboBox(gmSizeTable);

//  String[] gmLocationTable = {"Foot,top","Heel","Toe(s)","Foot,arch","Ankle,inner","Ankle,outer","Ankle,upper/Achilles",
//      "Shin","Calf","Knee","Knee,back","Hamstring","Thigh","Hip","Groin(male only)","Buttock","Abdomen,lower","Side,lower",
//      "Abdomen,upper","Side,lower","Abdomen,upper","Back,small of","Back,lower","Chest","Side,upper","Back,upper",
//      "Back,upper middle","Armpit","Arm,upper outer","Arm,upper inner","Elbow"};
  JComboBox jGMCritLocComboBox = new JComboBox();
  JComboBox jTRMakeItemComboBox;
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JButton jCleanSheetButton = new JButton();
  JLabel jSheetTotalLabel = new JLabel();
  JPanel LootBag = new JPanel();
  JPanel jTRTypePanel = new JPanel();
  JScrollPane jTRScrollPane = new JScrollPane();
  JTextArea jTRTextArea = new JTextArea();
//  GridLayout gridLayout2 = new GridLayout(0,8);
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton jTRGenButton = new JButton();
  JButton jTRResetButton = new JButton();
  JButton jButton1 = new JButton();
  JCheckBox jTRAppendCheckBox = new JCheckBox();
  JCheckBox jTRDetailsCheckBox = new JCheckBox();
  JPanel jGMCreatureInfoTab = new JPanel();
  JButton jGMMoraleCheckButton = new JButton();
  SpinnerNumberModel mGMMoraleRating = new SpinnerNumberModel(10, 1, 100, 1);
  JSpinner jGMMoraleRatingSpinner = new JSpinner(mGMMoraleRating);
  JLabel jGMMoraleLabel = new JLabel();
  JPanel jGMMoraleCheckPanel = new JPanel();
  JPanel jGMMoraleSpinnerPanel = new JPanel();
  GridLayout gMoraleCheckLayout = new GridLayout(0, 2);
//  GridLayout gMoraleSpinnerLayout = new GridLayout(0,2);
  GridBagLayout gMoraleSpinnerLayout = new GridBagLayout();
  JLabel jGMSpecHPLabel = new JLabel();
  JButton jGMMoraleResetButton = new JButton();

  SpinnerNumberModel mGMSpecCritRoll = new SpinnerNumberModel(0, 0, 10000, 1);
  JSpinner jGMSpecCritRoll = new JSpinner(mGMSpecCritRoll);
  JLabel jLabel18 = new JLabel();
  JButton jTRMakeItemButton = new JButton();
  JLabel jTRMakeItemLabel = new JLabel();
  JLabel jTRMakeItemLabel2 = new JLabel();
  SpinnerNumberModel mTRMakeItemSpinner = new SpinnerNumberModel(1, 1, 20, 1);
  JSpinner jTRMakeItemSpinner = new JSpinner(mTRMakeItemSpinner);
  JPanel jTRMakeItemPanel = new JPanel();
  JButton jGMSaveButton = new JButton();
  TitledBorder titledBorder1;
  Border border1;
  TitledBorder titledBorder2;
  Border border2;
  TitledBorder titledBorder3;
  Border border3;
  TitledBorder titledBorder4;
  Border border4;
  TitledBorder titledBorder5;
  Border border5;
  TitledBorder titledBorder6;
  Border border6;
  TitledBorder titledBorder7;
  JPanel jDiceRollerPanel = new JPanel();
  Border border7;
  TitledBorder titledBorder8;
  Border border8;
  TitledBorder titledBorder9;
  JButton jGMLoadBSButton = new JButton();
  JCheckBox jGMSaveBSCheckBox1 = new JCheckBox();
  JButton jGMBSReopenButton = new JButton();
  JMenuItem jMenuFileExit = new JMenuItem();
  JPanel WarRoomPanel = new JPanel();
  JScrollPane jGMAttackScrollPane = new JScrollPane();
  GridLayout gGMAttackLayout = new GridLayout(0, 1);
  JPanel jGMAttackPanel = new JPanel(gGMAttackLayout);
  Border border9;
  TitledBorder titledBorder10;
  Border border10;
  TitledBorder titledBorder11;
  SpinnerNumberModel mGMEXP = new SpinnerNumberModel(1, 1, 100000000, 1);
  JSpinner jGMEXPSpinner = new JSpinner(mGMEXP);
  JLabel jLabel20 = new JLabel();
  SpinnerNumberModel mGMMoraleSpinner = new SpinnerNumberModel(10, 1, 30, 1);
  JSpinner jGMMoraleSpinner = new JSpinner(mGMMoraleSpinner);
  JLabel jLabel21 = new JLabel();
  JLabel jLabel22 = new JLabel();
  JComboBox jGMSizeComboBox = new JComboBox(gmSizeTable);
  JLabel jLabel23 = new JLabel();
  JComboBox jGMAlignmentComboBox = new JComboBox(gmAlignmentTable);
  SpinnerNumberModel mGMHackFactor = new SpinnerNumberModel(1, 0, 100, 1);
  JSpinner jGMHackFactorSpinner = new JSpinner(mGMHackFactor);
  JLabel jLabel24 = new JLabel();
  JScrollPane jGMDescScrollPane = new JScrollPane();
  JTextArea jGMDescTextArea = new JTextArea();
  JPanel jGMBSCreatorPanel = new JPanel();
  Border border11;
  TitledBorder titledBorder12;
  SpinnerNumberModel mGMMove = new SpinnerNumberModel(12, 0, 48, 1);
  JSpinner jGMMoveSpinner = new JSpinner(mGMMove);
  JLabel jLabel19 = new JLabel();
  GridLayout gridLayout1 = new GridLayout(13, 1);
  JButton jGMResetCSButton = new JButton();
  JPanel PlayerPanel = new JPanel();
  Border border12;
  TitledBorder titledBorder13;

  Border border13;
  TitledBorder titledBorder14;

  Border border14;
  TitledBorder titledBorder15;
  Border border15;
  TitledBorder titledBorder16;
  Border border16;
  TitledBorder titledBorder17;
  Border border17;
  TitledBorder titledBorder18;
  Border border18;
  TitledBorder titledBorder19;
  Border border19;
  TitledBorder titledBorder20;
  Border border20;
  TitledBorder titledBorder21;
  Border border21;
  TitledBorder titledBorder22;
  Border border22;
  TitledBorder titledBorder23;
//  JPanel jplAbilitiesPanel = new JPanel(new GridLayout(0,1));
//  JPanel jplCoinPanel = new JPanel(new GridLayout(0,1));
  JPanel jCreatureListPanel = new JPanel(new GridLayout(0, 1));
  Border border23;
  TitledBorder titledBorder24;
  JTextField jFindCreatureTextField = new JTextField();
  JLabel jLabel49 = new JLabel();
  JButton jplLoadButton = new JButton();
  JButton jplOpenGroupButton = new JButton();
  JSpinner jGMFatigueFactorSpinner = new JSpinner(new SpinnerNumberModel(10, 1,
      100, 1));
  JLabel jLabel48 = new JLabel();
  JButton jplLoadPartyButton = new JButton();
  Border border24;
  TitledBorder titledBorder25;
  JComboBox jGMHonorComboBox = new JComboBox(gmHonorTypeTable);
  JLabel jLabel50 = new JLabel();
  JPanel jPanel1 = new JPanel();
  Border border25;
  TitledBorder titledBorder26;
  JTabbedPane jGMDiceBagTabbedPane = new JTabbedPane();
  JPanel jGMChecksPanel = new JPanel();
  JPanel jGMLogPanel = new JPanel();
  Border border26;
  TitledBorder titledBorder27;
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jGMLogButtonPanel = new JPanel();
  JScrollPane jCritScrollPane = new JScrollPane();
  JTextArea jCritTextArea = new JTextArea();
  JButton jClearCritButton = new JButton();
  JCheckBox jCritAppendCheckBox = new JCheckBox();
  JCheckBox jCritDetailsCheckBox = new JCheckBox();
  JButton jGMTurnButton = new JButton();
  JSpinner jGMTurnLevelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20,
      1));
  JComboBox jGMTurnComboBox1 = new JComboBox();
  JPanel jTurnTab = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  Border border27;
  TitledBorder titledBorder28;
  JPanel jMoraleTab = new JPanel();
  Border border28;
  TitledBorder titledBorder29;
  JLabel jLabel51 = new JLabel();
  Border border29;
  TitledBorder titledBorder30;
  JButton jGMRandomNameEditButton = new JButton();
  JButton jGMRandomNameGenerateButton = new JButton();
  JComboBox jGMRandomNameRaceComboBox = new JComboBox();
  JCheckBox jGMRandomNameMaleCheckBox = new JCheckBox();
  JCheckBox jGMRandomNameFemaleCheckBox = new JCheckBox();
  JCheckBox jCritFancyCheckBox = new JCheckBox();
  JSpinner jGMTurnSpecificSpinner = new JSpinner(new SpinnerNumberModel(0, 0,
      20, 1));
  JLabel jLabel52 = new JLabel();
  JPanel jGMConfigPane = new JPanel();
  Border border30;
  TitledBorder titledBorder31;
  JCheckBox jGMConfigTOPCheckBox1 = new JCheckBox();
  JCheckBox jGMConfigFatigueCheckBox2 = new JCheckBox();
  JButton jGMConfigNewHonorButton = new JButton();
  JButton jGMConfigNewEXPButton3 = new JButton();
  JButton jGmConfigNewSkillButton5 = new JButton();
  JButton jGMConfigNewClassButton6 = new JButton();
  JButton jGMConfigNewGearButton7 = new JButton();
  JButton jGMconfigNewQuirkButton8 = new JButton();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  Border border31;
  TitledBorder titledBorder32;
  Border border32;
  TitledBorder titledBorder33;
  Border border33;
  TitledBorder titledBorder34;
  GridLayout gridLayout2 = new GridLayout();
  JCheckBox jGMConfigDefeatAwardCheckBox1 = new JCheckBox();
  JCheckBox jGMConfigCritAwardCheckBox2 = new JCheckBox();
  JTabbedPane jRandomnessTabbedPane1 = new JTabbedPane();
  JPanel jRandomEncountersPanel = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
//  Box jGenerateEncounterPanel = Box.createVerticalBox();
  JPanel jGenerateEncounterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
  Border border34;
  TitledBorder titledBorder35;
  JPanel jRandomNamesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
  Border border35;
  TitledBorder titledBorder36;
  Border border36;
  TitledBorder titledBorder37;
  JScrollPane jScrollPane3 = new JScrollPane();
  JTextArea jRandomNameResultsTextArea = new JTextArea();
  JButton jButton2 = new JButton();
  GridLayout gridLayout4 = new GridLayout();
  DefaultListModel dmCreatureList = new DefaultListModel();
//  ArrayList aSearchedCreatureList = new ArrayList();
  JList jCreatureList = new JList(dmCreatureList);

  JButton jLoadCreatureButton = new JButton();
  JButton jRemoveCreatureButton = new JButton();
  JScrollPane jCreatureListScrollPane = new JScrollPane();
  JTabbedPane jCreatureDataTabbedPane = new JTabbedPane();
  JPanel jCreatureAttacksPanel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel jLabel53 = new JLabel();
  JComboBox jFrequencyComboBox = new JComboBox(sFrequency);
  JLabel jLabel54 = new JLabel();
  JComboBox jActivityCycleComboBox = new JComboBox(sActivityCycle);
  JLabel jLabel55 = new JLabel();
  JComboBox jDietComboBox = new JComboBox();
  JLabel jLabel56 = new JLabel();
  JLabel jLabel57 = new JLabel();
  JSpinner jNumAppSpinner = new JSpinner();
  JSpinner jNumAppSizeSpinner = new JSpinner();
  JSpinner jNumAppAdditionalSpinner = new JSpinner();
  JLabel jLabel58 = new JLabel();
  JLabel jLabel59 = new JLabel();
  JPanel jCreatureDescPanel = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel60 = new JLabel();
  JButton jEditTerrainButton = new JButton();
  JComboBox jAppearingInComboBox = new JComboBox(sAppearingIn);
  JLabel jLabel61 = new JLabel();
  JCheckBox jFilterNoAttackCheckBox = new JCheckBox();
  JButton jImportXMLButton = new JButton();
  JPanel jDiceButtonsPanel = new JPanel();
  //BoxLayout2 boxLayout21 = new BoxLayout2();
  GridLayout boxLayout21 = new GridLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jGMCritButtonsPanel = new JPanel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JPanel jFumbleButtonsPanel = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jCritFumbleMisHapPanel7 = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JPanel jMishapButtonsPanel = new JPanel();
  FlowLayout flowLayout3 = new FlowLayout();
  JPanel jCritTextAreaButtonsPanel = new JPanel();
  FlowLayout flowLayout4 = new FlowLayout();
  Border border37;
  JPanel jPanel10 = new JPanel();
  FlowLayout flowLayout5 = new FlowLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jCritListButtonsPanel = new JPanel();
  FlowLayout flowLayout6 = new FlowLayout();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  FlowLayout flowLayout7 = new FlowLayout();
  JPanel jPanel7 = new JPanel();
  BorderLayout borderLayout8 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  BorderLayout borderLayout9 = new BorderLayout();
  BorderLayout borderLayout10 = new BorderLayout();
  JPanel jSupriseTab = new JPanel();
  Border border38;
  TitledBorder titledBorder38;
  FlowLayout flowLayout8 = new FlowLayout();
  JButton jSupriseCheckButton = new JButton();
  JLabel jLabel5 = new JLabel();
  JSpinner jSupriseRangeSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 100,
      1));
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JSpinner jSupriseSpecificSpinner = new JSpinner(new SpinnerNumberModel(0, 0,
      100, 1));
  JSpinner jSupriseDiceSizeSpinner = new JSpinner(new SpinnerNumberModel(10, 1,
      100, 1));
  JLabel jLabel8 = new JLabel();
  JSpinner jSupriseModSpinner = new JSpinner(new SpinnerNumberModel(0, -100,
      100, 1));
  JTextField jGMSpecHP = new JTextField();
  JTextField jGMSpecialAttack = new JTextField();
  JTextField JGMSpecialDefense = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel62 = new JLabel();
  JCheckBox jGMForceSaveCheckBox = new JCheckBox();
  JTabbedPane jRandomnessTabbedPane = new JTabbedPane();
  JPanel jRandomEncountersTab = new JPanel();
  JPanel jRandomNamesTab = new JPanel();
  JScrollPane jEncountersScrollPane = new JScrollPane();
  JScrollPane jGeneratedEncountersListScrollPane = new JScrollPane();
  JScrollPane jEncounterTableScrollPane = new JScrollPane();
  DefaultListModel dmEncounterTable = new DefaultListModel();
  DefaultListModel dmEncounterGenerated = new DefaultListModel();
  JList jEncountersGeneratedList = new JList(dmEncounterGenerated);
  JList jEncounterTableList = new JList(dmEncounterTable);
  JTextArea jEncounterResultsTextArea = new JTextArea();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout11 = new BorderLayout();
  JPanel jPanel12 = new JPanel();
  BorderLayout borderLayout12 = new BorderLayout();
  JPanel jPanel13 = new JPanel();
  BorderLayout borderLayout13 = new BorderLayout();
  FlowLayout flowLayout9 = new FlowLayout();
  Border border39;
  TitledBorder titledBorder39;
  Border border40;
  TitledBorder titledBorder40;
  Border border41;
  TitledBorder titledBorder41;
  JButton jClearGeneratedButton = new JButton();
  JLabel jSupriseDetailsLabel = new JLabel();
  JButton jSupriseResetButton = new JButton();
  JTabbedPane jChecksTabbedPane = new JTabbedPane();
  JPanel jSupriseButtonsPanel2 = new JPanel();
  FlowLayout flowLayout10 = new FlowLayout();
  JScrollPane jSupriseScrollPane1 = new JScrollPane();
  JTextArea jSupriseTextArea = new JTextArea();
  JButton jClearSupriseTextButton = new JButton();
  JScrollPane jTurnUndeadScrollPane1 = new JScrollPane();
  JTextArea jTurnUndeadTextArea = new JTextArea();
  JButton jTurnUndeadClearButton = new JButton();
  JScrollPane jMoraleScrollPane1 = new JScrollPane();
  JTextArea jMoraleTextArea = new JTextArea();
  JButton jMoraleClearResultsButton = new JButton();
  JPanel jPickPocketDetectedTab = new JPanel();
  Border border42;
  TitledBorder titledBorder42;
  JButton jPickPocketDetectCheckButton = new JButton();
  JLabel jLabel63 = new JLabel();
  JSpinner jPickPocketDetectTargetLevelSpinner1 = new JSpinner(new SpinnerNumberModel(1,0,100,1));
  JLabel jLabel64 = new JLabel();
  JSpinner jPickPocketDetectTargetWisdomSpinner1 = new JSpinner(new SpinnerNumberModel(10,0,100,1));
  JLabel jLabel65 = new JLabel();
  JSpinner jPickPocketDetectThiefRollSpinner1 = new JSpinner(new SpinnerNumberModel(0,0,100,1));
  FlowLayout flowLayout11 = new FlowLayout();
  JScrollPane jPickPocketDetectScrollPane1 = new JScrollPane();
  JTextArea jPickPocketDetectTextArea1 = new JTextArea();
  JButton jPickPocketDetectClearButton = new JButton();
  JButton jNewPlayerButton = new JButton();
  JMenuItem jMenuVersionInformation = new JMenuItem();
  JPanel jMergeCoinPanel = new JPanel();
  BorderLayout borderLayout14 = new BorderLayout();
  JPanel jMergeCoinDisplayPanel = new JPanel();
  Border border43;
  TitledBorder titledBorder43;
  FlowLayout flowLayout18 = new FlowLayout();
  GridLayout gridLayout3 = new GridLayout();
  JPanel jCritButtonPanel2 = new JPanel();
  BorderLayout borderLayout15 = new BorderLayout();
  FlowLayout flowLayout12 = new FlowLayout();
  JPanel jGMCritClassPaneo = new JPanel();
  BorderLayout borderLayout16 = new BorderLayout();
  JPanel jGMCritLevelPanel = new JPanel();
  BorderLayout borderLayout17 = new BorderLayout();
  JPanel jGMCritToHitPanel = new JPanel();
  BorderLayout borderLayout18 = new BorderLayout();
  JPanel jGMCritACPanel = new JPanel();
  BorderLayout borderLayout19 = new BorderLayout();
  JPanel jGMCritWeaponTypePanel = new JPanel();
  BorderLayout borderLayout20 = new BorderLayout();
  JPanel jGMCritASizePanel = new JPanel();
  BorderLayout borderLayout21 = new BorderLayout();
  JPanel jGMCritTSizePanel = new JPanel();
  BorderLayout borderLayout22 = new BorderLayout();
  JPanel jGMCritSpecLocPanel = new JPanel();
  BorderLayout borderLayout23 = new BorderLayout();
  JPanel jGMCritSpecrollPanel = new JPanel();
  BorderLayout borderLayout24 = new BorderLayout();
  JPanel jGMCritFancyPanel = new JPanel();
  BorderLayout borderLayout25 = new BorderLayout();
  JPanel jGMCritSpecSevPanel23 = new JPanel();
  BorderLayout borderLayout26 = new BorderLayout();
  JSpinner jGMSpecSevSpinner = new JSpinner(new SpinnerNumberModel(0,0,24,1));
  JLabel jLabel25 = new JLabel();
  GridLayout gridLayout5 = new GridLayout();
  GridLayout gridLayout6 = new GridLayout();
  JPanel jADDConfigPanel = new JPanel();
  Border border44;
  TitledBorder titledBorder44;
  GridLayout gridLayout7 = new GridLayout();
  JCheckBox jADDKickerCheckBox = new JCheckBox();
  JCheckBox jADDPenetrationCheckBox = new JCheckBox();
  JCheckBox jADDCritCheckBox = new JCheckBox();
  JCheckBox jADDFumbleCheckBox = new JCheckBox();

  //Construct the frame
  public HackSackFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {

    FrameStartUp dlgStartUp = new FrameStartUp();
    border43 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder43 = new TitledBorder(border43,"Convert Coin Types");
    border44 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(103, 101, 98),new Color(148, 145, 140));
    titledBorder44 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(103, 101, 98),new Color(148, 145, 140)),"AD&D Style Toggles");
    dlgStartUp.jPanel1.setPreferredSize(new Dimension(360,50));
    Dimension dlgSize = dlgStartUp.jPanel1.getPreferredSize();
    Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
    dlgStartUp.setLocation((frmSize.width - dlgSize.width) / 2,
                           (frmSize.height - dlgSize.height) / 2);
    dlgStartUp.pack();
    dlgStartUp.setVisible(true);
//    dlgStartUp.show();
    dlgStartUp.jStartUpBar.setStringPainted(true);
    dlgStartUp.jStartUpBar.setIndeterminate(true);
    dlgStartUp.jStartUpBar.setValue(0);
    dlgStartUp.jStartUpBar.setString("Setting up frames.");
   // end start of the status bar

    image1 = new ImageIcon(hacksack.HackSackFrame.class.getResource(
        "openFile.png"));
    image2 = new ImageIcon(hacksack.HackSackFrame.class.getResource(
        "closeFile.png"));
    image3 = new ImageIcon(hacksack.HackSackFrame.class.getResource("help.png"));
    contentPane = (JPanel)this.getContentPane();
    titledBorder1 = new TitledBorder("");
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, Color.white, new Color(93, 93, 93),
        new Color(134, 134, 134)), "Creature Builder");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(103, 101, 98),
                                              new Color(148, 145, 140));
    titledBorder3 = new TitledBorder(border2, "Fumble, Crit and Spell Mishaps");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder4 = new TitledBorder(border3, "Morale Check Options");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder5 = new TitledBorder(border4, "Morale Variable Options");
    border5 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white, new Color(93, 93, 93),
                                              new Color(134, 134, 134));
    titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, Color.white, new Color(93, 93, 93),
        new Color(134, 134, 134)), "Make a Specific Magic Item");
    border6 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(115, 114, 105),
                                              new Color(165, 163, 151));
    titledBorder7 = new TitledBorder(border6, "Quick Select Dice");
    border7 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              Color.white,
                                              new Color(103, 101, 98),
                                              new Color(148, 145, 140));
    titledBorder8 = new TitledBorder(border7, "Roll Them Bones!");
    border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    titledBorder9 = new TitledBorder(border8, "Battle Sheet");
    border9 = BorderFactory.createEmptyBorder();
    titledBorder10 = new TitledBorder(BorderFactory.createEmptyBorder(),
                                      "Attacks");
    border10 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(103, 101, 98),
                                               new Color(148, 145, 140));
    titledBorder11 = new TitledBorder(border10, "Attacks");
    border11 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(103, 101, 98),
                                               new Color(148, 145, 140));
    titledBorder12 = new TitledBorder(border11, "Battle Sheet Creator");
    border12 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder13 = new TitledBorder(border12, "Basic Information");
    border13 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder14 = new TitledBorder(border13, "Quirks and Flaws");
    border14 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(103, 101, 98),
                                               new Color(148, 145, 140));
    titledBorder15 = new TitledBorder(border14, "Ability Scores");
    border15 = BorderFactory.createEmptyBorder();
    titledBorder16 = new TitledBorder(border15, "Gear");
    border16 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder17 = new TitledBorder(border16, "Gear");
    border17 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(103, 101, 98),
                                               new Color(148, 145, 140));
    titledBorder18 = new TitledBorder(border17, "Coins");
    border18 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder19 = new TitledBorder(border18, "Saves");
    border19 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder20 = new TitledBorder(border19, "AC, Health, Move, Misc");
    border20 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder21 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, new Color(182, 182, 182),
        new Color(62, 62, 62), new Color(89, 89, 89)),
                                      "Skills, Talents and Profs");
    border21 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(103, 101, 98),
                                               new Color(148, 145, 140));
    titledBorder22 = new TitledBorder(border21, "Class");
    border22 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder23 = new TitledBorder(border22, "Basic Information");
    border23 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder24 = new TitledBorder(border23, "Creature List");
    border24 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder25 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, new Color(182, 182, 182),
        new Color(62, 62, 62), new Color(89, 89, 89)),
                                      "Add New Skills, Quirks, Gear, Classes and  Awards");
    border25 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder26 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89)),"Characters and Groups");
    border26 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder27 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"Special Checks");
    border27 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder28 = new TitledBorder(border27, "Turn Undead");
    border28 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder29 = new TitledBorder(border28, "Morale");
    border29 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder30 = new TitledBorder(border29,
                                      "Random Names, Places and Areas");
    border30 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder31 = new TitledBorder(border30, "Configuration Options");
    border31 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder32 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(115, 114, 105),new Color(165, 163, 151)),"Class, Skills, Gear and Quirks");
    border32 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder33 = new TitledBorder(border32, "Awards");
    border33 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder34 = new TitledBorder(border33, "Toggles");
    border34 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(115, 114, 105),
                                               new Color(165, 163, 151));
    titledBorder35 = new TitledBorder(border34, "Random Options");
    border35 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder36 = new TitledBorder(border35, "Names");
    border36 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    titledBorder37 = new TitledBorder(border36, "Encounters");
    border37 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               Color.white,
                                               new Color(93, 93, 93),
                                               new Color(134, 134, 134));
    border38 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder38 = new TitledBorder(border38, "Suprise!");
    border39 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder39 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, new Color(182, 182, 182),
        new Color(62, 62, 62), new Color(89, 89, 89)), "Results");
    border40 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder40 = new TitledBorder(BorderFactory.createBevelBorder(
        BevelBorder.LOWERED, Color.white, new Color(182, 182, 182),
        new Color(62, 62, 62), new Color(89, 89, 89)), "Generated");
    border41 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white,
                                               new Color(182, 182, 182),
                                               new Color(62, 62, 62),
                                               new Color(89, 89, 89));
    titledBorder41 = new TitledBorder(border41, "Table");
    border42 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89));
    titledBorder42 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,new Color(182, 182, 182),new Color(62, 62, 62),new Color(89, 89, 89)),"Detect A Pickpocket Attempt");
    contentPane.setLayout(borderLayout1);
    this.setEnabled(true);
    this.setSize(new Dimension(1003, 775));
    this.setTitle("HackSack");
    this.addWindowListener(new HackSackFrame_this_windowAdapter(this));

    jMenuFile.setText("File");
    jMenuSavePrefs.setText("Save Window Locations");
    jMenuSavePrefs.addActionListener(new
                                     HackSackFrame_jMenuSavePrefs_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new
                                     HackSackFrame_jMenuHelpAbout_ActionAdapter(this));
    DiceBag.setLayout(borderLayout4);
    DiceBag.setFont(new java.awt.Font("Dialog", 0, 9));
    DiceBag.setMaximumSize(new Dimension(32767, 32767));
    D20.setFont(new java.awt.Font("Dialog", 0, 9));
    D20.setToolTipText("Roll a D20");
    D20.setText("D20");
    D20.addActionListener(new HackSackFrame_D20_actionAdapter(this));
    D12.setFont(new java.awt.Font("Dialog", 0, 9));
    D12.setToolTipText("Roll a D12");
    D12.setText("D12");
    D12.addActionListener(new HackSackFrame_D12_actionAdapter(this));
    D100.setFont(new java.awt.Font("Dialog", 0, 9));
    D100.setToolTipText("Roll a D100");
    D100.setText("D100");
    D100.addActionListener(new HackSackFrame_D100_actionAdapter(this));
    D10.setFont(new java.awt.Font("Dialog", 0, 9));
    D10.setToolTipText("Roll a D10");
    D10.setText("D10");
    D10.addActionListener(new HackSackFrame_D10_actionAdapter(this));
    D8.setFont(new java.awt.Font("Dialog", 0, 9));
    D8.setToolTipText("Roll a D8");
    D8.setText("D8");
    D8.addActionListener(new HackSackFrame_D8_actionAdapter(this));
    D6.setFont(new java.awt.Font("Dialog", 0, 9));
    D6.setToolTipText("Roll a D6");
    D6.setText("D6");
    D6.addActionListener(new HackSackFrame_D6_actionAdapter(this));
    D5.setFont(new java.awt.Font("Dialog", 0, 9));
    D5.setToolTipText("Roll a D5");
    D5.setText("D5");
    D5.addActionListener(new HackSackFrame_D5_actionAdapter(this));
    D4.setFont(new java.awt.Font("Dialog", 0, 9));
    D4.setToolTipText("Roll a D4");
    D4.setMargin(new Insets(2, 14, 2, 14));
    D4.setText("D4");
    D4.addActionListener(new HackSackFrame_D4_actionAdapter(this));
    DiceOutTextArea.setBackground(SystemColor.textInactiveText);
    DiceOutTextArea.setText("");
    DiceOutTextArea.setLineWrap(true);
    DiceOutTextArea.setWrapStyleWord(true);
    DiceTypePane.setBackground(Color.lightGray);
    DiceTypePane.setFont(new java.awt.Font("Dialog", 0, 9));
    DiceTypePane.setBorder(titledBorder7);
    DiceTypePane.setLayout(gridLayout4);
    D1000.setFont(new java.awt.Font("Dialog", 0, 9));
    D1000.setToolTipText("Roll a D1000");
    D1000.setText("D1000");
    D1000.addActionListener(new HackSackFrame_D1000_actionAdapter(this));
    D10000.setFont(new java.awt.Font("Dialog", 0, 9));
    D10000.setToolTipText("Roll a D10000");
    D10000.setText("D10000");
    D10000.addActionListener(new HackSackFrame_D10000_actionAdapter(this));
    DiceCountSpinner.setRequestFocusEnabled(true);
    DiceCountSpinner.setBounds(new Rectangle(8, 51, 74, 24));
    DiceModifierSpinner.setBounds(new Rectangle(8, 80, 74, 24));
    NumberOfDiceLable.setFont(new java.awt.Font("Dialog", 0, 9));
    NumberOfDiceLable.setToolTipText("Number of Dice to roll");
    NumberOfDiceLable.setText("# of Dice");
    NumberOfDiceLable.setBounds(new Rectangle(87, 52, 89, 21));
    DiceModLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    DiceModLabel.setToolTipText("A modifier applied to EACH dice rolled");
    DiceModLabel.setText("Per Dice Modifier");
    DiceModLabel.setBounds(new Rectangle(87, 82, 89, 19));
    ROLLDICE.setBounds(new Rectangle(8, 180, 73, 25));
    ROLLDICE.setFont(new java.awt.Font("Dialog", 0, 9));
    ROLLDICE.setToolTipText("Roll dem bones man!");
    ROLLDICE.setText("ROLL!");
    ROLLDICE.addActionListener(new HackSackFrame_ROLLDICE_actionAdapter(this));
    ClearRollSpace.setBounds(new Rectangle(87, 180, 90, 25));
    ClearRollSpace.setFont(new java.awt.Font("Dialog", 0, 9));
    ClearRollSpace.setToolTipText("Clear the dice roller text display.");
    ClearRollSpace.setText("Clear Space");
    ClearRollSpace.addActionListener(new
                                     HackSackFrame_ClearRollSpace_actionAdapter(this));
    DiceTotalModifier.setBounds(new Rectangle(8, 108, 74, 24));
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel1.setToolTipText("Modifier to the total rolls of all dice");
    jLabel1.setText("At Total Modifier");
    jLabel1.setBounds(new Rectangle(87, 111, 89, 15));
    DiceSidesSpinner.setBounds(new Rectangle(8, 23, 74, 24));
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel2.setToolTipText("Number of Sides on a Dice, 1-20000");
    jLabel2.setText("# Dice Sides");
    jLabel2.setBounds(new Rectangle(87, 26, 79, 15));
    D2.setFont(new java.awt.Font("Dialog", 0, 9));
    D2.setToolTipText("Roll a D2");
    D2.setText("D2");
    D2.addActionListener(new HackSackFrame_D2_actionAdapter(this));
    D3.setFont(new java.awt.Font("Dialog", 0, 9));
    D3.setToolTipText("Roll a D3");
    D3.setText("D3");
    D3.addActionListener(new HackSackFrame_D3_actionAdapter(this));
    contentPane.setBackground(Color.gray);
    contentPane.setEnabled(true);
    contentPane.setDebugGraphicsOptions(0);
    contentPane.setPreferredSize(new Dimension(900, 900));
    Penetration.setBackground(Color.lightGray);
    Penetration.setFont(new java.awt.Font("Dialog", 0, 9));
    Penetration.setToolTipText("Use penetration rules.");
    Penetration.setMargin(new Insets(2, 2, 2, 2));
    Penetration.setText("Penetrate");
    Penetration.setBounds(new Rectangle(8, 156, 73, 23));
    DiceRollDetailRadio.setBackground(Color.lightGray);
    DiceRollDetailRadio.setFont(new java.awt.Font("Dialog", 0, 9));
    DiceRollDetailRadio.setToolTipText("Show dice rolls with all detail.");
    DiceRollDetailRadio.setText("Roll Detail");
    DiceRollDetailRadio.setBounds(new Rectangle(8, 134, 87, 23));
    AppendRollsToggle.setBackground(Color.lightGray);
    AppendRollsToggle.setFont(new java.awt.Font("Dialog", 0, 9));
    AppendRollsToggle.setToolTipText("Append all dice rolls to the text area.");
    AppendRollsToggle.setText("Append Rolls");
    AppendRollsToggle.setBounds(new Rectangle(180, 180, 101, 25));
//    jScrollPane1.setBounds(new Rectangle(3, 238, 356, 229));
//    mPlayerHealth.addChangeListener(new HackSackFrame_mPlayerHealth_ChangeAdapter(this));
//    jPlayerHPAdjTextArea.addChangeListener(new HackSackFrame_jPlayerHPAdjTextArea_ChangeAdapter(this));
    jGMNameTextField.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMNameTextField.setText("Creature");
    jGMNameTextField.setBounds(new Rectangle(11, 38, 210, 21));
    jGMNameTextField.addKeyListener(new
                                    HackSackFrame_jGMNameTextField_keyAdapter(this));
    jGMClassComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMClassComboBox.setBounds(new Rectangle(357, 412, 119, 21));
    jGMClassComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jGMClassComboBox.setSelectedIndex(4);
    jGMAlignmentComboBox.setSelectedIndex(4);
    jGMSizeComboBox.setSelectedIndex(2);

    jGMNumAtksSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMNumAtksSpinner.setBounds(new Rectangle(11, 570, 57, 21));
    jGMNumAtksSpinner.addChangeListener(new
                                        HackSackFrame_jGMNumAtksSpinner_changeAdapter(this));
    jGMNameLabel10.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMNameLabel10.setToolTipText("Name of the Creature");
    jGMNameLabel10.setText("Name");
    jGMNameLabel10.setBounds(new Rectangle(11, 24, 53, 15));
    jGMClassLabel11.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMClassLabel11.setToolTipText("Class of Creature, Generally Monster");
    jGMClassLabel11.setText("Class");
    jGMClassLabel11.setBounds(new Rectangle(357, 398, 64, 15));
    jGMATKLabel12.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMATKLabel12.setToolTipText(
        "Number of Attacks this creature gets per round.");
    jGMATKLabel12.setText("# Attacks");
    jGMATKLabel12.setBounds(new Rectangle(11, 556, 52, 15));
    jGMHDiceSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMHDiceSpinner.setBounds(new Rectangle(229, 570, 57, 21));
    jGMHDiceSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jGMHDLabel13.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMHDLabel13.setToolTipText(
        "Numer of Hit dice, or Level if Fighter/Mage/Thief/Cleric");
    jGMHDLabel13.setText("Hit Dice");
    jGMHDLabel13.setBounds(new Rectangle(229, 556, 65, 15));
    jGMHDModSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMHDModSpinner.setBounds(new Rectangle(291, 570, 57, 21));
    jGMHDModSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jGMHDModLabel14.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMHDModLabel14.setToolTipText("Hit dice modifier.");
    jGMHDModLabel14.setText("HD Mod");
    jGMHDModLabel14.setBounds(new Rectangle(291, 556, 40, 15));
    jGMACSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMACSpinner.setBounds(new Rectangle(11, 531, 57, 21));
    jGMACSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jGMACLabel16.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMACLabel16.setToolTipText("Creatures Armor class.");
    jGMACLabel16.setText("AC");
    jGMACLabel16.setBounds(new Rectangle(11, 517, 40, 15));
    jGMNumCreaturesSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMNumCreaturesSpinner.setBounds(new Rectangle(135, 26, 54, 21));
    jGMNumMobLabel17.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMNumMobLabel17.setToolTipText("Total number of creatures of this type.");
    jGMNumMobLabel17.setText("# Creatures");
    jGMNumMobLabel17.setBounds(new Rectangle(135, 11, 58, 15));
    jGMMakeSheetButton.setBounds(new Rectangle(197, 26, 116, 21));
    jGMMakeSheetButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMakeSheetButton.setToolTipText("Add this creature to the battle sheet.");
    jGMMakeSheetButton.setText("Add Creature");
    jGMMakeSheetButton.addActionListener(new
                                         HackSackFrame_jGMMakeSheetButton_actionAdapter(this));
//    jGMScrollPane.setBounds(new Rectangle(4, 241, 497, 221));
    jGMClearButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMClearButton.setToolTipText("Clear dice box of all text.");
    jGMClearButton.setText("Clear");
    jGMClearButton.addActionListener(new
                                     HackSackFrame_jGMClearButton_actionAdapter(this));
    jGMAppendCheckBox.setBackground(SystemColor.activeCaptionBorder);
    jGMAppendCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMAppendCheckBox.setToolTipText(
        "Append all text in dice box to keep running log.");
    jGMAppendCheckBox.setSelected(true);
    jGMAppendCheckBox.setText("Append");
    jGMDetailsCheckBox.setBackground(SystemColor.activeCaptionBorder);
    jGMDetailsCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMDetailsCheckBox.setToolTipText("Detail of all dice rolls.");
    jGMDetailsCheckBox.setText("Details");
//    BattleSheetTabPanel.setVisible(false);
//    gridLayout1.setRows(nMaxSheetRows);
    jGMCritPanel.setBackground(Color.lightGray);
    jGMCritPanel.setBorder(titledBorder3);
    jGMCritPanel.setLayout(borderLayout6);
    jGMFumbleButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMFumbleButton.setPreferredSize(new Dimension(85, 22));
    jGMFumbleButton.setToolTipText("Roll a fumble result.");
    jGMFumbleButton.setText("Fumble!");
    jGMFumbleButton.addActionListener(new
                                      HackSackFrame_jGMFumbleButton_actionAdapter(this));
    jGMUnarmedCheckBox.setBackground(Color.lightGray);
    jGMUnarmedCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMUnarmedCheckBox.setToolTipText("The person fumbling has no weapon.");
    jGMUnarmedCheckBox.setText("Unarmed Fumble");
    jGMSpellMishapButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMSpellMishapButton.setPreferredSize(new Dimension(85, 22));
    jGMSpellMishapButton.setToolTipText("Roll spell mishap result.");
    jGMSpellMishapButton.setText("Mishap!");
    jGMSpellMishapButton.addActionListener(new
                                           HackSackFrame_jGMSpellMishapButton_actionAdapter(this));
    jGMCritButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritButton.setPreferredSize(new Dimension(68, 24));
    jGMCritButton.setToolTipText("Generate a critical hit!");
    jGMCritButton.setText("Crit Hit!");
    jGMCritButton.addActionListener(new
                                    HackSackFrame_jGMCritButton_actionAdapter(this));
    jGMCritClassComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritClassComboBox.setPreferredSize(new Dimension(68, 24));
    jGMCritLevelSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritLevelSpinner.setPreferredSize(new Dimension(68, 24));
    jGMCritToHitSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritToHitSpinner.setPreferredSize(new Dimension(68, 24));
    jGMCritACSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritACSpinner.setPreferredSize(new Dimension(68, 24));
    jGMCritWeaponComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritWeaponComboBox.setPreferredSize(new Dimension(68, 24));
    jGMCritTSizeComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritTSizeComboBox.setPreferredSize(new Dimension(68, 24));
    jGMCritASizeComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMCritASizeComboBox.setPreferredSize(new Dimension(68, 24));
    jLabel10.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel10.setToolTipText("Class of attacker.");
    jLabel10.setText("Class");
    jLabel11.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel11.setToolTipText("Level of attacker.");
    jLabel11.setText("Level");
    jLabel12.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel12.setToolTipText("To hit bonuses (any)");
    jLabel12.setText("To-Hit");
    jLabel13.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel13.setToolTipText("Armor class of target.");
    jLabel13.setVerifyInputWhenFocusTarget(true);
    jLabel13.setText("Def AC");
    jLabel14.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel14.setToolTipText("Type of weapon used.");
    jLabel14.setText("Wpn Type");
    jLabel15.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel15.setToolTipText("The size rating of the target creature.");
    jLabel15.setText("Target Size");
    jLabel16.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel16.setToolTipText("The size rating of the attacker.");
    jLabel16.setText("Attacker Size");
//    jGMCritLocComboBox.setBounds(new Rectangle(330, 70, 76, 21));
    jLabel17.setFont(new java.awt.Font("Dialog", 2, 11));
    jLabel17.setToolTipText("Specify the body location.  (optional)");
    jLabel17.setText("Location");
    jCleanSheetButton.setBounds(new Rectangle(10, 26, 116, 21));
    jCleanSheetButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jCleanSheetButton.setToolTipText("Clear Battle sheet of all creatures.");
    jCleanSheetButton.setText("Reset Battle Sheet");
    jCleanSheetButton.addActionListener(new
                                        HackSackFrame_jCleanSheetButton_actionAdapter(this));
    jCleanSheetButton.addActionListener(new
                                        HackSackFrame_jCleanSheetButton_actionAdapter(this));
    jCleanSheetButton.addActionListener(new
                                        HackSackFrame_jCleanSheetButton_actionAdapter(this));
    jSheetTotalLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jSheetTotalLabel.setToolTipText(
        "Total number of creatures on battle sheet.");
    jSheetTotalLabel.setText("");
    jSheetTotalLabel.setBounds(new Rectangle(10, 55, 116, 21));
    LootBag.setLayout(null);
    jTRTypePanel.setBackground(Color.gray);
    jTRTypePanel.setBounds(new Rectangle(3, 2, 379, 192));
//    jTRTypePanel.setLayout(gridLayout2);
    jTRTypePanel.setLayout(gridBagLayout2);
    jTRScrollPane.setBounds(new Rectangle(2, 197, 614, 262));
    jTRResetButton.setBounds(new Rectangle(383, 161, 106, 25));
    jTRResetButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRResetButton.setToolTipText("Clear all treasure type settings.");
    jTRResetButton.setText("Reset");
    jTRResetButton.addActionListener(new
                                     HackSackFrame_jTRResetButton_actionAdapter(this));
    jButton1.setBounds(new Rectangle(622, 197, 73, 25));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton1.setFocusPainted(true);
    jButton1.setText("Clear");
    jButton1.addActionListener(new HackSackFrame_jButton1_actionAdapter(this));
    jTRAppendCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRAppendCheckBox.setFocusPainted(true);
    jTRAppendCheckBox.setText("Append");
    jTRAppendCheckBox.setBounds(new Rectangle(622, 223, 83, 23));
    jTRDetailsCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRDetailsCheckBox.setFocusPainted(true);
    jTRDetailsCheckBox.setText("Details");
    jTRDetailsCheckBox.setBounds(new Rectangle(622, 246, 83, 23));
    jGMCreatureInfoTab.setBackground(Color.lightGray);
    jGMCreatureInfoTab.setBorder(titledBorder2);
    jGMCreatureInfoTab.setLayout(null);
    jGMMoraleCheckButton.setBounds(new Rectangle(6, 37, 91, 21));
    jGMMoraleCheckButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMoraleCheckButton.setToolTipText("Check Morale of a Creature.");
    jGMMoraleCheckButton.setText("Check Morale");
    jGMMoraleCheckButton.addActionListener(new
                                           HackSackFrame_jGMMoraleCheckButton_actionAdapter(this));
    jGMMoraleRatingSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMoraleRatingSpinner.setBounds(new Rectangle(103, 37, 66, 21));
    jGMMoraleLabel.setText("Morale Rating");
    jGMMoraleLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMoraleLabel.setToolTipText("Morale rating from the HoB.");
    jGMMoraleLabel.setBounds(new Rectangle(103, 24, 66, 12));
    jGMMoraleCheckPanel.setBackground(Color.lightGray);
    jGMMoraleCheckPanel.setBorder(titledBorder4);
    jGMMoraleCheckPanel.setBounds(new Rectangle(6, 211, 468, 118));
    jGMMoraleCheckPanel.setLayout(gMoraleCheckLayout);
    jGMMoraleSpinnerPanel.setBackground(SystemColor.activeCaptionBorder);
    jGMMoraleSpinnerPanel.setBorder(titledBorder5);
    jGMMoraleSpinnerPanel.setBounds(new Rectangle(6, 87, 468, 116));
    jGMMoraleSpinnerPanel.setLayout(gMoraleSpinnerLayout);
    jGMSpecHPLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMSpecHPLabel.setToolTipText("Specific HP value. Example: 12 or 1d4+10");
    jGMSpecHPLabel.setText("Spec HP");
    jGMSpecHPLabel.setBounds(new Rectangle(351, 556, 40, 15));
    jGMMoraleResetButton.setBounds(new Rectangle(175, 37, 91, 21));
    jGMMoraleResetButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMoraleResetButton.setToolTipText(
        "Reset all values to default for morale checks.");
    jGMMoraleResetButton.setText("Reset");
    jGMMoraleResetButton.addActionListener(new
                                           HackSackFrame_jGMMoraleResetButton_actionAdapter(this));
    jGMSpecCritRoll.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMSpecCritRoll.setPreferredSize(new Dimension(68, 24));
    jLabel18.setFont(new java.awt.Font("Dialog", 2, 11));
    jLabel18.setToolTipText("Specify the roll for location.  (optional)");
    jLabel18.setText("Roll");
    jTRMakeItemButton.setBounds(new Rectangle(7, 34, 99, 22));
    jTRMakeItemButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRMakeItemButton.setToolTipText("Generate a specific type of magic item.");
    jTRMakeItemButton.setText("Make Item");
    jTRMakeItemButton.addActionListener(new
                                        HackSackFrame_jTRMakeItemButton_actionAdapter(this));
    jTRMakeItemLabel.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRMakeItemLabel.setToolTipText("Group to generate magic item from.");
    jTRMakeItemLabel.setText("Type of Item");
    jTRMakeItemLabel.setBounds(new Rectangle(115, 21, 97, 15));
    jTRMakeItemLabel2.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRMakeItemLabel2.setToolTipText("Number of items to generate.");
    jTRMakeItemLabel2.setText("Number");
    jTRMakeItemLabel2.setBounds(new Rectangle(222, 21, 40, 15));
    jTRMakeItemSpinner.setBounds(new Rectangle(222, 34, 52, 22));
    jTRMakeItemPanel.setBackground(Color.lightGray);
    jTRMakeItemPanel.setBorder(titledBorder6);
    jTRMakeItemPanel.setBounds(new Rectangle(384, 3, 288, 75));
    jTRMakeItemPanel.setLayout(null);
    jTRGenButton.setToolTipText("Generate treasure type.");
    jGMSaveButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMSaveButton.setPreferredSize(new Dimension(95, 22));
    jGMSaveButton.setToolTipText(
        "Save the current creature to file to use again later.");
    jGMSaveButton.setText("Save Creature");
    jGMSaveButton.addActionListener(new
                                    HackSackFrame_jGMSaveButton_actionAdapter(this));
    LootBag.setDebugGraphicsOptions(0);
    jDiceRollerPanel.setBackground(Color.lightGray);
    jDiceRollerPanel.setFont(new java.awt.Font("Dialog", 0, 9));
    jDiceRollerPanel.setBorder(titledBorder8);
    jDiceRollerPanel.setPreferredSize(new Dimension(180, 220));
    jDiceRollerPanel.setLayout(null);
    jGMLoadBSButton.setBounds(new Rectangle(197, 81, 116, 21));
    jGMLoadBSButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMLoadBSButton.setToolTipText("Load a previously saved battle sheet.");
    jGMLoadBSButton.setText("Load Saved Sheet");
    jGMLoadBSButton.addActionListener(new
                                      HackSackFrame_jGMLoadBSButton_actionAdapter(this));
    jGMSaveBSCheckBox1.setBackground(Color.lightGray);
    jGMSaveBSCheckBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMSaveBSCheckBox1.setToolTipText(
        "Append saved battle sheet to current battle sheet panel.");
    jGMSaveBSCheckBox1.setSelected(true);
    jGMSaveBSCheckBox1.setText("Append Saved Sheet");
    jGMSaveBSCheckBox1.setBounds(new Rectangle(10, 84, 119, 21));
    jGMBSReopenButton.setBounds(new Rectangle(197, 54, 116, 21));
    jGMBSReopenButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMBSReopenButton.setToolTipText(
        "Opens up the battle sheet panel (incase you closed it).");
    jGMBSReopenButton.setText("Open Battle Panel");
    jGMBSReopenButton.addActionListener(new
                                        HackSackFrame_jGMBSReopenButton_actionAdapter(this));
    jMenuFileExit.addActionListener(new
                                    HackSackFrame_jMenuFileExit_ActionAdapter(this));
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new
                                    HackSackFrame_jMenuFileExit_ActionAdapter(this));
    WarRoomPanel.setLayout(borderLayout10);
    jGMAttackPanel.setBackground(Color.lightGray);
    jGMAttackPanel.setBorder(titledBorder11);
    jGMAttackPanel.setLayout(gridLayout1);
    jGMEXPSpinner.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMEXPSpinner.setBounds(new Rectangle(11, 117, 112, 21));
    jGMEXPSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jLabel20.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel20.setToolTipText("Experience award for this creature.");
    jLabel20.setText("EXP");
    jLabel20.setBounds(new Rectangle(11, 103, 40, 15));
    jGMMoraleSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMMoraleSpinner.setBounds(new Rectangle(11, 491, 57, 21));
    jGMMoraleSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jLabel21.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel21.setToolTipText("Morale rating of this creature.");
    jLabel21.setText("Morale");
    jLabel21.setBounds(new Rectangle(11, 477, 40, 15));
    jLabel22.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel22.setText("Size");
    jLabel22.setBounds(new Rectangle(11, 398, 40, 15));
    jGMSizeComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMSizeComboBox.setBounds(new Rectangle(11, 412, 112, 21));
    jGMSizeComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jLabel23.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel23.setText("Alignment");
    jLabel23.setBounds(new Rectangle(11, 301, 72, 15));
    jGMAlignmentComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMAlignmentComboBox.setBounds(new Rectangle(11, 315, 112, 21));
    jGMAlignmentComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jGMHackFactorSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMHackFactorSpinner.setBounds(new Rectangle(11, 78, 54, 21));
    jGMHackFactorSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jLabel24.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel24.setToolTipText("Hackfactor rating of this creature.");
    jLabel24.setText("HackFactor");
    jLabel24.setBounds(new Rectangle(11, 64, 60, 15));
    jGMDescTextArea.setText("Description: ");
    jGMDescTextArea.setLineWrap(true);
    jGMDescTextArea.setWrapStyleWord(true);
    jGMDescTextArea.addKeyListener(new HackSackFrame_jGMDescTextArea_keyAdapter(this));

    jGMBSCreatorPanel.setBackground(Color.lightGray);
    jGMBSCreatorPanel.setBorder(titledBorder12);
    jGMBSCreatorPanel.setPreferredSize(new Dimension(1, 120));
    jGMBSCreatorPanel.setLayout(null);
    jGMMoveSpinner.setBounds(new Rectangle(11, 452, 57, 21));
    jGMMoveSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jLabel19.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel19.setToolTipText("Move base of this creature.");
    jLabel19.setText("Move");
    jLabel19.setBounds(new Rectangle(11, 438, 40, 15));
    jGMResetCSButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMResetCSButton.setPreferredSize(new Dimension(95, 22));
    jGMResetCSButton.setToolTipText("Reset fields to default.");
    jGMResetCSButton.setText("Reset");
    jGMResetCSButton.addActionListener(new
                                       HackSackFrame_jGMResetCSButton_actionAdapter(this));
    PlayerPanel.setLayout(null);
//    jplAbilitiesPanel.setBackground(SystemColor.activeCaptionBorder);
//    jplAbilitiesPanel.setBorder(titledBorder15);
//    jplCoinPanel.setBackground(Color.lightGray);
//    jplCoinPanel.setBorder(titledBorder18);
    jCreatureListPanel.setBackground(Color.gray);
    jCreatureListPanel.setBorder(titledBorder24);
    jCreatureListPanel.setPreferredSize(new Dimension(335, 80));
    jCreatureListPanel.setLayout(borderLayout7);

    jFindCreatureTextField.setFont(new java.awt.Font("Dialog", 0, 9));
    jFindCreatureTextField.setMinimumSize(new Dimension(50, 16));
    jFindCreatureTextField.setPreferredSize(new Dimension(130, 20));
    jFindCreatureTextField.setText("");
    jFindCreatureTextField.addKeyListener(new
                                          HackSackFrame_jFindCreatureTextField_keyAdapter(this));
    jLabel49.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel49.setText("Find");
    jplLoadButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jplLoadButton.setToolTipText("Load a HackSACK saved character.");
    jplLoadButton.setText("Load Character");
    jplLoadButton.addActionListener(new
                                    HackSackFrame_jplLoadButton_actionAdapter(this));
    jplOpenGroupButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jplOpenGroupButton.setText("Open Group Panel");
    jplOpenGroupButton.addActionListener(new
                                         HackSackFrame_jplOpenGroupButton_actionAdapter(this));
    jGMFatigueFactorSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMFatigueFactorSpinner.setBounds(new Rectangle(229, 452, 57, 21));
    jGMFatigueFactorSpinner.addChangeListener(new
        HackSackFrame_jGMHackFactorSpinner_changeAdapter(this));
    jLabel48.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel48.setToolTipText("Fatigue Factor for this creature.");
    jLabel48.setText("Fatigue");
    jLabel48.setBounds(new Rectangle(229, 438, 60, 15));
    jplLoadPartyButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jplLoadPartyButton.setToolTipText("Load previously saved party.");
    jplLoadPartyButton.setText("Load Saved Group");
    jplLoadPartyButton.addActionListener(new
                                         HackSackFrame_jplLoadPartyButton_actionAdapter(this));
    jGMHonorComboBox.setBounds(new Rectangle(229, 412, 117, 21));
    jGMHonorComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jGMHonorComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMHonorComboBox.setSelectedIndex(1);
    jLabel50.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel50.setToolTipText("Creatures Honor.");
    jLabel50.setText("Honor");
    jLabel50.setBounds(new Rectangle(229, 398, 42, 15));
    WarRoomPanel.setBackground(Color.lightGray);
    PlayerPanel.setBackground(SystemColor.activeCaptionBorder);
    PlayerPanel.setVerifyInputWhenFocusTarget(true);
    jPanel1.setBackground(SystemColor.activeCaptionBorder);
    jPanel1.setForeground(Color.black);
    jPanel1.setBorder(titledBorder26);
    jPanel1.setBounds(new Rectangle(8, 16, 389, 145));
    jPanel1.setLayout(gridLayout3);
    jGMDiceBagTabbedPane.setBackground(SystemColor.activeCaptionBorder);
    jGMDiceBagTabbedPane.setPreferredSize(new Dimension(900, 900));
    jGMChecksPanel.setLayout(null);
    jGMLogPanel.setLayout(borderLayout2);
    jGMChecksPanel.setBackground(SystemColor.activeCaptionBorder);
    jGMChecksPanel.setBorder(titledBorder27);
    jGMLogPanel.setBackground(SystemColor.activeCaptionBorder);
    jGMLogButtonPanel.setBackground(SystemColor.activeCaptionBorder);
    jCritTextArea.setBackground(SystemColor.textInactiveText);
    jCritTextArea.setText("");
    jCritTextArea.setLineWrap(true);
    jCritTextArea.setWrapStyleWord(true);

    jClearCritButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jClearCritButton.setPreferredSize(new Dimension(85, 22));
    jClearCritButton.setText("Clear");
    jClearCritButton.addActionListener(new
                                       HackSackFrame_jClearCritButton_actionAdapter(this));
    jCritAppendCheckBox.setBackground(Color.lightGray);
    jCritAppendCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jCritAppendCheckBox.setPreferredSize(new Dimension(68, 22));
    jCritAppendCheckBox.setSelected(true);
    jCritAppendCheckBox.setText("Append");
    jCritDetailsCheckBox.setBackground(Color.lightGray);
    jCritDetailsCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jCritDetailsCheckBox.setPreferredSize(new Dimension(68, 22));
    jCritDetailsCheckBox.setText("Details");
    jGMTurnButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMTurnButton.setToolTipText("Roll turn undead dice and see results!");
    jGMTurnButton.setText("Turn!");
    jGMTurnButton.addActionListener(new
                                    HackSackFrame_jGMTurnButton_actionAdapter(this));
    jTurnTab.setBackground(Color.gray);
    jTurnTab.setFont(new java.awt.Font("Dialog", 0, 9));
    jTurnTab.setBorder(titledBorder28);
    jTurnTab.setLayout(flowLayout2);
    jMoraleTab.setBackground(Color.gray);
    jMoraleTab.setBorder(titledBorder29);
    jMoraleTab.setLayout(null);
    jLabel51.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel51.setToolTipText("Level of Cleric attempting the turn.");
    jLabel51.setText("Cleric Level:");
    jGMTurnLevelSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMTurnComboBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMRandomNameEditButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMRandomNameEditButton.setText("Add New Names");
    jGMRandomNameEditButton.addActionListener(new
                                              HackSackFrame_jGMRandomNameEditButton_actionAdapter(this));
    jGMRandomNameGenerateButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMRandomNameGenerateButton.setText("Generate Name");
    jGMRandomNameGenerateButton.addActionListener(new
                                                  HackSackFrame_jGMRandomNameGenerateButton_actionAdapter(this));
    jGMRandomNameMaleCheckBox.setBackground(Color.lightGray);
    jGMRandomNameMaleCheckBox.setToolTipText("Generate Male Name");
    jGMRandomNameMaleCheckBox.setSelected(true);
    jGMRandomNameMaleCheckBox.setText("Male");
    jGMRandomNameMaleCheckBox.setBorderPaintedFlat(false);
    jGMRandomNameMaleCheckBox.addActionListener(new
                                                HackSackFrame_jGMRandomNameMaleCheckBox_actionAdapter(this));
    jGMRandomNameFemaleCheckBox.setBackground(Color.lightGray);
    jGMRandomNameFemaleCheckBox.setToolTipText("Generate Female name.");
    jGMRandomNameFemaleCheckBox.setText("Female");
    jGMRandomNameFemaleCheckBox.addActionListener(new
                                                  HackSackFrame_jGMRandomNameFemaleCheckBox_actionAdapter(this));
    jCritFancyCheckBox.setBackground(Color.gray);
    jCritFancyCheckBox.setFont(new java.awt.Font("Dialog", 0, 11));
    jCritFancyCheckBox.setPreferredSize(new Dimension(68, 24));
    jCritFancyCheckBox.setToolTipText(
        "Display the critical hit in the pop up box.");
    jCritFancyCheckBox.setActionCommand("jCheckBox1");
    jCritFancyCheckBox.setSelected(true);
    jCritFancyCheckBox.setText("Fancy");
    jLabel52.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel52.setToolTipText(
        "Enter the dice rolled, or leave it at 0 and let HackSACK roll it.");
    jLabel52.setText("Specific Roll");
    jGMTurnSpecificSpinner.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMConfigPane.setBackground(Color.lightGray);
    jGMConfigPane.setBorder(titledBorder31);
    jGMConfigPane.setLayout(null);
    jGMConfigTOPCheckBox1.setBackground(Color.lightGray);
    jGMConfigTOPCheckBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMConfigTOPCheckBox1.setToolTipText(
        "Should a threshold of pain check be made when a creature is hit for " +
        "more than 1/2 its health?");
    jGMConfigTOPCheckBox1.setSelected(true);
    jGMConfigTOPCheckBox1.setText("Threshold of Pain Check");
    jGMConfigTOPCheckBox1.addActionListener(new
                                            HackSackFrame_jGMConfigTOPCheckBox1_actionAdapter(this));
    jGMConfigFatigueCheckBox2.setBackground(Color.lightGray);
    jGMConfigFatigueCheckBox2.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMConfigFatigueCheckBox2.setToolTipText(
        "Count down fatigue and warn when a check is needed?");
    jGMConfigFatigueCheckBox2.setText("Fatigue Warning");
    jGMConfigFatigueCheckBox2.addActionListener(new
                                                HackSackFrame_jGMConfigFatigueCheckBox2_actionAdapter(this));
    jGMConfigNewHonorButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMConfigNewHonorButton.setToolTipText("Add or remove Honor award types.");
    jGMConfigNewHonorButton.setText("Honor");
    jGMConfigNewHonorButton.addActionListener(new
                                              HackSackFrame_jGMConfigNewHonorButton_actionAdapter(this));
    jGMConfigNewEXPButton3.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMConfigNewEXPButton3.setToolTipText("Add or remove EXP awards.");
    jGMConfigNewEXPButton3.setText("EXP");
    jGMConfigNewEXPButton3.addActionListener(new
                                             HackSackFrame_jGMConfigNewEXPButton3_actionAdapter(this));
    jGmConfigNewSkillButton5.setFont(new java.awt.Font("Dialog", 0, 11));
    jGmConfigNewSkillButton5.setToolTipText(
        "Add a new skill, talent, ability, proficiency.");
    jGmConfigNewSkillButton5.setText("Skill");
    jGmConfigNewSkillButton5.addActionListener(new
                                               HackSackFrame_jGmConfigNewSkillButton5_actionAdapter(this));
    jGMConfigNewClassButton6.setEnabled(true);
    jGMConfigNewClassButton6.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMConfigNewClassButton6.setToolTipText("Add a new class.");
    jGMConfigNewClassButton6.setText("Class");
    jGMConfigNewClassButton6.addActionListener(new
                                               HackSackFrame_jGMConfigNewClassButton6_actionAdapter(this));
    jGMConfigNewGearButton7.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMConfigNewGearButton7.setToolTipText("Add new gear/item.");
    jGMConfigNewGearButton7.setText("Gear");
    jGMConfigNewGearButton7.addActionListener(new
                                              HackSackFrame_jGMConfigNewGearButton7_actionAdapter(this));
    jGMconfigNewQuirkButton8.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMconfigNewQuirkButton8.setToolTipText("Add new quirk or flaw.");
    jGMconfigNewQuirkButton8.setText("Quirk & Flaws");
    jGMconfigNewQuirkButton8.addActionListener(new
                                               HackSackFrame_jGMconfigNewQuirkButton8_actionAdapter(this));
    jPanel3.setBackground(Color.lightGray);
    jPanel3.setBorder(titledBorder34);
    jPanel3.setBounds(new Rectangle(372, 21, 191, 177));
    jPanel3.setLayout(gridLayout2);
    jPanel4.setBackground(Color.lightGray);
    jPanel4.setBorder(titledBorder33);
    jPanel4.setBounds(new Rectangle(8, 21, 333, 60));
    jPanel4.setLayout(gridLayout6);
    jPanel5.setBackground(Color.lightGray);
    jPanel5.setBorder(titledBorder32);
    jPanel5.setBounds(new Rectangle(8, 100, 333, 98));
    jPanel5.setLayout(gridLayout5);
    gridLayout2.setColumns(1);
    gridLayout2.setHgap(0);
    gridLayout2.setRows(0);
    jGMConfigDefeatAwardCheckBox1.setBackground(Color.lightGray);
    jGMConfigDefeatAwardCheckBox1.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMConfigDefeatAwardCheckBox1.setToolTipText(
        "When a creature is \"defeated\" automatically give honor and exp award " +
        "to apply at end of session.");
    jGMConfigDefeatAwardCheckBox1.setSelected(true);
    jGMConfigDefeatAwardCheckBox1.setText("Automagic Defeat Award");
    jGMConfigDefeatAwardCheckBox1.addActionListener(new
        HackSackFrame_jGMConfigDefeatAwardCheckBox1_actionAdapter(this));
    jGMConfigCritAwardCheckBox2.setBackground(Color.lightGray);
    jGMConfigCritAwardCheckBox2.setFont(new java.awt.Font("Dialog", 0, 9));
    jGMConfigCritAwardCheckBox2.setToolTipText(
        "Give honor award to player critically hit to be applied at end of " +
        "session.");
    jGMConfigCritAwardCheckBox2.setSelected(true);
    jGMConfigCritAwardCheckBox2.setText("Automagic Crit Award");
    jGMConfigCritAwardCheckBox2.addActionListener(new
                                                  HackSackFrame_jGMConfigCritAwardCheckBox2_actionAdapter(this));
    jRandomnessTabbedPane1.setBackground(SystemColor.textInactiveText);
    jRandomEncountersPanel.setLayout(null);
    jGenerateEncounterPanel.setBackground(Color.lightGray);
    jGenerateEncounterPanel.setBorder(titledBorder37);
    jGenerateEncounterPanel.setToolTipText(
        "Encounters generated will be from your creature database.");
    jRandomEncountersPanel.setBackground(Color.lightGray);
    jRandomEncountersPanel.setAlignmentX( (float) 0.5);
    jRandomEncountersPanel.setBorder(null);
    jRandomEncountersPanel.setDoubleBuffered(true);
    jRandomNamesPanel.setBackground(Color.lightGray);
    jRandomNamesPanel.setBorder(titledBorder36);
    jRandomNamesPanel.setBounds(new Rectangle(0, 5, 432, 60));
    jScrollPane3.setBounds(new Rectangle(2, 85, 432, 433));
    jRandomNameResultsTextArea.setBackground(SystemColor.activeCaptionBorder);
    jRandomNameResultsTextArea.setEditable(false);
    jRandomNameResultsTextArea.setText("");
    jRandomNameResultsTextArea.setLineWrap(true);
    jRandomNameResultsTextArea.setWrapStyleWord(true);
    jButton2.setBounds(new Rectangle(437, 85, 66, 25));
    jButton2.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton2.setToolTipText("Clear random results panel.");
    jButton2.setText("Clear");
    jButton2.addActionListener(new HackSackFrame_jButton2_actionAdapter(this));
    gridLayout4.setColumns(3);
    gridLayout4.setRows(0);
    jTRTextArea.setBackground(SystemColor.textInactiveText);
    jGMTextArea.setBackground(SystemColor.textInactiveText);

    jCreatureList.setCellRenderer(new CellRendererComboBox());

    jCreatureList.addMouseListener(new HackSackFrame_jCreatureList_mouseAdapter(this));
    jLoadCreatureButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jLoadCreatureButton.setToolTipText("Load selected creature.");
    jLoadCreatureButton.setText("Load");
    jLoadCreatureButton.addActionListener(new
                                          HackSackFrame_jLoadCreatureButton_actionAdapter(this));
    jRemoveCreatureButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jRemoveCreatureButton.setToolTipText(
        "Remove the currently selected creature.");
    jRemoveCreatureButton.setText("Remove");
    jRemoveCreatureButton.addActionListener(new
                                            HackSackFrame_jRemoveCreatureButton_actionAdapter(this));
    jCreatureList.setBackground(Color.lightGray);
    jCreatureList.setFont(new java.awt.Font("Dialog", 0, 11));
    jCreatureList.setToolTipText(
        "Double click on creature to load or select and use load button.");
    jCreatureDataTabbedPane.setBackground(Color.lightGray);
    jCreatureDataTabbedPane.setPreferredSize(new Dimension(623, 750));
    jCreatureAttacksPanel.setLayout(borderLayout3);
    jLabel53.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel53.setToolTipText(
        "Climate and Terrain this creature will be found in.");
    jLabel53.setText("Climate/Terrain");
    jLabel53.setBounds(new Rectangle(11, 143, 101, 15));
    jFrequencyComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jFrequencyComboBox.setBounds(new Rectangle(11, 196, 112, 21));
    jFrequencyComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jLabel54.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel54.setToolTipText("The frequency that this creature is encountered.");
    jLabel54.setText("Frequency");
    jLabel54.setBounds(new Rectangle(11, 182, 106, 15));
    jActivityCycleComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jActivityCycleComboBox.setBounds(new Rectangle(11, 236, 112, 21));
    jActivityCycleComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jLabel55.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel55.setToolTipText("Activity cycle of the creature, day, night, both.");
    jLabel55.setText("Activity Cycle");
    jLabel55.setBounds(new Rectangle(11, 222, 118, 15));
    jDietComboBox.setEnabled(false);
    jDietComboBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jDietComboBox.setBounds(new Rectangle(11, 275, 112, 21));
    jLabel56.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel56.setForeground(Color.red);
    jLabel56.setToolTipText("Carnivore, herbivore or omnivore.");
    jLabel56.setText("Diet");
    jLabel56.setBounds(new Rectangle(11, 261, 40, 15));
    jLabel57.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel57.setForeground(Color.red);
    jLabel57.setToolTipText("Number of creatures appearing, xDx+x (1d12+2)");
    jLabel57.setText("Number Appearing");
    jLabel57.setBounds(new Rectangle(11, 340, 106, 15));
    jNumAppSpinner.setEnabled(false);
    jNumAppSpinner.setBounds(new Rectangle(11, 354, 35, 21));
    jNumAppSizeSpinner.setEnabled(false);
    jNumAppSizeSpinner.setBounds(new Rectangle(63, 354, 35, 21));
    jNumAppAdditionalSpinner.setEnabled(false);
    jNumAppAdditionalSpinner.setBounds(new Rectangle(116, 354, 35, 21));
    jLabel58.setFont(new java.awt.Font("Dialog", 1, 9));
    jLabel58.setText("d");
    jLabel58.setBounds(new Rectangle(53, 354, 21, 19));
    jLabel59.setText("+");
    jLabel59.setBounds(new Rectangle(104, 354, 14, 15));
    jCreatureDescPanel.setBackground(Color.gray);
    jCreatureDescPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    jCreatureDescPanel.setBounds(new Rectangle(229, 39, 411, 336));
    jCreatureDescPanel.setLayout(borderLayout5);
    jLabel60.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel60.setForeground(Color.red);
    jLabel60.setToolTipText("* items not used at this time.");
    jLabel60.setText("* Items not used at this time.");
    jLabel60.setBounds(new Rectangle(11, 380, 143, 15));
    jEditTerrainButton.setBounds(new Rectangle(11, 157, 112, 23));
    jEditTerrainButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jEditTerrainButton.setToolTipText("Edit Climate and Terrain Settings");
    jEditTerrainButton.setText("Edit");
    jEditTerrainButton.addActionListener(new
                                         HackSackFrame_jEditTerrainButton_actionAdapter(this));
    jAppearingInComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jAppearingInComboBox.setBounds(new Rectangle(357, 452, 119, 21));
    jAppearingInComboBox.addActionListener(new
        HackSackFrame_jFrequencyComboBox_actionAdapter(this));
    jLabel61.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel61.setToolTipText(
        "What product does this creature appear in? HoB-1..8, Monster Matrix " +
        "or the Field Manual");
    jLabel61.setText("Appearing In");
    jLabel61.setBounds(new Rectangle(357, 438, 83, 15));
    jFilterNoAttackCheckBox.setBackground(Color.gray);
    jFilterNoAttackCheckBox.setFont(new java.awt.Font("Dialog", 0, 5));
    jFilterNoAttackCheckBox.setToolTipText(
        "Select this to filter out creatures that do not have attacks configured.");
    jFilterNoAttackCheckBox.setText("");
    jFilterNoAttackCheckBox.addActionListener(new
        HackSackFrame_jFilterNoAttackCheckBox_actionAdapter(this));
    jImportXMLButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jImportXMLButton.setToolTipText("Import a HMTK XML Character sheet.");
    jImportXMLButton.setText("Import Character");
    jImportXMLButton.addActionListener(new
        HackSackFrame_jImportXMLButton_actionAdapter(this));
    jDiceButtonsPanel.setBackground(Color.white);
    jDiceButtonsPanel.setLayout(boxLayout21);
    DiceOutAreaScrollPane.setPreferredSize(new Dimension(983, 620));
    jGMCritButtonsPanel.setBackground(Color.gray);
    jGMCritButtonsPanel.setMaximumSize(new Dimension(946, 46));
    jGMCritButtonsPanel.setPreferredSize(new Dimension(946, 46));
    jGMCritButtonsPanel.setLayout(flowLayout12);
    jLabel3.setText("    ");
    jLabel4.setText("   ");
    jFumbleButtonsPanel.setBackground(Color.lightGray);
    jFumbleButtonsPanel.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    flowLayout1.setHgap(0);
    jCritFumbleMisHapPanel7.setBackground(Color.lightGray);
    jCritFumbleMisHapPanel7.setBorder(border37);
    jCritFumbleMisHapPanel7.setLayout(verticalFlowLayout1);
    //verticalFlowLayout1.setAlignment(VerticalFlowLayout.TOP);
    verticalFlowLayout1.setVgap(0);
    jMishapButtonsPanel.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    flowLayout3.setHgap(0);
    jCritTextAreaButtonsPanel.setBackground(Color.lightGray);
    jCritTextAreaButtonsPanel.setLayout(flowLayout4);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout4.setHgap(0);
    jMishapButtonsPanel.setBackground(Color.gray);
    jPanel10.setBackground(Color.lightGray);
    jPanel10.setLayout(flowLayout5);
    flowLayout5.setAlignment(FlowLayout.LEFT);
    jCritListButtonsPanel.setBackground(Color.gray);
    jCritListButtonsPanel.setLayout(flowLayout6);
    flowLayout6.setAlignment(FlowLayout.LEFT);
    jPanel6.setBackground(Color.lightGray);
    jPanel6.setBounds(new Rectangle(309, 12, 330, 22));
    jPanel6.setLayout(flowLayout7);
    flowLayout7.setAlignment(FlowLayout.RIGHT);
    flowLayout7.setHgap(0);
    flowLayout7.setVgap(0);
    jPanel7.setLayout(borderLayout8);
    jCreatureAttacksPanel.setBackground(Color.lightGray);
    jPanel8.setLayout(borderLayout9);
    jPanel8.setPreferredSize(new Dimension(335, 100));
    jSupriseTab.setBackground(Color.gray);
    jSupriseTab.setBorder(titledBorder38);
    jSupriseTab.setPreferredSize(new Dimension(1103, 81));
    jSupriseTab.setLayout(flowLayout8);
    flowLayout8.setAlignment(FlowLayout.LEFT);
    jSupriseCheckButton.setFont(new java.awt.Font("Dialog", 0, 10));
    jSupriseCheckButton.setPreferredSize(new Dimension(72, 24));
    jSupriseCheckButton.setToolTipText(
        "Check for suprise using the current values.");
    jSupriseCheckButton.setText("Suprise!");
    jSupriseCheckButton.addActionListener(new
        HackSackFrame_jSupriseCheckButton_actionAdapter(this));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabel5.setToolTipText(
        "Select the range of suprise, typically is 1-3 so set value to 3.");
    jLabel5.setText("Range");
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabel6.setToolTipText(
        "Dice to roll to check for suprise, generally set to 10");
    jLabel6.setText("roll with");
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabel7.setToolTipText(
        "Instead of randomly rolling the dice, set the value here.");
    jLabel7.setText("Specific Roll");
    jSupriseRangeSpinner.setFont(new java.awt.Font("Dialog", 0, 8));
    jSupriseRangeSpinner.setPreferredSize(new Dimension(43, 20));
    jSupriseRangeSpinner.setToolTipText(
        "Select the range of suprise, typically is 1-3 so set value to 3.");
    jSupriseRangeSpinner.addChangeListener(new
        HackSackFrame_jSupriseRangeSpinner_changeAdapter(this));
    jSupriseSpecificSpinner.setFont(new java.awt.Font("Dialog", 0, 8));
    jSupriseSpecificSpinner.setPreferredSize(new Dimension(43, 20));
    jSupriseSpecificSpinner.setToolTipText(
        "Instead of randomly rolling the dice, set the value here.");
    jSupriseDiceSizeSpinner.setFont(new java.awt.Font("Dialog", 0, 8));
    jSupriseDiceSizeSpinner.setPreferredSize(new Dimension(43, 20));
    jSupriseDiceSizeSpinner.setToolTipText(
        "Dice to roll to check for suprise, generally set to 10");
    jSupriseDiceSizeSpinner.addChangeListener(new HackSackFrame_jSupriseDiceSizeSpinner_changeAdapter(this));
    jLabel8.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabel8.setToolTipText(
        "Modifier to suprise, if being suprised by elf this would be -4");
    jLabel8.setText("modifier");
    jSupriseModSpinner.setFont(new java.awt.Font("Dialog", 0, 8));
    jSupriseModSpinner.setPreferredSize(new Dimension(43, 20));
    jSupriseModSpinner.setToolTipText(
        "Modifier to suprise, if being suprised by elf this would be -4");
    jSupriseModSpinner.addChangeListener(new HackSackFrame_jSupriseModSpinner_changeAdapter(this));
    jGMSpecHP.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMSpecHP.setToolTipText("Specific HP value. Example: 12 or 1d4+10");
    jGMSpecHP.setText("0");
    jGMSpecHP.setBounds(new Rectangle(351, 570, 57, 20));
    jGMSpecHP.addFocusListener(new HackSackFrame_jGMSpecHP_focusAdapter(this));
    jGMSpecialAttack.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMSpecialAttack.setToolTipText("Special attack mode.");
    jGMSpecialAttack.setText("");
    jGMSpecialAttack.setBounds(new Rectangle(229, 491, 316, 20));
    jGMSpecialAttack.addKeyListener(new
                                    HackSackFrame_jGMSpecialAttack_keyAdapter(this));
    JGMSpecialDefense.setFont(new java.awt.Font("Dialog", 0, 11));
    JGMSpecialDefense.setToolTipText("Special defense mode.");
    JGMSpecialDefense.setText("");
    JGMSpecialDefense.setBounds(new Rectangle(229, 531, 316, 20));
    JGMSpecialDefense.addKeyListener(new
                                     HackSackFrame_jGMSpecialAttack_keyAdapter(this));
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel9.setText("Special Attack");
    jLabel9.setBounds(new Rectangle(229, 476, 75, 14));
    jLabel62.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel62.setText("Special Defense");
    jLabel62.setBounds(new Rectangle(229, 517, 75, 14));
    jGMForceSaveCheckBox.setBackground(Color.lightGray);
    jGMForceSaveCheckBox.setFont(new java.awt.Font("Dialog", 0, 10));
    jGMForceSaveCheckBox.setToolTipText(
        "Instead of asking if you want to overwrite a creature, setting this " +
        "will automatically replace it with the version you are saving.");
    jGMForceSaveCheckBox.setText("Overwrite");
    jRandomnessTabbedPane.setTabPlacement(JTabbedPane.TOP);
    jRandomnessTabbedPane.setBackground(Color.lightGray);
    jRandomnessTabbedPane.setBounds(new Rectangle(2, 5, 710, 551));
    jScrollPane2.setPreferredSize(new Dimension(700, 100));
    jEncounterResultsTextArea.setBackground(Color.lightGray);
    jEncounterResultsTextArea.setFont(new java.awt.Font("Dialog", 0, 11));
    jEncounterResultsTextArea.setText("");
    jGeneratedEncountersListScrollPane.setPreferredSize(new Dimension(259, 131));
    jEncountersScrollPane.setPreferredSize(new Dimension(259, 131));
    jPanel9.setBackground(Color.gray);
    jPanel9.setBorder(titledBorder39);
    jPanel9.setPreferredSize(new Dimension(700, 157));
    jPanel9.setLayout(borderLayout11);
    jPanel12.setBackground(Color.gray);
    jPanel12.setBorder(titledBorder40);
    jPanel12.setPreferredSize(new Dimension(310, 255));
    jPanel12.setLayout(borderLayout12);
    jPanel13.setBackground(Color.gray);
    jPanel13.setBorder(titledBorder41);
    jPanel13.setPreferredSize(new Dimension(310, 255));
    jPanel13.setLayout(borderLayout13);
    jRandomEncountersTab.setLayout(flowLayout9);
    flowLayout9.setAlignment(FlowLayout.LEFT);
    flowLayout9.setHgap(2);
    flowLayout9.setVgap(2);
    jRandomNamesTab.setLayout(null);
    jClearGeneratedButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jClearGeneratedButton.setToolTipText("Clear generated encounters.");
    jClearGeneratedButton.setText("Clear");
    jClearGeneratedButton.addActionListener(new
        HackSackFrame_jClearGeneratedButton_actionAdapter(this));
    jEncountersGeneratedList.setBackground(Color.lightGray);
    jEncountersGeneratedList.setToolTipText(
        "Double click a creature to load it in the creature panel.");
    jEncountersGeneratedList.addMouseListener(new
        HackSackFrame_jEncountersGeneratedList_mouseAdapter(this));
    jEncounterTableList.setBackground(Color.lightGray);
    jEncounterTableList.setToolTipText(
        "Double click a creature to load it in the creature panel.");
    jEncounterTableList.addMouseListener(new
        HackSackFrame_jEncounterTableList_mouseAdapter(this));
    jRandomEncountersTab.setBackground(Color.gray);
    jRandomNamesTab.setBackground(Color.gray);
    jSupriseDetailsLabel.setFont(new java.awt.Font("Dialog", 0, 11));
    jSupriseDetailsLabel.setPreferredSize(new Dimension(230, 15));
    jSupriseDetailsLabel.setText("Suprise range 1-3 on d10");
    jSupriseResetButton.setFont(new java.awt.Font("Dialog", 0, 10));
    jSupriseResetButton.setPreferredSize(new Dimension(72, 24));
    jSupriseResetButton.setToolTipText("Reset to Default values.");
    jSupriseResetButton.setText("Reset");
    jSupriseResetButton.addActionListener(new HackSackFrame_jSupriseResetButton_actionAdapter(this));
    jChecksTabbedPane.setBounds(new Rectangle(8, 19, 798, 391));
    jSupriseButtonsPanel2.setBackground(Color.gray);
    jSupriseButtonsPanel2.setPreferredSize(new Dimension(700, 35));
    jSupriseButtonsPanel2.setLayout(flowLayout10);
    flowLayout10.setAlignment(FlowLayout.LEFT);
    jSupriseScrollPane1.setPreferredSize(new Dimension(660, 250));
    jSupriseTextArea.setBackground(Color.lightGray);
    jSupriseTextArea.setEditable(false);
    jSupriseTextArea.setText("");
    jSupriseTextArea.setLineWrap(true);
    jSupriseTextArea.setWrapStyleWord(true);
    jClearSupriseTextButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jClearSupriseTextButton.setToolTipText("Clear Suprise results.");
    jClearSupriseTextButton.setText("Clear");
    jClearSupriseTextButton.addActionListener(new HackSackFrame_jClearSupriseTextButton_actionAdapter(this));
    flowLayout2.setAlignment(FlowLayout.LEFT);
    jTurnUndeadScrollPane1.setPreferredSize(new Dimension(660, 250));
    jTurnUndeadTextArea.setBackground(Color.lightGray);
    jTurnUndeadTextArea.setEnabled(true);
    jTurnUndeadTextArea.setEditable(false);
    jTurnUndeadTextArea.setText("");
    jTurnUndeadTextArea.setLineWrap(true);
    jTurnUndeadTextArea.setWrapStyleWord(true);
    jTurnUndeadClearButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jTurnUndeadClearButton.setToolTipText("Clear turn undead results.");
    jTurnUndeadClearButton.setActionCommand("jButton3");
    jTurnUndeadClearButton.setText("Clear");
    jTurnUndeadClearButton.addActionListener(new HackSackFrame_jTurnUndeadClearButton_actionAdapter(this));
    jMoraleScrollPane1.setBounds(new Rectangle(484, 14, 298, 315));
    jMoraleTextArea.setBackground(Color.lightGray);
    jMoraleTextArea.setEditable(false);
    jMoraleTextArea.setText("");
    jMoraleTextArea.setLineWrap(true);
    jMoraleTextArea.setWrapStyleWord(true);
    jMoraleClearResultsButton.setBounds(new Rectangle(405, 14, 75, 24));
    jMoraleClearResultsButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jMoraleClearResultsButton.setToolTipText("Clear morale results.");
    jMoraleClearResultsButton.setText("Clear");
    jMoraleClearResultsButton.addActionListener(new HackSackFrame_jMoraleClearResultsButton_actionAdapter(this));
    jPickPocketDetectedTab.setBackground(Color.gray);
    jPickPocketDetectedTab.setBorder(titledBorder42);
    jPickPocketDetectedTab.setMaximumSize(new Dimension(32767, 32767));
    jPickPocketDetectedTab.setLayout(flowLayout11);
    jPickPocketDetectCheckButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jPickPocketDetectCheckButton.setText("Check!");
    jPickPocketDetectCheckButton.addActionListener(new HackSackFrame_jPickPocketDetectCheckButton_actionAdapter(this));
    jLabel63.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel63.setToolTipText("The level of the person the thief is stealing from.");
    jLabel63.setText("Victim\'s Level");
    jLabel64.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel64.setToolTipText("The wisdom of the person the thief is stealing from.");
    jLabel64.setText("Victim\'s Wisdom");
    jLabel65.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel65.setToolTipText("The pickpocket roll made by the thief.");
    jLabel65.setText("Thief\'s Pickpocket Roll");
    flowLayout11.setAlignment(FlowLayout.LEFT);
    jPickPocketDetectScrollPane1.setPreferredSize(new Dimension(660, 250));
    jPickPocketDetectTextArea1.setBackground(Color.lightGray);
    jPickPocketDetectTextArea1.setEditable(false);
    jPickPocketDetectTextArea1.setText("");
    jPickPocketDetectTextArea1.setLineWrap(true);
    jPickPocketDetectTextArea1.setWrapStyleWord(true);
    jPickPocketDetectClearButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jPickPocketDetectClearButton.setToolTipText("Clear pickpocket detection results.");
    jPickPocketDetectClearButton.setText("Clear");
    jPickPocketDetectClearButton.addActionListener(new HackSackFrame_jPickPocketDetectClearButton_actionAdapter(this));
    jPickPocketDetectTargetLevelSpinner1.setPreferredSize(new Dimension(43, 20));
    jPickPocketDetectTargetWisdomSpinner1.setPreferredSize(new Dimension(43, 20));
    jPickPocketDetectThiefRollSpinner1.setPreferredSize(new Dimension(43, 20));
    jNewPlayerButton.setFont(new java.awt.Font("Dialog", 0, 11));
    jNewPlayerButton.setText("New Character");
    jNewPlayerButton.addActionListener(new HackSackFrame_jNewPlayerButton_actionAdapter(this));
    jMenuVersionInformation.setActionCommand("Version Info");
    jMenuVersionInformation.setText("OS Information");
    jMenuVersionInformation.addActionListener(new HackSackFrame_jMenuVersionInformation_actionAdapter(this));
    jMergeCoinPanel.setLayout(borderLayout14);
    jMergeCoinDisplayPanel.setBackground(Color.lightGray);
    jMergeCoinDisplayPanel.setBorder(titledBorder43);
    jMergeCoinDisplayPanel.setLayout(flowLayout18);
    flowLayout18.setAlignment(FlowLayout.LEFT);

    mergeCoin oMergeCoin = new mergeCoin();
    gridLayout3.setRows(3);
    jCritButtonPanel2.setLayout(borderLayout15);
    jGMCritClassPaneo.setLayout(borderLayout16);
    jGMCritLevelPanel.setLayout(borderLayout17);
    jGMCritToHitPanel.setLayout(borderLayout18);
    jGMCritACPanel.setLayout(borderLayout19);
    jGMCritWeaponTypePanel.setLayout(borderLayout20);
    jGMCritASizePanel.setLayout(borderLayout21);
    jGMCritTSizePanel.setLayout(borderLayout22);
    jGMCritSpecLocPanel.setLayout(borderLayout23);
    jGMCritSpecrollPanel.setLayout(borderLayout24);
    jGMCritFancyPanel.setLayout(borderLayout25);
    flowLayout12.setAlignment(FlowLayout.LEFT);
    flowLayout12.setHgap(0);
    flowLayout12.setVgap(5);
    jCritButtonPanel2.setBackground(Color.gray);
    jCritButtonPanel2.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritClassPaneo.setBackground(Color.gray);
    jGMCritClassPaneo.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritLevelPanel.setBackground(Color.gray);
    jGMCritLevelPanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritToHitPanel.setBackground(Color.gray);
    jGMCritToHitPanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritACPanel.setBackground(Color.gray);
    jGMCritACPanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritASizePanel.setBackground(Color.gray);
    jGMCritASizePanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritTSizePanel.setBackground(Color.gray);
    jGMCritTSizePanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritWeaponTypePanel.setBackground(Color.gray);
    jGMCritWeaponTypePanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritSpecLocPanel.setBackground(Color.gray);
    jGMCritSpecLocPanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritSpecrollPanel.setBackground(Color.gray);
    jGMCritSpecrollPanel.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMCritFancyPanel.setBackground(Color.gray);
    jGMCritFancyPanel.setFont(new java.awt.Font("Dialog", 0, 11));

    jGMCritLocComboBox.setPreferredSize(new Dimension(88, 24));
    jGMCritSpecSevPanel23.setLayout(borderLayout26);
    jLabel25.setBackground(Color.gray);
    jLabel25.setFont(new java.awt.Font("Dialog", 2, 11));
    jLabel25.setRequestFocusEnabled(true);
    jLabel25.setToolTipText("Specify the severity. (optional)");
    jLabel25.setText("Severity");
    jGMCritSpecSevPanel23.setBackground(Color.gray);
    jGMCritSpecSevPanel23.setFont(new java.awt.Font("Dialog", 0, 11));
    jGMSpecSevSpinner.setPreferredSize(new Dimension(68, 24));
    gridLayout5.setColumns(2);
    gridLayout5.setRows(2);
    jADDConfigPanel.setBackground(Color.lightGray);
    jADDConfigPanel.setAlignmentY((float) 0.5);
    jADDConfigPanel.setBorder(titledBorder44);
    jADDConfigPanel.setBounds(new Rectangle(569, 22, 155, 174));
    jADDConfigPanel.setLayout(gridLayout7);
    jADDKickerCheckBox.setBackground(Color.lightGray);
    jADDKickerCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jADDKickerCheckBox.setToolTipText("Do not apply +20 hp to creatures.");
    jADDKickerCheckBox.setHorizontalTextPosition(SwingConstants.TRAILING);
    jADDKickerCheckBox.setText("Disable HP Kicker");
    jADDKickerCheckBox.addActionListener(new HackSackFrame_jADDKickerCheckBox_actionAdapter(this));
    jADDPenetrationCheckBox.setBackground(Color.lightGray);
    jADDPenetrationCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jADDPenetrationCheckBox.setToolTipText("Do not use the penetration rules for damage.");
    jADDPenetrationCheckBox.setText("Disable Penetration");
    jADDPenetrationCheckBox.addActionListener(new HackSackFrame_jADDPenetrationCheckBox_actionAdapter(this));
    jADDCritCheckBox.setBackground(Color.lightGray);
    jADDCritCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jADDCritCheckBox.setToolTipText("Do not use HM critical hit rules.");
    jADDCritCheckBox.setText("Disable Crits");
    jADDCritCheckBox.addActionListener(new HackSackFrame_jADDCritCheckBox_actionAdapter(this));
    jADDFumbleCheckBox.setBackground(Color.lightGray);
    jADDFumbleCheckBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jADDFumbleCheckBox.setToolTipText("Do not use the HM fumble rules.");
    jADDFumbleCheckBox.setText("Disable Fumbles");
    jADDFumbleCheckBox.addActionListener(new HackSackFrame_jADDFumbleCheckBox_actionAdapter(this));
    gridLayout7.setColumns(1);
    gridLayout7.setRows(0);
    jMergeCoinDisplayPanel.add(oMergeCoin.getDisplayPanel());

    jFumbleButtonsPanel.add(jGMFumbleButton, null);
    jFumbleButtonsPanel.add(jGMUnarmedCheckBox, null);
    jMishapButtonsPanel.add(jGMSpellMishapButton, null);

    jCritFumbleMisHapPanel7.add(jGMCritButtonsPanel, null);
    jCritFumbleMisHapPanel7.add(jFumbleButtonsPanel, null);
    jCritFumbleMisHapPanel7.add(jMishapButtonsPanel, null);
    jCritFumbleMisHapPanel7.add(jCritTextAreaButtonsPanel, null);

    jGMCritPanel.add(jCritScrollPane, BorderLayout.CENTER);
    jGMCritPanel.add(jPanel10, BorderLayout.NORTH);
    jPanel10.add(jCritFumbleMisHapPanel7, null);
    jCritTextAreaButtonsPanel.add(jClearCritButton, null);
    jCritTextAreaButtonsPanel.add(jCritAppendCheckBox, null);
    jCritTextAreaButtonsPanel.add(jCritDetailsCheckBox, null);
    jCritScrollPane.getViewport().add(jCritTextArea, null);
    DiceBag.add(jDiceButtonsPanel, BorderLayout.NORTH);
    jDiceButtonsPanel.add(DiceTypePane, null);
    DiceTypePane.add(D10000, null);
    DiceTypePane.add(D1000, null);
    DiceTypePane.add(D100, null);
    DiceTypePane.add(D20, null);
    DiceTypePane.add(D12, null);
    DiceTypePane.add(D10, null);
    DiceTypePane.add(D8, null);
    DiceTypePane.add(D6, null);
    DiceTypePane.add(D5, null);
    DiceTypePane.add(D4, null);
    DiceTypePane.add(D3, null);
    DiceTypePane.add(D2, null);
    jDiceButtonsPanel.add(jDiceRollerPanel, null);
    jDiceRollerPanel.add(DiceSidesSpinner, null);
    jDiceRollerPanel.add(jLabel2, null);
    jDiceRollerPanel.add(ROLLDICE, null);
    jDiceRollerPanel.add(DiceCountSpinner, null);
    jDiceRollerPanel.add(NumberOfDiceLable, null);
    jDiceRollerPanel.add(DiceModifierSpinner, null);
    jDiceRollerPanel.add(DiceModLabel, null);
    jDiceRollerPanel.add(DiceTotalModifier, null);
    jDiceRollerPanel.add(jLabel1, null);
    jDiceRollerPanel.add(DiceRollDetailRadio, null);
    jDiceRollerPanel.add(Penetration, null);
    jDiceRollerPanel.add(ClearRollSpace, null);
    jDiceRollerPanel.add(AppendRollsToggle, null);
    jMenuFile.add(jMenuSavePrefs);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuVersionInformation);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jGMDiceBagTabbedPane, BorderLayout.CENTER);
//    jGMDiceBagTabbedPane.add(DiceBag,  "DiceBag");
//    jGMDiceBagTabbedPane.add(jGMCritPanel, "Crit, Fumble, Mishap");

//    jGMMakeSheetPanel..

    jGMTextArea.setText("");
    jGMTextArea.setLineWrap(true);
    jGMTextArea.setWrapStyleWord(true);
//    jTabbedPanel.add(WarRoomPanel,      "Creatures & Battlesheets");
//    jTabbedPanel.add(PlayerPanel,      "Players & Group");
//    jTabbedPanel.add(LootBag, "Loot Bag");

    jGMCritTSizeComboBox.setSelectedIndex(2);
    jGMCritASizeComboBox.setSelectedIndex(2);
    LootBag.add(jTRTypePanel, null);
    LootBag.add(jTRScrollPane, null);
    LootBag.add(jButton1, null);
    LootBag.add(jTRMakeItemPanel, null);
    jTRScrollPane.getViewport().add(jTRTextArea, null);
    jTRTextArea.setLineWrap(true);
    jTRTextArea.setWrapStyleWord(true);

//    SaxParserSkills.loadUp(this, sSkillsSaveFile);
//    SaxParserQuirks.loadUp(this, sQuirksSaveFile);

    dlgStartUp.jStartUpBar.setValue(30);
    dlgStartUp.jStartUpBar.setString("Loading data files.");

    lSkills = TableSkills.xmlGetSavedFromDoc(this, xmlControl.loadDoc(this,this,sSkillsSaveFile));
    lGear = TableGear.xmlgetSavedFromDoc(this, xmlControl.loadDoc(this,this,sGearSaveFile));
    lClass = TableClass.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sClassSaveFile));
    lRandomNames = TableRandomName.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sRandomNamesSaveFile));
    TableRandomName.RebuildSelectIndex_jGMRandomNameRaceComboBox(this);
    lEXPBonusType = EXPBonus.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sEXPBonusSaveFile));
    lHonorTypes = Honor.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sHonorSaveFile));
    lQuirks = TableQuirks.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sQuirksSaveFile));

//    TableCrit.LoadCritTable(this, "CritTable.txt");
//    SaxParserCritTable.loadUp(this,sFileCritTable);
    lTableCrit = TableCrit.xmlGetSavedFromDoc(this,xmlControl.loadDoc(this,this,sFileCritTable));
    CritResults.LoadCritLocationStrings(this);

    dlgStartUp.jStartUpBar.setValue(60);
    dlgStartUp.jStartUpBar.setString("Loading Creatures.");

    // this will allow people to load up their old creature files for now
    if (true) {
      Document doc = xmlControl.loadDoc(this, this, sCreatureSaveFileName);
      Attribute aO = doc.getRootElement().getAttribute("JDOM");
      if (aO == null) {
        System.gc();
        ShowError(this,
                  "Loading old format creature file.\nMake sure to save a creature!");
        SaxXMLParser.loadUp(this, sCreatureSaveFileName);
      }
      else {
        lSavedCreatures = TableSavedCreatures.xmlGetSavedFromDoc(this, doc);
      }
    }



    gplPlayer = new TablePlayer(this);
    gplGroupLog = new TableGroupLog(this);
    fBattleSheetFrame = new FrameBattleSheet(this, "Battle Sheet");
    fPlayerGroupFrame = new FramePlayerGroup(this, "Player Group");

    dlgStartUp.jStartUpBar.setValue(80);
    dlgStartUp.jStartUpBar.setString("Loading Tables.");

    LoadTable("fumbletable.txt", "8KK");
    LoadTable("fumbletableunarmed.txt", "17");
    LoadTable("SpellMishapTable7E.txt", "7E");
    LoadTable("Spellmishaptable7F.txt", "7F");
    LoadTable("Spellmishaptable7G.txt", "7G");
    LoadTable("Spellmishaptable7H.txt", "7H");
    LoadTable("Spellmishaptable7I.txt", "7I");
    LoadTable("CritTable8FF.txt", "8FF");
    LoadTable("CritTable8GG.txt", "8GG");


    TableCritBody.LoadCritTableBody(this, "CritTable83.txt", "83", 2);
    TableCritBody.LoadCritTableBody(this, "CritTable84_5.txt", "84_5", 2);
    TableCritBody.LoadCritTableBody(this, "CritTable86_7.txt", "86_7", 2);
    TableCritBody.LoadCritTableBody(this, "CritTable8EE.txt", "8EE", 2);

    TableTreasure.LoadTableTreasure(this, "TreasureTable13S.txt", "13S");
    TableMorale.LoadTableMorale(this, "MoraleTable8Z_8AA.txt", "8Z_8AA");
    LoadTable("MoraleTable8BB.txt", "8BB");
    LoadTableMoraleButtons();

    LoadTable("TreasureTable13B.txt", "13B");
    LoadTable("TreasureTable13C.txt", "13C");
    LoadTable("TreasureTable13D.txt", "13D");
    LoadTable("TreasureTable13E.txt", "13E");
    LoadTable("TreasureTable13F.txt", "13F");
    LoadTable("TreasureTable13G.txt", "13G");
    LoadTable("TreasureTable13H.txt", "13H");
    LoadTable("TreasureTable13IQ.txt", "13IQ");
    LoadTable("TreasureTable13IS.txt", "13IS");

    // lTableMagicItems
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA2.txt", "A2");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA3.txt", "A3");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA4.txt", "A4");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA5.txt", "A5");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA6.txt", "A6");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA7.txt", "A7");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA8.txt", "A8");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA9.txt", "A9");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA10.txt", "A10");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA11.txt", "A11");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA12.txt", "A12");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA13.txt", "A13");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA14.txt", "A14");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA15.txt", "A15");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA16.txt", "A16");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA17.txt", "A17");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA20.txt", "A20");
    TableMagicItem.LoadTableMagicItems(this, "MagicItemTableA24.txt", "A24");
    // simple tables, load into normal lTables list
    LoadTable("MagicItemTableA1.txt", "A1");
    LoadTable("MagicItemTableA18.txt", "A18");
    LoadTable("MagicItemTableA19.txt", "A19");
    LoadTable("MagicItemTableA20.txt", "A20");
    LoadTable("MagicItemTableA21.txt", "A21");
    LoadTable("MagicItemTableA22_A.txt", "A22_A");
    LoadTable("MagicItemTableA22_B.txt", "A22_B");
    LoadTable("MagicItemTableB2.txt", "B2");
    LoadTable("MagicItemTableB4.txt", "B4");
    LoadTable("MagicItemTableB5.txt", "B5");
    LoadTable("MagicItemTableB6.txt", "B6");
    LoadTable("MagicItemTableB8.txt", "B8");
    LoadTable("MagicItemTableB9.txt", "B9");
    LoadTable("MagicItemTableB10.txt", "B10");
    LoadTable("MagicItemTableB11.txt", "B11");
    LoadTable("MagicItemTableB13.txt", "B13");
    LoadTable("MagicItemTableB16.txt", "B16");
    LoadTable("MagicItemTableB17.txt", "B17");
    LoadTable("MagicItemTableB18.txt", "B18");
    LoadTable("MagicItemTableB19.txt", "B19");
    LoadTable("MagicItemTableB20.txt", "B20");
    LoadTable("MagicItemTableB22.txt", "B22");
    LoadTable("MagicItemTableB24.txt", "B24");
    LoadTable("MagicItemTableB25.txt", "B25");
    LoadTable("MagicItemTableB31.txt", "B31");
    LoadTable("MagicItemTableB34.txt", "B34");
    LoadTable("MagicItemTableB37.txt", "B37");
    LoadTable("MagicItemTableB38.txt", "B38");
    LoadTable("MagicItemTableB39.txt", "B39");
    LoadTable("MagicItemTableB41.txt", "B41");
    LoadTable("MagicItemTableB42.txt", "B42");
    LoadTable("MagicItemTableB43.txt", "B43");
    LoadTable("MagicItemTableB48.txt", "B48");
    LoadTable("MagicItemTableB49.txt", "B49");
    LoadTable("MagicItemTableB53.txt", "B53");
    LoadTable("MagicItemTableB54.txt", "B54");
    LoadTable("MagicItemTableB58.txt", "B58");
    LoadTable("MagicItemTableB60.txt", "B60");
    LoadTable("MagicItemTableB63.txt", "B63");
    LoadTable("MagicItemTableB69.txt", "B69");
    LoadTable("MagicItemTableB72.txt", "B72");
    LoadTable("MagicItemTableB73.txt", "B73");
    LoadTable("MagicItemTableB76.txt", "B76");
    LoadTable("MagicItemTableB77.txt", "B77");

    LoadTable("MagicItemTableB78.txt", "B78");
    LoadTable("MagicItemTableB79.txt", "B79");
    LoadTable("MagicItemTableB80.txt", "B80");
    LoadTable("MagicItemTableB81.txt", "B81");
    LoadTable("MagicItemTableB83.txt", "B83");
    LoadTable("MagicItemTableB84.txt", "B84");
    LoadTable("MagicItemTableB85.txt", "B85");
    LoadTable("MagicItemTableB89.txt", "B89");

    TableSave.LoadSaveTables(this, "SaveTable12G.txt", "CLERIC");
    TableSave.LoadSaveTables(this, "SaveTable12H.txt", "FIGHTER");
    TableSave.LoadSaveTables(this, "SaveTable12I.txt", "MAGE");
    TableSave.LoadSaveTables(this, "SaveTable12J.txt", "THIEF");

    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1A.txt", "1A", 0);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1B.txt", "1B", 1);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1C.txt", "1C", 2);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1D.txt", "1D", 3);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1E.txt", "1E", 4);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1F.txt", "1F", 5);
    TableAbilityScores.LoadAbilityTable(this, "AbilityTable1G.txt", "1G", 6);

    TableEncum.LoadEncumTable(this, "Table9Y.txt"); // load encum table

    TableTurnUndead.LoadTurnTable(this, "Table12K.txt", "1K");
    TableAttackMatrix.LoadAttackMatrix(this, "Table8h.txt", "8H",
                                       lMonsterMatrix);
    TableAttackMatrix.LoadAttackMatrix(this, "Table8i.txt", "8I", lClericMatrix);
    TableAttackMatrix.LoadAttackMatrix(this, "Table8j.txt", "8J", lMageMatrix);
    TableAttackMatrix.LoadAttackMatrix(this, "Table8k.txt", "8K", lThiefMatrix);
    TableAttackMatrix.LoadAttackMatrix(this, "Table8l.txt", "8L",
                                       lWarriorMatrix);

    jGMCritSpecSevPanel23.add(jGMSpecSevSpinner, BorderLayout.SOUTH);
    jGMCritSpecSevPanel23.add(jLabel25, BorderLayout.NORTH);
    jGMCritFancyPanel.add(jLabel4, BorderLayout.NORTH);
    jGMCritFancyPanel.add(jCritFancyCheckBox, BorderLayout.CENTER);
    jGMCritSpecrollPanel.add(jGMSpecCritRoll,  BorderLayout.SOUTH);
    jGMCritSpecrollPanel.add(jLabel18, BorderLayout.NORTH);
    jGMCritSpecLocPanel.add(jGMCritLocComboBox,  BorderLayout.SOUTH);
    jGMCritSpecLocPanel.add(jLabel17, BorderLayout.NORTH);
    jGMCritTSizePanel.add(jGMCritTSizeComboBox,  BorderLayout.SOUTH);
    jGMCritTSizePanel.add(jLabel15, BorderLayout.NORTH);
    jGMCritASizePanel.add(jGMCritASizeComboBox,  BorderLayout.SOUTH);
    jGMCritASizePanel.add(jLabel16, BorderLayout.NORTH);
    jGMCritWeaponTypePanel.add(jGMCritWeaponComboBox,  BorderLayout.SOUTH);
    jGMCritWeaponTypePanel.add(jLabel14, BorderLayout.NORTH);
    jGMCritACPanel.add(jGMCritACSpinner,  BorderLayout.SOUTH);
    jGMCritACPanel.add(jLabel13, BorderLayout.NORTH);
    jGMCritToHitPanel.add(jGMCritToHitSpinner,  BorderLayout.SOUTH);
    jGMCritToHitPanel.add(jLabel12, BorderLayout.NORTH);
    jGMCritLevelPanel.add(jGMCritLevelSpinner,  BorderLayout.SOUTH);
    jGMCritLevelPanel.add(jLabel11, BorderLayout.NORTH);
    jGMCritClassPaneo.add(jGMCritClassComboBox,  BorderLayout.SOUTH);
    jGMCritClassPaneo.add(jLabel10, BorderLayout.NORTH);
    jCritButtonPanel2.add(jGMCritButton,  BorderLayout.SOUTH);
    jCritButtonPanel2.add(jLabel3, BorderLayout.NORTH);

    jGMCritButtonsPanel.add(jCritButtonPanel2, null);
    jGMCritButtonsPanel.add(jGMCritClassPaneo, null);
    jGMCritButtonsPanel.add(jGMCritLevelPanel, null);
    jGMCritButtonsPanel.add(jGMCritToHitPanel, null);
    jGMCritButtonsPanel.add(jGMCritWeaponTypePanel, null);
    jGMCritButtonsPanel.add(jGMCritACPanel, null);

    jGMCritButtonsPanel.add(jGMCritASizePanel, null);
    jGMCritButtonsPanel.add(jGMCritTSizePanel, null);
    jGMCritButtonsPanel.add(jGMCritSpecrollPanel, null);
    jGMCritButtonsPanel.add(jGMCritSpecLocPanel, null);

    jGMCritButtonsPanel.add(jGMCritSpecSevPanel23, null);
    jGMCritButtonsPanel.add(jGMCritFancyPanel, null);

    // do these together
    LoadMagicTableList("A1");
    jTRMakeItemComboBox = new JComboBox(GetMagicTableNames());
    jTRMakeItemComboBox.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRMakeItemComboBox.setBounds(new Rectangle(115, 34, 99, 22));
    jTRMakeItemPanel.add(jTRMakeItemComboBox, null);
    jTRMakeItemPanel.add(jTRMakeItemButton, null);
    jTRMakeItemPanel.add(jTRMakeItemSpinner, null);
    jTRMakeItemPanel.add(jTRMakeItemLabel, null);
    jTRMakeItemPanel.add(jTRMakeItemLabel2, null);
    LootBag.add(jTRAppendCheckBox, null);
    LootBag.add(jTRDetailsCheckBox, null);
//    jGMDiceBagTabbedPane.add(jGMConfigPane, "Config");
    //

    LoadTableTreasureButtons();
// encounter stuff
    for (int i = 0; i < sTerrain.length; i++) {
      Terrain oT = new Terrain(sTerrain[i]);
//      oT.jActive.setSelected(true);
      aTerrain.add(oT);
    }
    for (int i = 0; i < sClimate.length; i++) {
      Climate oT = new Climate(sClimate[i]);
//      oT.jActive.setSelected(true);
      aClimate.add(oT);
    }

    dlgStartUp.jStartUpBar.setValue(90);
    dlgStartUp.jStartUpBar.setString("Populating encounter tables.");

    // testing this way
    TableEncounter oTableEncounter = new TableEncounter(this, aClimate,aTerrain);
    oTableEncounter.encounterGenerateEncounterPanel(jGenerateEncounterPanel);
    //

    jTRGenButton.setBounds(new Rectangle(383, 133, 106, 25));
    jTRGenButton.setFont(new java.awt.Font("Dialog", 0, 9));
    jTRGenButton.setText("Find Loot");
    jTRGenButton.addActionListener(new HackSackFrame_jTRGenButton_actionAdapter(this));
    jGMLogButtonPanel.add(jGMClearButton, null);
    jGMLogButtonPanel.add(jGMAppendCheckBox, null);
    jGMLogButtonPanel.add(jGMDetailsCheckBox, null);
    jRandomNamesPanel.add(jGMRandomNameGenerateButton, null);
    jRandomNamesPanel.add(jGMRandomNameEditButton, null);
    jRandomNamesPanel.add(jGMRandomNameRaceComboBox, null);
    jRandomNamesPanel.add(jGMRandomNameMaleCheckBox, null);
    jRandomNamesPanel.add(jGMRandomNameFemaleCheckBox, null);
    jRandomNamesTab.add(jScrollPane3, null);
    jRandomNamesTab.add(jButton2, null);
    jScrollPane3.getViewport().add(jRandomNameResultsTextArea, null);
    jRandomEncountersPanel.add(jRandomnessTabbedPane, null);

    jGMLogPanel.add(jGMScrollPane, BorderLayout.CENTER);
    jGMLogPanel.add(jGMLogButtonPanel, BorderLayout.NORTH);
    jGMScrollPane.getViewport().add(jGMTextArea, null);
    jGMCreatureInfoTab.add(jGMNameTextField, null);
    jGMCreatureInfoTab.add(jGMNameLabel10, null);
    jGMCreatureInfoTab.add(jGMHackFactorSpinner, null);
    jGMCreatureInfoTab.add(jLabel24, null);
    jGMCreatureInfoTab.add(jGMEXPSpinner, null);
    jGMCreatureInfoTab.add(jLabel20, null);
    jGMCreatureInfoTab.add(jLabel53, null);
    jGMCreatureInfoTab.add(jFrequencyComboBox, null);
    jGMCreatureInfoTab.add(jLabel54, null);
    jGMCreatureInfoTab.add(jLabel55, null);
    jGMCreatureInfoTab.add(jNumAppSizeSpinner, null);
    jGMCreatureInfoTab.add(jLabel56, null);
    jGMCreatureInfoTab.add(jDietComboBox, null);
    jGMCreatureInfoTab.add(jLabel23, null);
    jGMCreatureInfoTab.add(jGMAlignmentComboBox, null);
    jGMCreatureInfoTab.add(jLabel57, null);
    jGMCreatureInfoTab.add(jNumAppSpinner, null);
    jGMCreatureInfoTab.add(jLabel58, null);
    jGMCreatureInfoTab.add(jLabel59, null);
    jGMCreatureInfoTab.add(jNumAppAdditionalSpinner, null);
    jGMCreatureInfoTab.add(jActivityCycleComboBox, null);
    jGMCreatureInfoTab.add(jCreatureDescPanel, null);
    jCreatureDescPanel.add(jGMDescScrollPane, BorderLayout.CENTER);
    jGMCreatureInfoTab.add(jEditTerrainButton, null);
    jGMCreatureInfoTab.add(jLabel60, null);
    jGMCreatureInfoTab.add(jLabel22, null);
    jGMCreatureInfoTab.add(jGMSizeComboBox, null);
    jGMCreatureInfoTab.add(jLabel19, null);
    jGMCreatureInfoTab.add(jGMMoveSpinner, null);
    jGMCreatureInfoTab.add(jLabel21, null);
    jGMCreatureInfoTab.add(jGMMoraleSpinner, null);
    jGMCreatureInfoTab.add(jGMACLabel16, null);
    jGMCreatureInfoTab.add(jGMACSpinner, null);
    jGMCreatureInfoTab.add(jGMATKLabel12, null);
    jGMCreatureInfoTab.add(jGMNumAtksSpinner, null);
    jGMCreatureInfoTab.add(jLabel50, null);
    jGMCreatureInfoTab.add(jGMHonorComboBox, null);
    jGMCreatureInfoTab.add(jLabel48, null);
    jGMCreatureInfoTab.add(jGMFatigueFactorSpinner, null);
    jGMCreatureInfoTab.add(jAppearingInComboBox, null);
    jGMCreatureInfoTab.add(jGMClassLabel11, null);
    jGMCreatureInfoTab.add(jGMClassComboBox, null);
    jGMCreatureInfoTab.add(jLabel61, null);
    jGMCreatureInfoTab.add(jPanel6, null);
    jCreatureDataTabbedPane.add(jGMCreatureInfoTab, "Creature Information");
    jCreatureDataTabbedPane.add(jCreatureAttacksPanel, "Attacks");
    jCreatureAttacksPanel.add(jGMAttackScrollPane, BorderLayout.CENTER);
    jGMAttackScrollPane.getViewport().add(jGMAttackPanel, null);
    jGMDescScrollPane.getViewport().add(jGMDescTextArea, null);
    WarRoomPanel.add(jPanel7, BorderLayout.CENTER);
//    jGMDiceBagTabbedPane.add(PlayerPanel, "Players & Group");
    jGMBSCreatorPanel.add(jGMNumCreaturesSpinner, null);
    jGMBSCreatorPanel.add(jGMLoadBSButton, null);
    jGMBSCreatorPanel.add(jGMMakeSheetButton, null);
    jGMBSCreatorPanel.add(jCleanSheetButton, null);
    jGMBSCreatorPanel.add(jGMSaveBSCheckBox1, null);
    jGMBSCreatorPanel.add(jGMBSReopenButton, null);
    jGMBSCreatorPanel.add(jSheetTotalLabel, null);
    jGMBSCreatorPanel.add(jGMNumMobLabel17, null);
    WarRoomPanel.add(jPanel8, BorderLayout.EAST);
    jPanel8.add(jCreatureListPanel, BorderLayout.CENTER);
    jPanel6.add(jGMForceSaveCheckBox, null);
    jPanel6.add(jGMResetCSButton, null);
    jPanel6.add(jGMSaveButton, null);
    jGMCreatureInfoTab.add(jGMHDLabel13, null);
    jGMCreatureInfoTab.add(jGMHDiceSpinner, null);
    jGMCreatureInfoTab.add(jGMHDModSpinner, null);
    jGMCreatureInfoTab.add(jGMHDModLabel14, null);
    jGMCreatureInfoTab.add(jGMSpecHPLabel, null);

    jGMCreatureInfoTab.add(jGMSpecialAttack, null);
    jGMCreatureInfoTab.add(JGMSpecialDefense, null);
    jGMCreatureInfoTab.add(jLabel9, null);
    jGMCreatureInfoTab.add(jLabel62, null);
    jGMCreatureInfoTab.add(jGMSpecHP, null);
    jPanel7.add(jCreatureDataTabbedPane, BorderLayout.CENTER);
    jCritListButtonsPanel.add(jLoadCreatureButton, null);
    jCritListButtonsPanel.add(jLabel49, null);
    jCritListButtonsPanel.add(jFindCreatureTextField, null);
    jCritListButtonsPanel.add(jFilterNoAttackCheckBox, null);
    jCritListButtonsPanel.add(jRemoveCreatureButton, null);
    jPanel8.add(jGMBSCreatorPanel, BorderLayout.NORTH);
    jCreatureListPanel.add(jCreatureListScrollPane, BorderLayout.CENTER);
    jCreatureListPanel.add(jCritListButtonsPanel, BorderLayout.SOUTH);
    jCreatureListScrollPane.getViewport().add(jCreatureList, null);

//    TablePlayer.FillPlayerPanel(this, gplPlayer);

//    jScrollPane6.getViewport().add(jplAbilitiesPanel, null);
//    jScrollPane8.getViewport().add(jplCoinPanel, null);
    jPanel1.add(jNewPlayerButton, null);
    jPanel1.add(jplLoadPartyButton, null);
    jPanel1.add(jImportXMLButton, null);
    jPanel1.add(jplOpenGroupButton, null);
    jPanel1.add(jplLoadButton, null);
    PlayerPanel.add(jPanel1, null);
//    jGMDiceBagTabbedPane.add(jRandomnessTabbedPane1, "Randomness");
//    jGMDiceBagTabbedPane.add(LootBag, "Loot Bag");
    jRandomnessTabbedPane1.add(jRandomEncountersPanel, "Random Options");
    // build the proper pull down list for turning
    jGMTurnComboBox1.removeAllItems();
    for (int iiii = 0; iiii < lTableTurn.size(); iiii++) {
      TableTurnUndead oT = (TableTurnUndead) lTableTurn.get(iiii);
      jGMTurnComboBox1.addItem(oT.sNameType);
    }
    jTurnTab.add(jGMTurnButton, null);
    jTurnTab.add(jLabel51, null);
    jTurnTab.add(jGMTurnLevelSpinner, null);
    jTurnTab.add(jGMTurnComboBox1, null);
    jTurnTab.add(jLabel52, null);
    jTurnTab.add(jGMTurnSpecificSpinner, null);
    jTurnTab.add(jTurnUndeadScrollPane1, null);
    jTurnTab.add(jTurnUndeadClearButton, null);
    jTurnUndeadScrollPane1.getViewport().add(jTurnUndeadTextArea, null);
    jGMChecksPanel.add(jChecksTabbedPane, null);
    jChecksTabbedPane.add(jMoraleTab, "Morale");
    jChecksTabbedPane.add(jTurnTab,  "Turn!");
    jChecksTabbedPane.add(jSupriseTab,  "Suprise!");
    jChecksTabbedPane.add(jPickPocketDetectedTab,  "Detect Pickpocket");
    jMoraleTab.add(jGMMoraleCheckPanel, null);
    jMoraleTab.add(jGMMoraleSpinnerPanel, null);
    jMoraleTab.add(jMoraleScrollPane1, null);
    jMoraleTab.add(jGMMoraleRatingSpinner, null);
    jMoraleTab.add(jGMMoraleCheckButton, null);
    jMoraleTab.add(jGMMoraleLabel, null);
    jMoraleTab.add(jGMMoraleResetButton, null);
    jMoraleScrollPane1.getViewport().add(jMoraleTextArea, null);
//    jGMDiceBagTabbedPane.add(WarRoomPanel, "Creatures & Battlesheets");
    // done
//    jGMDiceBagTabbedPane.add(jGMMoralePanel, "Morale & Turn Undead");
    jPanel3.add(jGMConfigTOPCheckBox1, null);
    jPanel3.add(jGMConfigFatigueCheckBox2, null);
    jPanel3.add(jGMConfigDefeatAwardCheckBox1, null);
    jPanel3.add(jGMConfigCritAwardCheckBox2, null);
    jGMConfigPane.add(jADDConfigPanel, null);
    jADDConfigPanel.add(jADDKickerCheckBox, null);
    jADDConfigPanel.add(jADDPenetrationCheckBox, null);
    jADDConfigPanel.add(jADDCritCheckBox, null);
    jADDConfigPanel.add(jADDFumbleCheckBox, null);
//    jGMDiceBagTabbedPane.add(jGMLogPanel, "Log");
    jPanel4.add(jGMConfigNewHonorButton, null);
    jPanel4.add(jGMConfigNewEXPButton3, null);
    jGMConfigPane.add(jPanel5, null);
    jGMConfigPane.add(jPanel4, null);
    jPanel5.add(jGMConfigNewClassButton6, null);
    jPanel5.add(jGMConfigNewGearButton7, null);
    jPanel5.add(jGmConfigNewSkillButton5, null);
    jPanel5.add(jGMconfigNewQuirkButton8, null);
    jGMConfigPane.add(jPanel3, null);

    // tabs loaded here
    jGMDiceBagTabbedPane.add(DiceBag, "DiceBag");
    jGMDiceBagTabbedPane.add(PlayerPanel, "Players & Group");
    jGMDiceBagTabbedPane.add(WarRoomPanel, "Creatures & Battlesheets");
    jGMDiceBagTabbedPane.add(jGMCritPanel, "Crit, Fumble, Mishap");
    jGMDiceBagTabbedPane.add(jGMChecksPanel,  "Checks");
    jGMDiceBagTabbedPane.add(jRandomnessTabbedPane1, "Randomness");
    jGMDiceBagTabbedPane.add(LootBag, "Loot Bag");
    jGMDiceBagTabbedPane.add(jGMConfigPane, "Config");
    jGMDiceBagTabbedPane.add(jGMLogPanel, "Log");
    DiceBag.add(DiceOutAreaScrollPane, BorderLayout.CENTER);
    DiceOutAreaScrollPane.getViewport().add(DiceOutTextArea, null);
    jSupriseTab.add(jLabel5, null);
    jSupriseTab.add(jSupriseRangeSpinner, null);
    jSupriseTab.add(jLabel8, null);
    jSupriseTab.add(jSupriseModSpinner, null);
    jSupriseTab.add(jLabel6, null);
    jSupriseTab.add(jSupriseDiceSizeSpinner, null);
    jSupriseTab.add(jLabel7, null);
    jSupriseTab.add(jSupriseSpecificSpinner, null);
    jRandomnessTabbedPane.add(jRandomEncountersTab, "Encounters");
    jRandomnessTabbedPane.add(jRandomNamesTab, "Names");
    jRandomEncountersTab.add(jScrollPane2, null);
    jRandomEncountersTab.add(jPanel9, null);
    jPanel9.add(jEncountersScrollPane, BorderLayout.CENTER);
    jEncountersScrollPane.getViewport().add(jEncounterResultsTextArea, null);
    jRandomNamesTab.add(jRandomNamesPanel, null);
    jScrollPane2.getViewport().add(jGenerateEncounterPanel, null);
    jRandomEncountersTab.add(jPanel12, null);
    jPanel12.add(jGeneratedEncountersListScrollPane, BorderLayout.CENTER);
    jRandomEncountersTab.add(jPanel13, null);
    jPanel13.add(jEncounterTableScrollPane, BorderLayout.CENTER);
    jRandomEncountersTab.add(jClearGeneratedButton, null);
    jEncounterTableList.setCellRenderer(new CellRendererComboBox());
    jEncounterTableList.getSelectionModel().setSelectionMode(ListSelectionModel.
        SINGLE_SELECTION);
    jEncountersGeneratedList.setCellRenderer(new
                                             CellRendererEncountersGenerated());
    jEncountersGeneratedList.getSelectionModel().setSelectionMode(
        ListSelectionModel.SINGLE_SELECTION);
    jEncounterTableScrollPane.getViewport().add(jEncounterTableList, null);
    jGeneratedEncountersListScrollPane.getViewport().add(
        jEncountersGeneratedList, null);
    jSupriseTab.add(jSupriseButtonsPanel2, null);
    jSupriseButtonsPanel2.add(jSupriseCheckButton, null);
    jSupriseButtonsPanel2.add(jSupriseResetButton, null);
    jSupriseButtonsPanel2.add(jSupriseDetailsLabel, null);
    jSupriseTab.add(jSupriseScrollPane1, null);
    jSupriseScrollPane1.getViewport().add(jSupriseTextArea, null);
    jSupriseTab.add(jClearSupriseTextButton, null);
    jMoraleTab.add(jMoraleClearResultsButton, null);
    jPickPocketDetectedTab.add(jPickPocketDetectCheckButton, null);
    jPickPocketDetectedTab.add(jLabel63, null);
    jPickPocketDetectedTab.add(jPickPocketDetectTargetLevelSpinner1, null);
    jPickPocketDetectedTab.add(jLabel64, null);
    jPickPocketDetectedTab.add(jPickPocketDetectTargetWisdomSpinner1, null);
    jPickPocketDetectedTab.add(jLabel65, null);
    jPickPocketDetectedTab.add(jPickPocketDetectThiefRollSpinner1, null);
    jPickPocketDetectedTab.add(jPickPocketDetectScrollPane1, null);
    jPickPocketDetectScrollPane1.getViewport().add(jPickPocketDetectTextArea1, null);
    jPickPocketDetectedTab.add(jPickPocketDetectClearButton, null);
    jChecksTabbedPane.add(jMergeCoinPanel,    "Merge Coins");
    jMergeCoinPanel.add(jMergeCoinDisplayPanel,  BorderLayout.CENTER);
    // end load tabs


    // load window prefs
    LoadPrefSettings();
    //
    DisplayCreatureList("");
    // flush out values and make sure it's clear
    // also set bCreatureChanged to false.
    jGMResetCSButton_actionPerformed(null);

    dlgStartUp.jStartUpBar.setValue(100);
    dlgStartUp.jStartUpBar.setIndeterminate(false);
    dlgStartUp.jStartUpBar.setString(null);
    dlgStartUp.setVisible(false);
//    dlgStartUp.hide();
 }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  //File | Exit action performed
  public void jMenuSavePrefs_actionPerformed(ActionEvent e) {
    if (fBattleSheetFrame.isVisible()) {
      int nX = (int) fBattleSheetFrame.getLocation().getX();
      int nY = (int) fBattleSheetFrame.getLocation().getY();
      int nHx = fBattleSheetFrame.getSize().width;
      int nHy = fBattleSheetFrame.getSize().height;
      Prefs.setProperty("pref-bsX", Integer.toString(nX));
      Prefs.setProperty("pref-bsY", Integer.toString(nY));
      Prefs.setProperty("pref-bsHx", Integer.toString(nHx));
      Prefs.setProperty("pref-bsHy", Integer.toString(nHy));
    }
    if (fPlayerGroupFrame.isVisible()) {
      int nX = (int) fPlayerGroupFrame.getLocation().getX();
      int nY = (int) fPlayerGroupFrame.getLocation().getY();
      int nHx = fPlayerGroupFrame.getSize().width;
      int nHy = fPlayerGroupFrame.getSize().height;
      Prefs.setProperty("pref-plX", Integer.toString(nX));
      Prefs.setProperty("pref-plY", Integer.toString(nY));
      Prefs.setProperty("pref-plHx", Integer.toString(nHx));
      Prefs.setProperty("pref-plHy", Integer.toString(nHy));
    }

    int nX = (int)this.getLocation().getX();
    int nY = (int)this.getLocation().getY();
    int nHy = this.getSize().width;
    int nHx = this.getSize().height;
    Prefs.setProperty("pref-mainX", Integer.toString(nX));
    Prefs.setProperty("pref-mainY", Integer.toString(nY));
    Prefs.setProperty("pref-mainHx", Integer.toString(nHx));
    Prefs.setProperty("pref-mainHy", Integer.toString(nHy));

    SavePrefsFile();
  }

  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    HackSackFrame_AboutBox dlg = new HackSackFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
//    dlg.show();
  }

  void D10000_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(10000));
    ROLLDICE_actionPerformed(null);
  }

  void D100_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(100));
    ROLLDICE_actionPerformed(null);
  }

  void D1000_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(1000));
    ROLLDICE_actionPerformed(null);
  }

  void D20_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(20));
    ROLLDICE_actionPerformed(null);
  }

  void D12_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(12));
    ROLLDICE_actionPerformed(null);

  }

  void D10_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(10));
    ROLLDICE_actionPerformed(null);
  }

  void D8_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(8));
    ROLLDICE_actionPerformed(null);
  }

  void D6_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(6));
    ROLLDICE_actionPerformed(null);
  }

  void D5_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(5));
    ROLLDICE_actionPerformed(null);
  }

  void D4_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(4));
    ROLLDICE_actionPerformed(null);
  }

  void D2_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(2));
    ROLLDICE_actionPerformed(null);
  }

  void D3_actionPerformed(ActionEvent e) {
    DiceSidesModel.setValue(new Integer(3));
    ROLLDICE_actionPerformed(null);
  }

  void ROLLDICE_actionPerformed(ActionEvent e) {
    int nMaxDiceSides = DiceSidesModel.getNumber().intValue();
    int nMaxDiceRolls = DiceCountModel.getNumber().intValue();
    int nDiceModifier = DiceModifierModel.getNumber().intValue();
    int nDiceTotalModifier = DiceTotalModifierModel.getNumber().intValue();
    int nRollTotal = 0;
    int nRoll = 0;

    boolean bPenetrate = Penetration.isSelected();
    boolean bDiceRollDetails = DiceRollDetailRadio.isSelected();

    if (!AppendRollsToggle.isSelected()) {
      DiceOutTextArea.setText("");
    }
    String sMod = nDiceModifier > -1 ? "+" : "";
    DiceOutTextArea.append("" + nMaxDiceRolls + "D" + nMaxDiceSides + sMod +
                           "(" + nDiceModifier + ") with " + nDiceTotalModifier +
                           " to total.\n");
//    DiceOutTextArea.append("D"+Integer.toString(nMaxDiceSides)+"+("+Integer.toString(nDiceModifier)+") for "+
//                           Integer.toString(nMaxDiceRolls)+ " rolls with "+Integer.toString(nDiceTotalModifier)+
//                           " to total.\n");

    for (int i = 0; i < nMaxDiceRolls; i++) {
      if (bPenetrate) {
        nRoll = MyRandomWithPenetration(nMaxDiceSides, nMaxDiceSides,
                                        bDiceRollDetails,
                                        AppendRollsToggle.isSelected(),
                                        DiceOutTextArea);
      }
      else {
        nRoll = (Math.abs(rand.nextInt() % (nMaxDiceSides)) + 1);
        if (bDiceRollDetails) {
          DiceOutTextArea.append("Base Roll " + Integer.toString(nRoll) +
                                 " Mod " +
                                 Integer.toString(nDiceModifier) + "\n");
        }
      }
      nRollTotal += ( (nRoll + nDiceModifier) < 1 ? 1 : nRoll + nDiceModifier); // if roll and modifier are smaller than 1 set it to 1.
      /*
            while (bPenetrate && nRoll == nMaxDiceSides) {
              nRoll = (Math.abs(rand.nextInt() % (nMaxDiceSides)) + 1);
              nRollTotal += (nRoll - 1);
              if (bDiceRollDetails)
       DiceOutTextArea.append("Penetrated +" + Integer.toString(nRoll - 1) +
                                       "\n");
            } // end while
       */
    } // end for
    nRollTotal += nDiceTotalModifier;

    if (bDiceRollDetails) {
      DiceOutTextArea.append("\n");
      DiceOutTextArea.append("Mod to Total :" +
                             Integer.toString(nDiceTotalModifier) + "\n");
    }
    DiceOutTextArea.append("Total        :" + Integer.toString(nRollTotal) +
                           "\n\n");
  }

  // MyRandom Function
  public int MyRandom(int nMaxSizeDice) {
//      Random rand = new Random(System.currentTimeMillis());
    int nResult = (Math.abs(rand.nextInt() % (nMaxSizeDice)) + 1);
    return nResult;
  } // end MyRandom

  // roll and use penetration (and log it if you want)
  public int MyRandomWithPenetration(int nMaxSizeDice, int nPenetrationPoint,
                                     boolean bDebug, boolean bAppend,
                                     JTextArea jLog) {
    int nRoll = MyRandom(nMaxSizeDice);
    int nRollTotal = nRoll;

    if (bDebug && jLog != null) {
      if (!bAppend) {
        jLog.setText("");
      }
      jLog.append("Dice rolled =" + nRoll + "\n");
    }

    if (nPenetrationPoint != 0 && (nMaxSizeDice - 1) > nPenetrationPoint) {
      nPenetrationPoint = (nMaxSizeDice - nPenetrationPoint);
    }
    else {
      nPenetrationPoint = nMaxSizeDice;

      // deal with penetration damage...
    }
    while (nRoll >= nPenetrationPoint && nRoll != 1) {
      nRoll = (Math.abs(rand.nextInt() % (nMaxSizeDice)) + 1);
      nRollTotal += (nRoll - 1);
      if (bDebug && jLog != null) {
//        if (!bAppend)
//          jLog.setText("");
        jLog.append("Penetrated +" + (nRoll - 1) + "\n");
      }
    } // end while

    return nRollTotal;
  }

  void ClearRollSpace_actionPerformed(ActionEvent e) {
    DiceOutTextArea.setText("");
  }

// make battle sheet/append to it
  void jGMMakeSheetButton_actionPerformed(ActionEvent e) {
    try {
      FrameBattleSheet.LoadCreature(this, mGMNumCreatures.getNumber().intValue());
      fBattleSheetFrame.LoadBattleSheetPane(this);
    }
    catch (OutOfMemoryError err) {
      ShowError(this,"Out of memory error.\n");
    }
  }

  void jCleanSheetButton_actionPerformed(ActionEvent e) {
    fBattleSheetFrame.CleanBattleSheet(this);
  }

  void jGMClearButton_actionPerformed(ActionEvent e) {
    jGMTextArea.setText("");
  }

  void LoadTable(String sFileName, String sTable) {
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
          if (!sLine.equals("")) {
            String[] sFields = sLine.split(":");
            if (sFields[0] != null && sFields[1] != null) {
              String sTemp = (sFields.length < 3 ? "" : sFields[2]);
              TableBlock oTableRecord = new TableBlock(Integer.parseInt(sFields[
                  0]), sFields[1], sTable, sTemp);
              lTables.add(oTableRecord);
            }
          }
        }
        else {
          DiceOutTextArea.append("Error with Table " + sTable + " at line " + i +
                                 ":" + sFileName + "\n");
        }
      }
      fBuff.close();
      fFile.close();
    }
    catch (IOException e) {
      File oFile = new File("");
      sFileName = oFile.getAbsolutePath() + File.separatorChar + sFileName;
      DiceOutTextArea.append("Error opening Table " + sTable + " file:" +
                             sFileName + "\n");
    }
  }

  void LoadTableTreasureButtons() {
    Font fFont = new Font("Dialog", Font.PLAIN, 9);
    GridBagConstraints cThisTreasureBag = new GridBagConstraints();

//      jTRTypePanel
//      jTRTextArea

    // make colum titles
    for (int i = 1; i <= 4; i++) {
      cThisTreasureBag.fill = GridBagConstraints.BOTH;
      cThisTreasureBag.weightx = 1.0;

      JLabel jTRTLabel1 = new JLabel("Type");
      JLabel jTRTLabel2 = new JLabel("Multiplier");

      jTRTLabel1.setToolTipText("Treasure type.");
      jTRTLabel2.setToolTipText(
          "Multiplier to treasure type, eg at 3, this treasure type generated 3 times");

      jTRTLabel1.setFont(fFont);
      jTRTLabel2.setFont(fFont);
      jTRTLabel1.setForeground(Color.white);
      jTRTLabel2.setForeground(Color.white);

      gridBagLayout2.setConstraints(jTRTLabel1, cThisTreasureBag);
      if (i >= 4) {
        cThisTreasureBag.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridBagLayout2.setConstraints(jTRTLabel2, cThisTreasureBag);
      }

      jTRTypePanel.add(jTRTLabel1);
      jTRTypePanel.add(jTRTLabel2);
    }

    // load up the buttons A-Z
    int i = 1;
    for (char cA = 'A'; cA <= 'Z'; cA++) {
      TableTreasureOptions oRecord = new TableTreasureOptions("" + cA);
      cThisTreasureBag.fill = GridBagConstraints.BOTH;
      cThisTreasureBag.weightx = 1.0;
      cThisTreasureBag.gridwidth = 1;
      oRecord.jType.setFont(fFont);

      gridBagLayout2.setConstraints(oRecord.jType, cThisTreasureBag);

      jTRTypePanel.add(oRecord.jType);

      oRecord.jMultiplier.setFont(fFont);
      if (i >= 4) {
//treasureLog(false,false,"Hit I:"+i);
        i = 0;
        cThisTreasureBag.gridwidth = GridBagConstraints.REMAINDER; //end row
//             gridBagLayout2.setConstraints(oRecord.jMultiplier, cThisTreasureBag);
      }
      gridBagLayout2.setConstraints(oRecord.jMultiplier, cThisTreasureBag);
      jTRTypePanel.add(oRecord.jMultiplier);
      lTableTreasureOptions.add(oRecord);
      i++;
    }

    cThisTreasureBag.fill = GridBagConstraints.BOTH;
    cThisTreasureBag.weightx = 1.0;
    cThisTreasureBag.gridwidth = 2;
    gridBagLayout2.setConstraints(jTRGenButton, cThisTreasureBag);
    cThisTreasureBag.gridwidth = GridBagConstraints.REMAINDER; //end row
    gridBagLayout2.setConstraints(jTRResetButton, cThisTreasureBag);

    jTRTypePanel.add(jTRGenButton, null);
    jTRTypePanel.add(jTRResetButton, null);

  }

  void LoadTableMoraleButtons() {

    Font fFont = new Font("Dialog", Font.PLAIN, 9);
    /*
           jGMMoraleCheckButton.setFont(fFont);
           jGMMoraleLabel.setFont(fFont);
     */

    for (int i = 0; i < lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) lTableMorale.get(i);

      if (oThis.jSelect != null) {
        oThis.jSelect.setFont(fFont);
        oThis.jSelect.setBackground(Color.lightGray);
        jGMMoraleCheckPanel.add(oThis.jSelect);
      }
    }

    GridBagConstraints bBag = new GridBagConstraints();
    for (int i = 0; i < lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) lTableMorale.get(i);

      if (oThis.jNumber != null) {
        bBag.gridwidth = 1;
        bBag.weightx = 0.1;
        bBag.ipadx = 0;
        bBag.insets = new Insets(0, 0, 0, 0);
        bBag.anchor = GridBagConstraints.WEST;

        gMoraleSpinnerLayout.setConstraints(oThis.jNumber, bBag);
        oThis.jNumber.setFont(fFont);
        jGMMoraleSpinnerPanel.add(oThis.jNumber);

        bBag.gridwidth = GridBagConstraints.REMAINDER;
        bBag.weightx = 1.0;
        bBag.ipadx = 280;
        bBag.insets = new Insets(0, 10, 0, 0);
        gMoraleSpinnerLayout.setConstraints(oThis.jNumLabel, bBag);
        oThis.jNumLabel.setFont(fFont);
        jGMMoraleSpinnerPanel.add(oThis.jNumLabel);
      }
    }

//       GMDiceBagPanel.add(jGMMoraleCheckButton, null);
//       GMDiceBagPanel.add(jGMMoraleRatingSpinner, null);
//       GMDiceBagPanel.add(jGMMoraleLabel, null);
  }

  void jGMFumbleButton_actionPerformed(ActionEvent e) {
    int nRoll = MyRandom(1000); // d1000
    gmCritLog(true, false, Fumble(nRoll) + "\n");
  }

  // manage a fumble and return the fumble text.
  String Fumble(int nRoll) {
    String sReturn = null;
    // start at top of array and go down till you find a .nRoll that
    // our roll is > or == to.
    // for some reason I put the smallest occurance on this one but the
    // largest on the other tables so this one works down while the others
    // go up...
    for (int i = lTables.size() - 1; i >= 0; i--) {
      TableBlock oThisFumble = (TableBlock) lTables.get(i);
      if ( (nRoll >= oThisFumble.nRoll &&
            (oThisFumble.sTable.equals("8KK") && !jGMUnarmedCheckBox.isSelected()))
          ||
          (nRoll >= oThisFumble.nRoll &&
           (oThisFumble.sTable.equals("17") && jGMUnarmedCheckBox.isSelected()))) {
        sReturn = DiceParse(oThisFumble.sText);
        break;
      }
    }

    return sReturn;
  }

  void jGMSpellMishapButton_actionPerformed(ActionEvent e) {
    int nRoll = MyRandom(10000); // d10000
    int nEffect = MyRandom(10);
    String sEffect = "";
//    nRoll = 9215;
    // start at top of array and go down till you find a .nRoll that
    // our roll is > or == to.
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisFumble = (TableBlock) lTables.get(i);
      if (nRoll <= oThisFumble.nRoll && oThisFumble.sTable.equals("7E")) {
        String sThisText = oThisFumble.sText;

        // replace all 7H texts with 7H table info
        Matcher m7Hpattern = Pattern.compile("7H").matcher(sThisText);
        if (m7Hpattern.find()) {
          sThisText = m7Hpattern.replaceAll(TableBlock.GetTableRecord(this,
              "7H", 100, false));

          // replace all 7I texts with 7I table info
        }
        Matcher m7Ipattern = Pattern.compile("7I").matcher(sThisText);
        if (m7Ipattern.find()) {
          sThisText = m7Ipattern.replaceAll(TableBlock.GetTableRecord(this,
              "7I", 100, false));

          // replace all 7F texts with 7f table info
        }
        Matcher m7Fpattern = Pattern.compile("7F").matcher(sThisText);
        if (m7Fpattern.find()) {
          sThisText = m7Fpattern.replaceAll(TableBlock.GetTableRecord(this,
              "7F", 100, false));

          // replace all 7G texts with 7G table info
        }
        Matcher m7Gpattern = Pattern.compile("7G").matcher(sThisText);
        if (m7Gpattern.find()) {
          sThisText = m7Gpattern.replaceAll(TableBlock.Get7GTable(this));
        }
        if (nEffect > 7) {
          sEffect = "random party member";
        }
        else {
          sEffect = "self";
        }
        String sLog = "Spell Mishap Result to " + sEffect + ":(" + nRoll + ") " +
            DiceParse(sThisText) + "\n";
        gmCritLog(true, false, sLog);
        break;
      }
    }

  }

  // get color shade for Get7GTable()
  public String GetColorShade() {
    int nShade = MyRandom(100);
    String sThisShade = "BOGUS-SHADE"; // what shade is the color?

    if (nShade < 25) {
      sThisShade = " (very light)";
    }
    else if (nShade < 50) {
      sThisShade = " (medium)";
    }
    else if (nShade < 75) {
      sThisShade = " (dark)";
    }
    else if (nShade <= 100) {
      sThisShade = " (very dark)";
    }
    else {
      sThisShade = " (freaky)";

    }
    return sThisShade;
  }

  // unescape characters we escaped in XML
  String unEscapeChars(String sEscapeThis) {
    if (sEscapeThis != null) {
      sEscapeThis = sEscapeThis.replaceAll("\\\\n", "\n");
      sEscapeThis = sEscapeThis.replaceAll("\\\\r", "\r");
    }

    return sEscapeThis;
  }

  // fix escape characters for XML
  String EscapeChars(String sEscapeThis) {
    if (sEscapeThis != null) {
      sEscapeThis = sEscapeThis.replaceAll("&", "\\&amp;");
      sEscapeThis = sEscapeThis.replaceAll("'", "\\&apos;");
      sEscapeThis = sEscapeThis.replaceAll("<", "\\&lt;");
      sEscapeThis = sEscapeThis.replaceAll(">", "\\&gt;");
      sEscapeThis = sEscapeThis.replaceAll("\"", "\\&quot;");
      sEscapeThis = sEscapeThis.replaceAll("\n", "\\\\" + "n");
      sEscapeThis = sEscapeThis.replaceAll("\r", "\\\\" + "r");
    }

    return sEscapeThis;
  }

  // {xdx}
  // {xdx+x}
  // {xdx-x}
  // {xdx*x}
  public String DiceParse(String sParseThis) {
    int nSides = 4;
    int nDice = 1;
    int nTotal = 0;
    int nTotalMod = 0;

    String sDiceMatcher1 = "(?si)\\{(\\d+)d(\\d+)\\}";
    String sDiceMatcher2 = "(?si)\\{(\\d+)d(\\d+)([+-\\{*}])(\\d+)\\}";

    Matcher mDiceMatcher1 = Pattern.compile(sDiceMatcher1).matcher(sParseThis);
    while (mDiceMatcher1.find()) {
      nDice = Integer.parseInt(mDiceMatcher1.group(1));
      nSides = Integer.parseInt(mDiceMatcher1.group(2));
      nTotal = 0;
//         jTextArea1.append(nDice + "d" + nSides + "\n");
      for (int i = 0; i < nDice; i++) {
        int nThisRoll = MyRandom(nSides);
        nTotal += nThisRoll;
//          jTextArea1.append("Matcher1 roll (" + i + ") :" + nThisRoll +
//                            " for total of " + nTotal + "\n");
      }
      sParseThis = mDiceMatcher1.replaceFirst(Integer.toString(nTotal));
      mDiceMatcher1 = Pattern.compile(sDiceMatcher1).matcher(sParseThis);
    }

    Matcher mDiceMatcher2 = Pattern.compile(sDiceMatcher2).matcher(sParseThis);
    while (mDiceMatcher2.find()) {
      nDice = Integer.parseInt(mDiceMatcher2.group(1));
      nSides = Integer.parseInt(mDiceMatcher2.group(2));
      String sSign = mDiceMatcher2.group(3);
      nTotal = 0;
      nTotalMod = Integer.parseInt(mDiceMatcher2.group(4));
//jGMTextArea.append("Matcher2:"+nDice+"d"+nSides+":"+sSign+":"+nTotalMod+"\n");
      for (int i = 0; i < nDice; i++) {
        int nThisRoll = MyRandom(nSides);
        nTotal += nThisRoll;
//jGMTextArea.append("Matcher2 roll ("+i+") :"+nThisRoll+" for total of "+nTotal+"\n");
      }
      if (sSign.equals("*")) {
        nTotal *= nTotalMod;
      }
      else if (sSign.equals("-")) {
        nTotal -= nTotalMod;
      }
      else {
        nTotal += nTotalMod;

      }
      sParseThis = mDiceMatcher2.replaceFirst(Integer.toString(nTotal));
      mDiceMatcher2 = Pattern.compile(sDiceMatcher2).matcher(sParseThis);
    }

    return sParseThis;
  }

  void gmLog(boolean bNewBlock, boolean bDetailed, String sStr) {
    if (!jGMAppendCheckBox.isSelected() && bNewBlock) {
      jGMTextArea.setText("");
    }
    if (bDetailed && jGMDetailsCheckBox.isSelected()) {
      jGMTextArea.append(sStr);
    }
    if (!bDetailed) {
      jGMTextArea.append(sStr);
    }
  }

  void gmCritLog(boolean bNewBlock, boolean bDetailed, String sStr) {
    if (!jCritAppendCheckBox.isSelected() && bNewBlock) {
      jCritTextArea.setText("");
    }
    if (bDetailed && jCritDetailsCheckBox.isSelected()) {
      jCritTextArea.append(sStr);
    }
    if (!bDetailed) {
      jCritTextArea.append(sStr);
    }
  }

  /**
   * this is the button event for a manual critical attack display
   *
   * @param e ActionEvent
   */
  void jGMCritButton_actionPerformed(ActionEvent e) {
    int nToHit = mGMCritToHit.getNumber().intValue();
    // 0=HAck, 1=Crush, 2=Pierce
    int nWeaponTypeIndex = (jGMCritWeaponComboBox.getSelectedIndex()+1);
    int nLocationIndex = jGMCritLocComboBox.getSelectedIndex();
    int nAC = mGMCritAC.getNumber().intValue();
    // 0=Tiny,1=Small,2=Medium,3=Large,4=Huge,5=Giant
    int nTSize = jGMCritTSizeComboBox.getSelectedIndex();
    int nASize = jGMCritASizeComboBox.getSelectedIndex();
    // 0=Fighter,1=Cleric,2=Thief,3=Mage,4=Monster
    int nClassIndex = jGMCritClassComboBox.getSelectedIndex();
    int nLevel = mGMCritLevel.getNumber().intValue();
    int nToHitRating = AttackRank.GetToHitRating(nClassIndex, nLevel, 0);

    // they wanted to specify the severiy
    int nSpecSev = Integer.parseInt(jGMSpecSevSpinner.getValue().toString());
    jGMSpecSevSpinner.setValue(new Integer(0));

//    int nThaco = GetThacoRating(nClassIndex, nLevel, 0);
    int nRoll = mGMSpecCritRoll.getNumber().intValue();
    if (nRoll <= 0) {
      nRoll = MyRandom(10000);
    }
    else {
      mGMSpecCritRoll.setValue(new Integer(0));
    }

    CritResults oCrit = CritResults.GetCritResults(this, nClassIndex, nAC,
        nToHitRating, nToHit,
        nTSize, nASize, nWeaponTypeIndex, nRoll, nLocationIndex, nSpecSev,
        true,null, 0, null);

    /*  gmCritLog(true, false,
     "Critical hit; " + oCrit.sSide + " " + oCrit.sLocation + " (" + nRoll + ")[" +
                oCrit.nSeverity + "]:" +
                oCrit.sResult + "\n");
     */
    if (jCritFancyCheckBox.isSelected()) {
      DialogFancyCrit dlg = new DialogFancyCrit(null, null, oCrit);
      Dimension dlgSize = dlg.panel1.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);
//      dlg.show();
    }

    DisplayCriticalHitResults(oCrit, null, null);
  }

  void DisplayCriticalHitResults(CritResults oCrit, CreatureCombat oAtk,
                                 TablePlayer oPlayer) {
    boolean bAuto = (oAtk != null && oPlayer != null);
    String sAutoLog = null;

    gmCritLog(true, false,
              "HIT THE " + oCrit.sLocation + "(" + oCrit.sSide + ")\n");
    gmCritLog(false, true,
              "BSL:" + oCrit.nBSL + " severity added from roll " +
              oCrit.nSeverityRoll + " and dice rolled for crit " +
              oCrit.nDiceRoll + "\n");
    if (bAuto) {
      sAutoLog = "Critically hit by " + oAtk.oCreature.sCreatureName +
          " in the " +
          oCrit.sLocation + "(" + oCrit.sSide + ") at a severity level of " +
          oCrit.nSeverity +
          " with BSL " + oCrit.nBSL + " and rolled severity of " +
          oCrit.nSeverityRoll + "\n";

    }
    for (int i = 0; i < oCrit.lCritEffects.size(); i++) {
      CritEffect oE = (CritEffect) oCrit.lCritEffects.get(i);
      gmCritLog(false, false, oE.sName + ":" + oE.sDesc + "\n");
      if (bAuto) {
        sAutoLog += oE.sName + ":" + oE.sDesc + "\n";
      }
    }

    if (oCrit.bHasPermScar ||
        !oCrit.bCanCrit ||
        !oCrit.bCanFollowThrough ||
        !oCrit.bCanPenetrate) {
      gmCritLog(false, false, "Addtional critical effects to target:\n");
      if (oCrit.bHasPermScar) {
        gmCritLog(false, false, "Has a permanent scar.\n");
        if (bAuto) {
          sAutoLog += "Has a permanent scar.\n";
        }
      }
      if (!oCrit.bCanCrit ||
          !oCrit.bCanFollowThrough ||
          !oCrit.bCanPenetrate) {
        gmCritLog(false, false, "Can no longer perform ");
        if (bAuto) {
          sAutoLog += "Can no longer perform ";
        }
        if (!oCrit.bCanFollowThrough) {
          gmCritLog(false, false,
                    "follow through damage");
          if (bAuto) {
            sAutoLog += "follow through damage";
          }
        }
        if (!oCrit.bCanCrit) {
          gmCritLog(false, false,
                    (oCrit.bCanPenetrate ? " or " : ",") + "criticals ");
          if (bAuto) {
            sAutoLog += (oCrit.bCanPenetrate ? " or " : ",") + "criticals ";
          }
        }
        if (!oCrit.bCanPenetrate) {
          gmCritLog(false, false, "or penetration damage");
          if (bAuto) {
            sAutoLog += "or penetration damage";
          }
        }

        gmCritLog(false, false,
                  "\n");
        if (bAuto) {
          sAutoLog += "\n";
        }
      }

    }
    // log to player
    if (bAuto) {
      oPlayer.playerLog(sAutoLog);
      gplGroupLog.groupLog(oPlayer.sCharacter + " " + sAutoLog);
    }
  }

  // load up the crit locations into an array
  void LoadMagicTableList(String sTable) {
    int i = 0;
    for (int j = 1; j < lTables.size(); j++) {
      TableBlock oRecord = (TableBlock) lTables.get(j);
      if (oRecord.sTable.equalsIgnoreCase(sTable)) {
        TableMagicList oNew = new TableMagicList();
        oNew.sName = oRecord.sText;
        oNew.sMagicTable = oRecord.sExtra;
        oNew.nIndex = i;
        i++;
        lTableMagicList.add(oNew);
      }
    }
  }

  // return a array listing to use in pull down of magic item tables
  String[] GetMagicTableNames() {
    String[] sReturn = new String[lTableMagicList.size()];

    for (int i = 0; i < lTableMagicList.size(); i++) {
      TableMagicList oRecord = (TableMagicList) lTableMagicList.get(i);
      sReturn[i] = oRecord.sName;
    }

    return sReturn;
  }

  void jTRGenButton_actionPerformed(ActionEvent e) {
    String[] sTreasureName = {
        "cp", "sp", "ep", "gp", "hs", "pp", "gems", "art", "magic"};
    int _CP = 0;
    int _SP = 1;
    int _EP = 2;
    int _GP = 3;
    int _HS = 4;
    int _PP = 5;
    int _GEMS = 6;
    int _ART = 7;
    int _MAGIC = 8;
    int[] nTreasureValue = new int[9];
    String[] sTreasureSpecial = new String[9];

    for (int i = 0; i < lTableTreasureOptions.size(); i++) {
      TableTreasureOptions oRecord = (TableTreasureOptions)
          lTableTreasureOptions.get(i);
      if (oRecord.jType.isSelected()) {
        int nThisMultiplier = Integer.parseInt(oRecord.jMultiplier.getModel().
                                               getValue().toString());
//        jTRTextArea.append("Record (" + i + ") Type (" + oRecord.jType.getText() +
//                           ") with multiplier " +
//                           oRecord.jMultiplier.getModel().getValue().toString() +
//                           " " +
//                           (oRecord.jType.isSelected() ? "is selected" :
//                            "is not selected") + "\n");
        for (int j = 0; j < lTableTreasures.size(); j++) {
          TableTreasure oTreasure = (TableTreasure) lTableTreasures.get(j);
          if (oRecord.jType.getText().equalsIgnoreCase(oTreasure.cType + "")) {
            // run for each multiplier...
            for (int nMulti = 0; nMulti < nThisMultiplier; nMulti++) {
              // flip through coin types, gem, art and magic
              for (int nTType = 0; nTType < 9; nTType++) {
                if (MyRandom(100) <= oTreasure.nChance[nTType]) {
                  switch (nTType) {
                    case 0: // coin
                    case 1: // coin
                    case 2: // coin
                    case 3: // coin
                    case 4: // coin
                    case 5: // coin
                    case 6: // gem
                    case 7: // art
                      nTreasureValue[nTType] +=
                          Integer.parseInt(DiceParse(oTreasure.sReward[nTType]));
                      break;
                    case 8: // magic
                      if (sTreasureSpecial[nTType] == null) {
                        sTreasureSpecial[nTType] = "";
                      }
                      sTreasureSpecial[nTType] += oTreasure.sReward[nTType];
                      break;

                    default:
                      break;
                  }
                }
              }
            }
          }
        }
      }
    }

    // got all of the types of items done lets do specials
    String[] sTreasureGems = new String[nTreasureValue[_GEMS]];
    if (nTreasureValue[_GEMS] > 0) {
      int nGems = nTreasureValue[_GEMS];
      for (int k = 0; k < nGems; k++) {
        sTreasureGems[k] = GetGemTypeDetails();
        if (nGems - k >= 10) {
          sTreasureGems[k] = "10x " + sTreasureGems[k];
          k += 9;
        }
        else
        if (nGems - k >= 5) {
          sTreasureGems[k] = "5x " + sTreasureGems[k];
          k += 4;
        }
      }

    }

    String[] sMagicItemDetails = null;
    String sParseText = "";
    treasureLog(true, false, "Treasure total:\n");
    for (int i = 0; i < sTreasureName.length; i++) {
      if (nTreasureValue[i] > 0) {
        treasureLog(false, false,
                    "Valuables:" + nTreasureValue[i] + " " + sTreasureName[i] +
                    "\n");
      }
      if (sTreasureSpecial[i] != null) {
        sParseText += sTreasureSpecial[i];
      }
    } // magic items
    if (!sParseText.equals("")) {
      sMagicItemDetails = ParseMagicItemTables.GetMagicItemDetails(this,
          sParseText);
      treasureLog(false, false, "Magic:\n" + sMagicItemDetails[0] + "\n");

    }
    if (sTreasureGems.length > 0) {
      treasureLog(false, false, "Gems:\n");
    }
    for (int i = 0; i < sTreasureGems.length; i++) {
      if (sTreasureGems[i] != null) {
        treasureLog(false, false, sTreasureGems[i] + "\n");
      }
    }

  }

  // get a magic item from a table
  public TableMagicItem GetMagiItemFromTable(int nRoll, String sTable) {
    TableMagicItem oReturn = null;

//treasureLog(false,false,"GMIFT-CALLED:"+sTable+" roll:"+nRoll+"\n");

    for (int i = 0; i < lTableMagicItems.size(); i++) {
      TableMagicItem oThisRecord = (TableMagicItem) lTableMagicItems.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sTable)) {
        oReturn = oThisRecord;
//treasureLog(false,false,"Found record:"+oThisRecord.sName+"\n");
        break;
      }
    }

    return oReturn;
  }

  // return text from simple 1-100 sTable rolling d100
  public TableBlock GetMagicTableRecord(String sTable, int nTableSize,
                                        boolean bStaticRoll) {
    TableBlock oReturnThis = null;
    // if Static just use the TableSize sent and not random
    int nRoll = bStaticRoll ? nTableSize : MyRandom(nTableSize);
    String sThisText = ">MGTR-ERROR-" + sTable + "(" + nTableSize + ")" + "[" +
        nRoll + "]<";

    // start at top of array and go down till you find a .nRoll that
    // our roll is > or == to.
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sTable)) {
        sThisText = oThisRecord.sExtra;
        oReturnThis = oThisRecord;
        break;
      }
    }

    return (oReturnThis);
  }

  // get full specifics on gems found in treasure type
  String GetGemTypeDetails() {
    String sReturn = "";
    String sType = "", sGemTable = "";

    int nGemValue = 0;
    int nAdjustment = 0;
    int nRoll = MyRandom(100);

    // get gem type
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals("13B")) {
        nGemValue = Integer.parseInt(oThisRecord.sText);
//    sType = oThisRecord.sExtra;
        sGemTable = oThisRecord.sExtra;
        break;
      }
    }

//jGMTextArea.append("GemTable:("+sGemTable+")\n");
//jGMTextArea.append("GemValue:"+nGemValue+"\n");
    nRoll = MyRandom(100);
    // get Gem quality
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals("13IQ")) {
        nAdjustment += Integer.parseInt(oThisRecord.sText);
        sType += "," + oThisRecord.sExtra + " quality";
//jGMTextArea.append("Adjustment1:"+nAdjustment+"\n");
        break;
      }
    }

    nRoll = MyRandom(100);
    // get gem size
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals("13IS")) {
        nAdjustment += Integer.parseInt(oThisRecord.sText);
        sType += "," + oThisRecord.sExtra + " size";
//jGMTextArea.append("Adjustment2:"+nAdjustment+"\n");
        break;
      }
    }

    int nLast = 0; // need to get dice size of the gem table
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (oThisRecord.sTable.equals(sGemTable) && nLast < oThisRecord.nRoll) {
        nLast = oThisRecord.nRoll;
      }
    }

//jGMTextArea.append("DiceSize:"+nLast+"\n");
//jGMTextArea.append("TABLE:"+sGemTable+"\n");

    nRoll = MyRandom(nLast);
    nRoll += nAdjustment;
    if (nRoll > nLast) {
      nRoll = nLast; // sanity check

      // finally get gem name/colour/etc
    }
    for (int i = 0; i < lTables.size(); i++) {
      TableBlock oThisRecord = (TableBlock) lTables.get(i);
      if (nRoll <= oThisRecord.nRoll && oThisRecord.sTable.equals(sGemTable)) {
        sType = oThisRecord.sText + " " + sType;
        break;
      }
    }

    sReturn = sType + "(" + Integer.toString(nGemValue) + "gp)";
    return sReturn;
  }

  void treasureLog(boolean bNewBlock, boolean bDetailed, String sLog) {
    boolean bAppend = jTRAppendCheckBox.isSelected();
    boolean bDetails = jTRDetailsCheckBox.isSelected();

    if (bNewBlock && !bAppend) {
      jTRTextArea.setText("");

    }
    if (bDetailed && bDetails) {
      jTRTextArea.append(sLog);

    }
    if (!bDetailed) {
      jTRTextArea.append(sLog);
    }
  }

  void jTRResetButton_actionPerformed(ActionEvent e) {
    for (int i = 0; i < lTableTreasureOptions.size(); i++) {
      TableTreasureOptions oRecord = (TableTreasureOptions)
          lTableTreasureOptions.get(i);
      oRecord.jMultiplier.setValue(new Integer(1));
      oRecord.jType.setSelected(false);
    }

  }

  void jButton1_actionPerformed(ActionEvent e) {
    jTRTextArea.setText("");
  }

  void jGMMoraleCheckButton_actionPerformed(ActionEvent e) {
    checkSpecial.checkMorale(this);
  }

  // reset morale buttons to default
  void jGMMoraleResetButton_actionPerformed(ActionEvent e) {

    for (int i = 0; i < lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) lTableMorale.get(i);
      if (oThis.jSelect != null) {
        oThis.jSelect.setSelected(false);
      }
    }

    for (int i = 0; i < lTableMorale.size(); i++) {
      TableMorale oThis = (TableMorale) lTableMorale.get(i);
      if (oThis.jNumber != null) {
        oThis.jNumber.getModel().setValue(new Integer(0));
      }
    }

  }

  void jTRMakeItemButton_actionPerformed(ActionEvent e) {
    int nItemCount = mTRMakeItemSpinner.getNumber().intValue();
    int nItemIndex = jTRMakeItemComboBox.getSelectedIndex();
    TableMagicList oRecord = (TableMagicList) lTableMagicList.get(nItemIndex);

    if (oRecord != null) {
      treasureLog(true, false, oRecord.sName + ":\n");
      for (int i = 0; i < nItemCount; i++) {
        String sTemp = ParseMagicItemTables.ParseMagicItemTable(this,
            "{" + oRecord.sMagicTable + "}");
        treasureLog(false, false, sTemp);
      }
    }
  }

  void jGMSaveButton_actionPerformed(ActionEvent e) {
    String sCreatureName = jGMNameTextField.getText();
    TableSavedCreatures oNew = new TableSavedCreatures(this, sCreatureName);

    oNew.nBASENumAtks = Integer.parseInt(jGMNumAtksSpinner.getModel().getValue().
                                         toString());
    oNew.nBASEClassIndex = jGMClassComboBox.getSelectedIndex();
    oNew.nBASEHD = Integer.parseInt(jGMHDiceSpinner.getModel().getValue().
                                    toString());
    oNew.nBASEHDMod = Integer.parseInt(jGMHDModSpinner.getModel().getValue().
                                       toString());
    oNew.sBASESpecHP = jGMSpecHP.getText();
    oNew.nBASEAC = Integer.parseInt(jGMACSpinner.getModel().getValue().toString());
    oNew.nBASEEXP = mGMEXP.getNumber().intValue();
    oNew.nBASEAlignmentIndex = jGMAlignmentComboBox.getSelectedIndex();
    oNew.nBASEHackFactor = mGMHackFactor.getNumber().intValue();
    oNew.nBASEMorale = mGMMoraleSpinner.getNumber().intValue();
    oNew.nBASEMove = mGMMove.getNumber().intValue();
    oNew.sDescription = jGMDescTextArea.getText();
    oNew.sSpecialAttack = jGMSpecialAttack.getText(); //jGMSpecATKCheckBox.isSelected() ? "Y" : "N";
    oNew.sSpecialDefense = JGMSpecialDefense.getText(); //jGMSpecDefCheckBox.isSelected() ? "Y" : "N";
    oNew.nBASESizeIndex = jGMSizeComboBox.getSelectedIndex();

    while (oNew.lMyAttacks.size() != 0) { // remove all attacks cause we're re
      oNew.lMyAttacks.remove(0); // adding them from changes

    }
    for (int i = 0; i < lAttacks.size(); i++) {
      CreatureCombat oAtk = (CreatureCombat) lAttacks.get(i);
      TableSavedCreatureCombat oNewATK = new TableSavedCreatureCombat();

      oNewATK.nWeaponType = oAtk.jWeaponType.getSelectedIndex();

      oNewATK.sDamageDice = oAtk.jDamageDice.getText();
      /*oNewATK.nDiceSides = Integer.parseInt(oAtk.jDiceSides.getModel().getValue().
                                            toString());
       oNewATK.nNumDice = Integer.parseInt(oAtk.jNumDice.getModel().getValue().
                                          toString());

       oNewATK.nPerDiceMod = Integer.parseInt(oAtk.jPerDiceMod.getModel().
                                             getValue().toString());
       */
      oNewATK.nToHitMod = Integer.parseInt(oAtk.jToHitMod.getModel().getValue().
                                           toString());
      oNewATK.nTotalMod = Integer.parseInt(oAtk.jTotalMod.getModel().getValue().
                                           toString());

      oNewATK.nModCrit = Integer.parseInt(oAtk.jModCrit.getModel().getValue().
                                          toString());
      oNewATK.nModFumble = Integer.parseInt(oAtk.jModFumble.getModel().getValue().
                                            toString());
      oNewATK.nModPenetration = Integer.parseInt(oAtk.jModPenetration.getModel().
                                                 getValue().toString());

      oNew.lMyAttacks.add(oNewATK);
    }

// set the creature climate
    for (int i = 0; i < aClimate.size(); i++) {
      Climate oC = (Climate) aClimate.get(i);
      Climate oN = new Climate(oC.sName);
      oN.jActive.setSelected(oC.jActive.isSelected());
      oNew.aClimate.add(oN);
    }
// set the new creatures terrain
    for (int i = 0; i < aTerrain.size(); i++) {
      Terrain oC = (Terrain) aTerrain.get(i);
      Terrain oN = new Terrain(oC.sName);
      oN.jActive.setSelected(oC.jActive.isSelected());
      oNew.aTerrain.add(oN);
    }

    oNew.nFrequency = jFrequencyComboBox.getSelectedIndex();
    oNew.nAppearingIn = jAppearingInComboBox.getSelectedIndex();
    oNew.nActivityCycle = jActivityCycleComboBox.getSelectedIndex();

    oNew.nBASEFatigueFactor = Integer.parseInt(jGMFatigueFactorSpinner.getModel().
                                               getValue().toString());

    oNew.nBASEHonorIndex = jGMHonorComboBox.getSelectedIndex();

    oNew.nCreatureID = nCurrentCreatureID; // creature loaded ID
    TableSavedCreatures oOld = null;
    if (oNew.nCreatureID != -1) {
      oOld = oNew.exists(lSavedCreatures); // see if we have this creature
    }
    boolean bSaveType = false;
    boolean bReplace = jGMForceSaveCheckBox.isSelected();
    if (oOld != null) {
      // ask if they wanna overwrite, if not then new ID
      if ( (bReplace) ||
          (AskYN(this, "Replace creature " + oOld.sCreatureName + "\nwith this "+oNew.sCreatureName+"?"))) {
        bReplace = true;
        oNew.nCreatureID = oOld.nCreatureID;
//        dmCreatureList.removeElement(oOld);
        lSavedCreatures.remove(oOld);
      }
      else {
        // save new creature
        bSaveType = true;
        oNew.nCreatureID = (nMaxCreatureID++); // this is a ID for quick access viewing
      }

    }

    lSavedCreatures.add(oNew);
    /*    if (fLoadCreatureFrame.isVisible()) {
          FrameLoadCreature.LoadSavedCreaturesList(this, lSavedCreatures);
        }
     */
//    SaveCreature.toFile(this, sCreatureSaveFileName);
    xmlControl.saveDoc(this,this,TableSavedCreatures.xmlBuildDocFromList(
          this.lSavedCreatures,this.nMaxCreatureID),this.sCreatureSaveFileName);

    DisplayCreatureList(jFindCreatureTextField.getText().toUpperCase());
    jCreatureList.setSelectedValue(oNew, true);

    gmLog(true, true, "Saved creature " + sCreatureName + "\n");
    if (bSaveType) { // existed by NAME but we wanted to save another with same name
      ShowDone(this, "Saved new creature, " + oNew.sCreatureName + ".");
    }
    // lets try it without this ...
    else if (bReplace) {
//      ShowDone(this, "Replaced " + oNew.sCreatureName + ".");
    }
    else { // didn't exist at all (name)
      ShowDone(this, "Saved NEW creature " + oNew.sCreatureName + ".");
    }

    bCreatureChanged = false;
  }

  // ASK A YES NO QUESTION
  boolean AskYN(Component oFrame, String sQuestion) {
    boolean bReturn = true;
    int nYN = JOptionPane.showConfirmDialog(oFrame, sQuestion, "",
                                            JOptionPane.YES_NO_OPTION);
    if (nYN > 0) {
      bReturn = false;

    }
    oFrame.repaint();
    return bReturn;
  }

  // Show Done/Details with dialog
  void ShowDone(Component oFrame, String sShow) {
    JOptionPane.showMessageDialog(oFrame, sShow);
  }

  // Show Errors with dialog
  void ShowError(Component oFrame, String sShow) {
    JOptionPane.showMessageDialog(oFrame, sShow, "Error",
                                  JOptionPane.ERROR_MESSAGE);
  }

  // show done/details with Frame
  void ShowDoneFrame(Component oFrame, String sTitle, String sShow) {
    FrameDetails oThisFrame = new FrameDetails(sTitle, sShow);
    Dimension dlgSize = oThisFrame.getPreferredSize();
    Dimension frmSize = oFrame.getSize();
    Point loc = oFrame.getLocation();
    oThisFrame.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                           (frmSize.height - dlgSize.height) / 2 + loc.y);
//    oThisFrame.setModal(true);
    oThisFrame.pack();
    oThisFrame.setVisible(true);
//    oThisFrame.show();

  }

  /**
   * This creates a progress bar and returns the dlg, use that to close the
   * progress bar with ShowStatusProgressDone()
   *
   * @param oC Component that we'll set the location on for this bar
   * @param sTitle String Title of the status bar
   * @param sProcess String What process that we're monitoring
   * @return FrameStartUp
   */
  FrameStartUp ShowStatusProgress(Component oC, String sTitle, String sProcess) {
    FrameStartUp dlgStartUp = new FrameStartUp();
    dlgStartUp.titledBorder3.setTitle(sTitle);
    dlgStartUp.jPanel1.setPreferredSize(new Dimension(360,50));

//    Dimension dlgSize = dlgStartUp.jPanel1.getPreferredSize();
//    Dimension frmSize = oC.getSize();
    dlgStartUp.setLocation(oC.getLocation());
//    dlgStartUp.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
//                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlgStartUp.pack();
    dlgStartUp.setVisible(true);
//    dlgStartUp.show();
    dlgStartUp.jStartUpBar.setStringPainted(true);
    dlgStartUp.jStartUpBar.setIndeterminate(true);
    dlgStartUp.jStartUpBar.setValue(0);
    dlgStartUp.jStartUpBar.setString(sProcess);

    return dlgStartUp;
  }

  /**
   * This closes up a opened ShowStatusProgress() bar, just pass it the dlg
   * var that created it for it to close up.
   *
   * @param oDlg FrameStartUp
   */
  void ShowStatusProgressDone(FrameStartUp oDlg) {
    oDlg.jStartUpBar.setIndeterminate(false);
    oDlg.jStartUpBar.setValue(100);
    oDlg.jStartUpBar.setString(null);
    oDlg.setVisible(false);
//    oDlg.hide();
  }

// load a saved creature
  void jGMLoadButton_actionPerformed(ActionEvent e) {
//      FrameLoadCreature.LoadSavedCreaturesList(this, lSavedCreatures);
//      fLoadCreatureFrame.show();
  }

// load a battle sheet
  void jGMLoadBSButton_actionPerformed(ActionEvent e) {
    LoadBSFromFile();
  }

  void LoadBSFromFile() { // LoadBattleSheet From File
//    SaxParserForBS.AskWhatFileToLoad(this, this);
    CreatureCore.AskWhatFileToLoad(this, this);
  }

  void LoadPLFromFile() { // LoadPlayer From File
//    SaxParserForPL.AskWhatFileToLoad(this, this, false);
    TablePlayer.AskWhatFileToLoad(this, this, false);
  }

  void jGMBSReopenButton_actionPerformed(ActionEvent e) {
    fBattleSheetFrame.LoadBattleSheetPane(this);
  }

  void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  void jGMResetCSButton_actionPerformed(ActionEvent e) {
    jGMNameTextField.setText("Creature");
    jGMClassComboBox.setSelectedIndex(4);
    jGMHDiceSpinner.getModel().setValue(new Integer(1));
    jGMHDModSpinner.getModel().setValue(new Integer(0));
    jGMSpecHP.setText("0");
    jGMACSpinner.getModel().setValue(new Integer(10));
    mGMEXP.setValue(new Integer(0));
    jGMAlignmentComboBox.setSelectedIndex(4);
    mGMHackFactor.setValue(new Integer(0));
    mGMMoraleSpinner.setValue(new Integer(10));
    mGMMove.setValue(new Integer(12));
    jGMDescTextArea.setText("Description: ");
    jGMSpecialAttack.setText("");
    JGMSpecialDefense.setText("");
    jGMSizeComboBox.setSelectedIndex(2);
//    while (lAttacks.size() != 0)
//      lAttacks.remove(0);
    jGMNumAtksSpinner.getModel().setValue(new Integer(0));

    jFrequencyComboBox.setSelectedIndex(0);
    jActivityCycleComboBox.setSelectedIndex(0);
    jAppearingInComboBox.setSelectedIndex(0);

// set the creature climate
    for (int i = 0; i < aClimate.size(); i++) {
      Climate oC = (Climate) aClimate.get(i);
      oC.jActive.setSelected(false);
    }
// set the new creatures terrain
    for (int i = 0; i < aTerrain.size(); i++) {
      Terrain oC = (Terrain) aTerrain.get(i);
      oC.jActive.setSelected(false);
    }
    nCurrentCreatureID = -1;
    bCreatureChanged = false;
  }

  void this_windowClosed(WindowEvent e) {
    System.exit(0);
  }

  void jGMNumAtksSpinner_stateChanged(ChangeEvent e) {
    bCreatureChanged = true;
    int nAttackCount = mGMNumAtks.getNumber().intValue();
//jGMDescTextArea.append("Got spinner activity:"+nAttackCount+"\n");

    if (nAttackCount > 0) {
      if (lAttacks.size() < nAttackCount) {
        CreatureCombat oAtk = new CreatureCombat(this, null);
        lAttacks.add(oAtk);
      }
      else
      if (lAttacks.size() > nAttackCount) {
        while (lAttacks.size() > nAttackCount) {
          lAttacks.remove(lAttacks.size() - 1); // remove last attack
        }
      }
    }
    else {
      while (lAttacks.size() != 0) { // smaller than 0, remove everything
        lAttacks.remove(0);
      }
    }
    LoadUpNewCreatureAttackPanel();

  }

  void LoadUpNewCreatureAttackPanel() {
//jGMTextArea.append("got call to loadupnewcreature\n");
    jGMAttackPanel.removeAll();

    JPanel atkTitlePane = new JPanel(new GridLayout(0, 7));
    atkTitlePane.setBackground(Color.lightGray);
    Font fFont = new Font("Dialog", Font.PLAIN, 9);

    JLabel jLabelWeaponType = new JLabel("Weapon Type");
    jLabelWeaponType.setToolTipText(
        "Weapon type used, use Monster for default (hacking).");
    jLabelWeaponType.setFont(fFont);
    atkTitlePane.add(jLabelWeaponType);

    JLabel jLabelToHitMod = new JLabel("To Hit Mod");
    jLabelToHitMod.setToolTipText("Adjustment applied to attack roll.");
    jLabelToHitMod.setFont(fFont);
    atkTitlePane.add(jLabelToHitMod);

    JLabel jLabelNumDice = new JLabel("Damage");
    jLabelNumDice.setToolTipText(
        "Damage for this attack, 1d6 or 1d6+2 or 1d6-2, etc");
    jLabelNumDice.setFont(fFont);
    atkTitlePane.add(jLabelNumDice);

    /*    JLabel jLabelNumSides = new JLabel("# of Sides");
        jLabelNumSides.setToolTipText(
            "Number of sides on a dice, in 2d4 this would be 4.");
        jLabelNumSides.setFont(fFont);
        atkTitlePane.add(jLabelNumSides);

        JLabel jLabelPerDiceMod = new JLabel("Per Dice Mod");
        jLabelPerDiceMod.setToolTipText("This adjustment applied to every dice rolled, on 2d4-2 each roll would be -2 (minimum of 1)");
        jLabelPerDiceMod.setFont(fFont);
        atkTitlePane.add(jLabelPerDiceMod);
     */
    JLabel jLabelTotalMod = new JLabel("To Total");
    jLabelTotalMod.setToolTipText(
        "Adjustment applied after all damage dice rolled.");
    jLabelTotalMod.setFont(fFont);
    atkTitlePane.add(jLabelTotalMod);

    JLabel jLabelPenMod = new JLabel("Penetrate Mod");
    jLabelPenMod.setToolTipText(
        "Improve penetration roll so it occures on lower than MAX rolls.2 with d8 would penetrate at 6,7 and 8.");
    jLabelPenMod.setFont(fFont);
    atkTitlePane.add(jLabelPenMod);

    JLabel jLabelFumbleMod = new JLabel("Fumble Mod");
    jLabelFumbleMod.setToolTipText(
        "Increase chance to fumble, default is 1.");
    jLabelFumbleMod.setFont(fFont);
    atkTitlePane.add(jLabelFumbleMod);

    JLabel jLabelCrit = new JLabel("Crit Mod");
    jLabelCrit.setToolTipText(
        "Increase chance to critical hit. Each point reduces cap from 20, so at 2 crit would occur on 18,19 or 20.");
    jLabelCrit.setFont(fFont);
    atkTitlePane.add(jLabelCrit);

    jGMAttackPanel.add(atkTitlePane);
//jGMTextArea.append("lAttacks size= "+lAttacks.size()+"\n");
    for (int j = 0; j < lAttacks.size(); j++) {
//jGMTextArea.append("adding "+j+"\n");

//      JPanel atkPanel = new JPanel(new GridLayout(0, 7));
      atkTitlePane.setBackground(Color.lightGray);
      CreatureCombat thisAttack = (CreatureCombat) lAttacks.get(j);

      atkTitlePane.add(thisAttack.jWeaponType);
      atkTitlePane.add(thisAttack.jToHitMod);
      atkTitlePane.add(thisAttack.jDamageDice);

//      atkPanel.add(thisAttack.jNumDice);
//      atkPanel.add(thisAttack.jDiceSides);
//      atkPanel.add(thisAttack.jPerDiceMod);
      atkTitlePane.add(thisAttack.jTotalMod);
      atkTitlePane.add(thisAttack.jModPenetration);
      atkTitlePane.add(thisAttack.jModFumble);
      atkTitlePane.add(thisAttack.jModCrit);
//      jGMAttackPanel.add(atkPanel);
    }
    this.repaint();
  }

/*
  // save playertabs to file
    void jplSaveButton_actionPerformed(ActionEvent e) {
    SavePlayer.LoadPlayerFieldsFromTabs(this);
    SavePlayer.AskWhereToSavePlayer(this, jplSaveButton, gplPlayer);
  }
*/
  void jplLoadButton_actionPerformed(ActionEvent e) {
//    LoadPLFromFile();
//    SaxParserForPL.AskWhatFileToLoad(this,this,true);
    TablePlayer.AskWhatFileToLoad(this,this,true);
  }

  void jFindCreatureTextField_keyReleased(KeyEvent e) {
    DisplayCreatureList(jFindCreatureTextField.getText().toUpperCase());
  } // end findcreature text field

  void DisplayCreatureList(String sFind) {
    boolean bFilterNoAttacks = jFilterNoAttackCheckBox.isSelected();
    Object oPreviousSelect = jCreatureList.getSelectedValue();
    dmCreatureList.removeAllElements();
//    aSearchedCreatureList.clear();

    Collections.sort(lSavedCreatures);

    String sSearch = null;
    if (!sFind.equalsIgnoreCase("")) {
      sSearch = "(?i).*?" + sFind + ".*?";
    }
    else {
      sSearch = "";
    }
    for (int i = 0; i < lSavedCreatures.size(); i++) {
      TableSavedCreatures oRecord = (TableSavedCreatures) lSavedCreatures.get(i);
//      String sThisCreature = null;
      boolean bHasAttacks = (oRecord.lMyAttacks.size() > 0);
      /*      if ((sSearch.equals("")) ||  // list all with attacks
       (oRecord.sCreatureName.matches(sSearch))) { // list all specific
       */
      if ( (sSearch.equals("") && !bFilterNoAttacks) || // list all
          (sSearch.equals("") && bHasAttacks && bFilterNoAttacks) || // list all with attacks
          (oRecord.sCreatureName.matches(sSearch) && bHasAttacks &&
           bFilterNoAttacks) || // list specific with atks
          (oRecord.sCreatureName.matches(sSearch) && !bFilterNoAttacks)) { // list all specific
        /*        if (oRecord.lMyAttacks.size() <= 0) {
         sThisCreature = oRecord.sCreatureName + " *"; // if not atks add *
                }
                else {
                  sThisCreature = oRecord.sCreatureName;
                }
         */
        dmCreatureList.addElement(oRecord); // store creature name
//        aSearchedCreatureList.add(oRecord); // and creature object
      }
    }

    // keep select on the target we have
    jCreatureList.setSelectedValue(oPreviousSelect, true);
  }

/* // Add character to Party Group
    void jplAddToPartyButton_actionPerformed(ActionEvent e) {

    SavePlayer.LoadPlayerFieldsFromTabs(this);

    lPlayers.add(gplPlayer);
    // add player to the party list
    fPlayerGroupFrame.mPartyPlayerList.addElement(gplPlayer);

    fPlayerGroupFrame.LoadPartyGroupPane(this);

    // clear out "new" area on main panels
    gplPlayer = new TablePlayer(this);
    TablePlayer.FillPlayerPanel(this, gplPlayer);
  }
*/
/*// reset all playertab infos
    void jplResetButton_actionPerformed(ActionEvent e) {
    gplPlayer = new TablePlayer(this);
    TablePlayer.FillPlayerPanel(this, gplPlayer);
  }
*/
  void jplOpenGroupButton_actionPerformed(ActionEvent e) {
    fPlayerGroupFrame.LoadPartyGroupPane(this);
  }

  void jplLoadPartyButton_actionPerformed(ActionEvent e) {
    TableGroupLog.AskWhatFileToLoad(this, this);
  }

  void jClearCritButton_actionPerformed(ActionEvent e) {
    jCritTextArea.setText("");
  }

  void jGMTurnButton_actionPerformed(ActionEvent e) {
    int nLevel = Integer.parseInt(jGMTurnLevelSpinner.getValue().toString());
    int nTurnTypeIndex = jGMTurnComboBox1.getSelectedIndex();
    int nRoll = Integer.parseInt(jGMTurnSpecificSpinner.getValue().toString());
    if (nRoll == 0) {
      nRoll = MyRandom(20);
    }
    else {
      jGMTurnSpecificSpinner.setValue(new Integer(0));

    }
    TableTurnUndead.TurnUndead(this, nRoll, nLevel, nTurnTypeIndex);
  }

  void jGMRandomNameEditButton_actionPerformed(ActionEvent e) {
    DialogNewRandomName dlg = new DialogNewRandomName(this);
    dlg.panel1.setPreferredSize(new Dimension(500, 260));
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);

    dlg.setTitle("Random Names");
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
//    dlg.show();

  }

  void jGMRandomNameGenerateButton_actionPerformed(ActionEvent e) {
    TableRandomName.GenerateRandomName(this);
  }

  void jGMRandomNameMaleCheckBox_actionPerformed(ActionEvent e) {
    if (!jGMRandomNameMaleCheckBox.isSelected() &&
        !jGMRandomNameFemaleCheckBox.isSelected()) {
      jGMRandomNameMaleCheckBox.setSelected(true);

    }
  }

  void jGMRandomNameFemaleCheckBox_actionPerformed(ActionEvent e) {
    if (!jGMRandomNameMaleCheckBox.isSelected() &&
        !jGMRandomNameFemaleCheckBox.isSelected()) {
      jGMRandomNameMaleCheckBox.setSelected(true);

    }
  }

  void jGMConfigTOPCheckBox1_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-TOP",
                      (jGMConfigTOPCheckBox1.isSelected() ? "true" : "false"));
    SavePrefsFile();
  }

  void jGMConfigFatigueCheckBox2_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-Fatigue",
                      (jGMConfigTOPCheckBox1.isSelected() ? "true" : "false"));
    SavePrefsFile();
  }

  void jGMConfigDefeatAwardCheckBox1_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-AutoDefeat",
                      (jGMConfigDefeatAwardCheckBox1.isSelected() ? "true" :
                       "false"));
    SavePrefsFile();
  }

  void jGMConfigCritAwardCheckBox2_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-AutoCrit",
                      (jGMConfigCritAwardCheckBox2.isSelected() ? "true" :
                       "false"));
    SavePrefsFile();
  }

  // save prefs as set currently.
  void SavePrefsFile() {
    try {
      FileOutputStream fFile = new FileOutputStream(sPrefsFile);
      Prefs.store(fFile, "HackSack Prefs");
      fFile.close();
    }
    catch (IOException ex) {
      DiceOutTextArea.append("Unable to save to prefs file:" + sPrefsFile +
                             "\n");
      gmLog(true, false, "Unable to save to prefs file:" + sPrefsFile + "\n");
    }

  }

  void LoadPrefSettings() {
    try {
      FileInputStream fFile = new FileInputStream(sPrefsFile);
      Prefs.load(fFile);
      fFile.close();

      // warning/check prefs
      if (Prefs.getProperty("GMConfig-TOP") != null) {
        jGMConfigTOPCheckBox1.setSelected( (Prefs.getProperty("GMConfig-TOP").
                                            equalsIgnoreCase("true") ? true : false));
      }
      if (Prefs.getProperty("GMConfig-Fatigue") != null) {
        jGMConfigFatigueCheckBox2.setSelected( (Prefs.getProperty(
            "GMConfig-Fatigue").equalsIgnoreCase("true") ? true : false));

        // set auto do things
      }
      if (Prefs.getProperty("GMConfig-AutoDefeat") != null) {
        jGMConfigDefeatAwardCheckBox1.setSelected( (Prefs.getProperty(
            "GMConfig-AutoDefeat").equalsIgnoreCase("true") ? true : false));
      }
      if (Prefs.getProperty("GMConfig-AutoCrit") != null) {
        jGMConfigCritAwardCheckBox2.setSelected( (Prefs.getProperty(
            "GMConfig-AutoCrit").equalsIgnoreCase("true") ? true : false));

      }

      if (Prefs.getProperty("GMConfig-ADD-Kicker") != null) {
        jADDKickerCheckBox.setSelected( (Prefs.getProperty(
            "GMConfig-ADD-Kicker").equalsIgnoreCase("true") ? true : false));
      }
      if (Prefs.getProperty("GMConfig-ADD-Crit") != null) {
        jADDCritCheckBox.setSelected( (Prefs.getProperty(
            "GMConfig-ADD-Crit").equalsIgnoreCase("true") ? true : false));
      }
      if (Prefs.getProperty("GMConfig-ADD-Fumble") != null) {
        jADDFumbleCheckBox.setSelected( (Prefs.getProperty(
            "GMConfig-ADD-Fumble").equalsIgnoreCase("true") ? true : false));
      }
      if (Prefs.getProperty("GMConfig-ADD-Penetration") != null) {
        jADDPenetrationCheckBox.setSelected( (Prefs.getProperty(
            "GMConfig-ADD-Penetration").equalsIgnoreCase("true") ? true : false));
      }


      String sX = Prefs.getProperty("pref-mainX");
      String sY = Prefs.getProperty("pref-mainY");
      String sHx = Prefs.getProperty("pref-mainHx");
      String sHy = Prefs.getProperty("pref-mainHy");
      if (sX != null && sY != null & sHx != null && sHy != null) {
        int nX = Integer.parseInt(sX);
        int nY = Integer.parseInt(sY);
        int nHx = Integer.parseInt(sHx);
        int nHy = Integer.parseInt(sHy);
        this.setLocation(nX, nY);
        this.setSize(nHy, nHx);
      }
    }
    catch (IOException ex) {
//      DiceOutTextArea.append("Unable to open "+sPrefsFile+" for loading.\n");
    }
  }

  void jGMConfigNewHonorButton_actionPerformed(ActionEvent e) {
    TableGroupLog.DoNewHonorAward(this, this, false);
  }

  void jGMConfigNewEXPButton3_actionPerformed(ActionEvent e) {
    TableGroupLog.DoNewEXPBonus(this, this);
  }

  void jGMConfigNewClassButton6_actionPerformed(ActionEvent e) {
    TableClass.DoNewClass(this, this, null, null);
  }

  void jGMConfigNewGearButton7_actionPerformed(ActionEvent e) {
    TableGear.DoNewGear(this, this, null, null,null);
  }

  void jGmConfigNewSkillButton5_actionPerformed(ActionEvent e) {
    TableSkills.DoNewSkill(this, this, null, null,-1);
  }

  void jGMconfigNewQuirkButton8_actionPerformed(ActionEvent e) {
    TableQuirks.DoNewQuirk(this, this, null, null);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    jRandomNameResultsTextArea.setText("");
  }

  void jCreatureList_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      TableSavedCreatures oC = (TableSavedCreatures) jCreatureList.
          getSelectedValue();
//      TableSavedCreatures oC = (TableSavedCreatures) aSearchedCreatureList.get(
//          jCreatureList.getSelectedIndex());
      oC.loadButtonPressed(oC);
    }

  }

  void jLoadCreatureButton_actionPerformed(ActionEvent e) {
    TableSavedCreatures oC = (TableSavedCreatures) jCreatureList.
        getSelectedValue();
//    TableSavedCreatures oC = (TableSavedCreatures) aSearchedCreatureList.get(
//        jCreatureList.getSelectedIndex());
    oC.loadButtonPressed(oC);
  }

  void jRemoveCreatureButton_actionPerformed(ActionEvent e) {
//    TableSavedCreatures oC = (TableSavedCreatures) jCreatureList.getSelectedValue();
    Object[] oObjs = jCreatureList.getSelectedValues();
    for (int i=0;i<oObjs.length;i++) {
      TableSavedCreatures oC = (TableSavedCreatures)oObjs[i];
      oC.deleteButtonPressed(oC);
    }
  //  oC.deleteButtonPressed(oC);
  }

  void jEditTerrainButton_actionPerformed(ActionEvent e) {
    bCreatureChanged = true;
    DialogTerrain dlg = new DialogTerrain(this);
    Dimension dlgSize = dlg.panel1.getPreferredSize();
    Dimension frmSize = this.getSize();
    Point loc = this.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setTitle("Climate and Terrain");
    dlg.setModal(true);
//    dlg.pack();
    dlg.setVisible(true);
//    dlg.show();

  }

  void jFilterNoAttackCheckBox_actionPerformed(ActionEvent e) {
    DisplayCreatureList(jFindCreatureTextField.getText().toUpperCase());
  }

  // Import a HMTK XML character sheet
  void jImportXMLButton_actionPerformed(ActionEvent e) {
//    fPlayerGroupFrame.LoadPartyGroupPane(this, null);
    SaxParserXMLCharacter.AskWhatFileToLoad(this, fPlayerGroupFrame);
  }

  void jSupriseCheckButton_actionPerformed(ActionEvent e) {
    checkSpecial.checkSuprise(this);
  }

  // make sure the special HP value is properly set
  void jGMSpecHP_focusLost(FocusEvent e) {
    String sSpecialHp = jGMSpecHP.getText();
    // this matched 1d6 or 1d6+1 or 1d6-1 or 10
    String sDiceMatch = "(\\d+)|(\\d+d\\d+([+-]\\d+)?)";
    if (!sSpecialHp.matches("(?si)^" + sDiceMatch + "$")) {
      jGMSpecHP.setText("0");
      ShowError(this,
          "Invalid value entered.\nExamples:1d6 or 1d6+1 or 1d6+12 or 15\n");
    }
  }

  void jGMNameTextField_keyTyped(KeyEvent e) {
    bCreatureChanged = true;
  }

  void jGMHackFactorSpinner_stateChanged(ChangeEvent e) {
    bCreatureChanged = true;
  }

  void jFrequencyComboBox_actionPerformed(ActionEvent e) {
    bCreatureChanged = true;
  }

  void jGMSpecialAttack_keyTyped(KeyEvent e) {
    bCreatureChanged = true;
  }

  void jClearGeneratedButton_actionPerformed(ActionEvent e) {
    dmEncounterGenerated.removeAllElements();
    jEncounterResultsTextArea.setText("");
  }

  void jEncountersGeneratedList_mouseClicked(MouseEvent e) {
    if (e.getClickCount() >= 2) {
      CreatureEncounter oE = (CreatureEncounter) jEncountersGeneratedList.
          getSelectedValue();
      oE.oCreature.loadButtonPressed(oE.oCreature);
      jGMDiceBagTabbedPane.setSelectedIndex(2);
    }
  }

  void jEncounterTableList_mouseClicked(MouseEvent e) {
    if (e.getClickCount() >= 2) {
      CreatureEncounter oE = (CreatureEncounter) jEncounterTableList.
          getSelectedValue();
      oE.oCreature.loadButtonPressed(oE.oCreature);
      jGMDiceBagTabbedPane.setSelectedIndex(2);
    }

  }

  void jGMDescTextArea_keyTyped(KeyEvent e) {
    bCreatureChanged = true;
  }

  void jSupriseRangeSpinner_stateChanged(ChangeEvent e) {
    setSupriseDetailsLabel();
  }
  void jSupriseModSpinner_stateChanged(ChangeEvent e) {
    setSupriseDetailsLabel();
  }
  void jSupriseDiceSizeSpinner_stateChanged(ChangeEvent e) {
    setSupriseDetailsLabel();
   }
// this sets the suprise label to show the range, dice/etc properly
  void setSupriseDetailsLabel() {
    String sRange = jSupriseRangeSpinner.getValue().toString();
    String sMod = jSupriseModSpinner.getValue().toString();
    String sDiceSize = jSupriseDiceSizeSpinner.getValue().toString();
    jSupriseDetailsLabel.setText("Suprise range 1-"+sRange+
                                 (sMod.equals("0")?"":" ["+sMod+"]")+
                                 " on d"+sDiceSize);

  }
  void jSupriseResetButton_actionPerformed(ActionEvent e) {
    jSupriseRangeSpinner.setValue(new Integer(3));
    jSupriseModSpinner.setValue(new Integer(0));
    jSupriseDiceSizeSpinner.setValue(new Integer(10));
  }

  void jTurnUndeadClearButton_actionPerformed(ActionEvent e) {
    jTurnUndeadTextArea.setText("");
  }

  void jClearSupriseTextButton_actionPerformed(ActionEvent e) {
    jSupriseTextArea.setText("");
  }

  void jMoraleClearResultsButton_actionPerformed(ActionEvent e) {
    jMoraleTextArea.setText("");
  }

  void jPickPocketDetectCheckButton_actionPerformed(ActionEvent e) {
    checkSpecial.checkPickpocketDetect(this);
  }

  void jPickPocketDetectClearButton_actionPerformed(ActionEvent e) {
    jPickPocketDetectTextArea1.setText("");
  }

  void jNewPlayerButton_actionPerformed(ActionEvent e) {
    if (!fPlayerGroupFrame.isVisible())
      fPlayerGroupFrame.LoadPartyGroupPane(this);
    fPlayerGroupFrame.jNewPlayer_actionPerformed(null);
  }

  // dialog for OS/Java and other Version information.
  void jMenuVersionInformation_actionPerformed(ActionEvent e) {
    String sDetails = "";
    sDetails += "OS NAME:"+System.getProperties().getProperty("os.name")+"\n";
    sDetails += "OS SYSTEM ARCH:"+System.getProperties().getProperty("os.arch")+"\n";
    sDetails += "OS VERSION:"+System.getProperties().getProperty("os.version")+"\n";

    sDetails += "JAVA INSTALL:"+System.getProperties().getProperty("java.home")+"\n";
    sDetails += "JAVA VERSION:"+System.getProperties().getProperty("java.version")+"\n";

    sDetails += "JAVA VM NAME:"+System.getProperties().getProperty("java.vm.name")+"\n";
    sDetails += "JAVA VM VERSION:"+System.getProperties().getProperty("java.vm.version")+"\n";

    ShowDoneFrame(this,"Version Information",sDetails);
  }

  void jADDKickerCheckBox_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-ADD-Kicker",(jADDKickerCheckBox.isSelected() ? "true" : "false"));
    SavePrefsFile();
  }

  void jADDPenetrationCheckBox_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-ADD-Penetration",(jADDPenetrationCheckBox.isSelected() ? "true" : "false"));
    SavePrefsFile();
  }

  void jADDCritCheckBox_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-ADD-Crit",(jADDCritCheckBox.isSelected() ? "true" : "false"));
    SavePrefsFile();
  }

  void jADDFumbleCheckBox_actionPerformed(ActionEvent e) {
    Prefs.setProperty("GMConfig-ADD-Fumble",(jADDFumbleCheckBox.isSelected() ? "true" : "false"));
    SavePrefsFile();

  }


}

class HackSackFrame_jMenuSavePrefs_ActionAdapter
    implements ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jMenuSavePrefs_ActionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuSavePrefs_actionPerformed(e);
  }

}

class HackSackFrame_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jMenuHelpAbout_ActionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class HackSackFrame_D10000_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D10000_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D10000_actionPerformed(e);
  }
}

class HackSackFrame_D100_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D100_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D100_actionPerformed(e);
  }
}

class HackSackFrame_D1000_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D1000_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D1000_actionPerformed(e);
  }
}

class HackSackFrame_D20_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D20_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D20_actionPerformed(e);
  }
}

class HackSackFrame_D12_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D12_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D12_actionPerformed(e);
  }
}

class HackSackFrame_D10_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D10_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D10_actionPerformed(e);
  }
}

class HackSackFrame_D8_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D8_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D8_actionPerformed(e);
  }
}

class HackSackFrame_D6_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D6_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D6_actionPerformed(e);
  }
}

class HackSackFrame_D5_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D5_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D5_actionPerformed(e);
  }
}

class HackSackFrame_D4_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D4_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D4_actionPerformed(e);
  }
}

class HackSackFrame_D2_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D2_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D2_actionPerformed(e);
  }
}

class HackSackFrame_D3_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_D3_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.D3_actionPerformed(e);
  }
}

class HackSackFrame_ROLLDICE_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_ROLLDICE_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.ROLLDICE_actionPerformed(e);
  }
}

class HackSackFrame_ClearRollSpace_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_ClearRollSpace_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.ClearRollSpace_actionPerformed(e);
  }
}

/*class HackSackFrame_mPlayerHealth_ChangeAdapter
    implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_mPlayerHealth_ChangeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.mPlayerHealth_stateChanged(e);
  }
 }
 */
class HackSackFrame_jGMMakeSheetButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMMakeSheetButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMMakeSheetButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMClearButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMClearButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMClearButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMFumbleButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMFumbleButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMFumbleButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMSpellMishapButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMSpellMishapButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMSpellMishapButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMCritButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMCritButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMCritButton_actionPerformed(e);
  }
}

class HackSackFrame_jCleanSheetButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jCleanSheetButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCleanSheetButton_actionPerformed(e);
  }
}

class HackSackFrame_jTRGenButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jTRGenButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTRGenButton_actionPerformed(e);
  }
}

class HackSackFrame_jTRResetButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jTRResetButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTRResetButton_actionPerformed(e);
  }
}

class HackSackFrame_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jButton1_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class HackSackFrame_jGMMoraleCheckButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMMoraleCheckButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMMoraleCheckButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMMoraleResetButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMMoraleResetButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMMoraleResetButton_actionPerformed(e);
  }
}

class HackSackFrame_jTRMakeItemButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jTRMakeItemButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTRMakeItemButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMSaveButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMSaveButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMSaveButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMLoadBSButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMLoadBSButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMLoadBSButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMBSReopenButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMBSReopenButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMBSReopenButton_actionPerformed(e);
  }
}

class HackSackFrame_jMenuFileExit_ActionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jMenuFileExit_ActionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class HackSackFrame_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  HackSackFrame adaptee;

  HackSackFrame_this_windowAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}

class HackSackFrame_jGMNumAtksSpinner_changeAdapter
    implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMNumAtksSpinner_changeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jGMNumAtksSpinner_stateChanged(e);
  }
}

class HackSackFrame_jGMResetCSButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMResetCSButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMResetCSButton_actionPerformed(e);
  }
}

class HackSackFrame_jFindCreatureTextField_keyAdapter
    extends java.awt.event.KeyAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jFindCreatureTextField_keyAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.jFindCreatureTextField_keyReleased(e);
  }
}

class HackSackFrame_jplLoadButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jplLoadButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jplLoadButton_actionPerformed(e);
  }
}

class HackSackFrame_jplOpenGroupButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jplOpenGroupButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jplOpenGroupButton_actionPerformed(e);
  }
}

class HackSackFrame_jplLoadPartyButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jplLoadPartyButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jplLoadPartyButton_actionPerformed(e);
  }
}

class HackSackFrame_jClearCritButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jClearCritButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jClearCritButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMTurnButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMTurnButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMTurnButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMRandomNameEditButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMRandomNameEditButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMRandomNameEditButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMRandomNameGenerateButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMRandomNameGenerateButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMRandomNameGenerateButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMRandomNameMaleCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMRandomNameMaleCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMRandomNameMaleCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jGMRandomNameFemaleCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMRandomNameFemaleCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMRandomNameFemaleCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigTOPCheckBox1_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigTOPCheckBox1_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigTOPCheckBox1_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigFatigueCheckBox2_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigFatigueCheckBox2_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigFatigueCheckBox2_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigNewHonorButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigNewHonorButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigNewHonorButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigNewEXPButton3_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigNewEXPButton3_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigNewEXPButton3_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigNewClassButton6_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigNewClassButton6_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigNewClassButton6_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigNewGearButton7_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigNewGearButton7_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigNewGearButton7_actionPerformed(e);
  }
}

class HackSackFrame_jGmConfigNewSkillButton5_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGmConfigNewSkillButton5_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGmConfigNewSkillButton5_actionPerformed(e);
  }
}

class HackSackFrame_jGMconfigNewQuirkButton8_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMconfigNewQuirkButton8_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMconfigNewQuirkButton8_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigDefeatAwardCheckBox1_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigDefeatAwardCheckBox1_actionAdapter(HackSackFrame
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigDefeatAwardCheckBox1_actionPerformed(e);
  }
}

class HackSackFrame_jGMConfigCritAwardCheckBox2_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMConfigCritAwardCheckBox2_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jGMConfigCritAwardCheckBox2_actionPerformed(e);
  }
}

class HackSackFrame_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jButton2_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class HackSackFrame_jCreatureList_mouseAdapter
    extends java.awt.event.MouseAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jCreatureList_mouseAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jCreatureList_mouseClicked(e);
  }
}

class HackSackFrame_jLoadCreatureButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jLoadCreatureButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jLoadCreatureButton_actionPerformed(e);
  }
}

class HackSackFrame_jRemoveCreatureButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jRemoveCreatureButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRemoveCreatureButton_actionPerformed(e);
  }
}

class HackSackFrame_jEditTerrainButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jEditTerrainButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jEditTerrainButton_actionPerformed(e);
  }
}

class HackSackFrame_jFilterNoAttackCheckBox_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jFilterNoAttackCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jFilterNoAttackCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jImportXMLButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jImportXMLButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jImportXMLButton_actionPerformed(e);
  }
}

class HackSackFrame_jSupriseCheckButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jSupriseCheckButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jSupriseCheckButton_actionPerformed(e);
  }
}

class HackSackFrame_jGMSpecHP_focusAdapter
    extends java.awt.event.FocusAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jGMSpecHP_focusAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.jGMSpecHP_focusLost(e);
  }
}

class HackSackFrame_jGMNameTextField_keyAdapter
    extends java.awt.event.KeyAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jGMNameTextField_keyAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.jGMNameTextField_keyTyped(e);
  }
}

class HackSackFrame_jGMHackFactorSpinner_changeAdapter
    implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_jGMHackFactorSpinner_changeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jGMHackFactorSpinner_stateChanged(e);
  }
}

class HackSackFrame_jFrequencyComboBox_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jFrequencyComboBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jFrequencyComboBox_actionPerformed(e);
  }
}

class HackSackFrame_jGMSpecialAttack_keyAdapter
    extends java.awt.event.KeyAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jGMSpecialAttack_keyAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.jGMSpecialAttack_keyTyped(e);
  }
}

class HackSackFrame_jClearGeneratedButton_actionAdapter
    implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jClearGeneratedButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jClearGeneratedButton_actionPerformed(e);
  }
}

class HackSackFrame_jEncountersGeneratedList_mouseAdapter
    extends java.awt.event.MouseAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jEncountersGeneratedList_mouseAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jEncountersGeneratedList_mouseClicked(e);
  }
}

class HackSackFrame_jEncounterTableList_mouseAdapter
    extends java.awt.event.MouseAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jEncounterTableList_mouseAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jEncounterTableList_mouseClicked(e);
  }
}

class HackSackFrame_jGMDescTextArea_keyAdapter
    extends java.awt.event.KeyAdapter {
  HackSackFrame adaptee;

  HackSackFrame_jGMDescTextArea_keyAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.jGMDescTextArea_keyTyped(e);
  }
}

class HackSackFrame_jSupriseRangeSpinner_changeAdapter
    implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_jSupriseRangeSpinner_changeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jSupriseRangeSpinner_stateChanged(e);
  }
}

class HackSackFrame_jSupriseModSpinner_changeAdapter implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_jSupriseModSpinner_changeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.jSupriseModSpinner_stateChanged(e);
  }
}

class HackSackFrame_jSupriseDiceSizeSpinner_changeAdapter implements javax.swing.event.ChangeListener {
  HackSackFrame adaptee;

  HackSackFrame_jSupriseDiceSizeSpinner_changeAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void stateChanged(ChangeEvent e) {
    adaptee.jSupriseDiceSizeSpinner_stateChanged(e);
  }
}

class HackSackFrame_jSupriseResetButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jSupriseResetButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSupriseResetButton_actionPerformed(e);
  }
}

class HackSackFrame_jTurnUndeadClearButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jTurnUndeadClearButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTurnUndeadClearButton_actionPerformed(e);
  }
}

class HackSackFrame_jClearSupriseTextButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jClearSupriseTextButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jClearSupriseTextButton_actionPerformed(e);
  }
}

class HackSackFrame_jMoraleClearResultsButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jMoraleClearResultsButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMoraleClearResultsButton_actionPerformed(e);
  }
}

class HackSackFrame_jPickPocketDetectCheckButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jPickPocketDetectCheckButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPickPocketDetectCheckButton_actionPerformed(e);
  }
}

class HackSackFrame_jPickPocketDetectClearButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jPickPocketDetectClearButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jPickPocketDetectClearButton_actionPerformed(e);
  }
}

class HackSackFrame_jNewPlayerButton_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jNewPlayerButton_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jNewPlayerButton_actionPerformed(e);
  }
}

class HackSackFrame_jMenuVersionInformation_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jMenuVersionInformation_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuVersionInformation_actionPerformed(e);
  }
}

class HackSackFrame_jADDKickerCheckBox_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jADDKickerCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jADDKickerCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jADDPenetrationCheckBox_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jADDPenetrationCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jADDPenetrationCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jADDCritCheckBox_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jADDCritCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jADDCritCheckBox_actionPerformed(e);
  }
}

class HackSackFrame_jADDFumbleCheckBox_actionAdapter implements java.awt.event.ActionListener {
  HackSackFrame adaptee;

  HackSackFrame_jADDFumbleCheckBox_actionAdapter(HackSackFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jADDFumbleCheckBox_actionPerformed(e);
  }
}


