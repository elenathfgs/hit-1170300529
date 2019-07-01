package physicalobject;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import physicalobject.Planet;
import physicalobject.PlanetDecorator;

/**
 * test whether the decorator of planet works well 1. test the object matching the decorator 2. add
 * a satellite to a certain planet, test whether the planet contains the satellite
 * 
 * @author Administrator
 *
 */
class PlanetDecoratorTest {
  Planet testPlanet = new Planet("Earth", "Solid", "Blue", 6378.137, 0, 29.783, true);

  @Test
  void testPlanetDecorator() {
    PlanetDecorator testDecorator = new PlanetDecorator(testPlanet);
    assertTrue(testDecorator.input.getlabel().equals("Earth"));
  }

  @Test
  void testAddSatellite() {
    PlanetDecorator testDecorator = new PlanetDecorator(testPlanet);
    testDecorator.addSatellite("moon");
    assertTrue(testDecorator.getSatellites().contains("moon"));
  }

  @Test
  void testGetSatellites() {
    PlanetDecorator testDecorator = new PlanetDecorator(testPlanet);
    testDecorator.addSatellite("moon");
    assertTrue(testDecorator.getSatellites().contains("moon"));
  }

}
