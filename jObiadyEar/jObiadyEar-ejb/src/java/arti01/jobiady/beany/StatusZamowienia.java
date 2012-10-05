/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author arti01
 */
public class StatusZamowienia {
    public static String POCZATKOWY="Początkowy";
    public static String WREALIZACJI="W Realizacji";
    public static String ZREALIZOWANY="Zrealizowany";
    public static String ROZLICZONY="Rozliczony";
    private List<String> statusy;

    public List<String> getStatusy() {
            statusy=new ArrayList<String>(Arrays.asList(POCZATKOWY,WREALIZACJI,ZREALIZOWANY,ROZLICZONY));
        return statusy;
    }

    public void setStatusy(List<String> statusy) {
        this.statusy = statusy;
    }
    
}
