package org.arti01.all;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.sesBean.KursyImp;


public class KursyAc {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private List<Kursy>kursy;
	private ListDataModel<Kursy> allKursy = new ListDataModel<Kursy>();
	private Kursy kurs;
	@EJB KursyImp kursyImp;
	
	public KursyAc() {
		kurs=new Kursy();
    }
	
	public String list() throws Exception{
		//logger.info("jestemmmm");
		kursy=kursyImp.findAll();
		allKursy.setWrappedData(kursyImp.findAll());
		return "kursy.list";
	}
	
	public String detale() throws Exception{
		//kurs=kursyImp.find(model.getRowData());
		kurs=allKursy.getRowData();
		//logger.info(kurs.getNazwa()+"dddddddd"+model.getWrappedData().getClass()+"clasa");
		return "kursy.detale";
	}

	public List<Kursy> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kursy> kursyAll) {
		this.kursy = kursyAll;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public ListDataModel<Kursy> getAllKursy() {
		return allKursy;
	}

	public void setAllKursy(ListDataModel<Kursy> allKursy) {
		this.allKursy = allKursy;
	}
	
}