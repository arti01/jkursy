/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.encje.Dzial;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;

/**
 *
 * @author arti01
 */
@ManagedBean(name = "StruktDzialM")
@SessionScoped
public class StruktDzialM implements Serializable {
    private static final long serialVersionUID = 1L;

    UzytkownikJpaController userC;
    StrukturaJpaController struktC;
    DzialJpaController dzialC;
    private List<Struktura> srcRoots=new ArrayList<Struktura>();
     
    @PostConstruct
    public void init(){
        userC=new UzytkownikJpaController();
        struktC =new StrukturaJpaController();
        dzialC=new DzialJpaController();
    }
    
    public String lista(){
        return "/all/strukturaDzial";
    }
    

   public synchronized List<Struktura> getSourceRoots() {
      Struktura firma=new Struktura();
            Dzial uDzial=new Dzial();
      uDzial.setNazwa("Organizacja - wg działów");
      firma.setDzialId(uDzial);
      srcRoots.clear();
      srcRoots.addAll(struktC.getFindBezSzefa());
      firma.setBezpPod(srcRoots);
      List<Struktura> wynik=new ArrayList<Struktura>();
      wynik.add(firma);
      return wynik;
   }
    
    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }
    
    
}