package org.arti01.entit;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


/**
CREATE TABLE newsy
(
  idnewsy serial NOT NULL,
  tytul character varying(255) NOT NULL,
  tresc text,
  datadodania date NOT NULL DEFAULT now(),
  CONSTRAINT newsy_pkey PRIMARY KEY (idnewsy)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE newsy OWNER TO slow;
 */
@Entity
public class Newsy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idnewsy;

    @Temporal( TemporalType.DATE)
	private Date datadodania;

	private String tresc;

	@NotEmpty
	private String tytul;

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

}