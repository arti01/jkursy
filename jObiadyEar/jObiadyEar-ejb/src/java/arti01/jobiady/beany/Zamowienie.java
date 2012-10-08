/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "ZAMOWIENIE")
public class Zamowienie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQZAM")
    @SequenceGenerator(name="SEQZAM", sequenceName="SEQZAM")
    private Integer idzamowienie;
    
    @Column(name = "DATAZAMOWIENIA")
    private Timestamp dataZamowienia;
    
    @ManyToOne
    @JoinColumn(name = "username",  nullable=false)
    private Uzytkownik uzytkownik;
    
    private String statusZamowienia;
    
    @ManyToMany
    @JoinTable(name = "ZamowienieMenu",
    joinColumns = {
        @JoinColumn(name = "idzamowienie", nullable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "idmenu", nullable = false)})
    private List<Menu>potrawy=new ArrayList<Menu>();
   //  private List<Menu>potrawy;
    
    
    @ManyToOne()
    @JoinColumn(name="kurs_id")
    private Kurs kurs;
    
    private double wplacono;
    
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

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public List<Menu> getPotrawy() {
        return potrawy;
    }

    public void setPotrawy(List<Menu> potrawy) {
        this.potrawy = potrawy;
    }

    public double getSuma() {
        suma=0;
        System.out.println(getPotrawy());
        for(Menu m:getPotrawy()){
            suma+=m.getCena();
        }
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public double getWplacono() {
        return wplacono;
    }

    public void setWplacono(double wplacono) {
        this.wplacono = wplacono;
    }

    public double getRoznica() {
        roznica=wplacono-suma;
        return roznica;
    }

    public void setRoznica(double roznica) {
        this.roznica = roznica;
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
        Zamowienie toZam = this;
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