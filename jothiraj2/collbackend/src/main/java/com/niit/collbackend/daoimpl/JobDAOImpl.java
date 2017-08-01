package com.niit.collbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collbackend.dao.JobDAO;
import com.niit.collbackend.model.Job;

@Repository("JobDAO")
@Transactional
public class JobDAOImpl implements JobDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Job> list() {
		List<Job> jobList = (List<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return jobList;
	}
	@Transactional
	public void save(Job job) {
		sessionFactory.getCurrentSession().save(job);

	}
	@Transactional
	public void saveOrUpdate(Job job) {
		sessionFactory.getCurrentSession().saveOrUpdate(job);

	}
	@Transactional
	public Job getByJobId(int jobid) {
		Job listById = (Job) sessionFactory.getCurrentSession().get(Job.class, jobid);

		return listById;
	}
	@Transactional
	public void delete(int jobid) {
		sessionFactory.getCurrentSession().delete(jobid);

	}
}
