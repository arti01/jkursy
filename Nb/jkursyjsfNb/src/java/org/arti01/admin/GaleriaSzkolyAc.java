/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.sesBean.GaleriaszkolyFacade;

/**
 *
 * @author arti01
 */
@Named(value = "adminGaleriaSzkolyAc")
@SessionScoped
public class GaleriaSzkolyAc implements Serializable {
    private static final long serialVersionUID = 1L;
@EJB GaleriaszkolyFacade gszf;
String jakieFoty="";
    DataModel<Lekcjafotykursant> foty=new ListDataModel<Lekcjafotykursant>();

    
    public String list(){
        if(jakieFoty.equals("galeria")) {
            foty.setWrappedData(gszf.wszystkieGaleriaSzkoly());
        }
        else if(jakieFoty.equals("brak")) {
            foty.setWrappedData(gszf.wszystkieBezGalerii());
        }
        else foty.setWrappedData(gszf.wszystkieFoty());
            return "galeriaSzkolna";
    }
    
    public String usunZGalerii(){
        gszf.usunZGalerii(foty.getRowData());
        list();
        System.out.print(foty.getRowCount());
        return "galeriaSzkolna";
    }
    
    public String dodajDoGalerii(){
        gszf.dodajDoGalerii(foty.getRowData());
        list();
        System.out.print(foty.getRowCount());
        return "galeriaSzkolna";
    }
    
    public GaleriaSzkolyAc() {
    }

    public GaleriaszkolyFacade getGszf() {
        return gszf;
    }

    public void setGszf(GaleriaszkolyFacade gszf) {
        this.gszf = gszf;
    }

    public String getJakieFoty() {
        return jakieFoty;
    }

    public void setJakieFoty(String jakieFoty) {
        this.jakieFoty = jakieFoty;
    }

    public DataModel<Lekcjafotykursant> getFoty() {
        return foty;
    }

    public void setFoty(DataModel<Lekcjafotykursant> foty) {
        this.foty = foty;
    }
    
}
