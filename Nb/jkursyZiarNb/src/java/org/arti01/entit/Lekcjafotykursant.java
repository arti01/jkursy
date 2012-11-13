package org.arti01.entit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the lekcjafotykursant database table.
 *
 */
@Entity
@Table(name = "lekcjafotykursant")
public class Lekcjafotykursant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idlekcjafotykursant;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date datadodania;
    @Transient
    private String datadodaniaS;
    @Transient
    private List<Fotykursantkoment> dowykladowcy;
    @Column(length = 2147483647)
    private String exif;
    @Column(length = 2147483647)
    private String opis;
    private byte[] plik;
    private byte[] plikmini;
    @ManyToOne
    @JoinColumn(name = "idlekcja")
    private Lekcja lekcja;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @OneToMany(mappedBy = "lekcjafotykursant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy(value = "datadodania DESC")
    private List<Fotykursantkoment> fotykursantkoment;
    
    @OneToOne(mappedBy="lekcjafotykursant")
    private Galeriaszkoly galeriaszkoly;

    private boolean dousuniecia;

    public boolean isDousuniecia() {
        return dousuniecia;
    }

    public void setDousuniecia(boolean dousuniecia) {
        this.dousuniecia = dousuniecia;
    }
    @Transient
    private Fotykursantkoment lastKomentFota;
    @Transient
    private boolean komentWykl = false;

    public Lekcjafotykursant() {
    }

    public Integer getIdlekcjafotykursant() {
        return this.idlekcjafotykursant;
    }

    public void setIdlekcjafotykursant(Integer idlekcjafotykursant) {
        this.idlekcjafotykursant = idlekcjafotykursant;
    }

    public String getExif() {
        return this.exif;
    }

    public void setExif(String exif) {
        this.exif = exif;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public byte[] getPlik() {
        return this.plik;
    }

    public void setPlik(byte[] plik) {
        this.plik = plik;
    }

    public byte[] getPlikmini() {
        return this.plikmini;
    }

    public void setPlikmini(byte[] plikmini) {
        this.plikmini = plikmini;
    }

    @XmlTransient
    public Lekcja getLekcja() {
        return lekcja;
    }

    public void setLekcja(Lekcja lekcja) {
        this.lekcja = lekcja;
    }

    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatadodaniaS() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //System.out.println(getDatadodania());
        //System.out.println(getIdlekcjafotykursant());
        datadodaniaS = sdf.format(getDatadodania());
        return datadodaniaS;
    }

    public void setDatadodaniaS(String datadodaniaS) {
        this.datadodaniaS = datadodaniaS;
    }

    public Date getDatadodania() {
        return datadodania;
    }

    public void setDatadodania(Date datadodania) {
        this.datadodania = datadodania;
    }

    @XmlTransient
    public List<Fotykursantkoment> getFotykursantkoment() {
        return fotykursantkoment;
    }

    public void setFotykursantkoment(List<Fotykursantkoment> fotykursantkoment) {
        this.fotykursantkoment = fotykursantkoment;
    }

    @XmlTransient
    public Fotykursantkoment getLastKomentFota() {
        if (getFotykursantkoment().size() != 0) {
            lastKomentFota = getFotykursantkoment().iterator().next();
        } else {
            lastKomentFota = null;
        }
        return lastKomentFota;
    }

    public void setLastKomentFota(Fotykursantkoment lastKomentFota) {
        this.lastKomentFota = lastKomentFota;
    }

    public boolean isKomentWykl() {
        komentWykl = false;
        List<User> wykladowcy = getLekcja().getKursy().getWykladowcy();
        for (Fotykursantkoment fkk : getFotykursantkoment()) {
            if (wykladowcy.contains(fkk.getUser())) {
                komentWykl = true;
            }
        }
        return komentWykl;
    }

    public void setKomentWykl(boolean komentWykl) {
        this.komentWykl = komentWykl;
    }

    @XmlTransient
    public List<Fotykursantkoment> getDowykladowcy() {
        dowykladowcy = new ArrayList<Fotykursantkoment>();
        for (Fotykursantkoment fkk : getFotykursantkoment()) {
            if (fkk.isDowykladowcy()) {
                dowykladowcy.add(fkk);
            }
        }
        return dowykladowcy;
    }

    public void setDowykladowcy(List<Fotykursantkoment> dowykladowcy) {
        this.dowykladowcy = dowykladowcy;
    }

    public Galeriaszkoly getGaleriaszkoly() {
        return galeriaszkoly;
    }

    public void setGaleriaszkoly(Galeriaszkoly galeriaszkoly) {
        this.galeriaszkoly = galeriaszkoly;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.idlekcjafotykursant != null ? this.idlekcjafotykursant.hashCode() : 0);
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
        final Lekcjafotykursant other = (Lekcjafotykursant) obj;
        if (this.idlekcjafotykursant != other.idlekcjafotykursant && (this.idlekcjafotykursant == null || !this.idlekcjafotykursant.equals(other.idlekcjafotykursant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lekcjafotykursant{" + "idlekcjafotykursant=" + idlekcjafotykursant + '}';
    }
    
}