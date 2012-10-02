/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.konwertery;

import arti01.jobiady.beany.DniTyg;
import arti01.jobiady.beany.DniTygFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author arti01
 */

@ManagedBean(name = "dniTygConv")
@SessionScoped
public class DniTygConv implements Converter, Serializable {
    private static final long serialVersionUID = 1L;
    private Object object;
    @EJB
    DniTygFacade dtf;

    public DniTygFacade getDtf() {
        return dtf;
    }

    public void setDtf(DniTygFacade dtf) {
        this.dtf = dtf;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id;
        id = (value != null) ? Integer.valueOf(value) : null;
        return dtf.find(id);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        DniTyg dt=(DniTyg) value;
        return dt.getIddnityg().toString();
    }  
}
