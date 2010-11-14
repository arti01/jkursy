package org.arti01.baza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Baza implements Serializable {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Baza.class);
	Transaction tx = null;

	public Query przygotuj(String select) {
		Query query = null;
		//logger.info("robimy przygotowanie" + select);
		Session session = HibernateUtil.getInstance().getCurrentSession();
		try {
			// tx = session.beginTransaction();
			query = session.createQuery(select);
		} catch (RuntimeException e2) {
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					logger.info("rolling back transaction", e2);
					tx.rollback();
				} catch (HibernateException e1) {
					logger.info("Error rolling back transaction", e1);
				}
				logger.info("error", e2);
				// throw again the first exception
				throw e2;
			}
			logger.info("error", e2);
		}
		//logger.info("query" + query);
		return query;
	}

	@SuppressWarnings("rawtypes")
	public List select(Query query) {
		//logger.info("tuuuu");
		List  lista = new ArrayList();
		try {
			List obiekty = query.list();
			lista = obiekty;
			//logger.info("commitujemy query");
			// tx.commit();
		} catch (RuntimeException e2) {
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					logger.info("rolling back transaction", e2);
					tx.rollback();
				} catch (HibernateException e1) {
					logger.info("Error rolling back transaction", e1);
				}
				logger.info("error", e2);
				// throw again the first exception
				throw e2;
			}
			else logger.info("error2", e2);
		}
		return lista;
	}

	public void update(Query query) {
		//logger.info("tuuuu");
		try {
			query.executeUpdate();
			//logger.info("commitujemy query");
			// tx.commit();
		} catch (RuntimeException e2) {
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					logger.info("rolling back transaction", e2);
					tx.rollback();
				} catch (HibernateException e1) {
					logger.info("Error rolling back transaction", e1);
				}
				logger.info("error", e2);
				// throw again the first exception
				throw e2;
			}
			else logger.info("error2", e2);
		}
	}

	public void dodaj(Object obiekt)  {
		// Transaction tx = null;
		Session session;
		session = HibernateUtil.getInstance().getCurrentSession();
		//logger.info(obiekt.toString());
		try {
			session.save(obiekt);
			session.flush();
		} catch (Exception e) {
			logger.info("nieudane dodanie");
			session.flush();
			session=null;
			try {
				throw new Exception("sss");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.info("nieudane dodanie", e1);;
			} 
		}
		
	}

	public void update(Object obiekt) throws Exception{
		Session session = HibernateUtil.getInstance().getCurrentSession();
		try {
			session.update(obiekt);
			//session.saveOrUpdate(obiekt);
			session.flush();
		} catch (Exception e) {
			logger.info("nieudana zmiana", e);
			session.flush();
			session=null;
			throw new Exception("sss");
		}
	}

	public void usun(Object obiekt) throws Exception {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		try {
			session.delete(obiekt);
			session.flush();
		} catch (HibernateException e) {
			throw new Exception("sss");
		}
		//session.flush();
	}
	

	@SuppressWarnings("rawtypes")
	public List getAll(String select) {
		List<Object> lista = new ArrayList<Object>();
		// Transaction tx = null;
		// logger.info("robimy");
		Session session = HibernateUtil.getInstance().getCurrentSession();
		// logger.info("robimy1");
		try {
			// tx = session.beginTransaction();
			// logger.info("robimy2");
			//session.createQuery(null).l
			@SuppressWarnings("unchecked")
			List<Object>obiekty = session.createQuery(select).list();
			lista = obiekty;
			// logger.info("robimy3");
			// tx.commit();
		} catch (RuntimeException e2) {
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					logger.info("rolling back transaction", e2);
					tx.rollback();
				} catch (HibernateException e1) {
					logger.info("Error rolling back transaction", e1);
				}
				logger.info("dupa", e2);
				// throw again the first exception
				// throw e2;
			}

		}
		return lista;
	}

}
