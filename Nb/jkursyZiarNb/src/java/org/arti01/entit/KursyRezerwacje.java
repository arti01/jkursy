package org.arti01.entit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the kursy_rezerwacje database table.
 * 
 */
@Entity
@Table(name = "kursy_rezerwacje")
public class KursyRezerwacje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idkursyrezerwacje;

	@Column(nullable = false)
	private Boolean aktywna;

	@Column(nullable = false)
	private Boolean wykonana;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date datarezerwacji;

	private double wplata;
	
	@Size(max=250)
	private String polecajacy;

	@Temporal(TemporalType.DATE)
	private Date datawplaty;

	@ManyToOne
	@JoinColumn(name = "idkursy")
	private Kursy kursy;

	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "kursyRezerwacje")
	private Rachunki rachunki;

	public KursyRezerwacje() {
	}

	public Integer getIdkursyrezerwacje() {
		return this.idkursyrezerwacje;
	}

	public void setIdkursyrezerwacje(Integer idkursyrezerwacje) {
		this.idkursyrezerwacje = idkursyrezerwacje;
	}

	public Boolean getAktywna() {
		return this.aktywna;
	}

	public void setAktywna(Boolean aktywna) {
		this.aktywna = aktywna;
	}

	public Boolean getWykonana() {
		return this.wykonana;
	}

	public void setWykonana(Boolean wykonana) {
		this.wykonana = wykonana;
	}

@XmlTransient	
        public Kursy getKursy() {
		return kursy;
	}

	public void setKursy(Kursy kursy) {
		this.kursy = kursy;
	}

	public Rachunki getRachunki() {
		return rachunki;
	}

	public void setRachunki(Rachunki rachunki) {
		this.rachunki = rachunki;
	}
@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDatarezerwacji() {
		return datarezerwacji;
	}

	public void setDatarezerwacji(Date datarezerwacji) {
		this.datarezerwacji = datarezerwacji;
	}

	public double getWplata() {
		return wplata;
	}

	public void setWplata(double wplata) {
		this.wplata = wplata;
	}

	public Date getDatawplaty() {
		return datawplaty;
	}

	public void setDatawplaty(Date datawplaty) {
		this.datawplaty = datawplaty;
	}

	public String getPolecajacy() {
		return polecajacy;
	}

	public void setPolecajacy(String polecajacy) {
		this.polecajacy = polecajacy;
	}

}