package nyp;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
  
  static final Logger log = LogManager.getLogger(Main.class);
  
  public static void main(String[] args) {
    
    log.info("Programm gestartet.");
    
    //get locations of stored bank receipts and new path
    String filename = "Pfad.properties";
    
    Properties props = Loadprop.loadprops(filename);
    
    log.info("Properties erfolgreich gelesen.");	
    
    String Hauptordner = props.getProperty("Speicherpfad");
    File hauptverzeichnis = new File(Hauptordner);
    
    hauptverzeichnis.mkdir();
    
    ArrayList<Firmen> firma = new ArrayList<>();
    
    //adding folder name for each company
    firma.add(new Firmen("NoMag", "10_NoMag"));
    firma.add(new Firmen("BS", "30_B+S"));
    firma.add(new Firmen("Akros", "40_Akros"));
    firma.add(new Firmen("Frox", "50_Frox"));
    firma.add(new Firmen("Sohard", "60_Sohard"));
    firma.add(new Firmen("Dabis", "61_Dabis"));
    firma.add(new Firmen("NYP", "70_NYP"));
    firma.add(new Firmen("NoEngZD", "1010_NoEng ZD"));
    firma.add(new Firmen("NoEngWI", "1012_NoEng WI"));
    firma.add(new Firmen("NoEngBern", "1019_NoEng Bern"));
    firma.add(new Firmen("NoEngLU", "1021_NoEng LU"));
    
    //entering loop to create needed directories for each company
    for(Firmen f : firma) {
      //main folder and folder with company's name
      File foerstellen = new File(Hauptordner + "\\" + f.Ordnername);
      foerstellen.mkdir();
      log.debug(foerstellen.getPath() + " Ordner wurde erstellt.");
      
      Ordner.woerstellen(Hauptordner, f.Ordnername, f.Anzahlkonten, f.Bankkonto);
      
    }
    
    log.info("Programm beendet.");
    
  }
  
}
