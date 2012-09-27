/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.all;

import arti01.jobiady.encje.Menu;
import arti01.jobiady.encje.MenuFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author 103039
 */
//@Named(value = "allMenuAc")
@ManagedBean(name = "allMenuAc")
@SessionScoped
public class MenuAc implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    MenuFacade mf;
    List<Menu> menuAll;

    public String lista() {
        menuAll = mf.findAll();
        return "menuKnajpy";
    }

  

    public MenuFacade getMf() {
        return mf;
    }

    public void setMf(MenuFacade mf) {
        this.mf = mf;
    }

    public List<Menu> getMenuAll() {
        return menuAll;
    }

    public void setMenuAll(List<Menu> menuAll) {
        this.menuAll = menuAll;
    }
}
