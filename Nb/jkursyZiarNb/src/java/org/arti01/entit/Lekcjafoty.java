package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;


/**
 * The persistent class for the lekcjafoty database table.
 * 
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"lp", "idlekcja"}))
public class Lekcjafoty implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlekcjafoty;

	private Timestamp datadodania;

	private String opis;
	
	private String exif;

	private byte[] plik;

	private byte[] plikmini;
	
	@NotNull
	private Integer lp;

	//bi-directional many-to-one association to Lekcja
    @ManyToOne
	@JoinColumn(name="idlekcja")
	private Lekcja lekcja;

    public Lekcjafoty() {
    }

	public Integer getIdlekcjafoty() {
		return this.idlekcjafoty;
	}

	public void setIdlekcjafoty(Integer idlekcjafoty) {
		this.idlekcjafoty = idlekcjafoty;
	}

	public Timestamp getDatadodania() {
		return this.datadodania;
	}

	public void setDatadodania(Timestamp datadodania) {
		this.datadodania = datadodania;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getData() {
		return this.plik;
	}

	public void setData(byte[] plik) {
		this.plik = plik;
	}

	public byte[] getPlikmini() {
		return this.plikmini;
	}

	public void setPlikmini(byte[] plikmini) {
		this.plikmini = plikmini;
	}

	public Lekcja getLekcja() {
		return this.lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public Integer getLp() {
		return lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
	}

	public String getExif() {
		return exif;
	}

	public void setExif(String exif) {
		this.exif = exif;
	}
	
}