package org.arti01.admin;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.Rozne;

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
		kurs=allKursy.getRowData();
		kursyImp.delete(kurs);
		return "statyczneLista";
	}
	
	public String dodaj() {
		if (kursyImp.valid(kurs)) {
			logger.info("zle");
			
			logger.info(new UsersAc().getRozne().getInfoText());	
			new UsersAc().getRozne().setErrorText("ssssssssssssss");
			return "indexAdm";
		}
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