/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAZWA"})})
public class ZespolyAdminow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQZespolyAdminow")
    @SequenceGenerator(name = "SEQZespolyAdminow", sequenceName = "SEQZespolyAdminow")
    private Long id;
    @NotNull
    @Size(min = 1, max = 200)
    private String nazwa;
    @NotNull
    @Size(min = 1, max = 200)
    private String telefony;
    @OrderBy(value = "nazwa ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zespolyAdminow",orphanRemoval = true)
    private List<Systemy> systemyList;
    
    @OrderBy(value = "nazwa ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zespolyAdminow",orphanRemoval = true)
    private List<Admini> adminiList;
    
    @Transient
    private String systemyRazem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTelefony() {
        return telefony;
    }

    public void setTelefony(String telefony) {
        this.telefony = telefony;
    }

    public List<Systemy> getSystemyList() {
        if(systemyList!=null){
            Collections.sort(systemyList, new ZespAdmComparSyst());   
        }
        return systemyList;
    }

    public void setSystemyList(List<Systemy> SystemyList) {
        this.systemyList = SystemyList;
    }

    public List<Admini> getAdminiList() {
        return adminiList;
    }

    public void setAdminiList(List<Admini> adminiList) {
        this.adminiList = adminiList;
    }

    public String getSystemyRazem() {
        String wynik = null;
        for(Systemy s:getSystemyList()){
            wynik=wynik+s.getNazwa()+" ";
        }
        return wynik;
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
        if (!(object instanceof ZespolyAdminow)) {
            return false;
        }
        ZespolyAdminow other = (ZespolyAdminow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.zespoly[ id=" + id + " ]";
    }
}
