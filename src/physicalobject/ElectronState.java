package physicalobject;

import static org.junit.Assert.assertTrue;

import track.Track;

public class ElectronState implements PhysicalObject {
  private double lastAngle;
  // Rep Invariant: should be <=360 and >=0
  private Track lastTrack;
  // Rep Invariant: shoud be a track type
  private String num;
  // Rep Invariant: should be a int type

  // Abstraction Function: represent the state of an electron by its last angle and track that the
  // object stays
  /**.
   * constructors
   * @param lastElectron the electron which state need to be recorded
   */
  public ElectronState(Electron lastElectron) {
    this.lastAngle = lastElectron.getAngle();
    if (lastElectron.getTrack() != null) {
      this.lastTrack = new Track(lastElectron.getTrack().getRadius());
      int i = 1;
      while (lastTrack.getObjInTrack().iterator().hasNext()) {
        lastTrack.getObjInTrack().add(new Electron(i));
        i++;
      }
    }
    this.num = lastElectron.getlabel();

  }

  /**.
   * check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(lastAngle >= 0 && lastAngle <= 360);
  }

  @Override
  public String getlabel() {
    return this.num;
  }

  @Override
  public Track getTrack() {
    return this.lastTrack;
  }

  @Override
  public void setTrack(Track track) {
    System.out.println("you can not change the fomer state");
  }

  @Override
  public double getAngle() {
    return this.lastAngle;
  }

  @Override
  public void setAngle(double angle) {
    System.out.println("you can not change the fomer state");

  }

}
