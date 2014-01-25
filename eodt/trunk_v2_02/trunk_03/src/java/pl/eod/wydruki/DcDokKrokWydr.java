/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import pl.eod2.encje.DcDokumentKrok;

/**
 *
 * @author 103039
 */
public class DcDokKrokWydr {
    int id;
    int lp;
    String typNazwa;
String status;
    
    DcDokKrokWydr(DcDokumentKrok krok){
        id=krok.getId();
        lp=krok.getLp();
        typNazwa=krok.getDcAckeptTypKroku().getNazwa();
        status=krok.getAkcept().getNazwa();
    }
    
    DcDokKrokWydr(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public String getTypNazwa() {
        return typNazwa;
    }

    public void setTypNazwa(String typNazwa) {
        this.typNazwa = typNazwa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
