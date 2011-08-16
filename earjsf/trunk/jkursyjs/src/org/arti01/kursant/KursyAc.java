package org.arti01.kursant;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.KursyRezerwacje;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Newsykursy;
import org.arti01.entit.Rachunki;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.utility.Login;

@ManagedBean(name = "kursantKursyAc")
@SessionScoped
public class KursyAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);

	private Kursy kurs;
	private DataModel<Lekcja> lekcje = new ListDataModel<Lekcja>();
	private DataModel<KursyRezerwacje> kurRez = new ListDataModel<KursyRezerwacje>();
	@EJB KursyImp kursyImp;
	private Newsykursy news;
	private boolean rachTak;
	@ManagedProperty(value = "#{login}") private Login loginBean;
	private Rachunki rachunek = new Rachunki();
	private String errorText;
	private User kursant;
	private String polecajacy;

	public String rezerwacja() {
		rachunek.setImienazwisko(loginBean.getZalogowany().getImieNazwisko());
		rachunek.setMiasto(loginBean.getZalogowany().getMiasto());
		rachunek.setKodpocztowy(loginBean.getZalogowany().getKodpocztowy());
		rachunek.setUlica(loginBean.getZalogowany().getUlica());
		rachunek.setNip(loginBean.getZalogowany().getNip());
		return "/kursant/rezerwacja";
	}

	public String rezerwuj() {
		KursyRezerwacje kr = new KursyRezerwacje();
		kr.setPolecajacy(polecajacy);
		kr.setAktywna(true);
		kr.setWykonana(false);
		kr.setKursy(kurs);
		kr.setUser(loginBean.getZalogowany());
		if(rachTak)kr.setRachunki(rachunek);
		for (Kursy k : loginBean.getZalogowany().getKursyZarezerwowane()) {
			if (k.getIdkursy() == kurs.getIdkursy()) {
				errorText = "Masz ju�� rezerwacj�� na ten kurs";
				return "info";
			}
		}
		kursyImp.rezerwuj(kr);
		errorText = "Rezerwacja wykonana";
		return "info";
	}

	public String rezerwacjeLista() {
		return "rezerwacjeLista";
	}

	public String pokazKursanta(){
		return "pokazKursanta";
	}
	
	public String kasujRezerwacje(){
		KursyRezerwacje kr=kurRez.getRowData();
		kr.setAktywna(false);
		kursyImp.updateRezerwacje(kr);
		return "rezerwacjeLista";
	}
	
	public String lekcjaLista() {
		kurs = kursyImp.find(kurs);
		lekcje.setWrappedData(kurs.getLekcjeWidoczne());
		return "lekcjaLista";
	}

	public String newsWiecej() {
		return "news";
	}

	public Kursy getKursy() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public DataModel<Lekcja> getLekcje() {
		return lekcje;
	}

	public void setLekcje(DataModel<Lekcja> lekcje) {
		this.lekcje = lekcje;
	}

	public KursyImp getKursyImp() {
		return kursyImp;
	}

	public void setKursyImp(KursyImp kursyImp) {
		this.kursyImp = kursyImp;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public Newsykursy getNews() {
		return news;
	}

	public void setNews(Newsykursy news) {
		this.news = news;
	}

	public boolean isRachTak() {
		return rachTak;
	}

	public void setRachTak(boolean rachTak) {
		this.rachTak = rachTak;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

	public Rachunki getRachunek() {
		return rachunek;
	}

	public void setRachunek(Rachunki rachunek) {
		this.rachunek = rachunek;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public DataModel<KursyRezerwacje> getKurRez() {
		kurRez.setWrappedData(loginBean.getZalogowany().getRezerwacje());
		return kurRez;
	}

	public void setKurRez(DataModel<KursyRezerwacje> kurRez) {
		this.kurRez = kurRez;
	}

	public User getKursant() {
		return kursant;
	}

	public void setKursant(User kursant) {
		this.kursant = kursant;
	}

	public String getPolecajacy() {
		return polecajacy;
	}

	public void setPolecajacy(String polecajacy) {
		this.polecajacy = polecajacy;
	}

}