/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author arti01
 */
public class ParserPropToCfg {

    public static void Parsuj(String plikSrc, String plikProp, String plikDsc) throws IOException {
        //kopia pliku docelowego
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmm");
        String plikKopia = plikDsc + sdf.format(Calendar.getInstance().getTime());
        try {
            Files.copy(new File(plikDsc).toPath(), new File(plikKopia).toPath(), StandardCopyOption.REPLACE_EXISTING);   
        } catch (java.nio.file.NoSuchFileException e) {
            System.err.println("brak pliku docelowego: "+plikDsc+" stworze nowy");
        }

        FileInputStream in = new FileInputStream(plikProp);
        Properties plikNoweWartosci = new Properties();
        plikNoweWartosci.load(in);
        String plikTresc = new Scanner(new File(plikSrc)).useDelimiter("\\Z").next();
        Enumeration<?> e = plikNoweWartosci.propertyNames();
        for (; e.hasMoreElements();) {
            String klucz = e.nextElement().toString();
            String wartosc = plikNoweWartosci.getProperty(klucz);
            String zmienna = "\\$\\{" + klucz + "}";
            plikTresc = plikTresc.replaceAll(zmienna, wartosc);
            Files.write(Paths.get(plikDsc), plikTresc.getBytes());
        }
    }

}
