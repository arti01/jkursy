package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


@Entity
public class Newsykursy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idnewsykursy;

    @Temporal( TemporalType.DATE)
	private Date datadodania;

	private String tresc;

	@NotEmpty
	private String tytul;
	
	@ManyToOne
	@JoinColumn(name="idkursy")
	private Kursy kursy;

    public Newsykursy() {
    }

	public Integer getIdnewsykursy() {
		return this.idnewsykursy;
	}

	public void setIdnewsykursy(Integer idnewsykursy) {
		this.idnewsykursy = idnewsykursy;
	}

	public Date getDatadodania() {
		return this.datadodania;
	}

	public void setDatadodania(Date datadodania) {
		this.datadodania = datadodania;
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
		return kursy;
	}

	public void setKursy(Kursy kursy) {
		this.kursy = kursy;
	}

}