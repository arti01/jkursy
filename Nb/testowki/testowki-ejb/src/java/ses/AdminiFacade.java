/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.Admini;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 103039
 */
@Stateless
public class AdminiFacade extends AbstractFacade<Admini> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminiFacade() {
        super(Admini.class);
    }
    
}
