/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arti01
 */
public class CzytajConfig {
    Properties config=new Properties();

    
   public CzytajConfig(){
        try {
            FileInputStream in=new FileInputStream("config.properties");
            config.load(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CzytajConfig.class.getName()).log(Level.SEVERE, "brak pliku z konfiguracja", ex);
        } catch (IOException ex) {
            Logger.getLogger(CzytajConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> doList(String lista){
          List<String> items = Arrays.asList(lista.split("\\s*,\\s*"));
          return items;
    }

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }
    
    
}
