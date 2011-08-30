package org.arti.all;

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
import org.richfaces.component.SortOrder;


@ManagedBean(name="autaAc")
@SessionScoped
public class AutaAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger l = Logger.getLogger(AutaAc.class);
	@EJB AutoImp aimp;
	@EJB MarkaImp mimp;
	DataModel<Auto> autaAll=new ListDataModel<Auto>();
	DataModel<Marka> markaAll=new ListDataModel<Marka>();
	Auto auto;
	private SortOrder typOrder = SortOrder.unsorted;
	private SortOrder cenaOrder = SortOrder.descending;
	private SortOrder markaOrder = SortOrder.unsorted;
	private String markaFiltr="";

	public String list(){
		l.info(autaAll.getRowCount());
		return "autaLista";
	}
	
	public void pokazAuto(){
		auto=autaAll.getRowData();
		l.info(auto.getOpis());
	}

	public void filtrBymarka() {
		markaFiltr=markaAll.getRowData().getMarka();
	}
	
	public void sortBymarka() {
		cenaOrder=SortOrder.unsorted;
		typOrder=SortOrder.ascending;
		if (markaOrder.equals(SortOrder.ascending)) {
			markaOrder=SortOrder.descending;
		} else {
			markaOrder=SortOrder.ascending;
		}
	}
	
	public void sortBytyp() {
		l.info(getTypOrder());
		cenaOrder=SortOrder.unsorted;
		markaOrder=SortOrder.unsorted;
		if (typOrder.equals(SortOrder.ascending)) {
			typOrder=SortOrder.descending;
		} else {
			typOrder=SortOrder.ascending;
		}
	}
	
	public void sortBycena() {
		typOrder=SortOrder.unsorted;
		markaOrder=SortOrder.unsorted;
		if (cenaOrder.equals(SortOrder.ascending)) {
			cenaOrder=SortOrder.descending;
		} else {
			cenaOrder=SortOrder.ascending;
		}
	}
	
	public DataModel<Auto> getAutaAll() {
		autaAll.setWrappedData(aimp.getAll());
		return autaAll;
	}

	public void setAutaAll(DataModel<Auto> autaAll) {
		this.autaAll = autaAll;
	}

	public SortOrder getTypOrder() {
		return typOrder;
	}

	public void setTypOrder(SortOrder typOrder) {
		this.typOrder = typOrder;
	}

	public SortOrder getCenaOrder() {
		return cenaOrder;
	}

	public void setCenaOrder(SortOrder cenaOrder) {
		this.cenaOrder = cenaOrder;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public SortOrder getMarkaOrder() {
		return markaOrder;
	}

	public void setMarkaOrder(SortOrder markaOrder) {
		this.markaOrder = markaOrder;
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

	public String getMarkaFiltr() {
		return markaFiltr;
	}

	public void setMarkaFiltr(String markaFiltr) {
		this.markaFiltr = markaFiltr;
	}
	
}