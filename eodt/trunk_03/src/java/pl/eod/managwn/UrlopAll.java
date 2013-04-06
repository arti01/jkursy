/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;

@ManagedBean(name = "UrlopAll")
@SessionScoped
public class UrlopAll extends UrlopM{
    //private WnUrlopJpaController UrlopC;
    //private DataModel<WnUrlop> urlopyList = new ListDataModel<WnUrlop>();
    
    @PostConstruct
    @Override
    public void init() {
    setUrlopC(new WnUrlopJpaController());
    getUrlopyList().setWrappedData(getUrlopC().findWnUrlopEntities());
    }
    
    @Override
    public String list(){
        return "/urlop/urlopyListWszystko";
    }
/*
    public WnUrlopJpaController getUrlopC() {
        return UrlopC;
    }

    public void setUrlopC(WnUrlopJpaController UrlopC) {
        this.UrlopC = UrlopC;
    }

    public DataModel<WnUrlop> getUrlopyList() {
        return urlopyList;
    }

    public void setUrlopyList(DataModel<WnUrlop> urlopyList) {
        this.urlopyList = urlopyList;
    }
  */  
    
}
