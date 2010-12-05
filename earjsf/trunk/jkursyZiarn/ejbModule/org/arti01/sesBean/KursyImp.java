package org.arti01.sesBean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Kursy;

@Stateless
@LocalBean
public class KursyImp {
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		return em.createQuery("select k from Kursy k order by k.dataod desc").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Kursy> findNiezakonczone() {
		Query query=em.createNamedQuery("findNiezakonczone");
		query.setParameter("datado", new Date());
		return  query.getResultList();
	}
	public Kursy find(Kursy kurs) {
		if (kurs != null) {
			em.find(Kursy.class, kurs.getIdkursy());
		} else kurs = new Kursy();
		return kurs;
	}
	public void update(Kursy kursy){
		em.merge(kursy);
	}
	
	public void insert(Kursy kursy) {
		em.persist(kursy);
	}
	
}
