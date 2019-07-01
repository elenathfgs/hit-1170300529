package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CircularStreamReader implements CircularReaders {

  @Override
  public String read(File file) throws Exception {
    InputStream inputStream = new FileInputStream(file);
    byte[] buffer = new byte[(int)file.length()];
    long readStartTime = System.currentTimeMillis();
    while (inputStream.read(buffer) != -1) {
    }
    long readEndTime = System.currentTimeMillis();
    System.out.println("inputStream读HUGEstellar运行时间:" 
        + (readEndTime - readStartTime) + "ms");
    inputStream.close();
    String result = new String(buffer);
    buffer = null;
    System.gc();
    return result;
  }

}
