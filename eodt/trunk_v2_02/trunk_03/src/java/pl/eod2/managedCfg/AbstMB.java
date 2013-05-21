package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.DcKontrahenci;
import pl.eod2.encje.DcKontrahenciJpaController;
import pl.eod2.encje.DcProjekt;
import pl.eod2.encje.DcProjektJpaController;

public abstract class AbstMB<U> {
    public String link;
    public DataModel<U> lista;
    public Class<U> obiekt;
    public String error;

    public AbstMB() {
    }
    
  
    
    public String list() {
        refresh();
        System.err.println(link+"ddddddddddd");
        return link;
    }
    
}
