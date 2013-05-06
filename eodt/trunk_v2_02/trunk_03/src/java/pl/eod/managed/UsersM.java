/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.Dzial;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UserRoles;
import pl.eod.encje.UserRolesJpaController;
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
    List<Struktura> strukturyOld = new ArrayList<Struktura>();
    List<UserRoles>roleAll=new ArrayList<UserRoles>();
    List<Dzial>dzialyAll=new ArrayList<Dzial>();
    List<Struktura>kierownicyAll=new ArrayList<Struktura>();
    DataModel<Struktura> struktury = new ListDataModel<Struktura>();
    UzytkownikJpaController userC;
    Uzytkownik user;
    List<UserRoles> rolesKlon;
    boolean edytuj = false;
    String nameFilter;
    Dzial dzialFilter;
    StrukturaJpaController struktC;
    UserRolesJpaController urC;
    Struktura strukt;
    DzialJpaController dzialC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    boolean sprawdzacUnikEmail;

    @PostConstruct
    public void init() {
        userC = new UzytkownikJpaController();
        struktC = new StrukturaJpaController();
        dzialC = new DzialJpaController();
        urC=new UserRolesJpaController();
    }

    private void initUser() {
        strukt = new Struktura();
        strukt.setPrzyjmowanieWnioskow(false);
        user = new Uzytkownik();
        strukt.setUserId(user);
        Dzial dzial = new Dzial();
        strukt.setDzialId(dzial);
        struktury = new ListDataModel<Struktura>();
        struktury.setWrappedData(struktC.findStrukturaWidoczni(login.zalogowany.getUserId().getSpolkaId()));
        roleAll=urC.findDostepneDoEdycji();
        dzialyAll=dzialC.findDzialEntities(login.zalogowany.getUserId().getSpolkaId());
        kierownicyAll=struktC.getFindKierownicy(login.zalogowany.getUserId().getSpolkaId());
        //System.out.println(struktury.getRowCount()+"initUser");
    }

    public String lista() {
        edytuj = false;
        nameFilter=null;
        dzialFilter=new Dzial(new Long(0));
        initUser();
        return "/all/usersList";
    }
    
    public String nowy() {
        edytuj = false;
        nameFilter=null;
        dzialFilter=null;
        initUser();
        return "/all/usersAdd";
    }
    
    public String edycja() {
        rolesKlon=strukt.getUserId().getRole();
        return "/all/usersEdit";
    }
    
    public String ustawZastepce() {
        strukt=login.getZalogowany();
        return "/common/ustawZastepce";
    }
    
    public String ustawZastepceZapisz() throws NonexistentEntityException, Exception {
        zapisz();
        return "/logowanie/index";
    }
    
    public String listaFiltr() {
        edytuj = false;
        //System.err.println("c"+nameFilter+"c");
        return "usersList";
    }

    public void dodaj() throws NonexistentEntityException, Exception {
        String error = struktC.create(strukt);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        } else {
            FacesMessage message = new FacesMessage("dodano");
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            initUser();
        }
    }

    public void usun() {
        struktC.zrobNiewidczony(strukt);
        initUser();
        edytuj = false;
    }
    @Deprecated
    public void usunOld() throws NonexistentEntityException, Exception {
        struktC.destroyArti(strukt);
        initUser();
        edytuj = false;
    }

    public String zapisz() throws NonexistentEntityException, Exception {
        //pozostają role nieedytowalne
        rolesKlon.removeAll(roleAll);
        strukt.getUserId().getRole().addAll(rolesKlon);
        String error = struktC.editArti(strukt);
        if (error == null) {
            initUser();
            //error = "Zmiana wykonana";
            return "/all/usersList?faces-redirect=true";
        }
        FacesMessage message = new FacesMessage(error);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        context.addMessage(zapisz.getClientId(context), message);
        edytuj = true;
        //new Login().refresh();
        return "/all/usersEdit";
    }

    public void kierListener(ValueChangeEvent e)  {
        Boolean kier;
        kier = (Boolean) e.getNewValue();
        try {
            if (kier) {
                strukt.getDzialId().setNazwa("Nowy dział");
            } else {
                strukt.getDzialId().setNazwa(strukt.getSzefId().getDzialId().getNazwa());
            }
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        //strukt.setStKier(true);
    }

    public void changeKierListener(ValueChangeEvent e) throws NonexistentEntityException, Exception {
        Boolean kier;
        kier = (Boolean) e.getNewValue();
        strukt.setStKier(kier);
        String error=null;
        try {
            error = struktC.changeKier(strukt, strukt.getDzialId());
            strukt=struktC.findStruktura(strukt.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsersM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsersM.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent kierownikE = UIComponent.getCurrentComponent(context);
            context.addMessage(kierownikE.getClientId(context), message);
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
        users = userC.findUzytkownikEntities(login.zalogowany.getUserId().getSpolkaId());
        return users;
    }

    public void setUsers(List<Uzytkownik> users) {
        this.users = users;
    }

    public List<Struktura> getStrukturyOld() {
        return strukturyOld;
    }

    public void setStrukturyOld(List<Struktura> strukturyOld) {
        this.strukturyOld = strukturyOld;
    }

    public DataModel<Struktura> getStruktury() {
        return struktury;
    }

    public void setStruktury(DataModel<Struktura> struktury) {
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

    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }

    public List<UserRoles> getRoleAll() {
        return roleAll;
    }

    public void setRoleAll(List<UserRoles> roleAll) {
        this.roleAll = roleAll;
    }

    public List<Dzial> getDzialyAll() {
        return dzialyAll;
    }

    public void setDzialyAll(List<Dzial> dzialyAll) {
        this.dzialyAll = dzialyAll;
    }

    public List<Struktura> getKierownicyAll() {
        return kierownicyAll;
    }

    public void setKierownicyAll(List<Struktura> kierownicyAll) {
        this.kierownicyAll = kierownicyAll;
    }
    
}