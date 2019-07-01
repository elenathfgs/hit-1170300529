package centralobject;

import static org.junit.Assert.assertTrue;

import centralobject.CentralObjectFactory;
import centralobject.Stellar;
import org.junit.jupiter.api.Test;


/**.
 * test the constructor of the Stellar
 * 
 * @author Administrator 1. the radius should be as expected 2. the Weight should be as expected
 *
 */
class StellarTest {
  Stellar stellar = (Stellar) CentralObjectFactory.createStellar("Sun", 100, 30);

  @Test
  void testStellar() {
    Stellar stellar = new Stellar("Sun", 100, 30);
    assertTrue(stellar.getLabel().equals("Sun"));
  }

  @Test
  void testGetRadius() {
    assertTrue(stellar.getRadius() == 100);
  }

  @Test
  void testGetWeight() {
    assertTrue(stellar.getWeight() == 30);
  }

}
