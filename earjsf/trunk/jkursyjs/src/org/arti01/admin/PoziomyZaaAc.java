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
	private String infoTextUsun;

	public String list(){
		pz=new Poziomyzaawansowania();
		all.setWrappedData(pzImp.getAll());
		infoText="";
		return "pzForm";
	}
	
	
	public String dodaj() {
		pzImp.insert(pz);			
		pz=new Poziomyzaawansowania();
		all.setWrappedData(pzImp.getAll());
		infoText="";
		infoTextUsun="";
		return null;
	}
	
	public void zmien() {
		pz=all.getRowData();
		infoTextUsun="";
		//logger.info(pz.getNazwa());
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			pzImp.update(pz);
			infoText="zmiana wykonana";
		all.setWrappedData(pzImp.getAll());
	}
	
	public String usun() {
		//logger.info(pz.getNazwa()+"usun");
		infoText="";
		infoTextUsun="";
		try {
			pzImp.delete(all.getRowData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("usunięcie nieudane");
			infoTextUsun="usunięcie nieudane - istnieją kursy z tym poziomem";
		}
		all.setWrappedData(pzImp.getAll());
		//logger.info(all.getRowCount());
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
	
	public String getInfoTextUsun() {
		return infoTextUsun;
	}

	public void setInfoTextUsun(String infoTextUsun) {
		this.infoTextUsun = infoTextUsun;
	}


}