/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.all;

import arti01.jobiady.beany.Menu;
import arti01.jobiady.beany.MenuFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
    DataModel<Menu> menuAll=new ListDataModel<Menu>();
    
    public String lista() {
        menuAll.setWrappedData(mf.findAll());
        return "menuKnajpy";
    }

  

    public MenuFacade getMf() {
        return mf;
    }

    public void setMf(MenuFacade mf) {
        this.mf = mf;
    }

    public DataModel<Menu> getMenuAll() {
        return menuAll;
    }

    public void setMenuAll(DataModel<Menu> menuAll) {
        this.menuAll = menuAll;
    }
}
