package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

import org.arti01.entit.User;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@Local
public interface UserImpLocal {
	public User find(User user);
	public void insert(User user);
	public void update(User user);
	public void remove(User user);
	public User getUser();
	@VisitorFieldValidator(message = "błąd: ", key = "blad.loginu")
	public void setUser(User user);
}
