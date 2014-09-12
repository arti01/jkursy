/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedCfg;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.abstr.MgAbst;
import pl.eod2.encje.DcTeczka;
import pl.eod2.encje.DcTeczkaKontr;

@ManagedBean(name = "TeczkiCfg")
@SessionScoped
public class TeczkiNew extends MgAbst<DcTeczka, DcTeczkaKontr>{

    public TeczkiNew() throws InstantiationException, IllegalAccessException {
        super("/dccfg/teczki", new DcTeczkaKontr(), new DcTeczka());
    }
}
