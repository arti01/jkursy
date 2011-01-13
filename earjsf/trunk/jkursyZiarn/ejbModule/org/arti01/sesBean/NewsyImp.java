package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Newsy;

@Stateless
@LocalBean
public class NewsyImp {
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	Newsy news;
	
	@SuppressWarnings("unchecked")
	public List<Newsy> getAll() {
		return em.createQuery("select n from Newsy n order by n.datadodania desc").getResultList();
	}

	public Newsy find(Newsy news){
		news=em.find(Newsy.class, news.getIdnewsy());
		return news;
	}
	public void update(Newsy news){
		em.merge(news);
	}
	
	public void insert(Newsy news) {
		em.persist(news);
	}
	
	public void delete(Newsy news) {
		news=em.find(Newsy.class, news.getIdnewsy());
		em.remove(news);
		em.flush();
	}
	
	public Newsy getNews() {
		return news;
	}

	public void setNews(Newsy news) {
		this.news = news;
	}
	
}
