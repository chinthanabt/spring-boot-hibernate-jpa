package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthana.learn.springboot.hibernatejpa.SpringBootHibernateJpaApplication;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;
	
	@Test
	public void findByIdTest() {
		logger.info("CourseRepositoryTest.findByIdTest() is running...");
		assertEquals("Hibernate JPA", courseRepository.findById(10001).getName());
	}
	
	@Test
	@DirtiesContext
	//When using @DirtiesContext, after test is completed the database changes will roll back
	public void deleteByIdTest() {
		logger.info("CourseRepositoryTest.deleteByIdTest() is running...");
		courseRepository.deleteById(10004);
		assertNull(courseRepository.findById(10004));
	}
	
	
	@Test
	@DirtiesContext
	public void saveTest() {
		logger.info("CourseRepositoryTest.saveTest() is running...");		
		//get a Course
		Course course = courseRepository.findById(10001);
		//Check the curse
		assertEquals("Hibernate JPA", course.getName());
		
		//Update the course
		course.setName("Hibernate JPA Updated");		
		courseRepository.save(course);
		
		//get the Course
		Course course1 = courseRepository.findById(10001);
		//Check the curse
		assertEquals("Hibernate JPA Updated", course1.getName());		
	}	
	
}
