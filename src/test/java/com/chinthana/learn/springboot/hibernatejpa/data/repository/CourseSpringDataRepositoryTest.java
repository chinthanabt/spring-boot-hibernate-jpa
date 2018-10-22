package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthana.learn.springboot.hibernatejpa.SpringBootHibernateJpaApplication;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class CourseSpringDataRepositoryTest {
	
	@Autowired
	private CourseSpringDataRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findById() {
		Optional<Course> findById = repository.findById(10001);
		if(findById.isPresent())
			logger.info("Course:" + findById);
	}
	
	@Test
	public void playingWithCrudOparations() {
		Course course = new Course("Python in 20 steps");
		repository.save(course);
		
		course.setName("Python in 20 steps updated");		
		repository.save(course);
		
		logger.info("All course:" + repository.findAll());
		
		logger.info("All course count:" + repository.count());
		
	}
	
	@Test
	public void sort() {
		Sort sortDesc = new Sort(Sort.Direction.DESC, "name");
		logger.info("All course:" + repository.findAll(sortDesc));		
	}
	
	@Test
	public void pagination() {
		PageRequest page = PageRequest.of(0, 2);	
		Page<Course> firstPage = repository.findAll(page);		
		logger.info("First page of course:" + firstPage.getContent());
		
		Pageable nextPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(nextPageable);
		logger.info("Second page of course:" + secondPage.getContent());
		
	}

	@Test
	public void findByName() {
		List<Course> findByName = repository.findByName("Springboot JPA");
		logger.info("Second page of course:" + findByName);		
	}
	
	@Test
	public void courseWithNameJava() {
		List<Course> findByName = repository.courseWithNameJava();
		logger.info("Second page of course:" + findByName);		
	}

}
