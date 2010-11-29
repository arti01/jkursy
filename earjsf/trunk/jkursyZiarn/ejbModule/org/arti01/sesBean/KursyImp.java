package org.arti01.sesBean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

//import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;

@Stateless
@LocalBean
public class KursyImp implements KursyImpLocal, KursyImpRemote {
	//Logger logger = Logger.getLogger(KursyImp.class);
	@PersistenceContext
	EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		return em.createQuery("select k from Kursy k order by k.dataod desc").getResultList();
	}
	
	public String Test(){
		return "test";
	}
	
	/* (non-Javadoc)
	 * @see org.arti01.obiekty.KursyImpLocal#findNiezakonczone()
	 */
	@Override
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
	/*
	public void insert(Kursy kurs) {
		new Baza().dodaj(kurs);
	}
	
	public void remove(Kursy kurs) throws Exception{
		new Baza().usun(kurs);
	}
	
	public void update(Kursy kurs) throws Exception {
		logger.info(kurs.getNazwa());
	new Baza().update(kurs);
}
	*/
}
