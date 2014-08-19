/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author arti01
 */
public class DcProjektJpaController implements Serializable {
    private static final long serialVersionUID = 1L;

    public DcProjektJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(DcProjekt dcProjekt) {
        EntityManager em = null;
        if (findDcProjekt(dcProjekt.getNazwa()) != null) {
            return "nazwa już istnieje";
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dcProjekt);
            em.getTransaction().commit();
        }catch(Exception ex){
            return "blad";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public String edit(DcProjekt dcProjekt) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        DcProjekt old=findDcProjekt(dcProjekt.getId());
        if (findDcProjekt(dcProjekt.getNazwa()) != null && findDcProjekt(dcProjekt.getNazwa()).getId().equals(old.getId())) {
            return "nazwa już istnieje";
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DcProjekt persistentDcProjekt = em.find(DcProjekt.class, dcProjekt.getId());
            List<DcDokument> dcDokumentListOld = persistentDcProjekt.getDcDokumentList();
            List<DcDokument> dcDokumentListNew = dcProjekt.getDcDokumentList();
            List<String> illegalOrphanMessages = null;
            for (DcDokument dcDokumentListOldDcDokument : dcDokumentListOld) {
                if (!dcDokumentListNew.contains(dcDokumentListOldDcDokument)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DcDokument " + dcDokumentListOldDcDokument + " since its projektId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DcDokument> attachedDcDokumentListNew = new ArrayList<DcDokument>();
            for (DcDokument dcDokumentListNewDcDokumentToAttach : dcDokumentListNew) {
                dcDokumentListNewDcDokumentToAttach = em.getReference(dcDokumentListNewDcDokumentToAttach.getClass(), dcDokumentListNewDcDokumentToAttach.getId());
                attachedDcDokumentListNew.add(dcDokumentListNewDcDokumentToAttach);
            }
            dcDokumentListNew = attachedDcDokumentListNew;
            dcProjekt.setDcDokumentList(dcDokumentListNew);
            dcProjekt = em.merge(dcProjekt);
            for (DcDokument dcDokumentListNewDcDokument : dcDokumentListNew) {
                if (!dcDokumentListOld.contains(dcDokumentListNewDcDokument)) {
                    DcProjekt oldProjektIdOfDcDokumentListNewDcDokument = dcDokumentListNewDcDokument.getProjektId();
                    dcDokumentListNewDcDokument.setProjektId(dcProjekt);
                    dcDokumentListNewDcDokument = em.merge(dcDokumentListNewDcDokument);
                    if (oldProjektIdOfDcDokumentListNewDcDokument != null && !oldProjektIdOfDcDokumentListNewDcDokument.equals(dcProjekt)) {
                        oldProjektIdOfDcDokumentListNewDcDokument.getDcDokumentList().remove(dcDokumentListNewDcDokument);
                        oldProjektIdOfDcDokumentListNewDcDokument = em.merge(oldProjektIdOfDcDokumentListNewDcDokument);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dcProjekt.getId();
                if (findDcProjekt(id) == null) {
                    throw new NonexistentEntityException("The dcProjekt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DcProjekt dcProjekt;
            try {
                dcProjekt = em.getReference(DcProjekt.class, id);
                dcProjekt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dcProjekt with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DcDokument> dcDokumentListOrphanCheck = dcProjekt.getDcDokumentList();
            for (DcDokument dcDokumentListOrphanCheckDcDokument : dcDokumentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DcProjekt (" + dcProjekt + ") cannot be destroyed since the DcDokument " + dcDokumentListOrphanCheckDcDokument + " in its dcDokumentList field has a non-nullable projektId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(dcProjekt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DcProjekt> findEntities() {
        return findDcProjektEntities(true, -1, -1);
    }

    public List<DcProjekt> findDcProjektEntities(int maxResults, int firstResult) {
        return findDcProjektEntities(false, maxResults, firstResult);
    }

    private List<DcProjekt> findDcProjektEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DcProjekt.class));
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

    public DcProjekt findDcProjekt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DcProjekt.class, id);
        } finally {
            em.close();
        }
    }

    public DcProjekt findDcProjekt(String nazwa) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("DcProjekt.findByNazwa");
            q.setParameter("nazwa", nazwa);
            DcProjekt u = (DcProjekt) q.getResultList().get(0);
            //em.refresh(u.getStruktura());
            return u;
        } catch (NoResultException ex) {
            //ex.printStackTrace();
            return null;
        } catch (ArrayIndexOutOfBoundsException exb) {
            //ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    
    public int getDcProjektCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DcProjekt> rt = cq.from(DcProjekt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
