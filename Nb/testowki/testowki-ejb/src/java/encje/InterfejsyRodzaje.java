/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
public class InterfejsyRodzaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQInterfejsyRodzaje")
    @SequenceGenerator(name = "SEQInterfejsyRodzaje", sequenceName = "SEQInterfejsyRodzaje")
    private Long id;
    @NotNull
    @Size(min = 1, max = 200)
    private String nazwa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interfejsyRodzaje", orphanRemoval = true)
    List<InterfejsySystemy> interfejsySystemyList;

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

    public List<InterfejsySystemy> getInterfejsySystemyList() {
        return interfejsySystemyList;
    }

    public void setInterfejsySystemyList(List<InterfejsySystemy> interfejsySystemyList) {
        this.interfejsySystemyList = interfejsySystemyList;
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
        if (!(object instanceof InterfejsyRodzaje)) {
            return false;
        }
        InterfejsyRodzaje other = (InterfejsyRodzaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.InterfejsyRodzaje[ id=" + id + " ]";
    }
    
}
