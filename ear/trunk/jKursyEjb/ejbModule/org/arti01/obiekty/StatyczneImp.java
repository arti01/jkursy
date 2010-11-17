package org.arti01.obiekty;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.arti01.baza.Baza;

public class StatyczneImp {
	Logger logger = Logger.getLogger(StatyczneImp.class);

	@SuppressWarnings("unchecked")
	public List<Statyczne> findAll() {
		return new Baza().getAll("from Statyczne order by lp");
	}

	public void insert(Statyczne statyczne) {
		new Baza().dodaj(statyczne);
	}

	@SuppressWarnings("unchecked")
	public void update(Statyczne statyczne, Integer oldLp) throws Exception {
		Baza baza = new Baza();
		logger.info(statyczne.getTytul()+"-------------"+statyczne.getLp());
		if (oldLp > statyczne.getLp()) {//idziemy w góre
			baza.usun(statyczne);
			Query select= baza
			.przygotuj("from Statyczne where lp<:oldLp and lp>=:newLp order by lp desc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=new Baza().select(select);
			for (Statyczne s:sl){
				Query query = baza
				.przygotuj("update Statyczne s set lp=lp+1 where s=:s");
				query.setParameter("s", s);
				//query.executeUpdate();
				baza.update(query);
			}
			//logger.info(query.getQueryString()+"--------------------------------------++++++++++");
			insert(statyczne);
		} else if (oldLp < statyczne.getLp()) {//idziemy w dół
			baza.usun(statyczne);
			Query select= baza
			.przygotuj("from Statyczne where lp>:oldLp and lp<=:newLp order by lp asc");
			select.setParameter("newLp", statyczne.getLp());
			select.setParameter("oldLp", oldLp);
			List<Statyczne> sl=new Baza().select(select);
			for (Statyczne s:sl){
				Query query = baza
				.przygotuj("update Statyczne s set lp=lp-1 where s=:s");
				query.setParameter("s", s);
				logger.info(s.getLp()+"--------------------------------------++++++++++");
				//query.executeUpdate();
				baza.update(query);
			}
			insert(statyczne);
		}else baza.update(statyczne);
	}

	@SuppressWarnings("unchecked")
	public void remove(Statyczne statyczne) throws Exception {
		Baza baza = new Baza();
		statyczne=this.find(statyczne);
		baza.usun(statyczne);
		Query select= baza
		.przygotuj("from Statyczne where lp>:lp order by lp asc");
		select.setParameter("lp", statyczne.getLp());
		List<Statyczne> sl=new Baza().select(select);
		for (Statyczne s:sl){
			Query query = baza
			.przygotuj("update Statyczne s set lp=lp-1 where s=:s");
			query.setParameter("s", s);
			baza.update(query);
		}
		logger.info(statyczne.getLp()+"rrrrrrrrrrrrrrr");
	}

	public Statyczne find(Statyczne statyczne) {
		Statyczne statyczneNew;
		if (statyczne != null) {
			Baza baza = new Baza();
			Query query = baza.przygotuj("from Statyczne s where s=:statyczne");
			query.setParameter("statyczne", statyczne);
			statyczneNew = (Statyczne) baza.select(query).iterator().next();
		} else
			statyczneNew = new Statyczne();
		return statyczneNew;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> lpAll() {
		return (List<Integer>) new Baza()
				.getAll("select lp from Statyczne order by lp");
	}
}
