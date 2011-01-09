package org.arti01.sesBean;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjapliki;

@Stateless
@LocalBean
public class LekcjaplikiImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	List<Integer> lekcjeLpAll;
	@EJB LekacjaImp lekcjaImp;
	
	public Lekcjapliki find(Lekcjapliki lp) {
		lp=em.find(Lekcjapliki.class, lp.getIdlekcjapliki());
		em.refresh(lp);
		return lp;
	}
	
	public boolean update(Lekcjapliki lp){
			em.merge(lp);
			return true;
	}
	
	
	public boolean insert(Lekcjapliki plik){
		Lekcja lekcja=plik.getLekcja();
		lekcja=lekcjaImp.find(lekcja);
		em.persist(plik);
		return true;
	}
	
	public void delete(Lekcjapliki lp) {
		em.remove(em.merge(lp));
		em.flush();
	}
	

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
