/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "galeriaszkoly")
@NamedQueries({
    @NamedQuery(name = "Galeriaszkoly.findAll", query = "SELECT g FROM Galeriaszkoly g"),
    @NamedQuery(name = "Galeriaszkoly.findAllgaleriaKoncowa", query = "SELECT lfk FROM Lekcjafotykursant lfk WHERE lfk.lekcja.lekcjarodzaje.idlekcjarodzaje=3 order by lfk.idlekcjafotykursant desc"),
    @NamedQuery(name = "Galeriaszkoly.findAllbezGaleriiSzkoly", query = "SELECT lfk FROM Lekcjafotykursant lfk where lfk.idlekcjafotykursant not in "
        + "(SELECT gsz.lekcjafotykursant.idlekcjafotykursant  FROM Galeriaszkoly gsz JOIN gsz.lekcjafotykursant lkf) AND lfk.lekcja.lekcjarodzaje.idlekcjarodzaje=3 "
        + "order by lfk.idlekcjafotykursant desc"),
    @NamedQuery(name = "Galeriaszkoly.findAllgaleriaSzkoly", query = "SELECT gsz.lekcjafotykursant  FROM Galeriaszkoly gsz JOIN gsz.lekcjafotykursant lkf order by gsz.idgaleriaszkoly desc")
})
public class Galeriaszkoly implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgaleriaszkoly")
    private Integer idgaleriaszkoly;
    @JoinColumn(name = "idlekcjafoty", referencedColumnName = "idlekcjafotykursant")
    @OneToOne(optional = false)
    private Lekcjafotykursant lekcjafotykursant;

    public Galeriaszkoly() {
    }

    public Galeriaszkoly(Integer idgaleriaszkoly) {
        this.idgaleriaszkoly = idgaleriaszkoly;
    }

    public Integer getIdgaleriaszkoly() {
        return idgaleriaszkoly;
    }

    public void setIdgaleriaszkoly(Integer idgaleriaszkoly) {
        this.idgaleriaszkoly = idgaleriaszkoly;
    }

    public Lekcjafotykursant getLekcjafotykursant() {
        return lekcjafotykursant;
    }

    public void setLekcjafotykursant(Lekcjafotykursant lekcjafotykursant) {
        this.lekcjafotykursant = lekcjafotykursant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgaleriaszkoly != null ? idgaleriaszkoly.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Galeriaszkoly)) {
            return false;
        }
        Galeriaszkoly other = (Galeriaszkoly) object;
        if ((this.idgaleriaszkoly == null && other.idgaleriaszkoly != null) || (this.idgaleriaszkoly != null && !this.idgaleriaszkoly.equals(other.idgaleriaszkoly))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Galeriaszkoly[ idgaleriaszkoly=" + idgaleriaszkoly + " ]";
    }
    
}
