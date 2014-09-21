/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentArch;
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

    @PostConstruct
     public void refreshObiekt() {
        dcDokC = new DcDokumentJpaController();
        listaDoArchiwum.setWrappedData(dcDokC.findStatus(3));
        //obiekt = new DcDokument();
        //error = null;
    }

     public String listDo(){
         return "/dcarch/listDo";
     }
     
    public DataModel<DcDokument> getListaDoArchiwum() {
        return listaDoArchiwum;
    }

    public void setListaDoArchiwum(DataModel<DcDokument> listaDoArchiwum) {
        this.listaDoArchiwum = listaDoArchiwum;
    }
    
    
}