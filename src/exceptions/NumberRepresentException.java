package exceptions;

/**.
 * an exception class being thrown when the input number from file is illegal
 * 
 * @author Administrator
 *
 */
public class NumberRepresentException extends Exception {
  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  private final StringBuffer printStringBuffer = new StringBuffer();

  public NumberRepresentException() {
    // TODO Auto-generated constructor stub
  }

  /**.
   * the exception being thrown when the input number from file is illegal
   * @param numberString the wrong input number
   */
  public NumberRepresentException(String numberString) {
    printStringBuffer.append("输入的数字为：" + numberString);
    if (numberString.length() == 0) {
      printStringBuffer.append("输入为空");
    } else {
      if (numberString.contains("e")) {
        printStringBuffer.append("数字小于10000,不应该用科学记数法表示");
      } else {
        printStringBuffer.append("数字大于10000,应该用科学记数法表示");
      }
    }
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }

}
