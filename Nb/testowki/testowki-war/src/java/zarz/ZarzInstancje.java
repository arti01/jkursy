/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarz;

import encje.Instancje;
import excep.DatabaseEx;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import ses.InstancjeFacade;

@ManagedBean(name = "ZarzInstancje")
@SessionScoped
public class ZarzInstancje implements Serializable {

    @EJB
    private InstancjeFacade dcC;
    private Instancje obiekt;
    private String error;
    private String link;
    private String filtrAdresip;
    private String filtrNazwaserwera;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/zarzad/instancje.xhtml?faces-redirect=true";
        refresh();
    }

    public void refresh() {
        obiekt = new Instancje();
        error = null;
    }

    public void dodaj() {
        try {
            dcC.create(obiekt);
        } catch (DatabaseEx ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "pewnie powtórzona nazwa");
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        }
    }

    public void edytuj() {
        try {
            dcC.edit(obiekt);
        } catch (DatabaseEx ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "pewnie powtórzona nazwa");
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        }
    }

    public void usun() throws DatabaseEx {
        dcC.remove(obiekt);
    }

    public String list() {
        return link;
    }

    public Instancje getObiekt() {
        return obiekt;
    }

    public void setObiekt(Instancje obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFiltrAdresip() {
        return filtrAdresip;
    }

    public void setFiltrAdresip(String filtrAdresip) {
        this.filtrAdresip = filtrAdresip;
    }

    public String getFiltrNazwaserwera() {
        return filtrNazwaserwera;
    }

    public void setFiltrNazwaserwera(String filtrNazwaserwera) {
        this.filtrNazwaserwera = filtrNazwaserwera;
    }

    public InstancjeFacade getDcC() {
        return dcC;
    }

    public void setDcC(InstancjeFacade dcC) {
        this.dcC = dcC;
    }
}
