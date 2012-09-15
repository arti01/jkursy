package org.arti01.sesBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class UserData
 */
@Stateful
@LocalBean
public class UserData {

	String errorText;
	
    public UserData() {
        // TODO Auto-generated constructor stub
    }

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
