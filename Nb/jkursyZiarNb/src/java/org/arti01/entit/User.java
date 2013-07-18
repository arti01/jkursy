package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the _users database table.
 *
 */
@Entity
@Table(name = "_users")
@NamedQueries({
    @NamedQuery(name = "sortByNameAsc", query = "select u from User u order by u.imieNazwisko asc"),
    @NamedQuery(name = "sortByNameDesc", query = "select u from User u order by u.imieNazwisko desc")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(unique = true, nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_zmiany", nullable = false)
    private Date dataZmiany;
    @Column(nullable = false, length = 100)
    @NotEmpty
    @Email
    private String email;
    @Column(name = "imie_nazwisko", nullable = false, length = 2147483647)
    @Size(min = 3, max = 255)
    private String imieNazwisko;
    @Column(nullable = true, length = 2147483647)
    private String miasto;
    @Column(length = 10)
    @Size(min = 0, max = 6)
    private String kodpocztowy;
    @Column(nullable = true, length = 2147483647)
    //@Pattern(regexp="[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")
    private String nip;
    @Column(length = 2147483647)
    private String opis;
    @Column(nullable = true, length = 2147483647)
    private String tel1;
    @Column(nullable = true, length = 2147483647)
    private String ulica;
    @Column(nullable = true, length = 50)
    @Size(min = 2, max = 15)
    private String userpass;
    @Column(length = 255)
    private String wiadomosc;
    @Column(nullable = true)
    @Size(max = 255)
    private String polecajacy;
    private byte[] fota;
    @Transient
    private Lekcja konkretnaLekcja;
    @Transient
    private List<Lekcjafotykursant> fotyBezkomentarza;
    @Transient
    private List<Lekcjafotykursant> fotyDodaneDoLekcji;
    @Transient
    private Integer fotDoDodania;
    //bi-directional many-to-many association to Role
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "_users__roles", joinColumns = {
        @JoinColumn(name = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "roles_rola", nullable = false)
    })
    private List<Role> roles;
    //bi-directional many-to-many association to Kursy
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "kursy_users", joinColumns = {
        @JoinColumn(name = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "idkursy", nullable = false)
    })
    private List<Kursy> kursies;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "kursy_rezerwacje", joinColumns = {
        @JoinColumn(name = "username", nullable = false),}, inverseJoinColumns = {
        @JoinColumn(name = "idkursy", nullable = false)
    })
    private List<Kursy> kursyZarezerwowane;
    @OneToMany(mappedBy = "user")
    @OrderBy("datadodania")
    private List<Lekcjakoment> lekcjakoments;
    @OneToMany(mappedBy = "user")
    @OrderBy("datadodania")
    private List<Lekcjafotykursant> lekcjafotykursant;
    @OneToMany(mappedBy = "user")
    @OrderBy("datadodania")
    private List<Absolwforwatki> absolwforwatki;
    @OneToMany(mappedBy = "user")
    @OrderBy("datadodania")
    private List<Absolwforposty> absolwforposty;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OrderBy("lp")
    private List<Userfoty> userfoty;
    @OneToMany(mappedBy = "user")
    @OrderBy("idkursyrezerwacje DESC")
    private List<KursyRezerwacje> rezerwacje;
    @Transient
    private List<KursyRezerwacje> rezerwacjeZaakceptowane;
    
    @Transient
    private String nazwiskoImie;

    public List<KursyRezerwacje> getRezerwacjeZaakceptowane() {
        rezerwacjeZaakceptowane = new ArrayList<KursyRezerwacje>();
        for (KursyRezerwacje kr : getRezerwacje()) {
            if (kr.getWykonana()) {
                rezerwacjeZaakceptowane.add(kr);
            }
        }
        return rezerwacjeZaakceptowane;
    }
    @Transient
    private List<Kursy> kursyZaakceptowane;

    public List<Kursy> getKursyZaakceptowane() {
        kursyZaakceptowane = new ArrayList<Kursy>();
        for (KursyRezerwacje kr : getRezerwacjeZaakceptowane()) {
            kursyZaakceptowane.add(kr.getKursy());
        }
        return kursyZaakceptowane;
    }
    @Transient
    private List<Userfoty> userfotyakcept;
    @Transient
    private List<Userfoty> userfotyakceptBez;
    @Transient
    private Userfoty pierwsza;
    @Transient
    private List<KursyRezerwacje> rezerwacjeOdwolane;

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImieNazwisko() {
        return this.imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public String getMiasto() {
        return this.miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTel1() {
        return this.tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getUlica() {
        return this.ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getUserpass() {
        return this.userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getWiadomosc() {
        return this.wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    @XmlTransient
    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @XmlTransient
    public List<Kursy> getKursies() {
        return this.kursies;
    }

    public void setKursies(List<Kursy> kursies) {
        this.kursies = kursies;
    }

    public Date getDataZmiany() {
        return dataZmiany;
    }

    public void setDataZmiany(Date dataZmiany) {
        this.dataZmiany = dataZmiany;
    }

    public void setLekcjakoments(List<Lekcjakoment> lekcjakoments) {
        this.lekcjakoments = lekcjakoments;
    }

    public List<Lekcjakoment> getLekcjakoments() {
        return lekcjakoments;
    }

    public List<Lekcjafotykursant> getLekcjafotykursant() {
        return lekcjafotykursant;
    }

    public void setLekcjafotykursant(List<Lekcjafotykursant> lekcjafotykursant) {
        this.lekcjafotykursant = lekcjafotykursant;
    }

    public List<Absolwforwatki> getAbsolwforwatki() {
        return absolwforwatki;
    }

    public void setAbsolwforwatki(List<Absolwforwatki> absolwforwatki) {
        this.absolwforwatki = absolwforwatki;
    }

    public List<Absolwforposty> getAbsolwforposty() {
        return absolwforposty;
    }

    public void sewtAbsolwforposty(List<Absolwforposty> absolwforposty) {
        this.absolwforposty = absolwforposty;
    }

    public List<Lekcjafotykursant> getFotyBezkomentarza() {
        fotyBezkomentarza = new ArrayList<Lekcjafotykursant>();
        try {
            for (Lekcjafotykursant l : getKonkretnaLekcja().getFotyKursNieSkoment()) {
                if (l.getUser().getUsername().equals(username)) {
                    fotyBezkomentarza.add(l);
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        return fotyBezkomentarza;
    }

    public void setFotyBezkomentarza(List<Lekcjafotykursant> fotyBezkomentarza) {
        this.fotyBezkomentarza = fotyBezkomentarza;
    }

    public Lekcja getKonkretnaLekcja() {
        return konkretnaLekcja;
    }

    public void setKonkretnaLekcja(Lekcja konkretnaLekcja) {
        this.konkretnaLekcja = konkretnaLekcja;
    }

    public List<Lekcjafotykursant> getFotyDodaneDoLekcji() {
        fotyDodaneDoLekcji = new ArrayList<Lekcjafotykursant>();
        try {
            for (Lekcjafotykursant l : getKonkretnaLekcja().getLekcjafotykursant()) {
                if (l.getUser().getUsername().equals(username)) {
                    fotyDodaneDoLekcji.add(l);
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return fotyDodaneDoLekcji;
    }

    public void setFotyDodaneDoLekcji(List<Lekcjafotykursant> fotyDodaneDoLekcji) {
        this.fotyDodaneDoLekcji = fotyDodaneDoLekcji;
    }

    public Integer getFotDoDodania() {
        try {
            Integer wolneOgole = getKonkretnaLekcja().getKursy().getFotperkursantmax() - getFotyDodaneDoLekcji().size();
            Integer woleBezKoment = getKonkretnaLekcja().getKursy().getFotperkursantbezkoment() - getFotyBezkomentarza().size();
            if (wolneOgole < woleBezKoment) {
                fotDoDodania = wolneOgole;
            } else {
                fotDoDodania = woleBezKoment;
            }
            if (getKonkretnaLekcja().getLekcjaRodzaje().getIdlekcjarodzaje() != 0) {
                fotDoDodania = wolneOgole;
            }
            return fotDoDodania;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return 0;
        }
    }

    public void setFotDoDodania(Integer fotDoDodania) {
        this.fotDoDodania = fotDoDodania;
    }

    public List<Userfoty> getUserfoty() {
        return userfoty;
    }

    public void setUserfoty(List<Userfoty> userfoty) {
        this.userfoty = userfoty;
    }

    public List<Userfoty> getUserfotyakcept() {
        userfotyakcept = new ArrayList<Userfoty>();
        for (Userfoty f : getUserfoty()) {
            if (f.isAkcept()) {
                userfotyakcept.add(f);
            }
        }
        return userfotyakcept;
    }

    public List<Userfoty> getUserfotyakceptBez() {
        userfotyakceptBez = new ArrayList<Userfoty>();
        for (Userfoty f : getUserfoty()) {
            if (!f.isAkcept()) {
                userfotyakceptBez.add(f);
            }
        }
        return userfotyakceptBez;
    }

    public Userfoty getPierwsza() {
        for (Userfoty f : getUserfotyakcept()) {
            if (f.getLp() == 1) {
                pierwsza = f;
            }
        }
        return pierwsza;
    }

    public void setPierwsza(Userfoty pierwsza) {
        this.pierwsza = pierwsza;
    }

    public String getKodpocztowy() {
        return kodpocztowy;
    }

    public void setKodpocztowy(String kodpocztowy) {
        this.kodpocztowy = kodpocztowy;
    }

    @XmlTransient
    public List<Kursy> getKursyZarezerwowane() {
        return kursyZarezerwowane;
    }

    public void setKursyZarezerwowane(List<Kursy> kursyZarezerwowane) {
        this.kursyZarezerwowane = kursyZarezerwowane;
    }

    public List<KursyRezerwacje> getRezerwacje() {
        return rezerwacje;
    }

    public void setRezerwacje(List<KursyRezerwacje> rezerwacje) {
        this.rezerwacje = rezerwacje;
    }

    public List<KursyRezerwacje> getRezerwacjeOdwolane() {
        rezerwacjeOdwolane = new ArrayList<KursyRezerwacje>();
        for (KursyRezerwacje kr : getRezerwacje()) {
            if (!kr.getAktywna()) {
                rezerwacjeOdwolane.add(kr);
            }
        }
        return rezerwacjeOdwolane;
    }

    public byte[] getFota() {
        return fota;
    }

    public void setFota(byte[] fota) {
        this.fota = fota;
    }

    public String getPolecajacy() {
        return polecajacy;
    }

    public void setPolecajacy(String polecajacy) {
        this.polecajacy = polecajacy;
    }

    public String getNazwiskoImie() {
        nazwiskoImie="";
        String[] u=getImieNazwisko().split(" ");
        for(int i=u.length-1; i>=0; i=i-1){
          nazwiskoImie=nazwiskoImie+u[i]+" ";  
        }
        return nazwiskoImie;
    }
    
}