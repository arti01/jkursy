/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.test;

import arti01.jobiady.beany.DniTyg;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arti01
 */
@Stateless
public class DniTygFacade extends AbstractFacade<DniTyg> {
    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DniTygFacade() {
        super(DniTyg.class);
    }
    
}
