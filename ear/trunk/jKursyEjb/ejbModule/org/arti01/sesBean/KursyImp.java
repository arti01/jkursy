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

import org.apache.log4j.Logger;

@Stateless
@LocalBean
public class KursyImp implements KursyImpLocal {
	Logger logger = Logger.getLogger(KursyImp.class);
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		return new Baza().getAll("from Kursy order by dataod desc");
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
	
	public Kursy load(Kursy kurs) {
		Kursy kursNew;
		if (kurs != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Kursy where idkursy=:idkursy");
			//logger.info(user.getUsername());
			query.setParameter("idkursy", kurs.getIdkursy());
			kursNew = (Kursy) baza.select(query).iterator().next();
		} else kursNew = new Kursy();
		return kursNew;
	}
	
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
	
}
