package physicalobject;

/**.
 * a muti-method ConcretePhysicalObject factory class in order to create different classes of
 * ConcretePhysicalObject
 *
 */
public class PhysicalObjectFactory {
  /**.
   * create a electron object
   * 
   * @return a new Electron
   */
  public static ConcretePhysicalObject createElectron(int num) {
    return new Electron(num);
  }

  /**.
   * create a new planet Object
   * 
   * @param type the type of certain planet
   * @param form the form of a certain planet
   * @param color the color of a certain planet
   * @param size the size of a certain planet
   * @param beginAngle the beginning angle of a certain planet
   * @param velocity the speed that the planet moves following certain orbit
   * @param direction the direction that the planet moves
   * @return the travel direction of planet,true for clockwise,false for anti-clockwise
   */
  public static ConcretePhysicalObject createPlanet(String type, String form, String color,
      double size, double beginAngle, double velocity, boolean direction) {
    return new Planet(type, form, color, size, beginAngle, velocity, direction);
  }

  /**.
   * create a new friend object
   * 
   * @param name the name for the person
   * @param age the age of the person
   * @param gender true for male, false for female
   * @return a new Friend object
   */
  public static ConcretePhysicalObject createFriend(String name, int age, boolean gender) {
    return new Friend(name, age, gender);
  }

}
