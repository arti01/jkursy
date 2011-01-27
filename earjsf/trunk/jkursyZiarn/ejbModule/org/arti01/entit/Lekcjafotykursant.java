package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


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

	private Timestamp datadodania;

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

    public Lekcjafotykursant() {
    }

	public Integer getIdlekcjafotykursant() {
		return this.idlekcjafotykursant;
	}

	public void setIdlekcjafotykursant(Integer idlekcjafotykursant) {
		this.idlekcjafotykursant = idlekcjafotykursant;
	}

	public Timestamp getDatadodania() {
		return this.datadodania;
	}

	public void setDatadodania(Timestamp datadodania) {
		this.datadodania = datadodania;
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

}