/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.adm;

import arti01.jobiady.beany.DniTyg;
import arti01.jobiady.beany.DniTygFacade;
import arti01.jobiady.beany.Menu;
import arti01.jobiady.beany.MenuFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author 103039
 */
@ManagedBean(name = "admMenuAc")
@SessionScoped
public class MenuAc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value = "#{allMenuAc}")
    private arti01.all.MenuAc allMenuAc;
    
    @EJB MenuFacade mf;
    private Menu menu=new Menu();
    
    @EJB DniTygFacade dtf;
    List<DniTyg> alldniTyg;

    public List<DniTyg> getAlldniTyg() {
        alldniTyg=dtf.findAll();
        return alldniTyg;
    }

    public void setAlldniTyg(List<DniTyg> alldniTyg) {
        this.alldniTyg = alldniTyg;
    }
    
     public String lista() {
         allMenuAc.lista();
        return "menuKnajpy";
    }
       
        public void zapisz() {
            if(menu.getIdmenu()>0)mf.edit(menu);
            else mf.create(menu);
            allMenuAc.lista();
            menu=new Menu();
    }
        
        public void edytuj() {
            menu=allMenuAc.getMenuAll().getRowData();
        }
        
        public void usun() {
            menu=allMenuAc.getMenuAll().getRowData();
            mf.remove(menu);
            allMenuAc.lista();
        }
    
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
   public arti01.all.MenuAc getAllMenuAc() {
        return allMenuAc;
    }

    public void setAllMenuAc(arti01.all.MenuAc allMenuAc) {
        this.allMenuAc = allMenuAc;
    }  
}