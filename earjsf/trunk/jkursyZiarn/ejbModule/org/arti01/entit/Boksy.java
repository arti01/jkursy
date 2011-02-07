package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the boksy database table.
 * 
 */
@Entity
@Table(name="boksy")
public class Boksy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idboksy;

	@NotEmpty
	@Column(nullable=false, length=255)
	private String naglowek;

	private byte[] obraz;

	@Column(length=2147483647)
	private String tresc;
	
	@ManyToOne
	@JoinColumn(name="idboksycfg")
	private Boksycfg boksycfg;

    public Boksy() {
    }

	public Integer getIdboksy() {
		return this.idboksy;
	}

	public void setIdboksy(Integer idboksy) {
		this.idboksy = idboksy;
	}

	public String getNaglowek() {
		return this.naglowek;
	}

	public void setNaglowek(String naglowek) {
		this.naglowek = naglowek;
	}

	public byte[] getObraz() {
		return this.obraz;
	}

	public void setObraz(byte[] obraz) {
		this.obraz = obraz;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Boksycfg getBoksycfg() {
		return boksycfg;
	}

	public void setBoksycfg(Boksycfg boksycfg) {
		this.boksycfg = boksycfg;
	}

}