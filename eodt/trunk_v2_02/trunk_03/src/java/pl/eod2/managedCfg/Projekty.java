package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcProjekt;
import pl.eod2.encje.DcProjektJpaController;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "ProjektyCfg")
@SessionScoped
public class Projekty {

    private DataModel<DcProjekt> lista;
    private DcProjektJpaController dcC;
    private DcProjekt obiekt;
    private String error;
    private String link;
    
    @PostConstruct
    void init() {
        this.lista = new ListDataModel<DcProjekt>();
        this.dcC = new DcProjektJpaController();
        this.link = "/dccfg/projekty";
        refresh();
    }

    void refresh() {
        lista.setWrappedData(dcC.findEntities());
        obiekt = new DcProjekt();
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

    public DataModel<DcProjekt> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcProjekt> lista) {
        this.lista = lista;
    }

    public DcProjekt getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcProjekt obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
