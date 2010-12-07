package org.arti01.admin;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.sesBean.KursyImp;

public class KursyAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	
	private DataModel<Kursy> allKursy=new ListDataModel<Kursy>();
	private Kursy kurs;
	private String errorText;
	@EJB KursyImp kursyImp;

	
	public String kursyLista() throws Exception{
		return "kursyLista";
	}
	
	public String form(){
		errorText="";
		kurs=allKursy.getRowData();
		return "kursyForm";
	}
	public String formNew(){
		errorText="";
		kurs=new Kursy();
		return "kursyForm";
	}
	
	
	public String usun() throws Exception {
		kurs=allKursy.getRowData();
		kursyImp.delete(kurs);
		return "statyczneLista";
	}
	
	public String dodaj() {
		//logger.info(kurs.getDataod().toString()+new Date()+(kurs.getDataod().before(new Date()))+"bieżąca");
		//logger.info(kurs.getDataod().toString()+new Date()+(kurs.getDataod().after(new Date()))+"bieżąca");
		logger.info(kursyImp.valid(kurs));
		if(!kursyImp.valid(kurs)) {
			logger.info("zle");
			errorText="Daty bez sensu ('data do' < 'data od' lub 'data do' < bieżącej)";
			return "kursyForm";
		}
		logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja"+kursyImp.valid(kurs));
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

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}