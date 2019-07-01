package centralobject;

import static org.junit.Assert.assertTrue;

import centralobject.CentralObject;
import centralobject.CentralObjectFactory;
import org.junit.jupiter.api.Test;


/**.
 * test the constructor of Element type the name of the element should be as expecteds
 * 
 * @author Administrator
 *
 */
class ElementTest {

  @Test
  void testElement() {
    CentralObject element = CentralObjectFactory.createElement("Fe");
    assertTrue(element.getLabel().equals("Fe"));
  }

}
