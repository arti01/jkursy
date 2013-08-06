/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package all;

import encje.ZestawyTestowe;
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
import ses.ZestawyTestoweFacade;

/**
 *
 * @author arti01
 */
@Named(value = "CfgZestTest")
@SessionScoped
public class CfgZestTest implements Serializable {

    @EJB
    private ZestawyTestoweFacade dcC;
    private ZestawyTestowe obiekt;
    private String error;
    private String link;
    private String filtrNazwa;
    private String filtrFunkcja;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/all/zestawyTestowe.xhtml?faces-redirect=true";
        refresh();
    }

    public void refresh() {
        obiekt = new ZestawyTestowe();
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

    public ZestawyTestowe getObiekt() {
        return obiekt;
    }

    public void setObiekt(ZestawyTestowe obiekt) {
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

    public String getFiltrFunkcja() {
        return filtrFunkcja;
    }

    public void setFiltrFunkcja(String filtrFunkcja) {
        this.filtrFunkcja = filtrFunkcja;
    }

    public ZestawyTestoweFacade getDcC() {
        return dcC;
    }

    public void setDcC(ZestawyTestoweFacade dcC) {
        this.dcC = dcC;
    }
}
