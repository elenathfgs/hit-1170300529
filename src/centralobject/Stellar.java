package centralobject;

import static org.junit.Assert.assertTrue;

/**
 * . a stellar type that is used in a stellarOrbit system as its central object
 * 
 * @author Administrator
 *
 */
public class Stellar extends ConcreteCentralObject {
  private final double radius;
  // the radius of the centralobject
  // REP invariant: should be >=0
  private final double weight;
  // the weight of the stellar
  // REP invariant: should be >=0

  // immutability : can not change through any method of Stellar, and use final to protect the
  // variants from being changed ,also create a brand new Stellar class every time it is changed

  // safety from REP exposure: use private to set the variants
  /**
   * . constructor
   * 
   * @param label the label of the Stellar
   * @param radius the radius of the Stellar
   * @param weight the weight of the Stellar
   */
  public Stellar(String label, double radius, double weight) {
    super(label);
    this.radius = radius;
    this.weight = weight;
  }

  /**
   * . check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(radius >= 0);
    assertTrue(weight >= 0);
  }

  public double getRadius() {
    return radius;
  }

  public double getWeight() {
    return weight;
  }

}
