/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author 103039
 */
public class StrukturaJpaController implements Serializable {

    public StrukturaJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<Struktura> getFindKierownicy() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createNamedQuery("Struktura.kierownicy");
            //LOG.log(Level.OFF, query.getResultList().toString()+"logger");
            List<Struktura> wynik= query.getResultList();
            return wynik;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /*@SuppressWarnings("unchecked")
    public List<Uzytkownik> findBezposrPodwl(Uzytkownik u) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createNamedQuery("Struktura.findBySzefId");
            query.setParameter("szefId", u);
            //LOG.log(Level.OFF, query.getResultList().toString()+"logger");
            List<Uzytkownik> wynik= query.getResultList();
            return wynik;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/
    
    private static final Logger LOG = Logger.getLogger(StrukturaJpaController.class.getName());

    public void create(Struktura struktura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uzytkownik secUserId = struktura.getSecUserId();
            if (secUserId != null) {
                secUserId = em.getReference(secUserId.getClass(), secUserId.getId());
                struktura.setSecUserId(secUserId);
            }
            Uzytkownik userId = struktura.getUserId();
            /*if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                struktura.setUserId(userId);
            }*/
            Dzial dzialId = struktura.getDzialId();
            if (dzialId != null) {
                dzialId = em.getReference(dzialId.getClass(), dzialId.getId());
                struktura.setDzialId(dzialId);
            }
            em.persist(struktura);
            if (secUserId != null) {
                Struktura oldStrukturaSecOfSecUserId = secUserId.getStrukturaSec();
                if (oldStrukturaSecOfSecUserId != null) {
                    oldStrukturaSecOfSecUserId.setSecUserId(null);
                    oldStrukturaSecOfSecUserId = em.merge(oldStrukturaSecOfSecUserId);
                }
                secUserId.setStrukturaSec(struktura);
                secUserId = em.merge(secUserId);
            }
            if (userId != null) {
                Struktura oldStrukturaSecOfUserId = userId.getStrukturaSec();
                if (oldStrukturaSecOfUserId != null) {
                    oldStrukturaSecOfUserId.setUserId(null);
                    oldStrukturaSecOfUserId = em.merge(oldStrukturaSecOfUserId);
                }
                userId.setStrukturaSec(struktura);
                userId = em.merge(userId);
            }
            if (dzialId != null) {
                dzialId.getStrukturaList().add(struktura);
                dzialId = em.merge(dzialId);
            }
            em.getTransaction().commit();
            if(struktura.getSzefId()!=null)em.refresh(em.find(struktura.getClass(), struktura.getSzefId().getId()));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editArti(Struktura struktura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Struktura persistentStruktura=em.find(struktura.getClass(), struktura.getId());
            em.getTransaction().begin();
            em.merge(struktura);
            em.getTransaction().commit();
            if(struktura.getSzefId()!=null)em.refresh(em.find(struktura.getClass(), struktura.getSzefId().getId()));
            if(persistentStruktura.getSzefId()!=null)em.refresh(persistentStruktura.getSzefId());
    } finally {
            if (em != null) {
                em.close();
            }
        }}
            
    public void edit(Struktura struktura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Struktura persistentStruktura = em.find(Struktura.class, struktura.getId());
            Uzytkownik secUserIdOld = persistentStruktura.getSecUserId();
            Uzytkownik secUserIdNew = struktura.getSecUserId();
            Uzytkownik userIdOld = persistentStruktura.getUserId();
            Uzytkownik userIdNew = struktura.getUserId();
            Dzial dzialIdOld = persistentStruktura.getDzialId();
            Dzial dzialIdNew = struktura.getDzialId();
            if (secUserIdNew != null) {
                secUserIdNew = em.getReference(secUserIdNew.getClass(), secUserIdNew.getId());
                struktura.setSecUserId(secUserIdNew);
            }
            /*if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                struktura.setUserId(userIdNew);
            }
            if (dzialIdNew != null) {
                dzialIdNew = em.getReference(dzialIdNew.getClass(), dzialIdNew.getId());
                struktura.setDzialId(dzialIdNew);
            }*/
            struktura = em.merge(struktura);
            if (secUserIdOld != null && !secUserIdOld.equals(secUserIdNew)) {
                secUserIdOld.setStrukturaSec(null);
                secUserIdOld = em.merge(secUserIdOld);
            }
            if (secUserIdNew != null && !secUserIdNew.equals(secUserIdOld)) {
                Struktura oldStrukturaSecOfSecUserId = secUserIdNew.getStrukturaSec();
                if (oldStrukturaSecOfSecUserId != null) {
                    oldStrukturaSecOfSecUserId.setSecUserId(null);
                    oldStrukturaSecOfSecUserId = em.merge(oldStrukturaSecOfSecUserId);
                }
                secUserIdNew.setStrukturaSec(struktura);
                secUserIdNew = em.merge(secUserIdNew);
            }
            /*if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.setStrukturaSec(null);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                Struktura oldStrukturaSecOfUserId = userIdNew.getStrukturaSec();
                if (oldStrukturaSecOfUserId != null) {
                    oldStrukturaSecOfUserId.setUserId(null);
                    oldStrukturaSecOfUserId = em.merge(oldStrukturaSecOfUserId);
                }
                userIdNew.setStrukturaSec(struktura);
                userIdNew = em.merge(userIdNew);
            }
            if (dzialIdOld != null && !dzialIdOld.equals(dzialIdNew)) {
                dzialIdOld.getStrukturaList().remove(struktura);
                dzialIdOld = em.merge(dzialIdOld);
            }
            if (dzialIdNew != null && !dzialIdNew.equals(dzialIdOld)) {
                dzialIdNew.getStrukturaList().add(struktura);
                dzialIdNew = em.merge(dzialIdNew);
            }*/
            em.getTransaction().commit();
            if(struktura.getSzefId()!=null)em.refresh(struktura.getSzefId());
            if(persistentStruktura.getSzefId()!=null)em.refresh(persistentStruktura.getSzefId());
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = struktura.getId();
                if (findStruktura(id) == null) {
                    throw new NonexistentEntityException("The struktura with id " + id + " no longer exists.");
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
            Struktura struktura;
            try {
                struktura = em.getReference(Struktura.class, id);
                struktura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The struktura with id " + id + " no longer exists.", enfe);
            }
            Uzytkownik secUserId = struktura.getSecUserId();
            if (secUserId != null) {
                secUserId.setStrukturaSec(null);
                secUserId = em.merge(secUserId);
            }
            Uzytkownik userId = struktura.getUserId();
            if (userId != null) {
                userId.setStrukturaSec(null);
                userId = em.merge(userId);
            }
            Dzial dzialId = struktura.getDzialId();
            if (dzialId != null) {
                dzialId.getStrukturaList().remove(struktura);
                dzialId = em.merge(dzialId);
            }
            em.remove(struktura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Struktura> findStrukturaEntities() {
        return findStrukturaEntities(true, -1, -1);
    }
    
    public List<Struktura> getFindStrukturaEntities() {
        return findStrukturaEntities(true, -1, -1);
    }

    public List<Struktura> findStrukturaEntities(int maxResults, int firstResult) {
        return findStrukturaEntities(false, maxResults, firstResult);
    }

    private List<Struktura> findStrukturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Struktura.class));
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

    public Struktura findStruktura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Struktura.class, id);
        } finally {
            em.close();
        }
    }

    public int getStrukturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Struktura> rt = cq.from(Struktura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
