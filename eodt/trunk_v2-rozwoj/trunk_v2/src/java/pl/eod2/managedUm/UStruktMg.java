package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.swing.tree.TreeNode;
import org.richfaces.component.UITree;
import org.richfaces.component.UITreeNode;
import org.richfaces.event.DropEvent;
import org.richfaces.event.TreeToggleEvent;
import pl.eod.managed.Login;
import pl.eod2.encje.UmGrupa;
import pl.eod2.encje.UmGrupaJpaController;
import pl.eod2.encje.UmMasterGrupa;
import pl.eod2.encje.UmMasterGrupaJpaController;
import pl.eod2.encje.UmUrzadzenie;
import pl.eod2.encje.UmUrzadzenieJpaController;

@ManagedBean(name = "UStruktMg")
@SessionScoped
public class UStruktMg {

    @ManagedProperty(value = "#{login}")
    private Login login;
    private UmMasterGrupaJpaController dcC;
    private UmGrupaJpaController dcG;
    private UmUrzadzenieJpaController dcU;
    private List<TreeNode> rootNodes = new ArrayList<TreeNode>();

    @PostConstruct
    void init() {
        dcC = new UmMasterGrupaJpaController();
        dcG = new UmGrupaJpaController();
        dcU = new UmUrzadzenieJpaController();
        refresh();
    }

    public void refresh() {
        login.refresh();
        List<UmMasterGrupa> masterList = login.getZalogowany().getUserId().getSpolkaId().getUmMasterGrupaList();
        rootNodes.clear();
        for (UmMasterGrupa mg : masterList) {
            DrzMaster drMa = new DrzMaster(mg.getNazwa(), mg.getId(), mg.getOpis());
            
            for (UmGrupa gr : mg.getGrupaList()) {
                DrzGrupa drGr = new DrzGrupa(gr.getNazwa(), gr.getId(), gr.getOpis());
            
                for(UmUrzadzenie uz:gr.getUrzadzenieList()){
                    DrzUrzad drzU=new DrzUrzad(uz.getNazwa(), uz.getId(), uz.getNrInw()+uz.getNrSer()+uz.getNotatka());
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

    @SuppressWarnings("unchecked")
    public void drop(DropEvent event) throws Exception{

         //przeniesienie urządzenia
         if(event.getDragValue().getClass().equals(pl.eod2.managedUm.DrzUrzad.class)){
             DrzUrzad drU=(DrzUrzad) event.getDragValue();
             UmUrzadzenie uz=dcU.findUmUrzadzenie(drU.getId());
             
             DrzGrupa drG=(DrzGrupa) event.getDropValue();
             UmGrupa gr=dcG.findUmGrupa(drG.getId());
             
             uz.setGrupa(gr);
             dcU.edit(uz);
         }
         
         //przeniesienie grupy
         if(event.getDragValue().getClass().equals(pl.eod2.managedUm.DrzGrupa.class)){
             DrzGrupa drU=(DrzGrupa) event.getDragValue();
             UmGrupa uz=dcG.findUmGrupa(drU.getId());
             
             DrzMaster drG=(DrzMaster) event.getDropValue();
             UmMasterGrupa gr=dcC.findUmMasterGrupa(drG.getId());
             uz.setMasterGrp(gr);
             dcG.edit(uz);
         }
         
         refresh();
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
