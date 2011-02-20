package org.arti01.wykladowca;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Newsykursy;
import org.arti01.entit.User;
import org.arti01.entit.Userfoty;
import org.arti01.sesBean.NewsykursyImp;
import org.arti01.sesBean.UserImp;
import org.arti01.sesBean.UserfotyImp;
import org.arti01.utility.Login;
import org.arti01.utility.ResizeJpg;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@ManagedBean(name="wykladGaleriaAc")
@SessionScoped
public class GaleriaAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(GaleriaAc.class);
	private DataModel<Userfoty> fotyAll=new ListDataModel<Userfoty>();
	@ManagedProperty(value="#{login}") private Login loginBean;
	private Userfoty fota=new Userfoty();
	@EJB UserfotyImp ufi;
	@EJB UserImp ui;
	List<Integer> allLp=new ArrayList<Integer>();
	
	private static int DLUGOSC=600;
    private static int WYSOKOSC=400;
    private static int DLUGOSCmin=150;
    private static int WYSOKOSCmin=100;
	
	public String lista(){
		//logger.info("==============");
		allLp=new ArrayList<Integer>();
		User user=loginBean.getZalogowany();
		for(Userfoty uf:ui.find(user).getUserfoty()){
			allLp.add(uf.getLp());
		}
		fotyAll.setWrappedData(ui.find(user).getUserfoty());
		return "galeria";
	}

	public void zmien(){
		//logger.info("==============");
		fota=fotyAll.getRowData();
		ufi.update(fota);
		logger.info(fota.getOpis());
	}
	
	public void zmienLp(ValueChangeEvent event) throws IOException{
		fota=fotyAll.getRowData();
		ufi.update(fota, (Integer)event.getNewValue());
		fotyAll=new ListDataModel<Userfoty>();
		User user=loginBean.getZalogowany();
		fotyAll.setWrappedData(ui.find(user).getUserfoty());
		//FacesContext.getCurrentInstance().getExternalContext().redirect("galeria.xhtml");
	}
	
	public void usunFote(){
		fota=fotyAll.getRowData();
		ufi.delete(fota);
		User user=loginBean.getZalogowany();
		fotyAll.setWrappedData(ui.find(user).getUserfoty());
	}
	
	public void listenerFoty(FileUploadEvent event) {
		//logger.info("==============");
        UploadedFile item = event.getUploadedFile();
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
        fota.setDatadodania(new Timestamp(new Date().getTime()));
        fota.setAkcept(false);
        fota.setOpis(null);
        ufi.insert(fota);
        allLp=new ArrayList<Integer>();
		User user=loginBean.getZalogowany();
		for(Userfoty uf:ui.find(user).getUserfoty()){
			allLp.add(uf.getLp());
		}
        fotyAll.setWrappedData(loginBean.getZalogowany().getUserfoty());
        //logger.info(lekcja.getLekcjafotykursant());
    }
	
	public void paintMini(OutputStream stream, Object object) throws IOException {
		Userfoty uf=new Userfoty();
		uf.setIduserfoty((Integer) object);
    	stream.write(ufi.find(uf).getPlikmini());
    }
	
	public void paintDuza(OutputStream stream, Object object) throws IOException {
		Userfoty uf=new Userfoty();
		uf.setIduserfoty((Integer) object);
    	stream.write(ufi.find(uf).getPlik());
    }
	
	public DataModel<Userfoty> getFotyAll() {
		return fotyAll;
	}

	public void setFotyAll(DataModel<Userfoty> fotyAll) {
		this.fotyAll = fotyAll;
	}

	public Login getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}

	public Userfoty getFota() {
		return fota;
	}

	public void setFota(Userfoty fota) {
		this.fota = fota;
	}

	public List<Integer> getAllLp() {
		return allLp;
	}

	public void setAllLp(List<Integer> allLp) {
		this.allLp = allLp;
	}
}