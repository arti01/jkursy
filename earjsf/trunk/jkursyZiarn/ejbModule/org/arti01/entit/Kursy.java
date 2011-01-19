package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
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
	@ManyToMany(mappedBy="kursies", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private Set<User> users;
	
	//bi-directional many-to-one association to Lekcja
	@OneToMany(mappedBy="kursy", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@OrderBy(value="lp")
	private List<Lekcja> lekcjas;
	
	@OneToMany(mappedBy="kursy", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@OrderBy(value="datadodania")
	private List<Newsykursy> newsykursy;
	
	@NotNull
	private boolean stacjonarny;
	
	@Transient
	private List<User> wykladowcy;
	
	@Transient
	private List<User> kursanci;
	
	@Transient
	private List<Integer> lekcjeLpAll;
	
	@Transient
	private String stacjonarnyTN;
	
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

	public List<User> getWykladowcy() {
		return wykladowcy;
	}

	public void setWykladowcy(List<User> wykladowcy) {
		this.wykladowcy = wykladowcy;
	}

	public List<User> getKursanci() {
		return kursanci;
	}

	public void setKursanci(List<User> kursanci) {
		this.kursanci = kursanci;
	}

	public List<Lekcja> getLekcjas() {
		return lekcjas;
	}

	public void setLekcjas(List<Lekcja> lekcjas) {
		this.lekcjas = lekcjas;
	}

	public List<Integer> getLekcjeLpAll() {
		return lekcjeLpAll;
	}

	public void setLekcjeLpAll(List<Integer> lekcjeLpAll) {
		this.lekcjeLpAll = lekcjeLpAll;
	}

	public List<Newsykursy> getNewsykursy() {
		return newsykursy;
	}

	public void setNewsykursy(List<Newsykursy> newsykursy) {
		this.newsykursy = newsykursy;
	}

	public boolean isStacjonarny() {
		return stacjonarny;
	}

	public void setStacjonarny(boolean stacjonarny) {
		this.stacjonarny = stacjonarny;
	}

	public String getStacjonarnyTN() {
		if(stacjonarny) stacjonarnyTN="Stacjonarny";
		else stacjonarnyTN="Internetowy";
		return stacjonarnyTN;
	}

	public void setStacjonarnyTN(String stacjonarnyTN) {
		this.stacjonarnyTN = stacjonarnyTN;
	}
}