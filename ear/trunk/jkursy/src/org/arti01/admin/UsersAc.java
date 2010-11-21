package org.arti01.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.arti01.abstrakt.Akcja;
import org.arti01.entit.Kursy;
import org.arti01.entit.KursyUser;
import org.arti01.entit.Role;
import org.arti01.entit.User;
import org.arti01.entit.UserRole;
import org.arti01.sesBean.KursyImpLocal;
import org.arti01.sesBean.RoleImpLocal;
import org.arti01.sesBean.UserImp;
import org.arti01.sesBean.UserImpLocal;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class UsersAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UsersAc.class);
	User user;
	@EJB UserImpLocal userImp;
	@EJB RoleImpLocal roleImp;
	@EJB KursyImpLocal kursyImp;
	
	String haslo2;
	// boolean zmianaHasla;
	boolean zmien;
	List<UserImpLocal> users;
	List<Integer> zaznaczoneKursy = new ArrayList<Integer>();
	List<Kursy> kursyAll;
	List<String> zaznaczone = new ArrayList<String>();
	List<Role> roleAll;
	// private String nextAction;
	private boolean admin = false;

	private String prawo=null;
	private boolean asc = true;
	private String sortTyp = "";

	@SkipValidation
	private void form(String prawo){
		/*logger.info("userImp"+userImp);
		logger.info("userImp"+userImp+userImp.getUser());
		logger.info("userImp"+userImp+userImp.getUser()+userImp.getUser().getUsername());*/
		this.prawo=prawo;
		//if (user != null) {
		if (zmien) {
			logger.info("userImp"+userImp+userImp.getUser()+userImp.getUser().getUsername()+userImp.getUser().getImieNazwisko());
			logger.info("userImp"+userImp.getUser().getUserRoles().size());
			//user = userImp.find(userImp.getUser());
			zmien = true;
			for (UserRole r : userImp.getUser().getUserRoles()) {
				zaznaczone.add(r.getRole().getRola());
			}
			for (KursyUser ku : userImp.getUser().getKursyUsers()) {
				zaznaczoneKursy.add(ku.getKursy().getIdkursy());
				logger.info(ku.getKursy().getIdkursy());
			}
		} else {
			user= new User();
			user.setDataZmiany(new Date());
			zaznaczone.add(prawo);
		}
		roleAll = roleImp.findAll();
		kursyAll=kursyImp.findNiezakonczone();
	}
	
	@SkipValidation
	public String formAdmin() throws Exception {
		form(Role.ADMIN);
		return "form";
	}
	
	@SkipValidation
	public String formWyklad() throws Exception {
		form(Role.WYKLADOWCA);
		return "form";
	}

	@SkipValidation
	public String formKursant() throws Exception {
		form(Role.KURSANT);
		return "form";
	}

	public String dodaj() throws Exception {
		// obsluga roli
		Set<UserRole> userRoles = new HashSet<UserRole>();
		for (String i : zaznaczone) {
			Role r = new Role();
			r.setRola(i);
			UserRole ur =new UserRole();
			ur.setRole(r);
			ur.setUser(user);
			userRoles.add(ur);
		}
		Set<Kursy> kursy = new HashSet<Kursy>();
		for (Integer i : zaznaczoneKursy) {
			Kursy k = new Kursy();
			k.setIdkursy(i);
			kursy.add(k);
		}
		// koniec obslugi roli
		if (!zmien) {// dodawanie
			try {
				logger.info("dodanie"+user.getImieNazwisko());
				user.setUserRoles(userRoles);
				//user.setKursy(kursy);
				userImp.insert(user);
				setInfoText("login.dodany");
			} catch (Exception e) {
				logger.error("dodanie", e);
				addActionError("Nie udało się stworzyć użytkownika - najprawdopodobniej już taki istnieje");
				return "info";
			}
		} else {// edycja
			try {
				logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
				User userNew = new UserImp().find(user);
				userNew.setUserpass(user.getUserpass());
				userNew.setEmail(user.getEmail());
				userNew.setUlica(user.getUlica());
				userNew.setMiasto(user.getMiasto());
				userNew.setNip(user.getNip());
				userNew.setTel1(user.getTel1());
				userNew.setImieNazwisko(user.getImieNazwisko());
				userNew.setOpis(user.getOpis());
				//userNew.getRole().clear();
				//userNew.setRole(role);
				//userNew.setKursy(kursy);
				//logger.info("ilosc kursow"+ userNew.getKursyy().size());
				//new UserImp().update(userNew);
				setInfoText("login.zmieniony");
			} catch (Exception e) {
				addActionError("problem z edycja danych usera");
				logger.error("ssssssss", e);
				return "info";
			}
		}
		return "info";
	}

	@SkipValidation
	public String listAdmin() throws Exception {
		prawo=Role.ADMIN;
		users = userImp.findAdmin("imieNazwisko", asc);
		return "list";
	}
	
	@SkipValidation
	public String listWyklad() throws Exception {
		prawo=Role.WYKLADOWCA;
		users = userImp.findWyklad("imieNazwisko", asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	@SkipValidation
	public String listKursant() throws Exception {
		prawo=Role.KURSANT;
		users = userImp.findKursant(sortTyp, asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	@SkipValidation
	public String listNowych() throws Exception {
		prawo=Role.NOWY;
		users = userImp.findNowy(sortTyp, asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}
	
	@SkipValidation
	public String sortListAdmin() throws Exception {
		prawo=Role.ADMIN;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String sortListWyklad() throws Exception {
		prawo = Role.WYKLADOWCA;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String sortListKursyanci() throws Exception {
		prawo = Role.KURSANT;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	@SkipValidation
	public String usun() throws Exception {
		try {
			userImp.remove(user);
			setInfoText("login.usuniety");
		} catch (Exception e) {
			logger.info("ssssssss", e);
			addActionError("Nie udało się usunąc użytkownika - najprawdopodobniej posiada obiekty");
			return "info";
		}
		return SUCCESS;
	}

	public List<UserImpLocal> getUsers() {
		return users;
	}

	public void setUsers(List<UserImpLocal> users) {
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

	public List<Role> getRoleAll() {
		return roleAll;
	}

	public void setRoleAll(List<Role> roleAll) {
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

	public List<Kursy> getKursyAll() {
		return kursyAll;
	}

	public void setKursyAll(List<Kursy> kursyAll) {
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

	public UserImpLocal getUserImp() {
		return userImp;
	}

	public void setUserImp(UserImpLocal userImp) {
		this.userImp = userImp;
	}
}