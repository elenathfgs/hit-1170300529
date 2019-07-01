package io;

import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.io.File;

public interface CircularReaders {
  /**.
   * read the atom file using a certain strategy
   * @param file the file waiting to be read
   * @return String which contains the raw information
   */
  public String read(File file) throws Exception;

}
