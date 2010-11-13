package org.arti01.all;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.All;
import org.arti01.obiekty.StatyczneImp;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AllInter extends AbstractInterceptor {
	Logger logger = Logger.getLogger(AllInter.class);

	public String intercept(ActionInvocation invocation) throws Exception {
		All action = (All) invocation.getAction();
		action.setStatyczne(new StatyczneImp().findAll());
		return invocation.invoke();
	}
}
