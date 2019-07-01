package io;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;

class BufferedWriterTest {
  AtomStructure atomStructure = new AtomStructure();
  File atomFile = new File("src/../standard_input/AtomicStructure_Medium.txt");
  File outputAtomFile = new File("src/../outputFile/AtomicStructure.txt");
  
  StellarSystem stellarSystem = new StellarSystem();
  File stellarFile = new File("src/../standard_input/StellarSystem.txt");
  File outputStellarFile = new File("src/../outputFile/StellarSystem.txt");
  
  SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
  File socialFile = new File("src/../standard_input/SocialNetworkCircle.txt");
  File outputSocialFile = new File("src/../outputFile/SocialNetworkCircle.txt");

  @Test
  void testWriteAtomFile() throws Exception {
    atomStructure = SystemFactory.generateAtomStructure(
        CircularIO.readFile(new CircularChannelReader(), atomFile));
    long startTime = System.currentTimeMillis();
    CircularIO.writeFile(new CircularBufferedWriter(), 
        GetSystemInfo.getAtomInfo(atomStructure), outputAtomFile);
    long endTime = System.currentTimeMillis();
    System.out.println("写atom运行时间:" + (endTime - startTime) + "ms");
  }

  @Test
  void testWriteStellarFile() throws Exception {
    stellarSystem = SystemFactory.generateStellarSystem(
        CircularIO.readFile(new CircularChannelReader(), stellarFile));
    long startTime = System.currentTimeMillis();
    CircularIO.writeFile(new CircularBufferedWriter(), 
        GetSystemInfo.getStellarInfo(stellarSystem), outputStellarFile);   
    long endTime = System.currentTimeMillis();
    System.out.println("写stellar运行时间:" + (endTime - startTime) + "ms");
  }

  @Test
  void testWriteSocialFile() throws Exception {
    socialNetworkCircle = SystemFactory.generateSocialNetworkCircle(
        CircularIO.readFile(new CircularChannelReader(), socialFile));
    long startTime = System.currentTimeMillis();
    CircularIO.writeFile(new CircularBufferedWriter(), 
        GetSystemInfo.getSocialInfo(socialNetworkCircle), outputSocialFile);
    long endTime = System.currentTimeMillis();
    System.out.println("写stellar运行时间:" + (endTime - startTime) + "ms");
  }

}
