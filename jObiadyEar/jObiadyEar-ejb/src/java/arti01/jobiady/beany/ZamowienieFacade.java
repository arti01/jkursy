/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class ZamowienieFacade extends AbstractFacade<Zamowienie> {
    @PersistenceContext(unitName = "jObiadyEar-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZamowienieFacade() {
        super(Zamowienie.class);
    }
    
}
