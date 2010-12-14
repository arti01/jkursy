package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the kursy database table.
 * 
 */

@Entity
@Table(name="kursy")
@NamedQuery(name="findNiezakonczone", query="select k from Kursy k where k.datado>=:datado order by k.dataod desc")
public class Kursy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idkursy;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	@NotNull
	private Date datado;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	@NotNull
	private Date dataod;

	@Column(nullable=false, length=255)
	@Size(min=1, max=255)
	private String nazwa;

	@Column(nullable=false, length=2147483647)
	@Size(min=1)
	private String opis;

	@Column(name="opis_krotki", length=2147483647)
	@Size(min=1)
	@NotNull(message="problem")
	private String opisKrotki;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="kursies")
	private Set<User> users;
	
	@Transient
	private Set<User> wykladowcy;
	
	@Transient
	private Set<User> kursanci;
	
    public Kursy() {
    }

	public Integer getIdkursy() {
		return this.idkursy;
	}

	public void setIdkursy(Integer idkursy) {
		this.idkursy = idkursy;
	}

	public Date getDatado() {
		return this.datado;
	}

	public void setDatado(Date datado) {
		this.datado = datado;
	}

	public Date getDataod() {
		return this.dataod;
	}

	public void setDataod(Date dataod) {
		this.dataod = dataod;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOpisKrotki() {
		return this.opisKrotki;
	}

	public void setOpisKrotki(String opisKrotki) {
		this.opisKrotki = opisKrotki;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> getWykladowcy() {
		return wykladowcy;
	}

	public void setWykladowcy(Set<User> wykladowcy) {
		this.wykladowcy = wykladowcy;
	}

	public Set<User> getKursanci() {
		return kursanci;
	}

	public void setKursanci(Set<User> kursanci) {
		this.kursanci = kursanci;
	}
}