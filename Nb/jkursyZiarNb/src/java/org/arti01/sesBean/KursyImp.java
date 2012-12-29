package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Kursy;
import org.arti01.entit.KursyRezerwacje;
import org.arti01.entit.Rachunki;
import org.arti01.entit.User;

@Stateless
@LocalBean
public class KursyImp {

    @PersistenceContext
    EntityManager em;
    String errorText = "";
    List<Integer> lekcjeLpAll;
    @EJB
    RoleImp roleImp;

    @SuppressWarnings("unchecked")
    public List<Kursy> findAll() {
        //em.clear();
        List<Kursy> wynik = em.createQuery("select k from Kursy k order by k.lp").getResultList();
        List<Kursy> ret = new ArrayList<Kursy>();
        for (Kursy k : wynik) {
            k = find(k);
            ret.add(k);
        }
        return ret;
    }
    
    public List<Kursy> findAllWidoczne() {
        //em.clear();
        @SuppressWarnings("unchecked")
        List<Kursy> wynik = em.createQuery("select k from Kursy k WHERE k.widoczny=true order by k.lp").getResultList();
        List<Kursy> ret = new ArrayList<Kursy>();
        for (Kursy k : wynik) {
            k = find(k);
            ret.add(k);
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<Kursy> findNiezakonczone() {
        Query query = em.createNamedQuery("findNiezakonczone");
        query.setParameter("datado", new Date());
        return query.getResultList();
    }

    public Kursy find(Kursy kurs) {
        if (kurs != null) {
            kurs = em.find(Kursy.class, kurs.getIdkursy());
            em.refresh(kurs);
            kurs.setLekcjeLpAll(getLpAll(kurs));
        } else {
            kurs = new Kursy();
        }
        return kurs;
    }

    public boolean update(Kursy kursy) {
        if (validUpdate(kursy)) {
            em.merge(kursy);
            return true;
        } else {
            System.out.println("NIEWALID ;)");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void update(Kursy kurs, Integer newLp) {
        Integer oldLp = new Integer(kurs.getLp());
        kurs.setLp(-1);
        em.merge(kurs);
        //System.out.println(lekcja+"statOld");
        //System.out.println(oldLp+"new"+newLp);
        if (newLp < oldLp) {//idziemy w g��re
            //System.out.println("upupup");
            Query select = em.createQuery("select k from Kursy k where k.lp<:oldLp and k.lp>=:newLp order by k.lp desc");
            select.setParameter("oldLp", oldLp);
            select.setParameter("newLp", newLp);
            List<Kursy> sl = select.getResultList();
            for (Kursy s : sl) {
                //System.out.println(lekcja.getLp());
                //System.out.println(s.getTytul());
                //System.out.println(s.getLp()+1);
                s.setLp(s.getLp() + 1);
                em.merge(s);
                em.flush();
            }
            kurs.setLp(newLp);
            em.merge(kurs);
        } else if (newLp > oldLp) {//idziemy w d����
            Query select = em.createQuery("select k from Kursy k where k.lp>:oldLp and k.lp<=:newLp order by k.lp asc");
            select.setParameter("oldLp", oldLp);
            select.setParameter("newLp", newLp);
            List<Kursy> sl = select.getResultList();
            for (Kursy s : sl) {
                s.setLp(s.getLp() - 1);
                em.merge(s);
                em.flush();
            }
            kurs.setLp(newLp);
            em.merge(kurs);
        } else {
            update(kurs);
        }
    }

    public boolean insert(Kursy kursy) {
        if (validInsert(kursy)) {
            kursy.setLp(findAll().size() + 1);
            em.persist(kursy);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void delete(Kursy kurs) {
        Integer lp = kurs.getLp();
        em.remove(em.merge(kurs));
        em.flush();

        Query qt = em.createQuery("select k from Kursy k where k.lp>:lp order by k.lp");
        qt.setParameter("lp", lp);
        for (Kursy k : (List<Kursy>) qt.getResultList()) {
            k.setLp(k.getLp() - 1);
            em.merge(k);
        }
    }

    public boolean validUpdate(Kursy kurs) {
        if (kurs.getDataod() == null || kurs.getDatado() == null) {
            return true;
        }
        if (kurs.getDataod().after(kurs.getDatado())) {
            errorText = "Daty bez sensu ('data do' < 'data od')";
            return false;
        } else {
            return true;
        }
    }

    public boolean validInsert(Kursy kurs) {
        if (kurs.getDataod() == null || kurs.getDatado() == null) {
            return true;
        }
        if (kurs.getDataod().after(kurs.getDatado()) || kurs.getDatado().before(new Date())) {
            errorText = "Daty bez sensu ('data do' < 'data od' lub 'data do' < bieżcej)";
            return false;
        } else {
            return true;
        }
    }

    public void rezerwuj(KursyRezerwacje kr) {
        if (kr.getDatarezerwacji() == null) {
            kr.setDatarezerwacji(new Date());
        }
        Rachunki r = kr.getRachunki();
        kr.setRachunki(null);
        em.persist(kr);
        em.flush();
        em.refresh(kr);
        if (r != null) {
            r.setKursyRezerwacje(kr);
            kr.setRachunki(r);
            em.merge(kr);
        }
    }

    public void updateRezerwacje(KursyRezerwacje kr) {
        em.merge(kr);
    }

    public KursyRezerwacje findRezerwacje(KursyRezerwacje kr) {
        return em.find(KursyRezerwacje.class, kr.getIdkursyrezerwacje());
    }

    @SuppressWarnings("unchecked")
    public List<User> czarnaLista() {
        return em.createQuery("select distinct u from KursyRezerwacje kr join kr.user u where kr.aktywna=false").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getLpAll(Kursy kursy) {
        Query query = em.createQuery("select l.lp from Lekcja l where l.kursy=:kursy order by l.lp");
        query.setParameter("kursy", kursy);
        lekcjeLpAll = query.getResultList();
        return lekcjeLpAll;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public List<Integer> getLekcjeLpAll() {
        return lekcjeLpAll;
    }

    public void setLekcjeLpAll(List<Integer> lekcjeLpAll) {
        this.lekcjeLpAll = lekcjeLpAll;
    }
}
