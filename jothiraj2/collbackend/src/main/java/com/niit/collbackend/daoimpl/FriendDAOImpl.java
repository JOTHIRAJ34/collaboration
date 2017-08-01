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
	
	public void save(Friend friend) {
		sessionFactory.getCurrentSession().save(friend);

	}
	
	public Friend saveOrUpdate(Friend friend) {
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return friend;

	}
	
	public Friend getByFriendId(int id) {
		String oracle="from Friend where id="+"'"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(oracle);
		@SuppressWarnings("unchecked")
		List<Friend> friendlist = (List<Friend>) query.list();
		if (friendlist != null && !friendlist.isEmpty()) {
			return friendlist.get(0);
		}
		return null;
	}

	
	public void delete(int fid) {
		Friend friendtoDelete = new Friend();
		friendtoDelete.setFid(fid);
		sessionFactory.getCurrentSession().delete(friendtoDelete);
	}
	
	
	@Transactional
	public List<Friend> getByFriendName(String name) {
		String hql = "from Friend where friendName =" + "'" + name + "' and status = " + "'P'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
	}
	
	
	@Transactional
	public List<Friend> getByFriendAccepted(String friendName){
		String hql = "from Friend where friendName =" + "'" + friendName + "' and status = " + "'A'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
	}
	
	
	@Transactional
	public List<Friend> list(int friendId) {
		String hql = "from Friend where friendId =" + "'" + friendId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();

		return listFriend;
	}
	@Transactional
	public List<Friend> getByFriendAccepted1(String name) {
		String hql = "from Friend where userName =" + "'" + name + "' and status = " + "'A'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
	}
	
}
