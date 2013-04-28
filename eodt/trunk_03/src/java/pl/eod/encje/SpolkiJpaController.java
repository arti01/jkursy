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
import pl.eod.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class SpolkiJpaController implements Serializable {

    public SpolkiJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(Spolki spolki) {
        if (spolki.getUserList() == null) {
            spolki.setUserList(new ArrayList<Uzytkownik>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(spolki);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            return "blad";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public String edit(Spolki spolki) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Spolki persistentSpolki = em.find(Spolki.class, spolki.getId());
            List<Uzytkownik> userListOld = persistentSpolki.getUserList();
            List<Uzytkownik> userListNew = spolki.getUserList();
            List<Uzytkownik> attachedUserListNew = new ArrayList<Uzytkownik>();
            for (Uzytkownik userListNewUzytkownikToAttach : userListNew) {
                userListNewUzytkownikToAttach = em.getReference(userListNewUzytkownikToAttach.getClass(), userListNewUzytkownikToAttach.getId());
                attachedUserListNew.add(userListNewUzytkownikToAttach);
            }
            userListNew = attachedUserListNew;
            spolki.setUserList(userListNew);
            spolki = em.merge(spolki);
            for (Uzytkownik userListOldUzytkownik : userListOld) {
                if (!userListNew.contains(userListOldUzytkownik)) {
                    userListOldUzytkownik.setSpolkaId(null);
                    userListOldUzytkownik = em.merge(userListOldUzytkownik);
                }
            }
            for (Uzytkownik userListNewUzytkownik : userListNew) {
                if (!userListOld.contains(userListNewUzytkownik)) {
                    Spolki oldSpolkaIdOfUserListNewUzytkownik = userListNewUzytkownik.getSpolkaId();
                    userListNewUzytkownik.setSpolkaId(spolki);
                    userListNewUzytkownik = em.merge(userListNewUzytkownik);
                    if (oldSpolkaIdOfUserListNewUzytkownik != null && !oldSpolkaIdOfUserListNewUzytkownik.equals(spolki)) {
                        oldSpolkaIdOfUserListNewUzytkownik.getUserList().remove(userListNewUzytkownik);
                        oldSpolkaIdOfUserListNewUzytkownik = em.merge(oldSpolkaIdOfUserListNewUzytkownik);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = spolki.getId();
                if (findSpolki(id) == null) {
                    throw new NonexistentEntityException("The spolki with id " + id + " no longer exists.");
                }
            }
            //throw ex;
            return "blad";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Spolki spolki;
            try {
                spolki = em.getReference(Spolki.class, id);
                spolki.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The spolki with id " + id + " no longer exists.", enfe);
            }
            List<Uzytkownik> userList = spolki.getUserList();
            for (Uzytkownik userListUzytkownik : userList) {
                userListUzytkownik.setSpolkaId(null);
                userListUzytkownik = em.merge(userListUzytkownik);
            }
            em.remove(spolki);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Spolki> findSpolkiEntities() {
        System.err.println(findSpolkiEntities(true, -1, -1));
        return findSpolkiEntities(true, -1, -1);
    }

    public List<Spolki> findSpolkiEntities(int maxResults, int firstResult) {
        return findSpolkiEntities(false, maxResults, firstResult);
    }

    private List<Spolki> findSpolkiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Spolki.class));
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

    public Spolki findSpolki(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Spolki.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpolkiCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Spolki> rt = cq.from(Spolki.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
