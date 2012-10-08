/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class KursFacade extends AbstractFacade<Kurs> {
    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;
@EJB ZamowienieFacade zf;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void przyjmijZamowienie(Zamowienie zam, Kurs kurs){
        zam.setStatusZamowienia(StatusZamowienia.WREALIZACJI);
        zam.setKurs(kurs);
        kurs.getZamowienia().add(0, zam);
        edit(kurs);
    }
    
    public void wycofajZamowienie(Zamowienie zam){
        Kurs kurs=zam.getKurs();
        zam.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        zam.setKurs(null);
        kurs.getZamowienia().remove(zam);
        zf.edit(zam);
        edit(kurs);
    }
    
    public KursFacade() {
        super(Kurs.class);
    }
    
}
