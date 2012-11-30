/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import pl.eod.encje.Dzial;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.exceptions.NonexistentEntityException;

/**
 *
 * @author 103039
 */
@ManagedBean(name = "UsersM")
@SessionScoped
public class UsersM implements Serializable {

    private static final long serialVersionUID = 1L;
    List<Uzytkownik> users = new ArrayList<Uzytkownik>();
    List<Struktura> struktury = new ArrayList<Struktura>();
    UzytkownikJpaController userC;
    Uzytkownik user;
    boolean edytuj = false;
    String nameFilter;
    Dzial dzialFilter;
    StrukturaJpaController struktC;
    Struktura strukt;
    DzialJpaController dzialC;

    @PostConstruct
    public void init() {
        userC = new UzytkownikJpaController();
        struktC = new StrukturaJpaController();
        dzialC = new DzialJpaController();
        initUser();
    }

    private void initUser() {
        strukt = new Struktura();
        user = new Uzytkownik();
        strukt.setUserId(user);
        Dzial dzial = new Dzial();
        strukt.setDzialId(dzial);
    }

    public String lista() {
        edytuj = false;
        return "usersList";
    }

    public void dodaj() throws NonexistentEntityException, Exception {
        struktC.create(strukt);
        initUser();
    }

    public void usun() throws NonexistentEntityException, Exception {
        struktC.destroy(strukt.getId());
        initUser();
        edytuj = false;
    }

    public void zapisz() throws NonexistentEntityException, Exception {
        struktC.editArti(strukt);
        initUser();
        edytuj = true;
    }

    public void kierListener(ValueChangeEvent e) throws NullPointerException {
        Boolean kier;
        kier = (Boolean) e.getNewValue();
        try {
            if (kier) {
                strukt.getDzialId().setNazwa("");
            } else {
                strukt.getDzialId().setNazwa(strukt.getSzefId().getDzialId().getNazwa());
            }
        } catch (NullPointerException ex) {
        }
    }

    public void dzialListener(ValueChangeEvent e) throws NullPointerException {
        Struktura str = (Struktura) e.getNewValue();
        //System.out.println(strukt.getDzialId());
        if (!strukt.isStKier()) {
            if (str != null) {
                strukt.getDzialId().setNazwa(str.getDzialId().getNazwa());
            } else {
                strukt.getDzialId().setNazwa("");
            }
        }
    }

    public List<Uzytkownik> getUsers() {
        users = userC.findUzytkownikEntities();
        return users;
    }

    public void setUsers(List<Uzytkownik> users) {
        this.users = users;
    }

    public List<Struktura> getStruktury() {
        struktury = struktC.findStrukturaEntities();
        return struktury;
    }

    public void setStruktury(List<Struktura> struktury) {
        this.struktury = struktury;
    }

    public Uzytkownik getUser() {
        return user;
    }

    public void setUser(Uzytkownik user) {
        this.user = user;
    }

    public boolean isEdytuj() {
        return edytuj;
    }

    public void setEdytuj(boolean edytuj) {
        this.edytuj = edytuj;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        edytuj = false;
        this.nameFilter = nameFilter;
    }

    public Dzial getDzialFilter() {
        return dzialFilter;
    }

    public void setDzialFilter(Dzial dzialFilter) {
        edytuj = false;
        this.dzialFilter = dzialFilter;
    }

    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }

    public Struktura getStrukt() {
        return strukt;
    }

    public void setStrukt(Struktura strukt) {
        this.strukt = strukt;
    }
}