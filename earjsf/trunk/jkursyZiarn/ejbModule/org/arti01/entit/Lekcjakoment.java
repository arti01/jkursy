package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;


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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date datadodania;
	
	@Transient
	private String datadodaniaS;

	@Column(nullable=false, length=2147483647)
	@NotEmpty
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

	public Date getDatadodania() {
		return datadodania;
	}

	public void setDatadodania(Date datadodania) {
		this.datadodania = datadodania;
	}

	public String getDatadodaniaS() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		datadodaniaS=sdf.format(getDatadodania());
		return datadodaniaS;
	}

	public void setDatadodaniaS(String datadodaniaS) {
		this.datadodaniaS = datadodaniaS;
	}

}