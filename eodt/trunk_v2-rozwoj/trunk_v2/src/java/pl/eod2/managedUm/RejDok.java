package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcRodzaj;
import pl.eod2.encje.DcRodzajJpaController;
import pl.eod2.managedRej.Rejestracja;

@ManagedBean(name = "URejDokMg")
@SessionScoped
public class RejDok {

    private DataModel<DcDokument> lista = new ListDataModel<DcDokument>();
    private DataModel<DcRodzaj> rodzajLista = new ListDataModel<DcRodzaj>();
    @ManagedProperty(value = "#{login}")
    private Login login;
    @ManagedProperty(value = "#{RejestracjaRej}")
    private Rejestracja rejestracja;
    private DcRodzajJpaController dcR;
    
    @PostConstruct
    void init() {
    }

    @SuppressWarnings("unchecked")
    void refresh(){
        dcR=new DcRodzajJpaController();
        rodzajLista.setWrappedData(dcR.findDcRodzajUm());
        List<DcDokument>listD=new ArrayList<DcDokument>();
        for(DcRodzaj rodz: (List<DcRodzaj>) rodzajLista.getWrappedData()){
            listD.addAll(rodz.getDcDokumentList());
        }
        lista.setWrappedData(listD);
        rejestracja.setObiekt(null);
    }
    
    public String list() {
        refresh();
        return "/um/dokumenty";
    }

    public void dodaj() throws Exception {
        if (rejestracja.dodajAbst()) {
            refresh();
        }
    }
    
    public void edytuj(){
        if (rejestracja.edytujAbst()) {
            refresh();
        }
    }
    
    public String detale() {
        return "/um/dokumentDetale?faces-redirect=true";
    }
    
    public DataModel<DcDokument> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcDokument> lista) {
        this.lista = lista;
    }

    public DataModel<DcRodzaj> getRodzajLista() {
        return rodzajLista;
    }

    public void setRodzajLista(DataModel<DcRodzaj> rodzajLista) {
        this.rodzajLista = rodzajLista;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Rejestracja getRejestracja() {
        return rejestracja;
    }

    public void setRejestracja(Rejestracja rejestracja) {
        this.rejestracja = rejestracja;
    }

}
