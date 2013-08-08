/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author 103039
 */
@Entity
public class Admini implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQAdmini")
    @SequenceGenerator(name = "SEQAdmini", sequenceName = "SEQAdmini")
    private Long id;
    
    @NotNull
    @NotEmpty
    @Size(min=3, max=250)
    private String nazwa;
    
    @Size(min=3, max=250)
    private String telefon;
    
    @ManyToOne
    private ZespolyAdminow zespolyAdminow;

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public ZespolyAdminow getZespolyAdminow() {
        return zespolyAdminow;
    }

    public void setZespolyAdminow(ZespolyAdminow zespolyAdminow) {
        this.zespolyAdminow = zespolyAdminow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Admini)) {
            return false;
        }
        Admini other = (Admini) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.Admini[ id=" + id + " ]";
    }
    
}
