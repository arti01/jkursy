package org.arti01.entit_old;

import java.io.Serializable;
import javax.persistence.*;
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
	@SequenceGenerator(name="KURSY_IDKURSY_GENERATOR", sequenceName="KURSY_IDKURSY_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KURSY_IDKURSY_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idkursy;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date datado;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date dataod;

	@Column(nullable=false, length=255)
	private String nazwa;

	@Column(nullable=false, length=2147483647)
	private String opis;

	@Column(name="opis_krotki", length=2147483647)
	private String opisKrotki;

	//bi-directional many-to-one association to KursyUser
	@OneToMany(mappedBy="kursy")
	private Set<KursyUser> kursyUsers;

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

	public Set<KursyUser> getKursyUsers() {
		return this.kursyUsers;
	}

	public void setKursyUsers(Set<KursyUser> kursyUsers) {
		this.kursyUsers = kursyUsers;
	}
	
}