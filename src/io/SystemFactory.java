package io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import centralobject.CentralObject;
import centralobject.CentralObjectFactory;
import centralobject.CentralUser;
import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.CloseNumException;
import exceptions.ElementNameException;
import exceptions.NumOfTrackUnmatch;
import exceptions.SameLabelException;
import exceptions.SelfRelationException;
import exceptions.TieWithoutDefinitionException;
import physicalobject.Electron;
import physicalobject.FlightWeightElectronFactory;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.PhysicalObjectFactory;
import physicalobject.Planet;
import track.Track;

public class SystemFactory {
  static final Pattern signPattern = Pattern.compile("::=");
  static final String labelRegex = "([a-z]|[A-Z]|\\d)*";
  static final String ElementLabelRegex = "[A-Z][a-z]";
  static final String genderRegex = " *[MF] *";
  static final String closeNumRegex = "(0.\\d\\d?\\d?)|1(.0*)?";
  
  static final String ElementNameRegex = "^ElementName\\s*::=\\s*(.*?)$";
  static final String NumberOfTracksRegex = "^NumberOfTracks\\s*::=\\s*(.*?)$";
  static final String NumberOfElectronRegex = "^NumberOfElectron\\s*::=\\s*(.*?)$";
  
  
  /**.
   * generate a AtomStructure system using read in String information
   * @param input the information String using which to create the system
   * @return a AtomStructure system
   * @throws Exception Exceptions
   */
  public static AtomStructure generateAtomStructure(String input) throws Exception {
    AtomStructure atomStructure = new AtomStructure();
    int thisNumberOfTracks = 0;
    int electronMark = 0;// use to create the electron, as label  
    String[] inputStrings = input.split("\n");
        
    for (int i = 0; i < inputStrings.length; i++) {
      String buffer = inputStrings[i];
      if (Pattern.matches(ElementNameRegex, buffer)) {
        Matcher elementNameMatcher = Pattern.compile(ElementNameRegex).matcher(buffer);
        String elementName = "";
        if (elementNameMatcher.find()) {
          elementName = elementNameMatcher.group(1);
        }
        CentralObject centralObject = CentralObjectFactory.createElement(elementName);
        atomStructure.setCentralObject(centralObject);
      } else if (Pattern.matches(NumberOfTracksRegex, buffer)) {
        Matcher numberOfTracksMatcher = Pattern.compile(NumberOfTracksRegex).matcher(buffer);
        String numOfTracksString = "0";
        if (numberOfTracksMatcher.find()) {
          numOfTracksString = numberOfTracksMatcher.group(1);
        }
        thisNumberOfTracks = Integer.parseInt(numOfTracksString);
      } else if (Pattern.matches(NumberOfElectronRegex, buffer)) {
        Matcher numOfEletronMatcher = Pattern.compile(NumberOfElectronRegex).matcher(buffer);
        String elementString = "";
        if (numOfEletronMatcher.find()) {
          elementString = numOfEletronMatcher.group(1);
        }
        String[] elements = elementString.split(";");
        thisNumberOfTracks = elements.length;
        for (int j = 0; j < elements.length; j++) {
          String[] keyValues = elements[j].split("/");
          // split the "d/d" form to get the track and objects matches
          Track track = new Track(Integer.parseInt(keyValues[0]));
          for (int k = 0; k < Integer.parseInt(keyValues[1]); k++) {
//            FlightWeightElectronFactory factory = new FlightWeightElectronFactory();
//            atomStructure.addTrack(track);
//            factory.putElectron(Integer.parseInt(keyValues[0]), track);
            PhysicalObject physicalObject = PhysicalObjectFactory.createElectron(electronMark);
            electronMark++;
            atomStructure.setObjAtTrack(physicalObject, track);
          }
        }
      }
    }
    if (thisNumberOfTracks != atomStructure.getTrackNum()) {
      throw new NumOfTrackUnmatch(String.valueOf(atomStructure.getTrackNum()),
          String.valueOf(thisNumberOfTracks));
    }
    return atomStructure;
  }
  
