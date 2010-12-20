package org.arti01.wykladowca;

import java.util.Collections;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.entit.User;
import org.arti01.sesBean.LekacjaImp;

public class KursyAc {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private User zalogowany;
	private Kursy kurs;
	private Lekcja lekcja;
	@EJB LekacjaImp lekcjaImp;
	
	
	public String kursForm(){
		return "kursyForm";
	}
	
	public String lekcjaForm(){
		logger.info("lekcja");
		return "lekcjaForm";
	}
	
	public String lekcjaDodaj(){
		if (lekcja.getIdlekcja()!=null) {// edycja
			lekcja.setKursy(kurs);
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			lekcjaImp.update(lekcja);
		}
		else{
			if(lekcjaImp.getLpAll().size()==0) lekcja.setLp(1);
			else lekcja.setLp(Collections.max(lekcjaImp.getLpAll())+1);
			lekcjaImp.insert(lekcja);			
		}
		return "lekcjaForm";
	}
	
	public User getZalogowany() {
		return zalogowany;
	}
	public void setZalogowany(User zalogowany) {
		this.zalogowany = zalogowany;
	}
	public Kursy getKurs() {
		return kurs;
	}
	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}
	
}