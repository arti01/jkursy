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

    public void create(WnUrlop wnUrlop) {
        if (wnUrlop.getWnHistoriaList() == null) {
            wnUrlop.setWnHistoriaList(new ArrayList<WnHistoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WnStatusy statusId = wnUrlop.getStatusId();
            if (statusId != null) {
                statusId = em.getReference(statusId.getClass(), statusId.getId());
                wnUrlop.setStatusId(statusId);
            }
            WnRodzaje rodzajId = wnUrlop.getRodzajId();
            if (rodzajId != null) {
                rodzajId = em.getReference(rodzajId.getClass(), rodzajId.getId());
                wnUrlop.setRodzajId(rodzajId);
            }
            Uzytkownik uzytkownik = wnUrlop.getUzytkownik();
            if (uzytkownik != null) {
                uzytkownik = em.getReference(uzytkownik.getClass(), uzytkownik.getId());
                wnUrlop.setUzytkownik(uzytkownik);
            }
            List<WnHistoria> attachedWnHistoriaList = new ArrayList<WnHistoria>();
            for (WnHistoria wnHistoriaListWnHistoriaToAttach : wnUrlop.getWnHistoriaList()) {
                wnHistoriaListWnHistoriaToAttach = em.getReference(wnHistoriaListWnHistoriaToAttach.getClass(), wnHistoriaListWnHistoriaToAttach.getId());
                attachedWnHistoriaList.add(wnHistoriaListWnHistoriaToAttach);
            }
            wnUrlop.setWnHistoriaList(attachedWnHistoriaList);
            em.persist(wnUrlop);
            if (statusId != null) {
                statusId.getWnUrlopList().add(wnUrlop);
                statusId = em.merge(statusId);
            }
            if (rodzajId != null) {
                rodzajId.getWnUrlopList().add(wnUrlop);
                rodzajId = em.merge(rodzajId);
            }
            if (uzytkownik != null) {
                uzytkownik.getWnUrlopList().add(wnUrlop);
                uzytkownik = em.merge(uzytkownik);
            }
            for (WnHistoria wnHistoriaListWnHistoria : wnUrlop.getWnHistoriaList()) {
                WnUrlop oldUrlopIdOfWnHistoriaListWnHistoria = wnHistoriaListWnHistoria.getUrlopId();
                wnHistoriaListWnHistoria.setUrlopId(wnUrlop);
                wnHistoriaListWnHistoria = em.merge(wnHistoriaListWnHistoria);
                if (oldUrlopIdOfWnHistoriaListWnHistoria != null) {
                    oldUrlopIdOfWnHistoriaListWnHistoria.getWnHistoriaList().remove(wnHistoriaListWnHistoria);
                    oldUrlopIdOfWnHistoriaListWnHistoria = em.merge(oldUrlopIdOfWnHistoriaListWnHistoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WnUrlop wnUrlop) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WnUrlop persistentWnUrlop = em.find(WnUrlop.class, wnUrlop.getId());
            WnStatusy statusIdOld = persistentWnUrlop.getStatusId();
            WnStatusy statusIdNew = wnUrlop.getStatusId();
            WnRodzaje rodzajIdOld = persistentWnUrlop.getRodzajId();
            WnRodzaje rodzajIdNew = wnUrlop.getRodzajId();
            Uzytkownik uzytkownikOld = persistentWnUrlop.getUzytkownik();
            Uzytkownik uzytkownikNew = wnUrlop.getUzytkownik();
            List<WnHistoria> wnHistoriaListOld = persistentWnUrlop.getWnHistoriaList();
            List<WnHistoria> wnHistoriaListNew = wnUrlop.getWnHistoriaList();
            List<String> illegalOrphanMessages = null;
            for (WnHistoria wnHistoriaListOldWnHistoria : wnHistoriaListOld) {
                if (!wnHistoriaListNew.contains(wnHistoriaListOldWnHistoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain WnHistoria " + wnHistoriaListOldWnHistoria + " since its urlopId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (statusIdNew != null) {
                statusIdNew = em.getReference(statusIdNew.getClass(), statusIdNew.getId());
                wnUrlop.setStatusId(statusIdNew);
            }
            if (rodzajIdNew != null) {
                rodzajIdNew = em.getReference(rodzajIdNew.getClass(), rodzajIdNew.getId());
                wnUrlop.setRodzajId(rodzajIdNew);
            }
            if (uzytkownikNew != null) {
                uzytkownikNew = em.getReference(uzytkownikNew.getClass(), uzytkownikNew.getId());
                wnUrlop.setUzytkownik(uzytkownikNew);
            }
            List<WnHistoria> attachedWnHistoriaListNew = new ArrayList<WnHistoria>();
            for (WnHistoria wnHistoriaListNewWnHistoriaToAttach : wnHistoriaListNew) {
                wnHistoriaListNewWnHistoriaToAttach = em.getReference(wnHistoriaListNewWnHistoriaToAttach.getClass(), wnHistoriaListNewWnHistoriaToAttach.getId());
                attachedWnHistoriaListNew.add(wnHistoriaListNewWnHistoriaToAttach);
            }
            wnHistoriaListNew = attachedWnHistoriaListNew;
            wnUrlop.setWnHistoriaList(wnHistoriaListNew);
            wnUrlop = em.merge(wnUrlop);
            if (statusIdOld != null && !statusIdOld.equals(statusIdNew)) {
                statusIdOld.getWnUrlopList().remove(wnUrlop);
                statusIdOld = em.merge(statusIdOld);
            }
            if (statusIdNew != null && !statusIdNew.equals(statusIdOld)) {
                statusIdNew.getWnUrlopList().add(wnUrlop);
                statusIdNew = em.merge(statusIdNew);
            }
            if (rodzajIdOld != null && !rodzajIdOld.equals(rodzajIdNew)) {
                rodzajIdOld.getWnUrlopList().remove(wnUrlop);
                rodzajIdOld = em.merge(rodzajIdOld);
            }
            if (rodzajIdNew != null && !rodzajIdNew.equals(rodzajIdOld)) {
                rodzajIdNew.getWnUrlopList().add(wnUrlop);
                rodzajIdNew = em.merge(rodzajIdNew);
            }
            if (uzytkownikOld != null && !uzytkownikOld.equals(uzytkownikNew)) {
                uzytkownikOld.getWnUrlopList().remove(wnUrlop);
                uzytkownikOld = em.merge(uzytkownikOld);
            }
            if (uzytkownikNew != null && !uzytkownikNew.equals(uzytkownikOld)) {
                uzytkownikNew.getWnUrlopList().add(wnUrlop);
                uzytkownikNew = em.merge(uzytkownikNew);
            }
            for (WnHistoria wnHistoriaListNewWnHistoria : wnHistoriaListNew) {
                if (!wnHistoriaListOld.contains(wnHistoriaListNewWnHistoria)) {
                    WnUrlop oldUrlopIdOfWnHistoriaListNewWnHistoria = wnHistoriaListNewWnHistoria.getUrlopId();
                    wnHistoriaListNewWnHistoria.setUrlopId(wnUrlop);
                    wnHistoriaListNewWnHistoria = em.merge(wnHistoriaListNewWnHistoria);
                    if (oldUrlopIdOfWnHistoriaListNewWnHistoria != null && !oldUrlopIdOfWnHistoriaListNewWnHistoria.equals(wnUrlop)) {
                        oldUrlopIdOfWnHistoriaListNewWnHistoria.getWnHistoriaList().remove(wnHistoriaListNewWnHistoria);
                        oldUrlopIdOfWnHistoriaListNewWnHistoria = em.merge(oldUrlopIdOfWnHistoriaListNewWnHistoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = wnUrlop.getId();
                if (findWnUrlop(id) == null) {
                    throw new NonexistentEntityException("The wnUrlop with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WnUrlop wnUrlop;
            try {
                wnUrlop = em.getReference(WnUrlop.class, id);
                wnUrlop.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wnUrlop with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<WnHistoria> wnHistoriaListOrphanCheck = wnUrlop.getWnHistoriaList();
            for (WnHistoria wnHistoriaListOrphanCheckWnHistoria : wnHistoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WnUrlop (" + wnUrlop + ") cannot be destroyed since the WnHistoria " + wnHistoriaListOrphanCheckWnHistoria + " in its wnHistoriaList field has a non-nullable urlopId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            WnStatusy statusId = wnUrlop.getStatusId();
            if (statusId != null) {
                statusId.getWnUrlopList().remove(wnUrlop);
                statusId = em.merge(statusId);
            }
            WnRodzaje rodzajId = wnUrlop.getRodzajId();
            if (rodzajId != null) {
                rodzajId.getWnUrlopList().remove(wnUrlop);
                rodzajId = em.merge(rodzajId);
            }
            Uzytkownik uzytkownik = wnUrlop.getUzytkownik();
            if (uzytkownik != null) {
                uzytkownik.getWnUrlopList().remove(wnUrlop);
                uzytkownik = em.merge(uzytkownik);
            }
            em.remove(wnUrlop);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
