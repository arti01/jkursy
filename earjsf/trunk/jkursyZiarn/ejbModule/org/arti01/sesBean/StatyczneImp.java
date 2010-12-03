package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.arti01.entit.Statyczne;

@Stateless
@LocalBean
public class StatyczneImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	Statyczne strona;
	List<Integer> lpAll;
	
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

	@SuppressWarnings("unchecked")
	public List<Integer> getLpAll() {
		lpAll= em.createQuery("select s.lp from Statyczne s order by s.lp").getResultList();
		return lpAll;
	}

	public void setLpAll(List<Integer> lpAll) {
		this.lpAll = lpAll;
	}

	@SuppressWarnings("unchecked")
	public void update(Statyczne statyczne, Integer oldLp) {
		System.out.println(statyczne.getLp()+"old"+oldLp);
		em.remove(statyczne);
		em.flush();
		if (oldLp > statyczne.getLp()) {//idziemy w góre
			Query select=em.createQuery("select s from Statyczne s where s.lp<:oldLp and s.lp>=:newLp order by s.lp desc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=select.getResultList();
			for (Statyczne s:sl){
				System.out.println(statyczne.getLp());
				System.out.println(s.getTytul());
				System.out.println(s.getLp()+1);
				s.setLp(s.getLp()+1);
				em.merge(s);
			}
			em.persist(statyczne);
		} else if (oldLp < statyczne.getLp()) {//idziemy w dół
			Query select=em.createQuery("select s from Statyczne s where s.lp>:oldLp and s.lp<=:newLp order by s.lp asc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=select.getResultList();
			for (Statyczne s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
			}
			em.merge(statyczne);
		}else update(statyczne);
	}
	/*

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


	@SuppressWarnings("unchecked")
	public List<Integer> lpAll() {
		return (List<Integer>) new Baza()
				.getAll("select lp from Statyczne order by lp");
	}*/
}
