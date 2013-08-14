package pl.eod2.managedRej;

import java.util.ArrayList;
import java.util.Date;
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
import pl.eod.encje.Uzytkownik;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokDoWiadCel;
import pl.eod2.encje.DcDokDoWiadomosci;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentJpaController;
import pl.eod2.encje.DcKontrahenci;
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
    private DcKontrahenci kontrahent;
    private DcDokDoWiadomosci doWiad;
    private DcDokDoWiadCel doWiadCel;
    private Uzytkownik userDoWiad;

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
        dcPlikC = new DcPlikJpaController();
        kontrahent = new DcKontrahenci();
        userDoWiad=new Uzytkownik();
        doWiad=new DcDokDoWiadomosci();
        refresh(true);
    }

    void refresh(boolean obiektTak) {
        lista.setWrappedData(dcC.findDcDokumentEntities());
        if (obiektTak) {
            obiekt = new DcDokument();
        }
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

    public void wyslijDoAkceptacji() {
        obiekt = dcC.wyslijDoAkceptacji(obiekt);
        refresh(false);
    }

    public void edytuj() {
        try {
            error = dcC.edit(obiekt);
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
            error = dcC.edit(obiekt);
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

    public String kontrahentList() {
        refresh(true);
        return "/dcrej/kontrahenci";
    }

    public String list() {
        refresh(true);
        if (kontrahent.getId() != null) {
            obiekt.setKontrahentId(kontrahent);
        }
        kontrahent = new DcKontrahenci();
        return "/dcrej/dokumentList";
    }

    public String detale() {
        return "/dcrej/dokumentDetale?faces-redirect=true";
    }

    public void dodajDoWiadUser() {
        if (doWiad.getDcDokDoWiadCelList() == null) {
            doWiad.setDcDokDoWiadCelList(new ArrayList<DcDokDoWiadCel>());
        }
        DcDokDoWiadCel cel = new DcDokDoWiadCel();
        //userDoWiad=Uc.findUzytkownik(userDoWiad.getId());
        cel.setUserid(userDoWiad);
        cel.setIdDokDoWiad(doWiad);
        doWiad.getDcDokDoWiadCelList().add(cel);
        //usersLista.remove(user);
        userDoWiad = new Uzytkownik();
        System.err.println(cel.getId() + "-" + cel.getIdDokDoWiad() + "-" + cel.getUserid());
    }

    public void usunDoWiadUser() {
        doWiad.getDcDokDoWiadCelList().remove(doWiadCel);
    }

    public void dodajDoWiad() throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (obiekt.getDcDokDoWiadList() == null) {
            obiekt.setDcDokDoWiadList(new ArrayList<DcDokDoWiadomosci>());
        }
        doWiad.setWprowadzil(login.getZalogowany().getUserId());
        doWiad.setDataWprow(new Date());
        doWiad.setDokid(obiekt);
        error = dcC.editDoWiad(obiekt, doWiad);
        System.out.println(obiekt.getDcDokDoWiadList());

        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            //obiekt=dcC.findDcRodzaj(obiekt.getId());
        } else {
            userDoWiad = new Uzytkownik();
            doWiad = new DcDokDoWiadomosci();
        }
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

    public DcKontrahenci getKontrahent() {
        return kontrahent;
    }

    public void setKontrahent(DcKontrahenci kontrahent) {
        this.kontrahent = kontrahent;
    }

    public DcDokDoWiadomosci getDoWiad() {
        return doWiad;
    }

    public void setDoWiad(DcDokDoWiadomosci doWiad) {
        this.doWiad = doWiad;
    }

    public DcDokDoWiadCel getDoWiadCel() {
        return doWiadCel;
    }

    public void setDoWiadCel(DcDokDoWiadCel doWiadCel) {
        this.doWiadCel = doWiadCel;
    }

    public Uzytkownik getUserDoWiad() {
        return userDoWiad;
    }

    public void setUserDoWiad(Uzytkownik userDoWiad) {
        this.userDoWiad = userDoWiad;
    }
}
