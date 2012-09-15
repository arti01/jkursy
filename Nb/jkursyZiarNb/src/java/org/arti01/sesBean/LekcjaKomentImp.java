package org.arti01.sesBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Lekcjakoment;

@Stateless
@LocalBean
public class LekcjaKomentImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	
	public boolean insert(Lekcjakoment lk){
			em.persist(lk);
			return false;
	}

	public boolean delete(Lekcjakoment lk){
		em.remove(em.merge(lk));
		em.flush();
		em.clear();
		return false;
	}
	
	}
