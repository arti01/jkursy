package org.arti01.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Role;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.UserImp;

public class UsersAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UsersAc.class);
	User user;
	@EJB UserImp userImp;
	@EJB RoleImp roleImp;
	@EJB KursyImp kursyImp;
	
	String haslo2;
	// boolean zmianaHasla;
	boolean zmien;
	List<User> users;
	List<Integer> zaznaczoneKursy = new ArrayList<Integer>();
	private DataModel<User> allUsers=new ListDataModel<User>();
	List<String> zaznaczone = new ArrayList<String>();
	List<Role> roleAll;
	// private String nextAction;
	private boolean admin = false;

	private String prawo=null;
	private boolean asc = true;
	private String sortTyp = "";

	public String form(){
		//errorText="";
		user=allKursy.getRowData();
		return "kursyForm";
	}
	public String formNew(){
		errorText="";
		kurs=new Kursy();
		return "kursyForm";
	}
	
	/*private void form(String prawo){
		logger.info("userImp"+userImp);
		logger.info("userImp"+userImp+userImp.getUser());
		logger.info("userImp"+userImp+userImp.getUser()+userImp.getUser().getUsername());
		this.prawo=prawo;
		//if (user != null) {
		if (zmien) {
			userImp.setUser(userImp.find(userImp.getUser()));
			logger.info("userImp"+userImp+userImp.getUser()+userImp.getUser().getUsername()+userImp.getUser().getImieNazwisko());
			//user = userImp.find(userImp.getUser());
			zmien = true;
			for (Role r : userImp.getUser().getRoles()) {
				zaznaczone.add(r.getRola());
			}
			for (Kursy ku : userImp.getUser().getKursies()) {
				zaznaczoneKursy.add(ku.getIdkursy());
			}
		} else {
			userImp.setUser(new User());
			userImp.getUser().setDataZmiany(new Date());
			zaznaczone.add(prawo);
		}
		roleAll=roleImp.findAll();
		kursyAll=kursyImp.findNiezakonczone();
	}
	
	public String formAdmin() throws Exception {
		form(Role.ADMIN);
		return "form";
	}*/
	
	public String formWyklad() throws Exception {
		form(Role.WYKLADOWCA);
		return "form";
	}

	public String formKursant() throws Exception {
		form(Role.KURSANT);
		return "form";
	}

	public String dodaj() throws Exception {
		// obsluga roli
		for (String i : zaznaczone) {
			Role r = new Role();
			r.setRola(i);
			userImp.getUser().getRoles().add(r);
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
				logger.info("dodanie"+userImp.getUser().getImieNazwisko());
				logger.info("dodanie"+userImp.getUser().getUsername());
				//user.setKursy(kursy);
				userImp.insert(userImp.getUser());
				setInfoText("login.dodany");
			} catch (Exception e) {
				logger.error("dodanie", e);
				addActionError("Nie udało się stworzyć użytkownika - najprawdopodobniej już taki istnieje");
				return "info";
			}
		} else {// edycja
			try {
				logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
				logger.info("userImp"+userImp+userImp.getUser());
				logger.info("userImp"+userImp.getUser().getRoles().size());
				for(Role r:userImp.getUser().getRoles()){
					logger.info(r.getRola());	
				}
				//userImp.getUser().setUserRoles(userRoles);
				//user.setKursy(kursy);
				userImp.update(userImp.getUser());
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

	public String listAdmin() throws Exception {
		prawo=Role.ADMIN;
		Role rola=new Role();
		//rola.setRola(prawo);
		//rola=;
		//users =roleImp.findSortUser(rola, null, null).getUsers();
		users=new ArrayList<User>(roleImp.find(rola).getUsers());
		return "usersList";
	}
	
	
	public String listWyklad() throws Exception {
		prawo=Role.WYKLADOWCA;
		users = userImp.findWyklad("imieNazwisko", asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	
	public String listKursant() throws Exception {
		prawo=Role.KURSANT;
		users = userImp.findKursant(sortTyp, asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}

	
	public String listNowych() throws Exception {
		prawo=Role.NOWY;
		users = userImp.findNowy(sortTyp, asc);
		// logger.info("ssssssss"+users.size());
		return "list";
	}
	
	
	public String sortListAdmin() throws Exception {
		prawo=Role.ADMIN;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	
	public String sortListWyklad() throws Exception {
		prawo = Role.WYKLADOWCA;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	
	public String sortListKursyanci() throws Exception {
		prawo = Role.KURSANT;
		users = userImp.findNowy(sortTyp, asc);
		return "list";
	}
	
	
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

	//@VisitorFieldValidator(message = "błąd: ", key = "blad.loginu")
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

	//@FieldExpressionValidator(key = "hasla.rozne", expression = "haslo2.equals(userImp.getUser().getUserpass())")
	public void setHaslo2(String haslo2) {
		this.haslo2 = haslo2;
	}

	/*
	 *  public String sortDataZmiany() throws Exception { users =
	 * new UserImp().findOrderDataZmiany(asc); return "lista"; }
	 * 
	 * 
	 * public String getHaslo2() { return haslo2; }
	 * 
	 *  //@FieldExpressionValidator(key = "hasla.rozne",
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

	public UserImp getUserImp() {
		return userImp;
	}

	//@VisitorFieldValidator(message = "błąd: ", key = "blad.loginu")
	public void setUserImp(UserImp userImp) {
		this.userImp = userImp;
	}
	public DataModel<User> getAllUsers() {
		allUsers.setWrappedData(userImp.);
		return allUsers;
	}
	public void setAllUsers(DataModel<User> allUsers) {
		this.allUsers = allUsers;
	}
}