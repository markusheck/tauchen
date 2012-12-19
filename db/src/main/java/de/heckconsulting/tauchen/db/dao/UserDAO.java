package de.heckconsulting.tauchen.db.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Repository;

import de.heckconsulting.tauchen.db.JpaDAO;
import de.heckconsulting.tauchen.db.dbo.UserDBO;

@Repository
public class UserDAO extends JpaDAO<Long, UserDBO> {

	private static final long serialVersionUID = 4846563870487675751L;

	@PersistenceUnit(unitName="PFT")
	EntityManagerFactory entityManagerFactory;
	
	@PostConstruct
	public void init(){
		setEntityManagerFactory(entityManagerFactory);
	}
	
	public List<UserDBO> findByUsernameAndPassword( final String username, final String password ) {
		Object res = getJpaTemplate().execute(new JpaCallback<Object>() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("SELECT user FROM UserDBO user WHERE userName = '" + username + "' and password = '" + password + "'" ); 
                return q.getResultList(); 
            } 
        }); 
        return (List<UserDBO>)res; 
	}

	public List<UserDBO> findByUserName(final String userName) {
		Object res = getJpaTemplate().execute(new JpaCallback<Object>() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("SELECT user FROM UserDBO user WHERE userName = '" + userName + "'"); 
                return q.getResultList(); 
            } 
        }); 
        return (List<UserDBO>)res; 
	}

}
