package org.arti01.obiekty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


@Entity
@Table(name = "kursy")
public class Kurs{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "kursy_idkursy_seq")
	@SequenceGenerator(name = "kursy_idkursy_seq", sequenceName = "kursy_idkursy_seq")
	@Column(name = "idkursy")
	private Integer idkursy;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="kursy_users", joinColumns = @JoinColumn( name="idkursy"),inverseJoinColumns = @JoinColumn( name="username"))
	private Set <User> users;
	
	@Embedded
	private List <User> wykladowcy;
	
	@Column(name = "nazwa")
	private String nazwa;
	
	@Column(name = "opis")
	private String tresc;
	
	@Column(name = "opis_krotki")
	private String opisKrotki;
	
	@Column(name = "dataOd")
	@Temporal(TemporalType.DATE)
	private Date dataOd;
	
	@Column(name = "dataDo")
	@Temporal(TemporalType.DATE)
	private Date dataDo;

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Integer getIdkursy() {
		return idkursy;
	}

	public void setIdkursy(Integer idkursy) {
		this.idkursy = idkursy;
	}

	public String getNazwa() {
		return nazwa;
	}

	@RequiredStringValidator(message="proszę podać nazwę")
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}


	@RequiredFieldValidator(message="prosz� poda� dat�")
	public void setDataOd(String dataOd) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		this.dataOd = sdf.parse(dataOd);
	}

	@FieldExpressionValidator(key = "daty bez sensu", expression = "dataOd<dataDo")
	@RequiredFieldValidator(message="proszę podać datę")
	public void setDataDo(String dataDo) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		this.dataDo = sdf.parse(dataDo);
	}

	public List<User> getWykladowcy() {
		wykladowcy=new ArrayList<User>();
		for(User user :getUsers()){
			for(Rola r:user.getRole()){
				if(r.getRola().equals("wykladowca")) wykladowcy.add(user);
			}
		}
		return wykladowcy;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public Date getDataOd() {
		return dataOd;
	}

	public String getOpisKrotki() {
		return opisKrotki;
	}

	@RequiredStringValidator(message="proszę podać krótki opis")
	public void setOpisKrotki(String opisKrotki) {
		this.opisKrotki = opisKrotki;
	}

	public String getTresc() {
		return tresc;
	}

	@RequiredStringValidator(message="proszę podać opis")
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	}