package org.arti01.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.arti01.entit.Boksycfg;

@ManagedBean(name = "adminBackupAc")
@SessionScoped
public class BackupAc {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(IndexAc.class);
    String plikInfo;
    String plikFizycznie;
    String plik;

    public String getPlik() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        HttpServletRequest r=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        logger.info(r.getContextPath()+r.getRemoteAddr()+servletContext.getRealPath("/"));
        plikFizycznie=servletContext.getRealPath("/")+"jkursy.backup";
        plik=r.getContextPath()+"/jkursy.backup";
        return plik;
    }

    public String getPlikInfo() {
        getPlik();
            File file=new File(plikFizycznie);
            if(!file.exists()) {
                plikInfo="plik nie istnieje";
                plik="";
                return plikInfo;
            }
            plik=file.getAbsolutePath();
            String data;
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
            plikInfo=plik+" data modyfikacji "+sdf.format(file.lastModified())+" rozmiar "+file.length();
        return plikInfo;
    }

    public String zrob() {
        getPlik();
        Runtime r = Runtime.getRuntime();
        Process p = null;
        try {
            p = r.exec("pg_dump -Fc slow -f "+plikFizycznie);
            p.waitFor();
            System.out.println("Notepad returned " + p.exitValue());
        } catch (Exception e) {
            logger.info(e);
        }

        
        logger.info("test");
        return "backup";
    }
}