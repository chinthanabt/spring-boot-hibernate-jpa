package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;

@Repository
@Transactional
public interface CourseSpringDataRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByName(String name);
	
	@Query("select c from Course c where name like '%Java%'")
	List<Course> courseWithNameJava();
	
}
