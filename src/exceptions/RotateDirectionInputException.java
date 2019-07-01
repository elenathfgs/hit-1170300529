package exceptions;

/**.
 * the exception that the input of a planet rotate direction could have
 * 
 * @author Administrator
 *
 */
public class RotateDirectionInputException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * the exception that the input of a planet rotate direction could have
   * @param inputString the wrong input
   */
  public RotateDirectionInputException(String inputString) {
    if (inputString.length() == 0) {
      printStringBuffer.append("输入的方向为空");
    } else {
      printStringBuffer.append("输入为" + inputString + "只能为CCW(逆时针)或CW(顺时针)");
    }
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }
}
