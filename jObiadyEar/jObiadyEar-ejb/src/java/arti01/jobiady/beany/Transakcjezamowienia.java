/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "transakcjezamowienia")
@NamedQueries({
    @NamedQuery(name = "Transakcjezamowienia.findAll", query = "SELECT t FROM Transakcjezamowienia t")})
public class Transakcjezamowienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtransakcjezamowienia")
    private Integer idtransakcjezamowienia;
    @Column(name = "dataoperacji")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataoperacji;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kwota")
    private double kwota;
    @Size(max = 255)
    @Column(name = "tytulem")
    private String tytulem;
    @JoinColumn(name = "idzamowienie", referencedColumnName = "idzamowienie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Zamowienie idzamowienie;

    public Transakcjezamowienia() {
    }

    public Transakcjezamowienia(Integer idtransakcjezamowienia) {
        this.idtransakcjezamowienia = idtransakcjezamowienia;
    }

    public Transakcjezamowienia(Integer idtransakcjezamowienia, double kwota) {
        this.idtransakcjezamowienia = idtransakcjezamowienia;
        this.kwota = kwota;
    }

    public Integer getIdtransakcjezamowienia() {
        return idtransakcjezamowienia;
    }

    public void setIdtransakcjezamowienia(Integer idtransakcjezamowienia) {
        this.idtransakcjezamowienia = idtransakcjezamowienia;
    }

    public Date getDataoperacji() {
        return dataoperacji;
    }

    public void setDataoperacji(Date dataoperacji) {
        this.dataoperacji = dataoperacji;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public String getTytulem() {
        return tytulem;
    }

    public void setTytulem(String tytulem) {
        this.tytulem = tytulem;
    }

    public Zamowienie getIdzamowienie() {
        return idzamowienie;
    }

    public void setIdzamowienie(Zamowienie idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransakcjezamowienia != null ? idtransakcjezamowienia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcjezamowienia)) {
            return false;
        }
        Transakcjezamowienia other = (Transakcjezamowienia) object;
        if ((this.idtransakcjezamowienia == null && other.idtransakcjezamowienia != null) || (this.idtransakcjezamowienia != null && !this.idtransakcjezamowienia.equals(other.idtransakcjezamowienia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Transakcjezamowienia[ idtransakcjezamowienia=" + idtransakcjezamowienia + " ]";
    }
    
}
