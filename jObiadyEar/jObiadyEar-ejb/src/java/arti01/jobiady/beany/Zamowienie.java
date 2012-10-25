/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "zamowienie")
@NamedQueries({
    @NamedQuery(name = "Zamowienie.findAll", query = "SELECT z FROM Zamowienie z")})
public class Zamowienie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQZAM")
    @SequenceGenerator(name = "SEQZAM", sequenceName = "SEQZAM")
    private Integer idzamowienie;
    @Column(name = "datazamowienia")
    private Timestamp dataZamowienia;
    @Size(max = 255)
    @Column(name = "statuszamowienia")
    private String statusZamowienia;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "idzamowienie")
    private List<Zamowieniemenu> zamowieniemenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowienie", fetch = FetchType.LAZY)
    private List<Transakcjezamowienia> transakcjezamowienia;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Uzytkownik uzytkownik;
    @JoinColumn(name = "kurs_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Kurs kurs;
    @Transient
    private double saldo;
    @Transient
    private double suma;
    @Transient
    private double roznica;

    public Zamowienie() {
    }

    public Zamowienie(Integer idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    public Integer getIdzamowienie() {
        return idzamowienie;
    }

    public void setIdzamowienie(Integer idzamowienie) {
        this.idzamowienie = idzamowienie;
    }

    public Timestamp getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(Timestamp dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public List<Zamowieniemenu> getZamowieniemenu() {
        return zamowieniemenu;
    }

    public void setZamowieniemenu(List<Zamowieniemenu> zamowieniemenu) {
        this.zamowieniemenu = zamowieniemenu;
    }

    public double getSuma() {
        suma = 0;
        for (Zamowieniemenu zm : getZamowieniemenu()) {
            suma += zm.getMenu().getCena();
        }
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void setTransakcjezamowienia(List<Transakcjezamowienia> transakcjezamowienia) {
        this.transakcjezamowienia = transakcjezamowienia;
    }

    public List<Transakcjezamowienia> getTransakcjezamowienia() {
        return transakcjezamowienia;
    }

    public double getSaldo() {
        saldo = 0;
        for (Transakcjezamowienia trZam : getTransakcjezamowienia()) {
            saldo += trZam.getKwota();
        }
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getRoznica() {
        roznica = saldo - suma;
        return roznica;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idzamowienie != null ? idzamowienie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienie)) {
            return false;
        }
        Zamowienie other = (Zamowienie) object;
        if ((this.idzamowienie == null && other.idzamowienie != null) || (this.idzamowienie != null && !this.idzamowienie.equals(other.idzamowienie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Zamowienie[ idzamowienie=" + idzamowienie + " ]";
    }
}
