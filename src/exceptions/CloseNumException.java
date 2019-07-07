package exceptions;

/**.
 * the exceptions that the input social closeShip number could have
 * 
 * @author Administrator
 *
 */
public class CloseNumException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * the exceptions that the input social closeShip number could have
   * @param number the wrong close number
   */
  public CloseNumException(String number) {
    // TODO Auto-generated constructor stub
    if (number.isEmpty()) {
      printStringBuffer.append("读入的亲密度为空");
    } else {
      printStringBuffer.append("输入为" + number);
      if (number.matches("\\d+.\\d*")) {
        double num = Double.parseDouble(number);
        if (num > 1 || num < 0) {
          printStringBuffer.append(",亲密度只能在0到1之间");
        }
      }
      if (number.matches("0.\\d\\d\\d\\d+")) {
        printStringBuffer.append(",小数位最多只能有三位");
      }
      printStringBuffer.append(",请输入合法的小数");
    }
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }

}
