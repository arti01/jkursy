package org.arti01.all;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.arti01.abstrakt.All;
import org.arti01.admin.UsersAc;
import org.arti01.obiekty.Statyczne;
import org.arti01.obiekty.StatyczneImp;
import org.arti01.obiekty.User;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;


public class IndexAc extends All {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	private Statyczne strona;
	User user;
	
	public String execute() throws Exception{
		User u=new User();
		u.setUsername("aa");
		logger.info("jestemmmm"+u.getImieNazwisko());
		//statyczne= new StatyczneImp().findAll();
		return SUCCESS;
	}
	
	public String statyczne() throws Exception{
		//logger.info(this.getZalogowany());
		strona=new StatyczneImp().find(strona);
		return "statyczne";
	}
	public String zalozKonto() throws Exception{
		user= new User();
		user.setDataZmiany(new Date());
		//logger.info(this.getZalogowany());
		return "zalozKonto";
	}
	public String dodajKonto() throws Exception{
		UsersAc ua=new UsersAc();
		ua.setUser(user);
		ua.dodaj();
		setInfoText(ua.getInfoText()+" teraz czekaj az dostaniesz maila");
		//UserAc=new UsersAc().dodaj();
		//logger.info(this.getZalogowany());
		return "info";
	}
	
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		setLogin(null);
		return "logout";
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
	@VisitorFieldValidator(message = "błąd: ", key = "blad.loginu")
	public void setUser(User user) {
		this.user = user;
	}

}