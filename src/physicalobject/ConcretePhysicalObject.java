package physicalobject;

import static org.junit.Assert.assertTrue;

import track.Track;

public abstract class ConcretePhysicalObject implements PhysicalObject {
  protected String label;
  //the label of a certain object
  protected Track track;
  // the track that the Object stays
  // Rep invariant: should not be null
  // Abstraction function: AF(Track) = {track | all tracks belongs to this system}
  protected double angle;
  // the angle of the Object
  // some object doesn't need angle, set this is for the convenience of draw images
  // Rep invariant: 0<=angle<=360
  protected double size = 30;
  // the default size of all the concrete Physical Object
  // used to draw picture
  protected double speed = 0.5;
  // the default speed of this object
  // used to draw picture;
  // Rep invariant: 0<=speed<=360

  // Abstraction function: represent the location and some characristic of this object

  // immutability : use protected to set all the variants and every time client need to change it,
  // they need to create a new ConcretePhysicalObject

  // safe from REP exposure: the clients can not visit the inner variants through outer classes

  // constructors
  public ConcretePhysicalObject() {
    setAngle(0);
  }

  public ConcretePhysicalObject(Track track, double angle) {
    this.setTrack(track);
    this.setAngle(angle);
  }


  /**.
   * check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(track != null);
    assertTrue(angle >= 0 && angle <= 360);
  }

  // --------------------------getter and setters----------------------------------
  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }

  public double getSize() {
    return this.size;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ConcretePhysicalObject) {
      ConcretePhysicalObject concretePhysicalObject = (ConcretePhysicalObject) obj;
      if (concretePhysicalObject.getlabel().equals(this.label)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return this.label.hashCode();
  }



}
