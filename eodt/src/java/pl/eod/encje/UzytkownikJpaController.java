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
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.eod.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class UzytkownikJpaController implements Serializable {

    public UzytkownikJpaController() {
        if(this.emf==null) {
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
            Struktura strukturaSec = uzytkownik.getStrukturaSec();
            if (strukturaSec != null) {
                strukturaSec = em.getReference(strukturaSec.getClass(), strukturaSec.getId());
                uzytkownik.setStrukturaSec(strukturaSec);
            }
            Struktura struktura = uzytkownik.getStruktura();
            if (struktura != null) {
                struktura = em.getReference(struktura.getClass(), struktura.getId());
                uzytkownik.setStruktura(struktura);
            }
            em.persist(uzytkownik);
            if (strukturaSec != null) {
                Uzytkownik oldSecUserIdOfStrukturaSec = strukturaSec.getSecUserId();
                if (oldSecUserIdOfStrukturaSec != null) {
                    oldSecUserIdOfStrukturaSec.setStrukturaSec(null);
                    oldSecUserIdOfStrukturaSec = em.merge(oldSecUserIdOfStrukturaSec);
                }
                strukturaSec.setSecUserId(uzytkownik);
                strukturaSec = em.merge(strukturaSec);
            }
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
    
    public void edit(Uzytkownik uzytkownik) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uzytkownik persistentUzytkownik = em.find(Uzytkownik.class, uzytkownik.getId());
            Struktura strukturaSecOld = persistentUzytkownik.getStrukturaSec();
            Struktura strukturaSecNew = uzytkownik.getStrukturaSec();
            Struktura strukturaOld = persistentUzytkownik.getStruktura();
            Struktura strukturaNew = uzytkownik.getStruktura();
            if (strukturaSecNew != null) {
                strukturaSecNew = em.getReference(strukturaSecNew.getClass(), strukturaSecNew.getId());
                uzytkownik.setStrukturaSec(strukturaSecNew);
            }
            if (strukturaNew != null) {
                strukturaNew = em.getReference(strukturaNew.getClass(), strukturaNew.getId());
                uzytkownik.setStruktura(strukturaNew);
            }
            uzytkownik = em.merge(uzytkownik);
            if (strukturaSecOld != null && !strukturaSecOld.equals(strukturaSecNew)) {
                strukturaSecOld.setSecUserId(null);
                strukturaSecOld = em.merge(strukturaSecOld);
            }
            if (strukturaSecNew != null && !strukturaSecNew.equals(strukturaSecOld)) {
                Uzytkownik oldSecUserIdOfStrukturaSec = strukturaSecNew.getSecUserId();
                if (oldSecUserIdOfStrukturaSec != null) {
                    oldSecUserIdOfStrukturaSec.setStrukturaSec(null);
                    oldSecUserIdOfStrukturaSec = em.merge(oldSecUserIdOfStrukturaSec);
                }
                strukturaSecNew.setSecUserId(uzytkownik);
                strukturaSecNew = em.merge(strukturaSecNew);
            }
            if (strukturaOld != null && !strukturaOld.equals(strukturaNew)) {
                strukturaOld.setUserId(null);
                strukturaOld = em.merge(strukturaOld);
            }
            if (strukturaNew != null && !strukturaNew.equals(strukturaOld)) {
                Uzytkownik oldUserIdOfStruktura = strukturaNew.getUserId();
                if (oldUserIdOfStruktura != null) {
                    oldUserIdOfStruktura.setStruktura(null);
                    oldUserIdOfStruktura = em.merge(oldUserIdOfStruktura);
                }
                strukturaNew.setUserId(uzytkownik);
                strukturaNew = em.merge(strukturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = uzytkownik.getId();
                if (findUzytkownik(id) == null) {
                    throw new NonexistentEntityException("The uzytkownik with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
            Struktura strukturaSec = uzytkownik.getStrukturaSec();
            if (strukturaSec != null) {
                strukturaSec.setSecUserId(null);
                strukturaSec = em.merge(strukturaSec);
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
    
}
