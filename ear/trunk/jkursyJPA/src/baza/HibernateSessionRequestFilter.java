package baza;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

public class HibernateSessionRequestFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(HibernateSessionRequestFilter.class);

	private SessionFactory sf;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			log.info("Starting a database transaction");
			sf.getCurrentSession().beginTransaction();

			// Call the next filter (continue request processing)
			chain.doFilter(request, response);

			// Commit and cleanup
			log.info("Committing the database transaction");
			sf.getCurrentSession().getTransaction().commit();

		} catch (StaleObjectStateException staleEx) {
			log
					.info("This interceptor does not implement optimistic concurrency control!");
			log
					.info("Your application will not work until you add compensation actions!");
			// Rollback, close everything, possibly compensate for any permanent
			// changes
			// during the conversation, and finally restart business
			// conversation. Maybe
			// give the user of the application a chance to merge some of his
			// work with
			// fresh data... what you do here depends on your applications
			// design.
			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
				if (sf.getCurrentSession().getTransaction().isActive()) {
					log
							.info("Trying to rollback database transaction after exception");
					sf.getCurrentSession().getTransaction().rollback();
				}
			// Let others handle it... maybe another interceptor for exceptions?
	
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Initializing filter...");
		log
				.info("Obtaining SessionFactory from static HibernateUtil singleton");
		sf = HibernateUtil.getSessionFactory();

	}

	public void destroy() {
	}

}
