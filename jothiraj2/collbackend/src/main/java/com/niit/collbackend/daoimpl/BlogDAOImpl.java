package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.BlogDAO;
import com.niit.collbackend.model.Blog;

@Repository("BlogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Blog> list()
	{
		List<Blog> blogList = (List<Blog>) sessionFactory.getCurrentSession().createCriteria(Blog.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return blogList;
	}
	
	@SuppressWarnings("unchecked")
	public Blog getByBlogId(int blogId)
	{
		String hql="from Blog where blogId="+"'"+blogId+ "'";
		org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog>blogList=(List<Blog>)query.list();
		if(blogList!=null && !blogList.isEmpty())
		{
			return blogList.get(0);
		}
		return null;
	}
	public List<Blog> getAcceptedBlog() {
		String hql = "from Blog where status="+"'A'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> blogList = (List<Blog>) query.list();

		return blogList;
	}
	public List<Blog> getNotAcceptedBlog() {
		String hql = "from Blog where status="+"'NA'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> blogList = (List<Blog>) query.list();

		return blogList;
	}
	public Blog getByuId(int user_id) {
		String hql = "from Blog where user_id=" + "'" + user_id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> blogList = (List<Blog>) query.list();
		if (blogList != null && !blogList.isEmpty()) {
			return blogList.get(0);
		}

		return null;
	}
	public Blog getbyemailId(String email_Id) {

		return null;
	}

	public void save(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);

	}

	public Blog delete(int blogId) {
		Blog blogtoDelete = new Blog();
		blogtoDelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blogtoDelete);
		return blogtoDelete;

	}

	public void saveOrUpdate(Blog blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);

	}

	@SuppressWarnings("unchecked")
	public Blog getByBlogName(String blog_name) {
		String hql = "from Blog where blog_name=" + "'" + blog_name + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> blogList = (List<Blog>) query.list();
		if (blogList != null && !blogList.isEmpty()) {
			return blogList.get(0);
		}
		return null;
	}

}
