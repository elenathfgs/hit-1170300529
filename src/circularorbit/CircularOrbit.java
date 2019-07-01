package circularorbit;

import centralobject.CentralObject;
import exceptions.SameLabelException;
import java.util.Iterator;
import physicalobject.PhysicalObject;
import track.Track;

public interface CircularOrbit<L, E> {
  /**.
   * add an Physical Object in this system
   * 
   * @param obj the object waiting to be added
   * @return true for succeed
   */
  public boolean addObject(PhysicalObject obj) throws SameLabelException;

  /**.
   * delete a certain Physical Object in this system
   * 
   * @param obj the object waiting to be deleted
   * @return true for succeed
   */
  public boolean deleteObject(PhysicalObject obj);

  /**.
   * get the certain object in this system
   * 
   * @param obj the object to get
   * @return the PhysicalObject, null if not find
   */
  public PhysicalObject getObject(PhysicalObject obj);

  /**.
   * get the certain object which label matches the given label
   * 
   * @param label the label of the object
   * @return the object, null if not find
   */
  public PhysicalObject getObject(String label);

  /**.
   * get the number of objects in this system
   * 
   * @return the number of objects
   */
  public int getObjNum();

  /**.
   * get the iterator of the obj set
   * 
   * @return the iterator
   */
  public Iterator<PhysicalObject> objIterator();

  /**.
   * get a certain track in this System
   * 
   * @param track the track to get
   * @return track you need
   */
  public Track getTrack(Track track);

  /**.
   * get the track in the certain index of the trackList
   * 
   * @param index the track in the list
   * @return the track
   */
  public Track getTrack(int index);

  /**.
   * add a new track into a CircularOrbit system
   * 
   * @param track : the track waiting to be added in the system
   * @return true for successfully created,false for failed
   */
  public boolean addTrack(Track track) throws SameLabelException;

  /**.
   * delete a track in the TrackList
   * 
   * @param track is the track waiting to be deleted
   * @return
   */
  public boolean deleteTrack(Track track);

  /**.
   * get the number of the tracks in this system
   * 
   * @return number of the tracks
   */
  public int getTrackNum();

  /**.
   * set a certain object as the central obj of the system
   * 
   * @return
   */
  public boolean setCentralObject(L centralObject) throws SameLabelException;

  /**.
   * set a certain object at a certain track
   * 
   * @param obj is the object waiting to be set
   * @param track the object is set on which
   * @return
   */
  public boolean setObjAtTrack(E obj, Track track) throws SameLabelException;

  /**.
   * get the number of physicalobject that is in a certain track may need to count the number of
   * objects that each track contains as there may some same objects staying in different tracks
   * 
   * @return the number of total physicalObjects
   */
  public int getObjInTrackNum();

  /**.
   * get the logicalDistance of two physicalobject in a system logicalDistance means how many edges
   * you need to start from one edge to the other one
   * 
   * @param e1 the first object
   * @param e2 the second object
   * @return the number of edges needed , INFINITY if the two objects are not connected , -1 if the
   *         process failed
   */
  public int getLogicalDistance(E e1, E e2);

  /**.
   * get the CentralObject in this system
   * 
   * @return the CentralObject
   */
  public CentralObject getCentralObject();

  /**.
   * give the String form of this system, which means using a String to describe this system
   * 
   * @return the String form of this system
   */
  public String toString();

}
