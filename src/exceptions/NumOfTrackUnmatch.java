package exceptions;

/**.
 * NumberOfTracks 定义的轨道数量和 NumberOfElectron 中使用的轨道数量不一致
 * 
 * @author Administrator
 *
 */
public class NumOfTrackUnmatch extends Exception {
  private final StringBuffer printStringBuffer = new StringBuffer();

  /**.
   * 
   */
  private static final long serialVersionUID = 1L;

  /**.
   * NumberOfTracks 定义的轨道数量和 NumberOfElectron 中使用的轨道数量不一致
   * @param realTrackNum the real track number
   * @param assertTrackNum the asserted track number
   */
  public NumOfTrackUnmatch(String realTrackNum, String assertTrackNum) {
    // TODO Auto-generated constructor stub
    printStringBuffer
        .append("真实使用的轨道数量为：" + realTrackNum + ", 而定义的轨道数量为" + assertTrackNum + ", 两者不匹配");
  }

  public String getFeedback() {
    return printStringBuffer.toString();
  }
}
