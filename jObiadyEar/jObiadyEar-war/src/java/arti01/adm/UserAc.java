/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.adm;

import arti01.jobiady.beany.Role;
import arti01.jobiady.beany.Uzytkownik;
import arti01.jobiady.beany.UzytkownikFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author 103039
 */
@ManagedBean(name = "admUserAc")
@SessionScoped
public class UserAc implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private UIComponent itusername;

    private boolean edit=false;
    
    @EJB
    UzytkownikFacade uf;
    DataModel<Uzytkownik> userAll = new ListDataModel<Uzytkownik>();
    Uzytkownik uzytkownik = new Uzytkownik();
    List<Role> allRole = new ArrayList<Role>();

    public String lista() {
        userAll.setWrappedData(uf.findAll());
        Role r=new Role();
        r.setRola(Role.USER);
        uzytkownik.setRoles(new ArrayList<Role>());
        uzytkownik.getRoles().add(r);
        System.out.println(uzytkownik.getRoles().get(0).getRola());
        return "user";
    }

    public void zapisz() {
        //System.out.println(uf);
        try {
            if (uf.find(uzytkownik.getUsername()) != null) {
                uf.edit(uzytkownik);
            } else {
                uf.create(uzytkownik);
            }
        } catch (javax.ejb.EJBException ex) {
            FacesMessage message = new FacesMessage("taki user już istnieje");
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println(itusername);
            System.out.println(itusername.getClientId(context));
            context.addMessage(itusername.getClientId(context), message);
        }
        userAll.setWrappedData(uf.findAll());
        uzytkownik = new Uzytkownik();
        edit=false;
    }
    
    public void edytuj() {
            edit=true;
            uzytkownik=userAll.getRowData();
        }
    
    public void usun() {
            uzytkownik=userAll.getRowData();
            uf.remove(uzytkownik);
            userAll.setWrappedData(uf.findAll());
        }

    public DataModel<Uzytkownik> getUserAll() {
        return userAll;
    }

    public void setUserAll(DataModel<Uzytkownik> userAll) {
        this.userAll = userAll;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public List<Role> getAllRole() {
        allRole = new ArrayList<Role>();
        Role r=new Role();
        r.setRola("admin");
        Role r1=new Role();
        r1.setRola("user");
        allRole.add(r);
        allRole.add(r1);
        return allRole;
    }

    public void setAllRole(List<Role> allRole) {
        this.allRole = allRole;
    }

    public UIComponent getItusername() {
        return itusername;
    }

    public void setItusername(UIComponent itusername) {
        this.itusername = itusername;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
}