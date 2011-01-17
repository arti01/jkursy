package org.arti01.kursant;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.sesBean.LekacjaImp;

@ManagedBean(name="kursantLekcjaAc")
@SessionScoped
public class LekcjaAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LekcjaAc.class);

	private Lekcja lekcja;
	@EJB LekacjaImp LekacjaImp;
	private boolean exifPokaz;
	
	public String pokaz(){
		logger.info(exifPokaz);
		exifPokaz=false;
		lekcja=LekacjaImp.find(lekcja);
		return "lekcja?faces-redirect=true";
	}
	public String exifPokazZmien(){
		if(exifPokaz) exifPokaz=false;
		else exifPokaz=true;
		logger.info(exifPokaz);
		return null;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public LekacjaImp getLekacjaImp() {
		return LekacjaImp;
	}

	public void setLekacjaImp(LekacjaImp lekacjaImp) {
		LekacjaImp = lekacjaImp;
	}

	public boolean isExifPokaz() {
		return exifPokaz;
	}

	public void setExifPokaz(boolean exifPokaz) {
		this.exifPokaz = exifPokaz;
	}

}