package com.niit.collaboration.restfulservices;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collbackend.dao.FriendDAO;
import com.niit.collbackend.model.Friend;
import com.niit.collbackend.model.User;

@RestController
public class FriendController
{
@Autowired FriendDAO friendDAO;
@Autowired Friend friend;
public FriendDAO getFriendDAO() {
	return friendDAO;
}

public void setFriendDAO(FriendDAO friendDAO) {
	this.friendDAO = friendDAO;
}


@GetMapping("/friends")
public List<Friend> getCustomers() {
	return friendDAO.list();
}



@GetMapping("/friend/{userId}")
public List<Friend> getByUser(@PathVariable("userId") int userId) {
	return friendDAO.list(userId);
}



@GetMapping("/friends/{userName}")  
public List<Friend> geByID(@PathVariable("userName") String userName) {
	return friendDAO.getByFriendName(userName);
	
}

@GetMapping("/friendsAccepted/{friendName}")  
public List<Friend> geByFriendAccepted(@PathVariable("friendName") String friendName) {
	return friendDAO.getByFriendAccepted(friendName);
	
}



@PostMapping("/friends")
public ResponseEntity createFriend(@RequestBody User friendUser, HttpSession session) {
	User user = (User) session.getAttribute("user");   
	friend.setUserId(user.getUserId());
	friend.setUserName(user.getUser_name());
	friend.setStatus("P");
	friend.setFriendId(friendUser.getUserId());
	friend.setFriendName(friendUser.getUser_name());
	friend.setIsOnline("TRUE");

	friendDAO.save(friend);

	return new ResponseEntity(friend, HttpStatus.OK);
}
@GetMapping("/friendsAccepted1/{name}")  
public List<Friend> getByFriendAccepted1(@PathVariable("name") String name) {
	
	
	List<Friend> friendList1 = friendDAO.getByFriendAccepted1(name);
	
	return friendList1;
}

@PutMapping("/friendAccept")
public ResponseEntity acceptFriend(@RequestBody Friend friend){
	
	friend.setStatus("A");
	friend = friendDAO.saveOrUpdate(friend);
	
	return new ResponseEntity(friend, HttpStatus.OK);
}

@DeleteMapping("/friends/{fid}")
public ResponseEntity deleteFriend(@PathVariable int fid) {
	Friend friend=friendDAO.getByFriendId(fid);
		if (friend==null) {
		return new ResponseEntity("No friend found for fid " + fid, HttpStatus.NOT_FOUND);
	}
		friendDAO.delete(fid);
	return new ResponseEntity(fid, HttpStatus.OK);

}
	
	
}

