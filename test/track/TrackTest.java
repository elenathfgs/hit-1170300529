package track;

import static org.junit.Assert.assertTrue;

import centralobject.Element;
import circularorbit.AtomStructure;
import exceptions.SameLabelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import physicalobject.Electron;

class TrackTest {
  AtomStructure atomStructure = new AtomStructure();
  Track track = new Track(3.0);
  Element element = new Element("Fe");
  Electron electron = new Electron(2);

  @BeforeEach
  void initial() throws SameLabelException {
    atomStructure.addTrack(track);
    atomStructure.addObject(electron);
    atomStructure.setCentralObject(element);
    atomStructure.setObjAtTrack(electron, track);
  }

  @Test
  void testGetRadius() {
    // initial();
    assertTrue(track.getRadius() == 3.0);
  }

  @Test
  void testGetObjInTrack() {
    // initial();
    assertTrue(!track.getObjInTrack().isEmpty());
  }

  @Test
  void testGetObjNum() {
    // initial();
    assertTrue(track.getObjNum() == 1);
  }

  @Test
  void testDeleteObj() {
    // initial();
    track.deleteObj(electron.getlabel());
    assertTrue(track.getObjNum() == 0);
  }

  @Test
  void testHasObject() {
    // initial();
    assertTrue(track.hasObject(electron.getlabel()));
  }

}
