package org.arti01.abstrakt;

import org.arti01.obiekty.User;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class Akcja extends ActionSupport {
	String login;
	User zalogowany;
	String infoText;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public User getZalogowany() {
		return zalogowany;
	}

	public void setZalogowany(User zalogowany) {
		this.zalogowany = zalogowany;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

}
