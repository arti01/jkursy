/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.eod.encje.exceptions.IllegalOrphanException;
import pl.eod.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class WnUrlopJpaController implements Serializable {

    public WnUrlopJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public String createEdit(WnUrlop wnUrlop) {
        if(wnUrlop.getDataDo().before(wnUrlop.getDataOd())) return "Data końca nie może być przed datą początku";
        if (wnUrlop.getWnHistoriaList() == null) {
            wnUrlop.setWnHistoriaList(new ArrayList<WnHistoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Uzytkownik u=em.find(Uzytkownik.class, wnUrlop.getUzytkownik().getId());
            u.getWnUrlopList().add(0, wnUrlop);
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    
    public String destroy(WnUrlop wnUrlop) {   
        EntityManager em = null;
        try {
            em = getEntityManager();
            Uzytkownik u=em.find(Uzytkownik.class, wnUrlop.getUzytkownik().getId());
            System.err.println(u.getWnUrlopList());
            u.getWnUrlopList().remove(wnUrlop);
            System.err.println(u.getWnUrlopList());
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }
    
    
   

    public List<WnUrlop> findWnUrlopEntities() {
        return findWnUrlopEntities(true, -1, -1);
    }

    public List<WnUrlop> findWnUrlopEntities(int maxResults, int firstResult) {
        return findWnUrlopEntities(false, maxResults, firstResult);
    }

    private List<WnUrlop> findWnUrlopEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WnUrlop.class));
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

    public WnUrlop findWnUrlop(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WnUrlop.class, id);
        } finally {
            em.close();
        }
    }

    public int getWnUrlopCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WnUrlop> rt = cq.from(WnUrlop.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
