package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the _users database table.
 * 
 */
@Entity
@Table(name="_users")
@NamedQueries({
	@NamedQuery(name="sortByNameAsc", query="select u from User u order by u.imieNazwisko asc"),
	@NamedQuery(name="sortByNameDesc", query="select u from User u order by u.imieNazwisko desc")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	@Size(min=2, max=50)
	private String username;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="data_zmiany", nullable=false)
	private Date dataZmiany;

	@Column(nullable=false, length=100)
	@NotEmpty
	@Email
	private String email;

	@Column(name="imie_nazwisko", nullable=false, length=2147483647)
	@Size(min=3, max=255)
	private String imieNazwisko;

	@Column(nullable=false, length=2147483647)
	@Size(min=2, max=255)
	private String miasto;

	@Column(nullable=true, length=2147483647)
	//@Pattern(regexp="[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")
	private String nip;

	@Column(length=2147483647)
	private String opis;

	@Column(nullable=false, length=2147483647)
	@Size(min=2, max=255)
	private String tel1;

	@Column(nullable=false, length=2147483647)
	@Size(min=2, max=255)
	private String ulica;

	@Column(nullable=false, length=50)
	@Size(min=2, max=15)
	private String userpass;

	@Column(length=255)
	private String wiadomosc;
	
	@Transient
	private Lekcja konkretnaLekcja;
	
	@Transient
	private List<Lekcjafotykursant> fotyBezkomentarza;

	//bi-directional many-to-many association to Role
	@ManyToMany(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
	@JoinTable(
		name="_users__roles"
		, joinColumns={
			@JoinColumn(name="username", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="roles_rola", nullable=false)
			}
		)
	private Set<Role> roles;

	//bi-directional many-to-many association to Kursy
    @ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(
		name="kursy_users"
		, joinColumns={
			@JoinColumn(name="username", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idkursy", nullable=false)
			}
		)
	private List<Kursy> kursies;
    
    @OneToMany(mappedBy="user")
	@OrderBy("datadodania")
	private List<Lekcjakoment> lekcjakoments;
    
    @OneToMany(mappedBy="user")
	@OrderBy("datadodania")
	private List<Lekcjafotykursant> lekcjafotykursant;
    
    @OneToMany(mappedBy="user")
	@OrderBy("datadodania")
	private List<Absolwforwatki> absolwforwatki;

    @OneToMany(mappedBy="user")
	@OrderBy("datadodania")
	private List<Absolwforposty> absolwforposty;
    
    public User() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public List<Kursy> getKursies() {
		return this.kursies;
	}

	public void setKursies(List<Kursy> kursies) {
		this.kursies = kursies;
	}

	public Date getDataZmiany() {
		return dataZmiany;
	}

	public void setDataZmiany(Date dataZmiany) {
		this.dataZmiany = dataZmiany;
	}

	public void setLekcjakoments(List<Lekcjakoment> lekcjakoments) {
		this.lekcjakoments = lekcjakoments;
	}

	public List<Lekcjakoment> getLekcjakoments() {
		return lekcjakoments;
	}

	public List<Lekcjafotykursant> getLekcjafotykursant() {
		return lekcjafotykursant;
	}

	public void setLekcjafotykursant(List<Lekcjafotykursant> lekcjafotykursant) {
		this.lekcjafotykursant = lekcjafotykursant;
	}

	public List<Absolwforwatki> getAbsolwforwatki() {
		return absolwforwatki;
	}

	public void setAbsolwforwatki(List<Absolwforwatki> absolwforwatki) {
		this.absolwforwatki = absolwforwatki;
	}

	public List<Absolwforposty> getAbsolwforposty() {
		return absolwforposty;
	}

	public void setAbsolwforposty(List<Absolwforposty> absolwforposty) {
		this.absolwforposty = absolwforposty;
	}

	public List<Lekcjafotykursant> getFotyBezkomentarza() {
		fotyBezkomentarza=new ArrayList<Lekcjafotykursant>();
		for(Lekcjafotykursant l:getKonkretnaLekcja().getFotyKursNieSkoment()){
			if(l.getUser().getUsername().equals(username)) fotyBezkomentarza.add(l);
		}
		return fotyBezkomentarza;
	}

	public void setFotyBezkomentarza(List<Lekcjafotykursant> fotyBezkomentarza) {
		this.fotyBezkomentarza = fotyBezkomentarza;
	}

	public Lekcja getKonkretnaLekcja() {
		return konkretnaLekcja;
	}

	public void setKonkretnaLekcja(Lekcja konkretnaLekcja) {
		this.konkretnaLekcja = konkretnaLekcja;
	}
	
}