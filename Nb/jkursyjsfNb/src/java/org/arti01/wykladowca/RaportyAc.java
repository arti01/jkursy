package org.arti01.wykladowca;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Fotykursantkoment;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Lekcjakoment;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.LekcjafotykursantImp;
import org.arti01.utility.Login;

@ManagedBean(name="raportyAc")
@SessionScoped
public class RaportyAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(RaportyAc.class);
	@ManagedProperty(value="#{login}") private Login login;
	private Lekcjakoment lekcjaKoment;
	private Lekcjakoment komentOdp=new Lekcjakoment();
	private Fotykursantkoment fotaKoment=new Fotykursantkoment();
	private Fotykursantkoment komentFotyOdp=new Fotykursantkoment(); 
	@EJB LekacjaImp li;
	@EJB LekcjafotykursantImp lfki;

	public String raport1(){		
		return "raport1";
	}
	
	public String raport2(){		
		return "raport2";
	}
	
	public String raport3(){		
		return "raport3";
	}
	
	public String komentOdpowiedz(){
		komentOdp=new Lekcjakoment();
		return "komentOdpowiedz";
	}
	
	public String komentFotyOdpowiedz(){
		komentFotyOdp=new Fotykursantkoment();
		return "komentFotyOdpowiedz";
	}
	
	public String komentOdpowiedzZrob(){
		komentOdp.setUser(login.getZalogowany());
		komentOdp.setDatadodania(new Date());
		komentOdp.setDowykladowcy(false);
		komentOdp.setLekcja(lekcjaKoment.getLekcja());
		komentOdp.setTresc("to jest odpowiedz na:<p style=\"font-style: italic;\">\""+lekcjaKoment.getTresc()+"\"</p>"+komentOdp.getTresc());
		Lekcja l=komentOdp.getLekcja();
		//l.getLekcjakoments().remove(lekcjaKoment);
		lekcjaKoment.setDowykladowcy(false);
		l.getLekcjakoments().remove(lekcjaKoment);
		l.getLekcjakoments().add(lekcjaKoment);
		l.getLekcjakoments().add(komentOdp);
		li.update(l);
		return "raport2";
	}
	
	public String komentFotyOdpowiedzZrob(){
		komentFotyOdp.setUser(login.getZalogowany());
		komentFotyOdp.setDatadodania(new Date());
		komentFotyOdp.setDowykladowcy(false);
		komentFotyOdp.setLekcjafotykursant(fotaKoment.getLekcjafotykursant());
		komentFotyOdp.setTresc("to jest odpowiedz na: =\""+fotaKoment.getTresc()+"\"= "+komentFotyOdp.getTresc());
		Lekcjafotykursant lfk=komentFotyOdp.getLekcjafotykursant();
		fotaKoment.setDowykladowcy(false);
		lfk.getFotykursantkoment().remove(fotaKoment);
		lfk.getFotykursantkoment().add(0,fotaKoment);
		lfk.getFotykursantkoment().add(0,komentFotyOdp);
		lfki.update(lfk);
		return "raport3";
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

	public Fotykursantkoment getFotaKoment() {
		return fotaKoment;
	}

	public void setFotaKoment(Fotykursantkoment fotaKoment) {
		this.fotaKoment = fotaKoment;
	}

	public Fotykursantkoment getKomentFotyOdp() {
		return komentFotyOdp;
	}

	public void setKomentFotyOdp(Fotykursantkoment komentFotyOdp) {
		this.komentFotyOdp = komentFotyOdp;
	}
	
}