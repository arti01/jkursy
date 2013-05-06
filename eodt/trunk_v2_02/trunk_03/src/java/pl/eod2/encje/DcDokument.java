/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.eod.encje.Uzytkownik;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "dc_dokument")
@NamedQueries({
    @NamedQuery(name = "DcDokument.findAll", query = "SELECT d FROM DcDokument d"),
    @NamedQuery(name = "DcDokument.findById", query = "SELECT d FROM DcDokument d WHERE d.id = :id"),
    @NamedQuery(name = "DcDokument.findByNazwa", query = "SELECT d FROM DcDokument d WHERE d.nazwa = :tytul"),
    @NamedQuery(name = "DcDokument.findByOpis", query = "SELECT d FROM DcDokument d WHERE d.opis = :opis"),
    @NamedQuery(name = "DcDokument.findByDataWprow", query = "SELECT d FROM DcDokument d WHERE d.dataWprow = :dataWprow"),
    @NamedQuery(name = "DcDokument.findByDataDok", query = "SELECT d FROM DcDokument d WHERE d.dataDok = :dataDok")})
public class DcDokument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDCDOKUMENT")
    @SequenceGenerator(name = "SEQDCDOKUMENT", sequenceName = "SEQDCDOKUMENT")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String nazwa;
    @Size(max = 10485760)
    @Lob
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_wprow", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWprow;
    @Column(name = "data_dok")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDok;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uzytkownik userId;
    @JoinColumn(name = "zrodlo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcZrodlo zrodloId;
    @JoinColumn(name = "rodzaj_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcRodzaj rodzajId;
    @JoinColumn(name = "projekt_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcProjekt projektId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDok", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DcPlik> dcPlikList;
    @JoinColumn(name = "dokstatusid_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcDokumentStatus dokStatusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDok", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DcDokumentKrok> dcDokKrok;

    public DcDokument() {
    }

    public DcDokument(Integer id) {
        this.id = id;
    }

    public DcDokument(Integer id, String tytul, Date dataWprow, int userId) {
        this.id = id;
        this.nazwa = tytul;
        this.dataWprow = dataWprow;
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

    public Date getDataWprow() {
        return dataWprow;
    }

    public void setDataWprow(Date dataWprow) {
        this.dataWprow = dataWprow;
    }

    public Date getDataDok() {
        return dataDok;
    }

    public void setDataDok(Date dataDok) {
        this.dataDok = dataDok;
    }

    public Uzytkownik getUserId() {
        return userId;
    }

    public void setUserId(Uzytkownik userId) {
        this.userId = userId;
    }

    public DcZrodlo getZrodloId() {
        return zrodloId;
    }

    public void setZrodloId(DcZrodlo zrodloId) {
        this.zrodloId = zrodloId;
    }

    public DcRodzaj getRodzajId() {
        return rodzajId;
    }

    public void setRodzajId(DcRodzaj rodzajId) {
        this.rodzajId = rodzajId;
    }

    public DcProjekt getProjektId() {
        return projektId;
    }

    public void setProjektId(DcProjekt projektId) {
        this.projektId = projektId;
    }

    public List<DcPlik> getDcPlikList() {
        return dcPlikList;
    }

    public void setDcPlikList(List<DcPlik> dcPlikList) {
        this.dcPlikList = dcPlikList;
    }

    public DcDokumentStatus getDokStatusId() {
        return dokStatusId;
    }

    public void setDokStatusId(DcDokumentStatus dokStatusId) {
        this.dokStatusId = dokStatusId;
    }

    public List<DcDokumentKrok> getDcDokKrok() {
        return dcDokKrok;
    }

    public void setDcDokKrok(List<DcDokumentKrok> dcDokKrok) {
        this.dcDokKrok = dcDokKrok;
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
        if (!(object instanceof DcDokument)) {
            return false;
        }
        DcDokument other = (DcDokument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.DcDokument[ id=" + id + " ]";
    }
    
}
