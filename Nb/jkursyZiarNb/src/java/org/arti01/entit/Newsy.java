package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
//import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Newsy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idnewsy;

    @Temporal( TemporalType.DATE)
	private Date datadodania;

    @Transient
    private String datadodaniaS;
    
	private String tresc;

	@NotEmpty
	private String tytul;
	
	private byte[] fota;
	
	//@Transient
	@NotNull
	@NotEmpty
	private String skrot;

	@Column(nullable=false)
	@NotNull
	private Integer lp;
	
    public Newsy() {
    }

	public Integer getIdnewsy() {
		return this.idnewsy;
	}

	public void setIdnewsy(Integer idnewsy) {
		this.idnewsy = idnewsy;
	}

	public Date getDatadodania() {
		return this.datadodania;
	}

	public void setDatadodania(Date datadodania) {
		this.datadodania = datadodania;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getDatadodaniaS() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		datadodaniaS=sdf.format(datadodania);
		return datadodaniaS;
	}

	public void setDatadodaniaS(String datadodaniaS) {
		this.datadodaniaS = datadodaniaS;
	}

	/*public String getSkrot() {
		int maxDlugosc=300;
		if(tresc.length()<maxDlugosc) skrot=tresc;
		else {
			skrot=tresc.substring(0, maxDlugosc);
		}
		skrot=Jsoup.parse(skrot).text();
		//skrot=skrot.substring(0, skrot.lastIndexOf(" "));
		//System.out.println(skrot);
		if(skrot.lastIndexOf(" ")!=-1)skrot=skrot.substring(0, skrot.lastIndexOf(" "));
		return skrot;
	}*/
	
	public String getSkrot() {
		return skrot;
	}

	public void setSkrot(String skrot) {
		this.skrot = skrot;
	}

	public byte[] getFota() {
		return fota;
	}

	public void setFota(byte[] fota) {
		this.fota = fota;
	}

	public Integer getLp() {
		return lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
	}

}