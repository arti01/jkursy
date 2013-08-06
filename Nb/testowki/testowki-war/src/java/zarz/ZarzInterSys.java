package zarz;

import encje.InterfejsySystemy;
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
import ses.InterfejsySystemyFacade;

/**
 *
 * @author arti01
 */
@Named(value = "ZarzInterSys")
@SessionScoped
public class ZarzInterSys implements Serializable {

    @EJB
    private InterfejsySystemyFacade dcC;
    private InterfejsySystemy obiekt;
    private String error;
    private String link;
    private String filtrRodzaj;
    private String filtrNazwa;
    private Logger l;

    @PostConstruct
    void init() {
        l = Logger.getLogger(this.getClass().getName());
        this.link = "/zarzad/interfejsySystemy.xhtml?faces-redirect=true";
        refresh();
    }

    public void refresh() {
        obiekt = new InterfejsySystemy();
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

    public InterfejsySystemy getObiekt() {
        return obiekt;
    }

    public void setObiekt(InterfejsySystemy obiekt) {
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

    public String getFiltrRodzaj() {
        return filtrRodzaj;
    }

    public void setFiltrRodzaj(String filtrRodzaj) {
        this.filtrRodzaj = filtrRodzaj;
    }

    public String getFiltrNazwa() {
        return filtrNazwa;
    }

    public void setFiltrNazwa(String filtrNazwa) {
        this.filtrNazwa = filtrNazwa;
    }

    public InterfejsySystemyFacade getDcC() {
        return dcC;
    }

    public void setDcC(InterfejsySystemyFacade dcC) {
        this.dcC = dcC;
    }
}
