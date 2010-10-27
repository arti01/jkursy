package org.arti01.obiekty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_roles")
public class Rola {
	public static final String ADMIN ="admin" ;
	public static final String WYKLADOWCA ="wykladowca" ;
	public static final String KURSANT ="kursant" ;
	@Id
	@Column(name="rola")
	private String rola;
	
	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	@Column(name="opis")
	private String opis;

	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}

}