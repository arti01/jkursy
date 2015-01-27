/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.util.List;
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<UserRoles> ur = cq.from(UserRoles.class);
            cq.select(ur);
            cq.where(cb.equal(ur.get(UserRoles_.rolename), nazwa));
            Query q = em.createQuery(cq);
            return (UserRoles) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<UserRoles> findDostepneDoEdycji() {
        String rola1="eodstru";
        String rola2="eodurlop";
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<UserRoles> cfg = cq.from(UserRoles.class);
            cq.select(cfg);
            //cq.where(cb.equal(cfg.get(UserRoles_.rolename), rola1));
            cq.where(
                    cb.or(
                    cb.equal(cfg.get(UserRoles_.rolename), rola1), cb.equal(cfg.get(UserRoles_.rolename), rola2), cb.between(cfg.get(UserRoles_.id), 100, 120))
            );
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<UserRoles> findDostepneDoRodzajowUrlopow() {

        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<UserRoles> cfg = cq.from(UserRoles.class);
            cq.select(cfg);
            //cq.where(cb.equal(cfg.get(UserRoles_.rolename), rola1));
            cq.where(
                    cb.between(cfg.get(UserRoles_.id), 100, 120)
            );
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<UserRoles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<UserRoles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    public void edit(UserRoles rola){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(rola);
        em.getTransaction().commit();
    }
    
    private List<UserRoles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRoles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    

}
