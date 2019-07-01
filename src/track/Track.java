package track;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import physicalobject.PhysicalObject;


public class Track {
  private final double radius;
  // the radius of the track
  // if stands for the atom structure or social graph , then 1.00 refer to the first radius
  // Rep invariant : radius>0
  private final Set<PhysicalObject> objInTrack;
  // the objects in this track
  // RI: should not be null

  // AF : represent a track by its radius and objects

  // immutability : use final to set all the variants and every time client need to change it, they
  // need to create a new PlanetDecorator

  // safe from REP exposure:use final to decorate the variants and the clients can not visit the
  // inner variants through outer classes

  public Track(double radius) {
    this.radius = radius;
    this.objInTrack = new HashSet<PhysicalObject>();
  }

  public void checkRep() {
    assertTrue(radius > 0);
    assertTrue(objInTrack != null);
  }

  public double getRadius() {
    return radius;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Track) {
      Track track = (Track) obj;
      if (track.radius == this.radius) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return (int) radius;
  }

  public Set<PhysicalObject> getObjInTrack() {
    return objInTrack;
  }

  /**.
   * get the number of the objects in this track
   * 
   * @return the number of the objects in this track
   */
  public int getObjNum() {
    return this.getObjInTrack().size();
  }

  /**.
   * check whether this track contains certain object
   * @param label the label of the object
   * @return if has->true, not -> false
   */
  public boolean hasObject(String label) {
    Iterator<PhysicalObject> iterator = this.getObjInTrack().iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getlabel().equals(label)) {
        return true;
      }
    }
    return false;
  }

  /**.
   * get the string form of the object set
   * 
   * @return objects' set string
   */
  public String objSetToString() {
    StringBuffer stringBuffer = new StringBuffer();
    Iterator<PhysicalObject> objIterator = objInTrack.iterator();
    stringBuffer.append("{");
    while (objIterator.hasNext()) {
      stringBuffer.append(objIterator.next().getlabel() + ",");
    }
    stringBuffer.deleteCharAt(stringBuffer.length() - 1);// delete the last ","
    stringBuffer.append("}");
    return stringBuffer.toString();
  }

  /**.
   * delete certain object in this track
   * @param label the label of the object
   * @return true if succeed
   */
  public boolean deleteObj(String label) {
    for (PhysicalObject physicalObject : objInTrack) {
      if (physicalObject.getlabel().equals(label)) {
        objInTrack.remove(physicalObject);
        return true;
      }
    }
    return false;
  }

}


