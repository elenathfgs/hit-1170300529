package centralobject;

import static org.junit.Assert.assertTrue;

/**
 * . a CentralUser that stands for a central user that the social network contains
 * 
 * @author Administrator
 *
 */
public class CentralUser extends ConcreteCentralObject {
  private final int age;
  // the age of the user
  // RI:should be >=0
  private final boolean gender;
  // the gender of the user, true for male, false for female
  // RI: should be true or false

  // AF: represent the centralUser by its age and gender

  // immutability : can not change through any method of CentralUser, and use final to protect the
  // variants from being changed

  // safety from REP exposure: use private to set the variants and create a brand new CentralUser
  // class every time it is changed
  /**
   * . constructors
   * 
   * @param label the label of the CentralUser
   * @param age the age of the CentralUser
   * @param gender the gender of the CentralUser
   */
  public CentralUser(String label, int age, boolean gender) {
    super(label);
    // TODO Auto-generated constructor stub
    this.age = age;
    this.gender = gender;
  }

  /**
   * . check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(age >= 0);
  }

  @Override
  public String toString() {
    return "name:" + this.getLabel() + " age:" + this.getAge() + " gender:"
        + (this.getGender() ? "male" : "female");
  }

  public int getAge() {
    return age;
  }

  public boolean getGender() {
    return gender;
  }

}
