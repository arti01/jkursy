/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.eod.encje.WnStatusy;
import pl.eod.encje.WnStatusyJpaController;
import pl.eod.encje.WnUrlopJpaController;

@ManagedBean(name = "UrlopAll")
@SessionScoped
public class UrlopAll extends UrlopM {

    WnStatusy statusFilter;
    String nameFilter;
    WnStatusyJpaController wnStatusyC;
    

    @PostConstruct
    @Override
    public void init() {
        setWnStatusyC(new WnStatusyJpaController());
        setUrlopC(new WnUrlopJpaController());
    }

    @Override
    public String list() {
        statusFilter = new WnStatusy(new Long(0));
        wnStatusyC.getFindWnStatusyEntities();
        return "/urlop/urlopyListWszystko";
    }

    public WnStatusy getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(WnStatusy statusFilter) {
        this.statusFilter = statusFilter;
    }

    public WnStatusyJpaController getWnStatusyC() {
        return wnStatusyC;
    }

    public void setWnStatusyC(WnStatusyJpaController wnStatusyC) {
        this.wnStatusyC = wnStatusyC;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }
}
