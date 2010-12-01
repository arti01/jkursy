package org.arti01.all;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Statyczne;
import org.arti01.entit.User;
import org.arti01.sesBean.Rozne;
import org.arti01.sesBean.StatyczneImp;


public class IndexAc {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	@EJB StatyczneImp statyczneImp;
	@EJB Rozne rozne;
	User user;
	Statyczne strona;
	private ListDataModel<Statyczne> statyczneModel=new ListDataModel<Statyczne>() ;
	
	public String execute() throws Exception{
		return "SUCCESS";
	}
	
	public String statyczna() throws Exception{
		logger.info(this.getStatyczneModel().getRowCount()+"count");
		logger.info(this.getStatyczneModel().getRowIndex()+"indeks");
		logger.info(this.getStatyczneModel().isRowAvailable()+"dostepny");
		logger.info(this.getStatyczneModel().getWrappedData().getClass()+"clasa");
		logger.info(this.getStatyczneModel().getRowData().getTytul());
		//strona=statyczneImp.find(this.getStatyczneModel().getRowData());
		strona=this.getStatyczneModel().getRowData();
		logger.info(strona.getTytul());
		return "index.statyczne";
	}
	public String zalozKonto() throws Exception{
		user= new User();
		user.setDataZmiany(new Date());
		//logger.info(this.getZalogowany());
		return "zalozKonto";
	}
	/*public String dodajKonto() throws Exception{
		UsersAc ua=new UsersAc();
		ua.setUser(user);
		ua.dodaj();
		setInfoText(ua.getInfoText()+" teraz czekaj az dostaniesz maila");
		//UserAc=new UsersAc().dodaj();
		//logger.info(this.getZalogowany());
		return "info";
	}
	
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		setLogin(null);
		return "logout";
	}*/

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Statyczne getStrona() {
		return strona;
	}

	public void setStrona(Statyczne strona) {
		this.strona = strona;
	}

	public ListDataModel<Statyczne> getStatyczneModel() {
		if(statyczneModel.getRowIndex()==-1) statyczneModel.setWrappedData(rozne.getStatyczneAll());
		return statyczneModel;
	}

	public void setStatyczneModel(ListDataModel<Statyczne> statyczneModel) {
		this.statyczneModel = statyczneModel;
	}

}