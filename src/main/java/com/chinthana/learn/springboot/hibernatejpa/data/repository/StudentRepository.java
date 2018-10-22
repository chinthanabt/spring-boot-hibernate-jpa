package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Course;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Passport;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	//Find by id using JPA
	public Student findById(int id) {		
		return entityManager.find(Student.class, id);
	}
	
	//Typed query using JPQL
	public List<Student> findByNameTypedQuery() {
		TypedQuery<Student> typedQurary = entityManager.createQuery("select c from Student c where name like '%boot%'", Student.class);
		return typedQurary.getResultList();
	}
	
	//Named query using JPQL
	public List<Student> findAll() {
		TypedQuery<Student> namedQueary = entityManager.createNamedQuery("Find_All_Students", Student.class);
		return namedQueary.getResultList();
	}
	
	//Named query using JPQL
	public List<Student> findByName() {
		TypedQuery<Student> namedQueary = entityManager.createNamedQuery("Find_Students_By_Name", Student.class);
		return namedQueary.getResultList();
	}
	
	//Native Query
	public List<Student> findByName_native_query() {
		Query nativeQueary = entityManager.createNativeQuery("select * from student", Student.class);
		List<Student> list = nativeQueary.getResultList();
		return list;
	}
	
	//Native Query with named parameter
	public List<Student> findByName_native_query_with_named_parameter() {
		Query nativeQueary = entityManager.createNativeQuery("select * from student where id =:id", Student.class);
		nativeQueary.setParameter("id", 10001);
		List<Student> list = nativeQueary.getResultList();
		return list;
	}
	
	// Native Query with named parameter
	//We can have to native queries when doing the mass updates.
	public int mass_update() {
		Query nativeQueary = entityManager.createNativeQuery("update COURSE_DETAILS set updated_date = sysdate", Student.class);	
		return nativeQueary.executeUpdate();
	}	
	
	//Update using JPA Entity manager
	public Student update(Student student) {		
		return entityManager.merge(student);
	}	
	
	//Update/Save using JPA Entity manager
	public void save(Student student) {		
		if(findById(student.getId()) == null)
			entityManager.persist(student);
		else 
			entityManager.merge(student);		
	}
	
	//Delete using JPA Entity manager
	public void deleteById(int id) {
		entityManager.remove(findById(id));
	}
	
	public void playWithEntityManager() {
		Student student = new Student("Web Services");
		entityManager.persist(student);		
		entityManager.flush();		
		
		Student student1 = new Student("Angular JS");
		entityManager.detach(student1);		
		
		student1.setName("Angular JS - Updated");
		student.setName("Web Services - Updated");		
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("E23452452525");
		entityManager.persist(passport);
		Student student = new Student("Amaan");
		student.setPassport(passport);
		entityManager.persist(student);
	}
	
	public Student getStudent() {	
		Student student = entityManager.find(Student.class, 20001);	
		return student;
	}	
	
	public void addStudentWithCourse(Student student, Course course) {		
		course.setStudent(student);
		student.setCourse(course);
		entityManager.persist(student);
		entityManager.persist(course);
	}

}
