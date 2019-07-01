package physicalobject;

import static org.junit.Assert.assertTrue;

public class Planet extends ConcretePhysicalObject {
  private final String form;
  // the form of a certain planet
  private final String color;
  // the color of a certain planet
  private final double size;
  // the size of a certain planet
  // Rep invariant: should be >=0
  private final double beginAngle;
  // the beginning angle of a certain planet
  private final double velocity;
  // the speed that the plannet moves following certain orbit
  // Rep invariant: velocity should be >=0
  private final boolean direction;
  // the travel direction of planet,true for clockwise,false for anti-clockwise
  // Rep Invariant: should not be null

  // Abstraction Function: represent the status of a planet by its
  // type,form,color,size,beginAngle,velosity,direction

  // immutability : use final to set all the variants and every time client need to change it, they
  // need to create a new Planet

  // safe from REP exposure:use final to decorate the variants and the clients can not visit the
  // inner variants through outer classes

  // constructors
  /**.
   * 
   * @param type the type of the planet
   * @param form the form of the planet
   * @param color the color of the planet
   * @param size the size of the planet
   * @param beginAngle the beginAngle to describe the movement of the planet
   * @param velocity the move speed of the planet
   * @param direction the direction that the planet movess
   */
  public Planet(String type, String form, String color, double size, double beginAngle,
      double velocity, boolean direction) {
    // TODO Auto-generated constructor stub
    this.label = type;
    this.beginAngle = beginAngle;
    this.setAngle(beginAngle);
    this.color = color;
    this.direction = direction;
    this.form = form;
    this.size = size;
    this.velocity = velocity;

  }

  @Override
  public String getlabel() {
    return this.label;
  }

  /**.
   * check the REP invariant
   */
  public void checkRep() {
    assertTrue(this.getSize() > 0);
    assertTrue(this.getAngle() <= 360 && this.getAngle() >= 0);
    assertTrue(this.getVelocity() >= 0);
  }


  // -----------------------------------getters and
  // setters-------------------------------------------------------------------

  public String getForm() {
    return form;
  }


  public String getColor() {
    return color;
  }


  public double getSize() {
    return this.size;
  }


  public double getBeginAngle() {
    return beginAngle;
  }


  public double getVelocity() {
    return velocity;
  }


  public boolean getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Planet) {
      Planet planet = (Planet) obj;
      if (planet.label.equals(this.label)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.getlabel().hashCode();
  }

}
