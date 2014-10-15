package pl.eod2.managedRej;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod2.encje.DcDokument;

@ManagedBean(name = "BrakowanieRej")
@SessionScoped
public class Brakowane extends Rejestracja {

    int listaSize = 0;

    public Brakowane() {
        super();
        super.init();
    }

    @PostConstruct
    @Override
    @SuppressWarnings("unchecked")
    public void refreshObiekt() {
        super.refreshObiekt();
        List<DcDokument> l = new ArrayList<>();
        for (DcDokument d : (List<DcDokument>) lista.getWrappedData()) {
            if (d.isAlertBrakowanie()) {
                l.add(d);
            }
        }
        listaSize = l.size();
        lista.setWrappedData(l);
    }

    @Override
    public String list() {
        refreshObiekt();
        return "/dcrej/dokumentyDoBrak";
    }

    public int getListaSize() {
        return listaSize;
    }

    public void setListaSize(int listaSize) {
        this.listaSize = listaSize;
    }

}
