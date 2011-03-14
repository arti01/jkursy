package org.arti01.admin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Newsy;
import org.arti01.sesBean.NewsyImp;
import org.arti01.sesBean.RoleImp;
import org.arti01.utility.ResizeJpg;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@ManagedBean(name="adminNewsyAc")
@SessionScoped
public class NewsyAc implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(NewsyAc.class);
	
	private DataModel<Newsy> allNewsy=new ListDataModel<Newsy>();
	private Newsy news;
	@EJB NewsyImp newsyImp;
	@EJB RoleImp roleImp;
	private List<String> rolesName=new ArrayList<String>();
	private String rola;
	
	private static int DLUGOSC=300;
    private static int WYSOKOSC=200;
	
	public String form(){
		news=allNewsy.getRowData();
		return "newsyForm";
	}
	
	public String formNew(){
		news=new Newsy();
		return "newsyForm";
	}
	
	public String listNewsy(){
		//allStatyczne.setWrappedData(statImp.getFindAll());
		return "newsyLista";
	}
	
	
	public String dodaj() throws Exception {
		news.setDatadodania(new Date());
		if (news.getIdnewsy()!=null) {// edycja
			logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
			newsyImp.update(news);
		}
		else{
			newsyImp.insert(news);			
		}
		return "newsyLista";
	}
	
	public String usun() throws Exception {
		news=allNewsy.getRowData();
		newsyImp.delete(news);
		return "newsyLista";
	}

	public void listenerFoty(FileUploadEvent event) {
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
		news.setFota(new ResizeJpg().zrobB(DLUGOSC, WYSOKOSC, item.getData()));
        newsyImp.update(news);
    }
	
	public void paintFota(OutputStream stream, Object object) throws IOException {
		logger.info(object);
		news=new Newsy();
		news.setIdnewsy((Integer) object);
		news=newsyImp.find(news);
    	stream.write(news.getFota());
    }
	
	public String usunFote(){
		news.setFota(null);
		newsyImp.update(news);
		return "newsyForm";
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

	public DataModel<Newsy> getAllNewsy() {
		allNewsy.setWrappedData(newsyImp.getAll());
		return allNewsy;
	}

	public void setAllNewsy(DataModel<Newsy> allNewsy) {
		this.allNewsy = allNewsy;
	}

	public NewsyImp getNewsyImp() {
		return newsyImp;
	}

	public void setNewsyImp(NewsyImp newsyImp) {
		this.newsyImp = newsyImp;
	}

	public Newsy getNews() {
		return news;
	}

	public void setNews(Newsy news) {
		this.news = news;
	}
}