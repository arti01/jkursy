package org.arti01.absolwent;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Absolwforposty;
import org.arti01.entit.Absolwforwatki;
import org.arti01.sesBean.AbsolwForumImp;
import org.arti01.utility.Login;

@ManagedBean(name="absolwForumAc")
@SessionScoped
public class ForumAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ForumAc.class);
	private Absolwforwatki watek=new Absolwforwatki();
	private Absolwforposty post=new Absolwforposty();
	@EJB AbsolwForumImp forumImp;
	@ManagedProperty(value="#{login}") private Login loginBean;
	private DataModel<Absolwforwatki> allWatki = new ListDataModel<Absolwforwatki>();
	private DataModel<Absolwforposty> allPosty = new ListDataModel<Absolwforposty>();
	
	public void dodajWatek(){
		watek.setDatadodania(new Date());
		watek.setUser(loginBean.getZalogowany());
		post.setDatadodania(new Date());
		post.setUser(loginBean.getZalogowany());
		watek.getAbsolwforposties().add(post);
		forumImp.insertWatek(watek);
		watek=new Absolwforwatki();
		post=new Absolwforposty();
	}
	
	public String watek(){
		logger.info("sdfsdfsdf");
		watek=allWatki.getRowData();
		allPosty.setWrappedData(watek.getAbsolwforposties());
		return "watek";
	}
	
	public void dodajPost(){
		post.setDatadodania(new Date());
		post.setUser(loginBean.getZalogowany());
		watek.getAbsolwforposties().add(post);
		forumImp.updateWatek(watek);
		post=new Absolwforposty();
		allPosty.setWrappedData(watek.getAbsolwforposties());
	}
	
	public String index(){
		return "index";
	}
	
	

	public Absolwforwatki getWatek() {
		return watek;
	}

	public void setWatek(Absolwforwatki watek) {
		this.watek = watek;
	}

	public Absolwforposty getPost() {
		return post;
	}

	public void setPost(Absolwforposty post) {
		this.post = post;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

	public DataModel<Absolwforwatki> getAllWatki() {
		allWatki.setWrappedData(forumImp.findAllWatki());
		return allWatki;
	}

	public void setAllWatki(DataModel<Absolwforwatki> allWatki) {
		this.allWatki = allWatki;
	}

	public DataModel<Absolwforposty> getAllPosty() {
		return allPosty;
	}

	public void setAllPosty(DataModel<Absolwforposty> allPosty) {
		this.allPosty = allPosty;
	}

}