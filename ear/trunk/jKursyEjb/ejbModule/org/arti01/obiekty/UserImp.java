package org.arti01.obiekty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

@Stateless
@LocalBean
public class UserImp  implements UserImpLocal{
	
	@PersistenceContext
	EntityManager em;
	Logger logger = Logger.getLogger(UserImp.class);

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return em.createQuery("from User by username desc").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAdmin(String order, boolean asc) {
		if (order==null)order="imieNazwisko";
		String ascS;
		Query query;
		if(asc) ascS="asc";
		else ascS="desc";
		//query=baza.przygotuj("select distinct u from User as u join u.userRoles as r where r in(:role) order by u."+order+" "+ascS);
		query=em.createQuery("select distinct ur.user from UserRole as ur where ur.role=:role");
		Role rola=new Role();
		rola.setRola("admin");
		//Set<Role> role=new HashSet<Role>();
		//role.add(rola);
		query.setParameter("role", rola);
		return query.getResultList();
	}
	/*
	@SuppressWarnings("unchecked")
	public List<User> findWyklad(String order, boolean asc) {
		if (order==null)order="imieNazwisko";
		String ascS;
		Query query;
		if(asc) ascS="asc";
		else ascS="desc";
		Baza baza=new Baza();
		query=baza.przygotuj("select distinct u from User as u join u.role as r where r in(:role) order by u."+order+" "+ascS);
		Role rola=new Role();
		rola.setRola("wykladowca");
		Set<Role> role=new HashSet<Role>();
		role.add(rola);
		query.setParameter("role", role);
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
			Role rola=new Role();
		rola.setRola("kursant");
		Set<Role> role=new HashSet<Role>();
		role.add(rola);
		query.setParameter("role", role);
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
		Set<Role> role=new HashSet<Role>();
		Role rola=new Role();
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


	public User find(User user) {
		User userNew;
		if (user.getUsername() != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from User where username=:username");
			logger.info(user.getUsername());
			query.setParameter("username", user.getUsername());
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
		query.setParameter("username", user.getUsername());
		if(query.getResultList().size()==0) return false;
		else return true; 
	}*/
}
