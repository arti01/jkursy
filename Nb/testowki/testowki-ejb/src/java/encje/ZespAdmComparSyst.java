/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.util.Comparator;

/**
 *
 * @author arti01
 */
public class ZespAdmComparSyst implements Comparator<Systemy> {

    @Override
    public int compare(Systemy o1, Systemy o2) {
        int i=0;
        return o1.getNazwa().compareTo(o2.getNazwa());
    }
}
