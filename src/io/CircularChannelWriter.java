package io;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CircularChannelWriter implements CircularWriters {

  @Override
  public void write(File file, String writeInfo) throws IOException {
    Path outPath = file.toPath();
    FileChannel outChannel = 
        FileChannel.open(outPath,
            StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
    byte[] outputBytes = writeInfo.getBytes();
    MappedByteBuffer outMappedByteBuffer = 
        outChannel.map(MapMode.READ_WRITE, 0, outputBytes.length);
    //write
    long writeStartTime = System.currentTimeMillis();
    outMappedByteBuffer.put(outputBytes);
    long writeEndTime = System.currentTimeMillis();
    System.out.println("channelMappedNIO写的运行时间:" 
        + (writeEndTime - writeStartTime) + "ms");
  }

}
