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
    List <Uzytkownik> users=new ArrayList<Uzytkownik>();
    UzytkownikJpaController userC;
    Uzytkownik user;
    boolean edytuj=false;
    String nameFilter;
    Dzial dzialFilter;
    StrukturaJpaController struktC;
    DzialJpaController dzialC;
    
    @PostConstruct
    public void init(){
        userC=new UzytkownikJpaController();
        user=new Uzytkownik();
        Struktura struk=new Struktura();
        user.setStruktura(struk);
        struktC =new StrukturaJpaController();
        dzialC=new DzialJpaController();
    }
    
    public String lista(){
        edytuj=false;
        return "usersList";
    }
    
    public void dodaj() throws NonexistentEntityException, Exception{
        Struktura struktura=user.getStruktura();
        //Dzial dzial=new Dzial(new Long(1));
        //struktura.setDzialId(dzial);
        struktC.create(struktura);
        user.setStruktura(struktura);
        userC.create(user);
        user=new Uzytkownik();
        Struktura struk=new Struktura();
        user.setStruktura(struk);
    }
    
    public void usun() throws NonexistentEntityException, Exception{
        userC.destroy(user.getId());
        user=new Uzytkownik();
        Struktura struk=new Struktura();
        user.setStruktura(struk);
        edytuj=false;
    }
    
    public void zapisz() throws NonexistentEntityException, Exception{
        edytuj=true;
        userC.editaRTI(user);
        user=new Uzytkownik();
        Struktura struk=new Struktura();
        user.setStruktura(struk);
    }

    public List<Uzytkownik> getUsers() {
        users=userC.findUzytkownikEntities();
        return users;
    }

    public void setUsers(List<Uzytkownik> users) {
        this.users = users;
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
        edytuj=false;
        this.nameFilter = nameFilter;
    }

    public Dzial getDzialFilter() {
        return dzialFilter;
    }

    public void setDzialFilter(Dzial dzialFilter) {
        edytuj=false;
        this.dzialFilter = dzialFilter;
    }

    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }
    
    
}