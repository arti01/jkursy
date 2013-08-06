/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.ZestawyTestowe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class ZestawyTestoweFacade extends AbstractFacade<ZestawyTestowe> {
    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZestawyTestoweFacade() {
        super(ZestawyTestowe.class);
    }
    
}
