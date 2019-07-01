package io;

import centralobject.CentralUser;
import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.util.Iterator;
import java.util.Map;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.Planet;

public class GetSystemInfo {
  /**.
   * get the information of a system and turn it into a String form
   * the information's form follows the rules given in Lab3
   * @param atomStructure the system which information need to be gotten
   * @return a String contains the information of the system
   */
  public static String getAtomInfo(AtomStructure atomStructure) {
    StringBuilder infoBuilder = new StringBuilder();
    String elementString = "ElementName ::= " + atomStructure.getCentralObject().getLabel();
    infoBuilder.append(elementString);
    String numberOfTracksString = "NumberOfTracks ::= " + atomStructure.getTrackNum();
    infoBuilder.append(numberOfTracksString);
    String numberOfElectron = "NumberOfElectron ::= ";
    infoBuilder.append(numberOfElectron);
    for (int i = 0; i < atomStructure.getTrackNum(); i++) {
      infoBuilder.append((i + 1) + "/" + atomStructure.getTrack(i).getObjNum());
      if (i != atomStructure.getTrackNum() - 1) {
        infoBuilder.append(";");
      }
    }
    return infoBuilder.toString();
  }
  
  /**.
   * get the information of a system and turn it into a String form
   * the information's form follows the rules given in Lab3
   * @param stellarSystem the system which information need to be gotten
   * @return a String contains the information of the system
   */
  public static String getStellarInfo(StellarSystem stellarSystem) {
    StringBuilder infoBuilder = new StringBuilder();
    String stellarString = "Stellar ::= <" 
        + stellarSystem.getCentralObject().getLabel() + ","
        + ((Stellar)stellarSystem.getCentralObject()).getRadius() + "," 
        + ((Stellar)stellarSystem.getCentralObject()).getWeight() + ">";         
    infoBuilder.append(stellarString + "\n");
    Iterator<PhysicalObject> planetIterator = stellarSystem.getObjectIterator();
    
    while (planetIterator.hasNext()) {
      Planet planet = (Planet)planetIterator.next();
      String planetString = "Planet ::= <"
          + planet.getlabel() + ","
          + planet.getForm() + ","
          + planet.getColor() + ","
          + numToScienceRep(planet.getSize()) + ","
          + numToScienceRep(planet.getTrack().getRadius()) + ","
          + numToScienceRep(planet.getVelocity()) + ","
          + (planet.getDirection() ? "CW" : "CCW") + ","
          + planet.getAngle() + ">";
      infoBuilder.append(planetString + "\n");
    }
    
    return infoBuilder.toString();
  }
  
  /**.
   * get the information of a system and turn it into a String form
   * the information's form follows the rules given in Lab3
   * @param socialNetworkCircle the system which information need to be gotten
   * @return a String contains the information of the system
   */
  public static String getSocialInfo(SocialNetworkCircle socialNetworkCircle) {
    StringBuilder infoBuilder = new StringBuilder();    
    String centralUserString = "CentralUser ::= <"
        + socialNetworkCircle.getCentralObject().getLabel() + ","
        + ((CentralUser)socialNetworkCircle.getCentralObject()).getAge() + ","
        + (((CentralUser)socialNetworkCircle.getCentralObject()).getGender() ? "M" : "F") + ">";
    infoBuilder.append(centralUserString + "\n\n");
    
    Iterator<PhysicalObject> friendsIterator = socialNetworkCircle.getObjectIterator();
    while (friendsIterator.hasNext()) {
      Friend friend = (Friend)friendsIterator.next();
      String friendString = "Friend ::= <"
          + friend.getlabel() + ","
          + friend.getAge() + ","
          + (friend.getGender() ? "M" : "F") + ">"; 
      infoBuilder.append(friendString + "\n");
    }
    infoBuilder.append("\n");
    
    Map<Map.Entry<Friend, Friend>, Double> cleanMap = socialNetworkCircle.tidyClosemap();
    Iterator<Map.Entry<Friend, Friend>> closeEntryIterator = cleanMap.keySet().iterator();
    while (closeEntryIterator.hasNext()) {
      Map.Entry<Friend, Friend> friendEntry = closeEntryIterator.next();
      String socialTieString = "SocialTie ::= <"
          + friendEntry.getKey().getlabel() + ","
          + friendEntry.getValue().getlabel() + ","
          + cleanMap.get(friendEntry) + ">";
      infoBuilder.append(socialTieString + "\n");
    }
    return infoBuilder.toString();
  }
  
  /**.
   * turn a representation of a number which is bigger than 10000 to a scientific notation
   * only keep two decimals
   * @param num the number
   * @return the scientific notation form of the number
   */
  private static String numToScienceRep(double num) {
    String numString = String.valueOf(num);
    if (numString.contains("E") || num < 10000) {
      numString = numString.replace("E", "e");
      return numString;
    }
    StringBuilder numStringBuilder = new StringBuilder(numString);
    int pointIndex = numStringBuilder.indexOf(".");
    numStringBuilder.deleteCharAt(pointIndex);
    numStringBuilder.insert(1, ".");
    numStringBuilder.delete(4, numStringBuilder.length());
    numStringBuilder.append("e" + (pointIndex - 1));
    return numStringBuilder.toString();
  }

}
