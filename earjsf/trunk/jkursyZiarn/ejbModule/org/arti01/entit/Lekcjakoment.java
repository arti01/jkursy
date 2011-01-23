package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the lekcjakoment database table.
 * 
 */
@Entity
@Table(name="lekcjakoment")
public class Lekcjakoment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idlekcjakoment;

	@Column(nullable=false)
	private Timestamp datadodania;

	@Column(nullable=false, length=2147483647)
	private String tresc;
	
	@ManyToOne
	@JoinColumn(name="idlekcja")
	private Lekcja lekcja;

	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
    public Lekcjakoment() {
    }

	public Integer getIdlekcjakoment() {
		return this.idlekcjakoment;
	}

	public void setIdlekcjakoment(Integer idlekcjakoment) {
		this.idlekcjakoment = idlekcjakoment;
	}

	public Timestamp getDatadodania() {
		return this.datadodania;
	}

	public void setDatadodania(Timestamp datadodania) {
		this.datadodania = datadodania;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}