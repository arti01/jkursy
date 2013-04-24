/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

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
import javax.validation.constraints.NotNull;
import pl.eod.encje.Uzytkownik;

/**
 *
 * @author 103039
 */
@Entity
@Table(name = "DC_DOKUMENT_KROK_UZYTKOWNIK")
@NamedQueries({
    @NamedQuery(name = "DcDokumentKrokUzytkownik.findAll", query = "SELECT d FROM DcDokumentKrokUzytkownik d"),
    @NamedQuery(name = "DcDokumentKrokUzytkownik.findById", query = "SELECT d FROM DcDokumentKrokUzytkownik d WHERE d.id = :id"),
    @NamedQuery(name = "DcDokumentKrokUzytkownik.findByIdUser", query = "SELECT d FROM DcDokumentKrokUzytkownik d WHERE d.idUser = :idUser"),
    @NamedQuery(name = "DcDokumentKrokUzytkownik.findByIdDokumentKrok", query = "SELECT d FROM DcDokumentKrokUzytkownik d WHERE d.idDokumentKrok = :idDokumentKrok"),
    @NamedQuery(name = "DcDokumentKrokUzytkownik.findByAkcept", query = "SELECT d FROM DcDokumentKrokUzytkownik d WHERE d.akcept = :akcept")})
public class DcDokumentKrokUzytkownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDcDokumentKrokUzytkownik")
    @SequenceGenerator(name = "SEQDcDokumentKrokUzytkownik", sequenceName = "SEQDcDokumentKrokUzytkownik")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "ID_USER", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uzytkownik idUser;
    @JoinColumn(name = "ID_DOKUMENT_KROK", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcDokumentKrok idDokumentKrok;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AKCEPT", nullable = false)
    private int akcept;

    public DcDokumentKrokUzytkownik() {
    }

    public DcDokumentKrokUzytkownik(Integer id) {
        this.id = id;
    }

    public DcDokumentKrokUzytkownik(Integer id, int idUser, int idDokumentKrok, int akcept) {
        this.id = id;
        this.akcept = akcept;
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

    public DcDokumentKrok getIdDokumentKrok() {
        return idDokumentKrok;
    }

    public void setIdDokumentKrok(DcDokumentKrok idDokumentKrok) {
        this.idDokumentKrok = idDokumentKrok;
    }

    public int getAkcept() {
        return akcept;
    }

    public void setAkcept(int akcept) {
        this.akcept = akcept;
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
        if (!(object instanceof DcDokumentKrokUzytkownik)) {
            return false;
        }
        DcDokumentKrokUzytkownik other = (DcDokumentKrokUzytkownik) object;
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
