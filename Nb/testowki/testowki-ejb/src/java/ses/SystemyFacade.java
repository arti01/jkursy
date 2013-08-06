/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.Instancje;
import encje.Systemy;
import encje.ZestawyTestowe;
import excep.DatabaseEx;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class SystemyFacade extends AbstractFacade<Systemy> {

    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;
    @EJB
    ZespolyAdminowFacade zAF;
    @EJB
    ZestawyTestoweFacade zTF;
    @EJB
    InstancjeFacade iF;
    @EJB
    InterfejsySystemyFacade iSF;
    
    private final static Logger l = Logger.getLogger(SystemyFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(Systemy systemy) throws DatabaseEx {
        Systemy old = find(systemy.getId());
        if (!systemy.getZespolyAdminow().equals(old.getZespolyAdminow())) {
            old.getZespolyAdminow().getSystemyList().remove(old);
            //zAF.edit(old.getZespolyAdminow());
            systemy.getZespolyAdminow().getSystemyList().add(systemy);
            zAF.edit(systemy.getZespolyAdminow());
        } else {
            em.merge(systemy);
        }
    }

    @Override
    public void remove(Systemy systemy) {
        systemy.getZespolyAdminow().getSystemyList().remove(systemy);
        try {
            zAF.edit(systemy.getZespolyAdminow());
        } catch (DatabaseEx ex) {
            Logger.getLogger(SystemyFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.remove(systemy);
    }

    @Override
    public void create(Systemy systemy) throws DatabaseEx {
        em.persist(systemy);
        for(ZestawyTestowe zt:zTF.findAllOrderNazwa()){
            Instancje i=new Instancje();
            i.setAdresip("ip dla "+zt.getNazwa());
            i.setNazwaserwera("nazwa serwera dla "+zt.getNazwa());
            i.setZestawyTestowe(zt);
            i.setSystemy(systemy);
            iF.create(i);
        }
    }

    public SystemyFacade() {
        super(Systemy.class);
    }

    public InstancjeFacade getiF() {
        return iF;
    }

    public void setiF(InstancjeFacade iF) {
        this.iF = iF;
    }

    public InterfejsySystemyFacade getiSF() {
        return iSF;
    }

    public void setiSF(InterfejsySystemyFacade iSF) {
        this.iSF = iSF;
    }
    
}
