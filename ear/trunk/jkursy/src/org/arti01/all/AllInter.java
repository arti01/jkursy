package org.arti01.all;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.All;
import org.arti01.sesBean.StatyczneImpLocal;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AllInter extends AbstractInterceptor {
	Logger logger = Logger.getLogger(AllInter.class);
	@EJB StatyczneImpLocal statyczneImp;
	
	public String intercept(ActionInvocation invocation) throws Exception {
		All action = (All) invocation.getAction();
		action.setStatyczne(statyczneImp.findAll());
		return invocation.invoke();
	}
}
