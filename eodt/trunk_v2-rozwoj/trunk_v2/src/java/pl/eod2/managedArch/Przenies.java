/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcAkceptStatus;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchDane;
import pl.eod2.encje.DcDokumentJpaController;
import pl.eod2.encje.DcDokumentStatus;
import pl.eod2.encje.DcTeczka;
import pl.eod2.encje.DcTeczkaKontr;

@ManagedBean(name = "PrzeniesArch")
@SessionScoped
public class Przenies {

    private DataModel<DcDokument> listaDoArchiwum = new ListDataModel<>();
    private DcDokumentJpaController dcDokC;
    private DcTeczkaKontr dcTeczC;
    private DcDokumentArchDane dcDokArchDane = new DcDokumentArchDane();
    private List<DcDokument> wybrane = new ArrayList<>();
    private List<DcDokument> doWybrania = new ArrayList<>();
    private List<DcTeczka> doWybraniaTeczki = new ArrayList<>();
    private List<DcTeczka> wybraneTeczki = new ArrayList<>();
    private String typWyboru = "";

    @PostConstruct
    public void refreshObiekt() {
        dcDokC = new DcDokumentJpaController();
        dcTeczC = new DcTeczkaKontr();
        listaDoArchiwum.setWrappedData(dcDokC.findStatus(3));
        dcDokArchDane = new DcDokumentArchDane();
        typWyboru = "";
        //obiekt = new DcDokument();
        //error = null;
    }

    public String list() {
        refreshObiekt();
        return "/dcarch/listDo";
    }

    @SuppressWarnings("unchecked")
    public void archPojDok() {
        typWyboru = "pojedyncze";
        wybrane.clear();
        dcDokArchDane = new DcDokumentArchDane();
        for (DcDokument dok : (List<DcDokument>) listaDoArchiwum.getWrappedData()) {
            if (dok.isDoArchZnacznik()) {
                wybrane.add(dok);
            }
        }
        doWybrania = (List<DcDokument>) listaDoArchiwum.getWrappedData();
    }

    public void archTeczki() {
        typWyboru = "teczki";
        wybraneTeczki.clear();
        doWybraniaTeczki = dcTeczC.findDlaStatus(new DcDokumentStatus(3));
    }

    public void przeniesPoj() {
        dcDokArchDane.setArchData(new Date());
        for (DcDokument dok : wybrane) {
            DcDokumentArch dokArch = new DcDokumentArch(dok);
            dokArch.setDokArchDane(dcDokArchDane);
            dcDokC.przeniesDoArchiwum(dok, dokArch);
        }
        refreshObiekt();
    }

    public void przeniesTeczki() {
        dcDokArchDane.setArchData(new Date());
        for (DcTeczka tc : wybraneTeczki) {
            for (DcDokument dok : tc.getDcDokumentListDoArch()) {
                DcDokumentArch dokArch = new DcDokumentArch(dok);
                dokArch.setDokArchDane(dcDokArchDane);
                dcDokC.przeniesDoArchiwum(dok, dokArch);
            }
        }
        refreshObiekt();
    }

    public DataModel<DcDokument> getListaDoArchiwum() {
        return listaDoArchiwum;
    }

    public void setListaDoArchiwum(DataModel<DcDokument> listaDoArchiwum) {
        this.listaDoArchiwum = listaDoArchiwum;
    }

    public DcDokumentArchDane getDcDokArchDane() {
        return dcDokArchDane;
    }

    public void setDcDokArchDane(DcDokumentArchDane dcDokArchDane) {
        this.dcDokArchDane = dcDokArchDane;
    }

    public List<DcDokument> getWybrane() {
        return wybrane;
    }

    public void setWybrane(List<DcDokument> wybrane) {
        this.wybrane = wybrane;
    }

    public List<DcDokument> getDoWybrania() {
        return doWybrania;
    }

    public void setDoWybrania(List<DcDokument> doWybrania) {
        this.doWybrania = doWybrania;
    }

    public String getTypWyboru() {
        return typWyboru;
    }

    public void setTypWyboru(String typWyboru) {
        this.typWyboru = typWyboru;
    }

    public List<DcTeczka> getDoWybraniaTeczki() {
        return doWybraniaTeczki;
    }

    public void setDoWybraniaTeczki(List<DcTeczka> doWybraniaTeczki) {
        this.doWybraniaTeczki = doWybraniaTeczki;
    }

    public List<DcTeczka> getWybraneTeczki() {
        return wybraneTeczki;
    }

    public void setWybraneTeczki(List<DcTeczka> wybraneTeczki) {
        this.wybraneTeczki = wybraneTeczki;
    }

}
