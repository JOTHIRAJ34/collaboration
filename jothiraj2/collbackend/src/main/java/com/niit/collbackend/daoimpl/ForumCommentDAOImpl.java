package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.ForumCommentDAO;
import com.niit.collbackend.model.ForumComment;

@Repository("ForumComment")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public ForumCommentDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	public List<ForumComment> list() {
		List<ForumComment> forumCommentList = (List<ForumComment>) sessionFactory.getCurrentSession().createCriteria(ForumComment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return forumCommentList;
	}

	public void save(ForumComment forumComment) {
		sessionFactory.getCurrentSession().save(forumComment);
		
	}

	public void saveOrUpdate(ForumComment forumComment) {
		sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
		
	}

	public void delete(ForumComment forumComment) {
		sessionFactory.getCurrentSession().delete(forumComment);		
	}

	public List<ForumComment> getForumComments(int forumId) {
		String hql="from ForumComment where id="+"'"+forumId+"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<ForumComment> forumCommentList = (List<ForumComment>) query.list();
		
			return forumCommentList;
		
	}

	public ForumComment getComment(int Id) {
		String hql="from ForumComment where id="+"'"+Id+"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<ForumComment> forumCommentList = (List<ForumComment>) query.list();
		if (forumCommentList != null && !forumCommentList.isEmpty()) {
			return forumCommentList.get(0);
		}

		return null;
		
	}
}
