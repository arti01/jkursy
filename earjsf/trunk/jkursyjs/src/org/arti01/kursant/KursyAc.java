package org.arti01.kursant;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Newsykursy;
import org.arti01.sesBean.KursyImp;

@ManagedBean(name="kursantKursyAc")
@SessionScoped
public class KursyAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);

	private Kursy kurs;
	private DataModel<Lekcja> lekcje = new ListDataModel<Lekcja>();
	@EJB KursyImp kursyImp;
	private Newsykursy news;

	
	public String lekcjaLista(){
		kurs=kursyImp.find(kurs);
		lekcje.setWrappedData(kurs.getLekcjeWidoczne());		
		return "lekcjaLista";
	}
	
	public String newsWiecej(){
		return "news";
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

	public Newsykursy getNews() {
		return news;
	}

	public void setNews(Newsykursy news) {
		this.news = news;
	}

}