/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author 103039
 */
@Entity
@Table(name = "ZAMOWIENIEMENU")
@NamedQueries({
    @NamedQuery(name = "Zamowieniemenu.findAll", query = "SELECT z FROM Zamowieniemenu z")})
public class Zamowieniemenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZamowieniemenuPK zamowieniemenuPK;
    
    @Column(name = "ZREALIZOWANO")
    private boolean zrealizowano;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idmenu")
    private Menu menu;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idzamowienie")
    private Zamowienie zamowienie;

    public Zamowieniemenu() {
    }

    public Zamowieniemenu(ZamowieniemenuPK zamowieniemenuPK) {
        this.zamowieniemenuPK = zamowieniemenuPK;
    }

    public Zamowieniemenu(int idzamowienie, int idmenu) {
        this.zamowieniemenuPK = new ZamowieniemenuPK(idzamowienie, idmenu);
    }

    public ZamowieniemenuPK getZamowieniemenuPK() {
        return zamowieniemenuPK;
    }

    public void setZamowieniemenuPK(ZamowieniemenuPK zamowieniemenuPK) {
        this.zamowieniemenuPK = zamowieniemenuPK;
    }

    public boolean isZrealizowano() {
        return zrealizowano;
    }

    public void setZrealizowano(boolean zrealizowano) {
        this.zrealizowano = zrealizowano;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zamowieniemenuPK != null ? zamowieniemenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowieniemenu)) {
            return false;
        }
        Zamowieniemenu other = (Zamowieniemenu) object;
        if ((this.zamowieniemenuPK == null && other.zamowieniemenuPK != null) || (this.zamowieniemenuPK != null && !this.zamowieniemenuPK.equals(other.zamowieniemenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Zamowieniemenu[ zamowieniemenuPK=" + zamowieniemenuPK + " ]";
    }
    
}
