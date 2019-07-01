package circularorbit;

import static org.junit.Assert.assertTrue;

import circularorbit.AtomStructure;
import exceptions.SameLabelException;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import physicalobject.Electron;
import physicalobject.PhysicalObject;
import track.Track;

class ConcreteCircularOrbitTest {
  AtomStructure atom = AtomStructure.creator();

  @BeforeEach
  void init() throws SameLabelException {
    Track track = new Track(3);
    atom.addTrack(track);
    Track track2 = new Track(1);
    atom.addTrack(track2);
    Electron electron = new Electron(1);
    atom.setObjAtTrack(electron, track2);
  }


  @Test
  void testDeleteObjectString() {
    assertTrue(atom.getObject("1") != null);
    atom.deleteObject("1");
    assertTrue(atom.getObject("1") == null);
  }

  @Test
  void testObjIterator() {
    Iterator<PhysicalObject> iterator = atom.getObjectIterator();
    assertTrue(iterator.hasNext());
    assertTrue(iterator.next().getlabel().equals("1"));
  }

  @Test
  void testDeleteTrackTrack() {
    assertTrue(atom.getTrackNum() == 2);
    atom.deleteTrack(new Track(3));
    assertTrue(atom.getTrackNum() == 1);
  }

  @Test
  void testDeleteTrackInt() {
    assertTrue(atom.getTrackNum() == 2);
    atom.deleteTrack(0);
    assertTrue(atom.getTrackNum() == 1);
  }

}
