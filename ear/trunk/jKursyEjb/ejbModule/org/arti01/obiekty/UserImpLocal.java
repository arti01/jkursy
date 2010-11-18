package org.arti01.obiekty;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UserImpLocal {
	public List<User> findAdmin(String order, boolean asc) ;
}
