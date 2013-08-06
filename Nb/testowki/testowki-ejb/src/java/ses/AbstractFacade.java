/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ses;

import excep.DatabaseEx;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author arti01
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    @Resource
    private SessionContext sctx;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws DatabaseEx {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) throws DatabaseEx {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) throws DatabaseEx{
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> wynik=(List<T>)getEntityManager().createQuery(cq).getResultList();
        for(T t:wynik){
            getEntityManager().refresh(t);
        }
        return wynik;
    }
    
    public List<T> findAllOrderNazwa() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.orderBy(cb.asc(c.get("nazwa")));
        List<T> wynik=(List<T>)getEntityManager().createQuery(cq).getResultList();
        for(T t:wynik){
            getEntityManager().refresh(t);
        }
        return wynik;
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @AroundInvoke
    public Object interceptor(InvocationContext ic) throws Exception {
        Object o = null;
        try {
            o = ic.proceed();
            if (!sctx.getRollbackOnly()) {
                getEntityManager().flush();
            }
        } catch (PersistenceException ex) {
            throw new DatabaseEx(ex.getMessage() + "blad");
        }
        return o;
    }
}
