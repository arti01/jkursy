/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.Instancje;
import encje.InterfejsyInstancje;
import encje.InterfejsySystemy;
import encje.Systemy;
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
public class InterfejsySystemyFacade extends AbstractFacade<InterfejsySystemy> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;
    
    @EJB
    SystemyFacade sF;
    @EJB
    InstancjeFacade iF;
    @EJB
    InterfejsyInstancjeFacade iIF;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterfejsySystemyFacade() {
        super(InterfejsySystemy.class);
    }
    
    @Override
    public void create(InterfejsySystemy is) throws DatabaseEx{
        Systemy s=is.getSystemy();
        is.setInterfejsyInstancjeList(new ArrayList<InterfejsyInstancje>());
        for(Instancje i:is.getSystemy().getInstancjeList()){
            InterfejsyInstancje ii=new InterfejsyInstancje();
            ii.setInstancje(i);
            ii.setInterfejsySystemy(is);
            is.getInterfejsyInstancjeList().add(ii);
        }
        s.getInterfejsySystemyList().add(is);
        sF.edit(s);
        em.flush();
    }
    
    @Override
    public void remove(InterfejsySystemy is)throws DatabaseEx{
        Systemy s=is.getSystemy();
        s.getInterfejsySystemyList().remove(is);
        sF.edit(s);
    }
}
