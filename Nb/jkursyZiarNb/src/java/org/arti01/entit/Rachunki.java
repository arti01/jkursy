package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the rachunki database table.
 * 
 */
@Entity
@Table(name="rachunki")
public class Rachunki implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idrachunki;

	@Column(length=2147483647)
	private String adreskoresp;

	@Column(nullable=false, length=255)
	@NotEmpty
	private String imienazwisko;

	@Column(nullable=false, length=6)
	@Size(min=6, max=6)
	@NotEmpty
	private String kodpocztowy;

	@Column(nullable=false, length=255)
	@NotEmpty
	private String miasto;

	@Column(length=50)
	@NotEmpty
	private String nip;

	@Column(nullable=false, length=255)
	@NotEmpty
	private String ulica;
	
	@OneToOne
	@JoinColumn(name="idkursyrezerwacje")
	private KursyRezerwacje kursyRezerwacje;

    public Rachunki() {
    }

	public Integer getIdrachunki() {
		return this.idrachunki;
	}

	public void setIdrachunki(Integer idrachunki) {
		this.idrachunki = idrachunki;
	}

	public String getAdreskoresp() {
		return this.adreskoresp;
	}

	public void setAdreskoresp(String adreskoresp) {
		this.adreskoresp = adreskoresp;
	}

	public String getImienazwisko() {
		return this.imienazwisko;
	}

	public void setImienazwisko(String imienazwisko) {
		this.imienazwisko = imienazwisko;
	}

	public String getKodpocztowy() {
		return this.kodpocztowy;
	}

	public void setKodpocztowy(String kodpocztowy) {
		this.kodpocztowy = kodpocztowy;
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

	public String getUlica() {
		return this.ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

@XmlTransient	
        public KursyRezerwacje getKursyRezerwacje() {
		return kursyRezerwacje;
	}

	public void setKursyRezerwacje(KursyRezerwacje kursyRezerwacje) {
		this.kursyRezerwacje = kursyRezerwacje;
	}

}