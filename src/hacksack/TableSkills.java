package hacksack;
import java.awt.*;
import java.util.*;
import org.jdom.*;
//import org.w3c.dom.Element;
//import org.xml.sax.*;
//import org.xml.sax.helpers.*;
////JAXP 1.1
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
//import javax.xml.transform.stream.*;
//import javax.xml.transform.sax.*; 
//import org.xml.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableSkills implements  Comparable  {
  HackSackFrame oParent = null;
  int nSkillID = -1;
  String sName = null;
  String sDesc = null;
  int nSkillType = 0;
  int nSkillRating = 0;

  static int TYPE_SKILL = 0;
  static int TYPE_TALENT = 1;
  static int TYPE_PROF = 2;
  static int TYPE_ABILITY = 3;

//  public TableSkills() {
//  }

  public TableSkills(HackSackFrame oParent) {
    this.oParent = oParent;
  }

   public int compareTo(Object o) {
     String sThisName = ((TableSkills)o).sName.toUpperCase();
     String sComp     = sName.toUpperCase();
     return sComp.compareTo( sThisName );
   }

   static TableSkills GetSkillsFromName(HackSackFrame oParent, String sName)
      {
        TableSkills oFound = null;
        boolean bFound = false;
        for (int i=0;i<oParent.lSkills.size() && !bFound;i++)
        {
          TableSkills oFind = (TableSkills)oParent.lSkills.get(i);
          if (oFind.sName.equalsIgnoreCase(sName))
          {
            oFound = oFind;
            bFound = true;
          }
        }
        return oFound;
      }

      static TableSkills GetSkillsFromID(HackSackFrame oParent, int nSkillID)
         {
           TableSkills oFound = null;
           boolean bFound = false;
           for (int i=0;i<oParent.lSkills.size() && !bFound;i++)
           {
             TableSkills oFind = (TableSkills)oParent.lSkills.get(i);
             if (oFind.nSkillID == nSkillID)
             {
               oFound = oFind;
               bFound = true;
             }
           }
           return oFound;
         }

static void DoNewSkill(Component oThis, HackSackFrame oParent,
                             TablePlayer oPlayer,
                             Skills oEditSkill,
                             int nSkillType)
   {
     DialogNewSkill dlg = new DialogNewSkill(oParent,oPlayer,oEditSkill,nSkillType);
     dlg.panel1.setPreferredSize(new Dimension(500,390));
     Dimension dlgSize = dlg.getPreferredSize();
     Dimension frmSize = oThis.getSize();
     Point loc = oThis.getLocation();
     dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                     (frmSize.height - dlgSize.height) / 2 + loc.y);
    if (oEditSkill != null)
      dlg.setTitle("Edit Skill "+oEditSkill.sName);
      else
        dlg.setTitle("New Skill");
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
     Element eItem= new Element("Skill");

     eItem.addContent(new Element("sName").setText(xmlControl.escapeChars(sName)));
     eItem.addContent(new Element("sDesc").setText(xmlControl.escapeChars(sDesc)));

     eItem.addContent(new Element("nSkillID").setText(""+nSkillID));
     eItem.addContent(new Element("nSkillType").setText(""+nSkillType));
     eItem.addContent(new Element("nSkillRating").setText(""+nSkillRating));

     return eItem;
   }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @param oParent HackSackFrame
   * @return TableClass
   */
  static TableSkills xmlGetFromElements(Element eItem,
                                         HackSackFrame oParent) {
     TableSkills oO = new TableSkills(oParent);

     oO.sName = xmlControl.unEscapeChars(eItem.getChild("sName").getText());
     oO.sDesc = xmlControl.unEscapeChars(eItem.getChild("sDesc").getText());

     oO.nSkillID = Integer.parseInt(eItem.getChild("nSkillID").getText());
     oO.nSkillType = Integer.parseInt(eItem.getChild("nSkillType").getText());
     oO.nSkillRating = Integer.parseInt(eItem.getChild("nSkillRating").getText());
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
    Element eRoot = new Element("SkillList");
    eRoot.addContent(new Element("nMaxID").setText(""+nMaxID));
    Document doc = new Document(eRoot);

    for(int i=0;i<lList.size();i++) {

      TableSkills oO = (TableSkills)lList.get(i);
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
     oParent.nMaxSkillsID = Integer.parseInt(eRoot.getChild("nMaxID").getText());

     java.util.List lItems = eRoot.getChildren("Skill");

     Iterator in = lItems.iterator();
     while (in.hasNext()) {
       Element eItem = (Element)in.next();
       TableSkills oO = TableSkills.xmlGetFromElements(eItem,oParent);

       if (oO.nSkillID > oParent.nMaxSkillsID)
         oParent.nMaxSkillsID=(oO.nSkillID+1);

       TableSkills oOld = oO.GetSkillsFromID(oParent,oO.nSkillID);
       if (oOld != null) {
         oO.nSkillID = (oParent.nMaxSkillsID++);
         oParent.gmLog(false,false,oO.sName+" had duplicate ID as "+oOld.sName+" changed to "+oO.nSkillID+"\n");
         bChanged = true;
       }

       lList.add(oO);
     }

     if (bChanged)
       xmlControl.saveDoc(oParent, oParent, TableSkills.xmlBuildDocFromList(
        lList, oParent.nMaxSkillsID), oParent.sSkillsSaveFile);

     }
     catch (NullPointerException err) {
       oParent.ShowError(oParent,"NullpointerException:"+err.getLocalizedMessage()+"\n"+
                         "Error occured while trying to load skill from XML.");
     }

     return lList;
   }

}
