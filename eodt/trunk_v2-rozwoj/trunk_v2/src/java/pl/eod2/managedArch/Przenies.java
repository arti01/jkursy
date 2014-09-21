/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchDane;
import pl.eod2.encje.DcDokumentArchKontr;
import pl.eod2.encje.DcDokumentJpaController;

@ManagedBean(name = "PrzeniesArch")
@SessionScoped
public class Przenies extends AbstMg<DcDokumentArch, DcDokumentArchKontr>{
    public Przenies() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listW", new DcDokumentArchKontr(), new DcDokumentArch());
    }
    private DataModel<DcDokument> listaDoArchiwum = new ListDataModel<>();
    private DcDokumentJpaController dcDokC;
    private DcDokumentArchDane dcDokArchDane=new DcDokumentArchDane();
    private List<DcDokument> wybrane=new ArrayList<>();
    private List<DcDokument> doWybrania=new ArrayList<>();


    @PostConstruct
     public void refreshObiekt() {
        dcDokC = new DcDokumentJpaController();
        listaDoArchiwum.setWrappedData(dcDokC.findStatus(3));
        dcDokArchDane=new DcDokumentArchDane();
        //obiekt = new DcDokument();
        //error = null;
    }

     public String listDo(){
         refreshObiekt();
         return "/dcarch/listDo";
     }
     
    @SuppressWarnings("unchecked")
     public void archPojDok(){
        wybrane.clear();
        dcDokArchDane=new DcDokumentArchDane();
        for(DcDokument dok:(List<DcDokument>)listaDoArchiwum.getWrappedData()){
            if(dok.isDoArchZnacznik()){
                  wybrane.add(dok);         
            }
        }
        doWybrania=(List<DcDokument>) listaDoArchiwum.getWrappedData();
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
        System.err.println(wybrane);
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
      
}