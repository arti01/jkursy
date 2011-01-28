package org.arti01.entit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the poziomyzaawansowania database table.
 * 
 */
@Entity
@Table(name="poziomyzaawansowania")
public class Poziomyzaawansowania implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idpoziomyzaawansowania;

	@NotEmpty
	@Column(nullable=false, length=255)
	private String nazwa;
	
	@OneToMany(mappedBy="poziomyzaawansowania", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Kursy> kursy;

    public Poziomyzaawansowania() {
    }

	public Integer getIdpoziomyzaawansowania() {
		return this.idpoziomyzaawansowania;
	}

	public void setIdpoziomyzaawansowania(Integer idpoziomyzaawansowania) {
		this.idpoziomyzaawansowania = idpoziomyzaawansowania;
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

}