/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 103039
 */
@Entity
@Table(name = "TRANSAKCJEZAMOWIENIA")
@XmlRootElement

   public class Transakcjezamowienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQTRANSZAM")
    @SequenceGenerator(name="SEQTRANSZAM", sequenceName="SEQTRANSZAM")
    @Column(nullable = false)
    private int idTransakcjezamowienia;

    @NotNull
    @Column(name = "KWOTA", nullable = false)
    private double kwota;
    @Size(max = 255)
    @Column(name = "TYTULEM", length = 255)
    private String tytulem;
    
    @ManyToOne
    @JoinColumn(name = "idzamowienie",  nullable=false)
    private Zamowienie zamowienie;

    public Transakcjezamowienia() {
    }
    
    public int getIdTransakcjezamowienia() {
        return idTransakcjezamowienia;
    }

    public void setIdTransakcjezamowienia(int idTransakcjezamowienia) {
        this.idTransakcjezamowienia = idTransakcjezamowienia;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public String getTytulem() {
        return tytulem;
    }

    public void setTytulem(String tytulem) {
        this.tytulem = tytulem;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idTransakcjezamowienia;
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
        final Transakcjezamowienia other = (Transakcjezamowienia) obj;
        if (this.idTransakcjezamowienia != other.idTransakcjezamowienia) {
            return false;
        }
        return true;
    }
    
    
}
