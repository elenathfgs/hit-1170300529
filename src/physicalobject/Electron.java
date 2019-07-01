package physicalobject;

import track.Track;

/**.
 * the electron class
 */
public class Electron extends ConcretePhysicalObject {

  private ElectronState lastState;
  // Rep Invarant: should be a ElectronState

  // Abstraction Function: represent the identifier of this election and the last state

  // immutability : use final to set all the variants and every time client need to change it, they
  // need to create a new Electron

  // safe from REP exposure:use final to decorate the variants and the clients can not visit the
  // inner variants through outer classes
  /**.
   * constructors
   * @param this.label the this.label of the electron
   */
  public Electron(int label) {
    super();
    this.label = String.valueOf(label);
    this.lastState = new ElectronState(this);
  }


  @Override
  public String getlabel() {
    return String.valueOf(this.label);
  }

  /**.
   * get the identifier of this electron
   * 
   * @return an integer type
   */
  public int getNum() {
    return Integer.parseInt(this.label);
  }

  @Override
  public void setAngle(double angle) {
    this.lastState = new ElectronState(this);
    this.angle = angle;
  }

  @Override
  public void setTrack(Track track) {
    this.lastState = new ElectronState(this);
    this.track = track;
  }

  /**.
   * get the last state of this electron
   * 
   * @return
   */
  public ElectronState getLastState() {
    return this.lastState;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Electron) {
      Electron electron = (Electron) obj;
      if (electron.getNum() == this.getNum()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return String.valueOf(getNum()).hashCode();
  }

}
