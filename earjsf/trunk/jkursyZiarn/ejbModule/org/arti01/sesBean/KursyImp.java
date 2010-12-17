package org.arti01.sesBean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.arti01.entit.Kursy;
import org.arti01.entit.Role;
import org.arti01.entit.User;

@Stateless
@LocalBean
public class KursyImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	
	@EJB RoleImp roleImp;

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		//em.clear();
		List<Kursy> wynik=em.createQuery("select k from Kursy k order by k.dataod desc").getResultList();
		System.out.println(wynik+"----------------");
		for(Kursy k :wynik){
			System.out.println(k.getNazwa());
			System.out.println(k.getUsers().size());
		}
		return wynik;
	}
	
	@SuppressWarnings("unchecked")
	public List<Kursy> findNiezakonczone() {
		Query query=em.createNamedQuery("findNiezakonczone");
		query.setParameter("datado", new Date());
		return  query.getResultList();
	}
	public Kursy find(Kursy kurs) {
		if (kurs != null) {
			kurs=em.find(Kursy.class, kurs.getIdkursy());
			em.refresh(kurs);
			Set<User>kursanci=new HashSet<User>();
			Set<User>wykladowcy=new HashSet<User>();
			Role wyklad=new Role();
			wyklad.setRola(Role.WYKLADOWCA);
			wyklad=roleImp.find(wyklad);
			
			Role kursant=new Role();
			kursant.setRola(Role.KURSANT);
			kursant=roleImp.find(kursant);
			for(User u:kurs.getUsers()){
				if(u.getRoles().contains(wyklad)) wykladowcy.add(u);
				if(u.getRoles().contains(kursant)) kursanci.add(u);
				System.out.println(wykladowcy.size()+""+kursanci.size());
			}
			kurs.setKursanci(kursanci);
			kurs.setWykladowcy(wykladowcy);
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
		em.remove(em.merge(kurs));
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
