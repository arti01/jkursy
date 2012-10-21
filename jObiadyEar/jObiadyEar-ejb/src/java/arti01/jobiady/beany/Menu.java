/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "menu")
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQMYCLASSID")
    @SequenceGenerator(name="SEQMYCLASSID", sequenceName="SEQMYCLASSID")
    @Column(name = "idmenu")
    private Integer idmenu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;
    @Size(max = 255)
    @Column(name = "nazwa")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Zamowieniemenu> zamowieniemenuList;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "MenuDniTyg",
    joinColumns = {
        @JoinColumn(name = "idmenu", nullable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "iddnityg", nullable = false)})
    @OrderBy("iddnityg")
    private List<DniTyg> dniTyg;

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Zamowieniemenu> getZamowieniemenuList() {
        return zamowieniemenuList;
    }

    public void setZamowieniemenuList(List<Zamowieniemenu> zamowieniemenuList) {
        this.zamowieniemenuList = zamowieniemenuList;
    }

    public List<DniTyg> getDniTyg() {
        return dniTyg;
    }

    public void setDniTyg(List<DniTyg> dniTyg) {
        this.dniTyg = dniTyg;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Menu[ idmenu=" + idmenu + " ]";
    }
    
}
