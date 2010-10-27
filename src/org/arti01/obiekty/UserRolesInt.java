package org.arti01.obiekty;

import java.util.List;

public interface UserRolesInt {
	public List<UserRoles> findAll();

	public void insert(UserRoles userRoles);
	public void update(UserRoles userRoles) throws Exception;
	public void remove(UserRoles userRoles) throws Exception;

	public UserRoles find(UserRoles userRoles);

}
