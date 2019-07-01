package io;

import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.Planet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import centralobject.CentralUser;
import centralobject.Stellar;


public class CircularBufferedWriter implements CircularWriters {
  @Override
  public void write(File file, String writeInfo) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    bw.write(writeInfo);
    bw.close();
  } 
}
