package obiekty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.arti01.baza.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserImp implements UserInt {
	Logger logger = Logger.getLogger(UserImp.class);

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return new Baza().getAll("from User by username desc");
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAdmin(String order, boolean asc) {
		if (order==null)order="imieNazwisko";
		String ascS;
		Query query;
		if(asc) ascS="asc";
		else ascS="desc";
		Baza baza=new Baza();
		query=baza.przygotuj("select distinct u from User as u join u.role as r where r in(:role) order by u."+order+" "+ascS);
		Rola rola=new Rola();
		rola.setRola("admin");
		Set<Rola> role=new HashSet<Rola>();
		role.add(rola);
		query.setParameterList("role", role);
		return baza.select(query);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findWyklad(String order, boolean asc) {
		if (order==null)order="imieNazwisko";
		String ascS;
		Query query;
		if(asc) ascS="asc";
		else ascS="desc";
		Baza baza=new Baza();
		query=baza.przygotuj("select distinct u from User as u join u.role as r where r in(:role) order by u."+order+" "+ascS);
		Rola rola=new Rola();
		rola.setRola("wykladowca");
		Set<Rola> role=new HashSet<Rola>();
		role.add(rola);
		query.setParameterList("role", role);
		return baza.select(query);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findKursant(String order, boolean asc) {
		order="imieNazwisko";
		Query query;
		String ascS;
		if(asc) ascS="asc";
		else ascS="desc";
		Baza baza=new Baza();
		query=baza.przygotuj("select distinct u from User as u join u.role as r where r in(:role) order by u."+order+" "+ascS);
			Rola rola=new Rola();
		rola.setRola("kursant");
		Set<Rola> role=new HashSet<Rola>();
		role.add(rola);
		query.setParameterList("role", role);
		return baza.select(query);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findNowy(String order, boolean asc) {
		order="imieNazwisko";
		Query query;
		String ascS;
		if(asc) ascS="asc";
		else ascS="desc";
		Baza baza=new Baza();
		query=baza.przygotuj("select distinct u from User as u where u not in (select u from User as u join u.role as r)");
		Set<Rola> role=new HashSet<Rola>();
		Rola rola=new Rola();
		rola.setRola("");
		role.add(rola);
		//query.setParameterList("role", role);
		for(Object o:baza.select(query)){
			User u=(User) o;
			logger.info(u.getUsername());	
		}
		return baza.select(query);
	}
	
	@SuppressWarnings("unchecked")
	public List<User>findOrderUsername(boolean asc) {
		 Baza baza=new Baza();
		 Query query;
		if(asc) query=baza.przygotuj("from User user order by user.username asc");
		 else query=baza.przygotuj("from User user order by user.username desc");
		 return baza.select(query);
	 }
	
	@SuppressWarnings("unchecked")
	public List<User>findOrderDataZmiany(boolean asc) {
		 Baza baza=new Baza();
		 Query query;
		if(asc) query=baza.przygotuj("from User user order by user.data_zmiany asc");
		 else query=baza.przygotuj("from User user order by user.data_zmiany desc");
		 return baza.select(query);
	 }
	
	@SuppressWarnings("unchecked")
	public List<User> findNieAktywni() {
		return new Baza().getAll("from User where aktywny=false order by data_zmiany desc");
	}

	public void insert(User user) {
		new Baza().dodaj(user);
	}

	public void update(User user) throws Exception {
			//logger.info("rola--------"+user.getRole().iterator().next().getRolename());
			//logger.info("rola--------"+user.getRole().iterator().next().getUser().getUsername());
		new Baza().update(user);
	}

	public void remove(User user) throws Exception {
		if (user.getUsername() != null) {
			user = find(user);
			user = new UserImp().find(user);
			new Baza().usun(user);
		}
	}

	public User load(User user) {
		if (user.getUsername() != null) {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			session.load(user, user.getUsername());
		} else
			user = new User();
		return user;
	}

	public User find(User user) {
		User userNew;
		if (user.getUsername() != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from User where username=:username");
			logger.info(user.getUsername());
			query.setString("username", user.getUsername());
			userNew = (User) baza.select(query).iterator().next();
		} else
			userNew = new User();
		return userNew;
	}

	public boolean test(){
		return true;
	}
	public boolean istnieje(User user){
		if(user.getUsername()==null) return true;
		Baza baza = new Baza();
		Query query = baza.przygotuj("from User where username=:username");
		query.setString("username", user.getUsername());
		if(query.list().size()==0) return false;
		else return true; 
	}
}
