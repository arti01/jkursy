/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateful
public class UzytkownikFacade extends AbstractFacade<Uzytkownik> {
    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;
    @EJB ZamowienieFacade zf;
    @EJB KursFacade kf;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UzytkownikFacade() {
        super(Uzytkownik.class);
    }
    
    public Kurs dodajKurs(Uzytkownik u){
        Kurs kurs = new Kurs();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-2")).getTime().getTime());
        kurs.setDataKursu(ts);
        kurs.setTragarz(u);
        u.getKursy().add(0, kurs);
        u=getEntityManager().merge(u);
        return u.getKursy().get(0);
    }
    
    public void usunKurs(Kurs kurs){
    Uzytkownik u=kurs.getTragarz();
        for(Zamowienie zamF:kurs.getZamowienia()){
            zamF.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
            zamF.setKurs(null);
            zf.edit(zamF);
        }
        kurs.getZamowienia().clear();
        kf.edit(kurs);
        u.getKursy().remove(kurs);
        edit(u);
    }
    
    public Zamowienie dodajZam(Uzytkownik u){
        Zamowienie zam = new Zamowienie();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-2")).getTime().getTime());
        zam.setDataZamowienia(ts);
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zam.setUzytkownik(u);
        u.getZamowienia().add(0, zam);
        u=edit(u);
        return u.getZamowienia().get(0);
    }
    
    public void usunZam(Zamowienie zam){
        Uzytkownik u=zam.getUzytkownik();
        u.getZamowienia().remove(zam);
        edit(u);
     }
}
