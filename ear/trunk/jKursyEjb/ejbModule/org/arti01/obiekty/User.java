package org.arti01.obiekty;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the _users database table.
 * 
 */
@Entity
@Table(name="_users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="_USERS_USERNAME_GENERATOR", sequenceName="_USERS_USERNAME_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="_USERS_USERNAME_GENERATOR")
	private String username;

    @Temporal( TemporalType.DATE)
	@Column(name="data_zmiany")
	private Date dataZmiany;

	private String email;

	@Column(name="imie_nazwisko")
	private String imieNazwisko;

	private String miasto;

	private String nip;

	private String opis;

	private String tel1;

	private String ulica;

	private String userpass;

	private String wiadomosc;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private Set<UserRole> userRoles;

	//bi-directional many-to-one association to KursyUser
	@OneToMany(mappedBy="user")
	private Set<KursyUser> kursyUsers;

    public User() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDataZmiany() {
		return this.dataZmiany;
	}

	public void setDataZmiany(Date dataZmiany) {
		this.dataZmiany = dataZmiany;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImieNazwisko() {
		return this.imieNazwisko;
	}

	public void setImieNazwisko(String imieNazwisko) {
		this.imieNazwisko = imieNazwisko;
	}

	public String getMiasto() {
		return this.miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getNip() {
		return this.nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getUlica() {
		return this.ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getUserpass() {
		return this.userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getWiadomosc() {
		return this.wiadomosc;
	}

	public void setWiadomosc(String wiadomosc) {
		this.wiadomosc = wiadomosc;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public Set<KursyUser> getKursyUsers() {
		return this.kursyUsers;
	}

	public void setKursyUsers(Set<KursyUser> kursyUsers) {
		this.kursyUsers = kursyUsers;
	}
	
}