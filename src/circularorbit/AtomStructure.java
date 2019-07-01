package circularorbit;

import physicalobject.Electron;
import track.Track;

public class AtomStructure extends ConcreteCircularOrbit {

  /**.
   * create a new AtomStruture you should create a complete Atom file using the generatefromfile
   * class !!!!!!!!!!!
   * 
   * @return a AtomStructure instance
   */
  public static AtomStructure creator() {
    return new AtomStructure();
  }


  /**.
   * let a certain electron transit from a track to a new track
   * 
   * @param electron the electron that are transiting
   * @param track the track that the electron are transiting to
   */
  public boolean transit(Electron electron, Track track) {
    if (this.getObject(electron) == null || this.getTrack(track) == null) {
      return false;
    }
    Track formerTrack = electron.getTrack();
    formerTrack.getObjInTrack().remove(electron);// remove the obj from the old track
    electron.setTrack(track);// set the obj's track as new track
    track.getObjInTrack().add(electron);
    return true;
  }



}
