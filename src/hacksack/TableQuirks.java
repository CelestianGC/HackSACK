package hacksack;
import java.awt.*;
import java.util.*;
import org.jdom.*;
/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableQuirks implements  Comparable  {
  String sName = null;
  String sDesc = null;
  int nQuirkID = -1;

  public TableQuirks() {
  }

  public int compareTo(Object o) {
    String sThisName = ((TableQuirks)o).sName.toUpperCase();
    String sComp     = sName.toUpperCase();
    return sComp.compareTo( sThisName );
  }

  static TableQuirks GetQuirkFromName(HackSackFrame oParent, String sName)
    {
      TableQuirks oFound = null;
      boolean bFound = false;
      for (int i=0;i<oParent.lQuirks.size() && !bFound;i++)
      {
        TableQuirks oFind = (TableQuirks)oParent.lQuirks.get(i);
        if (oFind.sName.equalsIgnoreCase(sName))
        {
          oFound = oFind;
          bFound = true;
        }
      }
      return oFound;
    }

    static TableQuirks GetQuirkFromID(HackSackFrame oParent, int nID)
      {
        TableQuirks oFound = null;
        boolean bFound = false;
        for (int i=0;i<oParent.lQuirks.size() && !bFound;i++)
        {
          TableQuirks oFind = (TableQuirks)oParent.lQuirks.get(i);
          if (oFind.nQuirkID == nID)
          {
            oFound = oFind;
            bFound = true;
          }
        }
        return oFound;
      }

      /**
       * this will return a player version of the structure
       *
       * @return Quirks
       */
      Quirks getPlayerStruct() {
        Quirks oQ = new Quirks();

        oQ.sDesc = sDesc;
        oQ.sName = sName;
        oQ.nQuirkID = nQuirkID;

        return oQ;
      }

static void DoNewQuirk(Component oThis,HackSackFrame oParent,
                           TablePlayer oPlayer,
                           Quirks oEditQuirk)
  {
    DialogNewQuirk dlg = new DialogNewQuirk(oParent,oPlayer,oEditQuirk);
    dlg.panel1.setPreferredSize(new Dimension(400,300));
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = oThis.getSize();
    Point loc = oThis.getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
   if (oEditQuirk != null)
     dlg.setTitle("Edit Quirk "+oEditQuirk.sName);
     else
       dlg.setTitle("New Quirk");
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
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
  static TableQuirks xmlGetFromElements(Element eItem) {
    TableQuirks oO = new TableQuirks();

    oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
    oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
    oO.nQuirkID = Integer.parseInt(eItem.getChild("nQuirkID").getText());

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
   Element eRoot = new Element("QuirkList");
   eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
   Document doc = new Document(eRoot);

   for(int i=0;i<lList.size();i++) {

     TableQuirks oO = (TableQuirks)lList.get(i);
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
    boolean bChanged = false;

    try {
    Element eRoot = doc.getRootElement();
    oParent.nMaxQuirksID = Integer.parseInt(eRoot.getChild("nMaxID").getText());

    java.util.List lItems = eRoot.getChildren("Quirk");

    Iterator in = lItems.iterator();
    while (in.hasNext()) {
      Element eItem = (Element)in.next();
      TableQuirks oO = TableQuirks.xmlGetFromElements(eItem);

      if (oO.nQuirkID > oParent.nMaxQuirksID)
        oParent.nMaxQuirksID=(oO.nQuirkID+1);

      TableQuirks oOld = oO.GetQuirkFromID(oParent,oO.nQuirkID);
      if (oOld != null) {
        oO.nQuirkID = (oParent.nMaxQuirksID++);
        oParent.gmLog(false,false,oO.sName+" had duplicate ID as "+oOld.sName+" changed to "+oO.nQuirkID+"\n");
        bChanged = true;
      }

      lList.add(oO);
    }
    if (bChanged) {
      xmlControl.saveDoc(oParent, oParent, TableQuirks.xmlBuildDocFromList(
          lList, oParent.nMaxQuirksID), oParent.sQuirksSaveFile);
    }

    }
    catch (NullPointerException err) {
      oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                        "Error occured while trying to load quirk from XML.");
    }

    return lList;
  }

}
