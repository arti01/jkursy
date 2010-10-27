package org.arti01.obiekty;

import java.util.List;

public interface RolesInt {
	public List<Rola> findAll();

	public void insert(Rola userRoles);
	public void update(Rola userRoles) throws Exception;
	public void remove(Rola userRoles) throws Exception;

	public Rola find(Rola userRoles);

}
