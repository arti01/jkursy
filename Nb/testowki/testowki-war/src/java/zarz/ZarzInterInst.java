/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarz;

import encje.Instancje;
import encje.InterfejsyInstancje;
import excep.DatabaseEx;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import ses.InterfejsyInstancjeFacade;

/**
 *
 * @author arti01
 */
@Named(value = "ZarzInterInst")
@SessionScoped
public class ZarzInterInst implements Serializable {
    @EJB
    private InterfejsyInstancjeFacade dcC;
    private InterfejsyInstancje obiekt;
    private String error;
    private String link;
    private String filtrAdresip;
    private String filtrNazwaserwera;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/zarzad/interInst.xhtml?faces-redirect=true";
        refresh();
    }

    public void refresh() {
        obiekt = new InterfejsyInstancje();
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

    public InterfejsyInstancje getObiekt() {
        return obiekt;
    }

    public void setObiekt(InterfejsyInstancje obiekt) {
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

    public InterfejsyInstancjeFacade getDcC() {
        return dcC;
    }

    public void setDcC(InterfejsyInstancjeFacade dcC) {
        this.dcC = dcC;
    }
    
}
