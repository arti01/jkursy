/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb;

import java.util.List;
import javax.ejb.Remote;
import org.arti01.entit.Boksycfg;
import org.arti01.entit.Typykursu;

/**
 *
 * @author arti01
 */
@Remote
public interface BoksycfgImpRemote {

    public List<Boksycfg> getAll();
    
}
