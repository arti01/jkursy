package arti01.jobiady.beany;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the _users database table.
 *
 */
@Entity
public class Uzytkownik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 50)
    @Size(min = 2, max = 50)
    private String username;
    @Column(nullable = true, length = 100)
    @Email
    private String email;
    @Column(name = "imie_nazwisko", nullable = false)
    @Size(min = 3, max = 255)
    private String imieNazwisko;
    @Column(nullable = true)
    private String tel1;
    @Column(nullable = true, length = 50)
    @Size(min = 2, max = 15)
    private String userpass;
    
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {
        @JoinColumn(name = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "roles_rola", nullable = false)
    })
    private List<Role> roles;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @OrderBy("idzamowienie DESC")
    @JoinColumn(name = "username")
    private List<Zamowienie> zamowienia;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @OrderBy("id DESC")
    @JoinColumn(name = "tragarz_username")
    private List<Kurs> kursy;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public List<Kurs> getKursy() {
        return kursy;
    }

    public void setKursy(List<Kurs> kursy) {
        this.kursy = kursy;
    }

    public void addZamowienie(Zamowienie zam) {
        zam.setUzytkownik(this);
        this.zamowienia.add(0, zam);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.username != null ? this.username.hashCode() : 0);
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
}