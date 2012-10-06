/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.utils;

import arti01.jobiady.beany.Role;
import arti01.jobiady.beany.RoleFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author arti01
 */
@ManagedBean(name = "roleConv")
@SessionScoped
public class RoleConv implements Converter, Serializable {

    private static final long serialVersionUID = 1L;
    private Object object;
@EJB RoleFacade rf;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String rola;
        try {
            rola = (value != null) ? value : null;
        } catch (NumberFormatException ec) {
            return null;
        }
        //System.out.println(r);
        return rf.find(rola);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Role r = (Role) value;
        //System.out.println((value != null) ? r.getRola() : null);
        return (value != null) ? r.getRola() : null;
    }
}
