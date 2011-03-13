package org.arti01.kursant;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.Statyczne;
import org.arti01.entit.User;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.UserImp;
import org.arti01.utility.Login;

@ManagedBean(name="kursantIndexAc")
@SessionScoped
public class IndexAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	
	private Role role=new Role();
	private Statyczne strona;
	@EJB RoleImp roleImp;
	@EJB UserImp ui;
	private User user;
	private String userpass1;
	@ManagedProperty(value = "#{login}") private Login loginBean;
	@ManagedProperty(value = "#{kursantKursyAc}") private KursyAc kursyAc;
	private boolean takNie=false;
	
	public Role getRole() {
		role.setRola(Role.KURSANT);
		role=roleImp.find(role);
		return role;
	}
	
	public String profil(){
		kursyAc.setErrorText("");
		user=loginBean.getZalogowany();
		return "profil";
	}
	
	public String edytujProfil(){
		if(!user.getUserpass().equals(userpass1)&&takNie){
			kursyAc.setErrorText("różne hasła");
			return "profil";	
		}
		try {
			ui.update(user);
			kursyAc.setErrorText("profil zmieniony");
		} catch (Exception e) {
			kursyAc.setErrorText("coś nie teges ze zmianą");
			e.printStackTrace();
		}
		return "info";
	}
	
	public String statyczna(){
		return "statyczneDetale";
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleImp getRoleImp() {
		return roleImp;
	}

	public void setRoleImp(RoleImp roleImp) {
		this.roleImp = roleImp;
	}

	public Statyczne getStrona() {
		return strona;
	}

	public void setStrona(Statyczne strona) {
		this.strona = strona;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

	public String getUserpass1() {
		return userpass1;
	}

	public void setUserpass1(String userpass1) {
		this.userpass1 = userpass1;
	}

	public boolean isTakNie() {
		return takNie;
	}

	public void setTakNie(boolean takNie) {
		this.takNie = takNie;
	}

	public KursyAc getKursyAc() {
		return kursyAc;
	}

	public void setKursyAc(KursyAc kursyAc) {
		this.kursyAc = kursyAc;
	}

}