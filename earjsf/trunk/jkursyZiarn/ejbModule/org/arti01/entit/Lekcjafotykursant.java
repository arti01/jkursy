package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lekcjafotykursant database table.
 * 
 */
@Entity
@Table(name="lekcjafotykursant")
public class Lekcjafotykursant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idlekcjafotykursant;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date datadodania;
	
	@Transient
	private String datadodaniaS;

	@Column(length=2147483647)
	private String exif;

	@Column(length=2147483647)
	private String opis;

	private byte[] plik;

	private byte[] plikmini;

	@ManyToOne
	@JoinColumn(name="idlekcja")
	private Lekcja lekcja;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	@OneToMany(mappedBy="lekcjafotykursant", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@OrderBy(value="datadodania DESC")
	private List<Fotykursantkoment> fotykursantkoment;
	
    public Lekcjafotykursant() {
    }

	public Integer getIdlekcjafotykursant() {
		return this.idlekcjafotykursant;
	}

	public void setIdlekcjafotykursant(Integer idlekcjafotykursant) {
		this.idlekcjafotykursant = idlekcjafotykursant;
	}

	public String getExif() {
		return this.exif;
	}

	public void setExif(String exif) {
		this.exif = exif;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getPlik() {
		return this.plik;
	}

	public void setPlik(byte[] plik) {
		this.plik = plik;
	}

	public byte[] getPlikmini() {
		return this.plikmini;
	}

	public void setPlikmini(byte[] plikmini) {
		this.plikmini = plikmini;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDatadodaniaS() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//System.out.println(getDatadodania());
		//System.out.println(getIdlekcjafotykursant());
		datadodaniaS=sdf.format(getDatadodania());
		return datadodaniaS;
	}

	public void setDatadodaniaS(String datadodaniaS) {
		this.datadodaniaS = datadodaniaS;
	}

	public Date getDatadodania() {
		return datadodania;
	}

	public void setDatadodania(Date datadodania) {
		this.datadodania = datadodania;
	}

	public List<Fotykursantkoment> getFotykursantkoment() {
		return fotykursantkoment;
	}

	public void setFotykursantkoment(List<Fotykursantkoment> fotykursantkoment) {
		this.fotykursantkoment = fotykursantkoment;
	}

}