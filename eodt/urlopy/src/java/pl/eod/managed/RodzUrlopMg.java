package pl.eod.managed;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.richfaces.event.DropEvent;
import pl.eod.encje.UserRoles;
import pl.eod.encje.UserRolesJpaController;
import pl.eod.encje.WnRodzaje;
import pl.eod.encje.WnRodzajeJpaController;

@ManagedBean(name = "RodzUrlopMg")
@SessionScoped
public class RodzUrlopMg {

    private List<UserRoles> lista;
    private UserRolesJpaController dcC;
    private WnRodzajeJpaController dcR;
    private pl.eod.encje.UserRoles obiekt;
    private pl.eod.encje.WnRodzaje obiektRodzaj;
    private String error;

    @PostConstruct
    void init() {
        dcC = new UserRolesJpaController();
        dcR=new WnRodzajeJpaController();
        lista = new ArrayList<UserRoles>();
        refresh();
    }

    void refresh() {
        lista.clear();
        lista.addAll(dcC.findDostepneDoRodzajowUrlopow());
        obiekt = new UserRoles();
        obiektRodzaj = new WnRodzaje();
        error = null;
    }

    public String list() {
        refresh();
        return "/all/rodzajeUrlopow";
    }

    public void usun(){
        obiektRodzaj.getRole().remove(obiekt);
        obiekt.getWnRodzaje().remove(obiektRodzaj);
        try {
            dcC.edit(obiekt);
            dcR.edit(obiektRodzaj);
        } catch (Exception ex) {
            Logger.getLogger(RodzUrlopMg.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }
    
    public void drop(DropEvent event) {
        WnRodzaje rodz = (WnRodzaje) event.getDragValue();
        UserRoles rola=(UserRoles) event.getDropValue();
        rodz.getRole().add(rola);
        rola.getWnRodzaje().add(rodz);
        try {
            dcC.edit(rola);
        } catch (Exception ex) {
            Logger.getLogger(RodzUrlopMg.class.getName()).log(Level.SEVERE, null, ex);
        }

        refresh();
    }

    public List<UserRoles> getLista() {
        return lista;
    }

    public void setLista(List<UserRoles> lista) {
        this.lista = lista;
    }

    public UserRoles getObiekt() {
        return obiekt;
    }

    public void setObiekt(UserRoles obiekt) {
        this.obiekt = obiekt;
    }

    public WnRodzaje getObiektRodzaj() {
        return obiektRodzaj;
    }

    public void setObiektRodzaj(WnRodzaje obiektRodzaj) {
        this.obiektRodzaj = obiektRodzaj;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
