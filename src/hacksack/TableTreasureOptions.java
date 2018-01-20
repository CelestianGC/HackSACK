package hacksack;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableTreasureOptions {
  public JCheckBox jType = null;
  public JSpinner jMultiplier = null;

  public TableTreasureOptions() {
    jType = new JCheckBox ();
    SpinnerNumberModel mMultiplier = new SpinnerNumberModel(1,1,100,1);
    jMultiplier = new JSpinner(mMultiplier);
  }

  public TableTreasureOptions(String sThisName) {
    this();
    this.jType.setText(sThisName);
  }

}
