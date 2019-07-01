package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import centralobject.CentralObject;
import centralobject.CentralObjectFactory;
import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.ElementNameException;
import exceptions.NumOfTrackUnmatch;
import physicalobject.PhysicalObject;
import physicalobject.PhysicalObjectFactory;
import physicalobject.Planet;
import track.Track;

public class CircularChannelReader implements CircularReaders{
  
  @Override
  public String read(File atomFile) throws IOException, Exception {
    FileChannel inchannel = 
        FileChannel.open(atomFile.toPath(),StandardOpenOption.READ);
    MappedByteBuffer inMappedByteBuffer = inchannel.map(MapMode.READ_ONLY, 0, inchannel.size());
    byte[] bytes = new byte[inMappedByteBuffer.limit()];
    inMappedByteBuffer.get(bytes);//read
    String inputString = new String(bytes);
    bytes = null;
    inMappedByteBuffer = null;
    System.gc();
    inchannel.close();
    return inputString;
  }
  
}
