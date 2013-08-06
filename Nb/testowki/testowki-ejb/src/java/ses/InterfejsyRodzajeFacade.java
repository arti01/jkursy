/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.InterfejsyRodzaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class InterfejsyRodzajeFacade extends AbstractFacade<InterfejsyRodzaje> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterfejsyRodzajeFacade() {
        super(InterfejsyRodzaje.class);
    }
    
}
