package com.niit.collbackend.dao;

import java.util.List;

import com.niit.collbackend.model.Blog;

public interface BlogDAO {
	
	public List<Blog> list();

	public Blog getByBlogId(int blogId);

	public List<Blog> getAcceptedBlog();

	public List<Blog> getNotAcceptedBlog();

	public Blog getByuId(int user_id);

	
	public void save(Blog blog);

	public Blog delete(int blogId);

	public void saveOrUpdate(Blog blog);
	
	public Blog getByBlogName(String title);

}
