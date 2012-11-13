/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.sesBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Lekcjarodzaje;

/**
 *
 * @author arti01
 */
@Stateless
public class LekcjarodzajeFacade extends AbstractFacade<Lekcjarodzaje> {
    @PersistenceContext(unitName = "jkursyJPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LekcjarodzajeFacade() {
        super(Lekcjarodzaje.class);
    }
    
}
