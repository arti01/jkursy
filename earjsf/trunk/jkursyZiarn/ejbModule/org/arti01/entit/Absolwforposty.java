package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;


/**
 * The persistent class for the absolwforposty database table.
 * 
 */
@Entity
@Table(name="absolwforposty")
public class Absolwforposty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idabsolwforposty;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date datadodania;

	@NotEmpty
	@Column(nullable=false, length=2147483647)
	private String tresc;

	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	//bi-directional many-to-one association to Absolwforwatki
    @ManyToOne
	@JoinColumn(name="idabsolwforwatki", nullable=false)
	private Absolwforwatki absolwforwatki;

    public Absolwforposty() {
    }

	public Integer getIdabsolwforposty() {
		return this.idabsolwforposty;
	}

	public void setIdabsolwforposty(Integer idabsolwforposty) {
		this.idabsolwforposty = idabsolwforposty;
	}


	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Absolwforwatki getAbsolwforwatki() {
		return this.absolwforwatki;
	}

	public void setAbsolwforwatki(Absolwforwatki absolwforwatki) {
		this.absolwforwatki = absolwforwatki;
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
	
}