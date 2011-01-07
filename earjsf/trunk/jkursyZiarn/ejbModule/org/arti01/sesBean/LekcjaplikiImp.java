package org.arti01.sesBean;

import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafoty;

@Stateless
@LocalBean
public class LekcjaplikiImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	List<Integer> lekcjeLpAll;
	@EJB LekacjaImp lekcjaImp;
	
	public Lekcjafoty find(Lekcjafoty lf) {
		if (lf != null) {
			lf=em.find(Lekcjafoty.class, lf.getIdlekcjafoty());
			em.refresh(lf);
		} else lf = new Lekcjafoty();
		return lf;
	}
	
	public boolean update(Lekcjafoty lf){
			em.merge(lf);
			return true;
	}
	
	@SuppressWarnings("unchecked")
	public void update(Lekcjafoty lf, Integer newLp) {
		Integer oldLp=new Integer(lf.getLp());
		lf.setLp(-1);
		em.merge(lf);
		//System.out.println(lekcja+"statOld");
		//System.out.println(oldLp+"new"+newLp);
		if (newLp < oldLp) {//idziemy w góre
			//System.out.println("upupup");
			Query select=em.createQuery("select lf from Lekcjafoty lf where lf.lp<:oldLp and lf.lp>=:newLp and lf.lekcja=:lekcja order by lf.lp desc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("lekcja", lf.getLekcja());
			List<Lekcjafoty> sl=select.getResultList();
			for (Lekcjafoty s:sl){
				s.setLp(s.getLp()+1);
				em.merge(s);
				em.flush();
			}
			lf.setLp(newLp);
			em.merge(lf);
		} else if (newLp > oldLp) {//idziemy w dół
			Query select=em.createQuery("select lf from Lekcjafoty lf where lf.lp>:oldLp and lf.lp<=:newLp and lf.lekcja=:lekcja order by lf.lp asc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("lekcja", lf.getLekcja());
			List<Lekcjafoty> sl=select.getResultList();
			for (Lekcjafoty s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
				em.flush();
			}
			lf.setLp(newLp);
			em.merge(lf);
		}else update(lf);
	}
	
	public boolean insert(Lekcjafoty fota){
		Lekcja lekcja=fota.getLekcja();
		lekcja=lekcjaImp.find(lekcja);
		if(lekcja.getFotyLpAll().size()==0) fota.setLp(1);
		else fota.setLp(Collections.max(lekcja.getFotyLpAll())+1);
		em.persist(fota);
		return true;
	}
	
	public void delete(Lekcjafoty lf) {
		Integer lp=lf.getLp();
		Lekcja lekcja=lf.getLekcja();
		lf=em.find(lf.getClass(), lf.getIdlekcjafoty());
		em.remove(lf);
		em.flush();
		Query query=em.createQuery("update Lekcjafoty lf set lf.lp=lf.lp-1 where lf.lp>:lp and lf.lekcja=:lekcja");
		query.setParameter("lp", lp);
		query.setParameter("lekcja", lekcja);
		query.executeUpdate();
	}
	

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
