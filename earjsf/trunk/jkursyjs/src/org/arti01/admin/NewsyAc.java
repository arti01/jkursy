package org.arti01.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Newsy;
import org.arti01.entit.Role;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.NewsyImp;
import org.arti01.sesBean.RoleImp;

@ManagedBean(name="adminNewsyAc")
@SessionScoped
public class NewsyAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(NewsyAc.class);
	
	private DataModel<Newsy> allNewsy=new ListDataModel<Newsy>();
	private Newsy news;
	@EJB NewsyImp newsyImp;
	@EJB RoleImp roleImp;
	private List<String> rolesName=new ArrayList<String>();
	private String rola;
	
	public String form(){
		news=allNewsy.getRowData();
		return "newsyForm";
	}
	
	public String formNew(){
		news=new Newsy();
		return "newsyForm";
	}
	
	public String listNewsy(){
		//allStatyczne.setWrappedData(statImp.getFindAll());
		return "newsyLista";
	}
	
	
	public String dodaj() throws Exception {
		news.setDatadodania(new Date());
		if (news.getIdnewsy()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			newsyImp.update(news);
		}
		else{
			newsyImp.insert(news);			
		}
		return "newsyLista";
	}
	
	public String usun() throws Exception {
		news=allNewsy.getRowData();
		newsyImp.delete(news);
		return "newsyLista";
	}

	public RoleImp getRoleImp() {
		return roleImp;
	}

	public void setRoleImp(RoleImp roleImp) {
		this.roleImp = roleImp;
	}

	public List<String> getRolesName() {
		rolesName.clear();
		rolesName.add("wszyscy");
		rolesName.addAll(roleImp.getRolesName());
		return rolesName;
	}

	public void setRolesName(List<String> rolesName) {
		this.rolesName = rolesName;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public DataModel<Newsy> getAllNewsy() {
		allNewsy.setWrappedData(newsyImp.getAll());
		return allNewsy;
	}

	public void setAllNewsy(DataModel<Newsy> allNewsy) {
		this.allNewsy = allNewsy;
	}

	public NewsyImp getNewsyImp() {
		return newsyImp;
	}

	public void setNewsyImp(NewsyImp newsyImp) {
		this.newsyImp = newsyImp;
	}

	public Newsy getNews() {
		return news;
	}

	public void setNews(Newsy news) {
		this.news = news;
	}
}