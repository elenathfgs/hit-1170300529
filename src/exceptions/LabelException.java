package exceptions;

/**.
 * 关于label的异常
 * 
 * @author Administrator
 *
 */
public class LabelException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();
  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * the exception to be thrown when label input wrong
   * @param label the input label
   */
  public LabelException(String label) {
    if (label == null || "".equals(label.trim())) {
      printStringBuffer.append("输入的label不能为空");
    } else {
      printStringBuffer.append("输入为" + label + ":");
      if (label.contains(" ")) {
        printStringBuffer.append("label不能含有空格");
      } else {
        printStringBuffer.append("label只能含有英文字符和数字");
      }
    }



  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }
}
