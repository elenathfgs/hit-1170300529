package io;

import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.io.File;
import java.io.IOException;

public class CircularIO {
  /**.
   * 
   * @param certainReader a certain read strategy
   * @param file the file waiting to be read
   * @return AtomStructure
   * @throws Exception throw IO Exceptions
   */
  public static String readFile(
      CircularReaders certainReader,File file) throws Exception {
    return certainReader.read(file);        
  }
  
  /**.
   * 
   * @param certainWriter a certain write strategy
   * @param writeInfo the String contains all the information of a system
   * @param file the file waiting to be written
   * @throws IOException IOExceptions
   */
  public static void writeFile(
      CircularWriters certainWriter,String writeInfo,File file) throws IOException {
    certainWriter.write(file, writeInfo);
  }

}
