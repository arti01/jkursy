package org.arti01.admin;

import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.arti01.abstrakt.Akcja;
import org.arti01.obiekty.Kurs;
import org.arti01.obiekty.KursImp;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class KursyAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	
	private List<Kurs> kursy;
	private Kurs kurs;
	private boolean zmien;

	@SkipValidation
	public String listKursy() throws Exception{
		kursy= new KursImp().findAll();
		return "list";
	}
	

	@SkipValidation
	public String form() throws Exception{
		if (kurs != null) zmien = true;
		kurs= new KursImp().load(kurs);
		return "form";
	}
	
	@SkipValidation
	public String usun() throws Exception {
		try {
			new KursImp().remove(kurs);
			setInfoText("kurs.usuniety");
		} catch (Exception e) {
			logger.info("ssssssss", e);
			addActionError("nie uda�o si�");
			return "info";
		}
		return "info";
	}
	
	public String dodaj() throws Exception {
		logger.info("tutaj");
		if (!zmien) {// dodawanie
			try {
				new KursImp().insert(kurs);
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
				Kurs kursNew = new KursImp().load(kurs);
				kursNew.setNazwa(kurs.getNazwa());
				logger.info("eee"+kurs.getTresc()+"eeeeeeee");
				kursNew.setTresc(kurs.getTresc());
				kursNew.setOpisKrotki(kurs.getOpisKrotki());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				kursNew.setDataOd(sdf.format(kurs.getDataOd()));
				kursNew.setDataDo(sdf.format(kurs.getDataDo()));
				new KursImp().update(kursNew);
				setInfoText("kurs.zmieniony");
			} catch (Exception e) {
				addActionError("problem z edycja danych kursu");
				logger.info("ssssssss", e);
				return "info";
			}			
		}
		return "info";
	}

	public List<Kurs> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kurs> kursy) {
		this.kursy = kursy;
	}

	public Kurs getKurs() {
		return kurs;
	}
	
	@VisitorFieldValidator(message = "błąd: ", key = "blad.kursu")
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}

	public boolean isZmien() {
		return zmien;
	}

	public void setZmien(boolean zmien) {
		this.zmien = zmien;
	}

}