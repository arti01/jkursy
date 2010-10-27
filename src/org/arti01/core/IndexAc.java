package org.arti01.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.arti01.abstrakt.Akcja;

public class IndexAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	private Integer miejscid;
	private Integer katid;
	
	public String execute() throws Exception{

		logger.info("jestemmmm");
		return SUCCESS;
	}
	
	
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		setLogin(null);
		return "logout";
	}

	public Integer getMiejscid() {
		return miejscid;
	}

	public void setMiejscid(Integer miejscid) {
		this.miejscid = miejscid;
	}

	public Integer getKatid() {
		return katid;
	}

	public void setKatid(Integer katid) {
		this.katid = katid;
	}

}