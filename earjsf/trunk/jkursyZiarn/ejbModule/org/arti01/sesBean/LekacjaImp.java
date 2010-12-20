package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.arti01.entit.Lekcja;
import org.arti01.entit.Statyczne;

@Stateless
@LocalBean
public class LekacjaImp {

	@PersistenceContext
	EntityManager em;
	Lekcja lekcja;
	List<Integer> lpAll;

	@SuppressWarnings("unchecked")
	public List<Statyczne> getFindAll() {
		return em.createQuery("select s from Statyczne s order by s.lp").getResultList();
	}

	public Lekcja find(Lekcja lekcja){
		lekcja=em.find(Lekcja.class, lekcja.getIdlekcja());
		return lekcja;
	}
	
	public void update(Lekcja lekcja){
		em.merge(lekcja);
	}
	
	public void insert(Lekcja lekcja) {
		em.persist(lekcja);
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

	
	@SuppressWarnings("unchecked")
	public List<Integer> getLpAll() {
		lpAll= em.createQuery("select l.lp from Lekcja l order by l.lp").getResultList();
		return lpAll;
	}

	public void setLpAll(List<Integer> lpAll) {
		this.lpAll = lpAll;
	}

	/*@SuppressWarnings("unchecked")
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
	}*/
	
}
