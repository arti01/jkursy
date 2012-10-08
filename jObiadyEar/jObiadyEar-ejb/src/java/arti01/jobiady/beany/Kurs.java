/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 *
 * @author arti01
 */
@Entity
public class Kurs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQKURS")
    @SequenceGenerator(name="SEQKURS", sequenceName="SEQKURS")
    private Long id;
    
    @OneToMany( cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @OrderBy("idzamowienie DESC")
    @JoinColumn(name="kurs_id")
    private List<Zamowienie> zamowienia;
    
    private Timestamp dataKursu;
    
    @ManyToOne
    private Uzytkownik tragarz;
    
    @Transient
    private double suma;
    
    @Transient
    private double sumaWplat;
    
    @Transient
    private List<Entry<Menu, Integer>> zestawienie;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public Timestamp getDataKursu() {
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
        suma=0;
        for(Zamowienie z:zamowienia){
            suma+=z.getSuma();
        }
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
    

    public double getSumaWplat() {
        sumaWplat=0;
        for(Zamowienie z:zamowienia){
            sumaWplat+=z.getWplacono();
        }
        return sumaWplat;
    }

    public void setSumaWplat(double sumaWplat) {
        this.sumaWplat = sumaWplat;
    }

    public List getZestawienie() {
        Map<Menu, Integer> zestMap=new HashMap<Menu, Integer>();
            for(Zamowienie zam:zamowienia){
                for(Menu m:zam.getPotrawy()){
                    Integer ilosc=0;
                    System.out.println(zestMap.get(m)+"get");
                    if(zestMap.get(m)!=null){
                        System.out.println(m.getIdmenu()+m.getNazwa()+"if");
                        ilosc=zestMap.get(m)+1;
                    }
                    else ilosc=1;
                    //System.out.println(m.getNazwa()+ilosc);
                    zestMap.put(m, ilosc);
                }
            }
            zestawienie=new ArrayList<Entry<Menu, Integer>>( zestMap.entrySet());
        return zestawienie;
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
    
}
