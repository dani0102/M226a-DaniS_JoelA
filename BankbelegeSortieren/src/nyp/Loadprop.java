package nyp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Loadprop {
  
  static final Logger log = LogManager.getLogger(Loadprop.class);
  
  public static Properties loadprops(String filename) {
  
  Properties props = new Properties();

  
  
  FileInputStream reader = null;
  try {
    reader = new FileInputStream("./Files/" + filename);
    props.load(reader);
    
    
    
  } catch (IOException e1) {
  } finally {
    if (reader != null) {
      try {
        reader.close();
      } catch (IOException e) {
        
      }
      reader = null;
    }
  }
  return props;
}
}