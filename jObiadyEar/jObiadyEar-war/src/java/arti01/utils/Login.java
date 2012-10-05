package arti01.utils;

import arti01.jobiady.beany.Uzytkownik;
import arti01.jobiady.beany.UzytkownikFacade;
import java.io.Serializable;
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

    public String wyloguj() {
        HttpServletRequest r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //logger.info(r.getRemoteUser()+"wwwwwwwwwwwwwww");
        try {
            r.logout();
            zalogowany = null;
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //rozne.setInfoText("jeste�� wylogowany");
        return "/all/index.xhtml?faces-redirect=true";
    }

    public Uzytkownik getZalogowany() {
        HttpServletRequest r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //logger.info(r.getRemoteUser());
        Uzytkownik z = new Uzytkownik();
        z.setUsername(r.getRemoteUser());
        //zalogowany = userImp.find(z);
        //stub
        zalogowany = userImp.find("zzz");
        //logger.info("imie nazwisko"+zalogowany.getImieNazwisko());
        //rozne.ustaw(z);
        //rozne.setInfoText("jeste�� zalogowany");
        return zalogowany;
        //return rozne.getZalogowany();
    }

    public void setZalogowany(Uzytkownik user) {
        zalogowany = user;
    }
}
