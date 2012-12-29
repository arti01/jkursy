package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "kursy")
@NamedQuery(name = "findNiezakonczone", query = "select k from Kursy k where k.datado>=:datado order by k.dataod desc")
@XmlRootElement
public class Kursy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer idkursy;
    @Temporal( TemporalType.DATE)
    @Column(nullable = false)
    private Date datado;
    @Temporal( TemporalType.DATE)
    @Column(nullable = false)
    private Date dataod;
    @Column(nullable = false, length = 255)
    @Size(min = 1, max = 255)
    private String nazwa;
    @Column(nullable = false, length = 2147483647)
    @Size(min = 1)
    private String opis;
    @Column(nullable = false)
    @NotNull
    private Integer lp;
    private double cena;
    @Column(name = "opis_krotki", length = 2147483647)
    @Size(min = 1)
    @NotNull(message = "problem")
    private String opisKrotki;
    @NotNull
    @DecimalMin(message = "grupa nie może być mniejsza od 1", value = "1")
    private Integer wielkoscgrupy;
    @NotNull
    @DecimalMin(message = "zdjęć nie może być mniej niż 1", value = "1")
    private Integer fotperkursantmax;
    @NotNull
    @DecimalMin(message = "zdjęć nie może być mniej niż od 1", value = "1")
    private Integer fotperkursantbezkoment;
    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "kursies", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> users;
    @ManyToMany(mappedBy = "kursyZarezerwowane", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> rezerwujacy;
    //bi-directional many-to-one association to Lekcja
    @OneToMany(mappedBy = "kursy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderBy(value = "lp")
    private List<Lekcja> lekcjas;
    @OneToMany(mappedBy = "kursy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderBy(value = "datadodania")
    private List<Newsykursy> newsykursy;
    @ManyToOne
    @JoinColumn(name = "idpoziomyzaawansowania")
    private Poziomyzaawansowania poziomyzaawansowania;
    @ManyToOne
    @JoinColumn(name = "idtypykursu")
    private Typykursu typykursu;
    @OneToMany(mappedBy = "kursy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderBy(value = "idkursyrezerwacje")
    private List<KursyRezerwacje> rezerwacje;
    @NotNull
    private boolean stacjonarny;
    @NotNull
    private boolean rezerwacjaDostepna;
    @NotNull
    private boolean widoczny;
    @Transient
    private List<User> wykladowcy;
    @Transient
    private List<User> kursanci;
    @Transient
    private List<Integer> lekcjeLpAll;
    @Transient
    private String stacjonarnyTN;
    @Transient
    private ArrayList<Lekcja> lekcjeWidoczne;
    @Transient
    private Integer wolnychMiejsc;
    @Transient
    private List<KursyRezerwacje> rezerwacjeNowe;

    public Kursy() {
    }

    public Integer getIdkursy() {
        return this.idkursy;
    }

    public void setIdkursy(Integer idkursy) {
        this.idkursy = idkursy;
    }

    public Date getDatado() {
        return this.datado;
    }

    public void setDatado(Date datado) {
        this.datado = datado;
    }

    public Date getDataod() {
        return this.dataod;
    }

    public void setDataod(Date dataod) {
        this.dataod = dataod;
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getOpisKrotki() {
        return this.opisKrotki;
    }

    public void setOpisKrotki(String opisKrotki) {
        this.opisKrotki = opisKrotki;
    }

    @XmlTransient
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean isWidoczny() {
        return widoczny;
    }

    public void setWidoczny(boolean widoczny) {
        this.widoczny = widoczny;
    }
    
    @XmlTransient
    public List<User> getWykladowcy() {
        List<User> wykladowcy1 = new ArrayList<User>();
        Role wyklad = new Role();
        wyklad.setRola(Role.WYKLADOWCA);
        for (User u : getUsers()) {
            for (Role r : u.getRoles()) {
                if (r.getRola().equals(wyklad.getRola())) {
                    wykladowcy1.add(u);
                }
            }
        }
        wykladowcy = wykladowcy1;
        return wykladowcy;
    }

    public void setWykladowcy(List<User> wykladowcy) {
        this.wykladowcy = wykladowcy;
    }

    @XmlTransient
    public List<User> getKursanci() {
        List<User> kursanci1 = new ArrayList<User>();
        Role kursant = new Role();
        kursant.setRola(Role.KURSANT);
        for (User u : getUsers()) {
            for (Role r : u.getRoles()) {
                if (r.getRola().equals(kursant.getRola())) {
                    kursanci1.add(u);
                }
            }
        }
        kursanci = kursanci1;
        return kursanci;
    }

    public void setKursanci(List<User> kursanci) {
        this.kursanci = kursanci;
    }

    @XmlTransient
    public List<Lekcja> getLekcjas() {
        return lekcjas;
    }

    public void setLekcjas(List<Lekcja> lekcjas) {
        this.lekcjas = lekcjas;
    }

    public List<Integer> getLekcjeLpAll() {
        return lekcjeLpAll;
    }

    public void setLekcjeLpAll(List<Integer> lekcjeLpAll) {
        this.lekcjeLpAll = lekcjeLpAll;
    }

    public List<Newsykursy> getNewsykursy() {
        return newsykursy;
    }

    public void setNewsykursy(List<Newsykursy> newsykursy) {
        this.newsykursy = newsykursy;
    }

    public boolean isStacjonarny() {
        return stacjonarny;
    }

    public void setStacjonarny(boolean stacjonarny) {
        this.stacjonarny = stacjonarny;
    }

    public String getStacjonarnyTN() {
        if (stacjonarny) {
            stacjonarnyTN = "Stacjonarny";
        } else {
            stacjonarnyTN = "Internetowy";
        }
        return stacjonarnyTN;
    }

    public void setStacjonarnyTN(String stacjonarnyTN) {
        this.stacjonarnyTN = stacjonarnyTN;
    }

    public Poziomyzaawansowania getPoziomyzaawansowania() {
        return poziomyzaawansowania;
    }

    public void setPoziomyzaawansowania(Poziomyzaawansowania poziomyzaawansowania) {
        this.poziomyzaawansowania = poziomyzaawansowania;
    }

    public void setLekcjeWidoczne(ArrayList<Lekcja> lekcjeWidoczne) {
        this.lekcjeWidoczne = lekcjeWidoczne;
    }

    @XmlTransient
    public ArrayList<Lekcja> getLekcjeWidoczne() {
        lekcjeWidoczne = new ArrayList<Lekcja>();
        for (Lekcja l : getLekcjas()) {
            if (l.isWidoczna()) {
                lekcjeWidoczne.add(l);
            }
        }
        return lekcjeWidoczne;
    }

    public Integer getWielkoscgrupy() {
        return wielkoscgrupy;
    }

    public void setWielkoscgrupy(Integer wielkoscgrupy) {
        this.wielkoscgrupy = wielkoscgrupy;
    }

    public Integer getWolnychMiejsc() {
        if (idkursy == null) {
            return 0;
        }
        wolnychMiejsc = getWielkoscgrupy() - getKursanci().size() - getRezerwacjeNowe().size();
        return wolnychMiejsc;
    }

    public void setWolnychMiejsc(Integer wolnychMiejsc) {
        this.wolnychMiejsc = wolnychMiejsc;
    }

    @XmlTransient
    public Typykursu getTypykursu() {
        return typykursu;
    }

    public void setTypykursu(Typykursu typykursu) {
        this.typykursu = typykursu;
    }

    public Integer getFotperkursantmax() {
        return fotperkursantmax;
    }

    public void setFotperkursantmax(Integer fotperkursantmax) {
        this.fotperkursantmax = fotperkursantmax;
    }

    public Integer getFotperkursantbezkoment() {
        return fotperkursantbezkoment;
    }

    public void setFotperkursantbezkoment(Integer fotperkursantbezkoment) {
        this.fotperkursantbezkoment = fotperkursantbezkoment;
    }

    public Set<User> getRezerwujacy() {
        return rezerwujacy;
    }

    public void setRezerwujacy(Set<User> rezerwujacy) {
        this.rezerwujacy = rezerwujacy;
    }

    public List<KursyRezerwacje> getRezerwacje() {
        return rezerwacje;
    }

    public void setRezerwacje(List<KursyRezerwacje> rezerwacje) {
        this.rezerwacje = rezerwacje;
    }

    public List<KursyRezerwacje> getRezerwacjeNowe() {
        rezerwacjeNowe = new ArrayList<KursyRezerwacje>();
        for (KursyRezerwacje kr : getRezerwacje()) {
            if (kr.getAktywna() && !kr.getWykonana()) {
                rezerwacjeNowe.add(kr);
            }
        }
        return rezerwacjeNowe;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public boolean isRezerwacjaDostepna() {
        return rezerwacjaDostepna;
    }

    public void setRezerwacjaDostepna(boolean rezerwacjaDostepna) {
        this.rezerwacjaDostepna = rezerwacjaDostepna;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}