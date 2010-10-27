package org.arti01.obiekty;

import javax.persistence.*;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;



@Entity
@Table(name = "statyczne")
public class Statyczne{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "statyczne_id_statyczne_seq")
	@SequenceGenerator(name = "statyczne_id_statyczne_seq", sequenceName = "statyczne_id_statyczne_seq")
	@Column(name="id_statyczne")
	private int idStatyczne;
	
	private String tytul;
	
	private String opis;
	
	private String tresc;
	
	private int lp;
	
	
	public String getOpis() {
		return opis;
	}
	
	@RequiredStringValidator(message="proszę wypełnić opis")
	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getLp() {
		return lp;
	}

	public void setLp(int lp) {
		this.lp = lp;
	}	

	public String getTresc() {
		return tresc;
	}

	@RequiredStringValidator(message="proszę wypełnić treść")
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public int getIdStatyczne() {
		return idStatyczne;
	}

	public void setIdStatyczne(int idStatyczne) {
		this.idStatyczne = idStatyczne;
	}

	public String getTytul() {
		return tytul;
	}
	
	@RequiredStringValidator(message="proszę wypełnić tytuł")
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	}