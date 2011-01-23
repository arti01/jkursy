package org.arti01.kursant;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjakoment;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.LekcjaKomentImp;
import org.arti01.utility.Login;

@ManagedBean(name="kursantLekcjaAc")
@SessionScoped
public class LekcjaAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LekcjaAc.class);

	private Lekcja lekcja;
	@EJB LekacjaImp lekcjaImp;
	private boolean exifPokaz;
	private Lekcjakoment komentarz=new Lekcjakoment();
	@EJB LekcjaKomentImp lekcjaKomentImp;
	@ManagedProperty(value="#{login}") private Login loginBean;
	
	public String pokaz(){
		exifPokaz=false;
		lekcja=lekcjaImp.find(lekcja);
		return "lekcja?faces-redirect=true";
	}
	public String exifPokazZmien(){
		if(exifPokaz) exifPokaz=false;
		else exifPokaz=true;
		//logger.info(exifPokaz);
		return null;
	}

	public String dodajKomentarz(){
		komentarz.setDatadodania(new Timestamp(new Date().getTime()));
		komentarz.setUser(loginBean.getZalogowany());
		komentarz.setLekcja(lekcja);
		lekcjaKomentImp.insert(komentarz);
		lekcja=lekcjaImp.find(lekcja);
		komentarz=new Lekcjakoment();
		return null;
	}
	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public boolean isExifPokaz() {
		return exifPokaz;
	}

	public void setExifPokaz(boolean exifPokaz) {
		this.exifPokaz = exifPokaz;
	}
	public void setKomentarz(Lekcjakoment komentarz) {
		this.komentarz = komentarz;
	}
	public Lekcjakoment getKomentarz() {
		return komentarz;
	}
	public Login getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}
	public LekcjaKomentImp getLekcjaKomentImp() {
		return lekcjaKomentImp;
	}
	public void setLekcjaKomentImp(LekcjaKomentImp lekcjaKomentImp) {
		this.lekcjaKomentImp = lekcjaKomentImp;
	}
	public LekacjaImp getLekcjaImp() {
		return lekcjaImp;
	}
	public void setLekcjaImp(LekacjaImp lekcjaImp) {
		this.lekcjaImp = lekcjaImp;
	}

}