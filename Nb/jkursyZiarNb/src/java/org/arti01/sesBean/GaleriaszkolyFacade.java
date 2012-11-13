/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.sesBean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Galeriaszkoly;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Lekcjafotykursant;

/**
 *
 * @author arti01
 */
@Stateless
public class GaleriaszkolyFacade extends AbstractFacade<Galeriaszkoly> {
    @PersistenceContext(unitName = "jkursyJPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List <Lekcjafotykursant> wszystkieFoty(){
        em.flush();
        Query q=em.createNamedQuery("Galeriaszkoly.findAllgaleriaKoncowa");
        @SuppressWarnings("unchecked")
        List <Lekcjafotykursant> wynik=q.getResultList();
        return wynik;
    }
    
    public List <Lekcjafotykursant> wszystkieGaleriaSzkoly(){
        em.flush();
        Query q=em.createNamedQuery("Galeriaszkoly.findAllgaleriaSzkoly");
        @SuppressWarnings("unchecked")
        List <Lekcjafotykursant> wynik=q.getResultList();
        return wynik;
    }
    
      public List <Lekcjafotykursant> wszystkieBezGalerii(){
        em.flush();
        Query q=em.createNamedQuery("Galeriaszkoly.findAllbezGaleriiSzkoly");
        @SuppressWarnings("unchecked")
        List <Lekcjafotykursant> wynik=q.getResultList();
        return wynik;
    }
      
      public void usunZGalerii(Lekcjafotykursant lfk){
          remove(lfk.getGaleriaszkoly());
          lfk.setGaleriaszkoly(null);
          em.merge(lfk);
      }
      
      public void dodajDoGalerii(Lekcjafotykursant lfk){
          Galeriaszkoly gs=new Galeriaszkoly();
          gs.setLekcjafotykursant(lfk);
          create(gs);
          lfk.setGaleriaszkoly(gs);
          em.merge(lfk);
      }

    public GaleriaszkolyFacade() {
        super(Galeriaszkoly.class);
    }
    
}
