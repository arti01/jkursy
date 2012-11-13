/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.all;

import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import org.arti01.entit.Lekcjafotykursant;
import org.arti01.entit.Userfoty;
import org.arti01.sesBean.GaleriaszkolyFacade;
import org.arti01.sesBean.LekcjafotykursantImp;

/**
 *
 * @author arti01
 */
@Named(value = "galeriaSzkolnaAc")
@SessionScoped
public class GaleriaSzkolnaAc implements Serializable {
@EJB GaleriaszkolyFacade gf;
@EJB LekcjafotykursantImp lfkF;
private Lekcjafotykursant fota;
private Integer nrstrony;

 public String galeriaDetale() {
        nrstrony = (gf.wszystkieGaleriaSzkoly().indexOf(fota)) + 1;
        //logger.info("jestemmmm");
        return "galeriaSzkolyDetale";
    }

public void paintMini(OutputStream stream, Object object) throws IOException {
        Lekcjafotykursant lfkL = new Lekcjafotykursant();
        lfkL.setIdlekcjafotykursant((Integer) object);
        stream.write(lfkF.find(lfkL).getPlikmini());
    }

public void paintDuza(OutputStream stream, Object object) throws IOException {
        Lekcjafotykursant lfkL = new Lekcjafotykursant();
        lfkL.setIdlekcjafotykursant((Integer) object);
        stream.write(lfkF.find(lfkL).getPlik());
    }

    public GaleriaszkolyFacade getGf() {
        return gf;
    }

    public void setGf(GaleriaszkolyFacade gf) {
        this.gf = gf;
    }

    public Lekcjafotykursant getFota() {
        return fota;
    }

    public void setFota(Lekcjafotykursant fota) {
        this.fota = fota;
    }

    public Integer getNrstrony() {
        return nrstrony;
    }

    public void setNrstrony(Integer nrstrony) {
        this.nrstrony = nrstrony;
    }
    


    public GaleriaSzkolnaAc() {
    }
}
