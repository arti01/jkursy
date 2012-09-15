package org.arti01.sesBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.arti01.entit.Fotykursantkoment;


@Stateless
@LocalBean
public class FotykursantkomentImp {
	@PersistenceContext
	EntityManager em;
	String errorText="";

	
	
	public boolean insert(Fotykursantkoment fkk){
			em.persist(fkk);
			return false;
		}
	}
