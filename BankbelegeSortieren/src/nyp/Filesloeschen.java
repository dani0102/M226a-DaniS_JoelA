package nyp;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Filesloeschen {
 
  static final Logger log = LogManager.getLogger(Filesloeschen.class);

  public static void floeschen() {
    
    LocalDate datum = LocalDate.now().minusDays(14);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    String filename = "Pfad.properties";
    
    Properties props = Loadprop.loadprops(filename);
    
    File allebelege = new File(props.getProperty("Belegepfad"));
    
    File[] listofdir = allebelege.listFiles(new FilenameFilter() {
      public boolean accept(File allebelege, String name) {
        
        return allebelege.isDirectory();
        
      }
    });
    
    for(File d : listofdir) {
      
      if(d.length() == 0) {
        d.delete();
        log.debug(d.getPath() + " wurde gelöscht");
      } else {
        
        File[] listoffiles = d.listFiles();
        
        for(File s : listoffiles) {

          LocalDate filedatum = LocalDate.parse(format.format(s.lastModified()));
          
          if(filedatum.isBefore(datum)) {
            s.delete();
            log.debug(s.getPath() + " wurde gelöscht.");
          }
          
        }
        
      }
      
    }
    
  }
  
}
