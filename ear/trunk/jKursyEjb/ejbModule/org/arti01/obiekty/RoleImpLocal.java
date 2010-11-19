package org.arti01.obiekty;

import java.util.List;

import javax.ejb.Local;

@Local
public interface RoleImpLocal {
	public List<Role> findAll();
}
