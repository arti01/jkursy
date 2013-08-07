/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.Instancje;
import encje.InterfejsyInstancje;
import encje.InterfejsySystemy;
import excep.DatabaseEx;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class InstancjeFacade extends AbstractFacade<Instancje> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;
    
    @EJB
    InterfejsyInstancjeFacade iIF;
    @EJB
    SystemyFacade sF;
    @EJB
    InterfejsySystemyFacade iSF;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public InstancjeFacade() {
        super(Instancje.class);
    }
    
    @Override
    public void create(Instancje instancje) throws DatabaseEx{
        instancje.setInterfejsyInstancjeList(new ArrayList<InterfejsyInstancje>());
        for(InterfejsySystemy is:instancje.getSystemy().getInterfejsySystemyList()){
            InterfejsyInstancje ii=new InterfejsyInstancje();
            ii.setInterfejsySystemy(is);
            ii.setInstancje(instancje);
            instancje.getInterfejsyInstancjeList().add(ii);
        }
        instancje.getSystemy().getInstancjeList().add(instancje);
        sF.edit(instancje.getSystemy());
        em.flush();
    }
    
    @Override
    public void remove(Instancje i) throws DatabaseEx{
        i.getSystemy().getInstancjeList().remove(i);
        sF.edit(i.getSystemy());
        em.flush();
    }
}
