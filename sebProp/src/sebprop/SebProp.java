/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sebprop;

import com.sun.org.apache.xml.internal.security.utils.Constants;
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
        zmienPlikiProp("test", "ffffffffff");
    }

    static private void zmienPlikiProp(String klucz, String war) {
        try {
            PodmienWplikach pwP = new PodmienWplikach(new CzytajConfig().doList(config.getProperty("plikiProperties")));
            pwP.zmien(klucz, war);
        } catch (NullPointerException ex) {
            Logger.getLogger(SebProp.class.getName()).log(Level.SEVERE, "brak listy plikow prop", ex);
        }
    }

}
