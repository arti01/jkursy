package org.arti01.obiekty;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "foty")
public class Fota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "foty_id_foty_seq")
	@SequenceGenerator(name = "foty_id_foty_seq", sequenceName = "foty_id_foty_seq")
	@Column(name = "id_foty")
	private Integer id_foty;

	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "opis")
	private String opis;

	@Column(name = "datadodania")
	private Date dataDodania;

	
	@Column(name = "plik")
	private byte[] plik;
	
	@Column(name = "plikmini")
	private byte[] plikMini;
	
	@Column(name="lp")
	private Integer lp;
	

	public Integer getId_foty() {
		return id_foty;
	}

	public void setId_foty(Integer id_foty) {
		this.id_foty = id_foty;
	}

	public String getNazwa() {
		return nazwa;
	}
	//@RequiredStringValidator(message="nazwa nie może być pusta")
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getDataDodania() {
		return dataDodania;
	}
	//@RequiredFieldValidator(message="data nie może być pusta")
	public void setDataDodania(Date dataDodania) {
		this.dataDodania = dataDodania;
	}

	public void setPlik(byte[] plik) {
		this.plik = plik;
	}

	public byte[] getPlik() {
		return plik;
	}

	public byte[] getPlikMini() {
		return plikMini;
	}

	public void setPlikMini(byte[] plikMini) {
		this.plikMini = plikMini;
	}

	public Integer getLp() {
		return lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
	}


}