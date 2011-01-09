package org.arti01.utility;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.arti01.entit.User;
import org.arti01.sesBean.UserImp;

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(Login.class);
	User zalogowany;
	@EJB UserImp userImp;
	//SFSBFactory sfbs;
	
	public String wyloguj(){
		HttpServletRequest r=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//logger.info(r.getRemoteUser()+"wwwwwwwwwwwwwww");
		try {
			r.logout();
			zalogowany=null;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//rozne.setInfoText("jesteś wylogowany");
		return "indexAll";
	}

	public User getZalogowany() {
		HttpServletRequest r=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//logger.info(r.getRemoteUser());
		User z=new User();
		z.setUsername(r.getRemoteUser());
		zalogowany=userImp.find(z);
		//logger.info("imie nazwisko"+zalogowany.getImieNazwisko());
		//rozne.ustaw(z);
		//rozne.setInfoText("jesteś zalogowany");
		return zalogowany;
		//return rozne.getZalogowany();
	}

}
