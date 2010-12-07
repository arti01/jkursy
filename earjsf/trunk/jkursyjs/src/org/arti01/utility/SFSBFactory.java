package org.arti01.utility;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.arti01.sesBean.UserData;

public class SFSBFactory {
	Logger logger = Logger.getLogger(SFSBFactory.class);	

	InitialContext ctx;
	private UserData userData;

    public void getBean(Object bean) {
    	try {
    		ctx = new InitialContext();
    	    bean = ctx.lookup("java:global/jkursyEAR/jkursyZiarn/"+bean.getClass().getCanonicalName());
    	    logger.info("java:global/jkursyEAR/jkursyZiarn/"+bean.getClass().getCanonicalName());
    	    //session.setAttribute("my_stateful", myStateful);
    	} catch (Exception e) {
    		logger.error(e);
    	}

    }

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}
