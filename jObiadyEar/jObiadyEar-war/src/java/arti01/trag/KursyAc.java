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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
    
    public String dodaj() {
        kurs = new Kurs();
        kurs.setTragarz(login.getZalogowany());
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        kurs.setDataKursu(ts);
        kf.create(kurs);
        Uzytkownik u = login.getZalogowany();
        u.getKursy().add(0,kurs);
        uf.edit(u);
        return "kursEdycja";
    }
    
    public String usun() {
        Uzytkownik u = login.getZalogowany();
        u.getKursy().remove(kurs);
        uf.edit(u);
        return "kursyLista";
    }
    
    public String kursEdycja() {
        return "kursEdycja";
    }
    
    public void przyjmij() {
        zam.setStatusZamowienia(StatusZamowienia.WREALIZACJI);
        kurs.getZamowienia().add(zam);
        Uzytkownik u = login.getZalogowany();
        u.getKursy().set(u.getKursy().indexOf(kurs), kurs);
        uf.edit(u);
    }
    
    public void wycofaj() {
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zf.edit(zam);
        kurs.getZamowienia().remove(zam);
        Uzytkownik u=login.getZalogowany();
        u.getKursy().set(u.getKursy().indexOf(kurs), kurs);
        uf.edit(u);
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
    
    
}
