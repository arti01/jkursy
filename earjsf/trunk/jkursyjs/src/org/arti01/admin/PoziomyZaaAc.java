package org.arti01.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Poziomyzaawansowania;
import org.arti01.sesBean.PoziomyZaawansowaniaImp;

@ManagedBean(name="adminPoziomyZaaAc")
@SessionScoped
public class PoziomyZaaAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PoziomyZaaAc.class);
	
	private DataModel<Poziomyzaawansowania> all = new ListDataModel<Poziomyzaawansowania>();
	private Poziomyzaawansowania pz;
	@EJB PoziomyZaawansowaniaImp pzImp;
	private String infoText;
	
	public String list(){
		pz=new Poziomyzaawansowania();
		all.setWrappedData(pzImp.getAll());
		return "pzForm";
	}
	
	
	public String dodaj() {
		logger.info(pz.getNazwa());
		if (pz.getIdpoziomyzaawansowania()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			pzImp.update(pz);
		}
		else{
			pzImp.insert(pz);			
		}
		pz=new Poziomyzaawansowania();
		all.setWrappedData(pzImp.getAll());
		return null;
	}
	
	public void zmien() {
		pz=all.getRowData();
		//logger.info(pz.getNazwa());
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			pzImp.update(pz);
			infoText="zmiana wykonana";
		all.setWrappedData(pzImp.getAll());
	}
	
	public String usun() {
		//logger.info(pz.getNazwa()+"usun");
		pzImp.delete(all.getRowData());
		all.setWrappedData(pzImp.getAll());
		logger.info(all.getRowCount());
		return null;
	}

	public Poziomyzaawansowania getPz() {
		return pz;
	}

	public void setPz(Poziomyzaawansowania pz) {
		this.pz = pz;
	}

	public DataModel<Poziomyzaawansowania> getAll() {
		return all;
	}


	public void setAll(DataModel<Poziomyzaawansowania> all) {
		this.all = all;
	}


	public String getInfoText() {
		return infoText;
	}


	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
}