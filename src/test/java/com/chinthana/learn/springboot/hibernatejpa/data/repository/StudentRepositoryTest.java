package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;	

	@Autowired
	EntityManager em;
	
	@Test	
	@Transactional
	public void getStudentTest() {
		//Important!!!
		//The One to One relationship default will be eager fetching, that means when we retrieve a 
		//Student the Passport details will also be retrieved.
		//If we set passport to fetchType-Lasy we should use @Transactional annotation to the method since the repository.findById() will be 
		// executed on separate transaction.
		//See removing @Transactional, it will throw an exception { -org.hibernate.LazyInitializationException- no Session }
		Student student = repository.findById(20001);
		logger.info("Student Details:" + student);
		logger.info("Student Passport Details:" + student.getPassport());
		logger.info("Student Course Details:" + student.getCourses());		
	}
	
	@Test
	@DirtiesContext // This annotation is rollback the changes when the test method is completed	
	//When we are not using @Transctional, each data base updates will be executed within an independent transaction and will commit accordingly.
	public void withOutTarnsActinalAnnotation() {
		Student student = repository.findById(20001);
		student.setName("Ranga - Updated");
		//Student will persist to data base
		repository.save(student);
		student.setName("Ranga - Updated - Again");
		//Student will persist to data base
		repository.save(student);
		student.setName("Ranga - Updated - Again - Again");
		//Student will persist to data base
		repository.save(student);
	}
	
	@Test
	@Transactional
	//When we are using @Transctional, each and all database updates will be executed within a single transaction.
	//As soon as you define a transaction, the persistaceContext also be created.So the persistenceContext is the place where the all
	//Entities which are going to be operated are being stored. 
	public void withTarnsActinalAnnotation() {
		Student student = em.find(Student.class, 20001);
		student.setName("Ranga - Updated");
		//Student will persist to data base
		em.persist(student);
		student.setName("Ranga - Updated - Again");
		//Student will persist to data base
		em.persist(student);
		student.setName("Ranga - Updated - Again - Again");
		//Student will persist to data base
		em.persist(student);
	}
	
		
	@Test
	@Transactional
	//When we are using @Transctional, and if we want to commit in a middle the transaction we can use em.flush();
	public void withTarnsActinalAnnotationAndCommitOccatinally() {
		Student student = em.find(Student.class, 20001);
		student.setName("Ranga - Updated");
		//Student will persist to data base
		em.persist(student);
	    em.flush();
		
		student.setName("Ranga - Updated - Again");
		//Student will persist to data base
		em.persist(student);
		em.flush();
		
		student.setName("Ranga - Updated - Again - Again");
		//Student will persist to data base
		em.persist(student);
		em.flush();
	}
	
	@Test
	@Transactional
	//Note the ManyToMany relationship fetchType is default LASY
	public void getStudentWithCourses() {
		Student student = em.find(Student.class, 20001);
		List<Course> courses = student.getCourses();
		logger.info("Courses of student 20001:" + courses);		
	}
	
	@Test
	@Transactional
	//Note the ManyToMany relationship fetchType is default LASY
	public void getCourseWithStudents() {
		Course course = em.find(Course.class, 10001);
		List<Student> students = course.getStudents();
		logger.info("Courses of student 20001:" + students);
	}
		
}