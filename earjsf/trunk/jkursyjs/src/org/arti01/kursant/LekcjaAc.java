package org.arti01.kursant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.arti01.entit.Fotykursantkoment;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Lekcjakoment;
import org.arti01.sesBean.FotykursantkomentImp;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.LekcjaKomentImp;
import org.arti01.sesBean.LekcjafotykursantImp;
import org.arti01.utility.Login;
import org.arti01.utility.ResizeJpg;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@ManagedBean(name="kursantLekcjaAc")
@SessionScoped
public class LekcjaAc implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LekcjaAc.class);

	private Lekcja lekcja;
	@EJB LekacjaImp lekcjaImp;
	private boolean exifPokaz;
	private Lekcjakoment komentarz=new Lekcjakoment();
	@EJB LekcjaKomentImp lekcjaKomentImp;
	@EJB LekcjafotykursantImp lfki;
	@ManagedProperty(value="#{login}") private Login loginBean;
	private Lekcjafotykursant fotaKursant;
	@EJB LekcjafotykursantImp fotaKursantImp;
	private Fotykursantkoment komentarzFota=new Fotykursantkoment();
	@EJB FotykursantkomentImp fkki;
	private boolean next;
	private boolean prev;
	
	private static int DLUGOSC=600;
    private static int WYSOKOSC=400;
    private static int DLUGOSCmin=150;
    private static int WYSOKOSCmin=100;
	
	public String pokaz(){
		exifPokaz=false;
		lekcja=lekcjaImp.find(lekcja);
		return "lekcja?faces-redirect=true";
	}
	public String exifPokazZmien(){
		if(exifPokaz) exifPokaz=false;
		else exifPokaz=true;
		//logger.info(exifPokaz);
		return null;
	}
	
	public String fotyForm(){
		logger.info(lekcja.getLekcjafotykursant());
		return "fotyForm";
	}

	public String fotaKursant(){
		lekcja=fotaKursant.getLekcja();
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)+1>=lekcja.getLekcjafotykursant().size()) next=false;
		else next=true;
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)-1<0) prev=false;
		else prev=true;
		return "fotaKursanta";
	}
	
	public String prevFotaKursant(){
		fotaKursant=lekcja.getLekcjafotykursant().get(lekcja.getLekcjafotykursant().indexOf(fotaKursant)-1);
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)+1>=lekcja.getLekcjafotykursant().size()) next=false;
		else next=true;
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)-1<0) prev=false;
		else prev=true;
		return "fotaKursanta";
	}
	
	public String nextFotaKursant(){
		fotaKursant=lekcja.getLekcjafotykursant().get(lekcja.getLekcjafotykursant().indexOf(fotaKursant)+1);
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)+1>=lekcja.getLekcjafotykursant().size()) next=false;
		else next=true;
		if(lekcja.getLekcjafotykursant().indexOf(fotaKursant)-1<0) prev=false;
		else prev=true;
		return "fotaKursanta";
	}
	
	public void listenerFoty(FileUploadEvent event) {
		logger.info(lekcja.getLekcjafotykursant());
        UploadedFile item = event.getUploadedFile();
        //logger.info(item.getName());
        Lekcjafotykursant fota=new Lekcjafotykursant();
        String exif ="";
        try {
			Metadata metadata = JpegMetadataReader.readMetadata(new ByteArrayInputStream(item.getData()));
			Iterator<?> directories = metadata.getDirectoryIterator();
			while (directories.hasNext()) {
			    Directory directory = (Directory)directories.next();
			    // iterate through tags and print to System.out
			    Iterator<?> tags = directory.getTagIterator();
			    while (tags.hasNext()) {
			        Tag tag = (Tag)tags.next();
			        //System.out.println(tag.getTagName());
			        // use Tag.toString()
			        //tag.toString().c
			        if ((tag.toString().contains("[Exif]"))&&(!tag.toString().contains("Unknown tag"))) exif+=tag+"<br/>";
			    }
			}
		} catch (JpegProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fota.setExif(exif);
        fota.setPlik(new ResizeJpg().zrobB(DLUGOSC, WYSOKOSC, item.getData()));
        fota.setPlikmini(new ResizeJpg().zrobB(DLUGOSCmin, WYSOKOSCmin, item.getData()));
        fota.setUser(loginBean.getZalogowany());
        fota.setLekcja(lekcja);
        fota.setDatadodania(new Timestamp(new Date().getTime()));
        lfki.insert(fota);
        lekcja=lekcjaImp.find(lekcja);
        //logger.info(lekcja.getLekcjafotykursant());
    }
	
	public void paintBazy(OutputStream stream, Object object) throws IOException {
		Lekcjafotykursant lfk=new Lekcjafotykursant();
    	lfk.setIdlekcjafotykursant((Integer)object);
    	stream.write(lfki.find(lfk).getPlikmini());
    }
	public void paintDuza(OutputStream stream, Object object) throws IOException {
		Lekcjafotykursant lfk=new Lekcjafotykursant();
    	lfk.setIdlekcjafotykursant((Integer)object);
    	stream.write(lfki.find(lfk).getPlik());
    }
	
	public String dodajKomentarz(){
		komentarz.setDatadodania(new Timestamp(new Date().getTime()));
		komentarz.setUser(loginBean.getZalogowany());
		komentarz.setLekcja(lekcja);
		lekcjaKomentImp.insert(komentarz);
		lekcja=lekcjaImp.find(lekcja);
		komentarz=new Lekcjakoment();
		return null;
	}
	
	public String dodajKomentarzFota(){
		komentarzFota.setDatadodania(new Timestamp(new Date().getTime()));
		komentarzFota.setUser(loginBean.getZalogowany());
		komentarzFota.setLekcjafotykursant(fotaKursant);
		fotaKursant.getFotykursantkoment().add(0, komentarzFota);
		//fkki.insert(komentarzFota);
		//fotaKursant=fotaKursantImp.find(fotaKursant);
		fotaKursantImp.update(fotaKursant);
		komentarzFota=new Fotykursantkoment();
		return null;
	}
	
	public String usunKomentarzFota(){
		fotaKursant.getFotykursantkoment().remove(komentarzFota);
		//fkki.insert(komentarzFota);
		//fotaKursant=fotaKursantImp.find(fotaKursant);
		fotaKursantImp.update(fotaKursant);
		komentarzFota=new Fotykursantkoment();
		return null;
	}
	
	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public boolean isExifPokaz() {
		return exifPokaz;
	}

	public void setExifPokaz(boolean exifPokaz) {
		this.exifPokaz = exifPokaz;
	}
	public void setKomentarz(Lekcjakoment komentarz) {
		this.komentarz = komentarz;
	}
	public Lekcjakoment getKomentarz() {
		return komentarz;
	}
	public Login getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}
	public LekcjaKomentImp getLekcjaKomentImp() {
		return lekcjaKomentImp;
	}
	public void setLekcjaKomentImp(LekcjaKomentImp lekcjaKomentImp) {
		this.lekcjaKomentImp = lekcjaKomentImp;
	}
	public LekacjaImp getLekcjaImp() {
		return lekcjaImp;
	}
	public void setLekcjaImp(LekacjaImp lekcjaImp) {
		this.lekcjaImp = lekcjaImp;
	}
	public Lekcjafotykursant getFotaKursant() {
		return fotaKursant;
	}
	public void setFotaKursant(Lekcjafotykursant fotaKursant) {
		this.fotaKursant = fotaKursant;
	}
	public Fotykursantkoment getKomentarzFota() {
		return komentarzFota;
	}
	public void setKomentarzFota(Fotykursantkoment komentarzFota) {
		this.komentarzFota = komentarzFota;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}

}