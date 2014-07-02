package pl.eod2.managedArc;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentJpaController;

@ManagedBean(name = "PrzeniesArc")
@SessionScoped
public class Przenies {

    private DcDokument obiekt;
    private DcDokumentJpaController dcC;
    private DataModel<DcDokument> lista = new ListDataModel<DcDokument>();
    @ManagedProperty(value = "#{login}")
    private Login login;
    private String error;

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
        refresh();
    }

    public void refresh(){
    }
    
    public String list() {
        return "/dcarc/przeniesList";
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
    
}
