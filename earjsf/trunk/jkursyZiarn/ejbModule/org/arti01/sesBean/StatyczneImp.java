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
	
	public void delete(Statyczne statyczne) {
		Integer lp=statyczne.getLp();
		statyczne=em.find(statyczne.getClass(), statyczne.getIdStatyczne());
		em.remove(statyczne);
		em.flush();
		Query query=em.createQuery("update Statyczne s set s.lp=s.lp-1 where s.lp>:lp");
		query.setParameter("lp", lp);
		query.executeUpdate();
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
	public void update(Statyczne statyczne, Integer newLp) {
		Integer oldLp=new Integer(statyczne.getLp());
		statyczne.setLp(-1);
		em.merge(statyczne);
		//System.out.println(statyczne+"statOld");
		//System.out.println(oldLp+"new"+newLp);
		if (newLp < oldLp) {//idziemy w góre
			//System.out.println("upupup");
			Query select=em.createQuery("select s from Statyczne s where s.lp<:oldLp and s.lp>=:newLp order by s.lp desc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			List<Statyczne> sl=select.getResultList();
			for (Statyczne s:sl){
				//System.out.println(statyczne.getLp());
				//System.out.println(s.getTytul());
				//System.out.println(s.getLp()+1);
				s.setLp(s.getLp()+1);
				em.merge(s);
				em.flush();
			}
			statyczne.setLp(newLp);
			em.merge(statyczne);
		} else if (newLp > oldLp) {//idziemy w dół
			Query select=em.createQuery("select s from Statyczne s where s.lp>:oldLp and s.lp<=:newLp order by s.lp asc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			List<Statyczne> sl=select.getResultList();
			for (Statyczne s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
				em.flush();
			}
			statyczne.setLp(newLp);
			em.merge(statyczne);
		}else update(statyczne);
	}
	
}
