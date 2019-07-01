package centralobject;

/**
 * . a muti-method factory used to generate different type of centralobject
 *
 */
public class CentralObjectFactory {
  /**
   * . create a new CentralObject as a Atom
   * 
   * @param type refers to the type of the element
   * @return a CentralObject
   */
  public static CentralObject createElement(String type) {
    return new Element(type);
  }

  /**
   * . create a new CentralObject as a Stellar
   * 
   * @param name the name of the new CentralObject
   * @param radius the radius of the new CentralObject
   * @param weight the weight of the new CentralObject
   * @return
   */
  public static CentralObject createStellar(String name, double radius, double weight) {
    return new Stellar(name, radius, weight);
  }

  /**
   * . create a new CentralObject as a CentralUser
   * 
   * @param label the label of the new CentralObject
   * @param age the age of the new CentralObject
   * @param gender the gender of the new CentralObject
   * @return
   */
  public static CentralUser createCentralUser(String label, int age, boolean gender) {
    return new CentralUser(label, age, gender);
  }

}
