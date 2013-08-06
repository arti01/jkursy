/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.InterfejsyInstancje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class InterfejsyInstancjeFacade extends AbstractFacade<InterfejsyInstancje> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterfejsyInstancjeFacade() {
        super(InterfejsyInstancje.class);
    }
    
}