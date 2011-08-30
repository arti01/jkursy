package org.arti.jpa;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class MarkaImp {
	// Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;

	List<Marka> all;
	Marka marka;

	@SuppressWarnings("unchecked")
	public List<Marka> getAll() {
		all=em.createQuery("select m from Marka m order by m.marka").getResultList();
		return all;
	}

	public void setAll(List<Marka> all) {
		this.all = all;
	}
	
	public Marka find(Marka marka){
		return em.find(Marka.class, marka.getIdmarka());
	}

	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}
}
