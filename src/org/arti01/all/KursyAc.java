package org.arti01.all;

import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.Akcja;
import org.arti01.obiekty.Kurs;
import org.arti01.obiekty.KursImp;


public class KursyAc extends Akcja {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private List<Kurs>kursy;
	private Kurs kurs;
	
	public String list() throws Exception{
		//logger.info("jestemmmm");
		kursy=new KursImp().findAll();
		return "list";
	}
	
	public String detale() throws Exception{
		//logger.info("jestemmmm");
		kurs=new KursImp().load(kurs);
		return "detale";
	}

	public List<Kurs> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kurs> kursyAll) {
		this.kursy = kursyAll;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}
	
}