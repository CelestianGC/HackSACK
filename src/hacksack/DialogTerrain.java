package hacksack;

import java.util.*;

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

public class DialogTerrain extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel1 = new JPanel();
  JButton jSetButton = new JButton();
  JButton jCancelButton = new JButton();
  JPanel jPanel2 = new JPanel();
  JScrollPane jClimateScrollPane1 = new JScrollPane();
  JScrollPane jTerrainScrollPane2 = new JScrollPane();
  JPanel jClimatePanel3 = new JPanel(new GridLayout(0,3));
  JPanel jTerrainPanel = new JPanel(new GridLayout(0,3));
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  Border border4;
  TitledBorder titledBorder4;
  ArrayList aClimateSaved = new ArrayList();
  ArrayList aTerrainSaved = new ArrayList();
  HackSackFrame oParent = null;
  ArrayList aClimate = null;
  ArrayList aTerrain = null;

  public DialogTerrain(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogTerrain(HackSackFrame oParent) {
    this(null, "", false);
    this.oParent = oParent;
    aClimate = oParent.aClimate;
    aTerrain = oParent.aTerrain;

    jClimatePanel3.removeAll();
    for (int i=0;i<aClimate.size();i++) {
      Climate oC = (Climate)aClimate.get(i);
      Climate oS = new Climate(oC.sName);
      oS.jActive.setSelected(oC.jActive.isSelected());
      oC.jActive.setBackground(SystemColor.inactiveCaptionBorder);
      aClimateSaved.add(oS);
      jClimatePanel3.add(oC.jActive);
    }
    jTerrainPanel.removeAll();
    for (int i=0;i<aTerrain.size();i++) {
      Terrain oC = (Terrain)aTerrain.get(i);
      Terrain oS = new Terrain(oC.sName);
      oS.jActive.setSelected(oC.jActive.isSelected());
      aTerrainSaved.add(oS);
      oC.jActive.setBackground(Color.lightGray);
      jTerrainPanel.add(oC.jActive);
    }

  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Climate and Terrain");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(93, 93, 93),new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Climate and Terrain");
    border3 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(BorderFactory.createLineBorder(Color.gray,1),"Climate");
    border4 = BorderFactory.createEmptyBorder();
    titledBorder4 = new TitledBorder(BorderFactory.createLineBorder(Color.gray,1),"Terrain");
    panel1.setLayout(borderLayout1);
    panel1.setBackground(Color.lightGray);
    jSetButton.setText("Set");
    jSetButton.addActionListener(new DialogTerrain_jSetButton_actionAdapter(this));
    jCancelButton.setText("Cancel");
    jCancelButton.addActionListener(new DialogTerrain_jCancelButton_actionAdapter(this));
    jPanel1.setBackground(Color.gray);
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel2.setLayout(null);
    jClimateScrollPane1.getViewport().setBackground(Color.white);
    jClimateScrollPane1.setBounds(new Rectangle(16, 21, 368, 85));
    jTerrainScrollPane2.getViewport().setBackground(Color.white);
    jTerrainScrollPane2.setBounds(new Rectangle(16, 117, 368, 194));
    jPanel2.setBackground(Color.gray);
    jPanel2.setBorder(titledBorder2);
    jClimatePanel3.setBackground(SystemColor.inactiveCaptionBorder);
    jClimatePanel3.setBorder(titledBorder3);
    jTerrainPanel.setBackground(Color.lightGray);
    jTerrainPanel.setBorder(titledBorder4);
    this.addWindowListener(new DialogTerrain_this_windowAdapter(this));
    getContentPane().add(panel1);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jSetButton, null);
    jPanel1.add(jCancelButton, null);
    panel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jTerrainScrollPane2, null);
    jPanel2.add(jClimateScrollPane1, null);
    jClimateScrollPane1.getViewport().add(jClimatePanel3, null);
    jTerrainScrollPane2.getViewport().add(jTerrainPanel, null);
    panel1.setPreferredSize(new Dimension(400,320)); //w,h
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    CancelSave();
  }

  void jSetButton_actionPerformed(ActionEvent e) {
    // values set, lets go
    this.setVisible(false);
//    this.hide();
  }

  void this_windowClosed(WindowEvent e) {
    CancelSave();
  }

  void CancelSave() {
    // restore previous values
    for (int i=0;i<aClimateSaved.size();i++) {
      Climate oC = (Climate)aClimateSaved.get(i);
      Climate oO = (Climate)aClimate.get(i);
      oO.jActive.setSelected(oC.jActive.isSelected());
    }
    for (int i=0;i<aTerrainSaved.size();i++) {
      Terrain oC = (Terrain)aTerrainSaved.get(i);
      Terrain oO = (Terrain)aTerrain.get(i);
      oO.jActive.setSelected(oC.jActive.isSelected());
    }
    aClimateSaved.clear();
    aTerrainSaved.clear();

    this.setVisible(false);
//    this.hide();

  }
}

class DialogTerrain_jCancelButton_actionAdapter implements java.awt.event.ActionListener {
  DialogTerrain adaptee;

  DialogTerrain_jCancelButton_actionAdapter(DialogTerrain adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCancelButton_actionPerformed(e);
  }
}

class DialogTerrain_jSetButton_actionAdapter implements java.awt.event.ActionListener {
  DialogTerrain adaptee;

  DialogTerrain_jSetButton_actionAdapter(DialogTerrain adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jSetButton_actionPerformed(e);
  }
}

class DialogTerrain_this_windowAdapter extends java.awt.event.WindowAdapter {
  DialogTerrain adaptee;

  DialogTerrain_this_windowAdapter(DialogTerrain adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
}
