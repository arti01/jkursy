/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author arti01
 */
public class ParserPropToCfg {

    public static void Parsuj(String plikSrc, String plikProp, String plikDsc) throws IOException {
        //kopia pliku docelowego
        List<String> plikiBkp = new ArrayList<>();
        plikiBkp.add(plikDsc);
        KopiaPlikow.kopieZrob(plikiBkp, true);

        FileInputStream in = new FileInputStream(plikProp);
        Properties plikNoweWartosci = new Properties();
        plikNoweWartosci.load(in);
        String plikTresc = new String(Files.readAllBytes(Paths.get(plikSrc)));
        //String plikTresc = new Scanner(new File(plikSrc)).useDelimiter("\\Z").next();
        Enumeration<?> e = plikNoweWartosci.propertyNames();
        for (; e.hasMoreElements();) {
            String klucz = e.nextElement().toString();
            String wartosc = plikNoweWartosci.getProperty(klucz);
            String zmienna = "\\$\\{" + klucz + "}";
            plikTresc = plikTresc.replaceAll(zmienna, wartosc);
            Files.write(Paths.get(plikDsc), plikTresc.getBytes());
        }

        //sprawdzenie, czy nie zostaly jakies klucze
        String pozostaleKlucze = "";
        int pozycja = 0;
        while (pozycja < plikTresc.length()) {
            pozycja = plikTresc.indexOf("${", pozycja);
            int pozycjaEnd = plikTresc.indexOf("}", pozycja + 1);
            if (pozycja < 0) {
                break;
            } else {
                pozostaleKlucze += plikTresc.substring(pozycja, pozycjaEnd + 1) + ", ";
                pozycja = pozycjaEnd + 1;
            }
        }
        if (pozostaleKlucze.length() > 0) {
            System.err.println("klucze niesparsowane: " + pozostaleKlucze);
        }
    }

}
