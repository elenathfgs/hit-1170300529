package applications;

import apis.CircularOrbitApis;

import circularguis.CircularFrame;
import circularorbit.AtomStructure;
import circularorbit.ConcreteCircularOrbit;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.RotateDirectionInputException;
import exceptions.SameLabelException;
import exceptions.SelfRelationException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logger.MyLogger;
import physicalobject.Electron;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.Planet;
import track.Track;




public class Actions {
  private static Scanner scanner;

  /**.
   * @return the choose integer number
   * @throws IOException may not find the file 
   */
  public static int chooseSystem() throws IOException {
    scanner = new Scanner(System.in);
    int choose = scanner.nextInt();
    scanner.nextLine();
    while (choose > 3 || choose < 1) {
      System.out.println("请输入1-3之间的整数");
      choose = scanner.nextInt();
      scanner.nextLine();
    }

    if (choose == 1) {
      MyLogger._LOG.info("user选择了原子模型系统");
    } else if (choose == 2) {
      MyLogger._LOG.info("user选择了行星系统");
    } else {
      MyLogger._LOG.info("user选择了社交关系系统");
    }

    return choose;
  }

  /**.
   * choose a action to take
   * @param orbitSystem the system which takes the certain action
   * @throws IOException may not find the file
   * @throws ParseException may throw ParseException
   * @throws RotateDirectionInputException may throw RotateDirectionInputException
   * @throws SameLabelException 
   */
  public static void chooseAction(ConcreteCircularOrbit orbitSystem)
      throws IOException, ParseException, RotateDirectionInputException, SameLabelException {
    int choose = scanner.nextInt();
    scanner.nextLine();
    if (choose <= 6 && choose >= -1) {
      Actions.commonAction(orbitSystem, choose);
    } else {
      if (orbitSystem instanceof AtomStructure) {
        AtomStructure atomStructure = (AtomStructure) orbitSystem;
        Actions.atomAction(atomStructure, choose);
      } else if (orbitSystem instanceof StellarSystem) {
        StellarSystem stellarSystem = (StellarSystem) orbitSystem;
        Actions.stellarSystemAction(stellarSystem, choose);
      } else if (orbitSystem instanceof SocialNetworkCircle) {
        SocialNetworkCircle socialNetworkCircle = (SocialNetworkCircle) orbitSystem;
        Actions.socialNetworkAction(socialNetworkCircle, choose);
      }
    }

  }

