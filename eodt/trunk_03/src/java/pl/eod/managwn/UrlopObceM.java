/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

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
import pl.eod.encje.KomKolejka;
import pl.eod.encje.KomKolejkaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.WnHistoria;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnStatusy;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;
import pl.eod.managed.Login;

@ManagedBean(name = "UrlopObceM")
@SessionScoped
public class UrlopObceM {

    private WnUrlop urlop;
    private DataModel<WnUrlop> urlopyList = new ListDataModel<WnUrlop>();
    private WnUrlopJpaController urlopC;
    private WnRodzajeJpaController rodzajeC;
    private KomKolejkaJpaController KomKolC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    private Locale locale;

    public String list() {
        initUrlop();
        return "/urlop/urlopyListObce";
    }

    public void wyslij() {
        WnStatusy st = new WnStatusy();
        st.setId(new Long(2));
        urlop.setStatusId(st);
        urlop.setAkceptant(urlop.getUzytkownik().getStruktura().getSzefId().getUserId());
        
        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(2));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(login.getZalogowany().getUserId());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(urlop.getUzytkownik().getStruktura().getSzefId().getUserId());
        wnh.setOpisZmiany("uprawniona osoba wysłała wniosek do akceptu przełożonemu");
        
        urlop.getWnHistoriaList().add(wnh);

        urlopC.createEdit(urlop);
        
        //wysylanie maila
        KomKolejka kk=new KomKolejka();
        if(urlop.getUzytkownik().getStruktura().getExtraemail()!=null) kk.setAdresList(urlop.getAkceptant().getAdrEmail()+","+urlop.getUzytkownik().getStruktura().getExtraemail());
        else kk.setAdresList(urlop.getAkceptant().getAdrEmail());
        kk.setStatus(0);
        kk.setTemat("prośba o akceptację wniosku urlopowego");
        kk.setTresc("Proszę o akceptację wniosku urlopowego "+urlop.getNrWniosku()+" wystawionego przez "+urlop.getUzytkownik().getFullname());
        KomKolC.create(kk);
        
        initUrlop();
        
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context); 
        FacesMessage message = new FacesMessage();
        message.setSummary("uprawniona osoba wysłała wniosek");
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
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        
        if(urlop.getUzytkownik()==null){
            message.setSummary("wybierz pracownika");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(zapisz.getClientId(context), message);
            return;
        }
        WnStatusy st = new WnStatusy();
        st.setId(new Long(1));
        urlop.setStatusId(st);
        //urlop.setNrWniosku("ooooooooo");
        urlop.setDataWprowadzenia(new Date());

        String error = null;
        if (urlop.getId() == null) {
            urlop.setWnHistoriaList(new ArrayList<WnHistoria>());

            WnHistoria wnh = new WnHistoria();
            wnh.setDataZmiany(new Date());
            wnh.setStatusId(st);
            wnh.setZmieniajacy(login.getZalogowany().getUserId());
            wnh.setUrlopId(urlop);
            wnh.setOpisZmiany("uprawniona osoba wprowadziła wniosek");
            urlop.getWnHistoriaList().add(wnh);
            urlop.setPrzyjmujacy(login.getZalogowany().getUserId());
            error = urlopC.createEdit(urlop);
        } else {
            error = urlopC.createEdit(urlop);
        }
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
        login.refresh();
        urlop = new WnUrlop();
        urlop.setUzytkownik(new Uzytkownik());
        urlopyList.setWrappedData(login.getZalogowany().getUserId().getWnUrlopListPrzyjmujacy());
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
    
}
