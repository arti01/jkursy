/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.persistence.Transient;
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
    @NamedQuery(name = "DcDokument.findByDataDok", query = "SELECT d FROM DcDokument d WHERE d.dataDok = :dataDok"),
    @NamedQuery(name = "DcDokument.findByStatus", query = "SELECT d FROM DcDokument d WHERE d.dokStatusId.id = :statusId"),
    @NamedQuery(name = "DcDokument.findMaxNrKol", query = "SELECT max(d.symbolNrKol) FROM DcDokument d WHERE d.symbolSpDzialRok=:symbolSpDzialRok")
})
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
    @Size(max = 10485760)
    @Lob
    @Column(name = "opis_dlugi")
    private String opisDlugi;
    @Size(max = 50)
    @Column(name = "symbol_sp_dzial_rok")
    private String symbolSpDzialRok;
    @Column(name = "symbol_nr_kol")
    private int symbolNrKol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_wprow", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWprow;
    @Column(name = "data_dok")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDok;
    @Column(name = "arch_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date archData;
    @Size(max = 256)
    @Column(name = "arch_osoba_zdajaca", length = 256)
    private String archOsobaZdajaca;
    @Size(max = 256)
    @Column(name = "arch_osoba_odpowiadajaca", length = 256)
    private String archOsobaOdpowiadajaca;
    @Size(max = 256)
    @Column(name = "arch_pokoj ", length = 256)
    private String archPokoj;
    @Size(max = 256)
    @Column(name = "arch_regal ", length = 256)
    private String archRegal;
    @Size(max = 256)
    @Column(name = "arch_polka ", length = 256)
    private String archPolka;
    @Size(max = 256)
    @Column(name = "arch_karton ", length = 256)
    private String archKarton;
    @Size(max = 256)
    @Column(name = "arch_teczka ", length = 256)
    private String archTeczka;
    
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
    @JoinColumn(name = "kontrahent_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcKontrahenci kontrahentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dokid", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DcDokDoWiadomosci> dcDokDoWiadList;
    @Transient
    private String dataWprowStr;
    @Transient
    private String dataDokStr;
    @Transient
    private String procentWykonania;
    @Transient
    private boolean alertAkceptacja;
    @Transient
    private String symbolDok;
    
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

    public String getOpisDlugi() {
        return opisDlugi;
    }

    public String getSymbolSpDzialRok() {
        return symbolSpDzialRok;
    }

    public void setSymbolSpDzialRok(String symbolSpDzialRok) {
        this.symbolSpDzialRok = symbolSpDzialRok;
    }

    public void setOpisDlugi(String opisDlugi) {
        this.opisDlugi = opisDlugi;
    }

    public DcKontrahenci getKontrahentId() {
        return kontrahentId;
    }

    public void setKontrahentId(DcKontrahenci kontrahentId) {
        this.kontrahentId = kontrahentId;
    }

    public List<DcDokDoWiadomosci> getDcDokDoWiadList() {
        return dcDokDoWiadList;
    }

    public void setDcDokDoWiadList(List<DcDokDoWiadomosci> dcDokDoWiadList) {
        this.dcDokDoWiadList = dcDokDoWiadList;
    }

    public String getDataWprowStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(getDataWprow());
    }

    public String getDataDokStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (getDataDok() == null) {
            return "";
        } else {
            return sdf.format(getDataDok());
        }
    }

    public String getProcentWykonania() {
        if(getDokStatusId().getId()==3){
            return "100";
        }
        int krokiAll=getDcDokKrok().size();
        int krokiZaakceptowane=0;
        if(krokiAll==0) return "brak";
        for(DcDokumentKrok krok: getDcDokKrok()){
            if(krok.getAkcept().getId()==4){
                krokiZaakceptowane++;
            }
        }
        return (krokiZaakceptowane*100)/krokiAll+"";
    }

    public boolean isAlertAkceptacja() {
        if(this.getDokStatusId().getId()!=2) return false;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -getRodzajId().getLimitCzaasuAkceptacji());
        return getDataWprow().before(c.getTime());
    }

    public void setAlertAkceptacja(boolean alertAkceptacja) {
        this.alertAkceptacja = alertAkceptacja;
    }

    public String getSymbolDok() {
        try{
        symbolDok=this.symbolNrKol+"/"+this.symbolSpDzialRok+"/"+this.getRodzajId().getSymbol();
        }catch (NullPointerException ex){
            return null;
        }
        return symbolDok;
    }

    public int getSymbolNrKol() {
        return symbolNrKol;
    }

    public void setSymbolNrKol(int symbolNrKol) {
        this.symbolNrKol = symbolNrKol;
    }

    public Date getArchData() {
        return archData;
    }

    public void setArchData(Date archData) {
        this.archData = archData;
    }

    public String getArchOsobaZdajaca() {
        return archOsobaZdajaca;
    }

    public void setArchOsobaZdajaca(String archOsobaZdajaca) {
        this.archOsobaZdajaca = archOsobaZdajaca;
    }

    public String getArchOsobaOdpowiadajaca() {
        return archOsobaOdpowiadajaca;
    }

    public void setArchOsobaOdpowiadajaca(String archOsobaOdpowiadajaca) {
        this.archOsobaOdpowiadajaca = archOsobaOdpowiadajaca;
    }

    public String getArchPokoj() {
        return archPokoj;
    }

    public void setArchPokoj(String archPokoj) {
        this.archPokoj = archPokoj;
    }

    public String getArchRegal() {
        return archRegal;
    }

    public void setArchRegal(String archRegal) {
        this.archRegal = archRegal;
    }

    public String getArchPolka() {
        return archPolka;
    }

    public void setArchPolka(String archPolka) {
        this.archPolka = archPolka;
    }

    public String getArchKarton() {
        return archKarton;
    }

    public void setArchKarton(String archKarton) {
        this.archKarton = archKarton;
    }

    public String getArchTeczka() {
        return archTeczka;
    }

    public void setArchTeczka(String archTeczka) {
        this.archTeczka = archTeczka;
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
