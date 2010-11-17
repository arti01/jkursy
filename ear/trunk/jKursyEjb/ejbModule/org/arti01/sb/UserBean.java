package org.arti01.sb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arti01.obiekty.User;


/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserBeanLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
    public UserBean() {
    	
        // TODO Auto-generated constructor stub
    }
    public String test(String test){
    	em.find(User.class, "aa");
    	return test+"ssssssssssssssss"+em.find(User.class, "aa").getImieNazwisko();
    }

}
