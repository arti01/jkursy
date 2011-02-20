package org.arti01.all;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Role;
import org.arti01.entit.User;
import org.arti01.entit.Userfoty;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.UserImp;

@ManagedBean(name="kursyAc")
@SessionScoped
public class KursyAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private List<Kursy>kursy;
	private ListDataModel<Kursy> allKursy = new ListDataModel<Kursy>();
	private ListDataModel<User> allWykladowcy = new ListDataModel<User>();
	private User wykladowca=new User();
	private Kursy kurs;
	@EJB KursyImp kursyImp;
	@EJB UserImp userImp;
	@EJB RoleImp roleImp;
	private Integer nrstrony;
	private Userfoty uf=new Userfoty();
	
	public KursyAc() {
		kurs=new Kursy();
    }
	
	public String list() {
		//logger.info("jestemmmm");
		kursy=kursyImp.findAll();
		allKursy.setWrappedData(kursy);
		return "kursyLista";
	}
	
public String userGaleriaDetale(){
		nrstrony=(wykladowca.getUserfotyakcept().indexOf(uf))+1;
		//logger.info("jestemmmm");
		return "userGaleriaDetale";
	}
	
	public String listA() {
		kursy=kursyImp.findAll();
		allKursy.setWrappedData(kursy);
		return null;
	}
	
	public String wykladList(){
		Role rola = new Role();
		rola.setRola(Role.WYKLADOWCA);
		allWykladowcy.setWrappedData(new ArrayList<User>(roleImp.find(rola).getUsers()));
		return "wykladLista";
	}
	
	public String detale() throws Exception{
		FacesContext fs = FacesContext.getCurrentInstance();
		Map<String,String> params = fs.getExternalContext().getRequestParameterMap();
		String idkursy = params.get("idkursy");
		if(idkursy!=null){
			kurs.setIdkursy(new Integer(idkursy));
			kurs=kursyImp.find(kurs);
		}
		else kurs=allKursy.getRowData();
		return "kursyDetale";
	}
	
	public String wykladDetale(){
		FacesContext fs = FacesContext.getCurrentInstance();
		Map<String,String> params = fs.getExternalContext().getRequestParameterMap();
		String username = params.get("username");
		if(username!=null)wykladowca.setUsername(username);
		wykladowca=userImp.find(wykladowca);
		return "wykladowcaDetale";
	}
	
	public String userGaleria(){
		wykladowca=userImp.find(wykladowca);
		return "userGaleria";
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
	
	public ListDataModel<User> getAllWykladowcy() {
		return allWykladowcy;
	}

	public void setAllWykladowcy(ListDataModel<User> allWykladowcy) {
		this.allWykladowcy = allWykladowcy;
	}

	public Integer getNrstrony() {
		return nrstrony;
	}

	public void setNrstrony(Integer nrstrony) {
		this.nrstrony = nrstrony;
	}

	public Userfoty getUf() {
		return uf;
	}

	public void setUf(Userfoty uf) {
		this.uf = uf;
	}
	
	
}