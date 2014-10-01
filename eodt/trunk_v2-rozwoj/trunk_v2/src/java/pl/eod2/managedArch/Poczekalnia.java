/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchKontr;

@ManagedBean(name = "PoczekalniaArch")
@SessionScoped
public class Poczekalnia extends AbstMg<DcDokumentArch, DcDokumentArchKontr> {

    public Poczekalnia() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listPoczekalnia", new DcDokumentArchKontr(), new DcDokumentArch());
    }

    @Override
    public void refresh() throws InstantiationException, IllegalAccessException {
        List<DcDokumentArch> suma=new ArrayList<>();
        suma.addAll(dcC.findStatus(6));
        suma.addAll(dcC.findStatus(7));
        lista.setWrappedData(suma);
        obiekt = obiekt.getClass().newInstance();
        error = null;
    }

    @Override
    public String list() throws InstantiationException, IllegalAccessException{
        refresh();
        return "/dcarch/listPoczekalnia";
    }

}
