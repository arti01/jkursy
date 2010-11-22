package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.User;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@Stateless
@LocalBean
public class UserImp  implements UserImpLocal{
	
	@PersistenceContext
	EntityManager em;
	Logger logger = Logger.getLogger(UserImp.class);
	User user;

	public User find(User user) {
		if (user.getUsername() != null) {
			user=em.find(User.class, user.getUsername());
		} else
			user = new User();
		return user;
	}
	
	public void insert(User user) {
		em.persist(user);
	}
	
	public void update(User user) {
		em.merge(user);
	}
	
	public void remove(User user) {
		em.remove(em.find(User.class, user.getUsername()));
		//em.remove(em.merge(user));
		}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	}*/
