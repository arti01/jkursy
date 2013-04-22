/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedCfg;

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
import pl.eod2.encje.DcRodzaj;
import pl.eod2.encje.DcRodzajJpaController;
import pl.eod2.encje.DcTypFlow;
import pl.eod2.encje.DcTypFlowJpaController;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "RodzajeCfg")
@SessionScoped
public class Rodzaje {

    private DataModel<DcRodzaj> lista = new ListDataModel<DcRodzaj>();
    private DcRodzajJpaController dcC;
    private DcRodzaj obiekt;
    private String error;
    @ManagedProperty(value = "#{RodzajeGrupyCfg}")
    private RodzajeGrupy rodzajeGrupy;
    private List<DcTypFlow> typFlowLista =new ArrayList<DcTypFlow>();
    private DcTypFlowJpaController dcTypFlowC;

    @PostConstruct
    void init() {
        dcC = new DcRodzajJpaController();
        dcTypFlowC = new DcTypFlowJpaController();
        refresh();
    }

    void refresh() {
        lista.setWrappedData(dcC.findDcRodzajEntities());
        typFlowLista=dcTypFlowC.findDcTypFlowEntities();
        obiekt = new DcRodzaj();
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
            lista.setWrappedData(dcC.findDcRodzajEntities());
        } else {
            refresh();
            rodzajeGrupy.refresh();
        }
    }

    public void usun() throws IllegalOrphanException, NonexistentEntityException {
        //rodzajGrupa=lista.getRowData();
        dcC.destroy(obiekt.getId());
        refresh();
        rodzajeGrupy.refresh();
    }

    public void test() {
        System.err.println("test" + lista.getRowData().getNazwa());
    }

    public String list() {
        refresh();
        return "/dccfg/rodzaje";
    }
    
    public String krokiLista() {
        return "/dccfg/rodzajKroki?faces-redirect=true";
    }

    public DataModel<DcRodzaj> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcRodzaj> lista) {
        this.lista = lista;
    }

    public DcRodzaj getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcRodzaj obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public RodzajeGrupy getRodzajeGrupy() {
        return rodzajeGrupy;
    }

    public void setRodzajeGrupy(RodzajeGrupy rodzajeGrupy) {
        this.rodzajeGrupy = rodzajeGrupy;
    }

    public List<DcTypFlow> getTypFlowLista() {
        return typFlowLista;
    }

    public void setTypFlowLista(List<DcTypFlow> typFlowLista) {
        this.typFlowLista = typFlowLista;
    }

}
