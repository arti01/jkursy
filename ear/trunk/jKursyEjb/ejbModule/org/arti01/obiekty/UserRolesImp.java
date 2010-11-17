package org.arti01.obiekty;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;

public class UserRolesImp {
	Logger logger = Logger.getLogger(UserRolesImp.class);

	@SuppressWarnings("unchecked")
	public List<UserRole> findAll() {
		return new Baza().getAll("from UserRole order by username");
	}

	public void insert(UserRole userRoles) {
		new Baza().dodaj(userRoles);
	}

	public void update(UserRole userRoles) throws Exception {
		new Baza().update(userRoles);
	}

	public void remove(UserRole userRoles) throws Exception {
		if (userRoles.getIdUserRoles() != null) {
			new Baza().usun(userRoles);
		}
	}

	public UserRole find(UserRole userRoles) {
		UserRole userRolesNew;
		if (userRoles.getIdUserRoles() != null) {
			Baza baza = new Baza();
			Query query = baza
					.przygotuj("from UserRole where id_user_roles=:id_user_roles");
			query.setParameter("user", userRoles.getUser());
			userRolesNew = (UserRole) baza.select(query).iterator().next();
			// userNew=(UserRoles) new
			// Baza().pobierz("from UserRoles where username='" +
			// user.getUserRolesname()+"'").iterator().next();
		} else
			userRolesNew = new UserRole();
		return userRolesNew;
	}
}
