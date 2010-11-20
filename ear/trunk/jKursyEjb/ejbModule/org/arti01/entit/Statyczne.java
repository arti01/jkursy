package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the statyczne database table.
 * 
 */
@Entity
@Table(name="statyczne")
public class Statyczne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATYCZNE_IDSTATYCZNE_GENERATOR", sequenceName="STATYCZNE_ID_STATYCZNE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATYCZNE_IDSTATYCZNE_GENERATOR")
	@Column(name="id_statyczne", unique=true, nullable=false)
	private Integer idStatyczne;

	@Column(nullable=false)
	private Integer lp;

	@Column(nullable=false, length=255)
	private String opis;

	@Column(nullable=false, length=2147483647)
	private String tresc;

	@Column(nullable=false, length=255)
	private String tytul;

    public Statyczne() {
    }

	public Integer getIdStatyczne() {
		return this.idStatyczne;
	}

	public void setIdStatyczne(Integer idStatyczne) {
		this.idStatyczne = idStatyczne;
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

}