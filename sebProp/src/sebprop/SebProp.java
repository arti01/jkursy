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
import pl.arti01.prop.Ip2Ip;
import pl.arti01.prop.ParserPropToCfg;

/**
 *
 * @author arti01
 */
public class SebProp {

    static Properties config;

    public static void main(String[] args) {
        config = new CzytajConfig().getConfig();

        try {
            switch (args[0]) {
                case "-w":
                    zmienPlikiProp(args[1], args[2]);
                    break;
                case "-f":
                    zmienWgPliku(args[1]);
                    break;
                case "-p":
                    parseCfg(args[1], args[2], args[3]);
                    break;
                case "-z":
                    ip2ipM(args[1], args[2]);
                    break;

                default:
                    System.err.println("parametr: " + args[0] + " nieznany");
                    System.err.println("brak parametrow: -w <klucz> <wartosc>");
                    System.err.println("brak parametrow: -f <plik properties z nowymi wartosciami>");
                    System.err.println("brak parametrow: -p <wzorzec cfg> <properties> <docelowy cfg>");
                    System.err.println("brak parametrow: -z <plik mapy old> <plik mapy new>");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("brak parametrow: -w <klucz> <wartosc>");
            System.err.println("brak parametrow: -f <plik properties z nowymi wartosciami>");
            System.err.println("brak parametrow: -p <wzorzec cfg> <properties> <docelowy cfg>");
            System.err.println("brak parametrow: -z <plik mapy old> <plik mapy new>");
        }

    }

    static private void zmienPlikiProp(String klucz, String wartosc) {
        try {
            PodmienWplikach pwP = new PodmienWplikach(new CzytajConfig().doList(config.getProperty("plikiProperties")));
            pwP.zmienWartosci(klucz, wartosc);
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

    static private void parseCfg(String plikSrc, String plikProp, String plikDsc) {
        try {
            ParserPropToCfg.Parsuj(plikSrc, plikProp, plikDsc);
        } catch (IOException ex) {
            Logger.getLogger(SebProp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static private void ip2ipM(String plikMapOld, String plikMapNew) {
        Ip2Ip.zmienPliki(new CzytajConfig().doList(config.getProperty("plikiMapOldNew")), plikMapOld, plikMapNew);
    }
}
