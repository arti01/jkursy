package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the lekcjapliki database table.
 * 
 */
@Entity
public class Lekcjapliki implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlekcjapliki;

	private Timestamp datadodania;

	private String opis;

	private byte[] plik;

    @ManyToOne
	@JoinColumn(name="idlekcja")
	private Lekcja lekcja;

    public Lekcjapliki() {
    }

	public Integer getIdlekcjapliki() {
		return this.idlekcjapliki;
	}

	public void setIdlekcjapliki(Integer idlekcjapliki) {
		this.idlekcjapliki = idlekcjapliki;
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

	public byte[] getPlik() {
		return this.plik;
	}

	public void setPlik(byte[] plik) {
		this.plik = plik;
	}
	
	public Lekcja getLekcja() {
		return this.lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}
	
}