/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.KomKolejka;
import pl.eod.encje.KomKolejkaJpaController;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.WnHistoria;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnStatusy;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;
import pl.eod.managed.Login;

@ManagedBean(name = "UrlopM")
@SessionScoped
public class UrlopM implements Serializable {

    private static final long serialVersionUID = 1L;
    private WnUrlop urlop;
    private DataModel<WnUrlop> urlopyList = new ListDataModel<WnUrlop>();
    private DataModel<WnUrlop> urlopyAkcept = new ListDataModel<WnUrlop>();
    private WnUrlopJpaController urlopC;
    private WnRodzajeJpaController rodzajeC;
    private KomKolejkaJpaController KomKolC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    private Locale locale;

    public String list() {
        initUrlop();
        return "urlopyList";
    }
    
    public String listPodwl() {
        initUrlop();
        return "urlopyListPodwl";
    }

    public void wyslij() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(2));
        urlop.setStatusId(st);
        urlop.setAkceptant(login.getZalogowany().getSzefId().getUserId());
        
        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(2));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(urlop.getUzytkownik());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(login.getZalogowany().getSzefId().getUserId());
        wnh.setOpisZmiany("wysłano do akceptu przełożonemu");
        
        urlop.getWnHistoriaList().add(wnh);

        urlopC.createEdit(urlop);
        
        //wysylanie maila
        KomKolejka kk=new KomKolejka();
        kk.setAdresList(urlop.getAkceptant().getAdrEmail());
        kk.setStatus(0);
        kk.setTemat("prośba o akceptację wniosku urlopowego");
        kk.setTresc("Proszę o akceptację wniosku urlopowego wystawionego przez "+urlop.getUzytkownik().getFullname());
        KomKolC.create(kk);
        
        initUrlop();
        
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("wniosek wysłany");
        context.addMessage(zapisz.getClientId(context), message);

    }

    public void akcept() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(3));
        urlop.setStatusId(st);
        urlop.setAkceptant(null);
        
        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(3));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(login.getZalogowany().getUserId());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(null);
        wnh.setOpisZmiany("Wniosek zaakceptowany");
        
        urlop.getWnHistoriaList().add(wnh);

        urlopC.createEdit(urlop);

        KomKolejka kk=new KomKolejka();
        kk.setAdresList(urlop.getUzytkownik().getAdrEmail());
        kk.setStatus(0);
        kk.setTemat("Wniosek o urlop zaakceptowany");
        kk.setTresc("Twoj wniosek o urlop "+urlop.getNrWniosku()+" został zaakceptowany");
        KomKolC.create(kk);
        
        initUrlop();
        
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("Wniosek zaakceptowany");
        context.addMessage(zapisz.getClientId(context), message);
    }
    
    public void odrzuc() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(4));
        urlop.setStatusId(st);
        urlop.setAkceptant(null);
        
        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(4));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(login.getZalogowany().getUserId());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(null);
        wnh.setOpisZmiany("Wniosek odrzucony");
        
        urlop.getWnHistoriaList().add(wnh);

        urlopC.createEdit(urlop);

        KomKolejka kk=new KomKolejka();
        kk.setAdresList(urlop.getUzytkownik().getAdrEmail());
        kk.setStatus(0);
        kk.setTemat("Wniosek o urlop odrzucony");
        kk.setTresc("Twoj wniosek o urlop "+urlop.getNrWniosku()+" został odrzucony");
        KomKolC.create(kk);
        
        initUrlop();

        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("Wniosek odrzucony");
        context.addMessage(zapisz.getClientId(context), message);

    }
    
    public void cofnij() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(5));
        urlop.setStatusId(st);
        urlop.setAkceptant(null);
        
        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(5));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(login.getZalogowany().getUserId());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(null);
        wnh.setOpisZmiany("Wniosek cofnięty do wystawcy");
        
        urlop.getWnHistoriaList().add(wnh);

        urlopC.createEdit(urlop);
        
        KomKolejka kk=new KomKolejka();
        kk.setAdresList(urlop.getUzytkownik().getAdrEmail());
        kk.setStatus(0);
        kk.setTemat("Wniosek o urlop cofnięty");
        kk.setTresc("Twoj wniosek o urlop "+urlop.getNrWniosku()+" został cofnięty do poprawy");
        KomKolC.create(kk);
        
        initUrlop();

        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("Wniosek cofnięty do wystawcy");
        context.addMessage(zapisz.getClientId(context), message);
    }
    
    public void usun() {
        urlopC.destroy(urlop);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("wniosek usunięty");
        context.addMessage(zapisz.getClientId(context), message);
        initUrlop();
    }

    public void dodaj() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(1));
        urlop.setStatusId(st);
        urlop.setNrWniosku("ddddddddddd");
        urlop.setDataWprowadzenia(new Date());

        String error = null;
        if (urlop.getId() == null) {
            urlop.setWnHistoriaList(new ArrayList<WnHistoria>());

            WnHistoria wnh = new WnHistoria();
            wnh.setDataZmiany(new Date());
            wnh.setStatusId(st);
            wnh.setZmieniajacy(urlop.getUzytkownik());
            wnh.setUrlopId(urlop);
            wnh.setOpisZmiany("wprowadzono nowy wniosek");
            urlop.getWnHistoriaList().add(wnh);
            error = urlopC.createEdit(urlop);
        } else {
            error = urlopC.createEdit(urlop);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        if (error != null) {
            message.setSummary(error);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else {
            initUrlop();
            
            message.setSummary("wniosek zapisany");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        context.addMessage(zapisz.getClientId(context), message);
    }
    

    @PostConstruct
    public void init() {
        urlopC = new WnUrlopJpaController();
        rodzajeC = new WnRodzajeJpaController();
        KomKolC=new KomKolejkaJpaController();
        initUrlop();
    }

    private void initUrlop() {
        urlop = new WnUrlop();
        urlop.setUzytkownik(login.getZalogowany().getUserId());
        urlopyList.setWrappedData(login.getZalogowany().getUserId().getWnUrlopList());
        urlopyAkcept.setWrappedData(login.getZalogowany().getUserId().getWnUrlopListDoAkceptu());
    }

    public WnUrlop getUrlop() {
        return urlop;
    }

    public void setUrlop(WnUrlop urlop) {
        this.urlop = urlop;
    }

    public DataModel<WnUrlop> getUrlopyList() {
        return urlopyList;
    }

    public void setUrlopyList(DataModel<WnUrlop> urlopyList) {
        this.urlopyList = urlopyList;
    }

    public WnRodzajeJpaController getRodzajeC() {
        return rodzajeC;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Locale getLocale() {
        locale = new Locale("pl", "PL");
        return locale;
    }

    public DataModel<WnUrlop> getUrlopyAkcept() {
        return urlopyAkcept;
    }

    public void setUrlopyAkcept(DataModel<WnUrlop> urlopyAkcept) {
        this.urlopyAkcept = urlopyAkcept;
    }
    
}
