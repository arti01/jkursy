package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Poziomyzaawansowania;
import org.arti01.entit.Typykursu;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.PoziomyZaawansowaniaImp;
import org.arti01.sesBean.TypyKursuImp;
import org.arti01.sesBean.UserImp;
import org.richfaces.component.SortOrder;

public class KursyAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);

	private DataModel<Kursy> allKursy = new ListDataModel<Kursy>();
	private Kursy kurs;
	private String errorText;
	@EJB
	KursyImp kursyImp;
	@EJB
	UserImp userImp;
	@EJB
	PoziomyZaawansowaniaImp pzi;
	private ArrayList<Poziomyzaawansowania> allPz = new ArrayList<Poziomyzaawansowania>();

	@EJB
	TypyKursuImp tki;
	private ArrayList<Typykursu> allTk = new ArrayList<Typykursu>();

	private DataModel<User> wykladowcy = new ListDataModel<User>();
	private DataModel<User> kursanci = new ListDataModel<User>();
	private List<Integer> lpAll = new ArrayList<Integer>();

	private SortOrder nazwaOrder = SortOrder.unsorted;
	private SortOrder stacjonarnyTNOrder = SortOrder.unsorted;
	private SortOrder dataodOrder = SortOrder.unsorted;
	private SortOrder datadoOrder = SortOrder.unsorted;
	private SortOrder poziomyzaawansowaniaOrder = SortOrder.unsorted;
	private SortOrder typykursuOrder = SortOrder.unsorted;
	private SortOrder lpOrder = SortOrder.ascending;
	private SortOrder cenaOrder = SortOrder.ascending;

	public void sortBynazwa() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (nazwaOrder.equals(SortOrder.ascending)) {
			setNazwaOrder(SortOrder.descending);
		} else {
			setNazwaOrder(SortOrder.ascending);
		}
	}

	public void sortBystacjonarnyTN() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (stacjonarnyTNOrder.equals(SortOrder.ascending)) {
			setStacjonarnyTNOrder(SortOrder.descending);
		} else {
			setStacjonarnyTNOrder(SortOrder.ascending);
		}
	}

	public void sortBydataod() {
		setDatadoOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (dataodOrder.equals(SortOrder.ascending)) {
			setDataodOrder(SortOrder.descending);
		} else {
			setDataodOrder(SortOrder.ascending);
		}
	}

	public void sortBydatado() {
		setDataodOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (datadoOrder.equals(SortOrder.ascending)) {
			setDatadoOrder(SortOrder.descending);
		} else {
			setDatadoOrder(SortOrder.ascending);
		}
	}

	public void sortBypoziomyzaawansowania() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (poziomyzaawansowaniaOrder.equals(SortOrder.ascending)) {
			setPoziomyzaawansowaniaOrder(SortOrder.descending);
		} else {
			setPoziomyzaawansowaniaOrder(SortOrder.ascending);
		}
	}

	public void sortBytypykursu() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (typykursuOrder.equals(SortOrder.ascending)) {
			setTypykursuOrder(SortOrder.descending);
		} else {
			setTypykursuOrder(SortOrder.ascending);
		}
	}

	public void sortBylp() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setCenaOrder(SortOrder.unsorted);
		if (lpOrder.equals(SortOrder.ascending)) {
			setLpOrder(SortOrder.descending);
		} else {
			setLpOrder(SortOrder.ascending);
		}
	}

	public void sortBycena() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		setStacjonarnyTNOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		setPoziomyzaawansowaniaOrder(SortOrder.unsorted);
		setTypykursuOrder(SortOrder.unsorted);
		setLpOrder(SortOrder.unsorted);
		if (cenaOrder.equals(SortOrder.ascending)) {
			setCenaOrder(SortOrder.descending);
		} else {
			setCenaOrder(SortOrder.ascending);
		}
	}

	public String kursyLista() throws Exception {
		allKursy.setWrappedData(kursyImp.findAll());
		return "kursyLista";
	}

	public String usersLista() {
		kurs = kursyImp.find(allKursy.getRowData());
		wykladowcy.setWrappedData(new ArrayList<User>(kurs.getWykladowcy()));
		kursanci.setWrappedData(new ArrayList<User>(kurs.getKursanci()));
		return "kursyusersLista";
	}

	public String form() {
		errorText = "";
		kurs = allKursy.getRowData();
		allPz = new ArrayList<Poziomyzaawansowania>(pzi.getAll());
		allTk = new ArrayList<Typykursu>(tki.getAll());
		return "kursyForm";
	}

	public String formNew() {
		errorText = "";
		kurs = new Kursy();
		kurs.setPoziomyzaawansowania(new Poziomyzaawansowania());
		kurs.setTypykursu(new Typykursu());
		allPz = new ArrayList<Poziomyzaawansowania>(pzi.getAll());
		allTk = new ArrayList<Typykursu>(tki.getAll());
		return "kursyForm";
	}

	public String usun() throws Exception {
		kurs = allKursy.getRowData();
		kursyImp.delete(kurs);
		allKursy = new ListDataModel<Kursy>();
		allKursy.setWrappedData(kursyImp.findAll());
		return "kursyLista";
	}

	public String usunZkursu() {
		User user;
		if (wykladowcy.getRowIndex() < 0) {
			user = kursanci.getRowData();
		} else {
			user = wykladowcy.getRowData();
		}

		user.getKursies().remove(kurs);
		try {
			userImp.update(user);
			kurs.getKursanci().remove(user);
			kurs.getWykladowcy().remove(user);
			wykladowcy.setWrappedData(new ArrayList<User>(kurs.getWykladowcy()));
			kursanci.setWrappedData(new ArrayList<User>(kurs.getKursanci()));
		} catch (Exception e) {
			logger.info(e);
		}
		return "kursyusersLista";
	}

	public String dodaj() {
		kurs.setPoziomyzaawansowania(pzi.find(kurs.getPoziomyzaawansowania()));
		kurs.setTypykursu(tki.find(kurs.getTypykursu()));
		if (kurs.getIdkursy() != null) {// edycja
			if (kursyImp.update(kurs)) {
				return "kursyLista";
			}
		} else {
			Lekcja lekcja = new Lekcja();
			lekcja.setLp(0);
			lekcja.setTytul("Lekcja 0 - czyli wprowadzenie do kursu");
			lekcja.setTresc("<h2>&nbsp;</h2><h2><span style=\"font-size: medium;\">Witajcie!</span></h2><p><span style=\"font-size: medium;\">Naukę w Naszej Szkole rozpoczniemy od wstawienia wybranych przez Was kliku najlepszych czy ulubionych i koniecznie Waszych zdjęć, do Etapu 0, czyli naszej <strong>Galerii Przed-kursowej</strong>.</span></p><p><span style=\"font-size: medium;\">Tu możecie porozmawiać o tych zdjęciach, powiedzieć co i dlaczego Wam się podoba, poznać się troszkę zanim rozpoczniemy realizację konkretnych ćwiczeń.</span></p><p><span style=\"font-size: medium;\">Warto przypomnieć kilka zasad porządkowych, dzięki kt&oacute;rym łatwiej będzie się nam wsp&oacute;łpracowało.</span></p><p><span style=\"font-size: medium;\">Prowadzący kurs może, ale nie ma obowiązku uczestniczyć w rozmowach w Etapie 0. Podczas gdy w następnych, będzie wypowiadać się na temat każdego z <strong>3 zdjęć</strong> wstawianych jako realizacja ćwiczenia, a także na temat ewentualnych <strong>poprawek </strong>(do 3 w ramach jednego etapu kursu).</span></p><p><span style=\"font-size: medium;\">W czasie trwania kursu możecie oczywiście <strong>pytać </strong>-&nbsp; pod treścią materiału teoretycznego oraz pod zdjęciami własnymi i innych uczestnik&oacute;w. W przypadku, gdy pytanie jest skierowane do prowadzącego kurs, a nie do wsp&oacute;łuczestnik&oacute;w, koniecznie odhaczcie pole formularza, kt&oacute;re pomoże nam na sprawne informowanie wykładowcy o Waszych potrzebach. Pytania powinny dotyczyć przede wszystkim zakresu kursu. Na inne prowadzący nie musi (ale może) odpowiadać.</span></p><p><span style=\"font-size: medium;\">Podczas kursu prowadzący może kontaktować się z Wami wyłącznie za pomocą naszej platformy szkolnej, proszę o uszanowanie tej wewnątrzszkolnej zasady.</span></p><p><span style=\"font-size: medium;\">Teraz czekam już na prace w Galerii Początkowej.</span></p><p><span style=\"font-size: medium;\">Życzę Wam interesującej przygody z Fotografią.</span></p><p><span style=\"font-size: medium;\">Justyna Komar</span></p>");
			lekcja.setWidoczna(true);
			List<Lekcja> lekcjas = new ArrayList<Lekcja>();
			if (kursyImp.insert(kurs)) {
				allKursy = new ListDataModel<Kursy>();
				allKursy.setWrappedData(kursyImp.findAll());
				kurs.setLekcjas(lekcjas);
				lekcja.setKursy(kurs);
				lekcjas.add(lekcja);
				kursyImp.update(kurs);
				return "kursyLista";
			}
		}
		errorText = kursyImp.getErrorText();
		return "kursyForm";// bo nie udala si�� zmiana
	}

	public void zmienLp(ValueChangeEvent event) {
		// logger.info((Integer)event.getNewValue());
		kurs = allKursy.getRowData();
		kursyImp.update(kurs, (Integer) event.getNewValue());
		allKursy = new ListDataModel<Kursy>();
		allKursy.setWrappedData(kursyImp.findAll());
	}

	public String raport() {
		allKursy = new ListDataModel<Kursy>();
		allKursy.setWrappedData(kursyImp.findAll());
		return "raport";
	}

	public DataModel<Kursy> getAllKursy() {
		return allKursy;
	}

	public void setAllKursy(DataModel<Kursy> allKursy) {
		this.allKursy = allKursy;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public SortOrder getNazwaOrder() {
		return nazwaOrder;
	}

	public void setNazwaOrder(SortOrder nazwaOrder) {
		this.nazwaOrder = nazwaOrder;
	}

	public SortOrder getDataodOrder() {
		return dataodOrder;
	}

	public void setDataodOrder(SortOrder dataodOrder) {
		this.dataodOrder = dataodOrder;
	}

	public SortOrder getDatadoOrder() {
		return datadoOrder;
	}

	public void setDatadoOrder(SortOrder datadoOrder) {
		this.datadoOrder = datadoOrder;
	}

	public DataModel<User> getWykladowcy() {
		return wykladowcy;
	}

	public void setWykladowcy(DataModel<User> wykladowcy) {
		this.wykladowcy = wykladowcy;
	}

	public DataModel<User> getKursanci() {
		return kursanci;
	}

	public void setKursanci(DataModel<User> kursanci) {
		this.kursanci = kursanci;
	}

	public SortOrder getStacjonarnyTNOrder() {
		return stacjonarnyTNOrder;
	}

	public void setStacjonarnyTNOrder(SortOrder stacjonarnyTNOrder) {
		this.stacjonarnyTNOrder = stacjonarnyTNOrder;
	}

	public PoziomyZaawansowaniaImp getPzi() {
		return pzi;
	}

	public void setPzi(PoziomyZaawansowaniaImp pzi) {
		this.pzi = pzi;
	}

	public ArrayList<Poziomyzaawansowania> getAllPz() {
		return allPz;
	}

	public void setAllPz(ArrayList<Poziomyzaawansowania> allPz) {
		this.allPz = allPz;
	}

	public SortOrder getPoziomyzaawansowaniaOrder() {
		return poziomyzaawansowaniaOrder;
	}

	public void setPoziomyzaawansowaniaOrder(SortOrder poziomyzaawansowaniaOrder) {
		this.poziomyzaawansowaniaOrder = poziomyzaawansowaniaOrder;
	}

	public void setAllTk(ArrayList<Typykursu> allTk) {
		this.allTk = allTk;
	}

	public ArrayList<Typykursu> getAllTk() {
		return allTk;
	}

	public SortOrder getTypykursuOrder() {
		return typykursuOrder;
	}

	public void setTypykursuOrder(SortOrder typykursuOrder) {
		this.typykursuOrder = typykursuOrder;
	}

	public SortOrder getLpOrder() {
		return lpOrder;
	}

	public void setLpOrder(SortOrder lpOrder) {
		this.lpOrder = lpOrder;
	}

	public List<Integer> getLpAll() {
		Integer licz = 0;
		lpAll.clear();
		for (@SuppressWarnings("unused")
		Kursy k : allKursy) {
			licz++;
			lpAll.add(licz);
		}
		return lpAll;
	}

	public void setLpAll(List<Integer> lpAll) {
		this.lpAll = lpAll;
	}

	public SortOrder getCenaOrder() {
		return cenaOrder;
	}

	public void setCenaOrder(SortOrder cenaOrder) {
		this.cenaOrder = cenaOrder;
	}

}