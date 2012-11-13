/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.entit;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "lekcjarodzaje")
@NamedQueries({
    @NamedQuery(name = "Lekcjarodzaje.findAll", query = "SELECT l FROM Lekcjarodzaje l")})
    public class Lekcjarodzaje implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlekcjarodzaje")
    private Integer idlekcjarodzaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "opis")
    private String opis;
    
    @OneToMany(mappedBy = "lekcjarodzaje")
    private Collection<Lekcja> lekcjaCollection;

    public Lekcjarodzaje() {
    }

    public Lekcjarodzaje(Integer idlekcjarodzaje) {
        this.idlekcjarodzaje = idlekcjarodzaje;
    }

    public Lekcjarodzaje(Integer idlekcjarodzaje, String opis) {
        this.idlekcjarodzaje = idlekcjarodzaje;
        this.opis = opis;
    }

    public Integer getIdlekcjarodzaje() {
        return idlekcjarodzaje;
    }

    public void setIdlekcjarodzaje(Integer idlekcjarodzaje) {
        this.idlekcjarodzaje = idlekcjarodzaje;
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
        hash += (idlekcjarodzaje != null ? idlekcjarodzaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lekcjarodzaje)) {
            return false;
        }
        Lekcjarodzaje other = (Lekcjarodzaje) object;
        if ((this.idlekcjarodzaje == null && other.idlekcjarodzaje != null) || (this.idlekcjarodzaje != null && !this.idlekcjarodzaje.equals(other.idlekcjarodzaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arti01.entit.Lekcjarodzaje[ idlekcjarodzaje=" + idlekcjarodzaje + " ]";
    }

    @XmlTransient
    public Collection<Lekcja> getLekcjaCollection() {
        return lekcjaCollection;
    }

    public void setLekcjaCollection(Collection<Lekcja> lekcjaCollection) {
        this.lekcjaCollection = lekcjaCollection;
    }
    
}
