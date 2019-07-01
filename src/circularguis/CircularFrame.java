package circularguis;

import circularorbit.ConcreteCircularOrbit;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.Planet;

/**.
 * CircularFrame is a class that is used to draw the pictures of any circularorbit system, and use
 * JFrame and Jpanel to paint
 * 
 * @author Administrator
 *
 */
public class CircularFrame extends JFrame {
  private static final long serialVersionUID = 185816683665029226L;

  /**.
   * launch a frame to draw the GUI
   * @param concreteCircularOrbit the orbit system that this frame runs
   */
  public void launchFrame(ConcreteCircularOrbit concreteCircularOrbit) {
    setSize(Constant.GRAPH_HEIGHT, Constant.GRAPH_WIDTH);
    setLocation(100, 100);
    setVisible(true);
    DrawCircularOrbit drawCircularOrbit = new DrawCircularOrbit(concreteCircularOrbit);
    setContentPane(drawCircularOrbit);
    drawCircularOrbit.run();
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  /**.
   * implement the Runnable to make the picture redrawn every few millisecond
   * 
   * @author Administrator
   */
  class DrawCircularOrbit extends JPanel implements Runnable {
    @Override
    public void run() {
      while (true) {
        repaint();
        try {
          Thread.sleep(Constant.FPS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    @Override
    public void paint(Graphics graphics) {
      super.paint(graphics);
      this.drawTrace(graphics);
      this.move(graphics);
    }

    private static final long serialVersionUID = 1L;
    private static final int centralX = Constant.GRAPH_WIDTH / 2;
    private static final int centralY = Constant.GRAPH_HEIGHT / 2;
    private static final int firstTrackRadius = 50;
    private double startAngle = 0;
    // the start angle of all objects in every time that draw the graph, can grow when time changed
    // RI: should be >= 0 and <= 360
    private double speed = 5;
    // the grow speed of start angle
    // RI: should be >0

    private final ConcreteCircularOrbit concreteCircularOrbit;
    // the concreteCircularOrbit waiting to be drawn
    // RI: should not be null

    public DrawCircularOrbit(ConcreteCircularOrbit concreteCircularOrbit) {
      this.concreteCircularOrbit = concreteCircularOrbit;
    }



    /**.
     * draw all the traces in this system
     */
    public void drawTrace(Graphics graphics) {
      int growth = 300 / concreteCircularOrbit.getTrackNum();
      int trackNum = concreteCircularOrbit.getTrackNum();
      for (int i = 0; i < trackNum; i++) {
        graphics.drawOval(centralX - (firstTrackRadius + growth * i),
            centralY - (firstTrackRadius + growth * i), (firstTrackRadius + growth * i) * 2,
            (firstTrackRadius + growth * i) * 2);
      }
    }

    public void move(Graphics graphics) {
      if (concreteCircularOrbit instanceof SocialNetworkCircle) {
        this.drawSocialObj(graphics, startAngle);
        startAngle += speed;
        return;
      }
      if (concreteCircularOrbit instanceof StellarSystem) {
        this.drawStellarObj(graphics, startAngle);
        startAngle += speed;
        return;
      }
      this.drawObjects(graphics, startAngle);
      startAngle += speed;
    }

    /**.
     * draw the objects in this system
     * 
     * @param graphics the tool to draw the picture
     * @param startAngle the angle that all the objects have already moved
     */
    public void drawObjects(Graphics graphics, double startAngle) {
      int trackNum = concreteCircularOrbit.getTrackNum();
      int growth = 300 / trackNum;
      int centralObjSize = 30;
      graphics.setColor(Color.RED);
      graphics.fillOval(centralX - centralObjSize / 2, centralY - centralObjSize / 2,
          centralObjSize, centralObjSize);
      graphics.setColor(Color.black);
      for (int i = 0; i < trackNum; i++) {
        int objNum = concreteCircularOrbit.getTrack(i).getObjNum();
        double divide = 360.0 / (double) objNum;// to put all the objects evenly in the track
        double thisAngle = 0 + startAngle * (1.0 / (i + 1));// the position to put this object
        // (1.0/(i+1)) means the speed of objects can slow down when track radius is bigger
        double thisRadius = firstTrackRadius + growth * i;
        // graphics.setColor(randomColor());
        int size = growth / 3;
        for (int j = 0; j < objNum; j++) {
          graphics.fillOval((int) (centralX + thisRadius * Math.cos(toAngle(thisAngle)) - size / 2),
              (int) (centralY + thisRadius * Math.sin(toAngle(thisAngle)) - size / 2), size, size);
          thisAngle += divide;
        }
      }
    }

    /**.
     * draw the objects of SocialNetworkCircle
     * 
     * @param graphics the tool
     * @param startAngle the angle to start
     */
    public void drawSocialObj(Graphics graphics, double startAngle) {
      if (concreteCircularOrbit instanceof SocialNetworkCircle) {
        SocialNetworkCircle socialNetworkCircle = (SocialNetworkCircle) concreteCircularOrbit;
        Map<String, Map.Entry<Integer, Integer>> objLocation =
            new HashMap<String, Map.Entry<Integer, Integer>>();
        int trackNum = socialNetworkCircle.getTrackNum();
        int growth = 300 / trackNum; // the interval of tracks
        int centralObjSize = 30;
        graphics.setColor(Color.RED);
        graphics.fillOval(centralX - centralObjSize / 2, centralY - centralObjSize / 2,
            centralObjSize, centralObjSize);
        graphics.setColor(Color.black);
        for (int i = 0; i < trackNum; i++) {
          Iterator<PhysicalObject> objIterator =
              socialNetworkCircle.getTrack(i).getObjInTrack().iterator();
          int objNum = socialNetworkCircle.getTrack(i).getObjNum();
          double divide = 360.0 / (double) objNum;// to put all the objects evenly in the track
          double thisAngle = 0 + startAngle * (1.0 / (i + 1));// the position to put this object
          // (1.0/(i+1)) means the speed of objects can slow down when track radius is bigger
          double thisRadius = firstTrackRadius + growth * i;
          graphics.setColor(randomColor());
          int size = growth / 3; // make the size of objects matches the interval of tracks
          while (objIterator.hasNext()) {
            Friend friend = (Friend) objIterator.next();
            int x = (int) (centralX + thisRadius * Math.cos(toAngle(thisAngle)) - size / 2);
            int y = (int) (centralY + thisRadius * Math.sin(toAngle(thisAngle)) - size / 2);
            graphics.fillOval(x, y, size, size);
            thisAngle += divide;
            objLocation.put(friend.getlabel(),
                new AbstractMap.SimpleEntry<Integer, Integer>(x + size / 2, y + size / 2));
            // store the location of each object, used to draw the relation line
          }
        }
        objLocation.put(socialNetworkCircle.getCentralObject().getLabel(),
            new AbstractMap.SimpleEntry<Integer, Integer>(centralX, centralY));
        Iterator<Map.Entry<Friend, Friend>> relationIterator =
            socialNetworkCircle.relationIterator();
        while (relationIterator.hasNext()) {
          Map.Entry<Friend, Friend> entry = relationIterator.next();
          Map.Entry<Integer, Integer> locationEntry1 = objLocation.get(entry.getKey().getlabel());
          Map.Entry<Integer, Integer> locationEntry2 = objLocation.get(entry.getValue().getlabel());
          if (locationEntry1 != null && locationEntry2 != null) {
            graphics.drawLine(locationEntry1.getKey(), locationEntry1.getValue(),
                locationEntry2.getKey(), locationEntry2.getValue());
          }
        }
      }
    }

    /**.
     * draw the objects of drawStellarObj
     * 
     * @param graphics the tool
     * @param startAngle the angle to start
     */
    public void drawStellarObj(Graphics graphics, double startAngle) {
      if (concreteCircularOrbit instanceof StellarSystem) {
        StellarSystem stellarSystem = (StellarSystem) concreteCircularOrbit;
        int trackNum = stellarSystem.getTrackNum();
        int growth = 300 / trackNum;
        int centralObjSize = 30;
        graphics.setColor(Color.RED);
        graphics.fillOval(centralX - centralObjSize / 2, centralY - centralObjSize / 2,
            centralObjSize, centralObjSize);
        graphics.setColor(Color.black);
        for (int i = 0; i < trackNum; i++) {
          double thisRadius = firstTrackRadius + growth * i;
          // graphics.setColor(randomColor());
          int size = growth / 3;
          Iterator<PhysicalObject> iterator = stellarSystem.getTrack(i).getObjInTrack().iterator();
          while (iterator.hasNext()) {
            Planet nextPlanet = (Planet) iterator.next();
            double thisAngle = nextPlanet.getAngle()
                + startAngle * (1.0 / (i + 1)) * (nextPlanet.getDirection() ? 1 : -1);
            // the position to put this object
            // (1.0/(i+1)) means the speed of objects can slow down when track radius is bigger
            graphics.setColor(Constant.chooseColor(nextPlanet.getColor()));
            graphics.fillOval(
                (int) (centralX + thisRadius * Math.cos(toAngle(thisAngle)) - size / 2),
                (int) (centralY + thisRadius * Math.sin(toAngle(thisAngle)) - size / 2), size,
                size);
          }
        }
      }
    }

    public double toAngle(double angle) {
      angle = angle % 360;
      return 2 * Math.PI * (angle / 360);
    }

    public Color randomColor() {
      Random random = new Random();
      int r = random.nextInt(256);
      int g = random.nextInt(256);
      int b = random.nextInt(256);
      return new Color(r, g, b);
    }

  }
}
