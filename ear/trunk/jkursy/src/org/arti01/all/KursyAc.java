package org.arti01.all;

import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.All;
import org.arti01.obiekty.Kursy;
import org.arti01.obiekty.KursyImp;


public class KursyAc extends All {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KursyAc.class);
	private List<Kursy>kursy;
	private Kursy kurs;
	
	public String list() throws Exception{
		//logger.info("jestemmmm");
		kursy=new KursyImp().findAll();
		return "list";
	}
	
	public String detale() throws Exception{
		//logger.info("jestemmmm");
		kurs=new KursyImp().load(kurs);
		return "detale";
	}

	public List<Kursy> getKursy() {
		return kursy;
	}

	public void setKursy(List<Kursy> kursyAll) {
		this.kursy = kursyAll;
	}

	public Kursy getKurs() {
		return kurs;
	}

	public void setKurs(Kursy kurs) {
		this.kurs = kurs;
	}
	
}