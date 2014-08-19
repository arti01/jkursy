package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.Struktura;
import pl.eod.managed.Login;
import pl.eod2.encje.Ogloszenia;
import pl.eod2.encje.OgloszeniaJpaController;
import pl.eod2.encje.UmGrupa;
import pl.eod2.encje.UmMasterGrupa;
import pl.eod2.encje.UmUrzadzenie;
import pl.eod2.encje.UmUrzadzenieJpaController;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "UrzadzeniaMg")
@SessionScoped
public class UrzadzeniaMg {

    @ManagedProperty(value = "#{login}")
    private Login login;
    private DataModel<UmUrzadzenie> lista = new ListDataModel<UmUrzadzenie>();
    private List<UmGrupa> grupyList=new ArrayList<UmGrupa>();
    private UmUrzadzenieJpaController dcC;
    private UmUrzadzenie obiekt;
    private String error;

    @PostConstruct
    void init() {
        dcC = new UmUrzadzenieJpaController();
        refresh();
    }

    public void refresh() {
        login.refresh();
        lista.setWrappedData(dcC.findUmUrzadzenieEntities());
        obiekt = new UmUrzadzenie();
        grupyList.clear();
        for(UmMasterGrupa mg:login.getZalogowany().getUserId().getSpolkaId().getUmMasterGrupaList()){
            grupyList.addAll(mg.getGrupaList());
        }
        error = null;
    }

    public void dodaj() {
        error = dcC.create(obiekt);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        } else {
            refresh();
        }
    }

    public void edytuj() throws IllegalOrphanException, NonexistentEntityException, Exception {
        error=dcC.edit(obiekt);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            lista.setWrappedData(dcC.findUmUrzadzenieEntities());
        } else {
            refresh();
        }
    }

    public void usun() throws IllegalOrphanException, NonexistentEntityException {
        dcC.destroy(obiekt.getId());
        refresh();
    }

    public String list() {
        refresh();
        return "/um/urzadzenie";
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public DataModel<UmUrzadzenie> getLista() {
        return lista;
    }

    public void setLista(DataModel<UmUrzadzenie> lista) {
        this.lista = lista;
    }

    public UmUrzadzenie getObiekt() {
        return obiekt;
    }

    public void setObiekt(UmUrzadzenie obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<UmGrupa> getGrupyList() {
        return grupyList;
    }

    public void setGrupyList(List<UmGrupa> grupyList) {
        this.grupyList = grupyList;
    }
    
}

