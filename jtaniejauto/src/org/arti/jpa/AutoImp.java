package org.arti.jpa;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AutoImp {
	// Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;

	List<Auto> all;

	@SuppressWarnings("unchecked")
	public List<Auto> getAll() {
		all=em.createQuery("select a from Auto a order by a.idauto desc").getResultList();
		return all;
	}

	public void insert(Auto auto){
		em.persist(auto);
	}
	
	public void update(Auto auto){
		em.merge(auto);
	}
	
	public void setAll(List<Auto> all) {
		this.all = all;
	}
}
