package org.arti01.admin;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.KursyImp;

public class KursyAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	
	private DataModel<Kursy> allKursy=new ListDataModel<Kursy>();
	private Kursy kurs;
	@EJB KursyImp kursyImp;

	
	public String kursyLista() throws Exception{
		return "kursyLista";
	}
	
	public String form(){
		kurs=allKursy.getRowData();
		return "kursyForm";
	}
	public String formNew(){
		kurs=new Kursy();
		return "kursyForm";
	}
	
	
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
	
	public String dodaj() {
		logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
		if (kurs.getIdkursy()!=null) {// edycja
			kursyImp.update(kurs);
		}
		else{
			//logger.info("doooooooodawanie"+statImp.getLpAll().size());
			kursyImp.insert(kurs);
		}
		return "kursyLista";
	}



	public DataModel<Kursy> getAllKursy() {
		allKursy.setWrappedData(kursyImp.findAll());
		return allKursy;
	}



	public void setAllKursy(DataModel<Kursy> allKursy) {
		this.allKursy = allKursy;
	}

	public Kursy getKurs(){
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

}