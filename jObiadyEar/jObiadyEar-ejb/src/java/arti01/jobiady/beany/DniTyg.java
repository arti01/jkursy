/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 103039
 */
@Entity
public class DniTyg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iddnityg;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
// Property checks.
        DniTyg other = (DniTyg) obj;
        if (iddnityg == null ? other.iddnityg != null : !iddnityg.equals(other.iddnityg)) return false;
        // All passed.
        return true;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.iddnityg != null ? this.iddnityg.hashCode() : 0);
        hash = 19 * hash + (this.nazwa != null ? this.nazwa.hashCode() : 0);
        return hash;
    }
  
    public Integer getIddnityg() {
        return iddnityg;
    }

    public void setIddnityg(Integer iddnityg) {
        this.iddnityg = iddnityg;
    }
    private String nazwa;



    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
}
