package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arti01.entit.Role;
import org.arti01.entit.User;

@Stateless
@LocalBean
public class UserImp {

	@PersistenceContext
	EntityManager em;
	
	@EJB RoleImp roleImp;

	public User find(User user) {
		if (user.getUsername() != null) {
			user = em.find(User.class, user.getUsername());
		} else
			user = new User();
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return em.createQuery("select u from User u order by u.username")
				.getResultList();
	}

	public boolean insert(User user) {
		em.persist(user);
		return true;
	}

	public boolean update(User user) {
		em.merge(user);
		return true;
	}

	public void remove(User user) {
		em.remove(em.find(User.class, user.getUsername()));
		// em.remove(em.merge(user));
	}
	
	public List<String> getRolesName(User user){
		user=find(user);
		List<String> rolesName=new ArrayList<String>();
		for(Role r:user.getRoles()){
			rolesName.add(r.getRola());
		}
		return rolesName;
	}
	
	public User setRolesName(User user, List<String>rolesName){
		user=find(user);
		user.getRoles().clear();
		for(String name:rolesName){
			Role r=new Role();
			r.setRola(name);
			r=roleImp.find(r);
			user.getRoles().add(r);
		}
		return user;
	}
}
/*
 * @SuppressWarnings("unchecked") public List<User>findOrderDataZmiany(boolean
 * asc) { Baza baza=new Baza(); Query query; if(asc)
 * query=baza.przygotuj("from User user order by user.data_zmiany asc"); else
 * query=baza.przygotuj("from User user order by user.data_zmiany desc"); return
 * baza.select(query); }
 * 
 * @SuppressWarnings("unchecked") public List<User> findNieAktywni() { return
 * new Baza().getAll("from User where aktywny=false order by data_zmiany desc");
 * }
 * 
 * 
 * 
 * public void update(User user) throws Exception {
 * //logger.info("rola--------"+user.getRole().iterator().next().getRolename());
 * //logger.info("rola--------"+user.getRole().iterator().next().getUser().
 * getUsername()); new Baza().update(user); }
 * 
 * 
 * 
 * 
 * public boolean test(){ return true; } public boolean istnieje(User user){
 * if(user.getUsername()==null) return true; Baza baza = new Baza(); Query query
 * = baza.przygotuj("from User where username=:username");
 * query.setParameter("username", user.getUsername());
 * if(query.getResultList().size()==0) return false; else return true; }
 */
