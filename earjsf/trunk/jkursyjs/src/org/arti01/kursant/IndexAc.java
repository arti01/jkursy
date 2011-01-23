package org.arti01.kursant;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.RoleImp;

@ManagedBean(name="kursantIndexAc")
@SessionScoped
public class IndexAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	
	private Role role=new Role();
	private Statyczne strona;
	@EJB RoleImp roleImp;
	
	public Role getRole() {
		role.setRola(Role.KURSANT);
		role=roleImp.find(role);
		return role;
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

}