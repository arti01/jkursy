package org.arti01.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
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
	
	String userpass1;
	//boolean zmien;
	//List<Integer> zaznaczoneKursy = new ArrayList<Integer>();
	private DataModel<User> allUsers=new ListDataModel<User>();
	//List<String> zaznaczone = new ArrayList<String>();
	//List<Role> roleAll;
	// private String nextAction;
	//private boolean admin = false;
	private String prawo=null;
	//private boolean asc = true;
	//private String sortTyp = "";
	String errorText;

	public String form(){
		errorText="";
		user=allUsers.getRowData();
		userpass1=user.getUserpass();
		return "usersForm";
	}
	public String formNew(){
		errorText="";
		user=new User();
		return "usersForm";
	}
	
	public String dodaj() {
		user.setDataZmiany(new Date());
		if (user.getUsername()!=null) {// edycja
			logger.info("edycja");
			if(userImp.update(user)){
				return "usersLista";
			}
		}
		else{
			if(userImp.insert(user)){
				return "usersLista";
			}
		}
		//errorText=kursyImp.getErrorText();
		return "usersForm";//bo nie udala się zmiana
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
	}
	
	public String formWyklad() throws Exception {
		form(Role.WYKLADOWCA);
		return "form";
	}

	public String formKursant() throws Exception {
		form(Role.KURSANT);
		return "form";
	}
*/
	/*public String dodaj() throws Exception {
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
	}*/

	public String listAdmin() {
		prawo=Role.ADMIN;
		Role rola=new Role();
		rola.setRola(prawo);
		allUsers.setWrappedData(new ArrayList<User>(roleImp.find(rola).getUsers()));
		return "usersLista";
	}
	public String listAll() {
		allUsers.setWrappedData(userImp.findAll());
		return "usersLista";
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	public void setPrawo(String prawo) {
		this.prawo = prawo;
	}

	public String getPrawo() {
		return prawo;
	}

	public DataModel<User> getAllUsers() {
		return allUsers;
		
	}
	public void setAllUsers(DataModel<User> allUsers) {
		this.allUsers = allUsers;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getUserpass1() {
		return userpass1;
	}
	public void setUserpass1(String userpass1) {
		this.userpass1 = userpass1;
	}
}