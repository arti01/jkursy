/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.eod.abstr.AbstEncja;
import pl.eod.encje.Uzytkownik;

@Entity
@Table(name = "dc_dokument_dcdokarch")
@NamedQueries({
    @NamedQuery(name = "DcArchDcDokument.findAll", query = "SELECT d FROM DcArchDcDokument d"),
    @NamedQuery(name = "DcArchDcDokument.findById", query = "SELECT d FROM DcArchDcDokument d WHERE d.id = :id"),
    @NamedQuery(name = "DcArchDcDokument.findByNazwa", query = "SELECT d FROM DcArchDcDokument d WHERE d.nazwa = :nazwa"),
    @NamedQuery(name = "DcArchDcDokument.findByOpis", query = "SELECT d FROM DcArchDcDokument d WHERE d.opis = :opis")})
public class DcArchDcDokument extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDCARCHDCDOC")
    @SequenceGenerator(name = "SEQDCARCHDCDOC", sequenceName = "SEQDCARCHDCDOC")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Size(min = 0, max = 256)
    @Column(nullable = true, length = 256, unique = false)
    private String nazwa;
    @Size(max = 10485760)
    @Lob
    private String opis;
    
    @Column(name="data_wyp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWypozycz;
     //- data wypożyczenia
    
    @Column(name="data_pl_zwr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPlanZwrot;
    //- data planowanego zwrotu
    
    @Column(name="data_real_zwr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRzeczZwrot; 
    //data_real_zwr - data rzeczywistego zwrotu

    @Column(name="imie_nazwisko")
    private String imieNazwisko;
    //nazwisko_imie - nazwisko i imię osoby wypożyczającej
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Uzytkownik wydal;
    //id użytkownika który jest archiwistą i wydał dany dokument

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcDokument dokument;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcDokumentArch dokumentArch;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDataWypozycz() {
        return dataWypozycz;
    }

    public void setDataWypozycz(Date dataWypozycz) {
        this.dataWypozycz = dataWypozycz;
    }

    public Date getDataPlanZwrot() {
        return dataPlanZwrot;
    }

    public void setDataPlanZwrot(Date dataPlanZwrot) {
        this.dataPlanZwrot = dataPlanZwrot;
    }

    public Date getDataRzeczZwrot() {
        return dataRzeczZwrot;
    }

    public void setDataRzeczZwrot(Date dataRzeczZwrot) {
        this.dataRzeczZwrot = dataRzeczZwrot;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public Uzytkownik getWydal() {
        return wydal;
    }

    public void setWydal(Uzytkownik wydal) {
        this.wydal = wydal;
    }

    public DcDokument getDokument() {
        return dokument;
    }

    public void setDokument(DcDokument dokument) {
        this.dokument = dokument;
    }

    public DcDokumentArch getDokumentArch() {
        return dokumentArch;
    }

    public void setDokumentArch(DcDokumentArch dokumentArch) {
        this.dokumentArch = dokumentArch;
    }

    @Override
    public String toString() {
        return "DcArchDcDokument{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DcArchDcDokument other = (DcArchDcDokument) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
