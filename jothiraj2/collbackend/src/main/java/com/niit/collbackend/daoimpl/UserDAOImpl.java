package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.UserDAO;
import com.niit.collbackend.model.User;

@Repository("UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {		
		List<User> listUser = (List<User>)
		sessionFactory.getCurrentSession().createCriteria(User.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
		 
	}

	
	@Transactional	
	public User create(User users) {
		sessionFactory.getCurrentSession().save(users);
		return users;
	}
	@Transactional	
	public User delete(int userId) {
		User usertoDelete=new User();
		usertoDelete.setUserId(userId);
		sessionFactory.getCurrentSession().delete(usertoDelete);
		return usertoDelete;
		}
	@Transactional	
	public User update(User users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return users;
	}
	@Transactional	
	public User get(int id) {
		String hql = "from User where userId =" + "'"+ id +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;
}

	@Transactional
	public List<User> getbyUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from User where user_name =" + "'"+ username +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		
		return listUser;
	}
}
