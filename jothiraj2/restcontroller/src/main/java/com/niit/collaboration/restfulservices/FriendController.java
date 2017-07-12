package com.niit.collaboration.restfulservices;

import java.util.List;

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

@RestController
public class FriendController
{
@Autowired FriendDAO friendDAO;
	
	@GetMapping("/friend")
	public List<Friend> list(){
		List<Friend> freindList=friendDAO.list();
		return freindList;
		
	}
	
	@GetMapping("/friend/{friendId}")
	public Friend getByFriendId(@PathVariable("friendId") int id) {
		Friend friendList = friendDAO.getByFriendId(id);

		return friendList;
	}
	
	@PostMapping("/friend")
	public ResponseEntity<Friend> save(@RequestBody Friend friend) {
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@PutMapping("/friend")
	public ResponseEntity<Friend> update(@RequestBody Friend friend) {
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@DeleteMapping("/friends/{friendId}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendId") int id) {
		Friend friend = friendDAO.getByFriendId(id);
		if (friend == null) {
			return new ResponseEntity("No Friend found for ID " + id, HttpStatus.NOT_FOUND);
		}
		friendDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}
	
}

