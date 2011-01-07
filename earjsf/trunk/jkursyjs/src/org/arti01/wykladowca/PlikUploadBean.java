package org.arti01.wykladowca;

import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafoty;
import org.arti01.entit.Lekcjapliki;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.LekcjafotyImp;
import org.arti01.sesBean.LekcjaplikiImp;
import org.arti01.utility.ResizeJpg;
import org.arti01.utility.UploadedFileArti;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * @author Ilya Shaikovsky
 * 
 */
@ManagedBean(name="wykladPlikUploadBean")
@SessionScoped
public class PlikUploadBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PlikUploadBean.class);
	private ArrayList<UploadedFileArti> files = new ArrayList<UploadedFileArti>();
	private ArrayList<Lekcjafoty> foty = new ArrayList<Lekcjafoty>();
	private DataModel<Lekcjafoty> fotyMd=new ListDataModel<Lekcjafoty>();
    private int uploadsAvailable = 5;
    private boolean autoUpload = false;
    private boolean useFlash = false;
    private boolean upload = false;
    private Lekcja lekcja;
    private Lekcjapliki plik;
	@EJB LekcjaplikiImp LekcjaplikiImp;
	@EJB LekacjaImp lekcjaImp;
    
    
    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public String pokazUpload(){
    	if(upload) upload=false;
    	else upload=true;
    	logger.info(upload);
    	return null;
    }
    
	
    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        logger.info(item.getName());
        UploadedFileArti file = new UploadedFileArti();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        logger.info(file.getName());
        logger.info(file.getData());
        files.add(file);
        for(UploadedFileArti f:files){
        	logger.info(f.getName());
        	logger.info(f.getMime());
        }
        uploadsAvailable--;
    }

    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(5);
        return null;
    }
    
    public String akceptuj() {
    	logger.info(lekcja.getTytul());
    	for(UploadedFileArti f:files){
        	logger.info(f.getName());
        	logger.info(f.getMime());
            Lekcjafoty fota=new Lekcjafoty();
            fota.setLekcja(lekcja);
            fota.setData(new ResizeJpg().zrobB(900, 600, f.getData()));
            fota.setPlikmini(new ResizeJpg().zrobB(150, 100, f.getData()));
            lekcjafotyImp.insert(fota);
        }
        files.clear();
        setUploadsAvailable(5);
        pokazFoty();
        return "fotyForm";
    }

    public String akceptujOpis() {
    	lekcjafotyImp.update(fota);
    	foty.clear();
    	lekcja=lekcjaImp.find(lekcja);
    	logger.info(lekcja.getTytul());
    	logger.info(lekcja.getLekcjafoties().size());
    	for(Lekcjafoty lf:lekcja.getLekcjafoties()){
            lf=lekcjafotyImp.find(lf);
            foty.add(lf);
        }
    	return "fotyForm";
    }
    public String pokazFoty() {
    	foty.clear();
    	lekcja=lekcjaImp.find(lekcja);
    	logger.info(lekcja.getTytul());
    	logger.info(lekcja.getLekcjafoties().size());
    	for(Lekcjafoty lf:lekcja.getLekcjafoties()){
            lf=lekcjafotyImp.find(lf);
            logger.info(lf);
            logger.info(lf.getIdlekcjafoty());
            foty.add(lf);
        }
        return "fotyForm";
    }
    
    public String usun() {
    	lekcjafotyImp.delete(fota);
    	pokazFoty();
    	return null;
    }

    
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<UploadedFileArti> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<UploadedFileArti> files) {
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public ArrayList<Lekcjafoty> getFoty() {
		return foty;
	}

	public void setFoty(ArrayList<Lekcjafoty> foty) {
		this.foty = foty;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public DataModel<Lekcjafoty> getFotyMd() {
		fotyMd.setWrappedData(foty);
		return fotyMd;
	}

	public void setFotyMd(DataModel<Lekcjafoty> fotyMd) {
		this.fotyMd = fotyMd;
	}
	
    public LekcjaplikiImp getLekcjaplikiImp() {
		return LekcjaplikiImp;
	}

	public void setLekcjaplikiImp(LekcjaplikiImp lekcjaplikiImp) {
		LekcjaplikiImp = lekcjaplikiImp;
	}
    public Lekcjapliki getPlik() {
		return plik;
	}

	public void setPlik(Lekcjapliki plik) {
		this.plik = plik;
	}

}
