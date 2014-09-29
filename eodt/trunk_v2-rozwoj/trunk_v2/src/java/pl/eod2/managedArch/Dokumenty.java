/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchKontr;
import pl.eod2.encje.DcDokumentJpaController;
import pl.eod2.encje.DcRodzaj;
import pl.eod2.encje.DcRodzajJpaController;
import pl.eod2.managedRej.Rejestracja;

@ManagedBean(name = "DokumentyArch")
@SessionScoped
public class Dokumenty extends AbstMg<DcDokument, DcDokumentJpaController> {

    private DataModel<DcRodzaj> rodzajLista = new ListDataModel<>();
    private DcRodzajJpaController dcR;
    @ManagedProperty(value = "#{RejestracjaRej}")
    private Rejestracja rejestracja;

    public Dokumenty() throws InstantiationException, IllegalAccessException {
        super("/dcarch/dokumenty", new DcDokumentJpaController(), new DcDokument());
    }

    @Override
    public void refresh() throws InstantiationException, IllegalAccessException {
        dcR = new DcRodzajJpaController();
        rodzajLista.setWrappedData(dcR.findDcRodzajArch());
        lista.setWrappedData(dcC.findEntitiesDlaArch());
        obiekt = obiekt.getClass().newInstance();
        error = null;
        rejestracja.setObiekt(null);
    }

    public String detale() {
        return "/dcarch/dokumentDetale?faces-redirect=true";
    }

    public DataModel<DcRodzaj> getRodzajLista() {
        return rodzajLista;
    }

    public void setRodzajLista(DataModel<DcRodzaj> rodzajLista) {
        this.rodzajLista = rodzajLista;
    }

   public Rejestracja getRejestracja() {
        return rejestracja;
    }

    public void setRejestracja(Rejestracja rejestracja) {
        this.rejestracja = rejestracja;
    }

}
