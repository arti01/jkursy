/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.eod.encje.Uzytkownik;

/**
 *
 * @author 103039
 */
@Entity
@Table(name = "DC_DOKUMENT_KROK_UZYTKOWNIK_ARCH")

public class DcDokumentKrokUzytkownikArch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDcDokumentKrokUzytkownikArch")
    @SequenceGenerator(name = "SEQDcDokumentKrokUzytkownikArch", sequenceName = "SEQDcDokumentKrokUzytkownikArch")
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "data_akcept", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAkcept;
    @Size(max = 255)
    @Column(name = "informacja", length = 255)
    private String informacja;
    @JoinColumn(name = "ID_USER", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uzytkownik idUser;
    @JoinColumn(name = "ID_DOKUMENT_KROK_ARCH", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcDokumentKrokArch idDokumentKrokArch;
    @JoinColumn(name = "AKCEPT", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcAkceptStatus akcept;

    public DcDokumentKrokUzytkownikArch() {
        
    }

    public DcDokumentKrokUzytkownikArch(Integer id) {
        this.id = id;
    }

    public DcDokumentKrokUzytkownikArch(Integer id, int idUser, int idDokumentKrok, int akcept) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Uzytkownik getIdUser() {
        return idUser;
    }

    public void setIdUser(Uzytkownik idUser) {
        this.idUser = idUser;
    }

    public DcDokumentKrokArch getIdDokumentKrokArch() {
        return idDokumentKrokArch;
    }

    public void setIdDokumentKrokArch(DcDokumentKrokArch idDokumentKrokArch) {
        this.idDokumentKrokArch = idDokumentKrokArch;
    }

    public DcAkceptStatus getAkcept() {
        return akcept;
    }

    public void setAkcept(DcAkceptStatus akcept) {
        this.akcept = akcept;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Date getDataAkcept() {
        return dataAkcept;
    }

    public void setDataAkcept(Date dataAkcept) {
        this.dataAkcept = dataAkcept;
    }

    public String getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcDokumentKrokUzytkownikArch)) {
            return false;
        }
        DcDokumentKrokUzytkownikArch other = (DcDokumentKrokUzytkownikArch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.DcDokumentKrokUzytkownik[ id=" + id + " ]";
    }
    
}
