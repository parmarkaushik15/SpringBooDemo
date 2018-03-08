package com.esense.topclass.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.esense.topclass.dbmodel.Content;
import com.esense.topclass.dbmodel.RandomCity;
import com.esense.topclass.dbmodel.User;

@Service
public class GenericServiceImpl implements GenericService {
    
    @PersistenceContext	
	private EntityManager entityManager;
    
    
    
   public GenericServiceImpl()
	{
	       
	}
    
    @Override
    public User findByUsername(String username) {
    	String hql = "FROM User as atcl WHERE atcl.username = ?";
		List<User> resultList = entityManager.createQuery(hql).setParameter(1, username).getResultList();
		
		User findUser = null;
		for (User user : resultList)
		{
			findUser = user;
		}
    	return findUser;
    }

    @Override
    public List<User> findAllUsers() {
        String hql = "FROM User as atcl ORDER BY atcl.id";
	    Session session = entityManager.unwrap(Session.class);
	    SessionFactory sessionFactory = session.getSessionFactory();
	    System.out.println(sessionFactory.getStatistics());
		return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<RandomCity> findAllRandomCities() {
    	String hql1 = "FROM RandomCity as atcl";
        return (List<RandomCity>) entityManager.createQuery(hql1).getResultList();
    }

	@Override
	public List<Content> findVersionUpdate(String versionId)
	{
		String hql = "FROM Content as atcl WHERE atcl.versionNo = ?";
		List<Content> resultList = entityManager.createQuery(hql).setParameter(1, versionId).getResultList();
		return resultList;
	}

	@Override
	public List<Content> findAllVersionUpdate()
	{
		String hql = "FROM Content as atcl GROUP BY atcl.versionNo";
		List<Content> resultList = entityManager.createQuery(hql).getResultList();
		return resultList;
	}
}
