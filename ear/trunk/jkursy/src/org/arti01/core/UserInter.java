package org.arti01.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.arti01.abstrakt.Akcja;
import org.arti01.obiekty.*;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class UserInter extends AbstractInterceptor {
	Logger logger = Logger.getLogger(UserInter.class);
	
    public String intercept(ActionInvocation invocation) throws Exception {
    	Akcja action = (Akcja)invocation.getAction();
        HttpServletRequest request = ServletActionContext.getRequest();
        logger.info("login="+request.getRemoteUser());
        action.setLogin(request.getRemoteUser());
        User zalogowany=new User();
        zalogowany.setUsername(request.getRemoteUser());
        //zalogowany=new UserImp().load(zalogowany);
        zalogowany=new UserImp().find(zalogowany);
        action.setZalogowany(zalogowany);
       return invocation.invoke();
    }
}
