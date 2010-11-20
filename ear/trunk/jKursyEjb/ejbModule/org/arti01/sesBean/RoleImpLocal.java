package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

import org.arti01.entit.Role;

@Local
public interface RoleImpLocal {
	public List<Role> findAll();
}
