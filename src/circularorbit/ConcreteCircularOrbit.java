package circularorbit;

import static org.junit.Assert.assertTrue;

import centralobject.CentralObject;
import exceptions.SameLabelException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logger.MyLogger;
import physicalobject.PhysicalObject;
import track.Track;


public abstract class ConcreteCircularOrbit
    implements CircularOrbit<CentralObject, PhysicalObject> {
  static final int MAX = 2147483647;
  // if there is no road between two objects, then the distance is MAX

  private CentralObject centralObject;
  // the centralobject in this system


  private final Set<PhysicalObject> objects = new HashSet<PhysicalObject>();
  // the objects in this system

  private final List<Track> tracks = new ArrayList<Track>();
  // the tracks in this system
  // RI: should be Track type, CentralObject should not be null, objects should be PhysicalObject
  // type
  // AF: represent a ConcreteCircularOrbit by the tracks in it, the centralobject of it, the objects
  // it has
  // mutability: client can add objects to the object set every time they need to add objects, and
  // when a new track is recognized, it will be added to the track's set
  // Safe from REP exposure: use private to decorate the variants thus the clients can not change it
  // through any outer class
  private final Map<Double,Track> findTrack = new HashMap<Double, Track>();

  /**.
   * check the REP invariant of this class every ConcreteCircularOrbit should own a certain central
   * object should have two tracks which radius are same
   */
  public void checkRep() {
    assertTrue(centralObject != null);
    for (int i = 0; i < tracks.size(); i++) {
      Track track1 = tracks.get(i);
      for (int j = 0; j < tracks.size(); j++) {
        Track track2 = tracks.get(j);
        assertTrue(track1.getRadius() != track2.getRadius());
      }
    }
  }

  /**.
   * safe from REP exposure:the client can not get the set and list, they must use the methods below
   * to operate the target REP invariant:the centralobject should not be null
   * 
   * @throws SameLabelException SameLabelException
   */

  @Override
  public boolean addObject(PhysicalObject obj) throws SameLabelException {
//    if (this.objects.contains(obj)) {
//      throw new SameLabelException(obj.getlabel());
//    }
//    // **Defensive Programming**

    return this.objects.add(obj);
  }

  @Override
  public PhysicalObject getObject(PhysicalObject obj) {
    Iterator<PhysicalObject> iterator = objects.iterator();
    while (iterator.hasNext()) {
      PhysicalObject physicalObject = iterator.next();
      if (physicalObject.equals(obj)) {
        return physicalObject;
      }
    }
    return null;
  }

  @Override
  public PhysicalObject getObject(String label) {
    Iterator<PhysicalObject> iterator = objects.iterator();
    while (iterator.hasNext()) {
      PhysicalObject physicalObject = iterator.next();
      if (physicalObject.getlabel().equals(label)) {
        return physicalObject;
      }
    }
    return null;
  }

  @Override
  public boolean deleteObject(PhysicalObject obj) {
    obj.getTrack().getObjInTrack().remove(obj);// delete this object from its own track
    return this.objects.remove(obj);
  }


  /**.
   * delete a object
   * @param label the label of the object that need to be deleted
   * @return
   */
  public boolean deleteObject(String label) {
    for (PhysicalObject object : objects) {
      if (object.getlabel().equals(label)) {
        return deleteObject(object);
      }
    }
    return false;
  }

  @Override
  public int getObjNum() {
    return objects.size();
  }

  @Override
  public int getObjInTrackNum() {
    int totalNum = 0;
    for (int i = 0; i < tracks.size(); i++) {
      totalNum += tracks.get(i).getObjInTrack().size();
    }
    return totalNum;
  }

  @Override
  public Iterator<PhysicalObject> objIterator() {
    return objects.iterator();
  }

  @Override
  public boolean addTrack(Track track) throws SameLabelException {
//    int index = tracks.size() - 1;
//    if (tracks.isEmpty()) {
//      tracks.add(track);
//    } else { // add the track at the proper location(radius order match the index order)
//      while (index >= 0 && track.getRadius() <= tracks.get(index).getRadius()) {
//        index--;
//      }
//      tracks.add(index + 1, track);
//    }
    //judge whether this system contains the track
    if (findTrack.containsKey(track.getRadius())) {
      return false;
    }
    findTrack.put(track.getRadius(), track);
    return tracks.add(track);
  }

  @Override
  public Track getTrack(Track track) {
    return findTrack.get(track.getRadius());
  }
  
  public Track getTrack(double radius) {
    return findTrack.get(radius);
  }

  @Override
  public Track getTrack(int index) {
//    if (index > tracks.size() - 1 || index < 0) {
//      System.out.println("下标错误");
//      return null;
//    }
//    // **Defensive Programming**

    return tracks.get(index);
  }

  @Override
  public int getTrackNum() {
    return tracks.size();
  }

  @Override
  public boolean setObjAtTrack(PhysicalObject obj, Track track) throws SameLabelException {
    if (!this.tracks.contains(track)) {
      try {
        this.addTrack(track);
      } catch (SameLabelException e) {
        // TODO Auto-generated catch block
        MyLogger._LOG.warning(e.getFeedback());
        e.printStackTrace();
        return false;
      }
    }
    // **Defensive Programming**
    this.addObject(obj);
    track = this.getTrack(track);
    obj.setTrack(track);
    track.getObjInTrack().add(obj);
    return true;
  }

  @Override
  public boolean setCentralObject(CentralObject centralObject) throws SameLabelException {
    this.centralObject = centralObject;
    return true;
  }

  @Override
  public boolean deleteTrack(Track track) {
//    assert tracks.contains(track) : "没有这条轨道";
//    // **Defensive Programming**

    while (!track.getObjInTrack().isEmpty()) {
      Iterator<PhysicalObject> deleteIterator = track.getObjInTrack().iterator();
      while (deleteIterator.hasNext()) {
        PhysicalObject temp = deleteIterator.next();
        temp.setTrack(null);
        track.getObjInTrack().remove(temp);
      }
    }
    tracks.remove(track);
    return true;
  }

  /**.
   * delete a certain track
   * @param index the index of the track
   * @return
   */
  public boolean deleteTrack(int index) {
    assert index >= 0 && index <= tracks.size() - 1 : "删除的轨道的序号不合法";
    // **Defensive Programming**

    Track track = tracks.get(index);
    if (!tracks.contains(track)) {
      return false;
    }
    if (!track.getObjInTrack().isEmpty()) {
      track.getObjInTrack().removeAll(objects);
    }
    tracks.remove(index);
    return true;
  }

  @Override
  /**
   * because most of the system has no relations of their objects, the ordinary result is INFINITY
   */
  public int getLogicalDistance(PhysicalObject e1, PhysicalObject e2) {
    return MAX;
  }

  @Override
  public CentralObject getCentralObject() {
    return centralObject;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("中心点物体: " + this.getCentralObject().getLabel() + "\n");
    for (int i = 0; i < this.getTrackNum(); i++) {
      Track track = this.getTrack(i);
      sb.append("第" + (i + 1) + "条轨道：半径" + track.getRadius() + "  物体: { ");
      for (PhysicalObject physicalObject : track.getObjInTrack()) {
        sb.append(physicalObject.getlabel() + " ");
      }
      sb.append("}");
      sb.append("\n");
    }
    return sb.toString();
  }

  /**.
   * show the basic information of this system
   */
  public void show() {
    System.out.println(this.toString());
  }

  /**.
   * return this inner ObjectIterator
   */
  public ObjectIterator getObjectIterator() {
    return new ObjectIterator();
  }

  /**.
   * the Iterator that can go through all the objects in this system following some certain rules
   * 
   * @author Administrator
   *
   */
  class ObjectIterator implements Iterator<PhysicalObject> {
    List<PhysicalObject> list = new ArrayList<PhysicalObject>();
    int size = 0;
    int currentIndex = 0;

    public ObjectIterator() {
      for (int i = 0; i < tracks.size(); i++) {
        for (PhysicalObject physicalObject : tracks.get(i).getObjInTrack()) {
          if (list.size() == 0) {
            list.add(physicalObject);
          } else {
            int j = 0;
            while (physicalObject.getAngle() > list.get(j).getAngle()) {
              j++;
            }
            list.add(physicalObject);
          }
        }
      }
      size = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
      if (currentIndex <= size) {
        return true;
      }
      return false;
    }

    @Override
    public PhysicalObject next() {
      PhysicalObject physicalObject = list.get(currentIndex);
      currentIndex++;
      return physicalObject;
    }

  }

}
