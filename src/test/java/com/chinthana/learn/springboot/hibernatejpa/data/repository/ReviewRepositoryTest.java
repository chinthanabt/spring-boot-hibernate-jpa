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
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthana.learn.springboot.hibernatejpa.SpringBootHibernateJpaApplication;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class ReviewRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	//Note that OneToMany relationship fetch type default is LASY
	public void getReviewsByCourseId() {
		Course course = em.find(Course.class, 10001);
		List<Review> reviews = course.getReviews();
		logger.info("Reviews: " + reviews);
	}
	
	@Test
	@Transactional
	//Note that ManyToOne relationship fetch type default is EAGER
	public void getCourseByReviewId() {
		Review review = em.find(Review.class, 50001);
		Course course = review.getCourse();
		logger.info("Course of review: " + course);
	}
	
}