package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.BlogCommentDAO;
import com.niit.collbackend.model.BlogComment;

@Repository("BlogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public BlogCommentDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public List<BlogComment> getById(int blogId) {
		String hql = "from BlogComment where blogId =" + "'"+ blogId +"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BlogComment> listBlogComment = (List<BlogComment>) query.list();
		
		
		return listBlogComment;
	}

	public void save(BlogComment blogComment) {
		sessionFactory.getCurrentSession().save(blogComment);

	}

	public void saveOrUpdate(BlogComment blogComment) {
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);

	}

	public BlogComment  delete(int id) {
		BlogComment blogtoDelete = new BlogComment();
		blogtoDelete.setBlogId(id);
		sessionFactory.getCurrentSession().delete(blogtoDelete);
		return blogtoDelete;

	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> list() {
		List<BlogComment> listBlogComment = (List<BlogComment>) sessionFactory.getCurrentSession()
				.createCriteria(BlogComment.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listBlogComment;
	}
	@SuppressWarnings("unchecked")
	public BlogComment getByBId(int id) {
		String hql = "from BlogComment where id=" + "'" + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<BlogComment> listBlogComment = (List<BlogComment>) query.list();

		if (listBlogComment != null && !listBlogComment.isEmpty()) {
			return listBlogComment.get(0);
		}
		return null;
	}
	
}
