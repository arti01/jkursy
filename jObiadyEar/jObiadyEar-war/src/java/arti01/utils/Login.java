package arti01.utils;

import arti01.jobiady.beany.Uzytkownik;
import arti01.jobiady.beany.UzytkownikFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    Uzytkownik zalogowany;
    @EJB
    UzytkownikFacade userImp;
    String username;
    String password;

    public String wyloguj() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            zalogowany=null;
        } catch (ServletException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/all/index.xhtml?faces-redirect=true";
    }

    public Uzytkownik getZalogowany() {
        //stub
        //if(zalogowany!=null) zalogowany= userImp.find(zalogowany.getUsername());
        return zalogowany;
    }

    public String login() {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    try {
        request.login(username, password);
        zalogowany = userImp.find(username);
    } catch (ServletException e) {
            try {
                // Handle unknown username/password in request.login().
                //context.addMessage(null, new FacesMessage("błąd logowania"));
                request.logout();
                return "/all/loginerr";
            } catch (ServletException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                return "/all/loginerr";
            }
    }
    return "/all/index";
}

    
    public void setZalogowany(Uzytkownik user) {
        zalogowany = user;
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
