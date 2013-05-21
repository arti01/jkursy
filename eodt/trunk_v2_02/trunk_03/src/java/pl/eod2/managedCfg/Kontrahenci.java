
package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcKontrahenci;
import pl.eod2.encje.DcKontrahenciJpaController;
import pl.eod2.encje.DcProjekt;
import pl.eod2.encje.DcProjektJpaController;

@ManagedBean(name = "KontrahenciCfg")
@SessionScoped
public class Kontrahenci extends AbstMB<DcKontrahenci>{
private DcKontrahenciJpaController dcC;

    public Kontrahenci() {
        super();
    }

    
    
    @PostConstruct
    void init() {
        this.lista = new ListDataModel<DcKontrahenci>();
        this.dcC = new DcKontrahenciJpaController();
        this.link = "/dccfg/kontrahenci";
        refresh();
    }
    
    @Override
     void refresh() {
        lista.setWrappedData(dcC.findEntities());
        obiekt = new DcKontrahenci();
        setError(null);
    }
}
