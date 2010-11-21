package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

import org.arti01.entit.User;

@Local
public interface UserImpLocal {
	public List<User> findAdmin(String order, boolean asc);
	public List<User> findWyklad(String order, boolean asc);
	public List<User> findKursant(String order, boolean asc);
	public List<User> findNowy(String order, boolean asc);
	public List<User>findOrderUsername(boolean asc);
	public User find(User user);
	public void insert(User user);
	public void remove(User user);
	public User getUser();
	public void setUser(User user);
}
