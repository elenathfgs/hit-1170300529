package circularorbit;

import java.util.Iterator;
import physicalobject.PhysicalObject;
import physicalobject.Planet;

public class StellarSystem extends ConcreteCircularOrbit {
  // mutability: client can add objects to the object set every time they need to add objects, and
  // when a new track is recognized, it will be added to the track's set

  // Safe from REP exposure: use private to decorate the variants thus the clients can not change it
  // through any outer class

  /**.
   * move a certain physicalobject to a new location described by angle
   * 
   * @param planet the planet that are moving
   * @param angle the moving angle of the planet
   */
  public boolean move(Planet planet, double angle) {
    if (this.getObject(planet) == null) {
      return false;
    }
    planet.setAngle(angle);
    return true;
  }

  /**.
   * calculate the angle that a certain planet moved during a period of time
   * 
   * @param planet the planet that are moving
   * @param time the time that the planet moved
   */
  public void moveByTime(Planet planet, double time) {
    double perimeter = 2 * Math.PI * planet.getTrack().getRadius();
    double moveDistance = planet.getVelocity() * time;
    double moveAngle = (moveDistance / perimeter) % 360;
    double resultAngle = planet.getDirection() == true ? (planet.getAngle() + moveAngle) % 360
        : Math.abs((planet.getAngle() - moveAngle) % 360);
    planet.setAngle(resultAngle);
  }

  /**.
   * calculate the distance of two planet in a orbit system
   * 
   * @param a : planet a
   * @param b : planet b
   * @return the distance of a and b in a orbit system
   */
  public double calculateDistance(Planet a, Planet b) {
    double ra = a.getTrack().getRadius();// the track radius of planet a
    double rb = b.getTrack().getRadius();
    double oa = a.getAngle(); // the current angle of planet a
    double ob = b.getAngle();
    double distance = Math.sqrt(ra * ra + rb * rb - 2 * ra * rb * Math.cos(oa - ob));
    return distance;
  }

  /**.
   * give the String type to describe the location of each object
   * 
   * @return the location of the objects represented by a String type
   */
  private String showLocation() {
    Iterator<PhysicalObject> iterator = this.objIterator();
    StringBuffer locationInfo = new StringBuffer();
    while (iterator.hasNext()) {
      Planet planet = (Planet) iterator.next();
      locationInfo.append(planet.getlabel() + "位置 : 轨道半径  " + planet.getTrack().getRadius()
          + " ,  角度   " + planet.getAngle() + "\n");
    }
    return locationInfo.toString();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    String superString = super.toString();
    String thisString = this.showLocation();
    sb.append(superString);
    sb.append("\n");
    sb.append(thisString);
    return sb.toString();
  }
}
