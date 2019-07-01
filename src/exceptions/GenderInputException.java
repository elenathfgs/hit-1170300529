package exceptions;

public class GenderInputException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;


  /**.
   * 
   * @param genderInput the wrong input info
   */
  public GenderInputException(String genderInput) {
    genderInput = genderInput.replace("\\s+", "");
    if (genderInput.length() == 0) {
      System.out.println("输入为空");
    } else {
      printStringBuffer.append("输入为" + genderInput);
      printStringBuffer.append(" 输入应该是M或F的字符");
    }

  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }

}
