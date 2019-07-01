package centralobject;

import static org.junit.Assert.assertTrue;

import centralobject.CentralObject;
import centralobject.CentralObjectFactory;
import centralobject.CentralUser;
import org.junit.jupiter.api.Test;

/**.
 * test the user's correctness 1. user's constructor 2. user's toString 3. user's age,gender,label
 * should be as expected
 * 
 * @author Administrator
 *
 */
class CentralUserTest {
  CentralObject rawUser = CentralObjectFactory.createCentralUser("mike", 18, true);
  CentralUser user = (CentralUser) rawUser;

  @Test
  void testCentralUser() {
    CentralUser user = new CentralUser("mike", 18, true);
    assertTrue(user.getLabel().equals("mike"));
  }

  @Test
  void testToString() {
    rawUser.toString();
  }

  @Test
  void testGetAge() {
    assertTrue(user.getAge() == 18);
  }

  @Test
  void testGetGender() {
    assertTrue(user.getGender());
  }

}
