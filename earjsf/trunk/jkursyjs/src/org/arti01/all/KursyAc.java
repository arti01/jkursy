package org.arti01.all;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;


public class KursyAc {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private List<Kursy>kursy;
	private ListDataModel<Kursy> allKursy = new ListDataModel<Kursy>();
	private User wykladowca;
	private Kursy kurs;
	@EJB KursyImp kursyImp;
	
	public KursyAc() {
		kurs=new Kursy();
    }
	
	public String list() throws Exception{
		//logger.info("jestemmmm");
		kursy=kursyImp.findAll();
		allKursy.setWrappedData(kursyImp.findAll());
		return "kursyLista";
	}
	
	public String detale() throws Exception{
		kurs=allKursy.getRowData();
		return "kursyDetale";
	}
	
	public String wykladDetale() throws Exception{
		//wykladowca=wykladowcy.getRowData();
		//wykladowca=allKursy.getRowData().getWykladowcy()
		return "wykladowcaDetale";
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

	public User getWykladowca() {
		return wykladowca;
	}

	public void setWykladowca(User wykladowca) {
		this.wykladowca = wykladowca;
	}


	public ListDataModel<Kursy> getAllKursy() {
		return allKursy;
	}

	public void setAllKursy(ListDataModel<Kursy> allKursy) {
		this.allKursy = allKursy;
	}
	
}