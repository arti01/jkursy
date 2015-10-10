/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.Properties;

/**
 *
 * @author arti01
 */
public class PlikiProp {
    String plik;
    Properties prop;

    public PlikiProp(String plik, Properties prop) {
        this.plik = plik;
        this.prop = prop;
    }

    public String getPlik() {
        return plik;
    }

    public void setPlik(String plik) {
        this.plik = plik;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
    
}
