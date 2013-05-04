/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.eod.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class UzytkownikJpaController implements Serializable {

    public UzytkownikJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Uzytkownik create(Uzytkownik uzytkownik) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Struktura struktura = uzytkownik.getStruktura();
            if (struktura != null) {
                struktura = em.getReference(struktura.getClass(), struktura.getId());
                uzytkownik.setStruktura(struktura);
            }
            em.persist(uzytkownik);

            if (struktura != null) {
                Uzytkownik oldUserIdOfStruktura = struktura.getUserId();
                if (oldUserIdOfStruktura != null) {
                    oldUserIdOfStruktura.setStruktura(null);
                    oldUserIdOfStruktura = em.merge(oldUserIdOfStruktura);
                }
                struktura.setUserId(uzytkownik);
                struktura = em.merge(struktura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return uzytkownik;
    }

    public void editaRTI(Uzytkownik uzytkownik) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(uzytkownik);
        em.getTransaction().commit();
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uzytkownik uzytkownik;
            try {
                uzytkownik = em.getReference(Uzytkownik.class, id);
                uzytkownik.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uzytkownik with id " + id + " no longer exists.", enfe);
            }
            Struktura struktura = uzytkownik.getStruktura();
            if (struktura != null) {
                struktura.setUserId(null);
                struktura = em.merge(struktura);
            }
            em.remove(uzytkownik);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Uzytkownik> findUzytkownikEntities() {
        return findUzytkownikEntities(true, -1, -1);
    }

    public List<Uzytkownik> findUzytkownikEntities(Spolki spolka) {
         EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object> cq = cb.createQuery();
            Root<Uzytkownik> user = cq.from(Uzytkownik.class);
            cq.select(user);
            Predicate nadrz = cb.and(cb.equal(user.get(Uzytkownik_.spolkaId), spolka),cb.isNotNull(user.get(Uzytkownik_.spolkaId)));
            cq.where(nadrz);
            Query q = em.createQuery(cq);
            System.err.println(q.getResultList());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Uzytkownik> findUzytkownikEntities(int maxResults, int firstResult) {
        return findUzytkownikEntities(false, maxResults, firstResult);
    }

    private List<Uzytkownik> findUzytkownikEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uzytkownik.class));
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

    public Uzytkownik findUzytkownik(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uzytkownik.class, id);
        } finally {
            em.close();
        }
    }

    public Uzytkownik findUzytkownikByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Uzytkownik> user = cq.from(Uzytkownik.class);
            cq.select(user);
            Predicate emailP = cb.equal(user.get(Uzytkownik_.adrEmail), email);
            Predicate niePuste = cb.notEqual(user.get(Uzytkownik_.adrEmail), "");
            cq.where(cb.and(emailP, niePuste));
            Query q = em.createQuery(cq);
            if (q.getResultList().isEmpty()) {
                return null;
            } else {
                return (Uzytkownik) q.setMaxResults(1).getSingleResult();
            }
        } finally {
            em.close();
        }
    }

    public int getUzytkownikCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uzytkownik> rt = cq.from(Uzytkownik.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Struktura findStruktura(String email) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Uzytkownik.findByAdrEmail");
            q.setParameter("adrEmail", email);
            Uzytkownik u = (Uzytkownik) q.getResultList().get(0);
            em.refresh(u);
            //em.refresh(u.getStruktura());
            return u.getStruktura();
        } catch (NoResultException ex) {
            //ex.printStackTrace();
            return null;
        } catch (Exception ex1) {
            return null;
        } finally {
            em.close();
        }
    }

    public Struktura findStrukturaExtid(String extid) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Uzytkownik.findByExtId");
            q.setParameter("extId", extid);
            Uzytkownik u = (Uzytkownik) q.getSingleResult();
            em.refresh(u);
            //em.refresh(u.getStruktura());
            return u.getStruktura();
        } catch (NoResultException ex) {
            //ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
