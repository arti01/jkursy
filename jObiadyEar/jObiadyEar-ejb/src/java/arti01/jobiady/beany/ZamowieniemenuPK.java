/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 103039
 */
@Embeddable
public class ZamowieniemenuPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDZAMOWIENIE")
    private int idzamowienie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMENU", insertable=false, updatable=false)
    private int idmenu;

    public ZamowieniemenuPK() {
    }

    public ZamowieniemenuPK(int idzamowienie, int idmenu) {
        this.idzamowienie = idzamowienie;
        this.idmenu = idmenu;
    }

    public int getIdzamowienie() {
        return idzamowienie;
    }

    public void setIdzamowienie(int idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idzamowienie;
        hash += (int) idmenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowieniemenuPK)) {
            return false;
        }
        ZamowieniemenuPK other = (ZamowieniemenuPK) object;
        if (this.idzamowienie != other.idzamowienie) {
            return false;
        }
        if (this.idmenu != other.idmenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.ZamowieniemenuPK[ idzamowienie=" + idzamowienie + ", idmenu=" + idmenu + " ]";
    }
    
}
