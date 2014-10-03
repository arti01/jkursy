/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchDane;
import pl.eod2.encje.DcDokumentArchKontr;

@ManagedBean(name = "WArchiwumArch")
@SessionScoped
public class WArchiwum extends AbstMg<DcDokumentArch, DcDokumentArchKontr> {

    private List<DcDokumentArch> wybrane = new ArrayList<>();
    private List<DcDokumentArch> doWybrania = new ArrayList<>();
    private String typWyboru = "";
    private DcDokumentArchDane dcDokArchDane = new DcDokumentArchDane();

    public WArchiwum() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listW", new DcDokumentArchKontr(), new DcDokumentArch());
    }

    public void zmianaDanychArch() {
        typWyboru = "zmianaDanych";
        wybrane.clear();
        dcDokArchDane = new DcDokumentArchDane();
        for (DcDokumentArch dok : (List<DcDokumentArch>) lista.getWrappedData()) {
            if (dok.isWyborZnacznik()) {
                wybrane.add(dok);
            }
        }
        doWybrania = (List<DcDokumentArch>) lista.getWrappedData();
    }
    
    public void zmianaDanychArchWykonaj() throws InstantiationException, IllegalAccessException {
        dcDokArchDane.setArchData(new Date());
        for (DcDokumentArch dok : wybrane) {
            dok.setDokArchDane(dcDokArchDane);
            dcC.edit(dok);
        }
        refresh();
    }

    public String detale() {
        return "/dcarch/wArchDetale?faces-redirect=true";
    }

    public List<DcDokumentArch> getWybrane() {
        return wybrane;
    }

    public void setWybrane(List<DcDokumentArch> wybrane) {
        this.wybrane = wybrane;
    }

    public List<DcDokumentArch> getDoWybrania() {
        return doWybrania;
    }

    public void setDoWybrania(List<DcDokumentArch> doWybrania) {
        this.doWybrania = doWybrania;
    }

    public String getTypWyboru() {
        return typWyboru;
    }

    public void setTypWyboru(String typWyboru) {
        this.typWyboru = typWyboru;
    }

    public DcDokumentArchDane getDcDokArchDane() {
        return dcDokArchDane;
    }

    public void setDcDokArchDane(DcDokumentArchDane dcDokArchDane) {
        this.dcDokArchDane = dcDokArchDane;
    }

}
