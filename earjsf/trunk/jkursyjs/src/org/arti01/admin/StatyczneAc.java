package org.arti01.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.StatyczneImp;

public class StatyczneAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(StatyczneAc.class);
	
	private DataModel<Statyczne> allStatyczne=new ListDataModel<Statyczne>();
	private Statyczne statyczne;
	private ValueChangeEvent zmienE;
	@EJB StatyczneImp statImp;
	@EJB RoleImp roleImp;
	private List<String> rolesName=new ArrayList<String>();
	private String rola;
	
	public String form(){
		//logger.info(allStatyczne.isRowAvailable()+"dostepne");
		//logger.info(allStatyczne.getRowIndex()+"indexsssssssssss");
		statyczne=allStatyczne.getRowData();
		if(statyczne.getRole()==null) rola="wszyscy";
		else rola=statyczne.getRole().getRola();
		return "statyczneForm";
	}
	
	public String formNew(){
		statyczne=new Statyczne();
		if(statyczne.getRole()==null) rola="wszyscy";
		else rola=statyczne.getRole().getRola();
		return "statyczneForm";
	}
	
	public String listStatyczne(){
		//allStatyczne.setWrappedData(statImp.getFindAll());
		return "statyczneLista";
	}
	
	
	public String dodaj() throws Exception {
		logger.info(rola);
		Role rolaN=new Role();
		if(!rola.equals("wszyscy")) {
			rolaN.setRola(rola);
			statyczne.setRole(rolaN);
		}
		else statyczne.setRole(null);
		if (statyczne.getIdStatyczne()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			statImp.update(statyczne);
			/*try {
				if(lpAll.size()==0) lpAll.add(0);
				strona.setLp(Collections.max(lpAll)+1);
				new StatyczneImp().insert(strona);
				setInfoText("strona.dodany");
			} catch (Exception e) {
				logger.info("ssssssss", e);
				addActionError("nie udało się stworzyć strony, sprawdz, czy juz taka nie istnieje");
				return "info";
			}*/
		}
		else{
			//logger.info("doooooooodawanie"+statImp.getLpAll().size());
			if(statImp.getLpAll().size()==0) statyczne.setLp(1);
			else statyczne.setLp(Collections.max(statImp.getLpAll())+1);
			statImp.insert(statyczne);
			/*try {
				// logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
				Statyczne stronaNew = new StatyczneImp().find(strona);
				Integer oldLp=stronaNew.getLp();
				stronaNew.setTytul(strona.getTytul());
				stronaNew.setOpis(strona.getOpis());
				stronaNew.setLp(oldLp);
				//new Date().;
				//logger.info("eee"+strona.getTresc()+"eeeeeeee");
				stronaNew.setTresc(strona.getTresc());
				new StatyczneImp().update(stronaNew, oldLp);
				setInfoText("strona.zmieniony");
			} catch (Exception e) {
				addActionError("problem z edycja danych strony");
				logger.info("ssssssss", e);
				return "info";
			}*/			
		}
		return "statyczneLista";
	}
	
	public String usun() throws Exception {
		statyczne=allStatyczne.getRowData();
		statImp.delete(statyczne);
		return "statyczneLista";
	}

	public void zmienEe(ValueChangeEvent event) throws IOException{
		//logger.info(event.getNewValue());
		//logger.info(event.getOldValue());
		//logger.info("tttttttttttttttt");
		Statyczne st;//=new Statyczne();
		st=allStatyczne.getRowData();
		//logger.info(st.getTytul());
		//st.setLp((Integer)event.getNewValue());
		//logger.info(st.getLp()+"LP");
		//logger.info(st.getIdStatyczne()+"id");
		statImp.update(st, (Integer)event.getNewValue());
		//allStatyczne.setWrappedData(statImp.getFindAll());
		FacesContext.getCurrentInstance().getExternalContext().redirect("statyczneLista.xhtml");
	}

	public DataModel<Statyczne> getAllStatyczne() {
		allStatyczne.setWrappedData(statImp.getAll());
		//logger.info("sssssssssssssssssssssss");
		//logger.info(allStatyczne.getRowIndex());
		return allStatyczne;
	}

	public void setAllStatyczne(DataModel<Statyczne> allStatyczne) {
		this.allStatyczne = allStatyczne;
	}

	public Statyczne getStatyczne() {
		return statyczne;
	}

	public void setStatyczne(Statyczne statyczne) {
		this.statyczne = statyczne;
	}
	public StatyczneImp getStatImp() {
		return statImp;
	}
	public void setStatImp(StatyczneImp statImp) {
		this.statImp = statImp;
	}
	public void setZmienE(ValueChangeEvent zmienE) {
		this.zmienE = zmienE;
	}
	public ValueChangeEvent getZmienE() {
		return zmienE;
	}

	public RoleImp getRoleImp() {
		return roleImp;
	}

	public void setRoleImp(RoleImp roleImp) {
		this.roleImp = roleImp;
	}

	public List<String> getRolesName() {
		rolesName.clear();
		rolesName.add("wszyscy");
		rolesName.addAll(roleImp.getRolesName());
		return rolesName;
	}

	public void setRolesName(List<String> rolesName) {
		this.rolesName = rolesName;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}
}