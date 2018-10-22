package com.chinthana.learn.springboot.hibernatejpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class CriteriaQueryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	
	@Test
	public void criteria_queary_select_all_from_course() {
		
		//select c from Course c
		
		//1. User CriteriaBuilder to create Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define Root for tables which are involved in the query
		Root<Course> from = cq.from(Course.class);
		
		//3.Define predicates using Criteria builder.
		
		//4.Add predicates to the Criteria query	
		
		
		//5.Build the TypedQuery using em and and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(from));
		List<Course> resultList = query.getResultList();
		logger.info("Course List" + resultList);
	}
	
	@Test
	public void criteria_queary_select_course_like() {
		
		//select c from Course c where c.name like '%JPA%'
		
		//1. User CriteriaBuilder to create Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define Root for tables which are involved in the query
		Root<Course> from = cq.from(Course.class);
		
		//3.Define predicates using Criteria builder.
		Predicate likeJPA = cb.like(from.get("name"), "%JPA%");
		
		//4.Add predicates to the Criteria query
		cq.where(likeJPA);
		
		
		//5.Build the TypedQuery using em and and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(from));
		List<Course> resultList = query.getResultList();
		logger.info("Course List like %JPA%" + resultList);
	}
	
	@Test
	public void criteria_queary_select_course_without_students() {
		
		//select c from Course c where c.students is empty
		
		//1. User CriteriaBuilder to create Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define Root for tables which are involved in the query
		Root<Course> from = cq.from(Course.class);
		
		//3.Define predicates using Criteria builder.
		Predicate withoutStudents = cb.isEmpty(from.get("students"));
		
		//4.Add predicates to the Criteria query
		cq.where(withoutStudents);		
		
		//5.Build the TypedQuery using em and and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(from));
		List<Course> resultList = query.getResultList();
		logger.info("Course List like %JPA%" + resultList);
	}
	
	
	@Test
	public void criteria_queary_join() {
		
		//select c from Course c where c.students is empty
		
		//1. User CriteriaBuilder to create Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define Root for tables which are involved in the query
		Root<Course> from = cq.from(Course.class);
		
		//3.Define predicates using Criteria builder.
		Join<Object, Object> join = from.join("students");
		
		//4.Add predicates to the Criteria query
			
		
		//5.Build the TypedQuery using em and and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(from));
		List<Course> resultList = query.getResultList();
		logger.info("Course List like %JPA%" + resultList);
	}
	
	@Test
	public void criteria_queary_left_join() {
		
		//select c from Course c where c.students is empty
		
		//1. User CriteriaBuilder to create Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2.Define Root for tables which are involved in the query
		Root<Course> from = cq.from(Course.class);
		
		//3.Define predicates using Criteria builder.
		Join<Object, Object> join = from.join("students", JoinType.LEFT);
		
		//4.Add predicates to the Criteria query			
		
		//5.Build the TypedQuery using em and and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(from));
		List<Course> resultList = query.getResultList();
		logger.info("Course List like %JPA%" + resultList);
	}
	
}