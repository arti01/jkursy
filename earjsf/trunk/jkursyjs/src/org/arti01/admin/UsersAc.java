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
import org.richfaces.component.SortOrder;

public class UsersAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UsersAc.class);
	User user;
	@EJB
	UserImp userImp;
	@EJB
	RoleImp roleImp;
	@EJB
	KursyImp kursyImp;

	private SortOrder usernameOrder = SortOrder.unsorted;
	private SortOrder imieNazwiskoOrder = SortOrder.unsorted;
	private SortOrder dataZmianyOrder = SortOrder.unsorted;

	String userpass1;
	List<String> allRolesName = new ArrayList<String>();
	List<String> rolesName = new ArrayList<String>();
	private DataModel<User> allUsers = new ListDataModel<User>();
	private DataModel<User> dostepneKursy = new ListDataModel<User>();
	private String prawo = "";
	String errorText;

	public void sortByUsername() {
		setImieNazwiskoOrder(SortOrder.unsorted);
		setDataZmianyOrder(SortOrder.unsorted);
		if (usernameOrder.equals(SortOrder.ascending)) {
			setUsernameOrder(SortOrder.descending);
		} else {
			setUsernameOrder(SortOrder.ascending);
		}
	}

	public void sortByImieNazwisko() {
		setUsernameOrder(SortOrder.unsorted);
		setDataZmianyOrder(SortOrder.unsorted);
		if (imieNazwiskoOrder.equals(SortOrder.ascending)) {
			setImieNazwiskoOrder(SortOrder.descending);
		} else {
			setImieNazwiskoOrder(SortOrder.ascending);
		}
	}

	public void sortByDataZmiany() {
		setUsernameOrder(SortOrder.unsorted);
		setImieNazwiskoOrder(SortOrder.unsorted);
		if (dataZmianyOrder.equals(SortOrder.ascending)) {
			setDataZmianyOrder(SortOrder.descending);
		} else {
			setDataZmianyOrder(SortOrder.ascending);
		}
	}

	public String form() {
		allRolesName = roleImp.getRolesName();
		errorText = "";
		user = allUsers.getRowData();
		user=userImp.find(user);
		dostepneKursy.setWrappedData(userImp.dostepneKursy(user));
		logger.info(userImp.dostepneKursy(user));
		userpass1 = user.getUserpass();
		rolesName = userImp.getRolesName(user);
		logger.info(user.getKursies().size());
		return "usersForm";
	}

	public String formNew() {
		allRolesName = roleImp.getRolesName();
		rolesName=new ArrayList<String>();
		userpass1="";
		errorText = "";
		user = new User();
		return "usersForm";
	}

	public String usun() {
		user = allUsers.getRowData();
		logger.info(user.getUsername());
		userImp.remove(user);
		listAll();
		return "usersLista";
	}
	
	public String dodaj() {
		user.setDataZmiany(new Date());
		if (userImp.find(user)!=null)user.getRoles().clear();
		for (String name : rolesName) {
			Role r = new Role();
			r.setRola(name);
			r = roleImp.find(r);
			user.getRoles().add(r);
		}
		user.setDataZmiany(new Date());
		try {
			if (userImp.find(user)!=null) {// edycja
				logger.info("edycja");
					if (userImp.update(user)) {
						listAll();
						return "usersLista";
					}
			} else {
				logger.info("dodanie");
				user.getUsername();
				if (userImp.insert(user)) {
					listAll();
					return "usersLista";
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		// errorText=kursyImp.getErrorText();
		return "usersForm";// bo nie udala się zmiana
	}

	public String listAll() {
		allRolesName = roleImp.getRolesName();
		if(prawo.equals("nieprzypisani")){
			allUsers.setWrappedData(userImp.findNieprzypisani());	
		}
		else if(!prawo.equals("")){
			Role rola = new Role();
			rola.setRola(prawo);
			allUsers.setWrappedData(new ArrayList<User>(roleImp.find(rola).getUsers()));
		}else allUsers.setWrappedData(userImp.findAll());
		return "usersLista";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public SortOrder getUsernameOrder() {
		return usernameOrder;
	}

	public void setUsernameOrder(SortOrder usernameOrder) {
		this.usernameOrder = usernameOrder;
	}

	public SortOrder getImieNazwiskoOrder() {
		return imieNazwiskoOrder;
	}

	public void setImieNazwiskoOrder(SortOrder imieNazwiskoOrder) {
		this.imieNazwiskoOrder = imieNazwiskoOrder;
	}

	public SortOrder getDataZmianyOrder() {
		return dataZmianyOrder;
	}

	public void setDataZmianyOrder(SortOrder dataZmianyOrder) {
		this.dataZmianyOrder = dataZmianyOrder;
	}

	public DataModel<User> getDostepneKursy() {
		return dostepneKursy;
	}

	public void setDostepneKursy(DataModel<User> dostepneKursy) {
		this.dostepneKursy = dostepneKursy;
	}
}