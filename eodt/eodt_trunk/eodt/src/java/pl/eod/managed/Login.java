package pl.eod.managed;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    Struktura zalogowany;
    StrukturaJpaController strukC;
    String username;
    String password;

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
            StrukturaJpaController strukC = new StrukturaJpaController();
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

        System.err.println(request.getUserPrincipal().getName());
        //strukC=new StrukturaJpaController();
        UzytkownikJpaController uzytC = new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        /*try {
         // Handle unknown username/password in request.login().
         //context.addMessage(null, new FacesMessage("błąd logowania"));
         //request.logout();
         return "/all/loginerr";
         } catch (ServletException ex) {
         Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
         return "/all/loginerr";
         }*/
        return "/all/index";
    }

    public void setZalogowany(Struktura user) {
        zalogowany = user;
    }

    public Struktura getZalogowany() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        //strukC=new StrukturaJpaController();
        UzytkownikJpaController uzytC = new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        return zalogowany;
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
}
