package exceptions;

/**.
 * 存在标签完全一样的元素
 * 
 * @author Administrator
 *
 */
public class SameLabelException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * 存在标签完全一样的元素
   * @param label the repeated label
   */
  public SameLabelException(String label) {
    // TODO Auto-generated constructor stub
    printStringBuffer.append("输入为" + label + ", 这个系统里面已经有这个物体了");
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }
}
