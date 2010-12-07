package org.arti01.sesBean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Kursy;

@Stateless
@LocalBean
public class KursyImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		return em.createQuery("select k from Kursy k order by k.dataod desc").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Kursy> findNiezakonczone() {
		Query query=em.createNamedQuery("findNiezakonczone");
		query.setParameter("datado", new Date());
		return  query.getResultList();
	}
	public Kursy find(Kursy kurs) {
		if (kurs != null) {
			em.find(Kursy.class, kurs.getIdkursy());
		} else kurs = new Kursy();
		return kurs;
	}
	
	
	public boolean update(Kursy kursy){
		if(valid(kursy)){
			em.merge(kursy);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean insert(Kursy kursy){
		if(valid(kursy)){
			em.persist(kursy);
			return true;
		}else {
			return false;
		}
	}
	
	public void delete(Kursy kurs) {
		em.remove(kurs);
	}
	
	public boolean valid(Kursy kurs){
		if(kurs.getDataod().after(kurs.getDatado())||kurs.getDatado().before(new Date())){
			errorText="Daty bez sensu ('data do' < 'data od' lub 'data do' < bieżącej)";
			return false;
		}
		else return true;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
