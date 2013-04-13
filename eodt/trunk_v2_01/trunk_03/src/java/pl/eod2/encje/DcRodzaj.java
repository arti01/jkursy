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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "dc_rodzaj")
@NamedQueries({
    @NamedQuery(name = "DcRodzaj.findAll", query = "SELECT d FROM DcRodzaj d"),
    @NamedQuery(name = "DcRodzaj.findById", query = "SELECT d FROM DcRodzaj d WHERE d.id = :id"),
    @NamedQuery(name = "DcRodzaj.findByNazwa", query = "SELECT d FROM DcRodzaj d WHERE d.nazwa = :nazwa"),
    @NamedQuery(name = "DcRodzaj.findByOpis", query = "SELECT d FROM DcRodzaj d WHERE d.opis = :opis")})
public class DcRodzaj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDCRODZAJ")
    @SequenceGenerator(name = "SEQDCRODZAJ", sequenceName = "SEQDCRODZAJ")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nazwa;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String opis;
    @JoinColumn(name = "id_rodzaj_grupa", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcRodzajGrupa idRodzajGrupa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rodzajId", fetch = FetchType.LAZY)
    private List<DcDokument> dcDokumentList;

    public DcRodzaj() {
    }

    public DcRodzaj(Integer id) {
        this.id = id;
    }

    public DcRodzaj(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
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

    public DcRodzajGrupa getIdRodzajGrupa() {
        return idRodzajGrupa;
    }

    public void setIdRodzajGrupa(DcRodzajGrupa idRodzajGrupa) {
        this.idRodzajGrupa = idRodzajGrupa;
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
        if (!(object instanceof DcRodzaj)) {
            return false;
        }
        DcRodzaj other = (DcRodzaj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.DcRodzaj[ id=" + id + " ]";
    }
    
}
