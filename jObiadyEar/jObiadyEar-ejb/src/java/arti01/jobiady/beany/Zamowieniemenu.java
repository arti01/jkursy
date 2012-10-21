/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "zamowieniemenu")
@NamedQueries({
    @NamedQuery(name = "Zamowieniemenu.findAll", query = "SELECT z FROM Zamowieniemenu z")})
public class Zamowieniemenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQZAMMEN")
    @SequenceGenerator(name="SEQZAMMEN", sequenceName="SEQZAMMEN")
    private Long id;
    @Column(name = "zrealizowano")
    private Boolean zrealizowano;

    @JoinColumn(name = "idzamowienie", referencedColumnName = "idzamowienie")    
    @ManyToOne(fetch = FetchType.LAZY)
    private Zamowienie zamowienie;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;

    public Zamowieniemenu() {
    }

    public Zamowieniemenu(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isZrealizowano() {
        return zrealizowano;
    }

    public void setZrealizowano(Boolean zrealizowano) {
        this.zrealizowano = zrealizowano;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowieniemenu)) {
            return false;
        }
        Zamowieniemenu other = (Zamowieniemenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Zamowieniemenu[ id=" + id + " ]";
    }
    
}
