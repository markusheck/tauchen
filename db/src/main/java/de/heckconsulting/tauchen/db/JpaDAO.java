package de.heckconsulting.tauchen.db;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

public abstract class JpaDAO<K, E> extends JpaDaoSupport implements Serializable  { 
    
	private static final long serialVersionUID = -9095987320471098137L;
	protected Class<E> entityClass; 

    @SuppressWarnings("unchecked") 
    public JpaDAO() { 
        try {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass() 
			        .getGenericSuperclass(); 
			this.entityClass = (Class<E>) genericSuperclass 
			        .getActualTypeArguments()[1];
		} catch (Exception e) {
			this.entityClass = (Class<E>) getClass();
		} 
        
        
    } 

   
    public E persist(E entity) { 
    	entity = getJpaTemplate().merge(entity);
        getJpaTemplate().persist(entity);
        return entity;
    } 

   
    public void remove(E entity) { 
        getJpaTemplate().remove(entity); 
    } 
    
    public E merge(E entity) { 
        return getJpaTemplate().merge(entity); 
    } 
    
    public void refresh(E entity) { 
        getJpaTemplate().refresh(entity); 
    } 

    public E findById(K id) { 
        return getJpaTemplate().find(entityClass, id); 
    } 
    
    public List<E> find(String query) { 
        return getJpaTemplate().find(query); 
    } 
    
    public E flush(E entity) { 
        getJpaTemplate().flush(); 
        return entity; 
    } 
    
    
    public void flush() { 
        getJpaTemplate().flush(); 
    } 
    
    @SuppressWarnings("unchecked") 
    public List<E> findAll() { 
        Object res = getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("SELECT h FROM " + 
                        entityClass.getName() + " h"); 
                return q.getResultList(); 
            } 
            
        }); 
        
        return (List<E>) res; 
    } 

    @SuppressWarnings("unchecked") 
    public Integer removeAll() { 
        return (Integer) getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("DELETE FROM " + 
                        entityClass.getName() + " h"); 
                return q.executeUpdate(); 
            } 
            
        }); 
    }
    
    @SuppressWarnings("unchecked") 
    public List<E> findAll(final int first, final int count) { 
        Object res = getJpaTemplate().execute(new JpaCallback() { 
            public Object doInJpa(EntityManager em) throws PersistenceException { 
            	 Query q = em.createQuery("SELECT h FROM " + 
                        entityClass.getName() + " h"); 
                q.setFirstResult(first);
                q.setMaxResults(count);
                return q.getResultList(); 
            } 
        }); 
        return (List<E>) res; 
    } 
    
    
    @SuppressWarnings("unchecked") 
    public Long countAll() { 
        Object res = getJpaTemplate().execute(new JpaCallback() { 
            public Object doInJpa(EntityManager em) throws PersistenceException { 
            	Query q = em.createQuery("SELECT count(*) FROM " + 
                        entityClass.getName() + " h"); 
                return q.getSingleResult();
            } 
        }); 
        return (Long)res; 
    }

	public E getNew() {
		
		try {
			return entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	} 
   
    
}
