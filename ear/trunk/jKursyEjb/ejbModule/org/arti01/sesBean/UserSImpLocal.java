package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

import org.arti01.entit.User;

@Local
public interface UserSImpLocal {

	@SuppressWarnings("unchecked")
	public abstract List<User> findAll();

	@SuppressWarnings("unchecked")
	public abstract List<User> findWyklad(String order, boolean asc);

	@SuppressWarnings("unchecked")
	public abstract List<User> findAdmin(String order, boolean asc);

	@SuppressWarnings("unchecked")
	public abstract List<User> findKursant(String order, boolean asc);
	public abstract List<User> findNowy(String order, boolean asc);
	public abstract List<User> findOrderUsername(boolean asc);
}