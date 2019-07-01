package physicalobject;

import static org.junit.Assert.assertTrue;

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
import org.junit.jupiter.api.Test;

import physicalobject.Electron;

class AtomStructureTest {
  File atomFile = new File("src/../standard_input/AtomicStructure.txt");

  @Test
  void testCreator() {
    AtomStructure atomStructure = AtomStructure.creator();
    assertTrue(atomStructure != null);
  }

  @Test
  void testTransit() throws Exception {
    AtomStructure atomStructure;
    try {
      atomStructure = SystemFactory.generateAtomStructure(
          CircularIO.readFile(new CircularChannelReader(), atomFile));
    } catch (ElementNameException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getFeedBack());
      e.printStackTrace();
      return;
    }
    System.out.println(atomStructure.toString());
    atomStructure.transit((Electron) atomStructure.getObject("1"), atomStructure.getTrack(1));
    assertTrue(atomStructure.getTrack(1).getObjNum() == 9);
  }

}
