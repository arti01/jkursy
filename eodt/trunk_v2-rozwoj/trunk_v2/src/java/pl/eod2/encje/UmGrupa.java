/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.eod2.encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "UM_GRUPA")
public class UmGrupa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUMGRUPA")
    @SequenceGenerator(name = "SEQUMGRUPA", sequenceName = "SEQUMGRUPA")
    private Long id;
    @Size(min = 1, max = 256)
    @Column(name = "nazwa", nullable = false, length = 256, unique = true)
    private String nazwa;
    @OneToMany(mappedBy = "grupa", cascade = CascadeType.MERGE)
    private List<UmUrzadzenie> urzadzenieList;
    @ManyToOne()
    private UmMasterGrupa masterGrp;

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

    public List<UmUrzadzenie> getUrzadzenieList() {
        return urzadzenieList;
    }

    public void setUrzadzenieList(List<UmUrzadzenie> urzadzenieList) {
        this.urzadzenieList = urzadzenieList;
    }

    public UmMasterGrupa getMasterGrp() {
        return masterGrp;
    }

    public void setMasterGrp(UmMasterGrupa masterGrp) {
        this.masterGrp = masterGrp;
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
        if (!(object instanceof UmGrupa)) {
            return false;
        }
        UmGrupa other = (UmGrupa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.UmGrupa[ id=" + id + " ]";
    }
    
}
