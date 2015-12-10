/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 103039
 */
public class KopiaPlikow {

    static public void kopieZrob(List<String> pliki, boolean czyNowy) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmm");
        for (String plik : pliki) {
            //wykonuje kopie pliku
            String plikKopia = plik + sdf.format(Calendar.getInstance().getTime());
            try {
                Files.copy(new File(plik).toPath(), new File(plikKopia).toPath());
            } catch (java.nio.file.FileAlreadyExistsException e1) {
                System.err.println("plik kopi już istnieje: " + plikKopia);
            } catch (java.nio.file.NoSuchFileException e) {
                if (czyNowy) {
                    System.err.println("brak pliku: " + plik + " stworze nowy");
                } else {
                    System.err.println("brak pliku: " + plik);
                    new File(plik).delete();
                }
            } catch (IOException ex) {
                Logger.getLogger(KopiaPlikow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
