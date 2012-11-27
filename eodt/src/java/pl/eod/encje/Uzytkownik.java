/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "uzytkownik")
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findById", query = "SELECT u FROM Uzytkownik u WHERE u.id = :id"),
    @NamedQuery(name = "Uzytkownik.findByFullname", query = "SELECT u FROM Uzytkownik u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "Uzytkownik.findByAdrEmail", query = "SELECT u FROM Uzytkownik u WHERE u.adrEmail = :adrEmail"),
    @NamedQuery(name = "Uzytkownik.findByExtId", query = "SELECT u FROM Uzytkownik u WHERE u.extId = :extId")})
public class Uzytkownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUSER")
    @SequenceGenerator(name = "SEQUSER", sequenceName = "SEQUSER")
    private Long id;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "adr_email")
    private String adrEmail;
    @Column(name = "ext_id")
    private BigInteger extId;
    @OneToOne(mappedBy = "secUserId")
    Struktura strukturaSec;
    @OneToOne(mappedBy = "userId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.ALL})
    Struktura struktura;

    public Uzytkownik() {
    }

    public Uzytkownik(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdrEmail() {
        return adrEmail;
    }

    public void setAdrEmail(String adrEmail) {
        this.adrEmail = adrEmail;
    }

    public BigInteger getExtId() {
        return extId;
    }

    public void setExtId(BigInteger extId) {
        this.extId = extId;
    }

    public Struktura getStrukturaSec() {
        return strukturaSec;
    }

    public void setStrukturaSec(Struktura strukturaSec) {
        this.strukturaSec = strukturaSec;
    }

    public Struktura getStruktura() {
        return struktura;
    }

    public void setStruktura(Struktura struktura) {
        this.struktura = struktura;
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
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod.encje.Uzytkownik[ id=" + id + " ]";
    }
    
}
