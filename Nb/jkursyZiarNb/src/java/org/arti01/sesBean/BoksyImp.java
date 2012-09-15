package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Boksy;

@Stateless
@LocalBean
public class BoksyImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Boksy> getAll() {
		return em.createQuery("select n from Boksy n").getResultList();
	}

	public Boksy find(Boksy pz){
		pz=em.find(Boksy.class, pz.getIdboksy());
		return pz;
	}
	public void update(Boksy pz){
		System.out.println(pz.getIdboksy());
		em.merge(pz);
		System.out.println(pz.getNaglowek());
		pz=em.find(Boksy.class, pz.getIdboksy());
		System.out.println(pz.getNaglowek());
	}
	
	public void insert(Boksy pz) {
		em.persist(pz);
		//em.refresh(pz.getBoksycfg());
	}
	
	public void delete(Boksy pz) throws Exception {
		pz=em.find(Boksy.class, pz.getIdboksy());
		em.remove(pz);
		//em.refresh(pz.getBoksycfg());
	}
	
	
}
