/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.all;

import arti01.jobiady.encje.Menu;
import arti01.jobiady.encje.MenuFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author 103039
 */
@Named(value = "allMenuAc")
@SessionScoped
public class MenuAc implements Serializable {

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
