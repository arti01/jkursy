package org.arti01.wykladowca;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Lekcjapliki;
import org.arti01.entit.Role;
import org.arti01.entit.Statyczne;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.StatyczneImp;

@ManagedBean(name="wykladKursyAc")
@SessionScoped
public class KursyAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private User zalogowany;
	private Kursy kurs;
	private Lekcja lekcja;
	private DataModel<Lekcja> lekcje=new ListDataModel<Lekcja>();
	@EJB LekacjaImp lekcjaImp;
	@EJB KursyImp kursyImp;
	private String errorText="";
	@ManagedProperty(value="#{wykladImageUploadBean}") private ImageUploadBean iub;
	@ManagedProperty(value="#{wykladPlikUploadBean}")private PlikUploadBean pub;
	@ManagedProperty(value="#{wykladNewsyAc}")private NewsyAc wn;
	@EJB RoleImp roleImp;
	@EJB StatyczneImp statyczneImp;
	private Role role=new Role();
	private Statyczne strona;
	
	private Lekcjafotykursant fotaKursant;
	
	public String kursForm(){
		errorText="";
		kurs=kursyImp.find(kurs);
		lekcje.setWrappedData(kurs.getLekcjas());
		wn.setKurs(kurs);
		//logger.info(kurs.getNazwa());
		//logger.info(kurs.getLekcjeLpAll());
		return "kursyForm";
	}
	
	public String lekcjaFormNew(){
		errorText="";
		lekcja=new Lekcja();
		return "lekcjaForm";
	}
	
	public String statyczna(){
		errorText="";
		logger.info(strona.getTytul());
		strona =statyczneImp.find(strona);
		logger.info(strona.getTytul());
		return "/wykladowca/statyczneDetale.xhtml";
	}
	
	public String lekcjaForm(){
		errorText="";
		logger.info(lekcja.getTytul());
		return "lekcjaForm";
	}
	
	public String lekcjaDodaj(){
		errorText="";
		logger.info("lekcjaDodaj");
		lekcja.setKursy(kurs);
		lekcja.setDatazmiany(new Timestamp(new Date().getTime()));
		if (lekcja.getIdlekcja()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			lekcjaImp.update(lekcja);
		}
		else{
			//logger.info(lekcjaImp.getLpAll(kurs));
			if(kurs.getLekcjeLpAll().size()==0) lekcja.setLp(1);
			else lekcja.setLp(Collections.max(kurs.getLekcjeLpAll())+1);
			lekcjaImp.insert(lekcja);			
		}
		kurs=kursyImp.find(kurs);
		lekcje.setWrappedData(kurs.getLekcjas());
		return "kursyForm";
	}
	
	public String foty(){
		errorText="";
		logger.info("obsluga zdjec");
		//logger.info(lekcja.getFotyLpAll());
		iub.setLekcja(lekcjaImp.find(lekcja));
		iub.setFoty(new ArrayList<Lekcjafoty>(lekcja.getLekcjafoties()));
		return "fotyForm";
	}
	
	public String pliki(){
		errorText="";
		logger.info("obsluga plikow");
		pub.setLekcja(lekcjaImp.find(lekcja));
		pub.setPliki(new ArrayList<Lekcjapliki>(lekcja.getLekcjaplikis()));
		//logger.info(lekcja.getLekcjaplikis().size());
		return "plikiForm.xhtml";
	}
	
	public String lekcjaUsun(){
		errorText="";
			try {
				lekcjaImp.delete(lekcja);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errorText="nieudane usunięcie - lekcja zawiera zdjęcia, komentarze, foty kursantów - należy je usunąć";
			}
			kurs=kursyImp.find(kurs);
			lekcje.setWrappedData(kurs.getLekcjas());
		return "kursyForm";
	}
	
	public String lekcjaPodglad(){
		errorText="";
		return "lekcja.xhtml";
	}
	
	public String fotyKursantow(){
		errorText="";
		return "fotyKursantow.xhtml";
	}
	
	public String fotaKursant(){
		errorText="";
		return "fotyKursantow.xhtml";
	}
	
	public void zmienLekcjaLp(ValueChangeEvent event) throws IOException{
		errorText="";
		logger.info(event.getNewValue());
		logger.info(event.getOldValue());
		lekcja=lekcje.getRowData();
		logger.info(lekcja);
		logger.info(lekcja.getTytul());
		lekcjaImp.update(lekcja, (Integer)event.getNewValue());
		kurs=kursyImp.find(kurs);
		lekcje.setWrappedData(kurs.getLekcjas());
		FacesContext.getCurrentInstance().getExternalContext().redirect("kursyForm.xhtml");
	}
	
	public User getZalogowany() {
		return zalogowany;
	}
	public void setZalogowany(User zalogowany) {
		this.zalogowany = zalogowany;
	}
	public Kursy getKurs() {
		return kurs;
	}
	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public DataModel<Lekcja> getLekcje() {
		return lekcje;
	}

	public void setLekcje(DataModel<Lekcja> lekcje) {
		this.lekcje = lekcje;
	}

	public void setIub(ImageUploadBean iub) {
		this.iub = iub;
	}

	public RoleImp getRoleImp() {
		return roleImp;
	}

	public void setRoleImp(RoleImp roleImp) {
		this.roleImp = roleImp;
	}

	public Role getRole() {
		role.setRola(Role.WYKLADOWCA);
		role=roleImp.find(role);
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Statyczne getStrona() {
		return strona;
	}

	public void setStrona(Statyczne statyczna) {
		this.strona = statyczna;
	}

	public StatyczneImp getStatyczneImp() {
		return statyczneImp;
	}

	public void setStatyczneImp(StatyczneImp statyczneImp) {
		this.statyczneImp = statyczneImp;
	}

	public PlikUploadBean getPub() {
		return pub;
	}

	public void setPub(PlikUploadBean pub) {
		this.pub = pub;
	}

	public void setWn(NewsyAc wn) {
		this.wn = wn;
	}

	public NewsyAc getWn() {
		return wn;
	}

	public Lekcjafotykursant getFotaKursant() {
		return fotaKursant;
	}

	public void setFotaKursant(Lekcjafotykursant fotaKursant) {
		this.fotaKursant = fotaKursant;
	}

}