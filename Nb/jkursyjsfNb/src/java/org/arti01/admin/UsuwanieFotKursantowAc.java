/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.crypto.Data;
import org.apache.log4j.Logger;
import org.arti01.entit.Lekcja;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.sesBean.LekacjaImp;

/**
 *
 * @author arti01
 */
@Named(value = "adminUsuwanieFotKursantowAc")
@SessionScoped
public class UsuwanieFotKursantowAc implements Serializable {
    Logger logger = Logger.getLogger(org.arti01.admin.UsuwanieFotKursantowAc.class);
    private DataModel<Lekcja> lekcje = new ListDataModel<Lekcja>();
    @EJB
    LekacjaImp lekcjaImp;
    
   private Lekcjafotykursant fota;

    public Lekcjafotykursant getFota() {
        return fota;
    }

    public void setFota(Lekcjafotykursant fota) {
        this.fota = fota;
    }

    public String lista() {
        return "usuwanieFotKurs";
    }
    
    public String usun() {
        Lekcja lekcja=lekcje.getRowData();
        lekcja.getLekcjafotykursant().remove(fota);
        lekcjaImp.update(lekcja);
        return null;
    }

    public DataModel<Lekcja> getLekcje() {
        List<Lekcja> lek = new ArrayList<Lekcja>();
        for (Lekcja l : lekcjaImp.getFindAll()) {
            if (l.getFotDoUsun().size() > 0) {
                lek.add(l);
            }
        }
        lekcje.setWrappedData(lek);
        return lekcje;
    }

    public void setLekcje(DataModel<Lekcja> lekcje) {
        this.lekcje = lekcje;
    }
}
