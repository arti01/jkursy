package org.arti01.entit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the typykursu database table.
 * 
 */
@Entity
@Table(name="typykursu")
public class Typykursu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idtypykursu;

	@NotEmpty
	@Column(nullable=false, length=255)
	private String nazwa;
	
	@NotEmpty
	@Column(nullable=false)
	private String opis;
	
	@Column(length=7)
	@Size(max=7)
	private String kolor;
	
	@OneToMany(mappedBy="typykursu", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Kursy> kursy;

    public Typykursu() {
    }

	public Integer getIdtypykursu() {
		return this.idtypykursu;
	}

	public void setIdtypykursu(Integer idtypykursu) {
		this.idtypykursu = idtypykursu;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public List<Kursy> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kursy> kursy) {
		this.kursy = kursy;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getKolor() {
		return kolor;
	}

	public void setKolor(String kolor) {
		this.kolor = kolor;
	}

}