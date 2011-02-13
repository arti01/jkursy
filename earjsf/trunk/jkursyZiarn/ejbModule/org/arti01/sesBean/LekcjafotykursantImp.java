package org.arti01.sesBean;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Lekcjafotykursant;

@Stateless
@LocalBean
public class LekcjafotykursantImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	List<Integer> lekcjeLpAll;
	@EJB LekacjaImp lekcjaImp;
	
	public Lekcjafotykursant find(Lekcjafotykursant lf) {
			lf=em.find(Lekcjafotykursant.class, lf.getIdlekcjafotykursant());
			em.refresh(lf);
		return lf;
	}
	
	public boolean insert(Lekcjafotykursant fota){
		em.persist(fota);
		return true;
	}
	
	public boolean update(Lekcjafotykursant fota){
		em.merge(fota);
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
