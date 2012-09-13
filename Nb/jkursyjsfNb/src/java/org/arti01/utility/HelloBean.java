package org.arti01.utility;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.arti01.wykladowca.KursyAc;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{message}")
	private MessageBean messageBean;
	
	@ManagedProperty(value="#{wykladKursyAc}")
	private KursyAc kursyAc;

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSayWelcome(){
		//check if null?
		System.out.println(messageBean.getSayWelcome());
		System.out.println(kursyAc.getErrorText());
		System.out.println(kursyAc.getLekcja());
		if("".equals(name) || name ==null){
			return "start";
		}else{
			return messageBean.getSayWelcome() + name;
		}
	}

	public void setKursyAc(KursyAc kursyAc) {
		this.kursyAc = kursyAc;
	}
	
}