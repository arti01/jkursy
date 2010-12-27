package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Role;
import org.arti01.entit.User;

@Stateless
@LocalBean
public class LekcjafotyImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";
	List<Integer> lekcjeLpAll;
	@EJB RoleImp roleImp;

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		//em.clear();
		List<Kursy> wynik=em.createQuery("select k from Kursy k order by k.dataod desc").getResultList();
		List<Kursy> ret=new ArrayList<Kursy>();
		for(Kursy k :wynik){
			k=find(k);
			ret.add(k);
		}
		return ret;
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
			List<User>kursanci=new ArrayList<User>();
			List<User>wykladowcy=new ArrayList<User>();
			Role wyklad=new Role();
			wyklad.setRola(Role.WYKLADOWCA);
			wyklad=roleImp.find(wyklad);
			
			Role kursant=new Role();
			kursant.setRola(Role.KURSANT);
			kursant=roleImp.find(kursant);
			for(User u:kurs.getUsers()){
				if(u.getRoles().contains(wyklad)) wykladowcy.add(u);
				if(u.getRoles().contains(kursant)) kursanci.add(u);
				//System.out.println(wykladowcy.size()+""+kursanci.size());
			}
			kurs.setKursanci(kursanci);
			kurs.setWykladowcy(wykladowcy);
			kurs.setLekcjeLpAll(getLpAll(kurs));
		} else kurs = new Kursy();
		return kurs;
	}
	
	
	public boolean update(Kursy kursy){
		if(validUpdate(kursy)){
			em.merge(kursy);
			return true;
		}else {
			System.out.println("NIEWALID ;)");
			return false;
		}
	}
	
	public boolean insert(Lekcjafoty fota){
		em.persist(fota);
		return true;
		/*if(validInsert(kursy)){
			em.persist(kursy);
			return true;
		}else {
			return false;
		}*/
	}
	
	public void delete(Kursy kurs) {
		em.remove(em.merge(kurs));
	}
	
	public boolean validUpdate(Kursy kurs){
		if(kurs.getDataod().after(kurs.getDatado())){
			errorText="Daty bez sensu ('data do' < 'data od')";
			return false;
		}
		else return true;
	}
	
	public boolean validInsert(Kursy kurs){
		if(kurs.getDataod().after(kurs.getDatado())||kurs.getDatado().before(new Date())){
			errorText="Daty bez sensu ('data do' < 'data od' lub 'data do' < bieżącej)";
			return false;
		}
		else return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLpAll(Kursy kursy) {
		Query query = em.createQuery("select l.lp from Lekcja l where l.kursy=:kursy order by l.lp");
		query.setParameter("kursy", kursy);
		lekcjeLpAll=query.getResultList();
		return lekcjeLpAll;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public List<Integer> getLekcjeLpAll() {
		return lekcjeLpAll;
	}

	public void setLekcjeLpAll(List<Integer> lekcjeLpAll) {
		this.lekcjeLpAll = lekcjeLpAll;
	}
}
