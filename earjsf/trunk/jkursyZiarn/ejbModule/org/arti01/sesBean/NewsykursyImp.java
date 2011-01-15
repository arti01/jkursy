package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.arti01.entit.Kursy;
import org.arti01.entit.Newsykursy;

@Stateless
@LocalBean
public class NewsykursyImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	Newsykursy newsykursy;
	
	@SuppressWarnings("unchecked")
	public List<Newsykursy> getAll(Kursy k) {
		Query query=em.createQuery("select n from Newsykursy n where n.kursy=:kursy order by n.datadodania desc");
		query.setParameter("kursy", k);
		return query.getResultList();
	}

	public Newsykursy find(Newsykursy newsykursy){
		newsykursy=em.find(Newsykursy.class, newsykursy.getIdnewsykursy());
		return newsykursy;
	}
	public void update(Newsykursy newsykursy){
		em.merge(newsykursy);
	}
	
	public void insert(Newsykursy news) {
		em.persist(news);
	}
	
	public void delete(Newsykursy newsykursy) {
		newsykursy=em.find(Newsykursy.class, newsykursy.getIdnewsykursy());
		em.remove(newsykursy);
		em.flush();
	}

	public Newsykursy getNewsykursy() {
		return newsykursy;
	}

	public void setNewsykursykursy(Newsykursy newsykursy) {
		this.newsykursy = newsykursy;
	}

	
}
