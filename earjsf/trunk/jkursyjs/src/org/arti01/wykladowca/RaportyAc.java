package org.arti01.wykladowca;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjakoment;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.utility.Login;

@ManagedBean(name="raportyAc")
@SessionScoped
public class RaportyAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(RaportyAc.class);
	@ManagedProperty(value="#{login}") private Login login;
	private Lekcjakoment lekcjaKoment;
	private Lekcjakoment komentOdp=new Lekcjakoment();
	@EJB LekacjaImp li;

	public String raport1(){		
		return "raport1";
	}
	
	public String raport2(){		
		return "raport2";
	}
	
	public String komentOdpowiedz(){
		komentOdp=new Lekcjakoment();
		return "komentOdpowiedz";
	}
	
	public String komentOdpowiedzZrob(){
		komentOdp.setUser(login.getZalogowany());
		komentOdp.setDatadodania(new Date());
		komentOdp.setDowykladowcy(false);
		komentOdp.setLekcja(lekcjaKoment.getLekcja());
		komentOdp.setTresc("to jest odpowiedz na:=============\""+lekcjaKoment.getTresc()+"\"================"+komentOdp.getTresc());
		Lekcja l=komentOdp.getLekcja();
		//l.getLekcjakoments().remove(lekcjaKoment);
		lekcjaKoment.setDowykladowcy(false);
		l.getLekcjakoments().remove(lekcjaKoment);
		l.getLekcjakoments().add(lekcjaKoment);
		l.getLekcjakoments().add(komentOdp);
		li.update(l);
		return "raport2";
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Lekcjakoment getLekcjaKoment() {
		return lekcjaKoment;
	}

	public void setLekcjaKoment(Lekcjakoment lekcjaKoment) {
		this.lekcjaKoment = lekcjaKoment;
	}

	public Lekcjakoment getKomentOdp() {
		return komentOdp;
	}

	public void setKomentOdp(Lekcjakoment komentOdp) {
		this.komentOdp = komentOdp;
	}
	
}