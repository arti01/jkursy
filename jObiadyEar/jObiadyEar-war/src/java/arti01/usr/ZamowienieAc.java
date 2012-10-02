/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.usr;

import arti01.jobiady.beany.Menu;
import arti01.jobiady.beany.MenuFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


/**
 *
 * @author 103039
 */
@ManagedBean(name = "usrZamowienieAc")
@SessionScoped
public class ZamowienieAc implements Serializable {
    private static final long serialVersionUID = 1L;
    
       @EJB
   private MenuFacade mf;
   private DataModel<Menu> menuAll=new ListDataModel<Menu>();
    
     public String lista() {
         menuAll.setWrappedData(mf.findAll());
        return "zamowienia";
    }

    public DataModel<Menu> getMenuAll() {
        return menuAll;
    }

    public void setMenuAll(DataModel<Menu> menuAll) {
        this.menuAll = menuAll;
    }
    
}