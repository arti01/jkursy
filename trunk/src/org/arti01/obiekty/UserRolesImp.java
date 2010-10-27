package org.arti01.obiekty;

import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.hibernate.Query;

public class UserRolesImp implements UserRolesInt {
	Logger logger = Logger.getLogger(UserRolesImp.class);

	@SuppressWarnings("unchecked")
	public List<UserRoles> findAll() {
		return new Baza().getAll("from UserRoles order by username");
	}

	public void insert(UserRoles userRoles) {
		new Baza().dodaj(userRoles);
	}

	public void update(UserRoles userRoles) throws Exception {
		new Baza().update(userRoles);
	}

	public void remove(UserRoles userRoles) throws Exception {
		if (userRoles.getId_user_roles() != null) {
			new Baza().usun(userRoles);
		}
	}

	public UserRoles find(UserRoles userRoles) {
		UserRoles userRolesNew;
		if (userRoles.getId_user_roles() != null) {
			Baza baza = new Baza();
			Query query = baza
					.przygotuj("from UserRoles where id_user_roles=:id_user_roles");
			query.setParameter("user", userRoles.getUser());
			userRolesNew = (UserRoles) baza.select(query).iterator().next();
			// userNew=(UserRoles) new
			// Baza().pobierz("from UserRoles where username='" +
			// user.getUserRolesname()+"'").iterator().next();
		} else
			userRolesNew = new UserRoles();
		return userRolesNew;
	}
}
