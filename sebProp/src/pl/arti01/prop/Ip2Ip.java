package pl.arti01.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * zamienia we wczesniej zdefiniowanych plikach wartosci ze starych u nowych
 * jako parametr nalezy podac stary i nowy plik mapy
 */
public class Ip2Ip {

    static public void zmienPliki(List<String> pliki, String plikMapOld, String plikMapNew) {
        KopiaPlikow.kopieZrob(pliki, false);

        //ladowanie properties
        Properties plikMapOldProp = new Properties();
        try {
            plikMapOldProp.load(new FileInputStream(plikMapOld));
        } catch (IOException ex) {
            Logger.getLogger(PodmienWplikach.class.getName()).log(Level.SEVERE, "brak pliku mapy Old: " + plikMapOld, ex);
        }
        Properties plikMapNewProp = new Properties();
        try {
            plikMapNewProp.load(new FileInputStream(plikMapNew));
        } catch (IOException ex) {
            Logger.getLogger(PodmienWplikach.class.getName()).log(Level.SEVERE, "brak pliku mapy New: " + plikMapNew, ex);
        }
        //sprawdzenie, czy jest tyle samo kluczy
        if (plikMapNewProp.size() != plikMapOldProp.size()) {
            System.err.println("uwaga (ale kontynuuje) - roznica w liczbie kluczy - plikOld=" + plikMapOldProp.size() + ", plikNew=" + plikMapNewProp.size());
        }

        Enumeration<?> e = plikMapNewProp.propertyNames();
        for (String plik : pliki) {
            System.out.println("==================");
            System.out.println("plik: "+plik);
            try {
                //String plikTresc = new Scanner(new File(plik)).useDelimiter("\\Z").next();
                String plikTresc = new String(Files.readAllBytes(Paths.get(plik)));
                //System.out.println(plikTresc+"tresc");
                for (; e.hasMoreElements();) {
                    String klucz = e.nextElement().toString();
                    String wartoscNew = plikMapNewProp.getProperty(klucz);
                    String wartoscOld = plikMapOldProp.getProperty(klucz);
                    //System.out.println(klucz + "=" + wartoscOld + "->" + wartoscNew);
                    if (!wartoscNew.equals(wartoscOld)) {
                        if (plikTresc.contains(wartoscOld)) {
                            System.out.println(klucz + "=" + wartoscOld + "->" + wartoscNew);
                            plikTresc = plikTresc.replaceAll(wartoscOld, wartoscNew);
                        }
                    }
                }
                Files.write(Paths.get(plik), plikTresc.getBytes());
            } catch (FileNotFoundException ex) {
                System.out.println("brak pliku, nie przetwarzam go");
            } catch (IOException ex) {
                Logger.getLogger(Ip2Ip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
