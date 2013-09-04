package pl.eod2.managedRej;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
import pl.eod2.encje.DcDokumentStatus;
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
    private Locale locale;
    @ManagedProperty(value = "#{login}")
    private Login login;
    private DcKontrahenci kontrahent;
    private DcDokDoWiadomosci doWiad;
    private DcDokDoWiadCel doWiadCel;
    private Uzytkownik userDoWiad;
    private String filtrdataWprow;
    private String filtrNazwa;
    private String filtrdataDok;
    private String filtrKontrahent;
    private String filtrZrodlo;
    private String filtrStatus;
    private String filtrWprowadzil;
    private String filtrRodzaj;
    private String filtrProjekt;

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
        dcPlikC = new DcPlikJpaController();
        kontrahent = new DcKontrahenci();
        userDoWiad=new Uzytkownik();
        doWiad=new DcDokDoWiadomosci();
        refreshObiekt();
    }

    public void refreshObiekt() {
        lista.setWrappedData(dcC.findDcDokumentEntities());
            obiekt = new DcDokument();
        error = null;
    }
    void refreshBezObiekt() {
        lista.setWrappedData(dcC.findDcDokumentEntities());
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
            refreshObiekt();
        }
    }

    public void wyslijDoAkceptacji() {
        obiekt = dcC.wyslijDoAkceptacji(obiekt);
        refreshBezObiekt();
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
            refreshObiekt();
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
            refreshBezObiekt();
        }
    }

    public void usun() throws IllegalOrphanException, NonexistentEntityException {
        dcC.destroy(obiekt.getId());
        refreshObiekt();
    }

    public void anuluj() throws IllegalOrphanException, NonexistentEntityException, Exception {
        DcDokumentStatus ds=new DcDokumentStatus(4);
        obiekt.setDokStatusId(ds);
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
            refreshObiekt();
        }
    }
    
    public void usunPlik() throws IllegalOrphanException, NonexistentEntityException {
        dcPlikC.destroy(plik.getId());
        obiekt.getDcPlikList().remove(plik);
        edytujZdetale();
    }

    public String kontrahentList() {
        refreshObiekt();
        return "/dcrej/kontrahenci";
    }

    public String list() {
        refreshObiekt();
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

    public String getFiltrdataWprow() {
        return filtrdataWprow;
    }

    public void setFiltrdataWprow(String filtrdataWprow) {
        this.filtrdataWprow = filtrdataWprow;
    }

    public String getFiltrNazwa() {
        return filtrNazwa;
    }

    public void setFiltrNazwa(String filtrNazwa) {
        this.filtrNazwa = filtrNazwa;
    }

    public String getFiltrdataDok() {
        return filtrdataDok;
    }

    public void setFiltrdataDok(String filtrdataDok) {
        this.filtrdataDok = filtrdataDok;
    }

    public String getFiltrKontrahent() {
        return filtrKontrahent;
    }

    public void setFiltrKontrahent(String filtrKontrahent) {
        this.filtrKontrahent = filtrKontrahent;
    }

    public String getFiltrZrodlo() {
        return filtrZrodlo;
    }

    public void setFiltrZrodlo(String filtrZrodlo) {
        this.filtrZrodlo = filtrZrodlo;
    }

    public String getFiltrStatus() {
        return filtrStatus;
    }

    public void setFiltrStatus(String filtrStatus) {
        this.filtrStatus = filtrStatus;
    }

    public String getFiltrWprowadzil() {
        return filtrWprowadzil;
    }

    public void setFiltrWprowadzil(String filtrWprowadzil) {
        this.filtrWprowadzil = filtrWprowadzil;
    }

    public String getFiltrRodzaj() {
        return filtrRodzaj;
    }

    public void setFiltrRodzaj(String filtrRodzaj) {
        this.filtrRodzaj = filtrRodzaj;
    }

    public String getFiltrProjekt() {
        return filtrProjekt;
    }

    public void setFiltrProjekt(String filtrProjekt) {
        this.filtrProjekt = filtrProjekt;
    }

    public Locale getLocale() {
        locale = new Locale("pl", "PL");
        return locale;
    }
}
