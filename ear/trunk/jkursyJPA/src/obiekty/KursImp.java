package obiekty;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.hibernate.Query;

public class KursImp implements KursInt  {
	Logger logger = Logger.getLogger(KursImp.class);

	@SuppressWarnings("unchecked")
	public List<Kurs> findAll() {
		return new Baza().getAll("from Kurs order by dataod desc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Kurs> findNiezakończone() {
		Baza baza = new Baza();
		Query query = baza.przygotuj("from Kurs where datado>=:datado order by dataod desc");
		query.setDate("datado", new Date());
		logger.info("ile kursów"+baza.select(query).size());
		return  baza.select(query);
	}
	
	public Kurs load(Kurs kurs) {
		Kurs kursNew;
		if (kurs != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Kurs where idkursy=:idkursy");
			//logger.info(user.getUsername());
			query.setInteger("idkursy", kurs.getIdkursy());
			kursNew = (Kurs) baza.select(query).iterator().next();
		} else kursNew = new Kurs();
		return kursNew;
	}
	
	public void insert(Kurs kurs) {
		new Baza().dodaj(kurs);
	}
	
	public void remove(Kurs kurs) throws Exception{
		new Baza().usun(kurs);
	}
	
	public void update(Kurs kurs) throws Exception {
		logger.info(kurs.getNazwa());
	new Baza().update(kurs);
}
	
}
