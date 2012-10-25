/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateful
public class KursFacade extends AbstractFacade<Kurs> {
    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;
@EJB ZamowienieFacade zf;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
   public Kurs przyjmijZamowienie(Zamowienie zam, Kurs kurs){
        //zam=getEntityManager().find(Zamowienie.class, zam.getIdzamowienie());
        //getEntityManager().refresh(zam);
        zam.setStatusZamowienia(StatusZamowienia.WREALIZACJI);
        zam.setKurs(kurs);
        kurs.getZamowienia().add(0, zam);
        kurs=edit(kurs);
        /*Logger.getAnonymousLogger().log(Level.OFF, zam.getKurs()+"");
        Logger.getAnonymousLogger().log(Level.OFF, zam+"");
        Logger.getAnonymousLogger().log(Level.OFF, zam.getStatusZamowienia());
        Logger.getAnonymousLogger().log(Level.OFF, kurs.getZamowienia()+""+kurs.getZamowienia().get(0).getKurs());
        Logger.getAnonymousLogger().log(Level.OFF, kurs.getZamowienia()+""+kurs.getZamowienia().get(0).getStatusZamowienia());*/
        return kurs;
    }
    
    public Kurs wycofajZamowienie(Zamowienie zam){
        Kurs kurs=zam.getKurs();
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zam.setKurs(null);
        kurs.getZamowienia().remove(zam);
        zf.edit(zam);
        return edit(kurs);
    }
    
    public KursFacade() {
        super(Kurs.class);
    }
    
}
