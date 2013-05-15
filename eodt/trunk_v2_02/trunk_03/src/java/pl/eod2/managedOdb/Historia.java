package pl.eod2.managedOdb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "HistOdb")
@SessionScoped
public class Historia extends Akcept{

    
    @Override
     public String list() {
        return "/dcodb/histList";
    }
    
    @Override
     public String detale() {
        return "/dcodb/histDetale?faces-redirect=true";
    }
}
