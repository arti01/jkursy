package org.arti01.all;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Newsy;
import org.arti01.entit.Poziomyzaawansowania;
import org.arti01.entit.Statyczne;
import org.arti01.entit.Typykursu;
import org.arti01.entit.User;
import org.arti01.sesBean.PoziomyZaawansowaniaImp;
import org.arti01.sesBean.StatyczneImp;
import org.arti01.sesBean.TypyKursuImp;

@ManagedBean(name="indexAc")
@SessionScoped
public class IndexAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);

	User user;
	Statyczne strona;
	private Newsy news;
	
	private ListDataModel<Statyczne> statyczneModel=new ListDataModel<Statyczne>() ;
	@EJB StatyczneImp statyczneImp;
	
	private ListDataModel<Poziomyzaawansowania> pzModel=new ListDataModel<Poziomyzaawansowania>() ;
	@EJB PoziomyZaawansowaniaImp pzImp;
	private Poziomyzaawansowania pz;
	
	private ListDataModel<Typykursu> tkModel=new ListDataModel<Typykursu>() ;
	@EJB TypyKursuImp tkImp;
	private Typykursu tk;
	
	public String execute() throws Exception{
		return "SUCCESS";
	}
	
	public String index(){
		return "index.xhtml?faces-redirect=false";
	}
	
	public String newsWiecej(){
		//logger.info(news);
		return "news";
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
	
	public String pz() {
		return "pzDetale";
	}
	
	public String tk() {
		return "tkDetale";
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

	public Newsy getNews() {
		return news;
	}

	public void setNews(Newsy news) {
		this.news = news;
	}

	public ListDataModel<Poziomyzaawansowania> getPzModel() {
		if(pzModel.getRowIndex()==-1) pzModel.setWrappedData(pzImp.getAll());
		return pzModel;
	}

	public void setPzModel(ListDataModel<Poziomyzaawansowania> pzModel) {
		this.pzModel = pzModel;
	}

	public Poziomyzaawansowania getPz() {
		return pz;
	}

	public void setPz(Poziomyzaawansowania pz) {
		this.pz = pz;
	}

	public void setTkModel(ListDataModel<Typykursu> tkModel) {
		this.tkModel = tkModel;
	}

	public ListDataModel<Typykursu> getTkModel() {
		if(tkModel.getRowIndex()==-1) tkModel.setWrappedData(tkImp.getAll());
		return tkModel;
	}

	public void setTk(Typykursu tk) {
		this.tk = tk;
	}

	public Typykursu getTk() {
		return tk;
	}
}