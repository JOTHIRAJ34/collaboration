package com.niit.collbackend.dao;

import java.util.List;

import com.niit.collbackend.model.User;

public interface UserDAO 
{
	public List<User> list();
	
	public User getById(int userId);

	public List<User> getbyUsername(String user_name);
	
	public User getByEmail(String email_id);

	

	public User delete(int userId);

	
	public User login(User user);
	
	public void save(User user);
	
	public void saveOrUpdate(User user);
	
	List<String> getOnlineUsers();
	
}
