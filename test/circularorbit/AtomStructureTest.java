package circularorbit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.AtomStructure;
import exceptions.ElementNameException;
import exceptions.NumOfTrackUnmatch;
import exceptions.SameLabelException;
import io.CircularBufferedReader;
import io.CircularChannelReader;
import io.CircularIO;
import io.SystemFactory;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

import physicalobject.Electron;
import track.Track;

public class AtomStructureTest {
  AtomStructure atom = AtomStructure.creator();

  @Test
  public void testAddTrack() throws SameLabelException {
    Track track = new Track(3);
    atom.addTrack(track);
    assertTrue(atom.getTrackNum() != 0);
    Track track2 = new Track(1);
    atom.addTrack(track2);
    assertTrue(atom.getTrack(0).getRadius() == 1);
  }

  @Test
  public void testCreator() {
    AtomStructure atom = AtomStructure.creator();
    assertTrue(atom.getObjNum() == 0);
  }

  @Test
  public void testSetObjAtTrack() throws SameLabelException {
    Track track = new Track(3);
    Electron electron = new Electron(1);
    atom.addTrack(track);
    atom.setObjAtTrack(electron, atom.getTrack(track));
    assertTrue(electron.getTrack().equals(track));
    assertTrue(atom.getObject(electron) != null);
    assertTrue(atom.getTrack(track).getObjInTrack().contains(electron));
  }

  @Test
  public void testReadFileCreator() throws Exception {
    File atomFile1 = new File("src/../standard_input/AtomicStructure.txt");
    AtomStructure fileAtom;
    try {
      fileAtom = SystemFactory.generateAtomStructure(
          CircularIO.readFile(new CircularChannelReader(), atomFile1));
    } catch (ElementNameException e) {
      System.out.println(e.getFeedBack());
      e.printStackTrace();
      return;
    }
    assertTrue(fileAtom.getCentralObject().getLabel().equals("Rb"));
    assertTrue(fileAtom.getTrackNum() == 5);
    assertTrue(fileAtom.getTrack(new Track(4)).getObjInTrack().size() == 8);
    assertTrue(fileAtom.getObjInTrackNum() == 37);
  }

  @Test
  public void testDeleteTrack() throws SameLabelException {
    Track track = new Track(3);
    atom.addTrack(track);
    assertTrue(atom.getTrackNum() != 0);
    Track track2 = new Track(1);
    atom.addTrack(track2);
    assertTrue(atom.getTrackNum() == 2);
    atom.deleteTrack(track2);
    assertTrue(atom.getTrack(track2) == null);
  }

  @Test
  public void testTransit() throws SameLabelException {
    Track track = new Track(3);
    atom.addTrack(track);
    Track track2 = new Track(1);
    atom.addTrack(track2);
    Electron electron = new Electron(1);
    atom.setObjAtTrack(electron, track2);
    assertTrue(atom.getTrack(track2).getObjInTrack().contains(electron));
    atom.transit(electron, track);
    assertFalse(atom.getTrack(track2).getObjInTrack().contains(electron));
    assertTrue(atom.getTrack(track).getObjInTrack().contains(electron));
  }

}
