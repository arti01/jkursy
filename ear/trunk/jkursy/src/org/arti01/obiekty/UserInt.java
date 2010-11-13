package org.arti01.obiekty;

import java.util.List;

public interface UserInt {
	public List<User> findAll();

	public void insert(User user);
	public void update(User user) throws Exception;
	public void remove(User user) throws Exception;

	public User find(User user);

}
