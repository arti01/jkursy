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
import org.arti01.entit.Userfoty;
import org.arti01.entit.User;
import org.arti01.entit.Userfoty;

@Stateless
@LocalBean
public class UserfotyImp {
	@PersistenceContext EntityManager em;
	@EJB UserImp userImp;
	String errorText="";
	List<Integer> lpAll;
	
	public Userfoty find(Userfoty lf) {
		lf=em.find(Userfoty.class, lf.getIduserfoty());
		em.refresh(lf);
		return lf;
	}
	
	public boolean update(Userfoty lf){
			em.merge(lf);
			return true;
	}
	
	public boolean insert(Userfoty fota){
		User user=userImp.find(fota.getUser());
		if(user.getUserfoty().size()==0) fota.setLp(1);
		else fota.setLp(user.getUserfoty().size()+1);
		em.persist(fota);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(Userfoty lf) {
		Integer lp=lf.getLp();
		User user=lf.getUser();
		lf=em.find(lf.getClass(), lf.getIduserfoty());
		em.remove(lf);
		em.flush();
		
		Query qt=em.createQuery("select lf from Userfoty lf where lf.lp>:lp and lf.user=:user order by lf.lp");
		qt.setParameter("lp", lp);
		qt.setParameter("user", user);
		for(Userfoty lf1: (List<Userfoty>)qt.getResultList()){
			lf1.setLp(lf1.getLp()-1);
			em.merge(lf1);
			em.flush();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void update(Userfoty lf, Integer newLp) {
		Integer oldLp=new Integer(lf.getLp());
		lf.setLp(-1);
		em.merge(lf);
		//System.out.println(lekcja+"statOld");
		//System.out.println(oldLp+"new"+newLp);
		if (newLp < oldLp) {//idziemy w góre
			//System.out.println("upupup");
			Query select=em.createQuery("select lf from Userfoty lf where lf.lp<:oldLp and lf.lp>=:newLp and lf.user=:user order by lf.lp desc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("user", lf.getUser());
			List<Userfoty> sl=select.getResultList();
			for (Userfoty s:sl){
				//System.out.println(s.getOpis());
				//System.out.println(s.getLp());
				//System.out.println(s.getLp()+1);
				s.setLp(s.getLp()+1);
				em.merge(s);
				em.flush();
			}
			lf.setLp(newLp);
			em.merge(lf);
			em.flush();
			em.clear();
		} else if (newLp > oldLp) {//idziemy w dół
			Query select=em.createQuery("select lf from Userfoty lf where lf.lp>:oldLp and lf.lp<=:newLp and lf.user=:user order by lf.lp asc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			select.setParameter("user", lf.getUser());
			List<Userfoty> sl=select.getResultList();
			for (Userfoty s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
				em.flush();
			}
			lf.setLp(newLp);
			em.merge(lf);
			em.flush();
			em.clear();
		}else update(lf);
	}
	
	/*
	
	@SuppressWarnings("unchecked")
	public void delete(Userfoty lf) {
		Integer lp=lf.getLp();
		Lekcja lekcja=lf.getLekcja();
		lf=em.find(lf.getClass(), lf.getIdlekcjafoty());
		em.remove(lf);
		em.flush();
		
		Query qt=em.createQuery("select lf from Userfoty lf where lf.lp>:lp and lf.lekcja=:lekcja order by lf.lp");
		qt.setParameter("lp", lp);
		qt.setParameter("lekcja", lekcja);
		for(Userfoty lf1: (List<Userfoty>)qt.getResultList()){
			lf1.setLp(lf1.getLp()-1);
			em.merge(lf1);
		}
	}
	
*/
	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
