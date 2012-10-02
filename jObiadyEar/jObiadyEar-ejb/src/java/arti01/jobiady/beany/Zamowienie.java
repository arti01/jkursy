/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "ZAMOWIENIE")
@NamedQueries({
    @NamedQuery(name = "Zamowienie.findAll", query = "SELECT z FROM Zamowienie z"),
    @NamedQuery(name = "Zamowienie.findByIdzamowienie", query = "SELECT z FROM Zamowienie z WHERE z.idzamowienie = :idzamowienie"),
    @NamedQuery(name = "Zamowienie.findByUsername", query = "SELECT z FROM Zamowienie z WHERE z.username = :username"),
    @NamedQuery(name = "Zamowienie.findByData", query = "SELECT z FROM Zamowienie z WHERE z.data = :data")})
public class Zamowienie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDZAMOWIENIE", nullable = false)
    private Integer idzamowienie;
    
    @Column(name = "DATAZAMOWIENIA")
    private Character dataZamowienia;
    
    @ManyToOne
    @JoinColumn(name = "username")
    private Uzytkownik uzytkownik;
    
    public Zamowienie() {
    }

    public Zamowienie(Integer idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    public Integer getIdzamowienie() {
        return idzamowienie;
    }

    public void setIdzamowienie(Integer idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    public Character getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(Character dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idzamowienie != null ? idzamowienie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienie)) {
            return false;
        }
        Zamowienie other = (Zamowienie) object;
        if ((this.idzamowienie == null && other.idzamowienie != null) || (this.idzamowienie != null && !this.idzamowienie.equals(other.idzamowienie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Zamowienie[ idzamowienie=" + idzamowienie + " ]";
    }
    
}
