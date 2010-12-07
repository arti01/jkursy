package org.arti01.admin;

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
		if (kurs.getIdkursy()!=null) {// edycja
			if(kursyImp.update(kurs)){
				return "kursyLista";
			}
		}
		else{
			if(kursyImp.insert(kurs)){
				return "kursyLista";
			}
		}
		errorText=kursyImp.getErrorText();
		return "kursyForm";//bo nie udala się zmiana
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