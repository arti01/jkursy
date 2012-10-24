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
import arti01.jobiady.beany.Zamowieniemenu;
import arti01.trag.KursyAc;
import arti01.utils.Login;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Zamowieniemenu zamMenu;

    public String lista() {
        return "zamowieniaLista";
    }

    public String dodaj() {
        Uzytkownik u = login.getZalogowany();
        zamowienie = zf.dodajZam(login.getZalogowany());
        Logger.getAnonymousLogger().log(Level.SEVERE, u.getZamowienia()+"");
        Logger.getAnonymousLogger().log(Level.SEVERE, u.getZamowienia().size()+"");
        Logger.getAnonymousLogger().log(Level.SEVERE, login.getZalogowany().getZamowienia().size()+"");
        Logger.getAnonymousLogger().log(Level.SEVERE, login.getZalogowany().getZamowienia()+"");
        return "zamowieniaEdycja";
    }

    public String edycjaForm() {
        return "zamowieniaEdycja";
    }

    public String usun() {
        if (zamowienie.getKurs() != null) {
            kursyAc.setKurs(zamowienie.getKurs());
            kursyAc.setZam(zamowienie);
            kursyAc.wycofaj();
        }
        zf.remove(zamowienie);
        Uzytkownik u = login.getZalogowany();
        u.getZamowienia().remove(zamowienie);
        uf.edit(u);
        return "zamowieniaLista";
    }

    public void zamow() {
        Uzytkownik u = login.getZalogowany();
        Zamowieniemenu zm = new Zamowieniemenu();
        zm.setMenu(menu);
        //zm.setZamowienie(zamowienie);
        zamowienie.getZamowieniemenu().add(0, zm);
        //Logger.getLogger("zamienienie menu").log(Level.SEVERE, u.getZamowienia().indexOf(zamowienie)+"");
        //Logger.getLogger("zamienienie menu").log(Level.SEVERE, zamowienie+"");
        u.getZamowienia().set(u.getZamowienia().indexOf(zamowienie), zamowienie);
        uf.edit(u);
    }

    public void usunZzam() {
        Uzytkownik u = login.getZalogowany();
        zamowienie.getZamowieniemenu().remove(zamMenu);
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
        dostepneMenu = mf.findAll();
        dostepneMenu.removeAll(zamowienie.getZamowieniemenu());
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

    public Zamowieniemenu getZamMenu() {
        return zamMenu;
    }

    public void setZamMenu(Zamowieniemenu zamMenu) {
        this.zamMenu = zamMenu;
    }
}