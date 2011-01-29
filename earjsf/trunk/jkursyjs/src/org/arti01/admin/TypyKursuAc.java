package org.arti01.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Typykursu;
import org.arti01.sesBean.TypyKursuImp;

@ManagedBean(name="adminTypyKursuAc")
@SessionScoped
public class TypyKursuAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TypyKursuAc.class);
	
	private DataModel<Typykursu> all = new ListDataModel<Typykursu>();
	private Typykursu tk;
	@EJB TypyKursuImp tkImp;
	private String infoText;
	private String infoTextUsun;

	public String list(){
		tk=new Typykursu();
		all.setWrappedData(tkImp.getAll());
		infoText="";
		return "tkForm";
	}
	
	public void edytujForm(){
		tk=all.getRowData();
		all.setWrappedData(tkImp.getAll());
		infoText="";
	}
	
	
	public void dodaj() {
		if(tk.getIdtypykursu()==null){
			//logger.info("dodanie--"+tk.getNazwa()+"--"+tk.getOpis());
			tkImp.insert(tk);
		}
		else {
			//logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			tkImp.update(tk); 
		}
		tk=new Typykursu();
		all.setWrappedData(tkImp.getAll());
		infoText="";
		infoTextUsun="";
	}
	
	public void zmien() {
		tk=all.getRowData();
		//logger.info("zmien"+tk.getNazwa()+tk.getOpis());
		infoText="";
		infoTextUsun="";
		all.setWrappedData(tkImp.getAll());
	}
	
	public String usun() {
		infoText="";
		infoTextUsun="";
		try {
			tkImp.delete(all.getRowData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("usunięcie nieudane");
			infoTextUsun="usunięcie nieudane - istnieją kursy z tym typem";
		}
		all.setWrappedData(tkImp.getAll());
		tk=new Typykursu();
		return null;
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


	public DataModel<Typykursu> getAll() {
		return all;
	}


	public void setAll(DataModel<Typykursu> all) {
		this.all = all;
	}


	public Typykursu getTk() {
		return tk;
	}


	public void setTk(Typykursu tk) {
		this.tk = tk;
	}


}