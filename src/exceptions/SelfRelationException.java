package exceptions;

/**.
 * 出现指向自己的社交关系
 * 
 * @author Administrator
 *
 */
public class SelfRelationException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * 出现指向自己的社交关系
   * @param label the label of a object
   */
  public SelfRelationException(String label) {
    printStringBuffer.append("这是一个由" + label + "到" + label + "的关系,关系不能指向为自己");
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }
}
