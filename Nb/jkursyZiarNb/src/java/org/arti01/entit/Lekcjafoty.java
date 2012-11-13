package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The persistent class for the lekcjafoty database table.
 *
 */
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"lp", "idlekcja"}))
public class Lekcjafoty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlekcjafoty;
    private Timestamp datadodania;
    private String opis;
    private String exif;
    private byte[] plik;
    private byte[] plikmini;
    @NotNull
    private Integer lp;
    //bi-directional many-to-one association to Lekcja
    @ManyToOne
    @JoinColumn(name = "idlekcja")
    private Lekcja lekcja;

    public Lekcjafoty() {
    }

    public Integer getIdlekcjafoty() {
        return this.idlekcjafoty;
    }

    public void setIdlekcjafoty(Integer idlekcjafoty) {
        this.idlekcjafoty = idlekcjafoty;
    }

    public Timestamp getDatadodania() {
        return this.datadodania;
    }

    public void setDatadodania(Timestamp datadodania) {
        this.datadodania = datadodania;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public byte[] getData() {
        return this.plik;
    }

    public void setData(byte[] plik) {
        this.plik = plik;
    }

    public byte[] getPlikmini() {
        return this.plikmini;
    }

    public void setPlikmini(byte[] plikmini) {
        this.plikmini = plikmini;
    }

    public Lekcja getLekcja() {
        return this.lekcja;
    }

    public void setLekcja(Lekcja lekcja) {
        this.lekcja = lekcja;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public String getExif() {
        return exif;
    }

    public void setExif(String exif) {
        this.exif = exif;
    }

    public byte[] getPlik() {
        return plik;
    }

    public void setPlik(byte[] plik) {
        this.plik = plik;
    }

    @Override
    public String toString() {
        return "Lekcjafoty{" + "idlekcjafoty=" + idlekcjafoty + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.idlekcjafoty != null ? this.idlekcjafoty.hashCode() : 0);
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
        final Lekcjafoty other = (Lekcjafoty) obj;
        if (this.idlekcjafoty != other.idlekcjafoty && (this.idlekcjafoty == null || !this.idlekcjafoty.equals(other.idlekcjafoty))) {
            return false;
        }
        return true;
    }
    
}