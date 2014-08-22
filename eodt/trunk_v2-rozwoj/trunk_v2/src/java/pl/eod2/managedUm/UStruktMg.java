package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.swing.tree.TreeNode;
import pl.eod.managed.Login;
import pl.eod2.encje.UmGrupa;
import pl.eod2.encje.UmMasterGrupa;
import pl.eod2.encje.UmMasterGrupaJpaController;
import pl.eod2.encje.UmUrzadzenie;

@ManagedBean(name = "UStruktMg")
@SessionScoped
public class UStruktMg {

    @ManagedProperty(value = "#{login}")
    private Login login;
    private UmMasterGrupaJpaController dcC;
    private List<TreeNode> rootNodes = new ArrayList<TreeNode>();

    @PostConstruct
    void init() {
        dcC = new UmMasterGrupaJpaController();
        refresh();
    }

    public void refresh() {
        login.refresh();
        List<UmMasterGrupa> masterList = login.getZalogowany().getUserId().getSpolkaId().getUmMasterGrupaList();
        rootNodes.clear();
        for (UmMasterGrupa mg : masterList) {
            DrzMaster drMa = new DrzMaster();
            drMa.setName(mg.getNazwa());
            
            for (UmGrupa gr : mg.getGrupaList()) {
                DrzGrupa drGr = new DrzGrupa();
                drGr.setName(gr.getNazwa());
            
                for(UmUrzadzenie uz:gr.getUrzadzenieList()){
                    DrzUrzad drzU=new DrzUrzad();
                    drzU.setName(uz.getNazwa());
                    drGr.getDrzUrzad().add(drzU);
                }
                drMa.getDrzGrupa().add(drGr);
            }
            
            rootNodes.add(drMa);

        }
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

    public List<TreeNode> getRootNodes() {
        return rootNodes;
    }

    public void setRootNodes(List<TreeNode> rootNodes) {
        this.rootNodes = rootNodes;
    }

}
