package generatefromfile;

import static org.junit.Assert.assertTrue;

import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.ElementNameException;
import exceptions.NumOfTrackUnmatch;
import exceptions.NumberRepresentException;
import exceptions.RotateDirectionInputException;
import exceptions.SameLabelException;
import io.CircularBufferedReader;
import io.CircularChannelReader;
import io.CircularIO;
import io.SystemFactory;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import physicalobject.Friend;
import track.Track;

public class GenerateFromFileTest {
  AtomStructure atomStructure = new AtomStructure();
  File atomFile1 = new File("src/../standard_input/AtomicStructure.txt");
  StellarSystem stellarSystem = new StellarSystem();
  File stellarFile1 = new File("src/../standard_input/StellarSystem.txt");
  SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
  File socialFile = new File("src/../standard_input/SocialNetworkCircle.txt");

  @Test
  public void testReadAtomStructure() throws Exception {
    try {
      atomStructure = SystemFactory.generateAtomStructure(
          CircularIO.readFile(new CircularChannelReader(), atomFile1));
    } catch (ElementNameException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getFeedBack());
      e.printStackTrace();
      return;
    }
    assertTrue(atomStructure.getCentralObject().getLabel().equals("Rb"));
    assertTrue(atomStructure.getTrackNum() == 5);
    assertTrue(atomStructure.getTrack(new Track(4)).getObjInTrack().size() == 8);
    // System.out.println(atomStructure.getObjects().size());
    assertTrue(atomStructure.getObjInTrackNum() == 37);
  }

  @Test
  public void testReadStellarSystem() throws Exception {
    stellarSystem = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile1));;
    assertTrue(stellarSystem.getCentralObject().getLabel().equals("Sun"));
    assertTrue(stellarSystem.getTrackNum() == 8);
    assertTrue(
        stellarSystem.getTrack(new Track(2e8)).getObjInTrack().iterator().next().getAngle() == 70);
    assertTrue(stellarSystem.getObjInTrackNum() == 8);
    assertTrue(((Stellar) stellarSystem.getCentralObject()).getRadius() == 6.96392e5);
  }

  @Test
  public void testFindAfterSign() throws Exception {
    try {
      atomStructure = SystemFactory.generateAtomStructure(
          CircularIO.readFile(new CircularChannelReader(), atomFile1));
    } catch (ElementNameException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getFeedBack());
      e.printStackTrace();
      return;
    }
    assertTrue(atomStructure.getCentralObject().getLabel().equals("Rb"));
    assertTrue(atomStructure.getTrackNum() == 5);
    assertTrue(atomStructure.getTrack(new Track(4)).getObjInTrack().size() == 8);
  }

  @Test
  public void testReadSocialNetwork() throws Exception {
    socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
        CircularIO.readFile(new CircularChannelReader(), socialFile));
    Friend f1 = socialNetworkCircle.getFriend("TommyWong");
    Friend f2 = socialNetworkCircle.getFriend("DavidChen");
    Friend f3 = socialNetworkCircle.getFriend("FrankLee");
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f2) == 1);
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f3) == 2);
    assertTrue(socialNetworkCircle.getObjInTrackNum() == 6);
  }

}
