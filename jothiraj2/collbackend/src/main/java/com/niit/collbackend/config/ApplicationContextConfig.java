package com.niit.collbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collbackend.dao.BlogCommentDAO;
import com.niit.collbackend.dao.BlogDAO;
import com.niit.collbackend.dao.ForumCommentDAO;
import com.niit.collbackend.dao.ForumDAO;
import com.niit.collbackend.dao.FriendDAO;
import com.niit.collbackend.dao.JobDAO;
import com.niit.collbackend.dao.UserDAO;
import com.niit.collbackend.daoimpl.BlogCommentDAOImpl;
import com.niit.collbackend.daoimpl.BlogDAOImpl;
import com.niit.collbackend.daoimpl.ForumCommentDAOImpl;
import com.niit.collbackend.daoimpl.ForumDAOImpl;
import com.niit.collbackend.daoimpl.FriendDAOImpl;
import com.niit.collbackend.daoimpl.JobDAOImpl;
import com.niit.collbackend.daoimpl.UserDAOImpl;
import com.niit.collbackend.model.Blog;
import com.niit.collbackend.model.BlogComment;
import com.niit.collbackend.model.Forum;
import com.niit.collbackend.model.ForumComment;
import com.niit.collbackend.model.Friend;
import com.niit.collbackend.model.Job;
import com.niit.collbackend.model.User;

@Configuration
@ComponentScan("com.niit.collbackend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("JOEPROJECT");
		dataSource.setPassword("oracle");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(User.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {

		return new BlogDAOImpl(sessionFactory);
	}
	
	
	@Autowired(required = true)
	@Bean(name = "blogCommentDAO")
	public BlogCommentDAO getBlogCommentDAO(SessionFactory sessionFactory) {

		return new BlogCommentDAOImpl(sessionFactory);
	}
	
	
	@Autowired(required = true)
	@Bean(name = "forumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {

		return new ForumDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "forumCommentDAO")
	public ForumCommentDAO getForumCommentDAO(SessionFactory sessionFactory) {

		return new ForumCommentDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "friendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {

		return new FriendDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "jobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory) {

		return new JobDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {

		return new UserDAOImpl(sessionFactory);
	}


}
