/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarz;

import encje.Admini;
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
import ses.AdminiFacade;

/**
 *
 * @author 103039
 */
@Named(value = "ZarzAdmini")
@SessionScoped
public class ZarzAdmini implements Serializable {

    @EJB
    private AdminiFacade dcC;
    private Admini obiekt;
    private String error;
    private String link;
    private String filtrNazwa;
    private String filtrTelefon;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/zarzad/admini.xhtml";
        refresh();
    }

    public void refresh() {
        obiekt = new Admini();
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

    public Admini getObiekt() {
        return obiekt;
    }

    public void setObiekt(Admini obiekt) {
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

    public String getFiltrTelefon() {
        return filtrTelefon;
    }

    public void setFiltrTelefon(String filtrTelefon) {
        this.filtrTelefon = filtrTelefon;
    }

    public AdminiFacade getDcC() {
        return dcC;
    }

    public void setDcC(AdminiFacade dcC) {
        this.dcC = dcC;
    }
}
