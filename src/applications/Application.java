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
import logger.MyLogger;

public class Application {

  /**.
   * the main method of the application
   * 
   * @param args the parameter for main
   * @throws Exception Exception
   */
  public static void main(String[] args)
      throws Exception {
    File atomFile = new File("src/../standard_Input/AtomicStructure.txt");
    File stellarFile = new File("src/../standard_Input/StellarSystem.txt");
    File socialFile = new File("src/../standard_Input/SocialNetworkCircle.txt");

    MyLogger.init(MyLogger._LOG);

    Menu.showWelcome();
    int systemChoice = Actions.chooseSystem();


    switch (systemChoice) {
      case 1:
        AtomStructure atomStructure;
        try {
          atomStructure = SystemFactory.generateAtomStructure(
                  CircularIO.readFile(new CircularChannelReader(), atomFile));
        } catch (ElementNameException e1) {
          MyLogger._LOG.warning(e1.getFeedBack());
          e1.printStackTrace();
          return;
        } catch (NumOfTrackUnmatch e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        }
        while (systemChoice != 4) {
          Menu.showCommonMenu();
          Menu.showAtomMenu();
          System.out.println("请选择接下来操作");
          atomStructure.show();
          Actions.chooseAction(atomStructure);
        }
        break;
      case 2:
        StellarSystem stellarSystem;
        try {
          stellarSystem = SystemFactory.generateStellarSystem(
              CircularIO.readFile(new CircularChannelReader(), stellarFile));
        } catch (NumberRepresentException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        } catch (SameLabelException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        } catch (RotateDirectionInputException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        }

        while (systemChoice != 4) {
          Menu.showCommonMenu();
          Menu.showStellarSystemMenu();
          System.out.println("请选择接下来操作");
          //stellarSystem.show();

          Actions.chooseAction(stellarSystem);
        }
        break;

      case 3:
        SocialNetworkCircle socialNetworkCircle;
        try {
          socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
              CircularIO.readFile(new CircularBufferedReader(), socialFile));
        } catch (GenderInputException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        } catch (LabelException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        } catch (CloseNumException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        } catch (TieWithoutDefinitionException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
          return;
        }

        while (systemChoice != 4) {
          Menu.showCommonMenu();
          Menu.showSocialNetworkMenu();
          System.out.println("请选择接下来操作");
          //socialNetworkCircle.show();

          Actions.chooseAction(socialNetworkCircle);
        }
        break;
      default:
        break;
    }

  }

}
