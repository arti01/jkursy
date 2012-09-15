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

	List<String>rolesName=new ArrayList<String>();
	
	public Role find(Role role) {
		if (role.getRola() != null) {
			role=em.find(Role.class, role.getRola());
			em.refresh(role);
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
		rolesName.clear();
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

	public void setRolesName(List<String> rolesName) {
		this.rolesName = rolesName;
	}
}
