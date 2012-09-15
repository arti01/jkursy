package org.arti01.sesBean;

import bb.BoksycfgImpRemote;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arti01.entit.Boksycfg;

@Stateless
@LocalBean
public class BoksycfgImp implements BoksycfgImpRemote{
	//Logger logger = Logger.getLogger(StatyczneImp.class);
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
                 //@Override
	public List<Boksycfg> getAll() {
		return em.createQuery("select n from Boksycfg n order by n.nazwa asc").getResultList();
	}

	public Boksycfg find(Boksycfg pz){
		pz=em.find(Boksycfg.class, pz.getIdboksycfg());
		return pz;
	}
	public void update(Boksycfg pz){
		em.merge(pz);
		
	}
	
}
