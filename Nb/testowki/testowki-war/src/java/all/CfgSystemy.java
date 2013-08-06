/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package all;

import encje.Systemy;
import excep.DatabaseEx;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import ses.SystemyFacade;

@ManagedBean(name = "CfgSystemy")
@SessionScoped
public class CfgSystemy implements Serializable {

    @EJB
    private SystemyFacade dcC;
    private Systemy obiekt;
    private String error;
    private String link;
    private String filtrNazwa;
    private String filtrTelefony;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/all/systemy.xhtml?faces-redirect=true";
        refresh();
    }

    public void refresh() {
        obiekt = new Systemy();
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

    public void usun() {
        dcC.remove(obiekt);
    }

    public String list() {
        return link;
    }

    public Systemy getObiekt() {
        return obiekt;
    }

    public void setObiekt(Systemy obiekt) {
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

    public String getFiltrNazwa() {
        return filtrNazwa;
    }

    public void setFiltrNazwa(String filtrNazwa) {
        this.filtrNazwa = filtrNazwa;
    }

    public String getFiltrTelefony() {
        return filtrTelefony;
    }

    public void setFiltrTelefony(String filtrTelefony) {
        this.filtrTelefony = filtrTelefony;
    }

    public SystemyFacade getDcC() {
        return dcC;
    }

    public void setDcC(SystemyFacade dcC) {
        this.dcC = dcC;
    }
}
