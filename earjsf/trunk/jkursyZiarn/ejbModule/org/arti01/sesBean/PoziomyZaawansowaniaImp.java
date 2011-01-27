package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Poziomyzaawansowania;

@Stateless
@LocalBean
public class PoziomyZaawansowaniaImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Poziomyzaawansowania> getAll() {
		return em.createQuery("select n from Poziomyzaawansowania n order by n.nazwa asc").getResultList();
	}

	public Poziomyzaawansowania find(Poziomyzaawansowania pz){
		pz=em.find(Poziomyzaawansowania.class, pz.getIdpoziomyzaawansowania());
		return pz;
	}
	public void update(Poziomyzaawansowania pz){
		em.merge(pz);
	}
	
	public void insert(Poziomyzaawansowania pz) {
		em.persist(pz);
	}
	
	public void delete(Poziomyzaawansowania pz) {
		pz=em.find(Poziomyzaawansowania.class, pz.getIdpoziomyzaawansowania());
		em.remove(pz);
		em.flush();
	}
	
	
}
