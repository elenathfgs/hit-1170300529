package io;

import centralobject.CentralObject;
import centralobject.CentralObjectFactory;
import centralobject.CentralUser;
import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import exceptions.CloseNumException;
import exceptions.ElementNameException;
import exceptions.GenderInputException;
import exceptions.LabelException;
import exceptions.NumOfTrackUnmatch;
import exceptions.NumberRepresentException;
import exceptions.RotateDirectionInputException;
import exceptions.SameLabelException;
import exceptions.SelfRelationException;
import exceptions.TieWithoutDefinitionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import physicalobject.Friend;
import physicalobject.PhysicalObject;
import physicalobject.PhysicalObjectFactory;
import physicalobject.Planet;
import track.Track;

public class CircularBufferedReader implements CircularReaders {
  
  @Override
  public String read(File file) throws IOException, Exception {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    String buffer;
    StringBuilder stringBuilder = new StringBuilder();
    
    while ((buffer = bufferedReader.readLine()) != null) {
      stringBuilder.append(buffer + "\n");
    }
    bufferedReader.close();
    
    return stringBuilder.toString();
  }


}
