package apis;

import centralobject.CentralObject;
import circularorbit.AtomStructure;
import circularorbit.CircularOrbit;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import physicalobject.PhysicalObject;
import physicalobject.Planet;

public class CircularOrbitApis {
  /**.
   * count the entropy of the system we use the formula information-entropy to count the entropy of
   * the system which is: sum(P(x)log(P(x))) P(x) refers to the (objects in a certain track)/(all
   * the objects)
   * 
   * @param c the system to be count Entropy
   * @return
   */
  public static double getObjectDistributionEntropy(
      CircularOrbit<CentralObject, PhysicalObject> c) {
    double entropy = 0.0;
    for (int i = 0; i < c.getTrackNum(); i++) {
      double objPortion = (double) c.getTrack(i).getObjNum() / (double) c.getObjNum();
      entropy += objPortion * (Math.log(objPortion) / Math.log(2.0));
    }
    return -entropy;
  }

  /**.
   * get the logical distance of two objects(the length of the road that connect the two objects)
   * 
   * @param c the system two certain objects stays
   * @param e1 the first object
   * @param e2 the second object
   * @return the integer type of distance
   */
  public static int getLogicalDistance(CircularOrbit<CentralObject, PhysicalObject> c,
      PhysicalObject e1, PhysicalObject e2) {
    int distance = c.getLogicalDistance(e1, e2);
    assert distance >= 0 : "计算出的逻辑距离小于0了";
    return distance;
  }

  /**.
   * get the physical distance of two objects(which refers to the true distance)
   * 
   * @param c the system that two objects stays
   * @param e1 the first object
   * @param e2 the second object
   * @return the integer type of physical distance
   */
  public static double getPhysicalDistance(CircularOrbit<CentralObject, PhysicalObject> c,
      PhysicalObject e1, PhysicalObject e2) {
    if (c instanceof StellarSystem) {
      StellarSystem stellarSystem = (StellarSystem) c;
      if (!(e1 instanceof Planet) || !(e2 instanceof Planet)) {
        System.out.println("参数不符合要求");
        return -1;
      } else {
        Planet p1 = (Planet) e1;
        Planet p2 = (Planet) e2;
        double distance = stellarSystem.calculateDistance(p1, p2);
        assert distance > 0 : "物理距离不能小于0";
        return distance;
      }

    } else {
      System.out.println("这个系统没有物体的具体位置，计算物理距离无意义");
      return -1;
    }
  }

  /**.
   * get the difference between two CircularOrbit System, for example: the number of tracks the
   * number and type of objects in each track
   * 
   * @param c1 the first system
   * @param c2 the second system
   * @return a Difference type marks all the differences of the two system
   */
  public static Difference getDifference(CircularOrbit<CentralObject, PhysicalObject> c1,
      CircularOrbit<CentralObject, PhysicalObject> c2) {
    // -------------------------------------Atom
    // Structure---------------------------------------------------------------------
    if (c1 instanceof AtomStructure && c2 instanceof AtomStructure) {
      AtomStructure a1 = (AtomStructure) c1;
      AtomStructure a2 = (AtomStructure) c2;
      int trackNumDiffer = a1.getTrackNum() - a2.getTrackNum();
      Difference difference = new Difference(trackNumDiffer);
      if (trackNumDiffer >= 0) { // a1's number of track is bigger
        for (int i = 0; i < a1.getTrackNum(); i++) {
          if (i < a2.getTrackNum()) {
            difference
                .addObjInTrackNumDiffer(a1.getTrack(i).getObjNum() - a2.getTrack(i).getObjNum());
          } else {
            difference.addObjInTrackNumDiffer(a1.getTrack(i).getObjNum());
          }
        }
        return difference;
      } else {
        for (int i = 0; i < a2.getTrackNum(); i++) {
          if (i < a1.getTrackNum()) {
            difference
                .addObjInTrackNumDiffer(a1.getTrack(i).getObjNum() - a2.getTrack(i).getObjNum());
          } else {
            difference.addObjInTrackNumDiffer(-a2.getTrack(i).getObjNum());
          }
        }
        return difference;
      }
      // ----------------------------------------SocialNetwork Circle && Stellar
      // System-------------------------------------------------------------
    } else if ((c1 instanceof SocialNetworkCircle && c2 instanceof SocialNetworkCircle)
        || (c1 instanceof StellarSystem && c2 instanceof StellarSystem)) {
      int trackNumDiffer = c1.getTrackNum() - c2.getTrackNum();
      Difference difference = new Difference(trackNumDiffer);
      if (trackNumDiffer >= 0) { // c1's number of track is bigger
        for (int i = 0; i < c1.getTrackNum(); i++) {
          if (i < c2.getTrackNum()) {
            difference
                .addObjInTrackNumDiffer(c1.getTrack(i).getObjNum() - c2.getTrack(i).getObjNum());
            difference.addObjDiffer(
                c1.getTrack(i).objSetToString() + " - " + c2.getTrack(i).objSetToString());
          } else {
            difference.addObjInTrackNumDiffer(c1.getTrack(i).getObjNum());
            difference.addObjDiffer(c1.getTrack(i).objSetToString() + " - {}");
          }
        }
        return difference;
      } else {
        for (int i = 0; i < c2.getTrackNum(); i++) {
          if (i < c1.getTrackNum()) {
            difference
                .addObjInTrackNumDiffer(c1.getTrack(i).getObjNum() - c2.getTrack(i).getObjNum());
            difference.addObjDiffer(
                c1.getTrack(i).objSetToString() + " - " + c2.getTrack(i).objSetToString());
          } else {
            difference.addObjInTrackNumDiffer(-c2.getTrack(i).getObjNum());
            difference.addObjDiffer("{} - " + c2.getTrack(i).objSetToString());
          }
        }
        return difference;
      }
    } else {
      System.out.println("这两个系统不能比较");
      return null;
    }

  }

}
