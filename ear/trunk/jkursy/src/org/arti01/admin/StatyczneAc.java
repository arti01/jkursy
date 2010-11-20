package org.arti01.admin;

import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.arti01.abstrakt.Akcja;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.StatyczneImp;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class StatyczneAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(StatyczneAc.class);
	
	private List<Statyczne> statyczne;
	private Statyczne strona;
	private boolean zmien;
	private List<Integer>lpAll=new StatyczneImp().lpAll();

	@SkipValidation
	public String list() throws Exception{
		statyczne= new StatyczneImp().findAll();
		logger.info(statyczne.size());
		return "list";
	}
	

	@SkipValidation
	public String form() throws Exception{
		if (strona != null) zmien = true;
		strona= new StatyczneImp().find(strona);
		return "form";
	}
	
	@SkipValidation
	public String usun() throws Exception {
		try {
			new StatyczneImp().remove(strona);
			setInfoText("strona.usuniety");
		} catch (Exception e) {
			logger.info("ssssssss", e);
			addActionError("nie uda�o si�");
			return "info";
		}
		return "relist";
	}
	
	public String dodaj() throws Exception {
		if (!zmien) {// dodawanie
			try {
				if(lpAll.size()==0) lpAll.add(0);
				strona.setLp(Collections.max(lpAll)+1);
				new StatyczneImp().insert(strona);
				setInfoText("strona.dodany");
			} catch (Exception e) {
				logger.info("ssssssss", e);
				addActionError("nie udało się stworzyć strony, sprawdz, czy juz taka nie istnieje");
				return "info";
			}
		}
		else{
			try {
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
			}			
		}
		return "relist";
	}

	@SkipValidation
	public String zmienLp() throws Exception {
		Statyczne stronaNew = new StatyczneImp().find(strona);
		Integer oldLp=stronaNew.getLp();
		stronaNew.setLp(strona.getLp());
		new StatyczneImp().update(stronaNew, oldLp);
		return "relist";
	}
	public List<Statyczne> getStatyczne() {
		return statyczne;
	}


	public void setStatyczne(List<Statyczne> statyczne) {
		this.statyczne = statyczne;
	}


	public Statyczne getStrona() {
		return strona;
	}

	@VisitorFieldValidator(message = "błąd: ", key = "blad.strony")
	public void setStrona(Statyczne strona) {
		this.strona = strona;
	}


	public boolean isZmien() {
		return zmien;
	}


	public void setZmien(boolean zmien) {
		this.zmien = zmien;
	}


	public List<Integer> getLpAll() {
		return lpAll;
	}


	public void setLpAll(List<Integer> lpAll) {
		this.lpAll = lpAll;
	}
	

}