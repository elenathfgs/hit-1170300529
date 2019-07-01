package physicalobject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import circularorbit.AtomStructure;
import exceptions.SameLabelException;
import org.junit.jupiter.api.Test;
import physicalobject.Electron;
import track.Track;

class ElectronStateTest {
  AtomStructure atom = AtomStructure.creator();

  @Test
  void testElectronState() throws SameLabelException {
    Track track = new Track(3);
    atom.addTrack(track);
    Track track2 = new Track(1);
    atom.addTrack(track2);
    Electron electron = new Electron(1);
    atom.setObjAtTrack(electron, track2);
    Electron temp = (Electron) atom.objIterator().next();
    assertTrue(temp.getLastState().getTrack() == null);
    atom.transit(electron, track);
    assertFalse(temp.getLastState().getTrack() == track2);
  }

}
