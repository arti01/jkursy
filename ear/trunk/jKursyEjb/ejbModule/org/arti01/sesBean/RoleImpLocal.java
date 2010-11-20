package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

@Local
public interface RoleImpLocal {
	public List<Role> findAll();
}
