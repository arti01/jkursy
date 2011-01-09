package org.arti01.wykladowca;

import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjapliki;
import org.arti01.sesBean.LekacjaImp;
import org.arti01.sesBean.LekcjaplikiImp;
import org.arti01.utility.UploadedFileArti;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletResponse;

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
	private ArrayList<Lekcjapliki> pliki = new ArrayList<Lekcjapliki>();
	private DataModel<Lekcjapliki> plikiMd=new ListDataModel<Lekcjapliki>();
    private int uploadsAvailable = 5;
    private boolean autoUpload = false;
    private boolean useFlash = false;
    private boolean upload = false;
    private boolean trwaProces = false;
    private Lekcja lekcja;
    private Lekcjapliki plik;
	@EJB LekcjaplikiImp lekcjaplikiImp;
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
    	trwaProces=true;
    	logger.info(lekcja.getTytul());
    	for(UploadedFileArti f:files){
        	logger.info(f.getName());
        	logger.info(f.getMime());
            Lekcjapliki plik=new Lekcjapliki();
            plik.setLekcja(lekcja);
            plik.setPlik(f.getData());
            plik.setNazwapliku(f.getName());
            plik.setOpis(f.getName());
            lekcjaplikiImp.insert(plik);
        }
        files.clear();
        setUploadsAvailable(5);
        pokazPliki();
        trwaProces=false;
        return "plikiForm";
    }

    public void download() {
    	
    	final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=\""+plik.getNazwapliku()+"\""); // or whatever type you're sending back
        try {
            response.getOutputStream().write(plik.getPlik()); // from the UploadDetails bean
            response.setContentLength(plik.getPlik().length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            logger.error(e, e);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public String akceptujOpis() {
    	lekcjaplikiImp.update(plik);
    	pliki.clear();
    	lekcja=lekcjaImp.find(lekcja);
    	logger.info(lekcja.getTytul());
    	for(Lekcjapliki lp:lekcja.getLekcjaplikis()){
            pliki.add(lp);
        }
    	return null;
    }
    
    public String pokazPliki() {
    	pliki.clear();
    	lekcja=lekcjaImp.find(lekcja);
    	logger.info(lekcja.getTytul());
    	logger.info(lekcja.getLekcjafoties().size());
    	for(Lekcjapliki lp:lekcja.getLekcjaplikis()){
          //  lf=lekcjafotyImp.find(lf);
            logger.info(lp);
            logger.info(lp.getIdlekcjapliki());
            pliki.add(lp);
        }
        return "fotyForm";
    }
    
    public String usun() {
    	logger.info("usuwanie");
    	lekcjaplikiImp.delete(plik);
    	pokazPliki();
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

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}
	
    public Lekcjapliki getPlik() {
		return plik;
	}

	public void setPlik(Lekcjapliki plik) {
		this.plik = plik;
	}

	public ArrayList<Lekcjapliki> getPliki() {
		return pliki;
	}

	public void setPliki(ArrayList<Lekcjapliki> pliki) {
		this.pliki = pliki;
	}

	public DataModel<Lekcjapliki> getPlikiMd() {
		plikiMd.setWrappedData(pliki);
		return plikiMd;
	}

	public void setPlikiMd(DataModel<Lekcjapliki> plikiMd) {
		this.plikiMd = plikiMd;
	}

	public LekcjaplikiImp getLekcjaplikiImp() {
		return lekcjaplikiImp;
	}

	public void setLekcjaplikiImp(LekcjaplikiImp lekcjaplikiImp) {
		this.lekcjaplikiImp = lekcjaplikiImp;
	}

	public boolean isTrwaProces() {
		return trwaProces;
	}

	public void setTrwaProces(boolean trwaProces) {
		this.trwaProces = trwaProces;
	}

}
