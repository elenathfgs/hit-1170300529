package exceptions;

/**.
 * SocialNetworkCircle中没有用Friend定义就在SocialTie中使用
 * 
 * @author Administrator
 *
 */
public class TieWithoutDefinitionException extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  public TieWithoutDefinitionException(String label) {
    printStringBuffer.append("用户未定义错误：");
    printStringBuffer.append(label + "还没有在Friends里面定义，不能建立社交关系");
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }

}
