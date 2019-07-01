package centralobject;

import static org.junit.Assert.assertTrue;

/**
 * . a ConcreteCentralObject stands for a abstract class and can be implemented by any of its
 * concrete type
 * 
 * @author Administrator
 *
 */
public abstract class ConcreteCentralObject implements CentralObject {
  private final String label;
  // the label of a certain central Obj,for examples type of Stellar, type of elements

  // immutability : can not change through any method of ConcreteCentralObject, and use final to
  // protect the variants from being changed ,also create a brand new ConcreteCentralObject class
  // every time it is changed

  // safety from REP exposure: use private to set the variants
  // constructors
  public ConcreteCentralObject(String label) {
    this.label = label;
  }

  /**
   * . check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(label != null);
  }

  // -----------------------------the getters and setters-----------------------------
  public String getLabel() {
    return label;
  }

}
