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

public class TableClass implements  Comparable  {
  String sName = null;
  String sDesc = null;
  int nSaveAs = 0;
  int nFightAs = 0;
  int nClassID = -1;

  public TableClass() {
  }

  public int compareTo(Object o) {
     String sThisName = ((TableClass)o).sName.toUpperCase();
     String sComp     = sName.toUpperCase();
     return sComp.compareTo( sThisName );
   }

   TableClass GetClassFromID(HackSackFrame oParent, int nID)
   {
     TableClass oClass = null;
     boolean bFound = false;
     for (int i=0;i<oParent.lClass.size() && !bFound;i++)
     {
       TableClass oFind = (TableClass)oParent.lClass.get(i);
       if (oFind.nClassID == nID)
       {
         oClass = oFind;
         bFound = true;
       }
     }
     return oClass;
   }

   /**
    * this returns an element of this class used to place into a doc
    * and save as xml
    *
    * @return Element
    */
   Element xmlGetElements() {
     Element eClass = new Element("Class");

     eClass.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
     eClass.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));
     eClass.addContent(new Element("nFightAs").setText(""+nFightAs));
     eClass.addContent(new Element("nSaveAs").setText(""+nSaveAs));
     eClass.addContent(new Element("nClassID").setText(""+nClassID));

     return eClass;
   }

   /**
    * this gets an class from a element
    *
    * @param eItem Element
    * @return TableClass
    */
   static TableClass xmlGetFromElements(Element eItem) {
     TableClass oClass = new TableClass();

     oClass.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
     oClass.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());
     oClass.nClassID = Integer.parseInt(eItem.getChild("nClassID").getText());
     oClass.nFightAs = Integer.parseInt(eItem.getChild("nFightAs").getText());
     oClass.nSaveAs = Integer.parseInt(eItem.getChild("nSaveAs").getText());

     return oClass;
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
    Element eRoot = new Element("ClassList");
    eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
    Document doc = new Document(eRoot);

    for(int i=0;i<lList.size();i++) {

      TableClass oClass = (TableClass)lList.get(i);
      eRoot.addContent(oClass.xmlGetElements());

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
       oParent.nMaxClassID = Integer.parseInt(eRoot.getChild("nMaxID").getText());

       // get all the elements called "<Class>..</Class>
       java.util.List lItems = eRoot.getChildren("Class");

       Iterator in = lItems.iterator();
       while (in.hasNext()) {
         Element eItem = (Element) in.next();
         TableClass oClass = TableClass.xmlGetFromElements(eItem);

         if (oClass.nClassID > oParent.nMaxClassID)
           oParent.nMaxClassID = (oClass.nClassID + 1);

         lList.add(oClass);
       }
     }
     catch (NullPointerException err) {
       oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                         "Error occured while trying to load class from XML.");
     }
     return lList;
   }

   // make a new class
   static void DoNewClass(Component oThis, HackSackFrame oParent,
                          TablePlayer oPlayer,
                          TableClass oClass)
   {
     DialogNewClass dlg = new DialogNewClass(oParent,oPlayer,oClass);
     dlg.panel1.setPreferredSize(new Dimension(400,300));
     Dimension dlgSize = dlg.getPreferredSize();
     Dimension frmSize = oThis.getSize();
     Point loc = oThis.getLocation();
     dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                     (frmSize.height - dlgSize.height) / 2 + loc.y);
     dlg.setTitle("Add Class & Level");
     dlg.setModal(true);
     dlg.pack();
     dlg.setVisible(true);

   }
}
