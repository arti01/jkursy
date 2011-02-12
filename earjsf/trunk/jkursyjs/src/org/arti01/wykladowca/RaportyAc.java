package org.arti01.wykladowca;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.utility.Login;

@ManagedBean(name="raportyAc")
@SessionScoped
public class RaportyAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(RaportyAc.class);
	@ManagedProperty(value="#{login}") private Login login;

	public String raport1(){
		
		return "raport1";
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
}