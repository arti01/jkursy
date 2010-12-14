package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.arti01.entit.Kursy;
import org.arti01.entit.Role;
import org.arti01.entit.User;

@Stateless
@LocalBean
public class UserImp {

	@PersistenceContext
	EntityManager em;
	
	@EJB RoleImp roleImp;

	public List<User> allOrder(boolean asc){ 
		CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> queryDefinition = queryBuilder.createQuery(User.class);
		Root<User> user = queryDefinition.from(User.class);
		Order o;
		if(asc) o=queryBuilder.asc(user.get("username"));
		else o=queryBuilder.desc(user.get("username"));
		queryDefinition.select(user).orderBy(o);
		//System.out.println(em.createQuery(queryDefinition).getResultList());
		return em.createQuery(queryDefinition).getResultList(); 
	}
	
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

	public boolean insert(User user) throws Exception{
		em.persist(user);
		return true;
	}

	public boolean update(User user) throws Exception{
		em.merge(user);
		return true;
	}

	public void remove(User user) {
		/*user=em.find(User.class, user.getUsername());
		user.getKursies().clear();
		user.getRoles().clear();
		em.merge(user);
		em.flush();*/
		//em.remove(user);
		em.remove(em.merge(user));
	}

	@SuppressWarnings("unchecked")
	public List<Kursy> dostepneKursy(User user){
		Query q=em.createQuery("select k from Kursy k where k.idkursy not in(select kr.idkursy from User u join u.kursies kr where u.username=:username)");
		//Set<Kursy> kursy=new HashSet<Kursy>();
		//kursy.add(user.getKursies().iterator().next());
		q.setParameter("username", user.getUsername());
		return q.getResultList();
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

	public List<User> findNieprzypisani() {
		CriteriaBuilder qB = em.getCriteriaBuilder();
		CriteriaQuery<User> cQ = qB.createQuery(User.class);
		Root<User> user = cQ.from(User.class);
		Order o;
		o=qB.asc(user.get("username"));
		cQ.select(user).orderBy(o);
		cQ.where(qB.isEmpty(user.<List<User>>get("roles")));
		//System.out.println(em.createQuery(cQ).getResultList());
		return em.createQuery(cQ).getResultList();
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
