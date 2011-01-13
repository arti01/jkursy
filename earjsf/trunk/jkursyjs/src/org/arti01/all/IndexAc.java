package org.arti01.all;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Statyczne;
import org.arti01.entit.User;
import org.arti01.sesBean.StatyczneImp;

@ManagedBean(name="indexAc")
@SessionScoped
public class IndexAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);
	@EJB StatyczneImp statyczneImp;
	User user;
	Statyczne strona;
	boolean newsPokaz=false;
	private ListDataModel<Statyczne> statyczneModel=new ListDataModel<Statyczne>() ;
	
	public String execute() throws Exception{
		return "SUCCESS";
	}
	
	public String index() throws Exception{
		return "index.xhtml?faces-redirect=true";
	}
	
	public String newsWiecej() throws Exception{
		logger.info("dddddddddd");
		if(newsPokaz)newsPokaz=false;
		else newsPokaz=true;
		return null;
	}
	
	public String statyczna() throws Exception{
		/*logger.info(this.getStatyczneModel().getRowCount()+"count");
		logger.info(this.getStatyczneModel().getRowIndex()+"indeks");
		logger.info(this.getStatyczneModel().isRowAvailable()+"dostepny");
		logger.info(this.getStatyczneModel().getWrappedData().getClass()+"clasa");
		logger.info(this.getStatyczneModel().getRowData().getTytul());
		strona=statyczneImp.find(this.getStatyczneModel().getRowData());*/
		strona=this.getStatyczneModel().getRowData();
		//logger.info(strona.getTytul());
		return "statyczneDetale";
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
		if(statyczneModel.getRowIndex()==-1) statyczneModel.setWrappedData(statyczneImp.getFindForAll());
		return statyczneModel;
	}

	public void setStatyczneModel(ListDataModel<Statyczne> statyczneModel) {
		this.statyczneModel = statyczneModel;
	}

	public boolean isNewsPokaz() {
		return newsPokaz;
	}

	public void setNewsPokaz(boolean newsPokaz) {
		this.newsPokaz = newsPokaz;
	}

}