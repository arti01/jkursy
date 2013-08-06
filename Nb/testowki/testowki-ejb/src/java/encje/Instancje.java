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
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
public class Instancje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQInstancje")
    @SequenceGenerator(name = "SEQInstancje", sequenceName = "SEQInstancje")
    private Long id;

    @Size(min = 0, max = 200)
    private String adresip;
    
    @Size(min = 0, max = 200)
    private String nazwaserwera;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Systemy systemy;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ZestawyTestowe zestawyTestowe;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instancje", orphanRemoval = true)
    List<InterfejsyInstancje> interfejsyInstancjeList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Systemy getSystemy() {
        return systemy;
    }

    public void setSystemy(Systemy systemy) {
        this.systemy = systemy;
    }

    public ZestawyTestowe getZestawyTestowe() {
        return zestawyTestowe;
    }

    public void setZestawyTestowe(ZestawyTestowe zestawyTestowe) {
        this.zestawyTestowe = zestawyTestowe;
    }

    public String getAdresip() {
        return adresip;
    }

    public void setAdresip(String adresip) {
        this.adresip = adresip;
    }

    public String getNazwaserwera() {
        return nazwaserwera;
    }

    public void setNazwaserwera(String nazwaserwera) {
        this.nazwaserwera = nazwaserwera;
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
        if (!(object instanceof Instancje)) {
            return false;
        }
        Instancje other = (Instancje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.SystemZestaw[ id=" + id + " ]";
    }
    
}
