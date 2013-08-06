/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
public class InterfejsyInstancje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQInterfejsyInstancje")
    @SequenceGenerator(name = "SEQInterfejsyInstancje", sequenceName = "SEQInterfejsyInstancje")
    private Long id;

    @Size(min = 0, max = 250)
    private String namiary;
    
    @ManyToOne
    private Instancje instancje;
    
    @ManyToOne
    private InterfejsySystemy interfejsySystemy;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instancje getInstancje() {
        return instancje;
    }

    public void setInstancje(Instancje instancje) {
        this.instancje = instancje;
    }

    public InterfejsySystemy getInterfejsySystemy() {
        return interfejsySystemy;
    }

    public void setInterfejsySystemy(InterfejsySystemy interfejsySystemy) {
        this.interfejsySystemy = interfejsySystemy;
    }

    public String getNamiary() {
        return namiary;
    }

    public void setNamiary(String namiary) {
        this.namiary = namiary;
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
        if (!(object instanceof InterfejsyInstancje)) {
            return false;
        }
        InterfejsyInstancje other = (InterfejsyInstancje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.InterfejsyInstancje[ id=" + id + " ]";
    }
    
}
