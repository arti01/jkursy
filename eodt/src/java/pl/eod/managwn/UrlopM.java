/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;
import pl.eod.managed.Login;

@ManagedBean(name = "UrlopM")
@SessionScoped
public class UrlopM implements Serializable {

    private static final long serialVersionUID = 1L;
    private WnUrlop urlop;
    private DataModel<WnUrlop> urlopyList = new ListDataModel<WnUrlop>();
    private WnUrlopJpaController urlopC;
    private WnRodzajeJpaController rodzajeC;
    @ManagedProperty(value = "#{login}")
    private Login login;

    @PostConstruct
    public void init() {
        urlopC = new WnUrlopJpaController();
        rodzajeC = new WnRodzajeJpaController();
        initUrlop();
    }

    private void initUrlop() {
        urlop = new WnUrlop();
        urlop.setUzytkownik(login.getZalogowany().getUserId());
        urlopyList.setWrappedData(urlopC.findWnUrlopEntities());
    }

    public String list() {
        return "urlopyList";
    }

    public WnUrlop getUrlop() {
        return urlop;
    }

    public void setUrlop(WnUrlop urlop) {
        this.urlop = urlop;
    }

    public DataModel<WnUrlop> getUrlopyList() {
        return urlopyList;
    }

    public void setUrlopyList(DataModel<WnUrlop> urlopyList) {
        this.urlopyList = urlopyList;
    }

    public WnRodzajeJpaController getRodzajeC() {
        return rodzajeC;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
}
