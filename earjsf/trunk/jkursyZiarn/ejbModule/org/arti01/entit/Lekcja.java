package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the lekcja database table.
 * 
 */
@Entity
public class Lekcja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlekcja;

	private Timestamp datazmiany;

	@NotEmpty
	private String tresc;

	@NotEmpty
	private String tytul;
	
	@NotNull
	private Integer lp;

	//bi-directional many-to-one association to Kursy
    @ManyToOne
	@JoinColumn(name="idkursy")
	private Kursy kursy;

	//bi-directional many-to-one association to Lekcjafoty
	@OneToMany(mappedBy="lekcja")
	private Set<Lekcjafoty> lekcjafoties;

	//bi-directional many-to-one association to Lekcjapliki
	@OneToMany(mappedBy="lekcja")
	private Set<Lekcjapliki> lekcjaplikis;

	public Integer getIdlekcja() {
		return this.idlekcja;
	}

	public void setIdlekcja(Integer idlekcja) {
		this.idlekcja = idlekcja;
	}

	public Timestamp getDatazmiany() {
		return this.datazmiany;
	}

	public void setDatazmiany(Timestamp datazmiany) {
		this.datazmiany = datazmiany;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public Kursy getKursy() {
		return this.kursy;
	}

	public void setKursy(Kursy kursy) {
		this.kursy = kursy;
	}
	
	public Set<Lekcjafoty> getLekcjafoties() {
		return this.lekcjafoties;
	}

	public void setLekcjafoties(Set<Lekcjafoty> lekcjafoties) {
		this.lekcjafoties = lekcjafoties;
	}
	
	public Set<Lekcjapliki> getLekcjaplikis() {
		return this.lekcjaplikis;
	}

	public void setLekcjaplikis(Set<Lekcjapliki> lekcjaplikis) {
		this.lekcjaplikis = lekcjaplikis;
	}

	public Integer getLp() {
		return lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
	}
	
}