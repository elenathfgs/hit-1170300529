package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CircularStreamWriter implements CircularWriters {

  @Override
  public void write(File file, String writeInfo) throws IOException {
    OutputStream outputStream = new FileOutputStream(file);
    byte[] outputByte = writeInfo.getBytes();
    
    long writeStartTime = System.currentTimeMillis();
    outputStream.write(outputByte);
    long writeEndTime = System.currentTimeMillis();
    System.out.println("outputStream写HUGEstellar运行时间:" 
        + (writeEndTime - writeStartTime) + "ms");
    outputStream.close();
  }

}
