/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "kurs")
@NamedQueries({
    @NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k")})
public class Kurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQKURS")
    @SequenceGenerator(name = "SEQKURS", sequenceName = "SEQKURS")
    private Long id;
    @Column(name = "datakursu")
    private Timestamp dataKursu;
    @JoinColumn(name = "tragarz_username", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uzytkownik tragarz;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "kurs_id")
    private List<Zamowienie> zamowienia;
    @Transient
    private double suma;
    @Transient
    private double sumaWplat;
    @Transient
    private List<Entry<Menu, Integer>> zestawienie;
    @Transient
    private List<Entry<Uzytkownik, Entry<Menu, Integer>>> zestawieniePerUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataKursu() {
        return dataKursu;
    }

    public void setDataKursu(Timestamp dataKursu) {
        this.dataKursu = dataKursu;
    }

    public Uzytkownik getTragarz() {
        return tragarz;
    }

    public void setTragarz(Uzytkownik tragarz) {
        this.tragarz = tragarz;
    }

    public double getSuma() {
        suma = 0;
        for (Zamowienie z : zamowienia) {
            suma += z.getSuma();
        }
        return suma;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public double getSumaWplat() {
        sumaWplat = 0;
        for (Zamowienie z : zamowienia) {
            sumaWplat += z.getSaldo();
        }
        return sumaWplat;
    }

    public void setSumaWplat(double sumaWplat) {
        this.sumaWplat = sumaWplat;
    }

    public List getZestawienie() {
        Map<Menu, Integer> zestMap = new HashMap<Menu, Integer>();
        for (Zamowienie zam : zamowienia) {
            for (Zamowieniemenu zm : zam.getZamowieniemenu()) {
                Menu m = zm.getMenu();
                Integer ilosc = 0;
                //Logger.getLogger(this.getClass().getName()).log(Level.INFO, this.getClass().getName() + m);
                if (zestMap.get(m) != null) {

                    ilosc = zestMap.get(m) + 1;
                } else {
                    ilosc = 1;
                }
                zestMap.put(m, ilosc);
            }
        }
        zestawienie = new ArrayList<Entry<Menu, Integer>>(zestMap.entrySet());
        return zestawienie;
    }

    public List getZestawieniePerUser() {
        Map<Uzytkownik, List<Zamowienie>> userZam = new HashMap<Uzytkownik, List<Zamowienie>>();
        for (Zamowienie zam : getZamowienia()) {
            Uzytkownik u = zam.getUzytkownik();
            List<Zamowienie> zamNew = new ArrayList<Zamowienie>();
            if (userZam.get(u) != null) {
                Logger.getLogger("arti").log(Level.SEVERE, "jest user" + userZam.get(u));
                zamNew = userZam.get(u);
            }
            zamNew.add(zam);
            userZam.put(u, zamNew);
            Logger.getLogger("arti").log(Level.SEVERE, "po akcji" + userZam.get(u));
        }
        List<Entry<Uzytkownik, List<Zamowienie>>> userZamArr = new ArrayList<Entry<Uzytkownik, List<Zamowienie>>>(userZam.entrySet());
        //teraz liczenie dla userów
        Map<Uzytkownik, List<Entry<Menu, Integer>>> wynUserMap = new HashMap<Uzytkownik, List<Entry<Menu, Integer>>>();
        for (Entry<Uzytkownik, List<Zamowienie>> mapZam : userZamArr) {
            Map<Menu, Integer> zestMap = new HashMap<Menu, Integer>();
            for (Zamowienie zam : zamowienia) {
                if (zam.getUzytkownik().equals(mapZam.getKey())) {
                    for (Zamowieniemenu zm : zam.getZamowieniemenu()) {
                        Menu m = zm.getMenu();
                        Integer ilosc = 0;
                        if (zestMap.get(m) != null) {
                            ilosc = zestMap.get(m) + 1;
                        } else {
                            ilosc = 1;
                        }
                        zestMap.put(m, ilosc);
                    }
                }
            }
            //wynUserList=new AbstractMap.SimpleEntry<Uzytkownik, Map<Menu, Integer>>(mapZam.getKey(), zestMap);
            wynUserMap.put(mapZam.getKey(), new ArrayList(zestMap.entrySet()));
            zestMap.clear();
        }
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, this.getClass().getName() + wynUserMap + "wynikList");
        List<Entry<Uzytkownik, List<Entry<Menu, Integer>>>> wynik;

        wynik = new ArrayList<Entry<Uzytkownik, List<Entry<Menu, Integer>>>>(wynUserMap.entrySet());
        return wynik;
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
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Kurs[ id=" + id + " ]";
    }
}
