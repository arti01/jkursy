package org.arti01.wykladowca;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Kursy;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Newsykursy;
import org.arti01.entit.Userfoty;
import org.arti01.sesBean.NewsykursyImp;
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
	
	private static int DLUGOSC=600;
    private static int WYSOKOSC=400;
    private static int DLUGOSCmin=150;
    private static int WYSOKOSCmin=100;
	
	public String lista(){
		
		return "galeria";
	}

	public void listenerFoty(FileUploadEvent event) {
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
        setListaFot(lekcja.getLekcjafotykursant());
        zalogowany=loginBean.getZalogowany();
        zalogowany.setKonkretnaLekcja(lekcja);
        //logger.info(lekcja.getLekcjafotykursant());
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

}