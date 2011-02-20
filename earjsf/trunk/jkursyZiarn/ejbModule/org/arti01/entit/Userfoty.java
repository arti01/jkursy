package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the userfoty database table.
 * 
 */
@Entity
@Table(name="userfoty")
public class Userfoty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer iduserfoty;

	private Timestamp datadodania;

	@Column(length=2147483647)
	private String exif;

	@Column(nullable=false)
	private Integer lp;

	@Column(length=2147483647)
	private String opis;
	
	@ManyToOne()
	@JoinColumn(name="username")
	private User user;

	private byte[] plik;

	private byte[] plikmini;
	
	private boolean akcept;

    public Userfoty() {
    }

	public Integer getIduserfoty() {
		return this.iduserfoty;
	}

	public void setIduserfoty(Integer iduserfoty) {
		this.iduserfoty = iduserfoty;
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

	public Integer getLp() {
		return this.lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAkcept() {
		return akcept;
	}

	public void setAkcept(boolean akcept) {
		this.akcept = akcept;
	}

}