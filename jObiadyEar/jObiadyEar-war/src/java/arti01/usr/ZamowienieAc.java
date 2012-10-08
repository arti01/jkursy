/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.usr;

import arti01.jobiady.beany.Menu;
import arti01.jobiady.beany.MenuFacade;
import arti01.jobiady.beany.StatusZamowienia;
import arti01.jobiady.beany.Uzytkownik;
import arti01.jobiady.beany.UzytkownikFacade;
import arti01.jobiady.beany.Zamowienie;
import arti01.jobiady.beany.ZamowienieFacade;
import arti01.trag.KursyAc;
import arti01.utils.Login;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 103039
 */
@ManagedBean(name = "usrZamowienieAc")
@SessionScoped
public class ZamowienieAc implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private MenuFacade mf;
    
    @ManagedProperty(value = "#{login}")
    private arti01.utils.Login login;
    @ManagedProperty(value = "#{tragKursyAc}")
    KursyAc kursyAc;
    
    
    @EJB
    UzytkownikFacade uf;
    
    Zamowienie zamowienie;
    @EJB
    ZamowienieFacade zf;
    
    private List<Menu> dostepneMenu;
    
    private Menu menu;

    public String lista() {
        return "zamowieniaLista";
    }

    public String dodaj() {
        Uzytkownik u=login.getZalogowany();
        zamowienie = new Zamowienie();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-2")).getTime().getTime());
        zamowienie.setDataZamowienia(ts);
        zamowienie.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        u.addZamowienie(zamowienie);
        zf.create(zamowienie);
        uf.edit(u);
        return "zamowieniaEdycja";
    }
    
    public String edycjaForm() {
        return "zamowieniaEdycja";
    }
    
    public String usun() {
        if(zamowienie.getKurs()!=null){
        kursyAc.setKurs(zamowienie.getKurs());
        kursyAc.setZam(zamowienie);
        kursyAc.wycofaj();
        }
        zf.remove(zamowienie);
        Uzytkownik u=login.getZalogowany();
        u.getZamowienia().remove(zamowienie);
        uf.edit(u);
        return "zamowieniaLista";
    }
    
    public void zamow() {
        Uzytkownik u=login.getZalogowany();
        zamowienie.getPotrawy().add(menu);
        u.getZamowienia().set(u.getZamowienia().indexOf(zamowienie), zamowienie);
        uf.edit(u);
    }
    
    public void usunZzam() {
        Uzytkownik u=login.getZalogowany();
        zamowienie.getPotrawy().remove(menu);
        u.getZamowienia().set(u.getZamowienia().indexOf(zamowienie), zamowienie);
        uf.edit(u);
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public List<Menu> getDostepneMenu() {
        dostepneMenu=mf.findAll();
        dostepneMenu.removeAll(zamowienie.getPotrawy());
        return dostepneMenu;
    }

    public void setDostepneMenu(List<Menu> dostepneMenu) {
        this.dostepneMenu = dostepneMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public KursyAc getKursyAc() {
        return kursyAc;
    }

    public void setKursyAc(KursyAc kursyAc) {
        this.kursyAc = kursyAc;
    }
    
    
}