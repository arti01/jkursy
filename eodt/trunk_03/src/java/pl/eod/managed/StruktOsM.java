/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.richfaces.component.UITree;
import org.richfaces.model.TreeNode;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;

/**
 *
 * @author 103039
 */
@ManagedBean(name = "StruktOsM")
@SessionScoped
public class StruktOsM implements Serializable {

    private static final long serialVersionUID = 1L;
    protected org.richfaces.component.UITree sampleTreeBinding;
    private Map<TreeNode, Boolean> toggleState = new HashMap<TreeNode, Boolean>();
    UzytkownikJpaController userC;
    StrukturaJpaController struktC;
    DzialJpaController dzialC;
    private List<Struktura> srcRoots = new ArrayList<Struktura>();

    @PostConstruct
    public void init() {
        userC = new UzytkownikJpaController();
        struktC = new StrukturaJpaController();
        dzialC = new DzialJpaController();
    }

    public String lista() {
        return "/all/strukturaOs";
    }

    public void setTreeNodeExpanded(boolean expanded) {
        TreeNode node = (TreeNode) sampleTreeBinding.getRowData(); // Notice this line
        toggleState.put(node, expanded);
    }

    public boolean getTreeNodeExpanded() {
        TreeNode node = (TreeNode) sampleTreeBinding.getRowData(); // Notice this line
        System.out.println(node);
        if (toggleState.containsKey(node)) {
            return toggleState.get(node);
        }
        return false;
    }

    // And a method to expand/collapse any node programatically
    public void setToggeStateForNode(TreeNode node, boolean expanded) {
        System.out.println(node + "sss");
        toggleState.put(node, expanded);
    }

    public synchronized List<Struktura> getSourceRoots() {
        Struktura firma = new Struktura();
        Uzytkownik uFirma = new Uzytkownik();
        uFirma.setFullname("Organizacja - wg pracowników");
        firma.setUserId(uFirma);
        srcRoots.clear();
        srcRoots.addAll(struktC.getFindBezSzefa());
        firma.setBezpPod(srcRoots);
        List<Struktura> wynik = new ArrayList<Struktura>();
        wynik.add(firma);
        return wynik;

    }

    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }

    public UITree getSampleTreeBinding() {
        if (sampleTreeBinding != null) {
            System.out.println(sampleTreeBinding.getValue());
        }
        return sampleTreeBinding;
    }

    public void setSampleTreeBinding(UITree sampleTreeBinding) {
        System.out.println(sampleTreeBinding.getValue());
        this.sampleTreeBinding = sampleTreeBinding;
    }
}