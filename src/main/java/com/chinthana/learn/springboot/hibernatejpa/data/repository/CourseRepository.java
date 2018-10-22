package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Review;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	//Find by id using JPA	
	public Course findById(int id) {		
		return entityManager.find(Course.class, id);
	}
	
	//Typed query using JPQL
	public List<Course> findByNameTypedQuery() {
		TypedQuery<Course> typedQurary = entityManager.createQuery("select c from Course c where name like '%boot%'", Course.class);
		return typedQurary.getResultList();
	}
	
	//Named query using JPQL
	public List<Course> findAll() {
		TypedQuery<Course> namedQueary = entityManager.createNamedQuery("Find_All_Courses", Course.class);
		return namedQueary.getResultList();
	}
	
	//Named query using JPQL
	public List<Course> findByName() {
		TypedQuery<Course> namedQueary = entityManager.createNamedQuery("Find_Courses_By_Name", Course.class);
		return namedQueary.getResultList();
	}
	
	//Native Query
	public List<Course> findByName_native_query() {
		Query nativeQueary = entityManager.createNativeQuery("select * from course", Course.class);
		List<Course> list = nativeQueary.getResultList();
		return list;
	}
	
	//Native Query with named parameter
	public List<Course> findByName_native_query_with_named_parameter() {
		Query nativeQueary = entityManager.createNativeQuery("select * from course where id =:id", Course.class);
		nativeQueary.setParameter("id", 10001);
		List<Course> list = nativeQueary.getResultList();
		return list;
	}
	
	// Native Query with named parameter
	//We can have to native queries when doing the mass updates.
	public int mass_update() {
		Query nativeQueary = entityManager.createNativeQuery("update COURSE_DETAILS set updated_date = sysdate", Course.class);	
		return nativeQueary.executeUpdate();
	}	
	
	//Update using JPA Entity manager
	public Course update(Course course) {		
		return entityManager.merge(course);
	}	
	
	//Update/Save using JPA Entity manager
	public void save(Course course) {		
		if(findById(course.getId()) == null)
			entityManager.persist(course);
		else 
			entityManager.merge(course);		
	}
	
	//Delete using JPA Entity manager
	public void deleteById(int id) {
		entityManager.remove(findById(id));
	}
	
	public void playWithEntityManager() {
		Course course = new Course("Web Services");
		entityManager.persist(course);		
		entityManager.flush();		
		
		Course course1 = new Course("Angular JS");
		entityManager.detach(course1);		
		
		course1.setName("Angular JS - Updated");
		course.setName("Web Services - Updated");
		
	}
	
	public void addHardCodedReviewsToCourse() {
		Course course = entityManager.find(Course.class, 10001);		
		Review review1 = new Review("5", "This Course is Excellent");
		Review review2 = new Review("5", "Awasome");
		
		//Set the relationships
		course.addReview(review1);
		course.addReview(review2);
		review1.setCourse(course);
		review2.setCourse(course);
		
		//Persist to the database
		entityManager.persist(review1);
		entityManager.persist(review1);
	}
	
	public void addReviewsToCourse(int courseId, List<Review> reviews) {
		Course course = entityManager.find(Course.class, 10001);

		for (Review review : reviews) {
			// Set the relationships
			course.addReview(review);
			review.setCourse(course);

			// Persist to the database
			entityManager.persist(review);
		}

	}

}
