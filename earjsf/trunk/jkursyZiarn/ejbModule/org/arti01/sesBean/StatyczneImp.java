package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Statyczne;

@Stateless
@LocalBean
public class StatyczneImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	Statyczne strona;
	
	/* (non-Javadoc)
	 * @see org.arti01.obiekty.StatyczneImpLocal#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Statyczne> getFindAll() {
		return em.createQuery("select s from Statyczne s order by s.lp").getResultList();
	}

	/* (non-Javadoc)
	 * @see org.arti01.obiekty.StatyczneImpLocal#insert(org.arti01.obiekty.Statyczne)
	 */
	public Statyczne find(Statyczne statyczne){
		strona=em.find(Statyczne.class, statyczne.getIdStatyczne());
		return strona;
	}
	public void update(Statyczne statyczne){
		em.merge(statyczne);
	}
	
	public void insert(Statyczne statyczne) {
		em.persist(statyczne);
	}

	public Statyczne getStrona() {
		return strona;
	}

	public void setStrona(Statyczne strona) {
		this.strona = strona;
	}

	/*@SuppressWarnings("unchecked")
	public void update(Statyczne statyczne, Integer oldLp) throws Exception {
		logger.info(statyczne.getTytul()+"-------------"+statyczne.getLp());
		if (oldLp > statyczne.getLp()) {//idziemy w góre
			em.remove(statyczne);
			Query select= em.createQuery("from Statyczne where lp<:oldLp and lp>=:newLp order by lp desc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=select.getResultList();
			for (Statyczne s:sl){
				Query query = baza
				.przygotuj("update Statyczne s set lp=lp+1 where s=:s");
				query.setParameter("s", s);
				//query.executeUpdate();
				baza.update(query);
			}
			//logger.info(query.getQueryString()+"--------------------------------------++++++++++");
			insert(statyczne);
		} else if (oldLp < statyczne.getLp()) {//idziemy w dół
			baza.usun(statyczne);
			Query select= baza
			.przygotuj("from Statyczne where lp>:oldLp and lp<=:newLp order by lp asc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=new Baza().select(select);
			for (Statyczne s:sl){
				Query query = baza
				.przygotuj("update Statyczne s set lp=lp-1 where s=:s");
				query.setParameter("s", s);
				logger.info(s.getLp()+"--------------------------------------++++++++++");
				//query.executeUpdate();
				baza.update(query);
			}
			insert(statyczne);
		}else baza.update(statyczne);
	}

	@SuppressWarnings("unchecked")
	public void remove(Statyczne statyczne) throws Exception {
		Baza baza = new Baza();
		statyczne=this.find(statyczne);
		baza.usun(statyczne);
		Query select= baza
		.przygotuj("from Statyczne where lp>:lp order by lp asc");
		select.setParameter("lp", statyczne.getLp());
		List<Statyczne> sl=new Baza().select(select);
		for (Statyczne s:sl){
			Query query = baza
			.przygotuj("update Statyczne s set lp=lp-1 where s=:s");
			query.setParameter("s", s);
			baza.update(query);
		}
		logger.info(statyczne.getLp()+"rrrrrrrrrrrrrrr");
	}

	public Statyczne find(Statyczne statyczne) {
		Statyczne statyczneNew;
		if (statyczne != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Statyczne s where s=:statyczne");
			query.setParameter("statyczne", statyczne);
			statyczneNew = (Statyczne) baza.select(query).iterator().next();
		} else
			statyczneNew = new Statyczne();
		return statyczneNew;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> lpAll() {
		return (List<Integer>) new Baza()
				.getAll("select lp from Statyczne order by lp");
	}*/
}
