package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the statyczne database table.
 * 
 */
@Entity
@Table(name="statyczne")
public class Statyczne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_statyczne", unique=true, nullable=false)
	private Integer idStatyczne;

	@Column(nullable=false)
	private Integer lp;

	@Size(min=1, max=255)
	@Column(nullable=false, length=255)
	private String opis;
	
	@Size(min=1)
	@Column(nullable=false, length=2147483647)
	private String tresc;

	@Column(nullable=false, length=255)
	@NotNull
	@Size(min=1, max=255)
	private String tytul;
	
	@ManyToOne
	@JoinColumn(name="rola")
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}