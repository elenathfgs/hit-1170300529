package physicalobject;

import track.Track;

public interface PhysicalObject {
  /**.
   * get the track that the PhysicalObject stays
   * 
   * @return the track that the PhysicalObject stays
   */
  public Track getTrack();

  /**.
   * set the track the PhysicalObject stays
   * 
   */
  public void setTrack(Track track);

  /**.
   * get the angle
   * 
   * @return the angle
   */
  public double getAngle();

  /**.
   * set the current angle
   * 
   * @param angle the angle to describe position
   */
  public void setAngle(double angle);

  /**.
   * get the label of the object, which only refer to one certain object
   * 
   * @return the label of the object
   */
  public String getlabel();

}
