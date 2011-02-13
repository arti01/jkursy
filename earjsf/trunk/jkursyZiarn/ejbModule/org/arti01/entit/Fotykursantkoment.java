package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lekcjakoment database table.
 * 
 */
@Entity
@Table(name="fotykursantkoment")
public class Fotykursantkoment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idfotykursantkoment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date datadodania;
	
	@Transient
	private String datadodaniaS;

	@Column(nullable=false, length=2147483647)
	@NotEmpty
	private String tresc;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idlekcjafotykursant")
	private Lekcjafotykursant lekcjafotykursant;

	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@Transient
	private boolean komentWykl;
	
    public Fotykursantkoment() {
    }

	public String getDatadodaniaS() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		datadodaniaS=sdf.format(getDatadodania());
		return datadodaniaS;
	}

	public void setDatadodaniaS(String datadodaniaS) {
		this.datadodaniaS = datadodaniaS;
	}

	public Integer getIdfotykursantkoment() {
		return idfotykursantkoment;
	}

	public void setIdfotykursantkoment(Integer idfotykursantkoment) {
		this.idfotykursantkoment = idfotykursantkoment;
	}

	public Date getDatadodania() {
		return datadodania;
	}

	public void setDatadodania(Date datadodania) {
		this.datadodania = datadodania;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Lekcjafotykursant getLekcjafotykursant() {
		return lekcjafotykursant;
	}

	public void setLekcjafotykursant(Lekcjafotykursant lekcjafotykursant) {
		this.lekcjafotykursant = lekcjafotykursant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isKomentWykl() {
		//System.out.println(komentWykl+getDatadodaniaS());
		komentWykl=false;
		List<User> wykladowcy=getLekcjafotykursant().getLekcja().getKursy().getWykladowcy();
		if(wykladowcy.contains(getUser())) komentWykl=true;
		return komentWykl;
	}

	public void setKomentWykl(boolean komentWykl) {
		this.komentWykl = komentWykl;
	}

}