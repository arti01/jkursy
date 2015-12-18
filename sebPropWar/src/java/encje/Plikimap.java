/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import abstr.AbstEncja;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 103039
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SRODOWISKO", "nazwa"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plikimap.findAll", query = "SELECT p FROM Plikimap p"),
    @NamedQuery(name = "Plikimap.findById", query = "SELECT p FROM Plikimap p WHERE p.id = :id"),
    @NamedQuery(name = "Plikimap.findByNazwa", query = "SELECT d FROM Plikimap d WHERE d.nazwa = :nazwa"),
    @NamedQuery(name = "Plikimap.findBySrodowisko", query = "SELECT p FROM Plikimap p WHERE p.srodowisko = :srodowisko")})
public class Plikimap extends AbstEncja implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPlikimap")
    @SequenceGenerator(name = "SEQPlikimap", sequenceName = "SEQPlikimap")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false, length = 250)
    private String srodowisko;
    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false, length = 250)
    private String nazwa;
    @NotNull
    @Column(name = "data_zmiany", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZmiany;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "plikimap", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PlikimapDane> plikimapDaneList;
    
    public Plikimap() {
    }

    public String getSrodowisko() {
        return srodowisko;
    }

    public void setSrodowisko(String srodowisko) {
        this.srodowisko = srodowisko;
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getDataZmiany() {
        return dataZmiany;
    }

    @Override
    public void setDataZmiany(Date dataZmiany) {
        this.dataZmiany = dataZmiany;
    }    

    public List<PlikimapDane> getPlikimapDaneList() {
        return plikimapDaneList;
    }

    public void setPlikimapDaneList(List<PlikimapDane> plikimapDaneList) {
        this.plikimapDaneList = plikimapDaneList;
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
        if (!(object instanceof Plikimap)) {
            return false;
        }
        Plikimap other = (Plikimap) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "encje.Plikimap[ id=" + id + " ]";
    }
    
}
