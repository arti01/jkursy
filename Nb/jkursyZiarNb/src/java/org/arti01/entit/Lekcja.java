package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the lekcja database table.
 *
 */
@Entity
@XmlRootElement
public class Lekcja implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Transient
    //@EJB LekacjaImp li;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlekcja;
    private Timestamp datazmiany;
    @NotEmpty
    private String tresc;
    @NotEmpty
    private String tytul;
    @NotNull
    private Integer lp;
    @NotNull
    private boolean widoczna;
    @Transient
    private List<Integer> fotyLpAll;
    @Transient
    private List<Lekcjafotykursant> fotyKursNieSkoment;
    @Transient
    private List<Lekcjakoment> dowykladowcy;
    //bi-directional many-to-one association to Kursy
    @ManyToOne
    @JoinColumn(name = "idkursy")
    private Kursy kursy;
    //bi-directional many-to-one association to Lekcjafoty
    @OneToMany(mappedBy = "lekcja", cascade = CascadeType.PERSIST)
    @OrderBy("lp")
    private List<Lekcjafoty> lekcjafoties;
    @OneToMany(mappedBy = "lekcja")
    @OrderBy("datadodania DESC")
    private List<Lekcjafotykursant> lekcjafotykursant;
    //bi-directional many-to-one association to Lekcjapliki
    @OneToMany(mappedBy = "lekcja", cascade = CascadeType.ALL)
    @OrderBy("opis")
    private List<Lekcjapliki> lekcjaplikis;
    @OneToMany(mappedBy = "lekcja", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("datadodania DESC")
    private List<Lekcjakoment> lekcjakoments;
    @Transient
    private Lekcjakoment lastKoment;
    @Transient
    private Lekcjafotykursant lastFota;
    @Transient
    private Fotykursantkoment lastKomentFota;
    @Transient
    private Integer iloscKolmentFota = 0;
    @Transient
    private List<Fotykursantkoment> komentDoFotBezOdp;

    public Integer getIdlekcja() {
        return this.idlekcja;
    }

    public void setIdlekcja(Integer idlekcja) {
        this.idlekcja = idlekcja;
    }

    public Timestamp getDatazmiany() {
        return this.datazmiany;
    }

    public void setDatazmiany(Timestamp datazmiany) {
        this.datazmiany = datazmiany;
    }

    public String getTresc() {
        return this.tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getTytul() {
        return this.tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @XmlTransient
    public Kursy getKursy() {
        return this.kursy;
    }

    public void setKursy(Kursy kursy) {
        this.kursy = kursy;
    }

    public List<Lekcjafoty> getLekcjafoties() {
        return this.lekcjafoties;
    }

    public void setLekcjafoties(List<Lekcjafoty> lekcjafoties) {
        this.lekcjafoties = lekcjafoties;
    }

    public List<Lekcjapliki> getLekcjaplikis() {
        return this.lekcjaplikis;
    }

    public void setLekcjaplikis(List<Lekcjapliki> lekcjaplikis) {
        this.lekcjaplikis = lekcjaplikis;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public List<Integer> getFotyLpAll() {
        return fotyLpAll;
    }

    public void setFotyLpAll(List<Integer> fotyLpAll) {
        this.fotyLpAll = fotyLpAll;
    }

    public List<Lekcjakoment> getLekcjakoments() {
        return lekcjakoments;
    }

    public void setLekcjakoments(List<Lekcjakoment> lekcjakoments) {
        this.lekcjakoments = lekcjakoments;
    }

    public boolean isWidoczna() {
        return widoczna;
    }

    public void setWidoczna(boolean widoczna) {
        this.widoczna = widoczna;
    }

    public Lekcjakoment getLastKoment() {
        if (getLekcjakoments().size() != 0) {
            lastKoment = getLekcjakoments().iterator().next();
        } else {
            lastKoment = null;
        }
        return lastKoment;
    }

    public void setLastKoment(Lekcjakoment lastKoment) {
        this.lastKoment = lastKoment;
    }

    public Lekcjafotykursant getLastFota() {
        if (getLekcjafotykursant().size() != 0) {
            lastFota = getLekcjafotykursant().iterator().next();
        } else {
            lastFota = null;
        }
        return lastFota;
    }

    public void setLastFota(Lekcjafotykursant lastFota) {
        this.lastFota = lastFota;
    }

    public Fotykursantkoment getLastKomentFota() {
        lastKomentFota = null;
        if (getLekcjafotykursant().size() != 0) {
            for (Lekcjafotykursant lfk : getLekcjafotykursant()) {
                if (lfk.getLastKomentFota() != null) {
                    if (lastKomentFota == null) {
                        lastKomentFota = lfk.getLastKomentFota();
                    }
                    if (lastKomentFota.getDatadodania().before(lfk.getLastKomentFota().getDatadodania())) {
                        lastKomentFota = lfk.getLastKomentFota();
                    }
                }
            }
        }
        return lastKomentFota;
    }

    public void setLastKomentFota(Fotykursantkoment lastKomentFota) {
        this.lastKomentFota = lastKomentFota;
    }

    public Integer getIloscKolmentFota() {
        for (Lekcjafotykursant lfk : getLekcjafotykursant()) {
            if (lfk.getFotykursantkoment() != null) {
                iloscKolmentFota += lfk.getFotykursantkoment().size();
            }
        }
        return iloscKolmentFota;
    }

    public void setIloscKolmentFota(Integer iloscKolmentFota) {
        this.iloscKolmentFota = iloscKolmentFota;
    }

    public List<Lekcjafotykursant> getFotyKursNieSkoment() {
        fotyKursNieSkoment = new ArrayList<Lekcjafotykursant>();
        for (Lekcjafotykursant lfk : getLekcjafotykursant()) {
            if (!lfk.isKomentWykl()) {
                fotyKursNieSkoment.add(lfk);
            }
        }
        return fotyKursNieSkoment;
    }

    public void setFotyKursNieSkoment(List<Lekcjafotykursant> fotyKursNieSkoment) {
        this.fotyKursNieSkoment = fotyKursNieSkoment;
    }

    public List<Lekcjakoment> getDowykladowcy() {
        dowykladowcy = new ArrayList<Lekcjakoment>();
        for (Lekcjakoment lk : getLekcjakoments()) {
            if (lk.isDowykladowcy()) {
                dowykladowcy.add(lk);
            }
        }
        return dowykladowcy;
    }

    public void setDowykladowcy(List<Lekcjakoment> dowykladowcy) {
        this.dowykladowcy = dowykladowcy;
    }

    public List<Lekcjafotykursant> getLekcjafotykursant() {
        return lekcjafotykursant;
    }

    public void setLekcjafotykursant(List<Lekcjafotykursant> lekcjafotykursant) {
        this.lekcjafotykursant = lekcjafotykursant;
    }

    public List<Fotykursantkoment> getKomentDoFotBezOdp() {
        komentDoFotBezOdp = new ArrayList<Fotykursantkoment>();
        for (Lekcjafotykursant lfk : lekcjafotykursant) {
            komentDoFotBezOdp.addAll(lfk.getDowykladowcy());
        }
        return komentDoFotBezOdp;
    }

    public void setKomentDoFotBezOdp(List<Fotykursantkoment> komentDoFotBezOdp) {
        this.komentDoFotBezOdp = komentDoFotBezOdp;
    }
}