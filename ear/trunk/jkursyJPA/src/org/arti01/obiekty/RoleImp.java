package org.arti01.obiekty;

import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.hibernate.Query;

public class RoleImp  {
	Logger logger = Logger.getLogger(RoleImp.class);

	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		return new Baza().getAll("from Role");
	}

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
			query.setEntity("rola", rola);
			userRolesNew = (Role) baza.select(query).iterator().next();
			logger.info("rola"+userRolesNew.getRola());
			// userNew=(UserRoles) new
			// Baza().pobierz("from UserRoles where username='" +
			// user.getUserRolesname()+"'").iterator().next();
		return userRolesNew;
	}
}
