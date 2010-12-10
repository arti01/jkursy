package org.arti01.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	List<String> allRolesName = new ArrayList<String>();
	List<String> rolesName = new ArrayList<String>();
	private DataModel<String> order=new ListDataModel<String>();
	boolean asc;
	private DataModel<User> allUsers=new ListDataModel<User>();
	private String prawo=null;;
	String errorText;

	public String form(){
		allRolesName=roleImp.getRolesName();		
		errorText="";
		user=allUsers.getRowData();
		userpass1=user.getUserpass();
		rolesName=userImp.getRolesName(user);
		return "usersForm";
	}
	public String formNew(){
		allRolesName=roleImp.getRolesName();		
		errorText="";
		user=new User();
		return "usersForm";
	}
	
	public String dodaj() {
		user.getRoles().clear();
		for(String name:rolesName){
			Role r=new Role();
			r.setRola(name);
			r=roleImp.find(r);
			user.getRoles().add(r);
		}
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
		//logger.info(order+ asc);
		if (order==null)allUsers.setWrappedData(userImp.findAll());
		else allUsers.setWrappedData(userImp.allOrder(asc));
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
	public List<String> getAllRolesName() {
		return allRolesName;
	}
	public void setAllRolesName(List<String> allRolesName) {
		this.allRolesName = allRolesName;
	}
	public List<String> getRolesName() {
		return rolesName;
	}
	public void setRolesName(List<String> rolesName) {
		this.rolesName = rolesName;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	public DataModel<String> getOrder() {
		List<String> list=new ArrayList<String>();
		list.add("username");
		list.add("datazmiany");
		list.add("imieNazwisko");
		order.setWrappedData(list);
		return order;
	}
	public void setOrder(DataModel<String> order) {
		this.order = order;
	}
}