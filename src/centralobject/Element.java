package centralobject;

/**
 * . the Element type which stands for a Element that is marked by its type(name)
 * 
 * @author Administrator
 *
 */
public class Element extends ConcreteCentralObject {
  // immutability : can not change through any method of Element, and use final to protect the
  // variants from being changed

  // safety from REP exposure: use private to set the variants and create a brand new Element class
  // every time it is changed
  // constructors
  public Element(String name) {
    super(name);
  }
}
