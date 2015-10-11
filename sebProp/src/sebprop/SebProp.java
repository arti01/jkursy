/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sebprop;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.arti01.prop.PodmienWplikach;
import pl.arti01.prop.CzytajConfig;

/**
 *
 * @author arti01
 */
public class SebProp {
    
    static Properties config;
    
    public static void main(String[] args) {
        config = new CzytajConfig().getConfig();
        try {
            if (args[0].equals("-p")) {
                zmienPlikiProp(args[1], args[2]);
            }
            if (args[0].equals("-f")) {
                zmienWgPliku(args[1]);
            } else {
                System.err.println("parametr: " + args[0] + " nieznany");
                System.err.println("brak parametrow: -p <klucz> <wartosc>");
                System.err.println("brak parametrow: -f <plik properties z nowymi wartosciami>");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("brak parametrow: -p <klucz> <wartosc>");
            System.err.println("brak parametrow: -f <plik properties z nowymi wartosciami>");
        }
        
    }
    
    static private void zmienPlikiProp(String klucz, String war) {
        try {
            PodmienWplikach pwP = new PodmienWplikach(new CzytajConfig().doList(config.getProperty("plikiProperties")));
            pwP.zmienWartosci(klucz, war);
        } catch (NullPointerException ex) {
            Logger.getLogger(SebProp.class.getName()).log(Level.SEVERE, "brak listy plikow prop", ex);
        } catch (IOException ex) {
            Logger.getLogger(SebProp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static private void zmienWgPliku(String plikNowy) {
        PodmienWplikach pwP = new PodmienWplikach(new CzytajConfig().doList(config.getProperty("plikiProperties")));
        try {
            pwP.podmienWgPliku(plikNowy);
        } catch (IOException ex) {
            Logger.getLogger(SebProp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
