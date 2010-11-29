package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.User;

@Stateful
@LocalBean
public class UserSImp  {
	
	@PersistenceContext
	EntityManager em;
	//Logger logger = Logger.getLogger(UserSImp.class);
	@EJB UserImp userImp;
	
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return em.createQuery("from User by username desc").getResultList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<User> findWyklad(String order, boolean asc) {
		if (order==null)order="imieNazwisko";
		String ascS;
		Query query;
		if(asc) ascS="asc";
		else ascS="desc";
		query=
			em.createQuery("select distinct ur.user from UserRole as ur where ur.role=:role");
		Role rola=new Role();
		rola.setRola(Role.WYKLADOWCA);
		//Set<Role> role=new HashSet<Role>();
		//role.add(rola);
		query.setParameter("role", rola);
		return query.getResultList();
	}
	
	/*
	
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
		rola.setRola(Role.ADMIN);
		//Set<Role> role=new HashSet<Role>();
		//role.add(rola);
		query.setParameter("role", rola);
		admini=(List<UserImpLocal>)query.getResultList();
		return query.getResultList();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> findKursant(String order, boolean asc) {
		order="imieNazwisko";
		Query query;
		String ascS;
		if(asc) ascS="asc";
		else ascS="desc";
		logger.info("start");
		query=em.createQuery("select distinct ur.user from UserRole as ur where ur.role=:role");
			Role rola=new Role();
		rola.setRola(Role.KURSANT);
		//Set<Role> role=new HashSet<Role>();
		//role.add(rola);
		query.setParameter("role", rola);
		query.getResultList();
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> findNowy(String order, boolean asc) {
		order="imieNazwisko";
		Query query;
		String ascS;
		if(asc) ascS="asc";
		else ascS="desc";
		query=em.createQuery("select distinct u from User as u where u not in (select u from User as u join u.userRoles as r)");
		//Set<Role> role=new HashSet<Role>();
		//Role rola=new Role();
		//rola.setRola("");
		//role.add(rola);
		//query.setParameterList("role", role);
		for(Object o:query.getResultList()){
			User u=(User) o;
			logger.info(u.getUsername());	
		}
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User>findOrderUsername(boolean asc) {
		Query query;
		if(asc) query=em.createQuery("from User user order by user.username asc");
		 else query=em.createQuery("from User user order by user.username desc");
		 return query.getResultList();
	 }

	}
	/*
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

	

	public void update(User user) throws Exception {
			//logger.info("rola--------"+user.getRole().iterator().next().getRolename());
			//logger.info("rola--------"+user.getRole().iterator().next().getUser().getUsername());
		new Baza().update(user);
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
		*/ 
	}
