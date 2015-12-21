/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import abstr.AbstEncja;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @UniqueConstraint(columnNames = {"NAZWA, id_plikimap"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlikimapDane.findAll", query = "SELECT p FROM PlikimapDane p"),
    @NamedQuery(name = "PlikimapDane.findById", query = "SELECT p FROM PlikimapDane p WHERE p.id = :id"),
    @NamedQuery(name = "PlikimapDane.findByNazwa", query = "SELECT d FROM PlikimapDane d WHERE d.nazwa = :nazwa"),})
public class PlikimapDane extends AbstEncja implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPlikimapDane")
    @SequenceGenerator(name = "SEQPlikimapDane", sequenceName = "SEQPlikimapDane", allocationSize = 10)
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false, length = 250)
    private String nazwa;
    @NotNull
    @Size(max = 250)
    @Column(length = 250)
    private String dane;
    @Size(max = 250)
    @Column(length = 250)
    private String opis;
    
    @JoinColumn(name = "id_plikimap", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Plikimap plikimap;
    
    public PlikimapDane() {
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

    public String getDane() {
        return dane;
    }

    public void setDane(String dane) {
        this.dane = dane;
    }

    public Plikimap getPlikimap() {
        return plikimap;
    }

    public void setPlikimap(Plikimap plikimap) {
        this.plikimap = plikimap;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
        if (!(object instanceof PlikimapDane)) {
            return false;
        }
        PlikimapDane other = (PlikimapDane) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "encje.PlikimapDane[ id=" + id + " ]";
    }
    
}
