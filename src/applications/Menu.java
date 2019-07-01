package applications;

public class Menu {
  public static void showWelcome() {
    System.out.println("欢迎使用多轨道系统");
    System.out.println("请选择系统的类型：1.原子模型系统   2.行星系统  3.社交关系网络");
  }

  /**.
   * show the CommonMenu
   */
  public static void showCommonMenu() {
    System.out.println("-1. 筛选查看日志");
    System.out.println("0. 可视化展示");
    System.out.println("1. 增加物体");
    System.out.println("2. 增加轨道");
    System.out.println("3. 删减轨道");
    System.out.println("4. 删除某条轨道上的某个物体");
    System.out.println("5. 删除整条轨道");
    System.out.println("6, 计算轨道物体分布熵值");
  }


  /**.
   * show the StellarSystem Menu
   */
  public static void showStellarSystemMenu() {
    System.out.println("7. 计算运行t时刻后各个行星的位置");
    System.out.println("8. 计算两个行星（恒星与行星）之间的物理距离");
    System.out.println("9. 可视化模拟行星运动");
  }

  public static void showAtomMenu() {
    System.out.println("7. 电子跃迁");
  }

  /**.
   * show the SocialNetwork Menu
   */
  public static void showSocialNetworkMenu() {
    System.out.println("7. 给出各个用户的位置");
    System.out.println("8. 计算某个处于第一条轨道上的好友的信息扩散度");
    System.out.println("9. 增加一条社交关系");
    System.out.println("10 .计算两个轨道上用户间的逻辑距离");
  }

}
