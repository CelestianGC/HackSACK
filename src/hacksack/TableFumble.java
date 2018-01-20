package hacksack;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableFumble {
  public  int nRoll =-1;

  public String sTable = null;
  public String sText = null;

  public TableFumble() {

  }


  //
  public TableFumble(int nThisRoll, String sThisText, String sThisTable) {
    this();
    this.nRoll= nThisRoll;
    this.sText = sThisText;
    this.sTable = sThisTable;
  }

}