package pl.eod2.managedUm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.swing.tree.TreeNode;
import org.richfaces.event.DropEvent;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcRodzaj;
import pl.eod2.encje.DcRodzajJpaController;
import pl.eod2.encje.UmGrupa;
import pl.eod2.encje.UmMasterGrupa;
import pl.eod2.encje.UmUrzadzenie;
import pl.eod2.managedRej.Rejestracja;

@ManagedBean(name = "URejDokMg")
@SessionScoped
public class RejDok {

    private DataModel<DcDokument> lista = new ListDataModel<DcDokument>();
    private DataModel<DcRodzaj> rodzajLista = new ListDataModel<DcRodzaj>();
    @ManagedProperty(value = "#{login}")
    private Login login;
    @ManagedProperty(value = "#{RejestracjaRej}")
    private Rejestracja rejestracja;
    private DcRodzajJpaController dcR;
    private List<TreeNode> rootNodes = new ArrayList<TreeNode>();
    private UmUrzadzenie urzad;

    @PostConstruct
    void init() {
    }

    @SuppressWarnings("unchecked")
    void refresh() {
        dcR = new DcRodzajJpaController();
        rodzajLista.setWrappedData(dcR.findDcRodzajUm());
        List<DcDokument> listD = new ArrayList<DcDokument>();
        for (DcRodzaj rodz : (List<DcRodzaj>) rodzajLista.getWrappedData()) {
            listD.addAll(rodz.getDcDokumentList());
        }
        lista.setWrappedData(listD);
        rejestracja.setObiekt(null);
    }

    public void zrobDrzewoDlaDet() {
        List<UmMasterGrupa> masterList = rejestracja.getObiekt().getRodzajId().getUmMasterGrupaList();
        rootNodes.clear();
        for (UmMasterGrupa mg : masterList) {
            DrzMaster drMa = new DrzMaster(mg);

            for (UmGrupa gr : mg.getGrupaList()) {
                DrzGrupa drGr = new DrzGrupa(drMa, gr);

                for (UmUrzadzenie uz : gr.getUrzadzenieList()) {
                    DrzUrzad drzU = new DrzUrzad(drGr, uz);
                    drGr.getDrzUrzad().add(drzU);
                }
                drMa.getDrzGrupa().add(drGr);
            }
            rootNodes.add(drMa);
        }
    }

    public String list() {
        refresh();
        return "/um/dokumenty";
    }

    public void dodaj() throws Exception {
        if (rejestracja.dodajAbst()) {
            refresh();
        }
    }

    public void edytuj() {
        if (rejestracja.edytujAbst()) {
            refresh();
        }
    }

    public String detale() {
        zrobDrzewoDlaDet();
        return "/um/dokumentDetale?faces-redirect=true";
    }

    public void drop(DropEvent event) {

        //przypisanie urzadzenia do dokumenty
        NamedNode nn = (NamedNode) event.getDragValue();
        if (nn.getType().equals("urzadz")) {
            DrzUrzad drU = (DrzUrzad) event.getDragValue();
            UmUrzadzenie uz = drU.getObiektDb();
            rejestracja.getObiekt().getUrzadzeniaList().add(uz);
        }
        if (nn.getType().equals("grupa")) {
            DrzGrupa drG = (DrzGrupa) event.getDragValue();
            UmGrupa uGr = drG.getObiektDb();
            rejestracja.getObiekt().getUrzadzeniaList().addAll(uGr.getUrzadzenieList());
        }
        if (nn.getType().equals("master")) {
            DrzMaster drM = (DrzMaster) event.getDragValue();
            for (DrzGrupa drG : drM.getDrzGrupa()) {
                UmGrupa uGr = drG.getObiektDb();
                rejestracja.getObiekt().getUrzadzeniaList().addAll(uGr.getUrzadzenieList());
            }
        }
        rejestracja.edytujAbst();
    }

    public void usunUrzad(){
        rejestracja.getObiekt().getUrzadzeniaList().remove(urzad);
        rejestracja.edytujAbst();
    }
    
    public DataModel<DcDokument> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcDokument> lista) {
        this.lista = lista;
    }

    public DataModel<DcRodzaj> getRodzajLista() {
        return rodzajLista;
    }

    public void setRodzajLista(DataModel<DcRodzaj> rodzajLista) {
        this.rodzajLista = rodzajLista;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Rejestracja getRejestracja() {
        return rejestracja;
    }

    public void setRejestracja(Rejestracja rejestracja) {
        this.rejestracja = rejestracja;
    }

    public List<TreeNode> getRootNodes() {
        return rootNodes;
    }

    public void setRootNodes(List<TreeNode> rootNodes) {
        this.rootNodes = rootNodes;
    }

    public UmUrzadzenie getUrzad() {
        return urzad;
    }

    public void setUrzad(UmUrzadzenie urzad) {
        this.urzad = urzad;
    }
}
