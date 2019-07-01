package io;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Test;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;

class IoSpeedTest {
  File atomFile = new File("src/../standard_input/AtomicStructure_Medium.txt");
  
  File stellarFile = new File("src/../standard_input/StellarSystem_HUGE.txt");
  File stellarBufferedOutputFile = 
      new File("src/../outputFile/StellarSystem_HUGE_Buffered_output.txt");
  File stellarChannelOutputFile = 
      new File("src/../outputFile/StellarSystem_HUGE_Channel_output.txt");
  File stellarStreamOutputFile = 
      new File("src/../outputFile/StellarSystem_HUGE_Stream_output.txt");
  
  File socialFile = new File("src/../standard_input/SocialNetworkCircle_HUGE.txt");
  File socialBufferedOutputFile = 
      new File("src/../outputFile/SocialNetworkCircle_HUGE_Buffered_output.txt");
  File socialChannelOutputFile = 
      new File("src/../outputFile/SocialNetworkCircle_HUGE_Channel_output.txt");
  File socialStreamOutputFile = 
      new File("src/../outputFile/SocialNetworkCircle_HUGE_Stream_output.txt");

  @Test
  void bufferedReaderSpeedTest() throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(socialFile));
    StringBuilder sb = new StringBuilder();
    
    //read
    long readStartTime = System.currentTimeMillis();
    String buffer;
    while ((buffer = bufferedReader.readLine()) != null) {
      sb.append(buffer);
    }
    long readEndTime = System.currentTimeMillis();
    System.out.println("BufferedReader读HUGE运行时间:" + (readEndTime - readStartTime) + "ms");
    
    //write
    long writeStartTime = System.currentTimeMillis();
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(socialBufferedOutputFile));
    bufferedWriter.write(sb.toString());
    long writeEndTime = System.currentTimeMillis();
    System.out.println("BufferedWriter写HUGE运行时间:" + (writeEndTime - writeStartTime) + "ms");
    
    bufferedWriter.close();
    bufferedReader.close();
    
  }
  
  @Test
  void channelMappedNioTest() throws IOException {
    Path inPath = Paths.get("src/../standard_input/StellarSystem_HUGE.txt");
    Path outPath = Paths.get("src/../outputFile/StellarSystem_HUGE_Channel_output.txt");
    FileChannel inchannel = 
        FileChannel.open(inPath,StandardOpenOption.READ);
    FileChannel outChannel = 
        FileChannel.open(outPath,
            StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
    MappedByteBuffer inMappedByteBuffer = inchannel.map(MapMode.READ_ONLY, 0, inchannel.size());
    MappedByteBuffer outMappedByteBuffer = outChannel.map(MapMode.READ_WRITE, 0, inchannel.size());
    byte[] bytes = new byte[inMappedByteBuffer.limit()];
    
    //read
    long readStartTime = System.currentTimeMillis();
    inMappedByteBuffer.get(bytes);
    String inputString = new String(bytes);
    
    long readEndTime = System.currentTimeMillis();
    
    //write
    long writeStartTime = System.currentTimeMillis();
    outMappedByteBuffer.put(bytes);
    long writeEndTime = System.currentTimeMillis();
    
    System.out.println("channelMappedNIOReader读HUGE运行时间:" 
        + (readEndTime - readStartTime) + "ms");
    System.out.println("channelMappedNIOReader写HUGE运行时间:" 
        + (writeEndTime - writeStartTime) + "ms");
  }
  
  @Test
  void streamIoTest() throws IOException {
    InputStream inputStream = new FileInputStream(socialFile);
    
    OutputStream outputStream = new FileOutputStream(socialStreamOutputFile);
    
    byte[] buffer = new byte[(int)socialFile.length()];
    //read the file into the buffer
    long readStartTime = System.currentTimeMillis();
    while (inputStream.read(buffer) != -1) {
    }
    long readEndTime = System.currentTimeMillis();
    String[] inputStrings = (new String(buffer)).split("\n");
    System.out.println("inputStream读HUGE运行时间:" 
        + (readEndTime - readStartTime) + "ms");
    
    long writeStartTime = System.currentTimeMillis();
    outputStream.write(buffer);
    long writeEndTime = System.currentTimeMillis();
    System.out.println("outputStream写HUGE运行时间:" 
        + (writeEndTime - writeStartTime) + "ms");
    
    inputStream.close();
    outputStream.flush();
    outputStream.close();
  }

}
