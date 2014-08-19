/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.eod2.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class UmUrzadzenieJpaController implements Serializable {

    private static final long serialVersionUID = 1L;

    public UmUrzadzenieJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(UmUrzadzenie umUrzadzenie) {
        if(umUrzadzenie.getGrupa()==null) return "brak grupy";
        if(umUrzadzenie.getUserOdpow()==null) return "brak osoby odpowiedzialnej";
        String blad = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UmGrupa grupa = umUrzadzenie.getGrupa();
            if (grupa != null) {
                grupa = em.getReference(grupa.getClass(), grupa.getId());
                umUrzadzenie.setGrupa(grupa);
            }
            em.persist(umUrzadzenie);
            if (grupa != null) {
                grupa.getUrzadzenieList().add(umUrzadzenie);
                grupa = em.merge(grupa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            blad = "nazwa już istnieje";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return blad;
    }

    public String edit(UmUrzadzenie umUrzadzenie) throws NonexistentEntityException, Exception {
        String blad = null;
        EntityManager em = null;
        if(umUrzadzenie.getGrupa()==null) return "brak grupy";
        if(umUrzadzenie.getUserOdpow()==null) return "brak osoby odpowiedzialnej";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UmUrzadzenie persistentUmUrzadzenie = em.find(UmUrzadzenie.class, umUrzadzenie.getId());
            UmGrupa grupaOld = persistentUmUrzadzenie.getGrupa();
            UmGrupa grupaNew = umUrzadzenie.getGrupa();
            if (grupaNew != null) {
                grupaNew = em.getReference(grupaNew.getClass(), grupaNew.getId());
                umUrzadzenie.setGrupa(grupaNew);
            }
            umUrzadzenie = em.merge(umUrzadzenie);
            if (grupaOld != null && !grupaOld.equals(grupaNew)) {
                grupaOld.getUrzadzenieList().remove(umUrzadzenie);
                grupaOld = em.merge(grupaOld);
            }
            if (grupaNew != null && !grupaNew.equals(grupaOld)) {
                grupaNew.getUrzadzenieList().add(umUrzadzenie);
                grupaNew = em.merge(grupaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            umUrzadzenie.setId(null);
            blad = "nazwa już istnieje";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return blad;
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UmUrzadzenie umUrzadzenie;
            try {
                umUrzadzenie = em.getReference(UmUrzadzenie.class, id);
                umUrzadzenie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The umUrzadzenie with id " + id + " no longer exists.", enfe);
            }
            UmGrupa grupa = umUrzadzenie.getGrupa();
            if (grupa != null) {
                grupa.getUrzadzenieList().remove(umUrzadzenie);
                grupa = em.merge(grupa);
            }
            em.remove(umUrzadzenie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UmUrzadzenie> findUmUrzadzenieEntities() {
        return findUmUrzadzenieEntities(true, -1, -1);
    }

    public List<UmUrzadzenie> findUmUrzadzenieEntities(int maxResults, int firstResult) {
        return findUmUrzadzenieEntities(false, maxResults, firstResult);
    }

    private List<UmUrzadzenie> findUmUrzadzenieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UmUrzadzenie.class));
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

    public UmUrzadzenie findUmUrzadzenie(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UmUrzadzenie.class, id);
        } finally {
            em.close();
        }
    }

    public int getUmUrzadzenieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UmUrzadzenie> rt = cq.from(UmUrzadzenie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
