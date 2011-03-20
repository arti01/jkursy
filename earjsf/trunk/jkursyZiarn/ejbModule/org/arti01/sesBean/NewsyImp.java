package org.arti01.sesBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		return em.createQuery("select n from Newsy n order by n.lp").getResultList();
	}

	public Newsy find(Newsy news){
		news=em.find(Newsy.class, news.getIdnewsy());
		return news;
	}
	public void update(Newsy news){
		em.merge(news);
	}
	
	public void insert(Newsy news) {
		news.setLp(getAll().size()+1);
		em.persist(news);
	}
	
	@SuppressWarnings("unchecked")
	public void delete(Newsy news) {
		news=em.find(Newsy.class, news.getIdnewsy());
		Integer lp=news.getLp();
		em.remove(news);
		em.flush();
		Query qt=em.createQuery("select n from Newsy n where n.lp>:lp order by n.lp");
		qt.setParameter("lp", lp);
		for(Newsy n: (List<Newsy>)qt.getResultList()){
			n.setLp(n.getLp()-1);
			em.merge(n);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void update(Newsy news, Integer newLp) {
		Integer oldLp=new Integer(news.getLp());
		news.setLp(-1);
		em.merge(news);
		//System.out.println(lekcja+"statOld");
		//System.out.println(oldLp+"new"+newLp);
		if (newLp < oldLp) {//idziemy w góre
			//System.out.println("upupup");
			Query select=em.createQuery("select n from Newsy n where n.lp<:oldLp and n.lp>=:newLp order by n.lp desc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			List<Newsy> sl=select.getResultList();
			for (Newsy s:sl){
				//System.out.println(lekcja.getLp());
				//System.out.println(s.getTytul());
				//System.out.println(s.getLp()+1);
				s.setLp(s.getLp()+1);
				em.merge(s);
				em.flush();
			}
			news.setLp(newLp);
			em.merge(news);
		} else if (newLp > oldLp) {//idziemy w dół
			Query select=em.createQuery("select n from Newsy n where n.lp>:oldLp and n.lp<=:newLp order by n.lp asc");
			select.setParameter("oldLp", oldLp);
			select.setParameter("newLp", newLp);
			List<Newsy> sl=select.getResultList();
			for (Newsy s:sl){
				s.setLp(s.getLp()-1);
				em.merge(s);
				em.flush();
			}
			news.setLp(newLp);
			em.merge(news);
		}else update(news);
	}
	
	public Newsy getNews() {
		return news;
	}

	public void setNews(Newsy news) {
		this.news = news;
	}
	
}
