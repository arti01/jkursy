package pl.eod.managed;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.ConfigJpaController;
import pl.eod.encje.MenuLinki;
import pl.eod.encje.MenuLinkiJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    Struktura zalogowany = null;
    StrukturaJpaController strukC;
    String username;
    String password;
    boolean urlop;
    boolean struktura;
    boolean admin;
    boolean kierownik;
    String typLogowania;
    List<MenuLinki> menuLinki;
    MenuLinkiJpaController menuLinkiC;
    boolean menuStrukturaExp=false;
    boolean menuUrlopExp=false;

    @PostConstruct
    public void init() {
        ConfigJpaController confC = new ConfigJpaController();
        typLogowania = confC.findConfigNazwa("realm_szyfrowanie").getWartosc();
        menuLinkiC = new MenuLinkiJpaController();
        menuLinki = menuLinkiC.findMenuLinkiEntities();
    }

    public String wyloguj() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().invalidate();
        //return "../index.html?faces-redirect=true";
        return "../index.html?faces-redirect=true";
    }

    public String zmienHaslo() throws NonexistentEntityException, Exception {
        String error = null;
        if (!zalogowany.getUserId().getHasla().getPass().equals(password)) {
            error = "rózne hasła";
        } else {
            ConfigJpaController confC = new ConfigJpaController();
            if (confC.findConfigNazwa("realm_szyfrowanie").getWartosc().equals("md5")) {
                zalogowany.getUserId().getHasla().setPass(Login.md5(zalogowany.getUserId().getHasla().getPass()));
            }
            strukC = new StrukturaJpaController();
            strukC.editArti(zalogowany);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (error != null) {
            FacesMessage message = new FacesMessage(error);
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            return null;
        } else {
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().invalidate();
            FacesMessage message = new FacesMessage("zmiana wykonana");
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            //System.err.println("tutaj");
            return "../index.html";
        }
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        //System.err.println(request.getUserPrincipal().getName());
        //strukC=new StrukturaJpaController();
        UzytkownikJpaController uzytC = new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        return "/all/index";
    }

    public void setZalogowany(Struktura user) {
        zalogowany = user;
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        UzytkownikJpaController uzytC = new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        //obsluga zewnetrzne id
        if (zalogowany == null) {
            zalogowany = uzytC.findStrukturaExtid(request.getUserPrincipal().getName());
        }
        try{
        if (zalogowany.isUsuniety()) {
            this.wyloguj();
            //return null;
        }
        }catch(NullPointerException ex){
            System.err.println("brak użytkownika w bazie - user zewnętrzny");
        }
        //return zalogowany;
    }
    
    public void menuStrukturaExpList(ActionEvent event){
        if(menuStrukturaExp) menuStrukturaExp=false;
        else menuStrukturaExp=true;
        menuUrlopExp=false;
    }
    
    public void menuUrlopExpList(ActionEvent event){
        if(menuUrlopExp) menuUrlopExp=false;
        else menuUrlopExp=true;
        menuStrukturaExp=false;
    }

    public Struktura getZalogowany() {
        if (zalogowany == null) {
            refresh();
        }
        return zalogowany;
    }

    public static String md5(String input) {
        String md5 = null;
        if (null == input) {
            return null;
        }
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
            while (md5.length() < 32) {
                md5 = "0" + md5;
            }
            //new BigInteger
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUrlop() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("eodurlop")) {
            urlop = true;
        } else {
            urlop = false;
        }
        return urlop;
    }

    public boolean isStruktura() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("eodstru")) {
            struktura = true;
        } else {
            struktura = false;
        }
        return struktura;
    }

    public boolean isAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("eodadm")) {
            admin = true;
        } else {
            admin = false;
        }
        return admin;
    }

    public boolean isKierownik() {
        try {
            if (getZalogowany().isStKier()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    public String getTypLogowania() {
        return typLogowania;
    }

    public void setTypLogowania(String typLogowania) {
        this.typLogowania = typLogowania;
    }

    public List<MenuLinki> getMenuLinki() {
        return menuLinki;
    }

    public boolean isMenuStrukturaExp() {
        return menuStrukturaExp;
    }

    public void setMenuStrukturaExp(boolean menuStrukturaExp) {
        this.menuStrukturaExp = menuStrukturaExp;
    }

    public boolean isMenuUrlopExp() {
        return menuUrlopExp;
    }

    public void setMenuUrlopExp(boolean menuUrlopExp) {
        this.menuUrlopExp = menuUrlopExp;
    }
    
    
}
