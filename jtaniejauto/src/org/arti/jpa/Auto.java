package org.arti.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the auto database table.
 * 
 */
@Entity
@Table(name="auto")
public class Auto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idauto;

	@Column(nullable=false)
	private double cena;

	@Column(length=2147483647)
	private String opis;

	@Column(nullable=false, length=100)
	private String typ;

	//bi-directional many-to-one association to Marka
    @ManyToOne
	@JoinColumn(name="idmarka", nullable=false)
	private Marka marka;

    public Auto() {
    }

	public Integer getIdauto() {
		return this.idauto;
	}

	public void setIdauto(Integer idauto) {
		this.idauto = idauto;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Marka getMarka() {
		return this.marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}
	
}