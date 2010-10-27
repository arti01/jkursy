package org.arti01.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.arti01.abstrakt.Akcja;
import org.arti01.obiekty.Kurs;
import org.arti01.obiekty.KursImp;
import org.arti01.obiekty.User;
import org.arti01.obiekty.UserImp;
import org.arti01.obiekty.Rola;
import org.arti01.obiekty.RolaImp;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class UsersAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UsersAc.class);
	User user;
	String haslo2;
	// boolean zmianaHasla;
	boolean zmien;
	List<User> users;
	List<Integer> zaznaczoneKursy = new ArrayList<Integer>();
	List<Kurs> kursyAll;
	List<String> zaznaczone = new ArrayList<String>();
	List<Rola> roleAll;
	// private String nextAction;
	private boolean admin = false;
	private String prawo=null;
	private boolean asc = true;
	private String sortTyp = "";

	@SkipValidation
	public String formAdmin() throws Exception {
		logger.info("issssssssssssssssssssssss"+new UserImp().istnieje(null));
		prawo=Rola.ADMIN;
		if (user != null) {
			user = new UserImp().find(user);
			zmien = true;
			for (Rola r : user.getRole()) {
				zaznaczone.add(r.getRola());
			}
			for (Kurs k : user.getKursy()) {
				zaznaczoneKursy.add(k.getIdkursy());
			}
			zmien = true;
		} else {
			user= new User();
			user.setData_zmiany(new SimpleDateFormat("yyyy-MM-dd").format( new Date()));
			zaznaczone.add(Rola.ADMIN);
		}
		roleAll = new RolaImp().findAll();
		kursyAll=new KursImp().findNiezakończone();
		return "form";
	}
	
	@SkipValidation
	public String formWyklad() throws Exception {
		prawo=Rola.WYKLADOWCA;
		if (user != null) {
			user = new UserImp().find(user);
			zmien = true;
			for (Rola r : user.getRole()) {
				zaznaczone.add(r.getRola());
			}
			for (Kurs k : user.getKursy()) {
				zaznaczoneKursy.add(k.getIdkursy());
			}
			zmien = true;
		} else {
			user= new User();
			user.setData_zmiany(new SimpleDateFormat("yyyy-MM-dd").format( new Date()));
			zaznaczone.add(Rola.WYKLADOWCA);
		}
		roleAll = new RolaImp().findAll();
		kursyAll=new KursImp().findNiezakończone();
		return "form";
	}

	@SkipValidation
	public String formKursant() throws Exception {
		prawo=Rola.KURSANT;
		if (user != null) {
			user = new UserImp().find(user);
			zmien = true;
			for (Rola r : user.getRole()) {
				zaznaczone.add(r.getRola());
			}
			for (Kurs k : user.getKursy()) {
				zaznaczoneKursy.add(k.getIdkursy());
			}
			zmien = true;
		} else {
			user= new User();
			user.setData_zmiany(new SimpleDateFormat("yyyy-MM-dd").format( new Date()));
			zaznaczone.add(Rola.KURSANT);
		}
		roleAll = new RolaImp().findAll();
		kursyAll=new KursImp().findNiezakończone();
		return "form";
	}

	public String dodaj() throws Exception {
		// obsluga roli
		Set<Rola> role = new HashSet<Rola>();
		for (String i : zaznaczone) {
			Rola r = new Rola();
			r.setRola(i);
			role.add(r);
		}
		Set<Kurs> kursy = new HashSet<Kurs>();
		for (Integer i : zaznaczoneKursy) {
			Kurs k = new Kurs();
			k.setIdkursy(i);
			kursy.add(k);
		}
		// koniec obslugi roli
		if (!zmien) {// dodawanie
			try {
				user.setRole(role);
				user.setKursy(kursy);
				new UserImp().insert(user);
				setInfoText("login.dodany");
			} catch (Exception e) {
				addActionError("Nie udało się stworzyć użytkownika - najprawdopodobniej już taki istnieje");
				return "info";
			}
		} else {// edycja
			try {
				// logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
				User userNew = new UserImp().find(user);
				userNew.setUserpass(user.getUserpass());
				userNew.setEmail(user.getEmail());
				userNew.setUlica(user.getUlica());
				userNew.setMiasto(user.getMiasto());
				userNew.setNip(user.getNip());
				userNew.setTel1(user.getTel1());
				userNew.setImieNazwisko(user.getImieNazwisko());
				userNew.setOpis(user.getOpis());
				userNew.getRole().clear();
				userNew.setRole(role);
				userNew.setKursy(kursy);
				logger.info("ilosc kursow"+ userNew.getKursy().size());
				new UserImp().update(userNew);
				setInfoText("login.zmieniony");
			} catch (Exception e) {
				addActionError("problem z edycja danych usera");
				logger.info("ssssssss", e);
				return "info";
			}
		}
		return "info";
	}

	@SkipValidation
	public String listAdmin() throws Exception {
		prawo=Rola.ADMIN;
		users = new UserImp().findAdmin("imieNazwisko", asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}
	
	@SkipValidation
	public String listWyklad() throws Exception {
		prawo=Rola.WYKLADOWCA;
		users = new UserImp().findWyklad("imieNazwisko", asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	@SkipValidation
	public String listKursant() throws Exception {
		prawo=Rola.KURSANT;
		users = new UserImp().findKursant(sortTyp, asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	@SkipValidation
	public String sortListAdmin() throws Exception {
		prawo=Rola.ADMIN;
		users = new UserImp().findAdmin(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String sortListWyklad() throws Exception {
		prawo = Rola.WYKLADOWCA;
		users = new UserImp().findWyklad(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String sortListKursanci() throws Exception {
		prawo = Rola.KURSANT;
		users = new UserImp().findWyklad(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String usun() throws Exception {
		try {
			new UserImp().remove(user);
			setInfoText("login.usuniety");
		} catch (Exception e) {
			logger.info("ssssssss", e);
			addActionError("Nie udało się usunąc użytkownika - najprawdopodobniej posiada obiekty");
			return "info";
		}
		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public User getUser() {
		return user;
	}

	@VisitorFieldValidator(message = "błąd: ", key = "blad.loginu")
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isZmien() {
		return zmien;
	}

	public void setZmien(boolean zmien) {
		this.zmien = zmien;
	}

	public List<Rola> getRoleAll() {
		return roleAll;
	}

	public void setRoleAll(List<Rola> roleAll) {
		this.roleAll = roleAll;
	}

	public List<String> getZaznaczone() {
		return zaznaczone;
	}

	public void setZaznaczone(List<String> zaznaczone) {
		this.zaznaczone = zaznaczone;
	}

	public String getHaslo2() {
		return haslo2;
	}

	@FieldExpressionValidator(key = "hasla.rozne", expression = "haslo2.equals(user.getUserpass())")
	public void setHaslo2(String haslo2) {
		this.haslo2 = haslo2;
	}

	/*
	 * @SkipValidation public String sortDataZmiany() throws Exception { users =
	 * new UserImp().findOrderDataZmiany(asc); return "lista"; }
	 * 
	 * 
	 * public String getHaslo2() { return haslo2; }
	 * 
	 * @SkipValidation //@FieldExpressionValidator(key = "hasla.rozne",
	 * expression = "haslo2.equals(user.getUserpass())") public void
	 * setHaslo2(String haslo2) { this.haslo2 = haslo2; }
	 * 
	 * public boolean isZmianaHasla() { return zmianaHasla; }
	 * 
	 * public void setZmianaHasla(boolean zmianaHasla) { this.zmianaHasla =
	 * zmianaHasla; }
	 * 
	 * public String getNextAction() { return nextAction; }
	 * 
	 * public void setNextAction(String nextAction) { this.nextAction =
	 * nextAction; }
	 * 
	 * public boolean isAsc() { return asc; }
	 * 
	 * public void setAsc(boolean asc) { this.asc = asc; }
	 */

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getSortTyp() {
		return sortTyp;
	}

	public void setSortTyp(String sortTyp) {
		this.sortTyp = sortTyp;
	}

	public List<Kurs> getKursyAll() {
		return kursyAll;
	}

	public void setKursyAll(List<Kurs> kursyAll) {
		this.kursyAll = kursyAll;
	}

	public List<Integer> getZaznaczoneKursy() {
		return zaznaczoneKursy;
	}

	public void setZaznaczoneKursy(List<Integer> zaznaczoneKursy) {
		this.zaznaczoneKursy = zaznaczoneKursy;
	}

	public void setPrawo(String prawo) {
		this.prawo = prawo;
	}

	public String getPrawo() {
		return prawo;
	}
}