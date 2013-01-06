/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.encje;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "uzytkownik")
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findById", query = "SELECT u FROM Uzytkownik u WHERE u.id = :id"),
    @NamedQuery(name = "Uzytkownik.findByFullname", query = "SELECT u FROM Uzytkownik u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "Uzytkownik.findByAdrEmail", query = "SELECT u FROM Uzytkownik u WHERE u.adrEmail = :adrEmail"),
    @NamedQuery(name = "Uzytkownik.findByExtId", query = "SELECT u FROM Uzytkownik u WHERE u.extId = :extId")})
public class Uzytkownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUSER")
    @SequenceGenerator(name = "SEQUSER", sequenceName = "SEQUSER")
    private Long id;
    
    @Column(name = "fullname", nullable = false)
    @Size(min = 3, max = 255)
    @NotEmpty
    private String fullname;
    
    //@NotEmpty
    @Email
    //@UzytkowAdniot(value = true)
    @Column(name = "adr_email", unique = true)
    private String adrEmail;
    
    @Column(name = "ext_id")
    private String extId;
    
    @OneToOne(mappedBy = "secUserId")
    Struktura strukturaSec;
    
    @OneToOne(mappedBy = "userId", cascade = {CascadeType.ALL})
    Struktura struktura;
    
    @JoinColumn(name = "haslo_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Hasla hasla;
    
    @JoinColumn(name = "adr_email", referencedColumnName = "username")
    @ManyToMany()
    private List<UserRoles> role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uzytkownik", orphanRemoval = true)
    @OrderBy(value = "id DESC")
    private List<WnUrlop> wnUrlopList;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "akceptant")
    @OrderBy(value = "id DESC")
    private List<WnUrlop> wnUrlopListDoAkceptu;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zmieniajacy")
    private List<WnHistoria> wnHistoriaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "akceptant")
    private List<WnHistoria> wnHistoriaListAkceptant;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przyjmujacy")
    private List<WnUrlop> wnUrlopListPrzyjmujacy;
    
    public Uzytkownik() {
    }

    public Uzytkownik(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdrEmail() {
        return adrEmail;
    }

    public void setAdrEmail(String adrEmail) {
        this.adrEmail = adrEmail;
    }

    public Struktura getStrukturaSec() {
        return strukturaSec;
    }

    public void setStrukturaSec(Struktura strukturaSec) {
        this.strukturaSec = strukturaSec;
    }

    public Struktura getStruktura() {
        return struktura;
    }

    public void setStruktura(Struktura struktura) {
        this.struktura = struktura;
    }

    public Hasla getHasla() {
        return hasla;
    }

    public void setHasla(Hasla hasla) {
        this.hasla = hasla;
    }

    public List<UserRoles> getRole() {
        return role;
    }

    public void setRole(List<UserRoles> role) {
        this.role = role;
    }

    public List<WnUrlop> getWnUrlopList() {
        return wnUrlopList;
    }

    public void setWnUrlopList(List<WnUrlop> wnUrlopList) {
        this.wnUrlopList = wnUrlopList;
    }

    public List<WnHistoria> getWnHistoriaList() {
        return wnHistoriaList;
    }

    public void setWnHistoriaList(List<WnHistoria> wnHistoriaList) {
        this.wnHistoriaList = wnHistoriaList;
    }

    public List<WnUrlop> getWnUrlopListPrzyjmujacy() {
        return wnUrlopListPrzyjmujacy;
    }

    public void setWnUrlopListPrzyjmujacy(List<WnUrlop> wnUrlopListPrzyjmujacy) {
        this.wnUrlopListPrzyjmujacy = wnUrlopListPrzyjmujacy;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<WnUrlop> getWnUrlopListDoAkceptu() {
        return wnUrlopListDoAkceptu;
    }

    public void setWnUrlopListDoAkceptu(List<WnUrlop> wnUrlopListDoAkceptu) {
        this.wnUrlopListDoAkceptu = wnUrlopListDoAkceptu;
    }

    public List<WnHistoria> getWnHistoriaListAkceptant() {
        return wnHistoriaListAkceptant;
    }

    public void setWnHistoriaListAkceptant(List<WnHistoria> wnHistoriaListAkceptant) {
        this.wnHistoriaListAkceptant = wnHistoriaListAkceptant;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod.encje.Uzytkownik[ id=" + id + " ]";
    }
    
}
