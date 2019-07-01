package applications;

import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.CloseNumException;
import exceptions.ElementNameException;
import exceptions.GenderInputException;
import exceptions.LabelException;
import exceptions.NumOfTrackUnmatch;
import exceptions.NumberRepresentException;
import exceptions.RotateDirectionInputException;
import exceptions.SameLabelException;
import exceptions.TieWithoutDefinitionException;
import io.CircularBufferedReader;
import io.CircularChannelReader;
import io.CircularIO;
import io.SystemFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.junit.Test;

public class ActionsTest {
  File atomFile = new File("src/../standard_input/AtomicStructure.txt");
  File stellarFile = new File("src/../standard_input/StellarSystem.txt");

  File socialFile = new File("src/../standard_input/SocialNetworkCircle.txt");

  @Test
  public void testCommonAction() throws Exception {
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
    Actions.commonAction(atomStructure, 6);
  }

  @Test
  public void testAtomAction() throws Exception {
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
    Actions.commonAction(atomStructure, 6);
  }

  @Test
  public void testSocialNetworkAction() throws Exception {
    SocialNetworkCircle socialNetworkCircle;
    try {
      socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
          CircularIO.readFile(new CircularChannelReader(), socialFile));
      Actions.socialNetworkAction(socialNetworkCircle, 6);
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
  }

  @Test
  public void testStellarSystemAction()
      throws Exception {
    StellarSystem stellarSystem;
    try {
      stellarSystem = SystemFactory.generateStellarSystem(
          CircularIO.readFile(new CircularChannelReader(), stellarFile));
    } catch (NumberRepresentException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getFeedback());
      e.printStackTrace();
      return;
    }
    Actions.stellarSystemAction(stellarSystem, 6);
  }

}
