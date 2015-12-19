/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import abstr.AbstKontroler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author 103039
 */
@Stateless
public class PlikimapFacade extends AbstKontroler<Plikimap> {

    static final Logger LOGGER = Logger.getAnonymousLogger();

    public PlikimapFacade() {
        super(new Plikimap());
    }

    @Override
    public Map<String, String> create(Plikimap obiekt) throws Exception {
        Map<String, String> bledy = new HashMap<>();
        if (findEntities(obiekt.getNazwa()) != null) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if (findEntitiesSrodowisko(obiekt.getSrodowisko()) != null) {
            bledy.put("srodowiskoD", "srodowisko już istnieje");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }
        em.persist(obiekt);
        return bledy;
    }

    @Override
    public Map<String, String> edit(Plikimap obiekt) throws Exception {
        Plikimap oldObiekt = findObiekt(obiekt);
        Map<String, String> bledy = new HashMap<>();
        if ((findEntities(obiekt.getNazwa()) != null) && (!obiekt.getNazwa().equals(oldObiekt.getNazwa()))) {
            bledy.put("nazwaD", "nazwa już istnieje");
        }
        if (findEntitiesSrodowisko(obiekt.getSrodowisko()) != null && (!obiekt.getSrodowisko().equals(oldObiekt.getSrodowisko()))) {
            bledy.put("srodowiskoD", "środowisko już istnieje");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }

        //czyszczenie starych danych (bo inaczej klucz unikalny się rozjezdza)
        for (PlikimapDane pmd : oldObiekt.getPlikimapDaneList()) {
            if(!obiekt.getPlikimapDaneList().contains(pmd)){
                em.remove(pmd);
            }
        }
        em.flush();
        em.merge(obiekt);
        return bledy;
    }

    public Plikimap findEntitiesSrodowisko(String srodowisko) {
        try {
            Query q = em.createNamedQuery(Plikimap.class.getSimpleName() + ".findBySrodowisko");
            q.setParameter("srodowisko", srodowisko);
            @SuppressWarnings("unchecked")
            Plikimap u = (Plikimap) q.getResultList().get(0);
            return u;
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        }
    }
}
