/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "struktura")
@NamedQueries({
    @NamedQuery(name = "Struktura.findAll", query = "SELECT s FROM Struktura s"),
    @NamedQuery(name = "Struktura.findById", query = "SELECT s FROM Struktura s WHERE s.id = :id"),
    @NamedQuery(name = "Struktura.findBySzefId", query = "SELECT s FROM Struktura s WHERE s.szefId = :szefId"),
    @NamedQuery(name = "Struktura.findByStKier", query = "SELECT s FROM Struktura s WHERE s.stKier = :stKier"),
    @NamedQuery(name = "Struktura.findByNodeId", query = "SELECT s FROM Struktura s WHERE s.nodeId = :nodeId"),
        @NamedQuery(name = "Struktura.kierownicy", query = "SELECT s FROM Struktura s WHERE s.stKier=1")
})
public class Struktura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQSTRUKTURA")
    @SequenceGenerator(name = "SEQSTRUKTURA", sequenceName = "SEQSTRUKTURA")
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "szef_id", referencedColumnName = "id")
    @ManyToOne
    private Uzytkownik szefId;
    @Column(name = "st_kier", nullable = false)
    private Integer stKier;
    @Column(name = "node_id")
    private Integer nodeId;
    @JoinColumn(name = "sec_user_id", referencedColumnName = "id")
    @OneToOne()
    private Uzytkownik secUserId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne()
    private Uzytkownik userId;
    @JoinColumn(name = "dzial_id", referencedColumnName = "id")
    @ManyToOne()
    private Dzial dzialId;
    
    @Transient
    private List<Uzytkownik> bezposrPodwl;

    public Struktura() {
    }

    public Struktura(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uzytkownik getSzefId() {
        return szefId;
    }

    public void setSzefId(Uzytkownik szefId) {
        this.szefId = szefId;
    }

    public boolean isStKier() {
        if(stKier==null||stKier==0) return false;
        else return true;
    }

    public void setStKier(boolean stKier) {
        if(stKier) this.stKier=1;
        else this.stKier=0;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Uzytkownik getSecUserId() {
        return secUserId;
    }

    public void setSecUserId(Uzytkownik secUserId) {
        this.secUserId = secUserId;
    }

    public Uzytkownik getUserId() {
        return userId;
    }

    public void setUserId(Uzytkownik userId) {
        this.userId = userId;
    }

    public Dzial getDzialId() {
        return dzialId;
    }

    public void setDzialId(Dzial dzialId) {
        this.dzialId = dzialId;
    }

    public List<Uzytkownik> getBezposrPodwl() {
        StrukturaJpaController sC=new StrukturaJpaController();
        return sC.findBezposrPodwl(userId);
    }

    public void setBezposrPodwl(List<Uzytkownik> bezposrPodwl) {
        this.bezposrPodwl = bezposrPodwl;
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
        if (!(object instanceof Struktura)) {
            return false;
        }
        Struktura other = (Struktura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod.encje.Struktura[ id=" + id + " ]";
    }
    
}