  /**.
   * the actions that all kinds of circularSystem could take
   * 
   * @param orbitSystem the system to take actions
   * @param choose the action that the user chose
   * @throws IOException may not find the file
   * @throws ParseException may throw ParseException
   * @throws RotateDirectionInputException may throw RotateDirectionInputException
   * @throws SameLabelException 
   */
  public static void commonAction(ConcreteCircularOrbit orbitSystem, int choose)
      throws IOException, ParseException, RotateDirectionInputException, SameLabelException {
    int index;
    switch (choose) {
      case -1:
        System.out.println("请输入筛选条件: 1.按时间筛选 2.按类筛选 3.按方法筛选");
        int chooseFilter = scanner.nextInt();
        scanner.nextLine();
        while (chooseFilter != 1 && chooseFilter != 2 && chooseFilter != 3) {
          System.out.println("请输入1到3之间的整数");
          chooseFilter = scanner.nextInt();
          scanner.nextLine();
        }
        logAction(chooseFilter);
        break;

      case 0:
        new CircularFrame().launchFrame(orbitSystem);

        MyLogger._LOG.info("可视化模拟输出");
        break;
      case 1:
        System.out.println("请输入你要加入到哪个轨道");
        index = scanner.nextInt();
        scanner.nextLine();
        while (index > orbitSystem.getTrackNum() || index <= 0) {
          if (index > orbitSystem.getTrackNum()) {
            System.out.println("没有这条轨道");
          } else {
            System.out.println("输入的轨道序号不合法(应该大于0)");
          }
          index = scanner.nextInt();
          scanner.nextLine();
        }
        // --------------------------------------
        if (orbitSystem instanceof AtomStructure) {
          int num = -1;
          System.out.println("请输入要加入的原子的序号");
          num = scanner.nextInt();
          scanner.nextLine();
          while (num < 0) {
            System.out.println("序号不能为负");
            num = scanner.nextInt();
            scanner.nextLine();
          }
          Electron electron = new Electron(num);
          orbitSystem.setObjAtTrack(electron, orbitSystem.getTrack(index - 1));

          MyLogger._LOG.info("电子" + electron.getlabel() + "加入到了第" + index + "个track");
          break;
        }
        if (orbitSystem instanceof StellarSystem) {
          String newStellar;
          final String planetInput = "(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)";
          System.out
              .println("请输入行星的各个参数(type, form, color, size, beginAngle, velocity, direction)");
          newStellar = scanner.nextLine();
          while (!Pattern.matches(planetInput, newStellar)) {
            System.out.println("输入不符合格式要求，请重新输入");
            newStellar = scanner.nextLine();
          }
          final Pattern planetPattern = Pattern.compile(planetInput);
          Matcher planetMatcher = planetPattern.matcher(newStellar);
          planetMatcher.find();
          if (!planetMatcher.group(7).equals("CCW") && !planetMatcher.group(7).equals("CW")) {
            throw new RotateDirectionInputException(planetMatcher.group(7));
          }
          Planet planet = new Planet(planetMatcher.group(1), planetMatcher.group(2),
              planetMatcher.group(3), Double.parseDouble(planetMatcher.group(4)),
              Double.parseDouble(planetMatcher.group(5)),
              Double.parseDouble(planetMatcher.group(6)),
              planetMatcher.group(7).equalsIgnoreCase("CW") ? true : false);
          try {
            orbitSystem.addObject(planet);
          } catch (SameLabelException e) {
            // TODO Auto-generated catch block
            MyLogger._LOG.warning(e.getFeedback());
            e.printStackTrace();
          }
          orbitSystem.setObjAtTrack(planet, orbitSystem.getTrack(index - 1));
          MyLogger._LOG.info("行星" + planet.getlabel() + "加入到了第" + index + "个track");
          break;
        }
        if (orbitSystem instanceof SocialNetworkCircle) {
          String newFriend;
          final String friendInput = "(.*?),(.*?),(.*?)";
          System.out.println("请输入好友的各个参数(name, age, gender)");
          newFriend = scanner.nextLine();
          while (!Pattern.matches(friendInput, newFriend)) {
            System.out.println("输入不符合格式要求，请重新输入");
            newFriend = scanner.nextLine();
          }
          final Pattern friendPattern = Pattern.compile(friendInput);
          Matcher friendMatcher = friendPattern.matcher(newFriend);
          friendMatcher.find();
          Friend friend =
              new Friend(friendMatcher.group(1), Integer.parseInt(friendMatcher.group(2)),
                  friendMatcher.group(3).equals("M") ? true : false);
          try {
            orbitSystem.addObject(friend);
          } catch (SameLabelException e) {
            // TODO Auto-generated catch block
            MyLogger._LOG.warning(e.getFeedback());
            e.printStackTrace();
          }
          orbitSystem.setObjAtTrack(friend, orbitSystem.getTrack(index - 1));

          MyLogger._LOG.info("用户" + friend.getlabel() + "加入到了第" + index + "个track");
          break;
        }
        break;

      case 2:
        System.out.println("请输入轨道半径");
        double radius = scanner.nextDouble();
        scanner.nextLine();
        if (radius <= 0) {
          System.out.println("轨道半径应该大于0！！！");
          break;
        }
        Track track = new Track(radius);
        try {
          orbitSystem.addTrack(track);
        } catch (SameLabelException e) {
          // TODO Auto-generated catch block
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
        }

        MyLogger._LOG.info("加入了半径为 " + track.getRadius() + " 的轨道");
        // orbitSystem.show();
        break;
      case 3:
        System.out.println("请输入要删除哪个轨道（从小到大第几个）");
        index = scanner.nextInt();
        scanner.nextLine();
        while (index > orbitSystem.getTrackNum() || index <= 0) {
          if (index > orbitSystem.getTrackNum()) {
            System.out.println("没有这条轨道");
          } else {
            System.out.println("输入的轨道序号不合法(应该大于0)");
          }
          index = scanner.nextInt();
          scanner.nextLine();
        }
        orbitSystem.deleteTrack(index - 1);

        MyLogger._LOG.info("删除了第 " + index + " 条轨道");
        // orbitSystem.show();
        break;
      case 4:
        System.out.println("请输入你要删除物体的轨道号");
        index = scanner.nextInt();
        scanner.nextLine();
        while (index > orbitSystem.getTrackNum() || index <= 0) {
          if (index > orbitSystem.getTrackNum()) {
            System.out.println("没有这条轨道");
          } else {
            System.out.println("输入的轨道序号不合法(应该大于0)");
          }
          index = scanner.nextInt();
          scanner.nextLine();
        }
        Track track2 = orbitSystem.getTrack(index - 1);
        System.out.println("请输入你要删除物体的名字");
        String name = scanner.nextLine();
        if (!track2.hasObject(name)) {
          System.out.println("这条轨道上没有这个物体");
          break;
        } else {
          orbitSystem.deleteObject(name);

          MyLogger._LOG.info("删除了第 " + index + " 条轨道上的物体 " + name);
          break;
        }
      case 5:
        System.out.println("请输入要删除的轨道号");
        index = scanner.nextInt();
        scanner.nextLine();
        while (index > orbitSystem.getTrackNum() || index <= 0) {
          if (index > orbitSystem.getTrackNum()) {
            System.out.println("没有这条轨道");
          } else {
            System.out.println("输入的轨道序号不合法(应该大于0)");
          }
          index = scanner.nextInt();
          scanner.nextLine();
        }
        orbitSystem.deleteTrack(index - 1);

        MyLogger._LOG.info("删除了第 " + index + " 条轨道上的所有物体 ");
        // orbitSystem.show();
        break;
      case 6:
        double entropy = CircularOrbitApis.getObjectDistributionEntropy(orbitSystem);
        System.out.println("这个轨道系统物体分布的熵值为: " + entropy);

        MyLogger._LOG.info("计算出系统的熵值为" + entropy);
        break;
      default:
        System.out.println("请输入正确的选择序号");
        break;
    }
  }

