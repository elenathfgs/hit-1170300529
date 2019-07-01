package exceptions;

public class ElementNameException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * the exception can be thrown that when a element name not match the form
   * @param elementName the wrong input name
   */
  public ElementNameException(String elementName) {
    // TODO Auto-generated constructor stub
    if (elementName.length() == 0) {
      printStringBuffer.append("读入的元素名称为空");
    } else {
      printStringBuffer.append("读入的元素名称为" + elementName);
      if (elementName.length() > 2) {
        printStringBuffer.append(" 长度大于2了，应该为小于等于2的英文字母");
      } else if (elementName.length() == 2 && elementName.charAt(1) <= 'Z'
          && elementName.charAt(1) >= 'A') {
        printStringBuffer.append(" 第二位应该是小写英文字母");
      } else if (elementName.charAt(0) <= 'z' && elementName.charAt(0) >= 'a') {
        printStringBuffer.append(" 第一位应该是大写英文字母");
      } else {
        printStringBuffer.append(" 应该是一位大写和一位小写的英文字母的组合");
      }
    }

  }

  public String getFeedBack() {
    return printStringBuffer.toString();
  }



}
