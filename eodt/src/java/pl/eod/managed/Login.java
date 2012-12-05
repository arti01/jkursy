package pl.eod.managed;


import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;

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
        return "/index.html";
    }
/*  
    public Struktura getZalogowany() {
        //stub
        if(zalogowany!=null){
            zalogowany= userImp.find(zalogowany.getUsername());
        }
        //userImp.;
        return zalogowany;
    }
*/
    public String login() {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        System.err.println(request.getUserPrincipal().getName());
        //strukC=new StrukturaJpaController();
        UzytkownikJpaController uzytC=new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        System.err.println(zalogowany.getDzialId().getNazwa());
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

        System.err.println(request.getUserPrincipal().getName());
        //strukC=new StrukturaJpaController();
        UzytkownikJpaController uzytC=new UzytkownikJpaController();
        zalogowany = uzytC.findStruktura(request.getUserPrincipal().getName());
        System.err.println(zalogowany.getDzialId().getNazwa());
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
