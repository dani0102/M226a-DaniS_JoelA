package nyp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import com.itextpdf.text.pdf.PdfReader;

import org.apache.logging.log4j.LogManager;

public class Fileskopieren {
  
  static final Logger log = LogManager.getLogger(Fileskopieren.class);

  public static void fkopieren(int anzahlkonten, String jahrpath, String[] bankkonto, String[] monate, int i) {
      
      String filename = "Pfad.properties";
    
      Properties props = Loadprop.loadprops(filename);
    
      File allebelege = new File(props.getProperty("Belegepfad"));
      
      if(!allebelege.exists()) {
        log.error("Belegeverzeichnis wurde nicht gefunden");
      }
      
      File[] listofdir = allebelege.listFiles(new FilenameFilter() {
        public boolean accept(File allebelege, String name) {
          
          return allebelege.isDirectory();
          
        }
      });
      
      for(int m = 0; m < 12; m++) {
        
        for(File d : listofdir) {
          
          File help = new File(allebelege + "\\" + d.getName());
          
          File[] listoffiles = help.listFiles(new FilenameFilter() {
            public boolean accept(File help, String name) {
              
              return (name.startsWith(bankkonto[i]) && name.endsWith(".pdf"));
              
            }
          });
          
          for(File s : listoffiles) {
            
            Path file = Paths.get(help + "\\" + s.getName());
            String fileloc = file.toString();
            File des = new File(jahrpath + "\\" + monate[m] + "\\" + s.getName());
            Path destination = Paths.get(jahrpath + "\\" + monate[m] + "\\" + s.getName());
            
            //try {
              
              //PdfReader reader = new PdfReader(fileloc);
              
              if(m <= 9) {
                
                if(s.getName().contains("_" + Calendar.getInstance().get(Calendar.YEAR) + "-0" + (m+1))) {
                  
                  try {
                    
                    //if(new String(reader.getMetadata()).contains("FLOW=D")) {
                      
                      File gutordner = new File(jahrpath + "\\" + monate[m] /*+ "\\" + "Gutschrift"*/);
                      gutordner.mkdir();
                      
                      destination = Paths.get(gutordner + "\\" + s.getName());
                      
                   // } else if(new String(reader.getMetadata()).contains("FLOW=C")) {
                      
                      File belordner = new File(jahrpath + "\\" + monate[m] /*+ "\\" + "Belastung"*/);
                      belordner.mkdir();
                      
                      destination = Paths.get(belordner + "\\" + s.getName());
                      
                   // }
                    
                    Files.copy(file, destination);
                    log.debug(s.getPath() + " wurde kopiert.");
                    
                  } catch (IOException e) {
                    if(des.exists()) {
                      log.warn(s.getPath() + " wurde bereits kopiert.");
                    } else {
                      log.warn(s.getPath() + " konnte nicht kopiert werden");
                    }
                  }
                  
                }
                
              } else if(m > 9) {
                
                if(s.getName().contains("_" + Calendar.getInstance().get(Calendar.YEAR) + "-" + (m+1))) {

                  try {
                    
                    Files.copy(file, destination);
                    log.debug(s.getPath() + " wurde kopiert.");
                    
                  } catch (IOException e) {
                    if(des.exists()) {
                      log.warn(s.getPath() + " wurde bereits kopiert.");
                    } else {
                      log.warn(s.getPath() + " konnte nicht kopiert werden");
                    }
                  }
                  
                }
                
              }          
              
           /* } catch (IOException e1) {
              e1.printStackTrace();
            }*/

          }
          
        }
        

      }
  }
  
}
