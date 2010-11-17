package org.arti01.obiekty;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;

public class KursyImp {
	Logger logger = Logger.getLogger(KursyImp.class);

	@SuppressWarnings("unchecked")
	public List<Kursy> findAll() {
		return new Baza().getAll("from Kursy order by dataod desc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Kursy> findNiezakończone() {
		Baza baza = new Baza();
		Query query = baza.przygotuj("from Kursy where datado>=:datado order by dataod desc");
		query.setParameter("datado", new Date());
		logger.info("ile kursów"+baza.select(query).size());
		return  baza.select(query);
	}
	
	public Kursy load(Kursy kurs) {
		Kursy kursNew;
		if (kurs != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Kursy where idkursy=:idkursy");
			//logger.info(user.getUsername());
			query.setParameter("idkursy", kurs.getIdkursy());
			kursNew = (Kursy) baza.select(query).iterator().next();
		} else kursNew = new Kursy();
		return kursNew;
	}
	
	public void insert(Kursy kurs) {
		new Baza().dodaj(kurs);
	}
	
	public void remove(Kursy kurs) throws Exception{
		new Baza().usun(kurs);
	}
	
	public void update(Kursy kurs) throws Exception {
		logger.info(kurs.getNazwa());
	new Baza().update(kurs);
}
	
}
