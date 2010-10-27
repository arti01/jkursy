package org.arti01.obiekty;

import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.hibernate.Query;

public class RolaImp implements RolesInt {
	Logger logger = Logger.getLogger(RolaImp.class);

	@SuppressWarnings("unchecked")
	public List<Rola> findAll() {
		return new Baza().getAll("from Rola");
	}

	public void insert(Rola userRoles) {
		new Baza().dodaj(userRoles);
	}

	public void update(Rola userRoles) throws Exception {
		new Baza().update(userRoles);
	}

	public void remove(Rola userRoles) throws Exception {
			new Baza().usun(userRoles);
	}

	public Rola find(Rola rola) {
		Rola userRolesNew;
			Baza baza = new Baza();
			Query query = baza
					.przygotuj("from Rola where rola=:rola");
			query.setEntity("rola", rola);
			userRolesNew = (Rola) baza.select(query).iterator().next();
			logger.info("rola"+userRolesNew.getRola());
			// userNew=(UserRoles) new
			// Baza().pobierz("from UserRoles where username='" +
			// user.getUserRolesname()+"'").iterator().next();
		return userRolesNew;
	}
}
