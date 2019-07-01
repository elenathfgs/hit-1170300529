package io;

import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.io.File;
import java.io.IOException;

public interface CircularWriters {
  /**.
   * output the information of a atomStructure to a file
   * @param file the file waiting to be written
   * @param writeInfo the information String waiting to be written
   */
  public void write(File file,String writeInfo) throws IOException;

}
