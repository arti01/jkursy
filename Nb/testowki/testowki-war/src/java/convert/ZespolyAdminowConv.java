/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import encje.ZespolyAdminow;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import ses.ZespolyAdminowFacade;

@ManagedBean(name = "ZespolyAdminowConv")
@SessionScoped
public class ZespolyAdminowConv implements Converter, Serializable {

    @EJB
    ZespolyAdminowFacade uC;

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
        ZespolyAdminow u = (ZespolyAdminow) value;
        //System.out.println((value != null) ? u.getId().toString() : null);
        return (value != null) ? u.getId().toString() : null;
    }
}
