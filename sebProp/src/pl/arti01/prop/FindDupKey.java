/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author 103039
 */
public class FindDupKey {

    public static Map<String, String> doItPlik(String plik) {
        try {
            FileInputStream in = new FileInputStream(plik);
            Properties prop = new Properties();
            prop.load(in);
            BufferedReader br = new BufferedReader(new FileReader(plik));
            return FindDupKey.doIt(br);
        } catch (FileNotFoundException ex) {
            System.err.println("brak pliku " + plik);
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("plik " + plik + " zapewne nie jest plikiem typu"
                    + " properties");
        }
        return null;
    }

    public static Map<String, String> doIt(BufferedReader br) throws IOException {
        Properties prop=new Properties();
        Map<String, String>wynik=new HashMap<>();
        for (String line; (line = br.readLine()) != null;) {
            if(line.startsWith("#")||line.isEmpty()){
                continue;
            }
            String[] podzial=line.split("=",2);
            String kom="";
            //walidacja nazewnictwa kluczy
            if(podzial[0].contains(" ")){
                kom="zawiera spacje, ";
            }
            if(!podzial[0].toUpperCase().equals(podzial[0])){
                kom+="zawiera małe litery, ";
            }
            if(!kom.isEmpty()){
                wynik.put(podzial[0], kom);
                continue;
            }
            //walidacja duplikatow
            if(prop.containsKey(podzial[0])){
                wynik.put(podzial[0], "jest duplikatem");
            }else{
                prop.put(podzial[0], podzial[1]);
            }
        }
        return wynik;
    }
}
