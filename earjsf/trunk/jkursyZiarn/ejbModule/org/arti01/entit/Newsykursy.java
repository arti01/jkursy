package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Newsykursy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idnewsykursy;

    @Temporal( TemporalType.DATE)
	private Date datadodania;
    
    @Transient
    private String datadodaniaS;

	private String tresc;
	
	@Transient
	private String skrot;

	@NotEmpty
	private String tytul;
	
	@ManyToOne
	@JoinColumn(name="idkursy")
	private Kursy kursy;

    public Newsykursy() {
    }

	public Integer getIdnewsykursy() {
		return this.idnewsykursy;
	}

	public void setIdnewsykursy(Integer idnewsykursy) {
		this.idnewsykursy = idnewsykursy;
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

	public Kursy getKursy() {
		return kursy;
	}

	public void setKursy(Kursy kursy) {
		this.kursy = kursy;
	}

	public String getDatadodaniaS() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		datadodaniaS=sdf.format(datadodania);
		return datadodaniaS;
	}

	public void setDatadodaniaS(String datadodaniaS) {
		this.datadodaniaS = datadodaniaS;
	}

	public String getSkrot() {
		int maxDlugosc=300;
		if(tresc.length()<maxDlugosc) skrot=tresc;
		else {
			skrot=tresc.substring(0, maxDlugosc);
		}
		skrot=Jsoup.parse(skrot).text();
		if(skrot.lastIndexOf(" ")!=-1)skrot=skrot.substring(0, skrot.lastIndexOf(" "));
		return skrot;
	}

	public void setSkrot(String skrot) {
		this.skrot = skrot;
	}

}