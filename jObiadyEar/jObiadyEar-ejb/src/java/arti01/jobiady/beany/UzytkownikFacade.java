/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
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
    
    public Zamowienie dodajZamOld(Uzytkownik u){
        Zamowienie zam = new Zamowienie();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-2")).getTime().getTime());
        zam.setDataZamowienia(ts);
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zam.setUzytkownik(u);
        u.getZamowienia().add(0, zam);
        u=getEntityManager().merge(u);
        return u.getZamowienia().get(0);
    }
    
    public Zamowienie dodajZam(Uzytkownik u){
        Zamowienie zam = new Zamowienie();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-2")).getTime().getTime());
        zam.setDataZamowienia(ts);
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zam.setUzytkownik(u);
        zf.create(zam);
        zf.getEntityManager().refresh(zam);
        return zam;
        //u.getZamowienia().add(0, zam);
        //u=getEntityManager().merge(u);
    }
    
    @Deprecated
    public Uzytkownik findRefresh(String uO){
        Uzytkownik u=find(uO);
         //getEntityManager().flush();
         /*Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(u.getZamowienia().get(0).getZamowieniemenu().size()));
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(u.getZamowienia().get(0).getZamowieniemenu()));
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(u.getZamowienia().get(0).getPotrawy()));
         */
        getEntityManager().refresh(u);
         return u;
    }
    
}
