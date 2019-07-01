package apis;

import static org.junit.Assert.assertTrue;

import apis.CircularOrbitApis;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.CloseNumException;
import exceptions.GenderInputException;
import exceptions.LabelException;
import exceptions.TieWithoutDefinitionException;
import io.CircularBufferedReader;
import io.CircularChannelReader;
import io.CircularIO;
import io.SystemFactory;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import physicalobject.Friend;

class ApiTest {
  AtomStructure atomStructure = new AtomStructure();
  File atomFile1 = new File("src/../standard_input/AtomicStructure.txt");
  File atomFile2 = new File("src/../standard_input/AtomicStructure_Medium.txt");
  StellarSystem stellarSystem = new StellarSystem();
  File stellarFile1 = new File("src/../standard_input/StellarSystem.txt");
  File stellarFile2 = new File("src/../standard_input/StellarSystem_Medium.txt");
  SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
  File socialFile1 = new File("src/../standard_input/SocialNetworkCircle.txt");
  File socialFile2 = new File("src/../standard_input/SocialNetworkCircle_Medium.txt");

  @Test
  void testGetLogicalDistance() throws Exception {
    SocialNetworkCircle socialNetworkCircle;
    try {
      socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
          CircularIO.readFile(new CircularChannelReader(), socialFile1));
    } catch (GenderInputException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getFeedback());
      e.printStackTrace();
      return;
    } catch (LabelException e) {
      // TODO: handle exception
      System.out.println(e.getFeedback());
      e.printStackTrace();
      return;
    } catch (CloseNumException e) {
      // TODO: handle exception
      System.out.println(e.getFeedback());
      e.printStackTrace();
      return;
    } catch (TieWithoutDefinitionException e) {
      System.out.println(e.getFeedback());
      e.printStackTrace();
      return;
    }
    Friend f1 = socialNetworkCircle.getFriend("TommyWong");
    Friend f2 = socialNetworkCircle.getFriend("DavidChen");
    System.out.println(CircularOrbitApis.getLogicalDistance(socialNetworkCircle, f1, f2));
    assertTrue(CircularOrbitApis.getLogicalDistance(socialNetworkCircle, f1, f2) == 1);
    System.out.println(f2);
  }

  @Test
  public void testEntropy() throws Exception {
    AtomStructure atomStructure1 = atomStructure = SystemFactory.generateAtomStructure(
        CircularIO.readFile(new CircularChannelReader(), atomFile1));
    double atomStructureEntropy = CircularOrbitApis.getObjectDistributionEntropy(atomStructure1);
    System.out.println(atomStructureEntropy);
    socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
        CircularIO.readFile(new CircularChannelReader(), socialFile1));;
    double socialNetworkEntropy =
        CircularOrbitApis.getObjectDistributionEntropy(socialNetworkCircle);
    System.out.println(socialNetworkEntropy);
    StellarSystem stellarSystem1 = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile1));;
    double stellarEntropy = CircularOrbitApis.getObjectDistributionEntropy(stellarSystem1);
    System.out.println(stellarEntropy);
    assertTrue(stellarEntropy > socialNetworkEntropy && stellarEntropy > atomStructureEntropy);
  }

  @Test
  public void testGetPhysicalDistance() throws Exception {
    StellarSystem stellarSystem1 = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile1));;
    stellarSystem1.getObject("Mars").setAngle(0);
    double physicalDistance = CircularOrbitApis.getPhysicalDistance(stellarSystem1,
        stellarSystem1.getObject("Earth"), stellarSystem1.getObject("Mars"));
    System.out.println(physicalDistance);
    assertTrue(physicalDistance == Math.abs(stellarSystem1.getObject("Earth").getTrack().getRadius()
        - stellarSystem1.getObject("Mars").getTrack().getRadius()));
  }

  @Test
  public void testgetDifference() throws Exception {
    AtomStructure atomStructure1 = atomStructure = SystemFactory.generateAtomStructure(
        CircularIO.readFile(new CircularChannelReader(), atomFile1));
    AtomStructure atomStructure2 = SystemFactory.generateAtomStructure(
        CircularIO.readFile(new CircularChannelReader(), atomFile2));
    SocialNetworkCircle socialNetworkCircle1 = SystemFactory.generateSocialNetworkCircle(
        CircularIO.readFile(new CircularChannelReader(), socialFile1));;
    SocialNetworkCircle socialNetworkCircle2 = SystemFactory.generateSocialNetworkCircle(
        CircularIO.readFile(new CircularChannelReader(), socialFile2));;
    StellarSystem stellarSystem1 = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile1));;
    StellarSystem stellarSystem2 = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile2));;

    CircularOrbitApis.getDifference(atomStructure1, atomStructure2).toString();
    CircularOrbitApis.getDifference(socialNetworkCircle1, socialNetworkCircle2).toString();
    CircularOrbitApis.getDifference(stellarSystem1, stellarSystem2).toString();

  }

}
