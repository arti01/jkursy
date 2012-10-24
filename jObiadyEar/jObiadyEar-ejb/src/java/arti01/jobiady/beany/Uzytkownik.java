/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "uzytkownik")
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u")})
public class Uzytkownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "imie_nazwisko")
    private String imieNazwisko;
    @Size(max = 255)
    @Column(name = "tel1")
    private String tel1;
    @Size(max = 50)
    @Column(name = "userpass")
    private String userpass;
    @OneToMany(cascade= CascadeType.ALL, mappedBy="tragarz", fetch = FetchType.LAZY, orphanRemoval=true)
    @OrderBy("id DESC")
    private List<Kurs> kursy;
    
    @OneToMany(mappedBy="uzytkownik", cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @OrderBy("idzamowienie DESC")
    private List<Zamowienie> zamowienia;
    
    @OneToMany(cascade= CascadeType.ALL, mappedBy="tragarz", fetch = FetchType.LAZY)
    @OrderBy("idtransakcjezamowienia DESC")
    private List<Transakcjezamowienia> transakcjezamowienia;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {
        @JoinColumn(name = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "roles_rola", nullable = false)
    })
    private List<Role> roles;

    public Uzytkownik() {
    }

    public Uzytkownik(String username) {
        this.username = username;
    }

    public Uzytkownik(String username, String imieNazwisko) {
        this.username = username;
        this.imieNazwisko = imieNazwisko;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public List<Kurs> getKursy() {
        return kursy;
    }

    public void setKursy(List<Kurs> kursy) {
        this.kursy = kursy;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "arti01.jobiady.beany.Uzytkownik[ username=" + username + " ]";
    }
    
}
