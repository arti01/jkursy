package org.arti01.kursant;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.all.IndexAc;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.sesBean.KursyImp;

@ManagedBean(name="kursantKursyAc")
@SessionScoped
public class KursyAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);

	Kursy kurs;
	private DataModel<Lekcja> lekcje = new ListDataModel<Lekcja>();
	@EJB KursyImp kursyImp;
	@ManagedProperty(value="#{indexAc}") private IndexAc iac;
	
	public String lekcjaLista(){
		iac.setNewsPokaz(false);
		kurs=kursyImp.find(kurs);
		lekcje.setWrappedData(new ArrayList<Lekcja>(kurs.getLekcjas()));
		return "lekcjaLista";
	}

	public Kursy getKursy() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public DataModel<Lekcja> getLekcje() {
		return lekcje;
	}

	public void setLekcje(DataModel<Lekcja> lekcje) {
		this.lekcje = lekcje;
	}

	public KursyImp getKursyImp() {
		return kursyImp;
	}

	public void setKursyImp(KursyImp kursyImp) {
		this.kursyImp = kursyImp;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public IndexAc getIac() {
		return iac;
	}

	public void setIac(IndexAc iac) {
		this.iac = iac;
	}
	

}