  /**.
   * generate a Stellar system using read in String information
   * @param input the information String using which to create the system
   * @return a Stellar system
   * @throws Exception Exceptions
   */
  public static StellarSystem generateStellarSystem(String input) throws Exception {
    final String StellarRegex = "^Stellar\\s*::=\\s*<(.*?),(.*?),(.*?)>$";
    final String PlanetRegex = 
        "^Planet\\s*::=\\s*<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>$";
    final Pattern stellarPattern = Pattern.compile(StellarRegex);
    final Pattern planetPattern = Pattern.compile(PlanetRegex);
    String[] inputStrings = input.split("\n");
    input = null;
    System.gc();
    StellarSystem stellarSystem = new StellarSystem();
    boolean hasSetCentralObj = false;
    
    long startTime = System.currentTimeMillis();
    double size;
    double beginAngle;
    double velocity;
    String direction;
    boolean booleanDirection;
    String type;
    String form;
    String color;
    for (int i = 0; i < inputStrings.length; i++) {
      String buffer = inputStrings[i];
      if (!hasSetCentralObj && Pattern.matches(StellarRegex, buffer)) {
        Matcher stellarMatcher = stellarPattern.matcher(buffer);
        String label;
        if (stellarMatcher.find()) {
          label = stellarMatcher.group(1);
          double radius = Double.parseDouble(stellarMatcher.group(2));
          double weight = Double.parseDouble(stellarMatcher.group(3));
          Stellar stellar = new Stellar(label, radius, weight);
          stellarSystem.setCentralObject(stellar);
          hasSetCentralObj = true;
        }
      } else if (Pattern.matches(PlanetRegex, buffer)) {
        Matcher planetMatcher = planetPattern.matcher(buffer);
        if (planetMatcher.find()) {
          size = Double.parseDouble(planetMatcher.group(4));
          beginAngle = Double.parseDouble(planetMatcher.group(8));
          velocity = Double.parseDouble(planetMatcher.group(6));
          direction = planetMatcher.group(7);
          booleanDirection = direction.equalsIgnoreCase("CW") ? true : false;
          type = planetMatcher.group(1);
          form = planetMatcher.group(2);
          color = planetMatcher.group(3);
          Planet planet = new Planet(type,form,color,size,beginAngle,velocity,booleanDirection);
          Track planetTrack = new Track(Double.parseDouble(planetMatcher.group(5)));
          stellarSystem.setObjAtTrack(planet, planetTrack);          
        }
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("生成这个行星系统的速度为" + (endTime - startTime) + "ms");
    inputStrings = null;
    System.gc();
    return stellarSystem;
  }
  
  /**.
   * generate a SocialNetworkCircle system using read in String information
   * @param input the information String using which to create the system
   * @return a SocialNetworkCircle system
   * @throws Exception Exceptions
   */
  public static SocialNetworkCircle generateSocialNetworkCircle(String input) throws Exception {
    final String centralUserInput = "CentralUser\\s+::=\\s+<(.*?),(.*?),(.*?)>";
    final String socialTieInput = "SocialTie\\s+::=\\s+<(.*?),(.*?),(.*?)>";
    final String friendInput = "Friend\\s+::=\\s+<(.*?),(.*?),(.*?)>";
    final Pattern centralPattern = Pattern.compile(centralUserInput);
    final Pattern socialPattern = Pattern.compile(socialTieInput);
    final Pattern friendPattern = Pattern.compile(friendInput);
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    
    Matcher centralUserMatcher = centralPattern.matcher(input);
    
    long startTime = System.currentTimeMillis();
    
    while (centralUserMatcher.find()) {
      String name = centralUserMatcher.group(1);

      int age = Integer.parseInt(centralUserMatcher.group(2).replaceAll("\\s+", ""));
      boolean gender =
          centralUserMatcher.group(3).replaceAll("\\s+", "").equals("M") ? true : false;
      CentralUser centralUser = new CentralUser(name, age, gender);
      try {
        socialNetworkCircle.setCentralObject(centralUser);
      } catch (SameLabelException e) {
        System.out.println(e.getFeedback());
        e.printStackTrace();
      }
    }
    Matcher friendPatternMatcher = friendPattern.matcher(input);
    //add friends
    while (friendPatternMatcher.find()) {
      
      String name = friendPatternMatcher.group(1);
      int age = Integer.parseInt(friendPatternMatcher.group(2).replaceAll("\\s+", ""));
      boolean gender = 
          friendPatternMatcher.group(3).replaceAll("\\s+", "").equals("M") ? true : false;
      Friend friend = new Friend(name, age, gender);
      socialNetworkCircle.addObject(friend);
    }
    Matcher socialPatternMatcher = socialPattern.matcher(input);
    //add social ties
    while (socialPatternMatcher.find()) {
      String p1 = socialPatternMatcher.group(1).replaceAll("\\s+", "");
      String p2 = socialPatternMatcher.group(2).replaceAll("\\s+", "");
//      String relationString = socialPatternMatcher.group(3).replaceAll("\\s+", "");
//      if (!relationString.matches(closeNumRegex)) {
//        System.out.println("用户亲密度输入不合法");
//        throw new CloseNumException(relationString);
//      }
      double relation = Double.parseDouble(socialPatternMatcher.group(3).replaceAll("\\s+", ""));

      Friend f1 = socialNetworkCircle.getFriend(p1);
      Friend f2 = socialNetworkCircle.getFriend(p2);
      try {
        socialNetworkCircle.addRelation(f1, f2, relation);
      } catch (SelfRelationException e) {
        System.out.println(e.getFeedback());
        e.printStackTrace();
        System.exit(0);
      }
    }
    
    //set all the friends on their track(use logical distance to count the track)
    Queue<Friend> queue = new LinkedList<Friend>();// the queue used for BFS
    Queue<Integer> distanceQueue = new LinkedList<Integer>();// to count the distance
    Set<Friend> judgeReach = socialNetworkCircle.getFriends();
    // the targets waiting to reach, to judge whether the object is reached
    distanceQueue.offer(0);// the distance of first reached objects
    int currentDistance = 0;// the number of steps that the BFS has taken
    queue.offer(socialNetworkCircle.getFriend(
        socialNetworkCircle.getCentralObject().getLabel()));// let the first object(user) in queue
    while (!queue.isEmpty()) {
      Friend currentFriend = queue.poll();
      currentDistance = distanceQueue.poll();
      if (currentDistance != 0) {
        Track track = new Track(currentDistance);
        socialNetworkCircle.setObjAtTrack(currentFriend, track);
      }
      Iterator<Friend> friendIterator = 
          socialNetworkCircle.getBelongFriends(currentFriend).iterator();
      while (friendIterator.hasNext()) {
        Friend reachedFriend = friendIterator.next();
        if (judgeReach.contains(reachedFriend)) {
          queue.offer(reachedFriend);
          distanceQueue.offer(currentDistance + 1);
        }
      }
      judgeReach.remove(currentFriend);// to mark the target as hasReached
    }
    
//    Friend centralUser =
//        socialNetworkCircle.getFriend(socialNetworkCircle.getCentralObject().getLabel());
//    Iterator<Friend> friendIterator = socialNetworkCircle.getFriends().iterator();
//    while (friendIterator.hasNext()) {
//      Friend friend = friendIterator.next();
//      if (!friend.equals(centralUser)) {
//        int radius = socialNetworkCircle.getLogicalDistance(centralUser, friend);
//        //count logical distance is the main memory cost in this algorithm
//        Track track = new Track(radius);
//        socialNetworkCircle.setObjAtTrack(friend, track);
//      }
//    }
    long endTime = System.currentTimeMillis();
    System.out.println("生成这个社交系统的速度为" + (endTime - startTime) + "ms");
    return socialNetworkCircle;
  }

}
