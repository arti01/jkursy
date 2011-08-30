package org.arti.admin;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti.jpa.Auto;
import org.arti.jpa.AutoImp;
import org.arti.jpa.Marka;
import org.arti.jpa.MarkaImp;

@ManagedBean(name="adminAutaAc")
@SessionScoped
public class AutaAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger l = Logger.getLogger(AutaAc.class);
	DataModel<Auto> autaAll=new ListDataModel<Auto>();
	DataModel<Marka> markaAll=new ListDataModel<Marka>();
	@EJB AutoImp aimp;
	@EJB MarkaImp mimp;
	Auto auto;
	
	public String form(){
		auto=autaAll.getRowData();
		l.info(auto.getMarka().getIdmarka());
		return "autoForm";
	}
	
	public String formEmpty(){
		auto=new Auto();
		Marka marka=new Marka();
		auto.setMarka(marka);
		return "autoForm";
	}
	
	public String zapisz(){
		l.info(auto.getMarka().getIdmarka());
		auto.setMarka(mimp.find(auto.getMarka()));
		if(auto.getIdauto()!=null) aimp.update(auto);
		else aimp.insert(auto);
		return "autaLista";
	}
	
	public AutoImp getAimp() {
		return aimp;
	}
	public void setAimp(AutoImp aimp) {
		this.aimp = aimp;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public DataModel<Auto> getAutaAll() {
		autaAll.setWrappedData(aimp.getAll());
		return autaAll;
	}

	public void setAutaAll(DataModel<Auto> autaAll) {
		this.autaAll = autaAll;
	}

	public DataModel<Marka> getMarkaAll() {
		markaAll.setWrappedData(mimp.getAll());
		return markaAll;
	}

	public void setMarkaAll(DataModel<Marka> markaAll) {
		this.markaAll = markaAll;
	}

	public MarkaImp getMimp() {
		return mimp;
	}

	public void setMimp(MarkaImp mimp) {
		this.mimp = mimp;
	}
		
}