package com.chinthana.learn.springboot.hibernatejpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.FullTimeEmployee;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.PartTimeEmployee;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Passport;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Review;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Student;
import com.chinthana.learn.springboot.hibernatejpa.data.repository.CourseRepository;
import com.chinthana.learn.springboot.hibernatejpa.data.repository.EmployeeRepository;
import com.chinthana.learn.springboot.hibernatejpa.data.repository.PassportRepository;
import com.chinthana.learn.springboot.hibernatejpa.data.repository.ReviewRepository;
import com.chinthana.learn.springboot.hibernatejpa.data.repository.StudentRepository;

@SpringBootApplication
public class SpringBootHibernateJpaApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	PassportRepository passportRepository;	
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		//Find by Name typed JPQL		
//		logger.info("Find By name like 'boot':" + courseRepository.findByNameTypedQuery());
//		
//		//Find by id		
//		logger.info("Course 10001 Info:" + courseRepository.findById(10001));
//		
//		//Find All
//		logger.info("All Courses:" + courseRepository.findAll());
//		
//		//delete by id
//		//courseRepository.deleteById(10002);
//		
//		//save/update
//		Course newCourse = new Course("Core Java");
//		courseRepository.save(newCourse);
//		
//		//Different kind of operations in entity manager
//		courseRepository.playWithEntityManager();
//		//Find by name
//		logger.info("Course By Name" + courseRepository.findByName());
//		//Mass update
//		logger.info("Mass update" + courseRepository.mass_update());
//				
//		//Save Student with Passport
//		studentRepository.saveStudentWithPassport();
//		
//		//If an Exception occurred while in a transaction even flushed data also be rolled back 
//		try {
//			passportRepository.updatePassport();
//		} catch (Exception e) {
//			logger.error("Exception occured:" + e);
//		}
//		
//		//Get Student with Passport
//		Student student = studentRepository.getStudent();
//		logger.info("Student by id 4" + student);
//		//Here we can get passport from the student since the OneToOne relationship fetch type is default EAGER
//		Passport passport = student.getPassport();
//		logger.info("Passport of student 4" + passport);		
//		
//		//Get Passport
//		Passport passport1 = passportRepository.findById(40001);
//		logger.info("Student by id 4" + passport1);
//		//Here we can get student from the passport since the OneToOne relationship fetch type is default EAGER	
//		Student s1 = passport1.getStudent();
//		logger.info("Student of passport 40001" + s1);
//		
//		//Add review to course
//		//courseRepository.addHardCodedReviewsToCourse();
//		
//		//Add reviews to a Course
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "This Course is Excellent"));
//		reviews.add(new Review("5", "Awasome Course"));
//		reviews.add(new Review("4", "Exellent Course"));		
//	    courseRepository.addReviewsToCourse(10001, reviews);
//		
//		//Get review by id and get course from review object
//	    Review review = reviewRepository.findById(50001);
//	    //Here we can get the course because ManyToOne is default EAGER like OneToOne mapping.
//		Course course = review.getCourse();
//		logger.info("Review by id 40001" + review);
//		logger.info("Course by id 10001" + course);	
//		
//		//Insert student with courses
//		studentRepository.addStudentWithCourse(new Student("Jack"), new Course("Hibernate in 100 steps"));
		
//		//Save employees
	//	employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
	//	employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("5000")));
		
		//logger.info("All employees:" + employeeRepository.getAll());
		
		
		//Get employees
	//	logger.info("All FullTimeemployees:" + employeeRepository.getAllFullTime());
	//	logger.info("All PertTimeemployees:" + employeeRepository.getAllPartTime());
		
	}
}
