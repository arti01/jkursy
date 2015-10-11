/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arti01
 */
public class PodmienWplikach {

    List<PlikiProp> pliki = new ArrayList<>();

    //tworzy properties wg listy plikow
    public PodmienWplikach(List<String> plikiS) {
        for (String plik : plikiS) {
            Properties p = new Properties();
            try {
                p.load(new FileInputStream(plik));
                this.pliki.add(new PlikiProp(plik, p));
            } catch (IOException ex) {
                Logger.getLogger(PodmienWplikach.class.getName()).log(Level.SEVERE, "brak pliku properties-" + plik, ex);
            }
        }
    }

    public void zmienWartosci(String klucz, String wartosc) throws IOException {
        //przeglada pliki z properties i jesli natrafi na dany klucz to zmienia mu wartość
        for (PlikiProp pr : pliki) {
            if (pr.getProp().containsKey(klucz)) {
                System.out.println(pr.getPlik()+":"+klucz+" - old: "+pr.getProp().getProperty(klucz)+" - new: "+wartosc);
                pr.getProp().setProperty(klucz, wartosc);
                pr.getProp().store(new FileOutputStream(pr.getPlik()), null);
            }
        }
    }

    public void podmienWgPliku(String plikProp) throws FileNotFoundException, IOException {
        //argumentem jest plik prop, wartosci w nim wystepujace sa wyszukiwane i zmieniane w innych (zdefiniowanych) plikach 
        FileInputStream in = new FileInputStream(plikProp);
        Properties plikNoweWartosci = new Properties();
        plikNoweWartosci.load(in);
        Enumeration<?> e = plikNoweWartosci.propertyNames();
        for (; e.hasMoreElements();) {
            String klucz=e.nextElement().toString();
            String wartosc=plikNoweWartosci.getProperty(klucz);
            this.zmienWartosci(klucz, wartosc);
        }
    }
}
