package nyp;

import java.util.Properties;

public class Firmen {

  String Firmenname;
  String Ordnername;
  String[] Bankkonto;
  int Anzahlkonten;
  
  String filename = "Bankkontonummer.properties";
  
  Properties props = Loadprop.loadprops(filename);
  
  public Firmen(String firmenname, String ordnername) {
    Firmenname = firmenname;
    Ordnername = ordnername;
    Bankkonto = props.getProperty(Firmenname).split(",");
    Anzahlkonten = Bankkonto.length; 
    
  }

  
}
