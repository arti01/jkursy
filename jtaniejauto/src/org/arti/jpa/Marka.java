package org.arti.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marka database table.
 * 
 */
@Entity
@Table(name="marka")
public class Marka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idmarka;

	@Column(nullable=false, length=100)
	private String marka;

	//bi-directional many-to-one association to Auto
	@OneToMany(mappedBy="marka")
	private List<Auto> autos;

    public Marka() {
    }

	public Integer getIdmarka() {
		return this.idmarka;
	}

	public void setIdmarka(Integer idmarka) {
		this.idmarka = idmarka;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public List<Auto> getAutos() {
		return this.autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}
	
}