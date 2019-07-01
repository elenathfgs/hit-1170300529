package circularorbit;

import static org.junit.Assert.assertTrue;

import apis.CircularOrbitApis;
import centralobject.CentralUser;
import circularorbit.SocialNetworkCircle;
import exceptions.SameLabelException;
import exceptions.SelfRelationException;
import exceptions.TieWithoutDefinitionException;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

import physicalobject.Friend;
import track.Track;

class SocialNetworkCircleTest {
  SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
  CentralUser user = new CentralUser("Mike", 19, true);
  Friend f0 = new Friend("Mike", 20, true);
  Friend f1 = new Friend("Smith", 20, true);
  Friend f2 = new Friend("Mary", 30, false);
  Friend f3 = new Friend("Siri", 20, false);
  Friend f4 = new Friend("Jack", 50, true);
  Friend f5 = new Friend("Trump", 60, true);

  @Test
  void testSetCentralObject() throws SameLabelException {
    socialNetworkCircle.setCentralObject(user);
    assertTrue(socialNetworkCircle.getCentralObject().getLabel().equals("Mike"));
  }

  @Test
  void testGetLogicalDistance()
      throws SameLabelException, SelfRelationException, TieWithoutDefinitionException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.addObject(f3);
    socialNetworkCircle.addObject(f4);
    socialNetworkCircle.setCentralObject(user);
    socialNetworkCircle.addRelation(f1, f2, 1);
    socialNetworkCircle.addRelation(f2, f3, 0.5);
    socialNetworkCircle.addRelation(f3, f4, 1);
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f2) == 1);
    assertTrue(CircularOrbitApis.getLogicalDistance(socialNetworkCircle, f1, f4) == 3);
  }

  @Test
  void testCreator() throws SameLabelException {
    socialNetworkCircle = SocialNetworkCircle.creator();
    socialNetworkCircle.setCentralObject(user);
    assertTrue(socialNetworkCircle.getCentralObject().getLabel().equals("Mike"));
  }

  @Test
  void testAddRelation()
      throws SameLabelException, SelfRelationException, TieWithoutDefinitionException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.setCentralObject(user);
    socialNetworkCircle.addRelation(f1, f2, 1);
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f2) == 1);
  }

  @Test
  void testDeleteRelation()
      throws SameLabelException, SelfRelationException, TieWithoutDefinitionException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.addRelation(f1, f2, 1);
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f2) == 1);
    socialNetworkCircle.deleteRelation(f1, f2);
    assertTrue(socialNetworkCircle.getLogicalDistance(f1, f2) == SocialNetworkCircle.MAX);
  }

  @Test
  void testGetFriend() throws SameLabelException {
    socialNetworkCircle.setCentralObject(user);
    assertTrue(socialNetworkCircle.getFriend(user.getLabel()) != null);
  }

  @Test
  void testGetFriends()
      throws SameLabelException, SelfRelationException, TieWithoutDefinitionException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.addRelation(f1, f2, 1);
    assertTrue(socialNetworkCircle.getFriends().size() == 2);
  }

  @Test
  void testCountDistribution() throws SameLabelException, SelfRelationException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.addObject(f3);
    socialNetworkCircle.addObject(f4);
    socialNetworkCircle.setCentralObject(user);
    socialNetworkCircle.addRelation(f0, f1, 1);
    socialNetworkCircle.addRelation(f1, f2, 1);
    socialNetworkCircle.addRelation(f2, f3, 0.5);
    socialNetworkCircle.addRelation(f3, f4, 1);
    Iterator<Friend> friendIterator = socialNetworkCircle.getFriends().iterator();
    while (friendIterator.hasNext()) {
      Friend friend = friendIterator.next();
      if (!friend.equals(f0)) {
        int radius = socialNetworkCircle.getLogicalDistance(f0, friend);
        Track track = new Track(radius);
        track.checkRep();
        if (socialNetworkCircle.getTrack(track) == null) {
          try {
            socialNetworkCircle.addTrack(track);
          } catch (SameLabelException e) {
            System.out.println(e.getFeedback());
            e.printStackTrace();
          }
        }
        socialNetworkCircle.setObjAtTrack(friend, track);
      }
    }
    assertTrue(socialNetworkCircle.countDistribution(null) == -1);
    assertTrue(socialNetworkCircle.countDistribution(f1) == 1);
    assertTrue(socialNetworkCircle.countDistribution(f0) == -1);
  }

  @Test
  void testShowRelationInfo() throws SameLabelException, SelfRelationException {
    socialNetworkCircle.addObject(f1);
    socialNetworkCircle.addObject(f2);
    socialNetworkCircle.addObject(f3);
    socialNetworkCircle.addObject(f4);
    socialNetworkCircle.setCentralObject(user);
    socialNetworkCircle.addRelation(f0, f1, 1);
    socialNetworkCircle.addRelation(f1, f2, 1);
    socialNetworkCircle.addRelation(f2, f3, 0.5);
    socialNetworkCircle.addRelation(f3, f4, 1);
    System.out.println(socialNetworkCircle.toString());
  }
}
