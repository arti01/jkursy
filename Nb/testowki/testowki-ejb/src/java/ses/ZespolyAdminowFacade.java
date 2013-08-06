/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import encje.ZespolyAdminow;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author arti01
 */
@Stateless
public class ZespolyAdminowFacade extends AbstractFacade<ZespolyAdminow> {

    @PersistenceContext(unitName = "testowki-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*public List<ZespolyAdminow> findAllOrderNazwa() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZespolyAdminow> c = cq.from(ZespolyAdminow.class);
        cq.select(c);
        cq.orderBy(cb.asc(c.get("nazwa")));
        return getEntityManager().createQuery(cq).getResultList();
    }*/

    public ZespolyAdminowFacade() {
        super(ZespolyAdminow.class);
    }
}
