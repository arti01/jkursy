package org.arti01.sesBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.arti01.entit.Statyczne;
import org.arti01.entit.User;

/**
 * Session Bean implementation class Rozne
 */
@Stateful
@LocalBean
public class Rozne {
	User zalogowany;
	String infoText;
	String errorText;
	List<Statyczne> statyczneAll=new ArrayList<Statyczne>();
	@EJB UserImp userImp;
	@EJB StatyczneImp statyczneImp;

    /**
     * Default constructor. 
     */
    public Rozne() {
        // TODO Auto-generated constructor stub
    }

	public User getZalogowany() {
		return zalogowany;
	}

	public void ustaw(User zalogowany) {
		zalogowany=userImp.find(zalogowany);
		this.zalogowany = zalogowany;
	}
	public void setZalogowany(User zalogowany) {
		this.zalogowany = zalogowany;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		//System.out.println("settter"+infoText);
		this.infoText = infoText;
	}

	public List<Statyczne> getStatyczneAll() {
		//statyczneAll=statyczneImp.getFindAll();
		return statyczneAll;
	}

	public void setStatyczneAll(List<Statyczne> statyczneAll) {
		this.statyczneAll = statyczneAll;
	}

	public String getErrorText() {
		System.out.println("gett"+errorText);
		return errorText;
	}

	public void setErrorText(String errorText) {
		System.out.println("sett"+errorText);
		this.errorText = errorText;
	}

}
