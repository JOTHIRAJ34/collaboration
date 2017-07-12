package com.niit.collbackend.dao;

import java.util.List;

import com.niit.collbackend.model.Friend;

public interface FriendDAO
{
	public List<Friend> list();

	public List<Friend> list(int friendId);

	public void save(Friend friend);

	public void saveOrUpdate(Friend friend);

	public Friend getByFriendId(int id);

	public List<Friend> getByFriendName(String name);

	public List<Friend> getByFriendAccepted(String name);

	public void delete(int friendId);

}
