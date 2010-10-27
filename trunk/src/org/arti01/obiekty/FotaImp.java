package org.arti01.obiekty;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;
import org.hibernate.Query;

public class FotaImp implements FotaInt {
	Logger logger = Logger.getLogger(FotaImp.class);

	@SuppressWarnings("unchecked")
	public List<Fota> findAll() {
		return new Baza().getAll("from Fota order by id_foty desc");
	}

	public boolean save(Fota fota) throws Exception {
		fota.setDataDodania(Calendar.getInstance().getTime());
		if (fota.getId_foty() == null) {
			//obliczanie max lp dla sesji
			Baza baza =new Baza();
			Query query=baza.przygotuj("select max(lp)+1 from Fota where idobiekty=:idobiekty");
			logger.info(baza.select(query).iterator().next());
			if(baza.select(query).iterator().next()==null){
				fota.setLp(1);
			}else{
			fota.setLp((Integer)baza.select(query).iterator().next());
			}
			// new
			new Baza().dodaj(fota);
			return true;
		} else {
			// update
			new Baza().update(fota);
			return false;
		}
	}

	public void remove(Fota fota) throws Exception {
		if (fota.getId_foty() != null) {
			new Baza().usun(fota);
		}
	}

	public Fota find(Fota fota) {
		Fota fotaNew;
		if (fota.getId_foty() != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Fota where id_foty=:id_foty");
			query.setInteger("id_foty", fota.getId_foty());
			fotaNew = (Fota) baza.select(query).iterator().next();
			// fotaNew=(Fota) new Baza().pobierz("from Fota where id_foty=" +
			// fota.getId_foty()).iterator().next();
		} else
			fotaNew = new Fota();
		return fotaNew;


	}

}
