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
import arti01.utils.Login;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
        zamowienie = new Zamowienie();
        Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        zamowienie.setDataZamowienia(ts);
        zamowienie.setStatusZamowienia(StatusZamowienia.POCZATKOWY);
        Uzytkownik u=login.getZalogowany();
        //System.out.println(login.getZalogowany().getZamowienia().size());
        u.getZamowienia().add(0,zamowienie);
        zf.create(zamowienie);
        //System.out.println(u.getZamowienia().size());
        uf.edit(u);
        return "zamowieniaEdycja";
    }
    
    public String edycjaForm() {
        return "zamowieniaEdycja";
    }
    
    public String usun() {
        Uzytkownik u=login.getZalogowany();
        //System.out.println(login.getZalogowany().getZamowienia().size());
        u.getZamowienia().remove(zamowienie);
        //System.out.println(u.getZamowienia().size());
        uf.edit(u);
        return "zamowieniaLista";
    }
    
    public void zamow() {
        zamowienie.getPotrawy().add(menu);
        Uzytkownik u=login.getZalogowany();
        /*System.out.println(u.getZamowienia().size()+"zamow");
        System.out.println(u.getZamowienia());
        System.out.println(zamowienie);*/
        u.getZamowienia().set(u.getZamowienia().indexOf(zamowienie), zamowienie);
        uf.edit(u);
    }
    
    public void usunZzam() {
        zamowienie.getPotrawy().remove(menu);
        Uzytkownik u=login.getZalogowany();
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
        //System.out.println("getter");
        //System.out.println(zamowienie.getPotrawy());
        //System.out.println(dostepneMenu);
        dostepneMenu.removeAll(zamowienie.getPotrawy());
        //System.out.println(dostepneMenu);
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
    
    
}