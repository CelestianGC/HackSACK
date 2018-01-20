package hacksack;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.jdom.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
//import com.borland.jbcl.layout.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableInformation {
  JTextArea jName = null;
  JTextArea jRoomDescDM = null;
  JTextArea jRoomDescPlayer = null;

  JButton jDeleteButton = null;

  int nDEFAULT_WIDTH = 70;
  int nDEFAULT_HEIGHT = 80;

  public TableInformation(HackSackFrame oParent) {

    jName = new JTextArea("New Area");

    jRoomDescPlayer = new JTextArea("");
    jRoomDescPlayer.setBackground(new Color(186, 255, 155));
    jRoomDescPlayer.setWrapStyleWord(true);
    jRoomDescPlayer.setLineWrap(true);
    jRoomDescPlayer.setWrapStyleWord(true);

    jRoomDescDM = new JTextArea("");
    jRoomDescDM.setBackground(new Color(255, 179, 179));
    jRoomDescDM.setWrapStyleWord(true);
    jRoomDescDM.setLineWrap(true);
    jRoomDescDM.setWrapStyleWord(true);

    jDeleteButton = new JButton("Delete");
    jDeleteButton.setToolTipText("Delete this area information.");
    jDeleteButton.addActionListener(new jDeleteButton_actionAdapter(oParent,this));
  }

  JPanel getInformationPanel() {
    //JPanel jThisInfoPanel = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP));
    JPanel jThisInfoPanel = new JPanel(new VerticalFlowLayout());
    Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
                                              Color.white,
                                              new Color(182, 182, 182),
                                              new Color(62, 62, 62),
                                              new Color(89, 89, 89));
    TitledBorder titledBorder1 = new TitledBorder(border1, "Area Information");
    jThisInfoPanel.setBorder(titledBorder1);

    // name
    JLabel jNameLabel = new JLabel("Name");
    jThisInfoPanel.add(jNameLabel);
    jThisInfoPanel.add(this.jName);

    // RoomDescPlayer
    JScrollPane jRoomDescScrollPanelPlayer = new JScrollPane();
    jRoomDescScrollPanelPlayer.setPreferredSize(new Dimension(nDEFAULT_WIDTH*6,nDEFAULT_HEIGHT*3));
    jRoomDescScrollPanelPlayer.getViewport().add(this.jRoomDescPlayer, null);
    Border PlayerDescBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        Color.white,
        new Color(182, 182, 182),
        new Color(62, 62, 62),
        new Color(89, 89, 89));
    TitledBorder PlayerDescTitleBorder = new TitledBorder(PlayerDescBorder, "Player Information");
    jRoomDescScrollPanelPlayer.setBorder(PlayerDescTitleBorder);
    jThisInfoPanel.add(jRoomDescScrollPanelPlayer);

    // RoomDescGM
    JScrollPane jRoomDescScrollPanelGM = new JScrollPane();
    jRoomDescScrollPanelGM.setPreferredSize(new Dimension(nDEFAULT_WIDTH*6,nDEFAULT_HEIGHT*3));
    jRoomDescScrollPanelGM.getViewport().add(this.jRoomDescDM, null);
    Border DMDescBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
        Color.white,
        new Color(182, 182, 182),
        new Color(62, 62, 62),
        new Color(89, 89, 89));
    TitledBorder DMDescTitleBorder = new TitledBorder(DMDescBorder, "DM Information");
    jRoomDescScrollPanelGM.setBorder(DMDescTitleBorder);
    jThisInfoPanel.add(jRoomDescScrollPanelGM);

    // delete info panel button
    jThisInfoPanel.add(jDeleteButton);
    return jThisInfoPanel;
  }

void jDeleteButton_actionPerformed(ActionEvent e,HackSackFrame oParent) {
  if (oParent.AskYN(oParent.fBattleSheetFrame,"Remove this information area?")) {
    oParent.lInformation.remove(this);
    FrameBattleSheet.LoadInformationSheetTab(oParent);
    oParent.fBattleSheetFrame.jBSTabbedPane1.repaint();
  }
}

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("Area_Information");

    eItem.addContent(new Element("jName").setText(xmlControl.escapeChars(jName.getText())));
    eItem.addContent(new Element("jRoomDescPlayer").setText(xmlControl.escapeChars(jRoomDescPlayer.getText())));
    eItem.addContent(new Element("jRoomDescDM").setText(xmlControl.escapeChars(jRoomDescDM.getText())));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static TableInformation xmlGetFromElements(Element eItem,
                                             HackSackFrame oParent) {
    TableInformation oO = new TableInformation(oParent);

    oO.jName.setText(xmlControl.unEscapeChars(eItem.getChild("jName").getText()));
    oO.jRoomDescDM.setText(xmlControl.unEscapeChars(eItem.getChild("jRoomDescDM").getText()));
    oO.jRoomDescPlayer.setText(xmlControl.unEscapeChars(eItem.getChild("jRoomDescPlayer").getText()));

    return oO;
  }

  /**
   * this builds a document of elements from an arraylist so it can be saved to
   * an xml file.
   *
   * @param lList ArrayList
   * @return Element
   */
  static Element xmlBuildElementFromList(ArrayList lList) {
   Element eRoot = new Element("InformationSheets");
   for(int i=0;i<lList.size();i++) {

     TableInformation oO = (TableInformation)lList.get(i);
     eRoot.addContent(oO.xmlGetElements());

   }

   return eRoot;
  }

  /**
   * this builds an arraylist of this object type from a document that was built
   * from a xml file, Class.xml, Gear.xml/etc...
   *
   * @param oParent HackSackFrame
   * @param eInformationSheets Element
   * @return ArrayList
   */
  static ArrayList xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {
    ArrayList lList = new ArrayList();

    try {
    Element eRoot = doc.getRootElement();
    Element eSubRoot = eRoot.getChild("InformationSheets");

    java.util.List lItems = eSubRoot.getChildren("Area_Information");

    Iterator in = lItems.iterator();
    while (in.hasNext()) {
      Element eItem = (Element)in.next();
      TableInformation oO = TableInformation.xmlGetFromElements(eItem,oParent);

      lList.add(oO);
    }

    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load area information from XML.");
    }

    return lList;
  }

}

// - delete information button
class jDeleteButton_actionAdapter implements java.awt.event.ActionListener {
  TableInformation adaptee;
  HackSackFrame oParent;

  jDeleteButton_actionAdapter(HackSackFrame oParent, TableInformation adaptee) {
    this.adaptee = adaptee;
    this.oParent = oParent;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jDeleteButton_actionPerformed(e,oParent);
  }
}

