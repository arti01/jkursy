package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.arti01.entit.Role;

@Stateless
@LocalBean
public class RoleImp{
	//Logger logger = Logger.getLogger(RoleImp.class);
	@PersistenceContext
	EntityManager em;

	public Role find(Role role) {
		if (role.getRola() != null) {
			role=em.find(Role.class, role.getRola());
		} else
			role = new Role();
		return role;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		Query query=em.createQuery("select r from Role r");
		return query.getResultList();
	}
	
	public List<String> getRolesName(){
		List<String> rolesName=new ArrayList<String>();
		for(Role r:findAll()){
			rolesName.add(r.getRola());
		}
		return rolesName;
	}
/*
	public void insert(Role userRoles) {
		new Baza().dodaj(userRoles);
	}

	public void update(Role userRoles) throws Exception {
		new Baza().update(userRoles);
	}

	public void remove(Role userRoles) throws Exception {
			new Baza().usun(userRoles);
	}

	public Role find(Role rola) {
		Role userRolesNew;
			Baza baza = new Baza();
			Query query = baza
					.przygotuj("from Role where rola=:rola");
			query.setParameter("rola", rola);
			userRolesNew = (Role) baza.select(query).iterator().next();
			logger.info("rola"+userRolesNew.getRola());
			// userNew=(UserRoles) new
			// Baza().pobierz("from UserRoles where username='" +
			// user.getUserRolesname()+"'").iterator().next();
		return userRolesNew;
	}
	*/
}
