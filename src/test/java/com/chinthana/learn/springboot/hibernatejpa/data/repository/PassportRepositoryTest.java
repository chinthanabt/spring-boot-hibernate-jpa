package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthana.learn.springboot.hibernatejpa.SpringBootHibernateJpaApplication;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Passport;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class PassportRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PassportRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test	
	@Transactional
	public void getPassportTest() {		
		Passport passport = em.find(Passport.class, 40001);
		logger.info("Passport Details:" + passport);
		logger.info("Student details of Passport:" + passport.getStudent());
	}
	
	@Test	
	@Transactional
	public void updatePassport() {		
		Passport passport = em.find(Passport.class, 40001);
		passport.setName("E12234234-Updated");
		em.flush();
		
		passport.setName("E12234234-Updated-Upated");
		em.flush();
		
		try {
			throw new Exception();
		} catch (Exception e) {
			Passport passport1 = em.find(Passport.class, 40001);
			logger.info("Passport Details:" + passport1);
		}		
	}
	
	@Test
	//Hope we do not need a transaction here even we used lazy loading of Student.
	public void transactionTest() {		
		Passport passport = em.find(Passport.class, 40001);
		Student student = passport.getStudent();
		Course course = em.find(Course.class, 10001);
		
		logger.info("Passport Details:" + passport);
		logger.info("Student Details:" + student);
		logger.info("Student Details:" + course);
	}
}