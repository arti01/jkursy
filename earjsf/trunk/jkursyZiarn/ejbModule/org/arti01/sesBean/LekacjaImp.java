package org.arti01.sesBean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;

@Stateless
@LocalBean
public class LekacjaImp implements Serializable{
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager em;
	Lekcja lekcja;
	
	@SuppressWarnings("unchecked")
	public List<Lekcja> getFindAll() {
		return em.createQuery("select l from Lekcja l order by l.lp").getResultList();
	}

	public Lekcja find(Lekcja lekcja){
		lekcja=em.find(Lekcja.class, lekcja.getIdlekcja());
		em.refresh(lekcja);
		lekcja.setFotyLpAll(getLpAll(lekcja));
		//lekcja.setLastKomentFota(findlastKomentFota(lekcja));
		return lekcja;
	}
	
	public void update(Lekcja lekcja){
		em.merge(lekcja);
	}
	
	/*public Fotykursantkoment findlastKomentFota(Lekcja lekcja){
		Query query=em.createQuery("select from Lekcja l join l.lekcjafotykursant lfk join lfk.fotykursantkoment fkk where l=:lekcja order by fkk.datadodania DESC");
		query.setParameter("lekcja", lekcja);
		Fotykursantkoment fkk;
		if(query.setMaxResults(1).getResultList().size()==1) fkk=(Fotykursantkoment)query.setMaxResults(1).getResultList().iterator().next();
		else fkk=null;
		System.out.println(fkk);
		return fkk; 
	}*/
	
	public void insert(Lekcja lekcja) {
		Kursy kursy=lekcja.getKursy();
		if(kursy.getLekcjeLpAll().size()==0) lekcja.setLp(1);
		else lekcja.setLp(Collections.max(kursy.getLekcjeLpAll())+1);
		em.persist(lekcja);
	}
	
	@SuppressWarnings("unchecked")
	public void delete(Lekcja lekcja) throws  org.eclipse.persistence.exceptions.DatabaseException {
		Integer lp=lekcja.getLp();
		Kursy kursy=lekcja.getKursy();
		lekcja=em.find(lekcja.getClass(), lekcja.getIdlekcja());
		em.remove(lekcja);
		em.flush();
		
		Query qt=em.createQuery("select l from Lekcja l where l.lp>:lp and l.kursy=:kursy order by l.lp");
		qt.setParameter("lp", lp);
		qt.setParameter("kursy", kursy);
		for(Lekcja l: (List<Lekcja>)qt.getResultList()){
			l.setLp(l.getLp()-1);
			em.merge(l);
		}
	}

	@SuppressWarnings("unchecked")
	public void update(Lekcja lekcja, Integer newLp) {
		Integer oldLp=new Integer(lekcja.getLp());
		lekcja.setLp(-1);
		em.merge(lekcja);
		//System.out.println(lekcja+"statOld");
		//System.out.println(oldLp+"new"+newLp);
		if (newLp < oldLp) {//idziemy w góre
			//System.out.println("upupup");
			Query select=em.createQuery("select l from Lekcja l where l.lp<:oldLp and l.lp>=:newLp and l.kursy=:kurs order by l.lp desc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("kurs", lekcja.getKursy());
			List<Lekcja> sl=select.getResultList();
			for (Lekcja s:sl){
				//System.out.println(lekcja.getLp());
				//System.out.println(s.getTytul());
				//System.out.println(s.getLp()+1);
				s.setLp(s.getLp()+1);
				em.merge(s);
				em.flush();
			}
			lekcja.setLp(newLp);
			em.merge(lekcja);
		} else if (newLp > oldLp) {//idziemy w dół
			Query select=em.createQuery("select l from Lekcja l where l.lp>:oldLp and l.lp<=:newLp and l.kursy=:kurs order by l.lp asc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("kurs", lekcja.getKursy());
			List<Lekcja> sl=select.getResultList();
			for (Lekcja s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
				em.flush();
			}
			lekcja.setLp(newLp);
			em.merge(lekcja);
		}else update(lekcja);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLpAll(Lekcja lekcja) {
		Query query = em.createQuery("select lf.lp from Lekcjafoty lf where lf.lekcja=:lekcja order by lf.lp");
		query.setParameter("lekcja", lekcja);
		return query.getResultList();
	}
	
}
