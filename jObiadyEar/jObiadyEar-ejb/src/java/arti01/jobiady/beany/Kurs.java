/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author arti01
 */
@Entity
public class Kurs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQKURS")
    @SequenceGenerator(name="SEQKURS", sequenceName="SEQKURS")
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, orphanRemoval = false)
    @OrderBy("idzamowienie DESC")
    private List<Zamowienie> zamowienia;
    
    private Timestamp dataKursu;
    
    @OneToOne
    private Uzytkownik tragarz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public Timestamp getDataKursu() {
        return dataKursu;
    }

    public void setDataKursu(Timestamp dataKursu) {
        this.dataKursu = dataKursu;
    }

    public Uzytkownik getTragarz() {
        return tragarz;
    }

    public void setTragarz(Uzytkownik tragarz) {
        this.tragarz = tragarz;
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
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.NewEntity[ id=" + id + " ]";
    }
    
}
