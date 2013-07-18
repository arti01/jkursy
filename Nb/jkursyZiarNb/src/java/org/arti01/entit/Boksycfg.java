package org.arti01.entit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the boksycfg database table.
 * 
 */
@Entity
@Table(name="boksycfg")
@XmlRootElement
public class Boksycfg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idboksycfg;

	@Column(nullable=false)
	@NotNull
	@DecimalMin(value="1")
	private Integer iloscelementow;

	@Column(nullable=false, length=250)
	private String nazwa;

	@Column(nullable=false)
	private Boolean widoczny;
	@OrderBy(value = "idboksy DESC")
	@OneToMany(mappedBy="boksycfg", cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<Boksy> boksy;

    public Boksycfg() {
    }

	public Integer getIdboksycfg() {
		return this.idboksycfg;
	}

	public void setIdboksycfg(Integer idboksycfg) {
		this.idboksycfg = idboksycfg;
	}

	public Integer getIloscelementow() {
		return this.iloscelementow;
	}

	public void setIloscelementow(Integer iloscelementow) {
		this.iloscelementow = iloscelementow;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Boolean getWidoczny() {
		return this.widoczny;
	}

	public void setWidoczny(Boolean widoczny) {
		this.widoczny = widoczny;
	}

	public List<Boksy> getBoksy() {
		return boksy;
	}

	public void setBoksy(List<Boksy> boksy) {
		this.boksy = boksy;
	}

}