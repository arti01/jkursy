package pl.eod2.managedArch;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentJpaController;

@ManagedBean(name = "PrzeniesArchOld")
@SessionScoped
public class PrzeniesOld {

    private DcDokument obiekt;
    private DcDokumentJpaController dcC;
    private DataModel<DcDokument> lista = new ListDataModel<>();
    private DataModel<DcDokument> listaWarchiwum = new ListDataModel<>();
    @ManagedProperty(value = "#{login}")
    private Login login;
    private String error;

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
        //refreshObiekt();
    }

    public void refreshObiekt() {
        lista.setWrappedData(dcC.findStatus(3));
        listaWarchiwum.setWrappedData(dcC.findStatus(6));
        obiekt = new DcDokument();
        error = null;
    }

    void refreshBezObiekt() {
        lista.setWrappedData(dcC.findDcDokumentEntities());
        error = null;
    }
    
    public String przenies() {
        dcC.wyslijDoArchiwum(obiekt);
        refreshObiekt();
        return "/dcarch/przeniesList?faces-redirect=true";
    }
    
    public String list() {
        refreshObiekt();
        return "/dcarch/przeniesList";
    }
    
    public String listW() {
        refreshObiekt();
        return "/dcarch/listW";
    }

    public DcDokument getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcDokument obiekt) {
        this.obiekt = obiekt;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataModel<DcDokument> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcDokument> lista) {
        this.lista = lista;
    }

    public DataModel<DcDokument> getListaWarchiwum() {
        return listaWarchiwum;
    }

    public void setListaWarchiwum(DataModel<DcDokument> listaWarchiwum) {
        this.listaWarchiwum = listaWarchiwum;
    }
    
}
