package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}
	
	
	@Transactional	
	public User getById(int userId){
		String hql = "from User where userId =" + "'"+ userId +"'";
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
	
	
	
	@Transactional
	public User getByEmail(String email_id) {
		User userByEmail = (User) sessionFactory.getCurrentSession().get(User.class, email_id);

		return userByEmail;
	}
	
	@Transactional	
	public User delete(int userId) {
		User usertoDelete=new User();
		usertoDelete.setUserId(userId);
		sessionFactory.getCurrentSession().delete(usertoDelete);
		return usertoDelete;
		}
	
	@Transactional
	public User login(User user) {

		System.out.println(user.getEmail_id());
		System.out.println(user.getPassword());
		String hql = "from User where email_id=" + "'" + user.getEmail_id() + "'and password = " + "'" + user.getPassword()
				+ "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);

		}
		return null;
	}

	
	public List<String> getOnlineUsers() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("select username from User where online=1");
		List<String> onlineUsers = query.list();
		session.close();
		return onlineUsers;
	}

	

	
}
