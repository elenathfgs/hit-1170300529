package circularorbit;

import static org.junit.Assert.assertTrue;

import centralobject.Stellar;
import circularorbit.StellarSystem;
import exceptions.SameLabelException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import physicalobject.Planet;
import track.Track;

public class StellarSystemTest {
  StellarSystem stellarSystem = new StellarSystem();
  Stellar sun = new Stellar("sun", 6.96392e5, 1.9885e30);
  Planet earth = new Planet("Earth", "Solid", "Blue", 6378.137, 0, 29.783, true);
  //Planet ::= <Earth,Solid,Blue,6378.137,1.49e8,29.783,CW,0
  Planet earth2 = new Planet("Earth2", "Solid", "Blue", 6378.137, 180, 29.783, true);
  Planet earth3 = new Planet("Earth3", "Solid", "Blue", 57867, 180, 29.76, true);
  Track earthTrack = new Track(1.49e8);

  @Test
  public void testMove() throws SameLabelException {
    stellarSystem.setCentralObject(sun);
    stellarSystem.addTrack(earthTrack);
    stellarSystem.setObjAtTrack(earth, earthTrack);
    stellarSystem.move(earth, 20);
    System.out.println(stellarSystem.objIterator().next().getAngle());
    assertTrue(stellarSystem.objIterator().next().getAngle() == 20);
  }
  
  @Test
  public void testAddAndDelete() {
    try {
      stellarSystem.addObject(earth);
    } catch (SameLabelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      stellarSystem.addObject(earth3);
    } catch (SameLabelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(stellarSystem.getObjNum(), 2);
    stellarSystem.deleteObject(earth3);
    assertEquals(stellarSystem.getObjNum(), 3);
  }
  
  @Test
  public void testMoveByTime() throws SameLabelException {
    stellarSystem.setCentralObject(sun);
    stellarSystem.addTrack(earthTrack);
    stellarSystem.setObjAtTrack(earth, earthTrack);
    stellarSystem.moveByTime(earth, 2);
    assertTrue(stellarSystem.objIterator().next().getAngle() == 6.362566000142173E-8);
  }

  @Test
  public void testCalculateDistance() throws SameLabelException {
    stellarSystem.setCentralObject(sun);
    stellarSystem.addTrack(earthTrack);
    stellarSystem.setObjAtTrack(earth, earthTrack);
    stellarSystem.setObjAtTrack(earth2, earthTrack);
    double distance = stellarSystem.calculateDistance(earth, earth2);
    assertTrue(distance == 2.6641100575296625E8);
  }

  @Test
  public void testToString() throws SameLabelException {
    stellarSystem.setCentralObject(sun);
    stellarSystem.addTrack(earthTrack);
    stellarSystem.setObjAtTrack(earth, earthTrack);
    stellarSystem.toString();
    assertTrue(stellarSystem.toString().contains("位置 : 轨道半径"));
  }

}
