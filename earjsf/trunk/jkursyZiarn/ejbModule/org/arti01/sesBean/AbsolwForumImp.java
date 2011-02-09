package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arti01.entit.Absolwforposty;
import org.arti01.entit.Absolwforwatki;
import org.arti01.entit.Boksy;
import org.arti01.entit.Kursy;

@Stateless
@LocalBean
public class AbsolwForumImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Kursy> findAllWatki() {
		//em.clear();
		List<Kursy> wynik=em.createQuery("select w from Absolwforwatki w order by w.datadodania desc").getResultList();
		return wynik;
	}
	
	public Absolwforwatki findWatek(Absolwforwatki pz){
		pz=em.find(Absolwforwatki.class, pz.getIdabsolwforwatki());
		return pz;
	}
	
	public Absolwforposty findPost(Absolwforposty pz){
		pz=em.find(Absolwforposty.class, pz.getIdabsolwforposty());
		return pz;
	}
	
	public void updatePost(Absolwforposty pz){
		em.merge(pz);
	}
	
	public void updateWatek(Absolwforwatki pz){
		em.merge(pz);
	}
	
	public void insertWatek(Absolwforwatki pz) {
		em.persist(pz);
	}
	
	public void insertPost(Absolwforposty pz) {
		em.persist(pz);
	}
	
	public void delete(Boksy pz) throws Exception {
		pz=em.find(Boksy.class, pz.getIdboksy());
		em.remove(pz);
		em.flush();
	}
	
	
}
