package com.wc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wc.dao.IUserDAO;
import com.wc.pojo.User;

/**
 * @author pavankumarv
 *
 */
@Repository("userDAO")
public class UserDAO extends BaseDAO<User, Serializable> implements IUserDAO{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> fetchUsers(){
		return getSessionFactory().getCurrentSession().createQuery("from User").list();
	}
	
	@Override
    public void saveUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
    }
	
}