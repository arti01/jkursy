/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.abstr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import pl.eod2.encje.DcTeczka;

/**
 *
 * @author 103039
 * @param <X>
 */
public abstract class AbstKontroler<X extends AbstEncja> {

    private final X type;

    public AbstKontroler(X type) {
        this.type = type;
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<X> findEntities() {
        return findEntities(true, -1, -1);
    }

    public X findEntities(String nazwa) {
        EntityManager em = getEntityManager();
        try {
            System.err.println(this.type.getClass().getName());
            Query q = em.createNamedQuery(this.type.getClass().getSimpleName() + ".findByNazwa");
            q.setParameter("nazwa", nazwa);
            X u = (X) q.getResultList().get(0);
            //em.refresh(u.getStruktura());
            return u;
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    private X findObiekt(X obiekt) {
        EntityManager em = getEntityManager();
        try {
            return (X) em.find(type.getClass(), obiekt.getId());
        } finally {
            em.close();
        }
    }
    
    private List<X> findEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DcTeczka.class));
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

    public Map<String, String> create(X obiekt) {
        Map<String, String> bledy = new HashMap<>();
        EntityManager em = null;
        if (findEntities(obiekt.getNazwa()) != null) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if(!bledy.isEmpty()) return bledy;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obiekt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            obiekt.setId(null);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bledy;
    }

    public Map<String, String> edit(X obiekt) {
        X oldObiekt=findObiekt(obiekt);
        Map<String, String> bledy = new HashMap<>();
        EntityManager em = null;
        if ((findEntities(obiekt.getNazwa()) != null)&&(!obiekt.getNazwa().equals(oldObiekt.getNazwa()))) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if(!bledy.isEmpty()) return bledy;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(obiekt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            obiekt.setId(null);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bledy;
    }
    
    public void destroy(X obiekt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(obiekt));
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
