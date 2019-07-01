package apis;

import centralobject.CentralObject;
import circularorbit.CircularOrbit;
import physicalobject.PhysicalObject;

public class Helper {
  public static double getObjectDistributionEntropy(
      CircularOrbit<CentralObject, PhysicalObject> c) {
    return CircularOrbitApis.getObjectDistributionEntropy(c);
  }

  public static int getLogicalDistance(CircularOrbit<CentralObject, PhysicalObject> c,
      PhysicalObject e1, PhysicalObject e2) {
    return CircularOrbitApis.getLogicalDistance(c, e1, e2);
  }

  public static double getPhysicalDistance(CircularOrbit<CentralObject, PhysicalObject> c,
      PhysicalObject e1, PhysicalObject e2) {
    return CircularOrbitApis.getPhysicalDistance(c, e1, e2);
  }

  public static Difference getDifference(CircularOrbit<CentralObject, PhysicalObject> c1,
      CircularOrbit<CentralObject, PhysicalObject> c2) {
    return CircularOrbitApis.getDifference(c1, c2);
  }
}
