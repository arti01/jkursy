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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author arti01
 */
@Stateless
public class ZamowienieFacade extends AbstractFacade<Zamowienie> {

    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;
    private List<Zamowienie> zamPoczatkowe = new ArrayList<Zamowienie>();

    @Override
    protected EntityManager getEntityManager() {
        return em;
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
        return zamPoczatkowe;
    }
    
    public void przyjmijWplate(Zamowienie zam, double kwota, String tytulem){
        if(kwota==0) return;
         Transakcjezamowienia trZam=new Transakcjezamowienia();
            trZam.setKwota(kwota);
            trZam.setTytulem(tytulem);
            zam.getTransakcjezamowienia().add(0, trZam);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, zam.toString());
            this.edit(zam);
    }
    
    public ZamowienieFacade() {
        super(Zamowienie.class);
    }
}
