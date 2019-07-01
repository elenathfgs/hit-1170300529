package physicalobject;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import physicalobject.Friend;

/**
 * test the correctness of a Friend Type 1.test the getLabel: the label should be as expected 2.test
 * the GetName: the label should be as expected 3.test the GetAge: the label should be as expected
 * 4.test the GetAge: the label should be as expected
 * 
 * @author Administrator
 *
 */
class FriendTest {
  Friend friend = new Friend("Mike", 18, true);

  @Test
  void testGetlabel() {
    assertTrue(friend.getlabel().equals("Mike"));
  }

  @Test
  void testGetAge() {
    assertTrue(friend.getAge() == 18);
  }

  @Test
  void testGetGender() {
    assertTrue(friend.getGender());
  }

}
