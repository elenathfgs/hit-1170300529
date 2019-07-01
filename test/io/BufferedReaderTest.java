package io;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;

class BufferedReaderTest {
  AtomStructure atomStructure = new AtomStructure();
  File atomFile = new File("src/../standard_input/AtomicStructure_Medium.txt");
  
  StellarSystem stellarSystem = new StellarSystem();
  File stellarFile = new File("src/../standard_input/StellarSystem_HUGE.txt");
  
  SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
  File socialFile = new File("src/../standard_input/SocialNetworkCircle.txt");

  @Test
  void testReadAtomFile() throws Exception {
    long startTime = System.currentTimeMillis();
    atomStructure = SystemFactory.generateAtomStructure(CircularIO.readFile(new CircularChannelReader(), atomFile));
    long endTime = System.currentTimeMillis();
    System.out.println("读atom运行时间:" + (endTime - startTime) + "ms");
  }

  @Test
  void testReadStellarFile() throws Exception {
    long startTime = System.currentTimeMillis();
    stellarSystem = SystemFactory.generateStellarSystem(CircularIO.readFile(new CircularChannelReader(), stellarFile));
    long endTime = System.currentTimeMillis();
    System.out.println("读stellar运行时间:" + (endTime - startTime) + "ms");
  }

  @Test
  void testReadsocialFile() throws Exception {
    long startTime = System.currentTimeMillis();
    socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(CircularIO.readFile(new CircularChannelReader(), socialFile));
    long endTime = System.currentTimeMillis();
    System.out.println("读social运行时间:" + (endTime - startTime) + "ms");
  }

}
