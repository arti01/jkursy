package org.arti01.all;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.arti01.abstrakt.All;
import org.arti01.obiekty.Statyczne;
import org.arti01.obiekty.StatyczneImp;


public class IndexAc extends All {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	private Statyczne strona;
	
	public String execute() throws Exception{
		logger.info("jestemmmm");
		//statyczne= new StatyczneImp().findAll();
		return SUCCESS;
	}
	
	public String statyczne() throws Exception{
		//logger.info("jestemmmm");
		strona=new StatyczneImp().find(strona);
		return "statyczne";
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

}