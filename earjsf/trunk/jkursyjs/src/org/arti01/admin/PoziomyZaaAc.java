package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.log4j.Logger;
import org.arti01.entit.Poziomyzaawansowania;
import org.arti01.sesBean.PoziomyZaawansowaniaImp;

@ManagedBean(name="adminPoziomyZaaAc")
@SessionScoped
public class PoziomyZaaAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PoziomyZaaAc.class);
	
	private ArrayList<Poziomyzaawansowania> all=new ArrayList<Poziomyzaawansowania>();
	private Poziomyzaawansowania pz;
	@EJB PoziomyZaawansowaniaImp pzImp;
	
	public String list(){
		pz=new Poziomyzaawansowania();
		all=new ArrayList<Poziomyzaawansowania>(pzImp.getAll());
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
		all=new ArrayList<Poziomyzaawansowania>(pzImp.getAll());
		return null;
	}
	
	public String usun() throws Exception {
		pzImp.delete(pz);
		all=new ArrayList<Poziomyzaawansowania>(pzImp.getAll());
		return null;
	}

	public Poziomyzaawansowania getPz() {
		return pz;
	}

	public void setPz(Poziomyzaawansowania pz) {
		this.pz = pz;
	}


	public ArrayList<Poziomyzaawansowania> getAll() {
		return all;
	}


	public void setAll(ArrayList<Poziomyzaawansowania> all) {
		this.all = all;
	}


}