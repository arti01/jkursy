package org.arti01.admin;

import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.arti01.abstrakt.Akcja;
import org.arti01.entit.Kursy;
import org.arti01.sesBean.KursyImp;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class KursyAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	
	private List<Kursy> kursy;
	private Kursy kurs;
	private boolean zmien;

	@SkipValidation
	public String listKursy() throws Exception{
		kursy= new KursyImp().findAll();
		return "list";
	}
	

	@SkipValidation
	public String form() throws Exception{
		if (kurs != null) zmien = true;
		kurs= new KursyImp().load(kurs);
		return "form";
	}
	
	@SkipValidation
	public String usun() throws Exception {
		try {
			new KursyImp().remove(kurs);
			setInfoText("kurs.usuniety");
		} catch (Exception e) {
			logger.info("ssssssss", e);
			addActionError("nie uda�o si�");
			return "info";
		}
		return "info";
	}
	
	public String dodaj() throws Exception {
		if (!zmien) {// dodawanie
			try {
				new KursyImp().insert(kurs);
				setInfoText("kurs.dodany");
			} catch (Exception e) {
				logger.info("ssssssss", e);
				addActionError("nie uda�o si� stworzy� kursu, sprawdz, czy juz taki nie istnieje");
				return "info";
			}
		}
		else{
			try {
				logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
				Kursy kursNew = new KursyImp().load(kurs);
				kursNew.setNazwa(kurs.getNazwa());
				logger.info("eee"+kurs.getOpis());
				kursNew.setOpis(kurs.getOpis());
				kursNew.setOpisKrotki(kurs.getOpisKrotki());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				//kursNew.setDataod(sdf.format(kurs.getDataod()));
				//kursNew.setDatado(sdf.format(kurs.getDatado()));
				new KursyImp().update(kursNew);
				setInfoText("kurs.zmieniony");
			} catch (Exception e) {
				addActionError("problem z edycja danych kursu");
				logger.info("ssssssss", e);
				return "info";
			}			
		}
		return "info";
	}

	public List<Kursy> getKursyy() {
		return kursy;
	}

	public void setKursyy(List<Kursy> kursy) {
		this.kursy = kursy;
	}

	public Kursy getKursy() {
		return kurs;
	}
	
	@VisitorFieldValidator(message = "błąd: ", key = "blad.kursu")
	public void setKursy(Kursy kurs) {
		this.kurs = kurs;
	}

	public boolean isZmien() {
		return zmien;
	}

	public void setZmien(boolean zmien) {
		this.zmien = zmien;
	}

}