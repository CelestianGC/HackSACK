package hacksack;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableMagicList {
  protected int nIndex = -1;
  protected String sName = null;
  protected String sMagicTable = null;

  public TableMagicList() {
  }

  public TableMagicList(int nThisIndex) {
    this();
    this.nIndex = nThisIndex;
  }
}