package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pl.eod.managed.Login;
import pl.eod2.encje.UmMasterGrupa;
import pl.eod2.encje.UmMasterGrupaJpaController;

@ManagedBean(name = "UStruktMg")
@SessionScoped
public class UStruktMg {

    @ManagedProperty(value = "#{login}")
    private Login login;
    private UmMasterGrupaJpaController dcC;
    private List<UmMasterGrupa> masterList = new ArrayList<UmMasterGrupa>();

    @PostConstruct
    void init() {
        dcC = new UmMasterGrupaJpaController();
        refresh();
    }

    public void refresh() {
        login.refresh();
        masterList = login.getZalogowany().getUserId().getSpolkaId().getUmMasterGrupaList();
        //srcRoots.get(0).getGrupaList();
        //srcRoots.get(0).getGrupaList().get(0).getUrzadzenieList();
    }

    public String list() {
        refresh();
        return "/um/struktura";
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<UmMasterGrupa> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<UmMasterGrupa> masterList) {
        this.masterList = masterList;
    }

}
