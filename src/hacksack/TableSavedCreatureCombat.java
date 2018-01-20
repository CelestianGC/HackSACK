package hacksack;
import org.jdom.*;

/**
 * <p>Title: HackSACK</p>
 * <p>Description: HackMaster GM Tool</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Mike Wilson</p>
 * @author uce_mike@yahoo.com
 * @version 1.0
 */

public class TableSavedCreatureCombat {
  public int nWeaponType = 0;
  public int nToHitMod = 0;
  public String sDamageDice = null;
  public int nTotalMod = 0;
  public int nModPenetration = 0;
  public int nModFumble = 0;
  public int nModCrit = 0;

  public TableSavedCreatureCombat() {
  }

  /**
   * this returns an element of this class used to place into a doc
   * and save as xml
   *
   * @return Element
   */
  Element xmlGetElements() {
    Element eItem = new Element("Attack");

    eItem.addContent(new Element("nWeaponType").setText(""+nWeaponType));
    eItem.addContent(new Element("sDamageDice").setText(sDamageDice));
    eItem.addContent(new Element("nToHitMod").setText(""+nToHitMod));
    eItem.addContent(new Element("nTotalMod").setText(""+nTotalMod));
    eItem.addContent(new Element("nModCrit").setText(""+nModCrit));
    eItem.addContent(new Element("nModFumble").setText(""+nModFumble));
    eItem.addContent(new Element("nModPenetration").setText(""+nModPenetration));

    return eItem;
  }

  /**
   * this gets an class from a element
   *
   * @param eItem Element
   * @return TableClass
   */
  static TableSavedCreatureCombat xmlGetFromElements(Element eItem) {
    TableSavedCreatureCombat oO = new TableSavedCreatureCombat();

    oO.nWeaponType = Integer.parseInt(eItem.getChild("nWeaponType").getText());
    oO.sDamageDice = eItem.getChild("sDamageDice").getText();
    oO.nToHitMod = Integer.parseInt(eItem.getChild("nToHitMod").getText());
    oO.nTotalMod = Integer.parseInt(eItem.getChild("nTotalMod").getText());
    oO.nModCrit = Integer.parseInt(eItem.getChild("nModCrit").getText());
    oO.nModFumble = Integer.parseInt(eItem.getChild("nModFumble").getText());
    oO.nModPenetration = Integer.parseInt(eItem.getChild("nModPenetration").getText());

    return oO;
  }


}
