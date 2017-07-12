package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.FriendDAO;
import com.niit.collbackend.model.Friend;

@Repository("FriendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Friend> list() {
		List<Friend> friendList = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return friendList;
	}

	public List<Friend> list(int friendId) {

		return null;
	}

	public void save(Friend friend) {
		sessionFactory.getCurrentSession().save(friend);

	}

	public Friend getByFriendId(int friendId) {
		String oracle="from Friend where friendId="+"'"+friendId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(oracle);
		@SuppressWarnings("unchecked")
		List<Friend> friendlist = (List<Friend>) query.list();
		if (friendlist != null && !friendlist.isEmpty()) {
			return friendlist.get(0);
		}
		return null;
	}

	public List<Friend> getByFriendName(String name) {

		return null;
	}

	public List<Friend> getByFriendAccepted(String name) {

		return null;
	}

	public void delete(int friendId) {
		Friend friendtoDelete = new Friend();
		friendtoDelete.setFriendId(friendId);
		sessionFactory.getCurrentSession().delete(friendtoDelete);
	}

	public void saveOrUpdate(Friend friend) {
		sessionFactory.getCurrentSession().saveOrUpdate(friend);

	}
}
