package hacksack;
import java.util.*;
import java.awt.*;
import org.jdom.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableRandomName implements  Comparable  {
  String sRace = null;
  String sName = null;
  boolean bLast = true;
  boolean bMale = true;
  boolean bFemale = true;

  public int compareTo(Object o) {
    String sThisName = ((TableRandomName)o).sName.toUpperCase();
    String sComp     = sName.toUpperCase();
    return sComp.compareTo( sThisName );
  }

  public TableRandomName() {
  }

// count the names for this race/sec
  static int NumberOfThisRaceNames(HackSackFrame oParent,
                              String sRace,boolean bMale, boolean bFemale,
                              boolean bLast)
  {
    int nCount = 0;

    for (int i=0;i<oParent.lRandomNames.size();i++)
    {
      TableRandomName oR = (TableRandomName)oParent.lRandomNames.get(i);
      if (sRace.equalsIgnoreCase(oR.sRace) && (bLast == oR.bLast) &&
          ( (bMale && oR.bMale) ||
            (bFemale && oR.bFemale) ) )
        nCount++;
    }
    return nCount;
  }

  static String GetThisRaceName(HackSackFrame oParent,
                                int nNumber, String sRace,
                                boolean bMale, boolean bFemale,
                                boolean bLast)
  {
    String sReturn = null;
    int nCount = 0;
    for (int i=0;i<oParent.lRandomNames.size();i++)
    {
      TableRandomName oR = (TableRandomName)oParent.lRandomNames.get(i);
      if (sRace.equalsIgnoreCase(oR.sRace) && (bLast == oR.bLast) &&
          ( (bMale && oR.bMale) ||
            (bFemale && oR.bFemale)) )
      {
        nCount++;
        if (nCount == nNumber)
        {
          sReturn = oR.sName;
        }
      }
    }

    return sReturn;
  }

  static void GenerateRandomName(HackSackFrame oParent)
  {
    boolean bMale = oParent.jGMRandomNameMaleCheckBox.isSelected();
    boolean bFemale = oParent.jGMRandomNameFemaleCheckBox.isSelected();
    String sRace = (String)oParent.jGMRandomNameRaceComboBox.getSelectedItem();
    int nMaxNames = NumberOfThisRaceNames(oParent,sRace,bMale,bFemale,false);
    String sRandomFirstName = GetThisRaceName(oParent,oParent.MyRandom(nMaxNames),
                                         sRace,bMale,bFemale,false);

    nMaxNames = NumberOfThisRaceNames(oParent,sRace,bMale,bFemale,true);
    String sRandomLastName = GetThisRaceName(oParent,oParent.MyRandom(nMaxNames),
                                         sRace,bMale,bFemale,true);

    String sRandomName = sRandomFirstName+" "+sRandomLastName;

    oParent.gmLog(true,false,"Random Name Generated:"+sRandomName+"\n");
    oParent.jRandomNameResultsTextArea.append("Random Name Generated:"+sRandomName+"\n");
/*
    DialogDetails dlg = new DialogDetails(sRandomName,"Random Name");
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

  static void RebuildSelectIndex_jGMRandomNameRaceComboBox(HackSackFrame oParent) {

    oParent.jGMRandomNameRaceComboBox.removeAllItems();
    ArrayList lRaces = new ArrayList();
    for (int i = 0; i < oParent.lRandomNames.size(); i++) {
      TableRandomName oR = (TableRandomName) oParent.lRandomNames.get(i);
      boolean bFound = false;
      for (int j = 0; j < lRaces.size() && !bFound; j++) { // lets see if we've listed this race already
        String sRace = (String) lRaces.get(j);
        if (sRace.equalsIgnoreCase(oR.sRace))
          bFound = true;
      }
      if (!bFound) {
        lRaces.add(oR.sRace);
        oParent.jGMRandomNameRaceComboBox.addItem(oR.sRace);
      }
    }

  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem= new Element("RandomName");

    eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
    eItem.addContent(new Element("sRace").setText(xmlControl.escapeChars(sRace)));
    eItem.addContent(new Element("bLast").setText(bLast?"true":"false"));
    eItem.addContent(new Element("bFemale").setText(bFemale?"true":"false"));
    eItem.addContent(new Element("bMale").setText(bMale?"true":"false"));


    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static TableRandomName xmlGetFromElements(Element eItem) {
    TableRandomName oO = new TableRandomName();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sRace = xmlControl.unEscapeChars(eItem.getChild("sRace").getText());
    oO.bLast = eItem.getChild("bLast").getText().equalsIgnoreCase("true");
    oO.bFemale = eItem.getChild("bFemale").getText().equalsIgnoreCase("true");
    oO.bMale = eItem.getChild("bMale").getText().equalsIgnoreCase("true");

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
   Element eRoot = new Element("RandomNamesList");
   eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
   Document doc = new Document(eRoot);

   for(int i=0;i<lList.size();i++) {

     TableRandomName oO = (TableRandomName)lList.get(i);
     eRoot.addContent(oO.xmlGetElements());

   }

   return doc;
  }

  /**
   * this builds an arraylist of this object type from a document that was
   * built from a xml file, Class.xml, Gear.xml/etc...
   *
   * @param oParent HackSackFrame
   * @param doc Document
   * @return ArrayList
   */
  static ArrayList xmlGetSavedFromDoc(HackSackFrame oParent, Document doc) {
    ArrayList lList = new ArrayList();

    try {
    Element eRoot = doc.getRootElement();
    oParent.nMaxRandomNames = Integer.parseInt(eRoot.getChild("nMaxID").getText());

    java.util.List lItems = eRoot.getChildren("RandomName");

    Iterator in = lItems.iterator();
    while (in.hasNext()) {
      Element eItem = (Element)in.next();
      TableRandomName oO = TableRandomName.xmlGetFromElements(eItem);

//      if (oO.nCreatureID > oParent.nMaxCreatureID)
//        oParent.nMaxClassID=(oO.nCreatureID+1);

      lList.add(oO);
    }

    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load random names from XML.");
    }

    return lList;
  }


}
