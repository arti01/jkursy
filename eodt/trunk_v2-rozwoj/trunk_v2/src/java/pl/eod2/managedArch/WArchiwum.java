/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedArch;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.abstr.AbstMg;
import pl.eod2.encje.DcDokumentArch;
import pl.eod2.encje.DcDokumentArchKontr;

@ManagedBean(name = "WArchiwumArch")
@SessionScoped
public class WArchiwum extends AbstMg<DcDokumentArch, DcDokumentArchKontr> {

    public WArchiwum() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listW", new DcDokumentArchKontr(), new DcDokumentArch());
    }

    public String detale() {
        return "/dcarch/wArchDetale";
    }

}