  /**.
   * the actions that AtomStructure could take
   * 
   * @param atomStructure : the system to take actions
   * @param choose : the action that the user chose
   * @throws IOException may not find file
   * @throws SameLabelException SameLabelException
   */
  public static void atomAction(AtomStructure atomStructure, int choose) 
      throws IOException, SameLabelException {
    switch (choose) {
      case 7:
        System.out.println("请输入要跃迁的电子的序号");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index < 0 || index > atomStructure.getObjNum()) {
          System.out.println("输入的电子序号不合法 ");
          break;
        }
        System.out.println("请输入要跃迁到的轨道的序号");
        int trackIndex = scanner.nextInt();
        scanner.nextLine();
        while (trackIndex > atomStructure.getTrackNum() || trackIndex <= 0) {
          if (trackIndex > atomStructure.getTrackNum()) {
            System.out.println("没有这条轨道");
          } else {
            System.out.println("输入的轨道序号不合法(应该大于0)");
          }
          trackIndex = scanner.nextInt();
          scanner.nextLine();
        }
        Electron electron = (Electron) atomStructure.getObject(String.valueOf(index));
        electron.getTrack().deleteObj(String.valueOf(index));
        atomStructure.setObjAtTrack(electron, atomStructure.getTrack(trackIndex - 1));

        MyLogger._LOG.info("电子 " + electron.getlabel() + " 从原来的轨道跃迁到了第" + trackIndex + "条轨道");
        // atomStructure.show();
        break;
      default:
        System.out.println("请输入正确的选择序号");
        break;
    }
  }

  /**.
   * the actions that SocialNetworkCircle could take
   * @param socialNetworkCircle : the system to take actions
   * @param choose : the action that the user chose
   * @throws IOException may not find file
   */
  public static void socialNetworkAction(SocialNetworkCircle socialNetworkCircle, int choose)
      throws IOException {
    switch (choose) {
      case 7:
        for (int i = 0; i < socialNetworkCircle.getTrackNum(); i++) {
          System.out.print("第" + (i + 1) + "级好友：" + "{ ");
          for (PhysicalObject friend : socialNetworkCircle.getTrack(i).getObjInTrack()) {
            System.out.print(friend.getlabel() + " ");
          }
          System.out.print("}");
          System.out.println();
        }

        MyLogger._LOG.info("用户查看了各个用户的位置");
        break;
      case 8:
        System.out.println("请输入某个处于第一条轨道的好友名字");
        String name = scanner.nextLine();
        int distribution =
            socialNetworkCircle.countDistribution(socialNetworkCircle.getFriend(name));
        if (distribution == -1) {
          break;
        }
        System.out.println("一级好友" + name + "的信息扩散度为:  " + distribution);

        MyLogger._LOG.info("计算出第一条轨道上的用户" + name + "的信息扩散度");
        break;
      case 9:
        System.out.println("请输入你要建立的关系（名字,名字,好感度）");
        String relationString = scanner.nextLine();
        String inputPattern = "([a-zA-Z]+),([a-zA-Z]+),(.*)";
        if (!Pattern.matches(inputPattern, relationString)) {
          System.out.println("输入的关系格式不合法，请重新输入");
          relationString = scanner.nextLine();
        }
        Pattern relationPattern = Pattern.compile(inputPattern);
        Matcher relationMatcher = relationPattern.matcher(relationString);
        if (!relationMatcher.find()) {
          System.out.println("没有匹配到相应用户");
        }
        if (Double.parseDouble(relationMatcher.group(3)) > 1
            || Double.parseDouble(relationMatcher.group(3)) < 0) {
          System.out.println("关系只能在0和1之间");
        }
        try {
          socialNetworkCircle.addRelation(socialNetworkCircle.getFriend(relationMatcher.group(1)),
              socialNetworkCircle.getFriend(relationMatcher.group(2)),
              Double.parseDouble(relationMatcher.group(3)));
        } catch (NumberFormatException e) {
          System.out.println(e.getCause());
          e.printStackTrace();
        } catch (SelfRelationException e) {
          MyLogger._LOG.warning(e.getFeedback());
          e.printStackTrace();
        }

        MyLogger._LOG.info("增加了一条从用户 " + relationMatcher.group(1) + " 到用户 "
            + relationMatcher.group(2) + " 的亲密度为" + relationMatcher.group(3) + " 的关系");
        // socialNetworkCircle.show();
        break;

      case 10:
        System.out.println("请输入两个用户的名字(名字,名字)");
        String input = scanner.nextLine();
        String[] users = input.split(",");
        while (users.length != 2) {
          System.out.println("输入的格式不合法");
          input = scanner.nextLine();
          users = input.split(",");
        }
        int distance = CircularOrbitApis.getLogicalDistance(socialNetworkCircle,
            socialNetworkCircle.getObject(users[0]), socialNetworkCircle.getObject(users[1]));
        System.out.println("用户" + users[0] + "和用户" + users[1] + "的逻辑距离为:" + distance);

        MyLogger._LOG.info("计算出用户" + users[0] + "和用户" + users[1] + "的逻辑距离为:" + distance);
        break;
      default:
        break;
    }
  }

  /**.
   * the actions that stellarSystem could take
   * 
   * @param stellarSystem : the system to take actions
   * @param choose : the action that the user chose
   * @throws IOException may not find the files
   */
  public static void stellarSystemAction(StellarSystem stellarSystem, int choose)
      throws IOException {
    switch (choose) {
      case 7:
        System.out.println("请输入系统运行的时间");
        double t = scanner.nextDouble();
        scanner.nextLine();
        Iterator<PhysicalObject> planetIterator = stellarSystem.objIterator();
        while (planetIterator.hasNext()) {
          Planet planet = (Planet) planetIterator.next();
          stellarSystem.moveByTime(planet, t);
        }

        MyLogger._LOG.info("计算出系统运行时间为" + t + "后的各个行星的位置");
        // stellarSystem.show();
        break;
      case 8:
        System.out.println("请输入两个行星的名字(行星1,行星2)");
        String input = scanner.nextLine();
        String[] users = input.split(",");
        while (users.length != 2) {
          System.out.println("输入的格式不合法");
          input = scanner.nextLine();
          users = input.split(",");
        }
        double distance = stellarSystem.calculateDistance(
            (Planet) stellarSystem.getObject(users[0]), (Planet) stellarSystem.getObject(users[1]));
        System.out.println("两个行星之间的距离为: " + distance);

        MyLogger._LOG.info("计算出行星" + users[0] + "到行星" + users[1] + "之间的距离为" + distance);
        break;
      case 9:
        new CircularFrame().launchFrame(stellarSystem);

        MyLogger._LOG.info("可视化模拟行星运动");
        break;
      default:
        break;
    }
  }

  private static void logAction(int choose) throws ParseException, IOException {
    switch (choose) {
      case 1:
        System.out.println("请输入开始的时间(格式:(yyyy-MM-dd HH:mm:ss 例:2019-05-13 16:23:25)");
        String input = scanner.nextLine();
        Pattern formatTimePattern =
            Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d) (\\d\\d):(\\d\\d):(\\d\\d)");
        Matcher formatTimeMatcher = formatTimePattern.matcher(input);
        while (!formatTimeMatcher.find()) {
          System.out.println("请按时间格式输入合法的时间 例:2019-05-13 16:23:25");
          input = scanner.nextLine();
          formatTimeMatcher = formatTimePattern.matcher(input);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = simpleDateFormat.parse(input);

        System.out.println("请输入结束的时间(格式:(yyyy-MM-dd HH:mm:ss 例:2019-05-13 16:23:25)");
        input = scanner.nextLine();
        formatTimeMatcher = formatTimePattern.matcher(input);
        while (!formatTimeMatcher.find()) {
          System.out.println("请按时间格式输入合法的时间 例:2019-05-13 16:23:25");
          input = scanner.nextLine();
          formatTimeMatcher = formatTimePattern.matcher(input);
        }
        Date endDate = simpleDateFormat.parse(input);

        File file = new File("src/../log/log.txt");
        RandomAccessFile randomAF = new RandomAccessFile(file, "r");
        String buffer = "";
        Date thisLogDate;
        StringBuffer outputLog = new StringBuffer();

        while ((buffer = randomAF.readLine()) != null) {
          buffer = new String(buffer.getBytes("8859_1"), "UTF-8");
          if ((thisLogDate = MyLogger.getSimpleFormatTime(buffer)) != null) {
            if (thisLogDate.compareTo(endDate) <= 0 && thisLogDate.compareTo(beginDate) >= 0) {
              if (outputLog.length() == 0) {
                outputLog.append("下面是搜索到的在" + beginDate + "到" + endDate + "时间段内的内容\n");
              }
              outputLog.append(new String(randomAF.readLine().getBytes("8859_1"), "UTF-8"));
              outputLog.append("\n");
            }
          }
        }
        System.out.println(outputLog.toString());
        break;
      case 2:
        System.out.println("请输入要查询的类的名字");
        String classString = scanner.nextLine();
        classString = classString.replace("\\s+", "");

        file = new File("src/../log/log.txt");
        randomAF = new RandomAccessFile(file, "r");
        buffer = "";
        outputLog = new StringBuffer();

        while ((buffer = randomAF.readLine()) != null) {
          buffer = new String(buffer.getBytes("8859_1"), "UTF-8");
          if (buffer.contains(classString)) {
            if (outputLog.length() == 0) {
              outputLog.append("下面是搜索到的关于类" + classString + "内容\n");
            }
            outputLog.append(new String(randomAF.readLine().getBytes("8859_1"), "UTF-8"));
            outputLog.append("\n");
          }
        }
        System.out.println(outputLog.toString());
        break;
      case 3:
        System.out.println("请输入要查询的方法的名字");
        String methodString = scanner.nextLine();
        methodString = methodString.replace("\\s+", "");

        file = new File("src/../log/log.txt");
        randomAF = new RandomAccessFile(file, "r");
        buffer = "";
        outputLog = new StringBuffer();

        while ((buffer = randomAF.readLine()) != null) {
          buffer = new String(buffer.getBytes("8859_1"), "UTF-8");
          if (buffer.contains(methodString)) {
            if (outputLog.length() == 0) {
              outputLog.append("下面是搜索到的关于类" + methodString + "内容\n");
            }
            outputLog.append(new String(randomAF.readLine().getBytes("8859_1"), "UTF-8"));
            outputLog.append("\n");
          }
        }
        System.out.println(outputLog.toString());
        break;

      default:
        System.out.println("输入非法请重新输入");
        break;
    }
  }

}
