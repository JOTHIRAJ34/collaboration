package com.niit.collbackend.dao;

import java.util.List;

import com.niit.collbackend.model.Forum;

public interface ForumDAO
{
	public List<Forum> list();

	public List<Forum> getAcceptedList();

	public List<Forum> getNotAcceptedList();

	public Forum get(int forumid);

	public void save(Forum forum);

	public Forum saveOrUpdate(Forum forum);

	public void delete(int forumid);
}
