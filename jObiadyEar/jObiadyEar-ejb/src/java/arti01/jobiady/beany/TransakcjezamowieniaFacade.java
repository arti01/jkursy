/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 103039
 */
@Stateful
public class TransakcjezamowieniaFacade extends AbstractFacade<Transakcjezamowienia> {
    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransakcjezamowieniaFacade() {
        super(Transakcjezamowienia.class);
    }
    
}
