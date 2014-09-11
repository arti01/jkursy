package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcTeczka;
import pl.eod2.encje.DcTeczkaJpaController;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "TeczkiCfg")
@SessionScoped
public class Teczki {

    private DataModel<DcTeczka> lista;
    private DcTeczkaJpaController dcC;
    private DcTeczka obiekt;
    private String error;
    private String link;
    
    @PostConstruct
    void init() {
        this.lista = new ListDataModel<>();
        this.dcC = new DcTeczkaJpaController();
        this.link = "/dccfg/teczki";
        refresh();
    }

    void refresh() {
        lista.setWrappedData(dcC.findEntities());
        obiekt = new DcTeczka();
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
            lista.setWrappedData(dcC.findEntities());
        } else {
            refresh();
        }
    }

    public void usun() throws IllegalOrphanException, NonexistentEntityException {
        //rodzajGrupa=lista.getRowData();
        dcC.destroy(obiekt.getId());
        refresh();
    }

    public String list() {
        refresh();
        return link;
    }

    public DataModel<DcTeczka> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcTeczka> lista) {
        this.lista = lista;
    }

    public DcTeczka getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcTeczka obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
