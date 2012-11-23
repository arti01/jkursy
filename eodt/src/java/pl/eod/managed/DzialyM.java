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
import pl.eod.encje.exceptions.NonexistentEntityException;


/**
 *
 * @author 103039
 */
@ManagedBean(name = "DzialyM")
@SessionScoped
public class DzialyM implements Serializable {
    private static final long serialVersionUID = 1L;
    List <Dzial> dzialy=new ArrayList<Dzial>();
    DzialJpaController dzialC;
    Dzial dzial;
    boolean edytuj=false;
    
    @PostConstruct
    public void init(){
        dzialC=new DzialJpaController();
        dzial=new Dzial();
    }
    
    public String lista(){
        edytuj=false;
        return "dzialyList";
    }
    
    public void dodaj() throws NonexistentEntityException, Exception{
        dzialC.create(dzial);
        dzial=new Dzial();
    }
    
    public void usun() throws NonexistentEntityException, Exception{
        dzialC.destroy(dzial.getId());
        dzial=new Dzial();
    }
    
    public void zapisz() throws NonexistentEntityException, Exception{
        edytuj=true;
        dzialC.edit(dzial);
        dzial=new Dzial();
    }

    public List<Dzial> getDzialy() {
        dzialy=dzialC.findDzialEntities();
        return dzialy;
    }

    public void setDzialy(List<Dzial> dzialy) {
        this.dzialy = dzialy;
    }

    public Dzial getDzial() {
        System.out.println(dzial.getNazwa()+"nazwaGet");
        return dzial;
    }

    public void setDzial(Dzial dzial) {
        System.out.println(dzial.getNazwa()+"nazwa");
        this.dzial = dzial;
    }

    public boolean isEdytuj() {
        return edytuj;
    }

    public void setEdytuj(boolean edytuj) {
        this.edytuj = edytuj;
    }
    
    
    
}