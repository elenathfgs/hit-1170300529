package apis;

import java.util.ArrayList;
import java.util.List;

public class Difference {
  private final int trackNumDiffer;
  private final List<Integer> objInTrackNumDifferList = new ArrayList<Integer>();
  private final List<String> objDifferList = new ArrayList<String>();

  public Difference(int trackNumDiffer) {
    this.trackNumDiffer = trackNumDiffer;
  }

  public int getTrackNumDiffer() {
    return trackNumDiffer;
  }

  public boolean addObjInTrackNumDiffer(int numDiffer) {
    return objInTrackNumDifferList.add(numDiffer);
  }

  public boolean addObjDiffer(String objDiffer) {
    return objDifferList.add(objDiffer);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("轨道数差异: " + trackNumDiffer);
    sb.append("\n");
    for (int i = 0; i < objInTrackNumDifferList.size(); i++) {
      if (i < objDifferList.size()) {
        sb.append("轨道" + (i + 1) + "的物体数量差异" + objInTrackNumDifferList.get(i) + ";  " + "物体差异："
            + objDifferList.get(i) + ";");
      } else {
        sb.append("轨道" + (i + 1) + "的物体数量差异" + objInTrackNumDifferList.get(i) + ";");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
