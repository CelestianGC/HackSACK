package hacksack;

import java.io.*;
import java.awt.*;

//import org.w3c.*;
//import org.w3c.dom.Document;
//
//import javax.xml.*;
//import org.xml.*;
	
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003,2004</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class xmlControl {

  public xmlControl() {
  }

  /**
   * unescape characters we escaped in XML
   *
   * @param sEscapeThis String
   * @return String
   */
  static String unEscapeChars(String sEscapeThis) {
    if (sEscapeThis != null) {
      sEscapeThis = sEscapeThis.replaceAll("\\\\n", "\n");
      sEscapeThis = sEscapeThis.replaceAll("\\\\r", "\r");

      sEscapeThis = sEscapeThis.replaceAll("\\&amp;","&");
      sEscapeThis = sEscapeThis.replaceAll("\\&apos;","'");
      sEscapeThis = sEscapeThis.replaceAll("\\&lt;","<");
      sEscapeThis = sEscapeThis.replaceAll("\\&gt;",">");
      sEscapeThis = sEscapeThis.replaceAll("\\&quot;","\"");
    }

    return sEscapeThis;
  }

  /**
   * fix escape characters for XML
   *
   * @param sEscapeThis String
   * @return String
   */
  static String escapeChars(String sEscapeThis) {
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

  
  // save the document to filename
  static void saveDoc(HackSackFrame oParent, Component oComponent,
                      Document doc, String sFileName) {
    try {
      FileOutputStream oOut = new FileOutputStream(sFileName);
      XMLOutputter serializer = new XMLOutputter();
      serializer.getFormat().setIndent("  ");
      serializer.getFormat().setTextMode(serializer.getFormat().getTextMode().
                                         NORMALIZE);
//            fFormat.setExpandEmptyElements(true);
      serializer.output(doc, oOut);
      oOut.close();
    }
    catch (IOException err) {
      oParent.ShowError(oComponent,sFileName+" caused IOException\n"+err.getMessage()+"\n");
    }

  }

  // load a xml document and return it as a doc
  static Document loadDoc(HackSackFrame oParent, Component oComponent,
                          String sFileName) {
    Document doc = null;
    try {
      SAXBuilder builder = new SAXBuilder();
      doc = builder.build(sFileName);
    }
    catch (IOException err) {
      oParent.ShowError(oComponent,sFileName+" caused IOException\n"+err.getMessage()+"\n");
    }
    catch (JDOMException err) {
      oParent.ShowError(oComponent,sFileName+" caused JDOMException\n"+err.getMessage()+"\n");
    }

    return doc;
  }

}
