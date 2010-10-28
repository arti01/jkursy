package org.arti01.obiekty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;



@Entity
@Table(name = "_users")
public class User{
	
	//@OneToMany(mappedBy="user",fetch=FetchType.EAGER, orphanRemoval=true)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="_user_roles", joinColumns = @JoinColumn( name="username"),inverseJoinColumns = @JoinColumn( name="role_name"))
	//@JoinTable(inverseJoinColumns = @JoinColumn( name="username"))
	private Set <Rola> role;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="kursy_users", joinColumns = @JoinColumn( name="username"),inverseJoinColumns = @JoinColumn( name="idkursy"))
	private Set <Kurs> kursy;
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "userpass")
	private String userpass;
	
	@Column(name="data_zmiany")
	@Temporal(TemporalType.DATE)
	private Date dataZmiany;
	
	@Column(name = "nip")
	private String nip;
	
	@Column(name = "opis")
	private String opis;
	
	public String getUsername() {
		return username;
	}

	@FieldExpressionValidator(key = "login juz istnieje", expression = "!isIstnieje()")
	@RequiredStringValidator(message="login nie moze byc pusty")
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserpass() {
		return userpass;
	}
	@RequiredStringValidator(message="hasło nie moze byc puste")
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
	public Date getData_zmiany() {
		return dataZmiany;
	}

	public void setData_zmiany(String data_zmiany) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		this.dataZmiany = sdf.parse(data_zmiany);
	}
	
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}
	
	@Column(name="email")
	private String email;

	@Column(name="wiadomosc")
	private String wiadomosc;
	
	
	@Column(name = "tel1")
	private String tel1;
	
	@Column(name = "imie_nazwisko")
	private String imieNazwisko;
	
	public String getImieNazwisko() {
		return imieNazwisko;
	}

	@RequiredStringValidator(message="proszę podać imię i nazwisko")
	public void setImieNazwisko(String imieNazwisko) {
		this.imieNazwisko = imieNazwisko;
	}

	@Column(name = "ulica")
	private String ulica;
	
	@Column(name = "miasto")
	private String miasto;
	
	public String getEmail() {
		return email;
	}

	@RequiredStringValidator(message="proszę podać email")
	@EmailValidator(message="to nie jest adres e-mail")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getWiadomosc() {
		return wiadomosc;
	}

	public void setWiadomosc(String wiadomosc) {
		this.wiadomosc = wiadomosc;
	}

	public Set<Rola> getRole() {
		return role;
	}

	public void setRole(Set<Rola> role) {
		this.role = role;
	}
	
	public String getTel1() {
		return tel1;
	}

	@RequiredStringValidator(message="pole nie może być puste")
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getUlica() {
		return ulica;
	}

	@RequiredStringValidator(message="pole nie może być puste")
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMiasto() {
		return miasto;
	}

	@RequiredStringValidator(message="pole nie może być puste")
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public Set<Kurs> getKursy() {
		return kursy;
	}

	public void setKursy(Set<Kurs> kursy) {
		this.kursy = kursy;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isIstnieje() {
		return new UserImp().istnieje(this);
	}

	}