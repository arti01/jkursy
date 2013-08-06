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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
public class InterfejsySystemy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQInterfejsySystemy")
    @SequenceGenerator(name = "SEQInterfejsySystemy", sequenceName = "SEQInterfejsySystemy")
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 200)
    private String nazwa;
    
    @Size(max = 255)
    private String opis;
    
    @ManyToOne
    private InterfejsyRodzaje interfejsyRodzaje;
    
    @ManyToOne
    private Systemy systemy;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interfejsySystemy", orphanRemoval = true)
    List<InterfejsyInstancje> interfejsyInstancjeList;

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

    public InterfejsyRodzaje getInterfejsyRodzaje() {
        return interfejsyRodzaje;
    }

    public void setInterfejsyRodzaje(InterfejsyRodzaje interfejsyRodzaje) {
        this.interfejsyRodzaje = interfejsyRodzaje;
    }

    public Systemy getSystemy() {
        return systemy;
    }

    public void setSystemy(Systemy systemy) {
        this.systemy = systemy;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<InterfejsyInstancje> getInterfejsyInstancjeList() {
        return interfejsyInstancjeList;
    }

    public void setInterfejsyInstancjeList(List<InterfejsyInstancje> interfejsyInstancjeList) {
        this.interfejsyInstancjeList = interfejsyInstancjeList;
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
        if (!(object instanceof InterfejsySystemy)) {
            return false;
        }
        InterfejsySystemy other = (InterfejsySystemy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.InterfejsySystemy[ id=" + id + " ]";
    }
    
}
