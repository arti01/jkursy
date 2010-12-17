package org.arti01.admin;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.User;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.UserImp;
import org.richfaces.component.SortOrder;

public class KursyAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	
	private DataModel<Kursy> allKursy=new ListDataModel<Kursy>();
	private Kursy kurs;
	private String errorText;
	@EJB KursyImp kursyImp;
	@EJB UserImp userImp;
	private DataModel<User> users = new ListDataModel<User>();
	
	private SortOrder nazwaOrder = SortOrder.unsorted;
	private SortOrder dataodOrder = SortOrder.unsorted;
	private SortOrder datadoOrder = SortOrder.unsorted;

	public void sortBynazwa() {
		setDatadoOrder(SortOrder.unsorted);
		setDataodOrder(SortOrder.unsorted);
		if (nazwaOrder.equals(SortOrder.ascending)) {
			setNazwaOrder(SortOrder.descending);
		} else {
			setNazwaOrder(SortOrder.ascending);
		}
	}
	
	public void sortBydataod() {
		setDatadoOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		if (dataodOrder.equals(SortOrder.ascending)) {
			setDataodOrder(SortOrder.descending);
		} else {
			setDataodOrder(SortOrder.ascending);
		}
	}
	
	public void sortBydatado() {
		setDataodOrder(SortOrder.unsorted);
		setNazwaOrder(SortOrder.unsorted);
		if (datadoOrder.equals(SortOrder.ascending)) {
			setDatadoOrder(SortOrder.descending);
		} else {
			setDatadoOrder(SortOrder.ascending);
		}
	}
	
	
	public String kursyLista() throws Exception{
		allKursy.setWrappedData(kursyImp.findAll());
		return "kursyLista";
	}
	
	public String kursantLista() throws Exception{
		//users=new ArrayList<User>(allKursy.getRowData().getUsers());
		kurs=kursyImp.find(allKursy.getRowData());
		users.setWrappedData(new ArrayList<User>(kurs.getKursanci()));
		return "kursyusersLista";
	}
	
	public String wykladLista() throws Exception{
		//users=new ArrayList<User>(allKursy.getRowData().getUsers());
		kurs=kursyImp.find(allKursy.getRowData());
		//users=new ArrayList<User>(kurs.getWykladowcy());
		users.setWrappedData(new ArrayList<User>(kurs.getWykladowcy()));
		return "kursyusersLista";
	}
	
	public String form(){
		errorText="";
		kurs=allKursy.getRowData();
		return "kursyForm";
	}
	public String formNew(){
		errorText="";
		kurs=new Kursy();
		return "kursyForm";
	}
	
	
	public String usun() throws Exception {
		kurs=allKursy.getRowData();
		kursyImp.delete(kurs);
		return "kursyusersLista";
	}
	
	public String usunZkursu() throws Exception{
		logger.info(kurs.getNazwa());
		logger.info(kurs.getUsers());
		User user=users.getRowData();
		user=userImp.find(user);
		kurs=kursyImp.find(kurs);
		logger.info(user.getKursies().size());
		user.getKursies().remove(kurs);
		logger.info(user.getKursies().size());
		userImp.update(user);
		logger.info(user.getKursies().size());
		//kurs=kursyImp.find(kurs);
		//kurs.getUsers().remove(user);
		//kursyImp.update(kurs);
		logger.info(kurs.getUsers());
		return "kursyusersLista";
	}
	
	public String dodaj() {
		if (kurs.getIdkursy()!=null) {// edycja
			if(kursyImp.update(kurs)){
				return "kursyLista";
			}
		}
		else{
			if(kursyImp.insert(kurs)){
				return "kursyLista";
			}
		}
		errorText=kursyImp.getErrorText();
		return "kursyForm";//bo nie udala się zmiana
	}



	public DataModel<Kursy> getAllKursy() {
		return allKursy;
	}



	public void setAllKursy(DataModel<Kursy> allKursy) {
		this.allKursy = allKursy;
	}

	public Kursy getKurs(){
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

	public DataModel<User> getUsers() {
		return users;
	}

	public void setUsers(DataModel<User> users) {
		this.users = users;
	}

}