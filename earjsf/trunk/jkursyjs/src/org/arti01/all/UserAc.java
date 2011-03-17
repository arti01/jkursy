package org.arti01.all;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Newsy;
import org.arti01.entit.Role;
import org.arti01.entit.User;
import org.arti01.sesBean.UserImp;
import org.arti01.utility.Login;
import org.arti01.utility.ResizeJpg;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean(name="userAc")
@SessionScoped
public class UserAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UserAc.class);

	User user=new User();
	@EJB UserImp ui;
	String errorText;
	String userpass1;
	
	@ManagedProperty(value = "#{login}") private Login loginBean;
	
	private static int DLUGOSC=300;
    private static int WYSOKOSC=200;
	
	public String dodaj() {
		if(!user.getUserpass().equals(userpass1)){
			errorText="różne hasła";
			return "rejestracja";	
		}
		user.setDataZmiany(new Date());
		Set<Role> role=new HashSet<Role>();
		Role r=new Role();
		r.setRola(Role.KURSANT);
		role.add(r);
		user.setRoles(role);
		try {
			if(!ui.insert(user)) errorText="nieudana rejestracja";
			else errorText="udana rejestracja - możesz się zalogować";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorText="nieudane - taki login już istnieje - spróbuj jeszcze raz";
			e.printStackTrace();
		}
		user=new User();
		return "info";
	}
	
	public void listenerFoty(FileUploadEvent event) {
        UploadedFile item = event.getUploadedFile();
        User user=loginBean.getZalogowany();
		user.setFota(new ResizeJpg().zrobB(DLUGOSC, WYSOKOSC, item.getData()));
        try {
			ui.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void paintFota(OutputStream stream, Object object) throws IOException {
		logger.info(object);
		logger.info("sssssssssss");
    	stream.write(loginBean.getZalogowany().getFota());
    }
	
	public void usunFote(){
		User user=loginBean.getZalogowany();
		user.setFota(null);
		try {
			ui.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getUserpass1() {
		return userpass1;
	}

	public void setUserpass1(String userpass1) {
		this.userpass1 = userpass1;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

}