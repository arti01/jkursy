package org.arti01.wykladowca;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Lekcjapliki;
import org.arti01.entit.Newsykursy;
import org.arti01.sesBean.KursyImp;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.NewsykursyImp;
import org.arti01.utility.Login;

@ManagedBean(name="wykladKlonAc")
@SessionScoped
public class KlonAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KlonAc.class);
	Kursy kursOld=new Kursy();
	Kursy kursNew=new Kursy();
	List<String> lekcjeOld=new ArrayList<String>();
	List<Kursy> kursy=new ArrayList<Kursy>();
	@EJB KursyImp ki;
	@EJB LekacjaImp li;
	@ManagedProperty(value="#{login}") Login loginBean;
	
	public String wybierz(){
		kursOld=new Kursy();
		lekcjeOld=new ArrayList<String>();
		return "klonwybierz";
	}

	public void lekcjeOld(){
		logger.debug("test");
		kursOld=ki.find(kursOld);
		logger.debug(kursOld.getLekcjas().size());
		for(Kursy k:loginBean.getZalogowany().getKursies()){
			if(k.getIdkursy()!=kursOld.getIdkursy()) kursy.add(k);
		}
	}
	
	public String klonuj(){
		kursNew=ki.find(kursNew);
		logger.debug("klonuj");
		
		for(String ls:lekcjeOld){
			Integer l=new Integer(ls);
			logger.debug(l);
			Lekcja le=new Lekcja();
			le.setIdlekcja(l);
			le=li.find(le);
			Lekcja leN=new Lekcja();
			//Lekcja leN=li.klon(le);
			leN.setDatazmiany(new Timestamp(new Date().getTime()));
			leN.setLekcjafoties(le.getLekcjafoties());
			leN.setTresc(le.getTresc());
			leN.setTytul(le.getTytul());
			leN.setLekcjaplikis(le.getLekcjaplikis());
			leN.setKursy(kursNew);
			List<Lekcjafoty> nowa=new ArrayList<Lekcjafoty>();
			for(Lekcjafoty lf:leN.getLekcjafoties()){
				lf.setLekcja(leN);
				nowa.add(lf);
			}
			leN.getLekcjafoties().clear();
			leN.setLekcjafoties(nowa);
			
			List<Lekcjapliki> nowaP=new ArrayList<Lekcjapliki>();
			for(Lekcjapliki lp:leN.getLekcjaplikis()){
				lp.setLekcja(leN);
				nowaP.add(lp);
			}
			leN.getLekcjaplikis().clear();
			leN.setLekcjaplikis(nowaP);
			li.insert(leN);
		}
		//ki.update(kursNew);
		return "index";
	}
	
	public Kursy getKursOld() {
		return kursOld;
	}

	public void setKursOld(Kursy kursOld) {
		
		this.kursOld = kursOld;
	}

	public Kursy getKursNew() {
		return kursNew;
	}

	public void setKursNew(Kursy kursNew) {
		this.kursNew = kursNew;
	}


	public List<Kursy> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kursy> kursy) {
		this.kursy = kursy;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

	public List<String> getLekcjeOld() {
		return lekcjeOld;
	}

	public void setLekcjeOld(List<String> lekcjeOld) {
		this.lekcjeOld = lekcjeOld;
	}


}