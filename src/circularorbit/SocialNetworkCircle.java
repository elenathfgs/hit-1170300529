package circularorbit;

import static org.junit.Assert.assertTrue;

import centralobject.CentralObject;
import centralobject.CentralUser;
import exceptions.SameLabelException;
import exceptions.SelfRelationException;
import exceptions.TieWithoutDefinitionException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit {
  static final int MAX = 2147483647;
  private final Map<Friend, Set<Friend>> socialMap = new HashMap<Friend, Set<Friend>>();
  // the two people who have straight contact with each other
  // RI: should not be null
  private final Map<String, Friend> findFriend = new HashMap<String, Friend>();
  //use to quickly find a certain friend
  private final Map<Map.Entry<Friend, Friend>, Double> closeMap =
      new HashMap<Map.Entry<Friend, Friend>, Double>();
  // the Map which represent the relation of two Users and their close rate
  // RI: should not be null

  // AI:represent a SocialNetworkCircle by the relationship of Users in it

  // mutability: client can add objects to the object set every time they need to add objects, and
  // when a new track is recognized, it will be added to the track's set
  // also, there is certain relationships between users, the relationship can be added to the
  // socialMap and closeMap
  // Safe from REP exposure: use private to decorate the variants thus the clients can not change it
  // through any outer class
  /**.
   * create a new SocialNetworkCircle you should create a complete SocialNetworkCircle file using
   * the generatefromfile class !!!!!!!!!!!
   * 
   * @return a SocialNetworkCircle instance
   */
  public static SocialNetworkCircle creator() {
    return new SocialNetworkCircle();
  }

  /**.
   * check the REP invariant of this class no matter how the social relationship is changed,the
   * lowest path length from any object to the central user should be equal to the index of track
   * that the certain object stays
   */
  public void checkRep() {
    Friend user = this.getFriend(this.getCentralObject().getLabel());
    for (int i = 0; i < this.getTrackNum(); i++) {
      Set<PhysicalObject> thisTrackObj = this.getTrack(i).getObjInTrack();
      for (PhysicalObject physicalObject : thisTrackObj) {
        assertTrue(getLogicalDistance(physicalObject, user) <= i + 1);
      }
    }
  }

  /**.
   * override the setCentralObject method set the centralUser as one of a normal friends, in order
   * to better count the distance
   * 
   * @throws SameLabelException SameLabelException
   */
  @Override
  public boolean setCentralObject(CentralObject centralObject) throws SameLabelException {
    super.setCentralObject(centralObject);
    Friend user = new Friend(centralObject.getLabel(), ((CentralUser) centralObject).getAge(),
        ((CentralUser) centralObject).getGender());
    this.addObject(user);
    return true;

  }

  /**.
   * add a relation to to certain persons
   * 
   * @param a the first person
   * @param b the second person
   * @return success for true
   * @throws SelfRelationException SelfRelationException
   * @throws TieWithoutDefinitionException TieWithoutDefinitionException
   */
  public boolean addRelation(Friend a, Friend b, double value) throws SelfRelationException {
//    if (value > 1 || value < 0) {
//      System.out.println("亲密度不符合要求");
//    }
//    if (this.getObject(a) == null || this.getObject(b) == null || a.equals(b)) {
//      if (a.equals(b)) {
//        throw new SelfRelationException(a.getlabel());
//      }
//      return false;
//      // **Defensive Programming**
//    } else {
    this.socialMap.get(a).add(b);
    this.socialMap.get(b).add(a);
    Map.Entry<Friend, Friend> pairFriend = new AbstractMap.SimpleEntry<Friend, Friend>(a, b);
    closeMap.put(pairFriend, value);
    Map.Entry<Friend, Friend> pairFriend2 = new AbstractMap.SimpleEntry<Friend, Friend>(b, a);
    closeMap.put(pairFriend2, value);
    return true;
//    }
  }

  /**.
   * delete the relation between two persons
   * 
   * @param a person
   * @param b person
   * @return the intimacy of them (double),-1 if failed
   */
  public double deleteRelation(Friend a, Friend b) {
    if (this.getObject(a) == null || this.getObject(b) == null || a.equals(b)
        || !socialMap.keySet().contains(a) || !socialMap.keySet().contains(b)) {
      return -1;
      // **Defensive Programming**
    } else {
      this.socialMap.get(a).remove(b);
      this.socialMap.get(b).remove(a);
      Map.Entry<Friend, Friend> pairFriend1 = new AbstractMap.SimpleEntry<Friend, Friend>(a, b);
      Map.Entry<Friend, Friend> pairFriend2 = new AbstractMap.SimpleEntry<Friend, Friend>(b, a);
      if (closeMap.containsKey(pairFriend1)) {
        return closeMap.remove(pairFriend1);
      } else if (closeMap.containsKey(pairFriend2)) {
        return closeMap.remove(pairFriend2);
      }
      return -1;
    }
  }

  @Override
  public boolean addObject(PhysicalObject obj) {
    Friend friend = (Friend)obj;
    if (this.socialMap.containsKey(friend)) {
      return false;
    }
    this.findFriend.put(friend.getlabel(), friend);
    Set<Friend> initialFriends = new HashSet<Friend>();
    socialMap.put(friend,initialFriends);
    return true;
  }
  
  @Override
  public boolean deleteObject(PhysicalObject obj) {
    Friend friend = (Friend)obj;
    if (!this.socialMap.containsKey(friend)) {
      return false;
    }
    this.findFriend.remove(friend.getlabel());
    this.socialMap.remove(friend);
    return true;
  }
  
  
  /**.
   * find the certain friend according to the name of the person
   * 
   * @param name name of the friend
   * @return a Friend class
   */
  public Friend getFriend(String name) {
    return this.findFriend.get(name);
  }
  
  @Override
  public PhysicalObject getObject(PhysicalObject obj) {
    return this.getFriend(obj.getlabel());
  }
  
  @Override
  public PhysicalObject getObject(String label) {
    return this.getFriend(label);
  }

  /**.
   * get all the friends including the central user
   * 
   * @return a set including all the friends
   */
  public Set<Friend> getFriends() {
    return this.socialMap.keySet();
  }
  
  /**.
   * get the friends of one certain friend
   * @return Set all the friends of the certain friend
   */
  public Set<Friend> getBelongFriends(Friend friend) {
    return this.socialMap.get(friend);
  }

  @Override
  public int getLogicalDistance(PhysicalObject e1, PhysicalObject e2) {
    Friend f1 = (Friend) e1;
    Friend f2 = (Friend) e2;
    // all the things above are preparations

    if (this.getObject(f1) == null || this.getObject(f2) == null) {
      // the object is not in this graph
      return -1;
    }
    if (f1.equals(f2)) {
      return 0;
    }

    // BFS
    List<Friend> judgeReach = new ArrayList<Friend>(socialMap.keySet());
    // the targets waiting to reach, to judge whether the object is reached
    Queue<Friend> queue = new LinkedList<Friend>();// the queue used for BFS
    Queue<Integer> distanceQueue = new LinkedList<Integer>();// to count the distance
    queue.offer(f1);// let the first object in queue
    distanceQueue.offer(0);// the distance of first reached objects

    int currentDistance = 0;// the number of steps that the BFS has taken
    while (judgeReach.contains(f2) && !queue.isEmpty()) {
      Friend currentFriend = queue.poll();
      currentDistance = distanceQueue.poll();
      Iterator<Friend> friendIterator = socialMap.get(currentFriend).iterator();
      while (friendIterator.hasNext()) {
        Friend reachedFriend = friendIterator.next();
        if (judgeReach.contains(reachedFriend)) {
          queue.offer(reachedFriend);
          distanceQueue.offer(currentDistance + 1);
        }
      }
      judgeReach.remove(currentFriend);// to mark the target as hasReached
    }

    // still not reach
    if (judgeReach.contains(f2)) {
      return MAX;
    }
    return currentDistance;
  }

  /**
   * to count how many friends that the central user may get to know through a first range friend my
   * strategy is to judge if a edge's weight is >0.5,then the user could get to know the person that
   * this edge connected
   * 
   * @param f a first-range friend waiting to count the distribution
   * @return the integer distribution
   */
  public int countDistribution(Friend f) {
    if (f == null) {
      System.out.println("没有这个好友");
      return -1;
    }
    if (f.getlabel().equals(this.getCentralObject().getLabel())) {
      System.out.println("不能以中心用户计算扩散度");
      return -1;
    }
    if (f.getTrack().getRadius() != 1) {
      System.out.println("扩散度计算的是第一级好友!!!");
      return -1;
    }


    List<Friend> judgeReach = new ArrayList<Friend>(socialMap.keySet());
    // the targets waiting to reach, to judge whether the object is reached
    Queue<Friend> queue = new LinkedList<Friend>();// the queue used for BFS
    queue.offer(f);// let the first object in queue
    int countFriends = 0;
    judgeReach.remove(this.getFriend(this.getCentralObject().getLabel()));
    // you can not get back to the central user
    while (!queue.isEmpty()) {
      Friend currentFriend = queue.poll();
      judgeReach.remove(currentFriend);
      for (Friend friend : socialMap.get(currentFriend)) {

        Map.Entry<Friend, Friend> friendPair =
            new AbstractMap.SimpleEntry<Friend, Friend>(currentFriend, friend);
        if (closeMap.get(friendPair) > 0.5 && judgeReach.contains(friend)) {
          // if the close range >0.5,then add this point in the queue
          queue.offer(friend);
          System.out.println(friend.getlabel());
          countFriends++;
        }
      }
    }
    return countFriends;
  }

  /**.
   * give the String type of the information of the relationships of all the objects
   * 
   * @return
   */
  private String showRelationInfo() {
    StringBuffer sb = new StringBuffer();
    Iterator<Map.Entry<Friend, Friend>> iterator = closeMap.keySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Friend, Friend> friendPair = iterator.next();
      sb.append(friendPair.getKey().getlabel() + " -- " + friendPair.getValue().getlabel()
          + "  亲密度: " + closeMap.get(friendPair) + "\n");
    }
    return sb.toString();
  }

  public Iterator<Map.Entry<Friend, Friend>> relationIterator() {
    return this.closeMap.keySet().iterator();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    String superString = super.toString();
    String thisString = this.showRelationInfo();
    sb.append(superString);
    sb.append("\n");
    sb.append(thisString);
    return sb.toString();
  }
  
  /**.
   * clean the repeated key values in the closeMap, use to output
   * @return
   */
  public Map<Map.Entry<Friend, Friend>, Double> tidyClosemap() {
    Map<Map.Entry<Friend, Friend>, Double> cleanMap = 
        new HashMap<Map.Entry<Friend,Friend>, Double>();
    Iterator<Map.Entry<Friend, Friend>> iterator = closeMap.keySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Friend, Friend> friendPair = iterator.next();
      Map.Entry<Friend, Friend> friendPairUpsideDown =
          new AbstractMap.SimpleEntry<Friend, Friend>(friendPair.getValue(), friendPair.getKey());
      if (!cleanMap.containsKey(friendPairUpsideDown)) {
        cleanMap.put(friendPair, closeMap.get(friendPair));
      }
    }
    return cleanMap;
  }
}
