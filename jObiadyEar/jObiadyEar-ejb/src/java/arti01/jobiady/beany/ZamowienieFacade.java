/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author arti01
 */
@Stateful
public class ZamowienieFacade extends AbstractFacade<Zamowienie> {

    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;
    private List<Zamowienie> zamPoczatkowe = new ArrayList<Zamowienie>();
    //@EJB MenuFacade mf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void dodajMenu(Zamowienie zam, Menu menu) {
        //Zamowieniemenu zm = new Zamowieniemenu();
        //zm.setMenu(menu);
        //zam.getZamowieniemenu().add(0, zm);
        Logger.getAnonymousLogger().log(Level.OFF, getEntityManager().contains(zam)+"");
        edit(zam);

    }

    @SuppressWarnings("unchecked")
    public List<Zamowienie> getZamPoczatkowe() {
        Query query = getEntityManager().createQuery("select z from Zamowienie z where z.statusZamowienia=:status and z.dataZamowienia between :dataod and :datado");
        query.setParameter("status", StatusZamowienia.POCZATKOWY);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp dataod;
        dataod = new java.sql.Timestamp(cal.getTime().getTime());
        query.setParameter("dataod", dataod);
        Timestamp datado;
        cal.add(Calendar.DATE, 1);
        datado = new java.sql.Timestamp(cal.getTime().getTime());
        query.setParameter("datado", datado);
        zamPoczatkowe = query.getResultList();
        //Logger.getGlobal().log(Level.SEVERE, zamPoczatkowe+"");
        return zamPoczatkowe;
    }

    public void przyjmijWplate(Zamowienie zam, double kwota, String tytulem, Uzytkownik tragarz) {
        if (kwota == 0) {
            return;
        }
        Transakcjezamowienia trZam = new Transakcjezamowienia();
        trZam.setKwota(kwota);
        trZam.setTytulem(tytulem);
        Calendar cal = Calendar.getInstance();
        trZam.setDataoperacji(new java.sql.Timestamp(cal.getTime().getTime()));
        trZam.setZamowienie(zam);
        trZam.setTragarz(tragarz);
        zam.getTransakcjezamowienia().add(0, trZam);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, zam.toString());
        this.edit(zam);
    }

    public void przyjmijWplate(Zamowienie zam, double kwota, String tytulem, Timestamp dataoperacji) {
        if (kwota == 0) {
            return;
        }
        Transakcjezamowienia trZam = new Transakcjezamowienia();
        trZam.setKwota(kwota);
        trZam.setTytulem(tytulem);
        trZam.setDataoperacji(dataoperacji);
        //zam.getTransakcjezamowienia().add(0, trZam);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, zam.toString());
        this.edit(zam);
    }

    public void akceptujElementZamówienia(Zamowienie zam) {
        Zamowienie zamOldValue = find(zam.getIdzamowienie());
        boolean zrealizowaneWcalosci = true;
        boolean nieZrealizowaneWcalosci = true;
        for (Zamowieniemenu zamMen : zam.getZamowieniemenu()) {
            int index = zamOldValue.getZamowieniemenu().indexOf(zamMen);
            Zamowieniemenu zamMenOld = zamOldValue.getZamowieniemenu().get(index);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(zamMenOld.getZrealizowano()) + "-------------");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(zamMenOld) + "-------------");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.valueOf(zamMen.getZrealizowano()));
            if (zamMenOld.getZrealizowano() == null) {
                zamMenOld.setZrealizowano(false);
            }
            if (!zamMen.getZrealizowano()) {
                zrealizowaneWcalosci = false;
                if (zamMenOld.getZrealizowano()) {
                    Transakcjezamowienia trZam = new Transakcjezamowienia();
                    trZam.setKwota(zamMen.getMenu().getCena());
                    trZam.setTytulem("Korekta dla zakupu " + zamMen.getMenu().getNazwa());
                    Calendar cal = Calendar.getInstance();
                    trZam.setDataoperacji(new java.sql.Timestamp(cal.getTime().getTime()));
                    //zam.getTransakcjezamowienia().add(0, trZam);
                }
            }
            if (zamMen.getZrealizowano()) {
                nieZrealizowaneWcalosci = false;
                if (!zamMenOld.getZrealizowano()) {
                    Transakcjezamowienia trZam = new Transakcjezamowienia();
                    trZam.setKwota(-zamMen.getMenu().getCena());
                    trZam.setTytulem("Kupiono " + zamMen.getMenu().getNazwa());
                    Calendar cal = Calendar.getInstance();
                    trZam.setDataoperacji(new java.sql.Timestamp(cal.getTime().getTime()));
                    //zam.getTransakcjezamowienia().add(0, trZam);
                }
            }
        }
        if (zrealizowaneWcalosci) {
            zam.setStatusZamowienia("Zrealizowane w całości");
        } else if (nieZrealizowaneWcalosci) {
            zam.setStatusZamowienia("W całości NIE zrealizowane");
        } else {
            zam.setStatusZamowienia("Zrealizowane częsciowo");
        }
        edit(zam);
    }

    public void zrealizowaneWcalosci(Zamowienie zam) {
        List<Zamowieniemenu> zamList = new ArrayList<Zamowieniemenu>();
        for (Zamowieniemenu zamMen : zam.getZamowieniemenu()) {
            zamMen.setZrealizowano(true);
            zamList.add(zamMen);
        }
        zam.setZamowieniemenu(zamList);
        akceptujElementZamówienia(zam);
    }

    public ZamowienieFacade() {
        super(Zamowienie.class);
    }
}
