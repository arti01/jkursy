/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedRej;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod2.encje.DcPlikImport;
import pl.eod2.encje.DcPlikImportJpaController;

@ManagedBean(name = "RejImpPlik")
@SessionScoped
public class ImpPlik {
    DcPlikImportJpaController dcpiC;
    List<DcPlikImport> lista;
DcPlikImport plkImp;
    
    @PostConstruct
    void init() {
        dcpiC=new DcPlikImportJpaController();
        lista=dcpiC.findDcPlikImportEntities();
    }

    public String list() {
        lista=dcpiC.findDcPlikImportEntities();
        return "/dcrej/pliki";
    }

    public List<DcPlikImport> getLista() {
        return lista;
    }

    public void setLista(List<DcPlikImport> lista) {
        this.lista = lista;
    }

    public DcPlikImport getPlkImp() {
        return plkImp;
    }

    public void setPlkImp(DcPlikImport plkImp) {
        this.plkImp = plkImp;
    }
}
