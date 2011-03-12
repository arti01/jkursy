package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	private KursyRezerwacje rezerwacja;
	private List<KursyRezerwacje> rezLista=new ArrayList<KursyRezerwacje>();
	private List<User> userLista=new ArrayList<User>();

	public String listDoAkceptu() {
		kursyAll = kursyImp.findAll();
		return "rezDoAkceptu";
	}

	public String listAll() {
		kursyAll = kursyImp.findAll();
		return "rezAll";
	}
	
	public String akceptuj() {
		rezerwacja.setWykonana(true);
		// logger.info(rezerwacja);
		kursyImp.updateRezerwacje(rezerwacja);
		User u = rezerwacja.getUser();
		boolean flaga = true;
		for (Kursy k : u.getKursies()) {
			if(k.getIdkursy()==rezerwacja.getKursy().getIdkursy()) flaga=false;
		}
		if (flaga) {
			u.getKursies().add(rezerwacja.getKursy());
			try {
				ui.update(u);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "rezDoAkceptu";
	}
	
	public String kasujRezerwacje(){
		rezerwacja.setAktywna(false);
		//logger.info(rezerwacja);
		kursyImp.updateRezerwacje(rezerwacja);
		return "rezDoAkceptu";
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

	public List<KursyRezerwacje> getRezLista() {
		return rezLista;
	}

	public void setRezLista(List<KursyRezerwacje> rezLista) {
		this.rezLista = rezLista;
	}

	public List<User> getUserLista() {
		return userLista;
	}

	public void setUserLista(List<User> userLista) {
		this.userLista = userLista;
	}

}