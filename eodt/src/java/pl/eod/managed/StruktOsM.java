/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UzytkownikJpaController;


/**
 *
 * @author 103039
 */
@ManagedBean(name = "StruktOsM")
@SessionScoped
public class StruktOsM implements Serializable {
    private static final long serialVersionUID = 1L;
    
    UzytkownikJpaController userC;
    StrukturaJpaController struktC;
    DzialJpaController dzialC;
    
    @PostConstruct
    public void init(){
        userC=new UzytkownikJpaController();
        struktC =new StrukturaJpaController();
        dzialC=new DzialJpaController();
    }
    
    public String lista(){
        return "strukturaOs";
    }
    
    
    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }
    
    
}