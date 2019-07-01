package physicalobject;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import physicalobject.Planet;

class PlanetTest {
  Planet planet = new Planet("Earth", "Solid", "Blue", 6378.137, 0, 29.783, true);

  // Planet ::= <Earth,Solid,Blue,6378.137,1.49e8,29.783,CW,0>
  @Test
  void testGetSize() {
    assertTrue(planet.getSize() == 6378.137);
  }

  @Test
  void testGetlabel() {
    assertTrue(planet.getlabel().equals("Earth"));
  }

  @Test
  void testGetForm() {
    assertTrue(planet.getForm().equals("Solid"));
  }

  @Test
  void testGetColor() {
    assertTrue(planet.getColor().equals("Blue"));
  }

  @Test
  void testGetBeginAngle() {
    assertTrue(planet.getBeginAngle() == 0);
  }

  @Test
  void testGetVelocity() {
    assertTrue(planet.getVelocity() == 29.783);
  }

  @Test
  void testGetDirection() {
    assertTrue(planet.getVelocity() == 29.783);
  }

}
