/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstr;


public class AbstMgImpl<X extends AbstEncja, Y extends AbstKontroler<X>> extends AbstMg<X, Y> {

    public AbstMgImpl(String s, X obiekt) throws InstantiationException, IllegalAccessException {
        super(s, obiekt);
    }
    
}
