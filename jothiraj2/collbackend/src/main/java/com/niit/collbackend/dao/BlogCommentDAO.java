package com.niit.collbackend.dao;

import java.util.List;

import com.niit.collbackend.model.BlogComment;

public interface BlogCommentDAO
{
	
	public List<BlogComment> list();
	
	public List<BlogComment> getById(int blogId);
	
	public BlogComment getByBId(int bcid);
	
	public void save(BlogComment blogComment);
	
	public void saveOrUpdate(BlogComment blogComment);
	
	public BlogComment delete(int bcid);


}
