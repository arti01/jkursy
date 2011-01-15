package org.arti01.wykladowca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Newsykursy;
import org.arti01.sesBean.NewsykursyImp;

@ManagedBean(name="wykladNewsyAc")
@SessionScoped
public class NewsyAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(NewsyAc.class);
	
	private DataModel<Newsykursy> allNewsy=new ListDataModel<Newsykursy>();
	private Newsykursy news;
	@EJB NewsykursyImp newsykursyImp;
	private Kursy kurs;
	
	public String form(){
		news=allNewsy.getRowData();
		return "newsyForm";
	}
	
	public String formNew(){
		news=new Newsykursy();
		return "newsyForm";
	}
	
	public String listNewsy(){
		//allStatyczne.setWrappedData(statImp.getFindAll());
		return "newsyLista";
	}
	
	
	public String dodaj() throws Exception {
		news.setDatadodania(new Date());
		news.setKursy(kurs);
		if (news.getIdnewsykursy()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			newsykursyImp.update(news);
		}
		else{
			newsykursyImp.insert(news);			
		}
		return "kursyForm";
	}
	
	public String usun() throws Exception {
		news=allNewsy.getRowData();
		newsykursyImp.delete(news);
		return null;
	}

	public DataModel<Newsykursy> getAllNewsy() {
		allNewsy.setWrappedData(new ArrayList<Newsykursy>(newsykursyImp.getAll(kurs)));
		return allNewsy;
	}

	public void setAllNewsy(DataModel<Newsykursy> allNewsy) {
		this.allNewsy = allNewsy;
	}

	public Newsykursy getNews() {
		return news;
	}

	public void setNews(Newsykursy news) {
		this.news = news;
	}

	public NewsykursyImp getNewsykursyImp() {
		return newsykursyImp;
	}

	public void setNewsykursyImp(NewsykursyImp newsykursyImp) {
		this.newsykursyImp = newsykursyImp;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}


}