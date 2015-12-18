/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author 103039
 * @param <X>
 */
public abstract class AbstKontroler<X extends AbstEncja> {

    @PersistenceContext
    protected EntityManager em;

    private final X type;
    static final Logger LOGGER = Logger.getAnonymousLogger();

    public AbstKontroler(X type) {
        this.type = type;
    }

    public List<X> findEntities() {
        return findEntities(true, -1, -1);
    }

    public List<X> getFindEntities() {
        return findEntities();
    }

    public X findEntities(String nazwa) {
        try {
            Query q = em.createNamedQuery(this.type.getClass().getSimpleName() + ".findByNazwa");
            q.setParameter("nazwa", nazwa);
            @SuppressWarnings("unchecked")
            X u = (X) q.getResultList().get(0);
            return u;
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public X findObiekt(X obiekt) {
        return (X) em.find(type.getClass(), obiekt.getId());
    }

    @SuppressWarnings("unchecked")
    public X findObiekt(Integer id) {
        return (X) em.find(type.getClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<X> findEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(type.getClass()));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "blad", e);
        }
        return null;
    }

    public Map<String, String> create(X obiekt) throws Exception {
        Map<String, String> bledy = new HashMap<>();
        if (findEntities(obiekt.getNazwa()) != null) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }
        em.persist(obiekt);

        return bledy;
    }

    public Map<String, String> edit(X obiekt) throws Exception {
        X oldObiekt = findObiekt(obiekt);
        Map<String, String> bledy = new HashMap<>();
        if ((findEntities(obiekt.getNazwa()) != null) && (!obiekt.getNazwa().equals(oldObiekt.getNazwa()))) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }
        em.merge(obiekt);
        return bledy;
    }

    public void destroy(X obiekt) {
        try {
            em.remove(em.merge(obiekt));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "blad", ex);
        }
    }
}
