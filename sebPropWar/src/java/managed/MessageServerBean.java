/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 103039
 */
@SessionScoped
//@Dependent
@Named
public class MessageServerBean implements Serializable{
private int ttt=0;
    public String getMessage() {
        ttt++;
        return "Hello EJB! "+ttt;
    }
}
