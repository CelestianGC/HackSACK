package hacksack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class DialogTOP extends JDialog {
  HackSackFrame oParent = null;
  CreatureCore oCreature = null;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jDataPanel = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel2 = new JPanel();
  JButton jButton1 = new JButton();
  JLabel jNameLabel = new JLabel();
  JSpinner jSaveScoreSpinner = new JSpinner(new SpinnerNumberModel(1,1,20,1));
  JButton jButton2 = new JButton();
  JLabel jLabel1 = new JLabel();

  public DialogTOP(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogTOP(HackSackFrame oParent, CreatureCore oCreature) {
    this(null, "", false);
    this.oParent = oParent;
    this.oCreature = oCreature;

    // CLASS,SAVE_TYPE (0=para/poison/death), LEVEL
    String sSaveAsClass = (String)oCreature.jClassSelect.getSelectedItem();
    if (sSaveAsClass.equalsIgnoreCase("MONSTER")) // monsters save as fighters
      sSaveAsClass = "FIGHTER";
    if (sSaveAsClass.equalsIgnoreCase("MAGIC-USER")) // this just a look thing
      sSaveAsClass = "MAGE";

    int  nCurrentSave = Class.GetSave(oParent, sSaveAsClass, 0, oCreature.nLevel);

    jSaveScoreSpinner.setValue(new Integer(nCurrentSave));
    jNameLabel.setText(oCreature.sCreatureName);
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Threshold of Pain");
    panel1.setLayout(borderLayout1);
    jDataPanel.setBackground(Color.lightGray);
    jDataPanel.setBorder(titledBorder1);
    jButton1.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton1.setToolTipText("Ignore TOP warning.");
    jButton1.setText("Ignore");
    jButton1.addActionListener(new DialogTOP_jButton1_actionAdapter(this));
    jPanel2.setBackground(Color.lightGray);
    jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
    jNameLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jNameLabel.setForeground(Color.red);
    jNameLabel.setToolTipText("Name of creature needing to make a threshold of pain check.");
    jNameLabel.setText("Creature Name");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 9));
    jButton2.setToolTipText("Make a threshold of pain check!");
    jButton2.setText("Check!");
    jButton2.addActionListener(new DialogTOP_jButton2_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font("Dialog", 2, 9));
    jLabel1.setToolTipText("Roll needed to succeed check.");
    jLabel1.setText("Save");
    getContentPane().add(panel1);
    panel1.add(jDataPanel, BorderLayout.CENTER);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton1, null);
    jDataPanel.add(jButton2, null);
    jDataPanel.add(jNameLabel, null);
    jDataPanel.add(jLabel1, null);
    jDataPanel.add(jSaveScoreSpinner, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setVisible(false);
//    this.hide();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    int nSaveScore = Integer.parseInt(jSaveScoreSpinner.getValue().toString());
    int nRoll = oParent.MyRandom(20);

    oParent.gmLog(true,true,"ToP check dice roll "+nRoll+" for save of "+nSaveScore+"\n");
    if (nRoll < nSaveScore)
    {
      int nDiff =  nSaveScore - nRoll;
      String sDesc;
      if (nDiff >= 8) // in out cold
      {
        sDesc = "Incapacitated for "+nDiff+" rounds"+
                       " AND is UNCONCIOUS!\nMake con check each round or lose 1 point of constition, death at 0 con\n";
      } else
      if (nDiff >= 4) // in shock
      {
        sDesc = "Incapacitated for "+nDiff+" rounds"+
                       " AND in SHOCK!\nMmust make con check each turn of be Confused for a turn\n";
      }
      else
        sDesc = "Failed threshold of pain check!\nIncapacitated for "+
                       nDiff+" round"+((nDiff>1)?"s":"")+".\n";

     oParent.gplGroupLog.groupLog(oCreature.sCreatureName+" "+sDesc);
      oParent.ShowDone(this,oCreature.sCreatureName+" "+sDesc);
    }
    else
    {
      oParent.ShowDone(this,"Passed threshold of pain check.");
    }

    this.setVisible(false);
//    this.hide();
  }

static void LoadDialog(Component oLoc,
                       HackSackFrame oParent, CreatureCore oCreature) {
    DialogTOP dlg = new DialogTOP(oParent, oCreature);
    Dimension dlgSize = dlg.panel1.getPreferredSize();
    Dimension frmSize = oParent.getSize();
    Point loc = oLoc.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setTitle("Threshold of Pain Check");
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);

}

}

class DialogTOP_jButton1_actionAdapter implements java.awt.event.ActionListener {
  DialogTOP adaptee;

  DialogTOP_jButton1_actionAdapter(DialogTOP adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class DialogTOP_jButton2_actionAdapter implements java.awt.event.ActionListener {
  DialogTOP adaptee;

  DialogTOP_jButton2_actionAdapter(DialogTOP adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}
