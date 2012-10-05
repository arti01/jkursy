/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author 103039
 */
@Entity
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQMYCLASSID")
    @SequenceGenerator(name="SEQMYCLASSID", sequenceName="SEQMYCLASSID")
    private int idmenu;
    
    @NotNull
    @NotEmpty
    private String nazwa;
    
    @NotNull
    @Min(1)
    private double cena;
    
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "MenuDniTyg",
    joinColumns = {
        @JoinColumn(name = "idmenu", nullable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "iddnityg", nullable = false)})
    @OrderBy("iddnityg")
    private List<DniTyg> dniTyg;

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<DniTyg> getDniTyg() {
        return dniTyg;
    }

    public void setDniTyg(List<DniTyg> dniTyg) {
        this.dniTyg = dniTyg;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == 0 && other.idmenu != 0) || (this.idmenu != 0 && !(this.idmenu==other.idmenu))) {
            return false;
        }
        return true;
    }
}
