package pl.eod2.managedRej;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod2.encje.DcDokument;

@ManagedBean(name = "BrakowanieRej")
@SessionScoped
public class Brakowane extends Rejestracja {

    public Brakowane() {
        super();
    }

    @Override
    public void refreshObiekt(){
        super.refreshObiekt();
        List<DcDokument> l=new ArrayList<>();
        for(DcDokument d:(List<DcDokument>) lista.getWrappedData()){
            if(d.isAlertBrakowanie()){
                l.add(d);
            }
        }
        lista.setWrappedData(l);
    }
    
    @Override
    public String list() {
        refreshObiekt();
        return "/dcrej/dokumentyDoBrak";
}
}
