package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.arti01.entit.Role;
import org.arti01.entit.User;
import org.arti01.entit.Userfoty;
import org.arti01.sesBean.RoleImp;
import org.arti01.sesBean.UserImp;
import org.arti01.sesBean.UserfotyImp;

@ManagedBean(name="adminAkceptWyklAc")
@SessionScoped
public class AkceptWyklAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AkceptWyklAc.class);
	List<User>wykladowcy=new ArrayList<User>();
	User wykladowca=new User();
	@EJB UserImp ui;
	@EJB RoleImp roleImp;
	@EJB UserfotyImp ufi;
	Userfoty fota;
	

	public String list(){
		Role rola = new Role();
		rola.setRola(Role.WYKLADOWCA);
		wykladowcy=new ArrayList<User>(roleImp.find(rola).getUsers());
		return "userGaleria";
	}

	public void akcept(){
		//logger.info(fota.getDatadodania());
		fota.setAkcept(true);
		ufi.update(fota);
		Role rola = new Role();
		rola.setRola(Role.WYKLADOWCA);
		wykladowcy=new ArrayList<User>(roleImp.find(rola).getUsers());
	}
	
	public void akceptAll(){
		for(Userfoty f:wykladowca.getUserfotyakceptBez()){
			f.setAkcept(true);
			ufi.update(f);
		}
		Role rola = new Role();
		rola.setRola(Role.WYKLADOWCA);
		wykladowcy=new ArrayList<User>(roleImp.find(rola).getUsers());
	}

	public List<User> getWykladowcy() {
		return wykladowcy;
	}

	public void setWykladowcy(List<User> wykladowcy) {
		this.wykladowcy = wykladowcy;
	}


	public Userfoty getFota() {
		return fota;
	}


	public void setFota(Userfoty fota) {
		this.fota = fota;
	}

	public User getWykladowca() {
		return wykladowca;
	}

	public void setWykladowca(User wykladowca) {
		this.wykladowca = wykladowca;
	}
	
	

}