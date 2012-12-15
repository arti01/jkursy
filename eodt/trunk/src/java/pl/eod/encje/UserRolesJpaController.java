/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author arti01
 */
public class UserRolesJpaController implements Serializable {

    public UserRolesJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserRoles findByNazwa(String nazwa) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb=em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<UserRoles> ur = cq.from(UserRoles.class);
            cq.select(ur);
            cq.where(cb.equal(ur.get(UserRoles_.rolename), nazwa));
            Query q = em.createQuery(cq);
            return (UserRoles)q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
