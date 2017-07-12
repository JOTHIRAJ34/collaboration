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

import com.niit.collbackend.dao.BlogDAO;
import com.niit.collbackend.model.Blog;

@RestController
public class BlogController {
	@Autowired 
	private BlogDAO blogDAO;
	
	@GetMapping("/blogs")
	public List<Blog> getBlogs()
{
   List<Blog>blogList=blogDAO.list();
   return blogList;
}
	@GetMapping("/blogId/{blogId}")
	public ResponseEntity<Blog> getByBlogId(@PathVariable("blogId")int id)
	{
		Blog blog=blogDAO.getByBlogId(id);
		if (blog==null)
		{
			return new ResponseEntity("no Blog found for id"+id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@GetMapping("/blogName/{blog_name}")
	public ResponseEntity<Blog> getblogByBlogName(@PathVariable("blog_name") String blog_name) {
		Blog blog = blogDAO.getByBlogName(blog_name);
		if (blog == null) {
			return new ResponseEntity("No Blog found for Blog Name " + blog_name, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}
	
	@PostMapping("/blog")
	public ResponseEntity<Blog> save(@RequestBody Blog blog)
	{
		blogDAO.save(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@PutMapping("/blog/{blogId}")
	public ResponseEntity<Blog> update (@PathVariable("blogId")int id,@RequestBody Blog blog)
	{
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@DeleteMapping("/blog/{blogId}")
	public ResponseEntity<Blog> deleteBlog (@PathVariable("blogId")int id)
	{
		Blog blog=blogDAO.getByBlogId(id);
		if(blog==null)
		{
			return new ResponseEntity("no blog found form id" +id,HttpStatus.NOT_FOUND);
		
		}
		blogDAO.delete(id);
		return new ResponseEntity("id is deleted" +id,HttpStatus.OK);
	}
}
