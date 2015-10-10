/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.PlikiProp;

/**
 *
 * @author arti01
 */
public class PodmienWplikach {
    List<PlikiProp>pliki=new ArrayList<>();

    public PodmienWplikach(List<String> plikiS) {
        for(String plik:plikiS){
            Properties p=new Properties();
            try {
                p.load(new FileInputStream(plik));
                this.pliki.add(new PlikiProp(plik, p));
            } catch (IOException ex) {
                Logger.getLogger(PodmienWplikach.class.getName()).log(Level.SEVERE, "brak pliku properties-"+plik, ex);
            }
        }
    }
    
    public void zmien(String klucz, String wartosc) throws IOException{
        for(PlikiProp pr: pliki){
            pr.getProp().setProperty(klucz, wartosc);
            pr.getProp().store(new FileOutputStream(pr.getPlik()),null);
        }
    }
}
