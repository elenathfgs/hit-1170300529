package physicalobject;

import java.util.HashMap;
import java.util.Map;
import track.Track;

public class FlightWeightElectronFactory {
  private Electron electron;
  //the electron used in all the system
  private Map<Integer,Track> findTrack = new HashMap<Integer, Track>();

  // Abstraction Function: represent the identifier of this election and the last state

  // immutability : use final to set all the variants and every time client need to change it, they
  // need to create a new Electron

  // safe from REP exposure:use final to decorate the variants and the clients can not visit the
  // inner variants through outer classes
  
  protected Track track;
  // the track that the Object stays
  // Rep invariant: should not be null
  // Abstraction function: AF(Track) = {track | all tracks belongs to this system}
  
  /**.
   * get the instance of a electron according to the trackIndex
   * @param trackIndex the track's index in which the electron stays
   * @return a Electron
   */
  public Electron getElectron(int trackIndex) {
    electron = new Electron(trackIndex);
    electron.track = findTrack.get(trackIndex);
    return electron;
  }
  
  public void putElectron(int trackIndex,Track track) {
    findTrack.put(trackIndex, track);
  }
}
