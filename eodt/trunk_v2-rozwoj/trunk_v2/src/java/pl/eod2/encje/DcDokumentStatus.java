/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "dc_dokument_status")
@NamedQueries({
    @NamedQuery(name = "DcDokumentStatus.findAll", query = "SELECT d FROM DcDokumentStatus d"),
    @NamedQuery(name = "DcDokumentStatus.findById", query = "SELECT d FROM DcDokumentStatus d WHERE d.id = :id"),
    @NamedQuery(name = "DcDokumentStatus.findByNazwa", query = "SELECT d FROM DcDokumentStatus d WHERE d.nazwa = :nazwa"),
    @NamedQuery(name = "DcDokumentStatus.findByOpis", query = "SELECT d FROM DcDokumentStatus d WHERE d.opis = :opis")})
public class DcDokumentStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "nazwa", length = 255)
    private String nazwa;
    @Size(max = 255)
    @Column(name = "opis", length = 255)
    private String opis;
    @Size(max = 10)
    @Column(name = "kolor", length = 10)
    private String kolor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dokStatusId", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<DcDokument> dcDokumentList;

    public DcDokumentStatus() {
    }

    public DcDokumentStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public List<DcDokument> getDcDokumentList() {
        return dcDokumentList;
    }

    public void setDcDokumentList(List<DcDokument> dcDokumentList) {
        this.dcDokumentList = dcDokumentList;
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
        if (!(object instanceof DcDokumentStatus)) {
            return false;
        }
        DcDokumentStatus other = (DcDokumentStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.DcDokumentStatus[ id=" + id + " ]";
    }
    
}
