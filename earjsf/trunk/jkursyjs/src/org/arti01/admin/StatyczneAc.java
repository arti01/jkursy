package org.arti01.admin;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.ScalarDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.Rozne;
import org.arti01.sesBean.StatyczneImp;

public class StatyczneAc {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(StatyczneAc.class);
	
	private DataModel<Statyczne> allStatyczne=new ListDataModel<Statyczne>();
	private Statyczne statyczne;
	private boolean zmien;
	
	@EJB StatyczneImp statImp;

	
	public String form() throws Exception{
		statyczne=allStatyczne.getRowData();
		logger.info(statyczne.getTytul());
		//if (strona != null) zmien = true;
		//strona= new StatyczneImp().find(strona);
		return "statyczneForm";
	}
	
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
			statImp.
		}
			/*try {
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
		}*/
		return "relist";
	}

	public String zmienLp() throws Exception {
		Statyczne stronaNew = new StatyczneImp().find(strona);
		Integer oldLp=stronaNew.getLp();
		stronaNew.setLp(strona.getLp());
		new StatyczneImp().update(stronaNew, oldLp);
		return "relist";
	}

//	@VisitorFieldValidator(message = "błąd: ", key = "blad.strony")
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

	public DataModel<Statyczne> getAllStatyczne() {
		allStatyczne.setWrappedData(statImp.getFindAll());
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
}