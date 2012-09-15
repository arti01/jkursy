package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Typykursu;

@Stateless
@LocalBean
public class TypyKursuImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Typykursu> getAll() {
		return em.createQuery("select n from Typykursu n order by n.nazwa asc").getResultList();
	}

	public Typykursu find(Typykursu pz){
		pz=em.find(Typykursu.class, pz.getIdtypykursu());
		return pz;
	}
	public void update(Typykursu pz){
		em.merge(pz);
	}
	
	public void insert(Typykursu pz) {
		em.persist(pz);
	}
	
	public void delete(Typykursu pz) throws Exception {
		pz=em.find(Typykursu.class, pz.getIdtypykursu());
		em.remove(pz);
		em.flush();
	}
	
	
}
