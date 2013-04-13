
package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcRodzajGrupa;
import pl.eod2.encje.DcRodzajGrupaJpaController;

@ManagedBean(name = "RodzajeGrupy")
@SessionScoped
public class RodzajeGrupy {
    private DataModel<DcRodzajGrupa> lista = new ListDataModel<DcRodzajGrupa>();
    private DcRodzajGrupaJpaController dcRodzajGrupaC;
    private RodzajeGrupy rodzajGrupy;
    
    @PostConstruct
    void init(){
        dcRodzajGrupaC=new DcRodzajGrupaJpaController();
        refresh();
    }
    
    void refresh(){
        lista.setWrappedData(dcRodzajGrupaC.findDcRodzajGrupaEntities());
    }

    public void test(){
        System.err.println("test"+lista.getRowData().getNazwa());
    }
    
    public String list(){
        return "/dccfg/rodzajegrupy";
    }

    public DataModel<DcRodzajGrupa> getLista() {
        return lista;
    }

    public void setLista(DataModel<DcRodzajGrupa> lista) {
        this.lista = lista;
    }
    
    
}
