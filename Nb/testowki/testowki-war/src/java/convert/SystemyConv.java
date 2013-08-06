/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import encje.Systemy;
import encje.ZestawyTestowe;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import ses.SystemyFacade;

@ManagedBean(name = "SystemyConv")
@SessionScoped
public class SystemyConv implements Converter, Serializable {
    @EJB
    SystemyFacade uC;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String u;
        try {
            u = (value != null) ? value : null;
        } catch (NumberFormatException ec) {
            return null;
        }
        Object wynik;
        try {
            wynik = uC.find(new Long(u));
        } catch (NumberFormatException nfe) {
            wynik = null;
        }
        //System.out.println(wynik+"asobiekt");
        return wynik;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Systemy u = (Systemy) value;
        //System.out.println((value != null) ? u.getId().toString() : null);
        return (value != null) ? u.getId().toString() : null;
    }
}
