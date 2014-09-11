package pl.eod2.managedCfg;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.AbstKontroler;


public abstract class MgAbst<X> {

    private DataModel<X> lista;
    private AbstKontroler dcC;
    private Class<X> obiekt;
    private String error;
    private String link;
    
    
    @PostConstruct
    void init() throws InstantiationException, IllegalAccessException {
        this.lista = new ListDataModel<>();
        this.dcC.findEntities();
        this.link = "/dccfg/teczki";
        refresh();
    }

    void refresh() throws InstantiationException, IllegalAccessException {
        lista.setWrappedData(dcC.findEntities());
        obiekt = (Class<X>) obiekt.newInstance();
        error = null;
    }

   
}
