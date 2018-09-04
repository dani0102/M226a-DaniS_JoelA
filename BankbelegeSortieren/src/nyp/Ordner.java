package nyp;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Ordner {

  static final Logger log = LogManager.getLogger(Ordner.class);
  
  public static void woerstellen(String hauptordner, String ordnername, int anzahlkonten, String[] bankkonto) {
    
    String CHF = hauptordner + "\\" + ordnername + "\\CHF";
    String EUR = hauptordner + "\\" + ordnername + "\\EUR";
    String USD = hauptordner + "\\" + ordnername + "\\USD";
    
    File CHFordner = new File(CHF);
    File EURordner = new File(EUR);
    File USDordner = new File(USD);
    
    switch (anzahlkonten) {
      
      case 1:
        CHFordner.mkdir();
        break;
      case 2:
        CHFordner.mkdir();
        EURordner.mkdir();
        break;
      case 3:
        CHFordner.mkdir();
        EURordner.mkdir();
        USDordner.mkdir();
        break;
      default:
        log.warn("Unbekannter Währungsordner.");
        break;
      
    }
    
    Ordner.joerstellen(hauptordner, ordnername, anzahlkonten, bankkonto);
    
  }
  
  public static void joerstellen(String hauptordner, String ordnername, int anzahlkonten, String[] bankkonto) {
    
    File wordner = new File(hauptordner + "\\" + ordnername);
    
    File[] listofdir = wordner.listFiles(new FilenameFilter() {
      public boolean accept(File ordner, String name) {
        return ordner.isDirectory();
      }
    });
    
    int i = 0;
    
    for(File o : listofdir) {
      
      String jahr = hauptordner + "\\" + ordnername + "\\" + o.getName() + "\\" + Calendar.getInstance().get(Calendar.YEAR);
      
      File jordner = new File(jahr);
      jordner.mkdir();
      
      Ordner.moerstellen(jahr, anzahlkonten, bankkonto, i);
      
      i++;
      
    }
    
  }
  
  public static void moerstellen(String jahrpath, int anzahlkonten, String[] bankkonto, int i) {
    
    String[] monate = {
        ("01_Januar"),
        ("02_Februar"),
        ("03_März"),
        ("04_April"),
        ("05_Mai"),
        ("06_Juni"),
        ("07_Juli"),
        ("08_August"),
        ("09_September"),
        ("10_Oktober"),
        ("11_November"),
        ("12_Dezember")
    };
    
    for(String monat : monate) {
      
      File mordner = new File(jahrpath + "\\" + monat);
      mordner.mkdir();
      
    }
    
    Fileskopieren.fkopieren(anzahlkonten, jahrpath, bankkonto, monate, i);
    Filesloeschen.floeschen();

    
  }
  
}
