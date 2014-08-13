/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.eod.encje.Struktura;
import pl.eod.encje.Uzytkownik;

@Entity
@Table(name = "ogloszenia")
public class Ogloszenia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQOGLOSZENIA")
    @SequenceGenerator(name = "SEQOGLOSZENIA", sequenceName = "SEQOGLOSZENIA")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String tytul;
    @Size(max = 10485760)
    @Lob
    private String tresc;
    @Column(name = "data_wprow")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWprow;
    @ManyToOne()
    private Uzytkownik wprowadzil;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @ManyToMany()
    private List<Struktura> adresaciList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Uzytkownik getWprowadzil() {
        return wprowadzil;
    }

    public void setWprowadzil(Uzytkownik wprowadzil) {
        this.wprowadzil = wprowadzil;
    }

    public List<Struktura> getAdresaciList() {
        return adresaciList;
    }

    public void setAdresaciList(List<Struktura> adresaciList) {
        this.adresaciList = adresaciList;
    }

    public Date getDataWprow() {
        return dataWprow;
    }

    public void setDataWprow(Date dataWprow) {
        this.dataWprow = dataWprow;
    }

}
