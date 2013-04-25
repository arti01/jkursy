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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.eod.encje.Uzytkownik;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author 103039
 */
public class DcDokumentJpaController implements Serializable {

    public DcDokumentJpaController() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodtPU");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private DcDokument createKroki(DcDokument dcDokument) {
        dcDokument.setDcDokKrok(new ArrayList<DcDokumentKrok>());
        for (DcAkceptKroki aKrok : dcDokument.getRodzajId().getDcAkceptKroki()) {
            DcDokumentKrok krok = new DcDokumentKrok();
            krok.setAkcept(new DcAkceptStatus(1));
            krok.setDcAckeptTypKroku(aKrok.getDcAckeptTypKroku());
            krok.setIdDok(dcDokument);
            krok.setLp(aKrok.getLp());
            krok.setDcKrokUzytkownikaList(new ArrayList<DcDokumentKrokUzytkownik>());
            for (Uzytkownik u : aKrok.getUzytkownikList()) {
                DcDokumentKrokUzytkownik krokUser = new DcDokumentKrokUzytkownik();
                krokUser.setAkcept(new DcAkceptStatus(1));
                krokUser.setIdDokumentKrok(krok);
                krokUser.setIdUser(u);
                krok.getDcKrokUzytkownikaList().add(krokUser);
            }
            //em.persist(krok);
            dcDokument.getDcDokKrok().add(krok);
        }
        return dcDokument;
    }

    public String create(DcDokument dcDokument) {
        if (dcDokument.getDcPlikList() == null) {
            dcDokument.setDcPlikList(new ArrayList<DcPlik>());
        }
        dcDokument.setDataWprow(new Date());
        dcDokument.setDokStatusId(new DcDokumentStatus(1));
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dcDokument = this.createKroki(dcDokument);
            em.persist(dcDokument);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "blad";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void wyslijDoAkceptacji(DcDokument dcDokument) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dcDokument.setDokStatusId(new DcDokumentStatus(2));
            em.merge(dcDokument);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            //return "blad";
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String edit(DcDokument dcDokument) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DcDokument persistentDcDokument = em.find(DcDokument.class, dcDokument.getId());
            Uzytkownik userIdOld = persistentDcDokument.getUserId();
            Uzytkownik userIdNew = dcDokument.getUserId();
            DcZrodlo zrodloIdOld = persistentDcDokument.getZrodloId();
            DcZrodlo zrodloIdNew = dcDokument.getZrodloId();
            DcRodzaj rodzajIdOld = persistentDcDokument.getRodzajId();
            DcRodzaj rodzajIdNew = dcDokument.getRodzajId();
            DcProjekt projektIdOld = persistentDcDokument.getProjektId();
            DcProjekt projektIdNew = dcDokument.getProjektId();
            List<DcPlik> dcPlikListOld = persistentDcDokument.getDcPlikList();
            List<DcPlik> dcPlikListNew = dcDokument.getDcPlikList();
            List<String> illegalOrphanMessages = null;
            for (DcPlik dcPlikListOldDcPlik : dcPlikListOld) {
                if (!dcPlikListNew.contains(dcPlikListOldDcPlik)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DcPlik " + dcPlikListOldDcPlik + " since its idDok field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                dcDokument.setUserId(userIdNew);
            }
            if (zrodloIdNew != null) {
                zrodloIdNew = em.getReference(zrodloIdNew.getClass(), zrodloIdNew.getId());
                dcDokument.setZrodloId(zrodloIdNew);
            }
            if (rodzajIdNew != null) {
                rodzajIdNew = em.getReference(rodzajIdNew.getClass(), rodzajIdNew.getId());
                dcDokument.setRodzajId(rodzajIdNew);
            }
            if (projektIdNew != null) {
                projektIdNew = em.getReference(projektIdNew.getClass(), projektIdNew.getId());
                dcDokument.setProjektId(projektIdNew);
            }
            List<DcPlik> attachedDcPlikListNew = new ArrayList<DcPlik>();
            for (DcPlik dcPlikListNewDcPlikToAttach : dcPlikListNew) {
                dcPlikListNewDcPlikToAttach = em.getReference(dcPlikListNewDcPlikToAttach.getClass(), dcPlikListNewDcPlikToAttach.getId());
                attachedDcPlikListNew.add(dcPlikListNewDcPlikToAttach);
            }
            dcPlikListNew = attachedDcPlikListNew;
            dcDokument.setDcPlikList(dcPlikListNew);

            dcDokument = this.createKroki(dcDokument);

            dcDokument = em.merge(dcDokument);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getDcDokumentList().remove(dcDokument);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getDcDokumentList().add(dcDokument);
                userIdNew = em.merge(userIdNew);
            }
            if (zrodloIdOld != null && !zrodloIdOld.equals(zrodloIdNew)) {
                zrodloIdOld.getDcDokumentList().remove(dcDokument);
                zrodloIdOld = em.merge(zrodloIdOld);
            }
            if (zrodloIdNew != null && !zrodloIdNew.equals(zrodloIdOld)) {
                zrodloIdNew.getDcDokumentList().add(dcDokument);
                zrodloIdNew = em.merge(zrodloIdNew);
            }
            if (rodzajIdOld != null && !rodzajIdOld.equals(rodzajIdNew)) {
                rodzajIdOld.getDcDokumentList().remove(dcDokument);
                rodzajIdOld = em.merge(rodzajIdOld);
            }
            if (rodzajIdNew != null && !rodzajIdNew.equals(rodzajIdOld)) {
                rodzajIdNew.getDcDokumentList().add(dcDokument);
                rodzajIdNew = em.merge(rodzajIdNew);
            }
            if (projektIdOld != null && !projektIdOld.equals(projektIdNew)) {
                projektIdOld.getDcDokumentList().remove(dcDokument);
                projektIdOld = em.merge(projektIdOld);
            }
            if (projektIdNew != null && !projektIdNew.equals(projektIdOld)) {
                projektIdNew.getDcDokumentList().add(dcDokument);
                projektIdNew = em.merge(projektIdNew);
            }
            for (DcPlik dcPlikListNewDcPlik : dcPlikListNew) {
                if (!dcPlikListOld.contains(dcPlikListNewDcPlik)) {
                    DcDokument oldIdDokOfDcPlikListNewDcPlik = dcPlikListNewDcPlik.getIdDok();
                    dcPlikListNewDcPlik.setIdDok(dcDokument);
                    dcPlikListNewDcPlik = em.merge(dcPlikListNewDcPlik);
                    if (oldIdDokOfDcPlikListNewDcPlik != null && !oldIdDokOfDcPlikListNewDcPlik.equals(dcDokument)) {
                        oldIdDokOfDcPlikListNewDcPlik.getDcPlikList().remove(dcPlikListNewDcPlik);
                        oldIdDokOfDcPlikListNewDcPlik = em.merge(oldIdDokOfDcPlikListNewDcPlik);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dcDokument.getId();
                if (findDcDokument(id) == null) {
                    throw new NonexistentEntityException("The dcDokument with id " + id + " no longer exists.");
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
            DcDokument dcDokument;
            try {
                dcDokument = em.getReference(DcDokument.class, id);
                dcDokument.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dcDokument with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DcPlik> dcPlikListOrphanCheck = dcDokument.getDcPlikList();
            for (DcPlik dcPlikListOrphanCheckDcPlik : dcPlikListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DcDokument (" + dcDokument + ") cannot be destroyed since the DcPlik " + dcPlikListOrphanCheckDcPlik + " in its dcPlikList field has a non-nullable idDok field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Uzytkownik userId = dcDokument.getUserId();
            if (userId != null) {
                userId.getDcDokumentList().remove(dcDokument);
                userId = em.merge(userId);
            }
            DcZrodlo zrodloId = dcDokument.getZrodloId();
            if (zrodloId != null) {
                zrodloId.getDcDokumentList().remove(dcDokument);
                zrodloId = em.merge(zrodloId);
            }
            DcRodzaj rodzajId = dcDokument.getRodzajId();
            if (rodzajId != null) {
                rodzajId.getDcDokumentList().remove(dcDokument);
                rodzajId = em.merge(rodzajId);
            }
            DcProjekt projektId = dcDokument.getProjektId();
            if (projektId != null) {
                projektId.getDcDokumentList().remove(dcDokument);
                projektId = em.merge(projektId);
            }
            em.remove(dcDokument);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DcDokument> findDcDokumentEntities() {
        return findDcDokumentEntities(true, -1, -1);
    }

    public List<DcDokument> findDcDokumentEntities(int maxResults, int firstResult) {
        return findDcDokumentEntities(false, maxResults, firstResult);
    }

    private List<DcDokument> findDcDokumentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DcDokument.class));
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

    public DcDokument findDcDokument(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DcDokument.class, id);
        } finally {
            em.close();
        }
    }

    public int getDcDokumentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DcDokument> rt = cq.from(DcDokument.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
