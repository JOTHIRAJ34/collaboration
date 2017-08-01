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

import com.niit.collbackend.dao.BlogDAO;
import com.niit.collbackend.model.Blog;
import com.niit.collbackend.model.User;

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
	@GetMapping("/acceptedblog")
	public ResponseEntity<List<Blog>> getAcceptedBlog() {
		List<Blog> listblog = blogDAO.getAcceptedBlog();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	
	@GetMapping("/notAcceptedblog")
	public ResponseEntity<List<Blog>> getNotAcceptedBlog() {
		List<Blog> listblog = blogDAO.getNotAcceptedBlog();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	
	@GetMapping("/blogId/{blogId}")
	public ResponseEntity<Blog> getByBlogId(@PathVariable("blogId")int blogId)
	{
		Blog blog=blogDAO.getByBlogId(blogId);
		if (blog==null)
		{
			return new ResponseEntity("no Blog found for blogId"+blogId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@GetMapping("/blogName/{title}")
	public ResponseEntity<Blog> getblogByBlogName(@PathVariable("title") String title) {
		Blog blog = blogDAO.getByBlogName(title);
		if (blog == null) {
			return new ResponseEntity("No Blog found for title " + title, HttpStatus.NOT_FOUND);
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
	public ResponseEntity<Blog> update (@PathVariable("blogId")int blogId,@RequestBody Blog blog)
	{
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@DeleteMapping("/blog/{blogId}")
	public ResponseEntity<Blog> deleteBlog (@PathVariable("blogId")int blogId)
	{
		Blog blog=blogDAO.getByBlogId(blogId);
		if(blog==null)
		{
			return new ResponseEntity("no blog found form blogId" +blogId,HttpStatus.NOT_FOUND);
		
		}
		blogDAO.delete(blogId);
		return new ResponseEntity("id is deleted" +blogId,HttpStatus.OK);
	}
	
	@PutMapping("/acceptBlog")
	public ResponseEntity acceptBlog(@RequestBody Blog blog){
		blog.setStatus("A");
		 blogDAO.saveOrUpdate(blog);
		return new ResponseEntity(blog, HttpStatus.OK);
	}
	
	@PostMapping("/blogs")
	public ResponseEntity save(@RequestBody Blog blog, HttpSession session) {
		blog.setStatus("NA");
		User user = (User) session.getAttribute("user");   
		System.out.println(blog.getTitle());
		blog.setUserId(user.getUserId());
		blog.setUser_name(user.getUser_name());
    	blogDAO.save(blog);
				
		return new ResponseEntity(blog, HttpStatus.OK);
	}
}
