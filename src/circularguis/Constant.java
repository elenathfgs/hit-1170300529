package circularguis;

import java.awt.Color;

/**.
 * some constant data used to draw GUI
 * 
 * @author Administrator
 *
 */
public class Constant {
  public static final int GRAPH_WIDTH = 800;
  public static final int GRAPH_HEIGHT = 800;
  public static final int FPS = 40; // 整型常量FPS保存帧速率数值

  /**.
   * chooseColor
   * @param color the String color
   * @return
   */
  public static Color chooseColor(String color) {
    switch (color) {
      case "Blue":
        return Color.blue;
      case "Dark":
        return Color.black;
      case "Red":
        return Color.red;
      case "Yellow":
        return Color.yellow;

      default:
        break;
    }

    return null;
  }
}

// Planet ::= <Earth,Solid,Blue,6378.137,1.49e8,29.783,CW,0>
// Planet ::= <Mercury,Solid,Dark,1378.137,1.49e7,69,CW,20.085>
// Planet ::= <Saturn,Liquid,Red,2378,1.49e6,2.33e5,CCW,39.21>
// Planet ::= <Jupiter,Gas,Blue,1637.007,2e8,30,CW,70>
// Planet ::= <Mars,Solid,Red,637.137,9.99e10,1000.93,CCW,110>
// Planet ::= <Neptune,Liquid,Yellow,6627.137,1.49e5,9293.05,CCW,360>
// Planet ::= <Uranus,Gas,Blue,637.137,1.49e11,1e5,CW,359>
// Planet ::= <Venus,Solid,Red,6378.137,1.49e20,203.24,CCW,181.23>
