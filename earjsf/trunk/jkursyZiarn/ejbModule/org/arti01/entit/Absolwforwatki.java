package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the absolwforwatki database table.
 * 
 */
@Entity
@Table(name="absolwforwatki")
public class Absolwforwatki implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idabsolwforwatki;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date datadodania;

	@NotEmpty
	@Column(nullable=false, length=255)
	private String tytul;

	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	//bi-directional many-to-one association to Absolwforposty
	@OneToMany(mappedBy="absolwforwatki")
	private List<Absolwforposty> absolwforposties;

    public Absolwforwatki() {
    }

	public Integer getIdabsolwforwatki() {
		return this.idabsolwforwatki;
	}

	public void setIdabsolwforwatki(Integer idabsolwforwatki) {
		this.idabsolwforwatki = idabsolwforwatki;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
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

	public List<Absolwforposty> getAbsolwforposties() {
		return absolwforposties;
	}

	public void setAbsolwforposties(List<Absolwforposty> absolwforposties) {
		this.absolwforposties = absolwforposties;
	}
	
}