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
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


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
    private DataModel<Zamowienie> zamowienia=new ListDataModel<Zamowienie>();
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
        zero=0;
        return "transHist";
    }
    
    public String obslugaZamowienia(){
        return "obslugaZamowienia";
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

    public void akcjalist(ActionEvent ae){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(zero));
        zam=zamowienia.getRowData();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, zam.toString());
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ae.toString());
        if (zero!=0) {
            //zero= new Double(event.getNewValue().toString());
            if(zero>0)zf.przyjmijWplate(zam, zero, "przyjęcie kasy za zamówienie");
            if(zero<0)zf.przyjmijWplate(zam, zero, "zwrot kasy z zamowienia");
            zero=0;
        }
    }
    
    public void zmiana(ValueChangeEvent ev){
        //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(ev));
        zam=zamowienia.getRowData();
        //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, zam.toString());
        if(ev.getNewValue()==null) return;
        zero = new Double(ev.getNewValue().toString());
        if (zero!=0) {
            //zero= new Double(event.getNewValue().toString());
            if(zero>0)zf.przyjmijWplate(zam, zero, "przyjęcie kasy za zamówienie");
            if(zero<0)zf.przyjmijWplate(zam, zero, "zwrot kasy z zamowienia");
            zero=0;
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

    public DataModel<Zamowienie> getZamowienia() {
        zamowienia.setWrappedData(kurs.getZamowienia());
        return zamowienia;
    }

    public void setZamowienia(DataModel<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }
    
}
