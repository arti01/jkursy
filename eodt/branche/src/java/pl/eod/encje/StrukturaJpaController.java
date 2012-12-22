/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.eod.encje.exceptions.NonexistentEntityException;
import pl.eod.encje.exceptions.mojWyjatek;
import pl.eod.managed.Login;

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
            List<Struktura> wynik = query.getResultList();
            return wynik;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    private static final Logger LOG = Logger.getLogger(StrukturaJpaController.class.getName());

    @SuppressWarnings("empty-statement")
    public String create(Struktura struktura) throws Exception {
        if (struktura.getUserId().getExtId() != null) {
            if (struktura.getUserId().getExtId() == 0) {
                struktura.getUserId().setExtId(null);
            }
        }
        ConfigJpaController confC = new ConfigJpaController();
        String defPass = confC.findConfigNazwa("domysle_haslo").getWartosc();
        Hasla h = new Hasla();
        //System.out.println("--"+defPass+"====");
        if (confC.findConfigNazwa("realm_szyfrowanie").getWartosc().equals("md5")) {
            h.setPass(Login.md5(defPass));
        } else {
            h.setPass(defPass);
        }
        struktura.getUserId().setHasla(h);

        UserRolesJpaController urC = new UserRolesJpaController();
        List<UserRoles> url = new ArrayList<UserRoles>();
        url.add(urC.findByNazwa("eoduser"));
        struktura.getUserId().setRole(url);;

        EntityManager em = null;
        try {
            if (!struktura.isStKier()) {
                if (struktura.getSzefId() == null) {
                    return "pracownik musi mieć przełożonego";
                }
                struktura.setDzialId(struktura.getSzefId().getDzialId());
            }
            UzytkownikJpaController uC = new UzytkownikJpaController();
            if (uC.findStruktura(struktura.getUserId().getAdrEmail()) != null) {
                //System.err.println("valid tutaj 1");
                return "email już istnieje";
            }

            DzialJpaController dC = new DzialJpaController();
            if (dC.findDzialByNazwa(struktura.getDzialId().getNazwa()) != null && struktura.isStKier()) {
                //System.err.println("valid tutaj 1");
                return "dział już istnieje";
            }

            em = getEntityManager();
            //System.out.println(struktura.getDzialId());
            em.getTransaction().begin();
            em.merge(struktura);

            em.getTransaction().commit();
            if (struktura.getSzefId() != null) {
                em.refresh(em.find(struktura.getClass(), struktura.getSzefId().getId()));
            }
        } catch (Exception ex) {
            throw new mojWyjatek(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void zrobNiewidczony(Struktura struktura) {
        EntityManager em = null;
        em = getEntityManager();
Dzial dOld=em.find(Dzial.class, struktura.getDzialId().getId());
Struktura sOld=em.find(Struktura.class, struktura.getSzefId().getId());
        struktura.setUsuniety(1);
        if (struktura.isStKier()) {
            Dzial dzial = struktura.getDzialId();
            struktura.setDzialId(null);
            struktura.setSzefId(null);
            em.getTransaction().begin();
            em.merge(struktura);
            em.remove(em.merge(dzial));
            em.getTransaction().commit();
        } else {
            em = getEntityManager();
            em.getTransaction().begin();
            struktura.setSzefId(null);
            em.merge(struktura);
            em.getTransaction().commit();
        }
        em.refresh(em.merge(sOld));
        em.refresh(em.merge(dOld));
    }

    public String changeKier(Struktura struktura, Dzial dzialOld) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        if (!struktura.isStKier()) {
            if (struktura.getSzefId() != null) {
                struktura.setDzialId(struktura.getSzefId().getDzialId());
            } else {
                return "Brak przełożonego - ustaw i zapisz";
            }
        } else {
            Dzial d = new Dzial();
            d.setNazwa("Nowy dział");
            struktura.setDzialId(d);
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(struktura);
            em.getTransaction().commit();
            if (!struktura.isStKier()) {
                DzialJpaController dzialC = new DzialJpaController();
                dzialC.destroy(dzialOld);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public String editArti(Struktura struktura) throws NonexistentEntityException, Exception {
        if (struktura.getUserId().getExtId() != null) {
            if (struktura.getUserId().getExtId() == 0) {
                struktura.getUserId().setExtId(null);
            }
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Struktura oldStruktura = em.find(struktura.getClass(), struktura.getId());
            Long idOldSzef = null;
            if (oldStruktura.getSzefId() != null) {
                idOldSzef = oldStruktura.getSzefId().getId();
            }
            if (!struktura.isStKier()) {
                if (struktura.getSzefId() != null) {
                    struktura.setDzialId(struktura.getSzefId().getDzialId());
                } else {
                    return "Brak przełożonego - ustaw i zapisz";
                }
            }
            if (!struktura.getUserId().getAdrEmail().equals(oldStruktura.getUserId().getAdrEmail())) {
                UzytkownikJpaController uC = new UzytkownikJpaController();
                if (uC.findStruktura(struktura.getUserId().getAdrEmail()) != null) {
                    return "email już istnieje";
                }
            }

            if ((!struktura.getDzialId().getNazwa().equals(oldStruktura.getDzialId().getNazwa()))&&struktura.isStKier() == true) {
                DzialJpaController dC = new DzialJpaController();
                if (dC.findDzialByNazwa(struktura.getDzialId().getNazwa()) != null) {
                    //System.err.println("blad 1");
                    return "dział już istnieje";
                }
            }

            if (struktura.isStKier() == true && oldStruktura.isStKier() == false) {
                DzialJpaController dC = new DzialJpaController();
                if (dC.findDzialByNazwa(struktura.getDzialId().getNazwa()) != null) {
                    return "dział już istnieje";
                }
            }
            em.getTransaction().begin();
            em.merge(struktura);
            em.getTransaction().commit();

            if (struktura.getSzefId() != null) {
                em.refresh(em.find(struktura.getClass(), struktura.getSzefId().getId()));
            }
            if (idOldSzef != null) {
                em.refresh(em.find(struktura.getClass(), idOldSzef));
            }
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void destroyArti(Struktura struktura) throws NonexistentEntityException {
        EntityManager em = null;
        if (struktura.bezpPod.size() > 0) {
            return;
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (struktura.isStKier()) {
                em.remove(em.merge(struktura));
            } else {
                struktura.setDzialId(null);
                em.remove(em.merge(struktura));
            }
            if (struktura.getSzefId() != null) {
            }
            System.out.println(struktura.getId());
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Struktura> getFindBezSzefa() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Struktura.findBezSzefa");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Struktura> findStrukturaWidoczni() {
        List<Struktura> wynik = new ArrayList<Struktura>();
        for (Struktura s : findStrukturaEntities(true, -1, -1)) {
            if (!s.isUsuniety()) {
                wynik.add(s);
            }
        }
        return wynik;
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
