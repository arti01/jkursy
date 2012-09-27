/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.adm;

import arti01.jobiady.encje.Menu;
import arti01.jobiady.encje.MenuFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

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
    boolean dodaj = false;
   
    @EJB
    MenuFacade mf;
    Menu menu=new Menu();
    
     public String lista() {
         allMenuAc.lista();
        return "menuKnajpy";
    }
     
       public void dodaj() {
           System.out.println("eeeee");
        dodaj=true;
    }
       
        public void zapisz() {
            System.out.println("dddddddddd");
            System.out.println(menu);
            System.out.println(menu.getIdmenu());
            System.out.println(menu.getNazwa());
            mf.create(menu);
        //dodaj=false;
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
    
    
    public boolean isDodaj() {
        return dodaj;
    }

    public void setDodaj(boolean dodaj) {
        this.dodaj = dodaj;
    }
}
