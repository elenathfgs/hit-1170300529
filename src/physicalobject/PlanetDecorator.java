package physicalobject;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import track.Track;

public class PlanetDecorator implements PhysicalObject {
  protected Planet input;
  // the planet that this decorator decorates
  // Rep Invariant: should not be null
  private final Set<String> satellites = new HashSet<String>();
  // Rep Invariant: should be String type

  // Abstraction Function: represent some decorations of the planet

  // immutability : use final to set all the variants and every time client need to change it, they
  // need to create a new PlanetDecorator

  // safe from REP exposure:use final to decorate the variants and the clients can not visit the
  // inner variants through outer classes
  // constructors
  public PlanetDecorator(Planet input) {
    this.input = input;
  }

  /**.
   * check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(input != null);
  }

  /**.
   * add a satellite in this planet
   * @param satellite the satellite to be added
   * @return true if succeed
   */
  public boolean addSatellite(String satellite) {
    if (this.satellites.contains(satellite)) {
      return false;
    } else {
      this.satellites.add(satellite);
      return true;
    }
  }

  public Set<String> getSatellites() {
    return satellites;
  }

  @Override
  public String getlabel() {
    return this.input.getlabel();
  }

  @Override
  public Track getTrack() {
    return input.getTrack();
  }

  @Override
  public void setTrack(Track track) {
    input.setTrack(track);
  }

  @Override
  public double getAngle() {
    return input.getAngle();
  }

  @Override
  public void setAngle(double angle) {
    input.setAngle(angle);

  }

}
