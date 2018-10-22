package com.chinthana.learn.springboot.hibernatejpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class JPQLTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void getCourseWhichDoesNotHaveStudentsTest() {		
		TypedQuery<Course> typedQurary = em.createQuery("select c from Course c where c.students is empty", Course.class);
		List<Course> courses= typedQurary.getResultList();
		logger.info("Course:" + courses);
	}
	
	@Test
	public void getCourseWhichHaveAtLeast2StudentsTest() {		
		TypedQuery<Course> typedQurary = em.createQuery("select c from Course c where size(c.students) > 2", Course.class);
		List<Course> courses= typedQurary.getResultList();
		logger.info("Course:" + courses);
	}
	
	@Test
	public void getCourseOrderByStudentsTest() {		
		TypedQuery<Course> typedQurary = em.createQuery("select c from Course c order by size(c.students)", Course.class);
		List<Course> courses= typedQurary.getResultList();
		logger.info("Course:" + courses);
	}
	
	@Test
	public void getCourseCountTest() {		
		TypedQuery<Course> typedQurary = em.createQuery("select count(c.id) from Course c", Course.class);
		List<Course> courses= typedQurary.getResultList();
		logger.info("Course:" + courses);
	}
	
	@Test
	public void join_test() {		
		Query query = em.createQuery("select s, c from Student s join s.courses c");
		List<Object[]> results= query.getResultList();
		for(Object[] result: results) {
			logger.info("Course:" + result[0] + "Student:" +  result[1]);
		}
	}
	
	@Test
	public void left_join_test() {		
		Query query = em.createQuery("select c, s from Course c left join c.students s");
		List<Object[]> results= query.getResultList();
		for(Object[] result: results) {
			logger.info("Course:" + result[0] + "Student:" +  result[1]);
		}
	}
	
	@Test
	public void criteria_queary_test() {		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> from = createQuery.from(Course.class);
		TypedQuery<Course> queary = em.createQuery(createQuery.select(from));
		List<Course> resultList = queary.getResultList();
		logger.info("Courses:" + resultList);
	}	
	
}