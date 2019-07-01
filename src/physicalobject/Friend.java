package physicalobject;

import static org.junit.Assert.assertTrue;

public class Friend extends ConcretePhysicalObject {
  private final int age;
  // the age of a certain friend
  // Rep invariant: age should be >=0
  private final boolean gender;
  // the gender of a certain friend
  // true for male,false for female

  // Abstraction Function: represent A friend by its name , age ,and gender

  // immutability : can not change through any method of Friend

  // safety from REP exposure: use private to set the variants and create a brand new Friend class
  // every time it is changed
  /**.
   * constructor
   * @param name the name of the friend
   * @param age the age of the friend
   * @param gender the gender of the friend
   */
  public Friend(String name, int age, boolean gender) {
    this.label = name;
    this.age = age;
    this.gender = gender;
  }

  /**.
   * check the REP invariant of this class
   */
  public void checkRep() {
    assertTrue(age >= 0);
  }

  @Override
  public String getlabel() {
    return this.label;
  }

  // --------------------------getters and setters----------------------------
  @Override
  public String toString() {
    return "name:" + this.getlabel() + " age:" + this.getAge() + " gender:"
        + (this.getGender() ? "male" : "female");
  }

  public int getAge() {
    return age;
  }

  public boolean getGender() {
    return gender;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Friend) {
      Friend friend = (Friend) obj;
      if (friend.getlabel().equals(this.label)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.label.hashCode();
  }
}
