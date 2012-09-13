package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.KursyRezerwacje;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.UserImp;

@ManagedBean(name = "adminRezerwacjeAc")
@SessionScoped
public class RezerwacjeAc implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(RezerwacjeAc.class);
	private List<Kursy> kursyAll = new ArrayList<Kursy>();
	@EJB
	KursyImp kursyImp;
	@EJB
	UserImp ui;
	private KursyRezerwacje rezerwacja=new KursyRezerwacje();
	private DataModel<KursyRezerwacje> rezLista=new ListDataModel<KursyRezerwacje>();
	private List<User> userLista=new ArrayList<User>();

	public String listDoAkceptu() {
		rezLista=new ListDataModel<KursyRezerwacje>();
		List<KursyRezerwacje> rl=new ArrayList<KursyRezerwacje>();
		for(Kursy k:kursyImp.findAll()){
			rl.addAll(k.getRezerwacjeNowe());
		}
		rezLista.setWrappedData(rl);
		return "rezDoAkceptu";
	}

	public String listAll() {
		kursyAll = kursyImp.findAll();
		return "rezAll";
	}
	
	public void akceptuj() {
		rezerwacja=rezLista.getRowData();
		logger.info(rezerwacja.getDatawplaty()+"--"+rezerwacja.getWplata());
		rezerwacja.setWykonana(true);
		kursyImp.updateRezerwacje(rezerwacja);		
		listDoAkceptu();
		logger.info(rezLista.getRowCount());
	}
	
	public void kasujRezerwacje(){
		rezerwacja=rezLista.getRowData();
		rezerwacja.setAktywna(false);
		//logger.info(rezerwacja);
		kursyImp.updateRezerwacje(rezerwacja);
		listDoAkceptu();
	}
	
	public String czarnaLista(){
		userLista=kursyImp.czarnaLista();
		return "rezCzarna";	
	}

	public List<Kursy> getKursyAll() {
		return kursyAll;
	}

	public void setKursyAll(List<Kursy> kursyAll) {
		this.kursyAll = kursyAll;
	}

	public KursyRezerwacje getRezerwacja() {
		return rezerwacja;
	}

	public void setRezerwacja(KursyRezerwacje rezerwacja) {
		this.rezerwacja = rezerwacja;
	}

	public List<User> getUserLista() {
		return userLista;
	}

	public void setUserLista(List<User> userLista) {
		this.userLista = userLista;
	}

	public DataModel<KursyRezerwacje> getRezLista() {
		return rezLista;
	}

	public void setRezLista(DataModel<KursyRezerwacje> rezLista) {
		this.rezLista = rezLista;
	}

}