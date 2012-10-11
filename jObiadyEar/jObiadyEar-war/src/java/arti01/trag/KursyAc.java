/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.trag;

import arti01.jobiady.beany.Kurs;
import arti01.jobiady.beany.KursFacade;
import arti01.jobiady.beany.StatusZamowienia;
import arti01.jobiady.beany.Uzytkownik;
import arti01.jobiady.beany.UzytkownikFacade;
import arti01.jobiady.beany.Zamowienie;
import arti01.jobiady.beany.ZamowienieFacade;
import arti01.utils.Login;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author arti01
 */
@ManagedBean(name = "tragKursyAc")
@SessionScoped
public class KursyAc implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{login}")
    private arti01.utils.Login login;
    @EJB
    KursFacade kf;
    private Kurs kurs;
    @EJB
    UzytkownikFacade uf;
    @EJB
    ZamowienieFacade zf;
    private Zamowienie zam;
    private double zero=0.00;
    
    
    
    
    public String dodaj() {
        Uzytkownik u=login.getZalogowany();
        kurs = new Kurs();
        kurs.setTragarz(login.getZalogowany());
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        kurs.setDataKursu(ts);
        kf.create(kurs);
        u.getKursy().add(0,kurs);
        uf.edit(u);
        return "kursEdycja";
    }
    
    public String usun() {
        Uzytkownik u=login.getZalogowany();
        for(Zamowienie zamF:kurs.getZamowienia()){
            zamF.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
            zf.edit(zamF);
        }
        u.getKursy().remove(kurs);
        uf.edit(u);
        return "kursyLista";
    }
    
    public String transHist() {
        return "transHist";
    }
    
    public String kursEdycja() {
        return "kursEdycja";
    }
    
    public void przyjmij() {
        kf.przyjmijZamowienie(zam, kurs);
    }
    
    public void wycofaj() {
        kf.wycofajZamowienie(zam);
    }
    
    public String kursZestawienie() {
        return "kursZestawienie";
    }
    
     public void przyjmijWplate(ValueChangeEvent event) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, event.getNewValue().toString());
        if (null != event.getNewValue()) {
            zf.przyjmijWplate(zam, new Double(event.getNewValue().toString()), "przyjęcie kasy za zamówienie");
        }
    }
    
     public void test(){
         System.out.println(zam+"sssssssss");
     }
     
    public Kurs getKurs() {
        return kurs;
    }
    
    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }
    
    public Login getLogin() {
        return login;
    }
    
    public void setLogin(Login login) {
        this.login = login;
    }
    
    public ZamowienieFacade getZf() {
        return zf;
    }
    
    public void setZf(ZamowienieFacade zf) {
        this.zf = zf;
    }

    public Zamowienie getZam() {
        return zam;
    }

    public void setZam(Zamowienie zam) {
        this.zam = zam;
    }

    public double getZero() {
        zero=0.00;
        return zero;        
    }

    public void setZero(double zero) {
        this.zero = zero;
    }
    
    
}
