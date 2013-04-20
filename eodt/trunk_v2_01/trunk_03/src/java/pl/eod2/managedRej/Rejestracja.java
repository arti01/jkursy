package pl.eod2.managedRej;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentJpaController;
import pl.eod2.encje.DcPlik;
import pl.eod2.encje.DcPlikJpaController;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "RejestracjaRej")
@SessionScoped
public class Rejestracja {

    private DataModel<DcDokument> lista = new ListDataModel<DcDokument>();
    private DcDokumentJpaController dcC;
    private DcPlikJpaController dcPlikC;
    private DcDokument obiekt;
    private DcPlik plik;
    private String error;
    @ManagedProperty(value = "#{login}")
    private Login login;

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
        dcPlikC = new DcPlikJpaController();
        refresh(true);
    }

    void refresh(boolean obiektTak) {
        lista.setWrappedData(dcC.findDcDokumentEntities());
        if(obiektTak) obiekt = new DcDokument();
        error = null;
    }

    public void dodaj() {
        obiekt.setUserId(login.getZalogowany().getUserId());
        error = dcC.create(obiekt);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        } else {
            refresh(true);
        }
    }
    
    

    public void edytuj() {
        try {
            error=dcC.edit(obiekt);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.err.println(error);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            lista.setWrappedData(dcC.findDcDokumentEntities());
        } else {
            refresh(true);
        }
    }
    
    public void edytujZdetale() {
        try {
            error=dcC.edit(obiekt);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Rejestracja.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.err.println(error);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            lista.setWrappedData(dcC.findDcDokumentEntities());
        } else {
            refresh(false);
        }
    }

    public void usun() throws IllegalOrphanException, NonexistentEntityException {
        dcC.destroy(obiekt.getId());
        refresh(true);
    }
    
    public void usunPlik() throws IllegalOrphanException, NonexistentEntityException {
        dcPlikC.destroy(plik.getId());
        obiekt.getDcPlikList().remove(plik);
        edytujZdetale();
    }

    public String list() {
        refresh(true);
        return "/dcrej/dokumentList";
    }
    
    public String detale() {
        return "/dcrej/dokumentDetale?faces-redirect=true";
    }

    public DataModel<DcDokument> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcDokument> lista) {
        this.lista = lista;
    }

    public DcDokument getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcDokument obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }    

    public DcDokumentJpaController getDcC() {
        return dcC;
    }

    public void setDcC(DcDokumentJpaController dcC) {
        this.dcC = dcC;
    }

    public DcPlikJpaController getDcPlikC() {
        return dcPlikC;
    }

    public void setDcPlikC(DcPlikJpaController dcPlikC) {
        this.dcPlikC = dcPlikC;
    }

    public DcPlik getPlik() {
        return plik;
    }

    public void setPlik(DcPlik plik) {
        this.plik = plik;
    }
    
}
