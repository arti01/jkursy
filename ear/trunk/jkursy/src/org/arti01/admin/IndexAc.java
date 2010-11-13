package org.arti01.admin;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.Akcja;
import org.arti01.obiekty.User;

public class IndexAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	
	private User user;

	public String execute() throws Exception{
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}